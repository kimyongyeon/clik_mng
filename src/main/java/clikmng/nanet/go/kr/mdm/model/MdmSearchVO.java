package clikmng.nanet.go.kr.mdm.model;

import java.util.List;

public class MdmSearchVO {
	
	private String schDt1          = ""; // 검색시작날짜
	private String schDt2          = ""; // 검색끝 날짜
	private String schDt3          = ""; // 등록일 검색시작날짜
	private String schDt4          = ""; // 등록일 검색끝 날짜
	private String schBrtcCode     = ""; // 지역
	private String schRks022       = ""; // 수집유형
	private String schRks025       = ""; // 지역
	private String schRks026       = ""; // 수집기관
	private String schLoAsmTyCode  = ""; // 기관유형
	private String schLoAsmCode    = ""; // 기관
	private String schRasmblyNumpr = ""; // 대수
	private String schPprtyCode    = ""; // 소속정당
	private String schDuplication  = ""; // 중복
	private String schKey          = ""; // 검색키
	private String schKw           = ""; // 검색어
	private String schAsembyNm     = ""; // 의원명
	private String schPprtyNm      = ""; // 소속정당
	private String schEstCode      = ""; // 지역구 코드
	private String schEstNm        = ""; // 지역구명
	private List<String> schEstList = null; // 지역구명
	private int    listCnt         = 100; // 리스트 출력수
	private int    pageNum         = 1;  // 현재 페이지
	private int    firstRecord     = 0;  // 첫번째 리스트
	private int    lastRecord      = 0;  // 마지막 리스트
	
	private String rasmblyId       = ""; // 의회코드
	private int    rasmblyNumpr    = 0;  // 대수
	private String asembyId        = ""; // 의원코드
	private String biId            = ""; // 의안코드
	private String extId           = ""; // 정책정보코드
	private String cnId            = "";
	
	private String schMtgNm        = ""; // 회의명
	private List<String> schMtgNmList = null; // 회의명
	private String schContent      = ""; // 내용
	private String schApp          = ""; // 별첨제목
	private List<String> schAppList = null; // 별첨제목
	private String schBiSj         = ""; // 의안명
	private String schPropsr       = ""; // 제안자
	private String schJrsdCmitId   = ""; // 소관위원회
	private List<String> schJrsdCmitIdList = null; // 소관위원회

	private String schBiKndStdCd   = ""; // 의안종류
	private String schLastResultClStdCd = ""; // 처리결과
	private String schConversion = "";   // 변환여부
	
	private String schOrgCodeStep1 = ""; // 기관유형 1단계
	private String schOrgCodeStep2 = ""; // 기관유형 2단계
	private String schOrgCodeStep3 = ""; // 기관유형 3단계
	private String schOrgCodeStep4 = ""; // 기관유형 4단계(기관명)
	private String schRegion       = "";
	private String schRegion2		= "";	// 20150402 추가
	private String schSiteId       = "";
	private String schSeedId       = "";
	private String schIsView       = "";
	private String schDel          = "";
	private String schDocType      = ""; // 자료유형
	private String schTitle        = ""; // 자료제목
	private String mdmAdm          = ""; // 관리메뉴
	private String schDflt         = "";
	private String disfile         = "";
	
	private String selCondition		= ""; // radio button value,  20150402 추가
	private String mdmAdmOrg		= ""; // 기관유형별에 사용되는 관리메뉴,  20150402 추가
	private String schFile			= ""; // 첨부파일유무, 20150402 추가
	
	private List<String> schLoAsmTyCodeList = null; // 의회구분에 의한 리스트
	private List<String> schRegionList 		= null; // 지역구분에 의한 리스트

	private String chargerYn = ""; //지방의회 담당자 등록자료 여부
	private String sort = ""; //리스트 정렬값
	
	private String excelSearchCnList = "";
	
	private String schDtConditionOperators = ""; //수집기간, 등록기간 OR 추가 여부를 위한 변수
	/*
	 * 회의록,의안,의원 상세화면 이전다음버튼 관련 변수
	 * 이전 다음 페이지 조회 구분용
	 * */
	private String isPrevNextPaging = "N";
	private String prevNextGubun = "";
	
