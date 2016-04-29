<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
// 초기화 버튼
function setDefault() {
	$("#schKey option:eq(0)").attr("selected", "selected");
	$("#schKw").val("");
	$("#schDt1").val("");
	$("#schDt2").val("");
	$("#schDt3").val("");
	$("#schDt4").val("");
	$("#schOrgCodeStep1 option:eq(0)").attr("selected", "selected");
	$("#schOrgCodeStep2 option:eq(0)").attr("selected", "selected");
	$("#schOrgCodeStep3 option:eq(0)").attr("selected", "selected");
	$("#schDocType option:eq(0)").attr("selected", "selected");
// 	$("#schConversion option:eq(0)").attr("selected", "selected");
	$("#schDuplication option:eq(0)").attr("selected", "selected");
// 	$("#schFile option:eq(0)").attr("selected", "selected");
	$("#schIsView option:eq(0)").attr("selected", "selected");
	$("#schDel option:eq(0)").attr("selected", "selected");
	$("#schRegion option:eq(0)").attr("selected", "selected");
	$("#schSiteId option:eq(0)").attr("selected", "selected");
	$("#schSeedId option:eq(0)").attr("selected", "selected");
	
	$("#schDocType option:eq(0)").attr("selected","selected");
	$("#schLoAsmTyCode option:eq(0)").attr("selected","selected");
	$("#schRegion option:eq(0)").attr("selected","selected");
	$("#schRegion2 option:eq(0)").attr("selected","selected");
	$("#schLoAsmCode option:eq(0)").attr("selected","selected");
	$("#schRks025 option:eq(0)").attr("selected","selected");
	$("#schRasmblyNumpr option:eq(0)").attr("selected","selected");
	
	$("input:radio[name='selCondition']:radio[value='dataType']").prop("checked", true); // 선택하기
	$("#mdmAdm option:eq(0)").attr("selected","selected");
	
	$("input:radio[name='searchDataType']").eq(0).prop("checked", true);
	$('#excelSearchFile').val('');
	
	hideExclude('changeData');
	
}

