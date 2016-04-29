package clikmng.nanet.go.kr.cop.bbs.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import clikmng.nanet.go.kr.cmm.CommonStringUtil;
import clikmng.nanet.go.kr.cmm.CommonUtil;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngService;
import clikmng.nanet.go.kr.cmm.service.EgovFileMngUtil;
import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.cop.bbs.service.Board;
import clikmng.nanet.go.kr.cop.bbs.service.BoardMaster;
import clikmng.nanet.go.kr.cop.bbs.service.BoardMasterVO;
import clikmng.nanet.go.kr.cop.bbs.service.BoardVO;
import clikmng.nanet.go.kr.cop.bbs.service.BBSAttributeManageService;
import clikmng.nanet.go.kr.cop.bbs.service.BBSCommentService;
import clikmng.nanet.go.kr.cop.bbs.service.BBSManageService;
import clikmng.nanet.go.kr.cop.bbs.service.BBSSatisfactionService;
import clikmng.nanet.go.kr.cop.bbs.service.BBSScrapService;
import clikmng.nanet.go.kr.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 게시물 관리를 위한 컨트롤러 클래스
 * @author 
 * @since 
 * @version 1.0
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
public class BBSManageController {

	 
	 
	
    @Resource(name = "BBSManageService")
    private BBSManageService bbsMngService;

    @Resource(name = "BBSAttributeManageService")
    private BBSAttributeManageService bbsAttrbService;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Autowired(required=false)
    private BBSCommentService bbsCommentService;
    
    @Autowired(required=false)
    private BBSSatisfactionService bbsSatisfactionService;
    
    @Autowired(required=false)
    private BBSScrapService bbsScrapService;
    ////-------------------------------

    @Autowired
    private DefaultBeanValidator beanValidator;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
    
    //protected Logger log = Logger.getLogger(this.getClass());
    
    /**
     * XSS 방지 처리.
     * 
     * @param data
     * @return
     */
    protected String unscript(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }
        
        String ret = data;
        
        ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
        
        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        
        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

