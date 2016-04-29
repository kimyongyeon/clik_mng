package clikmng.nanet.go.kr.col.cfm.service;

import java.io.Serializable;

/**
 * 표준연계API 파일동기화 파일 VO
 * */
public class CfmFileVO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9146221061030930757L;
	
	private String compare_id;
	private String mngrID;
	private String path;
	

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMngrID() {
		return mngrID;
	}
	public void setMngrID(String mngrID) {
		this.mngrID = mngrID;
	}
	public String getCompare_id() {
		return compare_id;
	}
	public void setCompare_id(String compare_id) {
		this.compare_id = compare_id;
	}
}
