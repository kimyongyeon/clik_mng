package clikmng.nanet.go.kr.uat.uia.web;

import clikmng.nanet.go.kr.cmm.CommonStringUtil;
import clikmng.nanet.go.kr.cmm.EgovMessageSource;
import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.annotation.IncludedInfo;
import clikmng.nanet.go.kr.cmm.enc.Encrypt;
import clikmng.nanet.go.kr.cmm.service.CmmUseService;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.uat.uia.service.EgovLoginService;
import clikmng.nanet.go.kr.uss.mng.service.DocsService;
import clikmng.nanet.go.kr.utl.sim.service.EgovClntInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/*
import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.servlet.GPKIHttpServletRequest;
import com.gpki.servlet.GPKIHttpServletResponse;
*/


/**
 * @author 
 * @since 
 * @version 
 * @see
 */

@Controller
public class EgovLoginController {

    /** EgovLoginService */
	@Resource(name = "loginService")
    private EgovLoginService loginService;
	
	/** EgovCmmUseService */
	@Resource(name="CmmUseService")
	private CmmUseService cmmUseService;
	
	/** DocsService */
	@Resource(name="DocsService")
	private DocsService docsService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	/** log */
    protected static final Log LOG = LogFactory.getLog(EgovLoginController.class);
    
	/**
	 * 로그인 화면으로 들어간다
	 * @param vo - 로그인후 이동할 URL이 담긴 LoginVO
	 * @return 로그인 페이지
	 * @exception Exception
	 */
    @IncludedInfo(name="로그인", listUrl="/uat/uia/LoginUsr.do", order = 10, gid = 10)
    @RequestMapping(value="/uat/uia/LoginUsr.do")
	public String loginUsrView(@ModelAttribute("loginVO") LoginVO loginVO,
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) 
			throws Exception {
    	
//    	// 1. Spring Security 사용자권한 처리
//    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
//    	if(!isAuthenticated) {
//
//    		model.addAttribute("message", "");
//        	//return "clikMng/uat/uia/LoginUsr";
//
//    	} /*else {
//
//    		String accessCode = request.getParameter("accessCode");
//    		if (accessCode != null && accessCode.equals("9")) {
//    			return "clikMng/cmm/error/pageAccessFailure";
//    		} else {
//    			return "forward:/cmm/dashBoard/DashBoard.do";
//    		}
//
//    	}*/

        return "clikMng/uat/uia/LoginUsr";
	}
	
    /**
	 * 일반(세션) 로그인을 처리한다
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 로그인결과(세션정보)
	 * @exception Exception
	 */
    @RequestMapping(value="/uat/uia/actionLogin.do")
    public String actionLogin(@ModelAttribute("loginVO") LoginVO loginVO2, 
    							HttpServletRequest request,
    							ModelMap model)
            throws Exception {
    	
    	String userId = null;
		String password = null;
		String ipAddr = EgovClntInfo.getClntIP(request);
    	
    	// 0. DOCS 서버를 조회하여 회원이 있으면 0, 없으면 1로 처리
    	//injection 취약점 예방을 위한 parameter 변환
 		userId		= CommonStringUtil.getPrmStrCnvr(request.getParameter("userId")); 
		password 	= CommonStringUtil.getPrmStrCnvr(request.getParameter("password"));
		
		//####################################로그인통과를 위해 임시조치##########################################
//		if (userId.equals("1234561234567")){
//			LoginVO resultVO = new LoginVO();
//			resultVO.setAuthorCode("SUPER_ADMIN");
//			resultVO.setMngrId("1234561234567");
//			resultVO.setMngrNm("테스터");
//        	request.getSession().setAttribute("loginVO", resultVO);
//    		return "redirect:/uat/uia/actionMain.do";			
//		}
		//####################################로그인통과를 위해 임시조치##########################################
		
		int userCnt = 0;
		

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("password", password);

		
		// 비직원 관리자 체크
		LoginVO loginVO = loginService.actionLogin(map);
		
		//System.out.println("password----------------------------->"+  resultVO.getMngrId() + " "+  resultVO.getMngrPw() + "------------------------------------> ");
		
		if ( loginVO != null ) { // 관리자정보 && 패스워드
			
			//직원
			if("1".equals(loginVO.getMngrSeCode())){
				userCnt = docsService.confirmUserAt(map);
				
				if(userCnt == 0){
		        	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
		        	return "clikMng/uat/uia/LoginUsr";					
				}
			}
			//일반
			else if("2".equals(loginVO.getMngrSeCode())){
				loginVO = loginService.actionLogin2(map);
				if ( loginVO != null ) {
					userCnt = 1;
				}
				else{
		        	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
		        	return "clikMng/uat/uia/LoginUsr";										
				}
				
			}
		}
		else	//로그인정보불일치
		{
        	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "clikMng/uat/uia/LoginUsr";
		}
		
		//IP 셋팅
		loginVO.setIp(EgovClntInfo.getClntIP(request));
		//세션에 loginVO 셋팅
       	request.getSession().setAttribute("loginVO", loginVO);
       	
   		return "redirect:/uat/uia/actionMain.do";		
		
//		if ( loginVO != null &&  loginVO.getMngrPw() != null ) { // 관리자정보 && 패스워드
//			if ( password.equals(loginVO.getMngrPw())){
//				// 일반관리자 로그인 처리
//				userCnt =1;
//			}
//			
//		// 국회직원 관리자 체크	
//		} else { 
//			//직원식별번호체계 변경으로 Docs에서 인코딩 후 비교
////			password 	= EncodeBySType(password);     //비밀번호 암호화
////			HashMap<String, String> map = new HashMap<String, String>();
////			map.put("userId", userId);
////			map.put("password", password);
//			userCnt = docsService.confirmUserAt(map);
//		}
//		
//    	// 1. 1인 회원 아이디를 clikdb를 조회하여 데이터를 조회한다.
//		// 2. 0인 경우 로그인 페이지로 RETURN.
//		if (userCnt == 0){
//			
//        	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
//        	return "clikMng/uat/uia/LoginUsr";
//
//		} else {
//			
//			// 로그인 처리			
//			// 0. 해당 아이디가 clik DB에 있는지 확인
//			// 1. 있으면 LoginVO에 이름, 아이디, 부서 등 정보를 담는다.
//			// 2. 없으면 로그인 페이지로 RETURN
//	        loginVO = loginService.actionLogin(map);
//	        loginVO.setIp(EgovClntInfo.getClntIP(request));
//	        
//	        if (loginVO != null && loginVO.getMngrId() != null && !loginVO.getMngrId().equals("")) {
//	        	
//	        	// 2-1. 로그인 정보를 세션에 저장
//	        	request.getSession().setAttribute("loginVO", loginVO);
//	    		return "redirect:/uat/uia/actionMain.do";
//	      
//	        } else {
//	        	
//	        	model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
//	        	return "clikMng/uat/uia/LoginUsr";
//	        }
//	        
//			
//		}
		
        
    }   
    
  
    
