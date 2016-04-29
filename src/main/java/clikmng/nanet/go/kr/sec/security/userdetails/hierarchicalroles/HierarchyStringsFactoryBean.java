package clikmng.nanet.go.kr.sec.security.userdetails.hierarchicalroles;

import org.springframework.beans.factory.FactoryBean;

import clikmng.nanet.go.kr.sec.security.securedobject.ISecuredObjectService;

/**
 * DB 기반의 Role 계층 관계 정보를 얻어 이를 참조하는 Bean 의 초기화 데이터로
 * 제공한다.
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

public class HierarchyStringsFactoryBean implements FactoryBean {
	
    private ISecuredObjectService securedObjectService;
    private String hierarchyStrings;
    
	/**
	 * setSecuredObjectService
	 * @param securedObjectService ISecuredObjectService
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
        hierarchyStrings = (String) securedObjectService.getHierarchicalRoles();
    }
    
	/**
	 * getObject
	 * @return Object
	 * @exception Exception
	 */
    public Object getObject() throws Exception {
        if (hierarchyStrings == null) {
            init();
        }
        return hierarchyStrings;
    }
    
	/**
	 * getObjectType
	 * @return Class
	 */
    public Class getObjectType() {
        return String.class;
    }

	/**
	 * isSingleton
	 * @return boolean
	 */
    public boolean isSingleton() {
        return true;
    }
}
