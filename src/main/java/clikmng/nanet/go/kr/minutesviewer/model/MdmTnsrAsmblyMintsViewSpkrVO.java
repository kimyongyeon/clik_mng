package clikmng.nanet.go.kr.minutesviewer.model;

public class MdmTnsrAsmblyMintsViewSpkrVO {
	/**
	 * 
	 * 회의록 뷰어의 발언정보가 담기는 VO 클래스
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
	private int ASEMBY_ID = 0;
	private String ASEMBY_NM = "";
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
	
	public int getASEMBY_ID()
	{
		return ASEMBY_ID;
	}
	
	public void setASEMBY_ID(int asemby_id)
	{
		this.ASEMBY_ID = asemby_id;
	}
	
	public String getASEMBY_NM()
	{
		return ASEMBY_NM;
	}
	
	public void setASEMBY_NM(String asemby_nm)
	{
		this.ASEMBY_NM = asemby_nm;
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
