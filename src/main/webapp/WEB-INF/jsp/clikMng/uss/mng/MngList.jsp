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
<title>관리자 목록조회</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--

/* ********************************************************
* 탭클릭시 화면전환
******************************************************** */
function fn_tabClick(sel_name) {
	if (sel_name == 'MNG') {
		document.listForm.authorClCode = sel_name;
		document.listForm.action = "/uss/mng/MngList.do";
	} else {
		document.listForm.authorClCode = sel_name;
		document.listForm.action = "/uss/mng/LocalMngList.do";
	}
	
	document.listForm.submit();	
}

/* ********************************************************
* 관리자 등록
******************************************************** */
function fncAddMngInsert() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/uss/mng/MngRegist.do' />";

	varForm.submit();
}

/* ********************************************************
* 관리자 검색
******************************************************** */
function fnSearch() {
	/*
	if($("#searchKeyword").val() == "" || $("#searchKeyword").val() == null) {
		alert('검색어를 입력해 주세요.');
		return; 
	}
	*/
	
	var varForm = document.listForm;
	varForm.action = "<c:url value='/uss/mng/MngList.do' />";
	varForm.submit();
	
}


/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.pageIndex.value = currentPageNo;
    varForm.action = "<c:url value='/uss/mng/MngList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
	varForm.pageUnit.value = val;
	varForm.action = "<c:url value='/uss/mng/MngList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 정렬 변경(셀렉트박스)
******************************************************** */
function fnChgOrder(val) {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/uss/mng/MngList.do'/>";
    varForm.submit();
}

/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnMngDetailView(mngrId) {

	var varForm = document.listForm;
	varForm.mngrId.value = mngrId;
	varForm.action = "<c:url value='/uss/mng/MngDetail.do'/>";
	varForm.submit();
}
-->
</script>
<c:if test="${!empty msg}"><script>alert('${msg}');</script></c:if>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

