package clikmng.nanet.go.kr.sit.log.service;

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
public class LogManageVO extends LogManageDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 페이지 당 게시물 수 */
    private int selectCountPg;
    
    /** 로그인 아이디 */
    private String logId;
    
    /** 등록일자 */
    private String creatDt;
    
    /** 로그 타입 -  I : 로그인, O : 로그아웃 */
    private String conectMthd;
    
    /** IP */
    private String conectIp;
    
    /** 접속자 아이디 */
    private String conectId;
    
    /** 접속자 이름 */
    private String conectNm;

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;
    
    //-------------------------- 검색조건
    /** selectBOX */
    private String selSearchGubun;
    /** 이름 */
    private String selNm;
    /** 아이디 */
    private String selId;
    /** 타입 */
    private String selTy;

    
    /** 기간선택 - 시작 */
    private String selectBgdate;
    /** 기간선택 - 끝 */
    private String selectEndate;
    
    /** 기간선택 - 시작 */
    private String schDt1;
    /** 기간선택 - 끝 */
    private String schDt2;

    
    //--------------------------웹로그
    
    /** 로그 ID */
    private String requestId;    
    /** 발생일자 */
    private String occrrncDe;
    /** 발생날짜 */
    private String occrrncDeDate;
    /** 발생시간 */
    private String occrrncDeTime;
    /** 해당 URL */
    private String requestUrl;
    /** 해당 IP */
    private String requesterIp;
    /** 요청자 이름 */
    private String requesterId;
    /** 요청자 이름 */
    private String requesterNm;

    
    
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getOccrrncDe() {
		return occrrncDe;
	}
	public void setOccrrncDe(String occrrncDe) {
		this.occrrncDe = occrrncDe;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getRequesterIp() {
		return requesterIp;
	}
	public void setRequesterIp(String requesterIp) {
		this.requesterIp = requesterIp;
	}
	public String getRequesterId() {
		return requesterId;
	}
	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}
	public String getRequesterNm() {
		return requesterNm;
	}
	public void setRequesterNm(String requesterNm) {
		this.requesterNm = requesterNm;
	}
	public String getSelSearchGubun() {
		return selSearchGubun;
	}
	public void setSelSearchGubun(String selSearchGubun) {
		this.selSearchGubun = selSearchGubun;
	}
	public int getSelectCountPg() {
		return selectCountPg;
	}
	public void setSelectCountPg(int selectCountPg) {
		this.selectCountPg = selectCountPg;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getCreatDt() {
		return creatDt;
	}
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}
	public String getConectMthd() {
		return conectMthd;
	}
	public void setConectMthd(String conectMthd) {
		this.conectMthd = conectMthd;
	}
	public String getConectIp() {
		return conectIp;
	}
	public void setConectIp(String conectIp) {
		this.conectIp = conectIp;
	}
	public String getConectId() {
		return conectId;
	}
	public void setConectId(String conectId) {
		this.conectId = conectId;
	}
	public String getConectNm() {
		return conectNm;
	}
	public void setConectNm(String conectNm) {
		this.conectNm = conectNm;
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
	public String getSelNm() {
		return selNm;
	}
	public void setSelNm(String selNm) {
		this.selNm = selNm;
	}
	public String getSelId() {
		return selId;
	}
	public void setSelId(String selId) {
		this.selId = selId;
	}
	public String getSelTy() {
		return selTy;
	}
	public void setSelTy(String selTy) {
		this.selTy = selTy;
	}
	public String getSelectBgdate() {
		return selectBgdate;
	}
	public void setSelectBgdate(String selectBgdate) {
		this.selectBgdate = selectBgdate;
	}
	public String getSelectEndate() {
		return selectEndate;
	}
	public void setSelectEndate(String selectEndate) {
		this.selectEndate = selectEndate;
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
	public String getOccrrncDeDate() {
		return occrrncDeDate;
	}
	public void setOccrrncDeDate(String occrrncDeDate) {
		this.occrrncDeDate = occrrncDeDate;
	}
	public String getOccrrncDeTime() {
		return occrrncDeTime;
	}
	public void setOccrrncDeTime(String occrrncDeTime) {
		this.occrrncDeTime = occrrncDeTime;
	}

	
	
}
