package clikmng.nanet.go.kr.uss.umt.service;

import java.util.HashMap;
import java.util.List;

public interface UmtService {
	
	
	
	/**
	 * 검색조건에 의한 이용자 조회 카운트
	 * @param encMap
	 * @param map
	 * @param sql
	 * @return
	 */
	public int dbListCntEnc(HashMap encMap, HashMap map, String sql) throws Exception;
	
	
	/**
	 * 검색조건에 의한 이용자 조회 리스트
	 * @param encMap
	 * @param map
	 * @param sql
	 * @return
	 */
	public List dbListEnc(HashMap encMap, HashMap map, String sql) throws Exception;

	/**
	 * 특정  아이디 상세보기
	 * @param encMap
	 * @param map
	 * @param sql
	 * @return
	 */
	public HashMap dbReadEnc(HashMap encMap, HashMap map , String sql) throws Exception;
	

}
