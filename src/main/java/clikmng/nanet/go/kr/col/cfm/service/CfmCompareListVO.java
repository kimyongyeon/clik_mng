package clikmng.nanet.go.kr.col.cfm.service;

import java.io.Serializable;


/**
 * 
 * 파일비교리스트 VO 클래스
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
public class CfmCompareListVO  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5485238566650736568L;

	/** API KEY */
    private String api_key;
    
    /** 파일 URL */
    private String file_url;
    
    /** 파일 PATH */
    private String file_path;

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
    
    
    
	
}
