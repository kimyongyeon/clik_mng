package clikmng.nanet.go.kr.cop.err.service;

/**
 * 
 * 오류신고를 처리하는 VO 클래스
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
public class ErrManageVO extends ErrManageDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 오류신고 ID */
    private String errorReportId;
    
    /** 질문제목 */
    private String reportSj;    
    
    /** 질문내용 */
    private String reportCn;
    
    /** 작성비밀번호 */
    private String writngPassword;

    /** 전화번호 */
    private String telno;
    
    /** 중간전화번호 */     
    private String middleTelno;
    
    /** 끝전화번호 */
    private String endTelno;
        
    /** 이메일 주소 */
    private String emailAdres;
        
    /** 이메일 답변여부 */
    private String emailAnswerAt;

    /** 작성자 명 */
    private String wrterNm;
    
    /** 작성일자 */
    private String writngDe;
    
    /** 조회횟수 */
    private String inqireCo;
        
    /** 질의응답처리상태코드 */
    private String reportProcessSttusCode;

    /** 질의응답처리상태코드명 */
    private String reportProcessSttusNm;
    
    /** 답변내용 */
    private String answerCn;
    
    /** 답변일자 */
    private String answerDe;

    /** 작성비밀번호 확인여부 */
    private String passwordConfirmAt;
    
    /** 답변자명 */
    private String emplyrNm;
    
    /** 사무실전화번호 */
    private String offmTelno;

    /** 답변자 EMAIL 주소 */
    private String aemailAdres;
    
    /** 부서명 */
    private String orgnztNm;
        
    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;


	/**
	 * writngPassword attribute 를 리턴한다.
	 * @return the String
	 */
	public String getWritngPassword() {
		return writngPassword;
	}

	/**
	 * writngPassword attribute 값을 설정한다.
	 * @return writngPassword String
	 */
	public void setWritngPassword(String writngPassword) {
		this.writngPassword = writngPassword;
	}

	/**
	 * telno attribute 를 리턴한다.
	 * @return the String
	 */
	public String getTelno() {
		return telno;
	}

	/**
	 * areaNo attribute 값을 설정한다.
	 * @return areaNo String
	 */
	public void setTelno(String telno) {
		this.telno = telno;
	}

	/**
	 * middleTelno attribute 를 리턴한다.
	 * @return the String
	 */
	public String getMiddleTelno() {
		return middleTelno;
	}

	/**
	 * middleTelno attribute 값을 설정한다.
	 * @return middleTelno String
	 */
	public void setMiddleTelno(String middleTelno) {
		this.middleTelno = middleTelno;
	}

	/**
	 * endTelno attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEndTelno() {
		return endTelno;
	}

	/**
	 * endTelno attribute 값을 설정한다.
	 * @return endTelno String
	 */
	public void setEndTelno(String endTelno) {
		this.endTelno = endTelno;
	}

	/**
	 * emailAdres attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEmailAdres() {
		return emailAdres;
	}

	/**
	 * emailAdres attribute 값을 설정한다.
	 * @return emailAdres String
	 */
	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	/**
	 * emailAnswerAt attribute 를 리턴한다.
	 * @return the String
	 */
	public String getEmailAnswerAt() {
		return emailAnswerAt;
	}

	/**
	 * emailAnswerAt attribute 값을 설정한다.
	 * @return emailAnswerAt String
	 */
	public void setEmailAnswerAt(String emailAnswerAt) {
		this.emailAnswerAt = emailAnswerAt;
	}

	/**
	 * wrterNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getWrterNm() {
		return wrterNm;
	}

	/**
	 * wrterNm attribute 값을 설정한다.
	 * @return wrterNm String
	 */
	public void setWrterNm(String wrterNm) {
		this.wrterNm = wrterNm;
	}

	/**
	 * writngDe attribute 를 리턴한다.
	 * @return the String
	 */
	public String getWritngDe() {
		return writngDe;
	}

	/**
	 * writngDe attribute 값을 설정한다.
	 * @return writngDe String
	 */
	public void setWritngDe(String writngDe) {
		this.writngDe = writngDe;
	}

	/**
	 * inqireCo attribute 를 리턴한다.
	 * @return the String
	 */
	public String getInqireCo() {
		return inqireCo;
	}

	/**
	 * inqireCo attribute 값을 설정한다.
	 * @return inqireCo String
	 */
	public void setInqireCo(String inqireCo) {
		this.inqireCo = inqireCo;
	}

	/**
	 * answerCn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getAnswerCn() {
		return answerCn;
	}

	/**
	 * answerCn attribute 값을 설정한다.
	 * @return answerCn String
	 */
	public void setAnswerCn(String answerCn) {
		this.answerCn = answerCn;
	}

	/**
	 * answerDe attribute 를 리턴한다.
	 * @return the String
	 */
	public String getAnswerDe() {
		return answerDe;
	}

	/**
	 * answerDe attribute 값을 설정한다.
	 * @return answerDe String
	 */
	public void setAnswerDe(String answerDe) {
		this.answerDe = answerDe;
	}

	/**
	 * passwordConfirmAt attribute 를 리턴한다.
	 * @return the String
	 */
	public String getPasswordConfirmAt() {
		return passwordConfirmAt;
	}

	/**
	 * passwordConfirmAt attribute 값을 설정한다.
	 * @return passwordConfirmAt String
	 */
	public void setPasswordConfirmAt(String passwordConfirmAt) {
		this.passwordConfirmAt = passwordConfirmAt;
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
	 * offmTelno attribute 를 리턴한다.
	 * @return the String
	 */
	public String getOffmTelno() {
		return offmTelno;
	}

	/**
	 * offmTelno attribute 값을 설정한다.
	 * @return offmTelno String
	 */
	public void setOffmTelno(String offmTelno) {
		this.offmTelno = offmTelno;
	}

	/**
	 * aemailAdres attribute 를 리턴한다.
	 * @return the String
	 */
	public String getAemailAdres() {
		return aemailAdres;
	}

	/**
	 * aemailAdres attribute 값을 설정한다.
	 * @return aemailAdres String
	 */
	public void setAemailAdres(String aemailAdres) {
		this.aemailAdres = aemailAdres;
	}

	/**
	 * orgnztNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}

	/**
	 * orgnztNm attribute 값을 설정한다.
	 * @return orgnztNm String
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
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

	public String getErrorReportId() {
		return errorReportId;
	}

	public void setErrorReportId(String errorReportId) {
		this.errorReportId = errorReportId;
	}

	public String getReportSj() {
		return reportSj;
	}

	public void setReportSj(String reportSj) {
		this.reportSj = reportSj;
	}

	public String getReportCn() {
		return reportCn;
	}

	public void setReportCn(String reportCn) {
		this.reportCn = reportCn;
	}

	public String getReportProcessSttusCode() {
		return reportProcessSttusCode;
	}

	public void setReportProcessSttusCode(String reportProcessSttusCode) {
		this.reportProcessSttusCode = reportProcessSttusCode;
	}

	public String getReportProcessSttusNm() {
		return reportProcessSttusNm;
	}

	public void setReportProcessSttusNm(String reportProcessSttusNm) {
		this.reportProcessSttusNm = reportProcessSttusNm;
	}

}
