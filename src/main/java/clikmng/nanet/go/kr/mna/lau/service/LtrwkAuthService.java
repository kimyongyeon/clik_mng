package clikmng.nanet.go.kr.mna.lau.service;

/**
 * 
 * 저작물권한에 관한 서비스 인터페이스 클래스를 정의한다
 * @author 
 * @since 
 * @version 
 * @see
 */


public interface LtrwkAuthService {

	/**
	 *  저작물권한 목록을 조회한다
	 * @param ltrwkAuthVO
	 * @throws Exception
	 */	
	void selectLtrwkAuth(LtrwkAuthVO ltrwkAuthVO) throws Exception;
	
	
	/**
	 *  저작물권한을 등록한다
	 * @param ltrwkAuthVO
	 * @throws Exception
	 */		
	void insertLtrwkAuth(LtrwkAuthVO ltrwkAuthVO) throws Exception;	
	
	/**
	 *  저작물권한을 수정한다
	 * @param ltrwkAuthVO
	 * @throws Exception
	 */		
	void modifyLtrwkAuth(LtrwkAuthVO ltrwkAuthVO) throws Exception;	
	
	/**
	 *  저작물권한을 삭제한다
	 * @param ltrwkAuthVO
	 * @throws Exception
	 */		
	void deleteLtrwkAuth(LtrwkAuthVO ltrwkAuthVO) throws Exception;
}
