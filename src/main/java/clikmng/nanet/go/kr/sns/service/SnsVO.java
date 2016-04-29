package clikmng.nanet.go.kr.sns.service;

/**
 * SNS 최신글에 대한 VO를 정의한다.
 * @author 
 *
 */
public class SnsVO {
	
	/** SNS구분코드 */
	private String sns_se_code;
	/** 사용자ID */
	private String sns_acnt_id;
	/** 최신글 */
	private String nbc;
	/** 최초등록일 */
	private String frst_regist_pnttm;
	/** 배치수정일 */
	private String  last_batch_exc_pnttm;
	/** 최신글 링크 url */
	private String nbc_link;
	/** 새로운 사용자ID */
	private String new_sns_acnt_id;
	
	
	public String getSns_se_code() {
		return sns_se_code;
	}
	public void setSns_se_code(String sns_se_code) {
		this.sns_se_code = sns_se_code;
	}
	public String getSns_acnt_id() {
		return sns_acnt_id;
	}
	public void setSns_acnt_id(String sns_acnt_id) {
		this.sns_acnt_id = sns_acnt_id;
	}
	public String getNbc() {
		return nbc;
	}
	public void setNbc(String nbc) {
		this.nbc = nbc;
	}
	public String getFrst_regist_pnttm() {
		return frst_regist_pnttm;
	}
	public void setFrst_regist_pnttm(String frst_regist_pnttm) {
		this.frst_regist_pnttm = frst_regist_pnttm;
	}
	public String getLast_batch_exc_pnttm() {
		return last_batch_exc_pnttm;
	}
	public void setLast_batch_exc_pnttm(String last_batch_exc_pnttm) {
		this.last_batch_exc_pnttm = last_batch_exc_pnttm;
	}
	public String getNbc_link() {
		return nbc_link;
	}
	public void setNbc_link(String nbc_link) {
		this.nbc_link = nbc_link;
	}
	public String getNew_sns_acnt_id() {
		return new_sns_acnt_id;
	}
	public void setNew_sns_acnt_id(String new_sns_acnt_id) {
		this.new_sns_acnt_id = new_sns_acnt_id;
	}
}
