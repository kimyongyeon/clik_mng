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
<title>통계정보관리</title>
<script type="text/javaScript" language="javascript" defer="defer">

//기수 변경 취소시 사용하는 변수
var prev_HRSMNPD_SN = "";

$(document).ready(function(){
	
	$("#HRSMNPD_SN").on('focus', function () {
		 prev_HRSMNPD_SN = this.value;
    });
	    
	//검색 후 정보 설정
	if($('#HRSMNPD_SN').val() != ""){
		$('#ht_se_stdcd').val($('#HRSMNPD_SN :selected').val().split("@")[1]);
		
		var hrsmnpd = $('#HRSMNPD_SN > option:selected').text();
		$('#hrsmnpd_nm').val(hrsmnpd.substr(0, (hrsmnpd.indexOf('[') - 1)));
		$('#ht_se_stdcd_nm').val(hrsmnpd.substr(hrsmnpd.indexOf('[') + 1, (hrsmnpd.indexOf(']') - hrsmnpd.indexOf('[') - 1)));
		$('#begin_de').val(hrsmnpd.substr(hrsmnpd.indexOf('(') + 1, (hrsmnpd.indexOf('~') - hrsmnpd.indexOf('(') - 1)));
		$('#end_de').val(hrsmnpd.substr(hrsmnpd.indexOf('~') + 1, (hrsmnpd.indexOf(')') - hrsmnpd.indexOf('~') - 1)));
	}else{
		$('#table_asemby tbody tr').remove();
		$('#table_prmpst_cmit tbody tr').remove();
		$('#table_bill_stats_indo tbody tr').remove();
		$('input[name^="sbd_"]').val('0');
		$('span[id^="sbd_"]').text('');
		$('#MINTS_CO').val('');
		
		$('#INSTT_TY_CODE').val('');
		localChange('');
		
		$('#HRSMNPD_SN').val('');
		
		$('div.search select').each(function(index,target){
			$(target).prop('disabled',false);
		});
		
		$('#RASMBLY_NUMPR').val('');
		$('#RASMBLY_NUMPR').prop('disabled',false);
		
		$('#aSearch').show();
		$('#aReset').hide();
	}
	
	// 콤마 처리 S
	if($('#asemby_cnt').text() != ""){
		$('#asemby_cnt').text(commaNum($('#asemby_cnt').text()));
	}
	if($('#wdr_asemby_cnt').text() != ""){
		$('#wdr_asemby_cnt').text(commaNum($('#wdr_asemby_cnt').text()));
	}
	if($('#wdr_asemby_area_sm').text() != ""){
		$('#wdr_asemby_area_sm').text(commaNum($('#wdr_asemby_area_sm').text()));
	}
	if($('#wdr_asemby_prport_sm').text() != ""){
		$('#wdr_asemby_prport_sm').text(commaNum($('#wdr_asemby_prport_sm').text()));
	}
	for(var i = 1; i < 9; i++){
		$('#sbd_1_'+i).text(commaNum($('#sbd_1_'+i).text()));
	}
	// 콤마처리 E
});



/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	
	if($('#INSTT_TY_CODE').val() == ""){
		alert("기관유형 정보를 선택해 주세요.");
		$('#INSTT_TY_CODE').focus();
		return false;
	}
	
	if($('#BRTC_CODE').val() == ""){
		alert("지역 정보를 선택해 주세요.");
		$('#BRTC_CODE').focus();
		return false;
	}
	
	if($('#LOASM_CODE').val() == ""){
		alert("의회 정보를 선택해 주세요.");
		$('#LOASM_CODE').focus();
		return false;
	}
	
	if($('#HRSMNPD_SN').val() == ""){
		alert("기수 정보를 선택해 주세요.");
		$('#HRSMNPD_SN').focus();
		return false;
	}
	
	if($('#RASMBLY_NUMPR').val() == ""){
		alert("대수 정보를 입력해 주세요.");
		$('#RASMBLY_NUMPR').focus();
		return false;
	}
	
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sts/stm/StmInfoMngDetail.do'/>";
	
	$('#RASMBLY_NUMPR').removeAttr("disabled");
	
	varForm.submit();
}

