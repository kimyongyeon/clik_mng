package clikmng.nanet.go.kr.sit.spc.service;

import java.util.List;

import javax.annotation.Resource;

import clikmng.nanet.go.kr.cmm.service.FileVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 스페셜검색 처리하는 클래스
 */
public interface SpcService {
    
	/**
     * 스페셜검색 목록을 조회한다.
     * @param searchVO
     * @return 글 목록
     * @throws Exception
     */
    public List selectSpcList(SpcVO spcVO) throws Exception;
	
    /**
     * 사이트정보 총 갯수를 조회한다.
     * @param searchVO
     * @return	총 갯수
     */
    public int selectSpcListTotCnt(SpcDefaultVO searchVO);
    
    
    /**
     * 스페셜 검색 등록
     * @param searchVO
     * @return 글 목록
     * @throws Exception
     */
    public void insertSpcInfo(SpcVO spcVO) throws Exception;
    
    /**
     * 스페셜 검색 :: 키워드 삭제, 등록
     * @param searchVO
     * @return 글 목록
     * @throws Exception
     * 
     * 1. 키워드 테이블에 관련 키워드를 먼저 삭제함.
     * 2. 다시 키워드를 입력함.
     */
    public void deleteKwrdInfo(SpcVO spcVO) throws Exception;
    public void insertKwrdInfo(SpcVO spcVO) throws Exception;
    
    /**
     * 스페셜 검색 :: 상세보기, 키워드 가져오기
     * @param searchVO
     * @return 상세보기
     * @throws Exception
     */
    public SpcVO selectSpcDetail(SpcVO spcVO) throws Exception;
    public List selectSpcDetailKwrd(SpcVO spcVO) throws Exception;    
    
    /**
     * 스페셜 검색 :: 정보삭제
     * @param searchVO
     * @return 상세보기
     * @throws Exception
     */
    public void deleteSpcInf(SpcVO spcVO) throws Exception;
    
    /**
     * 스페셜 검색 :: 정보수정
     * @param searchVO
     * @return 상세보기
     * @throws Exception
     */
    public void updateSpcInfo(SpcVO spcVO) throws Exception;    
    
}
