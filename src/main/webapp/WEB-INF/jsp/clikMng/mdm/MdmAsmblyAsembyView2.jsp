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
<title>지방의원정보</title>

<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

var currCnId = '${asmblyAsembyView.ASEMBY_CN}';
var cnList;
$(document).ready(function(){
	
	var tempCn = '${cnList}';
	cnList = tempCn.split(",");
	
});

//이전버튼
function fn_prev(){
	$(".spinner").show();
	var frm = document.fvfrm;
	var url = "";
	var i = 0;
	for(; i < cnList.length; i++){
		if(currCnId == cnList[i]){
// 			url = "/mdm/MdmAsmblyAsembyView.do?cnId="+cnList[i-1];
			frm.action = "/mdm/MdmAsmblyAsembyView.do";
			frm.cnId.value = cnList[i-1];
			$('#isPrevNextPaging').val('N');
			break;
		}
	}
	
	if(${mdmSearchVO.pageNum} > 1 && i == 0)
	{
		frm.pageNum.value = ${mdmSearchVO.pageNum} - 1;
		frm.action = "/mdm/MdmAsmblyAsembyView.do";
		frm.cnId.value = "";
		frm.rasmblyId.value = "";
		$('#prevNextGubun').val('prev');
		$('#isPrevNextPaging').val('Y');
	}
	else if(${mdmSearchVO.pageNum} == 1 && i == 0)
	{
		$(".spinner").hide();
		alert("처음 입니다.");
		return false;
	}
	
// 	var frm = document.sfrm;
// 	frm.action = url;
// 	var ret_open = window.open('about:blank','VIEWER_POP','width=1366,height=768,scrollbars=yes,resizable=yes');
	
// 	if(ret_open == null) {
// 		$(".spinner").hide();
// 		alert("팝업차단을 해제하세요!");
// 		return false;
// 	}
	
// 	frm.title.value = '메타데이터보기';
// 	frm.target='VIEWER_POP';
	frm.submit();
// 	frm.target = "_self";
}

//다음버튼
function fn_next(){
	$(".spinner").show();
	var frm = document.fvfrm;
	var url = "";
	var i = 0;
	for(; i < cnList.length; i++){
		if(currCnId == cnList[i]){
// 			url = "/mdm/MdmAsmblyAsembyView.do?cnId="+cnList[i+1];
			frm.action = "/mdm/MdmAsmblyAsembyView.do";
			frm.cnId.value = cnList[i+1];
			$('#isPrevNextPaging').val('N');
			break;
		}
	}
	
	if(cnList.length == (i+1) && ${EndPage} > (${mdmSearchVO.pageNum} + 1))
	{
		frm.pageNum.value = ${mdmSearchVO.pageNum} + 1;
		frm.action = "/mdm/MdmAsmblyAsembyView.do";
		frm.cnId.value = "";
		frm.rasmblyId.value = "";
		$('#prevNextGubun').val('next');
		$('#isPrevNextPaging').val('Y');
	}
	else if(cnList.length == (i+1) && ${EndPage} == ${mdmSearchVO.pageNum})
	{
		$(".spinner").hide();
		alert("마지막 입니다.");
		return false;
	}
	
// 	var frm = document.sfrm;
// 	frm.action = url;
// 	var ret_open = window.open('about:blank','VIEWER_POP','width=1366,height=768,scrollbars=yes,resizable=yes');
	
// 	if(ret_open == null) {
// 		$(".spinner").hide();
// 		alert("팝업차단을 해제하세요!");
// 		return false;
// 	}
	
// 	frm.title.value = '메타데이터보기';
// 	frm.target='VIEWER_POP';
	frm.submit();
// 	frm.target = "_self";
}


function list() {
	pgfrm.action = "/mdm/MdmAsmblyAsembyList.do";
	document.pgfrm.submit();
}

