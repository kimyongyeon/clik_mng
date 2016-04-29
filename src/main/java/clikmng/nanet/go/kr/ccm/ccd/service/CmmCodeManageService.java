package clikmng.nanet.go.kr.ccm.ccd.service;

import java.util.List;

import clikmng.nanet.go.kr.cmm.service.CmmnDetailCode;

/**
 * 
 * 공통코드에 관한 서비스 인터페이스 클래스를 정의한다
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
 *
 * </pre>
 */
public interface CmmCodeManageService {


	/**
	 * 공통분류코드를 등록한다.
	 * @param cmmnClCode
	 * @throws Exception
	 */
	void insertCmmClCode(CmmClCode cmmClCode) throws Exception;	
		
	
	/**
	 * 공통코드를 등록한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void insertCmmCode(CmmCode cmmnCode) throws Exception;

	/**
	 * 공통상세코드를 등록한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void insertCmmDetailCode(CmmnDetailCode cmmnCode) throws Exception;

	

	/**
	 * 공통분류코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(공통코드 목록)
	 * @throws Exception
	 */
	List selectCmmClCodeList(CmmCodeVO searchVO) throws Exception;	
	
	
	/**
	 * 공통코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(공통코드 목록)
	 * @throws Exception
	 */
	List selectCmmCodeList(CmmCodeVO searchVO) throws Exception;


	/**
	 * 공통상세코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(공통코드 목록)
	 * @throws Exception
	 */
	List selectCmmDetailCodeList(CmmCodeVO searchVO) throws Exception;	
		

	/**
	 * 공통분류코드 상세항목을 조회한다.
	 * @param cmmnCode
	 * @return CmmnCode(공통코드)
	 * @throws Exception
	 */
	CmmCodeVO selectCmmClCode(CmmCodeVO cmmnCode) throws Exception;	 
	 
	/**
	 * 공통코드 상세항목을 조회한다.
	 * @param cmmnCode
	 * @return CmmnCode(공통코드)
	 * @throws Exception
	 */
	CmmCodeVO selectCmmCode(CmmCodeVO cmmnCode) throws Exception;

	
	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @param cmmnCode
	 * @return CmmnCode(공통코드)
	 * @throws Exception
	 */
	CmmCodeVO selectCmmCodeDetail(CmmCodeVO cmmnCode) throws Exception;	

    /**
	 * 공통코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통코드 총 갯수)
     */
    int selectCmmCodeListTotCnt(CmmCodeVO searchVO) throws Exception;

	/**
	 * 공통분류코드를 수정한다.
	 * @param cmmClCode
	 * @throws Exception
	 */
	void updateCmmClCode(CmmClCode cmmnClCode) throws Exception;    
    
	/**
	 * 공통코드를 수정한다.
	 * @param cmmCode
	 * @throws Exception
	 */
	void updateCmmCode(CmmCode cmmnCode) throws Exception;
	
	/**
	 * 공통상세코드를 수정한다.
	 * @param cmmClCode
	 * @throws Exception
	 */
	void updateCmmDetailCode(CmmnDetailCode cmmDetailCode) throws Exception;	
    
	/**
	 * 공통상세코드 순번을 수정한다.
	 * @param cmmClCode
	 * @throws Exception
	 */
	void updateCmmDetailCodeOrdr(CmmnDetailCode cmmDetailCode) throws Exception;	
    	
	
	/**
	 * 공통분류코드를 삭제한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void deleteCmmClCode(CmmClCode cmmnClCode) throws Exception;	
	
	/**
	 * 공통코드를 삭제한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void deleteCmmCode(CmmCode cmmnCode) throws Exception;
	
	/**
	 * 공통상세코드를 삭제한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void deleteCmmDetailCode(CmmnDetailCode cmmDetailCode) throws Exception;	
	
	/**
	 * 공통코드 순번을 수정한다.
	 * @param cmmClCode
	 * @throws Exception
	 */
	void updateCmmCodeOrdr(CmmCode cmmCode) throws Exception;
}
