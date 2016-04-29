package clikmng.nanet.go.kr.uss.mng.service;

import java.io.Serializable;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;

/**
 * 개요
 * - SNS 소통센터에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - SNS 소통센터의 SNS구분, SNS계정아이디, 사용여부, 삭제여부 항목을 관리한다.
 */
public class MngManage extends ComDefaultVO implements Serializable {

	/** 관리자 구분	 */
	private String mngrSeCode;

	/** 관리자 아이디	 */
	private String mngrId;

	/** 관리자 이름	 */	
	private String mngrNm;
	
	/** 관리자 패스워드  */	
	private String 	mngrPw;
	
	/** 관리자 부서	 */
	private String mngrDept;
	
	/** 관리자 이메일 */
	private String mngrEmail;
	
	
	/** 최초등록시점 */
	private String frstRegisterPnttm;

	/** 최초등록아이디 */
	private String frstRegisterId;

	/** 최종수정일 */
	private String lastUpdtPnttm;

	/** 최종수정자 아이디 */
	private String lastUpdusrId;
	
	/** 관리자 권한 분류 코드 */
	private String authorClCode;
	
// :::::::::::::: 지방의회 / 지자체 :::::::::::::: 
	/** 지방의회 / 지자체 */
	private String unityId;
	
	/** 소속코드	 */
	private String psitnCode;
	
	/** 담당자 구분 코드	 */
	private String chargerSeCode;
	
	/** 담당자 이름	 */
	private String chargerNm;
	
	/** 담당자 이메일	 */
	private String chargerEmail;
	
	/** 담당자 전화번호	 */
	private String chargerTelno;
	
	/** 관심지방의회 ID 1 */
	private String intrstRasmblyId1;
	
	/** 관심지방의회 ID 2 */
	private String intrstRasmblyId2;
	
	/** 관심지방의회 ID 3 */
	private String intrstRasmblyId3;		
	
	/** 담당 */
	private String chrg;	
	
	/** 협정기관 여부	 */
	private String trtyEngnAt;
	
	/** 승인상태 여부	 */
	private String confmSttusAt;
	
	/** 승인상태 명	 */
	private String confmSttusNm;
	
	/** 승인시점	 */
	private String confmPnttm;
	
	/** 승인자 id	 */
	private String confmerId;
	
	
	/** 관리자 및 지방의회/지자체 관리자 권한 리스트 관련 */
	// 권한코드
	private String authorCode;
	
	// 권한명
	private String authorNm;
	
	/** 페이지 출력 게시물 수 */
	private int selectCountperpg;
	
	/** 검색구분 */
	// 관리자 ID
	private String selMngrId;
	// 관리자 이름
	private String selMngrNm;
	
	// 관리자 구분(직원:1, 일반:2)
	private String selMngrOpt;
	
	// 관리자 여부 구분
	private String selChargerSeCode;
	
	// 관리자 담당 구분
	private String selChrg;
	
	// 관리자 승인여부 구분
	private String selConfmSttusAt;
	
	/** 등록일 */
	private String regDate;
	
	/** 기관분류코드 */
	private String insttClCode;
	
	/** 기관분류코드명 */
	private String insttClCodeNm;
	
	/** 기관분류코드 - 레벨1 */
	private String fInsttClCode;
	
	/** 기관분류코드명  - 레벨1*/
	private String fInsttClCodeNm;	
	
	/** 기관분류  - 광역시군구*/
	private String brtcCode;
	
	/** 기관분류  - 소속선택*/
	private String loasmCode;
	
	/** 관심의회  - 의회선택*/
	private String intRasmblyInsttClCode1;
	
	/** 관심의회  - 의회선택*/
	private String intRasmblyInsttClCode2;
	
	/** 관심의회  - 의회선택*/
	private String intRasmblyInsttClCode3;
	
	/** 관심의회  - 의회선택*/
	private String intRasmblyBrtc1;
	
	/** 관심의회  - 의회선택*/
	private String intRasmblyBrtc2;
	
	/** 관심의회  - 의회선택*/
	private String intRasmblyBrtc3;
	