    /**
	 * 로그인 후 메인화면으로 들어간다
	 * @param 
	 * @return 로그인 페이지
	 * @exception Exception
	 */
    @RequestMapping(value="/uat/uia/actionMain.do")
	public String actionMain(ModelMap model) 
			throws Exception {
    	
    	// 1. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/uat/uia/LoginUsr.do";
    	}

    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	/*
    	// 2. 메뉴조회
		MenuManageVO menuManageVO = new MenuManageVO();
    	menuManageVO.setTmp_Id(user.getId());
    	menuManageVO.setTmp_UserSe(user.getUserSe());
    	menuManageVO.setTmp_Name(user.getName());
    	menuManageVO.setTmp_Email(user.getEmail());
    	menuManageVO.setTmp_OrgnztId(user.getOrgnztId());
    	menuManageVO.setTmp_UniqId(user.getUniqId());
    	List list_headmenu = menuManageService.selectMainMenuHead(menuManageVO);
		model.addAttribute("list_headmenu", list_headmenu);
    	*/
    	
		// 3. 메인 페이지 이동(관리자 대쉬보드로 이동)
    	
		//String main_page = Globals.MAIN_PAGE;
		
		/*LOG.debug("Globals.MAIN_PAGE > " +  Globals.MAIN_PAGE);
		LOG.debug("main_page > " +  main_page);
		LOG.debug("main_page > " +  main_page);
		LOG.debug("main_page > " +  main_page);
		LOG.debug("main_page > " +  main_page);
		LOG.debug("main_page > " +  main_page);
		LOG.debug("main_page > " +  main_page);
		LOG.debug("main_page > " +  main_page);
		LOG.debug("main_page > " +  main_page);*/
		
		return "forward:/cmm/dashBoard/DashBoard.do";
		
/*		
		if (main_page.startsWith("/")) {
		    return "forward:" + main_page;
		} else {
		    return main_page;
		}
*/
		/*
		if (main_page != null && !main_page.equals("")) {
			
			// 3-1. 설정된 메인화면이 있는 경우
			return main_page;
			
		} else {
			
			// 3-2. 설정된 메인화면이 없는 경우
			if (user.getUserSe().equals("USR")) {
	    		return "clikMng/EgovMainView";
	    	} else {
	    		return "clikMng/EgovMainViewG";
	    	}
		}
		*/
	}
    
    /**
	 * 로그아웃한다.
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/uat/uia/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) 
			throws Exception {
    	
    	String userIp = EgovClntInfo.getClntIP(request);
    	
    	// 1. Security 연동
    	//return "redirect:/j_spring_security_logout";
    	
    	request.getSession().setAttribute("loginVO", null);
    	
    	
    	return "redirect:/uat/uia/LoginUsr.do";
    }
    
	// EncodeBySType(strD1);
	public String EncodeBySType(String strData){
		String strRet = null;
		strRet = Encrypt.com_Encode(":" + strData + ":sisenc");
		return strRet;	
	}
	
	// SSO 복호화, 사용방법 : strD1	=	DecodeBySType(strEncArr);
	public String DecodeBySType(String strData){
		String strRet = null;
		int e, d, s, i=0;

		strRet = Encrypt.com_Decode(strData);
		e = strRet.indexOf(":");
		d = strRet.indexOf(":sisenc");
		strRet = strRet.substring(e+1, d);
		return strRet;
	}	    
}