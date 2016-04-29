package clikmng.nanet.go.kr.sit.rls.service;

import java.util.List;

/**
 * 
 * 사이트정보를 처리하는 클래스
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
public interface SiteManageService {
	    
    /**
     * 사이트정보를 상세조회한다.
     * @param vo
     * @return	글 내용
     * @throws Exception
     */
	SiteManageVO selectSiteDetail(SiteManageVO vo) throws Exception;
    
    /**
     * 사이트목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @throws Exception
     */
    List selectSiteList(SiteManageDefaultVO searchVO) throws Exception;
    
    /**
     * 사이트정보 총 갯수를 조회한다.
     * @param searchVO
     * @return	총 갯수
     */
    //int selectSiteListTotCnt(SiteManageDefaultVO searchVO);
    
	/**
	 * 사이트정보를 등록한다.
	 * @param vo
	 * @throws Exception
	 */
    void insertSiteInfo(SiteManageVO vo) throws Exception;
    

	/**
	 * 사이트정보를 수정한다.
	 * @param vo
	 * @throws Exception
	 */
    void updateSiteInfo(SiteManageVO vo) throws Exception;
    
	/**
	 * 사이트정보를 삭제한다.
	 * @param vo
	 * @throws Exception
	 */
    void deleteSiteInfo(SiteManageVO vo) throws Exception;
    
    /**
     * 사이트 주제 분류 코드를 조회한다.
     */
    List selectClCode(SiteManageDefaultVO searchVO) throws Exception;
    
    /**
     * 사이트 주제 분류 상세코드를 조회한다.
     */
    List selectClDetailCode(SiteManageVO vo) throws Exception;    
    
	/**
	 * 사이트 순서 수정
	 * @param vo
	 * @throws Exception
	 */
    void updateSiteOrdrEdit(SiteManageVO vo) throws Exception;    
    
}
