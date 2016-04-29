package clikmng.nanet.go.kr.mdm.model;

public class MdmOrgCodeVO {
	private String ORGCODE = "";
	private String ORGNM   = "";
	private String SITEID	= "";
	private String SITENM	= "";
	
	
	
	public String getSITEID() {
		return SITEID;
	}
	public void setSITEID(String sITEID) {
		SITEID = sITEID;
	}
	public String getSITENM() {
		return SITENM;
	}
	public void setSITENM(String sITENM) {
		SITENM = sITENM;
	}
	public String getORGCODE() {
		return ORGCODE;
	}
	public void setORGCODE(String oRGCODE) {
		ORGCODE = oRGCODE;
	}
	public String getORGNM() {
		return ORGNM;
	}
	public void setORGNM(String oRGNM) {
		ORGNM = oRGNM;
	}
}