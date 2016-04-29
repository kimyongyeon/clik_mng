package clikmng.nanet.go.kr.sit.rls.service;

/**
 * 
 * 사이트정보를 처리하는 VO 클래스
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
public class SiteManageVO extends SiteManageDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 사이트 ID */
    private String siteId;
    
    /** 사이트 URL */
    private String siteUrl;
    
    /** 사이트명 */
    private String siteNm;
    
    /** 사이트설명 */
    private String siteDc;
    
    /** 사이트주제분류코드 */
    private String siteThemaClCode;

    /** 사이트주제분류명 */
    private String siteThemaClNm;
    
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
    private String siteThemaClDetailCode;   
    
    /** 사이트주제분류상세명 */
    private String siteThemaClDetailNm;
    
    /** 사이트 순서 */
    private String seOrdr;
    
    /** 사이트 순서변경 타입 - UP, DOWN */
    private String type;
    
    /** 사이트 순서변경 변경번호*/
    private String siteOrdrChg;
    
    

	public String getSiteOrdrChg() {
		return siteOrdrChg;
	}

	public void setSiteOrdrChg(String siteOrdrChg) {
		this.siteOrdrChg = siteOrdrChg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeOrdr() {
		return seOrdr;
	}

	public void setSeOrdr(String seOrdr) {
		this.seOrdr = seOrdr;
	}

	/**
	 * siteId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSiteId() {
		return siteId;
	}

	/**
	 * siteId attribute 값을 설정한다.
	 * @return siteId String
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	/**
	 * siteUrl attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSiteUrl() {
		return siteUrl;
	}

	/**
	 * siteUrl attribute 값을 설정한다.
	 * @return siteUrl String
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	/**
	 * siteNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSiteNm() {
		return siteNm;
	}

	/**
	 * siteNm attribute 값을 설정한다.
	 * @return siteNm String
	 */
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}

	/**
	 * siteDc attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSiteDc() {
		return siteDc;
	}

	/**
	 * siteDc attribute 값을 설정한다.
	 * @return siteDc String
	 */
	public void setSiteDc(String siteDc) {
		this.siteDc = siteDc;
	}

	/**
	 * siteThemaClCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSiteThemaClCode() {
		return siteThemaClCode;
	}

	/**
	 * siteThemaClCode attribute 값을 설정한다.
	 * @return siteThemaClCode String
	 */
	public void setSiteThemaClCode(String siteThemaClCode) {
		this.siteThemaClCode = siteThemaClCode;
	}

	/**
	 * siteThemaClNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSiteThemaClNm() {
		return siteThemaClNm;
	}

	/**
	 * siteThemaClNm attribute 값을 설정한다.
	 * @return siteThemaClNm String
	 */
	public void setSiteThemaClNm(String siteThemaClNm) {
		this.siteThemaClNm = siteThemaClNm;
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

	public String getSiteThemaClDetailCode() {
		return siteThemaClDetailCode;
	}

	public void setSiteThemaClDetailCode(String siteThemaClDetailCode) {
		this.siteThemaClDetailCode = siteThemaClDetailCode;
	}

	public String getSiteThemaClDetailNm() {
		return siteThemaClDetailNm;
	}

	public void setSiteThemaClDetailNm(String siteThemaClDetailNm) {
		this.siteThemaClDetailNm = siteThemaClDetailNm;
	}

    
    
   
}
