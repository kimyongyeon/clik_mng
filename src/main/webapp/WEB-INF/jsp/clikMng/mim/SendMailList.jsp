<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>메일발송 내역</title>

<script type="text/javaScript" language="javascript">
/* ********************************************************
* 달력
******************************************************** */


/* ********************************************************
* 발송내역 검색
******************************************************** */
function fnSearch() {
	var varForm = document.listForm;
	
	if($("#searchKeyword").val() == "" && $("#schDt1").val() == "" && $("#schDt2").val() == ""){
		alert('검색어 또는 기간을 입력해 주세요.');
		return;
	} else if ($("#searchKeyword").val() == null && ($("#schDt1").val() != "" && $("#schDt2").val() == "") 
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
/* 
	$("#schDt1").val(ntceBgndeYYYMMDD.split("-").join(""));
	$("#schDt2").val(ntceEnddeYYYMMDD.split("-").join(""));
 */
	varForm.action = "<c:url value='/mim/SendMailList.do'/>";
	varForm.submit();
	
}


/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.pageIndex.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/mim/SendMailList.do'/>";
    varForm.submit();
}


/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnSelectCountPg(val) {
	var varForm = document.listForm;
    varForm.action = "<c:url value='/mim/SendMailList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 현재로 부터 한달 셋팅
******************************************************** */
function fnSetOneMonth() {
	
	var currentDate = new Date();
	var currentDay; // 오늘 날짜
	
	currentDay = currentDate.getFullYear() + "-" + leadingZeros(currentDate.getMonth()+1, 2) + "-" + leadingZeros(currentDate.getDate(), 2);
	$("#schDt2").val(currentDay);
	
	var lastMonth; // 한달 전 날짜 
	lastMonth = currentDate.getFullYear() + "-" + leadingZeros(currentDate.getMonth(), 2) + "-" + leadingZeros(currentDate.getDate(), 2);
	$("#schDt1").val(lastMonth);
	
}

/* ********************************************************
* 현재로 부터 일주일 셋팅
******************************************************** */
function fnSetOneWeek() {
	
	var currentDate = new Date();
	var currentDay; // 오늘 날짜
	
	currentDay = currentDate.getFullYear() + "-" + leadingZeros(currentDate.getMonth()+1, 2) + "-" + leadingZeros(currentDate.getDate(), 2);
	$("#schDt2").val(currentDay);
	
	var lastWeek; // 한주 전 날짜 
	lastWeek = currentDate.getFullYear() + "-" + leadingZeros(currentDate.getMonth(), 2) + "-" + leadingZeros((currentDate.getDate() - 7), 2);
	$("#schDt1").val(lastWeek);
}

/* ********************************************************
* 달력 해당월 자리수 맞추기
******************************************************** */
function leadingZeros(n, digits) {
	
	var zero = '';
	
	n = n.toString();

	if (n.length < digits) {
		for (i = 0; i < digits - n.length; i++) {
			zero += '0';
	    }
	}
	
	return zero + n;
}

/* ********************************************************
* enter key event
******************************************************** */
function press(event) {
	if (event.keyCode==13) {
		actionLogin();
	}
}

-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form name="listForm" id="listForm" method="post">
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">메일 발송 내역</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>메일 발송 내역</h2>
            
				<div class="search">
					<input type="text" name="schDt1" id="schDt1" value="<c:out value="${searchVO.schDt1}" />" class="input-sm ip" style="width:150px;" />
					&nbsp;~&nbsp;
					<input type="text" name="schDt2" id="schDt2" value="<c:out value="${searchVO.schDt2}" />" class="input-sm ip" style="width:150px;" />
	 				<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-success">한달</button></a>
					<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-danger">일주일</button></a>
					<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-primary">오늘</button></a>
					<a href="#none"><button type="button" class="btn btn-danger" onclick="window.open('http://ems.nanet.go.kr')">EMS바로가기</button></a>
				</div>

							

				<div class="select_box">
					<span>
						<select name="searchCondition" id="searchCondition" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
						
						<option value="selSj" <c:if test="${searchVO.searchCondition == 'selSj' }">selected="selected" </c:if>>제목</option>
						<!--  <option value="selCn" <c:if test="${searchVO.searchCondition == 'selCn' }">selected="selected" </c:if>>내용</option> -->
					</select>
					<input type="text" name="searchKeyword" id="searchKeyword" class="ip input-sm input-search" value="<c:out value="${searchKeyword}" />" />
					<button type="button" class="btn btn-primary" onclick="fnSearch(); return false;" onkeypress="press(event);"><spring:message code="button.search" /></button>
					</span>
				</div><!--//select_box-->
				
				<div class="page">
					총 건수 : <c:out value="${resultCnt}" />건
	
					<span>
						출력건수
						<select name="selectCountPg" id="selectCountPg" aria-controls="dataTables-example" class=" input-sm" onchange="fnSelectCountPg(this.value); return false;">
							<option value="10" <c:if test="${empty selectCountperpg || selectCountperpg == '' || selectCountperpg == '10'}">selected</c:if>>10</option>
							<option value="30" <c:if test="${selectCountperpg == '30'}">selected</c:if>>30</option>
							<option value="50" <c:if test="${selectCountperpg == '50'}">selected</c:if>>50</option>
							<option value="100" <c:if test="${selectCountperpg == '100'}">selected</c:if>>100</option>
						</select>
					</span>
				
				</div>

				<table class="table table-striped table-bordered table-hover "  id="">
					<thead>
						<tr>
							<th style="width:5%">번호</th>
							<th>제목</th>
							<th>발송건수(수신건수)</th>
							<th>발송일시</th>
						
						</tr>
					</thead>
					<tbody>
					<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
					<c:if test="${fn:length(resultList) == 0}">
						<tr>
							<td colspan="4"><spring:message code="common.nodata.msg" /></td>
						</tr>
					</c:if>
					<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
								
					<c:forEach var="x" items="${resultList}" varStatus="s">								
						<tr>
							<td>${(searchVO.pageIndex-1) * searchVO.pageSize + s.count}</td>
							<td><c:out value="${x.sj}" /></td>
							<td><c:out value="${x.sendCnt}" />(<c:out value="${x.sendRejectCnt}" />)</td>
							<td><c:out value="${x.frstRegistPnttm }" /></td>
						</tr>
					</c:forEach>						
					</tbody>
				</table>

				<c:if test="${not empty resultList}">
			    <div align="center" >
			    	<ul class="pagination">
				        <ui:pagination paginationInfo = "${paginationInfo}"
				            type="image"
				            jsFunction="fn_paging"
				            />
				     </ul>
			    </div>
				</c:if>

				<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
		        <input type="hidden" name="currentPageNo" value="<c:out value='${ paginationInfo.currentPageNo }'/>" >	
        </div>
        <!-- /#page-wrapper -->
</form>        
</body>
</html>
