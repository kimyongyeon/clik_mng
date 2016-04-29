package clikmng.nanet.go.kr.sts.stm.service;

/**
 * 
 * 자료이용 통계 처리하는 VO 클래스
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
public class DtaUseLogSummaryVO extends StmManageDefaultVO {
	
    private static final long serialVersionUID = 1L;

    
    private String dtaUseLogSumryId;
    private String year;
    private String mt;
    private String de;
    private String logSeCode;
    private String rasmblyDtaSeCode;
    private String useCo;
    
    private String menuNo;
    private String menuNm;
    private String menuLevel;


    public DtaUseLogSummaryVO()
    {
    	dtaUseLogSumryId = "";
    	year = "";
    	mt = "";
    	de = "";
    	logSeCode = "";
    	rasmblyDtaSeCode = "";
    	useCo = "";
    }


	public String getDtaUseLogSumryId() {
		return dtaUseLogSumryId;
	}


	public void setDtaUseLogSumryId(String dtaUseLogSumryId) {
		this.dtaUseLogSumryId = dtaUseLogSumryId;
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


	public String getLogSeCode() {
		return logSeCode;
	}


	public void setLogSeCode(String logSeCode) {
		this.logSeCode = logSeCode;
	}


	public String getRasmblyDtaSeCode() {
		return rasmblyDtaSeCode;
	}


	public void setRasmblyDtaSeCode(String rasmblyDtaSeCode) {
		this.rasmblyDtaSeCode = rasmblyDtaSeCode;
	}


	public String getUseCo() {
		return useCo;
	}


	public void setUseCo(String useCo) {
		this.useCo = useCo;
	}


	public String getMenuNo() {
		return menuNo;
	}


	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}


	public String getMenuNm() {
		return menuNm;
	}


	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}


	public String getMenuLevel() {
		return menuLevel;
	}


	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
   
}
