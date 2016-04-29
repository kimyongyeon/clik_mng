package clikmng.nanet.go.kr.rlm.service;

/** 지방의회 */
public class RasmblyVO {

	private String rnum;
	private int totcnt;
	private String rasmbly_id;
	private String rasmbly_nm;
	private String rasmbly_site_url;
	private String rasmbly_sttus_code;
	private String rasmbly_sttus_nm;
	private String frst_regist_dt;
	private String last_updt_dt;
	private String delete_dt;
	private String delete_at;
	private String password;
	private String server_ip;
	private String api_crtfc_key;
	private String rasmbly_area_code;
	private String rasmbly_site_mints_url;
	private String rasmbly_site_bill_url;
	private String rasmbly_site_asemby_url;
	private String isview;
	
	private String brtcCode;
	private String loasmCode;
	private String insttClCode;
	private String insttClCodeNm;
	private String fInsttClCode;
	private String fInsttClCodeNm;
	
	//승격 전 지방의회 ID
	private String rasmbly_prmtago_id;

	//승격 지방의회 정보
	private String prmtBrtcCode;
	private String prmtLoasmCode;
	private String prmtInsttClCode;
	private String prmtInsttClCodeNm;
	private String prmtFInsttClCode;
	private String prmtFInsttClCodeNm;
	
	//openapi 공개여부
	private String mints_othbc_at;
	private String bi_othbc_at;
	private String asemby_othbc_at;
	
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public int getTotcnt() {
		return totcnt;
	}
	public void setTotcnt(int totcnt) {
		this.totcnt = totcnt;
	}
	public String getRasmbly_id() {
		return rasmbly_id;
	}
	public void setRasmbly_id(String rasmbly_id) {
		this.rasmbly_id = rasmbly_id;
	}
	public String getRasmbly_nm() {
		return rasmbly_nm;
	}
	public void setRasmbly_nm(String rasmbly_nm) {
		this.rasmbly_nm = rasmbly_nm;
	}
	public String getRasmbly_site_url() {
		return rasmbly_site_url;
	}
	public void setRasmbly_site_url(String rasmbly_site_url) {
		this.rasmbly_site_url = rasmbly_site_url;
	}
	public String getRasmbly_sttus_code() {
		return rasmbly_sttus_code;
	}
	public void setRasmbly_sttus_code(String rasmbly_sttus_code) {
		this.rasmbly_sttus_code = rasmbly_sttus_code;
	}
	public String getRasmbly_sttus_nm() {
		return rasmbly_sttus_nm;
	}
	public void setRasmbly_sttus_nm(String rasmbly_sttus_nm) {
		this.rasmbly_sttus_nm = rasmbly_sttus_nm;
	}
	public String getFrst_regist_dt() {
		return frst_regist_dt;
	}
	public void setFrst_regist_dt(String frst_regist_dt) {
		this.frst_regist_dt = frst_regist_dt;
	}
	public String getLast_updt_dt() {
		return last_updt_dt;
	}
	public void setLast_updt_dt(String last_updt_dt) {
		this.last_updt_dt = last_updt_dt;
	}
	public String getDelete_dt() {
		return delete_dt;
	}
	public void setDelete_dt(String delete_dt) {
		this.delete_dt = delete_dt;
	}
	public String getDelete_at() {
		return delete_at;
	}
	public void setDelete_at(String delete_at) {
		this.delete_at = delete_at;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getServer_ip() {
		return server_ip;
	}
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}
	public String getApi_crtfc_key() {
		return api_crtfc_key;
	}
	public void setApi_crtfc_key(String api_crtfc_key) {
		this.api_crtfc_key = api_crtfc_key;
	}
	public String getRasmbly_area_code() {
		return rasmbly_area_code;
	}
	public void setRasmbly_area_code(String rasmbly_area_code) {
		this.rasmbly_area_code = rasmbly_area_code;
	}
	public String getRasmbly_site_mints_url() {
		return rasmbly_site_mints_url;
	}
	public void setRasmbly_site_mints_url(String rasmbly_site_mints_url) {
		this.rasmbly_site_mints_url = rasmbly_site_mints_url;
	}
	public String getRasmbly_site_bill_url() {
		return rasmbly_site_bill_url;
	}
	public void setRasmbly_site_bill_url(String rasmbly_site_bill_url) {
		this.rasmbly_site_bill_url = rasmbly_site_bill_url;
	}
	public String getRasmbly_site_asemby_url() {
		return rasmbly_site_asemby_url;
	}
	public void setRasmbly_site_asemby_url(String rasmbly_site_asemby_url) {
		this.rasmbly_site_asemby_url = rasmbly_site_asemby_url;
	}
	public String getBrtcCode() {
		return brtcCode;
	}
	public void setBrtcCode(String brtcCode) {
		this.brtcCode = brtcCode;
	}
	public String getLoasmCode() {
		return loasmCode;
	}
	public void setLoasmCode(String loasmCode) {
		this.loasmCode = loasmCode;
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
	public String getRasmbly_prmtago_id() {
		return rasmbly_prmtago_id;
	}
	public void setRasmbly_prmtago_id(String rasmbly_prmtago_id) {
		this.rasmbly_prmtago_id = rasmbly_prmtago_id;
	}
	public String getPrmtBrtcCode() {
		return prmtBrtcCode;
	}
	public void setPrmtBrtcCode(String prmtBrtcCode) {
		this.prmtBrtcCode = prmtBrtcCode;
	}
	public String getPrmtLoasmCode() {
		return prmtLoasmCode;
	}
	public void setPrmtLoasmCode(String prmtLoasmCode) {
		this.prmtLoasmCode = prmtLoasmCode;
	}
	public String getPrmtInsttClCode() {
		return prmtInsttClCode;
	}
	public void setPrmtInsttClCode(String prmtInsttClCode) {
		this.prmtInsttClCode = prmtInsttClCode;
	}
	public String getPrmtInsttClCodeNm() {
		return prmtInsttClCodeNm;
	}
	public void setPrmtInsttClCodeNm(String prmtInsttClCodeNm) {
		this.prmtInsttClCodeNm = prmtInsttClCodeNm;
	}
	public String getPrmtFInsttClCode() {
		return prmtFInsttClCode;
	}
	public void setPrmtFInsttClCode(String prmtFInsttClCode) {
		this.prmtFInsttClCode = prmtFInsttClCode;
	}
	public String getPrmtFInsttClCodeNm() {
		return prmtFInsttClCodeNm;
	}
	public void setPrmtFInsttClCodeNm(String prmtFInsttClCodeNm) {
		this.prmtFInsttClCodeNm = prmtFInsttClCodeNm;
	}
	public String getIsview() {
		return isview;
	}
	public void setIsview(String isview) {
		this.isview = isview;
	}
	public String getMints_othbc_at() {
		return mints_othbc_at;
	}
	public void setMints_othbc_at(String mints_othbc_at) {
		this.mints_othbc_at = mints_othbc_at;
	}
	public String getBi_othbc_at() {
		return bi_othbc_at;
	}
	public void setBi_othbc_at(String bi_othbc_at) {
		this.bi_othbc_at = bi_othbc_at;
	}
	public String getAsemby_othbc_at() {
		return asemby_othbc_at;
	}
	public void setAsemby_othbc_at(String asemby_othbc_at) {
		this.asemby_othbc_at = asemby_othbc_at;
	}
	
	
}
