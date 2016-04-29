package clikmng.nanet.go.kr.cmm.service;

import java.io.Serializable;

/**
 * 공통상세코드 모델 클래스
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
public class CmmnDetailCode implements Serializable {
	
	/*
	 * 코드ID
	 */
    private String codeId = "";
    
    /*
     * 코드ID명
     */
    private String codeIdNm = "";
    
    /*
     * 코드
     */
	private String code = "";
	
	/*
	 * 코드명
	 */
    private String codeNm = "";
    
    /*
     * 코드설명
     */
    private String codeDc = "";
    
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
    
	/*
	 * 코드순번
	 */   
    private int codeOrdr;

	/*
	 * 코드 코드 변경
	 */   
    private String codeOrdrChg;
    
    /*
	 * 지방정부 및 의회 코드
	 */    
    private String loasmCode;

    /*
	 * 지방정부 및 의회 코드명
	 */    
    private String loasmNm;

    /*
	 * 기관유형 코드
	 */    
    private String insttTyCode;
    
    /*
	 * 광역시도코드
	 */    
    private String brtcCode;
    
    /*
	 * 시도구군코드
	 */    
    private String ctprvngugunCode;
    
    /*
	 * 기관분류코드
	 */    
    private String insttClCode;
    
    /** 기관분류레벨 */
    private String insttLevel;
    
    /** 기관분류명 */
    private String insttClCodeNm;
    
    /** 상위기관분류명 */
    private String upperInsttClCode;
    
    /** 정렬순서 */
    private int insttOrdr;
    
    /*
	 * 기관분류코드
	 */    
    private String fInsttClCode;
    
    /** 기관분류명 */
    private String fInsttClCodeNm;
    
    
    

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

	public String getInsttClCodeNm() {
		return insttClCodeNm;
	}

	public void setInsttClCodeNm(String insttClCodeNm) {
		this.insttClCodeNm = insttClCodeNm;
	}

	public String getInsttLevel() {
		return insttLevel;
	}

	public void setInsttLevel(String insttLevel) {
		this.insttLevel = insttLevel;
	}

	public String getUpperInsttClCode() {
		return upperInsttClCode;
	}

	public void setUpperInsttClCode(String upperInsttClCode) {
		this.upperInsttClCode = upperInsttClCode;
	}

	public int getInsttOrdr() {
		return insttOrdr;
	}

	public void setInsttOrdr(int insttOrdr) {
		this.insttOrdr = insttOrdr;
	}

	public String getLoasmCode() {
		return loasmCode;
	}

	public void setLoasmCode(String loasmCode) {
		this.loasmCode = loasmCode;
	}

	public String getLoasmNm() {
		return loasmNm;
	}

	public void setLoasmNm(String loasmNm) {
		this.loasmNm = loasmNm;
	}

	public String getInsttTyCode() {
		return insttTyCode;
	}

	public void setInsttTyCode(String insttTyCode) {
		this.insttTyCode = insttTyCode;
	}

	public String getBrtcCode() {
		return brtcCode;
	}

	public void setBrtcCode(String brtcCode) {
		this.brtcCode = brtcCode;
	}

	public String getCtprvngugunCode() {
		return ctprvngugunCode;
	}

	public void setCtprvngugunCode(String ctprvngugunCode) {
		this.ctprvngugunCode = ctprvngugunCode;
	}

	public String getInsttClCode() {
		return insttClCode;
	}

	public void setInsttClCode(String insttClCode) {
		this.insttClCode = insttClCode;
	}

	/**
	 * codeId attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeId() {
		return codeId;
	}

	/**
	 * codeId attribute 값을 설정한다.
	 * @param codeId String
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	/**
	 * codeIdNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeIdNm() {
		return codeIdNm;
	}

	/**
	 * codeIdNm attribute 값을 설정한다.
	 * @param codeIdNm String
	 */
	public void setCodeIdNm(String codeIdNm) {
		this.codeIdNm = codeIdNm;
	}

	/**
	 * code attribute 를 리턴한다.
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * code attribute 값을 설정한다.
	 * @param code String
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * codeNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeNm() {
		return codeNm;
	}

	/**
	 * codeNm attribute 값을 설정한다.
	 * @param codeNm String
	 */
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	/**
	 * codeDc attribute 를 리턴한다.
	 * @return String
	 */
	public String getCodeDc() {
		return codeDc;
	}

	/**
	 * codeDc attribute 값을 설정한다.
	 * @param codeDc String
	 */
	public void setCodeDc(String codeDc) {
		this.codeDc = codeDc;
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

	public int getCodeOrdr() {
		return codeOrdr;
	}

	public void setCodeOrdr(int codeOrdr) {
		this.codeOrdr = codeOrdr;
	}

	public String getCodeOrdrChg() {
		return codeOrdrChg;
	}

	public void setCodeOrdrChg(String codeOrdrChg) {
		this.codeOrdrChg = codeOrdrChg;
	}


}