/* ********************************************************
* 기관유형 검색
******************************************************** */
function insttTyChange(fInsttClCode) {
	// 지역선택 셀렉트박스 초기화
	$("#LOASM_TY_CODE option").remove();
	$("#LOASM_TY_CODE").append("<option value=''>기관유형 선택</option>");	
	$("#BRTC_CODE option").remove();
	$("#BRTC_CODE").append("<option value=''>지역 선택</option>");
	$("#LOASM_CODE option").remove();
	$("#LOASM_CODE").append("<option value=''>선택</option>");
	
 	$.ajax({
	   type: "POST",
	   url : "/sts/stm/selectAjaxInsttTy.do",
	   data : "fInsttClCode=" + fInsttClCode,
	   dataType:"json", 
	   async : false,
	   success: function(args) {
		   $.each(args, function() {
			   $("#LOASM_TY_CODE").append("<option value='"+this.fInsttTyClCode+"'>"+this.fInsttTyClCodeNm+"</option>");
			});
	   }
 		,error:function(e) {
			alert(e.responseText);
	   }
	});
} 

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
* 상임의원회 설치내역 추가
******************************************************** */
function fn_add_prmpst_cmit(){
	
	var tempId = "tempId"+$('#table_prmpst_cmit > tbody > tr').length;
	var str = "";
	
	if($('#table_asemby > tbody > tr').length == 0){
		alert("검색 후 사용해 주세요.");
		return false;
	}

	if($('#RASMBLY_NUMPR').val() == ""){
		alert("대수를 입력해 주세요");
		$('#RASMBLY_NUMPR').focus();
		return false;
	}
	
	if($('#table_prmpst_cmit > tbody > tr').length == 0){
		str += '<tr>';
		str += '	<td rowspan="2">' + $('#LOASM_CODE :selected').text() + '(' + $('#RASMBLY_NUMPR').val() + '대) ' + $('#begin_de').val() + ' ~ ' + $('#end_de').val() + ' (' + $('#ht_se_stdcd_nm').val() + ')' + '</td>';
		str += '	<td rowspan="1">0</td>';
		str += '	<td rowspan="1">0</td>';
		str += '</tr>';
		str += '<tr>';
		str += '	<td><input type="text" id="'+tempId+'" name="prmpst_cmit_nm" value="" style="width:80%"/></td>';
		str += '	<td><input type="text" id="'+tempId+'" name="prmpst_asemby_cnt" style="width:90px;" value="" onkeydown="checkNumber(event);" maxLength="4"/> <a href="#none" class="btn btn-default btn-sm" onclick="fn_delete_prmpst_cmit(this);">삭제</a></td>';
		str += '</tr>';
	}else{
		str += '<tr>';
		str += '	<td><input type="text" id="'+tempId+'" name="prmpst_cmit_nm" value="" style="width:80%"/></td>';
		str += '	<td><input type="text" id="'+tempId+'" name="prmpst_asemby_cnt" style="width:90px;" value="" onkeydown="checkNumber(event);" maxLength="4"/> <a href="#none" class="btn btn-default btn-sm" onclick="fn_delete_prmpst_cmit(this);">삭제</a></td>';
		str += '</tr>';
	}
	$('#table_prmpst_cmit > tbody').append(str);
	
	var tr = $('#table_prmpst_cmit > tbody > tr').eq(0);
	
	$(tr).find('td').each(function(index, value){
		if(index < 3){
			$(value).attr('rowspan',Number($(value).attr('rowspan'))+1);
		}	
	});
	
	$('#table_prmpst_cmit > tbody > tr').eq(0).find('td').eq(2).text($('#table_prmpst_cmit > tbody > tr').length -1);
}

/* ********************************************************
* 상임위원회 설치내역 삭제
******************************************************** */
function fn_delete_prmpst_cmit(el){
	if(confirm("삭제 하시겠습니까?")){
		$(el).parent().parent().remove();
		$('#table_prmpst_cmit > tbody > tr').eq(0).find('td').eq(2).text($('#table_prmpst_cmit > tbody > tr').length -1);
	}
	
	if($('#table_prmpst_cmit > tbody > tr').length == 1){
		$('#table_prmpst_cmit > tbody > tr').remove();
	}
}


