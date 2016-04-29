<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt"%> 

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>자료이용 통계 조회</title>
<script type="text/javaScript" language="javascript" defer="defer">

/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sts/stm/StmInfoMngList.do'/>";
	varForm.submit();
}

/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(pageNum) { 
	var varForm = document.listForm;
	listForm.action = "<c:url value='/sts/stm/StmInfoMngList.do'/>" + "?pageIndex=" + pageNum;
	document.listForm.submit();
}

/* ********************************************************
* 엑셀 다운로드
******************************************************** */
function fnExcel() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sts/stm/StmInfoMngExcel.do'/>";
	varForm.submit();
}

/* ********************************************************
* 기관유형 검색
******************************************************** */
// function insttTyChange(fInsttClCode) {
	
// 	// 지역선택 셀렉트박스 초기화
// 	$("#INSTT_TY_CODE option").remove();
// 	$("#INSTT_TY_CODE").append("<option value=''>기관유형 선택</option>");	
// 	$("#BRTC_CODE option").remove();
// 	$("#BRTC_CODE").append("<option value=''>지역 선택</option>");
// 	$("#LOASM_CODE option").remove();
// 	$("#LOASM_CODE").append("<option value=''>선택</option>");
	
//  	$.ajax({
// 	   type: "POST",
// 	   url : "/sts/stm/selectAjaxInsttTy.do",
// 	   data : "fInsttClCode=" + fInsttClCode,
// 	   dataType:"json", 
// 	   async : false,
// 	   success: function(args) {
// 		   $.each(args, function() {
// 			   $("#INSTT_TY_CODE").append("<option value='"+this.fInsttTyClCode+"'>"+this.fInsttTyClCodeNm+"</option>");
// 			});
// 	   }
//  		,error:function(e) {
// 			alert(e.responseText);
// 	   }
// 	});
// } 

