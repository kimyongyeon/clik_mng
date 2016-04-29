package clikmng.nanet.go.kr.mdm.model;

public class MdmTnsrAsmblyMtrVO {
	
	/**
	 * 
	 * 회의록 안건 정보를 처리하는 VO 클래스
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
	
	private String MTR_SJ         = ""; // 안건_제목
	private String RASMBLY_ID     = ""; // 지방의회_ID	
	private int    RASMBLY_SESN   = 0;  // 지방의회_회기	
	private String MTGNM_ID       = ""; // 회의명_ID	
	private int    RASMBLY_NUMPR  = 0;  // 지방의회_대수	
	private int    MINTS_ODR      = 0;  // 회의록_차수	
	private String MTR_ID         = ""; // 안건_ID	
	private int    MTR_SN         = 0;  // 안건_순번	
	private String FRST_REGIST_DT = ""; // 최초등록일시
	private String LAST_UPDT_DT   = ""; // 최종수정일시		
	private String DELETE_DT      = ""; // 삭제일시		
	private String CUD_CODE       = ""; // CUD_코드		
	private String SESN_SE_STDCD  = ""; // 회기_구분_표준코드	
	private int    MINTS_SN       = 0;  // 회의록_일련번호
	
	public String getMTR_SJ() {
		return MTR_SJ;
	}
	public void setMTR_SJ(String mTR_SJ) {
		MTR_SJ = mTR_SJ;
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
	public String getMTR_ID() {
		return MTR_ID;
	}
	public void setMTR_ID(String mTR_ID) {
		MTR_ID = mTR_ID;
	}
	public int getMTR_SN() {
		return MTR_SN;
	}
	public void setMTR_SN(int mTR_SN) {
		MTR_SN = mTR_SN;
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
	
}
