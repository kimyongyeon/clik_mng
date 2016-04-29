package clikmng.nanet.go.kr.mdm.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.support.SessionStatus;
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
import clikmng.nanet.go.kr.mdm.model.MdmIsViewVO;
import clikmng.nanet.go.kr.mdm.model.MdmOrgCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSeedVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSiteVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnpInsttCodeEstbsVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyItncAsembyVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMtgNmVO;
import clikmng.nanet.go.kr.mdm.service.MdmFileMngService;
import clikmng.nanet.go.kr.mdm.service.MdmFileMngUtil;
import clikmng.nanet.go.kr.mdm.service.MdmProperties;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyBiService;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyCodeService;
import clikmng.nanet.go.kr.mdm.service.MdmTnsrAsmblyMintsService;
import clikmng.nanet.go.kr.mdm.utl.MdmCalUtil;
import clikmng.nanet.go.kr.mdm.utl.MdmExcelUtil;
import clikmng.nanet.go.kr.mdm.utl.MdmFtp;
import clikmng.nanet.go.kr.mdm.utl.MdmPaging;
import clikmng.nanet.go.kr.mdm.utl.MdmStrUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;

/**
 * 메타데이터 관리를 처리하는 Controller 클래스
 */
@Controller
public class MdmTnsrAsmblyBiController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "MdmTnsrAsmblyBiService")
    private MdmTnsrAsmblyBiService mdmTnsrAsmblyBiService;

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

    @Resource(name = "MdmTnsrAsmblyMintsService")
    private MdmTnsrAsmblyMintsService mdmTnsrAsmblyMintsService;
    
    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

    /**
     * 의안 목록 조회
     * @param mdmBillVO
     * @param model
     * @return	"/Mdm/MdmBillList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/MdmBillList.do")
    public String MdmBillList(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
    	if( !mdmSearchVO.getSchBrtcCode().equals("") &&  mdmSearchVO.getSchLoAsmCode().equals("") ) {
    		mdmSearchVO.setSchLoAsmCode(mdmSearchVO.getSchBrtcCode()+"001");
    	}
 
    	if( mdmSearchVO.getSchKw() != null && !mdmSearchVO.getSchKw().trim().equals("") ) {
    		if( mdmSearchVO.getSchKey().equals("") || mdmSearchVO.getSchKey().equals("schBiSj") ) {
    			mdmSearchVO.setSchBiSj(mdmSearchVO.getSchKw());    // 의안명
    		}
    		else if( mdmSearchVO.getSchKey().equals("schPropsr") ) {
    			mdmSearchVO.setSchPropsr(mdmSearchVO.getSchKw());  // 제안자
    		}
    		else {
    	    	List<String> schJrsdCmitIdList = new ArrayList<String>();
    	    	schJrsdCmitIdList = mdmTnsrAsmblyCodeService.selectMdmMtgIdList(mdmSearchVO); // 소관위원회
    			mdmSearchVO.setSchJrsdCmitIdList(schJrsdCmitIdList);
    		}
    	}
    	
    	//기본정렬 설정
    	if(mdmSearchVO.getSort() == null || mdmSearchVO.getSort().equals("")){
    		mdmSearchVO.setSort("ITNC_DE DESC");
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
    	
    	/*
    	 * 검색 form 기본 정보 조회 S
    	 * */

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

    	List<MdmTnpInsttCodeEstbsVO> codeEstbsList = null;
    	if( mdmSearchVO.getSchLoAsmTyCode() != null &&  !mdmSearchVO.getSchLoAsmTyCode().equals("") ) {
    		codeEstbsList = mdmTnsrAsmblyCodeService.selectMdmTnpInsttCodeEstbs(mdmSearchVO);
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
    	List<MdmDetailCodeVO> codeRIS018List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRIS018();     // 의안종류
    	List<MdmDetailCodeVO> codeRIS020List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRIS020();     // 의안종류
    	List<MdmDetailCodeVO> codeRKS022List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS022();     // 수집유형
    	List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS025();     // 수집(지역)

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
    	
    	/*
    	 * 검색 form 기본 정보 조회 E
    	 * */
    	
    	int billListTotCnt = 0;
    	billListTotCnt = mdmTnsrAsmblyBiService.selectMdmBillListTotCnt(mdmSearchVO);
    	
    	MdmPaging paging = new MdmPaging();
    	paging.setPagingCalc(billListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
    	paging.setParam(mdmSearchVO);
    	
    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVO.setLastRecord(paging.getLastRecord());

    	List<MdmTnsrAsmblyBiVO> billList = null;
    	billList = mdmTnsrAsmblyBiService.selectMdmBillList(mdmSearchVO);
    	
    	if("cnId".equals(mdmSearchVO.getSchKey())){
    		mdmSearchVO.setSchKw(mdmSearchVO.getSchKw().replace("'", ""));
    		mdmSearchVO.setSchDt1(schDt1);
        	mdmSearchVO.setSchDt2(schDt2);
        	mdmSearchVO.setSchDt1(schDt3);
        	mdmSearchVO.setSchDt2(schDt4);
    	}
    	
    	model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
		model.addAttribute("codeOrgSiteList", codeOrgSiteList);
		model.addAttribute("siteList", siteList);
		model.addAttribute("seedList", seedList);
    	model.addAttribute("codeRKS021AllList", codeRKS021AllList);
    	
    	model.addAttribute("codeEstbsList",  codeEstbsList);
		model.addAttribute("codeRIS018List", codeRIS018List);
		model.addAttribute("codeRIS020List", codeRIS020List);
		model.addAttribute("codeRKS022List", codeRKS022List);
		model.addAttribute("codeRKS025List", codeRKS025List);

		model.addAttribute("billListTotCnt", billListTotCnt);
		model.addAttribute("billList", billList);
		model.addAttribute("paginationInfo", paging.getPaging(mdmSearchVO));
		model.addAttribute("listStartNo", paging.getFirstRecord());
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		
		String currentSearchCnList = "";
		for(MdmTnsrAsmblyBiVO vo : billList){
			currentSearchCnList += vo.getBI_CN() + ",";
		}
		
		if(!"".equals(currentSearchCnList))
			currentSearchCnList = currentSearchCnList.substring(0, currentSearchCnList.length()-1);
		
		model.addAttribute("cnList", currentSearchCnList.replace("'", ""));
		model.addAttribute("EndPage", paging.getTotalPages());
		
        return "clikMng/mdm/MdmBillList";
    }
    
    /**
     * 의안 목록 조회 (엑셀 조회)
     * @param mdmBillVO
     * @param model
     * @return	"/Mdm/MdmBillList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/ExcelMdmBillList.do")
    public String ExcelMdmBillList(final MultipartHttpServletRequest multiRequest, 
														@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, 
														ModelMap model) throws Exception {
    	
    	//기본정렬 설정
    	if(mdmSearchVO.getSort() == null || mdmSearchVO.getSort().equals("")){
    		mdmSearchVO.setSort("ITNC_DE DESC");
    	}
    	
    	/*
    	 * 검색 form 기본 정보 조회 S
    	 * */
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

    	List<MdmTnpInsttCodeEstbsVO> codeEstbsList = null;
    	if( mdmSearchVO.getSchLoAsmTyCode() != null &&  !mdmSearchVO.getSchLoAsmTyCode().equals("") ) {
    		codeEstbsList = mdmTnsrAsmblyCodeService.selectMdmTnpInsttCodeEstbs(mdmSearchVO);
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
    	List<MdmDetailCodeVO> codeRIS018List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRIS018();     // 의안종류
    	List<MdmDetailCodeVO> codeRIS020List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRIS020();     // 의안종류
    	List<MdmDetailCodeVO> codeRKS022List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS022();     // 수집유형
    	List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS025();     // 수집(지역)

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
    	List<MdmTnsrAsmblyBiVO> billList = mdmTnsrAsmblyBiService.selectMdmBillList(mdmSearchVOTemp);
    	String currentSearchCnList = "";
		for(MdmTnsrAsmblyBiVO vo : billList){
			currentSearchCnList += "'" + vo.getBI_CN() + "',";
		}
		
		if(!"".equals(currentSearchCnList)){
			currentSearchCnList = currentSearchCnList.substring(0, currentSearchCnList.length()-1);
			outbbsList = currentSearchCnList;
		}
		
    	// 총 검색 건수
    	int billListTotCnt = mdmTnsrAsmblyBiService.selectMdmBillListTotCnt(mdmSearchVOTemp);
    	
    	MdmPaging paging = new MdmPaging();
    	paging.setPagingCalc(billListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
    	paging.setParam(mdmSearchVO);

    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVO.setLastRecord(paging.getLastRecord());
    	mdmSearchVOTemp.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVOTemp.setLastRecord(paging.getLastRecord());
    	
   		// 검색 리스트
    	billList = mdmTnsrAsmblyBiService.selectMdmBillList(mdmSearchVOTemp);
    	 
    	model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
		model.addAttribute("codeOrgSiteList", codeOrgSiteList);
		model.addAttribute("siteList", siteList);
		model.addAttribute("seedList", seedList);
    	model.addAttribute("codeRKS021AllList", codeRKS021AllList);
    	
    	model.addAttribute("codeEstbsList",  codeEstbsList);
		model.addAttribute("codeRIS018List", codeRIS018List);
		model.addAttribute("codeRIS020List", codeRIS020List);
		model.addAttribute("codeRKS022List", codeRKS022List);
		model.addAttribute("codeRKS025List", codeRKS025List);

		model.addAttribute("billListTotCnt", billListTotCnt);
		model.addAttribute("billList", billList);
		model.addAttribute("paginationInfo", paging.getPaging(mdmSearchVO));
		model.addAttribute("listStartNo", paging.getFirstRecord());
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		
		model.addAttribute("isExcelSearch", "Y");
		model.addAttribute("excelSearchCnList", outbbsList.replace("'", ""));
		model.addAttribute("excelSearchCollection", "Bill");
		
		currentSearchCnList = "";
		for(MdmTnsrAsmblyBiVO vo : billList){
			currentSearchCnList += vo.getBI_CN() + ",";
		}
		
		if(!"".equals(currentSearchCnList))
			currentSearchCnList = currentSearchCnList.substring(0, currentSearchCnList.length()-1);
		
		if(!"".equals(outbbsList))
			currentSearchCnList = outbbsList;
		
		model.addAttribute("cnList", currentSearchCnList.replace("'", ""));
		model.addAttribute("EndPage", paging.getTotalPages());
		
        return "clikMng/mdm/MdmBillList";
    }

    /**
     * 의안 내용 보기1
     * @param mdmBillVO
     * @param model
     * @return	"/Mdm/MdmBillView1"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 내용 보기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillView1.do")
    public String MdmBillView1(
    			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
    			, ModelMap model
    			, HttpServletRequest request) throws Exception {
    	
    	//상세화면 이전다음 관련 조회 S
    	String cnList = "";
    	if("Y".equals(mdmSearchVO.getIsPrevNextPaging()))
    	{
    		//이전 다음 CN 정보 조회 
    		int billListTotCnt = 0;
        	billListTotCnt = mdmTnsrAsmblyBiService.selectMdmBillListTotCnt(mdmSearchVO);
        	
        	MdmPaging paging = new MdmPaging();
        	paging.setPagingCalc(billListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
        	paging.setParam(mdmSearchVO);
        	
        	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
        	mdmSearchVO.setLastRecord(paging.getLastRecord());

        	List<MdmTnsrAsmblyBiVO> billList = null;
        	billList = mdmTnsrAsmblyBiService.selectMdmBillList(mdmSearchVO);
	    	for(MdmTnsrAsmblyBiVO vo : billList){
	    		cnList += vo.getBI_CN() + ",";
	    	}
	    	
	    	cnList = cnList.substring(0, cnList.length()-1);
				
	    	//이전다음 페이징된 경우 해당하는 데이터 CN 설정
	    	if( "".equals(mdmSearchVO.getCnId())  && "next".equals(mdmSearchVO.getPrevNextGubun())){
    			mdmSearchVO.setCnId(billList.get(0).getBI_CN());
    		}else if( "".equals(mdmSearchVO.getCnId()) && "prev".equals(mdmSearchVO.getPrevNextGubun())){
    			mdmSearchVO.setCnId(billList.get(billList.size()-1).getBI_CN());
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
    	
    	MdmStrUtil strUtil = new MdmStrUtil();
    	MdmTnsrAsmblyMtgNmVO mdmTnsrAsmblyMtgnmVO = new MdmTnsrAsmblyMtgNmVO();

		MdmTnsrAsmblyBiVO billVO = mdmTnsrAsmblyBiService.selectMdmBillView(mdmSearchVO);
		List<MdmTnsrAsmblyBiFileVO> billFileList = mdmTnsrAsmblyBiService.selectMdmBillFileList(mdmSearchVO);
		
		
    	mdmTnsrAsmblyMtgnmVO.setRASMBLY_ID(billVO.getRASMBLY_ID());
    	mdmTnsrAsmblyMtgnmVO.setRASMBLY_NUMPR(billVO.getRASMBLY_NUMPR());

    	// 회기구분명
		List<MdmDetailCodeVO> sesnSeStdcdList = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS009");
		model.addAttribute("sesnSeStdcdList", sesnSeStdcdList);
    			
    	// 의안종류
		List<MdmDetailCodeVO> mdmBiKndStdCd = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS018");
		model.addAttribute("mdmBiKndStdCd", mdmBiKndStdCd);
    			
		//의회별 대수정보
    	List<HashMap<String,Object>> rasmblyNumperList = mdmTnsrAsmblyMintsService.selectRasmblyNumperList(billVO.getRASMBLY_ID());
    	model.addAttribute("rasmblyNumperList", rasmblyNumperList);
    	
    	// 소관위원회
    	List<MdmTnsrAsmblyMtgNmVO> mdmJrsdCmitId = mdmTnsrAsmblyCodeService.selectMdmJrsdCmitIdList(mdmTnsrAsmblyMtgnmVO); 
    	model.addAttribute("mdmJrsdCmitId", mdmJrsdCmitId);
    	
    	//회기구분코드
    	HashMap<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("rasmbly_id",billVO.getRASMBLY_ID());
    	paramMap.put("rasmbly_numpr",billVO.getRASMBLY_NUMPR());
    	paramMap.put("sesn_se_stdcd",billVO.getSESN_SE_STDCD());
    	List<HashMap<String,Object>> sesnList = mdmTnsrAsmblyMintsService.selectRasmblySesnList(paramMap);
    	model.put("sesnList",sesnList);
    	
    	// 위원회 처리결과
    	List<MdmDetailCodeVO> mdmCmitResult = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS020");
    	model.addAttribute("mdmCmitResult",  mdmCmitResult);

    	// 본회의 처리결과
		List<MdmDetailCodeVO> mdmPlnmtResult = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS020"); 
		model.addAttribute("mdmPlnmtResult", mdmPlnmtResult);
		
		//발의의원
		List<MdmTnsrAsmblyItncAsembyVO> asembyList = mdmTnsrAsmblyBiService.selectMdmBillItncAsembyList(mdmSearchVO.getCnId());
		if( asembyList != null ) {
			StringBuffer sb = new StringBuffer();
			sb.setLength(0);
			MdmTnsrAsmblyItncAsembyVO cmvo = null;
			for(int i = 0; i < asembyList.size(); i++) {
				cmvo = new MdmTnsrAsmblyItncAsembyVO();
				cmvo = asembyList.get(i);
				if( i > 0 ) {
					sb.append(", ");
				}
				sb.append(cmvo.getASEMBY_NM());
			}
			billVO.setPROPSR_CM(sb.toString());
		}
		
		billVO.setITNC_DE(strUtil.getStringFormat8("-", billVO.getITNC_DE()));
		billVO.setFRWRD_DE(strUtil.getStringFormat8("-", billVO.getFRWRD_DE()));
		billVO.setCMIT_REPORT_DE(strUtil.getStringFormat8("-", billVO.getCMIT_REPORT_DE()));
		billVO.setCMIT_SBMISN_DE(strUtil.getStringFormat8("-", billVO.getCMIT_SBMISN_DE()));
		billVO.setCMIT_PROCESS_DE(strUtil.getStringFormat8("-", billVO.getCMIT_PROCESS_DE()));
		billVO.setPLNMT_REPORT_DE(strUtil.getStringFormat8("-", billVO.getPLNMT_REPORT_DE()));
		billVO.setPLNMT_SBMISN_DE(strUtil.getStringFormat8("-", billVO.getPLNMT_SBMISN_DE()));
		billVO.setPLNMT_PROCESS_DE(strUtil.getStringFormat8("-", billVO.getPLNMT_PROCESS_DE()));
		billVO.setTRNSF_DE(strUtil.getStringFormat8("-", billVO.getTRNSF_DE()));
		billVO.setPRMLGT_DE(strUtil.getStringFormat8("-", billVO.getPRMLGT_DE()));
		
		billVO.setFRST_REGIST_DT(strUtil.getStringFormat8("-", billVO.getFRST_REGIST_DT()));
		billVO.setLAST_UPDT_DT(strUtil.getStringFormat8("-", billVO.getLAST_UPDT_DT()));
		billVO.setDELETE_DT(strUtil.getStringFormat8("-", billVO.getDELETE_DT()));
    	
		
		
    	String df = "";
    	if( billFileList.size() > 0 ) {
    		MdmTnsrAsmblyBiFileVO vo = new MdmTnsrAsmblyBiFileVO();
   			vo = billFileList.get(0);
   			if( mdmSearchVO.getDisfile().equals("") &&  vo.getDOC_CNVR_STTU_CODE() != null && (vo.getDOC_CNVR_STTU_CODE().equals("1") || vo.getDOC_CNVR_STTU_CODE().equals("3")) ) {
   				int flg = 0;
   				df = vo.getBI_PDF_FILE_NM();
   				MdmFtp mdmFtp = new MdmFtp();
   				flg = mdmFtp.ftp(vo.getBI_PDF_FILE_PATH(), df);
   				if( flg == 1 ) {
   				}
   			}
    	}
    	
    	model.addAttribute("disfile", df);
		model.addAttribute("billVO", billVO);
		model.addAttribute("billFileList",  billFileList);
		model.addAttribute("asembyList",  	asembyList);
		
		model.addAttribute("mdmSearchVO", 	 mdmSearchVO);

		return "clikMng/mdm/MdmBillView2";
    }

    /**
     * 의안 메타데이터 내용 보기 2
     * @param mdmBillVO
     * @param model
     * @return	"/Mdm/MdmBillView2"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 내용 보기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillView2.do")
    public String MdmBillView2(
			@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
			, ModelMap model
			, HttpServletRequest request) throws Exception {
	
		//상세화면 이전다음 관련 조회 S
		String cnList = "";
		if("Y".equals(mdmSearchVO.getIsPrevNextPaging()))
		{
			//이전 다음 CN 정보 조회 
			int billListTotCnt = 0;
	    	billListTotCnt = mdmTnsrAsmblyBiService.selectMdmBillListTotCnt(mdmSearchVO);
	    	
	    	MdmPaging paging = new MdmPaging();
	    	paging.setPagingCalc(billListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
	    	paging.setParam(mdmSearchVO);
	    	
	    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
	    	mdmSearchVO.setLastRecord(paging.getLastRecord());
	
	    	List<MdmTnsrAsmblyBiVO> billList = null;
	    	billList = mdmTnsrAsmblyBiService.selectMdmBillList(mdmSearchVO);
	    	for(MdmTnsrAsmblyBiVO vo : billList){
	    		cnList += vo.getBI_CN() + ",";
	    	}
	    	
	    	cnList = cnList.substring(0, cnList.length()-1);
	    	
	    	//이전다음 페이징된 경우 해당하는 데이터 CN 설정
	    	if( "".equals(mdmSearchVO.getCnId())  && "next".equals(mdmSearchVO.getPrevNextGubun())){
				mdmSearchVO.setCnId(billList.get(0).getBI_CN());
			}else if( "".equals(mdmSearchVO.getCnId()) && "prev".equals(mdmSearchVO.getPrevNextGubun())){
				mdmSearchVO.setCnId(billList.get(billList.size()-1).getBI_CN());
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
		
		MdmStrUtil strUtil = new MdmStrUtil();
		MdmTnsrAsmblyMtgNmVO mdmTnsrAsmblyMtgnmVO = new MdmTnsrAsmblyMtgNmVO();
	
		MdmTnsrAsmblyBiVO billVO = mdmTnsrAsmblyBiService.selectMdmBillView(mdmSearchVO);
		List<MdmTnsrAsmblyBiFileVO> billFileList = mdmTnsrAsmblyBiService.selectMdmBillFileList(mdmSearchVO);
		
		
		mdmTnsrAsmblyMtgnmVO.setRASMBLY_ID(billVO.getRASMBLY_ID());
		mdmTnsrAsmblyMtgnmVO.setRASMBLY_NUMPR(billVO.getRASMBLY_NUMPR());
	
		// 회기구분명
		List<MdmDetailCodeVO> sesnSeStdcdList = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS009");
		model.addAttribute("sesnSeStdcdList", sesnSeStdcdList);
				
		// 의안종류
		List<MdmDetailCodeVO> mdmBiKndStdCd = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS018");
		model.addAttribute("mdmBiKndStdCd", mdmBiKndStdCd);
				
		//의회별 대수정보
		List<HashMap<String,Object>> rasmblyNumperList = mdmTnsrAsmblyMintsService.selectRasmblyNumperList(billVO.getRASMBLY_ID());
		model.addAttribute("rasmblyNumperList", rasmblyNumperList);
		
		// 소관위원회
		List<MdmTnsrAsmblyMtgNmVO> mdmJrsdCmitId = mdmTnsrAsmblyCodeService.selectMdmJrsdCmitIdList(mdmTnsrAsmblyMtgnmVO); 
		model.addAttribute("mdmJrsdCmitId", mdmJrsdCmitId);
		
		//회기구분코드
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("rasmbly_id",billVO.getRASMBLY_ID());
		paramMap.put("rasmbly_numpr",billVO.getRASMBLY_NUMPR());
		paramMap.put("sesn_se_stdcd",billVO.getSESN_SE_STDCD());
		List<HashMap<String,Object>> sesnList = mdmTnsrAsmblyMintsService.selectRasmblySesnList(paramMap);
		model.put("sesnList",sesnList);
		
		// 위원회 처리결과
		List<MdmDetailCodeVO> mdmCmitResult = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS020");
		model.addAttribute("mdmCmitResult",  mdmCmitResult);
	
		// 본회의 처리결과
		List<MdmDetailCodeVO> mdmPlnmtResult = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS020"); 
		model.addAttribute("mdmPlnmtResult", mdmPlnmtResult);
		
		//발의의원
		List<MdmTnsrAsmblyItncAsembyVO> asembyList = mdmTnsrAsmblyBiService.selectMdmBillItncAsembyList(mdmSearchVO.getCnId());
		if( asembyList != null ) {
			StringBuffer sb = new StringBuffer();
			sb.setLength(0);
			MdmTnsrAsmblyItncAsembyVO cmvo = null;
			for(int i = 0; i < asembyList.size(); i++) {
				cmvo = new MdmTnsrAsmblyItncAsembyVO();
				cmvo = asembyList.get(i);
				if( i > 0 ) {
					sb.append(", ");
				}
				sb.append(cmvo.getASEMBY_NM());
			}
			billVO.setPROPSR_CM(sb.toString());
		}
		
		billVO.setITNC_DE(strUtil.getStringFormat8("-", billVO.getITNC_DE()));
		billVO.setFRWRD_DE(strUtil.getStringFormat8("-", billVO.getFRWRD_DE()));
		billVO.setCMIT_REPORT_DE(strUtil.getStringFormat8("-", billVO.getCMIT_REPORT_DE()));
		billVO.setCMIT_SBMISN_DE(strUtil.getStringFormat8("-", billVO.getCMIT_SBMISN_DE()));
		billVO.setCMIT_PROCESS_DE(strUtil.getStringFormat8("-", billVO.getCMIT_PROCESS_DE()));
		billVO.setPLNMT_REPORT_DE(strUtil.getStringFormat8("-", billVO.getPLNMT_REPORT_DE()));
		billVO.setPLNMT_SBMISN_DE(strUtil.getStringFormat8("-", billVO.getPLNMT_SBMISN_DE()));
		billVO.setPLNMT_PROCESS_DE(strUtil.getStringFormat8("-", billVO.getPLNMT_PROCESS_DE()));
		billVO.setTRNSF_DE(strUtil.getStringFormat8("-", billVO.getTRNSF_DE()));
		billVO.setPRMLGT_DE(strUtil.getStringFormat8("-", billVO.getPRMLGT_DE()));
		
		billVO.setFRST_REGIST_DT(strUtil.getStringFormat8("-", billVO.getFRST_REGIST_DT()));
		billVO.setLAST_UPDT_DT(strUtil.getStringFormat8("-", billVO.getLAST_UPDT_DT()));
		billVO.setDELETE_DT(strUtil.getStringFormat8("-", billVO.getDELETE_DT()));
		
		String df = "";
    	if( billFileList.size() > 0 ) {
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
		
		model.addAttribute("disfile", df);
		model.addAttribute("billVO", billVO);
		model.addAttribute("billFileList",  billFileList);
		model.addAttribute("asembyList",  	asembyList);
		
		model.addAttribute("mdmSearchVO", 	 mdmSearchVO);
	
		return "clikMng/mdm/MdmBillView2";
    }
    
    /**
     * 의안 등록폼
     * @param mdmBillVO
     * @param model
     * @return	"/Mdm/MdmBillView"
     * @throws Exception
     */
//    @IncludedInfo(name="메타데이터 내용 보기", order = 680 ,gid = 50)
//    @RequestMapping(value="/mdm/MdmBillForm.do")
//    public String MdmBillForm(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
//   	
//		List<MdmDetailCodeVO> mdmBiKndStdCd = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS018");  // 의안종류
//		List<MdmDetailCodeVO> mdmCmitResult = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS020");  // 위원회 처리결과
//		List<MdmDetailCodeVO> mdmPlnmtResult = mdmTnsrAsmblyCodeService.selectMdmDetailCode("RIS020"); // 본회의 처리결과
//
//		model.addAttribute("mdmBiKndStdCd", mdmBiKndStdCd);
//		model.addAttribute("mdmCmitResult",  mdmCmitResult);
//		model.addAttribute("mdmPlnmtResult", mdmPlnmtResult);
//
//		return "clikMng/mdm/MdmBillForm";
//    }

    /**
     * 의안 - 중복 목록
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmBillFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 중복 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillDplctListCmmn.do")
    public String MdmBillDplctListCmmn(@ModelAttribute("mdmTnsrAsmblyBiVO") MdmTnsrAsmblyBiVO mdmTnsrAsmblyBiVO, ModelMap model) throws Exception {
    	List<MdmTnsrAsmblyBiVO> list = null;
   		list = mdmTnsrAsmblyBiService.selectMdmBillDplctListCmmn(mdmTnsrAsmblyBiVO);
		model.addAttribute("list", list);
		return "clikMng/mdm/MdmBillDplctListCmmn";
    }

    /**
     * 의안 - 첨부파일 목록
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmBillFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 첨부파일 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillFileListCmmn.do")
    public String MdmBillFileListCmmn(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	List<MdmFileVO> fileList = null;
    	if( request.getParameter("BI_CN") != null ) {
    		fileList = mdmTnsrAsmblyBiService.selectMdmBillFileListCmmn(request.getParameter("BI_CN"));
    	}
		model.addAttribute("fileList", fileList);
		return "clikMng/mdm/MdmFillListCmmn";
    }

    /**
     * 의안 - 첨부파일 다운로드
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmBillFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 첨부파일 다운로드", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillFileDownLoadByBiCn.do")
    public void MdmBillFileDownLoadByBiCn(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	String downFile = "";
    	String orgFileName = "";
    	if( request.getParameter("DOWNID") != null ) {
    		downFile = mdmTnsrAsmblyBiService.selectMdmBillFileDownPathByBiCn(request.getParameter("DOWNID"));
    		orgFileName = downFile;
    		fileUtil.downFile(request, response, downFile, orgFileName);
    	}
    }

    /**
     * 의안 - 첨부파일 다운로드
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmBillFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 첨부파일 다운로드", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillFileDownLoadByBiFileId.do")
    public void MdmBillFileDownLoadByBiFileId(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	String downFile = "";
    	String orgFileName = "";
    	if( request.getParameter("DOWNID") != null ) {
    		downFile = mdmTnsrAsmblyBiService.selectMdmBillFileDownPathByBiFileId(request.getParameter("DOWNID"));
    		orgFileName = downFile;
    		fileUtil.downFile(request, response, downFile, orgFileName);
    	}
    }

    /**
     * 의안정보 등록
     * @param mdmBillVO
     * @param model
     * @return "/Mdm/MdmBillInfo"
     * @throws Exception
     */
//    @IncludedInfo(name="메타데이터 등록", order = 680 ,gid = 50)
//    @RequestMapping("/mdm/MdmBillRegist.do")
//    public String insertMdmBill(final MultipartHttpServletRequest multiRequest, @ModelAttribute("fileVO") MdmFileVO fileVO, 
//    		@ModelAttribute("mdmTnsrAsmblyBiVO") MdmTnsrAsmblyBiVO mdmTnsrAsmblyBiVO,
//    		@ModelAttribute("mdmTnsrAsmblyBiFileVO") MdmTnsrAsmblyBiFileVO mdmTnsrAsmblyBiFileVO,
//    		@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
//    		BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {
//
//		List<MdmFileVO> result = null;
//	    MdmFileVO fvo = null;
//	    MdmCalUtil calUtil = new MdmCalUtil();
//	    MdmStrUtil strUtil = new MdmStrUtil();
//	    String dt = "";
//	    String asembyId = "";
//	    String[] cm;
//
//	    try {
//	    	calUtil.setDecimalFormat("00");
//	    	int uid = mdmTnsrAsmblyBiService.selectMdmBillSeq();
//	    	
//	    	String BI_CN = "CLIKM" + calUtil.getYear() + strUtil.getDecimalFormat("0000000000", uid);
//	    	mdmTnsrAsmblyBiVO.setBI_CN(BI_CN);
//	    	
//	    	String FRST_REGIST_DT = calUtil.getTodayWithTime2();
//	    	mdmTnsrAsmblyBiVO.setFRST_REGIST_DT(FRST_REGIST_DT);
//
//	    	mdmTnsrAsmblyBiVO.setBI_ID(calUtil.getYear() + strUtil.getDecimalFormat("0000000000", uid));
//	    	
//			String CMIT_RESULT_STDCD = mdmTnsrAsmblyBiVO.getCMIT_RESULT();
//			if( CMIT_RESULT_STDCD != null &&  !CMIT_RESULT_STDCD.equals("") ) {
//				mdmTnsrAsmblyBiVO.setCMIT_RESULT_STDCD(mdmTnsrAsmblyBiVO.getCMIT_RESULT());
//				mdmTnsrAsmblyBiVO.setLAST_RESULT_CL_STDCD(CMIT_RESULT_STDCD);
//				mdmTnsrAsmblyBiVO.setCMIT_RESULT(mdmTnsrAsmblyCodeService.selectMdmDetailCodeRIS020ByNm(CMIT_RESULT_STDCD)); 
//			}
//			
//			String PLNMT_RESULT_STDCD = mdmTnsrAsmblyBiVO.getPLNMT_RESULT();
//			if( PLNMT_RESULT_STDCD != null && !PLNMT_RESULT_STDCD.equals("") ) {
//				mdmTnsrAsmblyBiVO.setPLNMT_RESULT_STDCD(mdmTnsrAsmblyBiVO.getPLNMT_RESULT());
//				mdmTnsrAsmblyBiVO.setLAST_RESULT_CL_STDCD(PLNMT_RESULT_STDCD);
//				mdmTnsrAsmblyBiVO.setPLNMT_RESULT(mdmTnsrAsmblyCodeService.selectMdmDetailCodeRIS020ByNm(PLNMT_RESULT_STDCD)); 
//			}
//
//			dt = strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getITNC_DE());
//	    	if( !dt.equals("") ) {
//	    		mdmTnsrAsmblyBiVO.setITNC_YEAR(dt.substring(0,  4));
//	    	}
//    		mdmTnsrAsmblyBiVO.setITNC_DE(dt);
//    		mdmTnsrAsmblyBiVO.setFRWRD_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getFRWRD_DE()));
//    		mdmTnsrAsmblyBiVO.setCMIT_REPORT_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getCMIT_REPORT_DE()));
//    		mdmTnsrAsmblyBiVO.setCMIT_SBMISN_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getCMIT_SBMISN_DE()));
//    		mdmTnsrAsmblyBiVO.setCMIT_PROCESS_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getCMIT_PROCESS_DE()));
//    		mdmTnsrAsmblyBiVO.setPLNMT_REPORT_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getPLNMT_REPORT_DE()));
//    		mdmTnsrAsmblyBiVO.setPLNMT_SBMISN_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getPLNMT_SBMISN_DE()));
//    		mdmTnsrAsmblyBiVO.setPLNMT_PROCESS_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getPLNMT_PROCESS_DE()));
//    		mdmTnsrAsmblyBiVO.setTRNSF_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getTRNSF_DE()));
//    		mdmTnsrAsmblyBiVO.setPRMLGT_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getPRMLGT_DE()));
//    		
//			mdmTnsrAsmblyBiService.insertMdmBill(mdmTnsrAsmblyBiVO);
//
//    		if( mdmTnsrAsmblyBiVO.getPROPSR_CM() != null && !mdmTnsrAsmblyBiVO.getPROPSR_CM().trim().equals("") ) {
//    			List<MdmTnsrAsmblyItncAsembyVO> alist = new ArrayList<MdmTnsrAsmblyItncAsembyVO>();
//    			MdmTnsrAsmblyItncAsembyVO avo = null;
//    			
//    			cm = mdmTnsrAsmblyBiVO.getPROPSR_CM().trim().split("\\s+|\\,|\\n");
//    			for(int i = 0; i < cm.length; i++) {
//    				if( cm[i] == null || cm[i].trim().equals("") ) {
//    					continue;
//    				}
//    				
//    				mdmSearchVO.setSchKw(cm[i].trim().replace(" ", ""));
//    				mdmSearchVO.setSchLoAsmCode(mdmTnsrAsmblyBiVO.getRASMBLY_ID());
//    				mdmSearchVO.setSchRasmblyNumpr(Integer.toString(mdmTnsrAsmblyBiVO.getRASMBLY_NUMPR()));
//    				asembyId = mdmTnsrAsmblyCodeService.selectMdmAsembyCode(mdmSearchVO);
//    				if( asembyId == null ) {
//    					continue;
//    				}
//    				avo = new MdmTnsrAsmblyItncAsembyVO();
//    				avo.setASEMBY_ID(asembyId);
//    				avo.setRASMBLY_ID(mdmTnsrAsmblyBiVO.getRASMBLY_ID());
//    				avo.setBI_ID(mdmTnsrAsmblyBiVO.getBI_ID());
//    				avo.setFRST_REGIST_DT(FRST_REGIST_DT);
//    				avo.setBI_CN(BI_CN);
//    				avo.setRASMBLY_NUMPR(mdmTnsrAsmblyBiVO.getRASMBLY_NUMPR());
//    				alist.add(avo);
//    			}
//    			mdmTnsrAsmblyBiService.insertMdmBillCm(alist);
//    		}
//
//			final Map<String, MultipartFile> files = multiRequest.getFileMap();
//			if( !files.isEmpty() ) {
//				result = fileUtil.parseFileInf(files, "clik001", calUtil.getYear() + "/" + mdmTnsrAsmblyBiVO.getRASMBLY_ID() + "/" + calUtil.getToday() + "/file/RES403/");
//				if( result.size() > 0 ) {
//					fvo = new MdmFileVO();
//					fvo = result.get(0);
///*					
//					System.out.println("파일 확장자 : " + fvo.getFileExtsn());
//					System.out.println("파일 절대경로 : " + fvo.getFileStreCours());
//					System.out.println("파일 파일 크기 : " + fvo.getFileMg());
//					System.out.println("파일 오리지널 파일명 : " + fvo.getOrignlFileNm());
//					System.out.println("파일 저장된 파일명 : " + fvo.getStreFileNm());
//					System.out.println(fvo.getAtchFileId());
//					System.out.println(fvo.getFileSn());
//*/					
//					mdmTnsrAsmblyBiFileVO.setBI_ID(mdmTnsrAsmblyBiVO.getBI_ID());
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_ID(fvo.getStreFileNm());
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_NM(fvo.getOrignlFileNm());
//					mdmTnsrAsmblyBiFileVO.setFRST_REGIST_DT(calUtil.getTodayWithTime2());
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_LAST_UPDT_TM(calUtil.getTodayWithTime2());
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_URL(fvo.getFileStreCours());
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_HASH(" ");
//					mdmTnsrAsmblyBiFileVO.setBI_CN(BI_CN);
//					
///*					String fileName = fvo.getStreFileNm();
//					if( fvo.getFileExtsn() != null && !fvo.getFileExtsn().equals("") ) {
//						fileName = fileName + "." + fvo.getFileExtsn();
//					}
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_PATH(fvo.getFileStreCours() + fileName);
//*/					
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_PATH(fvo.getFileStreCours());
//					mdmTnsrAsmblyBiService.insertMdmBillFile(mdmTnsrAsmblyBiFileVO);
//				}
//			}
//		}
//		catch(Exception e) {
//			System.out.println();
//			System.out.println(e.toString());
//			System.out.println();
//		}
//		
//		return "redirect:/mdm/MdmBillList.do?mdmAdm=Bill";
//    }   

    /**
     * 의안정보 수정
     * @param mdmBillVO
     * @param model
     * @return "/Mdm/MdmBillInfo"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 수정", order = 680 ,gid = 50)
    @RequestMapping("/mdm/MdmBillUpdate.do")
    public String updateMdmBill(final MultipartHttpServletRequest multiRequest, @ModelAttribute("fileVO") MdmFileVO fileVO, 
    		@ModelAttribute("mdmTnsrAsmblyBiVO") MdmTnsrAsmblyBiVO mdmTnsrAsmblyBiVO,
    		@ModelAttribute("mdmTnsrAsmblyBiFileVO") MdmTnsrAsmblyBiFileVO mdmTnsrAsmblyBiFileVO,
    		@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,
    		BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

//		List<MdmFileVO> result = null;
//	    MdmFileVO fvo = null;
	    MdmCalUtil calUtil = new MdmCalUtil();
	    MdmStrUtil strUtil = new MdmStrUtil();
//	    String dt = "";
//	    String asembyId = "";
//	    String[] cm;

	    try {
	    	calUtil.setDecimalFormat("00");	
	    	String LAST_UPDT_DT = calUtil.getTodayWithTime2();
	    	mdmTnsrAsmblyBiVO.setLAST_UPDT_DT(LAST_UPDT_DT);
/* 
 * 파일관련은 업데이트하지 않기로 하여 주석 처리 
 * */
	    	
//			String CMIT_RESULT_STDCD = mdmTnsrAsmblyBiVO.getCMIT_RESULT();
//			if( CMIT_RESULT_STDCD != null &&  !CMIT_RESULT_STDCD.equals("") ) {
//				mdmTnsrAsmblyBiVO.setCMIT_RESULT_STDCD(mdmTnsrAsmblyBiVO.getCMIT_RESULT());
//				mdmTnsrAsmblyBiVO.setLAST_RESULT_CL_STDCD(CMIT_RESULT_STDCD);
//				mdmTnsrAsmblyBiVO.setCMIT_RESULT(mdmTnsrAsmblyCodeService.selectMdmDetailCodeRIS020ByNm(CMIT_RESULT_STDCD)); 
//			}
//			
//			String PLNMT_RESULT_STDCD = mdmTnsrAsmblyBiVO.getPLNMT_RESULT();
//			if( PLNMT_RESULT_STDCD != null && !PLNMT_RESULT_STDCD.equals("") ) {
//				mdmTnsrAsmblyBiVO.setPLNMT_RESULT_STDCD(mdmTnsrAsmblyBiVO.getPLNMT_RESULT());
//				mdmTnsrAsmblyBiVO.setLAST_RESULT_CL_STDCD(PLNMT_RESULT_STDCD);
//				mdmTnsrAsmblyBiVO.setPLNMT_RESULT(mdmTnsrAsmblyCodeService.selectMdmDetailCodeRIS020ByNm(PLNMT_RESULT_STDCD)); 
//			}

//			dt = strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getITNC_DE());
//	    	if( !dt.equals("") ) {
//	    		mdmTnsrAsmblyBiVO.setITNC_YEAR(dt.substring(0,  4));
//	    	}
//    		mdmTnsrAsmblyBiVO.setITNC_DE(dt);
    		mdmTnsrAsmblyBiVO.setFRWRD_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getFRWRD_DE()));
    		mdmTnsrAsmblyBiVO.setCMIT_REPORT_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getCMIT_REPORT_DE()));
    		mdmTnsrAsmblyBiVO.setCMIT_SBMISN_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getCMIT_SBMISN_DE()));
    		mdmTnsrAsmblyBiVO.setCMIT_PROCESS_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getCMIT_PROCESS_DE()));
    		mdmTnsrAsmblyBiVO.setPLNMT_REPORT_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getPLNMT_REPORT_DE()));
    		mdmTnsrAsmblyBiVO.setPLNMT_SBMISN_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getPLNMT_SBMISN_DE()));
    		mdmTnsrAsmblyBiVO.setPLNMT_PROCESS_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getPLNMT_PROCESS_DE()));
    		mdmTnsrAsmblyBiVO.setTRNSF_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getTRNSF_DE()));
    		mdmTnsrAsmblyBiVO.setPRMLGT_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getPRMLGT_DE()));
    		mdmTnsrAsmblyBiVO.setITNC_DE(strUtil.getRemoveStr("\\-", mdmTnsrAsmblyBiVO.getITNC_DE()));
    		
    		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    		mdmTnsrAsmblyBiVO.setLAST_UPDUSR_ID(user.getMngrId());
    		
			mdmTnsrAsmblyBiService.updateMdmBill(mdmTnsrAsmblyBiVO);
