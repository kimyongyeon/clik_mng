package clikmng.nanet.go.kr.mdm.model;

public class MdmTnsrAsmblyMintsVO {

	/**
	 * 
	 * 회의록 정보를 처리하는 VO 클래스
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
	 *   014.10.31  최태광          최초 생성
	 *
	 * </pre>
	 */
	
	private String RASMBLY_ID = "";
	private int    RASMBLY_SESN = 0;
	private String MTGNM_ID = "";
	private String MTG_DE = "";
	private String ORGINL_FILE_NM = "";
	private String MINTS_HTML = "";
	private String BEGIN_TM = "";
	private String END_TM = "";
	private String OTHBC_STDCD = "";
	private int    RASMBLY_NUMPR = 0;
	private int    MINTS_ODR = 0;
	private String ORGINL_FILE_URL = "";
	private String FRST_REGIST_DT = "";
	private String LAST_UPDT_DT = "";
	private String DELETE_DT = "";
	private String CUD_CODE = "";
	private String SESN_SE_STDCD = "";
	private String CLOSE_AT = "";
	private String ODR_NM = "";
	private String ORGINL_FILE_LAST_UPDT_TM = "";
	private int    MINTS_SN = 0;
	private String ORGINL_FILE_HASH = "";
	private String MINTS_FILE_PATH = "";
	private String RASMBLY_MINTS_ID = "";
	private String MINTS_PDF_FILE_PATH = "";
	private String DOC_CNVR_STTU_CODE = "";
	private String DOC_CNVR_RESULT_MSSAGE = "";
	private String CNTC_INPUT_SE_CODE = "";
	private String ISVIEW = "";
	private String MINTS_CN = "";
	private String MINTS_PDF_FILE_NM = "";
	private String MTGNM = "";
	private String INQUIRY_NM = "";

	private String CODE_ID = "";
	private String RASMBLY_NM = "";

	private int    DUPCNT  = 0;
	private int    FILECNT     = 0;
	private int    FILEFLRCNT  = 0;
	private int    FILESUCCCNT = 0;

	private String APPFILEEXT = "";
	private String MINTSFILEEXT = "";

	private int pageNum = 0;
	private int firstRecord = 0;
	private int lastRecord  = 0;
	private String LAST_UPDUSR_ID = "";
	
	private String COLCT_AT;
	
