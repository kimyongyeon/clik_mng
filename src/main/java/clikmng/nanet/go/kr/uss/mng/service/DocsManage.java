package clikmng.nanet.go.kr.uss.mng.service;

import java.io.Serializable;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;

/**
 * 개요
 * - Docs 서버를 통한 데이터 조회
 * 
 * 상세내용
 * - 아이디, 이름 등을 관리한다.
 */
public class DocsManage extends ComDefaultVO implements Serializable {
 
	/** 직원 아이디	 */
	private String userId;

	/** 직원 이름	 */	
	private String name;
	
	/** 직원 이메일	 */	
	private String email;
	
	/** 직원 구분코드	 */	
	private String seCode;
	
	/** 직원 부서명	 */	
	private String deptNm;

	/** 직원 부서코드	 */	
	private String deptCd;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSeCode() {
		return seCode;
	}

	public void setSeCode(String seCode) {
		this.seCode = seCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	
}