//			mdmTnsrAsmblyBiService.deleteMdmBillItncAsemby(mdmTnsrAsmblyBiVO.getBI_CN());
//
//    		if( mdmTnsrAsmblyBiVO.getPROPSR_CM() != null && !mdmTnsrAsmblyBiVO.getPROPSR_CM().trim().equals("") ) {
//    			List<MdmTnsrAsmblyItncAsembyVO> alist = new ArrayList<MdmTnsrAsmblyItncAsembyVO>();
//    			MdmTnsrAsmblyItncAsembyVO avo = null;
//    			
//    			cm = mdmTnsrAsmblyBiVO.getPROPSR_CM().trim().split("\\s+|\\,|\\n");
//    			for(int i = 0; i < cm.length; i++) {
//    				if( cm[i] == null || cm[i].trim().equals("") ) {
//    					continue;
//    				}
//    				
//    				mdmSearchVO.setSchKw(cm[i].trim().replace(" ", ""));
//    				mdmSearchVO.setSchLoAsmCode(mdmTnsrAsmblyBiVO.getRASMBLY_ID());
//    				mdmSearchVO.setSchRasmblyNumpr(Integer.toString(mdmTnsrAsmblyBiVO.getRASMBLY_NUMPR()));
//    				asembyId = mdmTnsrAsmblyCodeService.selectMdmAsembyCode(mdmSearchVO);
//    				if( asembyId == null ) {
//    					continue;
//    				}
//    				avo = new MdmTnsrAsmblyItncAsembyVO();
//    				avo.setASEMBY_ID(asembyId);
//    				avo.setRASMBLY_ID(mdmTnsrAsmblyBiVO.getRASMBLY_ID());
//    				avo.setBI_ID(mdmTnsrAsmblyBiVO.getBI_ID());
//    				avo.setFRST_REGIST_DT(LAST_UPDT_DT);
//    				avo.setBI_CN(mdmTnsrAsmblyBiVO.getBI_CN());
//    				avo.setRASMBLY_NUMPR(mdmTnsrAsmblyBiVO.getRASMBLY_NUMPR());
//    				alist.add(avo);
//    			}
//    			mdmTnsrAsmblyBiService.insertMdmBillCm(alist);
//    		}

