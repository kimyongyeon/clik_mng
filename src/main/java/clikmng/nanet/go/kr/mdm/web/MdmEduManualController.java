package clikmng.nanet.go.kr.mdm.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import clikmng.nanet.go.kr.mdm.service.MdmEduManualService;
import clikmng.nanet.go.kr.mdm.service.MdmFileMngService;
import clikmng.nanet.go.kr.mdm.service.MdmFileMngUtil;
import clikmng.nanet.go.kr.mdm.service.MdmProperties;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyCodeService;
import clikmng.nanet.go.kr.mdm.utl.MdmCalUtil;
import clikmng.nanet.go.kr.mdm.utl.MdmExcelUtil;
import clikmng.nanet.go.kr.mdm.utl.MdmFtp;
import clikmng.nanet.go.kr.mdm.utl.MdmPaging;
import clikmng.nanet.go.kr.mdm.utl.MdmStrUtil;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 메타데이터 관리를 처리하는 Controller 클래스
 */
@Controller
public class MdmEduManualController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "MdmEduManualService")
    private MdmEduManualService mdmEduManualService;

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
     * 교육 & 매뉴얼 목록 조회
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/Mdm/MdmEduManualList"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualList.do")
    public String MdmEduManualList(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
    	if( !mdmSearchVO.getSchBrtcCode().equals("") &&  mdmSearchVO.getSchLoAsmCode().equals("") ) {
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
    	if( !"".equals(mdmSearchVO.getSchSiteId()) ) {
    		MdmOutSeedVO seedVO = new MdmOutSeedVO();
    		seedVO.setREGION(mdmSearchVO.getSchRegion());
    		seedVO.setSITEID(mdmSearchVO.getSchSiteId());
    		seedList = mdmTnsrAsmblyCodeService.selectMdmSeedList(seedVO); 
    	}
  	
    	List<MdmDetailCodeVO> codeRKS021AllList = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS021All();  // 자료유형
    	List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS025();        // 수집(지역)
    	
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
    	
    	policyInfoListTotCnt = mdmEduManualService.selectMdmEduManualListTotCnt(mdmSearchVO); // 총 검색 건수
    	
    	MdmPaging paging = new MdmPaging();
    	paging.setPagingCalc(policyInfoListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
    	paging.setParam(mdmSearchVO);
    	
    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVO.setLastRecord(paging.getLastRecord());
    	
    	List<MdmPolicyInfoVO> policyInfoList = null;
    	policyInfoList = mdmEduManualService.selectMdmEduManualList(mdmSearchVO);  // 검색 리스트
    	
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
		model.addAttribute("siteList", siteList);
		model.addAttribute("seedList", seedList);
		model.addAttribute("eduManualListTotCnt", policyInfoListTotCnt);
		model.addAttribute("eduManualList", policyInfoList);
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
		
        return "clikMng/mdm/MdmEduManualList";
    }
    
    /**
     * 교육 & 매뉴얼 목록 조회 (엑셀 조회)
     * @param multiRequest
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/Mdm/MdmEduManualList"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록 엑셀 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/ExcelMdmEduManualList.do")
    public String ExcelMdmEduManualList(final MultipartHttpServletRequest multiRequest, 
    																@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, 
    																ModelMap model) throws Exception {
    	
    	//기본정렬 설정
    	if(mdmSearchVO.getSort() == null || mdmSearchVO.getSort().equals("")){
    		mdmSearchVO.setSort("REGDATE DESC");
    	}
    	
    	/*
    	 * 검색 form 기본 정보 조회 S
    	 * */
    	if( !mdmSearchVO.getSchBrtcCode().equals("") &&  mdmSearchVO.getSchLoAsmCode().equals("") ) {
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
    	if( !"".equals(mdmSearchVO.getSchSiteId()) ) {
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
    	
    	//조회할 OUTBBS_CN 정보 생성
    	String outbbsList = MdmExcelUtil.getOutbbsCn(multiRequest);

    	mdmSearchVOTemp.setSchKw(outbbsList);
    	
    	// 총 검색 건수
    	int policyInfoListTotCnt = mdmEduManualService.selectMdmEduManualListTotCnt(mdmSearchVOTemp); 
    	
    	//엑셀조회 최대 카운트
    	final int maxExcelSearchCount = Integer.parseInt(MdmProperties.getProperty("Globals.mdm.max_excel_search_count"));
    	
    	//페이징 되지 않도록 최대 100까지 설정
    	policyInfoListTotCnt = policyInfoListTotCnt > maxExcelSearchCount ? maxExcelSearchCount : policyInfoListTotCnt;
    	
    	//최대 검색 결과를 제한 처리
    	mdmSearchVO.setLastRecord(maxExcelSearchCount);
    	mdmSearchVO.setListCnt(maxExcelSearchCount);
    	mdmSearchVOTemp.setLastRecord(maxExcelSearchCount);
    	mdmSearchVOTemp.setListCnt(maxExcelSearchCount);
    	
    	MdmPaging paging = new MdmPaging();
    	paging.setPagingCalc(policyInfoListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
    	paging.setParam(mdmSearchVO);
    	
    	// 검색 리스트
    	List<MdmPolicyInfoVO> policyInfoList = mdmEduManualService.selectMdmEduManualList(mdmSearchVOTemp);  

		model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
		model.addAttribute("codeRKS021AllList", codeRKS021AllList);
		model.addAttribute("codeRKS025List", codeRKS025List);
		model.addAttribute("siteList", siteList);
		model.addAttribute("seedList", seedList);
		model.addAttribute("eduManualListTotCnt", policyInfoListTotCnt);
		model.addAttribute("eduManualList", policyInfoList);
		model.addAttribute("paginationInfo", paging.getPaging(mdmSearchVO));
		model.addAttribute("listStartNo", paging.getFirstRecord());
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		model.addAttribute("codeOrgSiteList", codeOrgSiteList);
		model.addAttribute("isExcelSearch", "Y");
		model.addAttribute("excelSearchCnList", outbbsList);
		
		model.addAttribute("cnList", outbbsList.replace("'", ""));
		model.addAttribute("EndPage", paging.getTotalPages());
		
        return "clikMng/mdm/MdmEduManualList";
    }

    /**
     * 교육 & 매뉴얼 내용보기
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/Mdm/MdmEduManualView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 내용보기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualView.do")
    public String MdmManualFormatView(
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
			, ModelMap model
			, HttpServletRequest request) throws Exception {

		//상세화면 이전다음 관련 조회 S
		String cnList = "";
		if("Y".equals(mdmSearchVO.getIsPrevNextPaging()))
		{
			String tempCn = mdmSearchVO.getCnId();
			mdmSearchVO.setCnId("");

			//이전 다음 CN 정보 조회 
			int policyInfoListTotCnt = mdmEduManualService.selectMdmEduManualListTotCnt(mdmSearchVO); // 총 검색 건수
	    	
	    	MdmPaging paging = new MdmPaging();
	    	paging.setPagingCalc(policyInfoListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
	    	paging.setParam(mdmSearchVO);
	    	
	    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
	    	mdmSearchVO.setLastRecord(paging.getLastRecord());
	    	
	    	
	    	List<MdmPolicyInfoVO> policyInfoList = mdmEduManualService.selectMdmEduManualList(mdmSearchVO);  // 검색 리스트
			
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
    	
    	MdmPolicyInfoVO eduManualVO = mdmEduManualService.selectMdmEduManualView(mdmSearchVO.getCnId());
       	List<MdmPolicyInfoFileVO> eduManualFileList = mdmEduManualService.selectMdmEduManualFileList(mdmSearchVO.getCnId());
       	List<MdmOutSiteVO> codeOutSiteList = mdmEduManualService.selectMdmOutSiteList(eduManualVO.getREGION()); // 기관유형
       	List<MdmOutSeedVO> codeOutSeedList = mdmEduManualService.selectMdmOutSeedList(eduManualVO.getREGION()); // 기관유형
    	
       	String cDate = eduManualVO.getCDATE();
    	if( cDate != null && cDate.length() >= 8 ) {
    		cDate = cDate.replace(".", "").replace("/", "");
    		cDate = cDate.substring(0, 4) + "-" + cDate.substring(4, 6) + "-" + cDate.substring(6, 8); 
    		eduManualVO.setCDATE(cDate);
    	}
    	String regDate = eduManualVO.getREGDATE();
    	if( regDate != null && regDate.length() >= 8 ) {
    		regDate = regDate.substring(0, 4) + "-" + regDate.substring(4, 6) + "-" + regDate.substring(6, 8); 
    		eduManualVO.setREGDATE(regDate);
    	}
    	String upDate = eduManualVO.getUPDT();
    	if( upDate != null && upDate.length() >= 8 ) {
    		upDate = upDate.substring(0, 4) + "-" + upDate.substring(4, 6) + "-" + upDate.substring(6, 8); 
    		eduManualVO.setUPDT(upDate);
    	}
    	String delDate = eduManualVO.getDELDT();
    	if( delDate != null && delDate.length() >= 8 ) {
    		delDate = delDate.substring(0, 4) + "-" + delDate.substring(4, 6) + "-" + delDate.substring(6, 8); 
    		eduManualVO.setDELDT(delDate);
    	}
    	
    	String df = "";
    	if( eduManualFileList.size() > 0 ) {
   			MdmPolicyInfoFileVO vo = new MdmPolicyInfoFileVO();
   			vo = eduManualFileList.get(0);
   			if( mdmSearchVO.getDisfile().equals("") &&  vo.getDOC_CNVR_STTU_CODE() != null && (vo.getDOC_CNVR_STTU_CODE().equals("1") || vo.getDOC_CNVR_STTU_CODE().equals("3")) ) {
   				int flg = 0;
   				df = vo.getOUTBBS_PDF_FILE_NM();
   				MdmFtp mdmFtp = new MdmFtp();
   				flg = mdmFtp.ftp(vo.getDOC_CNVR_PDF_PATH(), df);
   				if( flg == 1 ) {
   				}
   			}
   			else {
   				df = mdmSearchVO.getDisfile();
   			}
    	}
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep1List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep1List();  // 기관유형 1단계
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep2List = null;
    	if( eduManualVO.getORG_1() != null && !eduManualVO.getORG_1().trim().equals("") ) {
    		codeOrgCodeStep2List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep2List(eduManualVO.getORG_1()); // 기관유형 2단계
    	}
    	
    	List<MdmOrgCodeVO> codeOrgCodeStep3List = null;
    	if( eduManualVO.getORG_2() != null && !eduManualVO.getORG_2().trim().equals("") ) {
    		codeOrgCodeStep3List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep3List(eduManualVO.getORG_2()); // 기관유형 3단계
    	}
    	
    	model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
		
    	model.addAttribute("disfile", df);
    	model.addAttribute("codeOutSiteList", codeOutSiteList);
    	model.addAttribute("codeOutSeedList", codeOutSeedList);
    	model.addAttribute("policyInfoVO", eduManualVO);
    	model.addAttribute("policyInfoFileList", eduManualFileList);
    	
    	model.addAttribute("maxFileSize", EgovProperties.getProperty("MAX_FILE_SIZE"));
    	model.addAttribute("mdmSearchVO", mdmSearchVO);
    	
        return "clikMng/mdm/MdmEduManualView2";
    }

    /**
     * 교육 & 매뉴얼 메타데이터 내용보기1
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/Mdm/MdmEduManualView1"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 내용보기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualMetaDataView1.do")
    public String MdmEduManualMetaDataView1(
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
			, ModelMap model
			, HttpServletRequest request) throws Exception {
    	
    	//상세화면 이전다음 관련 조회 S
			String cnList = "";
			if("Y".equals(mdmSearchVO.getIsPrevNextPaging()))
			{
				String tempCn = mdmSearchVO.getCnId();
				mdmSearchVO.setCnId("");

				//이전 다음 CN 정보 조회 
				int policyInfoListTotCnt = mdmEduManualService.selectMdmEduManualListTotCnt(mdmSearchVO); // 총 검색 건수
		    	
		    	MdmPaging paging = new MdmPaging();
		    	paging.setPagingCalc(policyInfoListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
		    	paging.setParam(mdmSearchVO);
		    	
		    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
		    	mdmSearchVO.setLastRecord(paging.getLastRecord());
		    	
		    	
		    	List<MdmPolicyInfoVO> policyInfoList = mdmEduManualService.selectMdmEduManualList(mdmSearchVO);  // 검색 리스트
				
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
	    	
	    	MdmPolicyInfoVO eduManualVO = mdmEduManualService.selectMdmEduManualView(mdmSearchVO.getCnId());
	       	List<MdmPolicyInfoFileVO> eduManualFileList = mdmEduManualService.selectMdmEduManualFileList(mdmSearchVO.getCnId());
	       	List<MdmOutSiteVO> codeOutSiteList = mdmEduManualService.selectMdmOutSiteList(eduManualVO.getREGION()); // 기관유형
	       	List<MdmOutSeedVO> codeOutSeedList = mdmEduManualService.selectMdmOutSeedList(eduManualVO.getREGION()); // 기관유형
	    	
	       	String cDate = eduManualVO.getCDATE();
	    	if( cDate != null && cDate.length() >= 8 ) {
	    		cDate = cDate.replace(".", "").replace("/", "");
	    		cDate = cDate.substring(0, 4) + "-" + cDate.substring(4, 6) + "-" + cDate.substring(6, 8); 
	    		eduManualVO.setCDATE(cDate);
	    	}
	    	String regDate = eduManualVO.getREGDATE();
	    	if( regDate != null && regDate.length() >= 8 ) {
	    		regDate = regDate.substring(0, 4) + "-" + regDate.substring(4, 6) + "-" + regDate.substring(6, 8); 
	    		eduManualVO.setREGDATE(regDate);
	    	}
	    	String upDate = eduManualVO.getUPDT();
	    	if( upDate != null && upDate.length() >= 8 ) {
	    		upDate = upDate.substring(0, 4) + "-" + upDate.substring(4, 6) + "-" + upDate.substring(6, 8); 
	    		eduManualVO.setUPDT(upDate);
	    	}
	    	String delDate = eduManualVO.getDELDT();
	    	if( delDate != null && delDate.length() >= 8 ) {
	    		delDate = delDate.substring(0, 4) + "-" + delDate.substring(4, 6) + "-" + delDate.substring(6, 8); 
	    		eduManualVO.setDELDT(delDate);
	    	}
	    	
	    	String df = "";
	    	if( eduManualFileList.size() > 0 ) {
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
	    	if( eduManualVO.getORG_1() != null && !eduManualVO.getORG_1().trim().equals("") ) {
	    		codeOrgCodeStep2List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep2List(eduManualVO.getORG_1()); // 기관유형 2단계
	    	}
	    	
	    	List<MdmOrgCodeVO> codeOrgCodeStep3List = null;
	    	if( eduManualVO.getORG_2() != null && !eduManualVO.getORG_2().trim().equals("") ) {
	    		codeOrgCodeStep3List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep3List(eduManualVO.getORG_2()); // 기관유형 3단계
	    	}
	    	
	    	model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
			model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
			model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
			
	    	model.addAttribute("disfile", df);
	    	model.addAttribute("codeOutSiteList", codeOutSiteList);
	    	model.addAttribute("codeOutSeedList", codeOutSeedList);
	    	model.addAttribute("policyInfoVO", eduManualVO);
	    	model.addAttribute("policyInfoFileList", eduManualFileList);
	    	
	    	model.addAttribute("maxFileSize", EgovProperties.getProperty("MAX_FILE_SIZE"));
	    	model.addAttribute("mdmSearchVO", mdmSearchVO);
	    	
	        return "clikMng/mdm/MdmEduManualView2";
	    }

    /**
     * 교육 & 매뉴얼 메타데이터 내용보기2
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfoView2"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 보기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualMetaDataView2.do")
    public String MdmEduManualMetaDataView2(
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
			, ModelMap model
			, HttpServletRequest request) throws Exception {
    	
    	//상세화면 이전다음 관련 조회 S
			String cnList = "";
			if("Y".equals(mdmSearchVO.getIsPrevNextPaging()))
			{
				String tempCn = mdmSearchVO.getCnId();
				mdmSearchVO.setCnId("");

				//이전 다음 CN 정보 조회 
				int policyInfoListTotCnt = mdmEduManualService.selectMdmEduManualListTotCnt(mdmSearchVO); // 총 검색 건수
		    	
		    	MdmPaging paging = new MdmPaging();
		    	paging.setPagingCalc(policyInfoListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
		    	paging.setParam(mdmSearchVO);
		    	
		    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
		    	mdmSearchVO.setLastRecord(paging.getLastRecord());
		    	
		    	List<MdmPolicyInfoVO> policyInfoList = mdmEduManualService.selectMdmEduManualList(mdmSearchVO);  // 검색 리스트
				
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
	    	
	    	MdmPolicyInfoVO eduManualVO = mdmEduManualService.selectMdmEduManualView(mdmSearchVO.getCnId());
	       	List<MdmPolicyInfoFileVO> eduManualFileList = mdmEduManualService.selectMdmEduManualFileList(mdmSearchVO.getCnId());
	       	List<MdmOutSiteVO> codeOutSiteList = mdmEduManualService.selectMdmOutSiteList(eduManualVO.getREGION()); // 기관유형
	       	List<MdmOutSeedVO> codeOutSeedList = mdmEduManualService.selectMdmOutSeedList(eduManualVO.getREGION()); // 기관유형
	    	
	       	String cDate = eduManualVO.getCDATE();
	    	if( cDate != null && cDate.length() >= 8 ) {
	    		cDate = cDate.replace(".", "").replace("/", "");
	    		cDate = cDate.substring(0, 4) + "-" + cDate.substring(4, 6) + "-" + cDate.substring(6, 8); 
	    		eduManualVO.setCDATE(cDate);
	    	}
	    	String regDate = eduManualVO.getREGDATE();
	    	if( regDate != null && regDate.length() >= 8 ) {
	    		regDate = regDate.substring(0, 4) + "-" + regDate.substring(4, 6) + "-" + regDate.substring(6, 8); 
	    		eduManualVO.setREGDATE(regDate);
	    	}
	    	String upDate = eduManualVO.getUPDT();
	    	if( upDate != null && upDate.length() >= 8 ) {
	    		upDate = upDate.substring(0, 4) + "-" + upDate.substring(4, 6) + "-" + upDate.substring(6, 8); 
	    		eduManualVO.setUPDT(upDate);
	    	}
	    	String delDate = eduManualVO.getDELDT();
	    	if( delDate != null && delDate.length() >= 8 ) {
	    		delDate = delDate.substring(0, 4) + "-" + delDate.substring(4, 6) + "-" + delDate.substring(6, 8); 
	    		eduManualVO.setDELDT(delDate);
	    	}
	    	
	    	String df = "";
	    	if( eduManualFileList.size() > 0 ) {
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
	    	if( eduManualVO.getORG_1() != null && !eduManualVO.getORG_1().trim().equals("") ) {
	    		codeOrgCodeStep2List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep2List(eduManualVO.getORG_1()); // 기관유형 2단계
	    	}
	    	
	    	List<MdmOrgCodeVO> codeOrgCodeStep3List = null;
	    	if( eduManualVO.getORG_2() != null && !eduManualVO.getORG_2().trim().equals("") ) {
	    		codeOrgCodeStep3List = mdmTnsrAsmblyCodeService.selectMdmOrgCodeStep3List(eduManualVO.getORG_2()); // 기관유형 3단계
	    	}
	    	
	    	model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
			model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
			model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
			
	    	model.addAttribute("disfile", df);
	    	model.addAttribute("codeOutSiteList", codeOutSiteList);
	    	model.addAttribute("codeOutSeedList", codeOutSeedList);
	    	model.addAttribute("policyInfoVO", eduManualVO);
	    	model.addAttribute("policyInfoFileList", eduManualFileList);
	    	
	    	model.addAttribute("maxFileSize", EgovProperties.getProperty("MAX_FILE_SIZE"));
	    	model.addAttribute("mdmSearchVO", mdmSearchVO);
	    	
	        return "clikMng/mdm/MdmEduManualView2";
	    }

    /**
     * 교육 & 매뉴얼 게시 수정
     * @param mdmEduManualVO
     * @param model
     * @return	"/mdm/MdmEduManualIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 게시 수정", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualIsView.do")
    public String MdmEduManualIsView(@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	MdmIsViewVO isViewVO = null;
    	
    	isView = mdmPolicyInfoVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		isViewVO = new MdmIsViewVO();
    		arr = isView[i].split("=");
    		isViewVO.setUid(arr[0]);
    		isViewVO.setIsview(arr[1]);
    		mdmEduManualService.updateMdmEduManualIsView(isViewVO);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }
    
    /**
     * 교육 & 매뉴얼 게시 삭제
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/mdm/MdmPolicyInfoDeleteChk"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 게시 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualDeleteChk.do")
    public String MdmEduManualDeleteChk(@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	MdmIsViewVO isViewVO = null;
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	isView = mdmPolicyInfoVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		isViewVO = new MdmIsViewVO();
    		arr = isView[i].split("=");
    		isViewVO.setUid(arr[0]);
    		isViewVO.setIsview(arr[1]);
    		isViewVO.setLAST_UPDUSR_ID(user.getMngrId());
    		mdmEduManualService.deleteMdmEduManualChk(isViewVO);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }
    
    /**
     * 교육 & 매뉴얼 등록폼
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfo"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 등록폼", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualForm.do")
    public String MdmEduManualForm(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
    	List<MdmOutDocTypeVO> codeOutDocTypeList = mdmEduManualService.selectMdmOutDocTypeList(); // 기관유형
    	model.addAttribute("codeOutDocTypeList", codeOutDocTypeList);
    	
        return "clikMng/mdm/MdmEduManualForm";
    }

    /**
     * 교육 & 매뉴얼 등록
     * @param mdmMemberVO
     * @param model
     * @return "/Mdm/MdmPolicyInfo"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 등록", order = 680 ,gid = 50)
    @RequestMapping("/mdm/MdmEduManualRegist.do")
    public String insertMdmEduManual(final MultipartHttpServletRequest multiRequest, @ModelAttribute("fileVO") MdmFileVO fileVO, 
    		@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO,
    		@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
    		BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

		List<MdmFileVO> result = null;
	    MdmFileVO fvo = null;
	    MdmCalUtil calUtil = new MdmCalUtil();
	    MdmStrUtil strUtil = new MdmStrUtil();
		MdmPolicyInfoFileVO mdmPolicyInfoFileVO = new MdmPolicyInfoFileVO();

	    String cDate = "";
	    String EXTID = "";
	    int uid = 0;
	    boolean flg = false;
	    
	    try {
	    	calUtil.setDecimalFormat("00");
	    	uid = mdmEduManualService.selectMdmEduManualSeq();
		    EXTID = "CLIKM" + calUtil.getYear() + strUtil.getDecimalFormat("0000000000", uid);
		    
		    mdmPolicyInfoVO.setREGDATE(calUtil.getTodayWithTime2());
		    cDate = mdmPolicyInfoVO.getREGDATE().replace("-", "") + "000000";
		    mdmPolicyInfoVO.setCDATE(cDate);
			mdmPolicyInfoVO.setOUTBBS_CN(EXTID);
			mdmPolicyInfoVO.setEXTID(EXTID);
			mdmPolicyInfoVO.setRASMBLY_ID(mdmPolicyInfoVO.getREGION() + "001");
			mdmPolicyInfoVO.setATTACHCNT("0");

			String siteNm = mdmEduManualService.selectMdmOutSite(mdmPolicyInfoVO.getSITEID());
			String seedNm = mdmEduManualService.selectMdmOutSeed(mdmPolicyInfoVO.getSEEDID());
			mdmPolicyInfoVO.setSITENM(siteNm);
			mdmPolicyInfoVO.setSEEDNM(seedNm);
			mdmPolicyInfoVO.setCATEGORYID(mdmPolicyInfoVO.getREGION()+mdmPolicyInfoVO.getDOCTYPE());

			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "manual", calUtil.getYear() + File.separator + calUtil.getToday() + File.separator);

				if( result.size() > 0 ) {
					flg = true;
					fvo = new MdmFileVO();
					fvo = result.get(0);
/*					
					System.out.println("파일 확장자 : " + fvo.getFileExtsn());
					System.out.println("파일 절대경로 : " + fvo.getFileStreCours());
					System.out.println("파일 파일 크기 : " + fvo.getFileMg());
					System.out.println("파일 오리지널 파일명 : " + fvo.getOrignlFileNm());
					System.out.println("파일 저장된 파일명 : " + fvo.getStreFileNm());
					System.out.println(fvo.getAtchFileId());
					System.out.println(fvo.getFileSn());
*/
					String downid = mdmEduManualService.selectMdmEduManualFileSeq();
					mdmPolicyInfoFileVO.setDOWNID(downid);
					mdmPolicyInfoFileVO.setREGDATE(calUtil.getTodayWithTime2());
					mdmPolicyInfoFileVO.setDOWNURL("");
					mdmPolicyInfoFileVO.setEXTID(EXTID);
					mdmPolicyInfoFileVO.setDOWNPURL("");
					mdmPolicyInfoFileVO.setOUTBBS_CN(EXTID);
					mdmPolicyInfoFileVO.setDOWNTYPE("ATTACH");
					mdmPolicyInfoFileVO.setDOC_CNVR_STTU_CODE("0");

					/*
					String fileName = fvo.getOrignlFileNm();
					if( fvo.getFileExtsn() != null && !fvo.getFileExtsn().equals("") ) {
						fileName = fileName + "." + fvo.getFileExtsn();
					}
					mdmPolicyInfoFileVO.setDOWNPATH(fvo.getFileStreCours() + fileName);
					*/					
					mdmPolicyInfoFileVO.setDOWNPATH(fvo.getFileStreCours());
					mdmPolicyInfoFileVO.setSEEDID(mdmPolicyInfoVO.getSEEDID());
					mdmPolicyInfoFileVO.setDOWNTYPE("ATTACH");
				}
			}
			if( flg == true ) {
				mdmPolicyInfoVO.setATTACHCNT("1");
			}
			mdmEduManualService.insertMdmEduManual(mdmPolicyInfoVO);
			if( flg == true ) {
			    mdmEduManualService.insertMdmEduManualFile(mdmPolicyInfoFileVO);
			}
		}
		catch(Exception e) {
		}
		
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		
		return "redirect:/mdm/MdmEduManualList.do";
    }   

    /**
     * 교육 & 매뉴얼 수정
     * @param mdmMemberVO
     * @param model
     * @return "/Mdm/MdmPolicyInfo"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 수정", order = 680 ,gid = 50)
    @RequestMapping("/mdm/MdmEduManualUpdate.do")
    public String updateMdmEduManualUpdate(final MultipartHttpServletRequest multiRequest, @ModelAttribute("fileVO") MdmFileVO fileVO, 
    		@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO,
    		@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
    		BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

		List<MdmFileVO> result = null;
	    MdmFileVO fvo = null;
	    MdmCalUtil calUtil = new MdmCalUtil();
		MdmPolicyInfoFileVO mdmPolicyInfoFileVO = new MdmPolicyInfoFileVO();

	    String cDate = "";
	    boolean flg = false;
	    
	    try {
	    	calUtil.setDecimalFormat("00");
		    
		    mdmPolicyInfoVO.setREGDATE(calUtil.getTodayWithTime2());
		    cDate = mdmPolicyInfoVO.getREGDATE().replace("-", "") + "000000";
		    mdmPolicyInfoVO.setCDATE(cDate);
			mdmPolicyInfoVO.setRASMBLY_ID(mdmPolicyInfoVO.getREGION() + "001");
			
			String siteNm = mdmEduManualService.selectMdmOutSite(mdmPolicyInfoVO.getSITEID());
			String seedNm = mdmEduManualService.selectMdmOutSeed(mdmPolicyInfoVO.getSEEDID());
			mdmPolicyInfoVO.setSITENM(siteNm);
			mdmPolicyInfoVO.setSEEDNM(seedNm);
			mdmPolicyInfoVO.setCATEGORYID(mdmPolicyInfoVO.getREGION()+mdmPolicyInfoVO.getDOCTYPE());

			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "manual", calUtil.getYear() + File.separator + calUtil.getToday() + File.separator);

				if( result.size() > 0 ) {
					flg = true;
					fvo = new MdmFileVO();
					fvo = result.get(0);
					
					String downid = mdmEduManualService.selectMdmEduManualFileSeq();
					mdmPolicyInfoFileVO.setDOWNID(downid);
					mdmPolicyInfoFileVO.setREGDATE(calUtil.getTodayWithTime2());
					mdmPolicyInfoFileVO.setDOWNURL("");
					mdmPolicyInfoFileVO.setEXTID(mdmPolicyInfoVO.getEXTID());
					mdmPolicyInfoFileVO.setDOWNPURL("");
					mdmPolicyInfoFileVO.setOUTBBS_CN(mdmPolicyInfoVO.getOUTBBS_CN());

					/*
					String fileName = fvo.getOrignlFileNm();
					if( fvo.getFileExtsn() != null && !fvo.getFileExtsn().equals("") ) {
						fileName = fileName + "." + fvo.getFileExtsn();
					}
					mdmPolicyInfoFileVO.setDOWNPATH(fvo.getFileStreCours() + fileName);
					*/					
					mdmPolicyInfoFileVO.setDOWNPATH(fvo.getFileStreCours());
					mdmPolicyInfoFileVO.setSEEDID(mdmPolicyInfoVO.getSEEDID());
					mdmPolicyInfoFileVO.setDOWNTYPE("ATTACH");
					mdmPolicyInfoFileVO.setDOC_CNVR_STTU_CODE("0");
				}
			}
			if( flg == true ) {
				mdmPolicyInfoVO.setATTACHCNT("1");
			}
			mdmEduManualService.updateMdmEduManual(mdmPolicyInfoVO);
			if( flg == true ) {
			    mdmEduManualService.insertMdmEduManualFile(mdmPolicyInfoFileVO);
			}
		}
		catch(Exception e) {
		}
		
		model.addAttribute("menuName", "매뉴얼&서식");
		model.addAttribute("cnId", mdmSearchVO.getCnId());
		model.addAttribute("region", mdmPolicyInfoVO.getREGION());
		model.addAttribute("return_url", "/mdm/MdmEduManualMetaDataView1.do?cnId="+mdmSearchVO.getCnId());
		model.addAttribute("work", "수정");
		
		//return "redirect:/mdm/MdmEduManualList.do";
        return "clikMng/mdm/MdmResult";
    }   

    /**
     * 교육 & 매뉴얼 - 삭제
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfoFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualDelete.do")
    public String MdmEduManualDelete(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
   		mdmEduManualService.deleteMdmEduManual(mdmSearchVO.getCnId());
		model.addAttribute("mdmSearchVO", mdmSearchVO);

		model.addAttribute("menuName", "매뉴얼&서식");
		model.addAttribute("cnId", mdmSearchVO.getCnId());
		model.addAttribute("work", "삭제");
		
		//return "redirect:/mdm/MdmEduManualList.do";
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
    @RequestMapping(value="/mdm/MdmEduManualOutSiteList.do")
    public String MdmEduManualOutSiteList(@RequestParam("REGION") String REGION, ModelMap model) throws Exception {
    	List<MdmOutSiteVO> codeOutSiteList = mdmEduManualService.selectMdmOutSiteList(REGION); // 기관유형
    	model.addAttribute("codeOutSiteList",  codeOutSiteList);
        return "clikMng/mdm/MdmOutSiteList";
    }
    
    /**
     * 교육 & 매뉴얼 - OUTSEED 코드 가져오기
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfo"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 OUTSEED 코드 가져오기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualOutSeedList.do")
    public String MdmEduManualOutSeedList(@RequestParam("DOCTYPE") String DOCTYPE, ModelMap model) throws Exception {
    	List<MdmOutSeedVO> codeOutSeedList = mdmEduManualService.selectMdmOutSeedList(DOCTYPE); // 기관유형
    	model.addAttribute("codeOutSeedList",  codeOutSeedList);
        return "clikMng/mdm/MdmOutSeedList";
    }

    /**
     * 교육 & 매뉴얼 - 첨부파일 다운로드
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfoFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 첨부파일 다운로드", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualDownLoad.do")
    public void MdmEduManualDownLoad(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	String downFile = "";
    	String orgFileName = "";
    	if( request.getParameter("DOWNID") != null ) {
    		downFile = mdmEduManualService.selectMdmEduManualFileDownPath(request.getParameter("DOWNID"));
    		orgFileName = downFile;
    		fileUtil.downFile(request, response, downFile, orgFileName);
    	}
    }

    /**
     * 교육 & 매뉴얼 - 첨부파일 삭제
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmPolicyInfoFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 첨부파일 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualFileDelete.do")
    public String MdmEduManualFileDelete(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	if( request.getParameter("DOWNID") != null ) {
    		mdmEduManualService.deleteMdmEduManualFile(request.getParameter("DOWNID"));
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";

    }
    
    /**
     * 교육 & 매뉴얼 - 첨부파일 목록
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmBillFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 첨부파일 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualFileListCmmn.do")
    public String MdmEduManualFileListCmmn(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	List<MdmFileVO> fileList = null;
    	if( request.getParameter("OUTBBS_CN") != null ) {
    		fileList = mdmEduManualService.selectMdmEduManualFileListCmmn(request.getParameter("OUTBBS_CN"));
    	}
		model.addAttribute("fileList", fileList);
		return "clikMng/mdm/MdmFillListCmmn";
    }

    /**
     * 교육 & 매뉴얼 - 중복 목록
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmBillFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 중복 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualListCmmn.do")
    public String MdmEduManualListCmmn(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	List<MdmPolicyInfoVO> list = null;
    	if( request.getParameter("OUTBBS_CN") != null ) {
    		list = mdmEduManualService.selectMdmEduManualListCmmn(request.getParameter("OUTBBS_CN"));
    	}
		model.addAttribute("list", list);
		return "clikMng/mdm/MdmPolicyInfoListCmmn";
    }

    /**
     * 교육 & 매뉴얼 - 파일 리스트 삭제
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 파일 리스트 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualFileListDelete.do")
    public String MdmEduManualFileListDelete(@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	isView = mdmPolicyInfoVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		arr = isView[i].split("=");
    		mdmEduManualService.deleteMdmEduManualFile(arr[0]);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 교육 & 매뉴얼 - 파일 재변환
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 파일 재변환", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmEduManualFileListReCnvrs.do")
    public String MdmEduManualFileListReCnvrs(@ModelAttribute("mdmPolicyInfoVO") MdmPolicyInfoVO mdmPolicyInfoVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	isView = mdmPolicyInfoVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		arr = isView[i].split("=");
    		mdmEduManualService.updateMdmEduManualFileListReCnvrs(arr[0]);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }
 
    /**
     * 교육 & 매뉴얼 - 최근 수집일
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    public String MdmPolicyInfoMaxCdate() throws Exception {
    	MdmStrUtil strUtil = new MdmStrUtil();
    	MdmCalUtil calUtil = new MdmCalUtil();
		calUtil.setDecimalFormat("00");

		String dt2 = mdmEduManualService.selectMdmEduManualMaxRegDate(calUtil.getToday());
		dt2 = strUtil.getStringFormat8("-", dt2);

		return dt2;
    }

    /**
     * 교육 & 매뉴얼 - 최근 수집일
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
     * 교육&메뉴얼 엑셀파일로 다운로드 한다.
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/selectMdmEduManualExcel.do")
    public ModelAndView selectMdmEduManualExcel(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
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
    	int policyInfoListTotCnt = mdmEduManualService.selectMdmEduManualListTotCnt(mdmSearchVO); 

    	//엑셀조회 후 다운로드할 경우 정보설정
    	if(commandMap.get("isExcelSearch") != null && commandMap.get("isExcelSearch").equals("Y")){
			mdmSearchVO.setSchKey("cnId");
			mdmSearchVO.setSchDt1("");
			mdmSearchVO.setSchDt2("");
			mdmSearchVO.setSchDt3("");
			mdmSearchVO.setSchDt4("");
			mdmSearchVO.setSchKw(commandMap.get("excelSearchCnList").toString().replaceAll("&apos;", "'"));
			int maxExcelSearchCount = Integer.parseInt(MdmProperties.getProperty("Globals.mdm.max_excel_search_count"));
			policyInfoListTotCnt = policyInfoListTotCnt > maxExcelSearchCount ? maxExcelSearchCount : policyInfoListTotCnt;
		}
		
		mdmSearchVO.setLastRecord(policyInfoListTotCnt);
		
		// 검색 리스트
		List<MdmPolicyInfoVO> policyInfoList = mdmEduManualService.selectMdmEduManualList(mdmSearchVO);  
		
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", mdmSearchVO);
        map.put("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? "" : (String) commandMap.get("selSearchGubun"));
        
    	map.put("resultList", policyInfoList);
    	map.put("resultCnt", Integer.toString(policyInfoListTotCnt));

        return new ModelAndView("MdmEduManualExcel", map);
    }
    
    /**
     * 교육&메뉴얼 Text파일로 다운로드 한다.
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/selectMdmEduManualText.do")
    public ModelAndView selectMdmEduManualText(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
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
    	int policyInfoListTotCnt = mdmEduManualService.selectMdmEduManualListTotCnt(mdmSearchVO); 

    	//엑셀조회 후 다운로드할 경우 정보설정
    	if(commandMap.get("isExcelSearch") != null && commandMap.get("isExcelSearch").equals("Y")){
			mdmSearchVO.setSchKey("cnId");
			mdmSearchVO.setSchDt1("");
			mdmSearchVO.setSchDt2("");
			mdmSearchVO.setSchDt3("");
			mdmSearchVO.setSchDt4("");
			mdmSearchVO.setSchKw(commandMap.get("excelSearchCnList").toString().replaceAll("&apos;", "'"));
			int maxExcelSearchCount = Integer.parseInt(MdmProperties.getProperty("Globals.mdm.max_excel_search_count"));
			policyInfoListTotCnt = policyInfoListTotCnt > maxExcelSearchCount ? maxExcelSearchCount : policyInfoListTotCnt;
		}
		
		mdmSearchVO.setLastRecord(policyInfoListTotCnt);
		
		// 검색 리스트
		List<MdmPolicyInfoVO> policyInfoList = mdmEduManualService.selectMdmEduManualList(mdmSearchVO);  
		
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", mdmSearchVO);
        map.put("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? "" : (String) commandMap.get("selSearchGubun"));
        
    	map.put("resultList", policyInfoList);
    	map.put("resultCnt", Integer.toString(policyInfoListTotCnt));

        return new ModelAndView("MdmEduManualText", map);
    }
}
