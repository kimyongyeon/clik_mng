package clikmng.nanet.go.kr.elm.crp.service;

import java.io.Serializable;
import java.util.HashMap;

import clikmng.nanet.go.kr.cmm.ComDefaultVO;
import clikmng.nanet.go.kr.elm.com.ReadGrantRequestSetupVO;


public class ElmCrpDetailVO extends ReadGrantRequestSetupVO implements Serializable
{

	private static final long serialVersionUID = 1L;

	private HashMap<String, String> openCode = new HashMap();

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
