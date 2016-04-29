package clikmng.nanet.go.kr.rcm.service;

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
@SuppressWarnings("serial")
public class RcmManageDefaultVO implements Serializable {
	
	/** 검색조건1 */
    private String searchCondition1 = "";
    
    /** 검색조건2 */
    private String searchCondition2 = "";
    
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
    
    /** 검색시작일 **/
    private String selectBgdate;
    /** 검색종료일 **/
    private String selectEndate;

    /** 검색시작일 **/
    private String schDt1;
    /** 검색종료일 **/
    private String schDt2;
    
    /** 정렬  코드**/
    private String sort_cl_cd;
    /** 정렬 구분**/
    private String sort_gbn;
    

	/**
	 * searchCondition attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchCondition1() {
		return searchCondition1;
	}

	/**
	 * searchCondition attribute 값을 설정한다.
	 * @return searchCondition String
	 */
	public void setSearchCondition1(String searchCondition1) {
		this.searchCondition1 = searchCondition1;
	}

	/**
	 * searchCondition attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchCondition2() {
		return searchCondition2;
	}

	/**
	 * searchCondition attribute 값을 설정한다.
	 * @return searchCondition String
	 */
	public void setSearchCondition2(String searchCondition2) {
		this.searchCondition2 = searchCondition2;
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

	public String getSelectBgdate() {
		return selectBgdate;
	}

	public void setSelectBgdate(String selectBgdate) {
		this.selectBgdate = selectBgdate;
	}

	public String getSelectEndate() {
		return selectEndate;
	}

	public void setSelectEndate(String selectEndate) {
		this.selectEndate = selectEndate;
	}

	public String getSort_cl_cd() {
		return sort_cl_cd;
	}

	public void setSort_cl_cd(String sort_cl_cd) {
		this.sort_cl_cd = sort_cl_cd;
	}

	public String getSort_gbn() {
		return sort_gbn;
	}

	public void setSort_gbn(String sort_gbn) {
		this.sort_gbn = sort_gbn;
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
    
    
    
}
