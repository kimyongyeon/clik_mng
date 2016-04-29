package clikmng.nanet.go.kr.mob.service;

import java.util.ArrayList;

/**
 * 
 * 사이트정보를 처리하는 VO 클래스
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
public class MobManageVO extends MobManageDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 시퀀스번호 */
    private String seq;
    
    /** 사용자 아이디 */
    private String userId;
    
    /** 사용자 클래스 */
    private String userClass;
    
    /** 의견내용 */
    private String opinionCn;
    
    /** 최초등록일시 */
    private String frstRegisterPnttm;
    
    /** 등록일 */
    private String regDate;
    
    /** 삭제 의견seq */
    private String delSeq;
    private ArrayList<String> delSeqList;

    
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	public String getOpinionCn() {
		return opinionCn;
	}

	public void setOpinionCn(String opinionCn) {
		this.opinionCn = opinionCn;
	}

	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public String getDelSeq() {
		return delSeq;
	}

	public void setDelSeq(String delSeq) {
		this.delSeq = delSeq;
	}

	public ArrayList<String> getDelSeqList() {
		return delSeqList;
	}

	public void setDelSeqList(ArrayList<String> delSeqList) {
		this.delSeqList = delSeqList;
	}
	
}
