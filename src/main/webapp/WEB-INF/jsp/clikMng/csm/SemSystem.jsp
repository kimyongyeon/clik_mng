<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>수집 대비 서비스 내역</title> 

<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--

/* ********************************************************
* SELECT BOX 검색
******************************************************** */
function fnSearching() {
	var varFrom = document.listForm;
	
	if(document.getElementById('schDt1').value == "" && document.getElementById('schDt2').value == ""
			&& varFrom.searchCondition.value == "") {
		alert("검색기간 또는 카테고리를 선택하여 주세요.");
		return;
	}
	
	if(document.getElementById('schDt1').value != "" || document.getElementById('schDt2').value != "") {
		var schDt1 = varFrom.schDt1.value;
		var schDt2 = varFrom.schDt2.value;

		var iChkBeginDe = schDt1.split("-").join("") * 1;
		var iChkEndDe = schDt2.split("-").join("") * 1;

		if(iChkBeginDe > iChkEndDe || iChkEndDe < iChkBeginDe ){
			alert("게시시작일자는 게시종료일자 보다 클수 없고,\n게시종료일자는 게시시작일자 보다 작을수 없습니다. ");
			return;
		}

		//varFrom.schDt1.value = schDt1.split("-").join("");
		//varFrom.schDt2.value = schDt2.split("-").join("");
	}

	varFrom.action = '/csm/SemSystem.do';
	varFrom.submit();
}

-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">정책정보 수집 대비 서비스 내역</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			
			
            
			<form name="listForm" method="post">
				<div class="search">
					<input type="text" name="schDt1" id="schDt1" value="<c:out value="${searchVO.schDt1}" />" class="input-sm ip" style="width:150px;" />
					&nbsp;~&nbsp;
					<input type="text" name="schDt2" id="schDt2" value="<c:out value="${searchVO.schDt2}" />" class="input-sm ip" style="width:150px;" />
	 				<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-default  btn-default1">한달</button></a>
					<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-default  btn-default2">일주일</button></a>
					<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-default  btn-default3">오늘</button></a>
	 				<script>setCal("schDt1","schDt2");</script>
				</div>

				<div class="select_box">
					<span>
						<select name="searchCondition" id="searchCondition" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
							<option value="">수집카테고리 선택</option>
							<c:forEach items="${categoryList}" var="x" varStatus="s">
							<option value="${x.docType}" <c:if test="${searchCondition == x.docType}">selected="selected"</c:if>><c:out value="${x.doctypeName}" /></option>
							</c:forEach>					
						</select>
					
						<a href="#LINK" onclick="fnSearching(); return false;"><button type="button" class="btn btn-primary" >검색</button></a>	
					</span>
				</div><!--//select_box-->
				
				<div class="page">
					총 건수 : <c:out value="${paginationInfo.totalRecordCount}" />건
					<!-- 
					<span>
						출력건수
						<select name="listCnt" id="listCnt" aria-controls="dataTables-example" class=" input-sm">
							<option value="10" <c:if test="${mdmSearchVO.listCnt == '10'}"> selected="selected"</c:if>>10</option>
							<option value="30" <c:if test="${mdmSearchVO.listCnt == '30'}"> selected="selected"</c:if>>30</option>
							<option value="50" <c:if test="${mdmSearchVO.listCnt == '50'}"> selected="selected"</c:if>>50</option>
							<option value="100" <c:if test="${mdmSearchVO.listCnt == '100'}"> selected="selected"</c:if>>100</option>
						</select>
					</span>
					 -->
				</div>
				
			</form>

				<table class="table table-striped table-bordered table-hover "  id="dataTable">
					<colgroup>
						<col width="5%" />					
						<col width="30%" />
						<col width="" />
						<col width="" />
					
					
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>수집카테고리</th>
							<th>수집건수</th>
							<th>서비스건수</th>
						</tr>
					</thead>
					<tbody>
					<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
					
					<c:if test="${fn:length(collectionServiceList) == 0}">
					<tr>
					<td colspan="4">
						<spring:message code="common.nodata.msg" />
					</td>
					</tr>
					</c:if>
					 <%-- 데이터를 화면에 출력해준다 --%>
					<c:forEach items="${collectionServiceList}" var="x" varStatus="s">
					<tr>
						<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + s.count}"/></td>
						<td><c:out value="${x.doctypeName}" /></td>
						<td><fmt:formatNumber type="number" value="${x.colCnt}"  /></td>
						<td><fmt:formatNumber type="number" value="${x.svcCnt}"  /></td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(collectionServiceList) != 0}">
					<tr>
						<td></td>
						<td><b>합계</b></td>
						<td><b><fmt:formatNumber type="number" value="${totalColSum}"  /></td>
						<td><b><fmt:formatNumber type="number" value="${totalSvnSum}"  /></td>
					</tr>
					</c:if>
					</tbody>
				</table>

				 <!-- /.panel-body -->

                    </div>
                    <!-- /.panel .chat-panel -->

</body>
</html>
