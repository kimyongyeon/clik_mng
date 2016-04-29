package clikmng.nanet.go.kr.cop.err.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.cop.err.service.ErrManageDefaultVO;
import clikmng.nanet.go.kr.cop.err.service.ErrManageService;
import clikmng.nanet.go.kr.cop.err.service.ErrManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;



/**
 * 
 * 오류신고정보를 처리하는 비즈니스 구현 클래스
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
@Service("ErrManageService")
public class ErrManageServiceImpl extends AbstractServiceImpl implements
        ErrManageService {

    @Resource(name="ErrManageDAO")
    private ErrManageDAO errManageDAO;
        
    /** ID Generation */    
	@Resource(name="egovErrManageIdGnrService")
	private EgovIdGnrService idgenService;

    
    /**
	 * 오류신고 글을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
    public ErrManageVO selectErrListDetail(ErrManageVO vo) throws Exception {
        ErrManageVO resultVO = errManageDAO.selectErrListDetail(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

	/**
	 * 오류신고 글을 수정한다.(조회수를 수정)
	 * @param vo
	 * @exception Exception
	 */
    public void updateErrInqireCo(ErrManageVO vo) throws Exception {
    	errManageDAO.updateErrInqireCo(vo);    	
    }
    
    /**
	 * 오류신고 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    public List<?> selectErrList(ErrManageDefaultVO searchVO) throws Exception {
        return errManageDAO.selectErrList(searchVO);
    }

    /**
	 * 오류신고 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    public int selectErrListTotCnt(ErrManageDefaultVO searchVO) {
		return errManageDAO.selectErrListTotCnt(searchVO);
	}
    
	/**
	 * 오류신고 글을 등록한다.
	 * @param vo
	 * @exception Exception
	 */
    public void insertErrCn(ErrManageVO vo) throws Exception {    	    	
		String	errorReportId = idgenService.getNextStringId();

		vo.setErrorReportId(errorReportId);
		
    	errManageDAO.insertErrCn(vo);    	
    }
    
    /**
	 * 작성비밀번호를 확인한다.
	 * @param vo
	 * @return 글 총 갯수
	 */
    public int selectErrPasswordConfirmCnt(ErrManageVO vo) {
		return errManageDAO.selectErrPasswordConfirmCnt(vo);
	}
    
	/**
	 * 오류신고 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    public void updateErrCn(ErrManageVO vo) throws Exception {           	
    	errManageDAO.updateErrCn(vo);    	
    }

	/**
	 * 오류신고 글을 삭제한다.
	 * @param vo
	 * @exception Exception
	 */
    public void deleteErrCn(ErrManageVO vo) throws Exception {
    	errManageDAO.deleteErrCn(vo);    	
    }

    
    /**
	 * 오류신고 답변 글을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
    public ErrManageVO selectErrAnswerListDetail(ErrManageVO vo) throws Exception {
        ErrManageVO resultVO = errManageDAO.selectErrAnswerListDetail(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }
    
    /**
	 * 오류신고 답변 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    public List<?> selectErrAnswerList(ErrManageDefaultVO searchVO) throws Exception {
        return errManageDAO.selectErrAnswerList(searchVO);
    }

    /**
	 * 오류신고 답변 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    public int selectErrAnswerListTotCnt(ErrManageDefaultVO searchVO) {
		return errManageDAO.selectErrListTotCnt(searchVO);
	}
            
	/**
	 * 오류신고 답변 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    public void updateErrCnAnswer(ErrManageVO vo) throws Exception {
    	errManageDAO.updateErrCnAnswer(vo);    	
    }
    
    /**
	 * 오류신고 담당자 이메일 주소를 조회한다.
	 * @param searchVO
	 * @return 담당자 이메일 목록
	 * @exception Exception
	 */
    public List<HashMap<String,Object>> selectErrChargerList() throws Exception{
    	return errManageDAO.selectErrChargerList();
    }
    
    /**
	 * 오류신고 관리자 정보를 조회한다.
	 * @param searchVO
	 * @return 관리자 목록
	 * @exception Exception
	 */
    public List<HashMap<String,Object>> selectMngList() throws Exception{
    	return errManageDAO.selectMngList();
    }
    
    /**
	 * 오류신고 메일 수신 담당자 정보를 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    public void updateErrMailRcvrCharger(HashMap<String,Object> vo) throws Exception{
    	errManageDAO.updateErrMailRcvrCharger(vo);
    }
}
