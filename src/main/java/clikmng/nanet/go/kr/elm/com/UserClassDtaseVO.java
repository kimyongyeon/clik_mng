package clikmng.nanet.go.kr.elm.com;

import java.io.Serializable;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;

/**
 * 공통코드 모델 클래스
 * @author 
 * @since 
 * @version 
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   
 *
 * </pre>
 */
public class UserClassDtaseVO extends ComDefaultVO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	
	private String userGroupId;
	private String prposCode;
	private String processMssage;
	private String dtaSeCode;
	private String frstRegisterId;
	private String frstRegistPnttm;
	private String lastUpdusrId;
	private String lastUpdtPnttm;


	public UserClassDtaseVO()
	{

		userGroupId				=	"";
		prposCode				=	"";
		processMssage			=	"";
		dtaSeCode				=	"";
		frstRegisterId			=	"";
		frstRegistPnttm			=	"";
		lastUpdusrId			=	"";
		lastUpdtPnttm			=	"";

	}


	public String getUserGroupId() {
		return userGroupId;
	}


	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}


	public String getPrposCode() {
		return prposCode;
	}


	public void setPrposCode(String prposCode) {
		this.prposCode = prposCode;
	}


	public String getProcessMssage() {
		return processMssage;
	}


	public void setProcessMssage(String processMssage) {
		this.processMssage = processMssage;
	}


	public String getDtaSeCode() {
		return dtaSeCode;
	}


	public void setDtaSeCode(String dtaSeCode) {
		this.dtaSeCode = dtaSeCode;
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
