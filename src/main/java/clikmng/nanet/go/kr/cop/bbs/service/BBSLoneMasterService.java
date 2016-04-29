package clikmng.nanet.go.kr.cop.bbs.service;

import java.util.Map;


/**
 * 게시판 속성관리를 위한 서비스 인터페이스 클래스
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------       --------    ---------------------------
 *
 * </pre>
 */
public interface BBSLoneMasterService {

    /**
     * 등록된 게시판 속성정보를 삭제한다.
     * 
     * @param BoardMaster
     */
    public void deleteMaster(BoardMaster boardMaster) throws Exception;

    /**
     * 신규 게시판 속성정보를 생성한다.
     * 
     * @param BoardMaster
     */
    public String insertMaster(BoardMaster boardMaster) throws Exception;

    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     * 
     * @param BoardMasterVO
     */
    public BoardMasterVO selectMaster(BoardMaster searchVO) throws Exception;

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * 
     * @param BoardMasterVO
     */
    public Map<String, Object> selectMasterList(BoardMasterVO searchVO) throws Exception;

    /**
     * 게시판 속성정보를 수정한다.
     * 
     * @param BoardMaster
     */
    public void updateMaster(BoardMaster boardMaster) throws Exception;
}
