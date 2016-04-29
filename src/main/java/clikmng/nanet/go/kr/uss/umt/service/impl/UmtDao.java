package clikmng.nanet.go.kr.uss.umt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import clikmng.nanet.go.kr.cmm.enc.NanetSEED;
import clikmng.nanet.go.kr.cmm.service.impl.EgovComAbstractDAO;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository("UmtDao")
public class UmtDao extends EgovComAbstractDAO{
	
	public UmtDao(){}
	
	@Resource(name="nanetSqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSuperSqlMapClient(sqlMapClient);
    }
	
	/**
	 * 
	 */
    public int dbListCntEnc(HashMap encMap, HashMap map, String sql) throws Exception {

		//파라미터 암호화
		map = encDec(encMap, map, 1);

		return (Integer)getSqlMapClientTemplate().queryForObject(sql, map);
    }
	
	/**
	 * List 반환(암호화)
	 * @param map
	 * @param sql
	 * @return
	 */
	public List<HashMap> dbListEnc(HashMap encMap, HashMap map, String sql) {

		//파라미터 암호화
		map = encDec(encMap, map, 1);
		
		//리스트 복호화
		List<HashMap> list = list(sql, map);
		List<HashMap> resultList = new ArrayList<HashMap>();
		for(int i=0;i<list.size();i++){
			HashMap row = list.get(i);
			if(row != null)
				row = encDec(encMap, row, 2);
			resultList.add(row);
		}
		
		return resultList;
	}
	
	
	/**
	 * 특정 아이디 상세보기
	 * @param map
	 * @param sql
	 * @return
	 */
	public HashMap dbReadEnc(HashMap encMap, HashMap map, String sql) {
		//파라미터 암호화
		map = encDec(encMap, map, 1);
		HashMap resultMap = (HashMap)selectByPk(sql, map);
		if(resultMap != null)
			resultMap = encDec(encMap, resultMap, 2);
		return resultMap;
	}	
    
    
    
	/**
	 * 암복호화
	 * @param map
	 * @param sql
	 * @param type 1:암호화, 2:복호화
	 * @return
	 */
	private HashMap encDec(HashMap encMap, HashMap map, int type) {
		Set keySet = map.keySet();
		Iterator iterator = keySet.iterator();
		while(iterator.hasNext()) {
			String keyName = (String)iterator.next();
			if(encMap.containsKey(keyName)){
				if("enc".equals(encMap.get(keyName))){
					if(type == 1){
						map.put(keyName, NanetSEED.getEnc(map.get(keyName).toString()));
					}else{
						map.put(keyName, NanetSEED.getDec(map.get(keyName).toString()));
					}
				}
			}
		}
		return map;
	}	
	
	
}
