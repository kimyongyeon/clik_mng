package clikmng.nanet.go.kr.rcm.service;

/**
 * 
 * 리소스센터 정보를  처리하는 VO 클래스
 * @author 
 * @since
 * @version 1.0
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
public class RcmManageVO extends RcmManageDefaultVO {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		/**  open api  코드 **/  
		private String openApiSeCode;
		/**  open api  명 **/
		private String openApiSeNm;
		/**  인증키 **/
		private String crtfcKey;
		/**  발급자 **/
		private String frstRegisterId;
		/**  일일처리한도 **/
		private String dalyPermTrfic;
		/** 발급일자 **/
		private String frstRegistPnttm;
		/** 상태코드 **/
		private String sttusCode;
		/** 상태명 **/
		private String sttusNm;
	    /**  UNITY  코드 **/  
		private String unityId;
		/**  **/
		private String procMode;
		
		/** 활용 용도 (sugarsoft) **/
		private String prcusePrpos;
		/** 설명**/
		private String dc;
		/** 신청기관명 */
		private String reqstInsttNm = "";
		/** 담당자명 */
		private String chargerNm = "";
		/** 담당자이메일 */
		private String chargerEmail = "";
		/** 비고 */
		private String rm = "";
		/** 승인시점 */
		private String confmPnttm = "";
		/** 담당자연락처 */
		private String chargerTelno = "";
		
		public String getDc() {
			return dc;
		}
		public void setDc(String dc) {
			this.dc = dc;
		}
		public String getOpenApiSeCode() {
			return openApiSeCode;
		}
		public void setOpenApiSeCode(String openApiSeCode) {
			this.openApiSeCode = openApiSeCode;
		}
		public String getOpenApiSeNm() {
			return openApiSeNm;
		}
		public void setOpenApiSeNm(String openApiSeNm) {
			this.openApiSeNm = openApiSeNm;
		}
		public String getCrtfcKey() {
			return crtfcKey;
		}
		public void setCrtfcKey(String crtfcKey) {
			this.crtfcKey = crtfcKey;
		}
		public String getFrstRegisterId() {
			return frstRegisterId;
		}
		public void setFrstRegisterId(String frstRegisterId) {
			this.frstRegisterId = frstRegisterId;
		}
		public String getDalyPermTrfic() {
			return dalyPermTrfic;
		}
		public void setDalyPermTrfic(String dalyPermTrfic) {
			this.dalyPermTrfic = dalyPermTrfic;
		}
		public String getFrstRegistPnttm() {
			return frstRegistPnttm;
		}
		public void setFrstRegistPnttm(String frstRegistPnttm) {
			this.frstRegistPnttm = frstRegistPnttm;
		}
		public String getSttusCode() {
			return sttusCode;
		}
		public void setSttusCode(String sttusCode) {
			this.sttusCode = sttusCode;
		}
		public String getSttusNm() {
			return sttusNm;
		}
		public void setSttusNm(String sttusNm) {
			this.sttusNm = sttusNm;
		}
		public String getUnityId() {
			return unityId;
		}
		public void setUnityId(String unityId) {
			this.unityId = unityId;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public String getProcMode() {
			return procMode;
		}
		public void setProcMode(String procMode) {
			this.procMode = procMode;
		}
		public String getPrcusePrpos() {
			return prcusePrpos;
		}
		public void setPrcusePrpos(String prcusePrpos) {
			this.prcusePrpos = prcusePrpos;
		}
		public String getReqstInsttNm() {
			return reqstInsttNm;
		}
		public void setReqstInsttNm(String reqstInsttNm) {
			this.reqstInsttNm = reqstInsttNm;
		}
		public String getChargerNm() {
			return chargerNm;
		}
		public void setChargerNm(String chargerNm) {
			this.chargerNm = chargerNm;
		}
		public String getChargerEmail() {
			return chargerEmail;
		}
		public void setChargerEmail(String chargerEmail) {
			this.chargerEmail = chargerEmail;
		}
		public String getRm() {
			return rm;
		}
		public void setRm(String rm) {
			this.rm = rm;
		}
		public String getConfmPnttm() {
			return confmPnttm;
		}
		public void setConfmPnttm(String confmPnttm) {
			this.confmPnttm = confmPnttm;
		}
		public String getChargerTelno() {
			return chargerTelno;
		}
		public void setChargerTelno(String chargerTelno) {
			this.chargerTelno = chargerTelno;
		}		
	
}
