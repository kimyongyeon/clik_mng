package clikmng.nanet.go.kr.cop.qna.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.cop.qna.service.QnaManageDefaultVO;
import clikmng.nanet.go.kr.cop.qna.service.QnaManageVO;


/**
 * 
 * Q&A정보를 처리하는 DAO 클래스
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
@Repository("QnaManageDAO")
public class QnaManageDAO extends EgovComAbstractDAO {


    /**
	 * Q&A 글 목록에 대한 상세내용을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
    public QnaManageVO selectQnaListDetail(QnaManageVO vo) throws Exception {
    	
        return (QnaManageVO) getSqlMapClientTemplate().queryForObject("QnaManageDAO.selectQnaListDetail", vo);
        
    }

	/**
	 * Q&A 글을 수정한다.(조회수를 수정)
	 * @param vo
	 * @exception Exception
	 */
    public void updateQnaInqireCo(QnaManageVO vo) throws Exception {
    	
        update("QnaManageDAO.updateQnaInqireCo", vo);
        
    }
    
    /**
	 * Q&A 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    public List selectQnaList(QnaManageDefaultVO searchVO) throws Exception {
    	
        return list("QnaManageDAO.selectQnaList", searchVO);
        
    }

    /**
	 * Q&A 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    public int selectQnaListTotCnt(QnaManageDefaultVO searchVO) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("QnaManageDAO.selectQnaListTotCnt", searchVO);
        
    }
    
	/**
	 * Q&A 글을 등록한다.
	 * @param vo
	 * @exception Exception
	 */
    public void insertQnaCn(QnaManageVO vo) throws Exception {
    	
        insert("QnaManageDAO.insertQnaCn", vo);
        
    }

    /**
	 * 작성비밀번호를 확인한다.
	 * @param vo
	 * @return 글 총 갯수
	 */
    public int selectQnaPasswordConfirmCnt(QnaManageVO vo) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("QnaManageDAO.selectQnaPasswordConfirmCnt", vo);
        
    }
    
	/**
	 * Q&A 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    public void updateQnaCn(QnaManageVO vo) throws Exception {
    	
        update("QnaManageDAO.updateQnaCn", vo);
        
    }

	/**
	 * Q&A 글을 삭제한다.
	 * @param vo
	 * @exception Exception
	 */
    public void deleteQnaCn(QnaManageVO vo) throws Exception {
    	
        delete("QnaManageDAO.deleteQnaCn", vo);
        
    }

    
    /**
	 * Q&A 답변 글 목록에 대한 상세내용을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
    public QnaManageVO selectQnaAnswerListDetail(QnaManageVO vo) throws Exception {
    	
        return (QnaManageVO) selectByPk("QnaManageDAO.selectQnaAnswerListDetail", vo);
        
    }

    
    /**
	 * Q&A 답변 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    public List selectQnaAnswerList(QnaManageDefaultVO searchVO) throws Exception {
    	
        return list("QnaManageDAO.selectQnaAnswerList", searchVO);
        
    }

    /**
	 * Q&A 답변 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    public int selectQnaAnswerListTotCnt(QnaManageDefaultVO searchVO) {
    	
        return (Integer)getSqlMapClientTemplate().queryForObject("QnaManageDAO.selectQnaAnswerListTotCnt", searchVO);
        
    }
        
	/**
	 * Q&A 답변 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    public void updateQnaCnAnswer(QnaManageVO vo) throws Exception {
    	
        update("QnaManageDAO.updateQnaCnAnswer", vo);
        
    }

    
}
