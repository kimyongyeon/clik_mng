package clikmng.nanet.go.kr.mdm.model;

public class MdmTnsrAsmblyAsembyVO {
	
	private String ASEMBY_ID       = ""; // 의원_ID	
	private String ASEMBY_NM       = ""; // 의원_이름
	private String PHOTO_FILE_NM; // 사진_파일_이름
	private String BRTHDY          = ""; // 생년월일
	private String OWNHOM_TLPHON   = ""; // 자택_번호
	private String MBTLNUM         = ""; // 휴대폰번호
	private String OFFM_TLPHON     = ""; // 사무실_전화
	private String FAX             = ""; // 팩스
	private String ADRES           = ""; // 주소
	private String RDNMADR         = ""; // 도로명주소
	private String EMAIL           = ""; // 이메일
	private String TWITTER         = ""; // 트위터
	private String FACEBOOK        = ""; // 페이스북
	private String HMPG            = ""; // 홈페이지
	private String RASMBLY_ID      = ""; // 지방의회_ID
	private String PHOTO_FILE_URL  = ""; // 사진_파일_URL
	private String FRST_REGIST_DT  = ""; // 최초등록일시
	private String LAST_UPDT_DT    = ""; // 최종수정일시
	private String DELETE_DT       = ""; // 삭제일시
	private String CUD_CODE        = ""; // CUD_코드
	private String PHOTO_FILE_LAST_UPDT_TM = ""; // 사진_파일_최종수정시각
	private String PHOTO_FILE_PATH = ""; // 사진_파일_경로
	private String PHOTO_FILE_HASH = ""; // 사진_파일_HASH
	private String ORGINL_PHOTO_FILE_PATH = ""; 
	private String CNTC_INPUT_SE_CODE = ""; // 수집유형
	
	private int    RASMBLY_NUMPR   = 0;    // 대수
	private String PPRTY_ID        = "";   // 소속정당 ID
	private String PPRTY_CODE      = "";   // 소속정당 ID
	private String PPRTY_NM        = "";   // 소속정당
	private String EST_ID          = "";   // 지역구 ID
	private String EST_NM          = "";   // 지역구
	private String HT_SE_STDCD = "";
	private String MTGNM_ID = "";
	private String ASEMBY_OFCPS_STDCD = "";
	private String ASEMBY_CN = "";
	
	private String HT_SE_STDCD01 = "";
	private String MTGNM_ID01 = "";
	private String ASEMBY_OFCPS_STDCD01 = "";

	private String HT_SE_STDCD02 = "";
	private String MTGNM_ID02 = "";
	private String ASEMBY_OFCPS_STDCD02 = "";

	private String ADRESDETAIL = "";
	private String phone1 = "";
	private String phone2 = "";
	private String phone3 = "";
	private String email1 = "";
	private String email2 = "";
	private String email3 = "";
	
	private String CAREER_MATTER   = ""; // 경력_사항
	private String ACDMCR_MATTER   = ""; // 학력_사항	
	
	private String RASMBLY_ASEMBY_ID = "";
	private String ISVIEW          = "";
	private String RASMBLY_NM      = ""; // 지방의회명
	private String ACT_AT = "";

	private int DUPCNT = 0;
	private String LAST_UPDUSR_ID = "";

	/* 활동및경력 저장용 */
	private String[] ACT_RASMBLY_ID;
	private String[] ACT_RASMBLY_NUMPR;
	private String[] ACT_ASEMBY_ID;
	private String[] ACT_ACT_AT;
	private String[] ACT_NOACT_RESN_STDCD;
	private String[] ACT_EST_ID;
	private String[] ACT_PPRTY_CODE;
	private String[] ACT_CAREER_MATTER;
	private String[] ACT_ACDMCR_MATTER;
	private String[] ACT_GRT;
	private String[] ACT_ASEMBY_CAREER;
	private String[] ACT_WNPZ_CAREER;
	private String[] ACT_CUD_CODE;
	
	private String[] POSITION_INFO;
	
	private String COLCT_AT;
	
	public String getASEMBY_ID() {
		return ASEMBY_ID;
	}

	public void setASEMBY_ID(String aSEMBY_ID) {
		ASEMBY_ID = aSEMBY_ID;
	}

	public String getASEMBY_NM() {
		return ASEMBY_NM;
	}

	public void setASEMBY_NM(String aSEMBY_NM) {
		ASEMBY_NM = aSEMBY_NM;
	}

	public String getPHOTO_FILE_NM() {
		return PHOTO_FILE_NM;
	}

	public void setPHOTO_FILE_NM(String pHOTO_FILE_NM) {
		PHOTO_FILE_NM = pHOTO_FILE_NM;
	}

	public String getBRTHDY() {
		return BRTHDY;
	}

