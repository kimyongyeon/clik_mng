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
<title>그룹 목록</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--


/* ********************************************************
* 접속로그 검색
******************************************************** */
function fnSearch() {
	var varForm = document.listForm;	
	
	if($("#searchKeyword").val() == "" && $("#selectBgdate").val() == "" && $("#selectEndate").val() == ""){
		alert('검색어 또는 기간을 입력해 주세요.');
		return;
	} else if ($("#searchKeyword").val() == null && ($("#selectBgdate").val() != "" && $("#selectEndate").val() == "") 
			|| ($("#selectBgdate").val() == "" && $("#selectEndate").val() != "")){
			alert("기간을 정상적으로 입력해 주세요.");
			return;
	}
	
	
	// 달력 validate
	var ntceBgndeYYYMMDD = document.getElementById('selectBgdate').value;
	var ntceEnddeYYYMMDD = document.getElementById('selectEndate').value;

	var iChkBeginDe = ntceBgndeYYYMMDD.split("-").join("") * 1;
	var iChkEndDe = ntceEnddeYYYMMDD.split("-").join("") * 1;

	if(iChkBeginDe > iChkEndDe || iChkEndDe < iChkBeginDe ){
		alert("게시시작일자는 게시종료일자 보다 클수 없고,\n게시종료일자는 게시시작일자 보다 작을수 없습니다. ");
		return;
	}

	$("#selectBgdate").val(ntceBgndeYYYMMDD.split("-").join(""));
	$("#selectEndate").val(ntceEnddeYYYMMDD.split("-").join(""));
	
	varForm.action = "<c:url value='/sit/log/LogMngList.do' />";
	varForm.submit();
	
}

/* ********************************************************
* 그룹 등록
******************************************************** */
function fncAddGroupInsert() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/mim/GroupRegist.do' />";

	varForm.submit();
}

/* ********************************************************
* 그룹 검색
******************************************************** */
function fnSearch() {
	
	var varForm = document.listForm;	
	
	if($("#searchKeyword").val() == "" && $("#selectBgdate").val() == "" && $("#selectEndate").val() == ""){
		alert('검색어 또는 기간을 입력해 주세요.');
		return;
	} else if ($("#searchKeyword").val() == null && ($("#selectBgdate").val() != "" && $("#selectEndate").val() == "") 
			|| ($("#selectBgdate").val() == "" && $("#selectEndate").val() != "")){
			alert("기간을 정상적으로 입력해 주세요.");
			return;
	}
	
	varForm.action = "<c:url value='/mim/GroupList.do' />";
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
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.pageIndex.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/uss/mng/MngList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
    varForm.action = "<c:url value='/mim/GroupList.do'/>";
    varForm.submit();
}

/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnGroupDetailView(emailGroupId) {

	var varForm = document.listForm;
	varForm.emailGroupId.value = emailGroupId;
	varForm.action = "<c:url value='/mim/GroupDetailView.do' />";
	varForm.submit();
}
-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form id="listForm" name="listForm" method="post" >

		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">그룹 설정</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>그룹 목록</h2>
            
			<div class="select_box">
				<span>
				<select name="searchCondition" id="searchCondition" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option value="0">전체</option>
					<option value="selGroupNm" <c:if test="${searchVO.searchCondition == 'selGroupNm' }">selected="selected" </c:if> >그룹명</option>
					<option value="selDc" <c:if test="${searchVO.searchCondition == 'selDc' }">selected="selected" </c:if>>설명</option>
				</select>
				<input type="text" id="searchKeyword" name="searchKeyword" value="<c:if test="${empty searchVO.searchKeyword }"></c:if><c:if test="${!empty searchVO.searchKeyword }"><c:out value='${searchVO.searchKeyword}'/></c:if>" class="ip input-sm input-search" />
				<button type="button" class="btn btn-primary"  onclick="fnSearch(); return false;"><spring:message code="button.search" /></button>
				</span>
			
			</div><!--//tc-->

			<div class="page">
				총 건수 : <c:out value="${paginationInfo.totalRecordCount}" /> 건

				<span>
					출력건수
						<select id="pageUnit" name="pageUnit" class="input-sm" onchange="fnChgListCnt(this.value);">
							<option value="10" <c:if test="${searchVO.pageUnit == '10' }">selected</c:if>>10</option>
							<option value="30" <c:if test="${searchVO.pageUnit == '30' }">selected</c:if>>30</option>
							<option value="50" <c:if test="${searchVO.pageUnit == '50' }">selected</c:if>>50</option>
							<option value="100" <c:if test="${searchVO.pageUnit == '100' }">selected</c:if>>100</option>
						</select>
				</span>
			
			</div>
				
			<table class="table table-striped table-bordered table-hover "   id="">
				<colgroup>
						<col width="5%" />
						<col width="30%"/>
						<col width="55%"/>						
						<col width="10%"/>
					 </colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>그룹명</th>
						<th>설명</th>
						<th>등록일</th>
					
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
						<td><c:out value="${x.groupNm }" /></td>
						<td><a href="#LINK" onclick="fnGroupDetailView('<c:out value="${x.emailGroupId }" />')" ><c:out value="${x.dc}" /></a></td>
						<td><c:out value="${x.frstRegistPnttm }" /></td>
					</tr>
				</c:forEach>	
				</tbody>
			</table>

			<p class="tr">
				<button type="button" class="btn btn-primary" onclick="fncAddGroupInsert();">등록</button>
			</p>
			

			<c:if test="${not empty resultList}">
				<div align="center">
					<div>
				    	<ul class="pagination">
					        <ui:pagination paginationInfo = "${paginationInfo}"
					            type="image"
					            jsFunction="fn_paging"
					            />
					     </ul>
		    		</div>
		    	</div>
			</c:if>

			<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
	        <input type="hidden" name="currentPageNo" value="<c:out value='${ paginationInfo.currentPageNo }'/>" >
	        <input type="hidden" name="emailGroupId" id="emailGroupId" />
	        
        </div>
        <!-- /#page-wrapper -->
</form>
</body>
</html>
