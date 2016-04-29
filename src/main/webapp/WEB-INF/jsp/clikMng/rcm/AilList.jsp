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
<title>인증키 발급내역 목록</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--


	
/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	
// 	if($("#searchKeyword").val() == "" || $("#searchKeyword").val() == null) {
// 		alert('검색어를 입력해 주세요.');
// 		return; 
// 	}
	
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rcm/AilList.do'/>";
	varForm.submit();
	
}


/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.currentPageNo.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/rcm/AilList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
	varForm.pageIndex.value = '1';
	varForm.pageUnit.value = val;
    varForm.action = "<c:url value='/rcm/AilList.do'/>";
    varForm.submit();
}

/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnMngDetailView(unityId) {

	var varForm = document.listForm;
	varForm.unityId.value = unityId;
	varForm.action = "<c:url value='/rcm/AilDetail.do'/>";
	varForm.submit();
}

function linkPage(pageNo){
    //document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/rcm/AilList.do'/>";
    document.listForm.submit();
}
-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">OPENAPI 신청내역</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>OPENAPI 신청내역 목록</h2>
			<form name="listForm" action="<c:url value='/rcm/AilList.do'/>" method="post">
				<input type="hidden" name="unityId">
				<input type="hidden" name="openApiSeCode">
				<input type="hidden" name="pageIndex" value="<c:if test="${empty searchVO.pageIndex }">1</c:if><c:if test="${!empty searchVO.pageIndex }"><c:out value='${searchVO.pageIndex}'/></c:if>">
				<div class="search" style="text-align:right;">
					<select name="searchCondition1" id="searchCondition1" class="input-sm"  style="min-width:150px;" title="상태">
						<option value="">승인상태선택</option>
						<option value="STC01" <c:if test="${searchVO.searchCondition1 == 'STC01'}">selected</c:if> >대기</option>
						<option value="STC02" <c:if test="${searchVO.searchCondition1 == 'STC02'}">selected</c:if> >승인</option>
						<option value="STC03" <c:if test="${searchVO.searchCondition1 == 'STC03'}">selected</c:if> >차단</option>
					</select>
					<select name="searchCondition2" id="searchCondition2" class="input-sm"  style="min-width:150px;" title="검색조건">
						<option value="">전체</option>
						<option value="REQST_INSTT_NM" <c:if test="${searchVO.searchCondition2 == 'REQST_INSTT_NM'}">selected</c:if> >신청기관명</option>
						<option value="CHARGER_NM" <c:if test="${searchVO.searchCondition2 == 'CHARGER_NM'}">selected</c:if> >담당자명</option>
						<option value="UNITY_ID" <c:if test="${searchVO.searchCondition2 == 'UNITY_ID'}">selected</c:if> >담당자 아이디</option>
						<option value="CHARGER_EMAIL" <c:if test="${searchVO.searchCondition2 == 'CHARGER_EMAIL'}">selected</c:if> >담당자 이메일</option>
						<option value="PRCUSE_PRPOS" <c:if test="${searchVO.searchCondition2 == 'PRCUSE_PRPOS'}">selected</c:if> >활용용도및서비스</option>
						<option value="DALY_PERM_TRFIC_OVER" <c:if test="${searchVO.searchCondition2 == 'DALY_PERM_TRFIC_OVER'}">selected</c:if> >일일한도 이상</option>
						<option value="DALY_PERM_TRFIC_DOWN" <c:if test="${searchVO.searchCondition2 == 'DALY_PERM_TRFIC_DOWN'}">selected</c:if> >일일한도 이하</option>
						
						
					</select>
					<input name="searchKeyword" type="text" value="<c:out value="${searchVO.searchKeyword}"/>" id="searchKeyword" title="검색단어입력" class="input-sm ip" />
					<button type="button" class="btn btn-primary" onclick="javascript:fnSearch();">검색</button>
				</div><!--//search-->
				<div class="page">
					총 건수 : ${paginationInfo.totalRecordCount}건
					<span>
						정렬 : 
						<select name="sort_gbn" id="sort_gbn" class="input-sm"  style="width:150px;" title="정렬">
							<option value="">정렬기준</option>
							<option value="FRST_REGIST_PNTTM DESC" <c:if test="${searchVO.sort_gbn =='FRST_REGIST_PNTTM DESC'}">selected</c:if>>신청일시</option>
							<option value="REQST_INSTT_NM DESC" <c:if test="${searchVO.sort_gbn =='REQST_INSTT_NM DESC'}">selected</c:if>>신천기관명</option>
							<option value="STTUS_CODE DESC" <c:if test="${searchVO.sort_gbn == 'STTUS_CODE DESC'}">selected</c:if>>승인상태</option>
						</select>
						&nbsp;&nbsp;&nbsp;
						출력건수 : 
						<select name="pageUnit" class=" input-sm" onchange="fnChgListCnt(this.value)" title="쪽당출력건수">
		                    <option value='10' <c:if test="${empty searchVO.pageUnit || searchVO.pageUnit == '' || searchVO.pageUnit == '10'}">selected</c:if>>10</option>
		                    <option value='20' <c:if test="${searchVO.pageUnit == '20'}">selected</c:if>>20</option>
		                    <option value='50' <c:if test="${searchVO.pageUnit == '50'}">selected</c:if>>50</option>
		                    <option value='100' <c:if test="${searchVO.pageUnit == '100'}">selected</c:if>>100</option>
						</select>
					</span>					
				</div><!--//page-->
			</form>				

			<table class="table table-striped table-bordered table-hover tc"  id="">
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
						<th class="tc">번호</th>
						<th class="tc">신청기관명<br/> (신청일시)</th>
						<th class="tc">담당자명<br/>(아이디/이메일)</th>
						<th class="tc">활용용도 및 서비스</th>
						<th class="tc">일일한도</th>
						<th class="tc">승인상태</th>
						<th class="tc">승인일시</th>
					</tr>
				</thead>
				<tbody>		
					<c:forEach var="resultList" items="${resultList}" varStatus="status">
					<tr>
						<td>${resultList.rnum}</td>
						<td><a href="#;" onclick="fnMngDetailView('<c:out value="${resultList.unityId}" />');" >${resultList.reqstInsttNm}<br>(${resultList.frstRegistPnttm})</a></td>
						<td>${resultList.chargerNm}<br>(${resultList.unityId}/${resultList.chargerEmail})</td>
						<td>${resultList.prcusePrpos}</td>
						<td>${resultList.dalyPermTrfic}</td>
						<td>${resultList.sttusCode}</td>
						<td>${resultList.confmPnttm}</td>
					</tr>						
					</c:forEach>																																		
				</tbody>
			</table>

			<c:if test="${!empty searchVO.pageIndex }">
			<div align="center">
			    <div>
			    	<ul class="pagination">
			        	<ui:pagination paginationInfo = "${paginationInfo}"
			            type="image"
			            jsFunction="linkPage"
			            />
			         </ul>
			    </div>
			</div>
			</c:if>

	</div><!--//page-wrapper-->
</body>
</html>
