package clikmng.nanet.go.kr.cop.qna.web;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import clikmng.nanet.go.kr.cmm.CommonStringUtil;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.cop.qna.service.QnaManageService;
import clikmng.nanet.go.kr.cop.qna.service.QnaManageDefaultVO;
import clikmng.nanet.go.kr.cop.qna.service.QnaManageVO;
import clikmng.nanet.go.kr.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

 


/**
 * 
 * Q&A를 처리하는 Controller 클래스
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일     	수정자           			수정내용
 *  ------------   --------    ---------------------------------------------
 *
 * </pre>
 */

@Controller
public class QnaManageController {
	 
	 

	protected Log log = LogFactory.getLog(this.getClass());
	
    @Resource(name = "QnaManageService")
    private QnaManageService qnaManageService;
    
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
     * 개별 배포시 메인메뉴를 조회한다.
     * @param model
     * @return	"/cop/qna/"
     * @throws Exception
     */
/*	
    @RequestMapping(value="/cop/qna/EgovMain.do")
    public String egovMain(ModelMap model) throws Exception {
    	return "clikMng/cop/qna/EgovMain";
    }
*/    
    /**
     * 메뉴를 조회한다.
     * @param model
     * @return	"/cop/qna/EgovLeft"
     * @throws Exception
     */
/*	
    @RequestMapping(value="/cop/qna/EgovLeft.do")
    public String egovLeft(ModelMap model) throws Exception {
    	return "clikMng/cop/qna/EgovLeft";
    }
*/    
    /**
     * Q&A정보 목록을 조회한다. (pageing)
     * @param searchVO
     * @param model
     * @return	"/cop/qna/EgovQnaListInqire"
     * @throws Exception
     */
    @IncludedInfo(name="Q&A관리", order = 550 ,gid = 50)
    @RequestMapping(value="/cop/qna/QnaListInqire.do")
    public String selectQnaList(@ModelAttribute("searchVO") QnaManageDefaultVO searchVO, ModelMap model) throws Exception {

    	searchVO.setPageUnit(searchVO.getPageUnit());
    	searchVO.setPageSize(searchVO.getPageSize());
    	
    	
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List QnaList = qnaManageService.selectQnaList(searchVO);
        model.addAttribute("resultList", QnaList);
        
    	// 인증여부 체크
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	//isAuthenticated = false;
    	    	
    	if(!isAuthenticated) {
    		
    		model.addAttribute("certificationAt", "N");   
    		    		
    	} else	{
    		
    		model.addAttribute("certificationAt", "Y");
    		
    	}
        
        
        int totCnt = qnaManageService.selectQnaListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "clikMng/cop/qna/EgovQnaListInqire";
    } 
    
    /**
     * Q&A정보 목록에 대한 상세정보를 조회한다.
     * @param passwordConfirmAt
     * @param qnaManageVO
     * @param searchVO
     * @param model
     * @return	"/cop/qna/EgovQnaDetailInqire"
     * @throws Exception
     */
    @RequestMapping("/cop/qna/QnaDetailInqire.do")
    public String	selectQnaListDetail(@RequestParam("passwordConfirmAt") String passwordConfirmAt ,
    		QnaManageVO qnaManageVO,
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO,
            ModelMap model) throws Exception {  
    	
    	
		QnaManageVO vo = qnaManageService.selectQnaListDetail(qnaManageVO);
				
		vo.setPasswordConfirmAt(passwordConfirmAt);    		// 작성비밀번호 확인여부
		
		// 작성 비밀번호를 얻는다.
		String	writngPassword = vo.getWritngPassword();		
		
		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 복호화한다.
    	vo.setWritngPassword(EgovFileScrty.decode(writngPassword));
				
		model.addAttribute("result", vo);
			
        return	"clikMng/cop/qna/EgovQnaDetailInqire";
    }

