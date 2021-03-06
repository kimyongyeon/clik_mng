package clikmng.nanet.go.kr.sts.stm.service;

import java.io.Serializable;

/**
 * 
 * 사이트정보를 처리하는 클래스
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
public class StmManageDefaultVO implements Serializable {
	
	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    private String bgYear = "";
    private String bgMonth = "";
    private String bgDay = "";

    private String edYear = "";
    private String edMonth = "";
    private String edDay = "";

    private String bgDt		=	"";
    private String edDt		=	"";
    
    private String schDt1	=	"";
    private String schDt2	=	"";
    
    private String searchInsttType;
    private String searchRegion;
    private String searchPsitn;
    
	/**
	 * searchCondition attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchCondition() {
		return searchCondition;
	}

	/**
	 * searchCondition attribute 값을 설정한다.
	 * @return searchCondition String
	 */
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * searchKeyword attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}

	/**
	 * searchKeyword attribute 값을 설정한다.
	 * @return searchKeyword String
	 */
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	/**
	 * searchUseYn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}

	/**
	 * searchUseYn attribute 값을 설정한다.
	 * @return searchUseYn String
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	/**
	 * pageIndex attribute 를 리턴한다.
	 * @return the int
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * pageIndex attribute 값을 설정한다.
	 * @return pageIndex int
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * pageUnit attribute 를 리턴한다.
	 * @return the int
	 */
	public int getPageUnit() {
		return pageUnit;
	}

	/**
	 * pageUnit attribute 값을 설정한다.
	 * @return pageUnit int
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	/**
	 * pageSize attribute 를 리턴한다.
	 * @return the int
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * pageSize attribute 값을 설정한다.
	 * @return pageSize int
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * firstIndex attribute 를 리턴한다.
	 * @return the int
	 */
	public int getFirstIndex() {
		return firstIndex;
	}

	/**
	 * firstIndex attribute 값을 설정한다.
	 * @return firstIndex int
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * lastIndex attribute 를 리턴한다.
	 * @return the int
	 */
	public int getLastIndex() {
		return lastIndex;
	}

	/**
	 * lastIndex attribute 값을 설정한다.
	 * @return lastIndex int
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * recordCountPerPage attribute 를 리턴한다.
	 * @return the int
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	/**
	 * recordCountPerPage attribute 값을 설정한다.
	 * @return recordCountPerPage int
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getBgYear() {
		return bgYear;
	}

	public void setBgYear(String bgYear) {
		this.bgYear = bgYear;
	}

	public String getBgMonth() {
		return bgMonth;
	}

	public void setBgMonth(String bgMonth) {
		this.bgMonth = bgMonth;
	}

	public String getBgDay() {
		return bgDay;
	}

	public void setBgDay(String bgDay) {
		this.bgDay = bgDay;
	}

	public String getEdYear() {
		return edYear;
	}

	public void setEdYear(String edYear) {
		this.edYear = edYear;
	}

	public String getEdMonth() {
		return edMonth;
	}

	public void setEdMonth(String edMonth) {
		this.edMonth = edMonth;
	}

	public String getEdDay() {
		return edDay;
	}

	public void setEdDay(String edDay) {
		this.edDay = edDay;
	}

	public String getBgDt() {
		return bgDt;
	}

	public void setBgDt(String bgDt) {
		this.bgDt = bgDt;
	}

	public String getEdDt() {
		return edDt;
	}

	public void setEdDt(String edDt) {
		this.edDt = edDt;
	}

	public String getSchDt1() {
		return schDt1;
	}

	public void setSchDt1(String schDt1) {
		this.schDt1 = schDt1;
	}

	public String getSchDt2() {
		return schDt2;
	}

	public void setSchDt2(String schDt2) {
		this.schDt2 = schDt2;
	}

	public String getSearchInsttType() {
		return searchInsttType;
	}

	public void setSearchInsttType(String searchInsttType) {
		this.searchInsttType = searchInsttType;
	}

	public String getSearchRegion() {
		return searchRegion;
	}

	public void setSearchRegion(String searchRegion) {
		this.searchRegion = searchRegion;
	}

	public String getSearchPsitn() {
		return searchPsitn;
	}

	public void setSearchPsitn(String searchPsitn) {
		this.searchPsitn = searchPsitn;
	}
}
