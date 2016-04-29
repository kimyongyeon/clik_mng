package clikmng.nanet.go.kr.mdm.model;

public class MdmPolicyInfoVO {
	/**
	 * 
	 * 지방정책정보를 처리하는 VO 클래스
	 * @author 회의록 개발 최태광
	 * @since 2014.10.31
	 * @version 1.0
	 * @see
	 *
	 * <pre>
	 * << 개정이력(Modification Information) >>
	 *   
	 *   수정일      수정자         수정내용
	 *  -------    --------    --------------------
	 *   014.11.06  최태광          최초 생성
	 *
	 * </pre>
	 */
	
	private String OUTBBS_CN   = "";
	private String EXTID       = ""; // 기본키(문서아이디값)
	private String REGDATE     = ""; // 수집일
	private String SEEDID      = "";  // 게시판아이디		
	private String SITEID      = "";  // 사이트아이디	
	private String SEEDNM      = ""; // 게시판명	
	private String SITENM      = ""; // 사이트명(기관명)
	private String TITLE       = ""; // 제목
	private String CONTENT     = ""; // 내용
	private String RASMBLY_ID  = ""; // 회기
	private String DURING      = ""; // 기간
	private String CMIT        = ""; // 위원회
	private String URL         = ""; // 문서링크URL
	private String CDATE       = ""; // 문서작성일
	private String WRITER      = ""; // 문서작성자
	private String SERIES      = ""; // 총서
	private String FILECONTNET = ""; // 파일내용TXT
	private String DUPLICATION = ""; // 중복키값(HASH값)
	private String DUPTITLE    = ""; // 제목중복여부판단HASH값
	private String DUPCONTENT  = ""; // 내용중복여부판단HASH값
	private String DUPFILE     = ""; // 파일내용중복여부판단HASH값
	private String REGION      = ""; // 지역코드
	private String DOCTYPE     = ""; // 문서유형코드
	private String CATEGORYID  = ""; // 카테고리아이디
	private String UPDT        = ""; //	수정일
	private String DELDT       = ""; // 삭제일
	private String SEEDURL     = ""; // 게시판URL
	private String CUD_CODE    = ""; // 생성/수정/삭제
	private String AUTO        = ""; // 자동 VIEW 여부
	private String ATTACHCNT   = "0"; // 첨부파일 수
	private int    CKCOUNT     = 0;
	private int    DUPCNT      = 0; // 
	private int    FILECNT     = 0; // 
	private int    FILEFLRCNT  = 0;
	private int    FILESUCCCNT = 0;

	private String FILEEXT     = "";
	private String ISVIEW      = "";
	private String MODE        = "";
	
	private String MDMREGIONNM = "";
	private String MDMSITENM   = "";
	private String MDMSEEDNM   = "";
	private String MDMDOCTYPENM = "";
	
	private String ORG_1 = "";
	private String ORG_2 = "";
	private String ORG_3 = "";
	private String ORG_1NM = "";
	private String ORG_2NM = "";
	private String ORG_3NM = "";
	
	private String SITEURL = "";
	private String SEEDNAME = "";
	
	private String WORKTYPE = "";
	
	private String EXTRACTHTML = "";
	private String LAST_UPDUSR_ID = "";
	