    /**
     * Q&A 조회수를  수정처리한다.      
     * @param qnaManageVO
     * @param searchVO
     * @return	"forward:/cop/qna/QnaDetailInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/qna/QnaInqireCoUpdt.do")
    public String updateQnaInqireCo(
            QnaManageVO qnaManageVO,
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO) 
            throws Exception {
    	
    	qnaManageService.updateQnaInqireCo(qnaManageVO);
            	        
        return "forward:/cop/qnm/QnaAnswerDetailInqire.do";
        
    }
    
    
    /**
     * 로그인/실명확인 처리
     * @param qnaManageVO
     * @param searchVO
     * @param model
     * @return	/cop/qna/EgovLoginRealnmChoice
     * @throws Exception
     */
    @RequestMapping("/cop/qna/LoginRealnmChoice.do")
    public String selectLoginRealnmChoice(
    		QnaManageVO qnaManageVO,
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO,
            Model	model)
            throws Exception {
    	    	
        model.addAttribute("QnaManageVO", new QnaManageVO());    	
    	        
        return "clikMng/cop/qna/EgovQnaLoginRealnmChoice";        
    }

    
    /**
     * Q&A정보를 등록하기 위한 전 처리(인증체크)   
     * @param searchVO
     * @param qnaManageVO
     * @param model
     * @return	"/cop/qna/EgovQnaCnRegist"
     * @throws Exception
     */
    @RequestMapping("/cop/qna/QnaCnRegistView.do")
    public String insertQnaCnView(
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO, 
    		QnaManageVO qnaManageVO,
            Model model)
            throws Exception {


    	//-- 사용자 관련 개발 끝나면 다시 개발
    	// 인증여부 체크
    	//Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	Boolean isAuthenticated = true;
    	
    	if(!isAuthenticated) {
    		
    		model.addAttribute("result", qnaManageVO);
       		
    		return "clikMng/cop/qna/EgovQnaCnRegist";
    		
    	}
    	 

    	//-- 사용자 관련 개발 끝나면 다시 개발
    	// 로그인VO에서  사용자 정보 가져오기
    	/*		
    	    LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	    	
        	String	wrterNm 	= loginVO.getName();					// 사용자명
        	String	emailAdres 	= loginVO.getEmail();					// email 주소

    	*/     	
    	
        String	wrterNm 	= "TESTER";					// 사용자명
        String	emailAdres 	= "tester@tester.co.kr";	// email 주소
    		    	    			      
        qnaManageVO.setWrterNm(wrterNm);							// 작성자명
        qnaManageVO.setEmailAdres(emailAdres);    					// email 주소

                
        model.addAttribute("result", qnaManageVO);
        model.addAttribute("qnaManageVO", qnaManageVO);              
        
        return "clikMng/cop/qna/EgovQnaCnRegist";
                
    }
    
    /**
     * Q&A정보를 등록한다.
     * @param searchVO
     * @param qnaManageVO
     * @param bindingResult
     * @return	"forward:/cop/qna/QnaListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/qna/QnaCnRegist.do")
    public String insertQnaCn(
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO,
            @ModelAttribute("qnaManageVO") QnaManageVO qnaManageVO,
            BindingResult bindingResult,
            ModelMap model)
            throws Exception {
    	
    	beanValidator.validate(qnaManageVO, bindingResult);
    	
		if(bindingResult.hasErrors()){
			
			return "clikMng/cop/qna/EgovQnaCnRegist";
						
		}
    	
		//-- 사용자 관련 개발 끝나면 다시 개발
    	// 로그인VO에서  사용자 정보 가져오기
    	// LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	// String	frstRegisterId = loginVO.getUniqId();
    	String	frstRegisterId = "TESTER";
		    	    			          			        
    	qnaManageVO.setFrstRegisterId(frstRegisterId);		// 최초등록자ID
    	qnaManageVO.setLastUpdusrId(frstRegisterId);    	// 최종수정자ID
    	
    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = qnaManageVO.getWritngPassword();
    	
    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	qnaManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));
    	
        qnaManageService.insertQnaCn(qnaManageVO);        
		        
        return "forward:/cop/qnm/QnaAnswerListInqire.do";        
    }

    /**
     * 작성 비밀번호를 확인하기 위한 전 처리
     * @param qnaManageVO
     * @param searchVO
     * @param model
     * @return	"/cop/qna/EgovQnaPasswordConfirm"
     * @throws Exception
     */
    @RequestMapping("/cop/qna/QnaPasswordConfirmView.do")
    public String selectPasswordConfirmView(
            QnaManageVO qnaManageVO,
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO,
            Model	model)
            throws Exception {
    	    	
        model.addAttribute("QnaManageVO", new QnaManageVO());    	
    	        
        return "clikMng/cop/qna/EgovQnaPasswordConfirm";        
    }
    
