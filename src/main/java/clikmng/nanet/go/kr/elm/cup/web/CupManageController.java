package clikmng.nanet.go.kr.elm.cup.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeManageService;
import clikmng.nanet.go.kr.ccm.ccd.service.CmmCodeVO;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngService;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngUtil;
import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.elm.com.CopyrightPermVO;
import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.elm.cup.service.CupManageService;
import clikmng.nanet.go.kr.elm.cup.service.ElmCupListVO;
//import clikmng.nanet.go.kr.elm.cup.service.ElmCupDetailVO;
import clikmng.nanet.go.kr.elm.gup.service.GupManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
* 저작권허락 권한 처리하는 Controller 클래스
*/

@Controller
public class CupManageController {

	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "CupManageService")
	private CupManageService cupManageService;

	@Resource(name = "GupManageService")
	private GupManageService gupManageService;

	@Resource(name = "CmmCodeManageService")
	private CmmCodeManageService cmmCodeManageService;

    @Resource(name="EgovFileMngService")
    private EgovFileMngService fileMngService;
	
    @Resource(name="EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;


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
	 * 저작권허락 권한 리스트
	 * @param searchVO
	 * @param model
	 * @return	"/elm/ElmGroupList"
	 * @throws Exception
	 */
	@IncludedInfo(name="전자도서관 권한 관리", order = 16 ,gid = 47)
	@RequestMapping(value="/elm/cup/ElmCupList.do")
	public String selectElmCupList(@ModelAttribute("searchVO") ElmCupListVO searchVO, ModelMap model, Map commandMap) throws Exception {

		// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

		CmmCodeVO cmmCodeVO = new CmmCodeVO();
		
		// 1. 클래스 그룹 목록 목록
		List<UserClassVO> userClassList = gupManageService.selectUserClass();
		model.addAttribute("userClassList", userClassList);

		// 3. 열람 권한 코드
		cmmCodeVO.setCodeId("ELA004");
		List svcScopeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("svcScopeList", svcScopeList);

		cmmCodeVO.setCodeId("ELA005");
		List cpyUseList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("cpyUseList", cpyUseList);
		

		//** pageing *//*
		PaginationInfo paginationInfo =	new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 1. 클래스 그룹 목록 목록
		int totCnt = cupManageService.selectElmCupListTotCnt(searchVO);
		List<ElmCupListVO> list	= cupManageService.selectElmCupList(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String) commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String) commandMap.get("searchCondition"));
		
		model.addAttribute("searchVO", searchVO);
		model.addAttribute("totCnt", totCnt);
		model.addAttribute("resultList", list);
		model.addAttribute("paginationInfo", paginationInfo);

		return "clikMng/elm/cup/ElmCupList";
	}


	/**
	 * 저작권허락 권한 리스트
	 * @param searchVO
	 * @param model
	 * @return	"/elm/ElmGroupList"
	 * @throws Exception
	 */
	@IncludedInfo(name="전자도서관 권한 관리", order = 16 ,gid = 47)
	@RequestMapping(value="/elm/cup/ElmCupListAjax.do")
	public String ElmCupListAjax(@ModelAttribute("searchVO") ElmCupListVO searchVO, ModelMap model) throws Exception {

		// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

		CmmCodeVO cmmCodeVO = new CmmCodeVO();
		
		// 1. 클래스 그룹 목록 목록
		List<UserClassVO> userClassList = gupManageService.selectUserClass();
		model.addAttribute("userClassList", userClassList);

		// 3. 열람 권한 코드
		cmmCodeVO.setCodeId("ELA004");
		List svcScopeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("svcScopeList", svcScopeList);

		cmmCodeVO.setCodeId("ELA005");
		List cpyUseList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("cpyUseList", cpyUseList);
		

		//** pageing *//*
		PaginationInfo paginationInfo =	new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 1. 클래스 그룹 목록 목록
		int totCnt = cupManageService.selectElmCupListTotCnt(searchVO);

		List<ElmCupListVO> list	= cupManageService.selectElmCupListAjax(searchVO);

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("searchVO", searchVO);
		model.addAttribute("totCnt", totCnt);
		model.addAttribute("resultList", list);
		model.addAttribute("paginationInfo", paginationInfo);

		return "clikMng/elm/cup/ElmCupListAjax";
	}


	/**
	 * 저작권허락 권한 등록
	 * @param vo - userClassVo
	 * @return 관리자 목록 페이지
	 * @exception Exception
	 */
	@RequestMapping(value="/elm/cup/ElmCupRegist.do")
	public String insertElmCupRegist(@ModelAttribute("vo") CopyrightPermVO vo
			, BindingResult bindingResult
			, Map commandMap
			, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}


		String sLocationUrl = "clikMng/elm/cup/ElmCupRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		log.info("cmd =>" + sCmd);


		CmmCodeVO cmmCodeVO = new CmmCodeVO();
		
		// 1. 클래스 그룹 목록 목록
		List<UserClassVO> userClassList = gupManageService.selectUserClass();
		model.addAttribute("userClassList", userClassList);

		// 3. 열람 권한 코드
		cmmCodeVO.setCodeId("ELA004");
		List svcScopeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("svcScopeList", svcScopeList);

		cmmCodeVO.setCodeId("ELA005");
		List cpyUseList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("cpyUseList", cpyUseList);

		sLocationUrl = "clikMng/elm/cup/ElmCupRegist";

		return sLocationUrl;

	}


	/**
	 * 저작권허락 권한 등록 처리
	 * @param vo - CopyrightPermVO
	 * @return ㅁㅁ관리자 목록 페이지ㅁ
	 * @exception Exception
	 */
	@RequestMapping(value="/elm/cup/ElmCupRegistProc.do")
	public String insertElmCupRegistProc(final MultipartHttpServletRequest multiRequest,
										Map commandMap,
										@ModelAttribute("copyrightPermVO") CopyrightPermVO copyrightPermVO,
										BindingResult bindingResult,
										ModelMap model) throws Exception {
		
		System.out.println("################################################");
		System.out.println("##### insertElmCupRegistProc ===>");
		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}


		String sLocationUrl = "forward:/elm/cup/ElmCupRegist.do";
		
		System.out.println("##### sLocationUrl ===> " + sLocationUrl);
		
		//서버  validate 체크
		beanValidator.validate(copyrightPermVO, bindingResult);
		if(bindingResult.hasErrors())
		{
			return sLocationUrl;
		}

        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        log.info("cmd =>" + sCmd);

        
		// 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

        //서버  validate 체크
        beanValidator.validate(copyrightPermVO, bindingResult);
        if(bindingResult.hasErrors())
        {
        	System.out.println("##### hasErrors ===> ");
            return sLocationUrl;
        } 
        else 
        {
        	
	    	List<FileVO> result = null;

	    	String uploadFolder = "Globals.iconFileStorePath";
	    	String atchFileId = "";

	    	System.out.println("##### uploadFolder ===> " + uploadFolder);
	    	
	    	final Map<String, MultipartFile> files = multiRequest.getFileMap();

	    	System.out.println("##### files ===> " + files);
	    	System.out.println("##### files.isEmpty() ===> " + files.isEmpty());
	    	
	    	if(!files.isEmpty())
	    	{
	    		if(copyrightPermVO.getIconFileNm() == null || "".equals(copyrightPermVO.getIconFileNm()))
	    		{
		    		cupManageService.deleteIconFile(copyrightPermVO);
		    		
		    	    result = fileUtil.parseFileInf(files, "ICON_", 0, "", uploadFolder);
		    	    System.out.println("##### result ===> " + result);	    	    
		    	    atchFileId = fileMngService.insertFileInfs(result);
		    	    System.out.println("##### atchFileId ===> " + atchFileId);
		    	    copyrightPermVO.setIconFileNm(atchFileId);
	    		}
	    	}
        }
        
        //저장
		try
		{
        	if(sCmd.equals("save"))
        	{
        		//아이디 설정
        		copyrightPermVO.setFrstRegisterId(loginVO.getMngrId());
        		
        		cupManageService.insertElmCupRegist(copyrightPermVO);	
    			
        	}
        	else
        	{
        		copyrightPermVO.setLastUpdusrId(loginVO.getMngrId());
        		
        		/** 20150421 : copyrightPermVO oriCpyrhtCode 추가 .  */
        		cupManageService.updateElmCupDetail(copyrightPermVO);
        	}
			
		}
		catch (Exception ex)
		{
			log.debug("Default Exeption Handler run...............::: insertElmCupRegist :::");
			ex.printStackTrace();

			model.addAttribute("msg", "fail.common.select");
			return "forward:/elm/cup/ElmCupList.do";
		}