	public String getORG_1NM() {
		return ORG_1NM;
	}
	public void setORG_1NM(String oRG_1NM) {
		ORG_1NM = oRG_1NM;
	}
	public String getORG_2NM() {
		return ORG_2NM;
	}
	public void setORG_2NM(String oRG_2NM) {
		ORG_2NM = oRG_2NM;
	}
	public String getORG_3NM() {
		return ORG_3NM;
	}
	public void setORG_3NM(String oRG_3NM) {
		ORG_3NM = oRG_3NM;
	}
	public String getWORKTYPE() {
		return WORKTYPE;
	}
	public void setWORKTYPE(String wORKTYPE) {
		WORKTYPE = wORKTYPE;
	}
	public String getSEEDNAME() {
		return SEEDNAME;
	}
	public void setSEEDNAME(String sEEDNAME) {
		SEEDNAME = sEEDNAME;
	}
	public String getOUTBBS_CN() {
		return OUTBBS_CN;
	}
	public void setOUTBBS_CN(String oUTBBS_CN) {
		OUTBBS_CN = oUTBBS_CN;
	}
	public String getEXTID() {
		return EXTID;
	}
	public void setEXTID(String eXTID) {
		EXTID = eXTID;
	}
	public String getREGDATE() {
		return REGDATE;
	}
	public void setREGDATE(String rEGDATE) {
		REGDATE = rEGDATE;
	}
	public String getSEEDID() {
		return SEEDID;
	}
	public void setSEEDID(String sEEDID) {
		SEEDID = sEEDID;
	}
	public String getSITEID() {
		return SITEID;
	}
	public void setSITEID(String sITEID) {
		SITEID = sITEID;
	}
	public String getSEEDNM() {
		return SEEDNM;
	}
	public void setSEEDNM(String sEEDNM) {
		SEEDNM = sEEDNM;
	}
	public String getSITENM() {
		return SITENM;
	}
	public void setSITENM(String sITENM) {
		SITENM = sITENM;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getRASMBLY_ID() {
		return RASMBLY_ID;
	}
	public void setRASMBLY_ID(String rASMBLY_ID) {
		RASMBLY_ID = rASMBLY_ID;
	}
	public String getDURING() {
		return DURING;
	}
	public void setDURING(String dURING) {
		DURING = dURING;
	}
	public String getCMIT() {
		return CMIT;
	}
	public void setCMIT(String cMIT) {
		CMIT = cMIT;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getCDATE() {
		return CDATE;
	}
	public void setCDATE(String cDATE) {
		CDATE = cDATE;
	}
	public String getWRITER() {
		return WRITER;
	}
	public void setWRITER(String wRITER) {
		WRITER = wRITER;
	}
	public String getSERIES() {
		return SERIES;
	}
	public void setSERIES(String sERIES) {
		SERIES = sERIES;
	}
	public String getFILECONTNET() {
		return FILECONTNET;
	}
	public void setFILECONTNET(String fILECONTNET) {
		FILECONTNET = fILECONTNET;
	}
	public String getDUPLICATION() {
		return DUPLICATION;
	}
	public void setDUPLICATION(String dUPLICATION) {
		DUPLICATION = dUPLICATION;
	}
	public String getDUPTITLE() {
		return DUPTITLE;
	}
	public void setDUPTITLE(String dUPTITLE) {
		DUPTITLE = dUPTITLE;
	}
	public String getDUPCONTENT() {
		return DUPCONTENT;
	}
	public void setDUPCONTENT(String dUPCONTENT) {
		DUPCONTENT = dUPCONTENT;
	}
	public String getDUPFILE() {
		return DUPFILE;
	}
	public void setDUPFILE(String dUPFILE) {
		DUPFILE = dUPFILE;
	}
	public String getREGION() {
		return REGION;
	}
	public void setREGION(String rEGION) {
		REGION = rEGION;
	}
	public String getDOCTYPE() {
		return DOCTYPE;
	}
	public void setDOCTYPE(String dOCTYPE) {
		DOCTYPE = dOCTYPE;
	}
	public String getCATEGORYID() {
		return CATEGORYID;
	}
	public void setCATEGORYID(String cATEGORYID) {
		CATEGORYID = cATEGORYID;
	}
	public String getUPDT() {
		return UPDT;
	}
	public void setUPDT(String uPDT) {
		UPDT = uPDT;
	}
	public String getDELDT() {
		return DELDT;
	}
	public void setDELDT(String dELDT) {
		DELDT = dELDT;
	}
	public String getSEEDURL() {
		return SEEDURL;
	}
	public void setSEEDURL(String sEEDURL) {
		SEEDURL = sEEDURL;
	}
	public String getCUD_CODE() {
		return CUD_CODE;
	}
	public void setCUD_CODE(String cUD_CODE) {
		CUD_CODE = cUD_CODE;
	}
	public String getAUTO() {
		return AUTO;
	}
	public void setAUTO(String aUTO) {
		AUTO = aUTO;
	}
	public String getATTACHCNT() {
		return ATTACHCNT;
	}
	public void setATTACHCNT(String aTTACHCNT) {
		ATTACHCNT = aTTACHCNT;
	}
	public int getCKCOUNT() {
		return CKCOUNT;
	}
	public void setCKCOUNT(int cKCOUNT) {
		CKCOUNT = cKCOUNT;
	}
	public int getDUPCNT() {
		return DUPCNT;
	}
	public void setDUPCNT(int dUPCNT) {
		DUPCNT = dUPCNT;
	}
	public int getFILECNT() {
		return FILECNT;
	}
	public void setFILECNT(int fILECNT) {
		FILECNT = fILECNT;
	}
	public int getFILEFLRCNT() {
		return FILEFLRCNT;
	}
	public void setFILEFLRCNT(int fILEFLRCNT) {
		FILEFLRCNT = fILEFLRCNT;
	}
	public int getFILESUCCCNT() {
		return FILESUCCCNT;
	}
	public void setFILESUCCCNT(int fILESUCCCNT) {
		FILESUCCCNT = fILESUCCCNT;
	}
	public String getFILEEXT() {
		return FILEEXT;
	}
	public void setFILEEXT(String fILEEXT) {
		FILEEXT = fILEEXT;
	}
	public String getISVIEW() {
		return ISVIEW;
	}
	public void setISVIEW(String iSVIEW) {
		ISVIEW = iSVIEW;
	}
	public String getMODE() {
		return MODE;
	}
	public void setMODE(String mODE) {
		MODE = mODE;
	}
	public String getMDMREGIONNM() {
		return MDMREGIONNM;
	}
	public void setMDMREGIONNM(String mDMREGIONNM) {
		MDMREGIONNM = mDMREGIONNM;
	}
	public String getMDMSITENM() {
		return MDMSITENM;
	}
	public void setMDMSITENM(String mDMSITENM) {
		MDMSITENM = mDMSITENM;
	}
	public String getMDMSEEDNM() {
		return MDMSEEDNM;
	}
	public void setMDMSEEDNM(String mDMSEEDNM) {
		MDMSEEDNM = mDMSEEDNM;
	}
	public String getMDMDOCTYPENM() {
		return MDMDOCTYPENM;
	}
	public void setMDMDOCTYPENM(String mDMDOCTYPENM) {
		MDMDOCTYPENM = mDMDOCTYPENM;
	}
	public String getORG_1() {
		return ORG_1;
	}
	public void setORG_1(String oRG_1) {
		ORG_1 = oRG_1;
	}
	public String getORG_2() {
		return ORG_2;
	}
	public void setORG_2(String oRG_2) {
		ORG_2 = oRG_2;
	}
	public String getORG_3() {
		return ORG_3;
	}
	public void setORG_3(String oRG_3) {
		ORG_3 = oRG_3;
	}
	public String getSITEURL() {
		return SITEURL;
	}
	public void setSITEURL(String sITEURL) {
		SITEURL = sITEURL;
	}
	public String getEXTRACTHTML() {
		return EXTRACTHTML;
	}
	public void setEXTRACTHTML(String eXTRACTHTML) {
		EXTRACTHTML = eXTRACTHTML;
	}
	public String getLAST_UPDUSR_ID() {
		return LAST_UPDUSR_ID;
	}
	public void setLAST_UPDUSR_ID(String lAST_UPDUSR_ID) {
		LAST_UPDUSR_ID = lAST_UPDUSR_ID;
	}
	
}
