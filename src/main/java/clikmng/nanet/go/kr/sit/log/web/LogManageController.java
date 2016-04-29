package clikmng.nanet.go.kr.sit.log.web;

import java.io.PrintWriter;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.sit.log.service.LogManageDefaultVO;
import clikmng.nanet.go.kr.sit.log.service.LogManageService;
import clikmng.nanet.go.kr.sit.log.service.LogManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 로그관리를 처리하는 Controller 클래스
 */

@Controller
public class LogManageController {




	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "LogManageService")
    private LogManageService logManageService;

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

    /**
     * 접속로그관리목록을 조회한다.
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @IncludedInfo(name="접속로그관리", order = 680 ,gid = 50)
    @RequestMapping(value="/sit/log/LogMngList.do")
    public String selectLogList(@ModelAttribute("searchVO") LogManageVO searchVO, Map commandMap, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

		if ( searchVO.getSchDt1() != null ) searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		if ( searchVO.getSchDt2() != null ) searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));
    	
    	/** EgovPropertyService.LogMngList */
		if(searchVO.getPageUnit() == 0)
		{
			searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		}
		if(searchVO.getPageSize() == 0)
		{
			searchVO.setPageSize(propertiesService.getInt("pageSize"));
		}
		
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		
        if(searchVO.getSelectCountPg() != 0) {
        	searchVO.setRecordCountPerPage(searchVO.getSelectCountPg());
        } else {
        	searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        }

		HashMap _map = (HashMap)logManageService.selectConnectLogListInfo(searchVO);
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));
		
		model.addAttribute("resultList", _map.get("resultList"));
		model.addAttribute("resultCnt", _map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("totCnt", totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);		
		model.addAttribute("selectCountperpg", searchVO.getSelectCountPg());
		
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

		
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("selSearchGubun", commandMap.get("selSearchGubun") == null ? "" : (String) commandMap.get("selSearchGubun"));

        return "clikMng/sit/log/LogMngList";
    }
    
    /**
     * 웹로그목록을 조회한다.
     * @param searchVO
     * @param model
     * @return	"/sit/log/WebLogMngList"
     * @throws Exception
     */
    @IncludedInfo(name="웹로그목록", order = 680 ,gid = 50)
    @RequestMapping(value="/sit/log/WebLogList.do")
    public String selectWebLogList(@ModelAttribute("searchVO") LogManageVO searchVO, Map commandMap, ModelMap model) throws Exception {

    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
		if ( searchVO.getSchDt1() != null ) searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		if ( searchVO.getSchDt2() != null ) searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));
    	
    	
    	/** EgovPropertyService.LogMngList */
		if(searchVO.getPageUnit() == 0)
		{
			searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		}
		if(searchVO.getPageSize() == 0)
		{
			searchVO.setPageSize(propertiesService.getInt("pageSize"));
		}

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		
        if(searchVO.getSelectCountPg() != 0) {
        	searchVO.setRecordCountPerPage(searchVO.getSelectCountPg());
        } else {
        	searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        }
        
		HashMap _map = (HashMap)logManageService.selectWebLogListInfo(searchVO);
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));
		
		model.addAttribute("resultList", _map.get("resultList"));
		model.addAttribute("resultCnt", _map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("totCnt", totCnt);		
		model.addAttribute("paginationInfo", paginationInfo);		
		model.addAttribute("selectCountperpg", searchVO.getSelectCountPg());
		
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
        
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));

        return "clikMng/sit/log/WebLogList";
    }    

    
    
    
    
    /**
     * 접속로그 엑셀 출력
     * @param searchVO
     * @param model
     * @return	"/sit/log/LogMngList"
     * @throws Exception
     */
    @IncludedInfo(name="접속로그관리", order = 680 ,gid = 50)
    @RequestMapping(value="/sit/log/LogMngExcel.do")
    public ModelAndView selectLogExcel(@ModelAttribute("searchVO") LogManageVO searchVO, Map commandMap, ModelMap model) throws Exception {
    	
		if ( searchVO.getSchDt1() != null ) searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		if ( searchVO.getSchDt2() != null ) searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));

		searchVO.setSearchCondition("EXCEL");
		
		HashMap _map = (HashMap)logManageService.selectConnectLogListInfo(searchVO);
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));
		

		
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
        map.put("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        map.put("selSearchGubun", commandMap.get("selSearchGubun") == null ? "" : (String) commandMap.get("selSearchGubun"));
        
    	map.put("resultList", _map.get("resultList"));
    	map.put("resultCnt", _map.get("resultCnt"));
    	
    	map.put("totCnt", totCnt);		

        return new ModelAndView("LogMngExcel", map);
    }
    
    /**
     * 웹로그 엑셀 출력
     * @param searchVO
     * @param model
     * @return	"/sit/log/WebLogMngList"
     * @throws Exception
     */
    @IncludedInfo(name="웹로그목록", order = 680 ,gid = 50)
    @RequestMapping(value="/sit/log/WebLogExcel.do")
    public ModelAndView selectWebLogExcel(@ModelAttribute("searchVO") LogManageVO searchVO, Map commandMap, ModelMap model) throws Exception {

		if ( searchVO.getSchDt1() != null ) searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		if ( searchVO.getSchDt2() != null ) searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));
        
		searchVO.setSearchCondition("EXCEL");
		
		HashMap _map = (HashMap)logManageService.selectWebLogListInfo(searchVO);
		
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));
		
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
    	map.put("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        
    	map.put("resultList", _map.get("resultList"));
    	map.put("resultCnt", _map.get("resultCnt"));
    	
    	map.put("totCnt", totCnt);		

    	return new ModelAndView("WebLogExcel", map);
    }     
}
