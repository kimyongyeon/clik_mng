package clikmng.nanet.go.kr.elm.crp.service;

import java.io.Serializable;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;

public class ElmCrpListVO  extends ComDefaultVO implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String readngReqstSetupId;
	private String dtaSeCode;
	private String ddc;
	private String pl;
	private String sl;
	private String postn;
	private String cnType;
	private String readngSeCode;
	
	
	private String searchDtaSeCode;
	private String searchOpenOnline;
	private String searchOpenNight;
	private String searchOpenAssembly;
	
	
	public ElmCrpListVO()
	{
		readngReqstSetupId = "";
		dtaSeCode = "";
		ddc = "";
		pl = "";
		sl = "";
		postn = "";
		cnType = "";
		readngSeCode = "";
		
		searchDtaSeCode = "";
		searchOpenOnline = "";
		searchOpenNight = "";
		searchOpenAssembly = "";
		
	}

	public String getReadngReqstSetupId() {
		return readngReqstSetupId;
	}

	public void setReadngReqstSetupId(String readngReqstSetupId) {
		this.readngReqstSetupId = readngReqstSetupId;
	}

	public String getDtaSeCode() {
		return dtaSeCode;
	}

	public void setDtaSeCode(String dtaSeCode) {
		this.dtaSeCode = dtaSeCode;
	}

	public String getDdc() {
		return ddc;
	}

	public void setDdc(String ddc) {
		this.ddc = ddc;
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getPostn() {
		return postn;
	}

	public void setPostn(String postn) {
		this.postn = postn;
	}

	public String getCnType() {
		return cnType;
	}

	public void setCnType(String cnType) {
		this.cnType = cnType;
	}

	public String getReadngSeCode() {
		return readngSeCode;
	}

	public void setReadngSeCode(String readngSeCode) {
		this.readngSeCode = readngSeCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSearchDtaSeCode() {
		return searchDtaSeCode;
	}

	public void setSearchDtaSeCode(String searchDtaSeCode) {
		this.searchDtaSeCode = searchDtaSeCode;
	}

	public String getSearchOpenOnline() {
		return searchOpenOnline;
	}

	public void setSearchOpenOnline(String searchOpenOnline) {
		this.searchOpenOnline = searchOpenOnline;
	}

	public String getSearchOpenNight() {
		return searchOpenNight;
	}

	public void setSearchOpenNight(String searchOpenNight) {
		this.searchOpenNight = searchOpenNight;
	}

	public String getSearchOpenAssembly() {
		return searchOpenAssembly;
	}

	public void setSearchOpenAssembly(String searchOpenAssembly) {
		this.searchOpenAssembly = searchOpenAssembly;
	}




	
}
