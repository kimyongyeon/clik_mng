package clikmng.nanet.go.kr.mob.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.mob.service.MobManageDefaultVO;
import clikmng.nanet.go.kr.mob.service.MobManageVO;
import clikmng.nanet.go.kr.sts.stm.service.UseLogSummaryVO;

/**
 * 
 * 의견보내기를 처리하는 DAO 클래스
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
@Repository("MobManageDAO")
public class MobManageDAO extends EgovComAbstractDAO {


    /**
     * 의견보내기 목록에 대한 상세내용을 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    public MobManageVO selectMobOnsDetail(MobManageVO vo) throws Exception {
    	
        return (MobManageVO) selectByPk("MobManageDAO.selectMobOnsDetail", vo);
        
    }

    /**
     * 의견보내기 목록을 조회한다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List selectMobOnsList(MobManageVO searchVO) throws Exception {
    	
        return list("MobManageDAO.selectMobOnsList", searchVO);
        
    }

    /**
     * 의견보내기 총 갯수를 조회한다.
     * @param searchVO
     * @return
     */
    public int selectMobOnsListTotCnt(MobManageDefaultVO searchVO) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("MobManageDAO.selectMobOnsListTotCnt", searchVO);
        
    }
    
	/**
	 * 의견보내기를 등록한다.
	 * @param vo
	 * @throws Exception
	 */
    public void insertMobOns(MobManageVO vo) throws Exception {
    	
        insert("MobManageDAO.insertMobOns", vo);
        
    }
    
	/**
	 * 의견보내기를 수정한다.
	 * @param vo
	 * @throws Exception
	 */
    public void updateMobOns(MobManageVO vo) throws Exception {
    	
        update("MobManageDAO.updateMobOns", vo);
        
    }

	/**
	 * 의견보내기를 삭제한다.
	 * @param vo
	 * @throws Exception
	 */
    public void deleteMobOns(MobManageVO vo) throws Exception {
    	
        delete("MobManageDAO.deleteMobOns", vo);
        
    }
    
	/**
	 * 모바일자료 이용통계
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<UseLogSummaryVO> selectDusList(MobManageDefaultVO mobManageDefaultVO)  throws Exception{
		return list("MobManageDAO.selectDusList", mobManageDefaultVO);
	}
	
	/**
	 * 모바일자료 이용통계(월별)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<UseLogSummaryVO> selectMonDusList(MobManageDefaultVO mobManageDefaultVO)  throws Exception{
		return list("MobManageDAO.selectMonDusList", mobManageDefaultVO);
	}
	
	/**
	 * 모바일자료 이용통계 - 접속현황조회
	 * @param MobManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public String selectMobileVisitTotalCnt(MobManageDefaultVO mobManageDefaultVO) throws Exception{
		return (String)getSqlMapClientTemplate().queryForObject("MobManageDAO.selectMobileVisitTotalCnt", mobManageDefaultVO);
	}
	
	/**
	 * 모바일 이용 현황 - 누적
	 */
    public List selectMusSumList(MobManageDefaultVO mobManageDefaultVO) throws Exception {
    	return list("MobManageDAO.selectMusSumList", mobManageDefaultVO);
    }

	/**
	 * 모바일 이용 현황
	 */
    public List selectMusList(MobManageDefaultVO mobManageDefaultVO) throws Exception {
    	return list("MobManageDAO.selectMusList", mobManageDefaultVO);
    }

}
