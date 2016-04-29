package clikmng.nanet.go.kr.elm.gup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantRequestVO;
import clikmng.nanet.go.kr.elm.com.UserClassDtaseVO;
import clikmng.nanet.go.kr.elm.gup.service.ElmGupListVO;
import clikmng.nanet.go.kr.elm.gup.service.ElmGupDetailVO;


/**
 * 
 * 환경설정
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */
@Repository("GupManageDAO")
public class GupManageDAO extends EgovComAbstractDAO {
	
	/**
     * 환경설정 - 자동/수동 게시 목록 
     * @param searchVO
     * @return 그룹 목록
     * @throws Exception
     */
	public List<ElmGupListVO> selectElmGupList()  throws Exception{
		return list("gupManageDAO.selectElmGupList", null);
	}

	public List<UserClassVO> selectUserClass()  throws Exception{
		return list("gupManageDAO.selectUserClass", null);
	}


	public List<UserClassDtaseVO> selectUserClassDtase(ElmGupDetailVO vo)  throws Exception{
		return list("gupManageDAO.selectUserClassDtase", vo);
	}

	public List<ReadGrantRequestVO> selectReadGrantRequest(ElmGupDetailVO vo)  throws Exception{
		return list("gupManageDAO.selectReadGrantRequest", vo);
	}

	public void insertUserClassDtase(UserClassDtaseVO vo) throws Exception {
	    insert("gupManageDAO.insertUserClassDtase", vo);
	}      

	
	public void deleteUserClassDtase(ElmGupDetailVO vo) throws Exception {
	    delete("gupManageDAO.deleteUserClassDtase", vo);
	}	

	public void insertReadGrantRequest(ReadGrantRequestVO vo) throws Exception {
	    insert("gupManageDAO.insertReadGrantRequest", vo);
	}      

	
	public void deleteReadGrantRequest(ElmGupDetailVO vo) throws Exception {
	    delete("gupManageDAO.deleteReadGrantRequest", vo);
	}	


}
