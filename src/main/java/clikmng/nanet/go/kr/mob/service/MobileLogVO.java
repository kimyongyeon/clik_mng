package clikmng.nanet.go.kr.mob.service;

import java.util.ArrayList;

public class MobileLogVO extends MobManageDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    private String logSeCode;
    private String creatDt;
    private String appVer;
    private String appOs;
    private String mobileLogId;
    private String appOsVer;
    private	String cnt;
    private String FR_ios;
    private String FR_android;
    private String NR_ios;
    private String NR_android;
    
    
	public String getLogSeCode() {
		return logSeCode;
	}
	public void setLogSeCode(String logSeCode) {
		this.logSeCode = logSeCode;
	}
	public String getCreatDt() {
		return creatDt;
	}
	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}
	public String getAppVer() {
		return appVer;
	}
	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}
	public String getAppOs() {
		return appOs;
	}
	public void setAppOs(String appOs) {
		this.appOs = appOs;
	}
	public String getMobileLogId() {
		return mobileLogId;
	}
	public void setMobileLogId(String mobileLogId) {
		this.mobileLogId = mobileLogId;
	}
	public String getAppOsVer() {
		return appOsVer;
	}
	public void setAppOsVer(String appOsVer) {
		this.appOsVer = appOsVer;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getFR_ios() {
		return FR_ios;
	}
	public void setFR_ios(String fR_ios) {
		FR_ios = fR_ios;
	}
	public String getFR_android() {
		return FR_android;
	}
	public void setFR_android(String fR_android) {
		FR_android = fR_android;
	}
	public String getNR_ios() {
		return NR_ios;
	}
	public void setNR_ios(String nR_ios) {
		NR_ios = nR_ios;
	}
	public String getNR_android() {
		return NR_android;
	}
	public void setNR_android(String nR_android) {
		NR_android = nR_android;
	}
}
