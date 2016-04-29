package clikmng.nanet.go.kr.elm.com;

import java.io.Serializable;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;

/**
 * 사용자 클래스 데이터 모델 클래스
 * @author 
 * @since 
 * @version 
 * @table : user_class
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   
 *
 * </pre>
 */
public class UserClassVO extends ComDefaultVO implements Serializable 
{

	private static final long serialVersionUID = 1L;

	private String userGroupId;
	private String userGroupNm;
	private String userClassNm;
	private String userSubClassNm;
	
	private String frstRegisterId;
	private String frstRegistPnttm;
	private String lastUpdusrId;
	private String lastUpdtPnttm;

/*
	public UserClassVO()
	{
		userClassNm 			= 	"";
		userSubClassNm 		= 	"";
		userGroupId 				= 	"";
		userGroupNm 			= 	"";
		frstRegisterId 			= 	"";
		frstRegistPnttm 		= 	"";
		lastUpdusrId 				= 	"";
		lastUpdtPnttm 			= 	"";	
	}
*/

	public String getUserClassNm() {
		return userClassNm;
	}


	public void setUserClassNm(String userClassNm) {
		this.userClassNm = userClassNm;
	}


	public String getUserSubClassNm() {
		return userSubClassNm;
	}


	public void setUserSubClassNm(String userSubClassNm) {
		this.userSubClassNm = userSubClassNm;
	}


	public String getUserGroupId() {
		return userGroupId;
	}


	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}


	public String getUserGroupNm() {
		return userGroupNm;
	}


	public void setUserGroupNm(String userGroupNm) {
		this.userGroupNm = userGroupNm;
	}


	public String getFrstRegisterId() {
		return frstRegisterId;
	}


	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}


	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}


	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}


	public String getLastUpdusrId() {
		return lastUpdusrId;
	}


	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}


	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}


	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	
	
}
