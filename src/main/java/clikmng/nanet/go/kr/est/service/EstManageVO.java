package clikmng.nanet.go.kr.est.service;

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
public class EstManageVO extends EstManageDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** input radio name */
    private String tagName;

    /** input radio pdf name */
    private String tagPdfName;
    
    /** 수집정보유형코드 */
    private String colctInfoTyCode ;
    
    /** 수집정보유형코드명 */
    private String colctInfoTyCodeNm;
    
    /** 자동게시여부 */
    private String atmcNtceAt;
    
    /** 자동PDF변환여부 */
    private String atmcCnvrAt;
    
    /** 자동게시여부 사이즈 */
    private int ntctAtListSize;
    
    /** 자동PDF변환여부 사이즈 */
    private int cnvrAtListSize;
    
    /** 최초등록시점 */
    private String frstRegistPnttm;

	/** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;

    
    
	public String getTagPdfName() {
		return tagPdfName;
	}

	public void setTagPdfName(String tagPdfName) {
		this.tagPdfName = tagPdfName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getNtctAtListSize() {
		return ntctAtListSize;
	}

	public void setNtctAtListSize(int ntctAtListSize) {
		this.ntctAtListSize = ntctAtListSize;
	}

	public int getCnvrAtListSize() {
		return cnvrAtListSize;
	}

	public void setCnvrAtListSize(int cnvrAtListSize) {
		this.cnvrAtListSize = cnvrAtListSize;
	}

	public String getColctInfoTyCodeNm() {
		return colctInfoTyCodeNm;
	}

	public void setColctInfoTyCodeNm(String colctInfoTyCodeNm) {
		this.colctInfoTyCodeNm = colctInfoTyCodeNm;
	}

	public String getColctInfoTyCode() {
		return colctInfoTyCode;
	}

	public void setColctInfoTyCode(String colctInfoTyCode) {
		this.colctInfoTyCode = colctInfoTyCode;
	}

	public String getAtmcNtceAt() {
		return atmcNtceAt;
	}

	public void setAtmcNtceAt(String atmcNtceAt) {
		this.atmcNtceAt = atmcNtceAt;
	}

	public String getAtmcCnvrAt() {
		return atmcCnvrAt;
	}

	public void setAtmcCnvrAt(String atmcCnvrAt) {
		this.atmcCnvrAt = atmcCnvrAt;
	}

	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
    
}
