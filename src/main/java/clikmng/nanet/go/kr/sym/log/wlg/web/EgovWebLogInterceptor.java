package clikmng.nanet.go.kr.sym.log.wlg.web;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.auth.AuthRollInfo;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import clikmng.nanet.go.kr.sym.log.wlg.service.EgovWebLogService;
import clikmng.nanet.go.kr.sym.log.wlg.service.WebLog;

/**
 * @Class Name : EgovWebLogInterceptor.java
 * @Description : 웹로그 생성을 위한 인터셉터 클래스
 * @Modification Information
 *
 *    수정일        수정자         수정내용
 *    -------      -------     -------------------
 *    2009. 3. 9.   이삼섭         최초생성
 *    2011. 7. 1.   이기하         패키지 분리(sym.log -> sym.log.wlg)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 9.
 * @version
 * @see
 *
 */
public class EgovWebLogInterceptor extends HandlerInterceptorAdapter {

	@Resource(name="EgovWebLogService")
	private EgovWebLogService webLogService;

	private Set<String> skipURL;
	
	public void setSkipURL(Set<String> skipURL) {
		this.skipURL = skipURL;
	}
	/**
	 * 웹 로그정보를 생성한다.
	 * 
	 * @param HttpServletRequest request, HttpServletResponse response, Object handler 
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView modeAndView) throws Exception {

		String requestURI = request.getRequestURI(); //요청 URI
		
		System.out.println("requestURI : " + requestURI + ", " + request.getRequestDispatcher("") + ", " + request.getRequestURL());
		boolean isSkipURL = false; 
		for(Iterator<String> it = this.skipURL.iterator(); it.hasNext();){
			String urlPattern = request.getContextPath() + (String) it.next();
			if(Pattern.matches(urlPattern, requestURI)){// 정규표현식을 이용해서 요청 URI가 허용된 URL에 맞는지 점검함.
				isSkipURL = true;
				break;
			}
		}	
		
		if(!isSkipURL) {
			
			WebLog webLog = new WebLog();
			String reqURL = request.getRequestURI();
			System.out.println("reqURL : " + reqURL);
			String mngrId = "";
			
	    	//Authenticated  
	        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(isAuthenticated.booleanValue()) {
				LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
				//uniqId = user.getUniqId();
				mngrId = user.getMngrId();
	    	}
	
		    // 사용자 OS 정보 조회
		    //String osInfo = EgovClntInfo.getClntOsInfo(request);
		    
	
			webLog.setUrl(reqURL);
			webLog.setRqesterId(mngrId);
			webLog.setRqesterIp(request.getRemoteAddr());
			
			webLogService.logInsertWebLog(webLog);
		}
	}
}
