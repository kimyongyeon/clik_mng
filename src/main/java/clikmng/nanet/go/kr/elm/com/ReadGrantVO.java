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
public class ReadGrantVO extends ComDefaultVO implements Serializable 
{

	private String readngReqstSetupId;
	private String readngSeCode;
	private String readngAt;

	public ReadGrantVO()
	{
		readngReqstSetupId		=	"";
		readngSeCode				=	"";
		readngAt						=	"";
	}

	public String getReadngReqstSetupId() {
		return readngReqstSetupId;
	}

	public void setReadngReqstSetupId(String readngReqstSetupId) {
		this.readngReqstSetupId = readngReqstSetupId;
	}

	public String getReadngSeCode() {
		return readngSeCode;
	}

	public void setReadngSeCode(String readngSeCode) {
		this.readngSeCode = readngSeCode;
	}

	public String getReadngAt() {
		return readngAt;
	}

	public void setReadngAt(String readngAt) {
		this.readngAt = readngAt;
	}
	
	
	
	
	
}
