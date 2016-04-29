package clikmng.nanet.go.kr.mdm.model;
/**
 * 
 * 사이트정보를 처리하는 VO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */
public class MdmManageVO extends MdmManageDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 사이트 ID */
    private String logId;
    
    /** 사이트 URL */
    private String logUrl;
    
    /** 사이트명 */
    private String logNm;
    
    /** 사이트설명 */
    private String logDc;
    
    /** 사이트주제분류코드 */
    private String logThemaClCode;

    /** 사이트주제분류명 */
    private String logThemaClNm;
    
    /** 활성여부 */
    private String actvtyAt;

    /** 활성여부명 */
    private String actvtyAtNm;
    
    /** 사용여부 */
    private String useAt;
    
    /** 사용여부명 */
    private String useAtNm;
    
    /** 등록자명 */
    private String emplyrNm;        

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;
    
    /** 코드 ID */
    private String codeId;
    
    /** 코드 ID 명 */
    private String codeIdNm;

    /** 분류 코드 */
    private String clCode;
    
    /** 상세 코드 */
    private String code;
    
    /** 상세코드 명 */
    private String codeNm;
    
    /** 코드 순서 */
    private String codeOrdr;    
    
    /** 사이트주제분류상세코드 */
    private String logThemaClDetailCode;   
    
    /** 사이트주제분류상세명 */
    private String logThemaClDetailNm;
    
    
    

	/**
	 * logId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getlogId() {
		return logId;
	}

	/**
	 * logId attribute 값을 설정한다.
	 * @return logId String
	 */
	public void setlogId(String logId) {
		this.logId = logId;
	}

	/**
	 * logUrl attribute 를 리턴한다.
	 * @return the String
	 */
	public String getlogUrl() {
		return logUrl;
	}

	/**
	 * logUrl attribute 값을 설정한다.
	 * @return logUrl String
	 */
	public void setlogUrl(String logUrl) {
		this.logUrl = logUrl;
	}

	/**
	 * logNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getlogNm() {
		return logNm;
	}

	/**
	 * logNm attribute 값을 설정한다.
	 * @return logNm String
	 */
	public void setlogNm(String logNm) {
		this.logNm = logNm;
	}

	/**
	 * logDc attribute 를 리턴한다.
	 * @return the String
	 */
	public String getlogDc() {
		return logDc;
	}

	/**
	 * logDc attribute 값을 설정한다.
	 * @return logDc String
	 */
	public void setlogDc(String logDc) {
		this.logDc = logDc;
	}

	/**
	 * logThemaClCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getlogThemaClCode() {
		return logThemaClCode;
	}

	/**
	 * logThemaClCode attribute 값을 설정한다.
	 * @return logThemaClCode String
	 */
	public void setlogThemaClCode(String logThemaClCode) {
		this.logThemaClCode = logThemaClCode;
	}

	/**
	 * logThemaClNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getlogThemaClNm() {
		return logThemaClNm;
	}

	/**
	 * logThemaClNm attribute 값을 설정한다.
	 * @return logThemaClNm String
	 */
	public void setlogThemaClNm(String logThemaClNm) {
		this.logThemaClNm = logThemaClNm;
	}

	/**
	 * actvtyAt attribute 를 리턴한다.
	 * @return the String
	 */
	public String getActvtyAt() {
		return actvtyAt;
	}

	/**
	 * actvtyAt attribute 값을 설정한다.
	 * @return actvtyAt String
	 */
	public void setActvtyAt(String actvtyAt) {
		this.actvtyAt = actvtyAt;
	}

	/**
	 * actvtyAtNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getActvtyAtNm() {
		return actvtyAtNm;
	}

	/**
	 * actvtyAtNm attribute 값을 설정한다.
	 * @return actvtyAtNm String
	 */
	public void setActvtyAtNm(String actvtyAtNm) {
		this.actvtyAtNm = actvtyAtNm;
	}

	/**
	 * useAt attribute 를 리턴한다.
	 * @return the String
	 */
	public String getUseAt() {
		return useAt;
	}

	/**
	 * useAt attribute 값을 설정한다.
	 * @return useAt String
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * useAtNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getUseAtNm() {
		return useAtNm;
	}

	/**
	 * useAtNm attribute 값을 설정한다.
	 * @return useAtNm String
	 */
	public void setUseAtNm(String useAtNm) {
		this.useAtNm = useAtNm;
	}

	/**
	 * emplyrNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEmplyrNm() {
		return emplyrNm;
	}

	/**
	 * emplyrNm attribute 값을 설정한다.
	 * @return emplyrNm String
	 */
	public void setEmplyrNm(String emplyrNm) {
		this.emplyrNm = emplyrNm;
	}

	/**
	 * frstRegisterPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * frstRegisterPnttm attribute 값을 설정한다.
	 * @return frstRegisterPnttm String
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @return frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * lastUpdusrPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrPnttm attribute 값을 설정한다.
	 * @return lastUpdusrPnttm String
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @return lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeIdNm() {
		return codeIdNm;
	}

	public void setCodeIdNm(String codeIdNm) {
		this.codeIdNm = codeIdNm;
	}

	public String getClCode() {
		return clCode;
	}

	public void setClCode(String clCode) {
		this.clCode = clCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getCodeOrdr() {
		return codeOrdr;
	}

	public void setCodeOrdr(String codeOrdr) {
		this.codeOrdr = codeOrdr;
	}

	public String getlogThemaClDetailCode() {
		return logThemaClDetailCode;
	}

	public void setlogThemaClDetailCode(String logThemaClDetailCode) {
		this.logThemaClDetailCode = logThemaClDetailCode;
	}

	public String getlogThemaClDetailNm() {
		return logThemaClDetailNm;
	}

	public void setlogThemaClDetailNm(String logThemaClDetailNm) {
		this.logThemaClDetailNm = logThemaClDetailNm;
	}

    
    
   
}
