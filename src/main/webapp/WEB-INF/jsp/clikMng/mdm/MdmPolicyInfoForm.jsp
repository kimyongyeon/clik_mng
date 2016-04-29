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

<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

//기관명 조회
function getOutSiteList(org3, gubun) {
	
	var org1 = $("#schOrgCodeStep1 option:selected").val();
	var org2 = $("#schOrgCodeStep2 option:selected").val();

	if( org1 == null || org1 == "" || org2 == null || org2 == "" ) {
		return false;
	}
	
	if(gubun == 'i') {
		$.ajax({
			url:"/mdm/MdmOutSiteList2.do",
			cache:false,
			type:"post",
			data:{"ORG_1":org1, "ORG_2":org2, "ORG_3":org3},
			success: function(data, status) {
				$("#outSiteList").html(data);
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
	} else {
		$.ajax({
			url:"/mdm/MdmOutSiteList2.do",
			cache:false,
			type:"post",
			data:{"ORG_1":org1, "ORG_2":org2, "ORG_3":org3, 'SITEID':gubun},
			success: function(data, status) {
				$("#outSiteList").html(data);
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
	}

	return false;
} 

//게시판 정보를 조회
function getOutSeedList(SITEID, gubun) {
	//var schRegion = $("#REGION").val(); 
	
	if( SITEID == null || SITEID == "" ) {
		$('#outSeedList option').remove();
		$('#outSeedList').append("<option value=''>-- 게시판 선택 --</option>");
		return false;
	}

	if(gubun == 'i') {
		$.ajax({
			url:"/mdm/MdmOutSeedList.do",
			cache:false,
			type:"post",
			//data:{"SITEID":SITEID, "REGION":schRegion},
			data:{"SITEID":SITEID},

			success: function(data, status) {
				$("#outSeedList").html(data);
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
			//data:{"SITEID":SITEID, "REGION":schRegion},
			data:{"SITEID":SITEID, "SEEDID":gubun},

			success: function(data, status) {
				$("#outSeedList").html(data);
			},
			error: function (data, status, e) {
				alert(e);
			}
		});		
	}

	return false;
}

//기관유형2를 조회한다
function getOrgCodeStep2List(schOrgCodeStep2) {
	if( schOrgCodeStep2 == null || schOrgCodeStep2 == "" ) {
		return false;
	}
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
	
	$('#schOrgCodeStep3 option').remove();
	$('#outSiteList option').remove();
	$('#outSeedList option').remove();
	
	$('#schOrgCodeStep3').append("<option value=''>-- 기관유형 3단계 --</option>");
	$('#outSiteList').append("<option value=''>-- 기관 선택 --</option>");
	$('#outSeedList').append("<option value=''>-- 게시판 선택 --</option>");

	return false;
}

//기관유형3을 조회한다
function getOrgCodeStep3List(schOrgCodeStep3, gubun) {
	if( schOrgCodeStep3 == null || schOrgCodeStep3 == "" ) {
		return false;
	}
	$.ajax({
		url:"/mdm/MdmOrgCodeStep3List.do",
		cache:false,
		type:"post",
		data:{"schOrgCodeStep3":schOrgCodeStep3},
		success: function(data, status) {
			$("#schOrgCodeStep3").html(data);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	
	
	// 기관명 
	var org1 = $("#schOrgCodeStep1 option:selected").val();
	var org2 = schOrgCodeStep3;
	
	if( org1 == null || org1 == "" || org2 == null || org2 == "" ) {
		return false;
	}
	
	$.ajax({
		url:"/mdm/MdmOutSiteList2.do",
		cache:false,
		type:"post",
		data:{"ORG_1":org1, "ORG_2":org2},
		success: function(data, status) {
			$("#outSiteList").html(data);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	
	//게시판정보 제거
	$('#outSeedList option').remove();
	$('#outSeedList').append("<option value=''>--  게시판 선택 --</option>");
	
	return false;
}

//첨부파일 추가
function addAttFile(el){
	var name = "FILE_"+(Number($(el).parent().parent().find('input[type="file"]').length) + 1);
	var file = "";
	file += '<p><input name="'+name+'" class="input-sm ip " value="찾아보기"  type="file" style="width:80%; float:left; margin-right:10px; margin-top:4px;">';
	file += '<a href="#" onclick="return delAttFile(\''+name+'\');"><button type="button" class="btn btn-danger">삭제</button></a></p>';
	$(el).parent().parent().append(file);
}

//첨부파일 삭제
function delAttFile(name){
	if( confirm("첨부파일을 삭제 하시겠습니까?") ) {
		$('#leftArea table input[name="'+name+'"]').parent().remove();
	}
	return false;
}

//등록
function regist() {
 	
	//smartEditor textarea 적용
	oEditors.getById["EXTRACTHTML"].exec("UPDATE_CONTENTS_FIELD", []); 
	
	document.pgfrm.EXTRACTHTML.value = encodeURIComponent(htmlEntityEnc(document.pgfrm.EXTRACTHTML.value)).replace(/[!'()*]/g, escape);
	document.pgfrm.TITLE.value = encodeURIComponent(htmlEntityEnc($('#TITLE_TEMP').val())).replace(/[!'()*]/g, escape);
	document.pgfrm.WRITER.value = encodeURIComponent(htmlEntityEnc($('#WRITER_TEMP').val())).replace(/[!'()*]/g, escape);
	document.pgfrm.CONTENT.value = encodeURIComponent(htmlEntityEnc($('#CONTENT_TEMP').val())).replace(/[!'()*]/g, escape);
	document.pgfrm.CDATE.value = encodeURIComponent(htmlEntityEnc($('#CDATE_TEMP').val())).replace(/[!'()*]/g, escape);
	
	if( document.pgfrm.TITLE.value == "" ) {
		alert("제목을 입력하십시오.");
		return false;
	}
	
	if( $("#outDocTypeList").val() == "" ) {
		alert("자료유형2를 선택하십시오.");
		return false;
	}
	
 	if( $("#schOrgCodeStep1").val() == "" ) {
		alert("기관유형1을 선택하십시오.");
		return false;
	}
 
 	if( $("#schOrgCodeStep2").val() == "" ) {
		alert("기관유형2을 선택하십시오.");
		return false;
	}
 
 	if( $("#outSiteList").val() == "" ) {
		alert("기관명을 선택하십시오.");
		return false;
	}
	
	if( document.pgfrm.CDATE.value == "" ) {
		alert("발행일을 입력하십시오.");
		return false;
	}
	
	if( document.pgfrm.WRITER.value == "" ) {
		alert("작성자를 입력하십시오.");
		return false;
	}
	
	if( $("#EXTRACTHTML").val() == encodeURIComponent(htmlEntityEnc("<p>&nbsp;</p>").replace(/[!'()*]/g, escape)) ) {
		alert("내용을 입력하십시오.");
		return false;
	}
	
	// 파일 확장자 체크
	var isValidExt = true;
	$('#leftArea > table [type="file"]').each(function(index, file){
		if(file.value != "" && isValidExt) {
	        if(!fn_confirmExt(file.value)) {
	        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
	        	isValidExt = false;
	        	return;
	        }
	    }
	});
	
	if(!isValidExt) return;
	
	if( !confirm("등록하시겠습니까?") ) {
		return false;
	}
	
	pgfrm.action="/mdm/MdmPolicyInfoRegist.do";
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

function list() {
	fvfrm.action = "/mdm/MdmPolicyInfoList.do";
	document.fvfrm.submit();
}

$(window).load(function(){
	
});
</script>
</head>
<body class="body" style="background:none;">
<div id="page-wrapper" style="margin-left:0; width:100%; float:left;">
	<form name="pgfrm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
		<input type="hidden" name="EXTID" value="${policyInfoVO.EXTID}" />
		<input type="hidden" name="OUTBBS_CN" value="${policyInfoVO.OUTBBS_CN}" />
		<input type="hidden" name="cnId" value="${policyInfoVO.OUTBBS_CN}" />
		<input type="hidden" name="REGION" id="REGION" value="${policyInfoVO.REGION}" />
	
	<div class="row">
    	<div class="col-lg-12">
        	<h1 class="page-header">메타데이터 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
	<h2>메타데이터 목록 (지방정책정보) </h2>
	<div class="row">
		<p class="tr">
			<a href="#;" onclick="list();"><button type="button" class="btn btn-default">닫기</button></a>
			<a href="#;" onclick="return regist();"><button type="button" class="btn btn-success">등록</button></a>
<!-- 			<a href="#;" onclick="return setDelete();"><button type="button" class="btn btn-danger">삭제</button></a> -->
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
									<input id="TITLE_TEMP" class="input-sm ip" type="text" style="width:100%;" value=""  maxlength="250"/>
									<input name="TITLE" class="input-sm ip" type="text" style="display:none;" value="" /><br>
								</td>
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
										<option value="100" >홍보/보도/소식</option>
										<option value="200" >정책/업무자료</option>
										<option value="300" >연구자료</option>
										<option value="400" >의정활동자료</option>
										<option value="500" >통계</option>
										<option value="600" >정책매뉴얼</option>
										<option value="700" >출장보고서</option>
										<option value="800" >세미나/공청회</option>
										<option value="900" >감사자료</option>
										<option value="999" >기타</option>
										<option value="140" <c:if test="${mdmAdm == 'manual'}">selected="selected"</c:if>>교육&메뉴얼</option>
									</select>
								</td>
							</tr>
							<tr>	
								<th>기관유형</th>
								<th>유형1</th>
								<td style="vertical-align: middle;">
									<select id="schOrgCodeStep1" name="ORG_1" onchange="return getOrgCodeStep2List(this.value)" >
									<option value="">기관유형1</option>
									<c:forEach var="cList" items="${codeOrgCodeStep1List}" varStatus="status">
										<option value="${cList.ORGCODE}" >${cList.ORGNM}</option>
									</c:forEach>
									</select>
 								</td>
 								<th>유형2</th>
								<td style="vertical-align: middle;">
									<select id="schOrgCodeStep2" name="ORG_2" onchange="return getOrgCodeStep3List(this.value)" >
									<option value="">기관유형2</option>
									</select>
 								</td>
 								<th>유형3</th>
								<td style="vertical-align: middle;">	
									<select id="schOrgCodeStep3" name="ORG_3" onchange="return getOutSiteList(this.value, 'i')">
									<option value="">기관유형3</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>기관명</th>
								<td colspan="6">
									<select name="SITEID" id="outSiteList" onchange="return getOutSeedList(this.value, 'i')" class=" input-sm" style="width:150px;">
										<option value="">-- 기관 선택 --</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>게시판명</th>
								<td colspan="6">
									<select name="SEEDID" id="outSeedList" class=" input-sm" style="width:150px;">
										<option value="">--  게시판 선택 --</option>
									</select>
								</td>
							</tr>
							<tr>	
								<th>발행일<br>(게시일자)</th>
								<td colspan="6">
									<input id="CDATE_TEMP" class="input-sm ip" type="text" style="width:30%;" value="" title="발행일" onkeydown="javascript:checkNumber(event);" maxlength="10"/>
									<input name="CDATE" class="input-sm ip" type="text" style="display:none;" value="" title="발행일" />
								</td>
							</tr>
							<tr>	
								<th>작성자</th>
								<td colspan="6">
									<input id="WRITER_TEMP" class="input-sm ip" type="text" style="width:30%;" value="" title="작성자"  maxlength="64"/>
									<input name="WRITER" class="input-sm ip" type="text" style="display:none;" value="" title="작성자" />
								</td>
							</tr>	
							<tr>	
								<th>내  용</th>
								<td colspan="6">
									<textarea name="EXTRACTHTML" id="EXTRACTHTML" cols="100" rows="10" class="ip" style="width:100%; height:400px; min-width:95%"></textarea>
								</td>
							</tr>
							<tr>	
								<th>요  약</th>
								<td colspan="6">
									<textarea id="CONTENT_TEMP" cols="100" rows="10" class="ip" style="width:100%; height:400px; min-width:95%"></textarea>
									<textarea name="CONTENT" cols="100" rows="10" class="ip" style="display:none;"></textarea>
								</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="6">
									<p>
									<button type="button" class="btn btn-info" onclick="addAttFile(this);">파일추가</button>
									<input name="FILE_1" id="FILE_1" class="input-sm ip " value="찾아보기"  type="file" style="width:80%; float:left; margin-right:10px;">
									</p>
 								</td>
							</tr>
							<tr>	
								<th>게시</th>
								<td colspan="2">
								<select name="ISVIEW">
								<option value="Y">Y</option>
								<option value="N">N</option>
								</select>
								</td> 
								<th></th>
								<td colspan="3"></td>
							</tr>
						</tbody>
					</table>
				</div><!-- /.table-responsive -->
			</div><!-- //movebox -->
			<p class="tr">
				<a href="#" onclick="list();"><button type="button" class="btn btn-default">닫기</button></a>
				<a href="#" onclick="return regist();"><button type="button" class="btn btn-success">등록</button></a>
			</p>
		</div>
			<!-- /.panel-body -->
	</div>
	</form>
</div>
	
	<form name="fvfrm" method="post">
		<%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchVar.jsp" %>
	</form>
	
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
</body>
</html>
