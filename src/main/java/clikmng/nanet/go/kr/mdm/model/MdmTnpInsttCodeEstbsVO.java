package clikmng.nanet.go.kr.mdm.model;

public class MdmTnpInsttCodeEstbsVO {
	
	private String LOASM_CODE    = ""; // 지방의회 고유 코드
	private String LOASM_TY_CODE = ""; // 광역 / 기초 구분 코드
	private String LOASM_NM      = ""; // 의회명
	private String BRTC_CODE     = ""; // 지역코드
	
	
	public String getLOASM_CODE() {
		return LOASM_CODE;
	}
	public void setLOASM_CODE(String lOASM_CODE) {
		LOASM_CODE = lOASM_CODE;
	}
	public String getLOASM_TY_CODE() {
		return LOASM_TY_CODE;
	}
	public void setLOASM_TY_CODE(String lOASM_TY_CODE) {
		LOASM_TY_CODE = lOASM_TY_CODE;
	}
	public String getLOASM_NM() {
		return LOASM_NM;
	}
	public void setLOASM_NM(String lOASM_NM) {
		LOASM_NM = lOASM_NM;
	}
	public String getBRTC_CODE() {
		return BRTC_CODE;
	}
	public void setBRTC_CODE(String bRTC_CODE) {
		BRTC_CODE = bRTC_CODE;
	}
}
