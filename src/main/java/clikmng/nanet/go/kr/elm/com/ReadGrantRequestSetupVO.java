package clikmng.nanet.go.kr.elm.com;

import java.io.Serializable;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;

/**
 * 공통코드 모델 클래스
 * @author 
 * @since 
 * @version 
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   
 *
 * </pre>
 */
public class ReadGrantRequestSetupVO extends ComDefaultVO implements Serializable  
{

	private String registNo;
	private String ddc;
	private String pl;
	private String sl;
	private String pblicteYear;
	private String postn;
	private String postnLc;
	private String printId;
	private String printIdDc;
	private String cnType;
	private String frstRegisterId;
	private String frstRegistPnttm;
	private String lastUpdusrId;
	private String lastUpdtPnttm;
	private String readngReqstSetupId;
	private String dtaSeCode;
	private String dtaSe;

	public ReadGrantRequestSetupVO()
	{
		registNo			=	"";
		ddc					=	"";
		pl					=	"";
		sl					=	"";
		pblicteYear			=	"";
		postn				=	"";
		postnLc				=	"";
		printId				=	"";
		printIdDc			=	"";
		cnType				=	"";
		frstRegisterId		=	"";
		frstRegistPnttm		=	"";
		lastUpdusrId		=	"";
		lastUpdtPnttm		=	"";
		readngReqstSetupId	=	"";
		dtaSeCode			=	"";
		dtaSe				=	"";

	}

	public String getRegistNo() {
		return registNo;
	}

	public void setRegistNo(String registNo) {
		this.registNo = registNo;
	}

	public String getDdc() {
		return ddc;
	}

	public void setDdc(String ddc) {
		this.ddc = ddc;
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getPblicteYear() {
		return pblicteYear;
	}

	public void setPblicteYear(String pblicteYear) {
		this.pblicteYear = pblicteYear;
	}

	public String getPostn() {
		return postn;
	}

	public void setPostn(String postn) {
		this.postn = postn;
	}

	public String getPostnLc() {
		return postnLc;
	}

	public void setPostnLc(String postnLc) {
		this.postnLc = postnLc;
	}

	public String getPrintId() {
		return printId;
	}

	public void setPrintId(String printId) {
		this.printId = printId;
	}

	public String getPrintIdDc() {
		return printIdDc;
	}

	public void setPrintIdDc(String printIdDc) {
		this.printIdDc = printIdDc;
	}

	public String getCnType() {
		return cnType;
	}

	public void setCnType(String cnType) {
		this.cnType = cnType;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	public String getReadngReqstSetupId() {
		return readngReqstSetupId;
	}

	public void setReadngReqstSetupId(String readngReqstSetupId) {
		this.readngReqstSetupId = readngReqstSetupId;
	}

	public String getDtaSeCode() {
		return dtaSeCode;
	}

	public void setDtaSeCode(String dtaSeCode) {
		this.dtaSeCode = dtaSeCode;
	}

	public String getDtaSe() {
		return dtaSe;
	}

	public void setDtaSe(String dtaSe) {
		this.dtaSe = dtaSe;
	}

	
	
	
	
}
