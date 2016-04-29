package clikmng.nanet.go.kr.uss.mng.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.sns.service.SnsManageVO;
import clikmng.nanet.go.kr.uss.mng.service.MngManage;
import clikmng.nanet.go.kr.uss.mng.service.MngVO;

@Repository("MngDao")
public class MngDao extends EgovComAbstractDAO{

	/**
	 * 관리자 목록을 조회한다.
	 * @param mngVO 
	 * @return List
	 * 
	 * @param mngVO
	 */
	public List selectMngList(MngVO mngVO) throws Exception {
	    return (List)list("MngManager.selectMngList", mngVO);
	}
	
    /**
     * 관리자 목록 갯수를 조회한다.
     * @param mngVO
     * @return int
     * 
     * @param mngVO
     */
    public int selectMngListCnt(MngVO mngVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("MngManager.selectMngListCnt", mngVO);
    }
	
	/**
	 * 관리자를 등록한다.
	 * @param mngVO
	 * 
	 */
    
    public int selectMngId(MngVO mngVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("MngManager.selectMngId", mngVO);
	}      
    
	public void insertMngManage(MngVO mngVO) throws Exception {
	    insert("MngManager.insertMngManage", mngVO);
	}      
	public void insertMngMappingManage(MngVO mngVO) throws Exception {
	    insert("MngManager.insertMngMappingManage", mngVO);
	}      	
	
	/**
	 * 관리자 권한목록을 조회한다.
	 * @param MngVO
	 * @return List
	 * 
	 * @param mngVO
	 */
	public List selectAuthorList(MngVO mngVO) throws Exception {
	    return (List)list("MngManager.selectAuthorList", mngVO);
	}	
	
	/**
	 * 관리자 상세내용 조회
	 * @param MngVO
	 * @return Object
	 * 
	 * @param mngVO
	 */
	public MngManage selectMngDetail(MngVO mngVO) throws Exception {
	    return (MngManage)getSqlMapClientTemplate().queryForObject("MngManager.selectMngDetail", mngVO);
	}	
	
	/**
	 * 관리자 상세내용 수정 / 보안설정 테이블 수정
	 * @param MngVO
	 * @return Object
	 * 
	 * @param mngVO
	 */
	public void editMngDetail(MngVO mngVO) throws Exception {
	    update("MngManager.editMngDetail", mngVO);
	}	
	
	public void editMngDetailWithPassword(MngVO mngVO) throws Exception {
	    update("MngManager.editMngDetailWithPassword", mngVO);
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
	public void delMngManage(MngVO mngVO) throws Exception {
	    delete("MngManager.delMngManage", mngVO);
	}	
	
	
	/** ---------------- 지방의회/지자체 관리자 시작 ----------------  */
	/**
	 * 지방의회/지자체 목록을 조회한다.
	 * @param mngVO 
	 * @return List
	 * 
	 * @param mngVO
	 */
	public List selectLocalMngList(MngVO mngVO) throws Exception {
	    return (List)list("MngManager.selectLocalMngList", mngVO);
	}
	
    /**
     * 지방의회/지자체 목록 갯수를 조회한다.
     * @param mngVO
     * @return int
     * 
     * @param mngVO
     */
    public int selectLocalMngListCnt(MngVO mngVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("MngManager.selectLocalMngListCnt", mngVO);
    }
    
	/**
	 * 지방의회/지자체 상세내용 조회
	 * @param MngVO
	 * @return Object
	 * 
	 * @param mngVO
	 */
	public MngVO selectLocalMngDetail(MngVO mngVO) throws Exception {
	    return (MngVO)getSqlMapClientTemplate().queryForObject("MngManager.selectLocalMngDetail", mngVO);
	}	
	
	/**
	 * 지방의회/지자체 관리자 승인
	 * @param mngVO
	 * @throws Exception
	 */
	public void updateApprovalLocalMng(MngVO mngVO) throws Exception{
		update ("MngManager.updateApprovalLocalMng", mngVO);
	}    
	
	/**
	 * 지방의회/지자체 관리자 수정처리
	 * @param mngVO
	 * @throws Exception
	 */
	public void updateLocalMngDetail(MngVO mngVO) throws Exception{
		update("MngManager.updateLocalMngDetail", mngVO);
	}
	
	/**
	 * 지방의회/지자체 관리자 삭제처리
	 * @param mngVO
	 * @throws Exception
	 */
	public void deleteLocalMngDetail(MngVO mngVO) throws Exception{
		update("MngManager.deleteLocalMngDetail", mngVO);
	}
	
}
