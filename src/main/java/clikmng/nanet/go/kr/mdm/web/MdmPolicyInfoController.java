package clikmng.nanet.go.kr.mdm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.EgovProperties;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mdm.model.MdmDetailCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmIsViewVO;
import clikmng.nanet.go.kr.mdm.model.MdmOrgCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutDocTypeVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSeedVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSiteVO;
import clikmng.nanet.go.kr.mdm.model.MdmPolicyInfoFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmPolicyInfoVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.service.MdmFileMngService;
import clikmng.nanet.go.kr.mdm.service.MdmFileMngUtil;
import clikmng.nanet.go.kr.mdm.service.MdmPolicyInfoService;
import clikmng.nanet.go.kr.mdm.service.MdmProperties;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyCodeService;
import clikmng.nanet.go.kr.mdm.utl.MdmCalUtil;
import clikmng.nanet.go.kr.mdm.utl.MdmExcelUtil;
import clikmng.nanet.go.kr.mdm.utl.MdmFtp;
import clikmng.nanet.go.kr.mdm.utl.MdmMd5Sha256;
import clikmng.nanet.go.kr.mdm.utl.MdmPaging;
import clikmng.nanet.go.kr.mdm.utl.MdmStrUtil;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 메타데이터 관리를 처리하는 Controller 클래스
 */
