package clikmng.nanet.go.kr.uss.mng.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import clikmng.nanet.go.kr.sns.service.SnsManageVO;
import clikmng.nanet.go.kr.uss.mng.service.DocsService;
import clikmng.nanet.go.kr.uss.mng.service.DocsVO;
import clikmng.nanet.go.kr.uss.mng.service.MngService;
import clikmng.nanet.go.kr.uss.mng.service.MngVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("DocsService")
public class DocsServiceImpl extends AbstractServiceImpl implements DocsService {

	@Resource(name="DocsDAO")
	private DocsDAO dao;

	@Override
	public List selectSearchEmp(DocsVO docsVO) throws Exception {
		return (List)dao.selectSearchEmp(docsVO);
	}
	
    public int selectSearchEmpCnt(DocsVO docsVO) throws Exception {
        return (Integer)dao.selectSearchEmpCnt(docsVO);
    }
	
    public int confirmUserAt(HashMap<String, String> map) throws Exception {
        return (Integer)dao.confirmUserAt(map);
    }
    
    
    // 메일링 국회의원 검색
    public List selectPopSearchEmp(DocsVO docsVO) throws Exception {
		return (List)dao.selectPopSearchEmp(docsVO);
	}
    
    // 메일링 국회의원 검색	
    public int selectPopSearchEmpCnt(DocsVO docsVO) throws Exception {
        return (Integer)dao.selectPopSearchEmpCnt(docsVO);
    }
    
}
