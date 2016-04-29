package clikmng.nanet.go.kr.cmm.interceptor;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
 
/**
 * 사용자IP 체크 인터셉터
 * @author 
 * @since 
 * @version 
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일     수정자          수정내용
 *  ----------  --------    ---------------------------
 *  </pre>
 */

public class IpObtainInterceptor extends HandlerInterceptorAdapter {
 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
 
		//System.out.println("-########################################################################################");
		
		String clientIp = request.getRemoteAddr();
 
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
 
		if (loginVO != null) {
			loginVO.setIp(clientIp);
		}
 
		return true;
	}
}
