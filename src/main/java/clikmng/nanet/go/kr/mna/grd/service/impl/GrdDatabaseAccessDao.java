package clikmng.nanet.go.kr.mna.grd.service.impl;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;

/**
 * 
 * 등급별 데이터베이스  권한에대한 데이터 접근 클래스를 정의한다
 * @author 
 * @since 
 * @version 
 * @see
 */

@Repository("GrdDatabaseAccessDao")
public class GrdDatabaseAccessDao extends EgovComAbstractDAO{

	/**
	 * 등급별 데이터베이스  권한을 등록한다.
	 * @param grdDatabaseAccessVO
	 * @throws Exception
	 */
	public void insertGrdDatabaseAccess(GrdDatabaseAccessDao grdDatabaseAccessVO) throws Exception{
		
	}

	/**
	 * 등급별 데이터베이스  권한을 변경한다.
	 * @param grdDatabaseAccessVO
	 * @throws Exception
	 */	
	public void modifyGrdDatabaseAccess(GrdDatabaseAccessDao grdDatabaseAccessVO) throws Exception{
		
	}
	
	
	/**
	 * 등급별 데이터베이스  권한을 조회한다.
	 * @param grdDatabaseAccessVO
	 * @throws Exception
	 */
	public void selectGrdDatabaseAccess(GrdDatabaseAccessDao grdDatabaseAccessVO) throws Exception{
		
	}	
}
