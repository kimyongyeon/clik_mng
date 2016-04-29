package clikmng.nanet.go.kr.sns.service;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import clikmng.nanet.go.kr.cmm.service.EgovProperties;

public class SnsTwitter {
	
	/** Api key */
	private String API_KEY;
	/** Api secret */
	private String API_SECRET;
	/** Access token */
	private String ACCESS_TOKEN;
	/** Access token secret */
	private String ACCESS_TOKEN_SECRET;
	private Twitter twitter;
	
	public SnsTwitter() {
		API_KEY = EgovProperties.getPathProperty("TW_API_KEY");
		API_SECRET = EgovProperties.getPathProperty("TW_API_SECRET");
		ACCESS_TOKEN = EgovProperties.getPathProperty("TW_ACCESS_TOKEN");
		ACCESS_TOKEN_SECRET = EgovProperties.getPathProperty("TW_ACCESS_TOKEN_SECRET");
		
		AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(API_KEY, API_SECRET);
		twitter.setOAuthAccessToken(accessToken);
	}
	
	/**
	 * 트위터 최신등록글을 조회한다.
	 * @return
	 * @throws TwitterException
	 */
	public SnsVO getRecentTwit(String userId){
		
		Status status = null;
		User user = null;
		SnsVO snsBean = null;
		
		try {
			ResponseList<Status> result = twitter.getUserTimeline(userId, new Paging().count(1));
		
			status = result.get(0);
			user = status.getUser();
			
			snsBean = new SnsVO();
			snsBean.setSns_se_code("TW");
			snsBean.setSns_acnt_id(userId);
			snsBean.setNbc(status.getText());
			
		} catch (TwitterException e) {
			System.out.println("TwitterException : [" + userId + "]");
			e.printStackTrace();
		}
		
		return snsBean;
	}
}
