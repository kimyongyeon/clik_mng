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
public class ReadGrantRequestVO extends ComDefaultVO implements Serializable 
{


	private String userGroupId;
	private String readngSeCode;
	private String frstRegisterId;
	private String frstRegistPnttm;
	private String lastUpdusrId;
	private String lastUpdtPnttm;


	public ReadGrantRequestVO()
	{

		userGroupId 			=	"";
		readngSeCode 			=	"";
		frstRegisterId 			=	"";
		frstRegistPnttm 		=	"";
		lastUpdusrId 			=	"";
		lastUpdtPnttm 			=	"";

	}


	public String getUserGroupId() {
		return userGroupId;
	}


	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}


	public String getReadngSeCode() {
		return readngSeCode;
	}


	public void setReadngSeCode(String readngSeCode) {
		this.readngSeCode = readngSeCode;
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
