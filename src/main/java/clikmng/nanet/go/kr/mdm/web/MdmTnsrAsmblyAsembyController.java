package clikmng.nanet.go.kr.mdm.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mdm.model.MdmDetailCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmOrgCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSeedVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSiteVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnmPprtyVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpInsttCodeEstbsVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyAsembyActVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyAsembyVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyEstVO;
import clikmng.nanet.go.kr.mdm.service.MdmFileMngService;
import clikmng.nanet.go.kr.mdm.service.MdmFileMngUtil;
import clikmng.nanet.go.kr.mdm.service.MdmProperties;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyAsembyService;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyCodeService;
import clikmng.nanet.go.kr.mdm.utl.MdmCalUtil;
import clikmng.nanet.go.kr.mdm.utl.MdmExcelUtil;
import clikmng.nanet.go.kr.mdm.utl.MdmPaging;
import clikmng.nanet.go.kr.mdm.utl.MdmStrUtil;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 메타데이터 관리를 처리하는 Controller 클래스
 */
@Controller
public class MdmTnsrAsmblyAsembyController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "MdmTnsrAsmblyAsembyService")
    private MdmTnsrAsmblyAsembyService mdmTnsrAsmblyAsembyService;

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
     * 의원정보 목록 보기
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmAsmblyAsemby"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/MdmAsmblyAsembyList.do")
    public String MdmAsmblyAsembyList(@ModelAttribute("mdmTnsrAsmblyAsembyActVO") MdmTnsrAsmblyAsembyActVO mdmTnsrAsmblyAsembyActVO, @ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
       
    	if( !mdmSearchVO.getSchBrtcCode().equals("") &&  mdmSearchVO.getSchLoAsmCode().equals("") ) {
    		mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode()+"001");
    	}

    	List<MdmTnpInsttCodeEstbsVO> codeEstbsList = null;
    	if( mdmSearchVO.getSchLoAsmTyCode() != null &&  !mdmSearchVO.getSchLoAsmTyCode().equals("") ) {
    		codeEstbsList = mdmTnsrAsmblyCodeService.selectMdmTnpInsttCodeEstbs(mdmSearchVO); 
    	}
    	
    	//기본정렬 설정
    	if(mdmSearchVO.getSort() == null || mdmSearchVO.getSort().equals("")){
    		mdmSearchVO.setSort("ASEMBY_NM ASC");
    	}
    	
    	String schDt1 = mdmSearchVO.getSchDt1();
    	String schDt2 = mdmSearchVO.getSchDt2();
    	
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
    		}
    	}
    	
    	/**
    	 * 검색항목 추가
    	 */
    	// 의회선택 값이 null 아닌 경우
    	if( mdmSearchVO.getSchLoAsmTyCode() != null && !mdmSearchVO.getSchLoAsmTyCode().equals("") ) {
    		List<String> schLoAsmTyCodeList = new ArrayList<String>();
    		schLoAsmTyCodeList = mdmTnsrAsmblyCodeService.selectLoAsmTyCodeList(mdmSearchVO); // 의회선택 리스트
    		mdmSearchVO.setSchLoAsmTyCodeList(schLoAsmTyCodeList);
    	}
    	
    	// 지역선택 값이 null 아닌 경우
    	if( mdmSearchVO.getSchRegion() != null && !mdmSearchVO.getSchRegion().equals("") ) {
    		List<String> schRegionList = new ArrayList<String>();
    		schRegionList = mdmTnsrAsmblyCodeService.selectRegionList(mdmSearchVO); // 지역선택 리스트
    		mdmSearchVO.setSchRegionList(schRegionList);
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
    	
    	MdmCalUtil calUtil = new MdmCalUtil();
   		calUtil.setDecimalFormat("00");
   		String today = calUtil.getToday();

    	List<MdmDetailCodeVO> codeRKS022List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS022();  // 수집유형
    	List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS025();        // 수집(지역)
    	List<MdmTnmPprtyVO> codePprtyList = mdmTnsrAsmblyCodeService.selectMdmTnmPprty(today); // 소속정당

    	int asmblyAsembyActListTotCnt = 0;

    	if( mdmSearchVO.getSchDflt() == null || !mdmSearchVO.getSchDflt().equals("N") ) { // 총 검색 건수(기본 1주일간)
    		
    		if(mdmSearchVO.getSchDt1() == null || "".equals(mdmSearchVO.getSchDt1())){
    			mdmSearchVO.setSchDt1(today);
    		}
    		if(mdmSearchVO.getSchDt2() == null || "".equals(mdmSearchVO.getSchDt2())){
    			mdmSearchVO.setSchDt2(today);
    		}
    	} 
    	
    	asmblyAsembyActListTotCnt = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyActListTotCnt(mdmSearchVO); // 총 검색 건수
    	
    	MdmPaging paging = new MdmPaging();
    	paging.setPagingCalc(asmblyAsembyActListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
    	paging.setParam(mdmSearchVO);
    	
    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVO.setLastRecord(paging.getLastRecord());
    	
    	List<MdmTnsrAsmblyAsembyActVO> asmblyAsembyActList = null;
    	asmblyAsembyActList = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyActList(mdmSearchVO);  // 검색 리스트
    	
    	if("cnId".equals(mdmSearchVO.getSchKey())){
    		mdmSearchVO.setSchKw(mdmSearchVO.getSchKw().replace("'", ""));
    		mdmSearchVO.setSchDt1(schDt1);
        	mdmSearchVO.setSchDt2(schDt2);
    	}
    	
    	int listStartNo = paging.getFirstRecord(); 

    	model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
		model.addAttribute("codeOrgSiteList", codeOrgSiteList);
		model.addAttribute("siteList", siteList);
		model.addAttribute("seedList", seedList);
    	model.addAttribute("codeRKS021AllList", codeRKS021AllList);
    	
    	model.addAttribute("codeEstbsList",  codeEstbsList);
    	model.addAttribute("codePprtyList",  codePprtyList);
		model.addAttribute("codeRKS022List", codeRKS022List);
		model.addAttribute("codeRKS025List", codeRKS025List);
		
		model.addAttribute("asmblyAsembyActListTotCnt", asmblyAsembyActListTotCnt);
		model.addAttribute("asmblyAsembyActList", asmblyAsembyActList);
		model.addAttribute("paginationInfo", paging.getPaging(mdmSearchVO));
		model.addAttribute("listStartNo", listStartNo);
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		
		String currentSearchCnList = "";
		for(MdmTnsrAsmblyAsembyActVO vo : asmblyAsembyActList){
			currentSearchCnList += vo.getASEMBY_CN() + ",";
		}
		
		if(!"".equals(currentSearchCnList))
			currentSearchCnList = currentSearchCnList.substring(0, currentSearchCnList.length()-1);
		
		model.addAttribute("cnList", currentSearchCnList.replace("'", ""));
		model.addAttribute("EndPage", paging.getTotalPages());
		
        return "clikMng/mdm/MdmAsmblyAsembyList";
        
    }
    
    /**
     * 의원정보 목록 보기 (엑셀 조회)
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmAsmblyAsemby"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/ExcelMdmAsmblyAsembyList.do")
    public String ExcelMdmAsmblyAsembyList(final MultipartHttpServletRequest multiRequest, 
																			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, 
																			ModelMap model) throws Exception {
    	
    	//기본정렬 설정
    	if(mdmSearchVO.getSort() == null || mdmSearchVO.getSort().equals("")){
    		mdmSearchVO.setSort("ASEMBY_NM ASC");
    	}
    	
    	/*
    	 * 검색 form 기본 정보 조회 S
    	 * */
    	if( !mdmSearchVO.getSchBrtcCode().equals("") &&  mdmSearchVO.getSchLoAsmCode().equals("") ) {
    		mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode()+"001");
    	}

    	MdmCalUtil calUtil = new MdmCalUtil();
   		calUtil.setDecimalFormat("00");
   		String today = calUtil.getToday();
   		
    	if( mdmSearchVO.getSchDflt() == null || !mdmSearchVO.getSchDflt().equals("N") ) { // 총 검색 건수(기본 1주일간)
    		
    		if(mdmSearchVO.getSchDt1() == null || "".equals(mdmSearchVO.getSchDt1())){
    			mdmSearchVO.setSchDt1(today);
    		}
    		if(mdmSearchVO.getSchDt2() == null || "".equals(mdmSearchVO.getSchDt2())){
    			mdmSearchVO.setSchDt2(today);
    		}
    	}

    	List<MdmTnpInsttCodeEstbsVO> codeEstbsList = null;
    	if( mdmSearchVO.getSchLoAsmTyCode() != null &&  !mdmSearchVO.getSchLoAsmTyCode().equals("") ) {
    		codeEstbsList = mdmTnsrAsmblyCodeService.selectMdmTnpInsttCodeEstbs(mdmSearchVO); 
    	}
    	
    	// 의회선택 값이 null 아닌 경우
    	if( mdmSearchVO.getSchLoAsmTyCode() != null && !mdmSearchVO.getSchLoAsmTyCode().equals("") ) {
    		List<String> schLoAsmTyCodeList = new ArrayList<String>();
    		schLoAsmTyCodeList = mdmTnsrAsmblyCodeService.selectLoAsmTyCodeList(mdmSearchVO); // 의회선택 리스트
    		mdmSearchVO.setSchLoAsmTyCodeList(schLoAsmTyCodeList);
    	}
    	
    	// 지역선택 값이 null 아닌 경우
    	if( mdmSearchVO.getSchRegion() != null && !mdmSearchVO.getSchRegion().equals("") ) {
    		List<String> schRegionList = new ArrayList<String>();
    		schRegionList = mdmTnsrAsmblyCodeService.selectRegionList(mdmSearchVO); // 지역선택 리스트
    		mdmSearchVO.setSchRegionList(schRegionList);
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
    	List<MdmDetailCodeVO> codeRKS022List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS022();  // 수집유형
    	List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS025();        // 수집(지역)
    	List<MdmTnmPprtyVO> codePprtyList = mdmTnsrAsmblyCodeService.selectMdmTnmPprty(today); // 소속정당
    	
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
//    		mdmSearchVOTemp.setSchKw(outbbsList);
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
    	
    	//이전,다음 관련해 정렬된 CN 값을 가지고 있기 위하여 파일업로드해서 조회할 경우에는 전체를 대상으로 조회하여  outbbsList 값 대체
    	mdmSearchVOTemp.setFirstRecord(0);
    	mdmSearchVOTemp.setLastRecord(Integer.parseInt(MdmProperties.getProperty("Globals.mdm.max_excel_search_count")));
    	List<MdmTnsrAsmblyAsembyActVO> asmblyAsembyActList = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyActList(mdmSearchVOTemp);
    	String currentSearchCnList = "";
    	for(MdmTnsrAsmblyAsembyActVO vo : asmblyAsembyActList){
			currentSearchCnList += "'" + vo.getASEMBY_CN() + "',";
		}
		
		if(!"".equals(currentSearchCnList)){
			currentSearchCnList = currentSearchCnList.substring(0, currentSearchCnList.length()-1);
			outbbsList = currentSearchCnList;
		}
		
    	// 총 검색 건수
    	int asmblyAsembyActListTotCnt = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyActListTotCnt(mdmSearchVOTemp); 
    	
    	MdmPaging paging = new MdmPaging();
    	paging.setPagingCalc(asmblyAsembyActListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
    	paging.setParam(mdmSearchVO);

    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVO.setLastRecord(paging.getLastRecord());
    	mdmSearchVOTemp.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVOTemp.setLastRecord(paging.getLastRecord());
    	
    	// 검색 리스트
    	asmblyAsembyActList = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyActList(mdmSearchVOTemp);
    	
    	model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
		model.addAttribute("codeOrgSiteList", codeOrgSiteList);
		model.addAttribute("siteList", siteList);
		model.addAttribute("seedList", seedList);
    	model.addAttribute("codeRKS021AllList", codeRKS021AllList);
    	
    	model.addAttribute("codeEstbsList",  codeEstbsList);
    	model.addAttribute("codePprtyList",  codePprtyList);
		model.addAttribute("codeRKS022List", codeRKS022List);
		model.addAttribute("codeRKS025List", codeRKS025List);
		
		model.addAttribute("asmblyAsembyActListTotCnt", asmblyAsembyActListTotCnt);
		model.addAttribute("asmblyAsembyActList", asmblyAsembyActList);
		model.addAttribute("paginationInfo", paging.getPaging(mdmSearchVO));
		model.addAttribute("listStartNo", paging.getFirstRecord());
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		
		model.addAttribute("isExcelSearch", "Y");
		model.addAttribute("excelSearchCnList", outbbsList.replace("'", ""));
		model.addAttribute("excelSearchCollection", "AsmblyAsemby");
		
		currentSearchCnList = "";
		for(MdmTnsrAsmblyAsembyActVO vo : asmblyAsembyActList){
			currentSearchCnList += vo.getASEMBY_CN() + ",";
		}
		
		if(!"".equals(currentSearchCnList))
			currentSearchCnList = currentSearchCnList.substring(0, currentSearchCnList.length()-1);
		
		if(!"".equals(outbbsList))
			currentSearchCnList = outbbsList;
		
		model.addAttribute("cnList", currentSearchCnList.replace("'", ""));
		model.addAttribute("EndPage", paging.getTotalPages());
		
        return "clikMng/mdm/MdmAsmblyAsembyList";
        
    }

    /**
     * 의원정보 상세 보기
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmAsmblyAsemby"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmAsmblyAsembyView.do")
    public String MdmAsmblyAsembyView(
    			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
    			, ModelMap model
    			, HttpServletRequest request) throws Exception {

    	//상세화면 이전다음 관련 조회 S
    	String cnList = "";
    	if("Y".equals(mdmSearchVO.getIsPrevNextPaging()))
    	{
    		// 의회선택 값이 null 아닌 경우
        	if( mdmSearchVO.getSchLoAsmTyCode() != null && !mdmSearchVO.getSchLoAsmTyCode().equals("") ) {
        		List<String> schLoAsmTyCodeList = new ArrayList<String>();
        		schLoAsmTyCodeList = mdmTnsrAsmblyCodeService.selectLoAsmTyCodeList(mdmSearchVO); // 의회선택 리스트
        		mdmSearchVO.setSchLoAsmTyCodeList(schLoAsmTyCodeList);
        	}
        	
        	// 지역선택 값이 null 아닌 경우
        	if( mdmSearchVO.getSchRegion() != null && !mdmSearchVO.getSchRegion().equals("") ) {
        		List<String> schRegionList = new ArrayList<String>();
        		schRegionList = mdmTnsrAsmblyCodeService.selectRegionList(mdmSearchVO); // 지역선택 리스트
        		mdmSearchVO.setSchRegionList(schRegionList);
        	}
        	
    		//이전 다음 CN 정보 조회 
    		int asmblyAsembyActListTotCnt = 0;
    		asmblyAsembyActListTotCnt = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyActListTotCnt(mdmSearchVO); // 총 검색 건수
        	
        	MdmPaging paging = new MdmPaging();
        	paging.setPagingCalc(asmblyAsembyActListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
        	paging.setParam(mdmSearchVO);
        	
        	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
        	mdmSearchVO.setLastRecord(paging.getLastRecord());
        	
        	List<MdmTnsrAsmblyAsembyActVO> asmblyAsembyActList = null;
        	asmblyAsembyActList = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyActList(mdmSearchVO);  // 검색 리스트
	    	for(MdmTnsrAsmblyAsembyActVO vo : asmblyAsembyActList){
	    		cnList += vo.getASEMBY_CN() + ",";
	    	}
	    	
	    	cnList = cnList.substring(0, cnList.length()-1);
	    	
	    	//이전다음 페이징된 경우 해당하는 데이터 CN 설정
	    	if( "".equals(mdmSearchVO.getRasmblyId())  
	    			&& "".equals(mdmSearchVO.getAsembyId())
	    			&& "next".equals(mdmSearchVO.getPrevNextGubun()))
	    	{
    			mdmSearchVO.setRasmblyId(asmblyAsembyActList.get(0).getRASMBLY_ID());
    			mdmSearchVO.setAsembyId(asmblyAsembyActList.get(0).getASEMBY_ID());
    		}
	    	else if( "".equals(mdmSearchVO.getRasmblyId())  
	    			&& "".equals(mdmSearchVO.getAsembyId())
	    			&& "prev".equals(mdmSearchVO.getPrevNextGubun()))
	    	{
    			mdmSearchVO.setRasmblyId(asmblyAsembyActList.get(asmblyAsembyActList.size()-1).getRASMBLY_ID());
    			mdmSearchVO.setAsembyId(asmblyAsembyActList.get(asmblyAsembyActList.size()-1).getASEMBY_ID());
    		}
	    	
	    	model.addAttribute("cnList", cnList);
	    	model.addAttribute("rasmblyId", mdmSearchVO.getRasmblyId());
    		model.addAttribute("asembyId", mdmSearchVO.getAsembyId());
			model.addAttribute("EndPage", paging.getTotalPages());
    	}
    	else
    	{
    		model.addAttribute("cnList", request.getParameter("cnList"));
    		model.addAttribute("rasmblyId", request.getParameter("rasmblyId"));
    		model.addAttribute("asembyId", request.getParameter("asembyId"));
			model.addAttribute("EndPage", request.getParameter("EndPage"));
    	}
		//상세화면 이전다음 관련 조회 E
    	
    	MdmCalUtil calUtil = new MdmCalUtil();
   		calUtil.setDecimalFormat("00");
   		String today = calUtil.getToday();
    	
   		//의원 정보
   		MdmTnsrAsmblyAsembyVO asmblyAsembyView = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyView(mdmSearchVO);
    	model.addAttribute("asmblyAsembyView", asmblyAsembyView);
    	
    	//의원활동 정보
    	List<HashMap<String,Object>> lamanActivityList = mdmTnsrAsmblyAsembyService.selectMdmLamanActivityList(asmblyAsembyView);
    	String[] clobList = {"GRT","WNPZ_CAREER","ASEMBY_CAREER","ACDMCR_MATTER","CAREER_MATTER"};
    	// 해당 map안의 CLOB형 객체를 취득하고  
    	for(HashMap<String,Object> obj : lamanActivityList){
    		for(String clobFieldNm : clobList){
	    		Clob clob = (Clob) obj.get(clobFieldNm);
			  
			    if(clob != null){
		    	    // reader를 생성  
		    	    Reader reader = clob.getCharacterStream();  
		    	  
		    	    StringBuffer out = new StringBuffer();  
		    	    char[] buff = new char[1024];  
		    	    int nchars = 0;  
		    	  
		    	    // 스트링 버퍼에 append 시킨후  
		    	    while ((nchars = reader.read(buff)) > 0) {  
		    	        out.append(buff, 0, nchars);  
		    	    }  
		    	  
		    	    // String형태로 재할당.  
		    	    obj.put(clobFieldNm, out.toString());  
			    }else{
			    	obj.put(clobFieldNm, "");
			    }
    		}
    	}
    	model.addAttribute("lamanActivityList", lamanActivityList);
    	
    	//미활동사유
    	List<MdmDetailCodeVO> noactResnStdcdList = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS022");
    	model.addAttribute("noactResnStdcdList", noactResnStdcdList);
    	
    	//선거구 목록
    	List<MdmTnsrAsmblyEstVO> codeEstbsList = null;
    	MdmTnsrAsmblyEstVO mdmTnsrAsmblyEstVO = new MdmTnsrAsmblyEstVO();
    	if( asmblyAsembyView.getRASMBLY_ID() != null &&  !asmblyAsembyView.getRASMBLY_ID().equals("") ) {
    		mdmTnsrAsmblyEstVO.setRASMBLY_ID(asmblyAsembyView.getRASMBLY_ID());
    		//mdmTnsrAsmblyEstVO.setRASMBLY_NUMPR(asmblyAsembyView.getRASMBLY_NUMPR());
    		codeEstbsList = mdmTnsrAsmblyAsembyService.selectMdmTnsrEstIdList(mdmTnsrAsmblyEstVO);
    		model.addAttribute("codeEstbsList", codeEstbsList);
    	}
    	
    	//정당 목록
       	List<MdmTnmPprtyVO> codePprtyList = mdmTnsrAsmblyCodeService.selectMdmTnmPprty(today);
       	model.addAttribute("codePprtyList", codePprtyList);
       	
    	//직위 목록
       	List<MdmDetailCodeVO> positionList = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS011");
    	model.addAttribute("positionList", positionList);
		
		model.addAttribute("mdmSearchVO", mdmSearchVO);

        return "clikMng/mdm/MdmAsmblyAsembyView2";
    }

    /**
     * 지역구 가져오기
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmTnsrAsmblyEst"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmTnsrEstIdList.do")
    public String MdmTnpInsttclList3(@ModelAttribute("mdmTnsrAsmblyEstVO") MdmTnsrAsmblyEstVO mdmTnsrAsmblyEstVO, ModelMap model) throws Exception {

    	List<MdmTnsrAsmblyEstVO> codeEstIdList = mdmTnsrAsmblyAsembyService.selectMdmTnsrEstIdList(mdmTnsrAsmblyEstVO);

    	model.addAttribute("codeEstIdList",  codeEstIdList);

        return "clikMng/mdm/MdmTnsrEstIdList";
        
    }

    /**
     * 의원정보 등록폼
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmAsmblyAsemby"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmAsmblyAsembyForm.do")
    public String MdmAsmblyAsembyForm(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
    	MdmCalUtil calUtil = new MdmCalUtil();
   		calUtil.setDecimalFormat("00");
   		String today = calUtil.getToday();

   	//미활동사유
    	List<MdmDetailCodeVO> noactResnStdcdList = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS022");
    	model.addAttribute("noactResnStdcdList", noactResnStdcdList);
    	
    	//정당 목록
       	List<MdmTnmPprtyVO> codePprtyList = mdmTnsrAsmblyCodeService.selectMdmTnmPprty(today);
       	model.addAttribute("codePprtyList", codePprtyList);
       	
       	//직위 목록
       	List<MdmDetailCodeVO> positionList = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS011");
    	model.addAttribute("positionList", positionList);
    	
        return "clikMng/mdm/MdmAsmblyAsembyForm";
    }

    /**
     * 의원정보를 등록한다.
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmAsmblyAsemby"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping("/mdm/MdmAsmblyAsembyRegist.do")
    public String insertMemberInfo(final MultipartHttpServletRequest multiRequest, @ModelAttribute("fileVO") MdmFileVO fileVO, 
    		@ModelAttribute("mdmTnsrAsmblyAsembyVO") MdmTnsrAsmblyAsembyVO mdmTnsrAsmblyAsembyVO,
    		@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
    		BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

		List<MdmFileVO> result = null;
	    MdmFileVO fvo = null;
	    MdmStrUtil strUtil = new MdmStrUtil();
	    MdmCalUtil calUtil = new MdmCalUtil();
	    calUtil.setDecimalFormat("00");
	    
	    int uid = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembySeq();
	    
	    String ASEMBY_ID = Integer.toString(uid);
	    
    	String ASEMBY_CN = "CLIKM" + calUtil.getYear() + strUtil.getDecimalFormat("0000000000", uid);
	    mdmTnsrAsmblyAsembyVO.setASEMBY_CN(ASEMBY_CN);
	    mdmTnsrAsmblyAsembyVO.setASEMBY_ID(ASEMBY_ID);
	    mdmTnsrAsmblyAsembyVO.setRASMBLY_ASEMBY_ID(ASEMBY_ID);
	    mdmTnsrAsmblyAsembyVO.setFRST_REGIST_DT(calUtil.getTodayWithTime2());
	    mdmTnsrAsmblyAsembyVO.setRASMBLY_ID(multiRequest.getParameter("schLoAsmCode"));
	    
	    if( mdmTnsrAsmblyAsembyVO.getPhone2() != null && !mdmTnsrAsmblyAsembyVO.getPhone2().equals("") ) {
	    	String MBTLNUM = mdmTnsrAsmblyAsembyVO.getPhone1() + "-" + mdmTnsrAsmblyAsembyVO.getPhone2() + "-" +mdmTnsrAsmblyAsembyVO.getPhone3();
	    	mdmTnsrAsmblyAsembyVO.setMBTLNUM(MBTLNUM);
	    }
	    
	    if( mdmTnsrAsmblyAsembyVO.getEmail1() != null && !mdmTnsrAsmblyAsembyVO.getEmail1().equals("") ) {
	    	String EMAIL = mdmTnsrAsmblyAsembyVO.getEmail1();
		    if( mdmTnsrAsmblyAsembyVO.getEmail2() != null && !mdmTnsrAsmblyAsembyVO.getEmail2().equals("") ) {
		    	EMAIL = EMAIL + "@" + mdmTnsrAsmblyAsembyVO.getEmail2();
		    }
		    else {
		    	EMAIL = EMAIL + "@" + mdmTnsrAsmblyAsembyVO.getEmail3();
		    }
	    	mdmTnsrAsmblyAsembyVO.setEMAIL(EMAIL);
	    }
	    
	    mdmTnsrAsmblyAsembyVO.setBRTHDY(mdmTnsrAsmblyAsembyVO.getBRTHDY().replace("-", ""));
	    mdmTnsrAsmblyAsembyVO.setBRTHDY(mdmTnsrAsmblyAsembyVO.getBRTHDY().replace("/", ""));
	    
	    try {
			
	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				
	   			result = fileUtil.parseFileInf(files, "assemblyinfo", calUtil.getYear() + "/" + mdmTnsrAsmblyAsembyVO.getRASMBLY_ID() + "/" + calUtil.getToday() + "/RES203/");
		   			
	   			if( result.size() > 0 ) {
						
					fvo = new MdmFileVO();
					fvo = result.get(0);
						
					mdmTnsrAsmblyAsembyVO.setPHOTO_FILE_NM(fvo.getStreFileNm()+"."+fvo.getFileExtsn());
					mdmTnsrAsmblyAsembyVO.setPHOTO_FILE_PATH(fvo.getFileStreCours());
					mdmTnsrAsmblyAsembyVO.setPHOTO_FILE_HASH(getEncryptSHA256(new File(fvo.getFileStreCours())));
						
				}
			}
	    }catch(Exception e){
	    	e.printStackTrace();
	    	File f = new File(fvo.getFileStreCours());
	    	if(f.exists()){
	    		f.delete();
	    	}
	    }
		
	    mdmTnsrAsmblyAsembyService.insertMdmTnsrAsmblyAsemby(mdmTnsrAsmblyAsembyVO);
	    
	    
	    
	    //의원 직위 정보
	    String[] ACT_RASMBLY_NUMPR = mdmTnsrAsmblyAsembyVO.getACT_RASMBLY_NUMPR();

		String[] POSITION_INFO_LIST = mdmTnsrAsmblyAsembyVO.getPOSITION_INFO();
		
		MdmTnsrAsmblyAsembyVO vo = null;
		String[] POSITION = null;
		
		for(int i = 0; POSITION_INFO_LIST != null && i < POSITION_INFO_LIST.length; i++)
		{
			POSITION = POSITION_INFO_LIST[i].split("@");
			
			for(int j = 0; j < POSITION.length; j++){
				vo = new MdmTnsrAsmblyAsembyVO();
				String[] values = POSITION[j].split("#");
				if(values.length == 3){
					vo.setHT_SE_STDCD(values[0]);
					vo.setMTGNM_ID(values[1]);
					vo.setASEMBY_OFCPS_STDCD(values[2]);
		
					vo.setASEMBY_ID(ASEMBY_ID);
					vo.setRASMBLY_ID(mdmTnsrAsmblyAsembyVO.getRASMBLY_ID());
					vo.setRASMBLY_NUMPR(Integer.parseInt(ACT_RASMBLY_NUMPR[i]));
					vo.setASEMBY_CN(ASEMBY_CN);
					
					mdmTnsrAsmblyAsembyService.insertMdmTnsrAsmblyOfCps(vo);
				}
			}
		}
		
	    //의원활동 정보
		MdmTnsrAsmblyAsembyActVO mdmTnsrAsmblyAsembyActVO = null;
		
		String[] ACT_ACT_AT = mdmTnsrAsmblyAsembyVO.getACT_ACT_AT();
		String[] ACT_NOACT_RESN_STDCD = mdmTnsrAsmblyAsembyVO.getACT_NOACT_RESN_STDCD();
		String[] ACT_EST_ID = mdmTnsrAsmblyAsembyVO.getACT_EST_ID();
		String[] ACT_PPRTY_CODE = mdmTnsrAsmblyAsembyVO.getACT_PPRTY_CODE();
		String[] ACT_CAREER_MATTER = mdmTnsrAsmblyAsembyVO.getACT_CAREER_MATTER();
		String[] ACT_ACDMCR_MATTER = mdmTnsrAsmblyAsembyVO.getACT_ACDMCR_MATTER();
		String[] ACT_GRT = mdmTnsrAsmblyAsembyVO.getACT_GRT();
		String[] ACT_ASEMBY_CAREER = mdmTnsrAsmblyAsembyVO.getACT_ASEMBY_CAREER();
		String[] ACT_WNPZ_CAREER = mdmTnsrAsmblyAsembyVO.getACT_WNPZ_CAREER();
	    
		for(int i = 0; ACT_RASMBLY_NUMPR != null && i < ACT_RASMBLY_NUMPR.length; i++)
		{
			mdmTnsrAsmblyAsembyActVO = new MdmTnsrAsmblyAsembyActVO();
			mdmTnsrAsmblyAsembyActVO.setRASMBLY_ID(mdmTnsrAsmblyAsembyVO.getRASMBLY_ID());
			mdmTnsrAsmblyAsembyActVO.setRASMBLY_NUMPR(ACT_RASMBLY_NUMPR[i]);
			mdmTnsrAsmblyAsembyActVO.setASEMBY_ID(ASEMBY_ID);
			mdmTnsrAsmblyAsembyActVO.setASEMBY_CN(ASEMBY_CN);
			mdmTnsrAsmblyAsembyActVO.setACT_AT(ACT_ACT_AT[i]);
			mdmTnsrAsmblyAsembyActVO.setNOACT_RESN_STDCD(ACT_NOACT_RESN_STDCD[i]);
			mdmTnsrAsmblyAsembyActVO.setEST_ID(ACT_EST_ID[i]);
			mdmTnsrAsmblyAsembyActVO.setPPRTY_CODE(ACT_PPRTY_CODE[i]);
			mdmTnsrAsmblyAsembyActVO.setCAREER_MATTER(ACT_CAREER_MATTER[i]);
			mdmTnsrAsmblyAsembyActVO.setACDMCR_MATTER(ACT_ACDMCR_MATTER[i]);
			mdmTnsrAsmblyAsembyActVO.setGRT(ACT_GRT[i]);
			mdmTnsrAsmblyAsembyActVO.setASEMBY_CAREER(ACT_ASEMBY_CAREER[i]);
			mdmTnsrAsmblyAsembyActVO.setWNPZ_CAREER(ACT_WNPZ_CAREER[i]);
			
			mdmTnsrAsmblyAsembyService.insertMdmTnsrAsmblyAsembyAct(mdmTnsrAsmblyAsembyActVO);
		}
		
		model.addAttribute("mdmSearchVO", mdmSearchVO);

		return "redirect:/mdm/MdmAsmblyAsembyList.do";
    }   
    

    /**
     * 의원정보 수정
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmAsmblyAsemby"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping("/mdm/MdmAsmblyAsembyUpdate.do")
    public String updateMemberInfo(final MultipartHttpServletRequest multiRequest, @ModelAttribute("fileVO") MdmFileVO fileVO, 
    		@ModelAttribute("mdmTnsrAsmblyAsembyVO") MdmTnsrAsmblyAsembyVO mdmTnsrAsmblyAsembyVO,
    		@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
    		BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

		List<MdmFileVO> result = null;
	    MdmFileVO fvo = null;
	    MdmCalUtil calUtil = new MdmCalUtil();
	    calUtil.setDecimalFormat("00");
	    
	    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    mdmTnsrAsmblyAsembyVO.setLAST_UPDUSR_ID(user.getMngrId());
	    mdmTnsrAsmblyAsembyVO.setLAST_UPDT_DT(calUtil.getTodayWithTime2());
	    
	    if( mdmTnsrAsmblyAsembyVO.getPhone2() != null && !mdmTnsrAsmblyAsembyVO.getPhone2().equals("") ) {
	    	String MBTLNUM = mdmTnsrAsmblyAsembyVO.getPhone1() + "-" + mdmTnsrAsmblyAsembyVO.getPhone2() + "-" +mdmTnsrAsmblyAsembyVO.getPhone3();
	    	mdmTnsrAsmblyAsembyVO.setMBTLNUM(MBTLNUM);
	    }
	    
	    if( mdmTnsrAsmblyAsembyVO.getEmail1() != null && !mdmTnsrAsmblyAsembyVO.getEmail1().equals("") ) {
	    	String EMAIL = mdmTnsrAsmblyAsembyVO.getEmail1();
		    if( mdmTnsrAsmblyAsembyVO.getEmail2() != null && !mdmTnsrAsmblyAsembyVO.getEmail2().equals("") ) {
		    	EMAIL = EMAIL + "@" + mdmTnsrAsmblyAsembyVO.getEmail2();
		    }
		    else {
		    	EMAIL = EMAIL + "@" + mdmTnsrAsmblyAsembyVO.getEmail3();
		    }
	    	mdmTnsrAsmblyAsembyVO.setEMAIL(EMAIL);
	    }

	    try {
			
	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				
				mdmSearchVO.setRasmblyId(mdmTnsrAsmblyAsembyVO.getRASMBLY_ID());
				mdmSearchVO.setAsembyId(mdmTnsrAsmblyAsembyVO.getASEMBY_ID());
				
		   		MdmTnsrAsmblyAsembyVO asmblyAsembyView = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyView(mdmSearchVO);
		   		
		   		if(asmblyAsembyView.getPHOTO_FILE_PATH() == null || "".equals(asmblyAsembyView.getPHOTO_FILE_PATH()))
		   		{
		   			result = fileUtil.parseFileInf(files, "assemblyinfo", calUtil.getYear() + "/" + mdmTnsrAsmblyAsembyVO.getRASMBLY_ID() + "/" + calUtil.getToday() + "/RES203/");
		   			
		   			if( result.size() > 0 ) {
						
						fvo = new MdmFileVO();
						fvo = result.get(0);
						
						mdmTnsrAsmblyAsembyVO.setPHOTO_FILE_NM(fvo.getOrignlFileNm());
						mdmTnsrAsmblyAsembyVO.setPHOTO_FILE_PATH(fvo.getFileStreCours());
						mdmTnsrAsmblyAsembyVO.setPHOTO_FILE_HASH(getEncryptSHA256(new File(fvo.getFileStreCours())));
						
					}
		   		}
		   		else //기존 사진 파일 존재할 경우 대체
		   		{
		   			String filePath = asmblyAsembyView.getPHOTO_FILE_PATH();
		   			filePath = filePath.replace("/clik-data/clik.ear/clik.war/image/assemphoto/", "");
		   			result = fileUtil.parseFileInf(files, "assemblyinfo",filePath.substring(0,filePath.lastIndexOf("/"))+"/");
		   			
		   			if( result.size() > 0 ) {
						
						fvo = new MdmFileVO();
						fvo = result.get(0);
						
						//기존 파일 삭제
						File file = new File(asmblyAsembyView.getPHOTO_FILE_PATH());
				   		if(file.exists()){
				   			file.delete();
				   		}
				   		
				   		//신규 파일 복사
				   		try {
					   		FileInputStream fis = new FileInputStream(fvo.getFileStreCours());
					   		FileOutputStream fos = new FileOutputStream(asmblyAsembyView.getPHOTO_FILE_PATH());
				   		 
					   		int data = 0;
					   		while((data=fis.read())!=-1) {
					   			fos.write(data);
					   		}
						   		fis.close();
						   		fos.close();
				   		   
				   		} catch (IOException e) {
				   			e.printStackTrace();
				   		}
				   		
				   		mdmTnsrAsmblyAsembyVO.setPHOTO_FILE_HASH(getEncryptSHA256(new File(fvo.getFileStreCours())));
				   		
				   		//업로드 파일 삭제
				   		file = new File(fvo.getFileStreCours());
				   		if(file.exists()){
				   			file.delete();
				   		}
					}
		   			else //사진 삭제만 했을 경우
		   			{
						
						if(asmblyAsembyView.getPHOTO_FILE_PATH() != null && !"".equals(asmblyAsembyView.getPHOTO_FILE_PATH())){
					   		File file = new File(asmblyAsembyView.getPHOTO_FILE_PATH());
					   		if(file.exists()){
					   			file.delete();
					   		}
						}

						mdmTnsrAsmblyAsembyService.deleteAsembyPhotoFile(mdmSearchVO);
		   			}
		   		}
			}//의원사진  E
		
		    mdmTnsrAsmblyAsembyService.updateMdmTnsrAsmblyAsemby(mdmTnsrAsmblyAsembyVO);
		    
		    
		    //의원활동 정보
			MdmTnsrAsmblyAsembyActVO mdmTnsrAsmblyAsembyActVO = null;
			
			String[] ACT_RASMBLY_NUMPR = mdmTnsrAsmblyAsembyVO.getACT_RASMBLY_NUMPR();
			String[] ACT_ACT_AT = mdmTnsrAsmblyAsembyVO.getACT_ACT_AT();
			String[] ACT_NOACT_RESN_STDCD = mdmTnsrAsmblyAsembyVO.getACT_NOACT_RESN_STDCD();
			String[] ACT_EST_ID = mdmTnsrAsmblyAsembyVO.getACT_EST_ID();
			String[] ACT_PPRTY_CODE = mdmTnsrAsmblyAsembyVO.getACT_PPRTY_CODE();
			String[] ACT_CAREER_MATTER = mdmTnsrAsmblyAsembyVO.getACT_CAREER_MATTER();
			String[] ACT_ACDMCR_MATTER = mdmTnsrAsmblyAsembyVO.getACT_ACDMCR_MATTER();
			String[] ACT_GRT = mdmTnsrAsmblyAsembyVO.getACT_GRT();
			String[] ACT_ASEMBY_CAREER = mdmTnsrAsmblyAsembyVO.getACT_ASEMBY_CAREER();
			String[] ACT_WNPZ_CAREER = mdmTnsrAsmblyAsembyVO.getACT_WNPZ_CAREER();
			String[] ACT_CUD_CODE = mdmTnsrAsmblyAsembyVO.getACT_CUD_CODE();
			
			for(int i = 0; ACT_RASMBLY_NUMPR != null && i < ACT_RASMBLY_NUMPR.length; i++)
			{
				mdmTnsrAsmblyAsembyActVO = new MdmTnsrAsmblyAsembyActVO();
				mdmTnsrAsmblyAsembyActVO.setRASMBLY_ID(mdmTnsrAsmblyAsembyVO.getRASMBLY_ID());
				mdmTnsrAsmblyAsembyActVO.setRASMBLY_NUMPR(ACT_RASMBLY_NUMPR[i]);
				mdmTnsrAsmblyAsembyActVO.setASEMBY_ID(mdmTnsrAsmblyAsembyVO.getASEMBY_ID());
				mdmTnsrAsmblyAsembyActVO.setASEMBY_CN(mdmTnsrAsmblyAsembyVO.getASEMBY_CN());
				mdmTnsrAsmblyAsembyActVO.setACT_AT(ACT_ACT_AT[i]);
				mdmTnsrAsmblyAsembyActVO.setNOACT_RESN_STDCD(ACT_NOACT_RESN_STDCD[i]);
				mdmTnsrAsmblyAsembyActVO.setEST_ID(ACT_EST_ID[i]);
				mdmTnsrAsmblyAsembyActVO.setPPRTY_CODE(ACT_PPRTY_CODE[i]);
				mdmTnsrAsmblyAsembyActVO.setCAREER_MATTER(ACT_CAREER_MATTER[i]);
				mdmTnsrAsmblyAsembyActVO.setACDMCR_MATTER(ACT_ACDMCR_MATTER[i]);
				mdmTnsrAsmblyAsembyActVO.setGRT(ACT_GRT[i]);
				mdmTnsrAsmblyAsembyActVO.setASEMBY_CAREER(ACT_ASEMBY_CAREER[i]);
				mdmTnsrAsmblyAsembyActVO.setWNPZ_CAREER(ACT_WNPZ_CAREER[i]);
				mdmTnsrAsmblyAsembyActVO.setCUD_CODE(ACT_CUD_CODE[i]);
				
				if(mdmTnsrAsmblyAsembyService.updateMdmTnsrAsmblyAsembyAct(mdmTnsrAsmblyAsembyActVO) == 0)
					mdmTnsrAsmblyAsembyService.insertMdmTnsrAsmblyAsembyAct(mdmTnsrAsmblyAsembyActVO);
			}
		    
		    //의원 직위 정보

			String[] POSITION_INFO_LIST = mdmTnsrAsmblyAsembyVO.getPOSITION_INFO();
			
			MdmTnsrAsmblyAsembyVO vo = null;
			String[] POSITION = null;
			
			for(int i = 0; POSITION_INFO_LIST != null && i < POSITION_INFO_LIST.length; i++)
			{
				POSITION = POSITION_INFO_LIST[i].split("@");
				
				for(int j = 0; j < POSITION.length; j++){
					vo = new MdmTnsrAsmblyAsembyVO();
					String[] values = POSITION[j].split("#");
					if(values.length == 3){
						vo.setHT_SE_STDCD(values[0]);
						vo.setMTGNM_ID(values[1]);
						vo.setASEMBY_OFCPS_STDCD(values[2]);
			
						vo.setASEMBY_ID(mdmTnsrAsmblyAsembyVO.getASEMBY_ID());
						vo.setRASMBLY_ID(mdmTnsrAsmblyAsembyVO.getRASMBLY_ID());
						vo.setRASMBLY_NUMPR(Integer.parseInt(ACT_RASMBLY_NUMPR[i]));
						vo.setASEMBY_CN(mdmTnsrAsmblyAsembyVO.getASEMBY_CN());
						vo.setCUD_CODE(ACT_CUD_CODE[i]);
						
						if(mdmTnsrAsmblyAsembyService.updateMdmTnsrAsmblyOfCps(vo) == 0)
							mdmTnsrAsmblyAsembyService.insertMdmTnsrAsmblyOfCps(vo);
					}
				}
			}
		    
			model.addAttribute("message", "정상처리되었습니다.");
			
			model.addAttribute("menuName", "지방의회 의원정보");
			model.addAttribute("cnId", mdmTnsrAsmblyAsembyVO.getASEMBY_CN());
			model.addAttribute("work", "수정");
			model.addAttribute("EndPage", multiRequest.getParameter("EndPage"));
			model.addAttribute("cnList", multiRequest.getParameter("cnList"));
			model.addAttribute("sort", multiRequest.getParameter("sort"));
			model.addAttribute("return_url", "/mdm/MdmAsmblyAsembyView.do?rasmblyId="+mdmTnsrAsmblyAsembyVO.getRASMBLY_ID()+"&asembyId="+mdmTnsrAsmblyAsembyVO.getASEMBY_ID());
	    }catch(Exception e) {
	    	model.addAttribute("message", e.getMessage());
		}finally{
			model.addAttribute("EndPage", multiRequest.getParameter("EndPage"));
		}
	    
	    
        return "clikMng/mdm/MdmResult";
    }   

    /**
     * 소속위원회 가져오기
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmAsmblyAsemby"
     * @throws Exception
     */
//    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
//    @RequestMapping(value="/mdm/MdmJrsdCmitIdList.do")
//    public String MdmJrsdCmitIdList(@ModelAttribute("mdmTnsrAsmblyMtgnmVO") MdmTnsrAsmblyMtgNmVO mdmTnsrAsmblyMtgnmVO, ModelMap model) throws Exception {
//
//    	List<MdmTnsrAsmblyMtgNmVO> codeJrsdCmitIdList = mdmTnsrAsmblyAsembyService.selectMdmTnsrJrsdCmitIdList(mdmTnsrAsmblyMtgnmVO);
//
//    	model.addAttribute("codeJrsdCmitIdList",  codeJrsdCmitIdList);
//
//        return "clikMng/mdm/MdmJrsdCmitIdList";
//        
//    }

    /**
     * 의원정보 게시 수정
     * @param mdmTnsrAsmblyAsembyVO
     * @param model
     * @return	"/mdm/MdmIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmTnsrAsmblyAsembyIsView.do")
    public String MdmTnsrAsmblyAsembyIsView(@ModelAttribute("mdmTnsrAsmblyAsembyVO") MdmTnsrAsmblyAsembyVO mdmTnsrAsmblyAsembyVO, ModelMap model) throws Exception {
    	String arr1[];
    	String arr2[];
    	String isView[];
    	
    	MdmTnsrAsmblyAsembyVO vo = null;
    	
    	isView = mdmTnsrAsmblyAsembyVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		vo = new MdmTnsrAsmblyAsembyVO();
    		arr1 = isView[i].split("=");
    		arr2 = arr1[0].split("-");
    		vo.setRASMBLY_ID(arr2[0]);
    		//vo.setRASMBLY_NUMPR(Integer.parseInt(arr2[1]));
    		vo.setASEMBY_ID(arr2[1]);
    		vo.setISVIEW(arr1[1]);
    		mdmTnsrAsmblyAsembyService.updateMdmTnsrAsmblyAsembyIsView(vo);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 의원정보 삭제
     * @param mdmTnsrAsmblyAsembyVO
     * @param model
     * @return	"/mdm/MdmIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmTnsrAsmblyAsembyDeleteChk.do")
    public String MdmTnsrAsmblyAsembyDeleteChk(@ModelAttribute("mdmTnsrAsmblyAsembyVO") MdmTnsrAsmblyAsembyVO mdmTnsrAsmblyAsembyVO, ModelMap model) throws Exception {
    	String arr1[];
    	String arr2[];
    	String isView[];
    	
    	MdmTnsrAsmblyAsembyVO vo = null;
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	isView = mdmTnsrAsmblyAsembyVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		vo = new MdmTnsrAsmblyAsembyVO();
    		arr1 = isView[i].split("=");
    		arr2 = arr1[0].split("-");
    		vo.setRASMBLY_ID(arr2[0]);
    		//vo.setRASMBLY_NUMPR(Integer.parseInt(arr2[1]));
    		vo.setASEMBY_ID(arr2[1]);
    		vo.setISVIEW(arr1[1]);
    		vo.setLAST_UPDUSR_ID(user.getMngrId());
    		//mdmTnsrAsmblyAsembyService.deleteMdmTnsrAsmblyAsembyChk(vo);
    		mdmTnsrAsmblyAsembyService.deleteMdmTnsrAsmblyAsemby(vo);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";

    }

    /**
     * 의원정보 삭제
     * @param mdmTnsrAsmblyAsembyVO
     * @param model
     * @return	"/mdm/MdmIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmAsmblyAsembyDelete.do")
    public String MdmTnsrAsmblyAsembyDelete(
    		@ModelAttribute("mdmTnsrAsmblyAsembyVO") MdmTnsrAsmblyAsembyVO mdmTnsrAsmblyAsembyVO,
    		HttpServletRequest request,
    		ModelMap model) throws Exception {
   		
        try{
        	
        	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	    mdmTnsrAsmblyAsembyVO.setLAST_UPDUSR_ID(user.getMngrId());
    	    
        	model.addAttribute("menuName", "지방의회 의원정보");
        	model.addAttribute("cnId", mdmTnsrAsmblyAsembyVO.getASEMBY_CN());
			model.addAttribute("work", "삭제");
			mdmTnsrAsmblyAsembyVO.setISVIEW("D");
			mdmTnsrAsmblyAsembyService.deleteMdmTnsrAsmblyAsemby(mdmTnsrAsmblyAsembyVO);
			//mdmTnsrAsmblyAsembyService.deleteMdmTnsrAsmblyAsembyChk(mdmTnsrAsmblyAsembyVO);
			model.addAttribute("message", "정삭처리되었습니다.");
    	}catch(Exception e){
    		model.addAttribute("message", "오류가 발생하였습니다.");
    	}finally{
    		model.put("EndPage", request.getParameter("EndPage"));
    	}
        	
        model.addAttribute("return_url", "MdmAsmblyAsembyView.do?rasmblyId="+mdmTnsrAsmblyAsembyVO.getRASMBLY_ID()+"&rasmblyNumpr="+mdmTnsrAsmblyAsembyVO.getRASMBLY_NUMPR()+"&asembyId="+mdmTnsrAsmblyAsembyVO.getASEMBY_ID());
        
        return "clikMng/mdm/MdmResult";
    }

    /**
     * 의원정보 - 최근 수집일
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    public String MdmTnsrAsmblyAsembyMaxRegDate() throws Exception {
    	MdmStrUtil strUtil = new MdmStrUtil();
    	MdmCalUtil calUtil = new MdmCalUtil();
		calUtil.setDecimalFormat("00");

		String dt2 = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyActMaxRegDate(calUtil.getToday());
		dt2 = strUtil.getStringFormat8("-", dt2);

		return dt2;
    }

    /**
     * 의원정보 - 최근 수집일
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    public String MdmTnsrAsmblyAsembyRegDate(String dt2) throws Exception {
    	MdmCalUtil calUtil = new MdmCalUtil();
    	calUtil.setDelimiter("-");
		calUtil.setDecimalFormat("00");
		String[] arr = dt2.split("-");

		return calUtil.getDateBeforeDt(calUtil.getUnixTime(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2])), -7);
    }

    /**
     * 의원 사진 삭제
     * @param mdmTnsrAsmblyAsembyVO
     * @param model
     * @return	"/mdm/MdmIsView"
     * @throws Exception
     */
    @IncludedInfo(name="의원 사진정보 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmAsmblyDeleteAsembyPhotoFile.do")
    public String deleteAsembyPhotoFile(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
    	
    	//의원 정보
   		MdmTnsrAsmblyAsembyVO asmblyAsembyView = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyView(mdmSearchVO);
   		
    	int deleteCount = mdmTnsrAsmblyAsembyService.deleteAsembyPhotoFile(mdmSearchVO);
    	
    	if(deleteCount > 0){
    		File file = new File(asmblyAsembyView.getPHOTO_FILE_PATH());
       		if(file.exists()){
       			file.delete();
       		}
       		model.addAttribute("message", "정상처리되었습니다.");
       		
    	}else{
    		model.addAttribute("message", "false");
    	}
    	model.addAttribute("return_url", "/mdm/MdmAsmblyAsembyView.do?rasmblyId="+asmblyAsembyView.getRASMBLY_ID()+"&rasmblyNumpr="+asmblyAsembyView.getRASMBLY_NUMPR()+"&asembyId="+asmblyAsembyView.getASEMBY_ID());
		model.addAttribute("menuName", "지방의회 의원정보");
		return "clikMng/mdm/MdmResult";
    }
    
    /**
     * 관련 회의록 보기
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmTnsrAsmblyEst"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmTnsrMinutes.do")
    public String MdmTnsrMinutes(@ModelAttribute("MdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {

    	List<HashMap<String,Object>> minutesList = mdmTnsrAsmblyAsembyService.selectMdmTnsrMinutes(mdmSearchVO);
    	
    	// 해당 map안의 CLOB형 객체를 취득하고  
//    	for(HashMap<String,Object> obj : minutesList){
//		    Clob clob = (Clob) obj.get("SPKNG_CN");  
//		  
//		    if(clob != null){
//	    	    // reader를 생성  
//	    	    Reader reader = clob.getCharacterStream();  
//	    	  
//	    	    StringBuffer out = new StringBuffer();  
//	    	    char[] buff = new char[1024];  
//	    	    int nchars = 0;  
//	    	  
//	    	    // 스트링 버퍼에 append 시킨후  
//	    	    while ((nchars = reader.read(buff)) > 0) {  
//	    	        out.append(buff, 0, nchars);  
//	    	    }  
//	    	  
//	    	    // String형태로 재할당.  
//	    	    obj.put("SPKNG_CN", out.toString());  
//		    }else{
//		    	obj.put("SPKNG_CN", "");
//		    }
//		   
//    	}
    	
    	model.addAttribute("minutesList",  minutesList);
    	
    	int listTotCnt = minutesList.size() > 0 ? Integer.parseInt(minutesList.get(0).get("TOTCNT").toString()) : 0;
    	
    	MdmPaging paging = new MdmPaging();
    	paging.setPagingCalc(listTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
    	paging.setParam(mdmSearchVO);
    	
    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVO.setLastRecord(paging.getLastRecord());
    	
    	int listStartNo = paging.getFirstRecord(); 
    	
		model.addAttribute("paginationInfo", paging.getPaging(mdmSearchVO));
		model.addAttribute("listStartNo", listStartNo);
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		model.addAttribute("asembyId", mdmSearchVO.getAsembyId());
		model.addAttribute("rasmblyId", mdmSearchVO.getRasmblyId());
    	
    	
        return "clikMng/mdm/MdmAsembyMinutes";
        
    }
    
    /**
     * 지방의회 별 대수 목록 조회
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmTnsrAsmblyEst"
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmTnsrRasmblyList.do")
    public String MdmTnsrRasmblyList(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {

    	List<HashMap<String,Object>> list = mdmTnsrAsmblyAsembyService.MdmTnsrRasmblyList(mdmSearchVO);

    	JSONArray jArr = new JSONArray();
    	JSONObject jObj = null;
    	
    	for(HashMap<String,Object> vo : list){
    		jObj = new JSONObject();
    		jObj.put("RASMBLY_NUMPR",vo.get("RASMBLY_NUMPR"));
    		jArr.add(jObj);
    	}
    	
    	model.addAttribute("msg",  jArr.toString());

        return "clikMng/mdm/MdmIsView";
        
    }
    
    /**
     * 지방의회 별 회의명 목록 조회
     * @param mdmMemberVO
     * @param model
     * @return	"/Mdm/MdmTnsrAsmblyEst"
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmTnsrMtgnmList.do")
    public String MdmTnsrMtgnmList(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {

    	List<HashMap<String,Object>> list = mdmTnsrAsmblyAsembyService.MdmTnsrMtgnmList(mdmSearchVO);

    	JSONArray jArr = new JSONArray();
    	JSONObject jObj = null;
    	
    	for(HashMap<String,Object> vo : list){
    		jObj = new JSONObject();
    		jObj.put("RASMBLY_ID",vo.get("RASMBLY_ID"));
    		jObj.put("RASMBLY_NUMPR",vo.get("RASMBLY_NUMPR"));
    		jObj.put("MTGNM_ID",vo.get("MTGNM_ID"));
    		jObj.put("MTGNM",vo.get("MTGNM"));
    		jObj.put("MTGNM_KND_STDCD",vo.get("MTGNM_KND_STDCD"));
    		jArr.add(jObj);
    	}
    	
    	model.addAttribute("msg",  jArr.toString());

        return "clikMng/mdm/MdmIsView";
        
    }
    
    /**
	 * 파일을 SHA-256으로 encode하여 반환해준다. 
	 * 
	 * @author lth
	 * @param file : SHA-256 해쉬값을 얻을 파일
	 * @return encode 된 문자열
	 * @throws Exception 
	 * */
	public String getEncryptSHA256(File file)
	{
		String encryptedSHA256 = "";
		MessageDigest md = null;
		FileInputStream in = null;
		if(file == null || !file.isFile()){
			return null;
		}
		try {
			md = MessageDigest.getInstance("SHA-256");
			in = new FileInputStream(file);
			byte[] bytes = new byte[1024];
			int byteCount;
			while ((byteCount = in.read(bytes)) > 0) {
				md.update(bytes, 0, byteCount);
			}
			encryptedSHA256 = new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{in.close();}catch(Exception e){}
		}

		return encryptedSHA256;
	}
	
    /**
     * 지방의회의원 엑셀파일로 다운로드 한다.
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/selectMdmAsmblyAsembyExcel.do")
    public ModelAndView selectMdmAsmblyAsembyExcel(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
    											, @SuppressWarnings("rawtypes") Map commandMap
    											, ModelMap model) throws Exception {
    	
    	if( !mdmSearchVO.getSchBrtcCode().equals("") &&  mdmSearchVO.getSchLoAsmCode().equals("") ) {
    		mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode()+"001");
    	}

    	if( mdmSearchVO.getSchDflt() == null || !mdmSearchVO.getSchDflt().equals("N") ) { // 총 검색 건수(기본 1주일간)
    		MdmCalUtil calUtil = new MdmCalUtil();
    		calUtil.setDecimalFormat("00");
    		String today = calUtil.getToday();
    		
    		if(mdmSearchVO.getSchDt1() == null || "".equals(mdmSearchVO.getSchDt1())){
    			mdmSearchVO.setSchDt1(today);
    		}
    		if(mdmSearchVO.getSchDt2() == null || "".equals(mdmSearchVO.getSchDt2())){
    			mdmSearchVO.setSchDt2(today);
    		}
    	} 
    	
    	// 의회선택 값이 null 아닌 경우
    	if( mdmSearchVO.getSchLoAsmTyCode() != null && !mdmSearchVO.getSchLoAsmTyCode().equals("") ) {
    		List<String> schLoAsmTyCodeList = new ArrayList<String>();
    		schLoAsmTyCodeList = mdmTnsrAsmblyCodeService.selectLoAsmTyCodeList(mdmSearchVO); // 의회선택 리스트
    		mdmSearchVO.setSchLoAsmTyCodeList(schLoAsmTyCodeList);
    	}
    	
    	// 지역선택 값이 null 아닌 경우
    	if( mdmSearchVO.getSchRegion() != null && !mdmSearchVO.getSchRegion().equals("") ) {
    		List<String> schRegionList = new ArrayList<String>();
    		schRegionList = mdmTnsrAsmblyCodeService.selectRegionList(mdmSearchVO); // 지역선택 리스트
    		mdmSearchVO.setSchRegionList(schRegionList);
    	}
    	
    	//대수,선거구,정당 정보 조회를 위하여 처리
    	if("".equals(mdmSearchVO.getSchRasmblyNumpr())){
    		mdmSearchVO.setSchRasmblyNumpr("ALL");
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
    	int asmblyAsembyActListTotCnt = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyActListTotCnt(mdmSearchVO); 

    	//엑셀조회 후 다운로드할 경우 정보설정
    	if(commandMap.get("isExcelSearch") != null && commandMap.get("isExcelSearch").equals("Y")){
    		mdmSearchVO.setSchDtConditionOperators("");
    		mdmSearchVO.setSchKey("cnId");
			mdmSearchVO.setSchDt1("");
			mdmSearchVO.setSchDt2("");
			String excelSearchCnList = commandMap.get("excelSearchCnList").toString().replaceAll("&apos;", "'");
			excelSearchCnList = excelSearchCnList.replaceAll(",", "','");
			excelSearchCnList = "'" + excelSearchCnList + "'"; 
			mdmSearchVO.setSchKw(excelSearchCnList);
			int maxExcelSearchCount = Integer.parseInt(MdmProperties.getProperty("Globals.mdm.max_excel_search_count"));
			asmblyAsembyActListTotCnt = asmblyAsembyActListTotCnt > maxExcelSearchCount ? maxExcelSearchCount : asmblyAsembyActListTotCnt;
		}
    	
    	mdmSearchVO.setLastRecord(asmblyAsembyActListTotCnt);
    	
    	// 검색 리스트
    	List<MdmTnsrAsmblyAsembyActVO> asmblyAsembyActList = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyActList(mdmSearchVO);  
    	
    	//대수,선거구,정당 정보 조회를 위하여 처리
    	if("ALL".equals(mdmSearchVO.getSchRasmblyNumpr())){
    		mdmSearchVO.setSchRasmblyNumpr("");
    	}
    	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", mdmSearchVO);
        map.put("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? "" : (String) commandMap.get("selSearchGubun"));
        
    	map.put("resultList", asmblyAsembyActList);
    	map.put("resultCnt", Integer.toString(asmblyAsembyActListTotCnt));

        return new ModelAndView("MdmAsmblyAsembyExcel", map);
		
    }
    
    /**
     * 지방의회의원 텍스트파일로 다운로드 한다.
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/selectMdmAsmblyAsembyText.do")
    public ModelAndView selectMdmAsmblyAsembyText(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
    											, @SuppressWarnings("rawtypes") Map commandMap
    											, ModelMap model) throws Exception {
    	
    	if( !mdmSearchVO.getSchBrtcCode().equals("") &&  mdmSearchVO.getSchLoAsmCode().equals("") ) {
    		mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode()+"001");
    	}

    	if( mdmSearchVO.getSchDflt() == null || !mdmSearchVO.getSchDflt().equals("N") ) { // 총 검색 건수(기본 1주일간)
    		MdmCalUtil calUtil = new MdmCalUtil();
    		calUtil.setDecimalFormat("00");
    		String today = calUtil.getToday();
    		
    		if(mdmSearchVO.getSchDt1() == null || "".equals(mdmSearchVO.getSchDt1())){
    			mdmSearchVO.setSchDt1(today);
    		}
    		if(mdmSearchVO.getSchDt2() == null || "".equals(mdmSearchVO.getSchDt2())){
    			mdmSearchVO.setSchDt2(today);
    		}
    	} 
    	
    	// 의회선택 값이 null 아닌 경우
    	if( mdmSearchVO.getSchLoAsmTyCode() != null && !mdmSearchVO.getSchLoAsmTyCode().equals("") ) {
    		List<String> schLoAsmTyCodeList = new ArrayList<String>();
    		schLoAsmTyCodeList = mdmTnsrAsmblyCodeService.selectLoAsmTyCodeList(mdmSearchVO); // 의회선택 리스트
    		mdmSearchVO.setSchLoAsmTyCodeList(schLoAsmTyCodeList);
    	}
    	
    	// 지역선택 값이 null 아닌 경우
    	if( mdmSearchVO.getSchRegion() != null && !mdmSearchVO.getSchRegion().equals("") ) {
    		List<String> schRegionList = new ArrayList<String>();
    		schRegionList = mdmTnsrAsmblyCodeService.selectRegionList(mdmSearchVO); // 지역선택 리스트
    		mdmSearchVO.setSchRegionList(schRegionList);
    	}
    	
    	//대수,선거구,정당 정보 조회를 위하여 처리
    	if("".equals(mdmSearchVO.getSchRasmblyNumpr())){
    		mdmSearchVO.setSchRasmblyNumpr("ALL");
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
    	int asmblyAsembyActListTotCnt = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyActListTotCnt(mdmSearchVO); 

    	//엑셀조회 후 다운로드할 경우 정보설정
    	if(commandMap.get("isExcelSearch") != null && commandMap.get("isExcelSearch").equals("Y")){
    		mdmSearchVO.setSchDtConditionOperators("");
    		mdmSearchVO.setSchKey("cnId");
			mdmSearchVO.setSchDt1("");
			mdmSearchVO.setSchDt2("");
			String excelSearchCnList = commandMap.get("excelSearchCnList").toString().replaceAll("&apos;", "'");
			excelSearchCnList = excelSearchCnList.replaceAll(",", "','");
			excelSearchCnList = "'" + excelSearchCnList + "'"; 
			mdmSearchVO.setSchKw(excelSearchCnList);
			int maxExcelSearchCount = Integer.parseInt(MdmProperties.getProperty("Globals.mdm.max_excel_search_count"));
			asmblyAsembyActListTotCnt = asmblyAsembyActListTotCnt > maxExcelSearchCount ? maxExcelSearchCount : asmblyAsembyActListTotCnt;
		}
    	
    	mdmSearchVO.setLastRecord(asmblyAsembyActListTotCnt);
    	
    	// 검색 리스트
    	List<MdmTnsrAsmblyAsembyActVO> asmblyAsembyActList = mdmTnsrAsmblyAsembyService.selectMdmTnsrAsmblyAsembyActList(mdmSearchVO);  
    	
    	//대수,선거구,정당 정보 조회를 위하여 처리
    	if("ALL".equals(mdmSearchVO.getSchRasmblyNumpr())){
    		mdmSearchVO.setSchRasmblyNumpr("");
    	}  

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", mdmSearchVO);
        map.put("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? "" : (String) commandMap.get("selSearchGubun"));
        
    	map.put("resultList", asmblyAsembyActList);
    	map.put("resultCnt", Integer.toString(asmblyAsembyActListTotCnt));

        return new ModelAndView("MdmAsmblyAsembyText", map);
		
    }
}
