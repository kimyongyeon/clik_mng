package clikmng.nanet.go.kr.uat.uia.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.uat.uia.service.EgovLoginService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 구현 클래스
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
@Service("loginService")
public class EgovLoginServiceImpl extends AbstractServiceImpl implements
        EgovLoginService {

    @Resource(name="loginDAO")
    private LoginDAO loginDAO;
    
    /** EgovSndngMailRegistService */
//	@Resource(name = "sndngMailRegistService")
//    private EgovSndngMailRegistService sndngMailRegistService;
	
	
	
    
	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	public LoginVO actionLogin(HashMap<String, String> loginInfo) throws Exception {
		
		// 1. 아이디가 
		LoginVO loginVO = loginDAO.actionLogin(loginInfo);
		
//		// 1. 결과를 리턴한다.
//		if (loginVO != null) {
//			return loginVO;
//		} else {
//			//loginVO = new LoginVO();
//			return null;
//		}
		
		return loginVO;
		
	}
    
	
	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	public LoginVO actionLogin2(HashMap<String, String> loginInfo) throws Exception {
		
		// 1. 아이디가 
		LoginVO loginVO = loginDAO.actionLogin2(loginInfo);
		
//		// 1. 결과를 리턴한다.
//		if (loginVO != null) {
//			return loginVO;
//		} else {
//			//loginVO = new LoginVO();
//			return null;
//		}
		
		return loginVO;
		
	}	
    
}
