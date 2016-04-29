package clikmng.nanet.go.kr.cop.bbs.web;

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

import clikmng.nanet.go.kr.cmm.EgovComponentChecker;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.cop.bbs.service.BoardUseInf;
import clikmng.nanet.go.kr.cop.bbs.service.BoardUseInfVO;
import clikmng.nanet.go.kr.cop.bbs.service.BBSUseInfoManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 게시판의 이용정보를 관리하기 위한 컨트롤러 클래스
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * </pre>
 */

@Controller
public class BBSUseInfoManageController__ {
	
	 
	 

    @Resource(name = "BBSUseInfoManageService")
    private BBSUseInfoManageService bbsUseService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    

    @Autowired
    private DefaultBeanValidator beanValidator;

    //protected Logger log = Logger.getLogger(this.getClass());
    
    /**
     * 게시판 사용 정보를 삭제한다.
     * 
     * @param bdUseVO
     * @param bdUseInf
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/deleteBBSUseInf.do")
    public String deleteBBSUseInf(@ModelAttribute("searchVO") BoardUseInfVO bdUseVO, @ModelAttribute("bdUseInf") BoardUseInf bdUseInf,
	    SessionStatus status, ModelMap model) throws Exception {

	//LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	if (isAuthenticated) {
	    bbsUseService.deleteBBSUseInf(bdUseInf);
	}

	return "forward:/cop/com/selectBBSUseInfs.do";
    }

    /**
     * 게사판 사용정보 등록을 위한 등록페이지로 이동한다.
     * 
     * @param bdUseVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/addBBSUseInf.do")
    public String addBBSUseInf(@ModelAttribute("searchVO") BoardUseInfVO bdUseVO, ModelMap model) throws Exception {
    	
    	//if(EgovComponentChecker.hasComponent("EgovCommunityManageService")){//2011.09.15
    		model.addAttribute("useCommunity", "true");
    	//}
    	//if(EgovComponentChecker.hasComponent("EgovClubManageService")){//2011.09.15
    		model.addAttribute("useClub", "true");
    	//}
    	
    	return "clikMng/cop/com/EgovBoardUseInfRegist";
    }

    /**
     * 게시판 사용정보를 등록한다.
     * 
     * @param bdUseVO
     * @param bdUseInf
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/insertBBSUseInf.do")
    public String insertBBSUseInf(@ModelAttribute("searchVO") BoardUseInfVO bdUseVO, @ModelAttribute("boardUseInf") BoardUseInf boardUseInf,
	    BindingResult bindingResult, Map<String, Object> commandMap, SessionStatus status, ModelMap model) throws Exception {

	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	beanValidator.validate(boardUseInf, bindingResult);

	if (bindingResult.hasErrors()) {
	    return "clikMng/cop/com/EgovBoardUseInfRegist";
	}

	String trgetType = (String)commandMap.get("param_trgetType");
	String registSeCode = "";
	
	// CMMNTY 06/CLUB 05/SYSTEM(REGC01)
	if ("CMMNTY".equals(trgetType)) {
	    registSeCode = "REGC06";
	} else if ("CLUB".equals(trgetType)) {
	    registSeCode = "REGC05";
	} else {
	    registSeCode = "REGC01";
	}

	boardUseInf.setUseAt("Y");
	boardUseInf.setFrstRegisterId(user.getMngrId());
	boardUseInf.setRegistSeCode(registSeCode);
	
	if (isAuthenticated) {
	    bbsUseService.insertBBSUseInf(boardUseInf);
	}

	return "forward:/cop/com/selectBBSUseInfs.do";
    }

    /**
     * 게시판 사용정보 목록을 조회한다.
     * 
     * @param bdUseVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @IncludedInfo(name="게시판사용정보", order = 190 ,gid = 40)
    @RequestMapping("/cop/com/selectBBSUseInfs.do")
    public String selectBBSUseInfs(@ModelAttribute("searchVO") BoardUseInfVO bdUseVO, ModelMap model) throws Exception {

	bdUseVO.setPageUnit(propertyService.getInt("pageUnit"));
	bdUseVO.setPageSize(propertyService.getInt("pageSize"));

	PaginationInfo paginationInfo = new PaginationInfo();
	
	paginationInfo.setCurrentPageNo(bdUseVO.getPageIndex());
	paginationInfo.setRecordCountPerPage(bdUseVO.getPageUnit());
	paginationInfo.setPageSize(bdUseVO.getPageSize());

	bdUseVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	bdUseVO.setLastIndex(paginationInfo.getLastRecordIndex());
	bdUseVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	Map<String, Object> map = bbsUseService.selectBBSUseInfs(bdUseVO);
	int totCnt = Integer.parseInt((String)map.get("resultCnt"));
	
	paginationInfo.setTotalRecordCount(totCnt);

	model.addAttribute("resultList", map.get("resultList"));
	model.addAttribute("resultCnt", map.get("resultCnt"));
	model.addAttribute("paginationInfo", paginationInfo);
	
	
	//if(EgovComponentChecker.hasComponent("EgovCommunityManageService")){//2011.09.15
		model.addAttribute("useCommunity", "true");
	//}
	//if(EgovComponentChecker.hasComponent("EgovClubManageService")){//2011.09.15
		model.addAttribute("useClub", "true");
	//}

	return "clikMng/cop/com/EgovBoardUseInfList";
    }

    /**
     * 게시판 사용정보를 수정한다.
     * 
     * @param bdUseVO
     * @param bdUseInf
     * @param sessionVO
     * @param status
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/updateBBSUseInf.do")
    public String updateBBSUseInf(@ModelAttribute("searchVO") BoardUseInfVO bdUseVO, @ModelAttribute("boardUseInf") BoardUseInf boardUseInf,
	    SessionStatus status, ModelMap model) throws Exception {

	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	
	if (isAuthenticated) {
	    bbsUseService.updateBBSUseInf(boardUseInf);
	}

	return "forward:/cop/com/selectBBSUseInfs.do";
    }

    /**
     * 게시판 사용정보에 대한 상세정보를 조회한다.
     * 
     * @param bdUseVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/com/selectBBSUseInf.do")
    public String selectBBSUseInf(@ModelAttribute("searchVO") BoardUseInfVO bdUseVO, ModelMap model, HttpServletRequest request) throws Exception {
	BoardUseInfVO vo = bbsUseService.selectBBSUseInf(bdUseVO);

	// 시스템 사용 게시판의 경우 URL 표시
	if ("SYSTEM_DEFAULT_BOARD".equals(vo.getTrgetId())) {
	    if (vo.getBbsTyCode().equals("BBST02")) {	// 익명게시판
		vo.setProvdUrl(request.getContextPath()+ "/cop/bbs/anonymous/selectBoardList.do?bbsId=" + vo.getBbsId());
	    } else {
		vo.setProvdUrl(request.getContextPath()+ "/cop/bbs/selectBoardList.do?bbsId=" + vo.getBbsId());
	    }
	}

	model.addAttribute("bdUseVO", vo);
	return "clikMng/cop/com/EgovBoardUseInfInqire";
    }
}
