package clikmng.nanet.go.kr.cmm.util;

import java.util.List;

import clikmng.nanet.go.kr.cmm.service.EgovUserDetailsService;

/**
 * EgovUserDetails Helper 클래스
 * 
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 * </pre>
 */

public class EgovUserDetailsHelper {
	
		static EgovUserDetailsService egovUserDetailsService;
		
		
		
	
		public EgovUserDetailsService getEgovUserDetailsService() {
			return egovUserDetailsService;
		}

		public void setEgovUserDetailsService(
				EgovUserDetailsService egovUserDetailsService) {
			this.egovUserDetailsService = egovUserDetailsService;
		}

		/**
		 * 인증된 사용자객체를 VO형식으로 가져온다.
		 * @return Object - 사용자 ValueObject
		 */
		public static Object getAuthenticatedUser() {
			return egovUserDetailsService.getAuthenticatedUser();
		}

		/**
		 * 인증된 사용자의 권한 정보를 가져온다.
		 * 
		 * @return List - 사용자 권한정보 목록
		 */
		public static List<String> getAuthorities() {
			

			return egovUserDetailsService.getAuthorities();
		}
		
		/**
		 * 인증된 사용자 여부를 체크한다.
		 * @return Boolean - 인증된 사용자 여부(TRUE / FALSE)	
		 */
		public static Boolean isAuthenticated() {
			
			return egovUserDetailsService.isAuthenticated();
		}
}