	public List<String> getSchLoAsmTyCodeList() {
		return schLoAsmTyCodeList;
	}
	public void setSchLoAsmTyCodeList(List<String> schLoAsmTyCodeList) {
		this.schLoAsmTyCodeList = schLoAsmTyCodeList;
	}
	public List<String> getSchRegionList() {
		return schRegionList;
	}
	public void setSchRegionList(List<String> schRegionList) {
		this.schRegionList = schRegionList;
	}
	public String getSchOrgCodeStep4() {
		return schOrgCodeStep4;
	}
	public void setSchOrgCodeStep4(String schOrgCodeStep4) {
		this.schOrgCodeStep4 = schOrgCodeStep4;
	}
	public String getSchFile() {
		return schFile;
	}
	public void setSchFile(String schFile) {
		this.schFile = schFile;
	}
	public String getSchRegion2() {
		return schRegion2;
	}
	public void setSchRegion2(String schRegion2) {
		this.schRegion2 = schRegion2;
	}
	public String getMdmAdmOrg() {
		return mdmAdmOrg;
	}
	public void setMdmAdmOrg(String mdmAdmOrg) {
		this.mdmAdmOrg = mdmAdmOrg;
	}
	public String getSelCondition() {
		return selCondition;
	}
	public void setSelCondition(String selCondition) {
		this.selCondition = selCondition;
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
	
	public String getSchDt3() {
		return schDt3;
	}
	public void setSchDt3(String schDt3) {
		this.schDt3 = schDt3;
	}
	public String getSchDt4() {
		return schDt4;
	}
	public void setSchDt4(String schDt4) {
		this.schDt4 = schDt4;
	}
	public String getSchBrtcCode() {
		return schBrtcCode;
	}
	public void setSchBrtcCode(String schBrtcCode) {
		this.schBrtcCode = schBrtcCode;
	}
	public String getSchRks022() {
		return schRks022;
	}
	public void setSchRks022(String schRks022) {
		this.schRks022 = schRks022;
	}
	public String getSchRks025() {
		return schRks025;
	}
	public void setSchRks025(String schRks025) {
		this.schRks025 = schRks025;
	}
	public String getSchRks026() {
		return schRks026;
	}
	public void setSchRks026(String schRks026) {
		this.schRks026 = schRks026;
	}
	public String getSchLoAsmTyCode() {
		return schLoAsmTyCode;
	}
	public void setSchLoAsmTyCode(String schLoAsmTyCode) {
		this.schLoAsmTyCode = schLoAsmTyCode;
	}
	public String getSchLoAsmCode() {
		return schLoAsmCode;
	}
	public void setSchLoAsmCode(String schLoAsmCode) {
		this.schLoAsmCode = schLoAsmCode;
	}
	public String getSchRasmblyNumpr() {
		return schRasmblyNumpr;
	}
	public void setSchRasmblyNumpr(String schRasmblyNumpr) {
		this.schRasmblyNumpr = schRasmblyNumpr;
	}
	public String getSchPprtyCode() {
		return schPprtyCode;
	}
	public void setSchPprtyCode(String schPprtyCode) {
		this.schPprtyCode = schPprtyCode;
	}
	public String getSchDuplication() {
		return schDuplication;
	}
	public void setSchDuplication(String schDuplication) {
		this.schDuplication = schDuplication;
	}
	public String getSchKey() {
		return schKey;
	}
	public void setSchKey(String schKey) {
		this.schKey = schKey;
	}
	public String getSchKw() {
		return schKw;
	}
	public void setSchKw(String schKw) {
		this.schKw = schKw;
	}
	public String getSchAsembyNm() {
		return schAsembyNm;
	}
	public void setSchAsembyNm(String schAsembyNm) {
		this.schAsembyNm = schAsembyNm;
	}
	public String getSchPprtyNm() {
		return schPprtyNm;
	}
	public void setSchPprtyNm(String schPprtyNm) {
		this.schPprtyNm = schPprtyNm;
	}
	public String getSchEstCode() {
		return schEstCode;
	}
	public void setSchEstCode(String schEstCode) {
		this.schEstCode = schEstCode;
	}
	public String getSchEstNm() {
		return schEstNm;
	}
	public void setSchEstNm(String schEstNm) {
		this.schEstNm = schEstNm;
	}
	public List<String> getSchEstList() {
		return schEstList;
	}
	public void setSchEstList(List<String> schEstList) {
		this.schEstList = schEstList;
	}
	public int getListCnt() {
		return listCnt;
	}
	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getFirstRecord() {
		return firstRecord;
	}
	public void setFirstRecord(int firstRecord) {
		this.firstRecord = firstRecord;
	}
	public int getLastRecord() {
		return lastRecord;
	}
	public void setLastRecord(int lastRecord) {
		this.lastRecord = lastRecord;
	}
	public String getRasmblyId() {
		return rasmblyId;
	}
	public void setRasmblyId(String rasmblyId) {
		this.rasmblyId = rasmblyId;
	}
	public int getRasmblyNumpr() {
		return rasmblyNumpr;
	}
	public void setRasmblyNumpr(int rasmblyNumpr) {
		this.rasmblyNumpr = rasmblyNumpr;
	}
	public String getAsembyId() {
		return asembyId;
	}
	public void setAsembyId(String asembyId) {
		this.asembyId = asembyId;
	}
	public String getBiId() {
		return biId;
	}
	public void setBiId(String biId) {
		this.biId = biId;
	}
	public String getExtId() {
		return extId;
	}
	public void setExtId(String extId) {
		this.extId = extId;
	}
	public String getCnId() {
		return cnId;
	}
	public void setCnId(String cnId) {
		this.cnId = cnId;
	}
	public String getSchMtgNm() {
		return schMtgNm;
	}
	public void setSchMtgNm(String schMtgNm) {
		this.schMtgNm = schMtgNm;
	}
	public List<String> getSchMtgNmList() {
		return schMtgNmList;
	}
	public void setSchMtgNmList(List<String> schMtgNmList) {
		this.schMtgNmList = schMtgNmList;
	}
	public String getSchContent() {
		return schContent;
	}
	public void setSchContent(String schContent) {
		this.schContent = schContent;
	}
	public String getSchApp() {
		return schApp;
	}
	public void setSchApp(String schApp) {
		this.schApp = schApp;
	}
	public List<String> getSchAppList() {
		return schAppList;
	}
	public void setSchAppList(List<String> schAppList) {
		this.schAppList = schAppList;
	}
	public String getSchBiSj() {
		return schBiSj;
	}
	public void setSchBiSj(String schBiSj) {
		this.schBiSj = schBiSj;
	}
	public String getSchPropsr() {
		return schPropsr;
	}
	public void setSchPropsr(String schPropsr) {
		this.schPropsr = schPropsr;
	}
	public String getSchJrsdCmitId() {
		return schJrsdCmitId;
	}
	public void setSchJrsdCmitId(String schJrsdCmitId) {
		this.schJrsdCmitId = schJrsdCmitId;
	}
	public List<String> getSchJrsdCmitIdList() {
		return schJrsdCmitIdList;
	}
	public void setSchJrsdCmitIdList(List<String> schJrsdCmitIdList) {
		this.schJrsdCmitIdList = schJrsdCmitIdList;
	}
	public String getSchBiKndStdCd() {
		return schBiKndStdCd;
	}
	public void setSchBiKndStdCd(String schBiKndStdCd) {
		this.schBiKndStdCd = schBiKndStdCd;
	}
	public String getSchLastResultClStdCd() {
		return schLastResultClStdCd;
	}
	public void setSchLastResultClStdCd(String schLastResultClStdCd) {
		this.schLastResultClStdCd = schLastResultClStdCd;
	}
	public String getSchConversion() {
		return schConversion;
	}
	public void setSchConversion(String schConversion) {
		this.schConversion = schConversion;
	}
	public String getSchOrgCodeStep1() {
		return schOrgCodeStep1;
	}
	public void setSchOrgCodeStep1(String schOrgCodeStep1) {
		this.schOrgCodeStep1 = schOrgCodeStep1;
	}
	public String getSchOrgCodeStep2() {
		return schOrgCodeStep2;
	}
	public void setSchOrgCodeStep2(String schOrgCodeStep2) {
		this.schOrgCodeStep2 = schOrgCodeStep2;
	}
	public String getSchOrgCodeStep3() {
		return schOrgCodeStep3;
	}
	public void setSchOrgCodeStep3(String schOrgCodeStep3) {
		this.schOrgCodeStep3 = schOrgCodeStep3;
	}
	public String getSchRegion() {
		return schRegion;
	}
	public void setSchRegion(String schRegion) {
		this.schRegion = schRegion;
	}
	public String getSchSiteId() {
		return schSiteId;
	}
	public void setSchSiteId(String schSiteId) {
		this.schSiteId = schSiteId;
	}
	public String getSchSeedId() {
		return schSeedId;
	}
	public void setSchSeedId(String schSeedId) {
		this.schSeedId = schSeedId;
	}
	public String getSchIsView() {
		return schIsView;
	}
	public void setSchIsView(String schIsView) {
		this.schIsView = schIsView;
	}
	public String getSchDel() {
		return schDel;
	}
	public void setSchDel(String schDel) {
		this.schDel = schDel;
	}
	public String getSchDocType() {
		return schDocType;
	}
	public void setSchDocType(String schDocType) {
		this.schDocType = schDocType;
	}
	public String getSchTitle() {
		return schTitle;
	}
	public void setSchTitle(String schTitle) {
		this.schTitle = schTitle;
	}
	public String getMdmAdm() {
		return mdmAdm;
	}
	public void setMdmAdm(String mdmAdm) {
		this.mdmAdm = mdmAdm;
	}
	public String getSchDflt() {
		return schDflt;
	}
	public void setSchDflt(String schDflt) {
		this.schDflt = schDflt;
	}
	public String getDisfile() {
		return disfile;
	}
	public void setDisfile(String disfile) {
		this.disfile = disfile;
	}
	public String getChargerYn() {
		return chargerYn;
	}
	public void setChargerYn(String chargerYn) {
		this.chargerYn = chargerYn;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getIsPrevNextPaging() {
		return isPrevNextPaging;
	}
	public void setIsPrevNextPaging(String isPrevNextPaging) {
		this.isPrevNextPaging = isPrevNextPaging;
	}
	public String getPrevNextGubun() {
		return prevNextGubun;
	}
	public void setPrevNextGubun(String prevNextGubun) {
		this.prevNextGubun = prevNextGubun;
	}
	public String getExcelSearchCnList() {
		return excelSearchCnList;
	}
	public void setExcelSearchCnList(String excelSearchCnList) {
		this.excelSearchCnList = excelSearchCnList;
	}
	public String getSchDtConditionOperators() {
		return schDtConditionOperators;
	}
	public void setSchDtConditionOperators(String schDtConditionOperators) {
		this.schDtConditionOperators = schDtConditionOperators;
	}
}
