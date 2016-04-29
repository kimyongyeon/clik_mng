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
public class UseLogSummaryVO extends StmManageDefaultVO {
	
    private static final long serialVersionUID = 1L;

    
    private String dtaUseLogSumryId;
    private String year;
    private String mt;
    private String de;
    private String logSeCode;
    private String menuSclasCode;
    private String useCo;
    
    private String menuNo;
    private String menuNm;
    private String menuLevel;
    
    private String rasmblyDtaSeCode;
    private String WAC001;
    private String WAC002;
    private String WAC003;
    private String BAC001;
    private String BAC002;
    private String BAC003;
    private String ASM;
    private String NOR;

    private String USE_DATE;
    private String SS;
    private String SC;
    private String DV;
    private String OV;
    private String DL;
    

    public UseLogSummaryVO()
    {
    	dtaUseLogSumryId = "";
    	year = "";
    	mt = "";
    	de = "";
    	logSeCode = "";
    	menuSclasCode = "";
    	useCo = "";
        menuNo = "";
        menuNm = "";
        menuLevel = "";
        rasmblyDtaSeCode = "";

    
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

	public String getMenuSclasCode() {
		return menuSclasCode;
	}

	public void setMenuSclasCode(String menuSclasCode) {
		this.menuSclasCode = menuSclasCode;
	}

	public String getUseCo() {
		return useCo;
	}

	public void setUseCo(String useCo) {
		this.useCo = useCo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getRasmblyDtaSeCode() {
		return rasmblyDtaSeCode;
	}

	public void setRasmblyDtaSeCode(String rasmblyDtaSeCode) {
		this.rasmblyDtaSeCode = rasmblyDtaSeCode;
	}

	public String getWAC001() {
		return WAC001;
	}

	public void setWAC001(String wAC001) {
		WAC001 = wAC001;
	}

	public String getWAC002() {
		return WAC002;
	}

	public void setWAC002(String wAC002) {
		WAC002 = wAC002;
	}

	public String getWAC003() {
		return WAC003;
	}

	public void setWAC003(String wAC003) {
		WAC003 = wAC003;
	}

	public String getBAC001() {
		return BAC001;
	}

	public void setBAC001(String bAC001) {
		BAC001 = bAC001;
	}

	public String getBAC002() {
		return BAC002;
	}

	public void setBAC002(String bAC002) {
		BAC002 = bAC002;
	}

	public String getBAC003() {
		return BAC003;
	}

	public void setBAC003(String bAC003) {
		BAC003 = bAC003;
	}

	public String getASM() {
		return ASM;
	}

	public void setASM(String aSM) {
		ASM = aSM;
	}

	public String getNOR() {
		return NOR;
	}

	public void setNOR(String nOR) {
		NOR = nOR;
	}
	
	public String getUSE_DATE() {
		return USE_DATE;
	}

	public void setUSE_DATE(String uSE_DATE) {
		USE_DATE = uSE_DATE;
	}

	public String getSS() {
		return SS;
	}

	public void setSS(String SS) {
		this.SS = SS;
	}

	public String getSC() {
		return SC;
	}

	public void setSC(String SC) {
		this.SC = SC;
	}

	public String getDV() {
		return DV;
	}

	public void setDV(String DV) {
		this.DV = DV;
	}

	public String getOV() {
		return OV;
	}

	public void setOV(String OV) {
		this.OV = OV;
	}

	public String getDL() {
		return DL;
	}

	public void setDL(String DL) {
		this.DL = DL;
	}
}
