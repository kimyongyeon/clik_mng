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
<title>표준연계API 수집관리</title>

<script type="text/javaScript" language="javascript" defer="defer">

/* ********************************************************
* 지역 조회
******************************************************** */
function localChange(insttClCode) {
	
	// 셀렉트박스 초기화
	$("#BRTC_CODE option").remove();
	$("#BRTC_CODE").append("<option value=''>지역 선택</option>");
	$("#LOASM_CODE option").remove();
	$("#LOASM_CODE").append("<option value=''>선택</option>");
   
	$.ajax({
		type: "POST",
		url : "/sts/stm/selectAjaxBrtc.do",
		dataType:"json",
		async : false,
		success: function(args) {
			$.each(args, function() {
				$("#BRTC_CODE").append("<option value='"+this.codeId+"'>"+this.codeIdNm+"</option>");
			});
		}
		,error:function(e) {
			alert(e.responseText);
		}
	});
} 

/* ********************************************************
* 소속 조회
******************************************************** */
function psitnChange(brtcCode) {

	var formData = "brtcCode=" + brtcCode + "&insttClCode=" + $('#INSTT_TY_CODE').val();
	
	// 셀렉트박스 초기화
	$("#LOASM_CODE option").remove();
	$("#LOASM_CODE").append("<option value=''>선택</option>");

	$.ajax({
	   type: "POST",
	   url : "/sts/stm/selectAjaxPsitn.do",
	   data : formData,
	   dataType:"json",
	   async : false,
	   success: function(args) {
		   $.each(args, function() {
			   $("#LOASM_CODE").append("<option value='"+this.loasmCode+"'>"+this.loasmNm+"</option>");
			});
	   }
		,error:function(e) {
			alert(e.responseText);
	   }
	});
	
} 

/* ********************************************************
* 등록화면
******************************************************** */
function fnInsert() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rlm/StdCntcApiColctDetail.do' />";

	varForm.submit();
}

/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	var varForm = document.listForm;
	varForm.pageIndex.value = '1';
	varForm.action = "<c:url value='/rlm/StdCntcApiColctMngList.do' />";
	varForm.submit();
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************* */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/rlm/StdCntcApiColctMngList.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.currentPageNo.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/rlm/StdCntcApiColctMngList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
	varForm.pageUnit.value = val;
    varForm.action = "<c:url value='/rlm/StdCntcApiColctMngList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 정렬 변경(셀렉트박스)
******************************************************** */
function fnChgOrder(val) {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rlm/StdCntcApiColctMngList.do'/>";
    varForm.submit();
}

