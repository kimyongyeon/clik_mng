<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>스페셜검색 목록</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--

/* ********************************************************
* 스페셜검색 등록
******************************************************** */
function fncAddSpcInsert() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sit/spc/SpcRegist.do' />";

	varForm.submit();
}

/* ********************************************************
* 스페셜검색 목록 검색
******************************************************** */
function fnSearch() {
	
	if($("#searchKeyword").val() == "" || $("#searchKeyword").val() == null) {
		alert('검색어를 입력해 주세요.');
		return; 
	}
	
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sit/spc/SpcList.do' />";
	varForm.submit();
	
}


/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.currentPageNo.value = currentPageNo;
    varForm.pageIndex.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/sit/spc/SpcList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
	varForm.pageUnit.value = val;
    varForm.action = "<c:url value='/sit/spc/SpcList.do'/>";
    varForm.submit();
}

/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnDetailView(speclSearchId, insttClCode, brtcCode, loasmCode) {

	var varForm = document.listForm;
	varForm.speclSearchId.value = speclSearchId;
	varForm.insttClCode.value = insttClCode;
	varForm.brtcCode.value = brtcCode;
	varForm.loasmCode.value = loasmCode;
	varForm.action = "<c:url value='/sit/spc/SpcDetail.do'/>";
	varForm.submit();
}
-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form:form name="listForm" action="" method="post">
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">스페셜검색 관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>스페셜검색 목록</h2>
			<div class="select_box">
		
				<span>
				<select name="searchCondition" id="searchCondition" class="input-sm"  style="width:150px;" title="검색조건">
					<option value='' >전체검색</option>
					<option value='selMainSj' <c:if test="${searchCondition =='selMainSj'}">selected</c:if>>제목</option>
					<option value='selAsmblyNm' <c:if test="${searchCondition =='selAsmblyNm'}">selected</c:if>>의회 명</option>
					<option value='selKwrd' <c:if test="${searchCondition =='selKwrd'}">selected</c:if>>연관 키워드</option>
				</select>
				<input type="text" id='searchKeyword' name='searchKeyword' class="input-sm ip input-search" value="<c:out value='${searchKeyword}' />" onKeyDown="javascript:if(event.keyCode==13){fnSearch();}">
				<a href="#LINK" onclick="javascript:fnSearch(); return false;"><button type="button" class="btn btn-primary"><spring:message code="button.search" /></button></a>
				</span>
			</div><!--//search-->
				
			<div class="page">
				총 건수 :  <c:out value="${paginationInfo.totalRecordCount}" />건

				<span>
					출력건수
					<select id="pageUnit" name="pageUnit" class="input-sm" onchange="fnChgListCnt(this.value);">
						<option value="10" <c:if test="${spcVO.pageUnit == '10' }">selected</c:if>>10</option>
						<option value="30" <c:if test="${spcVO.pageUnit == '30' }">selected</c:if>>30</option>
						<option value="50" <c:if test="${spcVO.pageUnit == '50' }">selected</c:if>>50</option>
						<option value="100" <c:if test="${spcVO.pageUnit == '100' }">selected</c:if>>100</option>
					</select>
				</span>
			
			</div>

				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="5%" />
						<col width="15%" />
						<col width="15%" />
						<col width="15%" />
						<col/>
						<col width="10%"/>
					
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>대표이미지</th>
							<th>제목</th>
							<th>의회 명</th>
							<th>연관 키워드</th>
							<th>등록일</th>
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
					    <td>${(spcVO.pageIndex-1) * spcVO.pageUnit + status.count}</td>
					    <td>
					    	<a href="<c:url  value="${x.mainUrl}" />" >
									<c:import url="/cmm/fms/selectImageFileInf.do" charEncoding="utf-8">
										<c:param name="atchFileId" value="${x.mainImageFileNm}" />
										<c:param name="fileSn" value="0" />
									</c:import>			    	
					    	</a>
					    </td>
					    
					    <td><a href="#LINK" onclick="fnDetailView('<c:out value="${x.speclSearchId}" />', '<c:out value="${x.insttClCode }" />', '<c:out value="${x.brtcCode }" />', '<c:out value="${x.loasmCode }" />');" ><c:out value="${x.mainSj}" /></a></td>
					    <td><c:out value="${x.loasmNm}" /></td>
					    <td><c:out value="${x.kwrd}" /></td>
					    <td><c:out value="${x.frstRegistPnttm}" /></td>
				 	</tr>
				 	</c:forEach>

					</tbody>
				</table>

				<p class="tr">
					<button type="button" class="btn btn-primary"  onclick="javascript:fncAddSpcInsert()">등록</button>
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


			<%-- <input type="hidden" id="pageUnit" name="pageUnit" value="${spcVO.pageUnit}" /> --%>
			<input type="hidden" id="pageSize" name="pageSize" value="${spcVO.pageSize}"/>
			<input type="hidden" id="pageIndex" name="pageIndex" value="${spcVO.pageIndex}"/>

	        <input type="hidden" name="currentPageNo" value="<c:out value='${ paginationInfo.currentPageNo }'/>" >
			<input type="hidden" name="speclSearchId" />
			<input type="hidden" name="insttClCode" />
			<input type="hidden" name="brtcCode" />
			<input type="hidden" name="loasmCode" />
			<input type="hidden" name="cmd" />

	</div><!--//page-wrapper-->
</form:form>

</body>
</html>