@Controller
public class MdmPolicyInfoController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "MdmPolicyInfoService")
    private MdmPolicyInfoService mdmPolicyInfoService;

    @Resource(name = "MdmTnsrAsmblyCodeService")
    private MdmTnsrAsmblyCodeService mdmTnsrAsmblyCodeService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="CmmUseService")
	private CmmUseService cmmUseService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "MdmFileMngService")
    private MdmFileMngService fileMngService;

    @Resource(name = "MdmFileMngUtil")
    private MdmFileMngUtil fileUtil;
    
    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

    /**
     * 지방정책정보 목록 조회
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfoList"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoList.do")
    public String MdmPolicyInfoList(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
    	if( mdmSearchVO.getMdmAdm() == null || mdmSearchVO.getMdmAdm().equals("") ) {
    		mdmSearchVO.setMdmAdm("PolicyInfo");
    	}

    	if( !mdmSearchVO.getSchBrtcCode().equals("") && mdmSearchVO.getSchLoAsmCode().equals("") ) {
    		mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode()+"001");
    	}
    	
    	//기본정렬 설정
    	if(mdmSearchVO.getSort() == null || mdmSearchVO.getSort().equals("")){
    		mdmSearchVO.setSort("REGDATE DESC");
    	}
    	
    	String schDt1 = mdmSearchVO.getSchDt1();
    	String schDt2 = mdmSearchVO.getSchDt2();
    	String schDt3 = mdmSearchVO.getSchDt3();
    	String schDt4 = mdmSearchVO.getSchDt4();
    	
    	if( mdmSearchVO.getSchKw() != null && !mdmSearchVO.getSchKw().trim().equals("") ) {
    		if( mdmSearchVO.getSchKey().equals("") || mdmSearchVO.getSchKey().equals("schTitle") ) {
    			mdmSearchVO.setSchTitle(mdmSearchVO.getSchKw());    			// 제목
    		}
    		else if( mdmSearchVO.getSchKey().equals("schContent") ) {
    			mdmSearchVO.setSchContent(mdmSearchVO.getSchKw());    	// 내용
    		}
    		else if( mdmSearchVO.getSchKey().equals("cnId") ) {
    			mdmSearchVO.setSchKw("'"+mdmSearchVO.getSchKw().replaceAll("'", "")+"'");		//문서번호
    			mdmSearchVO.setSchDt1("");
    			mdmSearchVO.setSchDt2("");
    			mdmSearchVO.setSchDt3("");
    			mdmSearchVO.setSchDt4("");
    		}
    	}
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep1List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep1List();  // 기관유형 1단계
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep2List = null;
    	if( mdmSearchVO.getSchOrgCodeStep1() != null && !mdmSearchVO.getSchOrgCodeStep1().trim().equals("") ) {
    		codeOrgCodeStep2List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep2List(mdmSearchVO.getSchOrgCodeStep1()); // 기관유형 2단계
    	}
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep3List = null;
    	if( mdmSearchVO.getSchOrgCodeStep2() != null && !mdmSearchVO.getSchOrgCodeStep2().trim().equals("") ) {
    		codeOrgCodeStep3List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep3List(mdmSearchVO.getSchOrgCodeStep2()); // 기관유형 3단계
    	}
    	
    	List<MdmOrgCodeVO> codeOrgSiteList = null;
    	if( mdmSearchVO.getSchOrgCodeStep3() != null && !mdmSearchVO.getSchOrgCodeStep3().trim().equals("") ) {
    		codeOrgSiteList = mdmTnsrAsmblyCodeService.selectMdmOrgSiteList(mdmSearchVO); // 기관명
    	}
    	
    	List<MdmOutSiteVO> siteList = null;
    	if( mdmSearchVO.getSchRegion() != null && !mdmSearchVO.getSchRegion().trim().equals("")  ) {
    		siteList = mdmTnsrAsmblyCodeService.selectMdmSiteList(mdmSearchVO.getSchRegion()); 
    	}
    	
    	List<MdmOutSeedVO> seedList = null;
    	if( !"".equals(mdmSearchVO.getSchSiteId())  ) {
    		MdmOutSeedVO seedVO = new MdmOutSeedVO();
    		seedVO.setREGION(mdmSearchVO.getSchRegion());
    		seedVO.setSITEID(mdmSearchVO.getSchSiteId());
    		seedList = mdmTnsrAsmblyCodeService.selectMdmSeedList(seedVO); 
    	}

    	List<MdmDetailCodeVO> codeRKS021AllList = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS021All();  // 자료유형
    	List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS025();        // 수집(지역)
    	
    	if( mdmSearchVO.getSchDflt() == null || !mdmSearchVO.getSchDflt().equals("N") ) { // 총 검색 건수(기본 1주일간)
    		MdmCalUtil calUtil = new MdmCalUtil();
    		calUtil.setDelimiter("-");
    		calUtil.setDecimalFormat("00");
    		String TODAY = calUtil.getToday();
    		
    		if(mdmSearchVO.getSchDt1() == null || "".equals(mdmSearchVO.getSchDt1())){
    			mdmSearchVO.setSchDt1(TODAY);
    		}
    		if(mdmSearchVO.getSchDt2() == null || "".equals(mdmSearchVO.getSchDt2())){
    			mdmSearchVO.setSchDt2(TODAY);
    		}
    		if(mdmSearchVO.getSchDt3() == null || "".equals(mdmSearchVO.getSchDt3())){
    			mdmSearchVO.setSchDt3(TODAY);
    		}
    		if(mdmSearchVO.getSchDt4() == null || "".equals(mdmSearchVO.getSchDt4())){
    			mdmSearchVO.setSchDt4(TODAY);
    		}
    	}

    	//수집기관, 등록기간 OR 연산을 위하여 추가
    	if( (!"".equals(mdmSearchVO.getSchDt1()) || !"".equals(mdmSearchVO.getSchDt2())) && (!"".equals(mdmSearchVO.getSchDt3()) || !"".equals(mdmSearchVO.getSchDt4())) ){
    		//수집, 등록 기간에 정보가 모두 존재할 경우
    		mdmSearchVO.setSchDtConditionOperators("Y");
    	}else if( "".equals(mdmSearchVO.getSchDt1()) && "".equals(mdmSearchVO.getSchDt2()) && "".equals(mdmSearchVO.getSchDt3()) && "".equals(mdmSearchVO.getSchDt4()) ){ 
    		//수집, 등록 기간에 정보가 모두 없을 경우
    		mdmSearchVO.setSchDtConditionOperators("");
    	}else{
    		//수집, 등록 기간에 정보가 한쪽만 존재할 경우
    		mdmSearchVO.setSchDtConditionOperators("N");
    	}
    	int policyInfoListTotCnt = 0;
    	policyInfoListTotCnt = mdmPolicyInfoService.selectMdmPolicyInfoListTotCnt(mdmSearchVO); // 총 검색 건수    		
    	
    	MdmPaging paging = new MdmPaging();
    	paging.setPagingCalc(policyInfoListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
    	paging.setParam(mdmSearchVO);
    	
    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVO.setLastRecord(paging.getLastRecord());
    	
    	List<MdmPolicyInfoVO> policyInfoList = null;
    	policyInfoList = mdmPolicyInfoService.selectMdmPolicyInfoList(mdmSearchVO);  // 검색 리스트
    	
    	if("cnId".equals(mdmSearchVO.getSchKey())){
    		mdmSearchVO.setSchKw(mdmSearchVO.getSchKw().replace("'", ""));
    		mdmSearchVO.setSchDt1(schDt1);
        	mdmSearchVO.setSchDt2(schDt2);
        	mdmSearchVO.setSchDt3(schDt3);
        	mdmSearchVO.setSchDt4(schDt4);
    	}
    	
    	int listStartNo = paging.getFirstRecord(); 
    	
		model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
		model.addAttribute("codeRKS021AllList", codeRKS021AllList);
		model.addAttribute("codeRKS025List", codeRKS025List);
		/*model.addAttribute("codeRKS022List", codeRKS022List);*/
		model.addAttribute("siteList", siteList);
		model.addAttribute("seedList", seedList);
		model.addAttribute("policyInfoListTotCnt", policyInfoListTotCnt);
		model.addAttribute("policyInfoList", policyInfoList);
		model.addAttribute("paginationInfo", paging.getPaging(mdmSearchVO));
		model.addAttribute("listStartNo", listStartNo);
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		model.addAttribute("codeOrgSiteList", codeOrgSiteList);
		
		String currentSearchCnList = "";
		for(MdmPolicyInfoVO vo : policyInfoList){
			currentSearchCnList += vo.getOUTBBS_CN() + ",";
		}
		
		if(!"".equals(currentSearchCnList))
			currentSearchCnList = currentSearchCnList.substring(0, currentSearchCnList.length()-1);
		
		model.addAttribute("cnList", currentSearchCnList.replace("'", ""));
		model.addAttribute("EndPage", paging.getTotalPages());
		
        return "clikMng/mdm/MdmPolicyInfoList";
    }
    
    /**
     * 지방정책정보 목록 조회(엑셀파일)
     * @param multiRequest
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfoList"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록 엑셀 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/ExcelMdmPolicyInfoList.do")
    public String ExcelMdmPolicyInfoList(final MultipartHttpServletRequest multiRequest, 
    															@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, 
    															ModelMap model) throws Exception {
    	
    	//기본정렬 설정
    	if(mdmSearchVO.getSort() == null || mdmSearchVO.getSort().equals("")){
    		mdmSearchVO.setSort("REGDATE DESC");
    	}
    	
    	/*
    	 * 검색 form 기본 정보 조회 S
    	 * */
    	if( mdmSearchVO.getMdmAdm() == null || mdmSearchVO.getMdmAdm().equals("") ) {
    		mdmSearchVO.setMdmAdm("PolicyInfo");
    	}

    	if( !mdmSearchVO.getSchBrtcCode().equals("") && mdmSearchVO.getSchLoAsmCode().equals("") ) {
    		mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode()+"001");
    	}
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep1List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep1List();  // 기관유형 1단계
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep2List = null;
    	if( mdmSearchVO.getSchOrgCodeStep1() != null && !mdmSearchVO.getSchOrgCodeStep1().trim().equals("") ) {
    		codeOrgCodeStep2List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep2List(mdmSearchVO.getSchOrgCodeStep1()); // 기관유형 2단계
    	}
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep3List = null;
    	if( mdmSearchVO.getSchOrgCodeStep2() != null && !mdmSearchVO.getSchOrgCodeStep2().trim().equals("") ) {
    		codeOrgCodeStep3List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep3List(mdmSearchVO.getSchOrgCodeStep2()); // 기관유형 3단계
    	}
    	
    	List<MdmOrgCodeVO> codeOrgSiteList = null;
    	if( mdmSearchVO.getSchOrgCodeStep3() != null && !mdmSearchVO.getSchOrgCodeStep3().trim().equals("") ) {
    		codeOrgSiteList = mdmTnsrAsmblyCodeService.selectMdmOrgSiteList(mdmSearchVO); // 기관명
    	}
    	
    	List<MdmOutSiteVO> siteList = null;
    	if( mdmSearchVO.getSchRegion() != null && !mdmSearchVO.getSchRegion().trim().equals("")  ) {
    		siteList = mdmTnsrAsmblyCodeService.selectMdmSiteList(mdmSearchVO.getSchRegion()); 
    	}
    	
    	List<MdmOutSeedVO> seedList = null;
    	if( !"".equals(mdmSearchVO.getSchSiteId())  ) {
    		MdmOutSeedVO seedVO = new MdmOutSeedVO();
    		seedVO.setREGION(mdmSearchVO.getSchRegion());
    		seedVO.setSITEID(mdmSearchVO.getSchSiteId());
    		seedList = mdmTnsrAsmblyCodeService.selectMdmSeedList(seedVO); 
    	}

    	List<MdmDetailCodeVO> codeRKS021AllList = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS021All();  // 자료유형
    	List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS025();        // 수집(지역)
    	
    	/*
    	 * 검색 form 기본 정보 조회 E
    	 * */
    	
    	//엑셀 조회 설정
    	MdmSearchVO mdmSearchVOTemp = new MdmSearchVO();
    	mdmSearchVOTemp.setSchKey("cnId");
    	mdmSearchVOTemp.setSort(mdmSearchVO.getSort());
    	
    	//조회할 OUTBBS_CN 정보 생성
    	String outbbsList = MdmExcelUtil.getOutbbsCn(multiRequest);
    	
    	if(!"".equals(outbbsList)){
	    	mdmSearchVOTemp.setSchKw(outbbsList);
	    	mdmSearchVO.setExcelSearchCnList(outbbsList);
    	}else{
    		outbbsList = mdmSearchVO.getExcelSearchCnList();
    		if(outbbsList != null)
    		{
	    		String[] tempList = outbbsList.replace("'", "").split(",");
	    		String tempOutbbsList = "";
	    		for(String vo : tempList){
	    			tempOutbbsList += "'" + vo + "',";
	    		}
	    		tempOutbbsList = tempOutbbsList.substring(0, tempOutbbsList.length()-1);
	    		mdmSearchVOTemp.setSchKw(tempOutbbsList);
    		}
    	}
    	
    	//이전,다음 관련해 정렬된 CN 값을 가지고 있기 위하여 파일업로드해서 조회할 경우에는 전체를 대상으로 조회하여  outbbsList 값 대체 S
    	mdmSearchVOTemp.setFirstRecord(0);
    	mdmSearchVOTemp.setLastRecord(Integer.parseInt(MdmProperties.getProperty("Globals.mdm.max_excel_search_count")));
    	List<MdmPolicyInfoVO> policyInfoList = mdmPolicyInfoService.selectMdmPolicyInfoList(mdmSearchVOTemp);
    	String currentSearchCnList = "";
    	for(MdmPolicyInfoVO vo : policyInfoList){
			currentSearchCnList += "'" + vo.getOUTBBS_CN() + "',";
		}
    	
		if(!"".equals(currentSearchCnList)){
			currentSearchCnList = currentSearchCnList.substring(0, currentSearchCnList.length()-1);
			outbbsList = currentSearchCnList;
		}
		//E
		
		// 총 검색 건수
    	int policyInfoListTotCnt = mdmPolicyInfoService.selectMdmPolicyInfoListTotCnt(mdmSearchVOTemp); 
    	
    	MdmPaging paging = new MdmPaging();
    	paging.setPagingCalc(policyInfoListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
    	paging.setParam(mdmSearchVO);
    	
    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVO.setLastRecord(paging.getLastRecord());
    	mdmSearchVOTemp.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVOTemp.setLastRecord(paging.getLastRecord());
    	
    	// 검색 리스트
    	policyInfoList = mdmPolicyInfoService.selectMdmPolicyInfoList(mdmSearchVOTemp);  

		model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
		model.addAttribute("codeRKS021AllList", codeRKS021AllList);
		model.addAttribute("codeRKS025List", codeRKS025List);
		model.addAttribute("siteList", siteList);
		model.addAttribute("seedList", seedList);
		model.addAttribute("policyInfoListTotCnt", policyInfoListTotCnt);
		model.addAttribute("policyInfoList", policyInfoList);
		model.addAttribute("paginationInfo", paging.getPaging(mdmSearchVO));
		model.addAttribute("listStartNo", paging.getFirstRecord());
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		model.addAttribute("codeOrgSiteList", codeOrgSiteList);
		model.addAttribute("isExcelSearch", "Y");
		model.addAttribute("excelSearchCnList", outbbsList.replace("'", ""));
		model.addAttribute("excelSearchCollection", "PolicyInfo");
		
		currentSearchCnList = "";
		for(MdmPolicyInfoVO vo : policyInfoList){
			currentSearchCnList += vo.getOUTBBS_CN() + ",";
		}
		
		if(!"".equals(currentSearchCnList))
			currentSearchCnList = currentSearchCnList.substring(0, currentSearchCnList.length()-1);
		
		if(!"".equals(outbbsList))
			currentSearchCnList = outbbsList;
		
		model.addAttribute("cnList", currentSearchCnList.replace("'", ""));
		model.addAttribute("EndPage", paging.getTotalPages());
		
        return "clikMng/mdm/MdmPolicyInfoList";
    }
    
    /**
     * 지방정책정보 내용보기
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfoView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 내용보기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoView.do")
    public String MdmPolicyInfoView(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
    	
    	MdmPolicyInfoVO policyInfoVO = mdmPolicyInfoService.selectMdmPolicyInfoView(mdmSearchVO.getCnId());
       	List<MdmPolicyInfoFileVO> policyInfoFileList = mdmPolicyInfoService.selectMdmPolicyInfoFileList(mdmSearchVO.getCnId());
       	List<MdmOutSiteVO> codeOutSiteList = mdmPolicyInfoService.selectMdmOutSiteList(policyInfoVO.getREGION()); // 기관유형
       	
       	MdmOutSeedVO mdmOutSeedVO = new MdmOutSeedVO();
       	mdmOutSeedVO.setREGION(policyInfoVO.getREGION());
       	mdmOutSeedVO.setSITEID(mdmOutSeedVO.getSITEID());
       	
       	List<MdmOutSeedVO> codeOutSeedList = mdmPolicyInfoService.selectMdmOutSeedList(mdmOutSeedVO); // 기관유형
       	List<MdmOutDocTypeVO> codeOutDocTypeList = mdmPolicyInfoService.selectMdmOutDocTypeList(); // 기관유형
    	
    	String cDate = policyInfoVO.getCDATE();
    	if( cDate != null && cDate.length() > 8 ) {
    		cDate = cDate.replace(".", "").replace("/", "");
    		cDate = cDate.substring(0, 4) + "-" + cDate.substring(4, 6) + "-" + cDate.substring(6, 8); 
    		policyInfoVO.setCDATE(cDate);
    	}
    	
    	String df = "";
    	if( policyInfoFileList.size() > 0 ) {
   			MdmPolicyInfoFileVO vo = new MdmPolicyInfoFileVO();
   			vo = policyInfoFileList.get(0);
   			if( mdmSearchVO.getDisfile().equals("") &&  vo.getDOC_CNVR_STTU_CODE() != null && (vo.getDOC_CNVR_STTU_CODE().equals("1") || vo.getDOC_CNVR_STTU_CODE().equals("3")) ) {
   				int flg = 0;
   				df = vo.getOUTBBS_PDF_FILE_NM();
   				MdmFtp mdmFtp = new MdmFtp();
   				flg = mdmFtp.ftp(vo.getDOC_CNVR_PDF_PATH(), df);
   				if( flg == 1 ) {
   				}
   			}
    	}
    	model.addAttribute("disfile", df);
    	model.addAttribute("codeOutSiteList", codeOutSiteList);
    	model.addAttribute("codeOutSeedList", codeOutSeedList);
    	model.addAttribute("codeOutDocTypeList", codeOutDocTypeList);
    	model.addAttribute("policyInfoVO", policyInfoVO);
    	model.addAttribute("policyInfoFileList", policyInfoFileList);

        return "clikMng/mdm/MdmPolicyInfoView";
    }

    /**
     * 지방정책정보 내용보기 1
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfoView1"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 내용보기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoMetaDataView1.do")
    public String MdmPolicyInfoView1(
					@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
					, ModelMap model
					, HttpServletRequest request) throws Exception {
    	
    	//상세화면 이전다음 관련 조회 S
    	String cnList = "";
    	if("Y".equals(mdmSearchVO.getIsPrevNextPaging()))
    	{
    		//이전 다음 CN 정보 조회 
	    	int policyInfoListTotCnt = mdmPolicyInfoService.selectMdmPolicyInfoListTotCnt(mdmSearchVO); // 총 검색 건수    		
	    	
	    	MdmPaging paging = new MdmPaging();
	    	paging.setPagingCalc(policyInfoListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
	    	paging.setParam(mdmSearchVO);
	    	
	    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
	    	mdmSearchVO.setLastRecord(paging.getLastRecord());
	    	
	    	String tempCn = mdmSearchVO.getCnId();
	    	mdmSearchVO.setCnId("");
	    	
	    	List<MdmPolicyInfoVO> policyInfoList = mdmPolicyInfoService.selectMdmPolicyInfoList(mdmSearchVO);  // 검색 리스트
	    	
	    	mdmSearchVO.setCnId(tempCn);
	    	
	    	for(MdmPolicyInfoVO vo : policyInfoList){
	    		cnList += vo.getOUTBBS_CN() + ",";
	    	}
	    	
	    	cnList = cnList.substring(0, cnList.length()-1);
	    	
	    	//이전다음 페이징된 경우 해당하는 데이터 CN 설정
	    	if( "".equals(mdmSearchVO.getCnId())  && "next".equals(mdmSearchVO.getPrevNextGubun())){
    			mdmSearchVO.setCnId(policyInfoList.get(0).getOUTBBS_CN());
    		}else if( "".equals(mdmSearchVO.getCnId()) && "prev".equals(mdmSearchVO.getPrevNextGubun())){
    			mdmSearchVO.setCnId(policyInfoList.get(policyInfoList.size()-1).getOUTBBS_CN());
    		}
	    	
	    	model.addAttribute("cnList", cnList);
			model.addAttribute("EndPage", paging.getTotalPages());
    	}
    	else
    	{
    		model.addAttribute("cnList", request.getParameter("cnList"));
			model.addAttribute("EndPage", request.getParameter("EndPage"));
    	}
		//상세화면 이전다음 관련 조회 E
    	
    	MdmPolicyInfoVO policyInfoVO = mdmPolicyInfoService.selectMdmPolicyInfoView(mdmSearchVO.getCnId());
       	List<MdmPolicyInfoFileVO> policyInfoFileList = mdmPolicyInfoService.selectMdmPolicyInfoFileList(mdmSearchVO.getCnId());
//       	List<MdmOutSiteVO> codeOutSiteList = mdmPolicyInfoService.selectMdmOutSiteList(policyInfoVO.getREGION()); // 기관유형
       	
//       	MdmOutSeedVO mdmOutSeedVO = new MdmOutSeedVO();
//       	mdmOutSeedVO.setREGION(policyInfoVO.getREGION());
//       	mdmOutSeedVO.setSITEID(policyInfoVO.getSITEID());
//       	List<MdmOutSeedVO> codeOutSeedList = mdmPolicyInfoService.selectMdmOutSeedList(mdmOutSeedVO); // 기관유형
    	
    	String cDate = policyInfoVO.getCDATE();
    	if( cDate != null && cDate.length() >= 8 ) {
    		cDate = cDate.replace(".", "").replace("/", "");
    		cDate = cDate.substring(0, 4) + "-" + cDate.substring(4, 6) + "-" + cDate.substring(6, 8); 
    		policyInfoVO.setCDATE(cDate);
    	}
    	String regDate = policyInfoVO.getREGDATE();
    	if( regDate != null && regDate.length() >= 8 ) {
    		regDate = regDate.substring(0, 4) + "-" + regDate.substring(4, 6) + "-" + regDate.substring(6, 8); 
    		policyInfoVO.setREGDATE(regDate);
    	}
    	String upDate = policyInfoVO.getUPDT();
    	if( upDate != null && upDate.length() >= 8 ) {
    		upDate = upDate.substring(0, 4) + "-" + upDate.substring(4, 6) + "-" + upDate.substring(6, 8); 
    		policyInfoVO.setUPDT(upDate);
    	}
    	String delDate = policyInfoVO.getDELDT();
    	if( delDate != null && delDate.length() >= 8 ) {
    		delDate = delDate.substring(0, 4) + "-" + delDate.substring(4, 6) + "-" + delDate.substring(6, 8); 
    		policyInfoVO.setDELDT(delDate);
    	}
    	
    	String df = "";
    	if( policyInfoFileList.size() > 0 ) {
   			MdmPolicyInfoFileVO vo = new MdmPolicyInfoFileVO();
   			vo = policyInfoFileList.get(0);
   			if( mdmSearchVO.getDisfile().equals("") &&  vo.getDOC_CNVR_STTU_CODE() != null && (vo.getDOC_CNVR_STTU_CODE().equals("1") || vo.getDOC_CNVR_STTU_CODE().equals("3")) ) {
   				int flg = 0;
   				df = vo.getOUTBBS_PDF_FILE_NM();
   				MdmFtp mdmFtp = new MdmFtp();
   				flg = mdmFtp.ftp(vo.getDOC_CNVR_PDF_PATH(), df);
   				if( flg == 1 ) {
   				}
   			}
    	}
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep1List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep1List();  // 기관유형 1단계
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep2List = null;
    	if( policyInfoVO.getORG_1() != null && !policyInfoVO.getORG_1().trim().equals("") ) {
    		codeOrgCodeStep2List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep2List(policyInfoVO.getORG_1()); // 기관유형 2단계
    	}
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep3List = null;
    	if( policyInfoVO.getORG_2() != null && !policyInfoVO.getORG_2().trim().equals("") ) {
    		codeOrgCodeStep3List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep3List(policyInfoVO.getORG_2()); // 기관유형 3단계
    	}
    	model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
    	
    	model.addAttribute("disfile", df);
//    	model.addAttribute("codeOutSiteList", codeOutSiteList);
//    	model.addAttribute("codeOutSeedList", codeOutSeedList);
//    	model.addAttribute("codeOutDocTypeList", codeOutDocTypeList);
    	model.addAttribute("policyInfoVO", policyInfoVO);
    	model.addAttribute("policyInfoFileList", policyInfoFileList);
    	
    	model.addAttribute("maxFileSize", EgovProperties.getProperty("MAX_FILE_SIZE"));
    	model.addAttribute("mdmSearchVO", mdmSearchVO);
    	
        return "clikMng/mdm/MdmPolicyInfoView2";
    }

    /**
     * 지방정책정보 메타데이터 보기2
     * @param mdmPolicyInfoVO 2
     * @param model
     * @return	"/Mdm/MdmPolicyInfoView 2"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 보기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoMetaDataView2.do")
    public String MdmPolicyInfoMetaDataView2(HttpServletRequest request, @ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,  ModelMap model) throws Exception {
    	
    	//상세화면 이전다음 관련 조회 S
    	String cnList = "";
    	if("Y".equals(mdmSearchVO.getIsPrevNextPaging()))
    	{
    		//이전 다음 CN 정보 조회 
	    	int policyInfoListTotCnt = mdmPolicyInfoService.selectMdmPolicyInfoListTotCnt(mdmSearchVO); // 총 검색 건수    		
	    	
	    	MdmPaging paging = new MdmPaging();
	    	paging.setPagingCalc(policyInfoListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
	    	paging.setParam(mdmSearchVO);
	    	
	    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
	    	mdmSearchVO.setLastRecord(paging.getLastRecord());
	    	
	    	String tempCn = mdmSearchVO.getCnId();
	    	mdmSearchVO.setCnId("");
	    	
	    	List<MdmPolicyInfoVO> policyInfoList = mdmPolicyInfoService.selectMdmPolicyInfoList(mdmSearchVO);  // 검색 리스트
	    	
	    	mdmSearchVO.setCnId(tempCn);
	    	
	    	for(MdmPolicyInfoVO vo : policyInfoList){
	    		cnList += vo.getOUTBBS_CN() + ",";
	    	}
	    	
	    	cnList = cnList.substring(0, cnList.length()-1);
	    	
	    	//이전다음 페이징된 경우 해당하는 데이터 CN 설정
	    	if( "".equals(mdmSearchVO.getCnId())  && "next".equals(mdmSearchVO.getPrevNextGubun())){
    			mdmSearchVO.setCnId(policyInfoList.get(0).getOUTBBS_CN());
    		}else if( "".equals(mdmSearchVO.getCnId()) && "prev".equals(mdmSearchVO.getPrevNextGubun())){
    			mdmSearchVO.setCnId(policyInfoList.get(policyInfoList.size()-1).getOUTBBS_CN());
    		}
	    	
	    	model.addAttribute("cnList", cnList);
			model.addAttribute("EndPage", paging.getTotalPages());
    	}
    	else
    	{
    		model.addAttribute("cnList", request.getParameter("cnList"));
			model.addAttribute("EndPage", request.getParameter("EndPage"));
    	}
		//상세화면 이전다음 관련 조회 E
    	
    	MdmPolicyInfoVO policyInfoVO = mdmPolicyInfoService.selectMdmPolicyInfoView(mdmSearchVO.getCnId());
       	List<MdmPolicyInfoFileVO> policyInfoFileList = mdmPolicyInfoService.selectMdmPolicyInfoFileList(mdmSearchVO.getCnId());
       	List<MdmOutSiteVO> codeOutSiteList = mdmPolicyInfoService.selectMdmOutSiteList(policyInfoVO.getREGION()); // 기관유형
       	
       	MdmOutSeedVO mdmOutSeedVO = new MdmOutSeedVO();
       	mdmOutSeedVO.setREGION(policyInfoVO.getREGION());
       	mdmOutSeedVO.setSITEID(policyInfoVO.getSITEID());
       	List<MdmOutSeedVO> codeOutSeedList = mdmPolicyInfoService.selectMdmOutSeedList(mdmOutSeedVO); // 기관유형
    	
    	String cDate = policyInfoVO.getCDATE();
    	if( cDate != null && cDate.length() >= 8 ) {
    		cDate = cDate.replace(".", "").replace("/", "");
    		cDate = cDate.substring(0, 4) + "-" + cDate.substring(4, 6) + "-" + cDate.substring(6, 8); 
    		policyInfoVO.setCDATE(cDate);
    	}
    	String regDate = policyInfoVO.getREGDATE();
    	if( regDate != null && regDate.length() >= 8 ) {
    		regDate = regDate.substring(0, 4) + "-" + regDate.substring(4, 6) + "-" + regDate.substring(6, 8); 
    		policyInfoVO.setREGDATE(regDate);
    	}
    	String upDate = policyInfoVO.getUPDT();
    	if( upDate != null && upDate.length() >= 8 ) {
    		upDate = upDate.substring(0, 4) + "-" + upDate.substring(4, 6) + "-" + upDate.substring(6, 8); 
    		policyInfoVO.setUPDT(upDate);
    	}
    	String delDate = policyInfoVO.getDELDT();
    	if( delDate != null && delDate.length() >= 8 ) {
    		delDate = delDate.substring(0, 4) + "-" + delDate.substring(4, 6) + "-" + delDate.substring(6, 8); 
    		policyInfoVO.setDELDT(delDate);
    	}
    	
    	String df = "";
    	if( policyInfoFileList.size() > 0 ) {
			df = request.getParameter("disfile");
    		if( df != null ) {
   				MdmFtp mdmFtp = new MdmFtp();
   				String arr[] = df.split("/");
   				int flg = mdmFtp.ftp(df, arr[arr.length - 1]);
       			if( flg == 1 ) {
    			}
       			df = arr[arr.length - 1];
    		}
    		else {
    			df = request.getParameter("disfile");
    		}
    	}
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep1List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep1List();  // 기관유형 1단계
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep2List = null;
    	if( policyInfoVO.getORG_1() != null && !policyInfoVO.getORG_1().trim().equals("") ) {
    		codeOrgCodeStep2List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep2List(policyInfoVO.getORG_1()); // 기관유형 2단계
    	}
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep3List = null;
    	if( policyInfoVO.getORG_2() != null && !policyInfoVO.getORG_2().trim().equals("") ) {
    		codeOrgCodeStep3List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep3List(policyInfoVO.getORG_2()); // 기관유형 3단계
    	}
    	model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
    	
    	model.addAttribute("disfile", df);
    	model.addAttribute("codeOutSiteList", codeOutSiteList);
    	model.addAttribute("codeOutSeedList", codeOutSeedList);
//    	model.addAttribute("codeOutDocTypeList", codeOutDocTypeList);
    	model.addAttribute("policyInfoVO", policyInfoVO);
    	model.addAttribute("policyInfoFileList", policyInfoFileList);
    	
    	model.addAttribute("maxFileSize", EgovProperties.getProperty("MAX_FILE_SIZE"));
    	model.addAttribute("mdmSearchVO", mdmSearchVO);
    	
        return "clikMng/mdm/MdmPolicyInfoView2";
    }

    /**
     * 정책정보 게시 수정
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/mdm/MdmPolicyInfoIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 게시 수정", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoIsView.do")
    public String MdmPolicyInfoIsView(@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	MdmIsViewVO isViewVO = null;
    	
    	isView = mdmPolicyInfoVO.getISVIEW().split(",");
    	for(int i = 0; i < isView.length; i++) {
    		isViewVO = new MdmIsViewVO();
    		arr = isView[i].split("=");
    		isViewVO.setUid(arr[0]);
    		isViewVO.setIsview(arr[1]);
    		mdmPolicyInfoService.updateMdmPolicyInfoIsView(isViewVO);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";

    }
    
    /**
     * 정책정보 게시 수정(게시판 일괄)
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/mdm/MdmPolicyInfoIsViewAll"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 게시 수정", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoIsViewAll.do")
    public String MdmPolicyInfoIsViewAll(@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO, HttpServletRequest request, ModelMap model) throws Exception {
 		
		MdmIsViewVO vo = new MdmIsViewVO();
		vo.setSeedid(String.valueOf(mdmPolicyInfoVO.getSEEDID()));
		vo.setIsview(mdmPolicyInfoVO.getISVIEW());
		
		String resMsg = "yes";
		try {
			mdmPolicyInfoService.updateMdmPolicyInfoIsViewAll(vo);
		} catch (Exception e) {
			resMsg = "no";
		}
		
		model.addAttribute("msg", "{\"rst\":\""+resMsg+"\"}");
		
        return "clikMng/mdm/MdmIsView";

    }

    /**
     * 정책정보 게시 삭제
     * @param mdmPolicyInfoVO
     * @param model
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@IncludedInfo(name="메타데이터 게시 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoDeleteChk.do")
    public void MdmPolicyInfoDeleteChk(@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO, HttpServletResponse response, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	MdmIsViewVO isViewVO = null;
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	
    	//결과 메시지
//    	String msg = "";
    	try {
    		
    		System.out.println(">>>>>>>>>>>>>>>>>>>>  ORIGINAL_OUTBBS :::: " + mdmPolicyInfoVO.getISVIEW());
    		System.out.println(">>>>>>>>>>>>>>>>>>>>  ORIGINAL_OUTBBS :::: " + mdmPolicyInfoVO.getISVIEW());
    		
        	isView = mdmPolicyInfoVO.getISVIEW().split("&");
        	
        	System.out.println(">>>>>>>>>>>>>>>>>>>>  SPLIT_OUTBBS :::: " + isView);
        	System.out.println(">>>>>>>>>>>>>>>>>>>>  SPLIT_OUTBBS :::: " + isView);
        	
        	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
        	
        	for(int i = 0; i < isView.length; i++) {
        		isViewVO = new MdmIsViewVO();
        		arr = isView[i].split("=");
        		isViewVO.setUid(arr[0]);
        		isViewVO.setIsview(arr[1]);
        		isViewVO.setLAST_UPDUSR_ID(user.getMngrId());
        		mdmPolicyInfoService.deleteMdmPolicyInfoChk(isViewVO);
        	}	
        	
        	jso.put("rst", "yes");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(":::::: METADATA CONTENTS DELETE ERROR OCCURED :::::: [[[[[" + e.getMessage() + "]]]]]");
			jso.put("rst", "no");
			
		}

    	
    	response.setHeader("Content-Type", "text/html;charset=utf-8");
    	PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(),"UTF-8"));
    	out.print(jso);
    	out.flush();
    	
//		String msg = "{\"rst\":\"yes\"}";

//		model.addAttribute("msg", msg);
//        return "clikMng/mdm/MdmIsView";

    }
    
    /**
     * 정책정보 등록폼
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfo"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 등록폼", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoForm.do")
    public String MdmPolicyInfoForm(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
    	
    	List<MdmOutDocTypeVO> codeOutDocTypeList = mdmPolicyInfoService.selectMdmOutDocTypeList(); // 자료유형
    	model.addAttribute("codeOutDocTypeList", codeOutDocTypeList);
        
    	List<MdmOrgCodeVO> codeOrgCodeStep1List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep1List();  // 기관유형 1단계
    	model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
    	
    	model.put("mdmAdm", mdmSearchVO.getMdmAdm());
    	
    	return "clikMng/mdm/MdmPolicyInfoForm";
    }

    /**
     * 정책정보 등록
     * @param mdmMemberVO
     * @param model
     * @return "/Mdm/MdmPolicyInfo"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 등록", order = 680 ,gid = 50)
    @RequestMapping("/mdm/MdmPolicyInfoRegist.do")
    public String insertMdmPolicyInfo(final MultipartHttpServletRequest multiRequest, @ModelAttribute("fileVO") MdmFileVO fileVO, 
    		@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO,
    		@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
    		BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

		List<MdmFileVO> result = null;
	    MdmFileVO fvo = null;
	    MdmCalUtil calUtil = new MdmCalUtil();
	    MdmStrUtil strUtil = new MdmStrUtil();
	    MdmMd5Sha256 md5   = new MdmMd5Sha256();
		MdmPolicyInfoFileVO mdmPolicyInfoFileVO = new MdmPolicyInfoFileVO();

	    String cDate = "";
	    String EXTID = "";
	    String hash  = "";
	    int uid = 0;
	    
	    try {
	    	calUtil.setDecimalFormat("00");
	    	uid = mdmPolicyInfoService.selectMdmPolicyInfoSeq();
		    EXTID = calUtil.getYear() + strUtil.getDecimalFormat("0000000000", uid);
		    
		    if(mdmPolicyInfoVO.getCDATE() != null && !"".equals(mdmPolicyInfoVO.getCDATE()))
		    {
		    	cDate = mdmPolicyInfoVO.getCDATE().replace("-", "").replace(".", "") + "000000";
		    	mdmPolicyInfoVO.setCDATE(cDate);
		    }
		    
		    MdmOutSiteVO mdmOutSiteVO =  mdmPolicyInfoService.selectMdmOutSite(mdmPolicyInfoVO.getSITEID());
		    mdmPolicyInfoVO.setSITENM(mdmOutSiteVO.getSITENM());
		    
		    mdmPolicyInfoVO.setREGION(mdmOutSiteVO.getREGION());
		    mdmPolicyInfoVO.setCDATE(cDate);
			mdmPolicyInfoVO.setOUTBBS_CN("CLIKC" + EXTID);
			mdmPolicyInfoVO.setEXTID(String.valueOf(uid));
			mdmPolicyInfoVO.setRASMBLY_ID(mdmPolicyInfoVO.getREGION() + "001");
			mdmPolicyInfoVO.setATTACHCNT("0");
			
			hash  = md5.getMD5(mdmPolicyInfoVO.getSITEID() + mdmPolicyInfoVO.getSEEDID() + mdmPolicyInfoVO.getTITLE());
			mdmPolicyInfoVO.setDUPLICATION(hash);
			
			String seedNm = mdmPolicyInfoService.selectMdmOutSeed(mdmPolicyInfoVO.getSEEDID());
			mdmPolicyInfoVO.setSEEDNM(seedNm);
			mdmPolicyInfoVO.setCATEGORYID(mdmPolicyInfoVO.getREGION()+mdmPolicyInfoVO.getDOCTYPE());
			
			String EXTRACHTML = mdmPolicyInfoVO.getEXTRACTHTML();
			if(EXTRACHTML != null && EXTRACHTML.trim().length() > 0){
				mdmPolicyInfoVO.setEXTRACTHTML(URLDecoder.decode(EXTRACHTML, "UTF-8"));
			}
			
			String CONTENT = mdmPolicyInfoVO.getCONTENT();
			if(CONTENT != null && CONTENT.trim().length() > 0){
				mdmPolicyInfoVO.setCONTENT(URLDecoder.decode(CONTENT, "UTF-8"));
			}
			
			String TITLE = mdmPolicyInfoVO.getTITLE();
			if(TITLE != null && TITLE.trim().length() > 0){
				mdmPolicyInfoVO.setTITLE(URLDecoder.decode(TITLE, "UTF-8"));
			}
			
			String WRITER = mdmPolicyInfoVO.getWRITER();
			if(WRITER != null && WRITER.trim().length() > 0){
				mdmPolicyInfoVO.setWRITER(URLDecoder.decode(WRITER, "UTF-8"));
			}
			
			String CDATE = mdmPolicyInfoVO.getCDATE();
			if(CDATE != null && CDATE.trim().length() > 0){
				mdmPolicyInfoVO.setCDATE(URLDecoder.decode(CDATE, "UTF-8"));
			}
			
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				if(mdmPolicyInfoVO.getDOCTYPE().equals("140"))
					result = fileUtil.parseFileInf(files, "manual", calUtil.getYear() + File.separator + calUtil.getToday() + File.separator);
				else
					result = fileUtil.parseFileInf(files, "policyinfo", calUtil.getYear() + File.separator + calUtil.getToday() + File.separator);

				if( result.size() > 0 ) {
					mdmPolicyInfoVO.setATTACHCNT(files.size()+"");
					
					for(int i = 0; i < result.size(); i++){
						fvo = result.get(i);
	
						String downid = mdmPolicyInfoService.selectMdmPolicyInfoFileSeq();
						mdmPolicyInfoFileVO.setDOWNID(downid);
						mdmPolicyInfoFileVO.setREGDATE(calUtil.getTodayWithTime2());
						mdmPolicyInfoFileVO.setDOWNURL("");
						mdmPolicyInfoFileVO.setEXTID(mdmPolicyInfoVO.getEXTID());
						mdmPolicyInfoFileVO.setDOWNPURL("");
						mdmPolicyInfoFileVO.setOUTBBS_CN(mdmPolicyInfoVO.getOUTBBS_CN());
						mdmPolicyInfoFileVO.setDOWNPATH(fvo.getFileStreCours());
						mdmPolicyInfoFileVO.setSEEDID(mdmPolicyInfoVO.getSEEDID());
						mdmPolicyInfoFileVO.setDOWNTYPE("ATTACH");
						mdmPolicyInfoFileVO.setDOC_CNVR_STTU_CODE("0");
						
						mdmPolicyInfoService.insertMdmPolicyInfoFile(mdmPolicyInfoFileVO);
					}
				}
			}
			
			mdmPolicyInfoService.insertMdmPolicyInfo(mdmPolicyInfoVO);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("mdmSearchVO", mdmSearchVO);

		return "redirect:/mdm/MdmPolicyInfoList.do";
    }   

    /**
     * 정책정보 수정
     * @param mdmMemberVO
     * @param model
     * @return "/Mdm/MdmPolicyInfo"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 수정", order = 680 ,gid = 50)
    @RequestMapping("/mdm/MdmPolicyInfoUpdate.do")
    public String updateMdmPolicyInfo(final MultipartHttpServletRequest multiRequest, @ModelAttribute("fileVO") MdmFileVO fileVO, 
    		@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO,
    		@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
    		BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

		List<MdmFileVO> result = null;
	    MdmFileVO fvo = null;
	    MdmCalUtil calUtil = new MdmCalUtil();
		MdmPolicyInfoFileVO mdmPolicyInfoFileVO = new MdmPolicyInfoFileVO();
		MdmMd5Sha256 md5   = new MdmMd5Sha256();
		 
	    String cDate = "";
	    
	    try {
	    	calUtil.setDecimalFormat("00");
		    
		    mdmPolicyInfoVO.setREGDATE(calUtil.getTodayWithTime2());
		    if(mdmPolicyInfoVO.getCDATE() != null && !"".equals(mdmPolicyInfoVO.getCDATE()))
		    {
		    	cDate = mdmPolicyInfoVO.getCDATE().replace("-", "").replace(".", "") + "000000";
		    	mdmPolicyInfoVO.setCDATE(cDate);
		    }
			
		    mdmPolicyInfoVO.setUPDT(new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
			mdmPolicyInfoVO.setCATEGORYID(mdmPolicyInfoVO.getREGION()+mdmPolicyInfoVO.getDOCTYPE());
			
			String hash  = md5.getMD5(mdmPolicyInfoVO.getSITEID() + mdmPolicyInfoVO.getSEEDID() + mdmPolicyInfoVO.getTITLE());
			mdmPolicyInfoVO.setDUPLICATION(hash);
			
			String EXTRACHTML = mdmPolicyInfoVO.getEXTRACTHTML();
			if(EXTRACHTML != null && EXTRACHTML.trim().length() > 0){
				mdmPolicyInfoVO.setEXTRACTHTML(URLDecoder.decode(EXTRACHTML, "UTF-8"));
			}
			
			String CONTENT = mdmPolicyInfoVO.getCONTENT();
			if(CONTENT != null && CONTENT.trim().length() > 0){
				mdmPolicyInfoVO.setCONTENT(URLDecoder.decode(CONTENT, "UTF-8"));
			}
			
			String TITLE = mdmPolicyInfoVO.getTITLE();
			if(TITLE != null && TITLE.trim().length() > 0){
				mdmPolicyInfoVO.setTITLE(URLDecoder.decode(TITLE, "UTF-8"));
			}
			
			String WRITER = mdmPolicyInfoVO.getWRITER();
			if(WRITER != null && WRITER.trim().length() > 0){
				mdmPolicyInfoVO.setWRITER(URLDecoder.decode(WRITER, "UTF-8"));
			}
			
			String CDATE = mdmPolicyInfoVO.getCDATE();
			if(CDATE != null && CDATE.trim().length() > 0){
				mdmPolicyInfoVO.setCDATE(URLDecoder.decode(CDATE, "UTF-8"));
			}
			
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			mdmPolicyInfoVO.setLAST_UPDUSR_ID(user.getMngrId());
			
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				if(mdmPolicyInfoVO.getDOCTYPE().equals("140"))
					result = fileUtil.parseFileInf(files, "manual", calUtil.getYear() + File.separator + calUtil.getToday() + File.separator);
				else
					result = fileUtil.parseFileInf(files, "policyinfo", calUtil.getYear() + File.separator + calUtil.getToday() + File.separator);

				if( result.size() > 0 ) {
					mdmPolicyInfoVO.setATTACHCNT(files.size()+"");
					
					for(int i = 0; i < result.size(); i++){
						fvo = result.get(i);
	
						String downid = mdmPolicyInfoService.selectMdmPolicyInfoFileSeq();
						mdmPolicyInfoFileVO.setDOWNID(downid);
						mdmPolicyInfoFileVO.setREGDATE(calUtil.getTodayWithTime2());
						mdmPolicyInfoFileVO.setDOWNURL("");
						mdmPolicyInfoFileVO.setEXTID(mdmPolicyInfoVO.getEXTID());
						mdmPolicyInfoFileVO.setDOWNPURL("");
						mdmPolicyInfoFileVO.setOUTBBS_CN(mdmPolicyInfoVO.getOUTBBS_CN());
						mdmPolicyInfoFileVO.setDOWNPATH(fvo.getFileStreCours());
						mdmPolicyInfoFileVO.setSEEDID(mdmPolicyInfoVO.getSEEDID());
						mdmPolicyInfoFileVO.setDOWNTYPE("ATTACH");
						mdmPolicyInfoFileVO.setDOC_CNVR_STTU_CODE("0");
						
						mdmPolicyInfoService.insertMdmPolicyInfoFile(mdmPolicyInfoFileVO);
					}
				}
			}
			
			mdmPolicyInfoService.updateMdmPolicyInfo(mdmPolicyInfoVO);
			
			model.addAttribute("message", "정상처리 되었습니다.");
		}
		catch(Exception e) {
			System.out.println("####  update fail  ####" + e.getMessage());
			model.addAttribute("message", "수정이 실패 되었습니다. 이유 : " + e.getMessage());
			
		}
		
		model.addAttribute("menuName", "지방정책정보");
		model.addAttribute("cnId", mdmPolicyInfoVO.getOUTBBS_CN());
		model.addAttribute("region", mdmPolicyInfoVO.getREGION());
		model.addAttribute("EndPage", multiRequest.getParameter("EndPage"));
		model.addAttribute("cnList", multiRequest.getParameter("cnList"));
		model.addAttribute("sort", multiRequest.getParameter("sort"));
		model.addAttribute("work", "수정");

		//return "redirect:/mdm/MdmPolicyInfoList.do";
        return "clikMng/mdm/MdmResult";
    }   

    /**
     * 정책정보 - 삭제
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfoFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoDelete.do")
    public String MdmPolicyInfoDelete(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
   		
    	MdmPolicyInfoVO mdmPolicyInfoVO = new MdmPolicyInfoVO();
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		mdmPolicyInfoVO.setLAST_UPDUSR_ID(user.getMngrId());
		mdmPolicyInfoVO.setOUTBBS_CN(mdmSearchVO.getCnId());
    	
		try{
			mdmPolicyInfoService.deleteMdmPolicyInfo(mdmPolicyInfoVO);
	    	
			model.addAttribute("menuName", "지방정책정보");
			model.addAttribute("cnId", mdmSearchVO.getCnId());
			model.addAttribute("work", "삭제");
			model.addAttribute("message", "정상처리 되었습니다.");
		}catch(Exception e){
			model.addAttribute("message", e.getMessage());
		}
		
        return "clikMng/mdm/MdmResult";
    }

    /**
     * 정책정보 - OUTSITE 코드 가져오기
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfo"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 OUTSITE 코드 가져오기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmOutSiteList.do")
    public String MdmPolicyInfoOutSiteList(@ModelAttribute("mdmOutSiteVO") MdmOutSiteVO mdmOutSiteVO, ModelMap model) throws Exception {
    	
    	try {
    		List<MdmOutSiteVO> codeOutSiteList = mdmPolicyInfoService.selectMdmOutSiteList(mdmOutSiteVO.getREGION()); // 기관유형
    		
    		model.addAttribute("codeOutSiteList",  codeOutSiteList);
        	model.addAttribute("siteId",  mdmOutSiteVO.getSITEID());
    	} catch (Exception e) {
			log.debug("::::::::::::::::::::::: " + e.getMessage());
		}
    	
        return "clikMng/mdm/MdmOutSiteList";
    }
    
    /**
     * 정책정보 - OUTSITE 코드 가져오기 - 기관코드로 기관명 가져오기
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfo"
     * @throws Exception
     */
    /*@IncludedInfo(name="메타데이터 OUTSITE 코드 가져오기", order = 680 ,gid = 50)*/
    @RequestMapping(value="/mdm/MdmOutSiteList2.do")
    public String MdmPolicyInfoOutSiteList2(@ModelAttribute("mdmOutSiteVO") MdmOutSiteVO mdmOutSiteVO, ModelMap model) throws Exception {
    	List<MdmOutSiteVO> codeOutSiteList = mdmPolicyInfoService.selectMdmOutSiteListForOrg(mdmOutSiteVO); // 기관유형
    	model.addAttribute("codeOutSiteList",  codeOutSiteList);
    	model.addAttribute("siteId",  mdmOutSiteVO.getSITEID());
        return "clikMng/mdm/MdmOutSiteList";
    }
    
    
    /**
     * 정책정보 - OUTSEED 코드 가져오기
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfo"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 OUTSEED 코드 가져오기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmOutSeedList.do")
    public String MdmPolicyInfoOutSeedList(@ModelAttribute("mdmOutSeedVO") MdmOutSeedVO mdmOutSeedVO, ModelMap model) throws Exception {
    	List<MdmOutSeedVO> codeOutSeedList = mdmPolicyInfoService.selectMdmOutSeedList(mdmOutSeedVO); // 기관유형
    	model.addAttribute("codeOutSeedList",  codeOutSeedList);
    	model.addAttribute("seedId",  mdmOutSeedVO.getSEEDID());
        return "clikMng/mdm/MdmOutSeedList";
    }

    /**
     * 정책정보 - 첨부파일 다운로드
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfoFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 첨부파일 다운로드", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoDownLoad.do")
    public void MdmPolicyInfoDownLoad(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	String fileName = "";
    	MdmPolicyInfoFileVO mdmPolicyInfoFileVO = mdmPolicyInfoService.selectMdmPolicyInfoFileDownPath(request.getParameter("DOWNID"));
    	String downloadFilePath = "";
    	
    	if(request.getParameter("GUBUN") != null && "CNVR".equals(request.getParameter("GUBUN"))){
    		//변환파일
    		//변환파일 저장 경로 : /clik-data/clik001/clik-cols/년도/...
    		downloadFilePath = mdmPolicyInfoFileVO.getDOC_CNVR_PDF_PATH();
    	}else{
    		//원본파일
    		//원본파일 저장 경로 : /clik-cols/outInfo/data/....
    		//수집 원본 파일에 경우에 downpath가 F:로 시작하고 있어서 접근할때 치환 처리해줌
    		downloadFilePath = mdmPolicyInfoFileVO.getDOWNPATH();
    		downloadFilePath =downloadFilePath.replace("F:","/clik-cols");
    	}
    	
    	fileName = downloadFilePath.split("/")[downloadFilePath.split("/").length - 1];
		
    	try{
	    	if( fileName != null && !"".equals(fileName)) {
	    		
				File uFile = new File(downloadFilePath);
				
				int fSize = (int)uFile.length();
				
				if (fSize > 0) 
				{
					String mimetype = "application/x-msdownload";
					response.setContentType(mimetype);
					setDisposition(fileName, request, response);
					response.setContentLength(fSize);
		
					BufferedInputStream in = null;
					BufferedOutputStream out = null;
		
					try {
						in = new BufferedInputStream(new FileInputStream(uFile));
						out = new BufferedOutputStream(response.getOutputStream());
		
						FileCopyUtils.copy(in, out);
						out.flush();
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						if (in != null) {
							try {
								in.close();
							} catch (Exception ignore) {
								ignore.printStackTrace();
							}
						}
						if (out != null) {
							try {
								out.close();
							} catch (Exception ignore) {
								ignore.printStackTrace();
							}
						}
					}
				}
				else
				{
					throw new Exception("파일이 존재하지 않습니다.");
				}
			}
    	}catch(Exception e){
    		response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<head>");
			printwriter.println("<script>");
			printwriter.println("alert('"+e.getMessage()+"');");
			printwriter.println("history.back(-1);");
			printwriter.println("</script>");
			printwriter.println("</head>");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
    	}
    }

    /**
     * 정책정보 - 첨부파일 삭제
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfoFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 첨부파일 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoFileDelete.do")
    public String MdmPolicyInfoFileDelete(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String msg = "";

		try{
	    	if( request.getParameter("DOWNID") != null ) {
	    		mdmPolicyInfoService.deleteMdmPolicyInfoFile(request.getParameter("DOWNID"));
	    		
	    		MdmPolicyInfoFileVO mdmPolicyInfoFileVO = mdmPolicyInfoService.selectMdmPolicyInfoFileDownPath(request.getParameter("DOWNID"));
	    		
	    		File file = null;
	    		
	    		//원본파일
	    		if(mdmPolicyInfoFileVO.getDOWNPATH() != null && !"".equals(mdmPolicyInfoFileVO.getDOWNPATH())){
	    			file = new File(mdmPolicyInfoFileVO.getDOWNPATH());
	    			
	    			if(file.exists()){
	    				file.delete();
	    			}
	    		}
	    		
	    		//변환파일
//	    		if(mdmPolicyInfoFileVO.getDOC_CNVR_PDF_PATH() != null && !"".equals(mdmPolicyInfoFileVO.getDOC_CNVR_PDF_PATH())){
//	    			file = new File(mdmPolicyInfoFileVO.getDOC_CNVR_PDF_PATH());
//
//	    			if(file.exists()){
//	    				file.delete();
//	    			}
//	    		}

	    		msg = "정상처리되었습니다.";
	    	}
		}catch(Exception e){
			msg = e.getMessage();
		}
		
		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 정책정보 - 첨부파일 목록
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmBillFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 첨부파일 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoFileListCmmn.do")
    public String MdmPolicyInfoFileListCmmn(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	List<MdmFileVO> fileList = null;
    	if( request.getParameter("OUTBBS_CN") != null ) {
    		fileList = mdmPolicyInfoService.selectMdmPolicyInfoFileListCmmn(request.getParameter("OUTBBS_CN"));
    	}
		model.addAttribute("fileList", fileList);
		return "clikMng/mdm/MdmFillListCmmn";
    }

    /**
     * 정책정보 - 중복 목록
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmBillFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 중복 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoListCmmn.do")
    public String MdmPolicyInfoListCmmn(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	List<MdmPolicyInfoVO> list = null;
    	if( request.getParameter("DUPLICATION") != null ) {
    		list = mdmPolicyInfoService.selectMdmPolicyInfoListCmmn(request.getParameter("DUPLICATION"));
    		
    		if(request.getParameter("OUTBBS_CN") != null)
    		model.addAttribute("outbbs_cn", request.getParameter("OUTBBS_CN"));
    	}
		model.addAttribute("list", list);
		return "clikMng/mdm/MdmPolicyInfoListCmmn";
    }
    
    /**
     * 정책정보 - 파일 리스트 삭제
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 파일 리스트 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoFileListDelete.do")
    public String MdmPolicyInfoFileListDelete(@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	isView = mdmPolicyInfoVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		arr = isView[i].split("=");
    		mdmPolicyInfoService.deleteMdmPolicyInfoFile(arr[0]);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 정책정보 - 파일 재변환
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 파일 재변환", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmPolicyInfoFileListReCnvrs.do")
    public String MdmPolicyInfoFileListReCnvrs(@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	isView = mdmPolicyInfoVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		arr = isView[i].split("=");
    		mdmPolicyInfoService.updateMdmPolicyInfoFileListReCnvrs(arr[0]);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 정책정보 - 최근 수집일
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    public String MdmPolicyInfoMaxCdate() throws Exception {
    	MdmStrUtil strUtil = new MdmStrUtil();
    	MdmCalUtil calUtil = new MdmCalUtil();
		calUtil.setDecimalFormat("00");

		String dt2 = mdmPolicyInfoService.selectMdmPolicyInfoMaxRegDate(calUtil.getToday());
		dt2 = strUtil.getStringFormat8("-", dt2);

		return dt2;
    }

    /**
     * 정책정보 - 최근 수집일
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    public String MdmPolicyInfoCdate(String dt2) throws Exception {
    	MdmCalUtil calUtil = new MdmCalUtil();
    	calUtil.setDelimiter("-");
		calUtil.setDecimalFormat("00");
		String[] arr = dt2.split("-");
		
		return calUtil.getDateBeforeDt(calUtil.getUnixTime(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2])), -7);
    }
    

    /**
     * Disposition 지정하기.
     * 
     * @param filename
     * @param request
     * @param response
     * @throws Exception
     */
    private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);
		
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;
		
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) {		// IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}
		
		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)){
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
    }
    
    /**
     * 브라우저 구분 얻기.
     * 
     * @param request
     * @return
     */
    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Trident") > -1) {	// IE11 문자열 깨짐 방지
            return "Trident";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }
    

    /**
     * 지방정책정보 엑셀 출력
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/selectMdmPolicyInfoExcel.do")
    public ModelAndView selectMdmPolicyInfoExcel(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
    											, @SuppressWarnings("rawtypes") Map commandMap
    											, ModelMap model) throws Exception {
    	
    	if( !mdmSearchVO.getSchBrtcCode().equals("") && mdmSearchVO.getSchLoAsmCode().equals("") ) {
    		mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode()+"001");
    	}
 
    	if( mdmSearchVO.getSchKw() != null && !mdmSearchVO.getSchKw().trim().equals("") ) {
    		if( mdmSearchVO.getSchKey().equals("") || mdmSearchVO.getSchKey().equals("schTitle") ) {
    			mdmSearchVO.setSchTitle(mdmSearchVO.getSchKw());   		 // 제목
    		}
    		else if( mdmSearchVO.getSchKey().equals("schContent") ) {
    			mdmSearchVO.setSchContent(mdmSearchVO.getSchKw());    // 내용
    		}
    		else if( mdmSearchVO.getSchKey().equals("cnId") ) {
    			mdmSearchVO.setCnId(mdmSearchVO.getSchKw());    	  		// 문서번호
    		}
    	}
    	
    	if( mdmSearchVO.getSchDflt() == null || !mdmSearchVO.getSchDflt().equals("N") ) { // 총 검색 건수(기본 1주일간)
    		MdmCalUtil calUtil = new MdmCalUtil();
    		calUtil.setDelimiter("-");
    		calUtil.setDecimalFormat("00");
    		String TODAY = calUtil.getToday();
    		
    		if(mdmSearchVO.getSchDt1() == null || "".equals(mdmSearchVO.getSchDt1())){
    			mdmSearchVO.setSchDt1(TODAY);
    		}
    		if(mdmSearchVO.getSchDt2() == null || "".equals(mdmSearchVO.getSchDt2())){
    			mdmSearchVO.setSchDt2(TODAY);
    		}
    		if(mdmSearchVO.getSchDt3() == null || "".equals(mdmSearchVO.getSchDt3())){
    			mdmSearchVO.setSchDt3(TODAY);
    		}
    		if(mdmSearchVO.getSchDt4() == null || "".equals(mdmSearchVO.getSchDt4())){
    			mdmSearchVO.setSchDt4(TODAY);
    		}
    	}
    	
    	//수집기관, 등록기간 OR 연산을 위하여 추가
    	if( (!"".equals(mdmSearchVO.getSchDt1()) || !"".equals(mdmSearchVO.getSchDt2())) && (!"".equals(mdmSearchVO.getSchDt3()) || !"".equals(mdmSearchVO.getSchDt4())) ){
    		//수집, 등록 기간에 정보가 모두 존재할 경우
    		mdmSearchVO.setSchDtConditionOperators("Y");
    	}else if( "".equals(mdmSearchVO.getSchDt1()) && "".equals(mdmSearchVO.getSchDt2()) && "".equals(mdmSearchVO.getSchDt3()) && "".equals(mdmSearchVO.getSchDt4()) ){ 
    		//수집, 등록 기간에 정보가 모두 없을 경우
    		mdmSearchVO.setSchDtConditionOperators("");
    	}else{
    		//수집, 등록 기간에 정보가 한쪽만 존재할 경우
    		mdmSearchVO.setSchDtConditionOperators("N");
    	}
    	
    	// 총 검색 건수
    	int policyInfoListTotCnt = mdmPolicyInfoService.selectMdmPolicyInfoListTotCnt(mdmSearchVO); 

    	//엑셀조회 후 다운로드할 경우 정보설정
    	if(commandMap.get("isExcelSearch") != null && commandMap.get("isExcelSearch").equals("Y")){
    		mdmSearchVO.setSchDtConditionOperators("");
			mdmSearchVO.setSchKey("cnId");
			mdmSearchVO.setSchDt1("");
			mdmSearchVO.setSchDt2("");
			mdmSearchVO.setSchDt3("");
			mdmSearchVO.setSchDt4("");
			String excelSearchCnList = commandMap.get("excelSearchCnList").toString().replaceAll("&apos;", "'");
			excelSearchCnList = excelSearchCnList.replaceAll(",", "','");
			excelSearchCnList = "'" + excelSearchCnList + "'"; 
			mdmSearchVO.setSchKw(excelSearchCnList);
			int maxExcelSearchCount = Integer.parseInt(MdmProperties.getProperty("Globals.mdm.max_excel_search_count"));
			policyInfoListTotCnt = policyInfoListTotCnt > maxExcelSearchCount ? maxExcelSearchCount : policyInfoListTotCnt;
		}
		
		mdmSearchVO.setLastRecord(policyInfoListTotCnt);
		
		// 검색 리스트
		List<MdmPolicyInfoVO> policyInfoList = mdmPolicyInfoService.selectMdmPolicyInfoList(mdmSearchVO);  
		
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", mdmSearchVO);
        map.put("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? "" : (String) commandMap.get("selSearchGubun"));
        
    	map.put("resultList", policyInfoList);
    	map.put("resultCnt", Integer.toString(policyInfoListTotCnt));

        return new ModelAndView("MdmPolicyInfoExcel", map);
    }
    
    /**
     * 지방정책정보 Text 출력
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/selectMdmPolicyInfoText.do")
    public ModelAndView selectMdmPolicyInfoText(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
    											, @SuppressWarnings("rawtypes") Map commandMap
    											, ModelMap model) throws Exception {
    	
		if( !mdmSearchVO.getSchBrtcCode().equals("") && mdmSearchVO.getSchLoAsmCode().equals("") ) {
    		mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode()+"001");
    	}
 
    	if( mdmSearchVO.getSchKw() != null && !mdmSearchVO.getSchKw().trim().equals("") ) {
    		if( mdmSearchVO.getSchKey().equals("") || mdmSearchVO.getSchKey().equals("schTitle") ) {
    			mdmSearchVO.setSchTitle(mdmSearchVO.getSchKw());   		 // 제목
    		}
    		else if( mdmSearchVO.getSchKey().equals("schContent") ) {
    			mdmSearchVO.setSchContent(mdmSearchVO.getSchKw());    // 내용
    		}
    		else if( mdmSearchVO.getSchKey().equals("cnId") ) {
    			mdmSearchVO.setCnId(mdmSearchVO.getSchKw());    	  		// 문서번호
    		}
    	}
    	
    	if( mdmSearchVO.getSchDflt() == null || !mdmSearchVO.getSchDflt().equals("N") ) { // 총 검색 건수(기본 1주일간)
    		MdmCalUtil calUtil = new MdmCalUtil();
    		calUtil.setDelimiter("-");
    		calUtil.setDecimalFormat("00");
    		String TODAY = calUtil.getToday();
    		
    		if(mdmSearchVO.getSchDt1() == null || "".equals(mdmSearchVO.getSchDt1())){
    			mdmSearchVO.setSchDt1(TODAY);
    		}
    		if(mdmSearchVO.getSchDt2() == null || "".equals(mdmSearchVO.getSchDt2())){
    			mdmSearchVO.setSchDt2(TODAY);
    		}
    		if(mdmSearchVO.getSchDt3() == null || "".equals(mdmSearchVO.getSchDt3())){
    			mdmSearchVO.setSchDt3(TODAY);
    		}
    		if(mdmSearchVO.getSchDt4() == null || "".equals(mdmSearchVO.getSchDt4())){
    			mdmSearchVO.setSchDt4(TODAY);
    		}
    	}
    	
    	//수집기관, 등록기간 OR 연산을 위하여 추가
    	if( (!"".equals(mdmSearchVO.getSchDt1()) || !"".equals(mdmSearchVO.getSchDt2())) && (!"".equals(mdmSearchVO.getSchDt3()) || !"".equals(mdmSearchVO.getSchDt4())) ){
    		//수집, 등록 기간에 정보가 모두 존재할 경우
    		mdmSearchVO.setSchDtConditionOperators("Y");
    	}else if( "".equals(mdmSearchVO.getSchDt1()) && "".equals(mdmSearchVO.getSchDt2()) && "".equals(mdmSearchVO.getSchDt3()) && "".equals(mdmSearchVO.getSchDt4()) ){ 
    		//수집, 등록 기간에 정보가 모두 없을 경우
    		mdmSearchVO.setSchDtConditionOperators("");
    	}else{
    		//수집, 등록 기간에 정보가 한쪽만 존재할 경우
    		mdmSearchVO.setSchDtConditionOperators("N");
    	}
    	
    	// 총 검색 건수
    	int policyInfoListTotCnt = mdmPolicyInfoService.selectMdmPolicyInfoListTotCnt(mdmSearchVO); 

    	//엑셀조회 후 다운로드할 경우 정보설정
    	if(commandMap.get("isExcelSearch") != null && commandMap.get("isExcelSearch").equals("Y")){
    		mdmSearchVO.setSchDtConditionOperators("");
    		mdmSearchVO.setSchKey("cnId");
			mdmSearchVO.setSchDt1("");
			mdmSearchVO.setSchDt2("");
			mdmSearchVO.setSchDt3("");
			mdmSearchVO.setSchDt4("");
			String excelSearchCnList = commandMap.get("excelSearchCnList").toString().replaceAll("&apos;", "'");
			excelSearchCnList = excelSearchCnList.replaceAll(",", "','");
			excelSearchCnList = "'" + excelSearchCnList + "'"; 
			mdmSearchVO.setSchKw(excelSearchCnList);
			int maxExcelSearchCount = Integer.parseInt(MdmProperties.getProperty("Globals.mdm.max_excel_search_count"));
			policyInfoListTotCnt = policyInfoListTotCnt > maxExcelSearchCount ? maxExcelSearchCount : policyInfoListTotCnt;
		}
		
		mdmSearchVO.setLastRecord(policyInfoListTotCnt);
		
		// 검색 리스트
		List<MdmPolicyInfoVO> policyInfoList = mdmPolicyInfoService.selectMdmPolicyInfoList(mdmSearchVO);  
		
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", mdmSearchVO);
        map.put("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? "" : (String) commandMap.get("selSearchGubun"));
        
    	map.put("resultList", policyInfoList);
    	map.put("resultCnt", Integer.toString(policyInfoListTotCnt));

        return new ModelAndView("MdmPolicyInfoText", map);
    }    
    
    /**
     * 엑셀검색 샘플파일을 다운로드한다.
     * @param request
     * @param response
     * @param model
     * @return 샘플엑셀파일 
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 검색용 엑셀파일 다운로드", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmSearchExcelDownLoad.do")
    public void MdmSearchExcelDownLoad(
				    		HttpServletRequest request
				    		, HttpServletResponse response
				    		, ModelMap model) throws Exception {
    	
    	String fileName = "searchExcel.xls";
    	String downloadFilePath = "/clik-web/clik-mgr.ear/clik-mgr.war/searchExcel.xls";
    	
    	try{
	    	if( fileName != null && !"".equals(fileName)) {
	    		
				File uFile = new File(downloadFilePath);
				
				int fSize = (int)uFile.length();
				
				if (fSize > 0) 
				{
					String mimetype = "application/x-msdownload";
					response.setContentType(mimetype);
					setDisposition(fileName, request, response);
					response.setContentLength(fSize);
		
					BufferedInputStream in = null;
					BufferedOutputStream out = null;
		
					try {
						in = new BufferedInputStream(new FileInputStream(uFile));
						out = new BufferedOutputStream(response.getOutputStream());
		
						FileCopyUtils.copy(in, out);
						out.flush();
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						if (in != null) {
							try {
								in.close();
							} catch (Exception ignore) {
								ignore.printStackTrace();
							}
						}
						if (out != null) {
							try {
								out.close();
							} catch (Exception ignore) {
								ignore.printStackTrace();
							}
						}
					}
				}
				else
				{
					throw new Exception("파일이 존재하지 않습니다.");
				}
			}
    	}catch(Exception e){
    		response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<head>");
			printwriter.println("<script>");
			printwriter.println("alert('"+e.getMessage()+"');");
			printwriter.println("history.back(-1);");
			printwriter.println("</script>");
			printwriter.println("</head>");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
    	}
    }
}
