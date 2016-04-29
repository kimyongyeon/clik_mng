package clikmng.nanet.go.kr.mob.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 모바일 관리 처리하는 클래스
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 *
 * </pre>
 */
public interface MobManageService {
	    
    /**
     * 모바일 관리 상세조회한다.
     * @param vo
     * @return	글 내용
     * @throws Exception
     */
	MobManageVO selectMobOnsDetail(MobManageVO vo) throws Exception;
    
    /**
     * 사이트목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @throws Exception
     */
    List selectMobOnsList(MobManageVO searchVO) throws Exception;
    
    /**
     * 사이트정보 총 갯수를 조회한다.
     * @param searchVO
     * @return	총 갯수
     */
    int selectMobOnsListTotCnt(MobManageDefaultVO searchVO);
    
	/**
	 * 모바일 관리 등록한다.
	 * @param vo
	 * @throws Exception
	 */
    void insertMobOns(MobManageVO vo) throws Exception;
    

	/**
	 * 모바일 관리 수정한다.
	 * @param vo
	 * @throws Exception
	 */
    void updateMobOns(MobManageVO vo) throws Exception;
    
	/**
	 * 모바일 관리 삭제한다.
	 * @param vo
	 * @throws Exception
	 */
    void deleteMobOns(MobManageVO vo) throws Exception;
     
	/**
	 * 모바일자료 이용통계
	 * @param MobManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public List selectDusList(MobManageDefaultVO mobManageDefaultVO) throws Exception;
	
	/**
	 * 모바일자료 이용통계(월별)
	 * @param MobManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public List selectMonDusList(MobManageDefaultVO mobManageDefaultVO) throws Exception;
	
	/**
	 * 모바일자료 이용통계 - 접속현황조회
	 * @param MobManageDefaultVO
	 * @return List	
	 * @throws Exception
	 */
	public String selectMobileVisitTotalCnt(MobManageDefaultVO mobManageDefaultVO) throws Exception;
   
	/**
	 * 엑셀출력
	 */
    public ModelAndView selectMobDusExcel(MobManageDefaultVO mobManageDefaultVO) throws Exception ;
    
    /**
	 * 모바일 이용 현황 - 누적
	 * @param vo
	 * @throws Exception
	 */
    public List selectMusSumList(MobManageDefaultVO mobManageDefaultVO) throws Exception;

	/**
	 * 모바일 이용 현황
	 * @param vo
	 * @throws Exception
	 */
    public List selectMusList(MobManageDefaultVO mobManageDefaultVO) throws Exception;
    
    
}
