package clikmng.nanet.go.kr.cop.err.service;

import java.util.HashMap;
import java.util.List;


/**
 * 
 * 오류신고를 처리하는 서비스 클래스
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
public interface ErrManageService {
	    
    /**
	 * 오류신고 글을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
	ErrManageVO selectErrListDetail(ErrManageVO vo) throws Exception;
    
	/**
	 * 오류신고 조회수를 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    void updateErrInqireCo(ErrManageVO vo) throws Exception;
	
    /**
	 * 오류신고 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    List<?> selectErrList(ErrManageDefaultVO searchVO) throws Exception;
    
    /**
	 * 오류신고 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 * @exception
	 */
    int selectErrListTotCnt(ErrManageDefaultVO searchVO);
    
	/**
	 * 오류신고 글을 등록한다.
	 * @param vo
	 * @exception Exception
	 */
    void insertErrCn(ErrManageVO vo) throws Exception;
    
    /**
	 * 오류신고  작성비밀번호를 확인한다.
	 * @param vo
	 * @return 확인결과
	 * @exception
	 */
    int selectErrPasswordConfirmCnt(ErrManageVO vo);
    
	/**
	 * 오류신고 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    void updateErrCn(ErrManageVO vo) throws Exception;
    
	/**
	 * 오류신고 글을 삭제한다.
	 * @param vo
	 * @exception Exception
	 */
    void deleteErrCn(ErrManageVO vo) throws Exception;
    
    
    /**
	 * 오류신고 답변 글을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
	ErrManageVO selectErrAnswerListDetail(ErrManageVO vo) throws Exception;
    
	
    /**
	 * 오류신고 답변 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    List<?> selectErrAnswerList(ErrManageDefaultVO searchVO) throws Exception;
    
    /**
	 * 오류신고 답변 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 * @exception
	 */
    int selectErrAnswerListTotCnt(ErrManageDefaultVO searchVO);
            
	/**
	 * 오류신고 답변 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    void updateErrCnAnswer(ErrManageVO vo) throws Exception;
    
    /**
	 * 오류신고 담당자 이메일 주소를 조회한다.
	 * @param searchVO
	 * @return 담당자 이메일 목록
	 * @exception Exception
	 */
    List<HashMap<String,Object>> selectErrChargerList() throws Exception;
    
    /**
	 * 오류신고 관리자 정보를 조회한다.
	 * @param searchVO
	 * @return 관리자 목록
	 * @exception Exception
	 */
    List<HashMap<String,Object>> selectMngList() throws Exception;
    
    /**
	 * 오류신고 메일 수신 담당자 정보를 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    void updateErrMailRcvrCharger(HashMap<String,Object> vo) throws Exception;
}