/* ********************************************************
* 기수기간 변경
******************************************************** */
function fn_change_hrsmnpd(){
	
	if($('#INSTT_CL_CODE').val() == ""){
		return false;
	}
	
	if($('#BRTC_CODE').val() == ""){
		return false;
	}
	
	if($('#LOASM_CODE').val() == ""){
		return false;
	}
	
	if($('#HRSMNPD_SN').val() != "" && confirm("저장하지 않은 정보는 초기화 됩니다. 선택한 기수로 검색 하시곘습니까?")){
		
		$('#table_asemby tbody tr').remove();
		$('#table_prmpst_cmit tbody tr').remove();
		$('#table_bill_stats_indo tbody tr').remove();
		$('input[name^="sbd_"]').val('0');
		$('span[id^="sbd_"]').text('');
		
		$('#ht_se_stdcd').val($('#HRSMNPD_SN').val().split("#")[1]);
		
		var hrsmnpd = $('#HRSMNPD_SN > option:selected').text();
		$('#hrsmnpd_nm').val(hrsmnpd.substr(0, (hrsmnpd.indexOf('[') - 1)));
		$('#ht_se_stdcd_nm').val(hrsmnpd.substr(hrsmnpd.indexOf('[') + 1, (hrsmnpd.indexOf(']') - hrsmnpd.indexOf('[') - 1)));
		$('#begin_de').val(hrsmnpd.substr(hrsmnpd.indexOf('(') + 1, (hrsmnpd.indexOf('~') - hrsmnpd.indexOf('(') - 1)));
		$('#end_de').val(hrsmnpd.substr(hrsmnpd.indexOf('~') + 1, (hrsmnpd.indexOf(')') - hrsmnpd.indexOf('~') - 1)));
	
		$.ajax({
			type : "POST"
			,url : "/sts/stm/selectRasmblyNumpr.do"
			,data: {
				hrsmnpd_sn : $('#HRSMNPD_SN').val()
				,rasmbly_id : $('#LOASM_CODE').val()}
			,data_type : "json"
			,success : function(result){
				$('#RASMBLY_NUMPR').val('');
				if($.parseJSON(result)[0] != undefined && $.parseJSON(result)[0].rasmbly_numpr != null){
					$('#RASMBLY_NUMPR').val($.parseJSON(result)[0].rasmbly_numpr);
					$('#RASMBLY_NUMPR').prop('readonly',true);
					fnSearch();
				}else{
					$('#RASMBLY_NUMPR').prop("disabled",false);
					$('#RASMBLY_NUMPR').prop('readonly',false);
					
					alert("기존 입력된 정보가 존재하지 않습니다. 대수 입력후 검색하여 주세요.");
				}
			}
		});
	}else{
		$('#HRSMNPD_SN').val(prev_HRSMNPD_SN);
	}
}

