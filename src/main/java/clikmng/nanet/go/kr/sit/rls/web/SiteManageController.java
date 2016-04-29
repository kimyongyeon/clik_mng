package clikmng.nanet.go.kr.sit.rls.web;

import java.io.PrintWriter;
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
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.ComDefaultCodeVO;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.sit.rls.service.SiteManageDefaultVO;
import clikmng.nanet.go.kr.sit.rls.service.SiteManageService;
import clikmng.nanet.go.kr.sit.rls.service.SiteManageVO;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *
 * 사이트정보를 처리하는 Controller 클래스
 * @author 
 * @since 
 * @version 
 * @see
 */

@Controller
public class SiteManageController {




	protected Log log = LogFactory.getLog(this.getClass());

    @Resource(name = "SiteManageService")
    private SiteManageService siteManageService;

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
     * 사이트목록을 조회한다.
     * @param searchVO
     * @param model
     * @return	"/sit/rls/EgovSiteListInqire"
     * @throws Exception
     */
    @IncludedInfo(name="사이트관리", order = 680 ,gid = 50)
    @RequestMapping(value="/sit/rls/SiteListInqire.do")
    public String selectSiteList(@ModelAttribute("searchVO") SiteManageVO searchVO, Map commandMap, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	/** EgovPropertyService.SiteList */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List SiteList = siteManageService.selectSiteList(searchVO);
        model.addAttribute("resultList", SiteList);

        //int totCnt = siteManageService.selectSiteListTotCnt(searchVO);
		//paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        // 카테고리 코드
    	// Query에서 공통코드 RSC 입력, 첫 페이지 로딩시 카레고리 값은 CT01 : 지방의회
    	List categoryList = siteManageService.selectClCode(searchVO);
    	model.addAttribute("categoryList", categoryList);
    	
    	// 해당 상세코드를 가져옴(지역명 가져올 때 query에 codeId : RKS002 하드코딩)
//    	
//    	List<SiteManageVO> localList = siteManageService.selectClDetailCode(searchVO);
//    	model.addAttribute("localList", localList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        // 카테고리 초기 페이지에 값이 없으면 지방의회 코드 하드코딩(CT01)
        model.addAttribute("searchCondition1", commandMap.get("searchCondition1") == null ? "CT01" : (String) commandMap.get("searchCondition1"));
//        model.addAttribute("searchCondition2", commandMap.get("searchCondition2") == null ? "" : (String) commandMap.get("searchCondition2"));
        model.addAttribute("searchCondition3", commandMap.get("searchCondition3") == null ? "" : (String) commandMap.get("searchCondition3"));
        
        return "clikMng/sit/rls/SiteListInqire";
    }

