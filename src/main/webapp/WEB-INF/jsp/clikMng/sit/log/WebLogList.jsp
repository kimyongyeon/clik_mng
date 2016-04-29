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
<title>웹 로그 목록</title>

<script type="text/javaScript" defer="defer">

/* ********************************************************
* 탭클릭시 화면전환
******************************************************** */
function fn_tabClick(sel_name) {
	if (sel_name == 'ACS') {
		location.href='<c:url value="/sit/log/LogMngList.do" />';
	} else {
		location.href='<c:url value="/sit/log/WebLogList.do" />';
	}
}



/* ********************************************************
* 접속로그 검색
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
	var ntceBgndeYYYMMDD = $('#schDt1').val();
	var ntceEnddeYYYMMDD = $('#schDt2').val();

	var iChkBeginDe = ntceBgndeYYYMMDD.split("-").join("") * 1;
	var iChkEndDe = ntceEnddeYYYMMDD.split("-").join("") * 1;

	if(iChkBeginDe > iChkEndDe || iChkEndDe < iChkBeginDe ){
		alert("게시시작일자는 게시종료일자 보다 클수 없고,\n게시종료일자는 게시시작일자 보다 작을수 없습니다. ");
		return;
	}

	
	varForm.action = "<c:url value='/sit/log/WebLogList.do' />";
	varForm.submit();
	
}

/* ********************************************************
* 엑셀 다운로드
******************************************************** */
function fnExcel() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sit/log/WebLogExcel.do' />";
	varForm.submit();
}


/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.pageIndex.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/sit/log/WebLogList.do'/>";
    varForm.submit();
    
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sit/log/WebLogList.do'/>";
	varForm.submit();
}
-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

<DIV id="page-wrapper">
	<div class="row">
    	<div class="col-lg-12">
            <h1 class="page-header">로그 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    
    <!-- /row -->
			<DIV class="panel-heading">웹 로그 목록</DIV>
			
			<ul class="nav nav-tabs">
				<li class="">
					<a href="#LINK" onClick="javascript:fn_tabClick('ACS')" data-toggle="tab">접속로그</a>
                </li>
                <li class="active">
                	<a href="#LINK" onClick="javascript:fn_tabClick('WEB')"  data-toggle="tab">웹로그</a>
                </li>
            </ul>	
			
			<!-- /.panel-heading -->			
				<form:form name="listForm" action="" method="post">
					<div class="search">


					<input type="text" name="schDt1" id="schDt1" value="<c:out value="${searchVO.schDt1}" />" class="input-sm ip" style="width:150px;" />
					&nbsp;~&nbsp;
					<input type="text" name="schDt2" id="schDt2" value="<c:out value="${searchVO.schDt2}" />" class="input-sm ip" style="width:150px;" />
					
	 				<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default1">한달</button></a>
					<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default2">일주일</button></a>
					<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default3">오늘</button></a>
	 				<script>setCal("schDt1","schDt2");</script>
				    	
							    		
					</div>

					<div class="select_box">
						<span>
							URL : 
							<input name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>' title="검색어 입력">
							<button type="button" class="btn btn-primary" onclick="fnSearch(); return false;"><spring:message code="button.search" /></button>
							<a href="#none"><button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button></a> 
						</span>
					</div><!--//select_box-->
			
					
					<div class="page">
					
						총 건수 :  <fmt:formatNumber value="${totCnt}" groupingUsed="true"/>건
			
						<span>
							출력건수
							<select id="pageUnit" name="pageUnit"  aria-controls="dataTables-example" class=" input-sm" onchange="fnChgListCnt(this.value);">
								<option value="10" <c:if test="${searchVO.pageUnit == '10' }">selected</c:if>>10</option>
								<option value="30" <c:if test="${searchVO.pageUnit == '30' }">selected</c:if>>30</option>
								<option value="50" <c:if test="${searchVO.pageUnit == '50' }">selected</c:if>>50</option>
								<option value="100" <c:if test="${searchVO.pageUnit == '100' }">selected</c:if>>100</option>
							</select>
						</span>
			
					</div>
					
					<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="로그 관리 테이블입니다.로그ID, 발생일자, 로그유형, 사용자, 사용자 IP 등 정보를 담고 있습니다.">
						<thead>
						<tr>
							<th class="title" width="10%" scope="col" nowrap="nowrap">번호</th>
							<th class="title" width="30%" scope="col" nowrap="nowrap">로그ID</th>
							<th class="title" width="15%" scope="col" nowrap="nowrap">발생일자</th>
							<th class="title" width="15%" scope="col" nowrap="nowrap">발생시간</th>
							<th class="title" width="10%" scope="col" nowrap="nowrap">URL</th>
							<th class="title" width="15%" scope="col" nowrap="nowrap">사용자IP</th>
						</tr>
					 	</thead>

					 	<tbody>
						<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
						<c:if test="${fn:length(resultList) == 0}">
							<tr>
								<td class="lt_text3" colspan="6"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
					 
					 	<c:forEach var="x" items="${resultList}" varStatus="status">
						 	<tr>
							    <td class="lt_text3" nowrap="nowrap">${(searchVO.pageIndex-1) * searchVO.pageUnit + status.count}</td>
							    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.requestId}" /></td>
							    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.occrrncDeDate}" /></td>
							    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.occrrncDeTime}" /></td>
							    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.requestUrl}" /></td>
							    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.requesterIp}" /></td>
						 	</tr>
					 	</c:forEach>
					 	</tbody>
					</table>
					
					<c:if test="${not empty resultList}">
					<div align="center">
					    <div>
					    	<ul class="pagination">
						        <ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_paging" />
						     </ul>
					    </div>
					</div>
					</c:if>

					<input type="hidden" name="pageIndex" value="1"/>
			        <input type="hidden" name="currentPageNo" value="<c:out value='${ paginationInfo.currentPageNo }'/>" >
			        </form:form>
				
			</div>
			<!-- /panel body -->
		</div>
		<!-- /.panel panel-default -->					
	</div>		
	<!-- /.MAIN -->		
</div>	
<!-- /page-wrapper -->	
</body>
</html>