    /**
     * 수정을 위해 작성 비밀번호를 확인한다.
     * @param qnaManageVO
     * @param searchVO
     * @return	"forward:/cop/qna/QnaDetailInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/qna/QnaPasswordConfirm.do")
    public String selectPasswordConfirm(
            QnaManageVO qnaManageVO,
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO,
            Model	model)            
            throws Exception {

    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = qnaManageVO.getWritngPassword();

    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	qnaManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));
    	    	    	
        int searchCnt = qnaManageService.selectQnaPasswordConfirmCnt(qnaManageVO);
            	    	
        if ( searchCnt > 0) {		// 작성 비밀번호가 일치하는 경우
        	
        	// Q&A를 수정할 수 있는 화면으로 이동.
        	return	"forward:/cop/qna/QnaCnUpdtView.do";
        	
        } else	{					// 작성비밀번호가 틀린경우
        
        	// 작성비밀번호 확인 결과 세팅.
        	//qnaManageVO.setPasswordConfirmAt("N");
        	
        	String	passwordConfirmAt = "N";
        	
            //model.addAttribute("QnaManageVO", qnaManageVO);
                    	
        	// Q&A 상세조회 화면으로 이동.
        	return "forward:/cop/qnm/QnaAnswerDetailInqire.do?passwordConfirmAt=" + passwordConfirmAt;
        	
        	
        }
                        
    }    
    
    /**
     * Q&A정보를 수정하기 위한 전 처리(비밀번호 암호화)       
     * @param qnaManageVO
     * @param searchVO
     * @param model
     * @return	"/cop/qna/EgovQnaCnUpdt
     * @throws Exception
     */
    @RequestMapping("/cop/qna/QnaCnUpdtView.do")
    public String updateQnaCnView(
    		QnaManageVO qnaManageVO,
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO, ModelMap model)
            throws Exception {    	    		        	
    	        
    	QnaManageVO vo = qnaManageService.selectQnaListDetail(qnaManageVO);

		// 작성 비밀번호를 얻는다.
		String	writngPassword = vo.getWritngPassword();		
		
		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 복호화한다.
    	vo.setWritngPassword(EgovFileScrty.decode(writngPassword));
		
        // 복호화된 패스워드를 넘긴다.. 
        model.addAttribute("qnaManageVO", vo);
        
        // result에도 세팅(jstl 사용을 위해)
        model.addAttribute(selectQnaListDetail("Y",qnaManageVO, searchVO, model));
    	        
        return "clikMng/cop/qna/EgovQnaCnUpdt";
    }

    /**
     * Q&A정보를 수정처리한다.           
     * @param searchVO
     * @param qnaManageVO
     * @param bindingResult
     * @return	"forward:/cop/qna/QnaListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/qna/QnaCnUpdt.do")
    public String updateQnaCn(
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO, 
            @ModelAttribute("qnaManageVO") QnaManageVO qnaManageVO,
            BindingResult bindingResult)
            throws Exception {
    	    	
    	// Validation
    	beanValidator.validate(qnaManageVO, bindingResult);
    	
		if(bindingResult.hasErrors()){
			
			return "clikMng/cop/qna/EgovQnaCnUpdt";
						
		}    	
    	
		//-- 사용자 관련 개발 끝나면 다시 개발
    	// 로그인VO에서  사용자 정보 가져오기
/*		
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	String	lastUpdusrId = loginVO.getUniqId();
*/ 
		
    	//String	lastUpdusrId = "TESTER";
		    			            			        
