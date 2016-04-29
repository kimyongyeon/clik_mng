package clikmng.nanet.go.kr.cop.err.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.cop.err.service.ErrManageDefaultVO;
import clikmng.nanet.go.kr.cop.err.service.ErrManageVO;


/**
 * 
 * 오류신고를 처리하는 DAO 클래스
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
@Repository("ErrManageDAO")
public class ErrManageDAO extends EgovComAbstractDAO {


    /**
	 * 오류신고 글 목록에 대한 상세내용을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
    public ErrManageVO selectErrListDetail(ErrManageVO vo) throws Exception {
    	
        return (ErrManageVO) getSqlMapClientTemplate().queryForObject("ErrManageDAO.selectErrListDetail", vo);
        
    }

	/**
	 * 오류신고 글을 수정한다.(조회수를 수정)
	 * @param vo
	 * @exception Exception
	 */
    public void updateErrInqireCo(ErrManageVO vo) throws Exception {
    	
        update("ErrManageDAO.updateErrInqireCo", vo);
        
    }
    
    /**
	 * 오류신고 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    public List<?> selectErrList(ErrManageDefaultVO searchVO) throws Exception {
    	
        return list("ErrManageDAO.selectErrList", searchVO);
        
    }

    /**
	 * 오류신고 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    public int selectErrListTotCnt(ErrManageDefaultVO searchVO) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("ErrManageDAO.selectErrListTotCnt", searchVO);
        
    }
    
	/**
	 * 오류신고 글을 등록한다.
	 * @param vo
	 * @exception Exception
	 */
    public void insertErrCn(ErrManageVO vo) throws Exception {
    	
        insert("ErrManageDAO.insertErrCn", vo);
        
    }

    /**
	 * 작성비밀번호를 확인한다.
	 * @param vo
	 * @return 글 총 갯수
	 */
    public int selectErrPasswordConfirmCnt(ErrManageVO vo) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("ErrManageDAO.selectErrPasswordConfirmCnt", vo);
        
    }
    
	/**
	 * 오류신고 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    public void updateErrCn(ErrManageVO vo) throws Exception {
    	
        update("ErrManageDAO.updateErrCn", vo);
        
    }

	/**
	 * 오류신고 글을 삭제한다.
	 * @param vo
	 * @exception Exception
	 */
    public void deleteErrCn(ErrManageVO vo) throws Exception {
    	
        delete("ErrManageDAO.deleteErrCn", vo);
        
    }

    
    /**
	 * 오류신고 답변 글 목록에 대한 상세내용을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
    public ErrManageVO selectErrAnswerListDetail(ErrManageVO vo) throws Exception {
    	
        return (ErrManageVO) selectByPk("ErrManageDAO.selectErrAnswerListDetail", vo);
        
    }

    
    /**
	 * 오류신고 답변 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    public List<?> selectErrAnswerList(ErrManageDefaultVO searchVO) throws Exception {
    	
        return list("ErrManageDAO.selectErrAnswerList", searchVO);
        
    }

    /**
	 * 오류신고 답변 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    public int selectErrAnswerListTotCnt(ErrManageDefaultVO searchVO) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("ErrManageDAO.selectErrAnswerListTotCnt", searchVO);
        
    }
        
	/**
	 * 오류신고 답변 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    public void updateErrCnAnswer(ErrManageVO vo) throws Exception {
    	
        update("ErrManageDAO.updateErrCnAnswer", vo);
        
    }

    /**
	 * 오류신고 담당자 이메일 주소를 조회한다.
	 * @param searchVO
	 * @return 담당자 이메일 목록
	 * @exception Exception
	 */
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectErrChargerList() throws Exception{
    	return list("ErrManageDAO.selectErrChargerList", null);
    }
    
    /**
	 * 오류신고 관리자 정보를 조회한다.
	 * @param searchVO
	 * @return 관리자 목록
	 * @exception Exception
	 */
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> selectMngList() throws Exception{
    	return list("ErrManageDAO.selectMngList", null);
    }
    
    /**
	 * 오류신고 메일 수신 담당자 정보를 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    public void updateErrMailRcvrCharger(HashMap<String,Object> vo) throws Exception{
    	update("ErrManageDAO.updateErrMailRcvrCharger", vo);
    }
}