        return ret;
    }

    /**
     * 게시물에 대한 목록을 조회한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectBoardList.do")
    public String selectBoardArticles(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {

   	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	if(!isAuthenticated) {
		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    	return "forward:/uat/uia/LoginUsr.do";
	}    	
    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	//String attrbFlag = "";

	// Login 개발 후 재개발
	boardVO.setBbsId(boardVO.getBbsId());
	boardVO.setBbsNm(boardVO.getBbsNm());
	
	BoardMasterVO vo = new BoardMasterVO();
	
	vo.setBbsId(boardVO.getBbsId());
	vo.setUniqId(user.getMngrId());
	
	BoardMasterVO master = bbsAttrbService.selectBBSMasterInf(vo);
	
	//-------------------------------
	// 방명록이면 방명록 URL로 forward
	//-------------------------------
	if (master.getBbsTyCode().equals("BBST04")) {
	    return "forward:/cop/bbs/selectGuestList.do";
	}
	////-----------------------------

	boardVO.setPageUnit(propertyService.getInt("pageUnit"));
	boardVO.setPageSize(propertyService.getInt("pageSize"));

	PaginationInfo paginationInfo = new PaginationInfo();
	
	paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
	paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
	paginationInfo.setPageSize(boardVO.getPageSize());

	boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
	boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	//Map<String, Object> map = bbsMngService.selectBoardArticles(boardVO, vo.getBbsAttrbCode());
	Map<String, Object> map = bbsMngService.selectBoardArticles(boardVO, master.getBbsAttrbCode());//2011.09.07
	int totCnt = Integer.parseInt((String)map.get("resultCnt"));
	
	paginationInfo.setTotalRecordCount(totCnt);

	//2014.10.24 lth 추가
	//목록에서 첨부파일 다운로드를 위하여 추가
	List<BoardVO> list = (List<BoardVO>)map.get("resultList");
	FileVO fileVO = null;
	for(int i = 0; i < list.size(); i++){
		fileVO = new FileVO();
		fileVO.setAtchFileId(list.get(i).getAtchFileId());
		List<FileVO> result = fileService.selectFileInfs(fileVO);
		
		if(result != null && result.size() > 0)
			list.get(i).setAtchFileSn(result.get(0).fileSn);
	}
	
	//-------------------------------
	// 기본 BBS template 지정 
	//-------------------------------
	if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
	    master.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
	}
	////-----------------------------

	model.addAttribute("resultList", map.get("resultList"));
	model.addAttribute("resultCnt", map.get("resultCnt"));
	model.addAttribute("boardVO", boardVO);
	model.addAttribute("brdMstrVO", master);
	model.addAttribute("paginationInfo", paginationInfo);

	return "clikMng/cop/bbs/EgovNoticeList";
    }

    /**
     * 게시물에 대한 상세 정보를 조회한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectBoardArticle.do")
    public String selectBoardArticle(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		// 조회수 증가 여부 지정
		boardVO.setPlusCount(true);
	
		//---------------------------------
		// 2009.06.29 : 2단계 기능 추가
		//---------------------------------
		if (!boardVO.getSubPageIndex().equals("")) {
		    boardVO.setPlusCount(false);
		}
		////-------------------------------
	
		boardVO.setLastUpdusrId(user.getMngrId());
		BoardVO vo = bbsMngService.selectBoardArticle(boardVO);
	
		model.addAttribute("result", vo);
		//CommandMap의 형태로 개선????
	
		model.addAttribute("sessionUniqId", user.getMngrId());
	
		//----------------------------
		// template 처리 (기본 BBS template 지정  포함)
		//----------------------------
		BoardMasterVO master = new BoardMasterVO();
		
		master.setBbsId(boardVO.getBbsId());
		master.setUniqId(user.getMngrId());
		
		BoardMasterVO masterVo = bbsAttrbService.selectBBSMasterInf(master);
	
		if (masterVo.getTmplatCours() == null || masterVo.getTmplatCours().equals("")) {
		    masterVo.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
		}
	
		model.addAttribute("brdMstrVO", masterVo);
		////-----------------------------
		
		if (bbsCommentService != null){
			if (bbsCommentService.canUseComment(boardVO.getBbsId())) {
			    model.addAttribute("useComment", "true");
			}
		}
		if (bbsSatisfactionService != null) {
			if (bbsSatisfactionService.canUseSatisfaction(boardVO.getBbsId())) {
			    model.addAttribute("useSatisfaction", "true");
			}
		}
		if (bbsScrapService != null ) {
			if (bbsScrapService.canUseScrap()) {
			    model.addAttribute("useScrap", "true");
			}
		}
		////--------------------------
	
		return "clikMng/cop/bbs/EgovNoticeInqire";
    }

    /**
     * 게시물 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/addBoardArticle.do")
    public String addBoardArticle(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	// 로그인VO에서  사용자 정보 가져오기
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
    	BoardMasterVO bdMstr = new BoardMasterVO();

		if (isAuthenticated) {
		    BoardMasterVO vo = new BoardMasterVO();
		    vo.setBbsId(boardVO.getBbsId());
		    vo.setUniqId(user.getMngrId());
	
		    bdMstr = bbsAttrbService.selectBBSMasterInf(vo);
		    model.addAttribute("bdMstr", bdMstr);
		}
/*
 		// 같은 건을 두번 저장
	    BoardMasterVO vo = new BoardMasterVO();
	    vo.setBbsId(boardVO.getBbsId());
	    vo.setUniqId(user.getMngrId());
	
	    bdMstr = bbsAttrbService.selectBBSMasterInf(vo);
	    model.addAttribute("bdMstr", bdMstr);
*/
	

		//----------------------------
		// 기본 BBS template 지정 
		//----------------------------
		if (bdMstr.getTmplatCours() == null || bdMstr.getTmplatCours().equals("")) {
		    bdMstr.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
		}
	
		model.addAttribute("brdMstrVO", bdMstr);
		////-----------------------------
	
		return "clikMng/cop/bbs/EgovNoticeRegist";
    }

    /**
     * 게시물을 등록한다.
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cop/bbs/insertBoardArticle.do")
    public String insertBoardArticle(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult, SessionStatus status,
	    ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

	   	// 로그인VO에서  사용자 정보 가져오기
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
	
		    BoardMasterVO master = new BoardMasterVO();
		    BoardMasterVO vo = new BoardMasterVO();
		    
		    vo.setBbsId(boardVO.getBbsId());
		    vo.setUniqId(user.getMngrId());
	
		    master = bbsAttrbService.selectBBSMasterInf(vo);
		    
		    model.addAttribute("bdMstr", master);
	
		    //----------------------------
		    // 기본 BBS template 지정 
		    //----------------------------
		    if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("css/clikmng/cop/tpl/BaseTemplate.css");
		    }
	
		    model.addAttribute("brdMstrVO", master);
		    ////-----------------------------
	
		    return "clikMng/cop/bbs/EgovNoticeRegist";
		}
		
		//---LOGIN관련
		if (isAuthenticated) {
		    List<FileVO> result = null;
		    String atchFileId = "";
		    
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "BBS_", 0, "", "Globals.bbsStorePath", boardVO.getBbsId());
			atchFileId = fileMngService.insertFileInfs(result);
		    }
		    board.setAtchFileId(atchFileId);
		    board.setFrstRegisterId(user.getMngrId());
		    board.setBbsId(board.getBbsId());
		    
		    
		    //board.setNtcrNm("");	// dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
		    board.setPassword("");	// dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
		    
		    
		    board.setNtcrId(user.getMngrId()); //게시물 통계 집계를 위해 등록자 ID 저장
		    board.setNtcrNm(user.getMngrNm()); //게시물 통계 집계를 위해 등록자 Name 저장
		    
		    board.setNttSj(URLDecoder.decode(board.getNttSj(),"UTF-8"));
		    board.setNttCn(URLDecoder.decode(board.getNttCn(),"UTF-8"));
		    
		    board.setNttCn(unscript(board.getNttCn()));	// XSS 방지
		    
		    bbsMngService.insertBoardArticle(board);
		}

		//status.setComplete();
		return "forward:/cop/bbs/selectBoardList.do";
    }

    /**
     * 게시물에 대한 답변 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/addReplyBoardArticle.do")
    public String addReplyBoardArticle(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}


		// 로그인VO에서  사용자 정보 가져오기
	    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	
		BoardMasterVO master = new BoardMasterVO();
		BoardMasterVO vo = new BoardMasterVO();
		
		vo.setBbsId(boardVO.getBbsId());
		vo.setUniqId(user.getMngrId());
	
		master = bbsAttrbService.selectBBSMasterInf(vo);
		
		model.addAttribute("bdMstr", master);
		model.addAttribute("result", boardVO);
	
		//----------------------------
		// 기본 BBS template 지정 
		//----------------------------
		if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
		    master.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
		}
	
		model.addAttribute("brdMstrVO", master);
		////-----------------------------
	
		return "clikMng/cop/bbs/EgovNoticeReply";
    }

    /**
     * 게시물에 대한 답변을 등록한다.
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cop/bbs/replyBoardArticle.do")
    public String replyBoardArticle(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult, ModelMap model,
	    SessionStatus status) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

	   	// 로그인VO에서  사용자 정보 가져오기
	   	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	
		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
		    BoardMasterVO master = new BoardMasterVO();
		    BoardMasterVO vo = new BoardMasterVO();
		    
		    vo.setBbsId(boardVO.getBbsId());
		    vo.setUniqId(user.getMngrId());
	
		    master = bbsAttrbService.selectBBSMasterInf(vo);
		    
		    model.addAttribute("bdMstr", master);
		    model.addAttribute("result", boardVO);
	
		    //----------------------------
		    // 기본 BBS template 지정 
		    //----------------------------
		    if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
		    }
	
		    model.addAttribute("brdMstrVO", master);
		    ////-----------------------------
	
		    return "clikMng/cop/bbs/EgovNoticeReply";
		}
	
		if (isAuthenticated) {
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    String atchFileId = "";
	
		    if (!files.isEmpty()) {
			List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, "", "Globals.bbsStorePath", boardVO.getBbsId());
			atchFileId = fileMngService.insertFileInfs(result);
		    }
	
		    board.setAtchFileId(atchFileId);
		    board.setReplyAt("Y");
		    board.setFrstRegisterId(user.getMngrId());
		    board.setBbsId(board.getBbsId());
		    board.setParnts(Long.toString(boardVO.getNttId()));
		    board.setSortOrdr(boardVO.getSortOrdr());
		    board.setReplyLc(Integer.toString(Integer.parseInt(boardVO.getReplyLc()) + 1));
		    
		    board.setNtcrNm("");	// dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
		    board.setPassword("");	// dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
		    
		    board.setNttCn(unscript(board.getNttCn()));	// XSS 방지
		    
		    bbsMngService.insertBoardArticle(board);
		}
		
		return "forward:/cop/bbs/selectBoardList.do";
    }

    /**
     * 게시물 수정을 위한 수정페이지로 이동한다.
     * 
     * @param boardVO
     * @param vo
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/forUpdateBoardArticle.do")
    public String selectBoardArticleForUpdt(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") BoardVO vo, ModelMap model)
	    throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

		//log.debug(this.getClass().getName()+"selectBoardArticleForUpdt getNttId "+boardVO.getNttId());
		//log.debug(this.getClass().getName()+"selectBoardArticleForUpdt getBbsId "+boardVO.getBbsId());
	
	  	// 로그인VO에서  사용자 정보 가져오기
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
		boardVO.setFrstRegisterId(user.getMngrId());
		
		BoardMaster master = new BoardMaster();
		BoardMasterVO bmvo = new BoardMasterVO();
		BoardVO bdvo = new BoardVO();
		
		vo.setBbsId(boardVO.getBbsId());
		
		master.setBbsId(boardVO.getBbsId());
		master.setUniqId(user.getMngrId());
	
		if (isAuthenticated) {
		    bmvo = bbsAttrbService.selectBBSMasterInf(master);
		    bdvo = bbsMngService.selectBoardArticle(boardVO);
		}
	
		model.addAttribute("result", bdvo);
		model.addAttribute("bdMstr", bmvo);
	
		//----------------------------
		// 기본 BBS template 지정 
		//----------------------------
		if (bmvo.getTmplatCours() == null || bmvo.getTmplatCours().equals("")) {
		    bmvo.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
		}
	
		model.addAttribute("brdMstrVO", bmvo);
		////-----------------------------
		
		return "clikMng/cop/bbs/EgovNoticeUpdt";
    }

    /**
     * 게시물에 대한 내용을 수정한다.
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cop/bbs/updateBoardArticle.do")
    public String updateBoardArticle(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult, ModelMap model,
	    SessionStatus status) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

	   	// 로그인VO에서  사용자 정보 가져오기
	    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	
	
		String atchFileId = boardVO.getAtchFileId();
	
		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
	
		    boardVO.setFrstRegisterId(user.getMngrId());
		    
		    BoardMaster master = new BoardMaster();
		    BoardMasterVO bmvo = new BoardMasterVO();
		    BoardVO bdvo = new BoardVO();
		    
		    master.setBbsId(boardVO.getBbsId());
		    master.setUniqId(user.getMngrId());
	
		    bmvo = bbsAttrbService.selectBBSMasterInf(master);
		    bdvo = bbsMngService.selectBoardArticle(boardVO);
	
		    model.addAttribute("result", bdvo);
		    model.addAttribute("bdMstr", bmvo);
	
		    return "clikMng/cop/bbs/EgovNoticeUpdt";
		}
		
		/*
		boardVO.setFrstRegisterId(user.getUniqId());
		BoardMaster _bdMstr = new BoardMaster();
		BoardMasterVO bmvo = new BoardMasterVO();
		BoardVO bdvo = new BoardVO();
		vo.setBbsId(boardVO.getBbsId());
		_bdMstr.setBbsId(boardVO.getBbsId());
		_bdMstr.setUniqId(user.getUniqId());
	
		if (isAuthenticated) {
		    bmvo = bbsAttrbService.selectBBSMasterInf(_bdMstr);
		    bdvo = bbsMngService.selectBoardArticle(boardVO);
		}
		//*/
	
		if (isAuthenticated) {
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    if (!files.isEmpty()) {
			if ("".equals(atchFileId)) {
			    List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId, "Globals.bbsStorePath", boardVO.getBbsId());
			    atchFileId = fileMngService.insertFileInfs(result);
			    board.setAtchFileId(atchFileId);
			} else {
			    FileVO fvo = new FileVO();
			    fvo.setAtchFileId(atchFileId);
			    int cnt = fileMngService.getMaxFileSN(fvo);
			    List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "Globals.bbsStorePath", boardVO.getBbsId());
			    fileMngService.updateFileInfs(_result);
			}
		    }
	
		    board.setLastUpdusrId(user.getMngrId());
		    
		    board.setNtcrId(user.getMngrId()); //게시물 통계 집계를 위해 등록자 ID 저장
		    board.setNtcrNm(user.getMngrNm());	// dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
		    board.setPassword("");	// dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
		    
		    board.setNttSj(URLDecoder.decode(board.getNttSj(),"UTF-8"));
		    board.setNttCn(URLDecoder.decode(board.getNttCn(),"UTF-8"));
		    
		    board.setNttCn(unscript(board.getNttCn()));	// XSS 방지
		    
		    bbsMngService.updateBoardArticle(board);
		}
		
		return "forward:/cop/bbs/selectBoardList.do";
    }

    /**
     * 게시물에 대한 내용을 삭제한다.
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/deleteBoardArticle.do")
    public String deleteBoardArticle(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") Board board,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		if (isAuthenticated) {
		    board.setLastUpdusrId(user.getMngrId());
		    board.setNttSj(CommonStringUtil.getHtmlStrCnvr(board.getNttSj()));
		    bbsMngService.deleteBoardArticle(board);
		}

		return "forward:/cop/bbs/selectBoardList.do";
    }

    /**
     * 방명록에 대한 목록을 조회한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectGuestList.do")
    public String selectGuestList(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	@SuppressWarnings("unused")
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
		// 수정 및 삭제 기능 제어를 위한 처리
		model.addAttribute("sessionUniqId", user.getMngrId());
		
		BoardVO vo = new BoardVO();
	
		vo.setBbsId(boardVO.getBbsId());
		vo.setBbsNm(boardVO.getBbsNm());
		vo.setNtcrNm(user.getMngrNm());
		vo.setNtcrId(user.getMngrId());
	
		BoardMasterVO masterVo = new BoardMasterVO();
		
		masterVo.setBbsId(vo.getBbsId());
		masterVo.setUniqId(user.getMngrId());
		
		BoardMasterVO mstrVO = bbsAttrbService.selectBBSMasterInf(masterVo);
	
		vo.setPageUnit(propertyService.getInt("pageUnit"));
		vo.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
	
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		Map<String, Object> map = bbsMngService.selectGuestList(vo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("brdMstrVO", mstrVO);
		model.addAttribute("boardVO", vo);
		model.addAttribute("paginationInfo", paginationInfo);
	
		return "clikMng/cop/bbs/EgovGuestList";
    }

    /**
     * 방명록 수정을 위한 특정 내용을 조회한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectSingleGuestList.do")
    public String selectSingleGuestList(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("brdMstrVO") BoardMasterVO brdMstrVO,
	    ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	@SuppressWarnings("unused")
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
		BoardVO vo = bbsMngService.selectBoardArticle(boardVO);
	
		boardVO.setBbsId(boardVO.getBbsId());
		boardVO.setBbsNm(boardVO.getBbsNm());
		boardVO.setNtcrNm(user.getMngrNm());
	
		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());
	
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		Map<String, Object> map = bbsMngService.selectGuestList(boardVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("boardVO", vo);
		model.addAttribute("brdMstrVO", brdMstrVO);
		model.addAttribute("paginationInfo", paginationInfo);
	
		return "clikMng/cop/bbs/EgovGuestList";
    }

    /**
     * 방명록에 대한 내용을 삭제한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/deleteGuestList.do")
    public String deleteGuestList(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") Board board, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	@SuppressWarnings("unused")
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
		if (isAuthenticated) {
		    bbsMngService.deleteGuestList(boardVO);
		}
	
		return "forward:/cop/bbs/selectGuestList.do";
    }

    /**
     * 방명록 수정의 위한 목록을 조회한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/updateGuestList.do")
    public String updateGuestList(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") Board board, BindingResult bindingResult,
	    ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
	
		//BBST02, BBST04
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
	
		    BoardVO vo = new BoardVO();
	
		    vo.setBbsId(boardVO.getBbsId());
		    vo.setBbsNm(boardVO.getBbsNm());
		    vo.setNtcrNm(user.getMngrNm());
		    vo.setNtcrId(user.getMngrId());
	
		    BoardMasterVO masterVo = new BoardMasterVO();
		    
		    masterVo.setBbsId(vo.getBbsId());
		    masterVo.setUniqId(user.getMngrId());
		    
		    BoardMasterVO mstrVO = bbsAttrbService.selectBBSMasterInf(masterVo);
	
		    vo.setPageUnit(propertyService.getInt("pageUnit"));
		    vo.setPageSize(propertyService.getInt("pageSize"));
	
		    PaginationInfo paginationInfo = new PaginationInfo();
		    paginationInfo.setCurrentPageNo(vo.getPageIndex());
		    paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		    paginationInfo.setPageSize(vo.getPageSize());
	
		    vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		    vo.setLastIndex(paginationInfo.getLastRecordIndex());
		    vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		    Map<String, Object> map = bbsMngService.selectGuestList(vo);
		    int totCnt = Integer.parseInt((String)map.get("resultCnt"));
	
		    paginationInfo.setTotalRecordCount(totCnt);
		    
		    model.addAttribute("resultList", map.get("resultList"));
		    model.addAttribute("resultCnt", map.get("resultCnt"));
		    model.addAttribute("brdMstrVO", mstrVO);
		    model.addAttribute("boardVO", vo);
		    model.addAttribute("paginationInfo", paginationInfo);
	
		    return "clikMng/cop/bbs/EgovGuestList";
		}
	
		if (isAuthenticated) {
		    bbsMngService.updateBoardArticle(board);
		    boardVO.setNttCn("");
		    boardVO.setPassword("");
		    boardVO.setNtcrId("");
		    boardVO.setNttId(0);
		}
	
		return "forward:/cop/bbs/selectGuestList.do";
    }

    /**
     * 방명록에 대한 내용을 등록한다.
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/insertGuestList.do")
    public String insertGuestList(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") Board board, BindingResult bindingResult,
	    ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

		//그러니까 무인증은 아니고  - _- 익명으로 등록이 가능한 부분임
		// 무인증이 되려면 별도의 컨트롤러를 하나 더 등록해야함
	
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
	
		    BoardVO vo = new BoardVO();
	
		    vo.setBbsId(boardVO.getBbsId());
		    vo.setBbsNm(boardVO.getBbsNm());
		    vo.setNtcrNm(user.getMngrNm());
		    vo.setNtcrId(user.getMngrId());
	
		    BoardMasterVO masterVo = new BoardMasterVO();
		    
		    masterVo.setBbsId(vo.getBbsId());
		    masterVo.setUniqId(user.getMngrId());
		    
		    BoardMasterVO mstrVO = bbsAttrbService.selectBBSMasterInf(masterVo);
	
		    vo.setPageUnit(propertyService.getInt("pageUnit"));
		    vo.setPageSize(propertyService.getInt("pageSize"));
	
		    PaginationInfo paginationInfo = new PaginationInfo();
		    paginationInfo.setCurrentPageNo(vo.getPageIndex());
		    paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		    paginationInfo.setPageSize(vo.getPageSize());
	
		    vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		    vo.setLastIndex(paginationInfo.getLastRecordIndex());
		    vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		    Map<String, Object> map = bbsMngService.selectGuestList(vo);
		    int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		    
		    paginationInfo.setTotalRecordCount(totCnt);
	
		    model.addAttribute("resultList", map.get("resultList"));
		    model.addAttribute("resultCnt", map.get("resultCnt"));
		    model.addAttribute("brdMstrVO", mstrVO);
		    model.addAttribute("boardVO", vo);	    
		    model.addAttribute("paginationInfo", paginationInfo);
	
		    return "clikMng/cop/bbs/EgovGuestList";
	
		}
	
		if (isAuthenticated) {
		    board.setFrstRegisterId(user.getMngrId());
		    
		    bbsMngService.insertBoardArticle(board);
	
		    boardVO.setNttCn("");
		    boardVO.setPassword("");
		    boardVO.setNtcrId("");
		    boardVO.setNttId(0);
		}
	
		return "forward:/cop/bbs/selectGuestList.do";
    }
    
    /**
     * 익명게시물에 대한 목록을 조회한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/selectBoardList.do")
    public String selectAnonymousBoardArticles(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//log.debug(this.getClass().getName() + " user.getId() "+ user.getId());
		//log.debug(this.getClass().getName() + " user.getName() "+ user.getName());
		//log.debug(this.getClass().getName() + " user.getUniqId() "+ user.getUniqId());
		//log.debug(this.getClass().getName() + " user.getOrgnztId() "+ user.getOrgnztId());
		//log.debug(this.getClass().getName() + " user.getUserSe() "+ user.getUserSe());
		//log.debug(this.getClass().getName() + " user.getEmail() "+ user.getEmail());
	
		//String attrbFlag = "";
	
		boardVO.setBbsId(boardVO.getBbsId());
		boardVO.setBbsNm(boardVO.getBbsNm());
	
		BoardMasterVO vo = new BoardMasterVO();
		
		vo.setBbsId(boardVO.getBbsId());
		vo.setUniqId("ANONYMOUS");	// 익명
		
		BoardMasterVO master = bbsAttrbService.selectBBSMasterInf(vo);
		
		//-------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		//-------------------------------
		if (!master.getBbsTyCode().equals("BBST02")) {
		    return "forward:/cop/bbs/selectBoardList.do";
		}
		////-----------------------------
	
		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());
	
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		Map<String, Object> map = bbsMngService.selectBoardArticles(boardVO, vo.getBbsAttrbCode());
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
	
		//-------------------------------
		// 기본 BBS template 지정 
		//-------------------------------
		if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
		    master.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
		}
		////-----------------------------
	
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("brdMstrVO", master);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("anonymous", "true");
	
		return "clikMng/cop/bbs/EgovNoticeList";
    }
    
    /**
     * 익명게시물 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/addBoardArticle.do")
    public String addAnonymousBoardArticle(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		BoardMasterVO bdMstr = new BoardMasterVO();
	
		if (isAuthenticated) {
		    BoardMasterVO vo = new BoardMasterVO();
		    vo.setBbsId(boardVO.getBbsId());
		    vo.setUniqId("ANONYMOUS");
	
		    bdMstr = bbsAttrbService.selectBBSMasterInf(vo);
		    model.addAttribute("bdMstr", bdMstr);
		}
		
		//-------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		//-------------------------------
		if (!bdMstr.getBbsTyCode().equals("BBST02")) {
		    return "forward:/cop/bbs/addBoardArticle.do";
		}
		////-----------------------------
	
		//----------------------------
		// 기본 BBS template 지정 
		//----------------------------
		if (bdMstr.getTmplatCours() == null || bdMstr.getTmplatCours().equals("")) {
		    bdMstr.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
		}
	
		model.addAttribute("brdMstrVO", bdMstr);
		////-----------------------------
		
		model.addAttribute("anonymous", "true");
	
		return "clikMng/cop/bbs/EgovNoticeRegist";
    }
    
    /**
     * 익명게시물을 등록한다.
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cop/bbs/anonymous/insertBoardArticle.do")
    public String insertAnonymousBoardArticle(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult, SessionStatus status,
	    ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
		    
		    BoardMasterVO master = new BoardMasterVO();
		    BoardMasterVO vo = new BoardMasterVO();
		    
		    vo.setBbsId(boardVO.getBbsId());
		    vo.setUniqId("ANONYMOUS");
	
		    master = bbsAttrbService.selectBBSMasterInf(vo);
		    
		    model.addAttribute("bdMstr", master);
		    
		    //-------------------------------
		    // 익명게시판이 아니면.. 원래 게시판 URL로 forward
		    //-------------------------------
		    if (!bdMstr.getBbsTyCode().equals("BBST02")) {
			return "forward:/cop/bbs/insertBoardArticle.do";
		    }
		    ////-----------------------------
	
		    //----------------------------
		    // 기본 BBS template 지정 
		    //----------------------------
		    if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
		    }
	
		    model.addAttribute("brdMstrVO", master);
		    ////-----------------------------
		    
		    model.addAttribute("anonymous", "true");
	
		    return "clikMng/cop/bbs/EgovNoticeRegist";
		}
		
		if (isAuthenticated) {
		    List<FileVO> result = null;
		    String atchFileId = "";
		    
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "BBS_", 0, "", "Globals.bbsStorePath", boardVO.getBbsId());
			atchFileId = fileMngService.insertFileInfs(result);
		    }
		    board.setAtchFileId(atchFileId);
		    board.setFrstRegisterId("ANONYMOUS");
		    board.setBbsId(board.getBbsId());
		    
		    // 익명게시판 관련
		    board.setNtcrNm(board.getNtcrNm());
		    board.setPassword(EgovFileScrty.encryptPassword(board.getPassword()));
		    
		    board.setNttCn(unscript(board.getNttCn()));	// XSS 방지
		    
		    bbsMngService.insertBoardArticle(board);
		}
	
		//status.setComplete();
		return "forward:/cop/bbs/anonymous/selectBoardList.do";
    }
    
    /**
     * 익명게시물에 대한 상세 정보를 조회한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/selectBoardArticle.do")
    public String selectAnonymousBoardArticle(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		// 조회수 증가 여부 지정
		boardVO.setPlusCount(true);
		
		//---------------------------------
		// 2009.06.29 : 2단계 기능 추가
		//---------------------------------
		if (!boardVO.getSubPageIndex().equals("")) {
		    boardVO.setPlusCount(false);
		}
		////-------------------------------
	
		boardVO.setLastUpdusrId("ANONYMOUS");
		BoardVO vo = bbsMngService.selectBoardArticle(boardVO);
	
		model.addAttribute("result", vo);
		//CommandMap의 형태로 개선????
	
		model.addAttribute("sessionUniqId", "ANONYMOUS");
	
		//----------------------------
		// template 처리 (기본 BBS template 지정  포함)
		//----------------------------
		BoardMasterVO master = new BoardMasterVO();
		
		master.setBbsId(boardVO.getBbsId());
		master.setUniqId("ANONYMOUS");
		
		BoardMasterVO masterVo = bbsAttrbService.selectBBSMasterInf(master);
		
		//-------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		//-------------------------------
		if (!masterVo.getBbsTyCode().equals("BBST02")) {
		    return "forward:/cop/bbs/selectBoardArticle.do";
		}
		////-----------------------------
	
		if (masterVo.getTmplatCours() == null || masterVo.getTmplatCours().equals("")) {
		    masterVo.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
		}
	
		model.addAttribute("brdMstrVO", masterVo);
		////-----------------------------
		
		model.addAttribute("anonymous", "true");
		
		//----------------------------
		// 2009.06.29 : 2단계 기능 추가
		// 2011.07.01 : 댓글, 스크랩, 만족도 조사 기능의 종속성 제거
		//----------------------------
		if (bbsCommentService != null){
			if (bbsCommentService.canUseComment(boardVO.getBbsId())) {
			    model.addAttribute("useComment", "true");
			}
		}
		if (bbsSatisfactionService != null){		
			if (bbsSatisfactionService.canUseSatisfaction(boardVO.getBbsId())) {
			    model.addAttribute("useSatisfaction", "true");
			}
		}
		if (bbsScrapService != null){
			if (bbsScrapService.canUseScrap()) {
			    model.addAttribute("useScrap", "true");
			}
		}
		////--------------------------

	return "clikMng/cop/bbs/EgovNoticeInqire";
    }
    
    /**
     * 익명게시물에 대한 내용을 삭제한다.
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/deleteBoardArticle.do")
    public String deleteAnonymousBoardArticle(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") Board board,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, ModelMap model) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		//--------------------------------------------------
		// 마스터 정보 얻기
		//--------------------------------------------------
		BoardMasterVO master = new BoardMasterVO();
		
		master.setBbsId(boardVO.getBbsId());
		master.setUniqId("ANONYMOUS");
		
		BoardMasterVO masterVo = bbsAttrbService.selectBBSMasterInf(master);
		
		//-------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		//-------------------------------
		if (!masterVo.getBbsTyCode().equals("BBST02")) {
		    return "forward:/cop/bbs/deleteBoardArticle.do";
		}
		////-----------------------------
		
		//-------------------------------
		// 패스워드 비교
		//-------------------------------
		String dbpassword = bbsMngService.getPasswordInf(board);
		String enpassword = EgovFileScrty.encryptPassword(board.getPassword());
		
		if (!dbpassword.equals(enpassword)) {
		    
		    model.addAttribute("msg", egovMessageSource.getMessage("cop.password.not.same.msg"));
		    
		    return "forward:/cop/bbs/anonymous/selectBoardArticle.do";
		}
		////-----------------------------
	
		if (isAuthenticated) {
		    board.setLastUpdusrId("ANONYMOUS");
		    
		    bbsMngService.deleteBoardArticle(board);
		}
	
		return "forward:/cop/bbs/anonymous/selectBoardList.do";
    }
    
    /**
     * 익명게시물 수정을 위한 수정페이지로 이동한다.
     * 
     * @param boardVO
     * @param vo
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/forUpdateBoardArticle.do")
    public String selectAnonymousBoardArticleForUpdt(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") BoardVO vo, ModelMap model)
	    throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

	//log.debug(this.getClass().getName()+"selectBoardArticleForUpdt getNttId "+boardVO.getNttId());
	//log.debug(this.getClass().getName()+"selectBoardArticleForUpdt getBbsId "+boardVO.getBbsId());

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		boardVO.setFrstRegisterId("ANONYMOUS");
		
		BoardMaster master = new BoardMaster();
		BoardMasterVO bmvo = new BoardMasterVO();
		BoardVO bdvo = new BoardVO();
		
		vo.setBbsId(boardVO.getBbsId());
		
		master.setBbsId(boardVO.getBbsId());
		master.setUniqId("ANONYMOUS");
	
		if (isAuthenticated) {
		    bmvo = bbsAttrbService.selectBBSMasterInf(master);
	
		    //-------------------------------
		    // 익명게시판이 아니면.. 원래 게시판 URL로 forward
		    //-------------------------------
		    if (!bmvo.getBbsTyCode().equals("BBST02")) {
			return "forward:/cop/bbs/forUpdateBoardArticle.do";
		    }
		    ////-----------------------------
	
		    //-------------------------------
		    // 패스워드 비교
		    //-------------------------------
		    String dbpassword = bbsMngService.getPasswordInf(boardVO);
		    String enpassword = EgovFileScrty.encryptPassword(boardVO.getPassword());
	
		    if (!dbpassword.equals(enpassword)) {
	
			model.addAttribute("msg", egovMessageSource.getMessage("cop.password.not.same.msg"));
	
			return "forward:/cop/bbs/anonymous/selectBoardArticle.do";
		    }
		    ////-----------------------------
	
		    bdvo = bbsMngService.selectBoardArticle(boardVO);
		}
	
		model.addAttribute("result", bdvo);
		model.addAttribute("bdMstr", bmvo);
	
		//----------------------------
		// 기본 BBS template 지정 
		//----------------------------
		if (bmvo.getTmplatCours() == null || bmvo.getTmplatCours().equals("")) {
		    bmvo.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
		}
	
		model.addAttribute("brdMstrVO", bmvo);
		////-----------------------------
		
		model.addAttribute("anonymous", "true");
		
		return "clikMng/cop/bbs/EgovNoticeUpdt";
    }
    
    /**
     * 익명게시물에 대한 내용을 수정한다.
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cop/bbs/anonymous/updateBoardArticle.do")
    public String updateAnonymousBoardArticle(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult, ModelMap model,
	    SessionStatus status) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
		String atchFileId = boardVO.getAtchFileId();
	
		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
	
		    boardVO.setFrstRegisterId("ANONYMOUS");
		    
		    BoardMaster master = new BoardMaster();
		    BoardMasterVO bmvo = new BoardMasterVO();
		    BoardVO bdvo = new BoardVO();
		    
		    master.setBbsId(boardVO.getBbsId());
		    master.setUniqId("ANONYMOUS");
	
		    bmvo = bbsAttrbService.selectBBSMasterInf(master);
		    
		    //-------------------------------
		    // 익명게시판이 아니면.. 원래 게시판 URL로 forward
		    //-------------------------------
		    if (!bdMstr.getBbsTyCode().equals("BBST02")) {
			return "forward:/cop/bbs/updateBoardArticle.do";
		    }
		    ////-----------------------------
		    
		    bdvo = bbsMngService.selectBoardArticle(boardVO);
	
		    model.addAttribute("result", bdvo);
		    model.addAttribute("bdMstr", bmvo);
		    
		    model.addAttribute("anonymous", "true");
	
		    return "clikMng/cop/bbs/EgovNoticeUpdt";
		}
	
		if (isAuthenticated) {
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    if (!files.isEmpty()) {
			if ("".equals(atchFileId)) {
			    List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId, "Globals.bbsStorePath", boardVO.getBbsId());
			    atchFileId = fileMngService.insertFileInfs(result);
			    board.setAtchFileId(atchFileId);
			} else {
			    FileVO fvo = new FileVO();
			    fvo.setAtchFileId(atchFileId);
			    int cnt = fileMngService.getMaxFileSN(fvo);
			    List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "Globals.bbsStorePath", boardVO.getBbsId());
			    fileMngService.updateFileInfs(_result);
			}
		    }
	
		    board.setLastUpdusrId("ANONYMOUS");
		    
		    // 익명게시판 관련
		    board.setNtcrNm(board.getNtcrNm());
		    board.setPassword(EgovFileScrty.encryptPassword(board.getPassword()));
		    
		    board.setNttCn(unscript(board.getNttCn()));	// XSS 방지
		    
		    bbsMngService.updateBoardArticle(board);
		}
		
		return "forward:/cop/bbs/anonymous/selectBoardList.do";
    }
    
    /**
     * 익명게시물에 대한 답변 등록을 위한 등록페이지로 이동한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/anonymous/addReplyBoardArticle.do")
    public String addAnonymousReplyBoardArticle(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	
		BoardMasterVO master = new BoardMasterVO();
		BoardMasterVO vo = new BoardMasterVO();
		
		vo.setBbsId(boardVO.getBbsId());
		vo.setUniqId("ANONYMOUS");
	
		master = bbsAttrbService.selectBBSMasterInf(vo);
		
		//-------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		//-------------------------------
		if (!master.getBbsTyCode().equals("BBST02")) {
		    return "forward:/cop/bbs/addReplyBoardArticle.do";
		}
		////-----------------------------
		
		model.addAttribute("bdMstr", master);
		model.addAttribute("result", boardVO);
	
		//----------------------------
		// 기본 BBS template 지정 
		//----------------------------
		if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
		    master.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
		}
	
		model.addAttribute("brdMstrVO", master);
		////-----------------------------
		
		model.addAttribute("anonymous", "true");
	
		return "clikMng/cop/bbs/EgovNoticeReply";
    }
    
    /**
     * 익명게시물에 대한 답변을 등록한다.
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cop/bbs/anonymous/replyBoardArticle.do")
    public String replyAnonymousBoardArticle(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult, ModelMap model,
	    SessionStatus status) throws Exception {
    	
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
		    BoardMasterVO master = new BoardMasterVO();
		    BoardMasterVO vo = new BoardMasterVO();
		    
		    vo.setBbsId(boardVO.getBbsId());
		    vo.setUniqId("ANONYMOUS");
	
		    master = bbsAttrbService.selectBBSMasterInf(vo);
		    
			//-------------------------------
			// 익명게시판이 아니면.. 원래 게시판 URL로 forward
			//-------------------------------
			if (!master.getBbsTyCode().equals("BBST02")) {
			    return "forward:/cop/bbs/replyBoardArticle.do";
			}
			////-----------------------------
		    
		    model.addAttribute("bdMstr", master);
		    model.addAttribute("result", boardVO);
	
		    //----------------------------
		    // 기본 BBS template 지정 
		    //----------------------------
		    if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("/css/clikmng/cop/tpl/bbsTemplate.css");
		    }
	
		    model.addAttribute("brdMstrVO", master);
		    ////-----------------------------
		    
		    model.addAttribute("anonymous", "true");
	
		    return "clikMng/cop/bbs/EgovNoticeReply";
		}
	
		if (isAuthenticated) {
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    String atchFileId = "";
	
		    if (!files.isEmpty()) {
			List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, "", "Globals.bbsStorePath", boardVO.getBbsId());
			atchFileId = fileMngService.insertFileInfs(result);
		    }
	
		    board.setAtchFileId(atchFileId);
		    board.setReplyAt("Y");
		    board.setFrstRegisterId("ANONYMOUS");
		    board.setBbsId(board.getBbsId());
		    board.setParnts(Long.toString(boardVO.getNttId()));
		    board.setSortOrdr(boardVO.getSortOrdr());
		    board.setReplyLc(Integer.toString(Integer.parseInt(boardVO.getReplyLc()) + 1));
		    
		    // 익명게시판 관련
		    board.setNtcrNm(board.getNtcrNm());
		    board.setPassword(EgovFileScrty.encryptPassword(board.getPassword()));
		    
		    board.setNttCn(unscript(board.getNttCn()));	// XSS 방지
		    
		    bbsMngService.insertBoardArticle(board);
		}
		
		return "forward:/cop/bbs/anonymous/selectBoardList.do";
    }
    
    /**
     * 템플릿에 대한 미리보기용 게시물 목록을 조회한다.
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/previewBoardList.do")
    public String previewBoardArticles(@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {
    	// 0. 로그인 여부 확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}
    	
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String template = boardVO.getSearchWrd();	// 템플릿 URL
		
		BoardMasterVO master = new BoardMasterVO();
		
		master.setBbsNm("미리보기 게시판");
	
		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));
	
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());
	
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		BoardVO target = null;
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		target = new BoardVO();
		target.setNttSj("게시판 기능 설명");
		target.setFrstRegisterId("ID");
		target.setFrstRegisterNm("관리자");
		target.setFrstRegisterPnttm("2009-01-01");
		target.setInqireCo(7);
		target.setParnts("0");
		target.setReplyAt("N");
		target.setReplyLc("0");
		target.setUseAt("Y");
		
		list.add(target);
		
		target = new BoardVO();
		target.setNttSj("게시판 부가 기능 설명");
		target.setFrstRegisterId("ID");
		target.setFrstRegisterNm("관리자");
		target.setFrstRegisterPnttm("2009-01-01");
		target.setInqireCo(7);
		target.setParnts("0");
		target.setReplyAt("N");
		target.setReplyLc("0");
		target.setUseAt("Y");
		
		list.add(target);
		
		boardVO.setSearchWrd("");
	
		int totCnt = list.size();
		
		paginationInfo.setTotalRecordCount(totCnt);
	
		master.setTmplatCours(template);
		
		model.addAttribute("resultList", list);
		model.addAttribute("resultCnt", Integer.toString(totCnt));
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("brdMstrVO", master);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("preview", "true");
	
		return "clikMng/cop/bbs/EgovNoticeList";
    }
}
