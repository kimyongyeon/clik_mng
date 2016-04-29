package clikmng.nanet.go.kr.rlm.service;

/**
 * 
 * 연계 API 관련 VO
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
public class RlmManageVO extends RlmManageDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 검색 - 기간 */
    private String selectBgdate;
    private String selectEndate;

    private String schDt1;
    private String schDt2;
    
    // 연계의회 선택
    private String selectRasmbly;
    // 연계api 선택
    private String selectApi;
    
    
    
    
    /** 목록 */
    //수집기관
    private String rasmblyNm;
    //연계API 구분
    private String codeNm;
    //수신건수
    private String recptnTotcnt;
    //수신일자
    private String occrrncDe;
    //error message
    private String resultMssage;
    
    
    /** 연계의회 셀렉트 박스 */
    // 연계의회 명
    private String rasmblyId;
    
    /** 연계 API 구분 선택 */
    private String codeId;
	
    /** 정렬 옵션 */
    private String sortOrder;
    
    //기관
    private String INSTT_CL_CODE = "";
    //기관유형
    private String INSTT_TY_CODE = "";
    
    private String LOASM_TY_CODE = "";
    //지역
    private String BRTC_CODE = "";
    //의회
    private String LOASM_CODE = "";
    //수집자료구분
    private String DTA_SE_CODE = "";
    
	public String getSelectRasmbly() {
		return selectRasmbly;
	}
	public void setSelectRasmbly(String selectRasmbly) {
		this.selectRasmbly = selectRasmbly;
	}
	public String getSelectApi() {
		return selectApi;
	}
	public void setSelectApi(String selectApi) {
		this.selectApi = selectApi;
	}
	public String getRasmblyId() {
		return rasmblyId;
	}
	public void setRasmblyId(String rasmblyId) {
		this.rasmblyId = rasmblyId;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getRasmblyNm() {
		return rasmblyNm;
	}
	public void setRasmblyNm(String rasmblyNm) {
		this.rasmblyNm = rasmblyNm;
	}
	public String getCodeNm() {
		return codeNm;
	}
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}
	public String getRecptnTotcnt() {
		return recptnTotcnt;
	}
	public void setRecptnTotcnt(String recptnTotcnt) {
		this.recptnTotcnt = recptnTotcnt;
	}
	public String getOccrrncDe() {
		return occrrncDe;
	}
	public void setOccrrncDe(String occrrncDe) {
		this.occrrncDe = occrrncDe;
	}
	public String getResultMssage() {
		return resultMssage;
	}
	public void setResultMssage(String resultMssage) {
		this.resultMssage = resultMssage;
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
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getINSTT_CL_CODE() {
		return INSTT_CL_CODE;
	}
	public void setINSTT_CL_CODE(String iNSTT_CL_CODE) {
		INSTT_CL_CODE = iNSTT_CL_CODE;
	}
	public String getINSTT_TY_CODE() {
		return INSTT_TY_CODE;
	}
	public void setINSTT_TY_CODE(String iNSTT_TY_CODE) {
		INSTT_TY_CODE = iNSTT_TY_CODE;
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
	public String getDTA_SE_CODE() {
		return DTA_SE_CODE;
	}
	public void setDTA_SE_CODE(String dTA_SE_CODE) {
		DTA_SE_CODE = dTA_SE_CODE;
	}
    
    
    

}
