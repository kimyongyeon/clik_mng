package clikmng.nanet.go.kr.elm.grp.service;

import java.io.Serializable;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;

public class ElmGrpListVO  extends ComDefaultVO implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String userClassId;
	private String userGroupNm;
	private String dtaSeCode;
	private String readngSeCode;
	
	public ElmGrpListVO()
	{
		userClassId = "";
		userGroupNm = "";
		dtaSeCode = "";
		readngSeCode = "";
	}

	public String getUserClassId() {
		return userClassId;
	}

	public void setUserClassId(String userClassId) {
		this.userClassId = userClassId;
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
