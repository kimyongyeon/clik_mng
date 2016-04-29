package clikmng.nanet.go.kr.sts.stm.service;

import java.io.Serializable;

/**
 * 
 * 통계정보관리 VO
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
@SuppressWarnings("serial")
public class StmInfoMngVO implements Serializable {
	
	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    /*
     * 검색
     * */
    
    private String INSTT_CL_CODE = "";
    private String INSTT_TY_CODE = "";
    private String LOASM_TY_CODE = "";
    private String BRTC_CODE = "";
    private String LOASM_CODE = "";
    
    /*
     * 결과
     * */
    private String TOTCNT = "";
    private String RNUM = "";
    
    private String LOASM_NM = "";
    private String RASMBLY_NUMPR = "";
    private String HRSMNPD_SN = "";
    private String HT_SE_STDCD = "";
    private String HT_SE_STDCD_NM = "";
    private String BEGIN_DE = "";
    private String END_DE = "";
    private String STATS_INFO = "";
    
    private String CMIT_CO;
    private String ASEMBY_CO;
    private String MINTS_CO;
    private String RCEPTER_BI_CO;
    
	/**
	 * searchCondition attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchCondition() {
		return searchCondition;
	}

	/**
	 * searchCondition attribute 값을 설정한다.
	 * @return searchCondition String
	 */
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * searchKeyword attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}

	/**
	 * searchKeyword attribute 값을 설정한다.
	 * @return searchKeyword String
	 */
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	/**
	 * searchUseYn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}

	/**
	 * searchUseYn attribute 값을 설정한다.
	 * @return searchUseYn String
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	/**
	 * pageIndex attribute 를 리턴한다.
	 * @return the int
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * pageIndex attribute 값을 설정한다.
	 * @return pageIndex int
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * pageUnit attribute 를 리턴한다.
	 * @return the int
	 */
	public int getPageUnit() {
		return pageUnit;
	}

	/**
	 * pageUnit attribute 값을 설정한다.
	 * @return pageUnit int
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	/**
	 * pageSize attribute 를 리턴한다.
	 * @return the int
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * pageSize attribute 값을 설정한다.
	 * @return pageSize int
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * firstIndex attribute 를 리턴한다.
	 * @return the int
	 */
	public int getFirstIndex() {
		return firstIndex;
	}

	/**
	 * firstIndex attribute 값을 설정한다.
	 * @return firstIndex int
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * lastIndex attribute 를 리턴한다.
	 * @return the int
	 */
	public int getLastIndex() {
		return lastIndex;
	}

	/**
	 * lastIndex attribute 값을 설정한다.
	 * @return lastIndex int
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * recordCountPerPage attribute 를 리턴한다.
	 * @return the int
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	/**
	 * recordCountPerPage attribute 값을 설정한다.
	 * @return recordCountPerPage int
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getINSTT_CL_CODE() {
		return INSTT_CL_CODE;
	}

	public void setINSTT_CL_CODE(String iNSTT_CL_CODE) {
		INSTT_CL_CODE = iNSTT_CL_CODE;
	}

	public String getLOASM_TY_CODE() {
		return LOASM_TY_CODE;
	}

	public void setLOASM_TY_CODE(String lOASM_TY_CODE) {
		LOASM_TY_CODE = lOASM_TY_CODE;
	}

	public String getBRTC_CODE() {
		return BRTC_CODE;
	}

	public void setBRTC_CODE(String bRTC_CODE) {
		BRTC_CODE = bRTC_CODE;
	}

	public String getLOASM_CODE() {
		return LOASM_CODE;
	}

	public void setLOASM_CODE(String lOASM_CODE) {
		LOASM_CODE = lOASM_CODE;
	}

	public String getTOTCNT() {
		return TOTCNT;
	}

	public void setTOTCNT(String tOTCNT) {
		TOTCNT = tOTCNT;
	}

	public String getRNUM() {
		return RNUM;
	}

	public void setRNUM(String rNUM) {
		RNUM = rNUM;
	}

	public String getLOASM_NM() {
		return LOASM_NM;
	}

	public void setLOASM_NM(String lOASM_NM) {
		LOASM_NM = lOASM_NM;
	}

	public String getRASMBLY_NUMPR() {
		return RASMBLY_NUMPR;
	}

	public void setRASMBLY_NUMPR(String rASMBLY_NUMPR) {
		RASMBLY_NUMPR = rASMBLY_NUMPR;
	}

	public String getHT_SE_STDCD_NM() {
		return HT_SE_STDCD_NM;
	}

	public void setHT_SE_STDCD_NM(String hT_SE_STDCD_NM) {
		HT_SE_STDCD_NM = hT_SE_STDCD_NM;
	}

	public String getBEGIN_DE() {
		return BEGIN_DE;
	}

	public void setBEGIN_DE(String bEGIN_DE) {
		BEGIN_DE = bEGIN_DE;
	}

	public String getEND_DE() {
		return END_DE;
	}

	public void setEND_DE(String eND_DE) {
		END_DE = eND_DE;
	}

	public String getSTATS_INFO() {
		return STATS_INFO;
	}

	public void setSTATS_INFO(String sTATS_INFO) {
		STATS_INFO = sTATS_INFO;
	}

	public String getHRSMNPD_SN() {
		return HRSMNPD_SN;
	}

	public void setHRSMNPD_SN(String hRSMNPD_SN) {
		HRSMNPD_SN = hRSMNPD_SN;
	}

	public String getHT_SE_STDCD() {
		return HT_SE_STDCD;
	}

	public void setHT_SE_STDCD(String hT_SE_STDCD) {
		HT_SE_STDCD = hT_SE_STDCD;
	}

	public String getINSTT_TY_CODE() {
		return INSTT_TY_CODE;
	}

	public void setINSTT_TY_CODE(String iNSTT_TY_CODE) {
		INSTT_TY_CODE = iNSTT_TY_CODE;
	}

	public String getCMIT_CO() {
		return CMIT_CO;
	}

	public void setCMIT_CO(String cMIT_CO) {
		CMIT_CO = cMIT_CO;
	}

	public String getASEMBY_CO() {
		return ASEMBY_CO;
	}

	public void setASEMBY_CO(String aSEMBY_CO) {
		ASEMBY_CO = aSEMBY_CO;
	}

	public String getMINTS_CO() {
		return MINTS_CO;
	}

	public void setMINTS_CO(String mINTS_CO) {
		MINTS_CO = mINTS_CO;
	}

	public String getRCEPTER_BI_CO() {
		return RCEPTER_BI_CO;
	}

	public void setRCEPTER_BI_CO(String rCEPTER_BI_CO) {
		RCEPTER_BI_CO = rCEPTER_BI_CO;
	}
    
}
