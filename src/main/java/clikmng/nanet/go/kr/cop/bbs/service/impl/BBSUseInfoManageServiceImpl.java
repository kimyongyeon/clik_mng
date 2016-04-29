package clikmng.nanet.go.kr.cop.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.cop.bbs.service.BoardUseInf;
import clikmng.nanet.go.kr.cop.bbs.service.BoardUseInfVO;
import clikmng.nanet.go.kr.cop.bbs.service.BBSUseInfoManageService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 게시판 이용정보를 관리하기 위한 서비스 구현 클래스
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
@Service("BBSUseInfoManageService")
public class BBSUseInfoManageServiceImpl extends AbstractServiceImpl implements BBSUseInfoManageService {
	
    @Resource(name = "BBSUseInfoManageDAO")
    private BBSUseInfoManageDAO bbsUseDAO;

    /**
     * 게시판 사용 정보를 삭제한다.
     * 
     * @see egovframework.BBSUseInfoManageService.cop.bbs.service.com.service.EgovBBSUseInfoManageService#deleteBBSUseInf(egovframework.BoardUseInf.cop.bbs.service.com.service.BoardUseInf)
     */
    public void deleteBBSUseInf(BoardUseInf bdUseInf) throws Exception {
	bbsUseDAO.deleteBBSUseInf(bdUseInf);
    }

    /**
     * 게시판 사용정보를 등록한다.
     * 
     * @see egovframework.BBSUseInfoManageService.cop.bbs.service.com.service.EgovBBSUseInfoManageService#insertBBSUseInf(egovframework.BoardUseInf.cop.bbs.service.com.service.BoardUseInf)
     */
    public void insertBBSUseInf(BoardUseInf bdUseInf) throws Exception {
	bbsUseDAO.insertBBSUseInf(bdUseInf);
    }

    /**
     * 게시판 사용정보 목록을 조회한다.
     * 
     * @see egovframework.BBSUseInfoManageService.cop.bbs.service.com.service.EgovBBSUseInfoManageService#selectBBSUseInfs(egovframework.BoardUseInfVO.cop.bbs.service.com.service.BoardUseInfVO)
     */
    public Map<String, Object> selectBBSUseInfs(BoardUseInfVO bdUseVO) throws Exception {

	List<BoardUseInfVO> result = bbsUseDAO.selectBBSUseInfs(bdUseVO);
	int cnt = bbsUseDAO.selectBBSUseInfsCnt(bdUseVO);
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 게시판 사용정보를 수정한다.
     * 
     * @see egovframework.BBSUseInfoManageService.cop.bbs.service.com.service.EgovBBSUseInfoManageService#updateBBSUseInf(egovframework.BoardUseInf.cop.bbs.service.com.service.BoardUseInf)
     */
    public void updateBBSUseInf(BoardUseInf bdUseInf) throws Exception {
	bbsUseDAO.updateBBSUseInf(bdUseInf);
    }

    /**
     * 게시판 사용정보에 대한 상세정보를 조회한다.
     * 
     * @see egovframework.BBSUseInfoManageService.cop.bbs.service.com.service.EgovBBSUseInfoManageService#selectBBSUseInf(egovframework.BoardUseInfVO.cop.bbs.service.com.service.BoardUseInfVO)
     */
    public BoardUseInfVO selectBBSUseInf(BoardUseInfVO bdUseVO) throws Exception {
	return bbsUseDAO.selectBBSUseInf(bdUseVO);
    }

    /**
     * 게시판에 대한 사용정보를 삭제한다.
     * 
     * @see egovframework.BBSUseInfoManageService.cop.bbs.service.EgovBBSUseInfoManageService#deleteBBSUseInfByBoardId(egovframework.BoardUseInf.cop.bbs.service.BoardUseInf)
     */
    public void deleteBBSUseInfByBoardId(BoardUseInf bdUseInf) throws Exception {
	bbsUseDAO.deleteBBSUseInfByBoardId(bdUseInf);
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 목록을 조회한다.
     * 
     * @see egovframework.BBSUseInfoManageService.cop.bbs.service.EgovBBSUseInfoManageService#selectBBSUseInfsByTrget(egovframework.BoardUseInfVO.cop.bbs.service.BoardUseInfVO)
     */
    public Map<String, Object> selectBBSUseInfsByTrget(BoardUseInfVO bdUseVO) throws Exception {
	List<BoardUseInfVO> result = bbsUseDAO.selectBBSUseInfsByTrget(bdUseVO);
	int cnt = bbsUseDAO.selectBBSUseInfsCntByTrget(bdUseVO);
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("resultList", result);
	map.put("resultCnt", Integer.toString(cnt));

	return map;
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보를 수정한다.
     */
    public void updateBBSUseInfByTrget(BoardUseInf bdUseInf) throws Exception {
	bbsUseDAO.updateBBSUseInfByTrget(bdUseInf);
    }
}
