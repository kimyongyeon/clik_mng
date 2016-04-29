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
public class WbsrLogSummaryVO extends StmManageDefaultVO {
	
    private static final long serialVersionUID = 1L;

    
    private String wbsrSumryId;
    private String year;
    private String mt;
    private String de;
    private String userWbsr;
    private String conectCo;
    private String dt;

    public WbsrLogSummaryVO()
    {
    	wbsrSumryId = "";
		year = "";
		mt = "";
		de = "";
		userWbsr = "";
		conectCo = "";
		dt = "";
    }

	public String getWbsrSumryId() {
		return wbsrSumryId;
	}

	public void setWbsrSumryId(String wbsrSumryId) {
		this.wbsrSumryId = wbsrSumryId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMt() {
		return mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getUserWbsr() {
		return userWbsr;
	}

	public void setUserWbsr(String userWbsr) {
		this.userWbsr = userWbsr;
	}

	public String getConectCo() {
		return conectCo;
	}

	public void setConectCo(String conectCo) {
		this.conectCo = conectCo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

    
    
}
