package clikmng.nanet.go.kr.sns.service.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.sns.service.SnsManageVO;
import clikmng.nanet.go.kr.sns.service.SnsVO;

/**
 * 개요
 * - SNS 소통센터에 대한 DAO를 정의한다.
 * 
 * 상세내용
 * - SNS 소통센터에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - SNS 소통센터의 조회기능은 목록조회, 상세조회로, 사용자화면 보기로 구분된다.
 */
@Repository("snsManageDAO")
public class SnsManageDAO extends EgovComAbstractDAO {

	public SnsManageDAO(){}

	/**
	 * SNS 소통센터를 관리하기 위해 등록된 SNS 소통센터목록을 조회한다.
	 * @param snsManageVO - SNS 소통센터 Vo
	 * @return List - SNS 소통센터 목록
	 * 
	 * @param snsManageVO
	 */
	public List selectSnsManageList(SnsManageVO snsManageVO) throws Exception {
	    return (List)list("SnsManage.selectSnsManage", snsManageVO);
	}
	
    /**
     * SNS 소통센터를 관리하기 위해 등록된 SNS 소통센터목록 총갯수를 조회한다.
     * @param snsManageVO - SNS 소통센터 Vo
     * @return List - SNS 소통센터 목록
     * 
     * @param snsManageVO
     */
    public int selectSnsManageListCount(SnsManageVO snsManageVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("SnsManage.selectSnsManageCnt", snsManageVO);
    }
        
	/**
	 * SNS 소통센터를 신규로 등록한다.
	 * @param snsManage
	 * @return boolean - 반영성공 여부
	 * 
	 * @param snsManage
	 */
	public void insertSnsManage(SnsManageVO snsManageVO) throws Exception {
	    insert("SnsManage.insertSnsManage", snsManageVO);
	}    
	
	/**
	 * SNS 코드 목록 
	 */
//	public List selectSnsCodeList() throws Exception {
//	    return (List)list("SnsManage.selectSnsCodeList", null);
//	}	
//    

	/**
	 * SNS 상세보기
	 * @param snsManageVO
	 * @return
	 * @throws Exception
	 */
	public SnsManageVO selectSnsDetail(SnsManageVO snsManageVO) throws Exception {
		return (SnsManageVO)getSqlMapClientTemplate().queryForObject("SnsManage.selectSnsDetail", snsManageVO);
	}
	
	/**
	 * 삭제한다.
	 */
	public void deleteSns(SnsManageVO snsManageVO) throws Exception {
	    update("SnsManage.deleteSnsManage", snsManageVO);
	}	
	
	/**
	 * 수정한다.
	 */
	public void updtSns(SnsManageVO snsManageVO) throws Exception {
	    update("SnsManage.updtSnsManage", snsManageVO);
	}
	
	
	/**
	 * SNS 최신글을 등록한다.
	 * @param snsVO
	 * @throws Exception
	 */
	public void insertSnsNbc(SnsVO snsVO) throws Exception {
		insert("SnsManage.insertSnsNbc", snsVO);
	}
	
	/**
	 * SNS 최신글을 삭제한다.
	 * @param snsVO
	 * @throws Exception
	 */
	public void deleteSnsNbc(SnsVO snsVO) throws Exception {
		delete("SnsManage.deleteSnsNbc", snsVO);
	}
	
	/**
	 * SNS 사용여부 수정
	 * @param snsVO
	 * @throws Exception
	 */
	public void updateSnsUseAt(SnsManageVO snsManageVO) throws Exception {
		delete("SnsManage.updateSnsUseAt", snsManageVO);
	}
	
}