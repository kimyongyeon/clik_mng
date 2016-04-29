package clikmng.nanet.go.kr.mdm.model;

public class MdmTnsrAsmblyApndxVO {
	
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

	private String APNDX_FILE_NM   = ""; // 부록_파일_이름
	private String RASMBLY_ID      = ""; // 지방의회_ID
	private int    RASMBLY_SESN    = 0;  // 지방의회_회기
	private String MTGNM_ID        = ""; // 회의명_ID
	private int    RASMBLY_NUMPR   = 0;  // 지방의회_대수
	private int    MINTS_ODR       = 0;  // 회의록_차수
	private String APNDX_ID        = ""; // 부록_ID
	private String APNDX_FILE_URL  = ""; // 부록_파일_URL
	private String APNDX_FILE_LAST_UPDT_TM = ""; //	부록_파일_최종수정일시
	private String FRST_REGIST_DT  = ""; // 최초등록일시		
	private String LAST_UPDT_DT    = ""; //최종수정일시		
	private String DELETE_DT       = ""; // 삭제일시		
	private String CUD_CODE        = ""; // CUD_코드		
	private String SESN_SE_STDCD   = ""; // 회기_구분_표준코드
	private int    MINTS_SN        = 0;  // 회의록_일련번호	
	private String APNDX_FILE_PATH = ""; // 부록_파일_경로	
	private String APNDX_FILE_HASH = ""; // 부록_파일_HASH
	
	private String ODR_NM = ""; // 차수
	private String MTG_DE = ""; // 회의일
	private String CNTC_INPUT_SE_CODE = "";
	private String RASMBLY_NM = "";
	
	public String getAPNDX_FILE_NM() {
		return APNDX_FILE_NM;
	}
	public void setAPNDX_FILE_NM(String aPNDX_FILE_NM) {
		APNDX_FILE_NM = aPNDX_FILE_NM;
	}
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
	public String getAPNDX_ID() {
		return APNDX_ID;
	}
	public void setAPNDX_ID(String aPNDX_ID) {
		APNDX_ID = aPNDX_ID;
	}
	public String getAPNDX_FILE_URL() {
		return APNDX_FILE_URL;
	}
	public void setAPNDX_FILE_URL(String aPNDX_FILE_URL) {
		APNDX_FILE_URL = aPNDX_FILE_URL;
	}
	public String getAPNDX_FILE_LAST_UPDT_TM() {
		return APNDX_FILE_LAST_UPDT_TM;
	}
	public void setAPNDX_FILE_LAST_UPDT_TM(String aPNDX_FILE_LAST_UPDT_TM) {
		APNDX_FILE_LAST_UPDT_TM = aPNDX_FILE_LAST_UPDT_TM;
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
	public int getMINTS_SN() {
		return MINTS_SN;
	}
	public void setMINTS_SN(int mINTS_SN) {
		MINTS_SN = mINTS_SN;
	}
	public String getAPNDX_FILE_PATH() {
		return APNDX_FILE_PATH;
	}
	public void setAPNDX_FILE_PATH(String aPNDX_FILE_PATH) {
		APNDX_FILE_PATH = aPNDX_FILE_PATH;
	}
	public String getAPNDX_FILE_HASH() {
		return APNDX_FILE_HASH;
	}
	public void setAPNDX_FILE_HASH(String aPNDX_FILE_HASH) {
		APNDX_FILE_HASH = aPNDX_FILE_HASH;
	}
	public String getODR_NM() {
		return ODR_NM;
	}
	public void setODR_NM(String oDR_NM) {
		ODR_NM = oDR_NM;
	}
	public String getMTG_DE() {
		return MTG_DE;
	}
	public void setMTG_DE(String mTG_DE) {
		MTG_DE = mTG_DE;
	}
	public String getCNTC_INPUT_SE_CODE() {
		return CNTC_INPUT_SE_CODE;
	}
	public void setCNTC_INPUT_SE_CODE(String cNTC_INPUT_SE_CODE) {
		CNTC_INPUT_SE_CODE = cNTC_INPUT_SE_CODE;
	}
	public String getRASMBLY_NM() {
		return RASMBLY_NM;
	}
	public void setRASMBLY_NM(String rASMBLY_NM) {
		RASMBLY_NM = rASMBLY_NM;
	}
}
