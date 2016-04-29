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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.ComDefaultCodeVO;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.CmmnDetailCode;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.mdm.model.MdmDetailCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmFileVO;
import clikmng.nanet.go.kr.mdm.model.MdmIsViewVO;
import clikmng.nanet.go.kr.mdm.model.MdmOrgCodeVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSeedVO;
import clikmng.nanet.go.kr.mdm.model.MdmOutSiteVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyMintsVO;
import clikmng.nanet.go.kr.mdm.service.MdmFileMngService;
import clikmng.nanet.go.kr.mdm.service.MdmFileMngUtil;
import clikmng.nanet.go.kr.mdm.service.MdmProperties;
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
public class MdmTnsrAsmblyMintsController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "MdmTnsrAsmblyMintsService")
    private MdmTnsrAsmblyMintsService mdmTnsrAsmblyMintsService;

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

    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

    @Resource(name = "MdmFileMngService")
    private MdmFileMngService fileMngService;

    @Resource(name = "MdmFileMngUtil")
    private MdmFileMngUtil fileUtil;

    /**
     * 메타데이터 목록 조회
     * @param mdmTnsrasmblyMintsVO
     * @param model
     * @return	"/Mdm/MdmList"
     * @throws Exception
     */
    /*@IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)*/
    @RequestMapping(value="/mdm/MdmMinutesList.do")
    public String MdmMinutesList(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
    	
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
    	
    	//기본정렬 설정
    	if(mdmSearchVO.getSort() == null || mdmSearchVO.getSort().equals("")){
    		mdmSearchVO.setSort("MTG_DE DESC");
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
       	List<MdmDetailCodeVO> codeRKS022List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS022();     // 수집유형
       	List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS025();     // 수집(지역현안소식)

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
    	int minutesListTotCnt = 0;
		minutesListTotCnt = mdmTnsrAsmblyMintsService.selectMdmMinutesListTotCnt(mdmSearchVO);
    		
    	MdmPaging paging = new MdmPaging();
    	paging.setPagingCalc(minutesListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
    	paging.setParam(mdmSearchVO);
    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVO.setLastRecord(paging.getLastRecord());

    	
    	List<MdmTnsrAsmblyMintsVO> minutesList = null;

    	minutesList = mdmTnsrAsmblyMintsService.selectMdmMinutesList(mdmSearchVO);
    	
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
		model.addAttribute("codeRKS022List", codeRKS022List);
		model.addAttribute("codeRKS025List", codeRKS025List);
		model.addAttribute("minutesListTotCnt", minutesListTotCnt);
    	model.addAttribute("minutesList", minutesList);
		model.addAttribute("paginationInfo", paging.getPaging(mdmSearchVO));
		model.addAttribute("listStartNo", paging.getFirstRecord());
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		
		String currentSearchCnList = "";
		for(MdmTnsrAsmblyMintsVO vo : minutesList){
			currentSearchCnList += vo.getMINTS_CN() + ",";
		}
		
		if(!"".equals(currentSearchCnList))
			currentSearchCnList = currentSearchCnList.substring(0, currentSearchCnList.length()-1);
		
		model.addAttribute("cnList", currentSearchCnList.replace("'", ""));
		model.addAttribute("EndPage", paging.getTotalPages());
		
        return "clikMng/mdm/MdmMinutesList";
    }
    
    /**
     * 메타데이터 목록 조회 (엑셀 조회)
     * @param mdmTnsrasmblyMintsVO
     * @param model
     * @return	"/Mdm/MdmList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/ExcelMdmMinutesList.do")
    public String ExcelMdmMinutesList(final MultipartHttpServletRequest multiRequest, 
														@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, 
														ModelMap model) throws Exception {
    	
    	//기본정렬 설정
    	if(mdmSearchVO.getSort() == null || mdmSearchVO.getSort().equals("")){
    		mdmSearchVO.setSort("MTG_DE DESC");
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
       	List<MdmDetailCodeVO> codeRKS022List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS022();     // 수집유형
       	List<MdmDetailCodeVO> codeRKS025List = mdmTnsrAsmblyCodeService.selectMdmDetailCodeRKS025();     // 수집(지역현안소식)

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
    	List<MdmTnsrAsmblyMintsVO> minutesList = mdmTnsrAsmblyMintsService.selectMdmMinutesList(mdmSearchVOTemp);
    	String currentSearchCnList = "";
		for(MdmTnsrAsmblyMintsVO vo : minutesList){
			currentSearchCnList += "'" + vo.getMINTS_CN() + "',";
		}
		
		if(!"".equals(currentSearchCnList)){
			currentSearchCnList = currentSearchCnList.substring(0, currentSearchCnList.length()-1);
			outbbsList = currentSearchCnList;
		}
		
    	// 총 검색 건수
    	int minutesListTotCnt = mdmTnsrAsmblyMintsService.selectMdmMinutesListTotCnt(mdmSearchVOTemp);

    	MdmPaging paging = new MdmPaging();
    	paging.setPagingCalc(minutesListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
    	paging.setParam(mdmSearchVO);
    	
    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVO.setLastRecord(paging.getLastRecord());
    	mdmSearchVOTemp.setFirstRecord(paging.getFirstRecord());
    	mdmSearchVOTemp.setLastRecord(paging.getLastRecord());
    	
    	// 검색 리스트
    	minutesList = mdmTnsrAsmblyMintsService.selectMdmMinutesList(mdmSearchVOTemp);
    		
    	model.addAttribute("codeOrgCodeStep1List", codeOrgCodeStep1List);
		model.addAttribute("codeOrgCodeStep2List", codeOrgCodeStep2List);
		model.addAttribute("codeOrgCodeStep3List", codeOrgCodeStep3List);
		model.addAttribute("codeOrgSiteList", codeOrgSiteList);
		model.addAttribute("siteList", siteList);
		model.addAttribute("seedList", seedList);
    	model.addAttribute("codeRKS021AllList", codeRKS021AllList);
		model.addAttribute("codeRKS022List", codeRKS022List);
		model.addAttribute("codeRKS025List", codeRKS025List);
		model.addAttribute("minutesListTotCnt", minutesListTotCnt);
   		model.addAttribute("minutesList", minutesList);
		model.addAttribute("paginationInfo", paging.getPaging(mdmSearchVO));
		model.addAttribute("listStartNo", paging.getFirstRecord());
		model.addAttribute("mdmSearchVO", mdmSearchVO);
		
		currentSearchCnList = "";
		for(MdmTnsrAsmblyMintsVO vo : minutesList){
			currentSearchCnList += vo.getMINTS_CN() + ",";
		}
		
		if(!"".equals(currentSearchCnList))
			currentSearchCnList = currentSearchCnList.substring(0, currentSearchCnList.length()-1);
		
		if(!"".equals(outbbsList))
			currentSearchCnList = outbbsList;
		
		model.addAttribute("isExcelSearch", "Y");
		model.addAttribute("excelSearchCnList", outbbsList.replace("'", ""));
		model.addAttribute("excelSearchCollection", "Minutes");
		
		model.addAttribute("cnList", currentSearchCnList.replace("'", ""));
		model.addAttribute("EndPage", paging.getTotalPages());
		
        return "clikMng/mdm/MdmMinutesList";
    }

    /**
     * 회의록 내용 보기1
     * @param mdmTnsrasmblyMintsVO
     * @param model
     * @return	"/Mdm/MdmList"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 내용 보기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesMetaDataView1.do")
    public String MdmMinutesMetaDataView1(
    				@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
    				, ModelMap model
    				, HttpServletRequest request) throws Exception {
    	
    	//상세화면 이전다음 관련 조회 S
    	String cnList = "";
    	if("Y".equals(mdmSearchVO.getIsPrevNextPaging()))
    	{
    		//이전 다음 CN 정보 조회 
	    	int minutesListTotCnt = mdmTnsrAsmblyMintsService.selectMdmMinutesListTotCnt(mdmSearchVO);
	    	MdmPaging paging = new MdmPaging();
	    	paging.setPagingCalc(minutesListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
	    	paging.setParam(mdmSearchVO);
	    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
	    	mdmSearchVO.setLastRecord(paging.getLastRecord());
	    	
	    	List<MdmTnsrAsmblyMintsVO> minutesList = mdmTnsrAsmblyMintsService.selectMdmMinutesList(mdmSearchVO);
	    	for(MdmTnsrAsmblyMintsVO vo : minutesList){
	    		cnList += vo.getMINTS_CN() + ",";
	    	}
	    	
	    	cnList = cnList.substring(0, cnList.length()-1);
	    	
	    	//이전다음 페이징된 경우 해당하는 데이터 CN 설정
	    	if( "".equals(mdmSearchVO.getCnId())  && "next".equals(mdmSearchVO.getPrevNextGubun())){
    			mdmSearchVO.setCnId(minutesList.get(0).getMINTS_CN());
    		}else if( "".equals(mdmSearchVO.getCnId()) && "prev".equals(mdmSearchVO.getPrevNextGubun())){
    			mdmSearchVO.setCnId(minutesList.get(minutesList.size()-1).getMINTS_CN());
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
    	HashMap<String,Object> paramMap = new HashMap<String,Object>();
    	
    	MdmTnsrAsmblyMintsVO minutesVO = mdmTnsrAsmblyMintsService.selectMdmMinutesView(mdmSearchVO.getCnId());
    	minutesVO.setMTG_DE(strUtil.getStringFormat8("-", minutesVO.getMTG_DE()));
    	
    	List<MdmFileVO> minutesFileList = null;
    	minutesFileList = mdmTnsrAsmblyMintsService.selectMdmMinutesFileListCmmn(mdmSearchVO.getCnId());
    	
    	//의회별 대수정보
    	List<HashMap<String,Object>> rasmblyNumperList = mdmTnsrAsmblyMintsService.selectRasmblyNumperList(minutesVO.getRASMBLY_ID());
    	model.addAttribute("rasmblyNumperList", rasmblyNumperList);
    	
    	//공개
    	ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
    	comDefaultCodeVO.setCodeId("RIS013");
    	List<CmmnDetailCode> othbcStdcdList = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
    	model.addAttribute("othbcStdcdList", othbcStdcdList);
    	
    	//회기구분
    	comDefaultCodeVO.setCodeId("RIS009");
    	List<CmmnDetailCode> rasmblySeanList = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
    	model.addAttribute("rasmblySeanList", rasmblySeanList);
    	
    	//회기구분코드
    	paramMap.put("rasmbly_id",minutesVO.getRASMBLY_ID());
    	paramMap.put("rasmbly_numpr",minutesVO.getRASMBLY_NUMPR());
    	List<HashMap<String,Object>> sesnList = mdmTnsrAsmblyMintsService.selectRasmblySesnList(paramMap);
    	model.put("sesnList",sesnList);
    	
    	//회의명
    	List<HashMap<String,Object>> mtgnmList = mdmTnsrAsmblyMintsService.selectMtgnmList(paramMap);
    	model.put("mtgnmList",mtgnmList);
    	
    	String df = "";
    	if( minutesFileList.size() > 0 ) {
    		MdmFileVO vo = new MdmFileVO();
   			vo = minutesFileList.get(0);
   			if( mdmSearchVO.getDisfile().equals("") &&  vo.getFileSn() != null && (vo.getFileSn().equals("1") || vo.getFileSn().equals("3")) ) {
   				int flg = 0;
   				df = vo.getOrignlFileNm();
   				MdmFtp mdmFtp = new MdmFtp();
   				flg = mdmFtp.ftp(vo.getStreFileNm(), df);
   				if( flg == 1 ) {
   				}
   			}
    	}
    	
    	model.addAttribute("disfile", df);
		model.addAttribute("minutesVO", minutesVO);
		model.addAttribute("minutesFileList", minutesFileList);
		model.addAttribute("mdmSearchVO", mdmSearchVO);

	    return "clikMng/mdm/MdmMinutesView2";
    }

    /**
     * 회의록 메타데이터 보기2
     * @param mdmTnsrasmblyMintsVO
     * @param model
     * @return	"/Mdm/MdmList"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 보기", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesMetaDataView2.do")
    public String MdmMinutesMetaDataView2(HttpServletRequest request, @ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO, ModelMap model) throws Exception {
    	//상세화면 이전다음 관련 조회 S
    	String cnList = "";
    	if("Y".equals(mdmSearchVO.getIsPrevNextPaging()))
    	{
    		//이전 다음 CN 정보 조회 
	    	int minutesListTotCnt = mdmTnsrAsmblyMintsService.selectMdmMinutesListTotCnt(mdmSearchVO);
	    	MdmPaging paging = new MdmPaging();
	    	paging.setPagingCalc(minutesListTotCnt, mdmSearchVO.getPageNum(), mdmSearchVO.getListCnt());
	    	paging.setParam(mdmSearchVO);
	    	mdmSearchVO.setFirstRecord(paging.getFirstRecord());
	    	mdmSearchVO.setLastRecord(paging.getLastRecord());
	    	
	    	List<MdmTnsrAsmblyMintsVO> minutesList = mdmTnsrAsmblyMintsService.selectMdmMinutesList(mdmSearchVO);
	    	for(MdmTnsrAsmblyMintsVO vo : minutesList){
	    		cnList += vo.getMINTS_CN() + ",";
	    	}
	    	
	    	cnList = cnList.substring(0, cnList.length()-1);
	    	
	    	//이전다음 페이징된 경우 해당하는 데이터 CN 설정
	    	if( "".equals(mdmSearchVO.getCnId())  && "next".equals(mdmSearchVO.getPrevNextGubun())){
    			mdmSearchVO.setCnId(minutesList.get(0).getMINTS_CN());
    		}else if( "".equals(mdmSearchVO.getCnId()) && "prev".equals(mdmSearchVO.getPrevNextGubun())){
    			mdmSearchVO.setCnId(minutesList.get(minutesList.size()-1).getMINTS_CN());
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
    	HashMap<String,Object> paramMap = new HashMap<String,Object>();
    	
    	MdmTnsrAsmblyMintsVO minutesVO = mdmTnsrAsmblyMintsService.selectMdmMinutesView(mdmSearchVO.getCnId());
    	minutesVO.setMTG_DE(strUtil.getStringFormat8("-", minutesVO.getMTG_DE()));
    	
    	List<MdmFileVO> minutesFileList = null;
    	minutesFileList = mdmTnsrAsmblyMintsService.selectMdmMinutesFileListCmmn(mdmSearchVO.getCnId());
    	
    	//의회별 대수정보
    	List<HashMap<String,Object>> rasmblyNumperList = mdmTnsrAsmblyMintsService.selectRasmblyNumperList(minutesVO.getRASMBLY_ID());
    	model.addAttribute("rasmblyNumperList", rasmblyNumperList);
    	
    	//공개
    	ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
    	comDefaultCodeVO.setCodeId("RIS013");
    	List<CmmnDetailCode> othbcStdcdList = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
    	model.addAttribute("othbcStdcdList", othbcStdcdList);
    	
    	//회기구분
    	comDefaultCodeVO.setCodeId("RIS009");
    	List<CmmnDetailCode> rasmblySeanList = cmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
    	model.addAttribute("rasmblySeanList", rasmblySeanList);
    	
    	//회기구분코드
    	paramMap.put("rasmbly_id",minutesVO.getRASMBLY_ID());
    	paramMap.put("rasmbly_numpr",minutesVO.getRASMBLY_NUMPR());
    	List<HashMap<String,Object>> sesnList = mdmTnsrAsmblyMintsService.selectRasmblySesnList(paramMap);
    	model.put("sesnList",sesnList);
    	
    	//회의명
    	List<HashMap<String,Object>> mtgnmList = mdmTnsrAsmblyMintsService.selectMtgnmList(paramMap);
    	model.put("mtgnmList",mtgnmList);
    	
    	String df = "";
    	if( minutesFileList.size() > 0 ) {
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
		model.addAttribute("minutesVO", minutesVO);
		model.addAttribute("minutesFileList", minutesFileList);
		model.addAttribute("mdmSearchVO", mdmSearchVO);

	    return "clikMng/mdm/MdmMinutesView2";
    }

    /**
     * 회의록 - 첨부파일 목록
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmBillFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 첨부파일 목록", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesFileListCmmn.do")
    public String MdmMinutesFileListCmmn(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	List<MdmFileVO> fileList = null;
    	if( request.getParameter("MINTS_CN") != null ) {
    		fileList = mdmTnsrAsmblyMintsService.selectMdmMinutesFileListCmmn(request.getParameter("MINTS_CN"));
    	}
		model.addAttribute("fileList", fileList);
		return "clikMng/mdm/MdmMinutesFillListCmmn";
    }

    /**
     * 회의록 뷰어
     * @param mdmTnsrasmblyMintsVO
     * @param model
     * @return	"/Mdm/MdmList"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 뷰어", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesViewer.do")
    public String MdmMinutesViewer(@ModelAttribute("mdmTnsrAsmblyMintsVO") MdmTnsrAsmblyMintsVO mdmTnsrAsmblyMintsVO, ModelMap model) throws Exception {
    	MdmTnsrAsmblyMintsVO vo = mdmTnsrAsmblyMintsService.selectMdmMinutesHtml(mdmTnsrAsmblyMintsVO);
		model.addAttribute("content", vo.getMINTS_HTML());
    	
	    return "clikMng/mdm/MdmMinutesViewer";
    }

    /**
     * 회의명 가져오기
     * @param mdmTnsrasmblyMintsVO
     * @param model
     * @return	"/Mdm/MdmList"
     * @throws Exception
     */
//    @IncludedInfo(name="메타데이터 회의명", order = 680 ,gid = 50)
//    @RequestMapping(value="/mdm/MdmMinutesMtgNmList.do")
//    public String MdmMinutesMtgNmList(@ModelAttribute("mdmTnsrAsmblyMtgnmVO") MdmTnsrAsmblyMtgNmVO mdmTnsrAsmblyMtgnmVO, ModelMap model) throws Exception {
//       	List<MdmTnsrAsmblyMtgNmVO> mtgNmList = mdmTnsrAsmblyMintsService.selectMdmMinutesMtgNmList(mdmTnsrAsmblyMtgnmVO);     // 수집유형
//
//		model.addAttribute("mtgNmList", mtgNmList);
//	    return "clikMng/mdm/MdmMinutesMtgNmList";
//    }

    /**
     * 회의록 게시 수정
     * @param mdmTnsrasmblyMintsVO
     * @param model
     * @return	"/mdm/MdmMinutesIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 게시 수정", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesIsView.do")
    public String MdmMinutesIsView(@ModelAttribute("mdmTnsrAsmblyMintsVO") MdmTnsrAsmblyMintsVO mdmTnsrAsmblyMintsVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	MdmIsViewVO isViewVO = null;
    	
    	isView = mdmTnsrAsmblyMintsVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		isViewVO = new MdmIsViewVO();
    		arr = isView[i].split("=");
    		isViewVO.setUid(arr[0]);
    		isViewVO.setIsview(arr[1]);
    		mdmTnsrAsmblyMintsService.updateMdmMinutesIsView(isViewVO);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";

    }
    
    /**
     * 회의록 게시 삭제
     * @param mdmPolicyInfoVO
     * @param model
     * @return	"/mdm/MdmPolicyInfoDeleteChk"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 회의록 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesDeleteChk.do")
    public String MdmMinutesDeleteChk(@ModelAttribute("mdmTnsrAsmblyMintsVO") MdmTnsrAsmblyMintsVO mdmTnsrAsmblyMintsVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	MdmIsViewVO isViewVO = null;
    	
    	isView = mdmTnsrAsmblyMintsVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		isViewVO = new MdmIsViewVO();
    		arr = isView[i].split("=");
    		isViewVO.setUid(arr[0]);
    		isViewVO.setIsview(arr[1]);
    		mdmTnsrAsmblyMintsService.deleteMdmMinutesChk(isViewVO);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 회의록 등록폼
     * @param mdmTnsrasmblyMintsVO
     * @param model
     * @return	"/Mdm/MdmList"
     * @throws Exception
     */
    //2015.04.15 미사용으로 주석처리
//    @IncludedInfo(name="메타데이터 목록", order = 680 ,gid = 50)
//    @RequestMapping(value="/mdm/MdmMinutesForm.do")
//    public String MdmMinutesForm(@ModelAttribute("mdmTnsrAsmblyMintsVO") MdmTnsrAsmblyMintsVO mdmTnsrAsmblyMintsVO, ModelMap model) throws Exception {
//	    return "clikMng/mdm/MdmMinutesForm";
//    }

    /**
     * 회의록 등록
     * @param mdmMinutesVO
     * @param model
     * @return "/Mdm/MdmMinutes"
     * @throws Exception
     */
  //2015.04.15 미사용으로 주석처리
//    @IncludedInfo(name="메타데이터 등록", order = 680 ,gid = 50)
//    @RequestMapping("/mdm/MdmMinutesRegist.do")
//    public String insertMdmMinutes(final MultipartHttpServletRequest multiRequest, @ModelAttribute("fileVO") MdmFileVO fileVO, 
//    		@ModelAttribute("mdmTnsrAsmblyMintsVO") MdmTnsrAsmblyMintsVO mdmTnsrAsmblyMintsVO,
//    		@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,	ModelMap model) throws Exception {
//
//		List<MdmFileVO> result = null;
//	    MdmFileVO fvo = null;
//	    MdmCalUtil calUtil = new MdmCalUtil();
//	    MdmStrUtil strUtil = new MdmStrUtil();
//
//	    int uid = 0;
//	    String MINTS_CN = "";
//	    
//	    try {
//	    	calUtil.setDecimalFormat("00");
//	    	uid = mdmTnsrAsmblyMintsService.selectMdmMinutesSeq();
//	    	MINTS_CN = "CLIKM" + calUtil.getYear() + strUtil.getDecimalFormat("0000000000", uid);
//	    	mdmTnsrAsmblyMintsVO.setMINTS_CN(MINTS_CN);
//	    	mdmTnsrAsmblyMintsVO.setMTG_DE(mdmTnsrAsmblyMintsVO.getMTG_DE().replace("-", "")); // + "000000";
//	    	mdmTnsrAsmblyMintsVO.setFRST_REGIST_DT(calUtil.getTodayWithTime2());
//
//	    	if( mdmTnsrAsmblyMintsVO.getCLOSE_AT() == null || mdmTnsrAsmblyMintsVO.getCLOSE_AT().equals("") ) {
//	    		mdmTnsrAsmblyMintsVO.setCLOSE_AT("N");
//    		}
//	    	
//    		String MTGNM_KND_STDCD = "";
//    		if( mdmTnsrAsmblyMintsVO.getMTGNM().trim().equals("본회의") ) {
//    			MTGNM_KND_STDCD = "MNK001";
//    		}
//    		else if( mdmTnsrAsmblyMintsVO.getMTGNM().indexOf("행정사무감사") != -1 ||  mdmTnsrAsmblyMintsVO.getMTGNM().indexOf("조사특별위원회") != -1) {
//    			MTGNM_KND_STDCD = "MNK004";
//    		}
//    		else if( mdmTnsrAsmblyMintsVO.getMTGNM().equals("특별위원회") ) {
//    			MTGNM_KND_STDCD = "MNK003";
//    		}
//    		else {
//    			MTGNM_KND_STDCD = "MNK002";
//    		}
//
//	    	if( mdmTnsrAsmblyMintsService.selectMdmAsmblyNumPr(mdmTnsrAsmblyMintsVO) < 1 ) {
//	    		MdmTnsrAsmblyNumPrVO npVO = new MdmTnsrAsmblyNumPrVO();
//	    		npVO.setRASMBLY_NUMPR(mdmTnsrAsmblyMintsVO.getRASMBLY_NUMPR());
//	    		npVO.setBEGIN_SESN(mdmTnsrAsmblyMintsVO.getRASMBLY_SESN());
//	    		npVO.setEND_SESN(mdmTnsrAsmblyMintsVO.getRASMBLY_SESN());
//	    		npVO.setBEGIN_DE(mdmTnsrAsmblyMintsVO.getMTG_DE());
//	    		npVO.setEND_DE(mdmTnsrAsmblyMintsVO.getMTG_DE());
//	    		npVO.setRASMBLY_ID(mdmTnsrAsmblyMintsVO.getRASMBLY_ID());
//	    		npVO.setHT_SE_STDCD("HTS"+strUtil.getDecimalFormat("000", mdmTnsrAsmblyMintsVO.getRASMBLY_NUMPR()));
//	    		npVO.setFRST_REGIST_DT(calUtil.getTodayWithTime2());
//	    	}
//	    	
//	    	if( mdmTnsrAsmblyMintsService.selectMdmAsmblySesn(mdmTnsrAsmblyMintsVO) < 1 ) {
//	    		MdmTnsrAsmblySesnVO snVO = new MdmTnsrAsmblySesnVO();
//	    		
//	    		snVO.setRASMBLY_ID(mdmTnsrAsmblyMintsVO.getRASMBLY_ID());
//	    		snVO.setRASMBLY_SESN(mdmTnsrAsmblyMintsVO.getRASMBLY_SESN());
//	    		snVO.setBEGIN_DE(mdmTnsrAsmblyMintsVO.getMTG_DE());
//	    		snVO.setEND_DE(mdmTnsrAsmblyMintsVO.getMTG_DE());
//	    		snVO.setRASMBLY_NUMPR(mdmTnsrAsmblyMintsVO.getRASMBLY_NUMPR());
//	    		snVO.setFRST_REGIST_DT(calUtil.getTodayWithTime2());
//	    		snVO.setSESN_SE_STDCD("SES100");
//	    	}
//	    	
//    		MdmTnsrAsmblyMtgNmVO nmVO = new MdmTnsrAsmblyMtgNmVO();
//	    	String MTGNM_ID = mdmTnsrAsmblyMintsService.selectMdmAsmblyMtgId(mdmTnsrAsmblyMintsVO);
//	    	if( MTGNM_ID == null ) {
//	    		nmVO.setRASMBLY_NUMPR(mdmTnsrAsmblyMintsVO.getRASMBLY_NUMPR());
//	    		nmVO.setRASMBLY_ID(mdmTnsrAsmblyMintsVO.getRASMBLY_ID());
//	    		nmVO.setMTGNM_ID(MTGNM_ID);
//	    		nmVO.setMTGNM(mdmTnsrAsmblyMintsVO.getMTGNM());
//	    		nmVO.setMTGNM_KND_STDCD(MTGNM_KND_STDCD);
//	    		nmVO.setFRST_REGIST_DT(calUtil.getTodayWithTime2());
//
//	    		if( MTGNM_KND_STDCD.equals("MNK001") ) {
//	    			MTGNM_ID = "000001";
//	    		}
//	    		else {
//	    	    	MTGNM_ID = mdmTnsrAsmblyMintsService.selectMdmAsmblyMaxMtgId(mdmTnsrAsmblyMintsVO);
//	    	    	if( MTGNM_ID == null ) {
//	    	    		MTGNM_ID = "000002";
//	    	    	}
//	    	    	else {
//		    			MTGNM_ID = strUtil.getDecimalFormat("000000", Integer.parseInt(MTGNM_ID)+1);
//	    	    	}
//	    		}
//	    		nmVO.setMTGNM_ID(MTGNM_ID);
//	    		mdmTnsrAsmblyMintsService.insertMdmAsmblyMtgNm(nmVO);
//	    	}
//
//	    	mdmTnsrAsmblyMintsVO.setMTGNM_ID(MTGNM_ID);
//	    	
//	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();
//			if( !files.isEmpty() ) {
//				result = fileUtil.parseFileInf(files, "clik001", calUtil.getYear() + "/" + mdmTnsrAsmblyMintsVO.getRASMBLY_ID() + "/" + calUtil.getToday() + "/file/RES301/");
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
//					
//					String fileName = fvo.getStreFileNm();
//					if( fvo.getFileExtsn() != null && !fvo.getFileExtsn().equals("") ) {
//						fileName = fileName + "." + fvo.getFileExtsn();
//					}
//					mdmTnsrAsmblyMintsVO.setORGINL_FILE_NM(fvo.getOrignlFileNm());
//					mdmTnsrAsmblyMintsVO.setMINTS_FILE_PATH(fvo.getFileStreCours() + fileName);
//				}
//			}
//	    	mdmTnsrAsmblyMintsService.insertMdmMinutes(mdmTnsrAsmblyMintsVO);
//		}
//		catch(Exception e) {
//			
//		}
//		
//		model.addAttribute("mdmSearchVO", mdmSearchVO);
//		return "redirect:/mdm/MdmMinutesList.do?mdmAdm=Minutes";
//    }   

    /**
     * 회의록 수정
     * @param mdmMinutesVO
     * @param model
     * @return "/Mdm/MdmMinutes"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 수정", order = 680 ,gid = 50)
    @RequestMapping("/mdm/MdmMinutesUpdate.do")
    public String updateMdmMinutes(final MultipartHttpServletRequest multiRequest, @ModelAttribute("fileVO") MdmFileVO fileVO, 
    		@ModelAttribute("mdmTnsrAsmblyMintsVO") MdmTnsrAsmblyMintsVO mdmTnsrAsmblyMintsVO,
    		@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO,	ModelMap model) throws Exception {

    	/* 
    	 * 파일관련은 업데이트하지 않기로 하여 주석 처리 
    	 * */
    	
    	String message = "";
//		List<MdmFileVO> result = null;
//	    MdmFileVO fvo = null;
	    MdmCalUtil calUtil = new MdmCalUtil();
//	    MdmStrUtil strUtil = new MdmStrUtil();

	    try {
	    	calUtil.setDecimalFormat("00");
	    	//mdmTnsrAsmblyMintsVO.setMTG_DE(mdmTnsrAsmblyMintsVO.getMTG_DE().replace("-", "")); // + "000000";
	    	mdmTnsrAsmblyMintsVO.setLAST_UPDT_DT(calUtil.getTodayWithTime2());

	    	if( mdmTnsrAsmblyMintsVO.getCLOSE_AT() == null || mdmTnsrAsmblyMintsVO.getCLOSE_AT().equals("") ) {
	    		mdmTnsrAsmblyMintsVO.setCLOSE_AT("N");
    		}
	    	
//    		String MTGNM_KND_STDCD = "";
//    		if( mdmTnsrAsmblyMintsVO.getMTGNM().trim().equals("본회의") ) {
//    			MTGNM_KND_STDCD = "MNK001";
//    		}
//    		else if( mdmTnsrAsmblyMintsVO.getMTGNM().indexOf("행정사무감사") != -1 ||  mdmTnsrAsmblyMintsVO.getMTGNM().indexOf("조사특별위원회") != -1) {
//    			MTGNM_KND_STDCD = "MNK004";
//    		}
//    		else if( mdmTnsrAsmblyMintsVO.getMTGNM().equals("특별위원회") ) {
//    			MTGNM_KND_STDCD = "MNK003";
//    		}
//    		else {
//    			MTGNM_KND_STDCD = "MNK002";
//    		}

//	    	if( mdmTnsrAsmblyMintsService.selectMdmAsmblyNumPr(mdmTnsrAsmblyMintsVO) < 1 ) {
//	    		MdmTnsrAsmblyNumPrVO npVO = new MdmTnsrAsmblyNumPrVO();
//	    		npVO.setRASMBLY_NUMPR(mdmTnsrAsmblyMintsVO.getRASMBLY_NUMPR());
//	    		npVO.setBEGIN_SESN(mdmTnsrAsmblyMintsVO.getRASMBLY_SESN());
//	    		npVO.setEND_SESN(mdmTnsrAsmblyMintsVO.getRASMBLY_SESN());
//	    		npVO.setBEGIN_DE(mdmTnsrAsmblyMintsVO.getMTG_DE());
//	    		npVO.setEND_DE(mdmTnsrAsmblyMintsVO.getMTG_DE());
//	    		npVO.setRASMBLY_ID(mdmTnsrAsmblyMintsVO.getRASMBLY_ID());
//	    		npVO.setHT_SE_STDCD("HTS"+strUtil.getDecimalFormat("000", mdmTnsrAsmblyMintsVO.getRASMBLY_NUMPR()));
//	    		npVO.setFRST_REGIST_DT(calUtil.getTodayWithTime2());
//	    	}
	    	
//	    	if( mdmTnsrAsmblyMintsService.selectMdmAsmblySesn(mdmTnsrAsmblyMintsVO) < 1 ) {
//	    		MdmTnsrAsmblySesnVO snVO = new MdmTnsrAsmblySesnVO();
//	    		
//	    		snVO.setRASMBLY_ID(mdmTnsrAsmblyMintsVO.getRASMBLY_ID());
//	    		snVO.setRASMBLY_SESN(mdmTnsrAsmblyMintsVO.getRASMBLY_SESN());
//	    		snVO.setBEGIN_DE(mdmTnsrAsmblyMintsVO.getMTG_DE());
//	    		snVO.setEND_DE(mdmTnsrAsmblyMintsVO.getMTG_DE());
//	    		snVO.setRASMBLY_NUMPR(mdmTnsrAsmblyMintsVO.getRASMBLY_NUMPR());
//	    		snVO.setFRST_REGIST_DT(calUtil.getTodayWithTime2());
//	    		snVO.setSESN_SE_STDCD("SES100");
//	    	}
	    	
//    		MdmTnsrAsmblyMtgNmVO nmVO = new MdmTnsrAsmblyMtgNmVO();
//	    	String MTGNM_ID = mdmTnsrAsmblyMintsService.selectMdmAsmblyMtgId(mdmTnsrAsmblyMintsVO);
//	    	if( MTGNM_ID == null ) {
//	    		nmVO.setRASMBLY_NUMPR(mdmTnsrAsmblyMintsVO.getRASMBLY_NUMPR());
//	    		nmVO.setRASMBLY_ID(mdmTnsrAsmblyMintsVO.getRASMBLY_ID());
//	    		nmVO.setMTGNM_ID(MTGNM_ID);
//	    		nmVO.setMTGNM(mdmTnsrAsmblyMintsVO.getMTGNM());
//	    		nmVO.setMTGNM_KND_STDCD(MTGNM_KND_STDCD);
//	    		nmVO.setFRST_REGIST_DT(calUtil.getTodayWithTime2());
//
//	    		if( MTGNM_KND_STDCD.equals("MNK001") ) {
//	    			MTGNM_ID = "000001";
//	    		}
//	    		else {
//	    	    	MTGNM_ID = mdmTnsrAsmblyMintsService.selectMdmAsmblyMaxMtgId(mdmTnsrAsmblyMintsVO);
//	    	    	if( MTGNM_ID == null ) {
//	    	    		MTGNM_ID = "000002";
//	    	    	}
//	    	    	else {
//		    			MTGNM_ID = strUtil.getDecimalFormat("000000", Integer.parseInt(MTGNM_ID)+1);
//	    	    	}
//	    		}
//	    		nmVO.setMTGNM_ID(MTGNM_ID);
//	    		mdmTnsrAsmblyMintsService.insertMdmAsmblyMtgNm(nmVO);
//	    	}

//	    	mdmTnsrAsmblyMintsVO.setMTGNM_ID(MTGNM_ID);
	    	
//	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();
//			if( !files.isEmpty() ) {
//				result = fileUtil.parseFileInf(files, "minutes", calUtil.getYear() + "/" + mdmTnsrAsmblyMintsVO.getRASMBLY_ID() + "/" + calUtil.getToday() + "/RES301/");
//				if( result.size() > 0 ) {
//					fvo = new MdmFileVO();
//					fvo = result.get(0);
//					
//					String fileName = fvo.getStreFileNm();
//					if( fvo.getFileExtsn() != null && !fvo.getFileExtsn().equals("") ) {
//						fileName = fileName + "." + fvo.getFileExtsn();
//					}
//					mdmTnsrAsmblyMintsVO.setORGINL_FILE_NM(fvo.getOrignlFileNm());
//					mdmTnsrAsmblyMintsVO.setMINTS_FILE_PATH(fvo.getFileStreCours() + fileName);
//				}
//			}
			
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			mdmTnsrAsmblyMintsVO.setLAST_UPDUSR_ID(user.getMngrId());
			
	    	mdmTnsrAsmblyMintsService.updateMdmMinutes(mdmTnsrAsmblyMintsVO);
			message = "정상처리되었습니다.";
		}
		catch(Exception e) {
			System.out.println();
			System.out.println(e.toString());
			System.out.println();
			message = "";
		}
		
		model.addAttribute("menuName", "지방의회 회의록");
		model.addAttribute("cnId", mdmTnsrAsmblyMintsVO.getMINTS_CN());
		model.addAttribute("work", "수정");
		model.addAttribute("message", message);
		model.addAttribute("return_url", "/mdm/MdmMinutesMetaDataView1.do?cnId="+mdmTnsrAsmblyMintsVO.getMINTS_CN());
		model.addAttribute("EndPage", multiRequest.getParameter("EndPage"));
		model.addAttribute("cnList", multiRequest.getParameter("cnList"));
		model.addAttribute("sort", multiRequest.getParameter("sort"));
		
		
		
		//return "redirect:/mdm/MdmMinutesList.do?mdmAdm=Minutes";
        return "clikMng/mdm/MdmResult";
    }   

    /**
     * 회의록 삭제
     * @param mdmTnsrAsmblyAsembyVO
     * @param model
     * @return	"/mdm/MdmIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 회의록 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesDelete.do")
    public String MdmMinutesDelete(@ModelAttribute("mdmTnsrAsmblyMintsVO") MdmTnsrAsmblyMintsVO mdmTnsrAsmblyMintsVO, ModelMap model) throws Exception {
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		mdmTnsrAsmblyMintsVO.setLAST_UPDUSR_ID(user.getMngrId());
		
    	try{
			model.addAttribute("menuName", "지방의회 회의록");
			model.addAttribute("cnId", mdmTnsrAsmblyMintsVO.getMINTS_CN());
			model.addAttribute("work", "삭제");
			mdmTnsrAsmblyMintsService.deleteMdmMinutes(mdmTnsrAsmblyMintsVO);
			model.addAttribute("message", "정삭처리되었습니다.");
    	}catch(Exception e){
    		model.addAttribute("message", "오류가 발생하였습니다.");
    	}
    	
        return "clikMng/mdm/MdmResult";
    }

    /**
     * 회의록 첨부파일 삭제
     * @param mdmTnsrAsmblyAsembyVO
     * @param model
     * @return	"/mdm/MdmIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 회의록 첨부파일 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesFileDelete.do")
    public String MdmMinutesFileDelete(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	String MINTS_CN = "";
    	if( request.getParameter("MINTS_CN") != null ) {
    		MINTS_CN = request.getParameter("MINTS_CN");
    		if( MINTS_CN.indexOf("CLIK") != -1 ) {
        		mdmTnsrAsmblyMintsService.deleteMdmMinutesFile(MINTS_CN);
    		}
    		else {
    			mdmTnsrAsmblyMintsService.deleteMdmMinutesAppFile(MINTS_CN);
    		}
    	}
   	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";

    }

    /**
     * 회의록 첨부파일 다운로드
     * @param mdmPolicyVO
     * @param model
     * @return	"/Mdm/MdmBillFile"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 첨부파일 다운로드", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesDownLoad.do")
    public void MdmMinutesDownLoad(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	String downFile = "";
    	String orgFileName = "";
    	if( request.getParameter("DOWNID") != null ) {
    		downFile = request.getParameter("DOWNID");
    		orgFileName = downFile;
   			fileUtil.downFile(request, response, downFile, orgFileName);
    	}
    }

    
    /**
     * 회의록 - 파일 리스트 삭제
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 파일 리스트 삭제", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesFileListDelete.do")
    public String MdmMinutesFileListDelete(@ModelAttribute("mdmTnsrAsmblyMintsVO") MdmTnsrAsmblyMintsVO mdmTnsrAsmblyMintsVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	isView = mdmTnsrAsmblyMintsVO.getISVIEW().split("&");
    	for(int i = 0; i < isView.length; i++) {
    		arr = isView[i].split("=");
    		if( arr[0].indexOf("CLIK") != -1 ) {
    			mdmTnsrAsmblyMintsService.deleteMdmMinutesFile(arr[0]);
    		}
    		else {
    			mdmTnsrAsmblyMintsService.deleteMdmMinutesAppFile(arr[0]);
    		}
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 회의록 - 파일 재변환
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    @IncludedInfo(name="메타데이터 파일 재변환", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesFileListReCnvrs.do")
    public String MdmMinutesFileListReCnvrs(@ModelAttribute("mdmTnsrAsmblyMintsVO") MdmTnsrAsmblyMintsVO mdmTnsrAsmblyMintsVO, ModelMap model) throws Exception {
    	String arr[];
    	String isView[];
    	
    	isView = mdmTnsrAsmblyMintsVO.getISVIEW().split("&");
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	mdmTnsrAsmblyMintsVO.setLAST_UPDUSR_ID(user.getMngrId());
    	
    	for(int i = 0; i < isView.length; i++) {
    		arr = isView[i].split("=");
    		mdmTnsrAsmblyMintsService.updateMdmMinutesFileListReCnvrs(arr[0]);
    	}
    	
		String msg = "{\"rst\":\"yes\"}";

		model.addAttribute("msg", msg);
        return "clikMng/mdm/MdmIsView";
    }

    /**
     * 회의록 - 최근 수집일
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    public String MdmMinutesRegDate() throws Exception {
    	MdmStrUtil strUtil = new MdmStrUtil();
    	MdmCalUtil calUtil = new MdmCalUtil();
		calUtil.setDecimalFormat("00");

		String dt2 = mdmTnsrAsmblyMintsService.selectMdmMinutesMaxRegDate(calUtil.getToday());
		dt2 = strUtil.getStringFormat8("-", dt2);

		return dt2;
    }

    /**
     * 회의록 - 최근 수집일
     * @param mdmTnsrasmblyBiVO
     * @param model
     * @return	"/mdm/MdmBillIsView"
     * @throws Exception
     */
    public String MdmMinutesRegDate(String dt2) throws Exception {
    	MdmCalUtil calUtil = new MdmCalUtil();
    	calUtil.setDelimiter("-");
		calUtil.setDecimalFormat("00");
		String[] arr = dt2.split("-");

		return calUtil.getDateBeforeDt(calUtil.getUnixTime(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2])), -7);
    }

    /**
     * 회의록 - 지방의회 회수 정보를 조회한다.
     * @param RASMBLY_ID, RASMBLY_NUMPR
     * @return	"clikMng/mdm/MdmMinutesRasmblySesnView"
     * @throws Exception
     */
    @IncludedInfo(name="지방의회 회수 정보를 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesRasmblySesnView.do")
    public String MdmMinutesRasmblySesnView(HttpServletRequest request, ModelMap model) throws Exception {
    	HashMap<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("rasmbly_id",request.getParameter("rasmbly_id"));
    	paramMap.put("rasmbly_numpr",request.getParameter("rasmbly_numpr"));
    	paramMap.put("sesn_se_stdcd",request.getParameter("sesn_se_stdcd"));
    	List<HashMap<String,Object>> list = mdmTnsrAsmblyMintsService.selectRasmblySesnList(paramMap);
    	
    	JSONObject jObj = new JSONObject();
    	JSONArray jArr = new JSONArray();
    	HashMap<String,Object> vo = null;
    	for(int i = 0; i < list.size(); i++)
    	{
    		jObj = new JSONObject();
    		vo = list.get(i);
    		jObj.put("RASMBLY_SESN",vo.get("RASMBLY_SESN"));
			jArr.put(jObj);
    	}
    	
    	model.put("msg", jArr.toString());
    	
    	return "clikMng/mdm/MdmIsView";
    }
    
    /**
     * 회의록 - 지방의회 회의명 정보를 조회한다.
     * @param RASMBLY_ID, RASMBLY_NUMPR
     * @return	"clikMng/mdm/MdmMinutesMtgnmView"
     * @throws Exception
     */
    @IncludedInfo(name="지방의회 회의명 정보를 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesMtgnmView.do")
    public String MdmMinutesMtgnmView(HttpServletRequest request, ModelMap model) throws Exception {
    	HashMap<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("rasmbly_id",request.getParameter("rasmbly_id"));
    	paramMap.put("rasmbly_numpr",request.getParameter("rasmbly_numpr"));
    	List<HashMap<String,Object>> list = mdmTnsrAsmblyMintsService.selectMtgnmList(paramMap);
    	
    	JSONObject jObj = new JSONObject();
    	JSONArray jArr = new JSONArray();
    	HashMap<String,Object> vo = null;
    	for(int i = 0; i < list.size(); i++)
    	{
    		jObj = new JSONObject();
    		vo = list.get(i);
    		jObj.put("MTR_SN",vo.get("MTR_SN"));
    		jObj.put("MTR_SJ",vo.get("MTR_SJ"));
			jArr.put(jObj);
    	}
    	
    	model.put("msg", jArr.toString());
    	
    	return "clikMng/mdm/MdmIsView";
    }
    
    /**
     * 회의록 - 지방의회 안건 정보를 조회한다.
     * @param MINTS_CN
     * @return	"/mdm/MdmMinutesRasmblySesnView"
     * @throws Exception
     */
    @IncludedInfo(name="지방의회 안건 정보를 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesItemView.do")
    public String MdmMinutesItemView(HttpServletRequest request, ModelMap model) throws Exception {
    	HashMap<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("mints_cn",request.getParameter("mints_cn"));
    	List<HashMap<String,Object>> itemList = mdmTnsrAsmblyMintsService.selectItemList(paramMap);
    	model.put("itemList",itemList);
		return "clikMng/mdm/MdmMinutesItemView";
    }
    
    /**
     * 회의록 - 지방의회 관련의원 정보를 조회한다.
     * @param MINTS_CN
     * @return	"/mdm/MdmMinutesRasmblySesnView"
     * @throws Exception
     */
    @IncludedInfo(name="지방의회 관련의원 정보를 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/mdm/MdmMinutesAsembyInfoView.do")
    public String MdmMinutesAsembyInfoView(HttpServletRequest request, ModelMap model) throws Exception {
    	HashMap<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("mints_cn",request.getParameter("mints_cn"));
    	List<HashMap<String,Object>> asembyInfoList = mdmTnsrAsmblyMintsService.selectAsembyList(paramMap);
    	model.put("asembyInfoList",asembyInfoList);
		return "clikMng/mdm/MdmMinutesAsembyInfoView";
    }
    
    /**
     * 지바의회회의록 엑셀파일로 다운로드 한다.
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/selectMdmMinutesExcel.do")
    public ModelAndView selectMdmMinutesExcel(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
    											, @SuppressWarnings("rawtypes") Map commandMap
    											, ModelMap model) throws Exception {
    	
		if ( mdmSearchVO.getSchDt1() != null ) mdmSearchVO.setSchDt1(mdmSearchVO.getSchDt1().replaceAll("-",""));
		if ( mdmSearchVO.getSchDt2() != null ) mdmSearchVO.setSchDt2(mdmSearchVO.getSchDt2().replaceAll("-",""));
		if ( mdmSearchVO.getSchDt3() != null ) mdmSearchVO.setSchDt3(mdmSearchVO.getSchDt3().replaceAll("-",""));
		if ( mdmSearchVO.getSchDt4() != null ) mdmSearchVO.setSchDt4(mdmSearchVO.getSchDt4().replaceAll("-",""));

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
    	
    	int minutesListTotCnt = mdmTnsrAsmblyMintsService.selectMdmMinutesListTotCnt(mdmSearchVO);
    	
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
			minutesListTotCnt = minutesListTotCnt > maxExcelSearchCount ? maxExcelSearchCount : minutesListTotCnt;
		}

    	mdmSearchVO.setLastRecord(minutesListTotCnt);
    	
    	List<MdmTnsrAsmblyMintsVO> minutesList = mdmTnsrAsmblyMintsService.selectMdmMinutesList(mdmSearchVO);
    		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", mdmSearchVO);
        map.put("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? "" : (String) commandMap.get("selSearchGubun"));
        
    	map.put("resultList", minutesList);
    	map.put("resultCnt", Integer.toString(minutesListTotCnt));

        return new ModelAndView("MdmMinutesExcel", map);
		
    }
    
    /**
     * 지방의회회의록 텍스트파일로 다운로드 한다.
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @RequestMapping(value="/mdm/selectMdmMinutesText.do")
    public ModelAndView selectMdmMinutesText(@ModelAttribute("mdmSearchVO") MdmSearchVO mdmSearchVO
    											, @SuppressWarnings("rawtypes") Map commandMap
    											, ModelMap model) throws Exception {
    	
    	if ( mdmSearchVO.getSchDt1() != null ) mdmSearchVO.setSchDt1(mdmSearchVO.getSchDt1().replaceAll("-",""));
		if ( mdmSearchVO.getSchDt2() != null ) mdmSearchVO.setSchDt2(mdmSearchVO.getSchDt2().replaceAll("-",""));
		if ( mdmSearchVO.getSchDt3() != null ) mdmSearchVO.setSchDt3(mdmSearchVO.getSchDt3().replaceAll("-",""));
		if ( mdmSearchVO.getSchDt4() != null ) mdmSearchVO.setSchDt4(mdmSearchVO.getSchDt4().replaceAll("-",""));

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
    	
    	int minutesListTotCnt = mdmTnsrAsmblyMintsService.selectMdmMinutesListTotCnt(mdmSearchVO);
    	
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
			minutesListTotCnt = minutesListTotCnt > maxExcelSearchCount ? maxExcelSearchCount : minutesListTotCnt;
		}

    	mdmSearchVO.setLastRecord(minutesListTotCnt);
    	
    	List<MdmTnsrAsmblyMintsVO> minutesList = mdmTnsrAsmblyMintsService.selectMdmMinutesList(mdmSearchVO);
    		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", mdmSearchVO);
        map.put("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? "" : (String) commandMap.get("selSearchGubun"));
        
    	map.put("resultList", minutesList);
    	map.put("resultCnt", Integer.toString(minutesListTotCnt));

        return new ModelAndView("MdmMinutesText", map);
		
    }
}
