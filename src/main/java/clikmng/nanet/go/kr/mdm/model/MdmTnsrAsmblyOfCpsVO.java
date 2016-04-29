package clikmng.nanet.go.kr.mdm.model;

public class MdmTnsrAsmblyOfCpsVO {

	private String HT_SE_STDCD = "";    // 반기_구분_표준코드	
	private String ASEMBY_OFCPS_STDCD = ""; // 의원_직위_표준코드	
	private String MTGNM_ID = "";       // 회의명_ID
	private String ASEMBY_ID = "";      // 의원_ID
	private String RASMBLY_ID = "";     // 지방의회_ID
	private int RASMBLY_NUMPR = 0;      // 지방의회_대수	
	private String FRST_REGIST_DT = ""; // 최초등록일시	
	private String LAST_UPDT_DT	= "";   // 최종수정일시
	private String DELETE_DT = "";      // 삭제일시
	private String CUD_CODE = "";       // CUD_코드
	
	public String getHT_SE_STDCD() {
		return HT_SE_STDCD;
	}
	public void setHT_SE_STDCD(String hT_SE_STDCD) {
		HT_SE_STDCD = hT_SE_STDCD;
	}
	public String getASEMBY_OFCPS_STDCD() {
		return ASEMBY_OFCPS_STDCD;
	}
	public void setASEMBY_OFCPS_STDCD(String aSEMBY_OFCPS_STDCD) {
		ASEMBY_OFCPS_STDCD = aSEMBY_OFCPS_STDCD;
	}
	public String getMTGNM_ID() {
		return MTGNM_ID;
	}
	public void setMTGNM_ID(String mTGNM_ID) {
		MTGNM_ID = mTGNM_ID;
	}
	public String getASEMBY_ID() {
		return ASEMBY_ID;
	}
	public void setASEMBY_ID(String aSEMBY_ID) {
		ASEMBY_ID = aSEMBY_ID;
	}
	public String getRASMBLY_ID() {
		return RASMBLY_ID;
	}
	public void setRASMBLY_ID(String rASMBLY_ID) {
		RASMBLY_ID = rASMBLY_ID;
	}
	public int getRASMBLY_NUMPR() {
		return RASMBLY_NUMPR;
	}
	public void setRASMBLY_NUMPR(int rASMBLY_NUMPR) {
		RASMBLY_NUMPR = rASMBLY_NUMPR;
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
}
