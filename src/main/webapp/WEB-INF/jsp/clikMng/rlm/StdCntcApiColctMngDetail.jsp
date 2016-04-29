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
<title>연계 API Key 관리 목록</title>

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
* 등록
******************************************************** */
function fnInsert() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rlm/StdCntcApiColctProc.do' />";
	
	//필수값 체크 S
	//수집지방의회
	if($('#LOASM_CODE').val() == ""){
		alert("의회를 선택해 주세요.");
		$('#LOASM_CODE').focus();
		return false;
	}
	
	//수집범위
	if($('input[name="DTA_SE_CODE"]:checked').val() == 'TAR101' || $('input[name="DTA_SE_CODE"]:checked').val() == 'TAR102'){
		if($('#BEGIN_DE').val() == ""){
			alert("수집범위 시작일자를 입력해 주세요.");
			$('#BEGIN_DE').focus();
			return false;
		}
		if($('#END_DE').val() == ""){
			alert("수집범위 종료일자를 입력해 주세요.");
			$('#END_DE').focus();
			return false;
		}
	}
	//대수
	else if($('input[name="DTA_SE_CODE"]:checked').val() == 'TAR103' || $('input[name="DTA_SE_CODE"]:checked').val() == 'TAR104'){
		if($('input[name="NUMPR_CHK"]:checked').length == 0){
			alert("대수정보를 선택해 주세요.");
			return false;
		}
	}
	
	//수집 예약일시
	if($('input[name="PROCESS_GUBUN"]:checked').val() == 'APPOINT' && $('#COLCT_RESVE_DT').val() == ""){
		alert("수집예약일시 지정일자를 입력해 주세요.");
		$('#COLCT_RESVE_DT').focus();
		return false;
	}
	//필수값 체크 E
	
	//의원,기타 일 경우 대수 정보 조합
	if($('input[name="DTA_SE_CODE"]:checked').val() == 'TAR103' || $('input[name="DTA_SE_CODE"]:checked').val() == 'TAR104'){
		$('input[name="NUMPR_CHK"]:checked').each(function(index,data){
			$('#NUMPR').val($('#NUMPR').val() + "," + data.value);
		});
		$('#NUMPR').val($('#NUMPR').val().substr(1));
	}
	
	varForm.submit();
}

/* ********************************************************
* 목록
******************************************************** */
function fnGoList() {
	if(confirm("등록을 취소하시겠습니까?")){
		var varForm = document.listForm;
		varForm.action = "<c:url value='/rlm/StdCntcApiColctMngList.do' />";
		varForm.submit();
	}
}

/* ********************************************************
* LPAD
******************************************************** */
function zeroPad(nr,base){
	var  len = (String(base).length - String(nr).length)+1;
	return len > 0? new Array(len).join('0')+nr : nr;
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
		if(!$(target).is(":checked") && $(target).val() != '0'){
			isCheckedAll = true;
			return;
		}
	});
	
	if(isCheckedAll){
		$('#allChk').prop("checked",false);
	}else{
		$('#allChk').prop("checked",true);
	}
}