	/** 관심의회  - 선택된 의회 */
	private String[] selAssembly;
	
	/** 관심의회  - 의회 이름 */
	private String intRasmblyInsttClCodeNm1;
	
	/** 관심의회  - 지자체 및 의회 이름 */
	private String loasmNm;
	
	/** 정렬옵션 */
	private String sortOrder;
	
	
	
	
	public String getMngrEmail() {
		return mngrEmail;
	}

	public void setMngrEmail(String mngrEmail) {
		this.mngrEmail = mngrEmail;
	}
	
	public String getMngrPw() {
		return mngrPw;
	}

	public void setMngrPw(String mngrPw) {
		this.mngrPw = mngrPw;
	}	

	public String getLoasmNm() {
		return loasmNm;
	}

	public void setLoasmNm(String loasmNm) {
		this.loasmNm = loasmNm;
	}

	public String getIntRasmblyInsttClCodeNm1() {
		return intRasmblyInsttClCodeNm1;
	}

	public void setIntRasmblyInsttClCodeNm1(String intRasmblyInsttClCodeNm1) {
		this.intRasmblyInsttClCodeNm1 = intRasmblyInsttClCodeNm1;
	}

	public String[] getSelAssembly() {
		return selAssembly;
	}

	public void setSelAssembly(String[] selAssembly) {
		this.selAssembly = selAssembly;
	}

	public String getIntRasmblyInsttClCode1() {
		return intRasmblyInsttClCode1;
	}

	public void setIntRasmblyInsttClCode1(String intRasmblyInsttClCode1) {
		this.intRasmblyInsttClCode1 = intRasmblyInsttClCode1;
	}

	public String getIntRasmblyInsttClCode2() {
		return intRasmblyInsttClCode2;
	}

	public void setIntRasmblyInsttClCode2(String intRasmblyInsttClCode2) {
		this.intRasmblyInsttClCode2 = intRasmblyInsttClCode2;
	}

	public String getIntRasmblyInsttClCode3() {
		return intRasmblyInsttClCode3;
	}

	public void setIntRasmblyInsttClCode3(String intRasmblyInsttClCode3) {
		this.intRasmblyInsttClCode3 = intRasmblyInsttClCode3;
	}

	public String getIntRasmblyBrtc1() {
		return intRasmblyBrtc1;
	}

	public void setIntRasmblyBrtc1(String intRasmblyBrtc1) {
		this.intRasmblyBrtc1 = intRasmblyBrtc1;
	}

	public String getIntRasmblyBrtc2() {
		return intRasmblyBrtc2;
	}

	public void setIntRasmblyBrtc2(String intRasmblyBrtc2) {
		this.intRasmblyBrtc2 = intRasmblyBrtc2;
	}

	public String getIntRasmblyBrtc3() {
		return intRasmblyBrtc3;
	}

	public void setIntRasmblyBrtc3(String intRasmblyBrtc3) {
		this.intRasmblyBrtc3 = intRasmblyBrtc3;
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

	public String getfInsttClCode() {
		return fInsttClCode;
	}

	public void setfInsttClCode(String fInsttClCode) {
		this.fInsttClCode = fInsttClCode;
	}

	public String getfInsttClCodeNm() {
		return fInsttClCodeNm;
	}

	public void setfInsttClCodeNm(String fInsttClCodeNm) {
		this.fInsttClCodeNm = fInsttClCodeNm;
	}

	public String getInsttClCode() {
		return insttClCode;
	}

	public void setInsttClCode(String insttClCode) {
		this.insttClCode = insttClCode;
	}

	public String getInsttClCodeNm() {
		return insttClCodeNm;
	}

	public void setInsttClCodeNm(String insttClCodeNm) {
		this.insttClCodeNm = insttClCodeNm;
	}

	public String getChrg() {
		return chrg;
	}

	public void setChrg(String chrg) {
		this.chrg = chrg;
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
	
	
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getMngrId() {
		return mngrId;
	}

	public void setMngrId(String mngrId) {
		this.mngrId = mngrId;
	}

	public String getMngrNm() {
		return mngrNm;
	}

	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}

	public String getMngrDept() {
		return mngrDept;
	}

	public void setMngrDept(String mngrDept) {
		this.mngrDept = mngrDept;
	}

	public String getAuthorClCode() {
		return authorClCode;
	}

	public void setAuthorClCode(String authorClCode) {
		this.authorClCode = authorClCode;
	}

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public String getAuthorNm() {
		return authorNm;
	}

	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}

