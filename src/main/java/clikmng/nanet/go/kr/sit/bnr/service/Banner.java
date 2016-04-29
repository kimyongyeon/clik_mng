/**
 * 개요
 * - 배너에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 배너의 일련번호, 배너명, 링크URL, 배너설명, 반영여부 항목을 관리한다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:07:10
 */

package clikmng.nanet.go.kr.sit.bnr.service;
import clikmng.nanet.go.kr.cmm.ComDefaultVO;

public class Banner extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 배너 ID
	 */
	private String bannerId;
	/**
	 * 배너 명
	 */
	private String bannerNm;
	/**
	 * 링크 URL
	 */
	private String linkUrl;
	/**
	 * 배너 이미지
	 */
	private String bannerImage;
	/**
	 * 배너 이미지 파일
	 */
	private String bannerImageFile;	
	/**
	 * 배너 설명
	 */
	private String bannerDc;
	/**
	 * 정렬 순서
	 */
	private String sortOrdr;
	/**
	 * 반영여부
	 */
	private String reflctAt;
	/**
	 * 사용자 ID
	 */
	private String userId;
	/**
	 * 등록일자
	 */
	private String regDate;
	/**
	 * 파일첨부여부
	 */
	private boolean isAtchFile;
	
	/**
	 * 게시시작일
	 */
	private String ntceBgnde;
	/**
	 * 게시종료일
	 */
	private String ntceEndde;
        
	/** 게시시작일(시간) */
        private String ntceBgndeHH;
        
        /** 게시시작일(분) */
        private String ntceBgndeMM;
        
        /** 게시종료일(시간) */
        private String ntceEnddeHH;
        
        /** 게시종료일(분) */
        private String ntceEnddeMM;

    /**
	 * 기본이미지 사용여부
	 */
    private String bassImageUseAt;
        
    //--------------------------홍보존 소식 이미지 관리
    
    /** 홍보존소식 이미지관리 -  아이디 */
	private String bannerImgMngId;
    /** 홍보존소식 이미지관리 -  파일삭제 후 아이디 */
	private String rBannerImgMngId;
	/** 홍보존소식 이미지관리 -  지역코드 */
	private String areaCode;
	/** 홍보존소식 이미지관리 -  지역코드명 */
	private String areaNm;
	/** 홍보존소식 이미지관리 -  이미지 파일명 */
	private String imageFileNm;   
	/** 홍보존소식 이미지관리 -  이미지 변환 파일명 */
	private String imageCnvrFileNm;	
	/** 홍보존소식 이미지관리 -  고정여부 */
	private String fixingAt;
	/** 홍보존소식 이미지관리 -  최초등록아이디 */
	private String frstRegisterId;
	/** 홍보존소식 이미지관리 -  최초등록시점 */
	private String frstRegistPnttm; 
	/** 홍보존소식 이미지관리 -  최종등록아이디 */
	private String lastUpdusrId;
	/** 홍보존소식 이미지관리 -  최종등록시점 */
	private String lastUpdtPnttm;    
	/** 홍보존소식 이미지관리 -  기관유형코드 */
	private String insttClCode;
	/** 홍보존소식 이미지관리 -  지역코드 */
	private String brtcCode;
	/** 홍보존소식 이미지관리 -  지역코드 */
	private String codeId;
	/** 홍보존소식 이미지관리 -  지역명 */
	private String codeIdNm;
	/** 홍보존소식 이미지관리 -  이미지 위치 */
	private String streCours;
	/** 홍보존소식 이미지관리 -  이미지 원 이름*/
	private String streFileNm;
	/** 홍보존소식 이미지관리 -  이미지 확장자 */
	private String fileExtsn;
	
	
	
    
    //--------------------------홍보존 소식 이미지 관리  
        
        
	/**
	 * @return the bannerId
	 */
	public String getBannerId() {
		return bannerId;
	}
	public String getrBannerImgMngId() {
		return rBannerImgMngId;
	}
	public void setrBannerImgMngId(String rBannerImgMngId) {
		this.rBannerImgMngId = rBannerImgMngId;
	}
	public String getStreCours() {
		return streCours;
	}
	public void setStreCours(String streCours) {
		this.streCours = streCours;
	}
	public String getStreFileNm() {
		return streFileNm;
	}
	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}
	public String getFileExtsn() {
		return fileExtsn;
	}
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
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
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaNm() {
		return areaNm;
	}
	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}
	public String getBrtcCode() {
		return brtcCode;
	}
	public void setBrtcCode(String brtcCode) {
		this.brtcCode = brtcCode;
	}
	public String getInsttClCode() {
		return insttClCode;
	}
	public void setInsttClCode(String insttClCode) {
		this.insttClCode = insttClCode;
	}
	public String getBannerImgMngId() {
		return bannerImgMngId;
	}
	public void setBannerImgMngId(String bannerImgMngId) {
		this.bannerImgMngId = bannerImgMngId;
	}
	public String getImageFileNm() {
		return imageFileNm;
	}
	public void setImageFileNm(String imageFileNm) {
		this.imageFileNm = imageFileNm;
	}
	public String getImageCnvrFileNm() {
		return imageCnvrFileNm;
	}
	public void setImageCnvrFileNm(String imageCnvrFileNm) {
		this.imageCnvrFileNm = imageCnvrFileNm;
	}
	public String getFixingAt() {
		return fixingAt;
	}
	public void setFixingAt(String fixingAt) {
		this.fixingAt = fixingAt;
	}
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}
	/**
	 * @param bannerId the bannerId to set
	 */
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
	/**
	 * @return the bannerNm
	 */
	public String getBannerNm() {
		return bannerNm;
	}
	/**
	 * @param bannerNm the bannerNm to set
	 */
	public void setBannerNm(String bannerNm) {
		this.bannerNm = bannerNm;
	}
	/**
	 * @return the linkUrl
	 */
	public String getLinkUrl() {
		return linkUrl;
	}
	/**
	 * @param linkUrl the linkUrl to set
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	/**
	 * @return the bannerImage
	 */
	public String getBannerImage() {
		return bannerImage;
	}
	/**
	 * @param bannerImage the bannerImage to set
	 */
	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}
	/**
	 * @return the bannerImageFile
	 */
	public String getBannerImageFile() {
		return bannerImageFile;
	}
	/**
	 * @param bannerImageFile the bannerImageFile to set
	 */
	public void setBannerImageFile(String bannerImageFile) {
		this.bannerImageFile = bannerImageFile;
	}
	/**
	 * @return the bannerDc
	 */
	public String getBannerDc() {
		return bannerDc;
	}
	/**
	 * @param bannerDc the bannerDc to set
	 */
	public void setBannerDc(String bannerDc) {
		this.bannerDc = bannerDc;
	}
	/**
	 * @return the sortOrdr
	 */
	public String getSortOrdr() {
		return sortOrdr;
	}
	/**
	 * @param sortOrdr the sortOrdr to set
	 */
	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}
	/**
	 * @return the reflctAt
	 */
	public String getReflctAt() {
		return reflctAt;
	}
	/**
	 * @param reflctAt the reflctAt to set
	 */
	public void setReflctAt(String reflctAt) {
		this.reflctAt = reflctAt;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return the isAtchFile
	 */
	public boolean isAtchFile() {
		return isAtchFile;
	}
	/**
	 * @param isAtchFile the isAtchFile to set
	 */
	public void setAtchFile(boolean isAtchFile) {
		this.isAtchFile = isAtchFile;
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
	public String getNtceBgndeHH() {
		return ntceBgndeHH;
	}
	public void setNtceBgndeHH(String ntceBgndeHH) {
		this.ntceBgndeHH = ntceBgndeHH;
	}
	public String getNtceBgndeMM() {
		return ntceBgndeMM;
	}
	public void setNtceBgndeMM(String ntceBgndeMM) {
		this.ntceBgndeMM = ntceBgndeMM;
	}
	public String getNtceEnddeHH() {
		return ntceEnddeHH;
	}
	public void setNtceEnddeHH(String ntceEnddeHH) {
		this.ntceEnddeHH = ntceEnddeHH;
	}
	public String getNtceEnddeMM() {
		return ntceEnddeMM;
	}
	public void setNtceEnddeMM(String ntceEnddeMM) {
		this.ntceEnddeMM = ntceEnddeMM;
	}
	public String getBassImageUseAt() {
		return bassImageUseAt;
	}
	public void setBassImageUseAt(String bassImageUseAt) {
		this.bassImageUseAt = bassImageUseAt;
	}

}
