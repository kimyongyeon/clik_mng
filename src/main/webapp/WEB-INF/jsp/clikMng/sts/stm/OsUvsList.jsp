<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>이용자 방문 통계 조회</title>
<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function(){
	
	setCal("schDt1","schDt2");
	$("#schDt1").datepicker("setDate", new Date());
	$("#schDt2").datepicker("setDate", new Date());
	
	if("${searchVO.schDt1}" != "" && "${searchVO.schDt2}" != ""){
		var schDt1 = "${searchVO.schDt1}";
		var schDt2 = "${searchVO.schDt2}";
		
		$("#schDt1").datepicker("setDate", schDt1);
		$("#schDt2").datepicker("setDate", schDt2);
	}
});

function lodding() {
	$(".spinner").show();
}

/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	lodding();
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sts/stm/OsUvsList.do'/>";
	varForm.submit();
}

/* ********************************************************
* 엑셀 다운로드
******************************************************** */
function fnExcel() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sts/stm/selectOSUvsExcel.do'/>";
	varForm.submit();
}
</script>
<style type="text/css">
.tr{
	margin-bottom: 20px;
}
</style>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
	<div id="page-wrapper">
           <div class="row">
               <div class="col-lg-12">
                   <h1 class="page-header">OS별 이용자 방문통계</h1>
               </div>
               <!-- /.col-lg-12 -->
           </div>

           <!-- /.row -->
		<h2>OS별 이용자 방문통계</h2>
		<form id="listForm" name="listForm" method="post">
		<table class="table table-striped table-bordered table-hover" >
			<colgroup>
				<col width="20%" />
				<col width="" />
			</colgroup>
			
			<tr>
				<th>방문기간</th>
				<td>
					<input type="text" name="schDt1" id="schDt1" value="" class="input-sm ip" style="width:150px;" />
					&nbsp;~&nbsp;
					<input type="text" name="schDt2" id="schDt2" value="" class="input-sm ip" style="width:150px;" />
					
					<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-success">오늘</button></a>
					<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-danger">일주일</button></a>
					<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-primary">한달</button></a>
				</td>
			</tr>
		</table>

		<div class="tr">
			<a href="#none"><button type="button" class="btn btn-primary" onclick="fnSearch();">검색</button></a>
			<a href="#none"><button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button></a>
		</div>
		</form>
		<table class="table table-striped table-bordered table-hover tc" >
			<colgroup>
				<col width="30%" />
				<col width="30%" />
				<col width="" />
				<!-- <col width="" /> -->
			</colgroup>
			
			<thead>
				<tr>
					<th>구분</th>
					<th>OS</th>
					<th>방문자수</th>
					<!-- <th>소계</th> -->
				</tr>
			</thead>
			
			<tbody>
				<c:set var="bf_platform" value="" />
				<c:set var="access_sum" value="0" />
				<c:set var="platform_index" value="0" />
				<c:forEach var="x" items="${logList}" varStatus="s">
					<tr>
						<c:if test="${bf_platform ne x.platform}">
							<td id="platform_${platform_index}">${x.platform}</td>
							<c:set var="platform_index" value="${platform_index + 1}" />
						</c:if>
						<c:if test="${bf_platform eq x.platform}">
							<td></td>
						</c:if>
						<td>${x.os}</td>
						<td><fmt:formatNumber value="${x.access_cnt}" groupingUsed="true"/></td>
						<!-- <td>0</td> -->
					</tr>
					<c:set var="bf_platform" value="${x.platform}" />
					<c:set var="access_sum" value="${access_sum + x.access_cnt}" />
				</c:forEach>
				
				<tr>
					<td colspan="2">합계</td>
					<td class="r"><b><fmt:formatNumber value="${access_sum}" groupingUsed="true"/></b></td>
					<!-- <td class="r"><b>0</b></td> -->
				</tr>
			</tbody>
		</table>
	</div><!--//page-wrapper-->
	
	<div class="spinner"></div>
</body>
</html>