function setUpdate() {
    
	if( !confirm("의원정보를 수정하시겠습니까?") ) {
		return false;
	}
	
	var vildChk = false;
	var rasmbly_numpr_list = {};
	
	//필수값 체크 S
	//활동및경력 사항 필수값 체크 및 중복 체크
	var actLength = $('table.lamanActivityTables').length;
	$('table.lamanActivityTables').each(function(index,table){
		if(index+1 < actLength){ //활동정보 템플릿이 존재하여 갯수 체크
			var numpr = $(table).find('[name="ACT_RASMBLY_NUMPR"]').val();
			
			//대수 정보 체크
			if(numpr == ""){
				alert("대수는 필수 입력값 입니다.");
				$(table).find('[name="ACT_RASMBLY_NUMPR"]').focus();
				vildChk = true;
				return false;
			}

			//대수 중복 체크 S
			$(rasmbly_numpr_list).each(function(index2,rasmbly_numpr){
				if(rasmbly_numpr[index2] == numpr){ 
					alert("활동및경력 - 중복된 대수정보가 존재합니다.");
					vildChk = true;
					return false;
				}
			});
			
			if(!vildChk){
				rasmbly_numpr_list[index] = numpr;
			}
			//대수 중복 체크 E
			
			//활동여부 체크
			if($(table).find('select[name="ACT_ACT_AT"]').val() == ""){
				alert("활동여부는 필수 입력값 입니다.");
				$(table).find('select[name="ACT_ACT_AT"]').focus();
				vildChk = true;
				return false;
			}
		}
	});
	
	if(vildChk){
		return false;
	}
	
	//직위 정보 체크
	$('table.lamanActivityTables').each(function(index,table){
  		$(table).find('select[name="ACT_POSITION_ID"]').each(function(targetIndex,targetPosition){
  			var targetHtSeStdcd = $(targetPosition).parent().find('[name="ACT_POSITION_HT_SE_STDCD"]').val();
  			var targetMtgnm = $(targetPosition).parent().find('[name="ACT_MTGNM_ID"]').val();
  			var targetPositionValue = $(targetPosition).val();
  			
  			if(!vildChk && targetHtSeStdcd == "" && (targetMtgnm != "" || targetPositionValue != "")){
  				alert("직위 - 전후반기 정보를 입력해 주세요.");
  				$(targetPosition).parent().find('[name="ACT_POSITION_HT_SE_STDCD"]').eq(targetIndex).focus();
  				vildChk = true;
  				return false;
  			}
  			
  			if(!vildChk && targetMtgnm == "" && (targetHtSeStdcd != "" || targetPositionValue != "")){
  				alert("직위 - 회의명 정보를 입력해 주세요.");
  				$(targetPosition).parent().find('[name="ACT_MTGNM_ID"]').eq(targetIndex).focus();
  				vildChk = true;
  				return false;
  			}
  			
  			if(!vildChk && targetPositionValue == "" && (targetHtSeStdcd != "" || targetMtgnm != "")){
  				alert("직위 정보를 입력해 주세요.");
  				$(targetPosition).focus();
  				vildChk = true;
  				return false;
  			}
  		});
  	});
	
	if(vildChk){
		return false;
	}
	//필수값 체크 E
	
	
	// 파일 확장자 체크
	if(document.getElementById('_PHOTO_FILE_NM') != null){
	    var confirmExt;
	    var thumbext = document.getElementById('_PHOTO_FILE_NM').value; 
	    if(document.getElementById('_PHOTO_FILE_NM').value != "") {
	        confirmExt = fn_confirmExt(thumbext);
	        if(!confirmExt) {
	        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
	        	return;
	        }
	    }
	}
    
  	//직위 중복 체크
  	$('table.lamanActivityTables').each(function(index,table){
  		var tableIndex = index;
  		$(table).find('[name="ACT_POSITION_ID"]').each(function(targetIndex,targetPosition){
  			var targetHtSeStdcd = $(targetPosition).parent().find('[name="ACT_POSITION_HT_SE_STDCD"]').val();
  			var targetMtgnm = $(targetPosition).parent().find('[name="ACT_MTGNM_ID"]').val();
  			var targetPositionValue = $(targetPosition).val();
  			
  			if(targetHtSeStdcd != "" && targetMtgnm != "" && targetPositionValue != ""){
	  			$(table).eq(tableIndex).find('[name="ACT_POSITION_ID"]').each(function(currIndex,currPosition){
	  				if(!vildChk
	  						&& targetIndex != currIndex
	  						&& targetHtSeStdcd == $(currPosition).parent().find('[name="ACT_POSITION_HT_SE_STDCD"]').val()
	  						&& targetMtgnm == $(currPosition).parent().find('[name="ACT_MTGNM_ID"]').val()){
	  	  				alert("중복되는 직위 정보가 존재합니다.");
	  	  				$(currPosition).parent().find('[name="ACT_MTGNM_ID"]').focus();
	  	  				vildChk = true;
	  	  				return false;
	  	  			}  				
	  			});
  			}
  		});
  	});
  	
  	if(vildChk){
  		return false;
  	}
  	
	//입력 최대값 체크
	if(getByteLength($('#BRTHDY').val()) > 8){
		alert("[생년월일] 최대 8byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#BRTHDY').focus();
		return false;
	}
	if(getByteLength($('#ADRES').val()) > 200){
		alert("[지번주소] 최대 200byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#ADRES').focus();
		return false;
	}
	if(getByteLength($('#RDNMADR').val()) > 200){
		alert("[도로명주소] 최대 200byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#RDNMADR').focus();
		return false;
	}
	if(getByteLength($('#OWNHOM_TLPHON').val()) > 20){
		alert("[자택] 최대 20byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#OWNHOM_TLPHON').focus();
		return false;
	}
	if(getByteLength($('#phone2').val()) > 4){
		alert("[휴대폰 2] 최대 4byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#phone2').focus();
		return false;
	}
	if(getByteLength($('#phone3').val()) > 4){
		alert("[휴대폰 3] 최대 4byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#phone3').focus();
		return false;
	}
	if(getByteLength($('#OFFM_TLPHON').val()) > 20){
		alert("[사무실] 최대 20byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#OFFM_TLPHON').focus();
		return false;
	}
	if(getByteLength($('#FAX').val()) > 20){
		alert("[팩스] 최대 20byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#FAX').focus();
		return false;
	}
	if(getByteLength($('#email1').val()) > 140){
		alert("[이메일 계정] 최대 140byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#email1').focus();
		return false;
	}
	if(getByteLength($('#email2').val()) > 50){
		alert("[이메일 도메인] 최대 50byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#email2').focus();
		return false;
	}
	if(getByteLength($('#HMPG').val()) > 200){
		alert("[홈페이지] 최대 200byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#HMPG').focus();
		return false;
	}
	if(getByteLength($('#TWITTER').val()) > 200){
		alert("[트위터] 최대 200byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#TWITTER').focus();
		return false;
	}
	if(getByteLength($('#FACEBOOK').val()) > 200){
		alert("[페이스북] 최대 200byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#FACEBOOK').focus();
		return false;
	}
	
	//미활동사유 disabled 제거
	$('select[name="ACT_NOACT_RESN_STDCD"]').removeAttr("disabled");
	
	//직위 정보 대수별 설정
	$('table.lamanActivityTables').each(function(index,table){
  		
		var position_info = "";
		
		$(table).find('[name="ACT_POSITION_ID"]').each(function(targetIndex,targetPosition){
  			var targetHtSeStdcd = $(targetPosition).parent().find('[name="ACT_POSITION_HT_SE_STDCD"]').val();
  			var targetMtgnm = $(targetPosition).parent().find('[name="ACT_MTGNM_ID"]').val();
  			var targetPositionValue = $(targetPosition).val();
  			
  			position_info += targetHtSeStdcd + "#" + targetMtgnm + "#" + targetPositionValue + "@";
  			
  		});
		
		$(table).find('input[name="POSITION_INFO"]').val(position_info);
  	});
	
	$(".spinner").show();
	document.pgfrm.action="/mdm/MdmAsmblyAsembyUpdate.do";
	
	var input = document.createElement("input");
	input.type = 'hidden';
	input.name = 'EndPage';
	input.value = '${EndPage}';
	document.pgfrm.appendChild(input);
	document.pgfrm.submit();
}

/**
 * 파일 확장자 체크
 * 해당 확장자가 아닌 다른 확장자로 업로드 실행 시 필터
 */
function fn_confirmExt(str) {
	str = str.slice(str.lastIndexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.

	var result = true;

	if(str != "jpg" && str != "png" &&  str != "gif" &&  str != "bmp"){ //확장자를 확인합니다.

		//alert('썸네일은 이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
		result = false;
		return result;

	} else {
		return result;
	}
}

function setDelete() {
	if( !confirm("의원정보를 삭제하시겠습니까?") ) {
		return false;
	}
	document.pgfrm.action="/mdm/MdmAsmblyAsembyDelete.do";
	document.pgfrm.submit();
}

function setEmail(val) {
	if( val == "" ) {
		return false;
	}
	if( val == "dw" ) {
		$("#email2").val("");
	}
	else {
		$("#email2").val(val);
	}
}

//문자입력 방지
//for firefox, IE, chreome
function checkNumber(e) {
	var eventCode =(window.netscape)? e.which : e.keyCode;

	if(eventCode==16) { alert('Shift는 사용 할 수 없습니다.'); return false; }

	if ( ( (96<=eventCode) && (eventCode<=105) ) || ( (48<=eventCode) && (eventCode<=57) ) || (eventCode==8) || (eventCode==37) || (eventCode==39) || (eventCode==9)|| (eventCode==46))
	{
		e.returnValue=true;
	}
	else
	{
		//e.preventDefault();
		//e.returnValue=false;

		//ie6,7,8
		(e.preventDefault) ? e.preventDefault() : e.returnValue = false;
	}
}

//의원사진삭제
function setPhotoFileDelete() {
	if( !confirm("의원사진을 삭제하시겠습니까?") ) {
		return false;
	}
	
	//20150714 flag 처리로 변경
	$('#asemby_photo').children().remove();
	$('#asemby_photo').append('<input name="_PHOTO_FILE_NM" id="_PHOTO_FILE_NM" class="input-sm ip " value="찾아보기"  type="file" style="width:90%; float:left;">');
	$('input[name="PHOTO_FILE_DELETE_YN"]').val('Y');
}

//관련회의록보기 버튼
function selectMinutesInfo(){
	var asemby_id = '${asmblyAsembyView.ASEMBY_ID}';
	var rasmbly_id = '${asmblyAsembyView.RASMBLY_ID}';
	var option="width=950px,height=900px,scrollbars=yes,resizable=yes";
	window.open("/mdm/MdmTnsrMinutes.do?rasmblyId="+rasmbly_id+"&asembyId="+asemby_id+"&pageNum=1&listCnt=1",'지방의회  회의록정보',option);
}

//미활동사유
function fn_noActDisabled(selectbox){
	if($(selectbox).val() == "Y"){
		$(selectbox).parent().parent().find('select[name="ACT_NOACT_RESN_STDCD"]').val('');
		$(selectbox).parent().parent().find('select[name="ACT_NOACT_RESN_STDCD"]').attr("disabled","true");
	}else{
		$(selectbox).parent().parent().find('select[name="ACT_NOACT_RESN_STDCD"]').removeAttr("disabled");
	}
}

//직위 정보 추가
function fn_positionAdd(btn,rasmblyId,rasmblyNumpr){

	var p = $("<p></p>");
	
	var htSeStdcd = $('<select name="ACT_POSITION_HT_SE_STDCD" class=" input-sm" style="width:120px; margin-right:4px;"></select>');
	$(htSeStdcd).append('<option value="">선택</option>');
	$(htSeStdcd).append('<option value="HTS001">전반기</option>');
	$(htSeStdcd).append('<option value="HTS002">후반기</option>');

	var mtgnm = $('<select name="ACT_MTGNM_ID" class=" input-sm" style="width:190px; margin-right:4px;"></select>');
	$(mtgnm).append('<option value="">선택</option>');
	
	var position = $('<select name="ACT_POSITION_ID" class=" input-sm" style="width:190px; margin-right:4px;"></select>');
	$(position).append('<option value="">선택</option>');
	$(position).append('<option value="OPS001">의장</option>');
	$(position).append('<option value="OPS002">부의장</option>');
	$(position).append('<option value="OPS003">위원장</option>');
	$(position).append('<option value="OPS004">부위원장</option>');
	$(position).append('<option value="OPS005">위원</option>');
	
	if(rasmblyNumpr == ''){
		rasmblyNumpr = $(btn).parent().parent().parent().find('select[name="ACT_RASMBLY_NUMPR"]').val();
	}
	
	if(rasmblyNumpr != ''){
		//직위 조회
		$.ajax({
			url:"/mdm/MdmTnsrMtgnmList.do",
			cache:false,
			type:"post",
			data:{"rasmblyId":rasmblyId,"rasmblyNumpr":rasmblyNumpr},
			success: function(data, status) {
				$(mtgnm).find('option').remove();
				$(mtgnm).append('<option value="">선택</option>');
				$($.parseJSON(data)).each(function(index,value){
					$(mtgnm).append('<option value="'+value.MTGNM_ID+'">'+value.MTGNM+'</option>');
				});
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
	}
	
	$(p).append(htSeStdcd);
	$(p).append(mtgnm);
	$(p).append(position);
	$(p).append('<a href="#;" onclick="fn_positionDel(this);"><button type="button" class="btn btn-danger">삭제</button></a>');
	
	$(btn).parent().next().append(p);
	
}

//직위정보 삭제
function fn_positionDel(btn){
	if(!confirm("선택한 직위 정보를 삭제하시겠습니까?")){
		return false;
	}else{
		$(btn).parent().remove();
	}
}

//활동및경력 정보 추가
function lamanActivityAdd(btn){
	var cloneTable = $('#lamanActivityTamp .lamanActivityTables').eq(0).clone();
	$('#lamanActivityList').append(cloneTable);
	
	if($('table.lamanActivityTables').length == 1)
		getRasmblyNumpr();
}

//활동및경력 정보 제거
function lamanActivityDel(btn){
	var numpr = $(btn).parent().parent().parent().parent().find('input[name="ACT_RASMBLY_NUMPR"]').val();
	
	if(numpr == null || numpr == undefined)
		numpr = $(btn).parent().parent().parent().parent().find('#ACT_RASMBLY_NUMPR').val();
	
	if(!confirm(numpr +"대 활동정보를 삭제하시겠습니까?")){
		return false;
	}else if(numpr == null || numpr == undefined){//신규 추가한 활동 정보는 삭제 처리
		$(btn).parent().parent().parent().parent().remove();	
	}else{//기존 정보는 업데이트를 위해서 숨김 처리
		$(btn).parent().parent().parent().parent().attr('style','display:none');
		$(btn).parent().parent().parent().parent().find('input[name="ACT_CUD_CODE"]').val('D');
		$(btn).parent().parent().parent().parent().next().remove();
	}
}

//의회별 대수 목록 조회
function getRasmblyNumpr(){
	$.ajax({
		url:"/mdm/MdmTnsrRasmblyList.do",
		cache:false,
		type:"post",
		data:{"rasmblyId":'${asmblyAsembyView.RASMBLY_ID}'},
		success: function(data, status) {
			$('table.lamanActivityTables select[name="ACT_RASMBLY_NUMPR"] option').remove();
			$('table.lamanActivityTables select[name="ACT_RASMBLY_NUMPR"]').append('<option value="">전체</option>');
			$($.parseJSON(data)).each(function(index,value){
				$('table.lamanActivityTables select[name="ACT_RASMBLY_NUMPR"]').append('<option value="'+value.RASMBLY_NUMPR+'">'+value.RASMBLY_NUMPR+'대</option>');
			});
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}

//선거구 조회
function getEstAndMtgnmList(el,RASMBLY_NUMPR) {
	if(RASMBLY_NUMPR != ''){
		//선거구 조회
		$.ajax({
			url:"/mdm/MdmTnsrEstIdList.do",
			cache:false,
			type:"post",
			data:{"RASMBLY_ID":'${asmblyAsembyView.RASMBLY_ID}',"RASMBLY_NUMPR":RASMBLY_NUMPR},
			success: function(data, status) {
				$(el).parent().parent().parent().find('select[name="ACT_EST_ID"]').html(data);
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
		//직위 조회
		$.ajax({
			url:"/mdm/MdmTnsrMtgnmList.do",
			cache:false,
			type:"post",
			data:{"rasmblyId":'${asmblyAsembyView.RASMBLY_ID}',"rasmblyNumpr":RASMBLY_NUMPR},
			success: function(data, status) {
				$(el).parent().parent().parent().find('select[name="ACT_MTGNM_ID"] option').remove();
				$(el).parent().parent().parent().find('select[name="ACT_MTGNM_ID"]').append('<option value="">선택</option>');
				$($.parseJSON(data)).each(function(index,value){
					$(el).parent().parent().parent().find('select[name="ACT_MTGNM_ID"]').append('<option value="'+value.MTGNM_ID+'">'+value.MTGNM+'</option>');
				});
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
	}
	return false;
}

//문자열 BYTE 계산
function getByteLength(s,b,i,c){
    for(b=i=0;c=s.charCodeAt(i++);b+=c>>11?3:c>>7?2:1);
    return b;
}

//서비스시스템 상새화면을 호출한다
function openServicePopup(){
	window.open('http://clik.nanet.go.kr/potal/search/searchView.do?collection=assemblyinfo&DOCID=${asmblyAsembyView.ASEMBY_CN}','_blank');
}
</script>
</head>
<body class="body" style="background:none;">
<div id="page-wrapper" style="margin-left:0; width:100%; float:left;">
<form name="pgfrm" method="post" enctype="multipart/form-data">
<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
<input type="hidden" name="ASEMBY_ID" value="${asmblyAsembyView.ASEMBY_ID}" />
<input type="hidden" name="asembyId" value="${asmblyAsembyView.ASEMBY_ID}" />
<input type="hidden" name="RASMBLY_ID" value="${asmblyAsembyView.RASMBLY_ID}" />
<input type="hidden" name="RASMBLY_NUMPR" value="${asmblyAsembyView.RASMBLY_NUMPR}" />
<input type="hidden" name="CUD_CODE" value="${asmblyAsembyView.CUD_CODE}" />
<input type="hidden" name="PHOTO_FILE_DELETE_YN" value="N" />
<input type="hidden" name="ASEMBY_CN" value="${asmblyAsembyView.ASEMBY_CN}" />
<input type="hidden" name="EndPage" id="EndPage" value="${EndPage}" >
<input type="hidden" name="cnList" id="cnList" value="${cnList}" >
<input type="hidden" name="sort" value="${mdmSearchVO.sort }" >

	<div class="row">
		<div class="col-lg-12">
        	<h1 class="page-header">메타데이터 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
	<h2>메타데이터 목록 (지방의회 의원정보) </h2>
    <!-- /.row -->
    <div class="row">
    	<p class="tr">
    		<a href="#none" class="btn btn btn-default" onclick="fn_prev();" style="color:#d84e2c">이전</a>
			<a href="#none" class="btn btn btn-default" onclick="fn_next();" style="color:#d84e2c">다음</a>
    	</p>
    	<p class="tr">
    		<a href="#;" onclick="return openServicePopup();"><button type="button" class="btn btn-default">서비스 화면보기</button></a>
    		<a href="#;" onclick="self.close();"><button type="button" class="btn btn-default">닫기</button></a>
			<a href="#;" onclick="return setUpdate();"><button type="button" class="btn btn-success">수정</button></a>
			<a href="#;" onclick="return setDelete();"><button type="button" class="btn btn-danger">삭제</button></a>
		</p>
		 <!-- /.panel-heading -->
		 <div class="panel-body ">
		 	<div class="movebox">
		 		<div class="table-responsive" id="leftArea">
		 			<table class="table table-striped table-bordered ">
		 				<colgroup>
		 					<col width="15%" />
							<col width="20%" />
							<col width="15%" />
							<col width="20%" />
							<col width="15%" />
							<col width="15%" />
						</colgroup>
						<tbody>
							<tr>
		                		<td colspan="6"><font color="red">주의!!<br>지방의회 API로 수집된 데이터입니다.<br>관리시스템에서 수정한 후에 재수집되는 경우 다시 업데이트가 될 수 있습니다.</font></td>
		                	</tr>
		                	<tr>
		                		<th>제어번호</th>
		                		<td colspan="2">${asmblyAsembyView.ASEMBY_CN }</td>
								<th>자동수집여부</th>
								<td colspan="2">
									<input type="radio" id="COLCT_AT_Y" name="COLCT_AT" value="Y" <c:if test="${asmblyAsembyView.COLCT_AT == 'Y'}"> checked="checked"</c:if>/> 허용
									<input type="radio" id="COLCT_AT_N" name="COLCT_AT" value="N" <c:if test="${asmblyAsembyView.COLCT_AT == 'N' || asmblyAsembyView.COLCT_AT == null}"> checked="checked"</c:if>/> 미허용
								</td>
							</tr>
		                	<tr>
								<th>의원사진</th>
								<td colspan="2" id="asemby_photo">
									<c:choose>
										<c:when test="${fn:trim(asmblyAsembyView.PHOTO_FILE_PATH) == ''}">
											<input name="_PHOTO_FILE_NM" id="_PHOTO_FILE_NM" class="input-sm ip " value="찾아보기"  type="file" style="width:90%; float:left;">
										</c:when>
										<c:otherwise>
											<img src="http://clik.nanet.go.kr/clik-data/image${fn:substringAfter(asmblyAsembyView.PHOTO_FILE_PATH, 'image')}" width="64" height="85" />
			 								<button type="button" class="btn btn-danger" onclick="setPhotoFileDelete();">사진삭제</button>
										</c:otherwise>
									</c:choose>
								</td>
								<th>관련 회의록/발언</th> 
								<td colspan="2"><button onclick="javascript:selectMinutesInfo();">관련 회의록 / 발언 보기</button></td>
							</tr>
							<tr>
								<th>의원명</th>
								<td>${asmblyAsembyView.ASEMBY_NM}<input type="hidden" name="ASEMBY_NM" value="${asmblyAsembyView.ASEMBY_NM}" /></td>
								<th>생년월일</th>
								<td><input type="text" name="BRTHDY" id="BRTHDY" value="${asmblyAsembyView.BRTHDY}" /></td>
								<th>의회명</th>
								<td>${asmblyAsembyView.RASMBLY_NM}</td>
							</tr>
							<tr>
								<th>주소</th>
								<td colspan="5">
									지번&nbsp;&nbsp;&nbsp;주소 : <input type="text" name="ADRES" id="ADRES" style="width:80%;" value="${asmblyAsembyView.ADRES}" /><br>
									도로명주소 : <input type="text" name="RDNMADR" id="RDNMADR" style="width:80%;" value="${asmblyAsembyView.RDNMADR}" />
								</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td colspan="5">
									자택 : <input type="text" name="OWNHOM_TLPHON" id="OWNHOM_TLPHON" value="${asmblyAsembyView.OWNHOM_TLPHON}" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									휴대폰 : 
									<c:set var="mbtlnum" value="${fn:split(asmblyAsembyView.MBTLNUM,'-')}" />
									<select name="phone1" class=" input-sm"> 
										<option value="010" <c:if test="${mbtlnum[0] == '010'}"> selected="selected"</c:if>>010</option>
										<option value="011" <c:if test="${mbtlnum[0] == '011'}"> selected="selected"</c:if>>011</option>
										<option value="016" <c:if test="${mbtlnum[0] == '016'}"> selected="selected"</c:if>>016</option>
										<option value="017" <c:if test="${mbtlnum[0] == '017'}"> selected="selected"</c:if>>017</option>
										<option value="018" <c:if test="${mbtlnum[0] == '018'}"> selected="selected"</c:if>>018</option>
										<option value="019" <c:if test="${mbtlnum[0] == '019'}"> selected="selected"</c:if>>019</option>
									</select>
									<input type="text" class="input-sm ip" name="phone2" id="phone2" value="${mbtlnum[1]}" onkeyup="checkNumber(event)" title="휴대폰 중간자리 입력창" maxlength="4" style="width:100px;"/> - 
									<input type="text" class="input-sm ip" name="phone3" id="phone3" value="${mbtlnum[2]}" onkeyup="checkNumber(event)" title="휴대폰 끝자리 입력창" maxlength="4" style="width:100px;" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									사무실 : <input type="text" name="OFFM_TLPHON" id="OFFM_TLPHON" value="${asmblyAsembyView.OFFM_TLPHON}" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									팩스 : <input type="text" class="input-sm ip" name="FAX" id="FAX" value="${asmblyAsembyView.FAX}" onkeyup="checkNumber(event)" title="팩스" />
								</td>
							</tr>
							<tr>
								<th>이메일</th>
								<td colspan="5">
									<c:set var="email" value="${fn:split(asmblyAsembyView.EMAIL,'@')}" />
									<input name="email1" id="email1" value="${email[0]}" class="input-sm ip" type="text" style="width:20%;"> @ <input name="email2" id="email2" value="${email[1]}" class="input-sm ip" type="text" style="width:20%;">
									<select name="email3" id="email3" onchange="return setEmail(this.value);" class=" input-sm" style="width:40%;">
										<option value="dw">직접입력</option>
										<option value="naver.com">naver.com</option>
										<option value="hanmail.net">hanmail.net</option>
										<option value="nate.com">nate.com</option>
										<option value="hotmail.com">hotmail.com</option>
										<option value="msn.com">msn.com</option>
										<option value="live.co.kr">live.co.kr</option>
										<option value="gmail.com">gmail.com</option>
										<option value="yahoo.co.kr">yahoo.co.kr</option>
										<option value="paran.com">paran.com</option>
										<option value="freechal.com">freechal.com</option>
										<option value="lycos.co.kr">lycos.co.kr</option>
										<option value="korea.com">korea.com</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>홈페이지</th>
								<td colspan="5"><input type="text" name="HMPG" id="HMPG" style="width:98%;" value="${asmblyAsembyView.HMPG}" /></td>
							</tr>
							<tr>
								<th>트위터</th>
								<td colspan="2"><input type="text" name="TWITTER" id="TWITTER" value="${asmblyAsembyView.TWITTER}" /></td>
								<th>페이스북</th>
								<td colspan="2"><input type="text" name="FACEBOOK" id="FACEBOOK" value="${asmblyAsembyView.FACEBOOK}" /></td>
							</tr>
							<tr>
								<th>활동 및 경력 <a href="#;" onclick="lamanActivityAdd(this);"><button type="button" class="btn btn-success">추가</button></a></th>
								<td colspan="5" id="lamanActivityList">
								<c:forEach var="item" items="${lamanActivityList}">
								<table class="table table-striped table-bordered lamanActivityTables">
									<colgroup>
					 					<col width="15%" />
										<col width="20%" />
										<col width="15%" />
										<col width="20%" />
										<col width="15%" />
										<col width="15%" />
									</colgroup>
									<tbody>
										<tr style="display:none;">
											<td colspan="5">
												<input type="hidden" name="ACT_RASMBLY_ID" value="${item.RASMBLY_ID}" />
												<input type="hidden" name="ACT_RASMBLY_NUMPR" value="${item.RASMBLY_NUMPR}" />
												<input type="hidden" name="ACT_ASEMBY_ID" value="${item.ASEMBY_ID}" />
												<input type="hidden" name="ACT_CUD_CODE" value="${item.CUD_CODE == 'C' ? 'U' : item.CUD_CODE }" />
											</td>
										</tr>
										<tr>
											<td colspan="5">
											<a href="#;" onclick="lamanActivityDel(this);"><button type="button" class="btn btn-danger">삭제</button></a>
											</td>
											</tr>
										<tr>
											<th><font style="color:red;">대수</font></th>
											<td colspan="5"><font style="color:red;">${item.RASMBLY_NUMPR }</font></td>
										</tr>
										<tr>
											<th>
												직위
												<a href="#;" onclick="fn_positionAdd(this,'${item.RASMBLY_ID}','${item.RASMBLY_NUMPR}');"><button type="button" class="btn btn-success">추가</button></a>
											</th>
											<td colspan="5">
												<input type="hidden" name="POSITION_INFO"/>
												
												<c:set var="position1" value="${item.POSITION1 }" />
												<c:set var="position1CodeList" value="${fn:split(position1,',') }" />
												<c:set var="position2" value="${item.POSITION2 }" />
												<c:set var="position2CodeList" value="${fn:split(position2,',') }" />
												
												<c:if test="${item.POSITION1 != null}">
												<c:forEach var="vo" items="${position2CodeList }" varStatus="status">
													<P>
													<c:set var="position2Code" value="${fn:split(vo,'@') }" />
													
													<input type="hidden" name="ACT_POSITION_HT_SE_STDCD" value="${position2Code[0] }" />
													<input type="hidden" name="ACT_MTGNM_ID" value="${position2Code[1] }" />
													<span>${position1CodeList[status.index] }</span>
													<select name="ACT_POSITION_ID" class=" input-sm" style="width:190px;">
													<c:forEach var="positionList" items="${positionList }">
														<option value="${positionList.CODE }" <c:if test="${positionList.CODE == position2Code[2]}">selected="selected"</c:if>>${positionList.CODE_NM }</option>
													</c:forEach>
													</select>
													</P>
												</c:forEach>
												</c:if>
											</td>
										</tr>
										<tr>
											<th>활동여부</th>
											<td colspan="2">
											<select name="ACT_ACT_AT" onchange="return fn_noActDisabled(this);">
											<option value="">선택</option>
											<option value="Y" <c:if test="${item.ACT_AT == 'Y'}">selected="selected"</c:if>>활동</option>
											<option value="N" <c:if test="${item.ACT_AT == 'N'}">selected="selected"</c:if>>미활동</option>
											</select>
											</td>
											<th>미활동사유</th>
											<td colspan="2">
											<select name="ACT_NOACT_RESN_STDCD">
											<option value="">선택</option>
											<c:forEach var="codeList" items="${noactResnStdcdList }">
											<option value="${codeList.CODE }" <c:if test="${codeList.CODE == item.NOACT_RESN_STDCD}">selected="selected"</c:if>>${codeList.CODE_NM }</option>
											</c:forEach>
											</select>
											</td>
										</tr>
										<tr>
											<th>선거구</th>
											<td colspan="2">
											<select name="ACT_EST_ID">
											<option value="">선택</option>
											<c:forEach var="codeList" items="${codeEstbsList }">
											<c:if test="${codeList.RASMBLY_NUMPR == item.RASMBLY_NUMPR}">
											<option value="${codeList.EST_ID }" <c:if test="${codeList.EST_ID == item.EST_ID}">selected="selected"</c:if>>${codeList.EST_NM }</option>
											</c:if>
											</c:forEach>
											</select>
											</td>
											<th>정당</th>
											<td colspan="2">
											<select name="ACT_PPRTY_CODE">
											<option value="">선택</option>
											<c:forEach var="codeList" items="${codePprtyList }">
											<option value="${codeList.PPRTY_CODE }" <c:if test="${codeList.PPRTY_CODE == item.PPRTY_CODE}">selected="selected"</c:if>>${codeList.PPRTY_NM }</option>
											</c:forEach>
											</select>
											</td>
										</tr>
										<tr>
											<th>경력</th>
											<td colspan="5">
											<textarea name="ACT_CAREER_MATTER" rows="3" style="width:98%;"><c:out value="${item.CAREER_MATTER}" escapeXml="false" /></textarea>
											</td>
										</tr>
										<tr>
											<th>학력</th>
											<td colspan="5">
											<textarea name="ACT_ACDMCR_MATTER" rows="3" style="width:98%;">${item.ACDMCR_MATTER }</textarea>
											</td>
										</tr>
										<tr>
											<th>인사말</th>
											<td colspan="5">
											<textarea name="ACT_GRT" rows="3" style="width:98%;">${item.GRT }</textarea>
											</td>
										</tr>
										<tr>
											<th>의원경력</th>
											<td colspan="5">
											<textarea name="ACT_ASEMBY_CAREER" rows="3" style="width:98%;">${item.ASEMBY_CAREER }</textarea>
											</td>
										</tr>
										<tr>
											<th>수상경력</th>
											<td colspan="5">
											<textarea name="ACT_WNPZ_CAREER" rows="3" style="width:98%;">${item.WNPZ_CAREER }</textarea>
											</td>
										</tr>
										<tr>
											<th>등록일</th>
											<td colspan="2">${item.FRST_REGIST_DT }</td>
											<th>수정일</th>
											<td colspan="2">${item.LAST_UPDT_DT }</td>
										</tr>
										<tr>
										</tbody>
									</table>
									<br>
								</c:forEach>
								</td>
							</tr>
							<tr>
								<th>수집일자</th>
								<td colspan="2">${asmblyAsembyView.FRST_REGIST_DT}</td>
								<th>수정/삭제 ID</th>
								<td colspan="2">${asmblyAsembyView.LAST_UPDUSR_ID }</td>
							</tr>
							<tr>	
								<th>수정일자</th>
								<td colspan="2">${asmblyAsembyView.LAST_UPDT_DT}</td>
								<th>삭제일자</th>
								<td colspan="2">${asmblyAsembyView.DELETE_DT}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div><!--//cntArea-->
		</div><!--//movebox-->
		<p class="tr">
    		<a href="#none" class="btn btn btn-default" onclick="fn_prev();" style="color:#d84e2c">이전</a>
			<a href="#none" class="btn btn btn-default" onclick="fn_next();" style="color:#d84e2c">다음</a>
    	</p>
    	<p class="tr">
    		<a href="#;" onclick="return openServicePopup();"><button type="button" class="btn btn-default">서비스 화면보기</button></a>
    		<a href="#;" onclick="self.close();"><button type="button" class="btn btn-default">닫기</button></a>
			<a href="#;" onclick="return setUpdate();"><button type="button" class="btn btn-success">수정</button></a>
			<a href="#;" onclick="return setDelete();"><button type="button" class="btn btn-danger">삭제</button></a>
		</p>
	</div>
	<!-- /.panel-body -->

</form>
</div>

	<div class="spinner"></div>

	<div id="lamanActivityTamp" style="display:none;">
	<table class="table table-striped table-bordered lamanActivityTables">
	<colgroup>
		<col width="15%" />
		<col width="20%" />
		<col width="15%" />
		<col width="20%" />
		<col width="15%" />
		<col width="15%" />
	</colgroup>
	<tbody>
		<tr>
		<td colspan="6">
		<a href="#;" onclick="lamanActivityDel(this);"><button type="button" class="btn btn-danger">삭제</button></a>
		</td>
		</tr>
		<tr style="display:none;">
			<td colspan="5">
				<input type="hidden" name="ACT_CUD_CODE" value="C" />
			</td>
		</tr>
		<tr>
			<th><font style="color:red;">* 대수</font></th>
			<td colspan="5">
			<select name="ACT_RASMBLY_NUMPR" id="ACT_RASMBLY_NUMPR" onchange="return getEstAndMtgnmList(this,this.value);" class=" input-sm" style="width:120px;">
				<option value="">전체</option>
			</select>
			</td>
		</tr>
		<tr>
			<th>
				직위
				<a href="#;" onclick="fn_positionAdd(this,'${asmblyAsembyView.RASMBLY_ID}','');"><button type="button" class="btn btn-success">추가</button></a>
			</th>
			<td colspan="5">
			<input type="hidden" name="POSITION_INFO"/>
			</td>
		</tr>
		<tr>
			<th>* 활동여부</th>
			<td colspan="2">
			<select name="ACT_ACT_AT" onchange="return fn_noActDisabled(this);">
			<option value="">선택</option>
			<option value="Y">활동</option>
			<option value="N">미활동</option>
			</select>
			</td>
			<th>미활동사유</th>
			<td colspan="2">
			<select name="ACT_NOACT_RESN_STDCD">
			<option value="">선택</option>
			<c:forEach var="codeList" items="${noactResnStdcdList }">
			<option value="${codeList.CODE }" <c:if test="${codeList.CODE == item.NOACT_RESN_STDCD}">selected="selected"</c:if>>${codeList.CODE_NM }</option>
			</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<th>선거구</th>
			<td colspan="2">
			<select name="ACT_EST_ID">
			<option value="">지역구선택</option>
			</select>
			</td>
			<th>정당</th>
			<td colspan="2">
			<select name="ACT_PPRTY_CODE">
			<option value="">선택</option>
			<c:forEach var="codeList" items="${codePprtyList }">
			<option value="${codeList.PPRTY_CODE }" <c:if test="${codeList.PPRTY_CODE == item.PPRTY_CODE}">selected="selected"</c:if>>${codeList.PPRTY_NM }</option>
			</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<th>경력</th>
			<td colspan="5">
			<textarea name="ACT_CAREER_MATTER" rows="3" style="width:98%;"></textarea>
			</td>
		</tr>
		<tr>
			<th>학력</th>
			<td colspan="5">
			<textarea name="ACT_ACDMCR_MATTER" rows="3" style="width:98%;"></textarea>
			</td>
		</tr>
		<tr>
			<th>인사말</th>
			<td colspan="5">
			<textarea name="ACT_GRT" rows="3" style="width:98%;"></textarea>
			</td>
		</tr>
		<tr>
			<th>의원경력</th>
			<td colspan="5">
			<textarea name="ACT_ASEMBY_CAREER" rows="3" style="width:98%;"></textarea>
			</td>
		</tr>
		<tr>
			<th>수상경력</th>
			<td colspan="5">
			<textarea name="ACT_WNPZ_CAREER" rows="3" style="width:98%;"></textarea>
			</td>
		</tr>
		<tr>
		</tbody>
	</table>
	</div>

<form name="fvfrm" method="post">
	<%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchVar.jsp" %>
	<input type="hidden" name="isPrevNextPaging" id="isPrevNextPaging" value="N" ><!-- 페이징할 경우사용 -->
	<input type="hidden" name="prevNextGubun" id="prevNextGubun" value="N" >
	<input type="hidden" name="EndPage" id="EndPage" value="${EndPage}" >
	<input type="hidden" name="cnList" id="cnList" value="${cnList}" >
	<input type="hidden" name="sort" value="${mdmSearchVO.sort }" >
</form>
</body>
</html>
