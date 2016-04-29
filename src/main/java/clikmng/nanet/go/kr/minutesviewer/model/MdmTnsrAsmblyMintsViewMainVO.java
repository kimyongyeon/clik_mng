package clikmng.nanet.go.kr.minutesviewer.model;

public class MdmTnsrAsmblyMintsViewMainVO {
	
	/**
	 * 
	 * 회의록 뷰어의 기본 정보가 담기는 VO 클래스
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

	private String MINTS_CN = "";
	
	private String RASMBLY_ID = "";
	private String RASMBLY_NM = "";
	
	private int RASMBLY_NUMPR = 0;
	private int RASMBLY_SESN = 0;
	private int MINTS_ODR = 0; 
	
	private String MTGNM_ID = "";	
	private String MTGNM_NM = "";
	
	private String ORGINL_FILE_NM = "";
	private String ORGINL_FILE_URL= "";
	private String ORGINL_FILE_PATH= "";
	
	private String MINTS_VIEW = "";
	
	public String getMINTS_CN()
	{
		return MINTS_CN;
	}
	
	public void setMINTS_CN(String mints_cn)
	{
		this.MINTS_CN = mints_cn;
	}

	public String getRASMBLY_ID()
	{
		return RASMBLY_ID;
	}
	
	public void setRASMBLY_ID(String rasmbly_id)
	{
		this.RASMBLY_ID = rasmbly_id;
	}
	
	public String getRASMBLY_NM()
	{
		return RASMBLY_NM;
	}
	
	public void setRASMBLY_NM(String rasmbly_nm)
	{
		this.RASMBLY_NM = rasmbly_nm;
	}
	
	public int getRASMBLY_NUMPR()
	{
		return RASMBLY_NUMPR;
	}
	
	public void setRASMBLY_NUMPR(int rasmbly_numpr)
	{
		this.RASMBLY_NUMPR = rasmbly_numpr;
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
	
	public String getMTGNM_ID()
	{
		return MTGNM_ID;
	}
	
	public void setMTGNM_ID(String mtgnm_id)
	{
		this.MTGNM_ID = mtgnm_id;
	}
	
	public String getMTGNM_NM()
	{
		return MTGNM_NM;
	}
	
	public void setMTGNM_NM(String mtgnm_nm)
	{
		this.MTGNM_NM = mtgnm_nm;
	}
	
	public String getORGINL_FILE_NM()
	{
		return ORGINL_FILE_NM;
	}
	
	public void setORGINL_FILE_NM(String orginl_file_nm)
	{
		this.ORGINL_FILE_NM = orginl_file_nm;
	}
	
	public String getORGINL_FILE_URL()
	{
		return ORGINL_FILE_URL;
	}
	
	public void setORGINL_FILE_URL(String orginl_file_url)
	{
		this.ORGINL_FILE_URL = orginl_file_url;
	}
	
	public String getORGINL_FILE_PATH()
	{
		return ORGINL_FILE_PATH;
	}
	
	public void setORGINL_FILE_PATH(String orginl_file_path)
	{
		this.ORGINL_FILE_PATH = orginl_file_path;
	}
	
	public String getMINTS_VIEW()
	{
		return MINTS_VIEW;
	}
	
	public void setMINTS_VIEW(String mints_view)
	{
		this.MINTS_VIEW = mints_view;
	}
}
