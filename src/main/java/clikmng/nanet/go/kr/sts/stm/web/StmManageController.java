package clikmng.nanet.go.kr.sts.stm.web;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeManageService;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.cmm.CommonUtil;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.CmmnDetailCode;
import clikmng.nanet.go.kr.cmm.util.CalUtil;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.sts.stm.service.HrsmnpdVO;
import clikmng.nanet.go.kr.sts.stm.service.StatsAsmblyAsemby;
import clikmng.nanet.go.kr.sts.stm.service.StatsAsmblyAsembyDetail;
import clikmng.nanet.go.kr.sts.stm.service.StatsPrmpstCmit;
import clikmng.nanet.go.kr.sts.stm.service.StatsPrmpstCmitDetail;
import clikmng.nanet.go.kr.sts.stm.service.StmInfoMngVO;
import clikmng.nanet.go.kr.sts.stm.service.StmManageDefaultVO;
import clikmng.nanet.go.kr.sts.stm.service.StmManageService;
import clikmng.nanet.go.kr.sts.stm.service.UseLogSummaryVO;
import clikmng.nanet.go.kr.sts.stm.service.UvsLogSummaryVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;



/**
 * 통계관리를 처리하는 Controller 클래스
 */

@Controller
public class StmManageController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "StmManageService")
    private  StmManageService StmManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="CmmUseService")
	private CmmUseService cmmUseService;

	@Resource(name = "CmmCodeManageService")
	private CmmCodeManageService cmmCodeManageService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    //통계정보관리 : 상임위원회 아이디
    @Resource(name="prmpstcmitIdGnrService")
	private EgovIdGnrService prmpstcmitIdGnrService;
    
  //통계정보관리 : 의안통계상세 아이디
    @Resource(name="billDetailIdGnrService")
	private EgovIdGnrService billDetailIdGnrService;
    
    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

    /**
     * 통계관리 - 의회별 자료이용 통계
     * @param searchVO
     * @param model
     * @return	"/Stm/DusList"
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@IncludedInfo(name="의회별 자료이용 통계 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/sts/stm/DusList.do")
    public String selectUseLogList(@ModelAttribute("searchVO") UseLogSummaryVO searchVO, ModelMap model) throws Exception {
    	
    	Calendar calendar = Calendar.getInstance();
    	String curYear = String.valueOf(calendar.get(Calendar.YEAR));
    	String curMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
    	String curDay = String.valueOf(calendar.get(Calendar.DATE));
		if(curMonth.length() == 1) curMonth = "0" + curMonth;
		if(curDay.length() == 1) curDay = "0" + curDay;
		if ( searchVO.getSchDt1() == null || "".equals(searchVO.getSchDt1()) ) searchVO.setSchDt1(curYear + curMonth + curDay);
		if ( searchVO.getSchDt2() == null || "".equals(searchVO.getSchDt2()) ) searchVO.setSchDt2(curYear + curMonth + curDay);
    	
    	if ( searchVO.getSearchCondition() == null || "".equals(searchVO.getSearchCondition())) searchVO.setSearchCondition("T");
		if ( searchVO.getSchDt1() != null ) searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		if ( searchVO.getSchDt2() != null ) searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));
    	    	
    	HashMap<String, List> itemMap = new HashMap();
    	HashMap<String, String> dataMap = new HashMap();
    	
		CmmCodeVO cmmCodeVO = new CmmCodeVO();

    	// 카테고리
		cmmCodeVO.setCodeClCd("RDC");
		List<EgovMap> categoryList = cmmCodeManageService.selectCmmCodeList(cmmCodeVO);
		
		
		for(EgovMap category : categoryList)
		{
			cmmCodeVO.setCodeId((String)category.get("codeId"));
			List<EgovMap> category2List = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
			itemMap.put((String)category.get("codeId"), category2List);
		}
		

		List<UseLogSummaryVO> dusList = (List<UseLogSummaryVO>) StmManageService.selectDusList(searchVO);

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("dusList", dusList);
		model.addAttribute("itemMap", itemMap);

		
    	// 자료유형
		cmmCodeVO.setCodeId("ELA004");
		List dataTypeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("dataTypeList", dataTypeList);

        if(searchVO.getSchDt1() != null && !"".equals(searchVO.getSchDt1()))
        {
        	searchVO.setSchDt1(searchVO.getSchDt1().substring(0, 4) + 
        			"-" + searchVO.getSchDt1().substring(4, 6) + 
        			"-" + searchVO.getSchDt1().substring(6, 8));
        }
        if(searchVO.getSchDt2() != null && !"".equals(searchVO.getSchDt2()))
        {
        	searchVO.setSchDt2(searchVO.getSchDt2().substring(0, 4) + 
        			"-" + searchVO.getSchDt2().substring(4, 6) + 
        			"-" + searchVO.getSchDt2().substring(6, 8));
        }


		model.addAttribute("searchVO", searchVO);
		
        return "clikMng/sts/stm/DusList";
    }
    
    /**
     * 통계관리 - 이용자 그룹별 자료이용 통계
     * @param searchVO
     * @param model
     * @return	"/Stm/GrpDusList"
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@IncludedInfo(name="이용자 그룹별 자료이용 통계 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/sts/stm/GrpDusList.do")
    public String selectGrpUseLogList(@ModelAttribute("searchVO") UseLogSummaryVO searchVO, ModelMap model) throws Exception {
    	
    	Calendar calendar = Calendar.getInstance();
    	String curYear = String.valueOf(calendar.get(Calendar.YEAR));
    	String curMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
    	String curDay = String.valueOf(calendar.get(Calendar.DATE));
		if(curMonth.length() == 1) curMonth = "0" + curMonth;
		if(curDay.length() == 1) curDay = "0" + curDay;
		if ( searchVO.getSchDt1() == null || "".equals(searchVO.getSchDt1()) ) searchVO.setSchDt1(curYear + curMonth + curDay);
		if ( searchVO.getSchDt2() == null || "".equals(searchVO.getSchDt2()) ) searchVO.setSchDt2(curYear + curMonth + curDay);
    	
    	if ( searchVO.getSearchCondition() == null || "".equals(searchVO.getSearchCondition())) searchVO.setSearchCondition("T");
		if ( searchVO.getSchDt1() != null ) searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		if ( searchVO.getSchDt2() != null ) searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));
    	    	
    	HashMap<String, List> itemMap = new HashMap();
    	HashMap<String, String> dataMap = new HashMap();
    	
		CmmCodeVO cmmCodeVO = new CmmCodeVO();

    	// 카테고리
		cmmCodeVO.setCodeClCd("RDC");
		List<EgovMap> categoryList = cmmCodeManageService.selectCmmCodeList(cmmCodeVO);
		
		
		for(EgovMap category : categoryList)
		{
			cmmCodeVO.setCodeId((String)category.get("codeId"));
			List<EgovMap> category2List = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
			itemMap.put((String)category.get("codeId"), category2List);
		}
		

		List<UseLogSummaryVO> dusList = (List<UseLogSummaryVO>) StmManageService.selectGrpDusList(searchVO);

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("dusList", dusList);
		model.addAttribute("itemMap", itemMap);

		
    	// 자료유형
		cmmCodeVO.setCodeId("ELA004");
		List dataTypeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("dataTypeList", dataTypeList);

        if(searchVO.getSchDt1() != null && !"".equals(searchVO.getSchDt1()))
        {
        	searchVO.setSchDt1(searchVO.getSchDt1().substring(0, 4) + 
        			"-" + searchVO.getSchDt1().substring(4, 6) + 
        			"-" + searchVO.getSchDt1().substring(6, 8));
        }
        if(searchVO.getSchDt2() != null && !"".equals(searchVO.getSchDt2()))
        {
        	searchVO.setSchDt2(searchVO.getSchDt2().substring(0, 4) + 
        			"-" + searchVO.getSchDt2().substring(4, 6) + 
        			"-" + searchVO.getSchDt2().substring(6, 8));
        }


		model.addAttribute("searchVO", searchVO);
		
        return "clikMng/sts/stm/GrpDusList";
    }
    
    
    /**
     * 통계관리 - 그룹별 이용자 방문 통계
     * @param searchVO
     * @param model
     * @return	"/Stm/GrpUvsList"
     * @throws Exception
     */
    @IncludedInfo(name="그룹별 이용자 방문 통계", order = 680 ,gid = 50)
    @RequestMapping("/sts/stm/GrpUvsList.do")
    public String selectGrpUvsList(@ModelAttribute("vo") StmManageDefaultVO searchVO
    	    , BindingResult bindingResult
    	    , Map commandMap
    	    , ModelMap model) throws Exception {

	    // 0. Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated)
	    {
	    	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	    	return "clikMng/uat/uia/EgovLoginUsr";
	    }
	    
	    // 기본 조회 날짜.
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf_search 	= new SimpleDateFormat("yyyy-MM-dd");
	    
	    String bgDt = null;
	    String edDt = null;
	    
	    if(searchVO.getSchDt1() != null && "".equals(searchVO.getSchDt1()) == false )
	    {
		    bgDt = searchVO.getSchDt1();
		    edDt = searchVO.getSchDt2();
	    }else{
	    	edDt = sdf_search.format(cal.getTime());
	    	//cal.add(cal.DATE, -1);
	    	bgDt = sdf_search.format(cal.getTime());
	    	
	    	searchVO.setSchDt1(bgDt.toString());
	    	searchVO.setSchDt2(edDt.toString());
	    }
	    
	    // 월별 조회 날짜
	    CalUtil calUtil = new CalUtil();
		calUtil.setDelimiter("-");
		calUtil.setDecimalFormat("00");
		
		String curYear = calUtil.getYear();
		String curMonth = calUtil.getMonth();
		
	    if(searchVO.getBgYear() == null || "".equals(searchVO.getBgYear())) searchVO.setBgYear(curYear);
		if(searchVO.getEdYear() == null || "".equals(searchVO.getEdYear())) searchVO.setEdYear(curYear);
		if(searchVO.getBgMonth() == null || "".equals(searchVO.getBgMonth())) searchVO.setBgMonth(curMonth);
		if(searchVO.getEdMonth() == null || "".equals(searchVO.getEdMonth())) searchVO.setEdMonth(curMonth);
    	
		if(searchVO.getBgDt() == null || "".equals(searchVO.getBgDt()) || 
				searchVO.getEdDt() == null || "".equals(searchVO.getEdDt()) ) {

    		String today 		= calUtil.getToday();
    		
    		if(searchVO.getBgDt() == null || "".equals(searchVO.getBgDt())){
    			searchVO.setBgDt(today);
    		}
    		if(searchVO.getEdDt() == null || "".equals(searchVO.getEdDt())){
    			searchVO.setEdDt(today);
    		}
		}
		
		if(searchVO.getSearchCondition() == null || "".equals(searchVO.getSearchCondition())){
    		searchVO.setSearchCondition("term");
    	}
		
	    List<?> termLogList = null;
	    
	    if(searchVO.getSearchCondition().equals("term")){
		    termLogList = StmManageService.selectGrpUvsList(searchVO);
	    	model.addAttribute("logList", termLogList);
	    }else if(searchVO.getSearchCondition().equals("month")){
			cal.set(Integer.parseInt(searchVO.getEdYear()), Integer.parseInt(searchVO.getEdMonth()) - 1, 1);
			String lastOfMonth = String.valueOf(cal.getActualMaximum(Calendar.DATE));
			if(lastOfMonth.length() == 1) lastOfMonth = "0" + lastOfMonth;
			
			searchVO.setSchDt1(searchVO.getBgYear() + "-" + searchVO.getBgMonth() + "-01");
			searchVO.setSchDt2(searchVO.getEdYear() + "-" + searchVO.getEdMonth() + "-" + lastOfMonth);
			
	    	termLogList = StmManageService.selectGrpUvsMonthList(searchVO);
	    	model.addAttribute("logList", termLogList);
	    }
	    
    	model.addAttribute("searchVO", searchVO);
    	
        return "clikMng/sts/stm/GrpUvsList";
    }
    
    /**
     * 통계관리 - 그룹별 이용자 방문 통계 - 월별 그룹화
     * @param searchVO
     * @param model
     * @return	"/Stm/GrpUvsList"
     * @throws Exception
     */
    @IncludedInfo(name="이용자 월별 방문 통계", order = 680 ,gid = 50)
    @RequestMapping("/sts/stm/UvsMonGrpList.do")
    public String selectUvsMonGrpList(@ModelAttribute("vo") StmManageDefaultVO searchVO
    	    , BindingResult bindingResult
    	    , Map commandMap
    	    , ModelMap model) throws Exception {

	    // 0. Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated)
	    {
	    	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	    	return "clikMng/uat/uia/EgovLoginUsr";
	    }
	    
	    // 기본 조회 날짜.
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf_search 	= new SimpleDateFormat("yyyy-MM-dd");
	    
	    String bgDt = null;
	    String edDt = null;
	    
	    if(searchVO.getSchDt1() != null && "".equals(searchVO.getSchDt1()) == false )
	    {
		    bgDt = searchVO.getSchDt1();
		    edDt = searchVO.getSchDt2();
	    }else{
	    	edDt = sdf_search.format(cal.getTime());
	    	//cal.add(cal.DATE, -1);
	    	bgDt = sdf_search.format(cal.getTime());
	    	
	    	searchVO.setSchDt1(bgDt.toString());
	    	searchVO.setSchDt2(edDt.toString());
	    }
	    
	    // 월별 조회 날짜
	    CalUtil calUtil = new CalUtil();
		calUtil.setDelimiter("-");
		calUtil.setDecimalFormat("00");
		
		String curYear = calUtil.getYear();
		String curMonth = calUtil.getMonth();
		
	    if(searchVO.getBgYear() == null || "".equals(searchVO.getBgYear())) searchVO.setBgYear(curYear);
		if(searchVO.getEdYear() == null || "".equals(searchVO.getEdYear())) searchVO.setEdYear(curYear);
		if(searchVO.getBgMonth() == null || "".equals(searchVO.getBgMonth())) searchVO.setBgMonth(curMonth);
		if(searchVO.getEdMonth() == null || "".equals(searchVO.getEdMonth())) searchVO.setEdMonth(curMonth);
    	
		if(searchVO.getBgDt() == null || "".equals(searchVO.getBgDt()) || 
				searchVO.getEdDt() == null || "".equals(searchVO.getEdDt()) ) {

    		String today 		= calUtil.getToday();
    		
    		if(searchVO.getBgDt() == null || "".equals(searchVO.getBgDt())){
    			searchVO.setBgDt(today);
    		}
    		if(searchVO.getEdDt() == null || "".equals(searchVO.getEdDt())){
    			searchVO.setEdDt(today);
    		}
		}
		
		if(searchVO.getSearchCondition() == null || "".equals(searchVO.getSearchCondition())){
    		searchVO.setSearchCondition("term");
    	}
		
	    List<?> termLogList = null;
	    
		cal.set(Integer.parseInt(searchVO.getEdYear()), Integer.parseInt(searchVO.getEdMonth()) - 1, 1);
		String lastOfMonth = String.valueOf(cal.getActualMaximum(Calendar.DATE));
		if(lastOfMonth.length() == 1) lastOfMonth = "0" + lastOfMonth;
		
		searchVO.setSchDt1(searchVO.getBgYear() + "-" + searchVO.getBgMonth() + "-01");
		searchVO.setSchDt2(searchVO.getEdYear() + "-" + searchVO.getEdMonth() + "-" + lastOfMonth);
		
    	termLogList = StmManageService.selectUvsMonGrpList(searchVO);
    	model.addAttribute("logList", termLogList);
	    
    	model.addAttribute("searchVO", searchVO);
    	
        return "clikMng/sts/stm/GrpUvsList";
    }
    
    /**
     * 통계관리 - OS별 이용자 방문 통계
     * @param searchVO
     * @param model
     * @return	"/Stm/OsUvsList"
     * @throws Exception
     */
    @IncludedInfo(name="OS별 이용자 방문 통계", order = 680 ,gid = 50)
    @RequestMapping("/sts/stm/OsUvsList.do")
    public String selectOsUvsList(@ModelAttribute("vo") StmManageDefaultVO searchVO
    	    , BindingResult bindingResult
    	    , Map commandMap
    	    , ModelMap model) throws Exception {

	    // 0. Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated)
	    {
	    	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	    	return "clikMng/uat/uia/EgovLoginUsr";
	    }
	    
	    // 기본 조회 날짜.
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf_search 	= new SimpleDateFormat("yyyy-MM-dd");
	    
	    String bgDt = null;
	    String edDt = null;
	    
	    if(searchVO.getSchDt1() != null && "".equals(searchVO.getSchDt1()) == false )
	    {
		    bgDt = searchVO.getSchDt1();
		    edDt = searchVO.getSchDt2();
	    }else{
	    	edDt = sdf_search.format(cal.getTime());
	    	//cal.add(cal.DATE, -1);
	    	bgDt = sdf_search.format(cal.getTime());
	    	
	    	searchVO.setSchDt1(bgDt.toString());
	    	searchVO.setSchDt2(edDt.toString());
	    }
	    
	    List logList = StmManageService.selectOsUvsList(searchVO);

    	model.addAttribute("logList", logList);
    	model.addAttribute("searchVO", searchVO);
    	
        return "clikMng/sts/stm/OsUvsList";
    }


    /**
     * 통계관리 - 이용자 방문 통계 - 사용안함
     * @param searchVO
     * @param model
     * @return	"/Stm/Uvs"
     * @throws Exception
     */
    @IncludedInfo(name="이용자 방문 통계", order = 680 ,gid = 50)
    @RequestMapping("/sts/stm/UvsList.do")
    public String selectUvsList(@ModelAttribute("vo") StmManageDefaultVO searchVO
    	    , BindingResult bindingResult
    	    , Map commandMap
    	    , ModelMap model) throws Exception {

	    // 0. Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated)
	    {
	    	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	    	return "clikMng/uat/uia/EgovLoginUsr";
	    }
	    
	    boolean isDefault = true;
	    
	    // 기본 조회 날짜.
	    Calendar cal = Calendar.getInstance();
    
	    SimpleDateFormat sdf_day 		= new SimpleDateFormat("yyyyMMdd");
	    SimpleDateFormat sdf_month 		= new SimpleDateFormat("yyyyMM");
	    SimpleDateFormat sdf_year 		= new SimpleDateFormat("yyyy");

	    SimpleDateFormat sdf_search 	= new SimpleDateFormat("yyyy-MM-dd");
	    
	    Date bgDt = null;
	    Date edDt = null;
	    
	    if(searchVO.getSchDt1() != null && "".equals(searchVO.getSchDt1()) == false )
	    {
		    bgDt = sdf_search.parse(searchVO.getSchDt1());
		    edDt = sdf_search.parse(searchVO.getSchDt2());
	    	
	    	isDefault = false;
	    }
	    
	    List logList = null;
	    
	    String sCmd	= commandMap.get("cmd")	== null	? "os" : (String)	commandMap.get("cmd");

	    if (sCmd.equals("os"))
	    {
	    	if(isDefault)
	    	{
		    	edDt = cal.getTime();
		    	cal.add(cal.DATE, -1);
		    	bgDt = cal.getTime();
	    	}
	    	
	    	searchVO.setEdDt(sdf_day.format(edDt));
		    searchVO.setBgDt(sdf_day.format(bgDt));

	    	logList = StmManageService.selectOSLogList(searchVO);
	    }
	    else if(sCmd.equals("wbsr"))
	    {
	    	if(isDefault)
	    	{
		    	edDt = cal.getTime();
		    	cal.add(cal.DATE, -1);
		    	bgDt = cal.getTime();
	    	}
	    	
	    	searchVO.setEdDt(sdf_day.format(edDt));
		    searchVO.setBgDt(sdf_day.format(bgDt));

	    	logList = StmManageService.selectWbsrLogList(searchVO);
	    }
	    else if(sCmd.equals("hour"))
	    {
	    	if(isDefault)
	    	{
		    	edDt = cal.getTime();
		    	cal.add(cal.DATE, -1);
		    	bgDt = cal.getTime();
	    	}
	    	
	    	searchVO.setEdDt(sdf_day.format(edDt));
		    searchVO.setBgDt(sdf_day.format(bgDt));

	    	logList = StmManageService.selectHourLogList(searchVO);
	    }
	    else if(sCmd.equals("day"))
	    {
	    	if(isDefault)
	    	{
		    	edDt = cal.getTime();
		    	cal.add(cal.MONTH, -1);
		    	bgDt = cal.getTime();
	    	}

	    	searchVO.setEdDt(sdf_month.format(edDt));
		    searchVO.setBgDt(sdf_month.format(bgDt));

	    	logList = StmManageService.selectDayLogList(searchVO);
	    }
	    else if(sCmd.equals("month"))
	    {
	    	if(isDefault)
	    	{
		    	edDt = cal.getTime();
		    	cal.add(cal.YEAR, -1);
		    	bgDt = cal.getTime();
	    	}
	    	
	    	searchVO.setEdDt(sdf_year.format(edDt));
		    searchVO.setBgDt(sdf_year.format(bgDt));

		    logList = StmManageService.selectMonthLogList(searchVO);
	    }
	    else if(sCmd.equals("year"))
	    {
	    	if(isDefault)
	    	{
		    	edDt = cal.getTime();
		    	cal.add(cal.YEAR, -1);
		    	bgDt = cal.getTime();
	    	}
	    	
	    	searchVO.setEdDt(sdf_year.format(edDt));
		    searchVO.setBgDt(sdf_year.format(bgDt));

	    	logList = StmManageService.selectYearLogList(searchVO);
	    }

	    

	    model.addAttribute("cmd", sCmd);
    	model.addAttribute("logList", logList);
    	model.addAttribute("paginationInfo", 0);
    	model.addAttribute("searchVO", searchVO);
    	
        return "clikMng/sts/stm/UvsList";
    }   


    /**
     * 통계관리 - 의회별 자료이용통계 엑셀다운로드
     * @param searchVO
     * @param model
     * @return 엑셀파일
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@IncludedInfo(name="의회별 자료 이용통계 엑셀출력", order = 680 ,gid = 50)
    @RequestMapping("/sts/stm/selectDusExcel.do")    
    public ModelAndView selectDusExcel(@ModelAttribute("searchVO") UseLogSummaryVO searchVO, ModelMap model) throws Exception {

		if ( searchVO.getSchDt1() != null ) searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		if ( searchVO.getSchDt2() != null ) searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));

    	HashMap<String, List> itemMap = new HashMap();
    	HashMap<String, String> dataMap = new HashMap();
    	
		CmmCodeVO cmmCodeVO = new CmmCodeVO();

    	// 카테고리
		cmmCodeVO.setCodeClCd("RDC");
		List<EgovMap> categoryList = cmmCodeManageService.selectCmmCodeList(cmmCodeVO);
		
		for(EgovMap category : categoryList)
		{
			cmmCodeVO.setCodeId((String)category.get("codeId"));
			List<EgovMap> category2List = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
			itemMap.put((String)category.get("codeId"), category2List);
		}
		    	
		List<UseLogSummaryVO> dusList = (List<UseLogSummaryVO>) StmManageService.selectDusList(searchVO);

        if(searchVO.getSchDt1() != null && !"".equals(searchVO.getSchDt1()))
        {
        	searchVO.setSchDt1(searchVO.getSchDt1().substring(0, 4) + 
        			"-" + searchVO.getSchDt1().substring(4, 6) + 
        			"-" + searchVO.getSchDt1().substring(6, 8));
        }
        if(searchVO.getSchDt2() != null && !"".equals(searchVO.getSchDt2()))
        {
        	searchVO.setSchDt2(searchVO.getSchDt2().substring(0, 4) + 
        			"-" + searchVO.getSchDt2().substring(4, 6) + 
        			"-" + searchVO.getSchDt2().substring(6, 8));
        }

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", searchVO);
    	map.put("categoryList", categoryList);
    	map.put("dusList", dusList);
    	map.put("detailList", itemMap);

    	return new ModelAndView("DusExcel", map);

    }    
    
    /**
     * 통계관리 - 이용자 그룹별 자료이용통계 엑셀다운로드
     * @param searchVO
     * @param model
     * @return 엑셀파일
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@IncludedInfo(name="이용자 그룹별 이용통계 엑셀출력", order = 680 ,gid = 50)
    @RequestMapping("/sts/stm/selectGrpDusExcel.do")    
    public ModelAndView selectGrpDusExcel(@ModelAttribute("searchVO") UseLogSummaryVO searchVO, ModelMap model) throws Exception {

    	Calendar calendar = Calendar.getInstance();
    	String curYear = String.valueOf(calendar.get(Calendar.YEAR));
    	String curMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
    	String curDay = String.valueOf(calendar.get(Calendar.DATE));
		if(curMonth.length() == 1) curMonth = "0" + curMonth;
		if(curDay.length() == 1) curDay = "0" + curDay;
		if ( searchVO.getSchDt1() == null || "".equals(searchVO.getSchDt1()) ) searchVO.setSchDt1(curYear + curMonth + curDay);
		if ( searchVO.getSchDt2() == null || "".equals(searchVO.getSchDt2()) ) searchVO.setSchDt2(curYear + curMonth + curDay);
    	
    	if ( searchVO.getSearchCondition() == null || "".equals(searchVO.getSearchCondition())) searchVO.setSearchCondition("T");
		if ( searchVO.getSchDt1() != null ) searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		if ( searchVO.getSchDt2() != null ) searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));
    	    	
    	HashMap<String, List> itemMap = new HashMap();
    	HashMap<String, String> dataMap = new HashMap();
    	
		CmmCodeVO cmmCodeVO = new CmmCodeVO();

    	// 카테고리
		cmmCodeVO.setCodeClCd("RDC");
		List<EgovMap> categoryList = cmmCodeManageService.selectCmmCodeList(cmmCodeVO);
		
		
		for(EgovMap category : categoryList)
		{
			cmmCodeVO.setCodeId((String)category.get("codeId"));
			List<EgovMap> category2List = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
			itemMap.put((String)category.get("codeId"), category2List);
		}
		

		List<UseLogSummaryVO> dusList = (List<UseLogSummaryVO>) StmManageService.selectGrpDusList(searchVO);

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("dusList", dusList);
		model.addAttribute("itemMap", itemMap);

		
    	// 자료유형
		cmmCodeVO.setCodeId("ELA004");
		List dataTypeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("dataTypeList", dataTypeList);

        if(searchVO.getSchDt1() != null && !"".equals(searchVO.getSchDt1()))
        {
        	searchVO.setSchDt1(searchVO.getSchDt1().substring(0, 4) + 
        			"-" + searchVO.getSchDt1().substring(4, 6) + 
        			"-" + searchVO.getSchDt1().substring(6, 8));
        }
        if(searchVO.getSchDt2() != null && !"".equals(searchVO.getSchDt2()))
        {
        	searchVO.setSchDt2(searchVO.getSchDt2().substring(0, 4) + 
        			"-" + searchVO.getSchDt2().substring(4, 6) + 
        			"-" + searchVO.getSchDt2().substring(6, 8));
        }


		model.addAttribute("searchVO", searchVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", searchVO);
    	map.put("categoryList", categoryList);
    	map.put("dusList", dusList);
    	map.put("detailList", itemMap);

    	return new ModelAndView("GrpDusExcel", map);

    }
    
    /**
     * 통계관리 - 그룹별 이용자 방문통계 엑셀다운로드
     * @param searchVO
     * @param model
     * @return 엑셀파일
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@IncludedInfo(name="그룹별 이용자 방문 통계 엑셀출력", order = 680 ,gid = 50)
    @RequestMapping("/sts/stm/selectGrpUvsExcel.do")    
    public ModelAndView selectUvsExcel(@ModelAttribute("vo") StmManageDefaultVO searchVO
    	    , BindingResult bindingResult
    	    , Map commandMap
    	    , ModelMap model) throws Exception {
	    
	    // 기본 조회 날짜.
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf_search 	= new SimpleDateFormat("yyyy-MM-dd");
	    
	    String bgDt = null;
	    String edDt = null;
	    
	    if(searchVO.getSchDt1() != null && "".equals(searchVO.getSchDt1()) == false )
	    {
		    bgDt = searchVO.getSchDt1();
		    edDt = searchVO.getSchDt2();
	    }else{
	    	edDt = sdf_search.format(cal.getTime());
	    	//cal.add(cal.DATE, -1);
	    	bgDt = sdf_search.format(cal.getTime());
	    	
	    	searchVO.setSchDt1(bgDt.toString());
	    	searchVO.setSchDt2(edDt.toString());
	    }
	    
	    List<UvsLogSummaryVO> logList = null;
	    
	    if(searchVO.getSearchCondition() != null && searchVO.getSearchCondition().equals("grp")){
	    	logList = (List<UvsLogSummaryVO>) StmManageService.selectUvsMonGrpList(searchVO);
	    }else{
	    	logList = (List<UvsLogSummaryVO>) StmManageService.selectGrpUvsList(searchVO);
	    }
	    
    	model.addAttribute("logList", logList);
    	model.addAttribute("searchVO", searchVO);

		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("logList", logList);
    	map.put("searchVO", searchVO);
    	
    	if(searchVO.getSearchCondition() != null && searchVO.getSearchCondition().equals("grp")){
    		return new ModelAndView("GrpUvsMonthExcel", map);
	    }else{
	    	return new ModelAndView("GrpUvsExcel", map);
	    }
    } 
    
    /**
     * 통계관리 - OS별 이용자 방문통계 엑셀다운로드
     * @param searchVO
     * @param model
     * @return 엑셀파일
     * @throws Exception
     */
    @IncludedInfo(name="OS별 이용자 방문 통계 엑셀출력", order = 680 ,gid = 50)
    @RequestMapping("/sts/stm/selectOSUvsExcel.do")    
    public ModelAndView selectOSUvsExcel(@ModelAttribute("vo") StmManageDefaultVO searchVO
    	    , BindingResult bindingResult
    	    , Map commandMap
    	    , ModelMap model) throws Exception {
	    
	    String bgDt = null;
	    String edDt = null;
	    
	    if(searchVO.getSchDt1() != null && "".equals(searchVO.getSchDt1()) == false )
	    {
		    bgDt = searchVO.getSchDt1();
		    edDt = searchVO.getSchDt2();
	    }else{
	    	// 기본 조회 날짜.
		    Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf_search 	= new SimpleDateFormat("yyyy-MM-dd");
		    
	    	edDt = sdf_search.format(cal.getTime());
	    	//cal.add(cal.DATE, -1);
	    	bgDt = sdf_search.format(cal.getTime());
	    	
	    	searchVO.setSchDt1(bgDt.toString());
	    	searchVO.setSchDt2(edDt.toString());
	    }
	    
	    List logList = StmManageService.selectOsUvsList(searchVO);

    	model.addAttribute("logList", logList);
    	model.addAttribute("searchVO", searchVO);

		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("logList", logList);
    	map.put("searchVO", searchVO);

		return new ModelAndView("OsUvsExcel", map);
    } 
    
    /**
     * 통계정보관리 - ajax 기관유형 불러오기
     * @param 
     * @return	
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/sts/stm/selectAjaxInsttTy.do")
    public void selectAjaxInsttTy(CmmnDetailCode vo
								    		, HttpServletResponse resp
								    		, HttpServletRequest request) throws Exception {
    	
    	// 0. 파라미터 값에 기관분류(insttCode)가 있으면 기관유형코드를 조회
    	vo.setInsttClCode(CommonUtil.nvl(request.getParameter("fInsttClCode"),""));
    	
    	// 1. 기관유형
     	List<CmmnDetailCode> result = cmmUseService.selectInsttTyDetails(vo);
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
    	for(int i=0; i<result.size(); i++) {
    		CmmnDetailCode cdc = new CmmnDetailCode(); 
    		cdc = (CmmnDetailCode) result.get(i);
    		jso = new JSONObject();
    		
			jso.put("fInsttTyClCodeNm", cdc.getfInsttClCodeNm());		// 기관유형 코드명
    		jso.put("fInsttTyClCode", cdc.getfInsttClCode());				// 기관유형 코드
    		
    		jarray.add(i, jso);
    	}
	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());
    } 
    
    /**
     * 통계정보관리 - ajax 광역시도 정보 불러오기
     * @param 
     * @return	
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/sts/stm/selectAjaxBrtc.do")
    public void selectAjaxBrtc( CmmnDetailCode vo
									    		, HttpServletResponse resp
									    		, HttpServletRequest request)
									            throws Exception {

    	// 0. 파라미터 값에 기관분류코드(insttClCode)가 있으면 광역시도코드를 조회
    	// 광역시도 값 가져오기 공통분류 LMC
    	vo.setBrtcCode("LMC");
     	List<CmmnDetailCode> result = cmmUseService.selectBrtcCodeDetails(vo);
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
    	for(int i=0; i<result.size(); i++) {
    		CmmnDetailCode cdc = new CmmnDetailCode(); 
    		cdc = (CmmnDetailCode) result.get(i);
    		jso = new JSONObject();
    		
			jso.put("codeIdNm", cdc.getCodeIdNm());		// 광역시도 코드명
    		jso.put("codeId", cdc.getCodeId());					// 광역시도 코드
    		
    		jarray.add(i, jso);
    	}
    	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());    
    } 
            
    
    /**
     * 통계정보관리 - ajax 소속 정보 불러오기
     * @param 
     * @return	
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/sts/stm/selectAjaxPsitn.do")
    public void selectAjaxPsitn(CmmnDetailCode vo
    		, HttpServletResponse resp
    		, HttpServletRequest request)
            throws Exception {

    	// 0. 기관코드 및 광역시군구 코드
    	vo.setInsttClCode(CommonUtil.nvl(request.getParameter("insttClCode"),""));
    	vo.setBrtcCode(CommonUtil.nvl(request.getParameter("brtcCode"),""));

    	// 1. 소속코드 및 소속명 리스트
    	List<CmmnDetailCode> result = cmmUseService.selectLoasmInfo(vo);
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
    	for(int i=0; i<result.size(); i++) {
    		CmmnDetailCode cdc = new CmmnDetailCode(); 
    		cdc = (CmmnDetailCode) result.get(i);
    		jso = new JSONObject();
    		
    		jso.put("loasmNm", cdc.getLoasmNm());					// 지방정부 및 지방의회명
    		jso.put("loasmCode", cdc.getLoasmCode());				// 지방정부 및 지방으회코드
    		
    		jarray.add(i, jso);
    	}
    	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());    
    }
    
    /**
     * 통계관리 - 통계정보관리
     * @param searchVO
     * @param model
     * @return	"/Stm/StmMngList"
     * @throws Exception
     */
    @IncludedInfo(name="통계정보관리 조회", order = 680 ,gid = 50)
    @RequestMapping(value="/sts/stm/StmInfoMngList.do")
    public String StmInfoMngList(@ModelAttribute("StmInfoMngVO") StmInfoMngVO searchVO, ModelMap model) throws Exception {

    	CmmnDetailCode vo = new CmmnDetailCode();
    	
    	//지방의회로 고정
    	searchVO.setINSTT_CL_CODE("INTSTTCL_000012");
    	
    	//기관유형 목록
    	if(searchVO.getINSTT_CL_CODE() != null && !"".equals(searchVO.getINSTT_CL_CODE())){
    		vo.setfInsttClCode(searchVO.getINSTT_CL_CODE());
         	List<CmmnDetailCode> loasm_ty_code_list = cmmUseService.selectInsttTyDetails(vo);
         	model.addAttribute("instt_ty_code_code_list", loasm_ty_code_list);
    	}
     	
    	//지역목록
    	if(searchVO.getINSTT_TY_CODE() != null && !"".equals(searchVO.getINSTT_TY_CODE())){
    		vo.setBrtcCode("LMC");
    		List<CmmnDetailCode> brtc_code_list = cmmUseService.selectBrtcCodeDetails(vo);
    		model.addAttribute("brtc_code_list", brtc_code_list);
    	}

    	//의회목록
     	if(searchVO.getBRTC_CODE() != null && !"".equals(searchVO.getBRTC_CODE())){
    		vo.setInsttClCode(searchVO.getINSTT_TY_CODE());
    		vo.setBrtcCode(searchVO.getBRTC_CODE());
         	List<CmmnDetailCode> loasm_code_list = cmmUseService.selectLoasmInfo(vo);
         	model.addAttribute("loasm_code_list", loasm_code_list);
    	}
     	
     	
    	/*
   		 * 페이징
   		 * */
   		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageSize());
		paginationInfo.setPageSize(searchVO.getPageUnit());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
    	
		List<StmInfoMngVO> list = StmManageService.selectStmInfoMngList(searchVO);
		
		if(list != null && list.size() > 0)
			paginationInfo.setTotalRecordCount(Integer.parseInt(list.get(0).getTOTCNT()));
		
		model.addAttribute("list", list);
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("paginationInfo",paginationInfo);
		
        return "clikMng/sts/stm/StmInfoMngList";
    }
    
    /**
     * 통계관리 - 통계정보관리 등록 / 수정 화면
     * @param searchVO
     * @param model
     * @return	"/Stm/StmMngList"
     * @throws Exception
     */
    @IncludedInfo(name="통계정보관리 등록 / 수정 화면", order = 680 ,gid = 50)
    @RequestMapping(value="/sts/stm/StmInfoMngDetail.do")
    public String StmInfoMngDetail(@ModelAttribute("StmInfoMngVO") StmInfoMngVO searchVO, ModelMap model) throws Exception {
    	
    	CmmnDetailCode vo = new CmmnDetailCode();
    	
    	//지방의회로 고정
    	searchVO.setINSTT_CL_CODE("INTSTTCL_000012");
    	
    	//검색 정보 설정을 위하여 의회 정보가 존재할 경우 조회
    	if(searchVO.getLOASM_CODE() != null && !"".equals(searchVO.getLOASM_CODE())){
    		HashMap<String, Object> param = new HashMap<String, Object>();
    		param.put("RASMBLY_ID", searchVO.getLOASM_CODE());
    		HashMap<String,Object> obj = StmManageService.selectRasmblyInfo(param);
    		
    		searchVO.setINSTT_CL_CODE(obj.get("UPPER_INSTT_CL_CODE").toString());
    		searchVO.setINSTT_TY_CODE(obj.get("INSTT_CL_CODE").toString());
    		searchVO.setLOASM_TY_CODE(obj.get("LOASM_TY_CODE").toString());
    		searchVO.setBRTC_CODE(obj.get("BRTC_CODE").toString());
    	}
    	
    	
    	//지역목록
    	if(searchVO.getINSTT_TY_CODE() != null && !"".equals(searchVO.getINSTT_TY_CODE())){
    		vo.setBrtcCode("LMC");
    		List<CmmnDetailCode> brtc_code_list = cmmUseService.selectBrtcCodeDetails(vo);
    		model.addAttribute("brtc_code_list", brtc_code_list);
    	}

    	//의회목록
     	if(searchVO.getBRTC_CODE() != null && !"".equals(searchVO.getBRTC_CODE())){
    		vo.setInsttClCode(searchVO.getINSTT_TY_CODE());
    		vo.setBrtcCode(searchVO.getBRTC_CODE());
         	List<CmmnDetailCode> loasm_code_list = cmmUseService.selectLoasmInfo(vo);
         	model.addAttribute("loasm_code_list", loasm_code_list);
    	}
     	
     	//기수정보
    	List<HrsmnpdVO> hrsmnpdList = StmManageService.selectHrsmnpdList();
    	model.addAttribute("hrsmnpdList", hrsmnpdList);
		
    	
    	StringBuffer sb = new StringBuffer();
    	
    	HashMap<String, Object> param = new HashMap<String, Object>();

    	if(searchVO.getLOASM_CODE() != null && !"".equals(searchVO.getLOASM_CODE())){
    		
    		String[] str = searchVO.getHRSMNPD_SN().split("@"); 
    		searchVO.setHRSMNPD_SN(str[0]);
    		searchVO.setHT_SE_STDCD(str[1]);
    		
	    	param.put("rasmbly_id", searchVO.getLOASM_CODE());
	    	param.put("hrsmnpd_sn", searchVO.getHRSMNPD_SN());
	    	param.put("ht_se_stdcd", searchVO.getHT_SE_STDCD());
	    	param.put("rasmbly_numpr", searchVO.getRASMBLY_NUMPR());
	    	
	    	
	    	/*
	    	 * 
	    	 * 의회정당별의원
	    	 * 
	    	 * */
	    	List<HashMap<String,Object>> AsmbyAsembyList = StmManageService.selectAsmbyAsembyList(param);
	    	sb.setLength(0);
	    	
	    	if(AsmbyAsembyList.size() > 0){
		    	StringBuffer head1 = new StringBuffer();
		    	StringBuffer head2 = new StringBuffer();
		    	StringBuffer head3 = new StringBuffer();
		    	StringBuffer body = new StringBuffer();
				
				head1.append("<thead>");
				head1.append("	<tr>");
				head1.append("		<th scope='col' rowspan='3'>의원정수(A=B+C)</th>");
				head1.append("		<th scope='col' colspan='3'>광역 의원(B)</th>");
				head1.append("		<th scope='col' colspan='" + (AsmbyAsembyList.size()*2) + "'>정당별</th>");
				head1.append("		<th scope='col' rowspan='3'>교육의원(C)</th>");
				head1.append("	</tr>");
				head2.append("	<tr>");
				head2.append("		<th scope='col'  rowspan='2'>계</th>");
				head2.append("		<th scope='col'  rowspan='2'>지역</th>");
				head2.append("		<th scope='col'  rowspan='2'>비례</th>");
				head3.append("	<tr>");
				
				for(HashMap<String,Object> map : AsmbyAsembyList){
					head2.append("		<th scope='col' colspan='2'>" + map.get("PPRTY_NM") + "</th>");
					head3.append("		<th scope='col'>지역</th>");
					head3.append("		<th scope='col'>비례</th>");
				}
				
				head2.append("	</tr>");
				head3.append("	</tr>");
				head1.append(head2.toString());
				head1.append(head3.toString());
				head1.append("</thead>");
				
				body.append("<tbody>");
				body.append("		<tr>");
				body.append("			<td class='r'><span id='asemby_cnt'></span></td>");
				body.append("			<td class='r'><span id='wdr_asemby_cnt'>");
				
				//광역의원 계
				if(AsmbyAsembyList.get(0).get("WDR_ASEMBY_AREA_SM") != null)
				{
					if(AsmbyAsembyList.get(0).get("WDR_ASEMBY_PRPORT_SM") != null)
					{
						body.append((Integer.parseInt(AsmbyAsembyList.get(0).get("WDR_ASEMBY_AREA_SM").toString())+Integer.parseInt(AsmbyAsembyList.get(0).get("WDR_ASEMBY_PRPORT_SM").toString())));
					}
					else
					{
						body.append(Integer.parseInt(AsmbyAsembyList.get(0).get("WDR_ASEMBY_AREA_SM").toString()));
					}
				}
				else if(AsmbyAsembyList.get(0).get("WDR_ASEMBY_PRPORT_SM") != null)
				{
					body.append(Integer.parseInt(AsmbyAsembyList.get(0).get("WDR_ASEMBY_PRPORT_SM").toString()));
				}
				
				body.append("</span></td>");
				
				//광역의원 지역
				if(AsmbyAsembyList.get(0).get("WDR_ASEMBY_AREA_SM") != null)
				{
					body.append("			<td class='b'><span id='wdr_asemby_area_sm'>" + AsmbyAsembyList.get(0).get("WDR_ASEMBY_AREA_SM").toString() + "</span></td>");
				}
				else
				{
					body.append("			<td class='b'><span id='wdr_asemby_area_sm'>0</span></td>");
				}
				
				//광역의원 비례
				if(AsmbyAsembyList.get(0).get("WDR_ASEMBY_PRPORT_SM") != null)
				{
					body.append("			<td class='b'><span id='wdr_asemby_prport_sm'>" + AsmbyAsembyList.get(0).get("WDR_ASEMBY_PRPORT_SM").toString() + "</span></td>");
				}
				else
				{
					body.append("			<td class='b'><span id='wdr_asemby_prport_sm'>0</span></td>");
				}
				
				int asemby_cnt = 0;
				
				for(HashMap<String,Object> map : AsmbyAsembyList){
					
					//의원정수 합
					if(map.get("AREA_ASEMBY_SM") != null)
					{
						if(map.get("PRPORT_ASEMBY_SM") != null)
						{
							asemby_cnt += (Integer.parseInt(map.get("AREA_ASEMBY_SM").toString())+Integer.parseInt(map.get("PRPORT_ASEMBY_SM").toString()));
						}
						else
						{
							asemby_cnt += Integer.parseInt(map.get("AREA_ASEMBY_SM").toString());
						}
					}
					else if(map.get("PRPORT_ASEMBY_SM") != null)
					{
						asemby_cnt += Integer.parseInt(map.get("PRPORT_ASEMBY_SM").toString());
					}
					
					//정당별 지역
					if(map.get("PPRTY_CODE") != null)
					{
						if(map.get("AREA_ASEMBY_SM") != null)
						{
							body.append("			<td><input type='text' id='"+map.get("PPRTY_CODE").toString()+"' name='area_asemby_sm' style='width:50%;' value='" + map.get("AREA_ASEMBY_SM").toString() + "' onkeydown='checkNumber(event);' onkeyup='fn_auto_sum();' maxLength='4'></input></td>");
						}
						else
						{
							body.append("			<td><input type='text' id='"+map.get("PPRTY_CODE").toString()+"' name='area_asemby_sm' style='width:50%;' value='0' onkeydown='checkNumber(event);' onkeyup='fn_auto_sum();' maxLength='4'></input></td>");
						}
					}
					
					//정당별 비례
					if(map.get("PPRTY_CODE") != null)
					{
						if(map.get("PRPORT_ASEMBY_SM") != null)
						{
							body.append("			<td><input type='text' id='"+map.get("PPRTY_CODE").toString()+"' name='prport_asemby_sm' style='width:50%;' value='" + map.get("PRPORT_ASEMBY_SM").toString() + "' onkeydown='checkNumber(event);' onkeyup='fn_auto_sum();' maxLength='4'></input></td>");
						}
						else
						{
							body.append("			<td><input type='text' id='"+map.get("PPRTY_CODE").toString()+"' name='prport_asemby_sm' style='width:50%;' value='0' onkeydown='checkNumber(event);' onkeyup='fn_auto_sum();' maxLength='4'></input></td>");
						}
					}
				}
				
				//교육의원
				if(AsmbyAsembyList.get(0).get("EDC_ASEMBY_SM") != null)
				{
					body.append("			<td class='r'><input type='text' id='edc_asemby_sm' name='edc_asemby_sm' style='width:50%;' value='" + AsmbyAsembyList.get(0).get("EDC_ASEMBY_SM").toString() + "' onkeydown='checkNumber(event);' onkeyup='fn_auto_sum();' maxLength='4'></input></td>");
				}
				else
				{
					body.append("			<td class='r'><input type='text' id='edc_asemby_sm' name='edc_asemby_sm' style='width:50%;' value='0' onkeydown='checkNumber(event);' onkeyup='fn_auto_sum();' maxLength='4'></input></td>");
				}
				
				body.append("		</tr>");
				body.append("</tbody>");
			
				if(AsmbyAsembyList.get(0).get("EDC_ASEMBY_SM") != null)
				{
					asemby_cnt += Integer.parseInt(AsmbyAsembyList.get(0).get("EDC_ASEMBY_SM").toString());
				}
		    	
				String bodyStr = body.toString().replace("<span id='asemby_cnt'></span>", "<span id='asemby_cnt'>"+asemby_cnt+"</span>");
				model.addAttribute("head1", head1);
				model.addAttribute("body", bodyStr);
				
	    	}//의회의원
			
	    	
	    	/* 
	    	 * 
	    	 * 상임위원회 
	    	 * 
	    	 * */
	    	List<HashMap<String,Object>> PrmpstCmitList = StmManageService.selectPrmpstCmitList(param);
	    	sb.setLength(0);
	    	
	    	if(PrmpstCmitList.size() > 0){
		    	for(int index = 0; index < PrmpstCmitList.size(); index++){
		    		
		    		HashMap<String,Object> map = PrmpstCmitList.get(index);
		    	
		    		if(index < 1){
		    			int prmpst_cmit_co = 0;
		    			prmpst_cmit_co = map.get("PRMPST_CMIT_CO") != null ? Integer.parseInt(map.get("PRMPST_CMIT_CO").toString()) + 1 : 0;
		    			sb.append("<tr>");
		    			sb.append("	<td rowspan='" + prmpst_cmit_co + "'>" + map.get("RASMBLY_NM") + "(" + map.get("NUMPR") + "대) " + map.get("BEGIN_DE") + " ~ " + map.get("END_DE") +  " (" + map.get("HT_SE_STDCD_NM") + ")</td>");
		    			sb.append("	<td rowspan='" + prmpst_cmit_co + "'>" + map.get("ASEMBY_TOTQY") + "</td>");
		    			sb.append("	<td rowspan='" + prmpst_cmit_co + "'>" + map.get("PRMPST_CMIT_CO") + "</td>");
		    			sb.append("</tr>");
		    		}
		    			sb.append("<tr>");
		    			sb.append("	<td><input type='text' id='" + map.get("PRMPST_CMIT_ID") + "' name='prmpst_cmit_nm' value='" + map.get("PRMPST_CMIT_NM") + "' style='width:80%'></td>");
		    			sb.append("	<td><input type='text' id='" + map.get("PRMPST_CMIT_ID") + "' name='prmpst_asemby_cnt' style='width:90px;' value='" + map.get("PRMPST_ASEMBY_CO") + "' onkeydown='checkNumber(event);' maxLength='4'>");
		    			sb.append("	<a href='#none' class='btn btn-default btn-sm' onclick='fn_delete_prmpst_cmit(this);'>삭제</a></td>");
		    			sb.append("</tr>");
		    	}
		    	
		    	model.addAttribute("PrmpstCmitList", sb.toString());
	    	}
	    	
	    	
	    	/*
	    	 * 
	    	 * 의안통계
	    	 * 
	    	 * */
	    	List<HashMap<String,Object>> StatsBillList = StmManageService.selectStatsBillList(param);
	    	sb.setLength(0);
	    	
	    	if(StatsBillList.size() > 0){
	    		
	    	
		    	int[] totalCount = new int[8];
		    	
		    	for(int index = 0; index < StatsBillList.size(); index++){
		    		HashMap<String,Object> map = StatsBillList.get(index); 
			    	sb.append("<tr>");
					sb.append("	<th scope='col'>"+map.get("BI_KND_NM")+"</th>");
					sb.append("	<td scope='col' style='display:none;'><input id='sbd_"+(index+2)+"_0' name='sbd_"+(index+2)+"_0' value='"+map.get("STATS_BI_DTLS")+"' /></td>");
					
					sb.append("	<td scope='col'><span id='sbd_"+(index+2)+"_total'>"+map.get("STTUS1_CO")+"</span><input type='text' id='sbd_"+(index+2)+"_1' name='sbd_"+(index+2)+"_1' style='display:none;' value='"+map.get("STTUS1_CO")+"' /></td>");
					
					sb.append("	<td scope='col'><input type='text' id='sbd_"+(index+2)+"_2' name='sbd_"+(index+2)+"_2' style='width:50px;' value='"+map.get("STTUS2_CO")+"' onkeydown='checkNumber(event);' onkeyup='changeTotalCount(this);' maxLength='5'/></td>");
					sb.append("	<td scope='col'><input type='text' id='sbd_"+(index+2)+"_3' name='sbd_"+(index+2)+"_3' style='width:50px;' value='"+map.get("STTUS3_CO")+"' onkeydown='checkNumber(event);' onkeyup='changeTotalCount(this);' maxLength='5'/></td>");
					sb.append("	<td scope='col'><input type='text' id='sbd_"+(index+2)+"_4' name='sbd_"+(index+2)+"_4' style='width:50px;' value='"+map.get("STTUS4_CO")+"' onkeydown='checkNumber(event);' onkeyup='changeTotalCount(this);' maxLength='5'/></td>");
					sb.append("	<td scope='col'><input type='text' id='sbd_"+(index+2)+"_5' name='sbd_"+(index+2)+"_5' style='width:50px;' value='"+map.get("STTUS5_CO")+"' onkeydown='checkNumber(event);' onkeyup='changeTotalCount(this);' maxLength='5'/></td>");
					sb.append("	<td scope='col'><input type='text' id='sbd_"+(index+2)+"_6' name='sbd_"+(index+2)+"_6' style='width:50px;' value='"+map.get("STTUS6_CO")+"' onkeydown='checkNumber(event);' onkeyup='changeTotalCount(this);' maxLength='5'/></td>");
					sb.append("	<td scope='col'><input type='text' id='sbd_"+(index+2)+"_7' name='sbd_"+(index+2)+"_7' style='width:50px;' value='"+map.get("STTUS7_CO")+"' onkeydown='checkNumber(event);' onkeyup='changeTotalCount(this);' maxLength='5'/></td>");
					sb.append("	<td scope='col'><input type='text' id='sbd_"+(index+2)+"_8' name='sbd_"+(index+2)+"_8' style='width:50px;' value='"+map.get("STTUS8_CO")+"' onkeydown='checkNumber(event);' onkeyup='changeTotalCount(this);' maxLength='5'/></td>");
					sb.append("</tr>");
					
					totalCount[0] += Integer.parseInt(map.get("STTUS1_CO").toString());
					totalCount[1] += Integer.parseInt(map.get("STTUS2_CO").toString());
					totalCount[2] += Integer.parseInt(map.get("STTUS3_CO").toString());
					totalCount[3] += Integer.parseInt(map.get("STTUS4_CO").toString());
					totalCount[4] += Integer.parseInt(map.get("STTUS5_CO").toString());
					totalCount[5] += Integer.parseInt(map.get("STTUS6_CO").toString());
					totalCount[6] += Integer.parseInt(map.get("STTUS7_CO").toString());
					totalCount[7] += Integer.parseInt(map.get("STTUS8_CO").toString());
					
		    	}
		    	
		    	//총계
		    	String totalStr = "";
		    	totalStr += "<tr>";
				totalStr += "	<th scope='col'>총계</th>";
				totalStr += "	<td scope='col'><span id='sbd_1_1' />"+totalCount[0]+"</td>";
				totalStr += "	<td scope='col'><span id='sbd_1_2' />"+totalCount[1]+"</td>";
				totalStr += "	<td scope='col'><span id='sbd_1_3' />"+totalCount[2]+"</td>";
				totalStr += "	<td scope='col'><span id='sbd_1_4' />"+totalCount[3]+"</td>";
				totalStr += "	<td scope='col'><span id='sbd_1_5' />"+totalCount[4]+"</td>";
				totalStr += "	<td scope='col'><span id='sbd_1_6' />"+totalCount[5]+"</td>";
				totalStr += "	<td scope='col'><span id='sbd_1_7' />"+totalCount[6]+"</td>";
				totalStr += "	<td scope='col'><span id='sbd_1_8' />"+totalCount[7]+"</td>";
				totalStr += "</tr>";
		    	
		    	model.addAttribute("StatsBillList", totalStr + sb.toString());
	    	}
	    	
	    	
	    	/*
	    	 * 
	    	 * 회의록
	    	 * 
	    	 * */
	    	if(searchVO.getRASMBLY_NUMPR() != null && !"".equals(searchVO.getRASMBLY_NUMPR())){
	    		HashMap<String,Object> StatsMintsCnt = StmManageService.selectMinutesCnt(param);
		    	
	    		if(StatsMintsCnt != null)
	    			model.addAttribute("StatsMintsCnt", StatsMintsCnt.get("MINTS_CO"));
	    		else
	    			model.addAttribute("StatsMintsCnt", 0);
	    	}
    	}
    	
    	
    	model.addAttribute("searchVO", searchVO);
    	
        return "clikMng/sts/stm/StmInfoMngDetail";
    }
    
    /**
	 * 통계정보관리화면 - 지방의회 대수를 조회한다.
	 * @param 기수
	 * @return 지방의회 대수
	 * @exception Exception
	 */       
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/sts/stm/selectRasmblyNumpr.do")
    public void selectRasmblyNumpr(HttpServletRequest request , HttpServletResponse resp) throws Exception {
    	
    	JSONObject jObj = new JSONObject();
    	JSONArray jArr = new JSONArray();
    	
    	String[] paramList = null; 
    	if(request.getParameter("hrsmnpd_sn") != null){
    		
    		String hrsmnpd_sn = request.getParameter("hrsmnpd_sn");
    		
    		paramList = hrsmnpd_sn.split("@");
    		
    		HashMap<String, Object> param = new HashMap<String, Object>();
        	param.put("hrsmnpd_sn", paramList[0]);
        	param.put("ht_se_stdcd", paramList[1]);
        	param.put("rasmbly_id", CommonUtil.nvl(request.getParameter("rasmbly_id").toString(), ""));
        	
        	List<HashMap<String,Object>> list = StmManageService.selectRasmblyNumpr(param);

        	HashMap<String,Object> map = null;
        	for(int i = 0; i < list.size(); i++)
        	{
        		jObj = new JSONObject();
        		map = list.get(i);
        		jObj.put("rasmbly_id",map.get("RASMBLY_ID"));
        		jObj.put("rasmbly_numpr",map.get("RASMBLY_NUMPR"));
    			jArr.add(jObj);
        	}
    	}
    	
    	resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jArr.toString());    
    }
    
    /**
     * 통계정보관리 - 통계정보관리를 처리한다.
     * @param 
     * @return	
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/sts/stm/stmInfoMngProc.do")
    public void stmInfoMngProc(HttpServletRequest request , HttpServletResponse resp) throws Exception {
    	
    	JSONObject jso = new JSONObject();
    	JSONArray jarray = new JSONArray();
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	String rasmbly_id 		= CommonUtil.nvl(request.getParameter("rasmbly_id"), ""); 
    	String ht_se_stdcd 		= CommonUtil.nvl(request.getParameter("ht_se_stdcd"), "");
    	String hrsmnpd_sn 		= CommonUtil.nvl(request.getParameter("hrsmnpd_sn"), "");
    	String pprty_asemby 	= CommonUtil.nvl(request.getParameter("pprty_asemby"), "");
    	String prmpst_cmit 		= CommonUtil.nvl(request.getParameter("prmpst_cmit"), "");
    	String numpr 			= CommonUtil.nvl(request.getParameter("numpr"), "");
    	String bill_info 		= CommonUtil.nvl(request.getParameter("bill_info"), "");
    	
    	int asembyCo = 0; //의원 수
    	int rcepterBiCo = 0; //접수의안건수
    	int processBiCo = 0; //처리의안건수
    	int mrngBiCo = 0; //계류의안건수
    	
    	try{
	    	//의회의원정보
	    	StatsAsmblyAsemby statsAsmblyAsemby = new StatsAsmblyAsemby(); 
	    	statsAsmblyAsemby.setHrsmnpd_sn(hrsmnpd_sn);
	    	statsAsmblyAsemby.setRasmbly_id(rasmbly_id);
	    	statsAsmblyAsemby.setHt_se_stdcd(ht_se_stdcd);
	    	
	    	statsAsmblyAsemby.setNumpr(numpr);
	    	statsAsmblyAsemby.setHt_se_stdcd_nm(CommonUtil.nvl(request.getParameter("ht_se_stdcd_nm"), ""));
	    	statsAsmblyAsemby.setLoasm_ty_code(CommonUtil.nvl(request.getParameter("loasm_ty_code"), ""));
	    	statsAsmblyAsemby.setRasmbly_nm(CommonUtil.nvl(request.getParameter("rasmbly_nm"), ""));
	    	statsAsmblyAsemby.setBegin_de(CommonUtil.nvl(request.getParameter("begin_de"), "").replace(".", ""));
	    	statsAsmblyAsemby.setEnd_de(CommonUtil.nvl(request.getParameter("end_de"), "").replace(".", ""));
	    	statsAsmblyAsemby.setWdr_asemby_sm(CommonUtil.nvl(request.getParameter("wdr_asemby_sm"), ""));
	    	statsAsmblyAsemby.setAsemby_sm(CommonUtil.nvl(request.getParameter("asemby_sm"), ""));
	    	
	    	statsAsmblyAsemby.setWdr_asemby_area_sm(CommonUtil.nvl(request.getParameter("wdr_asemby_area_sm"), ""));
	    	statsAsmblyAsemby.setWdr_asemby_prport_sm(CommonUtil.nvl(request.getParameter("wdr_asemby_prport_sm"), ""));
	    	statsAsmblyAsemby.setEdc_asemby_sm(CommonUtil.nvl(request.getParameter("edc_asemby_sm"), ""));
	    	
	    	if(!"".equals(CommonUtil.nvl(request.getParameter("asemby_sm"), ""))){
	    		asembyCo += Integer.parseInt(CommonUtil.nvl(request.getParameter("asemby_sm"), ""));
	    	}
	    	
	    	if(!"".equals(CommonUtil.nvl(request.getParameter("edc_asemby_sm"), ""))){
	    		asembyCo += Integer.parseInt(CommonUtil.nvl(request.getParameter("edc_asemby_sm"), ""));
	    	}
	    	
	    	if(StmManageService.updateStatsAsmblyAsemby(statsAsmblyAsemby) == 0)
	    	{
	    		StmManageService.insertStatsAsmblyAsemby(statsAsmblyAsemby);
	    	}
	    	
	    	//의회의원정보상세
	    	StatsAsmblyAsembyDetail statsAsmblyAsembyDetail = null; 
	    	if(pprty_asemby != null && !"".equals(pprty_asemby)){
	    		String[] pprty_asemby_list = pprty_asemby.split(",");
	    		
	    		for(String str : pprty_asemby_list){
	    			String[] list = str.split("#");
	    			statsAsmblyAsembyDetail = new StatsAsmblyAsembyDetail();
	    			statsAsmblyAsembyDetail.setHrsmnpd_sn(hrsmnpd_sn);
	    			statsAsmblyAsembyDetail.setRasmbly_id(rasmbly_id);
	    			statsAsmblyAsembyDetail.setPprty_code(list[0]);
	    			statsAsmblyAsembyDetail.setHt_se_stdcd(ht_se_stdcd);

	    			statsAsmblyAsembyDetail.setArea_asemby_sm(list[1]);
	    			statsAsmblyAsembyDetail.setPrport_asemby_sm(list[2]);
	    			
	    			if(StmManageService.updateStatsAsmblyAsembyDetail(statsAsmblyAsembyDetail) == 0)
	    	    	{
	    				StmManageService.insertStatsAsmblyAsembyDetail(statsAsmblyAsembyDetail);
	    	    	}
	    		}
	    	}
	    	/////////// -의회의원정보
	    	
	    	//상임위원회정보
	    	String[] prmpst_cmit_list = null;
	    	if(prmpst_cmit != null && !"".equals(prmpst_cmit)){
	    		prmpst_cmit_list = prmpst_cmit.split(",");
	    		
	    		StatsPrmpstCmit statsPrmpstCmit = new StatsPrmpstCmit();
	    		statsPrmpstCmit.setHrsmnpd_sn(hrsmnpd_sn);
		    	statsPrmpstCmit.setRasmbly_id(rasmbly_id);
		    	statsPrmpstCmit.setHt_se_stdcd(ht_se_stdcd);

		    	statsPrmpstCmit.setHrsmnpd_nm(CommonUtil.nvl(request.getParameter("hrsmnpd_nm"), ""));
		    	statsPrmpstCmit.setHt_se_stdcd_nm(CommonUtil.nvl(request.getParameter("ht_se_stdcd_nm"), ""));
		    	statsPrmpstCmit.setRasmbly_nm(CommonUtil.nvl(request.getParameter("rasmbly_nm"), ""));
		    	statsPrmpstCmit.setNumpr(numpr);
		    	statsPrmpstCmit.setBegin_de(CommonUtil.nvl(request.getParameter("begin_de"), "").replace(".", ""));
		    	statsPrmpstCmit.setEnd_de(CommonUtil.nvl(request.getParameter("end_de"), "").replace(".", ""));
		    	statsPrmpstCmit.setPrmpst_cmit_co(String.valueOf(prmpst_cmit_list.length));
		    	statsPrmpstCmit.setAsemby_totqy(CommonUtil.nvl(request.getParameter("prmpst_cmit_co"), ""));
		    	
		    	if(StmManageService.updateStatsPrmpstCmit(statsPrmpstCmit) == 0)
		    	{
		    		StmManageService.insertStatsPrmpstCmit(statsPrmpstCmit);
		    	}
		    	
	    	}
	    	
	    	//상임위원회상세정보
	    	
	    	//기존 상임위원회상세 정보 삭제
	    	StatsPrmpstCmitDetail statsPrmpstCmitDetail = null;
    		statsPrmpstCmitDetail = new StatsPrmpstCmitDetail();
			statsPrmpstCmitDetail.setRasmbly_id(rasmbly_id);
			statsPrmpstCmitDetail.setHt_se_stdcd(ht_se_stdcd);
			statsPrmpstCmitDetail.setHrsmnpd_sn(hrsmnpd_sn);
			StmManageService.deleteStatsPrmpstCmitDetail(statsPrmpstCmitDetail);
    		
	    	if(prmpst_cmit != null && !"".equals(prmpst_cmit)){
	    		for(String str : prmpst_cmit_list){
	    			String[] list = str.split("#");
	    			statsPrmpstCmitDetail = new StatsPrmpstCmitDetail();
	    			statsPrmpstCmitDetail.setHrsmnpd_sn(hrsmnpd_sn);
	    			statsPrmpstCmitDetail.setPrmpst_cmit_id(String.valueOf(prmpstcmitIdGnrService.getNextIntegerId()));
	    			statsPrmpstCmitDetail.setRasmbly_id(rasmbly_id);
	    			statsPrmpstCmitDetail.setHt_se_stdcd(ht_se_stdcd);
	    			statsPrmpstCmitDetail.setPrmpst_cmit_nm(list[1]);
	    			statsPrmpstCmitDetail.setPrmpst_asemby_co(list[2]);
	    			StmManageService.insertStatsPrmpstCmitDetail(statsPrmpstCmitDetail);
	    		}
	    	}
	    	/////////// -상임위원회상세정보
	    	
	    	
	    	//의안통계정보
	    	String[] bi_knd_nm = {"조례안","의견청취안","기타","청원","예산/결산안","동의/승인안","건의/결의안","규칙/규정안"}; 
	    	HashMap<String,Object> paramMap = new HashMap<String, Object>();
	    	paramMap.put("HRSMNPD_SN", hrsmnpd_sn);
	    	paramMap.put("RASMBLY_ID", rasmbly_id);
	    	paramMap.put("HT_SE_STDCD", ht_se_stdcd);
	    	
	    	paramMap.put("NUMPR", numpr);
	    	paramMap.put("HRSMNPD_NM", CommonUtil.nvl(request.getParameter("hrsmnpd_nm"), ""));
	    	paramMap.put("HT_SE_STDCD_NM", CommonUtil.nvl(request.getParameter("ht_se_stdcd_nm"), ""));
	    	paramMap.put("RASMBLY_NM", CommonUtil.nvl(request.getParameter("rasmbly_nm"), ""));
	    	
	    	paramMap.put("FRST_REGISTER_ID", user.getMngrNm());
	    	
	    	if(StmManageService.updateStatsBill(paramMap) == 0)
	    	{
	    		StmManageService.insertStatsBill(paramMap);
	    	}
	    	
	    	if(bill_info != null && !"".equals(bill_info)){
	    		String[] bill_info_list = bill_info.split(",");
	    		
	    		for(int i = 0, j = 1; i < bill_info_list.length; i++,j=1){
	    			String[] list = bill_info_list[i].split("#");
	    			paramMap.put("BI_KND_NM", bi_knd_nm[i]);
	    			paramMap.put("STATS_BI_DTLS", list[0]);
	    			paramMap.put("STTUS"+j+"_CO", list[j++]);
	    			paramMap.put("STTUS"+j+"_CO", list[j++]);
	    			paramMap.put("STTUS"+j+"_CO", list[j++]);
	    			paramMap.put("STTUS"+j+"_CO", list[j++]);
	    			paramMap.put("STTUS"+j+"_CO", list[j++]);
	    			paramMap.put("STTUS"+j+"_CO", list[j++]);
	    			paramMap.put("STTUS"+j+"_CO", list[j++]);
	    			paramMap.put("STTUS"+j+"_CO", list[j++]);
	    			
	    			int temp1 = 0;
	    			int temp2 = 0;
	    			
	    			if(!"".equals(CommonUtil.nvl(list[1],""))){
	    				temp1 = Integer.parseInt(list[1]); //처리의안건수
	    				processBiCo += temp1; 
	    			}
	    			if(!"".equals(CommonUtil.nvl(list[8],""))){
	    				temp2 = Integer.parseInt(list[8]); //계류의안건수
	    				mrngBiCo += temp2;
	    			}
	    			
	    			rcepterBiCo += temp1 + temp2; //접수의안건수
	    			
	    			if(!"".equals(list[0]) && StmManageService.updateStatsBillDetail(paramMap) == 0)
	    	    	{
	    				paramMap.put("STATS_BI_DTLS", billDetailIdGnrService.getNextIntegerId());
	    				StmManageService.insertStatsBillDetail(paramMap);
	    	    	}
	    			
	    		}
	    	}
	    	/////////// -의안통계정보
	    	
	    	
	    	//회의록 통계정보
	    	paramMap.clear();
	    	paramMap.put("HRSMNPD_SN", hrsmnpd_sn);
	    	paramMap.put("RASMBLY_ID", rasmbly_id);
	    	paramMap.put("HT_SE_STDCD", ht_se_stdcd);
	    	
	    	paramMap.put("NUMPR", numpr);
	    	paramMap.put("HRSMNPD_NM", CommonUtil.nvl(request.getParameter("hrsmnpd_nm"), ""));
	    	paramMap.put("HT_SE_STDCD_NM", CommonUtil.nvl(request.getParameter("ht_se_stdcd_nm"), ""));
	    	paramMap.put("RASMBLY_NM", CommonUtil.nvl(request.getParameter("rasmbly_nm"), ""));
	    	paramMap.put("MINTS_CO", CommonUtil.nvl(request.getParameter("mints_co"), ""));
	    	
	    	//의원 수
	    	paramMap.put("ASEMBY_CO", asembyCo);
	    	//상임위원회 수
	    	paramMap.put("CMIT_CO", prmpst_cmit_list != null ? prmpst_cmit_list.length : 0);
	    	//접수의안 수
	    	paramMap.put("RCEPTER_BI_CO", rcepterBiCo);
	    	//처리의안 수
	    	paramMap.put("PROCESS_BI_CO", processBiCo);
	    	//계류의안 수
	    	paramMap.put("MRNG_BI_CO", mrngBiCo);
	    	
	    	if(StmManageService.updateStatsMints(paramMap) == 0)
	    	{
	    		StmManageService.insertStatsMints(paramMap);
	    	}
	    	
	    	jso.put("result_code","success");
	    	jso.put("result_msg","정상 처리 되었습니다.");
	    	
    	}catch(Exception e){
    		e.printStackTrace();
    		jso.put("result_code","fail");
    		jso.put("result_msg","처리중 오류가 발생하였습니다.");
    	}
		
    	jarray.add(jso);
    	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());    
    }
    
    /**
     * 통계정보관리 - 통계정보관리를 삭제한다.
     * @param 
     * @return	
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/sts/stm/deleteStmInfo.do")
    public void deleteStmInfo(HttpServletRequest request , HttpServletResponse resp) throws Exception {
    	
    	JSONObject jso = new JSONObject();
    	JSONArray jarray = new JSONArray();
    	HashMap<String,Object> map = new HashMap<String, Object>();
    	
    	String param = request.getParameter("data_list");
    	String[] dataList = param.split(",");
    	
    	
    	try{
	    	for(String data : dataList){
	    		map.clear();
	    		String[] values = data.split("@");
	    		
	    		if(values.length >= 1)
	    		map.put("RASMBLY_ID", values[0]);
	    		if(values.length >= 2)
	    		map.put("RASMBLY_NUMPR", values[1]);
	    		if(values.length >= 3)
	    		map.put("HRSMNPD_SN", values[2]);
	    		if(values.length >= 4)
	    		map.put("HT_SE_STDCD", values[3]);
	    		
	    		//상임위원회
	    		StmManageService.deleteStmInfoScommitDetail(map);
	    		StmManageService.deleteStmInfoScommit(map);
	    		//의원
	    		StmManageService.deleteStmInfoLamanDetail(map);
	    		StmManageService.deleteStmInfoLaman(map);
	    		//의안
	    		StmManageService.deleteStmInfoBillDetail(map);
	    		StmManageService.deleteStmInfoBill(map);
	    		//회의록
	    		StmManageService.deleteStmInfoMints(map);
	    		
	    	}
	    	jso.put("result_code", "success");
			jso.put("result_msg", "정상처리되었습니다.");
    	}catch(Exception e){
    		jso.put("result_code", "fail");
    		jso.put("result_msg", e.getMessage());
    	}
		
		jarray.add(jso);
    	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());    
    }
    
    /**
     * 통계관리 - 통계정보관리 엑셀 다운로드
     * @param searchVO
     * @param model
     * @return	"/Stm/StmMngList"
     * @throws Exception
     */
    @IncludedInfo(name="통계정보관리 엑셀 다운로드", order = 680 ,gid = 50)
    @RequestMapping(value="/sts/stm/StmInfoMngExcel.do")
    public ModelAndView StmInfoMngExcel(@ModelAttribute("StmInfoMngVO") StmInfoMngVO searchVO, ModelMap model) throws Exception {

    	searchVO.setFirstIndex(0);
		searchVO.setLastIndex(Integer.MAX_VALUE);
    	
		List<StmInfoMngVO> list = StmManageService.selectStmInfoMngList(searchVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", searchVO);
    	map.put("resultList", list);
    	map.put("resultCnt", list.get(0).getTOTCNT());

        return new ModelAndView("StmInfoMngExcel", map);
    }
}
