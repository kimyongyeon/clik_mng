package clikmng.nanet.go.kr.uat.uia.service.impl;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.LoginVO;
import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;

/**
 * 일반 로그인 DAO 클래스 
 * @author 
 * @since 
 * @version 1.0
 * @see
 */
@Repository("loginDAO")
public class LoginDAO extends EgovComAbstractDAO {
	
	/** log */
    protected static final Log LOG = LogFactory.getLog(LoginDAO.class);
    
	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO actionLogin(HashMap<String, String> loginInfo) throws Exception {
    	return (LoginVO)selectByPk("loginDAO.actionLogin", loginInfo);
    }
    
	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO actionLogin2(HashMap<String, String> loginInfo) throws Exception {
    	return (LoginVO)selectByPk("loginDAO.actionLogin2", loginInfo);
    }    
}
