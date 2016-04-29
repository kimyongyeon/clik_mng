<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
 
<title>직원검색 팝업</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-mobile-web-app-capable" content="yes" />

<script language="javascript" src="<c:url value='/js/clikmng/jquery-1.10.2.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/bootstrap.min.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/jquery.metisMenu.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/sb-admin.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/jquery-ui.min.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/cmm/common.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/respond_src.js' />"></script>

<!-- Core CSS - Include with every page -->
<link href="<c:url value='/css/clikmng/bootstrap.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/jquery-ui.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/sb-admin.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/font-awesome.css' />" rel="stylesheet" type="text/css">

<style type="text/css">
/**/
body.popup{ position:relative; overflow:hidden; padding:0; background:#fff;}
div.today{  position:absolute;left:0; bottom:0; background:#f1f1f1; padding:10px; text-align:right; width:100%; border-top:1px solid #ccc;}
div.today label{color:#333;}
div.today a{color:#333;}

div.table-responsive{ }
h1{ margin-left:10px; font-size:18px; font-weight:bold;}
</style>

<script type="text/javaScript" defer="defer">
<!--
/* ********************************************************
 * 검색하기
 ******************************************************** */
function Search(){
	var varForm = document.listForm;
	if (varForm.searchKeyword.value == null || varForm.searchKeyword.value == '') {
		alert('검색어를 입력해 주세요.');
		return;
	}
	varForm.action = "<c:url value='/docs/SearchEmp.do' />";
	varForm.submit();
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************* */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/docs/SearchEmp.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
 * 부모창에 아이디와 이름 집어넣기
 ******************************************************* */
function pasteMother(userId, name){

	opener.document.getElementById('mngrId').value = userId;
	opener.document.getElementById('mngrNm').value = name;
	
	window.close();
}

-->
</script>

</head>

<body class="popup">
<h1 class="">지방의회 담당자 목록</h1>
<!-- /.panel-heading -->
<div class="panel-body ">
	<div class="search">
		
		<select name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
			<option>전체</option>
			<option>국회직원</option>
			<option>국회의원</option>
			<option>지방의원</option>
			<option>지방의회담당자</option>
			<option>지자체담당자</option>
		</select>
		<input type="text" class="ip input-sm" style="width:350px;" />
		<button type="button" class="btn btn-primary" >검색</button>
		
	</div><!--//tc-->

	<div class="table-responsive" style="">
		<table class="table table-striped table-bordered table-hover ">
			

			 <thead>
				<tr>
					<th>선택</th>
					<th>담당자명</th>
					<th>담당자 ID</th>
					<th>지방의회 명</th>
					<th>등록일</th>
				</tr>
			 </thead>
			
			<tbody>
				<tr>	
					<th><input type="radio" class="ip" /></th>
					<td>김서울</td>
					<td>seoul</td>
					<td>서울특별시의회</td>
					<td>2014-07-28</td>
				</tr>

				
				
			</tbody>

			
		</table>
	</div>
	<!-- /.table-responsive -->
</div>
<!-- /.panel-body -->

<p class="tc">
	<button type="button" class="btn btn-primary">등록</button>
</p>

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
</div>


<p class="tc">
	<button type="button" class="btn btn-primary" onclick="javascript:window.close();">닫기</button>
</p>

</body>
</html>
