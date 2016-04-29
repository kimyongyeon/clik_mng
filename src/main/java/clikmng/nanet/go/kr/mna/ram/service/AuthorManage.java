package clikmng.nanet.go.kr.mna.ram.service;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;

/**
 * 권한관리에 대한 model 클래스를 정의한다.
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */

public class AuthorManage extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 권한관리
	 */	
	private AuthorManage authorManage;
	/**
	 * 권한사용자코드
	 */
	private String authorClCode;	
	/**
	 * 권한코드
	 */
	private String authorCode;
	/**
	 * 권한등록일자
	 */
	private String authorCreatDe;
	/**
	 * 권한코드설명
	 */
	private String authorDc;
	/**
	 * 권한 명
	 */
	private String authorNm;
	
	/**
	 * authorManage attribute 를 리턴한다.
	 * @return AuthorManage
	 */
	public AuthorManage getAuthorManage() {
		return authorManage;
	}
	/**
	 * authorManage attribute 값을 설정한다.
	 * @param authorManage AuthorManage 
	 */
	public void setAuthorManage(AuthorManage authorManage) {
		this.authorManage = authorManage;
	}
	/**
	 * authorCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getAuthorCode() {
		return authorCode;
	}
	/**
	 * authorCode attribute 값을 설정한다.
	 * @param authorCode String 
	 */
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	/**
	 * authorCreatDe attribute 를 리턴한다.
	 * @return String
	 */
	public String getAuthorCreatDe() {
		return authorCreatDe;
	}
	/**
	 * authorCreatDe attribute 값을 설정한다.
	 * @param authorCreatDe String 
	 */
	public void setAuthorCreatDe(String authorCreatDe) {
		this.authorCreatDe = authorCreatDe;
	}
	/**
	 * authorDc attribute 를 리턴한다.
	 * @return String
	 */
	public String getAuthorDc() {
		return authorDc;
	}
	/**
	 * authorDc attribute 값을 설정한다.
	 * @param authorDc String 
	 */
	public void setAuthorDc(String authorDc) {
		this.authorDc = authorDc;
	}
	/**
	 * authorNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getAuthorNm() {
		return authorNm;
	}
	/**
	 * authorNm attribute 값을 설정한다.
	 * @param authorNm String 
	 */
	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}
	public String getAuthorClCode() {
		return authorClCode;
	}
	public void setAuthorClCode(String authorClCode) {
		this.authorClCode = authorClCode;
	}
	


	

}
