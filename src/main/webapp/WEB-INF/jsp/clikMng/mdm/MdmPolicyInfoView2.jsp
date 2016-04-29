<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<html lang="ko">
<head>
<title>메타데이터관리</title>

<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<link href="/css/clikmng/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/clikmng/jquery-ui.min.js"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<!-- Naver.com. SmartEditor 적용  -->
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<validator:javascript formName="contentsForm" staticJavascript="false" xhtml="true" cdata="false"/>

<style type="text/css">
 .myButton {
    padding: .2em 1em;
    font-size: 1em;
}
#myDiv {
	position:absolute; left:180px; top:900px;
    background-color:#fff;
    border:3px solid #b9b9b9;
    display:block;
    text-align:justify;
}
#myDiv p {
    margin: 15px;
    font-size: 0.917em;
}

</style>
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">


var currCnId = '${mdmSearchVO.cnId}';
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
			if(cnList[i-1] != undefined){
// 				url = "/mdm/MdmPolicyInfoMetaDataView1.do?cnId="+cnList[i-1];
				frm.action = "/mdm/MdmPolicyInfoMetaDataView1.do";
				frm.cnId.value = cnList[i-1]; 
			}
			$('#isPrevNextPaging').val('N');
			break;
		}
	}
	
	if(${mdmSearchVO.pageNum} > 1 && i == 0)
	{
		frm.pageNum.value = ${mdmSearchVO.pageNum} - 1;
		frm.action = "/mdm/MdmPolicyInfoMetaDataView1.do";
		frm.cnId.value = "";
		$('#prevNextGubun').val('prev');
		$('#isPrevNextPaging').val('Y');
	}
	else if(${mdmSearchVO.pageNum} == 1 && i == 0)
	{
		$(".spinner").hide();
		alert("처음 입니다.");
		return false;
	}
	
	
// 	var ret_open = window.open('about:blank','VIEWER_POP','width=1366,height=768,scrollbars=yes,resizable=yes');
	
// 	if(ret_open == null) {
// 		$(".spinner").hide();
// 		alert("팝업차단을 해제하세요!");
// 		return false;
// 	}
	
// 	frm.title.value = '메타데이터관리';
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
			frm.action = "/mdm/MdmPolicyInfoMetaDataView1.do";
			frm.cnId.value = cnList[i+1];
			$('#isPrevNextPaging').val('N');
			break;
		}
	}
	
	if(cnList.length == (i+1) && ${EndPage} > (${mdmSearchVO.pageNum} + 1))
	{
		frm.pageNum.value = ${mdmSearchVO.pageNum} + 1;
// 		url = "/mdm/MdmPolicyInfoMetaDataView1.do?cnId=";
		frm.action = "/mdm/MdmPolicyInfoMetaDataView1.do";
		frm.cnId.value = "";
		$('#prevNextGubun').val('next');
		$('#isPrevNextPaging').val('Y');
	}
	else if(cnList.length == (i+1) && ${EndPage} == ${mdmSearchVO.pageNum})
	{
		$(".spinner").hide();
		alert("마지막 입니다.");
		return false;
	}
	
// 	var ret_open = window.open('about:blank','VIEWER_POP','width=1366,height=768,scrollbars=yes,resizable=yes');
	
// 	if(ret_open == null) {
// 		$(".spinner").hide();
// 		alert("팝업차단을 해제하세요!");
// 		return false;
// 	}
	
// 	frm.title.value = '메타데이터관리';
// 	frm.target='VIEWER_POP';
	frm.submit();
// 	frm.target = "_self";
}

function fn_leftArea() {
	var fullWidth1 = 100;
	var fullWidth = 39;
	var basicWidth1 = 100;
	var basicWidth = 0;
	if($("#leftAreaBtn").css("display") == "none"){
		$("#leftArea").show("swing");
		$("#leftArea").animate({"width": fullWidth1+"%"}, 600 );
		$("#cntArea").animate({"width": fullWidth+"%"}, 600 );
		$("#leftAreaBtn").show();
	}
	else{
		$("#leftAreaBtn").hide();
		$("#leftArea").animate({"width": basicWidth1+"%"}, 400 );
		$("#cntArea").animate({"width": basicWidth+"%"}, 400 );
		$("#leftArea").show("swing");
	}
}
 
