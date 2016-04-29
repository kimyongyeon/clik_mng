package clikmng.nanet.go.kr.mna.grd.service;


public interface GrdDatabaseAccessService {
	
	
	/**
	 * 등급별 데이터베이스 접근 목록을 조회한다
	 * @param grdDatabaseAccessVO
	 * @throws Exception
	 */		
	void selectGrdDatabaseAccess(GrdDatabaseAccessVO grdAccessVO) throws Exception;
	
	
	/**
	 * 등급별 데이터베이스 접근 목록을 등록한다
	 * @param grdDatabaseAccessVO
	 * @throws Exception
	 */
	void insertGrdDatabaseAccess(GrdDatabaseAccessVO grdAccessVO) throws Exception;
	
	
	/**
	 * 등급별 데이터베이스 접근 목록을 변경한다
	 * @param grdDatabaseAccessVO
	 * @throws Exception
	 */	
	void modifyGrdDatabaseAccess(GrdDatabaseAccessVO grdAccessVO) throws Exception;	
	
}
