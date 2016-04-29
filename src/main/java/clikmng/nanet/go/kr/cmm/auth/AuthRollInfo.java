package clikmng.nanet.go.kr.cmm.auth;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;

import clikmng.nanet.go.kr.sec.security.securedobject.ISecuredObjectService;

/**
 * DefaultFilterInvocationDefinitionSource 클래스의 확장 클래스
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

public class AuthRollInfo implements ApplicationContextAware {
	
    private MessageSource messageSource;
    private ISecuredObjectService securedObjectService;
    private List<Map<String, Object>> authUrlPattern;

    /**
     * set ApplicationContext.
     * @param applicationContext
     *        to be set by container
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.messageSource = (MessageSource) applicationContext.getBean("messageSource");
    }

    /**
     * getMessageSource
     * @return the messageSource
     */
    protected MessageSource getMessageSource() {
        return messageSource;
    }
    
    /**
     * setSecuredObjectService
     * @return void
     */
    public void setSecuredObjectService(ISecuredObjectService securedObjectService) {
        this.securedObjectService = securedObjectService;
    }

    public AuthRollInfo(List<Map<String, Object>> requestMap) {
//        super(urlMatcher, requestMap);
    	try {
    		setAuthUrlPattern(requestMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * reloadRequestMap
     * @return void
	 * @throws Exception
     */
    public void reloadRequestMap() throws Exception {

        try {
            List<Map<String, Object>> reloadedMap = securedObjectService.getRoles();

            System.out.println(reloadedMap);
            //Iterator iterator = reloadedMap.entrySet().iterator();

//            // 이전 데이터 삭제
//            Map mapToUse = getRequestMap();
//            mapToUse.clear();

//            while (iterator.hasNext()) {
//            	
//                Map.Entry entry = (Map.Entry) iterator.next();
//                RequestKey reqKey = (RequestKey) entry.getKey();
//                
//                System.out.println("reqKey.getUrl():" + reqKey.getUrl());
//                
////                addSecureUrl(reqKey.getUrl(), reqKey.getMethod(), (ConfigAttributeDefinition) entry.getValue());
//                
//            }

            // System.out.println("■
            // ReloadableDefaultFilterInvocationDefinitionSource.reloadRequestMap
            // processed! ■");
            System.out.println("Secured Url Resources - Role Mappings reloaded at Runtime!");

        } catch (Exception e) {
        	System.out.println(getMessageSource().getMessage("error.security.runtime.error", new Object[] {"Reload RequestMap" }, Locale.getDefault()));
            /* 주석처리 
            if (e instanceof Exception) {
                throw (Exception) e;
            } else {
                throw new Exception(e);
            }*/
        }
    }

	public List<Map<String, Object>> getAuthUrlPattern() {
		return authUrlPattern;
	}

	public void setAuthUrlPattern(List<Map<String, Object>> authUrlPattern) {
		this.authUrlPattern = authUrlPattern;
	}
    
    /**
     * addSecureUrl
	 * @param pattern
	 * @param method
	 * @param attr
	 * @return void
	 * @throws Exception
     */
//    void addSecureUrl(String pattern, String method, ConfigAttributeDefinition attr) {
//    	
//    	Map<String, List<String>> map = new HashMap<String, List<String>>();
//    	
//    	
//    	map.put(pattern, value)
//    	authUrlPattern.add();
//    	
//        List<Map<String, String>> mapToUse = getRequestMap();
//
//        mapToUse.put(getUrlMatcher().compile(pattern), attr);
//
//        System.out.println("Added URL pattern: " + pattern + "; attributes: "
//                + attr
//                + (method == null ? "" : " for HTTP method '" + method + "'"));
//    }
}