function getOrgCodeStep2List(schOrgCodeStep2) {
	if( schOrgCodeStep2 == null || schOrgCodeStep2 == "" ) {
		return false;
	}
	
	$('#schOrgCodeStep2 option:eq(0)').attr('selected', 'selected');
	$('#schOrgCodeStep3 option:eq(0)').attr('selected', 'selected');
	$('#mdmAdmOrg option:eq(0)').attr('selected', 'selected');
	$('#showMdmAdmOrg option:eq(0)').attr('selected', 'selected');
	$('#showMdmAdmOrg').hide();
	
	$.ajax({
		url:"/mdm/MdmOrgCodeStep2List.do",
		cache:false,
		type:"post",
		data:{"schOrgCodeStep2":schOrgCodeStep2},
		success: function(data, status) {
			$("#schOrgCodeStep2").html(data);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}

function getOrgCodeStep3List(schOrgCodeStep3) {
	if( schOrgCodeStep3 == null || schOrgCodeStep3 == "" ) {
		return false;
	}
	
	$('#schOrgCodeStep3 option:eq(0)').attr('selected', 'selected');
	$('#showMdmAdmOrg option:eq(0)').attr('selected', 'selected');
	$("#showMdmAdmOrg").hide();

	getOrgSiteList(schOrgCodeStep3, "2Depth");
	
	$.ajax({
		url:"/mdm/MdmOrgCodeStep3List.do",
		cache:false,
		type:"post",
		data:{"schOrgCodeStep3":schOrgCodeStep3},
		success: function(data, status) {
			$("#schOrgCodeStep3").html(data);
			if(schOrgCodeStep3 == '004') { 
				$("#showMdmAdmOrg").show();
			}
			
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}


// SITE LIST 가져오기. 20150408. HOLD
function getOrgSiteList(orgCode, gubun) {
	
	$('#schOrgCodeStep4 option:eq(0)').attr('selected', 'selected');
	
	var org1 = $("#schOrgCodeStep1 option:selected").val();
	var org2 = $("#schOrgCodeStep2 option:selected").val();
	var org3 = orgCode;
	
	if( org1 == null || org1 == "" || org2 == null || org2 == "") {
		return false;
	}

 	if(gubun == "2Depth") {
 		var orgSiteId = '${mdmSearchVO.schOrgCodeStep4}';
 		$.ajax({
 			url:"/mdm/MdmOrgSiteList.do",
 			cache:false,
 			type:"post",
 			data:{"schOrgCodeStep1":org1, "schOrgCodeStep2":org2, "schOrgCodeStep4":orgSiteId},
 			success: function(data, status) {
 				$("#schOrgCodeStep4").html(data);
 			},
 			error: function (data, status, e) {
 				alert(e);
 			}
 		}); 
 	} else if(gubun == "O") {
 		var orgSiteId = '${mdmSearchVO.schOrgCodeStep4}';
 		$.ajax({
 			url:"/mdm/MdmOrgSiteList.do",
 			cache:false,
 			type:"post",
 			data:{"schOrgCodeStep1":org1, "schOrgCodeStep2":org2, "schOrgCodeStep3":org3, "schOrgCodeStep4":orgSiteId},
 			success: function(data, status) {
 				$("#schOrgCodeStep4").html(data);
 			},
 			error: function (data, status, e) {
 				alert(e);
 			}
 		});
 		
 	} else {
 		$.ajax({
 			url:"/mdm/MdmOrgSiteList.do",
 			cache:false,
 			type:"post",
 			data:{"schOrgCodeStep1":org1, "schOrgCodeStep2":org2, "schOrgCodeStep3":org3},
 			success: function(data, status) {
 				$("#schOrgCodeStep4").html(data);
 			},
 			error: function (data, status, e) {
 				alert(e);
 			}
 		});
 	}

	return false;
}



function getSite(schRegion, gubun) {
	if( schRegion == null || schRegion == "" ) {
		return false;
	}
	
	$('#schSiteId option:eq(0)').attr('selected', 'selected');
	$('#schSeedId option:eq(0)').attr('selected', 'selected');
	
	if(gubun == '0') {
		$.ajax({
			url:"/mdm/MdmOutSiteList.do",
			cache:false,
			type:"post",
			data:{"REGION":schRegion},	
			success: function(data, status) {
				$("#schSiteId").html(data);
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
	} else {
		gubun = Number(gubun);
		$.ajax({
			url:"/mdm/MdmOutSiteList.do",
			cache:false,
			type:"post",
			data:{"REGION":schRegion, "SITEID":gubun},	
			success: function(data, status) {
				$("#schSiteId").html(data);
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
	}
	return false;
}

function getSeed(schSiteId, gubun) {
	var schRegion = $("#schRegion2 option:selected").val();
	if( schRegion == null || schRegion == "" || schSiteId == null || schSiteId == "" ) {
		return false;
	}
	
	if(gubun == '0') {
		$.ajax({
			url:"/mdm/MdmOutSeedList.do",
			cache:false,
			type:"post",
			data:{"REGION":schRegion, "SITEID":schSiteId},
			success: function(data, status) {
				$("#schSeedId").html(data);
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
	} else {
		$.ajax({
			url:"/mdm/MdmOutSeedList.do",
			cache:false,
			type:"post",
			data:{"REGION":schRegion, "SITEID":schSiteId, "SEEDID":gubun},
			success: function(data, status) {
				$("#schSeedId").html(data);
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
	} 
	return false;
}

//사용안함
function getSchDt(schDt) {
	if( schDt == null || schDt == "" ) {
		return false;
	}
	$.ajax({
		url:"/mdm/MdmGetDate.do",
		cache:false,
		type:"post",
		data:{"schDt":schDt},
		success: function(data, status) {
			msg = JSON.parse(data);
			$("#schDt1").val(msg.schDt1);
			$("#schDt2").val(msg.schDt2);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}

//조회
function frmSubmit(searchGubun) {
		
	if($("#schKw").val() == "" && $("#schDt1").val() == "" && $("#schDt2").val() == "" && $("#schDt3").val() == "" && $("#schDt4").val() == "") {
		alert("검색기간 또는 검색어를 입력해 주세요.");
		return false;
	}
	
	if($('#mdmAdm').val() == 'AsmblyAsemby' && $("#schKw").val() == "" && $("#schDt1").val() == "" && $("#schDt2").val() == ""){
		alert("검색기간 또는 검색어를 입력해 주세요.");
		return false;
	}
	
	//검색 버튼 눌러서 검색할 경우 정렬값 초기화
	if(searchGubun == "btn"){
		$('#sort').val('');

		//페이징 초기화 처리
		$('#sFrmPageNum').val('1');
	}
	
	//검색타입 확인 및 엑셀 검색
	var searchType = $(':radio[name="searchType"]:checked').val();
	if(searchType == 'excel'){
		
		if($('#excelSearchCollection').val() != $(':radio[name="searchDataType"]:checked').val()){
			$('#excelSearchCnList').val('');
		}

		var isExcelFile = $('#excelSearchFile').val() != "" ? true : false;
		
		if(isExcelFile || $('#excelSearchCnList').val() != ""){
			lodding();
			document.sfrm.action = "/mdm/ExcelMdm"+$(':radio[name="searchDataType"]:checked').val()+"List.do";
			document.sfrm.submit();
			return true;
		}else{
			alert('excel 파일이 존재하지 않습니다.');
			$('#excelSearchFile').focus();
			return false;
		}
	}else{
		//검색유형
		var radioVal = $(':radio[name="selCondition"]:checked').val();
		
		var dataType = $("#mdmAdm").val();

		if(radioVal == 'dataType') {
			document.sfrm.action = "/mdm/Mdm" + dataType + "List.do";
		} else if(radioVal == 'orgType') {
			var dataTypeOrg = $("#mdmAdmOrg").val();
			document.sfrm.action = "/mdm/Mdm" + dataTypeOrg + "List.do";
		} else {
			document.sfrm.action = "/mdm/MdmPolicyInfoList.do";
		}
		
		lodding();
		document.sfrm.submit();
		return true;
	}
}

function paging(pageNum) {
	$(".spinner").show();
	$('#pageNum').val(pageNum);
	
	//검색타입 확인 및 엑셀 검색
	var searchType = $(':radio[name="searchType"]:checked').val();
	if(searchType == 'excel'){
		var isExcelFile = $('#excelSearchFile').val() != "" ? true : false;
		if(isExcelFile || $('#excelSearchCnList').val() != ""){
			lodding();
			document.pgfrm.action = "/mdm/ExcelMdm"+$(':radio[name="searchDataType"]:checked').val()+"List.do";
			document.pgfrm.submit();
		}else{
			alert('excel 파일이 존재하지 않습니다.');
			$('#excelSearchFile').focus();
		}
	}else{
		//검색유형
		var radioVal = $(':radio[name="selCondition"]:checked').val();
		
		var dataType = $("#mdmAdm").val();

		if(radioVal == 'dataType') {
			document.pgfrm.action = "/mdm/Mdm" + dataType + "List.do";
		} else if(radioVal == 'orgType') {
			var dataTypeOrg = $("#mdmAdmOrg").val();
			document.pgfrm.action = "/mdm/Mdm" + dataTypeOrg + "List.do";
		} else {
			document.pgfrm.action = "/mdm/MdmPolicyInfoList.do";
		}
		
		document.pgfrm.submit();
	}
	
	return true;
}

function getLoAsmTyCode() {
	$("#schRegion option:eq(0)").attr("selected","selected");
	$("#schLoAsmCode option:eq(0)").attr("selected","selected");
	$("#schRasmblyNumpr option:eq(0)").attr("selected","selected");
}

function getRegion(schRegion, gubun) {
	
	$("#schLoAsmCode option:eq(0)").attr("selected","selected");
	
	if( schRegion == null || schRegion == "" ) {
		return false;
	}
	
	if(gubun == '0') {
		var schLoAsmTyCode = $("#schLoAsmTyCode").val();
		$.ajax({
			url:"/mdm/MdmEstbsList.do",
			cache:false,
			type:"post",
			data:{"schLoAsmTyCode":schLoAsmTyCode,"schRegion":schRegion},
			success: function(data, status) {
				$("#schLoAsmCode").html(data);
				$("#schRasmblyNumpr option:eq(0)").attr("selected","selected");
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
	} else {
		var schLoAsmTyCode = $("#schLoAsmTyCode").val();
		$.ajax({
			url:"/mdm/MdmEstbsList.do",
			cache:false,
			type:"post",
			data:{"schLoAsmTyCode":schLoAsmTyCode,"schRegion":schRegion,"schLoAsmCode":gubun},
			success: function(data, status) {
				$("#schLoAsmCode").html(data);
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
	}
	

	return false;
}

function getLoAsmCode(code) {
	$("#schRasmblyNumpr option:eq(0)").attr("selected","selected");
}

function getMdmAdm(mdmAdm, gubun) {
	/**
	*	20150401
	*	ajax로 다중셀렉트 방식 구현
	*/
	if(gubun == 'i') {
		$("#schDocType option:eq(0)").attr("selected","selected");
		$("#schLoAsmTyCode option:eq(0)").attr("selected","selected");
		$("#schRegion option:eq(0)").attr("selected","selected");
		$("#schRegion2 option:eq(0)").attr("selected","selected");
		$("#schLoAsmCode option:eq(0)").attr("selected","selected");
		$("#schRks025 option:eq(0)").attr("selected","selected");
		$("#schRasmblyNumpr option:eq(0)").attr("selected","selected");	
	}
	
	if(mdmAdm == 'PolicyInfo') {
		$("#show1").show();
		$("#showPolicy").show();
		$("#showMinutes").hide();
		$("#showRegion").hide();
		$("#showFlag").hide();
		$("#showAssem").hide();
		$("#showOrg").hide();
// 		if(Number(${fn:replace(policyInfoListTotCnt,',','')}) < 50000
// 				&& Number(${fn:replace(policyInfoListTotCnt,',','')}) > 0){
			$('#btnExcelDownload').show();
// 		}else{
// 			$('#btnExcelDownload').hide();
// 		}
// 		if(Number(${fn:replace(policyInfoListTotCnt,',','')}) < 50000
// 				&& Number(${fn:replace(policyInfoListTotCnt,',','')}) > 0){
			$('#btnTextDownload').show();
// 		}else{
// 			$('#btnTextDownload').hide();
// 		}
// 		$('#schConversion').prev().show();
// 		$('#schConversion').show();
// 		$('#schFile').prev().show();
// 		$('#schFile').show();
		$('#schDuplication').prev().show();
		$('#schDuplication').show();
		$('#reg_date_row').show();
	} else if (mdmAdm == 'Minutes') {
		$("#show1").show();
		$("#showPolicy").hide();
		$("#showOrg").show();
		$("#showAssem").show();
		$("#showMinutes").show();
		$("#showRegion").hide();
		$("#showFlag").hide();
// 		if(Number(${fn:replace(minutesListTotCnt,',','')}) < 50000
// 				&& Number(${fn:replace(minutesListTotCnt,',','')}) > 0){
			$('#btnExcelDownload').show();
// 		}else{
// 			$('#btnExcelDownload').hide();
// 		}
// 		if(Number(${fn:replace(minutesListTotCnt,',','')}) < 50000
// 				&& Number(${fn:replace(minutesListTotCnt,',','')}) > 0){
			$('#btnTextDownload').show();
// 		}else{
// 			$('#btnTextDownload').hide();
// 		}
// 		$('#schConversion').prev().show();
// 		$('#schConversion').show();
// 		$('#schFile').prev().show();
// 		$('#schFile').show();
		$('#schDuplication').prev().hide();
		$('#schDuplication').hide();
		$('#reg_date_row').show();
	} else if (mdmAdm == 'Bill') {
		$("#show1").show();
		$("#showPolicy").hide();
		$("#showOrg").show();
		$("#showAssem").show();
		$("#showMinutes").show();
		$("#showRegion").hide();
		$("#showFlag").hide();
// 		if(Number(${fn:replace(billListTotCnt,',','')}) < 50000
// 				&& Number(${fn:replace(billListTotCnt,',','')}) > 0){
			$('#btnExcelDownload').show();
// 		}else{
// 			$('#btnExcelDownload').hide();
// 		}
// 		if(Number(${fn:replace(billListTotCnt,',','')}) < 50000
// 				&& Number(${fn:replace(billListTotCnt,',','')}) > 0){
			$('#btnTextDownload').show();
// 		}else{
// 			$('#btnTextDownload').hide();
// 		}
// 		$('#schConversion').prev().hide();
// 		$('#schConversion').hide();
// 		$('#schFile').prev().show();
// 		$('#schFile').show();
		$('#schDuplication').prev().hide();
		$('#schDuplication').hide();
		$('#reg_date_row').show();
	} else if (mdmAdm == 'AsmblyAsemby') {
		$("#show1").show();
		$("#showPolicy").hide();
		$("#showOrg").show();
		$("#showAssem").show();
		$("#showMinutes").show();
		$("#showRegion").hide();
		$("#showFlag").show();
// 		if(Number(${fn:replace(asmblyAsembyActListTotCnt,',','')}) < 50000
// 				&& Number(${fn:replace(asmblyAsembyActListTotCnt,',','')}) > 0){
			$('#btnExcelDownload').show();
// 		}else{
// 			$('#btnExcelDownload').hide();
// 		}
// 		if(Number(${fn:replace(asmblyAsembyActListTotCnt,',','')}) < 50000
// 				&& Number(${fn:replace(asmblyAsembyActListTotCnt,',','')}) > 0){
			$('#btnTextDownload').show();
// 		}else{
// 			$('#btnTextDownload').hide();
// 		}
// 		$('#schConversion').prev().show();
// 		$('#schConversion').show();
// 		$('#schFile').prev().hide();
// 		$('#schFile').hide();
		$('#schDuplication').prev().hide();
		$('#schDuplication').hide();
		$('#reg_date_row').hide();
	} else if (mdmAdm == 'RegionNews') {
		$("#show1").show();
		$("#showPolicy").hide();
		$("#showOrg").hide();
		$("#showAssem").hide();
		$("#showMinutes").hide();
		$("#showRegion").show();
		$("#showFlag").hide();
// 		if(Number(${fn:replace(regionNewsListTotCnt,',','')}) < 50000
// 				&& Number(${fn:replace(regionNewsListTotCnt,',','')}) > 0){
			$('#btnExcelDownload').show();
// 		}else{
// 			$('#btnExcelDownload').hide();
// 		}
// 		if(Number(${fn:replace(regionNewsListTotCnt,',','')}) < 50000
// 				&& Number(${fn:replace(regionNewsListTotCnt,',','')}) > 0){
			$('#btnTextDownload').show();
// 		}else{
// 			$('#btnTextDownload').hide();
// 		}
// 		$('#schConversion').prev().show();
// 		$('#schConversion').show();
// 		$('#schFile').prev().hide();
// 		$('#schFile').hide();
		$('#schDuplication').prev().show();
		$('#schDuplication').show();
		$('#reg_date_row').show();
	} else if (mdmAdm == 'EduManual') {
		$("#show1").show();
		$("#showPolicy").hide();
		$("#showOrg").hide();
		$("#showAssem").hide();
		$("#showMinutes").hide();
		$("#showRegion").hide();
		$("#showFlag").hide();
// 		if(Number(${fn:replace(eduManualListTotCnt,',','')}) < 50000
// 				&& Number(${fn:replace(eduManualListTotCnt,',','')}) > 0){
			$('#btnExcelDownload').show();
// 		}else{
// 			$('#btnExcelDownload').hide();
// 		}
// 		if(Number(${fn:replace(eduManualListTotCnt,',','')}) < 50000
// 				&& Number(${fn:replace(eduManualListTotCnt,',','')}) > 0){
			$('#btnTextDownload').show();
// 		}else{
// 			$('#btnTextDownload').hide();
// 		}
// 		$('#schConversion').prev().show();
// 		$('#schConversion').show();
// 		$('#schFile').prev().show();
// 		$('#schFile').show();
		$('#schDuplication').prev().show();
		$('#schDuplication').show();
		$('#reg_date_row').show();
	} else if (mdmAdm == 'default') {
		$("#changeData").show();
		$("#changeOrg").hide();
		$("#changeSite").hide();
		$('#btnExcelDownload').hide();
		$('#btnTextDownload').hide();
		$('#reg_date_row').show();
	}
}


$(document).ready(function() {

	// DOM 생성 완료 시 화면 숨김 (파라미터로 전달되는 id는 제외)
	hideExclude("changeData");

	// radio change 이벤트
	$("input[name=selCondition]").change(function() {
		$('#reg_date_row').show();
		$('#btnExcelDownload').hide();
		
		// 라이오 버튼 변경시마다 셀렉박스 초기화
		$("#mdmAdm option:eq(0)").attr("selected","selected");
		
		$("#schDocType option:eq(0)").attr("selected","selected");
		$("#schLoAsmTyCode option:eq(0)").attr("selected","selected");
		$("#schRegion option:eq(0)").attr("selected","selected");
		$("#schRegion2 option:eq(0)").attr("selected","selected");
		$("#schLoAsmCode option:eq(0)").attr("selected","selected");
		$("#schRks025 option:eq(0)").attr("selected","selected");
		$("#schRasmblyNumpr option:eq(0)").attr("selected","selected");
		
		$("#schOrgCodeStep1 option:eq(0)").attr("selected", "selected");
		$("#schOrgCodeStep2 option:eq(0)").attr("selected", "selected");
		$("#schOrgCodeStep3 option:eq(0)").attr("selected", "selected");
		$("#schOrgCodeStep4 option:eq(0)").attr("selected", "selected");
		
		$("#schRegion option:eq(0)").attr("selected", "selected");
		$("#schSiteId option:eq(0)").attr("selected", "selected");
		$("#schSeedId option:eq(0)").attr("selected", "selected");
		
		var radioValue = $(this).val();

		if (radioValue == "dataType") {
			hideExclude("changeData");
			$('#btnExcelDownload').show();
			$('#addBtn').css('display', 'none');
			getMdmAdm($('#mdmAdm').val(), 'i');
		} else if (radioValue == "orgType") {
			hideExclude("changeOrg");
			$('#addBtn').css('display', 'none');
// 			$('#schConversion').prev().show();
// 			$('#schConversion').show();
// 			$('#schFile').prev().show();
// 			$('#schFile').show();
			$('#schDuplication').prev().show();
			$('#schDuplication').show();
			$('#chargerYn').prop('checked',false);
		} else if (radioValue == "siteType") {
			hideExclude("changeSite");
			$('#addBtn').css('display', '');
// 			$('#schConversion').prev().show();
// 			$('#schConversion').show();
// 			$('#schFile').prev().show();
// 			$('#schFile').show();
			$('#schDuplication').prev().show();
			$('#schDuplication').show();
			$('#chargerYn').prop('checked',false);
		};
	});
	
	// 검색 시 자료유형 선택 
	var statsValue = '';
	<c:if test="${isExcelSearch == null}">
	statsValue = '${mdmSearchVO.mdmAdm}'; 
	</c:if>
	
	<c:if test="${isExcelSearch != null}">
	statsValue = '${excelSearchCollection}'; 
	</c:if>
	
	getMdmAdm(statsValue, 'o');
	
	// 라디오 버튼 선택 시 셀렉트 박스 설정
	if('${mdmSearchVO.selCondition}' == 'orgType') {
		hideExclude("changeOrg");
		
		var org1 	= '${mdmSearchVO.schOrgCodeStep1}';
		var org2 	= '${mdmSearchVO.schOrgCodeStep2}';
		var org3 	= '${mdmSearchVO.schOrgCodeStep3}';
		var orgSite = '${mdmSearchVO.schSiteId}';
		
		if(org2 == '004') {
			$('#showMdmAdmOrg').show();
		}
		
		if(org3 == null || org3 == '') {
			getOrgSiteList(org2, "2Depth");	
		} else {
			getOrgSiteList(org3, "O");
		}
		
	} else if('${mdmSearchVO.selCondition}' == 'siteType') {
		hideExclude("changeSite");
		
		var regionVal = '${mdmSearchVO.schRegion2}';
		var siteVal = '${mdmSearchVO.schSiteId}';
		var seedVal = '${mdmSearchVO.schSeedId}';
		
		getSite(regionVal, siteVal);
		getSeed(siteVal, seedVal);
		
		
	} else {
		var mdmAdm = '${mdmSearchVO.mdmAdm}';
		if(mdmAdm == 'Minutes') {
			$("#show1").show();
			$("#showPolicy").hide();
			$("#showOrg").show();
			$("#showAssem").show();
			$("#showMinutes").show();
			$("#showRegion").hide();
			$("#showFlag").hide();
			
			var schRegion 		= '${mdmSearchVO.schRegion}';
			var schLoAsmCode 	= '${mdmSearchVO.schLoAsmCode}';
			getRegion(schRegion, schLoAsmCode);
		}
	}
	
	//검색 유형 변경
	$("input[name=searchType]").change(function() {
		if(this.value == 'default'){
			$('#defaultSearch').attr('style','');
			$('#excelSearch').attr('style','display:none');
		}else if(this.value == 'excel'){
			$('#defaultSearch').attr('style','display:none');
			$('#excelSearch').attr('style','');
		}
	});
	
	//검색유형(자료유형)일 경우 자료유형에 따른 검색 조건 표시
	<c:if test="${mdmSearchVO.selCondition == 'dataType' || mdmSearchVO.selCondition == ''}">
		<c:if test="${mdmSearchVO.mdmAdm == 'PolicyInfo' || mdmSearchVO.mdmAdm == 'EduManual'}">
		//정책정보, 교육메뉴얼
// 		$('#schConversion').prev().show();
// 		$('#schConversion').show();
// 		$('#schFile').prev().show();
// 		$('#schFile').show();
		$('#schDuplication').prev().show();
		$('#schDuplication').show();
		$('#reg_date_row').show();
		</c:if>
		<c:if test="${mdmSearchVO.mdmAdm == 'Minutes'}">
		//회의록
// 		$('#schConversion').prev().show();
// 		$('#schConversion').show();
// 		$('#schFile').prev().show();
// 		$('#schFile').show();
		$('#schDuplication').prev().hide();
		$('#schDuplication').hide();
		$('#reg_date_row').show();
		</c:if>
		<c:if test="${mdmSearchVO.mdmAdm == 'Bill'}">
		//의안
// 		$('#schConversion').prev().hide();
// 		$('#schConversion').hide();
// 		$('#schFile').prev().show();
// 		$('#schFile').show();
		$('#schDuplication').prev().hide();
		$('#schDuplication').hide();
		$('#reg_date_row').show();
		</c:if>
		<c:if test="${mdmSearchVO.mdmAdm == 'AsmblyAsemby'}">
		//의원
// 		$('#schConversion').prev().show();
// 		$('#schConversion').show();
// 		$('#schFile').prev().hide();
// 		$('#schFile').hide();
		$('#schDuplication').prev().hide();
		$('#schDuplication').hide();
		$('#reg_date_row').hide();
		var schRegion 		= '${mdmSearchVO.schRegion}';
		var schLoAsmCode 	= '${mdmSearchVO.schLoAsmCode}';
		getRegion(schRegion, schLoAsmCode);
		</c:if>
		<c:if test="${mdmSearchVO.mdmAdm == 'RegionNews'}">
		//지역현안소식
// 		$('#schConversion').prev().hide();
// 		$('#schConversion').hide();
// 		$('#schFile').prev().hide();
// 		$('#schFile').hide();
		$('#schDuplication').prev().show();
		$('#schDuplication').show();
		$('#reg_date_row').show();
		</c:if>
	</c:if>
	
});//end document.ready

// text area 숨김
function hideExclude(excludeId) {
	$("#radioSection").children().each(function() {
		$(this).hide();
	});

	// 파라미터로 넘겨 받은 id 요소는 show
	$("#" + excludeId).show();
	
	if(excludeId == 'changeData'){
		$("#show1").show();
		$("#showPolicy").show();
		$("#showMinutes").hide();
		$("#showRegion").hide();
		$("#showFlag").hide();
		$("#showAssem").hide();
		$("#showOrg").hide();
	} else if (excludeId == 'changeOrg') {
		$("#showMdmAdmOrg").hide();	
	}
}

/* ********************************************************
* 엑셀 다운로드
******************************************************** */
function fnExcel() {
	if( !confirm("엑셀 다운로드 하시겠습니까?") ) {
		return false;
	}
	var varForm = document.sfrm;
	
	if($("input[name=searchType]:checked").val() == 'default')
		varForm.action = "<c:url value='/mdm/selectMdm"+ $("#mdmAdm").val() +"Excel.do'/>";
	else
		varForm.action = "<c:url value='/mdm/selectMdm"+ $("input[name=searchDataType]:checked").val() +"Excel.do'/>";
		
	varForm.submit();
}

/* ********************************************************
* TEXT 다운로드
******************************************************** */
function fnText() {
	if( !confirm("TEXT를 다운로드 하시겠습니까?") ) {
		return false;
	}
	var varForm = document.sfrm;

	if($("input[name=searchType]:checked").val() == 'default')
		varForm.action = "<c:url value='/mdm/selectMdm"+ $("#mdmAdm").val() +"Text.do'/>";
	else
		varForm.action = "<c:url value='/mdm/selectMdm"+ $("input[name=searchDataType]:checked").val() +"Text.do'/>";
		
	varForm.submit();
}

/* ********************************************************
* 엑셀 조회용 파일 다운로드
******************************************************** */
function SearchExcelFileDownload() {
	document.location.href="/mdm/MdmSearchExcelDownLoad.do";
}

/* ********************************************************
* 상세보기
******************************************************** */
function fn_detailView(url,title,option){
	var frm = document.sfrm;
	frm.action = url;
	var ret_open = window.open('about:blank','VIEWER_POP',option);
	
	if(ret_open == null) {
		alert("팝업차단을 해제하세요!");
		return false;
	}
	
	frm.pageNum.value = "${mdmSearchVO.pageNum}"; 
	frm.title.value = title;
	frm.target='VIEWER_POP';
	frm.submit();
	frm.target = "_self";
	
	//IE11 관리자권한으로 실행 시 팝업이 2개 뜨는 현상으로 수정
	//조회 목록이 90개가 넘을 경우 IE에서 parameter 문자열이 잘리는 현상으로 주석처리
// 	var formObjects = $('form[name="sfrm"]').serializeArray();
// 	var params = "";
// 	$(formObjects).each(function(index,obj){
// 		//params += "&" + obj.name + "=" + encodeURIComponent(htmlEntityEnc(obj.value)).replace(/[!'()*]/g, escape)
// 		if(obj.value.indexOf(" ") > -1)
// 			params += "&" + obj.name + "=" + encodeURIComponent(obj.value);
// 		else
// 			params += "&" + obj.name + "=" + obj.value;
// 	});
	
// 	window.open(url + params,'VIEWER_POP',option);
}

/* ********************************************************
* 회의록 / 의안 / 의원 재수집 요청 등록
******************************************************** */
function insertStdCntcApiColct(){
	if(confirm("선택한 항목을 재수집 요청하시겠습니까?")){
		var DTA_SE_CODE = '';
		var frm = document.sfrm;
		frm.action = "/rlm/StdCntcApiColctProc.do";
		frm.return_url.value = location.href.replace("http://"+location.host,""); 
		
		var pkList = "";
		var setObj = new Array();
		
		if("Minutes" == "${mdmSearchVO.mdmAdm }"){ 
			frm.DTA_SE_CODE.value = "TAR101";
			DTA_SE_CODE = "TAR101";
			
			$('table input[name="isview"]:checked').each(function(index,target){
				setObj[setObj.length] = $(target).next().val()+"#"+$(target).val() + "@" + $(target).next().next().val();
			});
			
		}else if("Bill" == "${mdmSearchVO.mdmAdm }"){ 
			frm.DTA_SE_CODE.value = "TAR102";
			DTA_SE_CODE = "TAR102";
			
			$('table input[name="isview"]:checked').each(function(index,target){
				setObj[setObj.length] = $(target).next().val()+"#"+$(target).val() + "@" + $(target).next().next().val();
			});
			
		}else if("AsmblyAsemby" == "${mdmSearchVO.mdmAdm }"){ 
			frm.DTA_SE_CODE.value = "TAR103";
			DTA_SE_CODE = "TAR103";
			
			$('table input[name="isview"]:checked').each(function(index,target){
				setObj[setObj.length] = $(target).next().val()+"#"+$(target).next().next().next().val() + "@" + $(target).next().next().val();
			});
		}
		
		var a = {};
	    for(var i=0; i <setObj.length; i++){
	        if(typeof a[setObj[i]] == "undefined")
	            a[setObj[i]] = 1;
	    }
	    setObj.length = 0;
	    for(var i in a)
	    	setObj[setObj.length] = i;
		
	    for(var j=0; j<setObj.length; j++){
	    	pkList += "," + setObj[j];
	    }
		
		pkList = pkList.substr(1);
		
		frm.CN.value = pkList;
		//frm.submit();
		
		
		$.ajax({
			url:"/rlm/StdCntcApiColctProc.do",
			cache:false,
			type:"post",
			data:{
				"LOASM_CODE" : ''
				,"DTA_SE_CODE" : DTA_SE_CODE
				,"COLCT_SE_CODE" : 'CLT103'
				,"PK" : pkList
				,"isMeta" : 'Y'
			},
			success: function(data, status) {
				$('table input[name="isview"]:checked').prop('checked',false);
				alert(data);
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
		
		
		
	};
}

</script>
<!-- 표준연게 API 재수집 관련 정보 S -->
<input type="hidden" name="return_url" id="return_url" value="" >
<input type="hidden" name="DTA_SE_CODE" id="DTA_SE_CODE" value="" >
<input type="hidden" name="CN" id="CN" value="" >
<input type="hidden" name="COLCT_SE_CODE" id="COLCT_SE_CODE" value="CLT103" >

<!-- 표준연게 API 재수집 관련 정보 E -->

<input type="hidden" name="EndPage" id="EndPage" value="${EndPage }" >
<input type="hidden" name="sort" id="sort" value="${mdmSearchVO.sort }" >
<input type="hidden" name="excelSearchCollection" id="excelSearchCollection" value="${excelSearchCollection}" >
<input type="hidden" name="isExcelSearch" id="isExcelSearch" value="${isExcelSearch}" >
<input type="hidden" name="cnList" id="cnList" value="${cnList }" >
<input type="hidden" name="excelSearchCnList" id="excelSearchCnList" value="${excelSearchCnList}" >

<div class="tl mb20">
	<input type="radio" name="searchType" value="default" <c:if test="${isExcelSearch == null}">checked="checked"</c:if>/> 일반검색
	<input type="radio" name="searchType" value="excel" <c:if test="${isExcelSearch != null}">checked="checked"</c:if>/> 엑셀검색
</div><!--추가-->
			
<div id="defaultSearch" <c:if test="${isExcelSearch != null}">style="display:none;"</c:if>>
	<div class="" style="text-align:left;">
		<table class="table table-bordered table-hover" >
			<colgroup>
				<col width="20%" />
				<col width="" />
			</colgroup>
			<tr>
				<th>검색어</th>
				<td>
					<select name="schKey" id="schKey" class=" input-sm" style="width:150px;">
						<option value="schTitle" <c:if test="${mdmSearchVO.schKey == 'schTitle'}"> selected="selected"</c:if>>제목</option>
						<option value="cnId" <c:if test="${mdmSearchVO.schKey == 'cnId'}"> selected="selected"</c:if>>문서번호</option>
						<option value="schContent" <c:if test="${mdmSearchVO.schKey == 'schContent'}"> selected="selected"</c:if>>내용</option>
					</select>
					<input type="text" name="schKw" id="schKw" value="${mdmSearchVO.schKw}" class="ip input-sm" style="width:427px;" onkeydown="javascript:if(event.keyCode==13){frmSubmit('btn');}" />
				</td>
			</tr>
			<tr>
				<th>수집일자</th>
				<td>
					<input type="text" name="schDt1" id="schDt1" value="<c:out value="${mdmSearchVO.schDt1}" />" class="input-sm ip" style="width:150px;" />
					&nbsp;~&nbsp;
					<input type="text" name="schDt2" id="schDt2" value="<c:out value="${mdmSearchVO.schDt2}" />" class="input-sm ip" style="width:150px;" />
					<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-success">오늘</button></a>
					<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-danger">일주일</button></a>
					<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-primary">한달</button></a>
					<script>
					$('#schDt1').datepicker({
						dateFormat: 'yy-mm-dd',
						changeYear: true,
						changeMonth: true,
						showMonthAfterYear: true,
						showButtonPanel: true,
						showOn: 'button',
						buttonText: '<img src="/images/clikmng/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
						yearRange: '-100:+1',
						dayNames: ['일','월','화','수','목','금','토'],
						dayNamesMin: ['일','월','화','수','목','금','토'],
						monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
						monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
					});
					$('#schDt2').datepicker({
						dateFormat: 'yy-mm-dd',
						changeYear: true,
						changeMonth: true,
						showMonthAfterYear: true,
						showButtonPanel: true,
						showOn: 'button',
						buttonText: '<img src="/images/clikmng/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
						yearRange: '-100:+1',
						dayNames: ['일','월','화','수','목','금','토'],
						dayNamesMin: ['일','월','화','수','목','금','토'],
						monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
						monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
					});
					</script>
				</td>
			</tr>
			<tr id="reg_date_row">
				<th>등록일자</th>
				<td>
					<input type="text" name="schDt3" id="schDt3" value="<c:out value="${mdmSearchVO.schDt3}" />" class="input-sm ip" style="width:150px;" />
					&nbsp;~&nbsp;
					<input type="text" name="schDt4" id="schDt4" value="<c:out value="${mdmSearchVO.schDt4}" />" class="input-sm ip" style="width:150px;" />
					<a href="#" onclick="getDate('T','schDt3','schDt4')"><button type="button" class="btn btn-success">오늘</button></a>
					<a href="#" onclick="getDate('W','schDt3','schDt4')"><button type="button" class="btn btn-danger">일주일</button></a>
					<a href="#" onclick="getDate('M','schDt3','schDt4')"><button type="button" class="btn btn-primary">한달</button></a>
					<script>
					$('#schDt3').datepicker({
						dateFormat: 'yy-mm-dd',
						changeYear: true,
						changeMonth: true,
						showMonthAfterYear: true,
						showButtonPanel: true,
						showOn: 'button',
						buttonText: '<img src="/images/clikmng/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
						yearRange: '-100:+1',
						dayNames: ['일','월','화','수','목','금','토'],
						dayNamesMin: ['일','월','화','수','목','금','토'],
						monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
						monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
					});
					$('#schDt4').datepicker({
						dateFormat: 'yy-mm-dd',
						changeYear: true,
						changeMonth: true,
						showMonthAfterYear: true,
						showButtonPanel: true,
						showOn: 'button',
						buttonText: '<img src="/images/clikmng/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
						yearRange: '-100:+1',
						dayNames: ['일','월','화','수','목','금','토'],
						dayNamesMin: ['일','월','화','수','목','금','토'],
						monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
						monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
					});
					</script>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				
					<label style="width:70px;text-align:left;">게시 :</label> 
					<select name="schIsView" id="schIsView" class=" input-sm" style="width:100px;">
						<option value="">전체</option>
						<option value="Y" <c:if test="${mdmSearchVO.schIsView == 'Y'}"> selected="selected"</c:if>>게시</option>
						<option value="N" <c:if test="${mdmSearchVO.schIsView == 'N'}"> selected="selected"</c:if>>미게시</option>
					</select>&nbsp;&nbsp;
					
					<label style="width:70px;text-align:left;">삭제 :</label>
					<select name="schDel" id="schDel" class=" input-sm" style="width:100px;">
						<option value="">전체</option>
						<option value="D" <c:if test="${mdmSearchVO.schDel == 'D'}"> selected="selected"</c:if>>삭제</option>
						<option value="Y" <c:if test="${mdmSearchVO.schDel == 'Y'}"> selected="selected"</c:if>>등록</option>
					</select>&nbsp;&nbsp;
				
<!-- 					<label style="width:70px;text-align:left;">변환 :</label> -->
<!-- 					<select name="schConversion" id="schConversion" class=" input-sm" style="width:100px;"> -->
<!-- 						<option value="">전체</option> -->
<%-- 						<option value="1" <c:if test="${mdmSearchVO.schConversion == '1'}"> selected="selected"</c:if>>성공</option> --%>
<%-- 						<option value="2" <c:if test="${mdmSearchVO.schConversion == '2'}"> selected="selected"</c:if>>실패</option> --%>
<!-- 					</select>&nbsp;&nbsp; -->
					
<!-- 					<label style="width:70px;text-align:left;">첨부파일 :</label> -->
<!-- 					<select name="schFile" id="schFile" class=" input-sm" style="width:100px;"> -->
<!-- 						<option value="">전체</option> -->
<%-- 						<option value="Y" <c:if test="${mdmSearchVO.schFile == 'Y'}"> selected="selected"</c:if>>있음</option> --%>
<%-- 						<option value="N" <c:if test="${mdmSearchVO.schFile == 'N'}"> selected="selected"</c:if>>없음</option> --%>
<!-- 					</select>&nbsp;&nbsp; -->
					
					<label style="width:70px;text-align:left;">중복 :</label> 
					<select name="schDuplication" id="schDuplication" class=" input-sm" style="width:100px;">
						<option value="">전체</option>
						<option value="Y" <c:if test="${mdmSearchVO.schDuplication == 'Y'}"> selected="selected"</c:if>>Y</option>
						<option value="N" <c:if test="${mdmSearchVO.schDuplication == 'N'}"> selected="selected"</c:if>>N</option>
					</select>&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<th>검색유형 선택</th>
				<td>
					<input type="radio" name="selCondition" value="dataType" checked="checked" <c:if test="${mdmSearchVO.selCondition == 'dataType' || mdmSearchVO.selCondition == ''}">checked="checked"</c:if> style="vertical-align:middle;" /> 자료유형     
					<input type="radio" name="selCondition" value="orgType" <c:if test="${mdmSearchVO.selCondition == 'orgType'}">checked="checked"</c:if> style="vertical-align:middle;" /> 기관유형     
					<input type="radio" name="selCondition" value="siteType" <c:if test="${mdmSearchVO.selCondition == 'siteType'}">checked="checked"</c:if> style="vertical-align:middle;" /> 수집사이트
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="radioSection" class="search" style="float: left;">
						<!-- 자료유형 조회 S-->
						<div id="changeData" style="display: block;">
								<div id="show1" style="text-align: left;">
								<label style="width:80px;text-align:left;">자료유형 1 :</label> 
								<select name="mdmAdm" id="mdmAdm" onchange="return getMdmAdm(this.value, 'i')" class=" input-sm" style="min-width:150px;">
									<option value="PolicyInfo" <c:if test="${mdmSearchVO.mdmAdm == 'PolicyInfo'}"> selected="selected"</c:if>>지방정책정보</option>
									<option value="EduManual" <c:if test="${mdmSearchVO.mdmAdm == 'EduManual'}"> selected="selected"</c:if>>교육&amp;매뉴얼</option>
									<option value="Minutes" <c:if test="${mdmSearchVO.mdmAdm == 'Minutes'}"> selected="selected"</c:if>>지방의회 회의록</option>
									<option value="Bill" <c:if test="${mdmSearchVO.mdmAdm == 'Bill'}"> selected="selected"</c:if>>지방의회 의안</option>
									<option value="AsmblyAsemby" <c:if test="${mdmSearchVO.mdmAdm == 'AsmblyAsemby'}"> selected="selected"</c:if>>지방의회 의원</option>
									<option value="RegionNews" <c:if test="${mdmSearchVO.mdmAdm == 'RegionNews'}"> selected="selected"</c:if>>지역현안소식</option>
								</select>&nbsp;&nbsp;
								</div>
								
								<!-- 지방정책정보 일 경우-->
								<div id="showPolicy" style="float: left;">
									<label style="width:80px;text-align:left;">자료유형 2 :</label> 
									<select name="schDocType" id="schDocType" class=" input-sm" style="min-width:150px;">
										<option value="">전체</option>
										<option value="100" <c:if test="${mdmSearchVO.schDocType == '100'}"> selected="selected"</c:if>>홍보/보도/소식</option>
										<option value="200" <c:if test="${mdmSearchVO.schDocType == '200'}"> selected="selected"</c:if>>정책/업무자료</option>
										<option value="300" <c:if test="${mdmSearchVO.schDocType == '300'}"> selected="selected"</c:if>>연구자료</option>
										<option value="400" <c:if test="${mdmSearchVO.schDocType == '400'}"> selected="selected"</c:if>>의정활동자료</option>
										<option value="600" <c:if test="${mdmSearchVO.schDocType == '600'}"> selected="selected"</c:if>>정책매뉴얼</option>
										<option value="800" <c:if test="${mdmSearchVO.schDocType == '800'}"> selected="selected"</c:if>>세미나/공청회</option>
										<option value="900" <c:if test="${mdmSearchVO.schDocType == '900'}"> selected="selected"</c:if>>감사자료</option>
										<option value="700" <c:if test="${mdmSearchVO.schDocType == '700'}"> selected="selected"</c:if>>출장보고서</option>
										<option value="500" <c:if test="${mdmSearchVO.schDocType == '500'}"> selected="selected"</c:if>>통계</option>			
										<option value="999" <c:if test="${mdmSearchVO.schDocType == '999'}"> selected="selected"</c:if>>기타</option>
									</select>&nbsp;&nbsp;
									
									<input type="checkbox" id="chargerYn" name="chargerYn" <c:if test="${mdmSearchVO.chargerYn == 'on'}"> checked="checked"</c:if>/> 지방의회 담당자 등록여부
								</div>
								
								<!-- 의회선택 : 회의록, 의안, 의원 -->
								<div id="showOrg" style="float: left;">
									<label style="width:80px;text-align:left;">의회선택 :</label>
									<select name="schLoAsmTyCode" id="schLoAsmTyCode" onchange="return getLoAsmTyCode()" class=" input-sm" style="min-width:150px;">
										<option value="">전체</option>
										<option value="WAC" <c:if test="${mdmSearchVO.schLoAsmTyCode == 'WAC'}"> selected="selected"</c:if>>광역의회</option>
										<option value="BAC" <c:if test="${mdmSearchVO.schLoAsmTyCode == 'BAC'}"> selected="selected"</c:if>>기초의회</option>
									</select>&nbsp;&nbsp;
								</div>
								
								<!-- 지역선택 : 회의록, 의안, 의원-->
								<div id="showAssem" style="float: left;">
									<label style="width:80px;text-align:left;">지역선택 :</label>
									<select name="schRegion" id="schRegion" onchange="return getRegion(this.value, '0')" class=" input-sm" style="min-width:150px;">
										<option value="">전체</option>
										<option value="002" <c:if test="${mdmSearchVO.schRegion == '002'}"> selected="selected"</c:if>>서울특별시</option>
										<option value="051" <c:if test="${mdmSearchVO.schRegion == '051'}"> selected="selected"</c:if>>부산광역시</option>
										<option value="053" <c:if test="${mdmSearchVO.schRegion == '053'}"> selected="selected"</c:if>>대구광역시</option>
										<option value="032" <c:if test="${mdmSearchVO.schRegion == '032'}"> selected="selected"</c:if>>인천광역시</option>
										<option value="062" <c:if test="${mdmSearchVO.schRegion == '062'}"> selected="selected"</c:if>>광주광역시</option>
										<option value="042" <c:if test="${mdmSearchVO.schRegion == '042'}"> selected="selected"</c:if>>대전광역시</option>
										<option value="052" <c:if test="${mdmSearchVO.schRegion == '052'}"> selected="selected"</c:if>>울산광역시</option>
										<option value="044" <c:if test="${mdmSearchVO.schRegion == '044'}"> selected="selected"</c:if>>세종특별자치시</option>
										<option value="031" <c:if test="${mdmSearchVO.schRegion == '031'}"> selected="selected"</c:if>>경기도</option>
										<option value="033" <c:if test="${mdmSearchVO.schRegion == '033'}"> selected="selected"</c:if>>강원도</option>
										<option value="043" <c:if test="${mdmSearchVO.schRegion == '043'}"> selected="selected"</c:if>>충청북도</option>
										<option value="041" <c:if test="${mdmSearchVO.schRegion == '041'}"> selected="selected"</c:if>>충청남도</option>
										<option value="063" <c:if test="${mdmSearchVO.schRegion == '063'}"> selected="selected"</c:if>>전라북도</option>
										<option value="061" <c:if test="${mdmSearchVO.schRegion == '061'}"> selected="selected"</c:if>>전라남도</option>
										<option value="054" <c:if test="${mdmSearchVO.schRegion == '054'}"> selected="selected"</c:if>>경상북도</option>
										<option value="055" <c:if test="${mdmSearchVO.schRegion == '055'}"> selected="selected"</c:if>>경상남도</option>
										<option value="064" <c:if test="${mdmSearchVO.schRegion == '064'}"> selected="selected"</c:if>>제주특별자치도</option>
									</select>&nbsp;&nbsp;
								</div>
								
								<!-- 의회선택 : 회의록, 의안, 의원 -->
								<div id="showMinutes" style="float: left;">
									<label style="width:80px;text-align:left;">의회명 :</label> 
									<select name="schLoAsmCode" id="schLoAsmCode" onchange="return getLoAsmCode(this.value)" class=" input-sm" style="min-width:150px;">
										<option value="">전체</option>
									</select>&nbsp;&nbsp;
								</div>
								
								<div id="showFlag" style="float: left;">
									<!-- 대수 -->
									<label style="width:80px;text-align:left;">대수 :</label> 
									<select name="schRasmblyNumpr" id="schRasmblyNumpr" class=" input-sm" style="min-width:150px;">
										<option value="">전체</option>
										<option value="10" <c:if test="${mdmSearchVO.schRasmblyNumpr == '10'}"> selected="selected"</c:if>>10대</option>
										<option value="9" <c:if test="${mdmSearchVO.schRasmblyNumpr == '9'}"> selected="selected"</c:if>>9대</option>
										<option value="8" <c:if test="${mdmSearchVO.schRasmblyNumpr == '8'}"> selected="selected"</c:if>>8대</option>
										<option value="7" <c:if test="${mdmSearchVO.schRasmblyNumpr == '7'}"> selected="selected"</c:if>>7대</option>
										<option value="6" <c:if test="${mdmSearchVO.schRasmblyNumpr == '6'}"> selected="selected"</c:if>>6대</option>
										<option value="5" <c:if test="${mdmSearchVO.schRasmblyNumpr == '5'}"> selected="selected"</c:if>>5대</option>
										<option value="4" <c:if test="${mdmSearchVO.schRasmblyNumpr == '4'}"> selected="selected"</c:if>>4대</option>
										<option value="3" <c:if test="${mdmSearchVO.schRasmblyNumpr == '3'}"> selected="selected"</c:if>>3대</option>
										<option value="2" <c:if test="${mdmSearchVO.schRasmblyNumpr == '2'}"> selected="selected"</c:if>>2대</option>
										<option value="1" <c:if test="${mdmSearchVO.schRasmblyNumpr == '1'}"> selected="selected"</c:if>>1대</option>
									</select>&nbsp;&nbsp;
								</div>
								
								<!-- 지역 : 지역현안소식 -->
								<div id="showRegion" style="float: left;">
									<label style="width:80px;text-align:left;">지역 :</label> 
									<select name="schRks025" id="schRks025" class=" input-sm" style="min-width:150px;">
										<option value="">전체</option>
										<c:forEach var="cList" items="${codeRKS025List}" varStatus="status">
											<option value="${cList.CODE}" <c:if test="${cList.CODE == mdmSearchVO.schRks025}"> selected="selected"</c:if>>${cList.CODE_NM}</option>
										</c:forEach>
									</select>&nbsp;&nbsp;
								</div>
						</div>
						<!-- 자료유형 조회 E -->
						
						<!-- 기관유형 조회 S -->
						<div id="changeOrg" style="float: left;">
							<span style="text-align: left;">
							<label style="width:75px;text-align:left;">기관유형1 :</label> 
							<select name="schOrgCodeStep1" id="schOrgCodeStep1" onchange="return getOrgCodeStep2List(this.value)" class=" input-sm" style="min-width:130px; max-width: 150px;">
								<option value="">-- 기관유형 1단계 --</option>
								<c:forEach var="cList" items="${codeOrgCodeStep1List}" varStatus="status">
									<option value="${cList.ORGCODE}" <c:if test="${cList.ORGCODE == mdmSearchVO.schOrgCodeStep1}"> selected="selected"</c:if>>${cList.ORGNM}</option>
								</c:forEach>
							</select>&nbsp;&nbsp;
							<label style="width:75px;text-align:left;">기관유형2 :</label>
							<select name="schOrgCodeStep2" id="schOrgCodeStep2" onchange="return getOrgCodeStep3List(this.value)" class=" input-sm" style="min-width:130px; max-width: 150px;">
								<option value="">-- 기관유형 2단계 --</option>
								<c:forEach var="cList" items="${codeOrgCodeStep2List}" varStatus="status">
									<option value="${cList.ORGCODE}" <c:if test="${cList.ORGCODE == mdmSearchVO.schOrgCodeStep2}"> selected="selected"</c:if>>${cList.ORGNM}</option>
								</c:forEach>
							</select>&nbsp;&nbsp;
							<label style="width:75px;text-align:left;">기관유형3 :</label>
							<select name="schOrgCodeStep3" id="schOrgCodeStep3" onchange="return getOrgSiteList(this.value, 'I')" class=" input-sm" style="min-width:130px; max-width: 150px;">
								<option value="">-- 기관유형 3단계 --</option>
								<c:forEach var="cList" items="${codeOrgCodeStep3List}" varStatus="status">
									<option value="${cList.ORGCODE}" <c:if test="${cList.ORGCODE == mdmSearchVO.schOrgCodeStep3}"> selected="selected"</c:if>>${cList.ORGNM}</option>
								</c:forEach>
							</select>&nbsp;&nbsp;
							<label style="width:75px;text-align:left;">기관명 :</label>
							<select name="schOrgCodeStep4" id="schOrgCodeStep4" class=" input-sm" style="min-width:130px; max-width: 150px;">
								<option value="">-- 기관명 --</option>
								<c:forEach var="cList" items="${codeOrgSiteList}" varStatus="status">
									<option value="${cList.SITEID}" <c:if test="${cList.SITEID == mdmSearchVO.schOrgCodeStep4}"> selected="selected"</c:if>>${cList.SITENM}</option>
								</c:forEach>
							</select>&nbsp;&nbsp;
							</span>
							<div id="showMdmAdmOrg" align="left">
							<label style="width:80px;text-align:left;">자료유형 :</label> 
							<select name="mdmAdmOrg" id="mdmAdmOrg" class=" input-sm" style="min-width:150px;">
								<option value="PolicyInfo" <c:if test="${mdmSearchVO.mdmAdmOrg == 'PolicyInfo'}"> selected="selected"</c:if>>지방정책정보</option>
								<option value="Minutes" <c:if test="${mdmSearchVO.mdmAdmOrg == 'Minutes'}"> selected="selected"</c:if>>지방의회 회의록</option>
								<option value="Bill" <c:if test="${mdmSearchVO.mdmAdmOrg == 'Bill'}"> selected="selected"</c:if>>지방의회 의안</option>
								<option value="AsmblyAsemby" <c:if test="${mdmSearchVO.mdmAdmOrg == 'AsmblyAsemby'}"> selected="selected"</c:if>>지방의회 의원</option>
							</select>
							</div>
						</div>
						<!-- 기관유형 조회 E -->
						
						<!-- 수집사이트 조회 S -->
						<div id="changeSite" style="float: left;">
							<span>
								<label style="width:80px;text-align:left;">지역 :</label> 
								<select name="schRegion2" id="schRegion2" onchange="return getSite(this.value, '0')" class=" input-sm" style="min-width:150px;">
									<option value="">-- 지역 선택 --</option>
									<option value="002" <c:if test="${mdmSearchVO.schRegion2 == '002'}"> selected="selected"</c:if>>서울특별시</option>
									<option value="051" <c:if test="${mdmSearchVO.schRegion2 == '051'}"> selected="selected"</c:if>>부산광역시</option>
									<option value="053" <c:if test="${mdmSearchVO.schRegion2 == '053'}"> selected="selected"</c:if>>대구광역시</option>
									<option value="032" <c:if test="${mdmSearchVO.schRegion2 == '032'}"> selected="selected"</c:if>>인천광역시</option>
									<option value="062" <c:if test="${mdmSearchVO.schRegion2 == '062'}"> selected="selected"</c:if>>광주광역시</option>
									<option value="042" <c:if test="${mdmSearchVO.schRegion2 == '042'}"> selected="selected"</c:if>>대전광역시</option>
									<option value="052" <c:if test="${mdmSearchVO.schRegion2 == '052'}"> selected="selected"</c:if>>울산광역시</option>
									<option value="044" <c:if test="${mdmSearchVO.schRegion2 == '044'}"> selected="selected"</c:if>>세종특별자치시</option>
									<option value="031" <c:if test="${mdmSearchVO.schRegion2 == '031'}"> selected="selected"</c:if>>경기도</option>
									<option value="033" <c:if test="${mdmSearchVO.schRegion2 == '033'}"> selected="selected"</c:if>>강원도</option>
									<option value="043" <c:if test="${mdmSearchVO.schRegion2 == '043'}"> selected="selected"</c:if>>충청북도</option>
									<option value="041" <c:if test="${mdmSearchVO.schRegion2 == '041'}"> selected="selected"</c:if>>충청남도</option>
									<option value="063" <c:if test="${mdmSearchVO.schRegion2 == '063'}"> selected="selected"</c:if>>전라북도</option>
									<option value="061" <c:if test="${mdmSearchVO.schRegion2 == '061'}"> selected="selected"</c:if>>전라남도</option>
									<option value="054" <c:if test="${mdmSearchVO.schRegion2 == '054'}"> selected="selected"</c:if>>경상북도</option>
									<option value="055" <c:if test="${mdmSearchVO.schRegion2 == '055'}"> selected="selected"</c:if>>경상남도</option>
									<option value="064" <c:if test="${mdmSearchVO.schRegion2 == '064'}"> selected="selected"</c:if>>제주특별자치도</option>
									<option value="001" <c:if test="${mdmSearchVO.schRegion2 == '001'}"> selected="selected"</c:if>>중앙기관</option>
								</select>&nbsp;&nbsp;		
								
								<label style="width:80px;text-align:left;">사이트명 :</label> 
								<select name="schSiteId" id="schSiteId" onchange="return getSeed(this.value, '0')" class=" input-sm" style="min-width:150px;">
									<option value="">-- 사이트 선택 --</option>
									<c:forEach var="cList" items="${siteList}" varStatus="status">
										<option value="${cList.SITEID}" <c:if test="${cList.SITEID == mdmSearchVO.schSiteId}"> selected="selected"</c:if>>${cList.SITENM}</option>
									</c:forEach>
								</select>&nbsp;&nbsp;
								
								<label style="width:80px;text-align:left;">게시판 :</label> 
								<select name="schSeedId" id="schSeedId" class=" input-sm" style="min-width:150px;">
									<option value="">-- 게시판 선택 --</option>
									<c:forEach var="cList" items="${seedList}" varStatus="status">
										<option value="${cList.SEEDID}" <c:if test="${cList.SEEDID == mdmSearchVO.schSeedId}"> selected="selected"</c:if>>${cList.SEEDNM}</option>
									</c:forEach>
								</select>
							</span>
						</div>
						<!-- 수집사이트 조회 E -->
					</div>
				</td>
			</tr>
		</table>
	</div>
</div><!-- defaultSearch E -->

<!-- 엑셀검색 -->
<div id="excelSearch" <c:if test="${isExcelSearch == null}">style="display:none;"</c:if>>
	<table class="table table-striped table-bordered table-hover" >
		<colgroup>
			<col width="20%" />
			<col width="" />
		</colgroup>
		<tr>
			<th>자료유형</th>
			<td>
				<input type="radio" name="searchDataType" value="PolicyInfo" <c:if test="${excelSearchCollection == null || excelSearchCollection == 'PolicyInfo' }">checked="checked"</c:if> /> 지방정책정보
				<input type="radio" name="searchDataType" value="Minutes" <c:if test="${excelSearchCollection != null && excelSearchCollection == 'Minutes' }">checked="checked"</c:if> /> 회의록
				<input type="radio" name="searchDataType" value="Bill" <c:if test="${excelSearchCollection != null && excelSearchCollection == 'Bill' }">checked="checked"</c:if> /> 의안
				<input type="radio" name="searchDataType" value="AsmblyAsemby" <c:if test="${excelSearchCollection != null && excelSearchCollection == 'AsmblyAsemby' }">checked="checked"</c:if> /> 의원
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="file" class="file fl" id="excelSearchFile" name="excelSearchFile" /> <span class="fl r">(조회건수는 1,000,000개로 제한됩니다.)</span>
				<a href="#none" class="btn btn-success" onclick="SearchExcelFileDownload();">검색용 엑셀파일 다운로드</a>
			</td>
		</tr>
	</table>	
</div>

<div style="clear: both; width: 100%;" ></div>

<div id="searchBtn" class="tr">
	<div style="display:inline; float:left;">
	<c:if test="${minutesList != null && fn:length(minutesList) > 0}">
	<a href="#;"onclick="return insertStdCntcApiColct();"><button type="button" class="btn btn-success">재수집</button></a>
	</c:if>
	<c:if test="${billList != null && fn:length(billList) > 0}">
	<a href="#;"onclick="return insertStdCntcApiColct();"><button type="button" class="btn btn-success">재수집</button></a>
	</c:if>
	<c:if test="${asmblyAsembyActList != null && fn:length(asmblyAsembyActList) > 0}">
	<a href="#;"onclick="return insertStdCntcApiColct();"><button type="button" class="btn btn-success">재수집</button></a>
	</c:if>
	
	<c:if test="${mdmSearchVO.mdmAdm == 'PolicyInfo' || mdmSearchVO.mdmAdm == 'EduManual' || mdmSearchVO.mdmAdm == 'AsmblyAsemby'}">
	<a href="#" onclick="return regist();"><button type="button" class="btn btn-primary">등록</button></a>
	</c:if>
	
	<a href="#;" onclick="return setCheck('Y');"><button type="button" class="btn btn-success">게시</button></a>
	<a href="#;" onclick="return setCheck('N');"><button type="button" class="btn btn-danger">미게시</button></a>
	<a href="#;" onclick="return setDelete('D');"><button type="button" class="btn btn-danger">삭제</button></a>
	<a href="#;" onclick="return setDelete('U');"><button type="button" class="btn btn-primary">복구</button></a>
	</div>
	
	<button type="button" class="btn btn-warning" onclick="frmSubmit('btn');">검색</button> 
	<button type="button" class="btn btn-info" onclick="setDefault();">초기화</button>
</div>
<br />
<br />