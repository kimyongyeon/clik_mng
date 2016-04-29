package clikmng.nanet.go.kr.mna.grd.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.mna.grd.service.GrdDatabaseAccessService;
import clikmng.nanet.go.kr.mna.grd.service.GrdDatabaseAccessVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 
 * 등급별 데이터베이스 접근에 대한 서비스 구현클래스를 정의한다
 * @author 
 * @since 
 * @version 
 * @see
 */

@Service("GrdDatabaseAccessService")
public class GrdDatabaseAccessServiceImpl extends AbstractServiceImpl implements GrdDatabaseAccessService {

	@Resource(name="GrdDatabaseAccessDao")
	private GrdDatabaseAccessDao grdDatabaseAccessDao;
	
	/**
	 * 등급별 데이터베이스 접근 목록을 조회한다
	 */
	@Override
	public void selectGrdDatabaseAccess(GrdDatabaseAccessVO grdAccessVO)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 등급별 데이터베이스 접근 목록을 등록한다
	 */
	@Override
	public void insertGrdDatabaseAccess(GrdDatabaseAccessVO grdAccessVO)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 등급별 데이터베이스 접근 목록을 변경한다
	 */
	@Override
	public void modifyGrdDatabaseAccess(GrdDatabaseAccessVO grdAccessVO)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
