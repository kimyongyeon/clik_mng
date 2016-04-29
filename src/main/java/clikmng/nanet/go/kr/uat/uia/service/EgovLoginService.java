package clikmng.nanet.go.kr.uat.uia.service;

import java.util.HashMap;

import clikmng.nanet.go.kr.cmm.LoginVO;

/**
 * 일반 로그인
 * @author 
 * @since 
 * @version 1.0
 * @see
 *  
 */
public interface EgovLoginService {
	
	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    LoginVO actionLogin(HashMap<String, String> loginInfo) throws Exception;
    
    LoginVO actionLogin2(HashMap<String, String> loginInfo) throws Exception;
    
}
