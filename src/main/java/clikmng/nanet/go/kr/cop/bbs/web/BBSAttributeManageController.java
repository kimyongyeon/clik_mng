package clikmng.nanet.go.kr.cop.bbs.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.ComDefaultCodeVO;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.cop.bbs.service.BBSAttributeManageService;
import clikmng.nanet.go.kr.cop.bbs.service.BoardMaster;
import clikmng.nanet.go.kr.cop.bbs.service.BoardMasterVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 * 게시판 속성관리를 위한 컨트롤러  클래스
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------       --------    ---------------------------
 * </pre>
 */

@Controller
public class BBSAttributeManageController {
	
	 
	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

    @Resource(name = "BBSAttributeManageService")
    private BBSAttributeManageService bbsAttrbService;

    @Resource(name="CmmUseService")
    private CmmUseService cmmUseService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    


    @Autowired
    private DefaultBeanValidator beanValidator;

    //Logger log = Logger.getLogger(this.getClass());
    
    /**
     * 신규 게시판 마스터 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cop/bbs/addBBSMaster.do")
    public String addBBSMaster(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
	BoardMaster boardMaster = new BoardMaster();

	ComDefaultCodeVO vo = new ComDefaultCodeVO();
	
	vo.setCodeId("RKS010");
	
	List codeResult = cmmUseService.selectCmmCodeDetail(vo);
	
	model.addAttribute("typeList", codeResult);

	vo.setCodeId("RKS011");
	
	codeResult = cmmUseService.selectCmmCodeDetail(vo);
	
	model.addAttribute("attrbList", codeResult);
	model.addAttribute("boardMaster", boardMaster);

	//---------------------------------
	// 2009.06.26 : 2단계 기능 추가
	//---------------------------------
	//String flag = EgovProperties.getProperty("Globals.addedOptions");
	//if (flag != null && flag.trim().equalsIgnoreCase("true")) {
	//    model.addAttribute("addedOptions", "true");
	//}
	////-------------------------------
	
	
	//---------------------------------
	// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
	//---------------------------------

	
	//if(EgovComponentChecker.hasComponent("BBSCommentService")){
		model.addAttribute("useComment", "true");
	//}
	//if(EgovComponentChecker.hasComponent("BBSSatisfactionService")){
		model.addAttribute("useSatisfaction", "true");
	//}
	
	////-------------------------------

	return "clikMng/cop/bbs/EgovBoardMstrRegist";
    }

    /**
     * 신규 게시판 마스터 정보를 등록한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cop/bbs/insertBBSMasterInf.do")
    public String insertBBSMasterInf(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {

    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
	
		    ComDefaultCodeVO vo = new ComDefaultCodeVO();
		    vo.setCodeId("COM004");
		    
		    List codeResult = cmmUseService.selectCmmCodeDetail(vo);
		    model.addAttribute("typeList", codeResult);
	
		    vo.setCodeId("COM009");
		   
		    codeResult = cmmUseService.selectCmmCodeDetail(vo);
		    
		    model.addAttribute("attrbList", codeResult);
	
		    return "clikMng/cop/bbs/EgovBoardMstrRegist";
		}
	
		if (isAuthenticated) {
		    boardMaster.setFrstRegisterId(user.getMngrId());
		    boardMaster.setUseAt("Y");
		    boardMaster.setTrgetId("SYSTEMDEFAULT_REGIST");
	
		    bbsAttrbService.insertBBSMastetInf(boardMaster);
		}

		return "forward:/cop/bbs/SelectBBSMasterInfs.do";
    }

    /**
     * 게시판 마스터 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="게시판속성관리",order = 180 ,gid = 40)
    @RequestMapping("/cop/bbs/SelectBBSMasterInfs.do")
    public String selectBBSMasterInfs(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	if(boardMasterVO.getPageUnit() == 0){
    		boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
    	}
    	boardMasterVO.setPageSize(propertyService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());

		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
    	
		Map<String, Object> map = bbsAttrbService.selectBBSMasterInfs(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);

		return "clikMng/cop/bbs/EgovBoardMstrList";
    }

    /**
     * 게시판 마스터 상세내용을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/SelectBBSMasterInf.do")
    public String selectBBSMasterInf(@ModelAttribute("searchVO") BoardMasterVO searchVO, ModelMap model, HttpServletRequest request) throws Exception {

    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}    	
	    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	searchVO.setUniqId(user.getMngrId());
    	
    	BoardMasterVO vo = bbsAttrbService.selectBBSMasterInf(searchVO);

	
		// 시스템 사용 게시판의 경우 URL 표시
		if ("SYSTEM_DEFAULT_BOARD".equals(vo.getTrgetId())) {
		    if (vo.getBbsTyCode().equals("BBST02")) {	// 익명게시판
			vo.setProvdUrl(request.getContextPath()+ "/cop/bbs/anonymous/selectBoardList.do?bbsId=" + vo.getBbsId());
		    } else {
			vo.setProvdUrl(request.getContextPath()+ "/cop/bbs/selectBoardList.do?bbsId=" + vo.getBbsId());
		    }
		}
	
		model.addAttribute("result", vo);
	
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		//String flag = EgovProperties.getProperty("Globals.addedOptions");
		//if (flag != null && flag.trim().equalsIgnoreCase("true")) {
		//    model.addAttribute("addedOptions", "true");
		//}
		////-------------------------------
		
		//---------------------------------
		// 2011.09.15 : 2단계 기능 추가 반영 방법 변경
		//---------------------------------
		
		//if(EgovComponentChecker.hasComponent("BBSCommentService")){
			model.addAttribute("useComment", "true");
		//}
		//if(EgovComponentChecker.hasComponent("BBSSatisfactionService")){
			model.addAttribute("useSatisfaction", "true");
		//}
		
		////-------------------------------
		
		
		return "clikMng/cop/bbs/EgovBoardMstrUpdt";
    }

    /**
     * 게시판 마스터 정보를 수정한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/UpdateBBSMasterInf.do")
    public String updateBBSMasterInf(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    BindingResult bindingResult, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}    	

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
		beanValidator.validate(boardMaster, bindingResult);
		if (bindingResult.hasErrors()) {
		    BoardMasterVO vo = bbsAttrbService.selectBBSMasterInf(boardMasterVO);
	
		    model.addAttribute("result", vo);
		    
		    return "clikMng/cop/bbs/EgovBoardMstrUpdt";
		}

		if (isAuthenticated) {
		    boardMaster.setLastUpdusrId(user.getMngrId());
		    bbsAttrbService.updateBBSMasterInf(boardMaster);
		}
	
		return "forward:/cop/bbs/SelectBBSMasterInfs.do";
    }

    /**
     * 게시판 마스터 정보를 삭제한다.
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/DeleteBBSMasterInf.do")
    public String deleteBBSMasterInf(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, @ModelAttribute("boardMaster") BoardMaster boardMaster,
	    SessionStatus status, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}    

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	if (isAuthenticated) {
    		boardMaster.setLastUpdusrId(user.getMngrId());
    		bbsAttrbService.deleteBBSMasterInf(boardMaster);
    	}
	// status.setComplete();
    	return "forward:/cop/bbs/SelectBBSMasterInfs.do";
    }

    /**
     * 게시판 마스터 선택 팝업을 위한 목록을 조회한다.
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/SelectBBSMasterInfsPop.do")
    public String selectBBSMasterInfsPop(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}    
	
    	boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
    	boardMasterVO.setPageSize(propertyService.getInt("pageSize"));

    	PaginationInfo paginationInfo = new PaginationInfo();
	
		paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
		paginationInfo.setPageSize(boardMasterVO.getPageSize());
	
		boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		boardMasterVO.setUseAt("Y");
		
		Map<String, Object> map = bbsAttrbService.selectNotUsedBdMstrList(boardMasterVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));	
		model.addAttribute("paginationInfo", paginationInfo);
	
		return "clikMng/cop/bbs/EgovBoardMstrListPop";
    }
}
