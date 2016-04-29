package clikmng.nanet.go.kr.sit.spc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.sit.spc.service.SpcDefaultVO;
import clikmng.nanet.go.kr.sit.spc.service.SpcVO;

/**
 * 스페셜검색를 처리하는 DAO 클래스
 */
@Repository("SpcDAO")
public class SpcDAO extends EgovComAbstractDAO {

    /**
     * 스페셜검색 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectSpcList(SpcVO spcVO) throws Exception {
    	
        return list("SpcDAO.selectSpcList", spcVO);
        
    }

    /**
     * 스페셜검색 총 갯수를 조회한다.
     * @param searchVO
     * @return
     */
    public int selectSpcListTotCnt(SpcDefaultVO searchVO) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("SpcDAO.selectSpcListTotCnt", searchVO);
        
    }
    
	/**
	 * 스페셜검색를 등록한다.
	 * @param vo
	 * @throws Exception
	 */
    public void insertSpcInfo(SpcVO vo) throws Exception {
        insert("SpcDAO.insertSpcInfo", vo);
    }
    
    
    /**
	 * 스페셜검색  ::: 관련 키워드를 삭제한다.
	 * @param vo
	 * @throws Exception
	 */
    public void deleteKwrdInfo(SpcVO vo) throws Exception {
        delete("SpcDAO.deleteKwrdInfo", vo);
    }
    
    
	/**
	 * 스페셜검색  ::: 키워드를 등록한다.
	 * @param vo
	 * @throws Exception
	 */
    public void insertKwrdInfo(SpcVO vo) throws Exception {
        insert("SpcDAO.insertKwrdInfo", vo);
    }
    
    /**
     *  스페셜검색  ::: 상세내용을 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    public SpcVO selectSpcDetail(SpcVO vo) throws Exception {
    	
        return (SpcVO) selectByPk("SpcDAO.selectSpcDetail", vo);
        
    }
    
    /**
     *  스페셜검색  ::: 키워드 조회.
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectSpcDetailKwrd(SpcVO vo) throws Exception {
    	
        return list ("SpcDAO.selectSpcDetailKwrd", vo);
        
    }    
    
    
    
	/**
	 * 스페셜검색를 수정한다.
	 * @param vo
	 * @throws Exception
	 */
    public void updateSpcInfo(SpcVO vo) throws Exception {
    	
        update("SpcDAO.updateSpcInfo", vo);
        
    }

	/**
	 * 스페셜검색를 삭제한다.
	 * @param vo
	 * @throws Exception
	 */
    public void deleteSpcInfo(SpcVO vo) throws Exception {
    	
        delete("SpcDAO.deleteSpcInfo", vo);
        
    }
    
    
    /**
     * 스페셜검색 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectClCode(SpcDefaultVO searchVO) throws Exception {
    	
        return list("SpcDAO.selectClCode", searchVO);
        
    }

    /**
     * 스페셜검색 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectClDetailCode(SpcVO spcVO) throws Exception {
        return list("SpcDAO.selectClDetailCode", spcVO);
    }    
}
