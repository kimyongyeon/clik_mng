<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"		uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>인증키 이용내역 조회</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--

/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	
	if ( ($("#schDt1").val() != "" && $("#schDt2").val() == "") 
			|| ($("#schDt1").val() == "" && $("#schDt2").val() != "")){
		alert("기간을 정상적으로 입력해 주세요.");
		return;
	}

	// 달력 validate
	var ntceBgndeYYYMMDD = document.getElementById('schDt1').value;
	var ntceEnddeYYYMMDD = document.getElementById('schDt2').value;
	
	var iChkBeginDe = ntceBgndeYYYMMDD.split("-").join("") * 1;
	var iChkEndDe = ntceEnddeYYYMMDD.split("-").join("") * 1;
	
	if(iChkBeginDe > iChkEndDe || iChkEndDe < iChkBeginDe ){
		alert("게시시작일자는 게시종료일자 보다 클수 없고,\n게시종료일자는 게시시작일자 보다 작을수 없습니다. ");
		return;
	}
	
	//$("#schDt1").val(ntceBgndeYYYMMDD.split("-").join(""));
	//$("#schDt2").val(ntceEnddeYYYMMDD.split("-").join(""));
	
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rcm/AulList.do'/>";
	varForm.submit();
	
}

/* ********************************************************
* 엑셀 다운로드
******************************************************** */
function fnExcel() {
	if( !confirm("엑셀 다운로드 하시겠습니까?") ) {
		return false;
	}
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rcm/AulListExcel.do'/>";
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
	        <h1 class="page-header">OPENAPI 이용내역</h1>
	    </div>
	    <!-- /.col-lg-12 -->
	</div>

          <!-- /.row -->
	<h2>OPENAPI 이용내역</h2>
	<form name="listForm" action="" method="post">
		<div class="cont mb20">
			<input type="text" name="schDt1" id="schDt1" value="<c:out value="${searchVO.schDt1}" />" class="input-sm ip" style="width:150px;" />&nbsp;~&nbsp;
			<input type="text" name="schDt2" id="schDt2" value="<c:out value="${searchVO.schDt2}" />" class="input-sm ip" style="width:150px;" />
			
			<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-success">한달</button></a>
			<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-danger">일주일</button></a>
			<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-primary">오늘</button></a>
			
			<script>setCal("schDt1","schDt2");</script>
			
			<span class="fr">	
			<button type="button" class="btn btn-primary"  onclick="fnSearch(); return false;">검색</button>
			<button type="button" class="btn btn-success"  onclick="fnExcel(); return false;">excel</button>
			</span>
		</div><!--//tc-->
	</form>				
		
	<table class="table table-striped table-bordered table-hover tr" >
		<colgroup>
			<col width="10%" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
		</colgroup>
		<thead>
			<tr>
				<th rowspan="2" class="tc">이용기관</th>
				<th colspan="3" class="tc">OPENAPI 이용 트래픽</th>
				<th rowspan="2" class="tc">합계</th>
			</tr>
			<tr>
				<th class="tc">회의록</th>
				<th class="tc">의안</th>
				<th class="tc">의원</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="minutesTotal" value="0"/>
			<c:set var="billTotal" value="0"/>
			<c:set var="asembyinfoTotal" value="0"/>
			<c:forEach var="resultList" items="${resultList}" varStatus="status">
			<tr>
				<c:set var="minutesTotal" value="${minutesTotal + resultList.minutesCo}"/>
				<c:set var="billTotal" value="${billTotal + resultList.billCo}"/>
				<c:set var="asembyinfoTotal" value="${asembyinfoTotal + resultList.assemblyinfoCo}"/>						
				<td>${resultList.reqstInsttNm}</td>
				<td><fmt:formatNumber value="${resultList.minutesCo}"/></td>
				<td><fmt:formatNumber value="${resultList.billCo}"/></td>
				<td><fmt:formatNumber value="${resultList.assemblyinfoCo}"/></td>			
				<td><fmt:formatNumber value="${resultList.minutesCo + resultList.billCo + resultList.assemblyinfoCo}"/></td>
			</tr>
			</c:forEach>
			<tr>						
				<td>합계</td>
				<td class="b"><fmt:formatNumber value="${minutesTotal}"/></td>
				<td class="b"><fmt:formatNumber value="${billTotal}"/></td>
				<td class="b"><fmt:formatNumber value="${asembyinfoTotal}"/></td>			
				<td class="r"><fmt:formatNumber value="${minutesTotal + billTotal + asembyinfoTotal}"/></td>
			</tr>	
		</tbody>
	</table>
</div><!--//page-wrapper-->

</body>
</html>