	public void setBRTHDY(String bRTHDY) {
		BRTHDY = bRTHDY;
	}

	public String getOWNHOM_TLPHON() {
		return OWNHOM_TLPHON;
	}

	public void setOWNHOM_TLPHON(String oWNHOM_TLPHON) {
		OWNHOM_TLPHON = oWNHOM_TLPHON;
	}

	public String getMBTLNUM() {
		return MBTLNUM;
	}

	public void setMBTLNUM(String mBTLNUM) {
		MBTLNUM = mBTLNUM;
	}

	public String getOFFM_TLPHON() {
		return OFFM_TLPHON;
	}

	public void setOFFM_TLPHON(String oFFM_TLPHON) {
		OFFM_TLPHON = oFFM_TLPHON;
	}

	public String getFAX() {
		return FAX;
	}

	public void setFAX(String fAX) {
		FAX = fAX;
	}

	public String getADRES() {
		return ADRES;
	}

	public void setADRES(String aDRES) {
		ADRES = aDRES;
	}

	public String getRDNMADR() {
		return RDNMADR;
	}

	public void setRDNMADR(String rDNMADR) {
		RDNMADR = rDNMADR;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getTWITTER() {
		return TWITTER;
	}

	public void setTWITTER(String tWITTER) {
		TWITTER = tWITTER;
	}

	public String getFACEBOOK() {
		return FACEBOOK;
	}

	public void setFACEBOOK(String fACEBOOK) {
		FACEBOOK = fACEBOOK;
	}

	public String getHMPG() {
		return HMPG;
	}

	public void setHMPG(String hMPG) {
		HMPG = hMPG;
	}

	public String getRASMBLY_ID() {
		return RASMBLY_ID;
	}

	public void setRASMBLY_ID(String rASMBLY_ID) {
		RASMBLY_ID = rASMBLY_ID;
	}

	public String getPHOTO_FILE_URL() {
		return PHOTO_FILE_URL;
	}

	public void setPHOTO_FILE_URL(String pHOTO_FILE_URL) {
		PHOTO_FILE_URL = pHOTO_FILE_URL;
	}

	public String getFRST_REGIST_DT() {
		return FRST_REGIST_DT;
	}

	public void setFRST_REGIST_DT(String fRST_REGIST_DT) {
		FRST_REGIST_DT = fRST_REGIST_DT;
	}

	public String getLAST_UPDT_DT() {
		return LAST_UPDT_DT;
	}

	public void setLAST_UPDT_DT(String lAST_UPDT_DT) {
		LAST_UPDT_DT = lAST_UPDT_DT;
	}

	public String getDELETE_DT() {
		return DELETE_DT;
	}

	public void setDELETE_DT(String dELETE_DT) {
		DELETE_DT = dELETE_DT;
	}

	public String getCUD_CODE() {
		return CUD_CODE;
	}

	public void setCUD_CODE(String cUD_CODE) {
		CUD_CODE = cUD_CODE;
	}

	public String getPHOTO_FILE_LAST_UPDT_TM() {
		return PHOTO_FILE_LAST_UPDT_TM;
	}

	public void setPHOTO_FILE_LAST_UPDT_TM(String pHOTO_FILE_LAST_UPDT_TM) {
		PHOTO_FILE_LAST_UPDT_TM = pHOTO_FILE_LAST_UPDT_TM;
	}

	public String getPHOTO_FILE_PATH() {
		return PHOTO_FILE_PATH;
	}

	public void setPHOTO_FILE_PATH(String pHOTO_FILE_PATH) {
		PHOTO_FILE_PATH = pHOTO_FILE_PATH;
	}

	public String getPHOTO_FILE_HASH() {
		return PHOTO_FILE_HASH;
	}

	public void setPHOTO_FILE_HASH(String pHOTO_FILE_HASH) {
		PHOTO_FILE_HASH = pHOTO_FILE_HASH;
	}

	public String getORGINL_PHOTO_FILE_PATH() {
		return ORGINL_PHOTO_FILE_PATH;
	}

	public void setORGINL_PHOTO_FILE_PATH(String oRGINL_PHOTO_FILE_PATH) {
		ORGINL_PHOTO_FILE_PATH = oRGINL_PHOTO_FILE_PATH;
	}

	public String getCNTC_INPUT_SE_CODE() {
		return CNTC_INPUT_SE_CODE;
	}

	public void setCNTC_INPUT_SE_CODE(String cNTC_INPUT_SE_CODE) {
		CNTC_INPUT_SE_CODE = cNTC_INPUT_SE_CODE;
	}

	public int getRASMBLY_NUMPR() {
		return RASMBLY_NUMPR;
	}

	public void setRASMBLY_NUMPR(int rASMBLY_NUMPR) {
		RASMBLY_NUMPR = rASMBLY_NUMPR;
	}

	public String getPPRTY_ID() {
		return PPRTY_ID;
	}

	public void setPPRTY_ID(String pPRTY_ID) {
		PPRTY_ID = pPRTY_ID;
	}

	public String getPPRTY_CODE() {
		return PPRTY_CODE;
	}

	public void setPPRTY_CODE(String pPRTY_CODE) {
		PPRTY_CODE = pPRTY_CODE;
	}

	public String getPPRTY_NM() {
		return PPRTY_NM;
	}

	public void setPPRTY_NM(String pPRTY_NM) {
		PPRTY_NM = pPRTY_NM;
	}

	public String getEST_ID() {
		return EST_ID;
	}

	public void setEST_ID(String eST_ID) {
		EST_ID = eST_ID;
	}

	public String getEST_NM() {
		return EST_NM;
	}

	public void setEST_NM(String eST_NM) {
		EST_NM = eST_NM;
	}

	public String getHT_SE_STDCD() {
		return HT_SE_STDCD;
	}

	public void setHT_SE_STDCD(String hT_SE_STDCD) {
		HT_SE_STDCD = hT_SE_STDCD;
	}

	public String getMTGNM_ID() {
		return MTGNM_ID;
	}

	public void setMTGNM_ID(String mTGNM_ID) {
		MTGNM_ID = mTGNM_ID;
	}

	public String getASEMBY_OFCPS_STDCD() {
		return ASEMBY_OFCPS_STDCD;
	}

	public void setASEMBY_OFCPS_STDCD(String aSEMBY_OFCPS_STDCD) {
		ASEMBY_OFCPS_STDCD = aSEMBY_OFCPS_STDCD;
	}

	public String getASEMBY_CN() {
		return ASEMBY_CN;
	}

	public void setASEMBY_CN(String aSEMBY_CN) {
		ASEMBY_CN = aSEMBY_CN;
	}

	public String getHT_SE_STDCD01() {
		return HT_SE_STDCD01;
	}

	public void setHT_SE_STDCD01(String hT_SE_STDCD01) {
		HT_SE_STDCD01 = hT_SE_STDCD01;
	}

	public String getMTGNM_ID01() {
		return MTGNM_ID01;
	}

	public void setMTGNM_ID01(String mTGNM_ID01) {
		MTGNM_ID01 = mTGNM_ID01;
	}

	public String getASEMBY_OFCPS_STDCD01() {
		return ASEMBY_OFCPS_STDCD01;
	}

	public void setASEMBY_OFCPS_STDCD01(String aSEMBY_OFCPS_STDCD01) {
		ASEMBY_OFCPS_STDCD01 = aSEMBY_OFCPS_STDCD01;
	}

	public String getHT_SE_STDCD02() {
		return HT_SE_STDCD02;
	}

	public void setHT_SE_STDCD02(String hT_SE_STDCD02) {
		HT_SE_STDCD02 = hT_SE_STDCD02;
	}

	public String getMTGNM_ID02() {
		return MTGNM_ID02;
	}

	public void setMTGNM_ID02(String mTGNM_ID02) {
		MTGNM_ID02 = mTGNM_ID02;
	}

	public String getASEMBY_OFCPS_STDCD02() {
		return ASEMBY_OFCPS_STDCD02;
	}

	public void setASEMBY_OFCPS_STDCD02(String aSEMBY_OFCPS_STDCD02) {
		ASEMBY_OFCPS_STDCD02 = aSEMBY_OFCPS_STDCD02;
	}

	public String getADRESDETAIL() {
		return ADRESDETAIL;
	}

	public void setADRESDETAIL(String aDRESDETAIL) {
		ADRESDETAIL = aDRESDETAIL;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getEmail3() {
		return email3;
	}

	public void setEmail3(String email3) {
		this.email3 = email3;
	}

	public String getCAREER_MATTER() {
		return CAREER_MATTER;
	}

	public void setCAREER_MATTER(String cAREER_MATTER) {
		CAREER_MATTER = cAREER_MATTER;
	}

	public String getACDMCR_MATTER() {
		return ACDMCR_MATTER;
	}

	public void setACDMCR_MATTER(String aCDMCR_MATTER) {
		ACDMCR_MATTER = aCDMCR_MATTER;
	}

	public String getRASMBLY_ASEMBY_ID() {
		return RASMBLY_ASEMBY_ID;
	}

	public void setRASMBLY_ASEMBY_ID(String rASMBLY_ASEMBY_ID) {
		RASMBLY_ASEMBY_ID = rASMBLY_ASEMBY_ID;
	}

	public String getISVIEW() {
		return ISVIEW;
	}

	public void setISVIEW(String iSVIEW) {
		ISVIEW = iSVIEW;
	}

	public String getRASMBLY_NM() {
		return RASMBLY_NM;
	}

	public void setRASMBLY_NM(String rASMBLY_NM) {
		RASMBLY_NM = rASMBLY_NM;
	}

	public String getACT_AT() {
		return ACT_AT;
	}

	public void setACT_AT(String aCT_AT) {
		ACT_AT = aCT_AT;
	}

	public int getDUPCNT() {
		return DUPCNT;
	}

	public void setDUPCNT(int dUPCNT) {
		DUPCNT = dUPCNT;
	}

	public String[] getACT_RASMBLY_ID() {
		return ACT_RASMBLY_ID;
	}

	public void setACT_RASMBLY_ID(String[] aCT_RASMBLY_ID) {
		ACT_RASMBLY_ID = aCT_RASMBLY_ID;
	}

	public String[] getACT_RASMBLY_NUMPR() {
		return ACT_RASMBLY_NUMPR;
	}

	public void setACT_RASMBLY_NUMPR(String[] aCT_RASMBLY_NUMPR) {
		ACT_RASMBLY_NUMPR = aCT_RASMBLY_NUMPR;
	}

	public String[] getACT_ASEMBY_ID() {
		return ACT_ASEMBY_ID;
	}

	public void setACT_ASEMBY_ID(String[] aCT_ASEMBY_ID) {
		ACT_ASEMBY_ID = aCT_ASEMBY_ID;
	}

	public String[] getACT_ACT_AT() {
		return ACT_ACT_AT;
	}

	public void setACT_ACT_AT(String[] aCT_ACT_AT) {
		ACT_ACT_AT = aCT_ACT_AT;
	}

	public String[] getACT_NOACT_RESN_STDCD() {
		return ACT_NOACT_RESN_STDCD;
	}

	public void setACT_NOACT_RESN_STDCD(String[] aCT_NOACT_RESN_STDCD) {
		ACT_NOACT_RESN_STDCD = aCT_NOACT_RESN_STDCD;
	}

	public String[] getACT_EST_ID() {
		return ACT_EST_ID;
	}

	public void setACT_EST_ID(String[] aCT_EST_ID) {
		ACT_EST_ID = aCT_EST_ID;
	}

	public String[] getACT_PPRTY_CODE() {
		return ACT_PPRTY_CODE;
	}

	public void setACT_PPRTY_CODE(String[] aCT_PPRTY_CODE) {
		ACT_PPRTY_CODE = aCT_PPRTY_CODE;
	}

	public String[] getACT_CAREER_MATTER() {
		return ACT_CAREER_MATTER;
	}

	public void setACT_CAREER_MATTER(String[] aCT_CAREER_MATTER) {
		ACT_CAREER_MATTER = aCT_CAREER_MATTER;
	}

	public String[] getACT_ACDMCR_MATTER() {
		return ACT_ACDMCR_MATTER;
	}

	public void setACT_ACDMCR_MATTER(String[] aCT_ACDMCR_MATTER) {
		ACT_ACDMCR_MATTER = aCT_ACDMCR_MATTER;
	}

	public String[] getACT_GRT() {
		return ACT_GRT;
	}

	public void setACT_GRT(String[] aCT_GRT) {
		ACT_GRT = aCT_GRT;
	}

	public String[] getACT_ASEMBY_CAREER() {
		return ACT_ASEMBY_CAREER;
	}

	public void setACT_ASEMBY_CAREER(String[] aCT_ASEMBY_CAREER) {
		ACT_ASEMBY_CAREER = aCT_ASEMBY_CAREER;
	}

	public String[] getACT_WNPZ_CAREER() {
		return ACT_WNPZ_CAREER;
	}

	public void setACT_WNPZ_CAREER(String[] aCT_WNPZ_CAREER) {
		ACT_WNPZ_CAREER = aCT_WNPZ_CAREER;
	}

	public String getLAST_UPDUSR_ID() {
		return LAST_UPDUSR_ID;
	}

	public void setLAST_UPDUSR_ID(String lAST_UPDUSR_ID) {
		LAST_UPDUSR_ID = lAST_UPDUSR_ID;
	}

	public String[] getACT_CUD_CODE() {
		return ACT_CUD_CODE;
	}

	public void setACT_CUD_CODE(String[] aCT_CUD_CODE) {
		ACT_CUD_CODE = aCT_CUD_CODE;
	}

	public String[] getPOSITION_INFO() {
		return POSITION_INFO;
	}

	public void setPOSITION_INFO(String[] pOSITION_INFO) {
		POSITION_INFO = pOSITION_INFO;
	}

	public String getCOLCT_AT() {
		return COLCT_AT;
	}

	public void setCOLCT_AT(String cOLCT_AT) {
		COLCT_AT = cOLCT_AT;
	}
	
}
