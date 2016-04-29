package clikmng.nanet.go.kr.sns.service;

import clikmng.nanet.go.kr.cmm.service.EgovProperties;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;

public class SnsFacebook {
	
	/** APP ID */
	private String API_KEY;
	/** APP SECRET */
	private String API_SECRET;
	/** APP ACCESS TOKEN */
	private String APP_ACCESS_TOKEN;
	private Facebook facebook;
	
	public SnsFacebook() {
		API_KEY = EgovProperties.getPathProperty("FB_API_KEY");
		API_SECRET = EgovProperties.getPathProperty("FB_API_SECRET");
		APP_ACCESS_TOKEN = EgovProperties.getPathProperty("FB_APP_ACCESS_TOKEN");
		
		ConfigurationBuilder confBuilder = new ConfigurationBuilder(); 
		confBuilder.setDebugEnabled(true);
		
		confBuilder.setOAuthAppId(API_KEY);
		confBuilder.setOAuthAppSecret(API_SECRET);
		confBuilder.setOAuthAccessToken(APP_ACCESS_TOKEN);

		confBuilder.setOAuthPermissions("email,publish_actions,publish_stream");
		confBuilder.setUseSSL(true);
		confBuilder.setJSONStoreEnabled(true);
		
		Configuration configuration = confBuilder.build();

		FacebookFactory facebookFactory = new FacebookFactory(configuration); 
		facebook = facebookFactory.getInstance();
	}
	
	/**
	 * 페이스북 최신등록글을 조회한다.
	 * @return
	 * @throws FacebookException
	 */
	public SnsVO getFacebookFeeds(String userId) {
		Reading reading = new Reading();
	    reading.limit(1);//조회할 글수 
	    Post post = null;
	    SnsVO snsBean = null;
	    
    	try{
	    	ResponseList<Post> list = facebook.getPosts(userId, reading);
		    
	    	if(list != null && list.size() > 0){
			    post = list.get(0);
			    
				snsBean = new SnsVO();
				snsBean.setSns_se_code("FB");
				snsBean.setSns_acnt_id(userId);
				snsBean.setNbc(post.getMessage());
				snsBean.setNbc_link(post.getLink().toString());
	    	}
    	}catch(Exception e){
    		System.out.println("FacebookException : [" + userId + "]");
    		e.printStackTrace();
    	}

	    return snsBean;
	}
}
