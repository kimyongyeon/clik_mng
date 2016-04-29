package clikmng.nanet.go.kr.sit.spc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.cmm.service.FileVO;
import clikmng.nanet.go.kr.sit.spc.service.SpcDefaultVO;
import clikmng.nanet.go.kr.sit.spc.service.SpcService;
import clikmng.nanet.go.kr.sit.spc.service.SpcVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * 
 * 스페셜검색를 처리하는 구현 클래스
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
@Service("SpcService")
public class SpcServiceImpl extends AbstractServiceImpl implements
        SpcService {

    @Resource(name="SpcDAO")
    private SpcDAO spcDAO;
        
    /**
	 * 스페셜검색 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
    public List selectSpcList(SpcVO spcVO) throws Exception {
        return spcDAO.selectSpcList(spcVO);
    }
	

    /**
	 * 스페셜검색 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
    public int selectSpcListTotCnt(SpcDefaultVO searchVO) {
		return spcDAO.selectSpcListTotCnt(searchVO);
	}	
    
    
    /**
	 * 스페셜 검색 등록
	 * @param searchVO  
	 * @return 글 목록
	 * @exception Exception
	 */
    public void insertSpcInfo(SpcVO spcVO) throws Exception {
        spcDAO.insertSpcInfo(spcVO);
    }
	// 0. 해당 테이블에 관련 키워드를 먼저 삭제한다.
	
    
	/**
	 * 스페셜 검색 :: 키워드 삭제
	 * @param searchVO 
	 * @return 글 목록
	 * @exception Exception
	 */
    public void deleteKwrdInfo(SpcVO spcVO) throws Exception {
    	spcDAO.deleteKwrdInfo(spcVO);
    }
    
    /**
	 * 스페셜 검색 :: 키워드 등록
	 * @param searchVO 
	 * @return 글 목록
	 * @exception Exception
	 */
    public void insertKwrdInfo(SpcVO spcVO) throws Exception {
        spcDAO.insertKwrdInfo(spcVO);
    }
    
    /**
	 * 스페셜 검색 :: 상세보기 조회
	 * @param searchVO 
	 * @return 글 목록
	 * @exception Exception
	 */
    public SpcVO selectSpcDetail(SpcVO spcVO) throws Exception {
        return spcDAO.selectSpcDetail(spcVO);
    }    
    
    /**
	 * 스페셜 검색 :: 키워드 조회
	 * @param searchVO 
	 * @return 글 목록
	 * @exception Exception
	 */
    public List selectSpcDetailKwrd(SpcVO spcVO) throws Exception {
        return spcDAO.selectSpcDetailKwrd(spcVO);
    }    
    
    /**
     * 스페셜 검색 :: 정보삭제
     * @param searchVO
     * @return 상세보기
     * @throws Exception
     */
    public void deleteSpcInf(SpcVO spcVO) throws Exception{
    	//0. 관련검색어 삭제
    	spcDAO.deleteKwrdInfo(spcVO);
    	//1. 스페셜검색 정보삭제
    	spcDAO.deleteSpcInfo(spcVO);
    	
    }
    
    
    /**
     * 스페셜 검색 :: 정보수정
     * @param searchVO
     * @return 상세보기
     * @throws Exception
     */
    public void updateSpcInfo(SpcVO spcVO) throws Exception{
    	spcDAO.updateSpcInfo(spcVO);
    }    
    
}
