package clikmng.nanet.go.kr.sit.rls.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.sit.rls.service.SiteManageDefaultVO;
import clikmng.nanet.go.kr.sit.rls.service.SiteManageVO;

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
@Repository("SiteManageDAO")
public class SiteManageDAO extends EgovComAbstractDAO {


    /**
     * 사이트 목록에 대한 상세내용을 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    public SiteManageVO selectSiteDetail(SiteManageVO vo) throws Exception {
    	
        return (SiteManageVO) selectByPk("SiteManageDAO.selectSiteDetail", vo);
        
    }

    /**
     * 사이트정보 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectSiteList(SiteManageDefaultVO searchVO) throws Exception {
    	
        return list("SiteManageDAO.selectSiteList", searchVO);
        
    }

    /**
     * 사이트정보 총 갯수를 조회한다.
     * @param searchVO
     * @return
     */
/*    
    public int selectSiteListTotCnt(SiteManageDefaultVO searchVO) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("SiteManageDAO.selectSiteListTotCnt", searchVO);
        
    }
*/    
	/**
	 * 사이트정보를 등록한다.
	 * @param vo
	 * @throws Exception
	 */
    public void insertSiteInfo(SiteManageVO vo) throws Exception {
    	
        insert("SiteManageDAO.insertSiteInfo", vo);
        
    }
    
	/**
	 * 사이트정보를 수정한다.
	 * @param vo
	 * @throws Exception
	 */
    public void updateSiteInfo(SiteManageVO vo) throws Exception {
    	
        update("SiteManageDAO.updateSiteInfo", vo);
        
    }

	/**
	 * 사이트정보를 삭제한다.
	 * @param vo
	 * @throws Exception
	 */
    public void deleteSiteInfo(SiteManageVO vo) throws Exception {
    	
        delete("SiteManageDAO.deleteSiteInfo", vo);
        
    }
    
    
    /**
     * 사이트정보 카테고리 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectClCode(SiteManageDefaultVO searchVO) throws Exception {
    	
        return list("SiteManageDAO.selectClCode", searchVO);
        
    }

    /**
     * 사이트정보 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectClDetailCode(SiteManageVO vo) throws Exception {
        return list("SiteManageDAO.selectClDetailCode", vo);
    }    
    
	/**
	 * 사이트순서 수정.
	 * @param vo
	 * @throws Exception
	 */
    public void updateSiteOrdrEdit(SiteManageVO vo) throws Exception {
    	
        update("SiteManageDAO.updateSiteOrdrEdit", vo);
        
    }

	/**
	 * 사이트순서를 DOWN으로 수정한다.
	 * @param vo
	 * @throws Exception
	 */
    public void updateSiteOrdrDown(SiteManageVO vo) throws Exception {
    	
        update("SiteManageDAO.updateSiteOrdrDown", vo);
        
    }
    
    
}
