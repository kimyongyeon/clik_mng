package clikmng.nanet.go.kr.csm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.csm.service.CsmManageDefaultVO;
import clikmng.nanet.go.kr.csm.service.CsmManageVO;

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
@Repository("CsmManageDAO")
public class CsmManageDAO extends EgovComAbstractDAO {
	
    /**
     * 수집기관 리스트
     * @param vo
     * @return	수집기관 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectOrgTypeList() throws Exception {
		return list("CsmManageDAO.selectOrgTypeList",null);
	}
	
    /**
     * 수집기관 리스트
     * @param vo
     * @return	수집기관 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectCollectionOrgList() throws Exception {
		return list("CsmManageDAO.selectCollectionOrgList",null);
	}

    /**
	 * 기관별 수집 내역
	 * @param vo - 조회할 정보가 담긴 CsmManageVO
	 * @return 기관별 수집 내역 조회
	 * @exception Exception
	 */
    public List selectCsmList(CsmManageVO vo) throws Exception {
        return list("CsmManageDAO.selectCsmList", vo);
    }
    public int selectCsmListTotCnt(CsmManageVO vo) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("CsmManageDAO.selectCsmListTotCnt", vo);
    }
    
    /**
     * 수집카테고리 리스트
     * @param vo
     * @return	수집카테고리 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectCategoryList() throws Exception {
		return list("CsmManageDAO.selectCategoryList", null);
	}
    
    /**
     * 수집대비 서비스 리스트
     * @param vo
     * @return	수집카테고리 리스트
     * @throws Exception
     */
	public List<CsmManageVO> selectCollectionServiceList(CsmManageVO vo) throws Exception {
		return list("CsmManageDAO.selectCollectionServiceList", vo);
	}
    
}
