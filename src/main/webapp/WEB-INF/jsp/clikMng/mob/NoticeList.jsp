<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>공지사항 목록</title>

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

<body>
<form name="listForm" method="post">
<jsp:include page="/cmm/top/top.do" />

		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">공지사항</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>공지사항 목록</h2>
			<div class="select_box">
				<span>
				<select name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option>전체</option>
					<option>그룹명</option>
					<option>이름</option>
					<option>이메일</option>
				</select>
				<input type="text" class="ip input-sm" style="width:350px;" />
				<button type="button" class="btn btn-primary" >검색</button>
				</span>
			
			</div><!--//tc-->
				
				<div class="page">
				총 건수 : 35건

				<span>
					출력건수
					<select name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm">
						<option>10</option>
						<option>20</option>
						<option>50</option>
						<option>100</option>
					</select>
				</span>
			
				</div>

				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="5%" />
						<col width="" />
						<col width="" />
						<col width="" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>[공지] 콘텐츠 이용 공지 </td>
							<td>김공지</td>
							<td>2014/06/24</td>
						</tr>
					</tbody>
				</table>
				<div class="tr mb20">
					<button type="button" class="btn btn-primary" onclick="fncAddNoticeInsert();">등록</button>
				</div><!--//tr-->


			<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
				<ul class="pagination">
					<li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous">
					<a href="#">Previous</a></li>
					<li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
					<a href="#">1</a></li>
					<li class="paginate_button active" aria-controls="dataTables-example" tabindex="0">
					<a href="#">2</a></li>
					<li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
					<a href="#">3</a></li>
					<li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
					<a href="#">4</a></li>
					<li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next">
					<a href="#">Next</a>
					</li>
				</ul>
			</div><!--//dataTables_paginate-->

	</div><!--//page-wrapper-->
</form>
</body>
</html>
