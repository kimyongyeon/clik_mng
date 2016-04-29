package clikmng.nanet.go.kr.uss.mng.service;

import java.util.HashMap;
import java.util.List;

public interface DocsService {
	
	/**
	 * Docs Server 회원정보를 조회한다.
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List selectSearchEmp(DocsVO docsVO) throws Exception;
	public int selectSearchEmpCnt(DocsVO docsVO) throws Exception;
	
	/**
	 * Docs Server 회원정보를 조회하여 있는지 확인
	 * @param map
	 * @return 
	 * @throws Exception 
	 */
	public int confirmUserAt(HashMap<String, String> map) throws Exception;
	

	/**
	 * Docs Server 회원정보를 조회한다. -- 메일링 등록 팝업
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public List selectPopSearchEmp(DocsVO docsVO) throws Exception;
	public int selectPopSearchEmpCnt(DocsVO docsVO) throws Exception;
	
		
}
