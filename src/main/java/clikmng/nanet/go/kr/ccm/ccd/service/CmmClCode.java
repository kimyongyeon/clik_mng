package clikmng.nanet.go.kr.ccm.ccd.service;

import java.io.Serializable;

/**
 * 공통분류코드 모델 클래스
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
 *
 * </pre>
 */
public class  CmmClCode implements Serializable {
	
	/*
	 * 분류코드
	 */
	private String clCode = "";
	
	/*
	 * 분류코드명
	 */
    private String clCodeNm = "";
    
    /*
     * 분류코드설명
     */
    private String clCodeDc = "";
    
    /*
     * 사용여부
     */
    private String useAt = "";
    
    /*
     * 최초등록자ID
     */
    private String frstRegisterId = "";
    
    /*
     * 최종수정자ID
     */
    private String lastUpdusrId   = "";

	/**
	 * clCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getClCode() {
		return clCode;
	}

	/**
	 * clCode attribute 값을 설정한다.
	 * @param clCode String
	 */
	public void setClCode(String clCode) {
		this.clCode = clCode;
	}

	/**
	 * clCodeNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getClCodeNm() {
		return clCodeNm;
	}

	/**
	 * clCodeNm attribute 값을 설정한다.
	 * @param clCodeNm String
	 */
	public void setClCodeNm(String clCodeNm) {
		this.clCodeNm = clCodeNm;
	}

	/**
	 * clCodeDc attribute 를 리턴한다.
	 * @return String
	 */
	public String getClCodeDc() {
		return clCodeDc;
	}

	/**
	 * clCodeDc attribute 값을 설정한다.
	 * @param clCodeDc String
	 */
	public void setClCodeDc(String clCodeDc) {
		this.clCodeDc = clCodeDc;
	}

	/**
	 * useAt attribute 를 리턴한다.
	 * @return String
	 */
	public String getUseAt() {
		return useAt;
	}

	/**
	 * useAt attribute 값을 설정한다.
	 * @param useAt String
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @param frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @param lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}


}