/* ********************************************************
* 등록
******************************************************** */
function fn_register(){
	if(!confirm("저장 하시겠습니까?")){
		return false;
	}
	
	if($('#RASMBLY_NUMPR').val() == ""){
		alert("대수를 입력해 주세요.");
		$('#RASMBLY_NUMPR').focus();
		return false;
	}
	
	if($('#table_asemby > tbody > tr').length == 0){
		alert("검색 후 정보를 입력해 주세요.");
		return false;
	}
	
	//기수 정보 변경 없이 수정할 경우 등록 정보 생성을 위해서 선 처리
	if($('#HRSMNPD_SN').val() != ""){
		$('#ht_se_stdcd').val($('#HRSMNPD_SN').val().split("#")[1]);
		
		var hrsmnpd = $('#HRSMNPD_SN > option:selected').text();
		$('#hrsmnpd_nm').val(hrsmnpd.substr(0, (hrsmnpd.indexOf('[') - 1)));
		$('#ht_se_stdcd_nm').val(hrsmnpd.substr(hrsmnpd.indexOf('[') + 1, (hrsmnpd.indexOf(']') - hrsmnpd.indexOf('[') - 1)));
		$('#begin_de').val(hrsmnpd.substr(hrsmnpd.indexOf('(') + 1, (hrsmnpd.indexOf('~') - hrsmnpd.indexOf('(') - 1)));
		$('#end_de').val(hrsmnpd.substr(hrsmnpd.indexOf('~') + 1, (hrsmnpd.indexOf(')') - hrsmnpd.indexOf('~') - 1)));
	}
	
	//정당별 의원
	var pprty_asemby = "";
	var asemby_sum = 0;
	$('input[name="area_asemby_sm"]').each(function(index,value){
		$('input[name="prport_asemby_sm"]').each(function(index2,value2){
			if($(value).attr('id') == $(value2).attr('id'))
			{
				if($(value).val() == "") $(value).val('0');
				if($(value2).val() == "") $(value2).val('0');
				asemby_sum += Number($(value).val());
				asemby_sum += Number($(value2).val());
				pprty_asemby += ","+($(value).attr('id') +"#"+$(value).val()+"#"+$(value2).val());
			}
		});
	});
	
	pprty_asemby = pprty_asemby.substr(1);
	
	
	//상임위원회
	var prmpst_cmit = "";
	var flag = true;
	$('input[name="prmpst_cmit_nm"]').each(function(index,value){
		$('input[name="prmpst_asemby_cnt"]').each(function(index2,value2){
			if($(value).attr('id') == $(value2).attr('id'))
			{
				if($(value).val() == ""){
					alert("상임위원회명을 입력해 주세요.");
					flag = false;
					return false;
				}
				
				if($(value2).val() == "") $(value2).val('0');
				
				prmpst_cmit += ","+($(value).attr('id') +"#"+$(value).val()+"#"+$(value2).val());
			}
		});
	});
	
	prmpst_cmit = prmpst_cmit.substr(1);
	
	//의안통계정보
	var bill_info = "";
	for(var i = 2; i <= 9; i++){
		for(var j = 0; j <= 8; j++){
			var value = Number($('#sbd_' + i + '_' + j).val());
			
			bill_info += value + "#";
		}	
		bill_info += ",";
	}
	
	bill_info = bill_info.substr(0,bill_info.length);
	
	if(flag){
	$.ajax({
		type : "POST"
		,url : "/sts/stm/stmInfoMngProc.do"
		,data: {
				rasmbly_id : $('#LOASM_CODE').val()
				,hrsmnpd_sn : $('#HRSMNPD_SN').val().split("@")[0]
				,ht_se_stdcd : $('#HRSMNPD_SN').val().split("@")[1]
				,bill_info : bill_info
				,mints_co : $('#MINTS_CO').val()
				,numpr : $('#RASMBLY_NUMPR').val()
				,hrsmnpd_nm : $('#hrsmnpd_nm').val() 
				,begin_de : $('#begin_de').val()
				,end_de : $('#end_de').val()
				,rasmbly_nm : $('#LOASM_CODE :selected').text()
				,ht_se_stdcd_nm : $('#ht_se_stdcd_nm').val()
				,wdr_asemby_sm : (Number($('#wdr_asemby_area_sm').text().replace(",","")) + Number($('#wdr_asemby_prport_sm').text().replace(",","")))
				,asemby_sm : asemby_sum
				,loasm_ty_code : $('#loasm_ty_code').val()
				,wdr_asemby_area_sm : $('#wdr_asemby_area_sm').text().replace(",","")
				,wdr_asemby_prport_sm : $('#wdr_asemby_prport_sm').text().replace(",","")
				,edc_asemby_sm : $('#edc_asemby_sm').val()
				,pprty_asemby : pprty_asemby
				,prmpst_cmit : prmpst_cmit
				,prmpst_cmit_co : $('#asemby_cnt').text().replace(",","")
				}
		,data_type : "json"
		,success : function(result){
			
			alert($.parseJSON(result)[0].result_msg);
			
			if($.parseJSON(result)[0].result_code == "success"){
				//fnSearch();
				window.location.href = '/sts/stm/StmInfoMngList.do';
			}
		}
	});
	}
}

/* ********************************************************
* 취소버튼
******************************************************** */
function fn_cancel(){
	if(confirm("목록화면으로 이동하시겠습니까?")){
// 		var varForm = document.listForm;
// 		varForm.action = "<c:url value='/sts/stm/StmInfoMngList.do'/>";
// 		varForm.submit();

		location.href = "<c:url value='/sts/stm/StmInfoMngList.do'/>";
	}
}

/* ********************************************************
* 의원 합산
******************************************************** */
function fn_auto_sum(){
	var wdr_area_sum = 0;
	var wdr_prport_sum = 0;
	$('#table_asemby > tbody > tr input[name="area_asemby_sm"]').each(function(index, value){
		wdr_area_sum += Number($(value).val());
	});
	$('#table_asemby > tbody > tr input[name="prport_asemby_sm"]').each(function(index, value){
		wdr_prport_sum += Number($(value).val());
	});
	
	$('#wdr_asemby_area_sm').text(commaNum(wdr_area_sum));
	$('#wdr_asemby_prport_sm').text(commaNum(wdr_prport_sum));
	$('#wdr_asemby_cnt').text(commaNum(wdr_area_sum + wdr_prport_sum));
	$('#asemby_cnt').text(commaNum(wdr_area_sum + wdr_prport_sum + Number($('#edc_asemby_sm').val())));
}