//			final Map<String, MultipartFile> files = multiRequest.getFileMap();
//			if( !files.isEmpty() ) {
//				result = fileUtil.parseFileInf(files, "clik001", calUtil.getYear() + "/" + mdmTnsrAsmblyBiVO.getRASMBLY_ID() + "/" + calUtil.getToday() + "/file/RES403/");
//				if( result.size() > 0 ) {
//					fvo = new MdmFileVO();
//					fvo = result.get(0);
///*					
//					System.out.println("파일 확장자 : " + fvo.getFileExtsn());
//					System.out.println("파일 절대경로 : " + fvo.getFileStreCours());
//					System.out.println("파일 파일 크기 : " + fvo.getFileMg());
//					System.out.println("파일 오리지널 파일명 : " + fvo.getOrignlFileNm());
//					System.out.println("파일 저장된 파일명 : " + fvo.getStreFileNm());
//					System.out.println(fvo.getAtchFileId());
//					System.out.println(fvo.getFileSn());
//*/					
//					mdmTnsrAsmblyBiFileVO.setBI_ID(mdmTnsrAsmblyBiVO.getBI_ID());
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_ID(fvo.getStreFileNm());
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_NM(fvo.getOrignlFileNm());
//					mdmTnsrAsmblyBiFileVO.setFRST_REGIST_DT(calUtil.getTodayWithTime2());
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_LAST_UPDT_TM(calUtil.getTodayWithTime2());
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_URL(fvo.getFileStreCours());
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_HASH(" ");
//					mdmTnsrAsmblyBiFileVO.setBI_CN(mdmTnsrAsmblyBiVO.getBI_CN());
//					
///*
//					String fileName = fvo.getStreFileNm();
//					if( fvo.getFileExtsn() != null && !fvo.getFileExtsn().equals("") ) {
//						fileName = fileName + "." + fvo.getFileExtsn();
//					}
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_PATH(fvo.getFileStreCours() + fileName);
//*/					
//					mdmTnsrAsmblyBiFileVO.setBI_FILE_PATH(fvo.getFileStreCours());
//					mdmTnsrAsmblyBiService.insertMdmBillFile(mdmTnsrAsmblyBiFileVO);
//				}
//			}
			
			model.addAttribute("message", "정상처리되었습니다.");
			model.addAttribute("return_url", "/mdm/MdmBillView1.do?cnId="+mdmTnsrAsmblyBiVO.getBI_CN());
		}
		catch(Exception e) {
			System.out.println();
			System.out.println(e.getMessage());
			System.out.println();
			
			model.addAttribute("return_url", "/mdm/MdmBillView1.do?cnId="+mdmTnsrAsmblyBiVO.getBI_CN());
			model.addAttribute("message", "처리중오류가발생했습니다.");
			
		}
		
		model.addAttribute("menuName", "지방의회 의안정보");
		model.addAttribute("cnId", mdmTnsrAsmblyBiVO.getBI_CN());
		model.addAttribute("EndPage", multiRequest.getParameter("EndPage"));
		model.addAttribute("cnList", multiRequest.getParameter("cnList"));
		model.addAttribute("sort", multiRequest.getParameter("sort"));
		model.addAttribute("work", "수정");
		model.addAttribute("work", "수정");
		
		//return "redirect:/mdm/MdmBillList.do?mdmAdm=Bill";
        return "clikMng/mdm/MdmResult";
    }   

    /**
     * 의안 게시 수정
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 게시 수정", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillIsView.do")
    public String MdmBillIsView(@ModelAttribute("mdmTnsrAsmblyBiVO") MdmTnsrAsmblyBiVO mdmTnsrAsmblyBiVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	MdmIsViewVO isViewVO = null;
    	
    	isView = mdmTnsrAsmblyBiVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		isViewVO = new MdmIsViewVO();
    		arr = isView[i].split("=");
    		isViewVO.setUid(arr[0]);
    		isViewVO.setIsview(arr[1]);
    		mdmTnsrAsmblyBiService.updateMdmBillIsView(isViewVO);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 의안정보 삭제
     * @param mdmTnsrAsmblyAsembyVO
     * @param model
     * @return	"/mdm/MdmIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillDeleteChk.do")
    public String MdmBillDeleteChk(@ModelAttribute("mdmTnsrAsmblyBiVO") MdmTnsrAsmblyBiVO mdmTnsrAsmblyBiVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	MdmIsViewVO isViewVO = null;
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	isView = mdmTnsrAsmblyBiVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		isViewVO = new MdmIsViewVO();
    		arr = isView[i].split("=");
    		isViewVO.setUid(arr[0]);
    		isViewVO.setIsview(arr[1]);
    		isViewVO.setLAST_UPDUSR_ID(user.getMngrId());
    		mdmTnsrAsmblyBiService.deleteMdmBillChk(isViewVO);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 의안정보 - 삭제
     * @param mdmTnsrAsmblyAsembyVO
     * @param model
     * @return	"/mdm/MdmIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillDelete.do")
    public String MdmBillDelete(@ModelAttribute("mdmTnsrAsmblyBiVO") MdmTnsrAsmblyBiVO mdmTnsrAsmblyBiVO, ModelMap model) throws Exception {
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		mdmTnsrAsmblyBiVO.setLAST_UPDUSR_ID(user.getMngrId());
    	
    	mdmTnsrAsmblyBiService.deleteMdmBill(mdmTnsrAsmblyBiVO);
   		
		model.addAttribute("menuName", "지방의회 의안정보");
		model.addAttribute("cnId", mdmTnsrAsmblyBiVO.getBI_CN());
		model.addAttribute("work", "삭제");
		
		model.addAttribute("message", "정상처리되었습니다.");
		model.addAttribute("return_url", "/mdm/MdmBillView1.do?cnId="+mdmTnsrAsmblyBiVO.getBI_CN());
		
		//return "redirect:/mdm/MdmBillList.do?mdmAdm=Bill";
        return "clikMng/mdm/MdmResult";
    }

    /**
     * 의안정보 - 리스트 삭제
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 리스트 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillDplctListDelete.do")
    public String MdmBillDplctListDelete(@ModelAttribute("mdmTnsrAsmblyBiVO") MdmTnsrAsmblyBiVO mdmTnsrAsmblyBiVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	isView = mdmTnsrAsmblyBiVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		arr = isView[i].split("=");
    		mdmTnsrAsmblyBiVO.setBI_CN(arr[0]);
    		mdmTnsrAsmblyBiService.deleteMdmBill(mdmTnsrAsmblyBiVO);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 의안정보 - 첨부파일 삭제
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmBillFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 첨부파일 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillFileDelete.do")
    public String MdmBillFileDelete(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String msg = "{\"rst\":\"no\"}";

    	if( request.getParameter("DOWNID") != null ) {
    		mdmTnsrAsmblyBiService.deleteMdmBillFile(request.getParameter("DOWNID"));
    		msg = "{\"rst\":\"yes\"}";
    	}
    	
		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 의안정보 - 파일 리스트 삭제
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 파일 리스트 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillFileListDelete.do")
    public String MdmBillFileListDelete(@ModelAttribute("mdmTnsrAsmblyBiVO") MdmTnsrAsmblyBiVO mdmTnsrAsmblyBiVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	isView = mdmTnsrAsmblyBiVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		arr = isView[i].split("=");
    		mdmTnsrAsmblyBiService.deleteMdmBillFile(arr[0]);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 의안정보 - 파일 재변환
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 파일 재변환", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmBillFileListReCnvrs.do")
    public String MdmBillFileListReCnvrs(@ModelAttribute("mdmTnsrAsmblyBiVO") MdmTnsrAsmblyBiVO mdmTnsrAsmblyBiVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	isView = mdmTnsrAsmblyBiVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		arr = isView[i].split("=");
    		mdmTnsrAsmblyBiService.updateMdmBillFileListReCnvrs(arr[0]);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 의안정보 - 최근 수집일
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    public String MdmBillRegDate() throws Exception {
    	MdmStrUtil strUtil = new MdmStrUtil();
    	MdmCalUtil calUtil = new MdmCalUtil();
		calUtil.setDecimalFormat("00");

		String dt2 = mdmTnsrAsmblyBiService.selectMdmBillMaxRegDate(calUtil.getToday());
		dt2 = strUtil.getStringFormat8("-", dt2);

		return dt2;
    }

    /**
     * 의안정보 - 최근 수집일
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    public String MdmBillRegDate(String dt2) throws Exception {
    	MdmCalUtil calUtil = new MdmCalUtil();
    	calUtil.setDelimiter("-");
		calUtil.setDecimalFormat("00");
		String[] arr = dt2.split("-");

		return calUtil.getDateBeforeDt(calUtil.getUnixTime(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2])), -7);
    }
    
    /**
     * 의안 - 소관위원회 정보를 조회한다.
     * @param RASMBLY_ID, RASMBLY_NUMPR
     * @return	"clikMng/mdm/MdmIsView"
     * @throws Exception
     */
    @IncludedInfo(name="소관위원회 정보를 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmJrsdCmitIdList.do")
    public String MdmJrsdCmitIdList(HttpServletRequest request, ModelMap model) throws Exception {
    	MdmTnsrAsmblyMtgNmVO mdmTnsrAsmblyMtgnmVO = new MdmTnsrAsmblyMtgNmVO();
    	mdmTnsrAsmblyMtgnmVO.setRASMBLY_ID(request.getParameter("rasmbly_id").toString());
    	mdmTnsrAsmblyMtgnmVO.setRASMBLY_NUMPR(Integer.parseInt(request.getParameter("rasmbly_numpr")));
    	List<MdmTnsrAsmblyMtgNmVO> list = mdmTnsrAsmblyCodeService.selectMdmJrsdCmitIdList(mdmTnsrAsmblyMtgnmVO); // 소관위원회
    	
    	JSONObject jObj = new JSONObject();
    	JSONArray jArr = new JSONArray();
    	MdmTnsrAsmblyMtgNmVO vo = null;
    	for(int i = 0; i < list.size(); i++)
    	{
    		jObj = new JSONObject();
    		vo = list.get(i);
    		jObj.put("MTGNM_ID",vo.getMTGNM_ID());
    		jObj.put("MTGNM",vo.getMTGNM());
			jArr.put(jObj);
    	}
    	
    	model.put("msg", jArr.toString());
    	
    	return "clikMng/mdm/MdmIsView";
    }
    
    /**
     * 지방의회의안 엑셀파일로 다운로드 한다.
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/selectMdmBillExcel.do")
    public ModelAndView selectMdmBillExcel(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
    											, @SuppressWarnings("rawtypes") Map commandMap
    											, ModelMap model) throws Exception {
    	
    	if( !mdmSearchVO.getSchBrtcCode().equals("") &&  mdmSearchVO.getSchLoAsmCode().equals("") ) {
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
    	
    	if( mdmSearchVO.getSchKw() != null && !mdmSearchVO.getSchKw().trim().equals("") ) {
    		if( mdmSearchVO.getSchKey().equals("") || mdmSearchVO.getSchKey().equals("schBiSj") ) {
    			mdmSearchVO.setSchBiSj(mdmSearchVO.getSchKw());    // 의안명
    		}
    		else if( mdmSearchVO.getSchKey().equals("schPropsr") ) {
    			mdmSearchVO.setSchPropsr(mdmSearchVO.getSchKw());  // 제안자
    		}
    		else {
    	    	List<String> schJrsdCmitIdList = new ArrayList<String>();
    	    	schJrsdCmitIdList = mdmTnsrAsmblyCodeService.selectMdmMtgIdList(mdmSearchVO); // 소관위원회
    			mdmSearchVO.setSchJrsdCmitIdList(schJrsdCmitIdList);
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
    	
    	int billListTotCnt = mdmTnsrAsmblyBiService.selectMdmBillListTotCnt(mdmSearchVO);

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
			billListTotCnt = billListTotCnt > maxExcelSearchCount ? maxExcelSearchCount : billListTotCnt;
		}
    	
    	mdmSearchVO.setLastRecord(billListTotCnt);

    	List<MdmTnsrAsmblyBiVO> billList = mdmTnsrAsmblyBiService.selectMdmBillList(mdmSearchVO);
    	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", mdmSearchVO);
        map.put("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? "" : (String) commandMap.get("selSearchGubun"));
        
    	map.put("resultList", billList);
    	map.put("resultCnt", Integer.toString(billListTotCnt));

        return new ModelAndView("MdmBillExcel", map);
		
    }
    
    /**
     * 지방의회의안 텍스트파일로 다운로드 한다.
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/selectMdmBillText.do")
    public ModelAndView selectMdmBillText(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
    											, @SuppressWarnings("rawtypes") Map commandMap
    											, ModelMap model) throws Exception {
    	
    	if( !mdmSearchVO.getSchBrtcCode().equals("") &&  mdmSearchVO.getSchLoAsmCode().equals("") ) {
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
    	
    	if( mdmSearchVO.getSchKw() != null && !mdmSearchVO.getSchKw().trim().equals("") ) {
    		if( mdmSearchVO.getSchKey().equals("") || mdmSearchVO.getSchKey().equals("schBiSj") ) {
    			mdmSearchVO.setSchBiSj(mdmSearchVO.getSchKw());    // 의안명
    		}
    		else if( mdmSearchVO.getSchKey().equals("schPropsr") ) {
    			mdmSearchVO.setSchPropsr(mdmSearchVO.getSchKw());  // 제안자
    		}
    		else {
    	    	List<String> schJrsdCmitIdList = new ArrayList<String>();
    	    	schJrsdCmitIdList = mdmTnsrAsmblyCodeService.selectMdmMtgIdList(mdmSearchVO); // 소관위원회
    			mdmSearchVO.setSchJrsdCmitIdList(schJrsdCmitIdList);
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
    	
    	int billListTotCnt = mdmTnsrAsmblyBiService.selectMdmBillListTotCnt(mdmSearchVO);

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
			billListTotCnt = billListTotCnt > maxExcelSearchCount ? maxExcelSearchCount : billListTotCnt;
		}
    	
    	mdmSearchVO.setLastRecord(billListTotCnt);

    	List<MdmTnsrAsmblyBiVO> billList = mdmTnsrAsmblyBiService.selectMdmBillList(mdmSearchVO);
    	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", mdmSearchVO);
        map.put("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? "" : (String) commandMap.get("selSearchGubun"));
        
    	map.put("resultList", billList);
    	map.put("resultCnt", Integer.toString(billListTotCnt));

        return new ModelAndView("MdmBillText", map);
		
    }
}
