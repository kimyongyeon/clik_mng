<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"		uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>정당코드 관리 목록</title>

<script type="text/javaScript" language="javascript" defer="defer">

/* ********************************************************
* 등록화면
******************************************************** */
function fnInsert() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rlm/selectPprtyCodeDetail.do' />";

	varForm.submit();
}

/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	var varForm = document.listForm;
	varForm.pageIndex.value = '1';
	varForm.action = "<c:url value='/rlm/selectPprtyCodeList.do' />";
	varForm.submit();
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************* */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/rlm/selectPprtyCodeList.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
	varForm.pageUnit.value = val;
    varForm.action = "<c:url value='/rlm/selectPprtyCodeList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 정렬 변경(셀렉트박스)
******************************************************** */
function fnChgOrder(val) {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rlm/selectPprtyCodeList.do'/>";
    varForm.submit();
}

/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnMngDetailView(pprty_code) {

	var varForm = document.listForm;
	varForm.pprty_code.value = pprty_code;
	varForm.action = "<c:url value='/rlm/selectPprtyCodeDetail.do'/>";
	varForm.submit();
}

/* ********************************************************
*  전체선택 / 해제
******************************************************** */
function fn_all_chk(){
	if($('input[id="allChk"]:checked').length == 1){
		$('table input[type="checkbox"]').prop("checked",true);
	}else{
		$('table input[type="checkbox"]').prop("checked",false);
	}
}

/* ********************************************************
*  전체선택 체크박스 해제
*  - 전체 선택 후 리스트에 체크 박스 해제 시 전체선택 체크박스 해제 
******************************************************** */
function fn_cb_chk(){
	var isCheckedAll = false;
	$('table input[type="checkbox"]').each(function(index,target){
		if(!$(target).is(":checked")){
			isCheckedAll = true;
			return;
		}
	});
	
	if(isCheckedAll){
		$('#allChk').prop("checked",false);
	}
}

/* ********************************************************
*  삭제
******************************************************** */
function fnDelete(){
	
	var keyList = "";
	
	$('input[type="checkbox"]:checked').each(function(index,value){
		if($(value).val().trim() != ""){
			keyList += $(value).val().trim() + ",";
		}
	});
	
	keyList = keyList.substring(0, keyList.length-1);
	
	if(!confirm("선택한 항목을 삭제 하시겠습니까?")){
		return false;
	}else if(keyList.length == 0){
		alert("선택한 항목이 존재하지 않습니다.");
		return false;
	}
	
	$.ajax({
		   type: "POST",
		   url : "/rlm/deletePprtyCode.do",
		   data : {"deleteKeyList" : keyList},
		   dataType:"json",
		   async : false,
		   success: function(args) {
			   alert(args[0].resultMsg);
			   
// 			   if(confirm("재 검색 하시겠습니까?")){
				   fnSearch();
// 			   }
		   }
			,error:function(e) {
				alert(e.responseText);
		   }
		});
}

$(document).ready(function() {
	
	
});
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form id="listForm" name="listForm" method="post">

	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	<input name="pprty_code" type="hidden" value=""/>
	<input name="deleteKeyList" type="hidden" value=""/>
	
	<div id="page-wrapper">
        <div class="row">
	        <div class="col-lg-12">
	        	<h1 class="page-header">정당코드 관리</h1>
	        </div>
       	</div>
		<!-- /.row -->

		<h2>정당코드 목록</h2>

		<div class="page">
			총 건수 : <fmt:formatNumber value="${paginationInfo.totalRecordCount}" type="number" />건

			<span>
				<input name="searchCondition" type="hidden" value="DELETE_AT"/>
				삭제여부 : 
				<select id="searchKeyword" name="searchKeyword" onchange="fnSearch();">
					<option value="">전체</option>
					<option value="Y" <c:if test="${searchVO.searchKeyword == 'Y'}">selected</c:if>>삭제</option>
					<option value="N" <c:if test="${searchVO.searchKeyword == 'N'}">selected</c:if>>미삭제</option>
				</select>
				
				정렬 : 
				<select id="sortOrder" name="sortOrder" onchange="fnChgOrder(this.value)">
					<option value="">정렬기준</option>
					<option value="PPRTY_CODE DESC" <c:if test="${searchVO.sortOrder == 'PPRTY_CODE DESC'}">selected</c:if>>정당코드 내림차순</option>
					<option value="PPRTY_CODE ASC" <c:if test="${searchVO.sortOrder == 'PPRTY_CODE ASC'}">selected</c:if>>정당코드 오름차순</option>
					<option value="PPRTY_NM DESC" <c:if test="${searchVO.sortOrder == 'PPRTY_NM DESC'}">selected</c:if>>정당명 내림차순</option>
					<option value="PPRTY_NM ASC" <c:if test="${searchVO.sortOrder == 'PPRTY_NM ASC'}">selected</c:if>>정당명 오름차순</option>
				</select>

				&nbsp;&nbsp;&nbsp;

				출력건수
				<select name="pageUnit" id="listCnt" class=" input-sm" onchange="fnChgListCnt(this.value)">
					<option value="10" <c:if test="${searchVO.pageUnit == '10'}"> selected="selected"</c:if>>10</option>
					<option value="30" <c:if test="${searchVO.pageUnit == '30'}"> selected="selected"</c:if>>30</option>
					<option value="50" <c:if test="${searchVO.pageUnit == '50'}"> selected="selected"</c:if>>50</option>
					<option value="100" <c:if test="${searchVO.pageUnit == '100'}"> selected="selected"</c:if>>100</option>
				</select>
			</span>

		</div>				
				

		<table class="table table-striped table-bordered table-hover tc"  id="dataTable" >
			<colgroup>
				<col width="5%" />
				<col width="5%" />
				<col width="10%" />
				<col width="" />
				<col width="10%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th style="text-align: center;"><input type="checkbox" value="all" id="allChk" onclick="javascript:fn_all_chk();"></th>
					<th>번호</th>
					<th>정당코드</th>
					<th>정당명</th>
					<th>정당약칭</th>
					<th>삭제여부</th>
				</tr>
			</thead>
			<tbody>
				<%-- 데이터를 없을때 화면에 메세지를 출력해준다 S --%>
				<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td class="lt_text3" colspan="8"><spring:message code="common.nodata.msg" /></td>
					</tr>
				</c:if>
				<%-- 데이터를 없을때 화면에 메세지를 출력해준다 E --%>
		
				<c:forEach items="${resultList}" var="item" varStatus="s">
				<tr>
					<td><input type="checkbox" name="cb" onclick="javascript:fn_cb_chk();" value="${item.PPRTY_CODE}"></td>
					<td><c:out value="${item.RNUM}"/></td>
					<td><c:out value="${item.PPRTY_CODE}"/></td>
					<td><a href="#none;" onclick="fnMngDetailView('${item.PPRTY_CODE}');"><c:out value="${item.PPRTY_NM}"/></a></td>
					<td><c:out value="${item.PPRTY_ABRV}"/></td>
					<td><c:out value="${item.DELETE_AT}"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<p class="tr">
			<button type="button" class="btn btn-danger" onclick="fnDelete();">선택삭제</button>		
			<button type="button" class="btn btn-primary" onclick="fnInsert();">등록</button>
		</p>
		
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
</form>	
</body>
</html>