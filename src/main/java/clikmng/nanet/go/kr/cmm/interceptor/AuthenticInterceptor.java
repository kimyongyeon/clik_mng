package clikmng.nanet.go.kr.cmm.interceptor;

import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.auth.AuthRollInfo;
import clikmng.nanet.go.kr.cmm.util.EgovUserDetailsHelper;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 인증여부 체크 인터셉터
 * @author 
 * @since 
 * @version 
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  </pre>
 */


public class AuthenticInterceptor extends HandlerInterceptorAdapter {
	
	private Set<String> permittedURL;
	
	private AuthRollInfo databaseAuthDefinitionSource;
	
	public void setPermittedURL(Set<String> permittedURL) {
		this.permittedURL = permittedURL;
	}
	
	/**
	 * 세션에 계정정보(LoginVO)가 있는지 여부로 인증 여부를 체크한다.
	 * 계정정보(LoginVO)가 없다면, 로그인 페이지로 이동한다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {	
		
		String requestURI = request.getRequestURI(); //요청 URI
		
		System.out.println("########requestURI######## : " + requestURI);
		
		boolean isPermittedURL = false;
		
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(loginVO != null){

			String myAuthCode = loginVO.getAuthorCode();

			for(Iterator<Map<String, Object>> it = databaseAuthDefinitionSource.getAuthUrlPattern().iterator(); it.hasNext();){

				Map<String, Object> map = (Map<String, Object>) it.next();
				String authCode = (String) map.get("AUTHORITY");
				String authPattern = (String) map.get("URL");

				if (myAuthCode.equals(authCode)) {

					String urlPattern = request.getContextPath() + authPattern;

					if(Pattern.matches(urlPattern, requestURI)) {// 정규표현식을 이용해서 요청 URI가 허용된 URL에 맞는지 점검함.
						isPermittedURL = false;
						break;
					} else {
						isPermittedURL = true;
					}
				} else {
					isPermittedURL = true;
				}
			}
			
		} else {

			for(Iterator<String> it = this.permittedURL.iterator(); it.hasNext();){
				String urlPattern = request.getContextPath() + (String) it.next();
				if(Pattern.matches(urlPattern, requestURI)){// 정규표현식을 이용해서 요청 URI가 허용된 URL에 맞는지 점검함.
					isPermittedURL = true;
					break;
				}
			}
			
		}
		String root = request.getContextPath();
		if(!isPermittedURL){
			response.sendRedirect(root);
			return false;
		}
		return true;

	}

	public AuthRollInfo getDatabaseAuthDefinitionSource() {
		return databaseAuthDefinitionSource;
	}

	public void setDatabaseAuthDefinitionSource(
			AuthRollInfo databaseAuthDefinitionSource) {
		this.databaseAuthDefinitionSource = databaseAuthDefinitionSource;
	}
}
