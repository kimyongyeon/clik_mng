package clikmng.nanet.go.kr.sit.rls.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.sit.rls.service.SiteManageService;
import clikmng.nanet.go.kr.sit.rls.service.SiteManageDefaultVO;
import clikmng.nanet.go.kr.sit.rls.service.SiteManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


/**
 * 
 * 사이트정보를 처리하는 구현 클래스
 */
@Service("SiteManageService")
public class SiteManageServiceImpl extends AbstractServiceImpl implements
        SiteManageService {

    @Resource(name="SiteManageDAO")
    private SiteManageDAO siteManageDAO;
        
    /** ID Generation */    
	@Resource(name="egovSiteManageIdGnrService")
	private EgovIdGnrService idgenService;

    
    /**
	 * 사이트상세정보를 조회한다.
	 * @param vo - 조회할 정보가 담긴 SiteManageVO
	 * @return 조회한 글
	 * @exception Exception
	 */
    public SiteManageVO selectSiteDetail(SiteManageVO vo) throws Exception {
        SiteManageVO resultVO = siteManageDAO.selectSiteDetail(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * 사이트정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
    public List selectSiteList(SiteManageDefaultVO searchVO) throws Exception {
        return siteManageDAO.selectSiteList(searchVO);
    }

    /**
	 * 사이트정보 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
/*  
    public int selectSiteListTotCnt(SiteManageDefaultVO searchVO) {
		return siteManageDAO.selectSiteListTotCnt(searchVO);
	}
*/    
	/**
	 * 사이트정보를 등록한다.
	 * @param vo - 등록할 정보가 담긴 SiteManageVO
	 * @exception Exception
	 */
    public void insertSiteInfo(SiteManageVO vo) throws Exception {
    	log.debug(vo.toString());
    	    	
		String	siteId = idgenService.getNextStringId();

		vo.setSiteId(siteId);
		
    	siteManageDAO.insertSiteInfo(vo);    	
    }
    
	/**
	 * 사이트정보를 수정한다.
	 * @param vo - 수정할 정보가 담긴 SiteManageVO
	 * @exception Exception
	 */
    public void updateSiteInfo(SiteManageVO vo) throws Exception {
    	log.debug(vo.toString());
    	    	           	
    	siteManageDAO.updateSiteInfo(vo);    	
    }

	/**
	 * 사이트정보를 삭제한다.
	 * @param vo - 수정할 정보가 담긴 SiteManageVO
	 * @exception Exception
	 */
    public void deleteSiteInfo(SiteManageVO vo) throws Exception {
    	log.debug(vo.toString());
    	    	
    	siteManageDAO.deleteSiteInfo(vo);    	
    }
    
    /**
	 * 사이트정보 주제 분류 코드를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 코드 목록
	 * @exception Exception
	 */
    public List selectClCode(SiteManageDefaultVO searchVO) throws Exception {
        return siteManageDAO.selectClCode(searchVO);
    }

    /**
	 * 사이트정보 주제 분류 코드를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 코드 목록
	 * @exception Exception
	 */
    public List selectClDetailCode(SiteManageVO vo) throws Exception {
        return siteManageDAO.selectClDetailCode(vo);
    }    
    
    /**
	 * 사이트 순서를 수정
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @exception Exception
	 */
    public void updateSiteOrdrEdit(SiteManageVO vo) throws Exception {
        siteManageDAO.updateSiteOrdrEdit(vo);
    }  
    
    /**
	 * 사이트정보 주제 분류 코드를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 코드 목록
	 * @exception Exception
	 */
    public void updateSiteOrdrDown(SiteManageVO vo) throws Exception {
        siteManageDAO.updateSiteOrdrDown(vo);
    }  
    
}
