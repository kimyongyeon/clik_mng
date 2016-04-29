package clikmng.nanet.go.kr.sts.stm.service;

/**
 * 
 * 사이트정보를 처리하는 VO 클래스
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */
public class UvsLogSummaryVO extends StmManageDefaultVO {
	
    private static final long serialVersionUID = 1L;

    
    private String rasmbly_nm;
    private String pc_access_cnt;
    private String mobile_access_cnt;
    private String pc_query_cnt;
    private String mobile_query_cnt;
    
    private String platform;
    private String os;
    private String access_cnt;
    
    private String target_date;
    
	public String getRasmbly_nm() {
		return rasmbly_nm;
	}
	public void setRasmbly_nm(String rasmbly_nm) {
		this.rasmbly_nm = rasmbly_nm;
	}
	public String getPc_access_cnt() {
		return pc_access_cnt;
	}
	public void setPc_access_cnt(String pc_access_cnt) {
		this.pc_access_cnt = pc_access_cnt;
	}
	public String getMobile_access_cnt() {
		return mobile_access_cnt;
	}
	public void setMobile_access_cnt(String mobile_access_cnt) {
		this.mobile_access_cnt = mobile_access_cnt;
	}
	public String getPc_query_cnt() {
		return pc_query_cnt;
	}
	public void setPc_query_cnt(String pc_query_cnt) {
		this.pc_query_cnt = pc_query_cnt;
	}
	public String getMobile_query_cnt() {
		return mobile_query_cnt;
	}
	public void setMobile_query_cnt(String mobile_query_cnt) {
		this.mobile_query_cnt = mobile_query_cnt;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getAccess_cnt() {
		return access_cnt;
	}
	public void setAccess_cnt(String access_cnt) {
		this.access_cnt = access_cnt;
	}
	public String getTarget_date() {
		return target_date;
	}
	public void setTarget_date(String target_date) {
		this.target_date = target_date;
	}
}
