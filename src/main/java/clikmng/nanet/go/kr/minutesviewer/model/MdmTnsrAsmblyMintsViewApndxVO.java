package clikmng.nanet.go.kr.minutesviewer.model;

public class MdmTnsrAsmblyMintsViewApndxVO {

	/**
	 * 
	 * 회의록 뷰어의 부록정보가 담기는 VO 클래스
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
	private int APNDX_ID = 0;
	private String APNDX_FILE_NM = "";
	private String APNDX_FILE_HASH = "";
	private String APNDX_FILE_PATH = "";
	private String APNDX_FILE_URL = "";
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
	
	public int getAPNDX_ID()
	{
		return APNDX_ID;
	}
	
	public void setAPNDX_ID(int apndx_id)
	{
		this.APNDX_ID = apndx_id;
	}
	
	public String getAPNDX_FILE_NM()
	{
		return APNDX_FILE_NM;
	}
	
	public void setAPNDX_FILE_NM(String apndx_file_nm)
	{
		this.APNDX_FILE_NM = apndx_file_nm;
	}
	
	public String getAPNDX_FILE_HASH()
	{
		return APNDX_FILE_HASH;
	}
	
	public void setAPNDX_FILE_HASH(String apndx_file_hash)
	{
		this.APNDX_FILE_HASH = apndx_file_hash;
	}
	
	public String getAPNDX_FILE_PATH()
	{
		return APNDX_FILE_PATH;
	}
	
	public void setAPNDX_FILE_PATH(String apndx_file_path)
	{
		this.APNDX_FILE_PATH = apndx_file_path;
	}
	
	public String getAPNDX_FILE_URL()
	{
		return APNDX_FILE_URL;
	}
	
	public void setAPNDX_FILE_URL(String apndx_file_url)
	{
		this.APNDX_FILE_URL = apndx_file_url;
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