	public String getRASMBLY_ID() {
		return RASMBLY_ID;
	}
	public void setRASMBLY_ID(String rASMBLY_ID) {
		RASMBLY_ID = rASMBLY_ID;
	}
	public int getRASMBLY_SESN() {
		return RASMBLY_SESN;
	}
	public void setRASMBLY_SESN(int rASMBLY_SESN) {
		RASMBLY_SESN = rASMBLY_SESN;
	}
	public String getMTGNM_ID() {
		return MTGNM_ID;
	}
	public void setMTGNM_ID(String mTGNM_ID) {
		MTGNM_ID = mTGNM_ID;
	}
	public String getMTG_DE() {
		return MTG_DE;
	}
	public void setMTG_DE(String mTG_DE) {
		MTG_DE = mTG_DE;
	}
	public String getORGINL_FILE_NM() {
		return ORGINL_FILE_NM;
	}
	public void setORGINL_FILE_NM(String oRGINL_FILE_NM) {
		ORGINL_FILE_NM = oRGINL_FILE_NM;
	}
	public String getMINTS_HTML() {
		return MINTS_HTML;
	}
	public void setMINTS_HTML(String mINTS_HTML) {
		MINTS_HTML = mINTS_HTML;
	}
	public String getBEGIN_TM() {
		return BEGIN_TM;
	}
	public void setBEGIN_TM(String bEGIN_TM) {
		BEGIN_TM = bEGIN_TM;
	}
	public String getEND_TM() {
		return END_TM;
	}
	public void setEND_TM(String eND_TM) {
		END_TM = eND_TM;
	}
	public String getOTHBC_STDCD() {
		return OTHBC_STDCD;
	}
	public void setOTHBC_STDCD(String oTHBC_STDCD) {
		OTHBC_STDCD = oTHBC_STDCD;
	}
	public int getRASMBLY_NUMPR() {
		return RASMBLY_NUMPR;
	}
	public void setRASMBLY_NUMPR(int rASMBLY_NUMPR) {
		RASMBLY_NUMPR = rASMBLY_NUMPR;
	}
	public int getMINTS_ODR() {
		return MINTS_ODR;
	}
	public void setMINTS_ODR(int mINTS_ODR) {
		MINTS_ODR = mINTS_ODR;
	}
	public String getORGINL_FILE_URL() {
		return ORGINL_FILE_URL;
	}
	public void setORGINL_FILE_URL(String oRGINL_FILE_URL) {
		ORGINL_FILE_URL = oRGINL_FILE_URL;
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
	public String getSESN_SE_STDCD() {
		return SESN_SE_STDCD;
	}
	public void setSESN_SE_STDCD(String sESN_SE_STDCD) {
		SESN_SE_STDCD = sESN_SE_STDCD;
	}
	public String getCLOSE_AT() {
		return CLOSE_AT;
	}
	public void setCLOSE_AT(String cLOSE_AT) {
		CLOSE_AT = cLOSE_AT;
	}
	public String getODR_NM() {
		return ODR_NM;
	}
	public void setODR_NM(String oDR_NM) {
		ODR_NM = oDR_NM;
	}
	public String getORGINL_FILE_LAST_UPDT_TM() {
		return ORGINL_FILE_LAST_UPDT_TM;
	}
	public void setORGINL_FILE_LAST_UPDT_TM(String oRGINL_FILE_LAST_UPDT_TM) {
		ORGINL_FILE_LAST_UPDT_TM = oRGINL_FILE_LAST_UPDT_TM;
	}
	public int getMINTS_SN() {
		return MINTS_SN;
	}
	public void setMINTS_SN(int mINTS_SN) {
		MINTS_SN = mINTS_SN;
	}
	public String getORGINL_FILE_HASH() {
		return ORGINL_FILE_HASH;
	}
	public void setORGINL_FILE_HASH(String oRGINL_FILE_HASH) {
		ORGINL_FILE_HASH = oRGINL_FILE_HASH;
	}
	public String getMINTS_FILE_PATH() {
		return MINTS_FILE_PATH;
	}
	public void setMINTS_FILE_PATH(String mINTS_FILE_PATH) {
		MINTS_FILE_PATH = mINTS_FILE_PATH;
	}
	public String getRASMBLY_MINTS_ID() {
		return RASMBLY_MINTS_ID;
	}
	public void setRASMBLY_MINTS_ID(String rASMBLY_MINTS_ID) {
		RASMBLY_MINTS_ID = rASMBLY_MINTS_ID;
	}
	public String getDOC_CNVR_STTU_CODE() {
		return DOC_CNVR_STTU_CODE;
	}
	public void setDOC_CNVR_STTU_CODE(String dOC_CNVR_STTU_CODE) {
		DOC_CNVR_STTU_CODE = dOC_CNVR_STTU_CODE;
	}
	public String getDOC_CNVR_RESULT_MSSAGE() {
		return DOC_CNVR_RESULT_MSSAGE;
	}
	public void setDOC_CNVR_RESULT_MSSAGE(String dOC_CNVR_RESULT_MSSAGE) {
		DOC_CNVR_RESULT_MSSAGE = dOC_CNVR_RESULT_MSSAGE;
	}
	public String getMINTS_PDF_FILE_PATH() {
		return MINTS_PDF_FILE_PATH;
	}
	public void setMINTS_PDF_FILE_PATH(String mINTS_PDF_FILE_PATH) {
		MINTS_PDF_FILE_PATH = mINTS_PDF_FILE_PATH;
	}
	public String getCNTC_INPUT_SE_CODE() {
		return CNTC_INPUT_SE_CODE;
	}
	public void setCNTC_INPUT_SE_CODE(String cNTC_INPUT_SE_CODE) {
		CNTC_INPUT_SE_CODE = cNTC_INPUT_SE_CODE;
	}
	public String getISVIEW() {
		return ISVIEW;
	}
	public void setISVIEW(String iSVIEW) {
		ISVIEW = iSVIEW;
	}
	public String getMINTS_CN() {
		return MINTS_CN;
	}
	public void setMINTS_CN(String mINTS_CN) {
		MINTS_CN = mINTS_CN;
	}
	public String getMINTS_PDF_FILE_NM() {
		return MINTS_PDF_FILE_NM;
	}
	public void setMINTS_PDF_FILE_NM(String mINTS_PDF_FILE_NM) {
		MINTS_PDF_FILE_NM = mINTS_PDF_FILE_NM;
	}
	public String getMTGNM() {
		return MTGNM;
	}
	public void setMTGNM(String mTGNM) {
		MTGNM = mTGNM;
	}
	public String getINQUIRY_NM() {
		return INQUIRY_NM;
	}
	public void setINQUIRY_NM(String iNQUIRY_NM) {
		INQUIRY_NM = iNQUIRY_NM;
	}
	public String getCODE_ID() {
		return CODE_ID;
	}
	public void setCODE_ID(String cODE_ID) {
		CODE_ID = cODE_ID;
	}
	public String getRASMBLY_NM() {
		return RASMBLY_NM;
	}
	public void setRASMBLY_NM(String rASMBLY_NM) {
		RASMBLY_NM = rASMBLY_NM;
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
	public String getAPPFILEEXT() {
		return APPFILEEXT;
	}
	public void setAPPFILEEXT(String aPPFILEEXT) {
		APPFILEEXT = aPPFILEEXT;
	}
	public String getMINTSFILEEXT() {
		return MINTSFILEEXT;
	}
	public void setMINTSFILEEXT(String mINTSFILEEXT) {
		MINTSFILEEXT = mINTSFILEEXT;
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
	public String getLAST_UPDUSR_ID() {
		return LAST_UPDUSR_ID;
	}
	public void setLAST_UPDUSR_ID(String lAST_UPDUSR_ID) {
		LAST_UPDUSR_ID = lAST_UPDUSR_ID;
	}
	public String getCOLCT_AT() {
		return COLCT_AT;
	}
	public void setCOLCT_AT(String cOLCT_AT) {
		COLCT_AT = cOLCT_AT;
	}
}
