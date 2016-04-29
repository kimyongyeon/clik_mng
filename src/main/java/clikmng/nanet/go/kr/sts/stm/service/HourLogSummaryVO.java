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
public class HourLogSummaryVO extends StmManageDefaultVO {
	
    private static final long serialVersionUID = 1L;

    
    private String hourSumryId;
    private String year;
    private String mt;
    private String de;
    private String hour;
    private String conectCo;
    
    private String dt;
    
    

    public HourLogSummaryVO()
    {
    	hourSumryId = "";
		year = "";
		mt = "";
		de = "";
		hour = "";
		conectCo = "";
		dt = "";
    }

	public String getOsSumryId() {
		return hourSumryId;
	}

	public void setHourSumryId(String hourSumryId) {
		this.hourSumryId = hourSumryId;
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

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
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
