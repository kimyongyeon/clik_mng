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
<script type="text/javaScript" language="javascript" defer="defer">

//의회선책
function getLoAsmTyCode() {
	//지역 초기화
	$("#schRegion option:eq(0)").attr("selected","selected");
	//의회명 초기화
	$("#schLoAsmCode option").remove();
	$("#schLoAsmCode").append('<option value="">전체</option>');
	
	//활동정보 대수 초기화
	$('table.lamanActivityTables select[name="ACT_RASMBLY_NUMPR"]').each(function(index,select){
		$(select).find('option').eq(0).attr("selected","selected");
	});
	//활동정보 선거구 초기화
	$('table.lamanActivityTables select[name="ACT_EST_ID"] option').remove();
	$('table.lamanActivityTables select[name="ACT_EST_ID"]').append('<option value="">지역구선택</option>');
	$('table.lamanActivityTables select[name="ACT_EST_ID"]').each(function(index,select){
		$(select).find('option').eq(0).attr("selected","selected");
	});
}

//지역 선택
function getRegion(schRegion) {
	if( schRegion == null || schRegion == "" ) {
		return false;
	}
	var schLoAsmTyCode = $("#schLoAsmTyCode").val();
	$.ajax({
		url:"/mdm/MdmEstbsList.do",
		cache:false,
		type:"post",
		data:{"schLoAsmTyCode":schLoAsmTyCode,"schRegion":schRegion},
		success: function(data, status) {
			
			//활동정보 대수 초기화
			$('table.lamanActivityTables select[name="ACT_RASMBLY_NUMPR"]').each(function(index,select){
				$(select).find('option').eq(0).attr("selected","selected");
			});
			//활동정보 선거구 초기화
			$('table.lamanActivityTables select[name="ACT_EST_ID"] option').remove();
			$('table.lamanActivityTables select[name="ACT_EST_ID"]').append('<option value="">지역구선택</option>');
			$('table.lamanActivityTables select[name="ACT_EST_ID"]').each(function(index,select){
				$(select).find('option').eq(0).attr("selected","selected");
			});
			
			$("#schLoAsmCode").html(data);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}

//의회별 대수 목록 조회
function getRasmblyNumpr(){
	$.ajax({
		url:"/mdm/MdmTnsrRasmblyList.do",
		cache:false,
		type:"post",
		data:{"rasmblyId":$('#schLoAsmCode').val()},
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
			data:{"RASMBLY_ID":$('#schLoAsmCode').val(),"RASMBLY_NUMPR":RASMBLY_NUMPR},
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
			data:{"rasmblyId":$('#schLoAsmCode').val(),"rasmblyNumpr":RASMBLY_NUMPR},
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

//목록보기
function list() {
	if(!confirm("등록을 취소 하시겠습니까?")){
		return false;
	}else{
		document.fvfrm.action = "/mdm/MdmAsmblyAsembyList.do";
		document.fvfrm.submit();
	}
}

//의원정보 등록
function regist() {
	
	var vildChk = false;
	var rasmbly_numpr_list = {};
	
	//필수값 체크 S
	if(trim($('#ASEMBY_NM').val()) == ""){
		alert("의원명은 필수 입력값 입니다.");
		$('#ASEMBY_NM').focus();
		return false;
	}
	if(trim($('#schLoAsmCode').val()) == ""){
		alert("의회명은 필수 입력값 입니다.");
		$('#schLoAsmCode').focus();
		return false;
	}
	
	//활동및경력 사항 필수값 체크 및 중복 체크
	var actLength = $('table.lamanActivityTables').length;
	$('table.lamanActivityTables').each(function(index,table){
		if(index+1 < actLength){ //활동정보 템플릿이 존재하여 갯수 체크
			var numpr = $(table).find("#ACT_RASMBLY_NUMPR").val();
			
			//대수 정보 체크
			if(numpr == ""){
				alert("대수는 필수 입력값 입니다.");
				$(table).find('select[name="ACT_RASMBLY_NUMPR"]').focus();
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
  			var targetHtSeStdcd = $(targetPosition).parent().find('select[name="ACT_POSITION_HT_SE_STDCD"]').val();
  			var targetMtgnm = $(targetPosition).parent().find('select[name="ACT_MTGNM_ID"]').val();
  			var targetPositionValue = $(targetPosition).val();
  			
  			if(!vildChk && targetHtSeStdcd == "" && (targetMtgnm != "" || targetPositionValue != "")){
  				alert("직위 - 전후반기 정보를 입력해 주세요.");
  				$(targetPosition).parent().find('select[name="ACT_POSITION_HT_SE_STDCD"]').eq(targetIndex).focus();
  				vildChk = true;
  				return false;
  			}
  			
  			if(!vildChk && targetMtgnm == "" && (targetHtSeStdcd != "" || targetPositionValue != "")){
  				alert("직위 - 회의명 정보를 입력해 주세요.");
  				$(targetPosition).parent().find('select[name="ACT_MTGNM_ID"]').eq(targetIndex).focus();
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
    var confirmExt;
    var thumbext = document.getElementById('_PHOTO_FILE_NM').value; 
    if(document.getElementById('_PHOTO_FILE_NM').value != "") {
        confirmExt = fn_confirmExt(thumbext);
        if(!confirmExt) {
        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
        	vildChk = true;
        	return;
        }
    }
    
  	//직위 중복 체크
  	$('table.lamanActivityTables').each(function(index,table){
  		$(table).find('select[name="ACT_POSITION_ID"]').each(function(targetIndex,targetPosition){
  			var targetHtSeStdcd = $(targetPosition).parent().find('select[name="ACT_POSITION_HT_SE_STDCD"]').val();
  			var targetMtgnm = $(targetPosition).parent().find('select[name="ACT_MTGNM_ID"]').val();
  			var targetPositionValue = $(targetPosition).val();
  			
  			if(targetHtSeStdcd != "" && targetMtgnm != "" && targetPositionValue != ""){
	  			$(table).eq(targetIndex).find('select[name="ACT_POSITION_ID"]').each(function(currIndex,currPosition){
	  				if(!vildChk
	  						&& targetIndex != currIndex
	  						&& targetHtSeStdcd == $(currPosition).parent().find('select[name="ACT_POSITION_HT_SE_STDCD"]').val()
	  						&& targetMtgnm == $(currPosition).parent().find('select[name="ACT_MTGNM_ID"]').val()){
	  	  				alert("중복되는 직위 정보가 존재합니다.");
	  	  				$(currPosition).parent().find('select[name="ACT_MTGNM_ID"]').focus();
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
	if(getByteLength($('#ASEMBY_NM').val()) > 50){
		alert("[의원명] 최대 50byte 까지 가능합니다.(한글:3byte,영문,숫자:1byte)");
		$('#ASEMBY_NM').focus();
		return false;
	}
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
		
		$(table).find('select[name="ACT_POSITION_ID"]').each(function(targetIndex,targetPosition){
  			var targetHtSeStdcd = $(targetPosition).parent().find('select[name="ACT_POSITION_HT_SE_STDCD"]').val();
  			var targetMtgnm = $(targetPosition).parent().find('select[name="ACT_MTGNM_ID"]').val();
  			var targetPositionValue = $(targetPosition).val();
  			
  			position_info += targetHtSeStdcd + "#" + targetMtgnm + "#" + targetPositionValue + "@";
  			
  		});
		
		$(table).find('input[name="POSITION_INFO"]').val(position_info);
  	});
	
	document.pgfrm.submit();
}

//문자열 BYTE 계산
function getByteLength(s,b,i,c){
    for(b=i=0;c=s.charCodeAt(i++);b+=c>>11?3:c>>7?2:1);
    return b;
}

/**
 * 파일 확장자 체크
 * 해당 확장자가 아닌 다른 확장자로 업로드 실행 시 필터
 */
function fn_confirmExt(str) {
	var result = true;

	str = str.slice(str.lastIndexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.

	if(str != "jpg" && str != "png" &&  str != "gif" &&  str != "bmp" && str != "jpeg"){ //확장자를 확인합니다.
		result = false;
	} 

	return result;
}

//도메일정보 설정
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

//활동및경력 정보 추가
function lamanActivityAdd(btn){
	var cloneTable = $('#lamanActivityTamp .lamanActivityTables').eq(0).clone();
	$('#lamanActivityList').append(cloneTable);
	
	if($('table.lamanActivityTables').length == 1)
		getRasmblyNumpr();
}

//활동및경력 정보 제거
function lamanActivityDel(btn){
	var numpr = $(btn).parent().parent().parent().parent().find('#ACT_RASMBLY_NUMPR').val();
	if(!confirm(numpr +"대 활동정보를 삭제하시겠습니까?")){
		return false;
	}else{
		$(btn).parent().parent().parent().parent().remove();
	}
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
function fn_positionAdd(btn){
	
	var rasmblyId = $('#schLoAsmCode').val();
	var rasmblyNumpr = $(btn).parent().parent().parent().find('select[name="ACT_RASMBLY_NUMPR"]').val();
	
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
	
	if(rasmblyId != '' && rasmblyNumpr != ''){
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
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

<div id="page-wrapper">
<form name="pgfrm" method="post" action="/mdm/MdmAsmblyAsembyRegist.do" enctype="multipart/form-data">
	<div class="row">
		<div class="col-lg-12">
        	<h1 class="page-header">메타데이터 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>

	<h2>메타데이터 등록 (지방의회 의원정보) </h2>
    <!-- /.row -->
	<div class="row">
	   	<p class="tr">
	   		<a href="#;" onclick="list();"><button type="button" class="btn btn-default">닫기</button></a>
			<a href="#;" onclick="regist();"><button type="button" class="btn btn-success">등록</button></a>
		</p>
		<p class="tr">
			<font style="color:red;">지방의회 의원정보를 관리자에서 직접 등록할 경우 지방의회 시스템상에서 생성되는 의원 ID가 동기화되지 않아 동일한 의원 정보가 중복될 수 있습니다.</font> 
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
								<th>수집여부</th>
								<td colspan="5">
									<input type="radio" id="COLCT_AT_Y" name="COLCT_AT" value="Y" /> 허용
									<input type="radio" id="COLCT_AT_N" name="COLCT_AT" value="N" checked="checked"/> 미허용
								</td>
							</tr>
		                	<tr>
								<th>의원사진</th>
								<td colspan="5" id="asemby_photo">
									<input name="_PHOTO_FILE_NM" id="_PHOTO_FILE_NM" class="input-sm ip " value="찾아보기"  type="file" style="width:90%; float:left;">
								</td>
							</tr>
							<tr>
								<th>* 의원명</th>
								<td colspan="2"><input type="text" id="ASEMBY_NM" name="ASEMBY_NM" value="" /></td>
								<th>생년월일</th>
								<td colspan="2"><input type="text" name="BRTHDY" id="BRTHDY" value="" maxLength="8"/></td>
							</tr>
							<tr>
								<th>* 의회명</th>
								<td colspan="5">
									의회선택 :
									<select name="schLoAsmTyCode" id="schLoAsmTyCode" onchange="return getLoAsmTyCode()" class=" input-sm" style="width:150px;">
										<option value="">전체</option>
										<option value="WAC" >광역의회</option>
										<option value="BAC" >기초의회</option>
									</select>
									지역선택 :
									<select name="schRegion" id="schRegion" onchange="return getRegion(this.value, '0')" class=" input-sm" style="width:150px;">
										<option value="">전체</option>
										<option value="002">서울특별시</option>
										<option value="051">부산광역시</option>
										<option value="053">대구광역시</option>
										<option value="032">인천광역시</option>
										<option value="062">광주광역시</option>
										<option value="042">대전광역시</option>
										<option value="052">울산광역시</option>
										<option value="044">세종특별자치시</option>
										<option value="031">경기도</option>
										<option value="033">강원도</option>
										<option value="043">충청북도</option>
										<option value="041">충청남도</option>
										<option value="063">전라북도</option>
										<option value="061">전라남도</option>
										<option value="054">경상북도</option>
										<option value="055">경상남도</option>
										<option value="064">제주특별자치도</option>
										<option value="001">중앙기관</option>
									</select>&nbsp;&nbsp;
									의회명 : 
									<select name="schLoAsmCode" id="schLoAsmCode" onchange="return getRasmblyNumpr();" class=" input-sm" style="width:150px;">
										<option value="">전체</option>
									</select>&nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<th>주소</th>
								<td colspan="5">
									지번&nbsp;&nbsp;&nbsp;주소 : <input type="text" name="ADRES" id="ADRES" style="width:80%;" value="" /><br>
									도로명주소 : <input type="text" name="RDNMADR" id="RDNMADR" style="width:80%;" value="" />
								</td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td colspan="5">
									자택 : <input type="text" name="OWNHOM_TLPHON" id="OWNHOM_TLPHON" value="" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									휴대폰 : 
									<select name="phone1" class=" input-sm"> 
										<option value="010">010</option>
										<option value="011">011</option>
										<option value="016">016</option>
										<option value="017">017</option>
										<option value="018">018</option>
										<option value="019">019</option>
									</select>
									<input type="text" class="input-sm ip" name="phone2" id="phone2" value="" onkeyup="checkNumber(event)" title="휴대폰 중간자리 입력창" maxlength="4" style="width:100px;"/> - 
									<input type="text" class="input-sm ip" name="phone3" id="phone3" value="" onkeyup="checkNumber(event)" title="휴대폰 끝자리 입력창" maxlength="4" style="width:100px;" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
									팩스 : <input type="text" class="input-sm ip" name="FAX" id="FAX" value="" onkeyup="checkNumber(event)" title="팩스" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									사무실 : <input type="text" name="OFFM_TLPHON" id="OFFM_TLPHON" value="" />
								</td>
							</tr>
							<tr>
								<th>이메일</th>
								<td colspan="5">
									<input name="email1" id="email1" value="" class="input-sm ip" type="text" style="width:20%;"> @ 
									<input name="email2" id="email2" value="" class="input-sm ip" type="text" style="width:20%;">
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
								<td colspan="5"><input type="text" name="HMPG" id="HMPG" style="width:98%;" value="" /></td>
							</tr>
							<tr>
								<th>트위터</th>
								<td colspan="2"><input type="text" name="TWITTER" id="TWITTER" value="" /></td>
								<th>페이스북</th>
								<td colspan="2"><input type="text" name="FACEBOOK" id="FACEBOOK" value="" /></td>
							</tr>
							<tr>
								<th>활동 및 경력 <a href="#;" onclick="lamanActivityAdd(this);"><button type="button" class="btn btn-success">추가</button></a></th>
								<td colspan="5" id="lamanActivityList">
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div><!--//cntArea-->
		</div><!--//movebox-->
		<p class="tr">
			<a href="#;" onclick="list();"><button type="button" class="btn btn-default">닫기</button></a>
			<a href="#;" onclick="regist();"><button type="button" class="btn btn-success">등록</button></a>
		</p>
		<p class="tr">
			<font style="color:red;">지방의회 의원정보를 관리자에서 직접 등록할 경우 지방의회 시스템상에서 생성되는 의원 ID가 동기화되지 않아 동일한 의원 정보가 중복될 수 있습니다.</font> 
		</p>
	</div>
	<!-- /.panel-body -->
</form>

<form name="fvfrm" method="post">
	<%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchVar.jsp" %>
</form>

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
			<a href="#;" onclick="fn_positionAdd(this);"><button type="button" class="btn btn-success">추가</button></a>
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
</div>
</body>
</html>
