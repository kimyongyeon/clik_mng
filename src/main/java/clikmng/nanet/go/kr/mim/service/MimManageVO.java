package clikmng.nanet.go.kr.mim.service;


/**
 * 
 * 메일정보를 처리하는 VO 클래스
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
public class MimManageVO extends MimManageDefaultVO {
	
	/** ---------그룹설정 시작 ------------*/
    private static final long serialVersionUID = 1L;
    
    /** Process 구분 */
	private String cmd;
    
    /** 그룹아이디 */
    private String emailGroupId;
    
    /** 그룹 명 */
    private String groupNm;
    
    /** 그룹설명 */
    private String dc;
    
    /** 그룹 TEXTAREA */
    private String groupArea;
    
    /** 그룹 구성원 */
    private String groupDtlsInfo;
    
    /** ------그룹검색 ---------*/
    /** 페이지 당 게시물 수 */
    private int selectCountPg;
    
    /** 검색 - 그룹명 */
    private String selGroupNm;
    
    /** 검색 - 설명 */
    private String selDc;

    /** 검색 - 기간 */
    private String selectBgdate;
    private String selectEndate;

    /** 검색 - 기간 */
    private String schDt1;
    private String schDt2;
    
    
    /** ---------그룹설정 끝 ------------*/
    
    /** ---------팝업그룹멤버 선택 시작 ------------*/
    /** 팝업 - 멤버이름 */
    private String memNm;
    
    /** 팝업 - 멤버이메일 */
    private String memEmail;

    /** ---------팝업그룹멤버 선택 끝 ------------*/
    
    /** 지방의원 이름, 이메일 */
    private String asembyNm;
    
	private String email;
    
	/** 지방의회 담당자, 지자체 담당자 조회 */
	/** 지방의회, 지자체 구분 코드*/
    private String asembySeCode;
	
    /** 지방의회, 지자체 담당자 이름*/
    private String chargerNm;
    
    /** 지방의회, 지자체 담당자 Email*/
    private String chargerEmail;
    
    /** 최초등록시점 */
    private String frstRegistPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;
    
    /** 그룹 구성원 ID */
    private String emailGroupDtlsNo;
    
    /** 그룹 구성원 이름 */
    private String rcverNm;
    
    /** 그룹 구성원 이메일 */
    private String rcverEmail;
    
    /**---------------- 메일 발송 ------------------- */
    
    /** 첨부파일 ID */
    private String atchFileId;
    
    /** 메일발송 ID */
    private String emailSndngId;
    
    /** 수신자 ID */
    private String mailRcver;
    
    /** 발신자 ID */
    private String sendNm;
    
    /** 메일제목 */
    private String sj;

    /** 메일내용 */
    private String emailCn;
    
    /** 발신일시 */
    private String dsptchDt;
    
    /** 상세내용 Sequence ID */
    private int sn;
    
    /** 이메일 양식 폼 아이디*/
    private String emailFormId;
    
    /** 이메일 양식 폼 양식명 */
    private String emailFormNm;
    
    /** 이메일 양식 폼 HTML */
    private String emailFormHtml;
    
    /** 이메일 양식 선택 */
    private String selForm;
    
    /** 이메일 발송 성공 여부 */
    private String sendSuccesAt;
    
    /** 이메일 수신 거부 수 */
    private int sendRejectCnt;
    
    
    /**---------------- 메일 발송 ------------------- */
    
    /**---------------- 메일 발송 목록------------------- */
    
    
    /** 발송자수 */
    private String sendCnt;
    /** 검색조건 - 제목 */
    private String selSj;
    /** 검색조건 - 내용 */
    private String selCn;
    
    /**---------------- 메일 발송 목록------------------- */
    
    
    /**---------------- 메일 수신거부 목록------------------- */
    
    /** 검색조건 - 이름 */
    private String selNm;
    
    /** 검색조건 - 이메일 */
    private String selEmail;
    
    /** 거부ID */
    private String rejectId;
    
    /** 거부직업 */
    private String rejectRcverJob;
    
    /** 수신거부자 이름 */
    private String rejectRcverNm;
    
    /** 수신거부자 이메일 */
    private String rejectRcverEmail;
    
    /** 수신거부 등록일 */
    private String rejectRecptnPnttm;
    
    
    /**---------------- 메일 수신거부 목록------------------- */    
    
    
    
    
	public String getAtchFileId() {
		return atchFileId;
	}

	public String getRejectRcverJob() {
		return rejectRcverJob;
	}

	public void setRejectRcverJob(String rejectRcverJob) {
		this.rejectRcverJob = rejectRcverJob;
	}

	public String getRejectId() {
		return rejectId;
	}

	public void setRejectId(String rejectId) {
		this.rejectId = rejectId;
	}

	public String getRejectRcverNm() {
		return rejectRcverNm;
	}

	public void setRejectRcverNm(String rejectRcverNm) {
		this.rejectRcverNm = rejectRcverNm;
	}

	public String getRejectRcverEmail() {
		return rejectRcverEmail;
	}

	public void setRejectRcverEmail(String rejectRcverEmail) {
		this.rejectRcverEmail = rejectRcverEmail;
	}

	public String getRejectRecptnPnttm() {
		return rejectRecptnPnttm;
	}

	public void setRejectRecptnPnttm(String rejectRecptnPnttm) {
		this.rejectRecptnPnttm = rejectRecptnPnttm;
	}

	public String getSelNm() {
		return selNm;
	}

	public void setSelNm(String selNm) {
		this.selNm = selNm;
	}

	public String getSelEmail() {
		return selEmail;
	}

	public void setSelEmail(String selEmail) {
		this.selEmail = selEmail;
	}

	public String getSendSuccesAt() {
		return sendSuccesAt;
	}

	public void setSendSuccesAt(String sendSuccesAt) {
		this.sendSuccesAt = sendSuccesAt;
	}

	public int getSendRejectCnt() {
		return sendRejectCnt;
	}

	public void setSendRejectCnt(int sendRejectCnt) {
		this.sendRejectCnt = sendRejectCnt;
	}

	public String getSendCnt() {
		return sendCnt;
	}

	public void setSendCnt(String sendCnt) {
		this.sendCnt = sendCnt;
	}

	public String getSelSj() {
		return selSj;
	}

	public void setSelSj(String selSj) {
		this.selSj = selSj;
	}

	public String getSelCn() {
		return selCn;
	}

	public void setSelCn(String selCn) {
		this.selCn = selCn;
	}

	public String getSelForm() {
		return selForm;
	}

	public void setSelForm(String selForm) {
		this.selForm = selForm;
	}

	public String getEmailFormId() {
		return emailFormId;
	}

	public void setEmailFormId(String emailFormId) {
		this.emailFormId = emailFormId;
	}

	public String getEmailFormNm() {
		return emailFormNm;
	}

	public void setEmailFormNm(String emailFormNm) {
		this.emailFormNm = emailFormNm;
	}

	public String getEmailFormHtml() {
		return emailFormHtml;
	}

	public void setEmailFormHtml(String emailFormHtml) {
		this.emailFormHtml = emailFormHtml;
	}

	public String getMailRcver() {
		return mailRcver;
	}

	public void setMailRcver(String mailRcver) {
		this.mailRcver = mailRcver;
	}

	public String getEmailSndngId() {
		return emailSndngId;
	}

	public void setEmailSndngId(String emailSndngId) {
		this.emailSndngId = emailSndngId;
	}

	public String getSendNm() {
		return sendNm;
	}

	public void setSendNm(String sendNm) {
		this.sendNm = sendNm;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getEmailCn() {
		return emailCn;
	}

	public void setEmailCn(String emailCn) {
		this.emailCn = emailCn;
	}

	public String getDsptchDt() {
		return dsptchDt;
	}

	public void setDsptchDt(String dsptchDt) {
		this.dsptchDt = dsptchDt;
	}


	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getGroupDtlsInfo() {
		return groupDtlsInfo;
	}

	public void setGroupDtlsInfo(String groupDtlsInfo) {
		this.groupDtlsInfo = groupDtlsInfo;
	}

	public String getGroupArea() {
		return groupArea;
	}

	public void setGroupArea(String groupArea) {
		this.groupArea = groupArea;
	}

	public String getEmailGroupDtlsNo() {
		return emailGroupDtlsNo;
	}

	public void setEmailGroupDtlsNo(String emailGroupDtlsNo) {
		this.emailGroupDtlsNo = emailGroupDtlsNo;
	}

	public String getRcverNm() {
		return rcverNm;
	}

	public void setRcverNm(String rcverNm) {
		this.rcverNm = rcverNm;
	}

	public String getRcverEmail() {
		return rcverEmail;
	}

	public void setRcverEmail(String rcverEmail) {
		this.rcverEmail = rcverEmail;
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

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getChargerNm() {
		return chargerNm;
	}

	public void setChargerNm(String chargerNm) {
		this.chargerNm = chargerNm;
	}

	public String getChargerEmail() {
		return chargerEmail;
	}

	public void setChargerEmail(String chargerEmail) {
		this.chargerEmail = chargerEmail;
	}

	public String getAsembySeCode() {
		return asembySeCode;
	}

	public void setAsembySeCode(String asembySeCode) {
		this.asembySeCode = asembySeCode;
	}

	public String getAsembyNm() {
		return asembyNm;
	}

	public void setAsembyNm(String asembyNm) {
		this.asembyNm = asembyNm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
	public String getEmailGroupId() {
		return emailGroupId;
	}

	public void setEmailGroupId(String emailGroupId) {
		this.emailGroupId = emailGroupId;
	}

	public String getGroupNm() {
		return groupNm;
	}

	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public int getSelectCountPg() {
		return selectCountPg;
	}

	public void setSelectCountPg(int selectCountPg) {
		this.selectCountPg = selectCountPg;
	}

	public String getSelGroupNm() {
		return selGroupNm;
	}

	public void setSelGroupNm(String selGroupNm) {
		this.selGroupNm = selGroupNm;
	}

	public String getSelDc() {
		return selDc;
	}

	public void setSelDc(String selDc) {
		this.selDc = selDc;
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

	public String getMemNm() {
		return memNm;
	}

	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
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
 
	
	
    
}
