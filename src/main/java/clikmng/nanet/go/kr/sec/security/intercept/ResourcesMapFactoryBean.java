package clikmng.nanet.go.kr.sec.security.intercept;

import java.util.LinkedHashMap;

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

public class ResourcesMapFactoryBean implements FactoryBean {
	
    private String resourceType;
    private LinkedHashMap resourcesMap;
    private ISecuredObjectService securedObjectService;

	/**
	 * setResourceType
	 * @param resourceType
	 * @exception Exception
	 */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    
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
        if ("method".equals(resourceType)) {
            resourcesMap = securedObjectService.getRolesAndMethod();
        } else if ("pointcut".equals(resourceType)) {
            resourcesMap = securedObjectService.getRolesAndPointcut();
        } else {
            resourcesMap = securedObjectService.getRolesAndUrl();
        }
    }
    
	/**
	 * getObject
	 * @return Object resourcesMap
	 * @exception Exception
	 */
    public Object getObject() throws Exception {
        if (resourcesMap == null) {
            init();
        }
        return resourcesMap;
    }
    
	/**
	 * getObjectType
	 * @return Class LinkedHashMap.class
	 */
    public Class getObjectType() {
        return LinkedHashMap.class;
    }
    
	/**
	 * isSingleton
	 * @return boolean
	 */
    public boolean isSingleton() {
        return true;
    }
}
