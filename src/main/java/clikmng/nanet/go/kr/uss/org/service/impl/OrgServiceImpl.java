package clikmng.nanet.go.kr.uss.org.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.uss.org.service.OrgService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("OrgService")
public class OrgServiceImpl extends AbstractServiceImpl implements OrgService {

	@Resource(name="OrgDao")
	private OrgDao orgDao;
	
	@Override
	public int dbListCnt(HashMap map, String sql) throws Exception{
	    return orgDao.dbListCnt(map,sql);
	}
	
	public List dbList(HashMap map, String sql){
		return orgDao.dbList(map,sql);
	}
	
	public HashMap dbRead(HashMap map, String sql){
		return orgDao.dbRead(map,sql);
	}
	
}
