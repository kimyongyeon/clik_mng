package clikmng.nanet.go.kr.elm.gup.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;
import clikmng.nanet.go.kr.elm.com.UserClassVO;
import clikmng.nanet.go.kr.elm.com.UserClassDtaseVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantRequestVO;

public class ElmGupDetailVO  extends UserClassVO implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String prposCode;
	// 자료 이용 권한
	private HashMap<String, String> grantUseCode = new HashMap();
	
	// 자료 이용 제한
	private HashMap<String, String> grantBenCode = new HashMap();
	
	// 자료 이용 제한 메세지
	private HashMap<String, String> grantBenMsg = new HashMap();
	
	// 열람 권한
	private HashMap<String, String> openCode = new HashMap();
	
	
	public ElmGupDetailVO()
	{
		prposCode = "";
		
		// 자료 이용 권한
		grantUseCode = new HashMap();
		
		// 자료 이용 제한
		grantBenCode = new HashMap();
		
		// 자료 이용 제한 메세지
		grantBenMsg = new HashMap();
		
		// 열람 권한
		openCode = new HashMap();

	}

	
	
	

	public String getPrposCode() {
		return prposCode;
	}

	public void setPrposCode(String prposCode) {
		this.prposCode = prposCode;
	}

	public HashMap<String, String> getGrantUseCode() {
		return grantUseCode;
	}


	public void setGrantUseCode(HashMap<String, String> grantUseCode) {
		this.grantUseCode = grantUseCode;
	}


	public HashMap<String, String> getGrantBenCode() {
		return grantBenCode;
	}


	public void setGrantBenCode(HashMap<String, String> grantBenCode) {
		this.grantBenCode = grantBenCode;
	}


	public HashMap<String, String> getGrantBenMsg() {
		return grantBenMsg;
	}


	public void setGrantBenMsg(HashMap<String, String> grantBenMsg) {
		this.grantBenMsg = grantBenMsg;
	}


	public HashMap<String, String> getOpenCode() {
		return openCode;
	}


	public void setOpenCode(HashMap<String, String> openCode) {
		this.openCode = openCode;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
	
}
