package clikmng.nanet.go.kr.minutesviewer.model;

public class MdmTnsrAsmblyMintsViewBillVO {
	
	/**
	 * 
	 * 회의록 뷰어의 안건정보가 담기는 VO 클래스
	 * @author 강희준(ngluka@gmail.com)
	 * @since 2014.12.29
	 * @version 1.0
	 * @see
	 *
	 * <pre>
	 * << 개정이력(Modification Information) >>
	 *   
	 *   수정일 		수정자			수정내용
	 *  -------		---			--------------------
	 *   014.12.29	강희준			최초 생성
	 *
	 * </pre>
	 */
	
	private int RASMBLY_ID = 0;
	private int RASMBLY_NUMPR = 0;
	private String MTGNM_ID = "";
	private int RASMBLY_SESN = 0;
	private int MINTS_ODR = 0;
	
	private String MTR_ID = "";
	private String MTR_SN = "";
	private String MTR_SJ = "";
	
	private String MINTS_CN = "";
	
	public int getRASMBLY_ID()
	{
		return RASMBLY_ID;
	}
	
	public void setRASMBLY_ID(int rasmbly_id)
	{
		this.RASMBLY_ID = rasmbly_id;
	}
	
	public int getRASMBLY_NUMPR()
	{
		return RASMBLY_NUMPR;
	}
	
	public void setRASMBLY_NUMPR(int rasmbly_numpr)
	{
		this.RASMBLY_NUMPR = rasmbly_numpr;
	}
	
	public String getMTGNM_ID()
	{
		return MTGNM_ID;
	}
	
	public void setMTGNM_ID(String mtgnm_id)
	{
		this.MTGNM_ID = mtgnm_id;
	}
	
	public int getRASMBLY_SESN()
	{
		return RASMBLY_SESN;
	}
	
	public void setRASMBLY_SESN(int rasmbly_sesn)
	{
		this.RASMBLY_SESN = rasmbly_sesn;
	}
	
	public int getMINTS_ODR()
	{
		return MINTS_ODR;
	}
	
	public void setMINTS_ODR(int mints_odr)
	{
		this.MINTS_ODR = mints_odr;
	}
	
	public String getMTR_ID()
	{
		return MTR_ID;
	}
	
	public void setMTR_ID(String mtr_id)
	{
		this.MTR_ID = mtr_id;
	}
	
	public String getMTR_SN()
	{
		return MTR_SN;
	}
	
	public void setMTR_SN(String mtr_sn)
	{
		this.MTR_SN = mtr_sn;
	}
	
	public String getMTR_SJ()
	{
		return MTR_SJ;
	}
	
	public void setMTR_SJ(String mtr_sj)
	{
		this.MTR_SJ = mtr_sj;
	}
	
	public String getMINTS_CN()
	{
		return MINTS_CN;
	}
	
	public void setMINTS_CN(String mints_cn)
	{
		this.MINTS_CN = mints_cn;
	}
}
