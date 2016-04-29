package clikmng.nanet.go.kr.csm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.EgovProperties;
import clikmng.nanet.go.kr.cmm.util.CalUtil;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.csm.service.CsmManageDefaultVO;
import clikmng.nanet.go.kr.csm.service.CsmManageService;
import clikmng.nanet.go.kr.csm.service.CsmManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 메일링 관리를 처리하는 Controller 클래스
 */

@Controller
public class CsmManageController {

	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "CsmManageService")
    private CsmManageService csmManageService;

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
     * 수집 / 검색엔진 관리
     * @param searchVO
     * @param model
     * @return	"/csm/CsmInqire"
     * @throws Exception
     */
    @IncludedInfo(name="수집/검색엔진 관리", order = 680 ,gid = 50)
    @RequestMapping(value="/csm/CsmInqire.do")
    public String csmInquire(@ModelAttribute("searchVO") CsmManageDefaultVO searchVO, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}


    	model.addAttribute("collectionUrl", EgovProperties.getProperty("collection.url"));
    	model.addAttribute("searchUrl", EgovProperties.getProperty("search.url"));
    	model.addAttribute("issueUrl", EgovProperties.getProperty("issue.url"));
    	
        return "clikMng/csm/CsmInqire";
    }
    
    /**
     * 기관별 수집 내역
     * @param searchVO
     * @param model
     * @return	"/csm/CemSystem"
     * @throws Exception
     */
    @IncludedInfo(name="기관별 수집 내역", order = 680 ,gid = 50)
    @RequestMapping(value="/csm/CemSystem.do")
    public String selectCollectionEngineMngSystem(@ModelAttribute("searchVO") CsmManageVO searchVO
    																	, ModelMap model
    																	, Map commandMap) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
		CalUtil calUtil = new CalUtil();
		calUtil.setDelimiter("");
		calUtil.setDecimalFormat("00");
		String beforeWeek 	= calUtil.getBeforeWeek();
		String today 		= calUtil.getToday();
		
		//기간조건
		if ( searchVO.getSchDt1() != null ) 
			searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		else
			searchVO.setSchDt1(beforeWeek);
		
		if ( searchVO.getSchDt2() != null ) 
			searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));
		else
			searchVO.setSchDt2(today);
    	
		//정렬조건
		if(searchVO.getSortOrder() == null || "".equals(searchVO.getSortOrder()))
			searchVO.setSortOrder("siteNm_ASC");

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
    	// 기관 유형 목록
    	List<CsmManageVO> orgTypeList = csmManageService.selectOrgTypeList();
    	model.addAttribute("orgTypeList", orgTypeList);

    	// 수집기관 유형 목록
    	List<CsmManageVO> collectionOrgList = csmManageService.selectCollectionOrgList();
    	
    	
    	model.addAttribute("collectionOrgList", collectionOrgList);
    		
    	// 기관별 수집내역 목록
    	List<CsmManageVO> orgCollectionList = csmManageService.selectCsmList(searchVO);

        int totCnt = (Integer) csmManageService.selectCsmListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);    	    	
    	
    	
    	model.addAttribute("orgCollectionList", orgCollectionList);
    	
    	int totalSum = 0;
    	
    	CsmManageVO vo = new CsmManageVO();
    	for(int i=0; i<orgCollectionList.size(); i++) 
    	{
    		vo = (CsmManageVO)orgCollectionList.get(i);
    		totalSum += vo.getCollectionCnt();
    	}
        
        model.addAttribute("totalSum", totalSum);
        
		
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
        model.addAttribute("searchCondition1", commandMap.get("searchCondition1") == null ? "" : (String) commandMap.get("searchCondition1"));
        model.addAttribute("searchCondition2", commandMap.get("searchCondition2") == null ? "" : (String) commandMap.get("searchCondition2"));
        
        return "clikMng/csm/CemSystem";
    }
    
    /**
     * 기관별 수집 내역
     * @param searchVO
     * @param model
     * @return	"/csm/CemSystem"
     * @throws Exception
     */
    @IncludedInfo(name="기관별 수집 내역", order = 680 ,gid = 50)
    @RequestMapping(value="/csm/CemSystemExcel.do")
    public Object CemSystemExcel(@ModelAttribute("searchVO") CsmManageVO searchVO
    																	, ModelMap model
    																	, Map commandMap) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
		CalUtil calUtil = new CalUtil();
		calUtil.setDelimiter("");
		calUtil.setDecimalFormat("00");
		String beforeWeek 	= calUtil.getBeforeWeek();
		String today 		= calUtil.getToday();
		
		//기간조건
		if ( searchVO.getSchDt1() != null ) 
			searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		else
			searchVO.setSchDt1(beforeWeek);
		
		if ( searchVO.getSchDt2() != null ) 
			searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));
		else
			searchVO.setSchDt2(today);
    	
		//정렬조건
		if(searchVO.getSortOrder() == null || "".equals(searchVO.getSortOrder()))
			searchVO.setSortOrder("siteNm_ASC");
    		
		int totCnt = (Integer) csmManageService.selectCsmListTotCnt(searchVO);
		
		searchVO.setFirstIndex(0);
		searchVO.setRecordCountPerPage(totCnt);
		
		// 기관별 수집내역 목록
    	List<CsmManageVO> orgCollectionList = csmManageService.selectCsmList(searchVO);
    	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVO", searchVO);
		map.put("resultCnt", totCnt);
    	map.put("resultList", orgCollectionList);

    	return new ModelAndView("CemSystemExcel", map);
    }
    
    /**
     * 수집대비 서비스 내역
     * @param searchVO
     * @param model
     * @return	"/csm/SemSystem"
     * @throws Exception
     */
    @IncludedInfo(name="수집대비 서비스 내역", order = 680 ,gid = 50)
    @RequestMapping(value="/csm/SemSystem.do")
    public String selectSearchEngineMngSystem(@ModelAttribute("searchVO") CsmManageVO searchVO
    																, ModelMap model
    																, Map commandMap) throws Exception {
    	

    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		CalUtil calUtil = new CalUtil();
		calUtil.setDelimiter("");
		calUtil.setDecimalFormat("00");
		String beforeWeek 	= calUtil.getBeforeWeek();
		String today 		= calUtil.getToday();
		
		//기간조건
		if ( searchVO.getSchDt1() != null ) 
			searchVO.setSchDt1(searchVO.getSchDt1().replaceAll("-",""));
		else
			searchVO.setSchDt1(beforeWeek);
		
		if ( searchVO.getSchDt2() != null ) 
			searchVO.setSchDt2(searchVO.getSchDt2().replaceAll("-",""));
		else
			searchVO.setSchDt2(today);
		
    	// 수집카테고리 목록
    	List<CsmManageVO> categoryList = csmManageService.selectCategoryList();
    	model.addAttribute("categoryList", categoryList);

    	// 기관별 수집내역 목록
    	List<CsmManageVO> collectionServiceList = csmManageService.selectCollectionServiceList(searchVO);
    	model.addAttribute("collectionServiceList", collectionServiceList);
    	
    	int totalColSum = 0;
    	int totalSvnSum = 0;
    	
    	CsmManageVO vo = new CsmManageVO();
    	for(int i=0; i<collectionServiceList.size(); i++) {
    		vo = (CsmManageVO)collectionServiceList.get(i);
    		//if(vo.getColCnt() == null) vo.setColCnt(0);  
    		//if(vo.getSvcCnt() == null) vo.setSvcCnt(0);
    		totalColSum += vo.getColCnt();
    		totalSvnSum += vo.getSvcCnt();
    	}
        int totCnt = (Integer) collectionServiceList.size();
        paginationInfo.setTotalRecordCount(totCnt);
        
        
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
        
        model.addAttribute("paginationInfo", paginationInfo);    	    	
    	
    	
    	System.out.println("##### totCnt ===> " + totalColSum);
    	System.out.println("##### svcCnt ===> " + totalSvnSum);
    	
        model.addAttribute("totalColSum", totalColSum);
        model.addAttribute("totalSvnSum", totalSvnSum);
    	
    	model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));
        
        model.addAttribute("ntceBgndeYYYMMDD", commandMap.get("ntceBgndeYYYMMDD") == null ? "" : (String) commandMap.get("ntceBgndeYYYMMDD"));
        model.addAttribute("ntceEnddeYYYMMDD", commandMap.get("ntceEnddeYYYMMDD") == null ? "" : (String) commandMap.get("ntceEnddeYYYMMDD"));
        
        System.out.println("##########################################################");
        
        return "clikMng/csm/SemSystem";
    }    

}
