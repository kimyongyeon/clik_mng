package clikmng.nanet.go.kr.rcm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.rcm.service.RcmManageDefaultVO;
import clikmng.nanet.go.kr.rcm.service.RcmManageVO;

/**
 * 
 * 사이트정보를 처리하는 DAO 클래스
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */
@Repository("RcmManageDAO")
public class RcmManageDAO extends EgovComAbstractDAO {


    /**
     * 사이트 목록에 대한 상세내용을 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    public RcmManageVO selectRcmDetail(RcmManageVO vo) throws Exception {
    	
        return (RcmManageVO) selectByPk("RcmManageDAO.selectRcmDetail", vo);
        
    }

    /**
     * 인증키 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List<?> selectAilList(RcmManageDefaultVO searchVO) throws Exception {
    	
        return list("RcmManageDAO.selectAilList", searchVO);
        
    }

    /**
     * 인증키 총 갯수를 조회한다.
     * @param searchVO
     * @return
     */
    public int selectAilListTotCnt(RcmManageDefaultVO searchVO) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("RcmManageDAO.selectAilListTotCnt", searchVO);
        
    }
    
    
    /**
     *  인증키 목록에 대한 상세내용을 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    public RcmManageVO selectAiDetail(RcmManageVO vo) throws Exception {
    	
        return (RcmManageVO) selectByPk("RcmManageDAO.selectAiDetail", vo);
        
    }
    
    
	/**
	 * 인증키정보를 수정한다.
	 * @param vo
	 * @throws Exception
	 */
    public int updateAiDetail(RcmManageVO vo) throws Exception {
    	
        return update("RcmManageDAO.updateAiDetail", vo);
        
    }

	/**
	 * 인증키정보를 삭제한다.
	 * @param vo
	 * @throws Exception
	 */
    public int deleteAiDetail(RcmManageVO vo) throws Exception {
    	
    	return update("RcmManageDAO.deleteAiDetail", vo);
        
    }
    
    
    /**
     * 인증키 이용 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List<?> selectAuList(RcmManageDefaultVO searchVO) throws Exception {
    	
        return list("RcmManageDAO.selectAuList", searchVO);
        
    }

    /**
     * 인증키 이용 총 갯수를 조회한다.
     * @param searchVO
     * @return
     */
    public int selectAuListTotCnt(RcmManageDefaultVO searchVO) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("RcmManageDAO.selectAuListTotCnt", searchVO);
        
    }
}