/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnMngDetailView(rasmbly_id) {

	var varForm = document.listForm;
	varForm.rasmbly_id.value = rasmbly_id;
	varForm.action = "<c:url value='/rlm/StdCntcApiColctMngDetail.do'/>";
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
	
// 	var varForm = document.listForm;
	var keyList = "";
	
	$('input[type="checkbox"]:checked').each(function(index,value){
		if($(value).val() != ""){
			keyList += $(value).val() + ",";
		}
	});
	
	keyList = keyList.substring(0, keyList.length-1);
	
	if(!confirm("선택한 항목을 삭제 하시겠습니까?")){
		return false;
	}else if(keyList.length == 0){
		alert("선택한 항목이 존재하지 않습니다.");
		return false;
	}
	
// 	varForm.deleteKeyList.value = keyList;
// 	varForm.action = "<c:url value='/rlm/DeleteStdCntcApiColct.do'/>";
// 	varForm.submit();
	
	
	$.ajax({
		   type: "POST",
		   url : "/rlm/DeleteStdCntcApiColct.do",
		   data : {"deleteKeyList" : keyList},
		   dataType:"json",
		   async : false,
		   success: function(args) {
			   alert(args[0].resultMsg);
			   
			   if(confirm("재 검색 하시겠습니까?")){
				   fnSearch();
			   }
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
	<input name="deleteKeyList" type="hidden" value=""/>
	
	<div id="page-wrapper">
        <div class="row">
	        <div class="col-lg-12">
	        	<h1 class="page-header">표준연계 API 수집관리</h1>
	        </div>
       	</div>
		<!-- /.row -->

		<h2>표준연계 API 수집요청 목록</h2>
		
		<input type="hidden" id="INSTT_CL_CODE" name="INSTT_CL_CODE" value="${searchVO.INSTT_CL_CODE }" />
		<div class="search" style="text-align:right;">
	    	지방의회:
	    	<select id="INSTT_TY_CODE" name="INSTT_TY_CODE" style="min-width:150px;" onchange="localChange(this.value);">
	    		<option value="">기관유형 선택</option>
	    		<option value="INTSTTCL_000023" <c:if test="${searchVO.INSTT_TY_CODE == 'INTSTTCL_000023'}">selected="selected"</c:if>>광역의회</option>
	    		<option value="INTSTTCL_000024" <c:if test="${searchVO.INSTT_TY_CODE == 'INTSTTCL_000024'}">selected="selected"</c:if>>기초의회</option>
	    	</select>
	    	&nbsp;&nbsp;
	    	지역선택:
	    	<select id="BRTC_CODE" name="BRTC_CODE" style="min-width:150px;" onchange="psitnChange(this.value);">
	    		<option value="">지역 선택</option>
	    		<c:forEach var="item" items="${brtc_code_list }">
	    		<option value="${item.codeId }" <c:if test="${searchVO.BRTC_CODE == item.codeId}">selected="selected"</c:if>>${item.codeIdNm }</option>
	    		</c:forEach>
	    	</select>
	    	&nbsp;&nbsp;
	    	의회명:
	    	<select id="LOASM_CODE" name="LOASM_CODE" style="min-width:150px;" >
	    		<option value="">의회 선택</option>
	    		<c:forEach var="item" items="${loasm_code_list }">
	    		<option value="${item.loasmCode }" <c:if test="${searchVO.LOASM_CODE == item.loasmCode}">selected="selected"</c:if>>${item.loasmNm }</option>
	    		</c:forEach>
	    	</select>
	    	&nbsp;&nbsp;
	    	수집자료구분:
	    	<select id="DTA_SE_CODE" name="DTA_SE_CODE" style="min-width:150px;" >
	    		<option value="">자료구분 선택</option>
	    		<option value="TAR101" <c:if test="${searchVO.DTA_SE_CODE == 'TAR101'}">selected="selected"</c:if>>회의록</option>
	    		<option value="TAR102" <c:if test="${searchVO.DTA_SE_CODE == 'TAR102'}">selected="selected"</c:if>>의안</option>
	    		<option value="TAR103" <c:if test="${searchVO.DTA_SE_CODE == 'TAR103'}">selected="selected"</c:if>>의원</option>
	    		<option value="TAR104" <c:if test="${searchVO.DTA_SE_CODE == 'TAR104'}">selected="selected"</c:if>>회기,직위,회의명,선거구</option>
	    	</select>
	    	&nbsp;&nbsp;
			<a href="#none"><button type="button" class="btn btn-primary" onclick="fnSearch();">검색</button></a> 
		</div>

		<div class="page">
			총 건수 : <fmt:formatNumber value="${paginationInfo.totalRecordCount}" type="number" />건

			<span>
				정렬 : 
				<select id="sortOrder" name="sortOrder" onchange="fnChgOrder(this.value)">
					<option value="">정렬기준</option>
					<option value="RASMBLY_NM DESC" <c:if test="${searchVO.sortOrder == 'RASMBLY_NM DESC'}">selected</c:if>>지방의회명 내림차순</option>
					<option value="RASMBLY_NM ASC" <c:if test="${searchVO.sortOrder == 'RASMBLY_NM ASC'}">selected</c:if>>지방의회명 오름차순</option>
					<option value="COLCT_SE_CODE DESC" <c:if test="${searchVO.sortOrder == 'COLCT_SE_CODE DESC'}">selected</c:if>>수집자료구분 내림차순</option>
					<option value="COLCT_SE_CODE ASC" <c:if test="${searchVO.sortOrder == 'COLCT_SE_CODE ASC'}">selected</c:if>>수집자료구분 오름차순</option>
					<option value="DTA_SE_CODE DESC" <c:if test="${searchVO.sortOrder == 'DTA_SE_CODE DESC'}">selected</c:if>>수집구분 내림차순</option>
					<option value="DTA_SE_CODE ASC" <c:if test="${searchVO.sortOrder == 'DTA_SE_CODE ASC'}">selected</c:if>>수집구분 오름차순</option>
					<option value="COLCT_RESVE_DT DESC" <c:if test="${searchVO.sortOrder == 'COLCT_RESVE_DT DESC'}">selected</c:if>>수집예약일시 내림차순</option>
					<option value="COLCT_RESVE_DT ASC" <c:if test="${searchVO.sortOrder == 'COLCT_RESVE_DT ASC'}">selected</c:if>>수집예약일시 오름차순</option>
					<option value="PROCESS_STTUS_CODE DESC" <c:if test="${searchVO.sortOrder == 'PROCESS_STTUS_CODE DESC'}">selected</c:if>>수집상태 내림차순</option>
					<option value="PROCESS_STTUS_CODE ASC" <c:if test="${searchVO.sortOrder == 'PROCESS_STTUS_CODE ASC'}">selected</c:if>>수집상태 오름차순</option>
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
				<col width="15%" />
				<col width="10%" />
				<col width="20%" />
				<col width="15%" />
				<col width="250px" />
				<col width="" />
			</colgroup>
			<thead>
				<tr>
					<th style="text-align: center;"><input type="checkbox" value="all" id="allChk" onclick="javascript:fn_all_chk();"></th>
					<th>번호</th>
					<th>지방의회 명</th>
					<th>수집자료구분</th>
					<th>수집구분</th>
					<th>수집예약일시</th>
					<th>수집범위</th>
<!-- 					<th>수집상태</th> -->
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
					<td><input type="checkbox" name="cb" onclick="javascript:fn_cb_chk();" value="${item.STD_CNTC_SETUP_ID}"></td>
					<td><c:out value="${item.RNUM}"/></td>
					<td><c:out value="${item.RASMBLY_NM}"/></td>
					<td><c:out value="${item.COLCT_SE_CODE_NM}"/></td>
					<td><c:out value="${item.DTA_SE_CODE_NM}"/></td>
					<td><c:out value="${item.COLCT_RESVE_DT}"/></td>
					<td><c:out value="${item.COLCT_SCOPE}"/></td>
<%-- 					<td><c:out value="${item.PROCESS_STTUS_CODE}"/></td> --%>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<span class="r">* 수집범위 : 회의록 =&gt; 회의일자, 의안 =&gt; 제안일자</span>
		
		<p class="tr">
			<button type="button" class="btn btn-danger" onclick="fnDelete();">선택삭제</button>		
			<button type="button" class="btn btn-primary" onclick="fnInsert();">수집등록</button>
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