package clikmng.nanet.go.kr.sit.log.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.sit.log.service.LogManageDefaultVO;
import clikmng.nanet.go.kr.sit.log.service.LogManageVO;

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
@Repository("LogManageDAO")
public class LogManageDAO extends EgovComAbstractDAO {

	
    /**
     * 접속 로그 목록
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectConnectLogListInfo(LogManageVO vo) throws Exception {
    	
        return list("LogManageDAO.selectConnectLogListInfo", vo);
        
    }
	
    /**
     * 접속 로그 갯수
     * @param searchVO
     * @return
     */
    public int selectConnectLogListInfoCnt(LogManageVO vo) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("LogManageDAO.selectConnectLogListInfoCnt", vo);
        
    }
    
    /**
     * 웹 접속 로그 목록
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectWebLogListInfo(LogManageVO vo) throws Exception {
    	
        return list("LogManageDAO.selectWebLogListInfo", vo);
        
    }
	
    /**
     * 웹 접속 로그 갯수
     * @param searchVO
     * @return
     */
    public int selectWebLogListInfoCnt(LogManageVO vo) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("LogManageDAO.selectWebLogListInfoCnt", vo);
        
    }

    
  
}
