package clikmng.nanet.go.kr.rlm.service;

/** 지방의회 대수기간 */
public class RasmblyNumprPdVO {

	private String rnum;
	private int totcnt;
	private String hrsmnpd_sn;
	private String rasmbly_numpr;
	private String rasmbly_id;
	private String rasmbly_nm;
	private String frst_regist_dt;
	private String last_updt_dt;
	private String delete_dt;
	private String delete_at;
	
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
	public String getHrsmnpd_sn() {
		return hrsmnpd_sn;
	}
	public void setHrsmnpd_sn(String hrsmnpd_sn) {
		this.hrsmnpd_sn = hrsmnpd_sn;
	}
	public String getRasmbly_numpr() {
		return rasmbly_numpr;
	}
	public void setRasmbly_numpr(String rasmbly_numpr) {
		this.rasmbly_numpr = rasmbly_numpr;
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
}
