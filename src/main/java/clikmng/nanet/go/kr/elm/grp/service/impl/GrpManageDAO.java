package clikmng.nanet.go.kr.elm.grp.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.elm.com.UserClassVO;

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
@Repository("GrpManageDAO")
public class GrpManageDAO extends EgovComAbstractDAO {
	
	/**
     * 환경설정 - 자동/수동 게시 목록 
     * @param searchVO
     * @return 그룹 목록
     * @throws Exception
     */
	public List<UserClassVO> selectElmGrpList()  throws Exception{
		return list("grpManageDAO.selectElmGrpList", null);
	}

    /**
     * 관리자 등록시 중복 아이디 체크
     */
    public int selectUserGroupIdChk(String userGroupId) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("grpManageDAO.selectUserGroupIdChk", userGroupId);
    }
	
	
	/**
	 * 관리자를 등록한다.
	 * @param mngVO
	 * 
	 */
	public void insertElmGrpRegist(UserClassVO userClassVO) throws Exception {
	    insert("grpManageDAO.insertElmGrpRegist", userClassVO);
	}      

	
	/**
	 * 관리자 상세내용 조회
	 * @param MngVO
	 * @return Object
	 * 
	 * @param mngVO
	 */
	public UserClassVO selectElmGrpDetail(UserClassVO userClassVO) throws Exception {
	    return (UserClassVO)getSqlMapClientTemplate().queryForObject("grpManageDAO.selectElmGrpDetail", userClassVO);
	}	
	
	/**
	 * 관리자 상세내용 수정 / 보안설정 테이블 수정
	 * @param MngVO
	 * @return Object
	 * 
	 * @param mngVO
	 */
	public void updateElmGrpDetail(UserClassVO userClassVO) throws Exception {
	    update("grpManageDAO.updateElmGrpDetail", userClassVO);
	}	
/*	
	public void editAuthorDetail(MngVO mngVO) throws Exception {
	    update("MngManager.editAuthorDetail", mngVO);
	}	
*/
	/**
	 * 관리자 상세내용 삭제 / 보안설정 삭제 
 	 * @param MngVO
	 * @return Object
	 * 
	 * @param mngVO
	 */
/*	
	public void delMngMappingManage(MngVO mngVO) throws Exception {
		delete("MngManager.delMngMappingManage", mngVO);
	}
*/	
	public void deleteElmGrpDetail(UserClassVO userClassVO) throws Exception {
	    delete("grpManageDAO.deleteElmGrpDetail", userClassVO);
	}	
		
}
