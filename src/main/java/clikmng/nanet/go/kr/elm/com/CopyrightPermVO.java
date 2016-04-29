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
public class CopyrightPermVO extends ComDefaultVO implements Serializable 
{

	private String chrgnAt;
	private String userGroupId;
	private String userGroupId2;
	private String cpyrhtCode;
	private String oriCpyrhtCode;
	private String cpyrhtUsePermCode;
	private String cpyrhtSvcScopeCode;
	private String iconFileNm;
	private String iconMssage;
	private String frstRegisterId;
	private String frstRegistPnttm;
	private String lastUpdusrId;
	private String lastUpdtPnttm;

	public CopyrightPermVO()
	{
		chrgnAt					=	"";
		userGroupId				=	"";
		userGroupId2			=	"0";
		cpyrhtCode				=	"";
		oriCpyrhtCode			=	"";
		cpyrhtUsePermCode		=	"";
		cpyrhtSvcScopeCode		=	"";
		iconFileNm				=	"";
		iconMssage				=	"";
		frstRegisterId			=	"";
		frstRegistPnttm			=	"";
		lastUpdusrId			=	"";
		lastUpdtPnttm			=	"";

	}

	



	public String getOriCpyrhtCode() {
		return oriCpyrhtCode;
	}

	public void setOriCpyrhtCode(String oriCpyrhtCode) {
		this.oriCpyrhtCode = oriCpyrhtCode;
	}

	public String getChrgnAt() {
		return chrgnAt;
	}

	public void setChrgnAt(String chrgnAt) {
		this.chrgnAt = chrgnAt;
	}

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getCpyrhtCode() {
		return cpyrhtCode;
	}

	public void setCpyrhtCode(String cpyrhtCode) {
		this.cpyrhtCode = cpyrhtCode;
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
