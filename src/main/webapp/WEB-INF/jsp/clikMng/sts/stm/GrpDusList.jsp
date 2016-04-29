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
<title>이용자 그룹별 자료이용 통계</title>
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
	varForm.action = "<c:url value='/sts/stm/GrpDusList.do'/>";
	varForm.submit();
}

/* ********************************************************
* 엑셀 다운로드
******************************************************** */
function fnExcel() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sts/stm/selectGrpDusExcel.do'/>";
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
                    <h1 class="page-header">이용자 그룹별 자료이용 통계</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
			<h2>이용자 그룹별 자료이용 통계</h2>
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
			
			<div class="tl mb20" style="border:1px solid #ccc; background:#f9f9f9; padding:10px 15px; line-height:180%; color:#666;">
				 [국회.지방의회 의정자료 공유통합시스템]을 이용하는 로그인 기준 이용자(지방의회담당자, 국회직원, 일반사용자)별 자료이용에 대한 통계입니다. 
				<br/>통계 기준은 [국회.지방의회 의정자료 공유통합시스템]에서 카테고리/자료유형별 검색 결과 목록상에서 이용자가 발생시키는 이벤트만 해당합니다. 
				<br/>검색 결과 목록상에서 통계에 취합되는 이벤트는 자료제목클릭, 원문보기클릭, 원문다운로드클릭 입니다. 상세페이지 내에서 발생하는 이벤트에 대한 통계는 포함되지 않았습니다. 
			</div>
			</form>
			<table class="table table-striped table-bordered table-hover " id="dataTable" >
			<thead>
				<tr>
					<th colspan="2"  align="center">구분</th>
					<th colspan="3"  align="center">광역의회</th>
					<th colspan="3"  align="center">기초의회</th>
					<th align="center">국회</th>
					<th align="center">일반</th>
				</tr>
				<tr>
					<th align="center">카테고리</th>
					<th align="center">자료유형</th>
					<th align="center">직원</th>
					<th align="center">관리자</th>
					<th align="center">의원</th>
					<th align="center">직원</th>
					<th align="center">관리자</th>
					<th align="center">의원</th>
					<th align="center"></th>
					<th align="center"></th>
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
						<td id="WAC001_${i.code}" align="right">0</td>
						<td id="WAC002_${i.code}" align="right">0</td>
						<td id="WAC003_${i.code}" align="right">0</td>
						<td id="BAC001_${i.code}" align="right">0</td>
						<td id="BAC002_${i.code}" align="right">0</td>
						<td id="BAC003_${i.code}" align="right">0</td>
						<td id="ASM_${i.code}" align="right">0</td>
						<td id="NOR_${i.code}" align="right">0</td>
					</tr>
				</c:forEach>
			</c:forEach>
				
			<tr>
				<td>합계</td>
				<td></td>
				<td id="WAC001_SUM" align="right">0</td>
				<td id="WAC002_SUM" align="right">0</td>
				<td id="WAC003_SUM" align="right">0</td>
				<td id="BAC001_SUM" align="right">0</td>
				<td id="BAC002_SUM" align="right">0</td>
				<td id="BAC003_SUM" align="right">0</td>
				<td id="ASM_SUM" align="right">0</td>
				<td id="NOR_SUM" align="right">0</td>
			</tr>		
			</tbody>				
			</table>
			<script>
			
			<c:set var="wac001_sum" value="0"/>
			<c:set var="wac002_sum" value="0"/>
			<c:set var="wac003_sum" value="0"/>
			<c:set var="bac001_sum" value="0"/>
			<c:set var="bac002_sum" value="0"/>
			<c:set var="bac003_sum" value="0"/>
			<c:set var="asm_sum" value="0"/>
			<c:set var="nor_sum" value="0"/>
			
			<c:forEach var="i" items="${dusList}" varStatus="dus">
				<c:if test="${i.rasmblyDtaSeCode != null}">
				$("#WAC001_${i.rasmblyDtaSeCode }").html(numberWithCommas(<c:out value="${i.WAC001}" />));
				$("#WAC002_${i.rasmblyDtaSeCode }").html(numberWithCommas(<c:out value="${i.WAC002}" />));
				$("#WAC003_${i.rasmblyDtaSeCode }").html(numberWithCommas(<c:out value="${i.WAC003}" />));
				$("#BAC001_${i.rasmblyDtaSeCode }").html(numberWithCommas(<c:out value="${i.BAC001}" />));
				$("#BAC002_${i.rasmblyDtaSeCode }").html(numberWithCommas(<c:out value="${i.BAC002}" />));
				$("#BAC003_${i.rasmblyDtaSeCode }").html(numberWithCommas(<c:out value="${i.BAC003}" />));
				$("#ASM_${i.rasmblyDtaSeCode }").html(numberWithCommas(<c:out value="${i.ASM}" />));
				$("#NOR_${i.rasmblyDtaSeCode }").html(numberWithCommas(<c:out value="${i.NOR}" />));
				
				<c:set var="wac001_sum" value="${wac001_sum + i.WAC001}"/>
				<c:set var="wac002_sum" value="${wac002_sum + i.WAC002}"/>
				<c:set var="wac003_sum" value="${wac003_sum + i.WAC003}"/>
				<c:set var="bac001_sum" value="${bac001_sum + i.BAC001}"/>
				<c:set var="bac002_sum" value="${bac002_sum + i.BAC002}"/>
				<c:set var="bac003_sum" value="${bac003_sum + i.BAC003}"/>
				<c:set var="asm_sum" value="${asm_sum + i.ASM}"/>
				<c:set var="nor_sum" value="${nor_sum + i.NOR}"/>
				</c:if>
			</c:forEach>
			
			$("#WAC001_SUM").html(numberWithCommas(<c:out value="${wac001_sum}" />));
			$("#WAC002_SUM").html(numberWithCommas(<c:out value="${wac002_sum}" />));
			$("#WAC003_SUM").html(numberWithCommas(<c:out value="${wac003_sum}" />));
			$("#BAC001_SUM").html(numberWithCommas(<c:out value="${bac001_sum}" />));
			$("#BAC002_SUM").html(numberWithCommas(<c:out value="${bac002_sum}" />));
			$("#BAC003_SUM").html(numberWithCommas(<c:out value="${bac003_sum}" />));
			$("#ASM_SUM").html(numberWithCommas(<c:out value="${asm_sum}" />));
			$("#NOR_SUM").html(numberWithCommas(<c:out value="${nor_sum}" />));
			</script>
	</div><!--//page-wrapper-->	
	
	<div class="spinner"></div>
</body>
</html>