function setUpdate() {

	//smartEditor textarea 적용
	oEditors.getById["EXTRACTHTML"].exec("UPDATE_CONTENTS_FIELD", []); 
	
	document.pgfrm.EXTRACTHTML.value = encodeURIComponent(htmlEntityEnc(document.pgfrm.EXTRACTHTML.value));
	document.pgfrm.TITLE.value = encodeURIComponent(htmlEntityEnc($('#TITLE_TEMP').val())).replace(/[!'()*]/g, escape);
	document.pgfrm.WRITER.value = encodeURIComponent(htmlEntityEnc($('#WRITER_TEMP').val())).replace(/[!'()*]/g, escape);
	document.pgfrm.CONTENT.value = encodeURIComponent(htmlEntityEnc($('#CONTENT_TEMP').val())).replace(/[!'()*]/g, escape);
	document.pgfrm.CDATE.value = encodeURIComponent(htmlEntityEnc($('#CDATE_TEMP').val())).replace(/[!'()*]/g, escape);
	
	if( $("#outDocTypeList").val() == "" ) {
		alert("자료유형을 선택하십시오.");
		return false;
	}
	if( document.pgfrm.CDATE.value == "" ) {
		alert("발행일을 입력하십시오.");
		return false;
	}
	
	// 파일 확장자 체크
    var confirmExt;
    var thumbext = document.getElementById('FILE_1').value; 
    if(document.getElementById('FILE_1').value != "") {
        confirmExt = fn_confirmExt(thumbext);
        if(!confirmExt) {
        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
        	return false;
        }
    }    
    
    
	if( !confirm("수정하시겠습니까?") ) {
		return false;
	}
	
// 	if(!attachFileSizeCheck()){
// 		return false;
// 	}
	
	$(".spinner").show();
	
	pgfrm.action="/mdm/MdmPolicyInfoUpdate.do";
	document.pgfrm.submit();
}

/**
 * 파일 확장자 체크
 * 해당 확장자가 아닌 다른 확장자로 업로드 실행 시 필터
 */

function fn_confirmExt(str) {
	str = str.slice(str.lastIndexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.

	var result = true;

	if(str != "jpg" && str != "png" &&  str != "gif" &&  str != "bmp"
		&& str != "alz" && str != "doc" && str != "docx" && str != "hwp" && str != "jpeg" 
		&& str != "pdf" && str != "ppt" && str != "pptx" && str != "pptx" && str != "txt"
		&& str != "xls" && str != "xlsx" && str != "zip" ){ //확장자를 확인합니다.

		//alert('썸네일은 이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
		result = false;
		return result;

	} else {
		return result;
	}
}

function htmlEntityEnc(str){
    if(str == "" || str == null){
        return str;
    }
    else{
    	str = str.split("&").join("&amp;");
    	str = str.split("#").join("&#35;");
    	str = str.split("<").join("&lt;");
    	str = str.split(">").join("&gt;");
    	str = str.split("\"").join("&quot;");
    	str = str.split("\\").join("&#39;");
    	str = str.split("%").join("&#37;");
    	str = str.split("(").join("&#40;");
    	str = str.split(")").join("&#41;");
    	str = str.split("+").join("&#43;");
    	str = str.split("/").join("&#47;");
    	str = str.split(".").join("&#46;");
    	
        return str;
        //return str.replace("&", "&amp;").replace("#", "&#35;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace('\\', "&#39;").replace('%', "&#37;").replace('(', "&#40;").replace(')', "&#41;").replace('+', "&#43;").replace('/', "&#47;").replace('.', "&#46;");
    }
}

//변환파일 뷰
function fileview(disfile) {
	$('#disfile').val(disfile);
	fvfrm.action = "/mdm/MdmPolicyInfoMetaDataView2.do";
	document.fvfrm.submit();
}

//첨부파일 다운로드
function setDownload(DOWNID,GUBUN) {
	document.location.href="/mdm/MdmPolicyInfoDownLoad.do?DOWNID="+DOWNID+"&GUBUN="+GUBUN;
}

//게시물 삭제
function setDelete() {
	if( !confirm("게시물을 삭제하시겠습니까?") ) {
		return false;
	}
	
	//smartEditor textarea 적용
	oEditors.getById["EXTRACTHTML"].exec("UPDATE_CONTENTS_FIELD", []); 
	
	document.pgfrm.EXTRACTHTML.value = encodeURIComponent(htmlEntityEnc(document.pgfrm.EXTRACTHTML.value));
	
	pgfrm.action = "/mdm/MdmPolicyInfoDelete.do";
	document.pgfrm.submit();
}

//첨부파일 삭제
function setFileDelete(DOWNID) {
// 	if( !confirm("수집,변환 파일이 모두 삭제 됩니다. \n삭제하시겠습니까?") ) {
// 		return false;
// 	}
	if( !confirm("삭제하시겠습니까?") ) {
		return false;
	}
	$.ajax({
		url:"/mdm/MdmPolicyInfoFileDelete.do",
		cache:false,
		type:"post",
		data:{"DOWNID":DOWNID},
		success: function(data, status) {
			var target = $("#filedel"+DOWNID).parent().parent().parent().parent();
// 			var target_parent = $("#filedel"+DOWNID).parent().parent().parent().parent().parent();
			
			$(target).remove();
			
// 			if(trim($(target_parent).html()) == ""){
// 				$(target_parent).remove();
// 			}
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}

//자료 중복 레이어팝업
function getSideMenu(MODE, DUPLICATION, OUTBBS_CN) {
	$("#slideId").html("");
	var url = "";

	if( MODE == "FILE" ) {
		url = "/mdm/MdmPolicyInfoFileListCmmn.do";
	}
	else {
		url = "/mdm/MdmPolicyInfoListCmmn.do";
	}
	$.ajax({
		url:url,
		cache:false,
		type:"get",
		data:{"DUPLICATION":DUPLICATION
			,"OUTBBS_CN":OUTBBS_CN},
		success: function(data, status) {
			$("#slideId").html(data);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
}
function getSlideShowHidden() {
	// Set the effect type
	var effect = 'slide';
	// Set the options for the effect type chosen
	var options = { direction: 'right' }; //{ direction: $('.mySelect').val() };
	// Set the duration (default: 400 milliseconds)
	var duration = 500;
	$('#myDiv').toggle(effect, options, duration);
}
function getSlideCheckAll() {
	var FLength = document.getElementsByName("chkid").length;
	var checkAllValue = document.getElementById('slideCheckAll').checked;

	//undefined
	if( FLength == 1 ) {
		document.getElementsByName("chkid")[0].checked = checkAllValue;
	}
	else {
		for(var i=0; i < FLength; i++) {
			document.getElementsByName("chkid")[i].checked = checkAllValue;
		}
	}
}
function getRealOffsetTop(o) { return o ? o.offsetTop + getRealOffsetTop(o.offsetParent) : 0; } 
function getRealOffsetLeft(o) { return o ? o.offsetLeft + getRealOffsetLeft(o.offsetParent) : 0; }
//- 레이어팝업 관련

//첨부파일 추가
function addAttFile(el){
	var name = "FILE_"+(Number($(el).parent().parent().find('input[type="file"]').length) + 1);
	var file = "";
	file += '<p><input name="'+name+'" class="input-sm ip " value="찾아보기"  type="file" style="width:80%; float:left; margin-right:10px; margin-top:4px;">';
	file += '<a href="#" onclick="return delAttFile(\''+name+'\');"><button type="button" class="btn btn-danger">삭제</button></a></p>';
	$(el).parent().parent().append(file);
	
}

//첨부파일 제거
function delAttFile(name){
	if( !confirm("첨부파일을 삭제 하시겠습니까?") ) {
		return false;
	}
	
	$('#leftArea table input[name="'+name+'"]').parent().remove();
}

//첨부파일 업로드 가능 사이즈 체크
var maxFileSize = ${maxFileSize};
var maxSizeMB = Math.round(maxFileSize / 1024 / 1024);
function attachFileSizeCheck(){
	var result = true;
	$('input[type="file"]').each(function(index,file){
		if(file.files[0] == null)
			return;
		
		if(result && file.files[0].size > maxFileSize){
			alert("[ " + file.value.substr(file.value.lastIndexOf('\\') + 1) + " ] 업로드 가능한 사이즈 초과\n최대 :  "+maxSizeMB+"MB");
			result = false;
		}
	});
	return result;
}


$(window).load(function(){
	
	$(".myButton").click(function () {
		
		//기존에 열려 있는 팝업이 있을 경우 닫기 위해서 toggle 호출
		if($('#myDiv').css('display') == "block")
			$('#myDiv').toggle(effect, options, duration);
		
		var top = getRealOffsetTop(this) - 7;
		var left = getRealOffsetLeft(this) + 100;
		
		$('#myDiv').css('top',top);
		$('#myDiv').css('left',left);
		
		// Set the effect type
		var effect = 'slide';
		// Set the options for the effect type chosen
		var options = { direction: 'right' }; //{ direction: $('.mySelect').val() };
		// Set the duration (default: 400 milliseconds)
		var duration = 500;
		
		$('#myDiv').toggle(effect, options, duration);
	});
	
	
});

//서비스시스템 상새화면을 호출한다
function openServicePopup(){
	window.open('http://clik.nanet.go.kr/potal/search/searchView.do?collection=policyinfo&DOCID=${policyInfoVO.OUTBBS_CN}','_blank');
}
</script>
</head>
<body class="body" style="background:none;">
<c:choose>
<c:when test="${fn:length(policyInfoFileList) >= 0 && disfile != null && disfile != ''}"> 
<div id="page-wrapper" style="margin-left:0; width:60%; float:left;">
</c:when>
<c:otherwise>
<div id="page-wrapper" style="margin-left:0; width:100%; float:left;">
</c:otherwise>
</c:choose>
	<form name="pgfrm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
		<input type="hidden" name="EXTID" value="${policyInfoVO.EXTID}" />
		<input type="hidden" name="OUTBBS_CN" value="${policyInfoVO.OUTBBS_CN}" />
		<input type="hidden" name="cnId" value="${policyInfoVO.OUTBBS_CN}" />
		<input type="hidden" name="REGION" id="REGION" value="${policyInfoVO.REGION}" />
		<input type="hidden" name="EndPage" id="EndPage" value="${EndPage}" >
		<input type="hidden" name="cnList" id="cnList" value="${cnList}" >
		<input type="hidden" name="sort" value="${mdmSearchVO.sort }" >
		
	<div class="row">
    	<div class="col-lg-12">
        	<h1 class="page-header">메타데이터 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
	<h2>메타데이터 목록 (지방정책정보) </h2>
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
					<table class="table table-striped table-bordered " style="table-layout:fixed;">
						<colgroup>
							<col width="15%" />
							<col width="10%" />
							<col width="15%" />
							<col width="10%" />
							<col width="15%" />
							<col width="10%" />
							<col width="20%" />
						 </colgroup>
							
						<tbody>
							<tr>	
								<th>제  목</th>
								<td colspan="6" style="word-wrap:break-word;">
									<input id="TITLE_TEMP" class="input-sm ip" type="text" style="width:100%" value="${fn:replace(policyInfoVO.TITLE, '"', '&quot;')}" maxlength="250"/>
									<input name="TITLE" class="input-sm ip" type="text" style="display:none;" /><br>
									<a href="${policyInfoVO.URL}" title="새창열기" target="_blank" >${policyInfoVO.URL}</a>
								</td>
							</tr>
							<tr>	
								<th>제어번호</th>
								<td colspan="6">${policyInfoVO.OUTBBS_CN}</td>
							</tr>
							<tr>	
								<th>자료유형</th>
								<th>유형1</th>
								<td colspan="2">
									지방정책정보
								</td>
								<th>유형2</th>
								<td colspan="2">
									<select name="DOCTYPE" id="outDocTypeList" class=" input-sm" style="width:180px;">
										<option value="">-- 자료유형 선택 --</option>
										<option value="100" <c:if test="${policyInfoVO.DOCTYPE == '100'}">selected=selected</c:if>>홍보/보도/소식</option>
										<option value="200" <c:if test="${policyInfoVO.DOCTYPE == '200'}">selected=selected</c:if>>정책/업무자료</option>
										<option value="300" <c:if test="${policyInfoVO.DOCTYPE == '300'}">selected=selected</c:if>>연구자료</option>
										<option value="400" <c:if test="${policyInfoVO.DOCTYPE == '400'}">selected=selected</c:if>>의정활동자료</option>
										<option value="500" <c:if test="${policyInfoVO.DOCTYPE == '500'}">selected=selected</c:if>>통계</option>
										<option value="600" <c:if test="${policyInfoVO.DOCTYPE == '600'}">selected=selected</c:if>>정책매뉴얼</option>
										<option value="700" <c:if test="${policyInfoVO.DOCTYPE == '700'}">selected=selected</c:if>>출장보고서</option>
										<option value="800" <c:if test="${policyInfoVO.DOCTYPE == '800'}">selected=selected</c:if>>세미나/공청회</option>
										<option value="900" <c:if test="${policyInfoVO.DOCTYPE == '900'}">selected=selected</c:if>>감사자료</option>
										<option value="999" <c:if test="${policyInfoVO.DOCTYPE == '999'}">selected=selected</c:if>>기타</option>
										<option value="140" <c:if test="${policyInfoVO.DOCTYPE == '140'}">selected=selected</c:if>>교육&메뉴얼</option>
									</select>
								</td>
							</tr>
							<tr>	
								<th>기관유형</th>
								<th>유형1</th>
								<td style="vertical-align: middle;">
									${policyInfoVO.ORG_1NM} &nbsp;&nbsp;
 								</td>
 								<th>유형2</th>
								<td style="vertical-align: middle;">
									${policyInfoVO.ORG_2NM} &nbsp;&nbsp;
 								</td>
 								<th>유형3</th>
								<td style="vertical-align: middle;">	
									${policyInfoVO.ORG_3NM}
								</td>
							</tr>
							<tr>
								<th>기관명</th>
								<td colspan="6">
									<a href="${policyInfoVO.SITEURL}" title="새창열기" target="_blank">${policyInfoVO.SITENM}</a>
								</td>
							</tr>
							<tr>
								<th>게시판명</th>
								<td colspan="6">
									<a href="${policyInfoVO.SEEDURL}" title="새창열기" target="_blank">${policyInfoVO.SEEDNM}</a>
								</td>
							</tr>
							<tr>	
								<th>발행일<br>(게시일자)</th>
								<td colspan="6">
									<input id="CDATE_TEMP" class="input-sm ip" type="text" style="width:30%" value="${policyInfoVO.CDATE}" onkeydown="javascript:checkNumber(event);" maxlength="10"/>
									<input name="CDATE" class="input-sm ip" type="text" style="display:none;" title="작성일" />
								</td>
							</tr>
							<tr>	
								<th>작성자</th>
								<td colspan="6">
									<input id="WRITER_TEMP" class="input-sm ip" type="text" style="width:30%" value="${policyInfoVO.WRITER}" maxlength="64" />
									<input name="WRITER" class="input-sm ip" type="text" style="display:none;" title="작성자" />
								</td>
							</tr>	
							<tr>	
								<th>내  용</th>
								<td colspan="6">
									<textarea name="EXTRACTHTML" id="EXTRACTHTML" cols="100" rows="10" class="ip" style="width:100%; height:400px; min-width:95%"><c:out value="${policyInfoVO.EXTRACTHTML}" escapeXml="false" /></textarea>
								</td>
							</tr>
							<tr>	
								<th>요  약</th>
								<td colspan="6">
									<textarea id="CONTENT_TEMP" cols="100" rows="10" class="ip" style="width:100%; height:400px; min-width:95%"><c:out value="${policyInfoVO.CONTENT}" escapeXml="false" /></textarea>
									<textarea name="CONTENT" cols="100" rows="10" class="ip" style="display:none;"></textarea>
								</td>
							</tr>
							
							<c:set var="isAttachFile" value="false" />
							<c:set var="isImageFile" value="false" />
							<c:forEach var="cList" items="${policyInfoFileList}" varStatus="status">
							<c:if test="${cList.DOC_CNVR_STTU_CODE != '5'}">
							<c:set var="isAttachFile" value="true" />
							</c:if>
							<c:if test="${cList.DOC_CNVR_STTU_CODE == '5'}">
							<c:set var="isImageFile" value="true" />
							</c:if>
							</c:forEach>
							<tr>
								<c:choose>
								<c:when test="${fn:length(policyInfoFileList) > 0 && isAttachFile}">	
								<th rowspan="2">첨부<br>파일</th>
								</c:when>
								<c:otherwise>
								<th>첨부<br>파일</th>
								</c:otherwise>
								</c:choose>
								<td colspan="6">
									<p>
									<button type="button" class="btn btn-info" onclick="addAttFile(this);">파일추가</button>
									<input name="FILE_1" id="FILE_1" class="input-sm ip " value="찾아보기"  type="file" style="width:80%; float:left; margin-right:10px;">
									</p> 
 								</td>
							</tr>
							<c:if test="${fn:length(policyInfoFileList) > 0 && isAttachFile}">
							<tr>
								<td colspan="6">
									<c:forEach var="cList" items="${policyInfoFileList}" varStatus="status">
									<c:set var="afile" value="${fn:split(cList.DOWNPATH, '/')}" />
									<table class="table table-striped table-bordered " style="table-layout:fixed;">
										<c:if test="${cList.DOC_CNVR_STTU_CODE != '5' }">
										<tr>
<!-- 											<th rowspan="2" style="width:30px;vertical-align: middle;"> -->
<%-- 											<c:choose> --%>
<%-- 											<c:when test="${cList.DOC_CNVR_STTU_CODE == '0'}"><font style="color:#428bca;">변환대기</font></c:when> --%>
<%-- 											<c:when test="${cList.DOC_CNVR_STTU_CODE == '1'}"><font style="color:#428bca;">변환성공</font></c:when> --%>
<%-- 											<c:when test="${cList.DOC_CNVR_STTU_CODE == '2'}"><font style="color:red;">변환실패</font></c:when> --%>
<%-- 											<c:when test="${cList.DOC_CNVR_STTU_CODE == '3'}"><font style="color:#428bca;">변환성공</font></c:when> --%>
<%-- 											<c:when test="${cList.DOC_CNVR_STTU_CODE == '4'}"><font style="color:#428bca;">변환중</font></c:when> --%>
<%-- 											</c:choose> --%>
<!-- 											</th> -->
<!-- 											<th style="width:30px;vertical-align: middle;">수집</th> -->
											<td style="vertical-align: middle;"><span id="filemsg${cList.DOWNID}">${afile[fn:length(afile)-1]}</span></td>
											<td style="width:95px;vertical-align: middle;"><button type="button" class="btn btn-danger" onclick="setDownload('${cList.DOWNID}');">다운로드</button></td>
											<td rowspan="2" style="width:65px;vertical-align: middle;"><button type="button" id="filedel${cList.DOWNID}" class="btn btn-danger" onclick="setFileDelete('${cList.DOWNID}');">삭제</button></td>
										</tr>
<!-- 										<tr> -->
<!-- 											<th>변환</th> -->
<!-- 											<td style="vertical-align: middle;"> -->
<%-- 												<c:choose> --%>
<%-- 													<c:when test="${disfile == cList.OUTBBS_PDF_FILE_NM}"> --%>
<%-- 														<strong><span id="filemsg${cList.DOWNID}">${cList.OUTBBS_PDF_FILE_NM}</span></strong> --%>
<%-- 													</c:when> --%>
<%-- 													<c:otherwise> --%>
<%-- 														<a href="#;" onclick="fileview('${cList.DOC_CNVR_PDF_PATH}');"><span id="filemsg${cList.DOWNID}">${cList.OUTBBS_PDF_FILE_NM}</span></a> --%>
<%-- 													</c:otherwise> --%>
<%-- 												</c:choose> --%>
<!-- 											</td> -->
<!-- 											<td style="vertical-align: middle;"> -->
<%-- 												<c:if test="${cList.OUTBBS_PDF_FILE_NM != null && cList.OUTBBS_PDF_FILE_NM != ''}"> --%>
<%-- 												<button type="button" class="btn btn-danger" onclick="setDownload('${cList.DOWNID}','CNVR');">다운로드</button> --%>
<%-- 												</c:if> --%>
<!-- 											</td> -->
<!-- 										</tr> -->
										</c:if>
									</table>
									</c:forEach>
								</td>
							</tr>
							</c:if>
							<c:if test="${fn:length(policyInfoFileList) > 0 && isImageFile}">
							<tr>
								<th>본문이미지</th>
								<td colspan="6">
									<c:forEach var="cList" items="${policyInfoFileList}" varStatus="status">
										<c:set var="afile" value="${fn:split(cList.DOWNPATH, '/')}" />
										<c:choose>
											<c:when test="${cList.DOC_CNVR_STTU_CODE == '5'}">
												<div id="filedel${cList.DOWNID}" style="padding-bottom:8px;">
												<button type="button" class="btn btn-danger" onclick="setDownload('${cList.DOWNID}');">다운로드</button>
												&nbsp;<span id="filemsg${cList.DOWNID}">${afile[fn:length(afile)-1]}</span>
												</div>
											</c:when>
										</c:choose>
									</c:forEach>
								</td>
							</tr>
							</c:if>
							<tr>	
								<th>중복</th>
								<td colspan="2">
									<c:choose>
										<c:when test="${policyInfoVO.DUPCNT > 1}"><a href="#;" class="myButton" onclick="getSideMenu('DPLCT', '${policyInfoVO.DUPLICATION}', '${policyInfoVO.OUTBBS_CN}');">중복(${policyInfoVO.DUPCNT})</a></c:when>
										<c:otherwise>N</c:otherwise>
									</c:choose>
								</td> 
								<th>게시</th>
								<td colspan="3">${policyInfoVO.ISVIEW }</td>
							</tr>
							<tr>	
								<th>수집일자</th>
								<td colspan="2">${policyInfoVO.REGDATE }</td>
								<th>수정/삭제 ID</th>
								<td colspan="3">${policyInfoVO.LAST_UPDUSR_ID }</td>
							</tr>
							<tr>	
								<th>수정일자</th>
								<td colspan="2">${policyInfoVO.UPDT }</td>
								<th>삭제일자</th>
								<td colspan="3">${policyInfoVO.DELDT }</td>
							</tr>
						</tbody>
					</table>
				</div><!-- /.table-responsive -->
			</div><!-- //movebox -->
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
	</div>
	</form>
</div>
	
	<form name="fvfrm" method="post">
		<%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchVar.jsp" %>
		<input type="hidden" name="isPrevNextPaging" id="isPrevNextPaging" value="N" ><!-- 페이징할 경우사용 -->
		<input type="hidden" name="prevNextGubun" id="prevNextGubun" value="N" >
		<input type="hidden" name="EndPage" id="EndPage" value="${EndPage}" >
		<input type="hidden" name="cnList" id="cnList" value="${cnList}" >
		<input type="hidden" name="sort" value="${mdmSearchVO.sort }" >
	</form>
	

	<c:if test="${fn:length(policyInfoFileList) >= 0 && disfile != null && disfile != ''}">
		<div id="cntArea" class="pdf" style="float:left; width:39%; margin-top:116px; min-width:490px;">
			<div id="leftAreaBtn" style="display:none">
				<object width="100%" height="1000px" type="application/pdf" data="/unidocs/fileTemp/${disfile}">
					<param name="src" value="/unidocs/fileTemp/${disfile}" />
				</object>
			</div>
		</div><!-- //cntArea -->
	</c:if>

	<c:if test="${fn:length(policyInfoFileList) >= 0 && disfile != null && disfile != ''}">
		<script type="text/javascript">
			//fn_leftArea();
			var fullWidth1 = 100;
			var fullWidth = 39;
			var basicWidth1 = 100;
			var basicWidth = 0;
			if($("#leftAreaBtn").css("display") == "none"){
				$("#leftArea").show("swing");
				$("#leftArea").animate({"width": fullWidth1+"%"}, 600 );
				$("#cntArea").animate({"width": fullWidth+"%"}, 600 );
				$("#leftAreaBtn").show();
			}
			else{
				$("#leftAreaBtn").hide();
				$("#leftArea").animate({"width": basicWidth1+"%"}, 400 );
				$("#cntArea").animate({"width": basicWidth+"%"}, 400 );
				$("#leftArea").show("swing");
			}
		</script>
	</c:if>

	<div id="myDiv" style="display:none; height:400px; top:900px;">
		<div style="padding:5px; float:auto; background:#efefef;  text-align:center; margin-bottom:5px;"><a href="#" onclick="getSlideShowHidden();"><img src="/images/clikmng/mdm/btn_close.gif"></a></div>
		<div id="slideId" style="clear:both; height:330px; width:490px; overflow:auto;"></div>
	</div>

<script type="text/javaScript" language="javascript">
var oEditors = [];

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "EXTRACTHTML",
	sSkinURI: "/smarteditor/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			
		}
	}, //boolean
	fOnAppLoad : function(){
		
	},
	fCreator: "createSEditor2"
});

</script>

<div class="spinner"></div>

</body>
</html>
