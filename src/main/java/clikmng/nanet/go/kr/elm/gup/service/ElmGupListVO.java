package clikmng.nanet.go.kr.elm.gup.service;

import java.io.Serializable;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;

public class ElmGupListVO  extends ComDefaultVO implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String userGroupId;
	private String userGroupNm;
	private String dtaSeCode;
	private String readngSeCode;
	
	public ElmGupListVO()
	{
		userGroupId = "";
		userGroupNm = "";
		dtaSeCode = "";
		readngSeCode = "";
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

	public String getDtaSeCode() {
		return dtaSeCode;
	}

	public void setDtaSeCode(String dtaSeCode) {
		this.dtaSeCode = dtaSeCode;
	}

	public String getReadngSeCode() {
		return readngSeCode;
	}

	public void setReadngSeCode(String readngSeCode) {
		this.readngSeCode = readngSeCode;
	}
	
	
}
