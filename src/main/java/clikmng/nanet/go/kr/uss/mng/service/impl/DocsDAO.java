package clikmng.nanet.go.kr.uss.mng.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;
import clikmng.nanet.go.kr.sns.service.SnsManageVO;
import clikmng.nanet.go.kr.uss.mng.service.DocsVO;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("DocsDAO")
public class DocsDAO extends EgovComAbstractDAO{
	
	public DocsDAO(){}
	
	@Resource(name="docsSqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSuperSqlMapClient(sqlMapClient);
    }
	
	/**
	 * Docs 서버 조회
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	public List selectSearchEmp(DocsVO docsVO) throws Exception {
	    return (List)list("DocsManage.selectSearchEmp", docsVO);
	}	

    public int selectSearchEmpCnt(DocsVO docsVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("DocsManage.selectSearchEmpCnt", docsVO);
    }
	
    /**
     * Docs 서버에 회원존재 유무 확인
	 * @param 
	 * @return 1: 유, 0 : 무
	 * @throws Exception
     */
    public int confirmUserAt(HashMap<String, String> map) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("DocsManage.confirmUserAt", map);
    }
    
    
    /**
     * Docs 서버에 회원존재 유무 확인 - 메일링 회원검색
	 * @param 
	 * @return 
	 * @throws Exception
     */    
	public List selectPopSearchEmp(DocsVO docsVO) throws Exception {
	    return (List)list("DocsManage.selectPopSearchEmp", docsVO);
	}	

    public int selectPopSearchEmpCnt(DocsVO docsVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("DocsManage.selectPopSearchEmpCnt", docsVO);
    }    
    
}