    	qnaManageVO.setLastUpdusrId(qnaManageVO.getEmplyrNm());    	// 최종수정자ID

    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = qnaManageVO.getWritngPassword();
    	
    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	qnaManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));
    	
    	qnaManageService.updateQnaCn(qnaManageVO);
            	        
        return "forward:/cop/qnm/QnaAnswerListInqire.do";
        
    }

    /**
     * 삭제을 위해 작성 비밀번호를 확인한다.
     * @param qnaManageVO
     * @param searchVO
     * @return	"forward:/cop/qna/QnaDetailInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/qna/QnaPasswordConfirmDel.do")
    public String selectPasswordConfirmDel(
            QnaManageVO qnaManageVO,
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO,
            Model	model)            
            throws Exception {

    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = qnaManageVO.getWritngPassword();

    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	qnaManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));
    	    	    	
        int searchCnt = qnaManageService.selectQnaPasswordConfirmCnt(qnaManageVO);
            	    	
        if ( searchCnt > 0) {		// 작성 비밀번호가 일치하는 경우
        	
        	// Q&A를 삭제
        	return	"forward:/cop/qna/QnaCnDelete.do";
        	
        } else	{					// 작성비밀번호가 틀린경우
        
        	// 작성비밀번호 확인 결과 세팅.
        	//qnaManageVO.setPasswordConfirmAt("N");
        	
        	String	passwordConfirmAt = "N";
        	
            //model.addAttribute("QnaManageVO", qnaManageVO);
                    	
        	// Q&A 상세조회 화면으로 이동.
        	return "forward:/cop/qna/QnaDetailInqire.do?passwordConfirmAt=" + passwordConfirmAt;
        	
        	
        }
                        
    }    
    
    /**
     * Q&A정보를 삭제처리한다.               
     * @param qnaManageVO
     * @param searchVO
     * @return	"forward:/cop/qna/QnaListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/qna/QnaCnDelete.do")
    public String deleteQnaCn(
            QnaManageVO qnaManageVO,
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO)
            throws Exception {
    	
    	qnaManageService.deleteQnaCn(qnaManageVO);        
        
        return "forward:/cop/qnm/QnaAnswerListInqire.do";
    }

    /**
     * Q&A답변정보 목록을 조회한다. (pageing)
     * @param searchVO
     * @param model
     * @return	"/cop/qna/EgovQnaAnswerListInqire"
     * @throws Exception
     */
    @IncludedInfo(name="Q&A답변관리", order = 551 ,gid = 50)
    @RequestMapping(value="/cop/qnm/QnaAnswerListInqire.do")
    public String selectQnaAnswerList(@ModelAttribute("searchVO") QnaManageDefaultVO searchVO, ModelMap model) throws Exception {
    	
    	/** EgovPropertyService.SiteList */
    	searchVO.setPageUnit(searchVO.getPageUnit());
    	searchVO.setPageSize(searchVO.getPageSize());
    	
    	
    	System.out.println("======================================>" + searchVO.getPageUnit() + "");
    	
    	
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        

		//-- 사용자 관련 개발 끝나면 다시 개발
		// 인증여부 체크
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	//isAuthenticated = false;
/*    	    	
    	if(!isAuthenticated) {
    		model.addAttribute("certificationAt", "N");   
    	} else	{
    		model.addAttribute("certificationAt", "Y");
    	}
*/		
    	// 수정 시 LOGIN 창 수정
    	model.addAttribute("certificationAt", "Y");
    	
        List QnaAnswerList = qnaManageService.selectQnaAnswerList(searchVO);
        model.addAttribute("resultList", QnaAnswerList);
        
        int totCnt = qnaManageService.selectQnaAnswerListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "clikMng/cop/qna/EgovQnaAnswerListInqire";
    } 
    
    /**
     * Q&A답변정보 목록에 대한 상세정보를 조회한다.
     * @param qnaManageVO
     * @param searchVO
     * @param model
     * @return	"/cop/qna/EgovQnaAnswerDetailInqire"
     * @throws Exception
     */
    @RequestMapping("/cop/qnm/QnaAnswerDetailInqire.do")
    public String	selectQnaAnswerListDetail(
    		QnaManageVO qnaManageVO,
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO,
            ModelMap model) throws Exception {  
    	    	
    	
		QnaManageVO vo = qnaManageService.selectQnaListDetail(qnaManageVO);
		
		//vo.setEmplyrNm("Emply");
		
		// 공통코드를 가져오기 위한 Vo
    	ComDefaultCodeVO vo2 = new ComDefaultCodeVO();
		vo2.setCodeId("RKS015");
		
		List<?> _result = cmmUseService.selectCmmCodeDetail(vo2);
		model.addAttribute("resultList", _result);
		
		model.addAttribute("result", vo);
			
        return	"clikMng/cop/qna/EgovQnaAnswerDetailInqire";
    }

        
    /**
     * Q&A답변정보를 수정하기 위한 전 처리(공통코드 처리)        
     * @param qnaManageVO
     * @param searchVO
     * @param model
     * @return	"/cop/qna/EgovQnaCnAnswerUpdt"
     * @throws Exception
     */
    @RequestMapping("/cop/qnm/QnaCnAnswerUpdtView.do")
    public String updateQnaCnAnswerView(
    		QnaManageVO qnaManageVO,
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO, ModelMap model)
            throws Exception {    	    		        		        
  

    	// 공통코드를 가져오기 위한 Vo
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("RKS015");
		
		List _result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("resultList", _result);		
    	
        // 변수명은 CoC 에 따라 
        model.addAttribute(selectQnaAnswerListDetail(qnaManageVO, searchVO, model));
        
        return "clikMng/cop/qna/EgovQnaCnAnswerUpdt";
    }

    /**
     * Q&A답변정보를 수정처리한다.          
     * @param qnaManageVO
     * @param searchVO
     * @return	"forward:/cop/qnm/QnaAnswerListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/qnm/QnaCnAnswerUpdt.do")
    public String updateQnaCnAnswer(
            QnaManageVO qnaManageVO,
            @ModelAttribute("searchVO") QnaManageDefaultVO searchVO) 
            throws Exception {

    	//-- 사용자 관련 개발 끝나면 다시 개발
    	// 로그인VO에서  사용자 정보 가져오기
		
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	String lastUpdusrId = loginVO.getMngrId();

   
    	//String	lastUpdusrId = "TESTER";
    	qnaManageVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID    	

    	//System.out.println("==========================" + qnaManageVO.getEmplyrNm() );
    	
    	qnaManageVO.setAnswerCn(CommonStringUtil.getHtmlStrCnvr(URLDecoder.decode(qnaManageVO.getAnswerCn(), "UTF-8")));
    	
    	qnaManageService.updateQnaCnAnswer(qnaManageVO);
            	        
        return "forward:/cop/qnm/QnaAnswerListInqire.do";
        
    }

	
}
