package clikmng.nanet.go.kr.sns.service;

import java.io.Serializable;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;

/**
 * 개요
 * - SNS 소통센터에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - SNS 소통센터의 SNS구분, SNS계정아이디, 사용여부, 삭제여부 항목을 관리한다.
 */
public class SnsManage extends ComDefaultVO implements Serializable {
 
	/**
	 * 새로운 SNS구분
	 */
	private String newSnsSeCode;
	/**
	 * 새로운 SNS계정아이디
	 */	
	private String newSnsAcntId;
	
	/**
	 * SNS구분 리스트
	 */
	private String snsCodeList;
	/**
	 * SNS구분
	 */
	private String snsSeCode;
	/**
	 * SNS구분
	 */
	private String snsSeCodeNm;
	
	/**
	 * SNS계정아이디
	 */
	private String snsAcntId;
	/**
	 * 사용여부
	 */
	private String useAt;
	/**
	 * 삭제여부
	 */
	private String delAt;

	/** 최초등록시점 */
	private String frstRegisterPnttm;

	/** 최초등록아이디 */
	private String frstRegisterId;

	/** 최종수정일 */
	private String lastUpdusrPnttm;

	/** 최종수정자 아이디 */
	private String lastUpdusrId;
	
	/** 이름 */
	private String asembyNm;
	
	private String rnum;
	
	/** 정렬조건 **/
	private String searchSort;
	
	
    /** 페이지 당 게시물 수 */
    private int selectCountperpg;
	
	
    
	public String getSearchSort() {
		return searchSort;
	}

	public void setSearchSort(String searchSort) {
		this.searchSort = searchSort;
	}

	public String getSnsSeCodeNm() {
		return snsSeCodeNm;
	}

	public void setSnsSeCodeNm(String snsSeCodeNm) {
		this.snsSeCodeNm = snsSeCodeNm;
	}
	
	public String getSnsSeCode() {
		return snsSeCode;
	}
	
	public void setSnsSeCode(String snsSeCode) {
		this.snsSeCode = snsSeCode;
	}
	
	public String getSnsAcntId() {
		return snsAcntId;
	}
	
	public void setSnsAcntId(String snsAcntId) {
		this.snsAcntId = snsAcntId;
	}
	
	public String getUseAt() {
		return useAt;
	}
	
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	
	public String getDelAt() {
		return delAt;
	}
	
	public void setDelAt(String delAt) {
		this.delAt = delAt;
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

	public String getSnsCodeList() {
		return snsCodeList;
	}

	public void setSnsCodeList(String snsCodeList) {
		this.snsCodeList = snsCodeList;
	}

	public String getNewSnsSeCode() {
		return newSnsSeCode;
	}

	public void setNewSnsSeCode(String newSnsSeCode) {
		this.newSnsSeCode = newSnsSeCode;
	}

	public String getNewSnsAcntId() {
		return newSnsAcntId;
	}

	public void setNewSnsAcntId(String newSnsAcntId) {
		this.newSnsAcntId = newSnsAcntId;
	}

	public String getAsembyNm() {
		return asembyNm;
	}

	public void setAsembyNm(String asembyNm) {
		this.asembyNm = asembyNm;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public int getSelectCountperpg() {
		return selectCountperpg;
	}

	public void setSelectCountperpg(int selectCountperpg) {
		this.selectCountperpg = selectCountperpg;
	}



	
}