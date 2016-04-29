package clikmng.nanet.go.kr.uss.org.service.impl;

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

@Repository("OrgDao")
public class OrgDao extends EgovComAbstractDAO{
	
	public OrgDao(){}
	
	@Resource(name="nadlSqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSuperSqlMapClient(sqlMapClient);
    }
	
	/**
	 * 
	 */
    public int dbListCnt(HashMap map, String sql) throws Exception {

		return (Integer)getSqlMapClientTemplate().queryForObject(sql, map);
    }    
    
    
	public List<HashMap> dbList(HashMap map, String sql) {

		return list(sql, map);
	}
    
	/**
	 * HashMap 형태의 반환
	 * @param map
	 * @param sql
	 * @return
	 */
	public HashMap dbRead(HashMap map, String sql) {
		return (HashMap)selectByPk(sql, map);
	}
	
    
    
	/**
	 * 암복호화
	 * @param map
	 * @param sql
	 * @param type 1:암호화, 2:복호화
	 * @return
	 */
/*	
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
*/	
	
}
