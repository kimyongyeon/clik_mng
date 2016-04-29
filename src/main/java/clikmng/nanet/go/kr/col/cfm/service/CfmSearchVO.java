package clikmng.nanet.go.kr.col.cfm.service;

import java.io.Serializable;

/**
 * 표준연계API 파일동기화 조회 VO
 * */
public class CfmSearchVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 수집파일비교ID */
	private String compare_id;

	/** 관리자ID */
	private String mngrID;

	/** 수집년도 */
    private String colct_year;
    
    /** 지방의회ID */
    private String rasmbly_id;

    /** 지방의회명 */
    private String rasmbly_nm;

    /** 자료유형코드 */
    private String api_code;

    /** 자료유형명 */
    private String api_nm;

    /** 수집파일비교실행여부 */
    private boolean compareYn = false;

	public String getCompare_id() {
		return compare_id;
	}

	public void setCompare_id(String compare_id) {
		this.compare_id = compare_id;
	}

	public String getMngrID() {
		return mngrID;
	}

	public void setMngrID(String mngrID) {
		this.mngrID = mngrID;
	}

	public String getColct_year() {
		return colct_year;
	}

	public void setColct_year(String colct_year) {
		this.colct_year = colct_year;
	}

	public String getRasmbly_id() {
		return rasmbly_id;
	}

	public void setRasmbly_id(String rasmbly_id) {
		this.rasmbly_id = rasmbly_id;
	}

	public String getRasmbly_nm() {
		return rasmbly_nm;
	}

	public void setRasmbly_nm(String rasmbly_nm) {
		this.rasmbly_nm = rasmbly_nm;
	}

	public String getApi_code() {
		return api_code;
	}

	public void setApi_code(String api_code) {
		this.api_code = api_code;
	}

	public String getApi_nm() {
		return api_nm;
	}

	public void setApi_nm(String api_nm) {
		this.api_nm = api_nm;
	}

	public boolean isCompareYn() {
		return compareYn;
	}

	public void setCompareYn(boolean compareYn) {
		this.compareYn = compareYn;
	}
  




}
