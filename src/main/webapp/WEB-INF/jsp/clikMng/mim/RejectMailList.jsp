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
<title>메일수신 거부 대상자 목록</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--


/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	
	if($("#searchKeyword").val() == "" || $("#searchKeyword").val() == null) {
		alert('검색어를 입력해 주세요.');
		return; 
	}
	
	var varForm = document.listForm;
	varForm.action = "<c:url value='/mim/RejectMailList.do' />";
	varForm.submit();
	
}


/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.pageIndex.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/mim/RejectMailList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnSelectCountPg(val) {
	var varForm = document.listForm;
    varForm.action = "<c:url value='/mim/RejectMailList.do'/>";
    varForm.submit();
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
                    <h1 class="page-header">메일 수신 거부 내역</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>메일 수신 거부 내역 대상자 목록</h2>
            
			<div class="select_box">
				
				<span>
					<select name="searchCondition" id="searchCondition" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
						<option value="0">전체</option>
						<option value="selJob" <c:if test="${searchVO.searchCondition == 'selJob'}">selected="selected" </c:if>>직업</option>
						<option value="selNm" <c:if test="${searchVO.searchCondition == 'selNm'}">selected="selected" </c:if>>이름</option>
						<option value="selEmail" <c:if test="${searchVO.searchCondition == 'selEmail'}">selected="selected" </c:if>>이메일</option>
					</select>
					<input type="text" name="searchKeyword" id="searchKeyword" class="ip input-sm input-search" value="<c:out value="${searchKeyword}" />" />
					<button type="button" class="btn btn-primary" onclick="fnSearch(); return false;" ><spring:message code="button.search" /></button>
				</span>
			
			</div><!--//tc-->
				
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
						<th>직업</th>
						<th>이름</th>
						<th>이메일</th>
						<th>수신거부일시</th>
					
					</tr>
				</thead>
				<tbody>
				<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
				<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="5"><spring:message code="common.nodata.msg" /></td>
					</tr>
				</c:if>
				<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>				
				<c:forEach var="x" items="${resultList}" varStatus="s">								
					<tr>
						<td>${(searchVO.pageIndex-1) * searchVO.pageSize + s.count}</td>
						<td><c:out value="${x.rejectRcverJob}" /></td>
						<td><c:out value="${x.rejectRcverNm}" /></td>
						<td><c:out value="${x.rejectRcverEmail}" /></td>
						<td><c:out value="${x.rejectRecptnPnttm}" /></td>
					</tr>
				</c:forEach>	
				</tbody>
			</table>

			<c:if test="${not empty resultList}">
		    <div align="center">
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
