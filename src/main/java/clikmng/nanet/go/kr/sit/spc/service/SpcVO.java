package clikmng.nanet.go.kr.sit.spc.service;

/**
 * 
 * 스페셜검색을 처리하는 VO 클래스
 */
public class SpcVO extends SpcDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 페이지 당 게시물 수 */
    private int selectCountPg;
    
    /** 스페셜검색 ID */
    private String speclSearchId;
    
    /** 스페셜검색 제목 */
    private String mainSj;
    
    /** 스페셜검색 내용 */
    private String mainCtt;
    
    /** 스페셜검색 이미지 path */
    private String mainImagePath;
    
    /** 스페셜검색이미지 명 */
    private String mainImageFileNm;
    
    /** 스페셜검색 URL */
    private String mainUrl;
    
    /** 스페셜검색 의회명 */
    private String asmblyNm;

    /** 최초등록시점 */
    private String frstRegistPnttm;

	/** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;

    /** 연관 키워드 */
    private String kwrd;
    
    /** SUB 데이터 */
    private String subImage1Path;					// 이미지 저장 주소
    private String subImage2Path;
    private String subImage3Path;
    private String subImage4Path;
    private String subImage5Path;
    private String subImage6Path;
    
    private String subImage1FileNm;					// 이미지 명
    private String subImage2FileNm;
    private String subImage3FileNm;
    private String subImage4FileNm;
    private String subImage5FileNm;
    private String subImage6FileNm;
    
    private String subImage1Url;						// 링크 주소
    private String subImage2Url;
    private String subImage3Url;
    private String subImage4Url;
    private String subImage5Url;
    private String subImage6Url;
    
    private String subText1;								//설명
    private String subText2;
    private String subText3;
    private String subText4;
    private String subText5;
    private String subText6;
    
    private int inputFileSn;							// 파일 순서
    
    
    
    /** ------------ 검색관련 키워드 ----------- */
    
    /** 제목 */
    private String selMainSj;
    /** 의회명 */
    private String selAsmblyNm;
    /** 연관 검색어 */
    private String selKwrd;
    
    /** ------------ 지방의회 관련  ----------- */

    /** 광역시도코드*/
    private String brtcCode;
    
    /** 기관유형 공통코드 RKS001*/
    private String insttTyCode;
    
    /** 지방의회유형코드  WAC : 광역시의회, BAC : 기초의회 */
    private String loasmTyCode;
    
    /** 지방의회명 */
    private String loasmNm;
    
    /** 지방의회코드 */
    private String loasmCode;
    
    /** 지역코드 선택 */
    private String selRegion;
    
    /** 지방의회 선택 */
    private String selAssembly; 
    
    /** 시도구군코드 */
    private String ctprvngugunCode;
    
    /** 기관분류코드 */
    private String insttClCode;
    
    /** 공통코드 명 */
    private String codeIdNm;
    
    /**이미지 경로**/
    private String streCours;
    /**이미지 명 **/
    private String streFileNm;
    /**이미지 확장자 **/
    private String fileExtsn;
    
    /** ------------ 키워드 테이블 관련  ----------- */
    private String speclSearchKwrdId;
    
    
    
	public int getInputFileSn() {
		return inputFileSn;
	}

	public void setInputFileSn(int inputFileSn) {
		this.inputFileSn = inputFileSn;
	}

	public String getCodeIdNm() {
		return codeIdNm;
	}

	public void setCodeIdNm(String codeIdNm) {
		this.codeIdNm = codeIdNm;
	}

	public String getCtprvngugunCode() {
		return ctprvngugunCode;
	}

	public void setCtprvngugunCode(String ctprvngugunCode) {
		this.ctprvngugunCode = ctprvngugunCode;
	}

	public String getInsttClCode() {
		return insttClCode;
	}

	public void setInsttClCode(String insttClCode) {
		this.insttClCode = insttClCode;
	}

	public String getSpeclSearchKwrdId() {
		return speclSearchKwrdId;
	}

	public void setSpeclSearchKwrdId(String speclSearchKwrdId) {
		this.speclSearchKwrdId = speclSearchKwrdId;
	}

	public String getSelRegion() {
		return selRegion;
	}

	public void setSelRegion(String selRegion) {
		this.selRegion = selRegion;
	}

	public String getSelAssembly() {
		return selAssembly;
	}

	public void setSelAssembly(String selAssembly) {
		this.selAssembly = selAssembly;
	}

	public String getMainCtt() {
		return mainCtt;
	}

	public void setMainCtt(String mainCtt) {
		this.mainCtt = mainCtt;
	}

	public String getInsttTyCode() {
		return insttTyCode;
	}

	public void setInsttTyCode(String insttTyCode) {
		this.insttTyCode = insttTyCode;
	}

	public String getLoasmTyCode() {
		return loasmTyCode;
	}

	public void setLoasmTyCode(String loasmTyCode) {
		this.loasmTyCode = loasmTyCode;
	}

	public String getLoasmNm() {
		return loasmNm;
	}

	public void setLoasmNm(String loasmNm) {
		this.loasmNm = loasmNm;
	}

	public String getLoasmCode() {
		return loasmCode;
	}

	public void setLoasmCode(String loasmCode) {
		this.loasmCode = loasmCode;
	}

	public String getBrtcCode() {
		return brtcCode;
	}

	public void setBrtcCode(String brtcCode) {
		this.brtcCode = brtcCode;
	}

	public String getSelMainSj() {
		return selMainSj;
	}

	public void setSelMainSj(String selMainSj) {
		this.selMainSj = selMainSj;
	}

	public String getSelAsmblyNm() {
		return selAsmblyNm;
	}

	public void setSelAsmblyNm(String selAsmblyNm) {
		this.selAsmblyNm = selAsmblyNm;
	}

	public String getSelKwrd() {
		return selKwrd;
	}

	public void setSelKwrd(String selKwrd) {
		this.selKwrd = selKwrd;
	}

	public int getSelectCountPg() {
		return selectCountPg;
	}

	public String getMainUrl() {
		return mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public void setSelectCountPg(int selectCountPg) {
		this.selectCountPg = selectCountPg;
	}

	public String getSpeclSearchId() {
		return speclSearchId;
	}

	public void setSpeclSearchId(String speclSearchId) {
		this.speclSearchId = speclSearchId;
	}

	public String getMainSj() {
		return mainSj;
	}

	public void setMainSj(String mainSj) {
		this.mainSj = mainSj;
	}

	public String getMainImagePath() {
		return mainImagePath;
	}

	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}

	public String getMainImageFileNm() {
		return mainImageFileNm;
	}

	public void setMainImageFileNm(String mainImageFileNm) {
		this.mainImageFileNm = mainImageFileNm;
	}

	public String getAsmblyNm() {
		return asmblyNm;
	}

	public void setAsmblyNm(String asmblyNm) {
		this.asmblyNm = asmblyNm;
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

    public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
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

	public String getKwrd() {
		return kwrd;
	}

	public void setKwrd(String kwrd) {
		this.kwrd = kwrd;
	}

	public String getSubImage1Path() {
		return subImage1Path;
	}

	public void setSubImage1Path(String subImage1Path) {
		this.subImage1Path = subImage1Path;
	}

	public String getSubImage2Path() {
		return subImage2Path;
	}

	public void setSubImage2Path(String subImage2Path) {
		this.subImage2Path = subImage2Path;
	}

	public String getSubImage3Path() {
		return subImage3Path;
	}

	public void setSubImage3Path(String subImage3Path) {
		this.subImage3Path = subImage3Path;
	}

	public String getSubImage4Path() {
		return subImage4Path;
	}

	public void setSubImage4Path(String subImage4Path) {
		this.subImage4Path = subImage4Path;
	}

	public String getSubImage5Path() {
		return subImage5Path;
	}

	public void setSubImage5Path(String subImage5Path) {
		this.subImage5Path = subImage5Path;
	}

	public String getSubImage6Path() {
		return subImage6Path;
	}

	public void setSubImage6Path(String subImage6Path) {
		this.subImage6Path = subImage6Path;
	}

	public String getSubImage1FileNm() {
		return subImage1FileNm;
	}

	public void setSubImage1FileNm(String subImage1FileNm) {
		this.subImage1FileNm = subImage1FileNm;
	}

	public String getSubImage2FileNm() {
		return subImage2FileNm;
	}

	public void setSubImage2FileNm(String subImage2FileNm) {
		this.subImage2FileNm = subImage2FileNm;
	}

	public String getSubImage3FileNm() {
		return subImage3FileNm;
	}

	public void setSubImage3FileNm(String subImage3FileNm) {
		this.subImage3FileNm = subImage3FileNm;
	}

	public String getSubImage4FileNm() {
		return subImage4FileNm;
	}

	public void setSubImage4FileNm(String subImage4FileNm) {
		this.subImage4FileNm = subImage4FileNm;
	}

	public String getSubImage5FileNm() {
		return subImage5FileNm;
	}

	public void setSubImage5FileNm(String subImage5FileNm) {
		this.subImage5FileNm = subImage5FileNm;
	}

	public String getSubImage6FileNm() {
		return subImage6FileNm;
	}

	public void setSubImage6FileNm(String subImage6FileNm) {
		this.subImage6FileNm = subImage6FileNm;
	}

	public String getSubImage1Url() {
		return subImage1Url;
	}

	public void setSubImage1Url(String subImage1Url) {
		this.subImage1Url = subImage1Url;
	}

	public String getSubImage2Url() {
		return subImage2Url;
	}

	public void setSubImage2Url(String subImage2Url) {
		this.subImage2Url = subImage2Url;
	}

	public String getSubImage3Url() {
		return subImage3Url;
	}

	public void setSubImage3Url(String subImage3Url) {
		this.subImage3Url = subImage3Url;
	}

	public String getSubImage4Url() {
		return subImage4Url;
	}

	public void setSubImage4Url(String subImage4Url) {
		this.subImage4Url = subImage4Url;
	}

	public String getSubImage5Url() {
		return subImage5Url;
	}

	public void setSubImage5Url(String subImage5Url) {
		this.subImage5Url = subImage5Url;
	}

	public String getSubImage6Url() {
		return subImage6Url;
	}

	public void setSubImage6Url(String subImage6Url) {
		this.subImage6Url = subImage6Url;
	}

	public String getSubText1() {
		return subText1;
	}

	public void setSubText1(String subText1) {
		this.subText1 = subText1;
	}

	public String getSubText2() {
		return subText2;
	}

	public void setSubText2(String subText2) {
		this.subText2 = subText2;
	}

	public String getSubText3() {
		return subText3;
	}

	public void setSubText3(String subText3) {
		this.subText3 = subText3;
	}

	public String getSubText4() {
		return subText4;
	}

	public void setSubText4(String subText4) {
		this.subText4 = subText4;
	}

	public String getSubText5() {
		return subText5;
	}

	public void setSubText5(String subText5) {
		this.subText5 = subText5;
	}

	public String getSubText6() {
		return subText6;
	}

	public void setSubText6(String subText6) {
		this.subText6 = subText6;
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
	
}
