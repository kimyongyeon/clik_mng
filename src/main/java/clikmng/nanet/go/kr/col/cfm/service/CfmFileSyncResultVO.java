package clikmng.nanet.go.kr.col.cfm.service;

/**
 * 
 * 수집파일 동기화 결과 VO 클래스
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
public class CfmFileSyncResultVO {
	
	/** 수집파일동기화결과ID */
	private String syncRetId;
	/** 수집파일동기화로그ID */
	private String compareId;
	/** 파일URL */
	private String fileUrl;
	/** 파일경로 */
	private String filePath;
	/** API키 */
	private String apiKey;
	/** 최초등록시점 */
	private String frstRegistPnttm;
	/** 최초등록자ID */
	private String frstRegisterId;
	/** 임시파일경로 */
	private String tempFilePath;
	
	/** 번호 */
	private String rnum;
	
	/** 지방의회ID */
	private String rasmblyId;
	
	/** 회의록,의안,의원,부록 ID */
	private String cn;
	
	/** 파일ID */
	private String fileId;
	
	/** 파일여부 */
	private String fileAt;
	
	/** 표준연계API코드 */
	private String apiCode;
	
	/** 표준연계API명 */
	private String apiNm;
	
	/** 지방의회명 */
	private String rasmblyNm;
	
	/** 의안 ID */
	private String biId;
	
	/** 의안파일 ID */
	private String biFileId;
	
	/** 의원 ID */
	private String asembyId;
	
	/** 회의록 ID */
	private String mintsId;
	
	/** 부록 ID */
	private String apndxId;
	
	public String getSyncRetId() {
		return syncRetId;
	}

	public void setSyncRetId(String syncRetId) {
		this.syncRetId = syncRetId;
	}

	public String getCompareId() {
		return compareId;
	}

	public void setCompareId(String compareId) {
		this.compareId = compareId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
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

	public String getTempFilePath() {
		return tempFilePath;
	}

	public void setTempFilePath(String tempFilePath) {
		this.tempFilePath = tempFilePath;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getRasmblyId() {
		return rasmblyId;
	}

	public void setRasmblyId(String rasmblyId) {
		this.rasmblyId = rasmblyId;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileAt() {
		return fileAt;
	}

	public void setFileAt(String fileAt) {
		this.fileAt = fileAt;
	}

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getApiNm() {
		return apiNm;
	}

	public void setApiNm(String apiNm) {
		this.apiNm = apiNm;
	}

	public String getRasmblyNm() {
		return rasmblyNm;
	}

	public void setRasmblyNm(String rasmblyNm) {
		this.rasmblyNm = rasmblyNm;
	}

	public String getBiId() {
		return biId;
	}

	public void setBiId(String biId) {
		this.biId = biId;
	}

	public String getBiFileId() {
		return biFileId;
	}

	public void setBiFileId(String biFileId) {
		this.biFileId = biFileId;
	}

	public String getAsembyId() {
		return asembyId;
	}

	public void setAsembyId(String asembyId) {
		this.asembyId = asembyId;
	}

	public String getMintsId() {
		return mintsId;
	}

	public void setMintsId(String mintsId) {
		this.mintsId = mintsId;
	}

	public String getApndxId() {
		return apndxId;
	}

	public void setApndxId(String apndxId) {
		this.apndxId = apndxId;
	}
	
}
