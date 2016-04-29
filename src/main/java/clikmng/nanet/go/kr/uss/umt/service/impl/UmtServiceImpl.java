package clikmng.nanet.go.kr.uss.umt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.uss.umt.service.UmtService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("UmtService")
public class UmtServiceImpl extends AbstractServiceImpl implements UmtService {

	@Resource(name="UmtDao")
	private UmtDao umtDao;
	
	@Override
	public int dbListCntEnc(HashMap encMap, HashMap map, String sql) throws Exception{
	    return umtDao.dbListCntEnc(encMap, map,sql);
	}

	public List dbListEnc(HashMap encMap, HashMap map, String sql){
		return umtDao.dbListEnc(encMap, map,sql);
	}
	
	public HashMap dbReadEnc(HashMap encMap, HashMap map, String sql){
		return umtDao.dbReadEnc(encMap, map,sql);
	}
	
}
