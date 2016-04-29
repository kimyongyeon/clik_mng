package clikmng.nanet.go.kr.mna.lau.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.mna.lau.service.LtrwkAuthService;
import clikmng.nanet.go.kr.mna.lau.service.LtrwkAuthVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 
 * 저작물권한에 대한 서비스 구현클래스를 정의한다
 * @author 
 * @since 
 * @version 
 * @see
 */

@Service("LtrwkAuthService")
public class LtrwkAuthServiceImpl extends AbstractServiceImpl implements LtrwkAuthService {

	@Resource(name="LtrwkAuthDao")
	private LtrwkAuthDao ltrwkAuthDao;
	
	/**
	 * 저작물권한 목록을 조회한다
	 */	
	@Override
	public void selectLtrwkAuth(LtrwkAuthVO ltrwkAuthVO) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 저작물권한 목록을 등록한다
	 */		
	@Override
	public void insertLtrwkAuth(LtrwkAuthVO ltrwkAuthVO) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 저작물권한 목록을 변경한다
	 */		
	@Override
	public void modifyLtrwkAuth(LtrwkAuthVO ltrwkAuthVO) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 저작물권한 목록을 삭제한다
	 */		
	@Override
	public void deleteLtrwkAuth(LtrwkAuthVO ltrwkAuthVO) throws Exception {
		// TODO Auto-generated method stub

	}

}
