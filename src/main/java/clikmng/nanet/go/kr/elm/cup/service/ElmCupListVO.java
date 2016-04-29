package clikmng.nanet.go.kr.elm.cup.service;

import java.io.Serializable;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;

public class ElmCupListVO  extends ComDefaultVO implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String cpyrhtCode;
	private String chrgnAt;
	private String cpyrhtUsePermCode;
	private String cpyrhtSvcScopeCode;
	private String userGroupId;
	private String iconFileNm;
	private String iconMssage;


	// 셀렉트 박스 검색
	private String searchChrgnAt;
	private String searchCpyrhtUsePermCode;
	private String searchCpyrhtSvcScopeCode;
	private String searchUserGroupId;
	
	public ElmCupListVO()
	{
		cpyrhtCode = "";
		chrgnAt = "";
		cpyrhtUsePermCode = "";
		cpyrhtSvcScopeCode = "";
		userGroupId = "";
		iconFileNm = "";
		iconMssage = "";
		
		searchChrgnAt = "";
		searchCpyrhtUsePermCode = "";
		searchCpyrhtSvcScopeCode = "";
		searchUserGroupId = "";
		
	}


	public String getCpyrhtCode() {
		return cpyrhtCode;
	}


	public void setCpyrhtCode(String cpyrhtCode) {
		this.cpyrhtCode = cpyrhtCode;
	}


	public String getChrgnAt() {
		return chrgnAt;
	}


	public void setChrgnAt(String chrgnAt) {
		this.chrgnAt = chrgnAt;
	}


	public String getCpyrhtUsePermCode() {
		return cpyrhtUsePermCode;
	}


	public void setCpyrhtUsePermCode(String cpyrhtUsePermCode) {
		this.cpyrhtUsePermCode = cpyrhtUsePermCode;
	}


	public String getCpyrhtSvcScopeCode() {
		return cpyrhtSvcScopeCode;
	}


	public void setCpyrhtSvcScopeCode(String cpyrhtSvcScopeCode) {
		this.cpyrhtSvcScopeCode = cpyrhtSvcScopeCode;
	}


	public String getUserGroupId() {
		return userGroupId;
	}


	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}


	public String getIconFileNm() {
		return iconFileNm;
	}


	public void setIconFileNm(String iconFileNm) {
		this.iconFileNm = iconFileNm;
	}


	public String getIconMssage() {
		return iconMssage;
	}


	public void setIconMssage(String iconMssage) {
		this.iconMssage = iconMssage;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getSearchChrgnAt() {
		return searchChrgnAt;
	}


	public void setSearchChrgnAt(String searchChrgnAt) {
		this.searchChrgnAt = searchChrgnAt;
	}


	public String getSearchCpyrhtUsePermCode() {
		return searchCpyrhtUsePermCode;
	}


	public void setSearchCpyrhtUsePermCode(String searchCpyrhtUsePermCode) {
		this.searchCpyrhtUsePermCode = searchCpyrhtUsePermCode;
	}


	public String getSearchCpyrhtSvcScopeCode() {
		return searchCpyrhtSvcScopeCode;
	}


	public void setSearchCpyrhtSvcScopeCode(String searchCpyrhtSvcScopeCode) {
		this.searchCpyrhtSvcScopeCode = searchCpyrhtSvcScopeCode;
	}


	public String getSearchUserGroupId() {
		return searchUserGroupId;
	}


	public void setSearchUserGroupId(String searchUserGroupId) {
		this.searchUserGroupId = searchUserGroupId;
	}




	
}