System.out.println("######################################################### END");
		return "forward:/elm/cup/ElmCupList.do";
		
	}
	

    

	/**
	 * 관리자 수정을 한다
	 * @param vo - MngVo
	 * @return 관리자 목록 페이지
	 * @exception Exception
	 */
	@RequestMapping(value="/elm/cup/ElmCupDetail.do")
	public String updateElmCupDetail(@ModelAttribute("vo") CopyrightPermVO searchVO
			, BindingResult bindingResult
			, Map commandMap
			, ModelMap model) throws Exception {

		// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}


		
		// 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		// return Url
		String sLocationUrl = "clikMng/elm/cup/ElmCupDetail";

		// 기능구분 del = 삭제, edit = 수정
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");


		/** 관리자 및 지방의회 상세보기 */
		if(sCmd.equals("edit")) 
		{
			// 관리자 수정
			//서버  validate 체크
			beanValidator.validate(searchVO, bindingResult);
			if(bindingResult.hasErrors())
			{
				return sLocationUrl;
			}

			//아이디 설정
			searchVO.setLastUpdusrId(loginVO.getMngrId());

			cupManageService.updateElmCupDetail(searchVO);

			return sLocationUrl;

		} 
		else if(sCmd.equals("del")) 
		{
			cupManageService.deleteIconFile(searchVO);
			cupManageService.deleteElmCupDetail(searchVO);

			return "clikMng/elm/cup/ElmCupList";
		}

		


		// 관리자 상세내용 조회
		model.addAttribute("result", cupManageService.selectElmCupDetail(searchVO));


		CmmCodeVO cmmCodeVO = new CmmCodeVO();
		
		// 1. 클래스 그룹 목록 목록
		List<UserClassVO> userClassList = gupManageService.selectUserClass();
		model.addAttribute("userClassList", userClassList);

		// 3. 열람 권한 코드
		cmmCodeVO.setCodeId("ELA004");
		List svcScopeList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("svcScopeList", svcScopeList);

		cmmCodeVO.setCodeId("ELA005");
		List cpyUseList = cmmCodeManageService.selectCmmDetailCodeList(cmmCodeVO);
		model.addAttribute("cpyUseList", cpyUseList);

		return sLocationUrl;

	}
}