/* ********************************************************
* 지역 조회
******************************************************** */
function localChange(insttClCode) {
	
	$("#BRTC_CODE option").remove();
	$("#BRTC_CODE").append("<option value=''>지역 선택</option>");
	$("#LOASM_CODE option").remove();
	$("#LOASM_CODE").append("<option value=''>선택</option>");
	
	// 셀렉트박스 초기화
	$("BRTC_CODE option").remove();
   
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
* 통계정보 삭제
******************************************************** */
function setDelete(){
	
	if(confirm("선택 항목을 삭제하시겠습니까?")){
		
		if($('input[type="checkbox"]:checked').length <= 0){
			alert("삭제할 항목이 존재하지 않습니다.");
			return false;
		}
		
		var data_list = "";
		$('input[type="checkbox"]:checked').each(function(index,checkbox){
			data_list += checkbox.value + ",";
		});
		
		$.ajax({
			   type: "POST",
			   url : "/sts/stm/deleteStmInfo.do",
			   data : {"data_list" : data_list},
			   dataType:"json",
			   async : false,
			   success: function(args) {
					
				   alert(args[0].result_msg);

				   if(args[0].result_code == "success"){
					   location.reload();   
				   }
			   }
				,error:function(e) {
					alert(e.responseText);
			   }
			});
	}
	
	return false;
}

/* ********************************************************
* 등록화면
******************************************************** */
function setRegist(){
	window.location.href = '/sts/stm/StmInfoMngDetail.do';
}
</script>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
	<div id="page-wrapper">
           <div class="row">
               <div class="col-lg-12">
                   <h1 class="page-header">통계정보관리</h1>
               </div>
               <!-- /.col-lg-12 -->
           </div>
		<h2>통계정보관리</h2>
		<form id="listForm" name="listForm" method="post" action="<c:url value='/sts/stm/StmMngList.do'/>" enctype="multipart/form-data">
			<div class="search" style="text-align:right;">
<!-- 				<select id="INSTT_CL_CODE" name="INSTT_CL_CODE" onchange="insttTyChange(this.value);"> -->
<!-- 		    		<option value="">기관선택</option> -->
<%-- 				    <option value="INTSTTCL_000006" <c:if test="${searchVO.INSTT_CL_CODE == 'INTSTTCL_000006'}">selected="selected"</c:if>>지방정부</option> --%>
<%-- 				    <option value="INTSTTCL_000012" <c:if test="${searchVO.INSTT_CL_CODE == 'INTSTTCL_000012'}">selected="selected"</c:if>>지방의회</option> --%>
<!-- 		    	</select> -->
		    	<select id="INSTT_TY_CODE" name="INSTT_TY_CODE" onchange="localChange(this.value);" style="min-width:150px;">
		    		<option <c:if test="${searchVO.INSTT_CL_CODE == ''}">disabled="disabled"</c:if> value="">기관유형 선택</option>
		    		<c:forEach var="item" items="${instt_ty_code_code_list }">
		    		<option value="${item.fInsttClCode }" <c:if test="${searchVO.INSTT_TY_CODE == item.fInsttClCode}">selected="selected"</c:if>>${item.fInsttClCodeNm }</option>
		    		</c:forEach>
		    	</select>
		    	<select id="BRTC_CODE" name="BRTC_CODE" onchange="psitnChange(this.value);" style="min-width:150px;">
		    		<option <c:if test="${searchVO.INSTT_TY_CODE == ''}">disabled="disabled"</c:if> value="">지역 선택</option>
		    		<c:forEach var="item" items="${brtc_code_list }">
		    		<option value="${item.codeId }" <c:if test="${searchVO.BRTC_CODE == item.codeId}">selected="selected"</c:if>>${item.codeIdNm }</option>
		    		</c:forEach>
		    	</select>
		    	<select id="LOASM_CODE" name="LOASM_CODE" style="min-width:150px;">
		    		<option <c:if test="${searchVO.BRTC_CODE == ''}">disabled="disabled"</c:if> value="">선택</option>
		    		<c:forEach var="item" items="${loasm_code_list }">
		    		<option value="${item.loasmCode }" <c:if test="${searchVO.LOASM_CODE == item.loasmCode}">selected="selected"</c:if>>${item.loasmNm }</option>
		    		</c:forEach>
		    	</select>

				<a href="#none"><button type="button" class="btn btn-primary" onclick="fnSearch();">검색</button></a> 
				<a href="#none"><button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button></a>
			</div>
		</form>
		
		<table class="table table-striped table-bordered table-hover " id="dataTable" >
			<colgroup>
				<col width="5%" />
				<col width="10%" />
				<col width="5%" />
				<col width="20%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th style="text-align: center;">번호</th>
					<th style="text-align: center;">의회명</th>
					<th style="text-align: center;">대수</th>
					<th style="text-align: center;">기수</th>
<!-- 					<th style="text-align: center;">통계정보</th> -->
					<th style="text-align: center;">의원정수</th>
					<th style="text-align: center;">상임위원회개수</th>
					<th style="text-align: center;">의안합계</th>
					<th style="text-align: center;">회의록합계</th>
					<th style="text-align: center;">선택</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${list }" varStatus="s">
				<tr>
					<td style="text-align: center;">${item.RNUM }</td>
					<td>${item.LOASM_NM }</td>
					<td style="text-align: center;">${item.RASMBLY_NUMPR }</td>
					<td><a href="/sts/stm/StmInfoMngDetail.do?LOASM_CODE=${item.LOASM_CODE }&RASMBLY_NUMPR=${item.RASMBLY_NUMPR}&HRSMNPD_SN=${item.HRSMNPD_SN}@${item.HT_SE_STDCD}">${item.HRSMNPD_SN}기[${item.HT_SE_STDCD_NM}] (${item.BEGIN_DE} ~ ${item.END_DE})</a></td>
<!-- 					<td> -->
<%-- 						<c:set value="${fn:split(item.STATS_INFO,',')}" var="STATS_INFO" /> --%>
<%-- 						<c:forEach var="statsInfo" items="${STATS_INFO }" varStatus="s2"> --%>
<%-- 							<c:if test="${!s2.last }">${statsInfo }, </c:if> --%>
<%-- 							<c:if test="${s2.last }">${statsInfo }</c:if> --%>
<%-- 						</c:forEach> --%>
<!-- 					</td> -->
					<td style="text-align: center;"><fmt:formatNumber value="${item.ASEMBY_CO }"/></td>
					<td style="text-align: center;"><fmt:formatNumber value="${item.CMIT_CO }"/></td>
					<td style="text-align: center;"><fmt:formatNumber value="${item.RCEPTER_BI_CO }"/></td>
					<td style="text-align: center;"><fmt:formatNumber value="${item.MINTS_CO }"/></td>
					<td style="text-align: center;"><input type="checkbox" value="${item.LOASM_CODE }@${item.RASMBLY_NUMPR}@${item.HRSMNPD_SN}@${item.HT_SE_STDCD}"/> </td>
				</tr>
			</c:forEach>		
			</tbody>				
		</table>
		
		<p class="tr">
			<a href="#;" onclick="return setRegist();"><button type="button" class="btn btn-primary">등록</button></a>
			<a href="#;" onclick="return setDelete();"><button type="button" class="btn btn-danger">삭제</button></a>
		</p>
				
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
	</div><!--//page-wrapper-->	
</body>
</html>
