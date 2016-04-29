package clikmng.nanet.go.kr.cmm.auth;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.FactoryBean;

import clikmng.nanet.go.kr.sec.security.securedobject.ISecuredObjectService;

/**
 * DB 기반의 보호자원 맵핑 정보를 얻어 이를 참조하는 Bean 의 초기화 데이터로 제공한다.
 * 
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *
 * </pre>
 */

public class AuthMapFactoryBean implements FactoryBean {
	
    private List<Map<String, Object>> authMap;
    private ISecuredObjectService securedObjectService;

    
	/**
	 * setSecuredObjectService
	 * @param securedObjectService
	 * @exception Exception
	 */
    public void setSecuredObjectService(
            ISecuredObjectService securedObjectService) {
        this.securedObjectService = securedObjectService;
    }
    
	/**
	 * init
	 * @exception Exception
	 */
    public void init() throws Exception {
    	
    	authMap = securedObjectService.getRoles();
    	
    }
    
	/**
	 * getObject
	 * @return Object resourcesMap
	 * @exception Exception
	 */
    public Object getObject() throws Exception {
        if (authMap == null) {
            init();
        }
        return authMap;
    }
    
	/**
	 * getObjectType
	 * @return Class LinkedHashMap.class
	 */
    public Class getObjectType() {
        return List.class;
    }
    
	/**
	 * isSingleton
	 * @return boolean
	 */
    public boolean isSingleton() {
        return true;
    }
}
