package clikmng.nanet.go.kr.cmm.top.service;


/**
 * 
 * TOP를 처리하는 VO 클래스
 */
public class TopManageVO {
	
    private static final long serialVersionUID = 1L;
    
    /** 메뉴정보 */
    /** 메뉴번호 */
    private   int      menuNo;
    /** 메뉴순서 */
    private   int      menuOrdr;
    /** 메뉴명 */
    private   String   menuNm;
    /** 메뉴레벨 */
    private   int 	   menuLevel;
    /** 상위메뉴번호 */
    private   int      upperMenuNo;
    /** 메뉴설명 */
    private   String   menuDc;
    /** 관련이미지경로 */
    private   String   relateImagePath;
    /** 관련이미지명 */
    private   String   relateImageNm;
    /** URL */
    private   String   progrmFileNm;
    /** 권한코드 */
    private   String   authorCode;
    
    
	public int getMenuNo() {
		return menuNo;
	}
	
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	
	public int getMenuOrdr() {
		return menuOrdr;
	}
	
	public void setMenuOrdr(int menuOrdr) {
		this.menuOrdr = menuOrdr;
	}
	
	public String getMenuNm() {
		return menuNm;
	}
	
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	
	public String getMenuDc() {
		return menuDc;
	}
	
	public void setMenuDc(String menuDc) {
		this.menuDc = menuDc;
	}
	
	public String getRelateImagePath() {
		return relateImagePath;
	}
	
	public void setRelateImagePath(String relateImagePath) {
		this.relateImagePath = relateImagePath;
	}
	
	public String getRelateImageNm() {
		return relateImageNm;
	}
	
	public void setRelateImageNm(String relateImageNm) {
		this.relateImageNm = relateImageNm;
	}
	
	public String getProgrmFileNm() {
		return progrmFileNm;
	}
	
	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
	}

	public int getUpperMenuNo() {
		return upperMenuNo;
	}

	public void setUpperMenuNo(int upperMenuNo) {
		this.upperMenuNo = upperMenuNo;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}


    
}
