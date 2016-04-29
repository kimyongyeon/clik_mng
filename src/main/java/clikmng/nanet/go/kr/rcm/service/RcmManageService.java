package clikmng.nanet.go.kr.rcm.service;

import java.util.List;

/**
 * 
 * 리소스센터  처리 클래스
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 *
 * </pre>
 */
public interface RcmManageService {
	    
    /**
     * 리소스센터 상세조회한다.
     * @param vo
     * @return	글 내용
     * @throws Exception
     */
	RcmManageVO selectRcmDetail(RcmManageVO vo) throws Exception;
    
    /**
     * 인증키 목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @throws Exception
     */
    List selectAilList(RcmManageDefaultVO searchVO) throws Exception;
    
    /**
     * 인증키 총 갯수를 조회한다.
     * @param searchVO
     * @return	총 갯수
     */
    int selectAilListTotCnt(RcmManageDefaultVO searchVO);
    
    
    /**
     * 인증키 상세조회한다.
     * @param vo
     * @return	글 내용
     * @throws Exception
     */
	RcmManageVO selectAiDetail(RcmManageVO vo) throws Exception;

    /**
     * 인증키 변경 한다.
     * @param vo
     * @return	글 내용
     * @throws Exception
     */
	int updateAiDetail(RcmManageVO vo) throws Exception;
	
	/**
     * 인증키 삭제 한다.
     * @param vo
     * @return	글 내용
     * @throws Exception
     */
	int deleteAiDetail(RcmManageVO vo) throws Exception;
	
    /**
     * 인증키 이용내역을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @throws Exception
     */
    List selectAuList(RcmManageDefaultVO searchVO) throws Exception;	
	
    
    /**
     * 인증키 총 갯수를 조회한다.
     * @param searchVO
     * @return	총 갯수
     */
    int selectAuListTotCnt(RcmManageDefaultVO searchVO);    
}
