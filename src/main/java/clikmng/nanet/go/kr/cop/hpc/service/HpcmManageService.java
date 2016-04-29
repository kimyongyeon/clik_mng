package clikmng.nanet.go.kr.cop.hpc.service;

import java.util.List;

/**
 * 
 * 도움말을 처리하기 위한 서비스 클래스
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
public interface HpcmManageService {
	    
    /**
	 * 도움말 글을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
	HpcmManageVO selectHpcmDetail(HpcmManageVO vo) throws Exception;
    
    /**
	 * 도움말 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    List selectHpcmList(HpcmManageDefaultVO searchVO) throws Exception;
    
    /**
	 * 도움말 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    int selectHpcmListTotCnt(HpcmManageDefaultVO searchVO);
    
	/**
	 * 도움말 글을 등록한다.
	 * @param vo
	 * @exception Exception
	 */
    void insertHpcmCn(HpcmManageVO vo) throws Exception;
    

	/**
	 * 도움말 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    void updateHpcmCn(HpcmManageVO vo) throws Exception;
    
	/**
	 * 도움말 글을 삭제한다.
	 * @param vo
	 * @exception Exception
	 */
    void deleteHpcmCn(HpcmManageVO vo) throws Exception;
    
    
}
