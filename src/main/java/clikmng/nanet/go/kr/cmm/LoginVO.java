package clikmng.nanet.go.kr.cmm;

import java.io.Serializable;

/**
 * @Class Name : LoginVO.java
 * @Description : Login VO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 *
 *  @author 
 *  @since 
 *  @version 
 *  @see
 *  
 */
public class LoginVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8274004534207618049L;
	
	/** 아이디 */
	private String mngrId;
	/** 이름 */
	private String mngrNm;
	/** 패스워드 */
	private String mngrPw;	
	/** 이메일 */
	private String mngrEmail;
	/** 조직(부서)명 */
	private String mngrDept;
	/** 권한 */
	private String authorCode;
	/** 등록일 */
	private String frstRegistPnttm;
	/** 로그인 후 이동할 페이지 */
	private String url;
	/** 사용자 IP정보 */
	private String ip;
	/** 관리자구분코드 */
	private String mngrSeCode;


	
	
	public String getMngrEmail() {
		return mngrEmail;
	}
	public void setMngrEmail(String mngrEmail) {
		this.mngrEmail = mngrEmail;
	}
	public String getMngrPw() {
		return mngrPw;
	}
	public void setMngrPw(String mngrPw) {
		this.mngrPw = mngrPw;
	}	
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}
	/**
	 * url attribute 를 리턴한다.
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * url attribute 값을 설정한다.
	 * @param url String
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * ip attribute 를 리턴한다.
	 * @return String
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * ip attribute 값을 설정한다.
	 * @param ip String
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMngrId() {
		return mngrId;
	}
	public void setMngrId(String mngrId) {
		this.mngrId = mngrId;
	}
	public String getMngrNm() {
		return mngrNm;
	}
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}
	public String getMngrDept() {
		return mngrDept;
	}
	public void setMngrDept(String mngrDept) {
		this.mngrDept = mngrDept;
	}
	public String getRegDate() {
		return frstRegistPnttm;
	}
	public void setRegDate(String regDate) {
		this.frstRegistPnttm = regDate;
	}
	public String getAuthorCode() {
		return authorCode;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	public String getMngrSeCode() {
		return mngrSeCode;
	}
	public void setMngrSeCode(String mngrSeCode) {
		this.mngrSeCode = mngrSeCode;
	}
	
}
