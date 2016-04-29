<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form" %>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>의회별 자료이용 통계</title>
<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function(){
	
	//setCal("schDt1","schDt2");
	//$("#schDt1").datepicker("setDate", new Date());
	//$("#schDt2").datepicker("setDate", new Date());
	
	if("${searchVO.schDt1}" != "" && "${searchVO.schDt2}" != ""){
		var schDt1 = "${searchVO.schDt1}";
		var schDt2 = "${searchVO.schDt2}";
		
		$("#schDt1").datepicker("setDate", schDt1);
		$("#schDt2").datepicker("setDate", schDt2);
	}
	
	var searchCondition = "${searchVO.searchCondition}";
	if(searchCondition == null || searchCondition == '') searchCondition = 'T';
	$("input[name='searchCondition'][value='"+searchCondition+"']").prop('checked', true);
	$("input[name='searchCondition']").on('change', function(e){
		changeSearchCondition(e.currentTarget.value);
	});
	
	changeSearchCondition(searchCondition);

	setInsttType('INTSTTCL_000012', 0);
});

function changeSearchCondition(gubun){
	if(gubun == "L"){
		$('#searchAssembly').css('display', '');
	}else{
		$('#searchAssembly').css('display', 'none');
	}
}

//기관유형
function setInsttType(fInsttClCode, type) {
	
	var insttClCode = "${searchVO.searchInsttType}";
	
	// 지역선택 셀렉트박스 초기화
	$("select[name='searchInsttType'] option").remove();
	$("#searchInsttType").append("<option value=''>기관유형 선택</option>");	
	$("select[name='searchRegion'] option").remove();
	$("#searchRegion").append("<option value=''>지역 선택</option>");
	$("select[name='searchPsitn'] option").remove();
	$("#searchPsitn").append("<option value=''>소속 선택</option>");
	
 	$.ajax({
	   type: "POST",
	   url : "/sts/stm/selectAjaxInsttTy.do",
	   data : "fInsttClCode=" + fInsttClCode,
	   dataType:"json", 
	   async : false,
	   success: function(args) {
		   	$.each(args, function() {
			   	$("#searchInsttType").append("<option value='"+this.fInsttTyClCode+"'>"+this.fInsttTyClCodeNm+"</option>");
			   	if(type == 0) {
				   $("#searchInsttType").val(insttClCode).attr("selected", "selected");
				   setRegion(insttClCode, 0);
			   	}
			});
	   }
 		,error:function(e) {
			alert(e.responseText);
	   }
	});
}

//지역
function setRegion(insttClCode, type1) {
	
	var brtcCode = "${searchVO.searchRegion}";
	
	// 셀렉트박스 초기화
	$("select[name='searchRegion'] option").remove();
	$("#searchRegion").append("<option value=''>지역 선택</option>");
	$("select[name='searchPsitn'] option").remove();
	$("#searchPsitn").append("<option value=''>소속 선택</option>");
   
   	$.ajax({
		type: "POST",
	   	url : "/sts/stm/selectAjaxBrtc.do",
	   	dataType:"json",
	   	async : false,
	   	success: function(args) {
			$.each(args, function() {
				$("#searchRegion").append("<option value='"+this.codeId+"'>"+this.codeIdNm+"</option>");
			   	if(type1 == 0) {
				   	$("#searchRegion").val(brtcCode).attr("selected", "selected");
				   	setPsitn(brtcCode, 0);
			   	}
			   
			});
	   	}
   		,error:function(e) {
  			alert(e.responseText);
	   	}
	});
	
}

//소속
function setPsitn(brtcCode, type1) {
	var insttClCode = "";
	// 기관분류코드
// 	if(type1 == 0) {
// 		insttClCode = 'INTSTTCL_000023';	
// 	} else {
		insttClCode = $('[name="searchInsttType"]').val();
// 	}
	
   	// 소속코드
	var psitnCode = "${searchVO.searchPsitn}";
	var formData = "brtcCode=" + brtcCode + "&insttClCode=" + insttClCode;
	
	// 셀렉트박스 초기화
	$("select[name='searchPsitn'] option").remove();
	$("#searchPsitn").append("<option value=''>소속 선택</option>");

	$.ajax({
	   	type: "POST",
	   	url : "/sts/stm/selectAjaxPsitn.do",
	   	data : formData,
	   	dataType:"json",
	   	async : false,
	   	success: function(args) {
		   	$.each(args, function() {
				$("#searchPsitn").append("<option value='"+this.loasmCode+"'>"+this.loasmNm+"</option>");
			   	if(type1 == 0) {
				   	$("#searchPsitn").val(psitnCode).attr("selected", "selected");
			   	}
			});
	   	}
		,error:function(e) {
			alert(e.responseText);
	   	}
	});
	
}

function lodding() {
	$(".spinner").show();
}

/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	lodding();
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sts/stm/DusList.do'/>";
	varForm.submit();
}