	public int getSelectCountperpg() {
		return selectCountperpg;
	}

	public void setSelectCountperpg(int selectCountperpg) {
		this.selectCountperpg = selectCountperpg;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getSelMngrId() {
		return selMngrId;
	}

	public void setSelMngrId(String selMngrId) {
		this.selMngrId = selMngrId;
	}

	public String getSelMngrNm() {
		return selMngrNm;
	}

	public void setSelMngrNm(String selMngrNm) {
		this.selMngrNm = selMngrNm;
	}

	public String getSelMngrOpt() {
		return selMngrOpt;
	}

	public void setSelMngrOpt(String selMngrOpt) {
		this.selMngrOpt = selMngrOpt;
	}	

	public String getUnityId() {
		return unityId;
	}

	public void setUnityId(String unityId) {
		this.unityId = unityId;
	}

	public String getPsitnCode() {
		return psitnCode;
	}

	public void setPsitnCode(String psitnCode) {
		this.psitnCode = psitnCode;
	}

	public String getChargerSeCode() {
		return chargerSeCode;
	}

	public void setChargerSeCode(String chargerSeCode) {
		this.chargerSeCode = chargerSeCode;
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

	public String getChargerTelno() {
		return chargerTelno;
	}

	public void setChargerTelno(String chargerTelno) {
		this.chargerTelno = chargerTelno;
	}

	public String getIntrstRasmblyId1() {
		return intrstRasmblyId1;
	}

	public void setIntrstRasmblyId1(String intrstRasmblyId1) {
		this.intrstRasmblyId1 = intrstRasmblyId1;
	}

	public String getIntrstRasmblyId2() {
		return intrstRasmblyId2;
	}

	public void setIntrstRasmblyId2(String intrstRasmblyId2) {
		this.intrstRasmblyId2 = intrstRasmblyId2;
	}

	public String getIntrstRasmblyId3() {
		return intrstRasmblyId3;
	}

	public void setIntrstRasmblyId3(String intrstRasmblyId3) {
		this.intrstRasmblyId3 = intrstRasmblyId3;
	}

	public String getTrtyEngnAt() {
		return trtyEngnAt;
	}

	public void setTrtyEngnAt(String trtyEngnAt) {
		this.trtyEngnAt = trtyEngnAt;
	}

	public String getConfmSttusAt() {
		return confmSttusAt;
	}

	public void setConfmSttusAt(String confmSttusAt) {
		this.confmSttusAt = confmSttusAt;
	}

	public String getConfmPnttm() {
		return confmPnttm;
	}

	public void setConfmPnttm(String confmPnttm) {
		this.confmPnttm = confmPnttm;
	}

	public String getConfmerId() {
		return confmerId;
	}

	public void setConfmerId(String confmerId) {
		this.confmerId = confmerId;
	}

	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	public String getConfmSttusNm() {
		return confmSttusNm;
	}

	public void setConfmSttusNm(String confmSttusNm) {
		this.confmSttusNm = confmSttusNm;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getMngrSeCode() {
		return mngrSeCode;
	}

	public void setMngrSeCode(String mngrSeCode) {
		this.mngrSeCode = mngrSeCode;
	}

	public String getSelChrg() {
		return selChrg;
	}

	public void setSelChrg(String selChrg) {
		this.selChrg = selChrg;
	}

	public String getSelConfmSttusAt() {
		return selConfmSttusAt;
	}

	public void setSelConfmSttusAt(String selConfmSttusAt) {
		this.selConfmSttusAt = selConfmSttusAt;
	}

	public String getSelChargerSeCode() {
		return selChargerSeCode;
	}

	public void setSelChargerSeCode(String selChargerSeCode) {
		this.selChargerSeCode = selChargerSeCode;
	}
	
	
	
}