   /**
    * 사이트정보 목록에 대한 상세정보를 조회한다.
    * @param siteManageVO
    * @param searchVO
    * @param model
    * @return	"/sit/rls/EgovSiteDetailInqire"
    * @throws Exception
    */
/*    
    @RequestMapping("/sit/rls/SiteDetailInqire.do")
    public String	selectSiteDetail(SiteManageVO siteManageVO,
            @ModelAttribute("searchVO") SiteManageDefaultVO searchVO,
            ModelMap model) throws Exception {

    	// 공통코드를 가져옴
    	List _result = siteManageService.selectClCode(siteManageVO);
    	
    	// 해당 상세코드를 가져옴.
    	List<SiteManageVO> detailResult = siteManageService.selectClDetailCode(siteManageVO);
    	
		SiteManageVO vo = siteManageService.selectSiteDetail(siteManageVO);

		model.addAttribute("result", vo);
		model.addAttribute("codeList", _result);
		model.addAttribute("detailCodeList", detailResult);

        return	"clikMng/sit/rls/EgovSiteDetailInqire";
    }
*/
    /**
     * 사이트정보 등록전 단계
     * @param searchVO
     * @param model
     * @return	"/sit/rls/SiteInfoRegist"
     * @throws Exception
     */
    @RequestMapping("/sit/rls/SiteInfoRegistView.do")
    public String insertSiteInfoView(
            @ModelAttribute("searchVO") SiteManageVO searchVO, Model model)
            throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "clikMng/uat/uia/LoginUsr";
    	}
    	
    	// 관련사이트 코드를 조회.
    	List _result = siteManageService.selectClCode(searchVO);
    	
    	model.addAttribute("resultList", _result);
    	
    	model.addAttribute("siteManageVO", new SiteManageVO());

        return "clikMng/sit/rls/SiteInfoRegist";

    }

   /**
    * 사이트정보를 등록한다.
    * @param searchVO
    * @param siteManageVO
    * @param bindingResult
    * @return	"forward:/sit/rls/SiteListInqire.do"
    * @throws Exception
    */
    @RequestMapping("/sit/rls/SiteInfoRegist.do")
    public String insertSiteInfo(
            @ModelAttribute("searchVO") SiteManageDefaultVO searchVO,
            @ModelAttribute("siteManageVO") SiteManageVO siteManageVO,
            BindingResult bindingResult,
            Model model)
            throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "clikMng/uat/uia/LoginUsr";
    	}
    	
    	beanValidator.validate(siteManageVO, bindingResult);

		if(bindingResult.hasErrors()){

			return "clikMng/sit/rls/SiteInfoRegist";

		}

    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	String frstRegisterId = loginVO.getMngrId();

    	siteManageVO.setFrstRegisterId(frstRegisterId);		// 최초등록자ID
    	siteManageVO.setLastUpdusrId(frstRegisterId);    	// 최종수정자ID
    	
        siteManageService.insertSiteInfo(siteManageVO);

        return "forward:/sit/rls/SiteListInqire.do";
    }

    /**
     * 사이트정보 수정 전 처리
     * @param siteId
     * @param searchVO
     * @param model
     * @return	"/sit/rls/SiteInfoUpdt"
     * @throws Exception
     */
    @RequestMapping("/sit/rls/SiteInfoUpdtView.do")
    public String updateSiteInfoView(@RequestParam("siteId") String siteId , SiteManageVO siteManageVO,
            @ModelAttribute("searchVO") SiteManageDefaultVO searchVO, ModelMap model)
            throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "clikMng/uat/uia/LoginUsr";
    	}
    	
    	// 관련사이트 코드 조회.
    	List _result = siteManageService.selectClCode(siteManageVO);
    	
    	// 관련 사이트 상세 코드 조회
    	List<SiteManageVO> detailResult = siteManageService.selectClDetailCode(siteManageVO);
    	
		SiteManageVO vo = siteManageService.selectSiteDetail(siteManageVO);

		model.addAttribute("result", vo);
		model.addAttribute("codeList", _result);
		model.addAttribute("detailCodeList", detailResult);


        return "clikMng/sit/rls/SiteInfoUpdt";
    }

    /**
     * 사이트정보를 수정한다.
     * @param searchVO
     * @param siteManageVO
     * @param bindingResult
     * @return	"forward:/sit/rls/SiteListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/sit/rls/SiteInfoUpdt.do")
    public String updateSiteInfo(
            @ModelAttribute("searchVO") SiteManageDefaultVO searchVO,
            @ModelAttribute("siteManageVO") SiteManageVO siteManageVO,
            BindingResult bindingResult,
            ModelMap model)
            throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "clikMng/uat/uia/LoginUsr";
    	}
    	
    	// Validation
    	beanValidator.validate(siteManageVO, bindingResult);

		if(bindingResult.hasErrors()){

			return "clikMng/sit/rls/SiteInfoUpdt";

		}

    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	String	lastUpdusrId = loginVO.getMngrId();
    	siteManageVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID

    	siteManageService.updateSiteInfo(siteManageVO);

        return "forward:/sit/rls/SiteListInqire.do";

    }

    /**
     * 사이트정보를 삭제처리한다.
     * @param siteManageVO
     * @param searchVO
     * @return	"forward:/sit/rls/SiteListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/sit/rls/SiteInfoDelete.do")
    public String deleteSiteInfo(
            SiteManageVO siteManageVO,
            @ModelAttribute("searchVO") SiteManageDefaultVO searchVO
            , ModelMap model)
            throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "clikMng/uat/uia/LoginUsr";
    	}
    	
    	
    	siteManageService.deleteSiteInfo(siteManageVO);

        return "forward:/sit/rls/SiteListInqire.do";
    }
    
    
    
    /**
     * ajax 상세코드 불러오기
     * @param searchVO
     * @param model
     * @return	"/sit/rls/EgovSiteInfoRegist"
     * @throws Exception
     */
    @RequestMapping("/sit/rls/AjaxSiteDetailCode.do")
    public void selectAjaxSiteDetailCode(
    		@ModelAttribute("siteManageVO") SiteManageVO siteManageVO
    		, HttpServletResponse resp
    		, HttpServletRequest request)
            throws Exception {


    	List<SiteManageVO> result = siteManageService.selectClDetailCode(siteManageVO);
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	
    	for(int i=0; i<result.size(); i++) {
    		SiteManageVO vo = new SiteManageVO(); 
    		vo = result.get(i);
    		jso = new JSONObject();
    		jso.put("codeId", vo.getCodeId());
    		jso.put("code", vo.getCode());
    		jso.put("codeNm", vo.getCodeNm());
    		jso.put("codeOrdr", vo.getCodeOrdr());
    		
    		jarray.add(i, jso);
    	}
    	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());    
    }
    
   
    /**
     * 사이트 순서를 수정한다.
     * @param searchVO
     * @param siteManageVO
     * @param bindingResult
     * @return	"forward:/sit/rls/SiteOrdrUpdt.do"
     * @throws Exception
     */
    @RequestMapping(" /sit/rls/SiteOrdrUpdt.do")
    public String updateSiteOrdr(
            @ModelAttribute("searchVO") SiteManageVO siteManageVO,
            BindingResult bindingResult, ModelMap model)
            throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "clikMng/uat/uia/LoginUsr";
    	}
    	
		//-- 사용자 관련 개발 끝나면 다시 개발
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String lastUpdusrId = loginVO.getMngrId();
    	siteManageVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID

    	
    	String strSiteOrders = StringUtil.nullConvert(siteManageVO.getSiteOrdrChg());
    	
		if(strSiteOrders.length() > 1 && strSiteOrders.lastIndexOf(",") > 0){
			String[] arrSiteOrders = strSiteOrders.split(",");
			for(int i=0;i<arrSiteOrders.length;i++){
				String[] arrInfo = arrSiteOrders[i].split("=");				
				siteManageVO.setSiteId(arrInfo[0]);
				siteManageVO.setSeOrdr(arrInfo[1]);
				siteManageService.updateSiteOrdrEdit(siteManageVO);
			}
		}

        return "forward:/sit/rls/SiteListInqire.do";

    }    	
    	
}
