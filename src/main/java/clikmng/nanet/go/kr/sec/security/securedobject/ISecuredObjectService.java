/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clikmng.nanet.go.kr.sec.security.securedobject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.ConfigAttributeDefinition;

/**
 * Provides interfaces that ISecuredObjectService is an
 * interface for enabling applications to approach the
 * data of secured object resources from DataBase which
 * is refered to initial data of Spring Security.
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

public interface ISecuredObjectService {
    Log LOGGER = LogFactory.getLog(ISecuredObjectService.class);

	/**
	 * url 형식의 role 권한 획득
	 * @return LinkedHashMap
	 * @exception Exception
	 */
    public List<Map<String, Object>> getRoles() throws Exception;

	/**
	 * url 형식의 role 권한 획득
	 * @return LinkedHashMap
	 * @exception Exception
	 */
    public LinkedHashMap getRolesAndUrl() throws Exception;
    
	/**
	 * method 형식의 role 권한 획득
	 * @return LinkedHashMap
	 * @exception Exception
	 */
    public LinkedHashMap getRolesAndMethod() throws Exception;

	/**
	 * pointcut 형식의 role 권한 획득
	 * @return LinkedHashMap
	 * @exception Exception
	 */
    public LinkedHashMap getRolesAndPointcut() throws Exception;

	/**
	 * getMatchedRequestMapping
	 * @return ConfigAttributeDefinition
	 * @exception Exception
	 */    
    public ConfigAttributeDefinition getMatchedRequestMapping(String url)
            throws Exception;
    
	/**
	 * role 의 계층(Hierarchy) 관계를 조회
	 * @return String
	 * @exception Exception
	 */   
    public String getHierarchicalRoles() throws Exception;

}