/* ********************************************************
* 의안통계 합산
******************************************************** */
function changeTotalCount(input){
	var total = 0;
	for(var i = 2; i <= 9; i++){
		for(var j = 2; j < 8; j++){
			total += Number($('#sbd_'+i+'_'+j).val());
		}
		$('#sbd_'+i+'_1').val(total);
		$('#sbd_'+i+'_total').text(commaNum(total));
		total = 0;
	}
	
	//총계
	total = 0;
	for(var i = 1; i <= 8; i++){
		for(var j = 2; j <= 9; j++){
			total += Number($('#sbd_'+j+'_'+i).val());
		}
		$('#sbd_1_'+i).text(commaNum(total));
		total = 0;
	}
}

/* ********************************************************
* 천단위 콤마찍기
******************************************************** */
function commaNum(num) {  
    var len, point, str;  

    num = num + "";  
    point = num.length % 3;
    len = num.length;  

    str = num.substring(0, point);  
    while (point < len) {  
        if (str != "") str += ",";  
        str += num.substring(point, point + 3);  
        point += 3;  
    }  
    return str;  
}

/* ********************************************************
* 정보 초기화 및 조회 조건 변경 처리
******************************************************** */
function fnReset(){
	if(confirm("저장하지 않은 정보는 초기화 됩니다. 조회조건 및 조회정보를 초기화 하시겠습니까?")){
		$('#table_asemby tbody tr').remove();
		$('#table_prmpst_cmit tbody tr').remove();
		$('#table_bill_stats_indo tbody tr').remove();
		$('input[name^="sbd_"]').val('0');
		$('span[id^="sbd_"]').text('');
		$('#MINTS_CO').val('');
		
		$('#INSTT_TY_CODE').val('');
		localChange('');
		
		$('#HRSMNPD_SN').val('');
		
		$('div.search select').each(function(index,target){
			$(target).prop('disabled',false);
		});
		
		$('#RASMBLY_NUMPR').val('');
		$('#RASMBLY_NUMPR').prop('disabled',false);
		
		$('#aSearch').show();
		$('#aReset').hide();
	}
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
		<!-- /.row -->
		
		<input type="hidden" id="hrsmnpd_nm" name="hrsmnpd_nm" value="" />
		<input type="hidden" id="ht_se_stdcd" name="ht_se_stdcd" value="" />
		<input type="hidden" id="ht_se_stdcd_nm" name="ht_se_stdcd_nm" value="" />
		<input type="hidden" id="begin_de" name="begin_de" value="" />
		<input type="hidden" id="end_de" name="end_de" value="" />
		<input type="hidden" id="loasm_ty_code" name="loasm_ty_code" value="${searchVO.LOASM_TY_CODE }" />
		
		<h2>통계정보관리</h2>
		<form id="listForm" name="listForm" method="post" action="<c:url value='/sts/stm/StmMngList.do'/>" enctype="multipart/form-data">
			<input type="hidden" id="INSTT_CL_CODE" name="INSTT_CL_CODE" value="${searchVO.INSTT_CL_CODE }" />
			<div class="search" style="text-align:left;">
		    	기관유형:
		    	<select id="INSTT_TY_CODE" name="INSTT_TY_CODE" style="width:12%;" onchange="localChange(this.value);" <c:if test="${searchVO.LOASM_CODE != null }">disabled="disabled"</c:if>>
		    		<option value="">기관유형 선택</option>
		    		<option value="INTSTTCL_000023" <c:if test="${searchVO.INSTT_TY_CODE == 'INTSTTCL_000023'}">selected="selected"</c:if>>광역의회</option>
		    		<option value="INTSTTCL_000024" <c:if test="${searchVO.INSTT_TY_CODE == 'INTSTTCL_000024'}">selected="selected"</c:if>>기초의회</option>
		    	</select>
		    	지역:
		    	<select id="BRTC_CODE" name="BRTC_CODE" onchange="psitnChange(this.value);" <c:if test="${searchVO.LOASM_CODE != null }">disabled="disabled"</c:if>>
		    		<option value="">지역 선택</option>
		    		<c:forEach var="item" items="${brtc_code_list }">
		    		<option value="${item.codeId }" <c:if test="${searchVO.BRTC_CODE == item.codeId}">selected="selected"</c:if>>${item.codeIdNm }</option>
		    		</c:forEach>
		    	</select>
		    	의회:
		    	<select id="LOASM_CODE" name="LOASM_CODE" <c:if test="${searchVO.LOASM_CODE != null }">disabled="disabled"</c:if>>
		    		<option value="">의회 선택</option>
		    		<c:forEach var="item" items="${loasm_code_list }">
		    		<option value="${item.loasmCode }" <c:if test="${searchVO.LOASM_CODE == item.loasmCode}">selected="selected"</c:if>>${item.loasmNm }</option>
		    		</c:forEach>
		    	</select>
		    	기수:
				<select id="HRSMNPD_SN" name="HRSMNPD_SN" onchange="fn_change_hrsmnpd();" <c:if test="${searchVO.LOASM_CODE != null }">disabled="disabled"</c:if>>
					<option value="">기수 선택</option>
					<c:forEach items="${hrsmnpdList}" var="item">
					<option value="${item.hrsmnpd_sn}@HTS001" <c:if test="${searchVO.HRSMNPD_SN == item.hrsmnpd_sn && searchVO.HT_SE_STDCD == 'HTS001'}">selected="selected"</c:if>>${item.hrsmnpd_nm } [전반기] (${item.frhfyr_begin_de }~${item.frhfyr_end_de })</option>
					<option value="${item.hrsmnpd_sn}@HTS002" <c:if test="${searchVO.HRSMNPD_SN == item.hrsmnpd_sn && searchVO.HT_SE_STDCD == 'HTS002'}">selected="selected"</c:if>>${item.hrsmnpd_nm } [후반기] (${item.shyy_begin_de }~${item.shyy_end_de })</option>
					</c:forEach>
				</select>
				
				대수 
				<input id="RASMBLY_NUMPR" name="RASMBLY_NUMPR" type="text" class="input-sm ip" style="width:5%;" value="${searchVO.RASMBLY_NUMPR}" <c:if test="${searchVO.RASMBLY_NUMPR != ''}">disabled="disabled"</c:if>/> 
			 	
			 	
				<a href="#none" id="aSearch" <c:if test="${searchVO.LOASM_CODE != null }">style="display:none;"</c:if>><button type="button" class="btn btn-primary" onclick="fnSearch();">검색</button></a> 
				<a href="#none" id="aReset" <c:if test="${searchVO.LOASM_CODE == null }">style="display:none;"</c:if>><button type="button" class="btn btn-primary" onclick="fnReset();">조회 정보수정</button></a>
			</div>
		</form>
		
		<h2>의회-정당별의원</h2>
		<table id="table_asemby" name="table_asemby" class="table table-striped table-bordered table-hover tc" summary="의원정수(A=B+C),광역 의원(B),정당별,교육의원(C) 구성되어 있습니다.">
			<c:if test="${head1 == null }">
			<thead>
				<tr>
					<th scope="col" rowspan="3">의원정수(A=B+C)</th>
					<th scope="col" colspan="3">광역 의원(B)</th>
					<th scope="col" colspan="10">정당별</th>
					<th scope="col" rowspan="3">교육의원(C)</th>
				</tr>
				<tr>
					<th scope="col" rowspan="2">계</th>
					<th scope="col" rowspan="2">지역</th>
					<th scope="col" rowspan="2">비례</th>
					<th scope="col" colspan="2">새누리당</th>
					<th scope="col" colspan="2">새정치</th>
					<th scope="col" colspan="2">통합진보</th>
					<th scope="col" colspan="2">기타</th>
					<th scope="col" colspan="2">무소속</th>
				</tr>
				<tr>
					<th scope="col">지역</th>
					<th scope="col">비례</th>
					<th scope="col">지역</th>
					<th scope="col">비례</th>
					<th scope="col">지역</th>
					<th scope="col">비례</th>
					<th scope="col">지역</th>
					<th scope="col">비례</th>
					<th scope="col">지역</th>
					<th scope="col">비례</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
			</c:if>
			<c:if test="${head1 != null }">
				${head1 }
				${body }
			</c:if>
		</table>
		
		<h2>상임위원회 설치내역</h2>
		<div class="btn_list" style="margin-bottom:5px; float:right;">
			<a href="#none" class="btn btn-default btn-sm" onclick='fn_add_prmpst_cmit();'>위원회추가</a>
		</div>
		<table id="table_prmpst_cmit" name="table_prmpst_cmit" class="table table-striped table-bordered table-hover" summary="상임위원회 대수,의원정수,상임위원회수,상임위원회 설치내역,상임위원회명,상임위원수 구성되어 있습니다.">
			<thead>
				<tr>
				<th scope="col" rowspan="2">상임위원회 대수</th>
				<th scope="col" rowspan="2">의원정수</th>
				<th scope="col" rowspan="2">상임위원회수</th>
				<th scope="col" colspan="2">상임위원회 설치내역</th>
				</tr>
				<tr>
				<th scope="col">상임위원회명</th>
				<th scope="col">상임위원수</th>
				</tr>
			</thead>
			<tbody>
				${PrmpstCmitList }
			</tbody>
		</table>
		
		<h2>의안통계정보</h2>
		<table id="table_bill_stats_indo" class="table table-striped table-bordered table-hover tc" >
			<colgroup>
			<col width="10%" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			<col width="" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col" rowspan="3">구분</th>
					<th scope="col" rowspan="3">처리</th>
					<th scope="col" colspan="6">처리내용</th>
					<th scope="col" rowspan="3">미처리(계류)</th>
				</tr>
				<tr>
					<th scope="col" colspan="2">가결</th>
					<th scope="col" rowspan="2">부결</th>
					<th scope="col" rowspan="2">폐기</th>
					<th scope="col" rowspan="2">철회</th>
					<th scope="col" rowspan="2">기타</th>
				</tr>
				<tr>
					<th scope="col">원안</th>
					<th scope="col">수정</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${StatsBillList == null }">
				<tr>
					<th scope="col">총계</th>
					<td scope='col'><span id='sbd_1_1' ></span></td>
					<td scope='col'><span id='sbd_1_2' ></span></td>
					<td scope='col'><span id='sbd_1_3' ></span></td>
					<td scope='col'><span id='sbd_1_4' ></span></td>
					<td scope='col'><span id='sbd_1_5' ></span></td>
					<td scope='col'><span id='sbd_1_6' ></span></td>
					<td scope='col'><span id='sbd_1_7' ></span></td>
					<td scope='col'><span id='sbd_1_8' ></span></td>
				</tr>
				<tr>
					<th scope="col">조례안</th>
					<td scope="col" style='display:none;'><input type="text" id="sbd_2_0" name="sbd_2_0" /></td>
					<td scope="col"><span id="sbd_2_total"></span><input type="text" id="sbd_2_1" name="sbd_2_1" style="display:none;" value="0"/></td>
					<td scope="col"><input type="text" id="sbd_2_2" name="sbd_2_2" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_2_3" name="sbd_2_3" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_2_4" name="sbd_2_4" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_2_5" name="sbd_2_5" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_2_6" name="sbd_2_6" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_2_7" name="sbd_2_7" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_2_8" name="sbd_2_8" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
				</tr>
				<tr>
					<th scope="col">예산/결산안</th>
					<td scope="col" style='display:none;'><input type="text" id="sbd_3_0" name="sbd_3_0" /></td>
					<td scope="col"><span id="sbd_3_total"></span><input type="text" id="sbd_3_1" name="sbd_3_1" style="display:none;" value="0"/></td>
					<td scope="col"><input type="text" id="sbd_3_2" name="sbd_3_2" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_3_3" name="sbd_3_3" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_3_4" name="sbd_3_4" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_3_5" name="sbd_3_5" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_3_6" name="sbd_3_6" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_3_7" name="sbd_3_7" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_3_8" name="sbd_3_8" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
				</tr>
				<tr>
					<th scope="col">동의/승인안</th>
					<td scope="col" style='display:none;'><input type="text" id="sbd_4_0" name="sbd_4_0" /></td>
					<td scope="col"><span id="sbd_4_total"></span><input type="text" id="sbd_4_1" name="sbd_4_1" style="display:none;" value="0"/></td>
					<td scope="col"><input type="text" id="sbd_4_2" name="sbd_4_2" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_4_3" name="sbd_4_3" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_4_4" name="sbd_4_4" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_4_5" name="sbd_4_5" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_4_6" name="sbd_4_6" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_4_7" name="sbd_4_7" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_4_8" name="sbd_4_8" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
				</tr>
				<tr>
					<th scope="col">건의/결의안</th>
					<td scope="col" style='display:none;'><input type="text" id="sbd_5_0" name="sbd_5_0" /></td>
					<td scope="col"><span id="sbd_5_total"></span><input type="text" id="sbd_5_1" name="sbd_5_1" style="display:none;" value="0"/></td>
					<td scope="col"><input type="text" id="sbd_5_2" name="sbd_5_2" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_5_3" name="sbd_5_3" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_5_4" name="sbd_5_4" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_5_5" name="sbd_5_5" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_5_6" name="sbd_5_6" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_5_7" name="sbd_5_7" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_5_8" name="sbd_5_8" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
				</tr>
				<tr>
					<th scope="col">의견청취안</th>
					<td scope="col" style='display:none;'><input type="text" id="sbd_6_0" name="sbd_6_0" /></td>
					<td scope="col"><span id="sbd_6_total"></span><input type="text" id="sbd_6_1" name="sbd_6_1" style="display:none;" value="0"/></td>
					<td scope="col"><input type="text" id="sbd_6_2" name="sbd_6_2" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_6_3" name="sbd_6_3" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_6_4" name="sbd_6_4" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_6_5" name="sbd_6_5" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_6_6" name="sbd_6_6" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_6_7" name="sbd_6_7" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_6_8" name="sbd_6_8" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
				</tr>
				<tr>
					<th scope="col">규칙/규정안</th>
					<td scope="col" style='display:none;'><input type="text" id="sbd_7_0" name="sbd_7_0" /></td>
					<td scope="col"><span id="sbd_7_total"></span><input type="text" id="sbd_7_1" name="sbd_7_1" style="display:none;" value="0"/></td>
					<td scope="col"><input type="text" id="sbd_7_2" name="sbd_7_2" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_7_3" name="sbd_7_3" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_7_4" name="sbd_7_4" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_7_5" name="sbd_7_5" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_7_6" name="sbd_7_6" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_7_7" name="sbd_7_7" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_7_8" name="sbd_7_8" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
				</tr>
				<tr>
					<th scope="col">기타</th>
					<td scope="col" style='display:none;'><input type="text" id="sbd_8_0" name="sbd_8_0" /></td>
					<td scope="col"><span id="sbd_8_total"></span><input type="text" id="sbd_8_1" name="sbd_8_1" style="display:none;" value="0"/></td>
					<td scope="col"><input type="text" id="sbd_8_2" name="sbd_8_2" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_8_3" name="sbd_8_3" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_8_4" name="sbd_8_4" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_8_5" name="sbd_8_5" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_8_6" name="sbd_8_6" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_8_7" name="sbd_8_7" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_8_8" name="sbd_8_8" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
				</tr>
				<tr>
					<th scope="col">청원</th>
					<td scope="col" style='display:none;'><input type="text" id="sbd_9_0" name="sbd_9_0" /></td>
					<td scope="col"><span id="sbd_9_total"></span><input type="text" id="sbd_9_1" name="sbd_9_1" style="display:none;" value="0"/></td>
					<td scope="col"><input type="text" id="sbd_9_2" name="sbd_9_2" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_9_3" name="sbd_9_3" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_9_4" name="sbd_9_4" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_9_5" name="sbd_9_5" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_9_6" name="sbd_9_6" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_9_7" name="sbd_9_7" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
					<td scope="col"><input type="text" id="sbd_9_8" name="sbd_9_8" style="width:50px;" onkeydown="checkNumber(event);" onkeyup="changeTotalCount(this);" value="0" maxLength='5'/></td>
				</tr>
			</c:if>	
			<c:if test="${StatsBillList != null }">
				${StatsBillList}
			</c:if>
			</tbody>
		</table>
		
		<h2>회의록통계정보</h2>
		<input type="text" id="MINTS_CO" name="MINTS_CO" type="text" class="input-sm ip" style="width:100px;" value="${StatsMintsCnt }"/>
		
		<p class="tr">
		<button type="button" class="btn btn-primary" onclick="fn_register();">저장</button>
		<button type="button" class="btn btn-default" onclick="fn_cancel();">목록</button>
		</p>
	
	</div><!--//page-wrapper-->
	
</body>
</html>
