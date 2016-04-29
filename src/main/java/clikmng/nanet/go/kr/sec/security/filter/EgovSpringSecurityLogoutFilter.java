package clikmng.nanet.go.kr.sec.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * 개정이력(Modification Information) 
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  
 *  </pre>
 */

public class EgovSpringSecurityLogoutFilter implements Filter{
	private FilterConfig config;
	
	protected final static Log LOG = LogFactory.getLog(EgovSpringSecurityLogoutFilter.class);

	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		String requestURL = ((HttpServletRequest)request).getRequestURI();	
		LOG.debug(requestURL);
		
		((HttpServletRequest)request).getSession().setAttribute("loginVO", null);
		((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/j_spring_security_logout");

			
	}

	public void init(FilterConfig filterConfig) throws ServletException {

		this.config = filterConfig;

		
	}
}
