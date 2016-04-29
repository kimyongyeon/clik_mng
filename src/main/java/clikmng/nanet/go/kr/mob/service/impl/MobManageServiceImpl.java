package clikmng.nanet.go.kr.mob.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import clikmng.nanet.go.kr.mob.service.MobManageDefaultVO;
import clikmng.nanet.go.kr.mob.service.MobManageService;
import clikmng.nanet.go.kr.mob.service.MobManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;



/**
 * 
 * 의견보내기를 처리하는 구현 클래스
 */
@Service("MobManageService")
public class MobManageServiceImpl extends AbstractServiceImpl implements
        MobManageService {

    @Resource(name="MobManageDAO")
    private MobManageDAO MobManageDAO;
        
    /** ID Generation */
/*    
	@Resource(name="egovMobManageIdGnrService")
	private EgovIdGnrService idgenService;
*/
    
    /**
	 * 의견보내기 상세정보를 조회한다.
	 * @param vo - 조회할 정보가 담긴 MobManageVO
	 * @return 조회한 글
	 * @exception Exception
	 */
    public MobManageVO selectMobOnsDetail(MobManageVO vo) throws Exception {
        MobManageVO resultVO = MobManageDAO.selectMobOnsDetail(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * 의견보내기 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
    public List selectMobOnsList(MobManageVO searchVO) throws Exception {
        return MobManageDAO.selectMobOnsList(searchVO);
    }

    /**
	 * 의견보내기 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
    public int selectMobOnsListTotCnt(MobManageDefaultVO searchVO) {
		return MobManageDAO.selectMobOnsListTotCnt(searchVO);
	}
    
	/**
	 * 의견보내기를 등록한다.
	 * @param vo - 등록할 정보가 담긴 MobManageVO
	 * @exception Exception
	 */
    public void insertMobOns(MobManageVO vo) throws Exception {
    	log.debug(vo.toString());
    	    	
/*		String	MobId = idgenService.getNextStringId();*/

/*		vo.setMobId(MobId);*/
		
    	MobManageDAO.insertMobOns(vo);    	
    }
    
	/**
	 * 의견보내기를 수정한다.
	 * @param vo - 수정할 정보가 담긴 MobManageVO
	 * @exception Exception
	 */
    public void updateMobOns(MobManageVO vo) throws Exception {
    	log.debug(vo.toString());
    	    	           	
    	MobManageDAO.updateMobOns(vo);    	
    }

	/**
	 * 의견보내기를 삭제한다.
	 * @param vo - 수정할 정보가 담긴 MobManageVO
	 * @exception Exception
	 */
    public void deleteMobOns(MobManageVO vo) throws Exception {
    	log.debug(vo.toString());
    	    	
    	MobManageDAO.deleteMobOns(vo);    	
    }    
  
	/**
	 * 모바일자료 이용통계
	 */
	public List selectDusList(MobManageDefaultVO mobManageDefaultVO) throws Exception {
		return (List)MobManageDAO.selectDusList(mobManageDefaultVO);
	}	
	
	/**
	 * 모바일자료 이용통계(월별)
	 */
	public List selectMonDusList(MobManageDefaultVO mobManageDefaultVO) throws Exception {
		return (List)MobManageDAO.selectMonDusList(mobManageDefaultVO);
	}
	
	/**
	 * 모바일자료 이용통계 - 접속현황조회
	 * @param MobManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public String selectMobileVisitTotalCnt(MobManageDefaultVO mobManageDefaultVO) throws Exception{
		return MobManageDAO.selectMobileVisitTotalCnt(mobManageDefaultVO);
	}
	
	/**
	 * 자료 이용 통계 조회 엑셀출력
	 */
	public ModelAndView selectMobDusExcel(MobManageDefaultVO mobManageDefaultVO)  throws Exception {
		return (ModelAndView)MobManageDAO.selectDusList(mobManageDefaultVO);
	}
	
	/**
	 * 모바일 이용 현황 - 누적
	 */
    public List selectMusSumList(MobManageDefaultVO mobManageDefaultVO) throws Exception {
    	return (List)MobManageDAO.selectMusSumList(mobManageDefaultVO);
    }

	/**
	 * 모바일 이용 현황
	 */
    public List selectMusList(MobManageDefaultVO mobManageDefaultVO) throws Exception {
    	return (List)MobManageDAO.selectMusList(mobManageDefaultVO);
    }
    
}
