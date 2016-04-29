package clikmng.nanet.go.kr.csm.service;

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
public class CsmManageVO extends CsmManageDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 기관유형 ID */
    private String seedId;
    
    /** 기관유형명 */
    private String seedNm;
    
    /** 수집기관 ID */
    private String siteId;
    
    /** 수집기관명 */
    private String siteNm;
    
    /** 수집건수 */
    private int collectionCnt;

    /** 수집일자 */
    private String regDate;
    
    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;
    
    /** 검색 시작일 */
    private String ntceBgnde;
    
    /** 검색 종료일 */
    private String ntceEndde;

    /** 검색 시작일 */
    private String schDt1;
    
    /** 검색 종료일 */
    private String schDt2;

    /** 검색 조건(기관유형) */
    private String searchCondition1;
    
    /** 검색 조건(수집기관) */
    private String searchCondition2;
    
    
    //----------------------------------- 수집 대비 서비스 내역 -----------------------
    /** 카테고리 코드 */
    private String docType;
    
    /** 카테고리 코드명 */
    private String doctypeName;
    
    /** 카테고리 수집건수 */
    private int colCnt;
    
    /** 카테고리 서비스건수 */
    private int svcCnt;
    
  //----------------------------------- 수집 대비 서비스 내역  끝 -----------------------
    
    /** 정렬옵션 */
    private String sortOrder;
    
    

	public String getSearchCondition1() {
		return searchCondition1;
	}

	public int getColCnt() {
		return colCnt;
	}

	public void setColCnt(int colCnt) {
		this.colCnt = colCnt;
	}

	public int getSvcCnt() {
		return svcCnt;
	}

	public void setSvcCnt(int svcCnt) {
		this.svcCnt = svcCnt;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDoctypeName() {
		return doctypeName;
	}

	public void setDoctypeName(String doctypeName) {
		this.doctypeName = doctypeName;
	}

	public void setSearchCondition1(String searchCondition1) {
		this.searchCondition1 = searchCondition1;
	}

	public String getSearchCondition2() {
		return searchCondition2;
	}

	public void setSearchCondition2(String searchCondition2) {
		this.searchCondition2 = searchCondition2;
	}

	public String getNtceBgnde() {
		return ntceBgnde;
	}

	public void setNtceBgnde(String ntceBgnde) {
		this.ntceBgnde = ntceBgnde;
	}

	public String getNtceEndde() {
		return ntceEndde;
	}

	public void setNtceEndde(String ntceEndde) {
		this.ntceEndde = ntceEndde;
	}
	
	public String getSchDt1() {
		return schDt1;
	}

	public void setSchDt1(String schDt1) {
		this.schDt1 = schDt1;
	}

	public String getSchDt2() {
		return schDt2;
	}

	public void setSchDt2(String schDt2) {
		this.schDt2 = schDt2;
	}

	public String getSeedId() {
		return seedId;
	}

	public void setSeedId(String seedId) {
		this.seedId = seedId;
	}

	public String getSeedNm() {
		return seedNm;
	}

	public void setSeedNm(String seedNm) {
		this.seedNm = seedNm;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteNm() {
		return siteNm;
	}

	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}

	public int getCollectionCnt() {
		return collectionCnt;
	}

	public void setCollectionCnt(int collectionCnt) {
		this.collectionCnt = collectionCnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
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

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	
}
