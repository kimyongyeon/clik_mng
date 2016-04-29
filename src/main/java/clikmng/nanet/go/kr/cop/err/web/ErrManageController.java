package clikmng.nanet.go.kr.cop.err.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

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
import clikmng.nanet.go.kr.cmm.CommonStringUtil;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.ImHttpRequestor;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.service.EgovProperties;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.cop.err.service.ErrManageDefaultVO;
import clikmng.nanet.go.kr.cop.err.service.ErrManageService;
import clikmng.nanet.go.kr.cop.err.service.ErrManageVO;
import clikmng.nanet.go.kr.mim.service.MimManageService;
import clikmng.nanet.go.kr.mim.service.MimManageVO;
import clikmng.nanet.go.kr.mim.web.MimManageController;
import clikmng.nanet.go.kr.utl.fcc.service.StringUtil;
import clikmng.nanet.go.kr.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

 


/**
 * 
 * 오류신고를 처리하는 Controller 클래스
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
public class ErrManageController {
	 
	 

	protected Log log = LogFactory.getLog(this.getClass());
	
    @Resource(name = "ErrManageService")
    private ErrManageService errManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	@Resource(name="CmmUseService")
	private CmmUseService cmmUseService;
        
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "MimManageService")
    private MimManageService mimManageService;
    
    // Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;
	
    /**
     * 오류신고 목록을 조회한다. (pageing)
     * @param searchVO
     * @param model
     * @return	"/cop/err/EgovErrListInqire"
     * @throws Exception
     */
    @IncludedInfo(name="오류신고관리", order = 550 ,gid = 50)
    @RequestMapping(value="/cop/err/ErrListInqire.do")
    public String selectErrList(@ModelAttribute("searchVO") ErrManageDefaultVO searchVO, ModelMap model) throws Exception {

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
		
        List<?> ErrList = errManageService.selectErrList(searchVO);
        model.addAttribute("resultList", ErrList);
        
    	// 인증여부 체크
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	//isAuthenticated = false;
    	    	
    	if(!isAuthenticated) {
    		
    		model.addAttribute("certificationAt", "N");   
    		    		
    	} else	{
    		
    		model.addAttribute("certificationAt", "Y");
    		
    	}
        
    	//오류신고 메일수신 담당자 목록
    	List<?> errChargerList = errManageService.selectErrChargerList();
    	model.addAttribute("errChargerList", errChargerList);
    	
        int totCnt = errManageService.selectErrListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "clikMng/cop/err/EgovErrListInqire";
    } 
    
    /**
     * 오류신고 목록에 대한 상세정보를 조회한다.
     * @param passwordConfirmAt
     * @param errManageVO
     * @param searchVO
     * @param model
     * @return	"/cop/err/EgovErrDetailInqire"
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrDetailInqire.do")
    public String	selectErrListDetail(@RequestParam("passwordConfirmAt") String passwordConfirmAt ,
    		ErrManageVO errManageVO,
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO,
            ModelMap model) throws Exception {  
    	
    	
		ErrManageVO vo = errManageService.selectErrListDetail(errManageVO);
				
		vo.setPasswordConfirmAt(passwordConfirmAt);    		// 작성비밀번호 확인여부
		
		// 작성 비밀번호를 얻는다.
		String	writngPassword = vo.getWritngPassword();		
		
		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 복호화한다.
    	vo.setWritngPassword(EgovFileScrty.decode(writngPassword));
				
		model.addAttribute("result", vo);
			
        return	"clikMng/cop/err/EgovErrDetailInqire";
    }

    /**
     * 오류신고 조회수를  수정처리한다.      
     * @param errManageVO
     * @param searchVO
     * @return	"forward:/cop/err/ErrDetailInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrInqireCoUpdt.do")
    public String updateErrInqireCo(
            ErrManageVO errManageVO,
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO) 
            throws Exception {
    	
    	errManageService.updateErrInqireCo(errManageVO);
            	        
        return "forward:/cop/err/ErrAnswerDetailInqire.do";
        
    }
    
    
    /**
     * 로그인/실명확인 처리
     * @param errManageVO
     * @param searchVO
     * @param model
     * @return	/cop/err/EgovLoginRealnmChoice
     * @throws Exception
     */
    @RequestMapping("/cop/err/LoginRealnmChoice.do")
    public String selectLoginRealnmChoice(
    		ErrManageVO errManageVO,
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO,
            Model	model)
            throws Exception {
    	    	
        model.addAttribute("ErrManageVO", new ErrManageVO());    	
    	        
        return "clikMng/cop/err/EgovErrLoginRealnmChoice";        
    }

    
    /**
     * 오류신고를 등록하기 위한 전 처리(인증체크)   
     * @param searchVO
     * @param errManageVO
     * @param model
     * @return	"/cop/err/EgovErrCnRegist"
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrCnRegistView.do")
    public String insertErrCnView(
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO, 
    		ErrManageVO errManageVO,
            Model model)
            throws Exception {


    	//-- 사용자 관련 개발 끝나면 다시 개발
    	// 인증여부 체크
    	//Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	Boolean isAuthenticated = true;
    	
    	if(!isAuthenticated) {
    		
    		model.addAttribute("result", errManageVO);
       		
    		return "clikMng/cop/err/EgovErrCnRegist";
    		
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
    		    	    			      
        errManageVO.setWrterNm(wrterNm);							// 작성자명
        errManageVO.setEmailAdres(emailAdres);    					// email 주소

                
        model.addAttribute("result", errManageVO);
        model.addAttribute("errManageVO", errManageVO);              
        
        return "clikMng/cop/err/EgovErrCnRegist";
                
    }
    
    /**
     * 오류신고를 등록한다.
     * @param searchVO
     * @param errManageVO
     * @param bindingResult
     * @return	"forward:/cop/err/ErrListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrCnRegist.do")
    public String insertErrCn(
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO,
            @ModelAttribute("errManageVO") ErrManageVO errManageVO,
            BindingResult bindingResult,
            ModelMap model)
            throws Exception {
    	
    	beanValidator.validate(errManageVO, bindingResult);
    	
		if(bindingResult.hasErrors()){
			
			return "clikMng/cop/err/EgovErrCnRegist";
						
		}
    	
		//-- 사용자 관련 개발 끝나면 다시 개발
    	// 로그인VO에서  사용자 정보 가져오기
    	// LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	// String	frstRegisterId = loginVO.getUniqId();
    	String	frstRegisterId = "TESTER";
		    	    			          			        
    	errManageVO.setFrstRegisterId(frstRegisterId);		// 최초등록자ID
    	errManageVO.setLastUpdusrId(frstRegisterId);    	// 최종수정자ID
    	
    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = errManageVO.getWritngPassword();
    	
    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	errManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));
    	
        errManageService.insertErrCn(errManageVO);        
		        
        return "forward:/cop/err/ErrAnswerListInqire.do";        
    }

    /**
     * 작성 비밀번호를 확인하기 위한 전 처리
     * @param errManageVO
     * @param searchVO
     * @param model
     * @return	"/cop/err/EgovErrPasswordConfirm"
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrPasswordConfirmView.do")
    public String selectPasswordConfirmView(
            ErrManageVO errManageVO,
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO,
            Model	model)
            throws Exception {
    	    	
        model.addAttribute("ErrManageVO", new ErrManageVO());    	
    	        
        return "clikMng/cop/err/EgovErrPasswordConfirm";        
    }
    
    /**
     * 수정을 위해 작성 비밀번호를 확인한다.
     * @param errManageVO
     * @param searchVO
     * @return	"forward:/cop/err/ErrDetailInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrPasswordConfirm.do")
    public String selectPasswordConfirm(
            ErrManageVO errManageVO,
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO,
            Model	model)            
            throws Exception {

    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = errManageVO.getWritngPassword();

    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	errManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));
    	    	    	
        int searchCnt = errManageService.selectErrPasswordConfirmCnt(errManageVO);
            	    	
        if ( searchCnt > 0) {		// 작성 비밀번호가 일치하는 경우
        	
        	// 오류신고 를 수정할 수 있는 화면으로 이동.
        	return	"forward:/cop/err/ErrCnUpdtView.do";
        	
        } else	{					// 작성비밀번호가 틀린경우
        
        	// 작성비밀번호 확인 결과 세팅.
        	//errManageVO.setPasswordConfirmAt("N");
        	
        	String	passwordConfirmAt = "N";
        	
            //model.addAttribute("ErrManageVO", errManageVO);
                    	
        	// 오류신고  상세조회 화면으로 이동.
        	return "forward:/cop/err/ErrAnswerDetailInqire.do?passwordConfirmAt=" + passwordConfirmAt;
        	
        	
        }
                        
    }    
    
    /**
     * 오류신고를 수정하기 위한 전 처리(비밀번호 암호화)       
     * @param errManageVO
     * @param searchVO
     * @param model
     * @return	"/cop/err/EgovErrCnUpdt
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrCnUpdtView.do")
    public String updateErrCnView(
    		ErrManageVO errManageVO,
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO, ModelMap model)
            throws Exception {    	    		        	
    	        
    	ErrManageVO vo = errManageService.selectErrListDetail(errManageVO);

		// 작성 비밀번호를 얻는다.
		String	writngPassword = vo.getWritngPassword();		
		
		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 복호화한다.
    	vo.setWritngPassword(EgovFileScrty.decode(writngPassword));
		
        // 복호화된 패스워드를 넘긴다.. 
        model.addAttribute("errManageVO", vo);
        
        // result에도 세팅(jstl 사용을 위해)
        model.addAttribute(selectErrListDetail("Y",errManageVO, searchVO, model));
    	        
        return "clikMng/cop/err/EgovErrCnUpdt";
    }

    /**
     * 오류신고를 수정처리한다.           
     * @param searchVO
     * @param errManageVO
     * @param bindingResult
     * @return	"forward:/cop/err/ErrListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrCnUpdt.do")
    public String updateErrCn(
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO, 
            @ModelAttribute("errManageVO") ErrManageVO errManageVO,
            BindingResult bindingResult)
            throws Exception {
    	    	
    	// Validation
    	beanValidator.validate(errManageVO, bindingResult);
    	
		if(bindingResult.hasErrors()){
			
			return "clikMng/cop/err/EgovErrCnUpdt";
						
		}    	
    	
		//-- 사용자 관련 개발 끝나면 다시 개발
    	// 로그인VO에서  사용자 정보 가져오기
/*		
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	String	lastUpdusrId = loginVO.getUniqId();
*/ 
		
    	//String	lastUpdusrId = "TESTER";
		    			            			        
    	errManageVO.setLastUpdusrId(errManageVO.getEmplyrNm());    	// 최종수정자ID

    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = errManageVO.getWritngPassword();
    	
    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	errManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));
    	
    	errManageService.updateErrCn(errManageVO);
            	        
        return "forward:/cop/err/ErrAnswerListInqire.do";
        
    }

    /**
     * 삭제을 위해 작성 비밀번호를 확인한다.
     * @param errManageVO
     * @param searchVO
     * @return	"forward:/cop/err/ErrDetailInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrPasswordConfirmDel.do")
    public String selectPasswordConfirmDel(
            ErrManageVO errManageVO,
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO,
            Model	model)            
            throws Exception {

    	// 작성비밀번호를 암호화 하기 위해서 Get
    	String	writngPassword = errManageVO.getWritngPassword();

    	// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
    	errManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));
    	    	    	
        int searchCnt = errManageService.selectErrPasswordConfirmCnt(errManageVO);
            	    	
        if ( searchCnt > 0) {		// 작성 비밀번호가 일치하는 경우
        	
        	// 오류신고 를 삭제
        	return	"forward:/cop/err/ErrCnDelete.do";
        	
        } else	{					// 작성비밀번호가 틀린경우
        
        	// 작성비밀번호 확인 결과 세팅.
        	//errManageVO.setPasswordConfirmAt("N");
        	
        	String	passwordConfirmAt = "N";
        	
            //model.addAttribute("ErrManageVO", errManageVO);
                    	
        	// 오류신고  상세조회 화면으로 이동.
        	return "forward:/cop/err/ErrDetailInqire.do?passwordConfirmAt=" + passwordConfirmAt;
        	
        	
        }
                        
    }    
    
    /**
     * 오류신고를 삭제처리한다.               
     * @param errManageVO
     * @param searchVO
     * @return	"forward:/cop/err/ErrListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrCnDelete.do")
    public String deleteErrCn(
            ErrManageVO errManageVO,
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO)
            throws Exception {
    	
    	errManageService.deleteErrCn(errManageVO);        
        
        return "forward:/cop/err/ErrAnswerListInqire.do";
    }

    /**
     * 오류신고 답변정보 목록을 조회한다. (pageing)
     * @param searchVO
     * @param model
     * @return	"/cop/err/EgovErrAnswerListInqire"
     * @throws Exception
     */
    @IncludedInfo(name="오류신고관리", order = 551 ,gid = 50)
    @RequestMapping(value="/cop/err/ErrAnswerListInqire.do")
    public String selectErrAnswerList(@ModelAttribute("searchVO") ErrManageDefaultVO searchVO, ModelMap model) throws Exception {
    	
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
//    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
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
    	
        List<?> ErrAnswerList = errManageService.selectErrAnswerList(searchVO);
        model.addAttribute("resultList", ErrAnswerList);
        
        int totCnt = errManageService.selectErrAnswerListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        //오류신고 메일수신 담당자 목록
    	List<?> errChargerList = errManageService.selectErrChargerList();
    	model.addAttribute("errChargerList", errChargerList);
    	
        return "clikMng/cop/err/EgovErrAnswerListInqire";
    } 
    
    /**
     * 오류신고 답변정보 목록에 대한 상세정보를 조회한다.
     * @param errManageVO
     * @param searchVO
     * @param model
     * @return	"/cop/err/EgovErrAnswerDetailInqire"
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrAnswerDetailInqire.do")
    public String	selectErrAnswerListDetail(
    		ErrManageVO errManageVO,
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO,
            ModelMap model) throws Exception {  
    	    	
    	
		ErrManageVO vo = errManageService.selectErrListDetail(errManageVO);
		
		//vo.setEmplyrNm("Emply");
		
		// 공통코드를 가져오기 위한 Vo
    	ComDefaultCodeVO vo2 = new ComDefaultCodeVO();
		vo2.setCodeId("RKS015");
		
		List<?> _result = cmmUseService.selectCmmCodeDetail(vo2);
		model.addAttribute("resultList", _result);
		
		model.addAttribute("result", vo);
			
        return	"clikMng/cop/err/EgovErrAnswerDetailInqire";
    }

        
    /**
     * 오류신고 답변정보를 수정하기 위한 전 처리(공통코드 처리)        
     * @param errManageVO
     * @param searchVO
     * @param model
     * @return	"/cop/err/EgovErrCnAnswerUpdt"
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrCnAnswerUpdtView.do")
    public String updateErrCnAnswerView(
    		ErrManageVO errManageVO,
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO, ModelMap model)
            throws Exception {    	    		        		        
  

    	// 공통코드를 가져오기 위한 Vo
    	ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("RKS015");
		
		List<?> _result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("resultList", _result);		
    	
        // 변수명은 CoC 에 따라 
        model.addAttribute(selectErrAnswerListDetail(errManageVO, searchVO, model));
        
        return "clikMng/cop/err/EgovErrCnAnswerUpdt";
    }

    /**
     * 오류신고 답변정보를 수정처리한다.          
     * @param errManageVO
     * @param searchVO
     * @return	"forward:/cop/err/ErrAnswerListInqire.do"
     * @throws Exception
     */
    @RequestMapping("/cop/err/ErrCnAnswerUpdt.do")
    public String updateErrCnAnswer(
            ErrManageVO errManageVO,
            @ModelAttribute("searchVO") ErrManageDefaultVO searchVO) 
            throws Exception {

    	//-- 사용자 관련 개발 끝나면 다시 개발
    	// 로그인VO에서  사용자 정보 가져오기
		
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	String lastUpdusrId = loginVO.getMngrId();

   
    	//String	lastUpdusrId = "TESTER";
    	errManageVO.setLastUpdusrId(lastUpdusrId);    	// 최종수정자ID    	

    	//System.out.println("==========================" + errManageVO.getEmplyrNm() );
    	
    	errManageVO.setAnswerCn(CommonStringUtil.getHtmlStrCnvr(URLDecoder.decode(errManageVO.getAnswerCn(), "UTF-8")));
    	
    	//처리상태코드(완료) 
    	errManageVO.setReportProcessSttusCode("3");
            	        
    	//닫변 내용 신고자에게 메일 전송
    	if(!errManageVO.getEmailAdres().equals("")){
    		MimManageVO mimManageVO = new MimManageVO();
    		ErrManageVO vo = errManageService.selectErrListDetail(errManageVO);
			try {
	    		//담당자 이메일
				mimManageVO.setEmail(vo.getEmailAdres());
	    		//제목
				mimManageVO.setSj("[국회.지방의회 의정자료 공유통합시스템][오류신고 답변]");
				//내용
				StringBuffer answerCn = new StringBuffer();
				
				answerCn.append("<!doctype html>																																																		");
				answerCn.append("<html lang='en'>                                                                                                                                                                                                    	");
				answerCn.append("	<head>                                                                                                                                                                                                           	");
				answerCn.append("		 <meta charset='UTF-8'>                                                                                                                                                                                      	");
				answerCn.append("		 <meta name='Generator' content='EditPlus®'>                                                                                                                                                                 	");
				answerCn.append("		 <meta name='Author' content=''>                                                                                                                                                                             	");
				answerCn.append("		 <meta name='Keywords' content=''>                                                                                                                                                                           	");
				answerCn.append("		 <meta name='Description' content=''>                                                                                                                                                                        	");
				answerCn.append("		 <title>Document</title>                                                                                                                                                                                     	");
				answerCn.append("	</head>                                                                                                                                                                                                          	");
				answerCn.append("	<body style='margin:0; padding:0; font-size:14px; color:#666; font-family:'맑은고딕', '돋움', Dotum, arial, sans-serif;'>                                                                                        		");
				answerCn.append("		<div id='question' style='margin:10px auto; width:710px; height:100%; border:1px solid #ccc; border-top:2px solid #ef522f; border-bottom:2px solid #ef522f; background:url(\"http://clik.nanet.go.kr/images/error/bg_err_q.png\")left 10px no-repeat;'>	");
				answerCn.append("			<h1 style='color:#383838; margin:25px 0 0 80px;'>신고내용</h1>                                                                                                                                             	");
				answerCn.append("			<p style='line-height:2em; margin:30px 0 30px 60px; padding:0 20px;'>                                                                                                                                      	");
				answerCn.append("			"+vo.getReportSj()+"                                                                                                                                                                                       	");
				answerCn.append("			<br>                                                                                                                                                                                                       	");
				answerCn.append("			"+vo.getReportCn()+"                                                                                                                                                                                       	");
				answerCn.append("			</p>                                                                                                                                                                                                       	");
				answerCn.append("		</div>                                                                                                                                                                                                         	");
				answerCn.append("		<div id='answer' style='margin:10px auto; width:710px; height:100%; border:1px solid #ccc; border-top:2px solid #ef522f; border-bottom:2px solid #ef522f; background:url(\"http://clik.nanet.go.kr/images/error/bg_err_a.png\")left 10px no-repeat;'>    	");
				answerCn.append("			<h1 style='color:#383838; margin:25px 0 0 80px;'>답변내용</h1>                                                                                                                                             	");
				answerCn.append("			<p style='line-height:2em; margin:30px 0 30px 60px; padding:0 20px;'>                                                                                                                                      	");
				answerCn.append("			"+errManageVO.getAnswerCn()+"	                                                                                                                                                                           	");
				answerCn.append("			</p>                                                                                                                                                                                                       	");
				answerCn.append("		</div>                                                                                                                                                                                                         	");
				answerCn.append("	</body>                                                                                                                                                                                                            	");
				answerCn.append("</html>                                                                                                                                                                                                               	");

				mimManageVO.setEmailCn(answerCn.toString());
				//발신자
				mimManageVO.setSendNm("clik@nanet.go.kr");
				
				mimManageVO.setMailRcver(vo.getWrterNm() + "(" + vo.getEmailAdres() + ")");
				
		    	// 로그인VO에서  사용자 정보 가져오기
		    	String frstRegisterId = loginVO.getMngrId();
		    	mimManageVO.setFrstRegisterId(frstRegisterId);			// 최초등록자ID
		    	mimManageVO.setLastUpdusrId(frstRegisterId);    		// 최종수정자ID
		
		    	mimManageService.insertSendMailInfo(mimManageVO);
		    	
		       	// 3. 메일 발송----------------------------------------------------------------------------------------
		    	String returnMsg = "";
				String from = StringUtil.isNullToString(mimManageVO.getSendNm()); //보내는사람
				String to = ""; 			//받는사람 - 메일주소
		
				String subject = StringUtil.isNullToString(mimManageVO.getSj()); //제목
				String body = StringUtil.isNullToString(mimManageVO.getEmailCn()); //내용
				String charset = "utf-8"; //인코딩
		    	
				subject = new String(subject.getBytes("utf-8"));
				body = new String(body.getBytes("utf-8"));
				
		
	    		// 수신자 이메일
	    		to = StringUtil.isNullToString(vo.getEmailAdres());
	    		
	    		boolean find = false;
	    		String cd = "";
	    		find = MimManageController.isEmailPattern(to);
	    		
	    		if(find == false){
	    			cd = "false";
	    		}
	    		
	        	// 이메일 유효성 검사
	        	if(cd.equals("false"))
	        	{
	    			System.out.println("OPENAPI 승인 메일 발송 실패 - 이메일 유효성 오류");
	    		}
	        	else
	        	{
	    	    	String hostAddress = EgovProperties.getProperty("Globals.HostAddress");
	    	    	String hostPort = EgovProperties.getProperty("Globals.Port");
	    	    	
	    	    	log.info("hostAddress = " + hostAddress + ", hostPort = " + hostPort);
	    	    	
	    			URL url = new URL("http://ems.nanet.go.kr/servlet/sendemailu");
	    	        InputStream is = null;
	    	        BufferedReader br = null;
	    	        ImHttpRequestor requestor = new ImHttpRequestor(url);
	    	    	requestor.addParameter("from", from);		// 보내는 사람
	    	    	requestor.addParameter("to", to);			// 받는사람 이메일
	    	    	requestor.addParameter("subject", subject);	// 제목
	    	    	requestor.addParameter("body", body);		// 본문
	    	    	requestor.addParameter("charset", charset);	
	    	    	
	    	        try {
	    	            is = requestor.sendMultipartPost();
	    	            br = new BufferedReader(new InputStreamReader(is));
	    	            String line = "";
	    	            while( (line=br.readLine())!= null ) {
	    	                returnMsg += line.trim();
	    	            }
	    	            System.out.println("returnMsg : " + returnMsg);
	    	            br.close();
	    	        } catch(Exception e){
	    	        	e.printStackTrace();
	    	        } finally {
	    	            if ( is != null ){
	    	            	try {
	    	            		is.close();
	    	            	}
	    	            	catch(Exception e){
	    	            		e.printStackTrace();
	    	            	}
	    	            }
	    	            if ( br != null ){
	    	            	try {
	    	            		br.close();
	    	            	}catch(Exception e){
	    	            		e.printStackTrace();
	    	            	}
	    	            }
	    	        }
	    		}    		
		    	
		    	// 실 수신자 수
		    	mimManageVO.setSendRejectCnt(1);
		    	
				// 메일 발송이 성공이면 메일 상태를 발송성공으로 수정 및 메일 수신거부자 수정
				mimManageService.updateSendMailStatus(mimManageVO);
				
				errManageVO.setEmailAnswerAt("Y");
	    	} catch (Exception e) {
	    		log.debug("::: EMAIL SEND ERROR ::: " + e.getMessage());
	    		e.printStackTrace();
				e.getMessage();
			}
			
    	}
    	
    	errManageService.updateErrCnAnswer(errManageVO);
    	
        return "forward:/cop/err/ErrAnswerListInqire.do";
        
    }

    /**
     * 관리자 목록을 조회한다.
     * @param searchVO
     * @param model
     * @return	관리자 목록
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/cop/err/selectMngList.do")
    public void selectMngList(
    		HttpServletResponse resp
    		, HttpServletRequest request)
            throws Exception {

     	List<HashMap<String,Object>> result = errManageService.selectMngList();
    	
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();
    	HashMap<String,Object> vo = null;
    	
    	for(int i=0; i<result.size(); i++) {
    		vo = result.get(i); 
    		jso = new JSONObject();
    		
			jso.put("MNGR_ID", vo.get("MNGR_ID"));
			jso.put("MNGR_NM", vo.get("MNGR_NM"));
			jso.put("MNGR_DEPT", vo.get("MNGR_DEPT"));
			jso.put("AUTHOR_CODE", vo.get("AUTHOR_CODE"));
			jso.put("MNGR_EMAIL", vo.get("MNGR_EMAIL"));
			jso.put("MNGR_SE_CODE", vo.get("MNGR_SE_CODE"));
			jso.put("ER_EMAIL_RECPTN_AT", vo.get("ER_EMAIL_RECPTN_AT"));
			
    		jarray.add(i, jso);
    	}
    	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());    
    }
    
    /**
     * 오류신고 메일수신 담당자 정보를 수정한다.
     * @param searchVO
     * @param model
     * @return	관리자 목록
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/cop/err/updateErrMailRcvrCharger.do")
    public void updateErrMailRcvrCharger(HttpServletResponse resp, HttpServletRequest request) throws Exception {
    	
    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	JSONObject jso = new JSONObject();    // JASON 객체생성
    	JSONArray jarray = new JSONArray();

    	HashMap<String,Object> paramMap = new HashMap<String, Object>();
    	String mngrId = request.getParameter("mngrId");
    	String[] mngrIdList = mngrId.split(",");
    	
    	//이전 정보 제거
    	paramMap.put("ER_EMAIL_RECPTN_AT", "N");
		paramMap.put("LAST_UPDUSR_ID", loginVO.getMngrId());
		errManageService.updateErrMailRcvrCharger(paramMap);
		
		//새로운 정보로 대체
    	try{
    		paramMap.put("ER_EMAIL_RECPTN_AT", "Y");
    		paramMap.put("LAST_UPDUSR_ID", loginVO.getMngrId());
	    	for(String vo : mngrIdList){
	    		if(!"".equals(vo)){
		    		paramMap.put("MNGR_ID", vo);
		    		errManageService.updateErrMailRcvrCharger(paramMap);
	    		}
	    	}
	    	jso.put("result_code", "success");
			jso.put("result_msg", "정상처리되었습니다.");
    	}catch(Exception e){
    		e.printStackTrace();
    		jso.put("result_code", "fail");
			jso.put("result_msg", e.getMessage());
    	}
    		
    	jarray.add(jso);    	
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();  
		out.print(jarray.toString());    
    }
}