$(document).ready(function() {
	
	//수집예약 시,분 설정
	var i = 0;
	var time = "";
	while(i < 12){
		time = zeroPad(i,10);
		$('#COLCT_RESVE_H').append("<option value='"+time+"'>"+time+"</option>");
		i++;
	}
	
	i = 0;
	
	while(i <= 59){
		time = zeroPad(i,10);
		$('#COLCT_RESVE_M').append("<option value='"+time+"'>"+time+"</option>");
		i++;
	}
	
	//수집구분 변경 처리
	$('input[name="DTA_SE_CODE"]').change(function(){
		if(this.value == 'TAR101' || this.value == 'TAR102'){
			if(this.value == 'TAR101'){
				$('#colct_se_type1 th').text('수집범위(회의일자)');
			}else{
				$('#colct_se_type1 th').text('수집범위(제안일자)');
			}
			$('#colct_se_type1').show();
			$('#colct_se_type2').hide();
			$('#COLCT_SE_CODE').val("CLT101");
		}else{
			$('#colct_se_type1').hide();
			$('#colct_se_type2').show();
			$('#COLCT_SE_CODE').val("CLT102");
		}
	});
	
});
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form id="listForm" name="listForm" method="post">

	<div id="page-wrapper">
        <div class="row">
	        <div class="col-lg-12">
	        	<h1 class="page-header">표준연계 API 수집요청 관리</h1>
	        </div>
       	</div>
		<!-- /.row -->

		<h2>표준연계 API 수집요청 등록</h2>
		
		<!-- 공통 S -->
		<div class="tl mb20">
			<input type="radio" name="DTA_SE_CODE" value="TAR101" <c:if test="${result.DTA_SE_CODE == 'TAR101' || result.DTA_SE_CODE == null }">checked="checked"</c:if>/> 회의록
			<input type="radio" name="DTA_SE_CODE" value="TAR102" <c:if test="${result.DTA_SE_CODE == 'TAR102' }">checked="checked"</c:if>/> 의안
			<input type="radio" name="DTA_SE_CODE" value="TAR103" <c:if test="${result.DTA_SE_CODE == 'TAR103' }">checked="checked"</c:if>/> 의원
			<input type="radio" name="DTA_SE_CODE" value="TAR104" <c:if test="${result.DTA_SE_CODE == 'TAR104' }">checked="checked"</c:if>/> 회기, 직위, 회의명, 선거구
		</div><!--//search-->
		
		<input type="hidden" id="INSTT_CL_CODE" name="INSTT_CL_CODE" value="${searchVO.INSTT_CL_CODE }" />
		<input type="hidden" id="COLCT_SE_CODE" name="COLCT_SE_CODE" value="CLT101" />
		<input type="hidden" id="MODE" name="MODE" value="" />
		<!-- 공통 E -->
		
		<div class="cont">
			<table class="table table-striped table-bordered table-hover" >
				<colgroup>
					<col width="20%" />
					<col width="" />
				</colgroup>
				<tr>
					<th>수집 지방의회</th>
					<td>
				    	<select id="INSTT_TY_CODE" name="INSTT_TY_CODE" style="min-width:150px;" onchange="localChange(this.value);">
				    		<option value="">기관유형 선택</option>
				    		<option value="INTSTTCL_000023" >광역의회</option>
				    		<option value="INTSTTCL_000024" >기초의회</option>
				    	</select>
				    	<select id="BRTC_CODE" name="BRTC_CODE" style="min-width:150px;" onchange="psitnChange(this.value);">
				    		<option value="">지역 선택</option>
				    		<c:forEach var="item" items="${brtc_code_list }">
				    		<option value="${item.codeId }" >${item.codeIdNm }</option>
				    		</c:forEach>
				    	</select>
				    	<select id="LOASM_CODE" name="LOASM_CODE" style="min-width:150px;" >
				    		<option value="">의회 선택</option>
				    		<c:forEach var="item" items="${loasm_code_list }">
				    		<option value="${item.loasmCode }" >${item.loasmNm }</option>
				    		</c:forEach>
				    	</select>
					</td>
				</tr>
				
				<tr id="colct_se_type1" style="">
					<th>수집범위(회의일자)</th>
					<td>
					<input type="text" name="BEGIN_DE" id="BEGIN_DE" value="<c:out value="" />" class="input-sm ip" style="width:150px;" />
					&nbsp;~&nbsp;
					<input type="text" name="END_DE" id="END_DE" value="<c:out value="" />" class="input-sm ip" style="width:150px;" />
					<a href="#" onclick="getDate('T','BEGIN_DE','END_DE')"><button type="button" class="btn btn-success">오늘</button></a>
					<a href="#" onclick="getDate('W','BEGIN_DE','END_DE')"><button type="button" class="btn btn-danger">일주일</button></a>
					<a href="#" onclick="getDate('M','BEGIN_DE','END_DE')"><button type="button" class="btn btn-primary">한달</button></a>
					<script>
					$('#BEGIN_DE').datepicker({
						dateFormat: 'yy-mm-dd',
						changeYear: true,
						changeMonth: true,
						showMonthAfterYear: true,
						showButtonPanel: true,
						showOn: 'button',
						buttonText: '<img src="/images/clikmng/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
						yearRange: '-100:+0',
						dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
						dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
						monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
						monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
					});
					$('#END_DE').datepicker({
						dateFormat: 'yy-mm-dd',
						changeYear: true,
						changeMonth: true,
						showMonthAfterYear: true,
						showButtonPanel: true,
						showOn: 'button',
						buttonText: '<img src="/images/clikmng/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
						yearRange: '-100:+0',
						dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
						dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
						monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
						monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
					});
					</script>
					</td>
				</tr>
				
				<tr id="colct_se_type2" style="display:none;">
					<th>수집대수</th>
					<td>
						<input type="hidden" id="NUMPR" name="NUMPR" value=""/>
						<input type="checkbox" name="NUMPR_CHK" id="allChk" onclick="javascript:fn_all_chk();" value="0"/>  전체
						<input type="checkbox" name="NUMPR_CHK" value="1" onclick="javascript:fn_cb_chk();"/> 제1대
						<input type="checkbox" name="NUMPR_CHK" value="2" onclick="javascript:fn_cb_chk();"/> 제2대
						<input type="checkbox" name="NUMPR_CHK" value="3" onclick="javascript:fn_cb_chk();"/> 제3대
						<input type="checkbox" name="NUMPR_CHK" value="4" onclick="javascript:fn_cb_chk();"/> 제4대
						<input type="checkbox" name="NUMPR_CHK" value="5" onclick="javascript:fn_cb_chk();"/> 제5대
						<input type="checkbox" name="NUMPR_CHK" value="6" onclick="javascript:fn_cb_chk();"/> 제6대
						<input type="checkbox" name="NUMPR_CHK" value="7" onclick="javascript:fn_cb_chk();"/> 제7대
						<input type="checkbox" name="NUMPR_CHK" value="8" onclick="javascript:fn_cb_chk();"/> 제8대
						<input type="checkbox" name="NUMPR_CHK" value="9" onclick="javascript:fn_cb_chk();"/> 제9대
						<input type="checkbox" name="NUMPR_CHK" value="10" onclick="javascript:fn_cb_chk();"/> 제10대

						<button id="btn_council" type="button" class="btn btn-default">의회별 대수정보</button>
					</td>
				</tr>
				
				<tr>
					<th>수집 예약일시</th>
					<td>
						<input type="radio" name="PROCESS_GUBUN" value="PROMPT" checked="checked"/> 즉시 (10분의 오차가 발생할 수있음)
						<input type="radio" name="PROCESS_GUBUN" value="APPOINT" /> 지정

						<input type="text" name="COLCT_RESVE_DT" id="COLCT_RESVE_DT" value="<c:out value="" />" class="input-sm ip" style="width:150px;" />
						<script>
						$('#COLCT_RESVE_DT').datepicker({
							dateFormat: 'yy-mm-dd',
							changeYear: true,
							changeMonth: true,
							showMonthAfterYear: true,
							showButtonPanel: true,
							showOn: 'button',
							buttonText: '<img src="/images/clikmng/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
							yearRange: '-100:+0',
							dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
							dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
							monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
							monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
							minDate : +0
						});
						</script>

						<select  class=" input-sm" id="COLCT_RESVE_H" name="COLCT_RESVE_H"></select> 시
						<select  class=" input-sm" id="COLCT_RESVE_M" name="COLCT_RESVE_M"></select> 분
					</td>
				</tr>
			</table>
			
			<p class="tr">
				<button type="button" class="btn btn-danger" onclick="fnGoList();">취소</button>
				<button type="button" class="btn btn-primary" onclick="fnInsert();">저장</button>
			</p>
			
		</div><!--//cont-->

	</div><!--//page-wrapper-->
</form>

//의회별 대수 정보
<jsp:include page="/rlm/councilInfo.do" />
	
</body>
</html>