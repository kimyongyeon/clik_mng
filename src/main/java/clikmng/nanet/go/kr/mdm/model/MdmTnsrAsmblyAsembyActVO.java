package clikmng.nanet.go.kr.mdm.model;

public class MdmTnsrAsmblyAsembyActVO {
	private String RASMBLY_NUMPR    = "";  // 지방의회_대수
	private String RASMBLY_ID       = ""; // 지방의회_ID
	private String ACT_AT           = ""; // 활동_여부
	private String NOACT_RESN_STDCD = ""; // 미활동_사유_코드
	private String EST_ID           = ""; // 선거구_ID
	private String PPRTY_CODE       = ""; // 정당_코드
	private String CAREER_MATTER    = ""; // 경력_사항
	private String ACDMCR_MATTER    = ""; // 학력_사항	
	private String GRT              = ""; // 인사말
	private String ASEMBY_CAREER    = ""; // 의원_경력
	private String WNPZ_CAREER      = ""; // 수상_경력
	private String ASEMBY_ID        = ""; // 의원_ID
	private String FRST_REGIST_DT   = ""; // 최초등록일시
	private String LAST_UPDT_DT     = ""; // 최종수정일시
	private String DELETE_DT        = ""; // 삭제일시
	private String CUD_CODE         = ""; // CUD_코드	
	private String CNTC_INPUT_SE_CODE = ""; // 수집유형
	private String PHOTO_FILE_PATH    = "";
	
	private String ASEMBY_NM        = ""; // 의원명
	private String EST_NM           = ""; // 선거구
	private String PPRTY_NM         = ""; // 지역구
	private String PPRTY_ID         = ""; // 소속정당 ID
	private String RASMBLY_ASEMBY_ID = "";
	private String ISVIEW            = "";
	private String ASEMBY_CN         = ""; 
	private String RASMBLY_NM        = ""; // 지방의회명
	private String BRTC_CODE         = ""; // 지역코드
	private int    DUPCNT            = 0;
	
	private String TWITTER			= ""; 
	private String FACEBOOK			= ""; 
	private String HMPG				= ""; // 홈페이지
	private String BRTHDY			= ""; // 생년월일
	
	/**
	 * 2015.04.14
	 * 검색 조건 추가
	 */
	private String schLoAsmTyCode	= ""; //의회선택(광역, 기초)
	private String schRegion		= ""; //지역
	private String schLoAsmCode		= ""; //의회코드
	private String schRasmblyNumpr	= ""; //대수
	
	
	public String getSchLoAsmTyCode() {
		return schLoAsmTyCode;
	}
	public void setSchLoAsmTyCode(String schLoAsmTyCode) {
		this.schLoAsmTyCode = schLoAsmTyCode;
	}
	public String getSchRegion() {
		return schRegion;
	}
	public void setSchRegion(String schRegion) {
		this.schRegion = schRegion;
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
	public String getBRTHDY() {
		return BRTHDY;
	}
	public void setBRTHDY(String bRTHDY) {
		BRTHDY = bRTHDY;
	}
	public String getRASMBLY_NUMPR() {
		return RASMBLY_NUMPR;
	}
	public void setRASMBLY_NUMPR(String rASMBLY_NUMPR) {
		RASMBLY_NUMPR = rASMBLY_NUMPR;
	}
	public String getRASMBLY_ID() {
		return RASMBLY_ID;
	}
	public void setRASMBLY_ID(String rASMBLY_ID) {
		RASMBLY_ID = rASMBLY_ID;
	}
	public String getACT_AT() {
		return ACT_AT;
	}
	public void setACT_AT(String aCT_AT) {
		ACT_AT = aCT_AT;
	}
	public String getNOACT_RESN_STDCD() {
		return NOACT_RESN_STDCD;
	}
	public void setNOACT_RESN_STDCD(String nOACT_RESN_STDCD) {
		NOACT_RESN_STDCD = nOACT_RESN_STDCD;
	}
	public String getEST_ID() {
		return EST_ID;
	}
	public void setEST_ID(String eST_ID) {
		EST_ID = eST_ID;
	}
	public String getPPRTY_CODE() {
		return PPRTY_CODE;
	}
	public void setPPRTY_CODE(String pPRTY_CODE) {
		PPRTY_CODE = pPRTY_CODE;
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
	public String getGRT() {
		return GRT;
	}
	public void setGRT(String gRT) {
		GRT = gRT;
	}
	public String getASEMBY_CAREER() {
		return ASEMBY_CAREER;
	}
	public void setASEMBY_CAREER(String aSEMBY_CAREER) {
		ASEMBY_CAREER = aSEMBY_CAREER;
	}
	public String getWNPZ_CAREER() {
		return WNPZ_CAREER;
	}
	public void setWNPZ_CAREER(String wNPZ_CAREER) {
		WNPZ_CAREER = wNPZ_CAREER;
	}
	public String getASEMBY_ID() {
		return ASEMBY_ID;
	}
	public void setASEMBY_ID(String aSEMBY_ID) {
		ASEMBY_ID = aSEMBY_ID;
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
	public String getCNTC_INPUT_SE_CODE() {
		return CNTC_INPUT_SE_CODE;
	}
	public void setCNTC_INPUT_SE_CODE(String cNTC_INPUT_SE_CODE) {
		CNTC_INPUT_SE_CODE = cNTC_INPUT_SE_CODE;
	}
	public String getPHOTO_FILE_PATH() {
		return PHOTO_FILE_PATH;
	}
	public void setPHOTO_FILE_PATH(String pHOTO_FILE_PATH) {
		PHOTO_FILE_PATH = pHOTO_FILE_PATH;
	}
	public String getISVIEW() {
		return ISVIEW;
	}
	public void setISVIEW(String iSVIEW) {
		ISVIEW = iSVIEW;
	}
	public String getASEMBY_CN() {
		return ASEMBY_CN;
	}
	public void setASEMBY_CN(String aSEMBY_CN) {
		ASEMBY_CN = aSEMBY_CN;
	}
	public String getASEMBY_NM() {
		return ASEMBY_NM;
	}
	public void setASEMBY_NM(String aSEMBY_NM) {
		ASEMBY_NM = aSEMBY_NM;
	}
	public String getEST_NM() {
		return EST_NM;
	}
	public void setEST_NM(String eST_NM) {
		EST_NM = eST_NM;
	}
	public String getPPRTY_NM() {
		return PPRTY_NM;
	}
	public void setPPRTY_NM(String pPRTY_NM) {
		PPRTY_NM = pPRTY_NM;
	}
	public String getPPRTY_ID() {
		return PPRTY_ID;
	}
	public void setPPRTY_ID(String pPRTY_ID) {
		PPRTY_ID = pPRTY_ID;
	}
	public String getRASMBLY_ASEMBY_ID() {
		return RASMBLY_ASEMBY_ID;
	}
	public void setRASMBLY_ASEMBY_ID(String rASMBLY_ASEMBY_ID) {
		RASMBLY_ASEMBY_ID = rASMBLY_ASEMBY_ID;
	}
	public String getRASMBLY_NM() {
		return RASMBLY_NM;
	}
	public void setRASMBLY_NM(String rASMBLY_NM) {
		RASMBLY_NM = rASMBLY_NM;
	}
	public String getBRTC_CODE() {
		return BRTC_CODE;
	}
	public void setBRTC_CODE(String bRTC_CODE) {
		BRTC_CODE = bRTC_CODE;
	}
	public int getDUPCNT() {
		return DUPCNT;
	}
	public void setDUPCNT(int dUPCNT) {
		DUPCNT = dUPCNT;
	}
}