/* ********************************************************
* 엑셀 다운로드
******************************************************** */
function fnExcel() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sts/stm/selectDusExcel.do'/>";
	varForm.submit();
}
</script>
<style type="text/css">
.tr{
	margin-bottom: 20px;
}
</style>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">의회별 자료이용 통계</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
			<h2>의회별 자료이용 통계 조회</h2>
			<form id="listForm" name="listForm" method="post" action="<c:url value='/sts/stm/DusList.do'/>">
			<table class="table table-striped table-bordered table-hover" >
				<colgroup>
					<col width="20%" />
					<col width="" />
				</colgroup>
			
				<tr>
					<th>구분</th>
					<td>
						<input type="radio" name="searchCondition" value="T" /> 전체
						<input type="radio" name="searchCondition" value="L" /> 지방의회
					</td>
				</tr>
				<tr id="searchAssembly" style="display:none;">
					<th>지방의회</th>
					<td>
						<select style="min-width:150px;" class=" input-sm" id="searchInsttType" name="searchInsttType" onchange="javascript:setRegion(this.value, 1);">
							<option disabled="disabled" value="">기관유형 선택</option>
						</select> 
						<select style="min-width:150px;" class=" input-sm" id="searchRegion" name="searchRegion" onchange="javascript:setPsitn(this.value, 1);">
							<option disabled="disabled" value="0">지역 선택</option>
						</select> 
						<select style="min-width:150px;" class=" input-sm" id="searchPsitn" name="searchPsitn">
							<option disabled="disabled" value="0">소속 선택</option>
						</select> 
					</td>
				</tr>
				<tr>
					<th>이용기간</th>
					<td>
						<input type="text" name="schDt1" id="schDt1" value="<c:out value="${searchVO.schDt1}" />" class="input-sm ip" style="width:150px;" />
						&nbsp;~&nbsp;
						<input type="text" name="schDt2" id="schDt2" value="<c:out value="${searchVO.schDt2}" />" class="input-sm ip" style="width:150px;" />
						
		 				<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default1">한달</button></a>
						<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default2">일주일</button></a>
						<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default3">오늘</button></a>
		 				<script type="text/javascript">setCal("schDt1","schDt2");</script>
					</td>
				</tr>
			</table>

			<div class="tr">
				<a href="#none"><button type="button" class="btn btn-primary" onclick="fnSearch();">검색</button></a>
				<a href="#none"><button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button></a>
			</div>
			</form>
			<table class="table table-striped table-bordered table-hover " id="dataTable" >
			<thead>
				<tr>
					<th colspan="2"  align="center">구분</th><th colspan="3"  align="center">이용 현황(건수)</th>
				</tr>
				<tr>
					<th align="center">카테고리</th>
					<th align="center">자료유형</th>
					<th align="center">상세보기</th>
					<th align="center">원문보기</th>
					<th align="center">다운로드</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="menu" value="1" />
						
			<c:forEach var="x" items="${categoryList }" varStatus="s">
				<c:forEach var="i" items="${itemMap[x.codeId]}" varStatus="s1">
					<tr>
						<td>
							<c:if test="${x.codeIdNm != menu}" >
								 ${x.codeIdNm }
								 <c:set var="menu"  value="${x.codeIdNm }" />
							</c:if>
						</td>
						<td>${i.codeNm} </td>
						<td id="DV_${i.code}" align="right">0</td>
						<td id="OV_${i.code}" align="right">0</td>
						<td id="DL_${i.code}" align="right">0</td>
					</tr>
				</c:forEach>
			</c:forEach>
				
			<tr>
				<td>합계</td>
				<td></td>
				<td id="DV_SUM" align="right">0</td>
				<td id="OV_SUM" align="right">0</td>
				<td id="DL_SUM" align="right">0</td>
			</tr>		
			</tbody>				
			</table>
			<script>
			<c:set var="dv_sum" value="0"/>
			<c:set var="ov_sum" value="0"/>
			<c:set var="dl_sum" value="0"/>
			<c:forEach var="i" items="${dusList }" varStatus="dus">
				$("#${i.logSeCode }_${i.rasmblyDtaSeCode }").html(numberWithCommas(<c:out value="${i.useCo}" />));
				<c:if test="${i.logSeCode eq 'DV'}"><c:set var="dv_sum" value="${dv_sum + i.useCo}"/></c:if>
				<c:if test="${i.logSeCode eq 'OV'}"><c:set var="ov_sum" value="${ov_sum + i.useCo}"/></c:if>
				<c:if test="${i.logSeCode eq 'DL'}"><c:set var="dl_sum" value="${dl_sum + i.useCo}"/></c:if>
			</c:forEach>
			$("#DV_SUM").html(numberWithCommas(<c:out value="${dv_sum}" />));
			$("#OV_SUM").html(numberWithCommas(<c:out value="${ov_sum}" />));
			$("#DL_SUM").html(numberWithCommas(<c:out value="${dl_sum}" />));
			</script>
	</div><!--//page-wrapper-->	
	
	<div class="spinner"></div>
</body>
</html>
