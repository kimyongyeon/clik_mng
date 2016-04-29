<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>


<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>수집 / 검색엔진 관리</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--


/* ********************************************************
* 공지사항 등록
******************************************************** */
function fncAddNoticeInsert() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/mob/NoticeRegist.do' />";

	varForm.submit();
}

/* ********************************************************
* 공지사항 검색
******************************************************** */
function fnSearch() {
	
	if($("#searchKeyword").val() == "" || $("#searchKeyword").val() == null) {
		alert('검색어를 입력해 주세요.');
		return; 
	}
	
	var varForm = document.listForm;
	varForm.action = "<c:url value='/uss/mng/MngList.do' />";
	varForm.submit();
	
}


/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.currentPageNo.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/uss/mng/MngList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
    varForm.action = "<c:url value='/uss/mng/MngList.do'/>";
    varForm.submit();
}

/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnMngDetailView(mngrId) {

	var varForm = document.listForm;
	varForm.mngrId.value = mngrId;
	varForm.action = "<c:url value='/uss/mng/MngDetail.do'/>";
	varForm.submit();
}
-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">크롤러 / 검색엔진 관리</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>

<!-- /.row -->

		<p class="tc" style="margin-top:50px;">
			<a href="<c:url value='${searchUrl}' />" target="_blank" style="padding-right:65px;"><img src="/images/clikmng/icon_admin01.png" alt="수집엔진 관리 시스템" /></a>
			<a href="<c:url value='${collectionUrl}' />" target="_blank" style="padding-right:65px;"><img src="/images/clikmng/icon_admin02.png" alt="검색엔진 관리 시스템" /> </a>
			<a href="<c:url value='${issueUrl}' />" target="_blank"> <img src="/images/clikmng/icon_admin03.png" alt="이슈키워드 관리 시스템" /></a>
		</p>
                    
	 <!-- /.panel-body -->
 
</div>
<!-- /.panel .chat-panel -->
<!-- /#page-wrapper -->
</body>
</html>
