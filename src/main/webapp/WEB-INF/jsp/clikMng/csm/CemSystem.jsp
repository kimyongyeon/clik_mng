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
<title>정책정보 기관별 수집 내역</title>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--

/* ********************************************************
* SELECT BOX 검색
******************************************************** */
function fnSearching() {
	
	var varFrom = document.listForm;
	if(document.getElementById('schDt1').value == "" && document.getElementById('schDt2').value == ""
			 && varFrom.searchCondition2.value == "") {
			alert("검색기간 또는 수집기관을 선택하여 주세요.");
			return;
	}
	

	varFrom.action = '/csm/CemSystem.do';
	if(document.getElementById('schDt1').value != "" || document.getElementById('schDt2').value != "") {
		var schDt1 = varFrom.schDt1.value;
		var schDt2 = varFrom.schDt2.value;

		var iChkBeginDe = schDt1.split("-").join("") * 1;
		var iChkEndDe = schDt2.split("-").join("") * 1;

		if(iChkBeginDe > iChkEndDe || iChkEndDe < iChkBeginDe ){
			alert("게시시작일자는 게시종료일자 보다 클수 없고,\n게시종료일자는 게시시작일자 보다 작을수 없습니다. ");
			return;
		}

	}
	
	varFrom.submit();
}


/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(pageIndex) {
    var varForm = document.listForm;
    varForm.pageIndex.value = pageIndex;
    varForm.target="_self";
    varForm.action = "<c:url value='/csm/CemSystem.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
 
    varForm.action = "<c:url value='/csm/CemSystem.do'/>";
    
    varForm.submit();
}

/* ********************************************************
* 게시물 정렬 변경(셀렉트박스)
******************************************************** */
function fnChgOrder(val) {
	var varForm = document.listForm;
    varForm.action = "<c:url value='/csm/CemSystem.do'/>";
    varForm.submit();
}


/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnMngDetailView(mngrId) {

	var varForm = document.listForm;
	varForm.mngrId.value = mngrId;
	varForm.action = "<c:url value='/csm/CemSystem.do'/>";
	varForm.submit();
}

/* ********************************************************
* 엑셀다운로드
******************************************************** */
function fnExcelDownload() {
	var varForm = document.listForm;
    varForm.action = "<c:url value='/csm/CemSystemExcel.do'/>";
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
                    <h1 class="page-header">정책정보 기관별 수집 내역</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			
			
            
			<form name="listForm" method="post">
			<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
			<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${searchVO.pageSize}"/>" />
			
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
						<select name="searchCondition2" id="searchCondition2" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
							<option value="">수집기관 선택</option>
							<c:forEach items="${collectionOrgList}" var="x" varStatus="s">
							<option value="${x.siteId}" <c:if test="${searchCondition2 == x.siteId}">selected="selected"</c:if>><c:out value="${x.siteNm}" /></option>
							</c:forEach>
						</select>
						
						 <a href="#LINK" onclick="fnSearching(); return false;"><button type="button" class="btn btn-primary" >검색</button></a>
						 <a href="#LINK" onclick="fnExcelDownload(); return false;"><button type="button" class="btn btn-success" >EXCEL</button></a>
					</span>
				</div><!--//select_box-->


					<div class="page">
						총 건수 : <fmt:formatNumber value="${paginationInfo.totalRecordCount}" type="number"/> 건
						<span>
							정렬 : 
							<select id="sortOrder" name="sortOrder" onchange="fnChgOrder(this.value)">
								<option value="">정렬기준</option>
								<option value="siteNm_DESC" <c:if test="${searchVO.sortOrder == 'siteNm_DESC'}">selected</c:if>>수집기관 내림차순</option>
								<option value="siteNm_ASC" <c:if test="${searchVO.sortOrder == 'siteNm_ASC'}">selected</c:if>>수집기관 오름차순</option>
<%-- 								<option value="regDate_DESC" <c:if test="${searchVO.sortOrder == 'regDate_DESC'}">selected</c:if>>수집일자 내림차순</option> --%>
<%-- 								<option value="regDate_ASC" <c:if test="${searchVO.sortOrder == 'regDate_ASC'}">selected</c:if>>수집일자 오름차순</option> --%>
								<option value="collectionCnt_DESC" <c:if test="${searchVO.sortOrder == 'collectionCnt_DESC'}">selected</c:if>>수집건수 내림차순</option>
								<option value="collectionCnt_ASC" <c:if test="${searchVO.sortOrder == 'collectionCnt_ASC'}">selected</c:if>>수집건수 오름차순</option>
							</select>
							&nbsp;&nbsp;&nbsp;
							출력건수 :
							<select title="쪽당출력건수" id="pageUnit" name="pageUnit" onchange="fnChgListCnt(this.value)">
		                        <option value='10' <c:if test="${searchVO.pageUnit == '10'}">selected</c:if>>10</option>
		                        <option value='30' <c:if test="${searchVO.pageUnit == '30'}">selected</c:if>>30</option>
		                        <option value='50' <c:if test="${searchVO.pageUnit == '50'}">selected</c:if>>50</option>
		                        <option value='100' <c:if test="${searchVO.pageUnit == '100'}">selected</c:if>>100</option>
		                    </select>
						</span>
					</div>


			</form>


				<table class="table table-striped table-bordered table-hover "  id="dataTable">
					<colgroup>
						<col width="5%" />
						<col width="" />
						<col width="15%" />
<%-- 						<col width="10%" /> --%>
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>수집기관</th>
							<th>수집건수</th>
<!-- 							<th>수집일자</th> -->
						</tr>
					</thead>
					<tbody>
					<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
					
					<c:if test="${fn:length(orgCollectionList) == 0}">
					<tr>
					<td colspan="3">
						<spring:message code="common.nodata.msg" />
					</td>
					</tr>
					</c:if>
					 <%-- 데이터를 화면에 출력해준다 --%>
					<c:forEach items="${orgCollectionList}" var="x" varStatus="s">
					<tr>
						<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageUnit + s.count}"/></td>
						<td><c:out value="${x.siteNm}" /></td>
						<td><fmt:formatNumber type="number" value="${x.collectionCnt}"  /></td>
<%-- 						<td>${x.regDate}</td> --%>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(orgCollectionList) != 0}">
					<tr>
						<td></td>
						<td><b>합계</b></td>
						<td><b><fmt:formatNumber type="number" value="${totalSum}"  /></td>
<!-- 						<td></td> -->
					</tr>
					</c:if>
					</tbody>
				</table>
                      
				<div align="center">
					<div>
						<ul class="pagination">
							<ui:pagination paginationInfo="${paginationInfo}"
									type="image"
									jsFunction="fn_paging"
									/>
						</ul>
					</div>
				</div>

				 <!-- /.panel-body -->
                     
		</div>
                    <!-- /.panel .chat-panel -->
                    
                    
</body>
</html>
