package clikmng.nanet.go.kr.cmm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import clikmng.nanet.go.kr.cmm.service.EgovUserDetailsService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

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

public class EgovUserDetailsSessionServiceImpl extends AbstractServiceImpl implements
		EgovUserDetailsService {

	public Object getAuthenticatedUser() {

	

		return RequestContextHolder.getRequestAttributes().getAttribute("loginVO", RequestAttributes.SCOPE_SESSION);

	}

	public List<String> getAuthorities() {

		// 권한 설정을 리턴한다.
		List<String> listAuth = new ArrayList<String>();

		return listAuth;
	}

	public Boolean isAuthenticated() {
		// 인증된 유저인지 확인한다.

		if (RequestContextHolder.getRequestAttributes() == null) {
			return false;
		} else {

			if (RequestContextHolder.getRequestAttributes().getAttribute(
					"loginVO", RequestAttributes.SCOPE_SESSION) == null) {
				return false;
			} else {
				return true;
			}
		}


	}

}
