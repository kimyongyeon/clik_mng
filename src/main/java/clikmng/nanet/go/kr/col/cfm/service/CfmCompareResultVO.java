package clikmng.nanet.go.kr.col.cfm.service;

import java.io.Serializable;


/**
 * 
 * 파일비교결과 VO 클래스
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
public class CfmCompareResultVO  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5485238566650736568L;

	
	private String compare_id;
	private String colct_year;
	private String api_code;
	private String api_nm;
	private String rasmbly_id;
	private String rasmbly_nm;
	
	private String brtcCode;
	private String loasmCode;
	private String insttClCode;
	private String insttClCodeNm;
	private String fInsttClCode;
	private String fInsttClCodeNm;
	
	/** 총파일개수 */
    private int totCnt;
    
    /** 정상파일개수 */
    private int nrmltCnt;
    
    /** 자료는있으나 파일이 없는경우 개수 */
    private int retryColCnt;
    
    /** 자료는 없는데 파일만 있는경우 개수 */
    private int delCnt;
    
    
	public int getTotCnt() {
		return totCnt;
	}

	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}

	public int getNrmltCnt() {
		return nrmltCnt;
	}

	public void setNrmltCnt(int nrmltCnt) {
		this.nrmltCnt = nrmltCnt;
	}

	public int getRetryColCnt() {
		return retryColCnt;
	}

	public void setRetryColCnt(int retryColCnt) {
		this.retryColCnt = retryColCnt;
	}

	public int getDelCnt() {
		return delCnt;
	}

	public void setDelCnt(int delCnt) {
		this.delCnt = delCnt;
	}

	public String getCompare_id() {
		return compare_id;
	}

	public void setCompare_id(String compare_id) {
		this.compare_id = compare_id;
	}

	public String getColct_year() {
		return colct_year;
	}

	public void setColct_year(String colct_year) {
		this.colct_year = colct_year;
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

	public String getBrtcCode() {
		return brtcCode;
	}

	public void setBrtcCode(String brtcCode) {
		this.brtcCode = brtcCode;
	}

	public String getLoasmCode() {
		return loasmCode;
	}

	public void setLoasmCode(String loasmCode) {
		this.loasmCode = loasmCode;
	}

	public String getInsttClCode() {
		return insttClCode;
	}

	public void setInsttClCode(String insttClCode) {
		this.insttClCode = insttClCode;
	}

	public String getInsttClCodeNm() {
		return insttClCodeNm;
	}

	public void setInsttClCodeNm(String insttClCodeNm) {
		this.insttClCodeNm = insttClCodeNm;
	}

	public String getfInsttClCode() {
		return fInsttClCode;
	}

	public void setfInsttClCode(String fInsttClCode) {
		this.fInsttClCode = fInsttClCode;
	}

	public String getfInsttClCodeNm() {
		return fInsttClCodeNm;
	}

	public void setfInsttClCodeNm(String fInsttClCodeNm) {
		this.fInsttClCodeNm = fInsttClCodeNm;
	}
    
    
	
	
}