<DIV id="page-wrapper">
	<div class="row">
    	<div class="col-lg-12">
            <h1 class="page-header">관리자 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    
    <!-- /row -->
	<div id="border" style="display:" class="">

		<DIV class="">
			<h2> 관리자 목록(clik 관리자)</h2>
			
			<!--  ul class="nav nav-tabs"> 
				<li class="active">
					<a href="#LINK" onClick="javascript:fn_tabClick('MNG')" data-toggle="tab">국회직원 관리자</a>
                </li>
                <li class="">
                	<a href="#LINK" onClick="javascript:fn_tabClick('CLIK')"  data-toggle="tab">지방의회/지자체 관리자</a>
                </li>
            </ul>  -->	
			
			<!-- /.panel-heading -->			
			<DIV id="main" class="">
				<form:form name="listForm" action="" method="post">
					<div class="select_box">
						<span>

							<select name="selMngrOpt" id="selMngrOpt" title="구분" class="ip input-sm" style="width:100px;">
								<option>구분</option>
								<option value='1' <c:if test="${selMngrOpt =='1'}">selected</c:if>>직원</option>
								<option value='2' <c:if test="${selMngrOpt =='2'}">selected</c:if>>비직원</option>
				     		</select>
						
							<select name="searchCondition" id="searchCondition" title="검색조건" class="ip input-sm" style="width:100px;">
								<%-- <option value='selMngrId' <c:if test="${searchCondition =='selMngrId'}">selected</c:if>>아이디</option> --%>
								<option value='selMngrNm' <c:if test="${searchCondition =='selMngrNm'}">selected</c:if>>이름</option>
								<option value='selMngrDept' <c:if test="${searchCondition =='selMngrDept'}">selected</c:if>>부서</option>
								<option value='selMngrAuthor' <c:if test="${searchCondition =='selMngrAuthor'}">selected</c:if>>권한</option>
				     		</select>
				     		
				  			<input type='text' id='searchKeyword' name='searchKeyword' value="<c:out value='${searchKeyword}'/>" style="width:200px;" onKeyDown="javascript:if(event.keyCode==13){fnSearch();}">
				  			<a href="#LINK" onclick="javascript:fnSearch(); return false;"><button type="button" class="btn btn-primary"><spring:message code="button.search" /></button></a>
						</span>
					</div>
					<div class="page">
						총 관리자 수 : <c:out value="${paginationInfo.totalRecordCount}" /> 명
						<span>
						정렬 : 
						<select id="sortOrder" name="sortOrder" onchange="fnChgOrder(this.value)">
							<option value="">정렬기준</option>
							<option value="mngrNm_DESC" <c:if test="${mngVO.sortOrder == 'mngrNm_DESC'}">selected</c:if>>이름 내림차순</option>
							<option value="mngrNm_ASC" <c:if test="${mngVO.sortOrder == 'mngrNm_ASC'}">selected</c:if>>이름 오름차순</option>
							<option value="mngrDept_DESC" <c:if test="${mngVO.sortOrder == 'mngrDept_DESC'}">selected</c:if>>부서 내림차순</option>
							<option value="mngrDept_ASC" <c:if test="${mngVO.sortOrder == 'mngrDept_ASC'}">selected</c:if>>부서 오름차순</option>
							<option value="authorNm_DESC" <c:if test="${mngVO.sortOrder == 'authorNm_DESC'}">selected</c:if>>등급 내림차순</option>
							<option value="authorNm_ASC" <c:if test="${mngVO.sortOrder == 'authorNm_ASC'}">selected</c:if>>등급 오름차순</option>
							<option value="regDate_DESC" <c:if test="${mngVO.sortOrder == 'regDate_DESC'}">selected</c:if>>등록일자 내림차순</option>
							<option value="regDate_ASC" <c:if test="${mngVO.sortOrder == 'regDate_ASC'}">selected</c:if>>등록일자 오름차순</option>
						</select>
	
						&nbsp;&nbsp;&nbsp;

						출력건수 :
						<select title="쪽당출력건수" id="pageUnit" name="pageUnit" onchange="fnChgListCnt(this.value)">
	                        <option value='10' <c:if test="${mngVO.pageUnit == '10'}">selected</c:if>>10</option>
	                        <option value='30' <c:if test="${mngVO.pageUnit == '30'}">selected</c:if>>30</option>
	                        <option value='50' <c:if test="${mngVO.pageUnit == '50'}">selected</c:if>>50</option>
	                        <option value='100' <c:if test="${mngVO.pageUnit == '100'}">selected</c:if>>100</option>
	                    </select>	
						</span>
					</div>
					
					<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="기능 관리 테이블입니다.기능  ID,기능 명,기능 타입,기능 Sort,기능 설명,등록일자의 정보를 담고 있습니다.">
						<thead>
						<tr>
							<th class="title" width="13%" scope="col" nowrap="nowrap">번호</th>
							<th class="title" width="25%" scope="col" nowrap="nowrap">구분</th>
							<th class="title" width="25%" scope="col" nowrap="nowrap">이름</th>
							<th class="title" width="25%" scope="col" nowrap="nowrap">부서</th>
							<th class="title" width="25%" scope="col" nowrap="nowrap">등급</th>
							<th class="title" width="12%" scope="col" nowrap="nowrap">등록일</th>
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
						    <td class="lt_text3" nowrap="nowrap">
						    	${(mngVO.pageIndex-1) * mngVO.pageUnit + status.count}
						    </td>
						    <td class="lt_text3" nowrap="nowrap">
						    	<c:choose>
						    		<c:when test="${x.mngrSeCode == '1'}">직원</c:when>
						    		<c:otherwise>일반</c:otherwise>
						    	</c:choose>
						    </td>
						    <td class="lt_text" nowrap="nowrap"><a href="#LINK" onclick="fnMngDetailView('<c:out value="${x.mngrId}" />');"><c:out value="${x.mngrNm}" /></a></td>
						    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.mngrDept}" /></td>
						    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.authorNm}" /></td>
						    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.regDate}" /></td>
					 	</tr>
					 	</c:forEach>
					 	</tbody>
					</table>
					
					
					<p class="tr">
						<button type="button" class="btn btn-primary" onclick="javascript:fncAddMngInsert()">등록</button>
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
					<input type="hidden" name="pageIndex" value="1"/>
					<input type="hidden" name="authorClCode" value="MNG"/>
					<input type="hidden" name="mngrId" />
					</form:form>
				
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->					
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>
