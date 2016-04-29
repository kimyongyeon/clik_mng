package clikmng.nanet.go.kr.uss.org.service;

import java.util.HashMap;
import java.util.List;

public interface OrgService {
	
	
	/**
	 * 검색조건에 의한 협정기관 조회 카운트
	 * @param map
	 * @param sql
	 * @return
	 */
	public int dbListCnt(HashMap map, String sql) throws Exception;
	
	/**
	 * 검색조건에 의한 협정기관 조회 리스트
	 * @param map
	 * @param sql
	 * @return
	 */
	public List dbList(HashMap map, String sql) throws Exception;
	
	/**
	 * 검색조건에 의한 협정기관 상세보기
	 * @param map
	 * @param sql
	 * @return
	 */
	public HashMap dbRead(HashMap map , String sql) throws Exception;
	
	
}
