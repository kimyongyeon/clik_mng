<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>인증키 발급내역 상세정보</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mngManage" staticJavascript="false" xhtml="true" cdata="false"/>
 
<script type="text/javaScript" language="javascript">
<!--

$(document).ready(function(){
	//openapi 구분
	$(':radio[name="API_TYPE"]').change(function(){
		$('#SEARCH_TYPE option').remove();

		if(this.value === 'minutes'){
			$('#SEARCH_TYPE').append('<option value="ALL">전체</option>');
			$('#SEARCH_TYPE').append('<option value="MTR_SJ">안건</option>');
			$('#SEARCH_TYPE').append('<option value="ASEMBY_NM">발언자</option>');
			$('#SEARCH_TYPE').append('<option value="MINTS_HTML">내용</option>');
			$('#SEARCH_TYPE').append('<option value="APNDX_FILE_NM">부록</option>');
			$('#SEARCH_TYPE').append('<option value="RASMBLY_NM">지방의회명</option>');
			$('#SEARCH_TYPE').append('<option value="PRMPST_CMIT_NM">위원회명</option>');
		}else if(this.value === 'bill'){
			$('#SEARCH_TYPE').append('<option value="ALL">전체</option>');
			$('#SEARCH_TYPE').append('<option value="BI_SJ">의안제목</option>');
			$('#SEARCH_TYPE').append('<option value="RASMBLY_NM">지방의회명</option>');
			$('#SEARCH_TYPE').append('<option value="PROPSR">발의자</option>');
			$('#SEARCH_TYPE').append('<option value="BI_OUTLINE">의안요지</option>');
		}else if(this.value === 'assemblyinfo'){
			$('#SEARCH_TYPE').append('<option value="ALL">전체</option>');
			$('#SEARCH_TYPE').append('<option value="ASEMBY_NM">의원명</option>');
			$('#SEARCH_TYPE').append('<option value="PPRTY_NM">정당명</option>');
			$('#SEARCH_TYPE').append('<option value="MTGNM">위원회명</option>');
			$('#SEARCH_TYPE').append('<option value="SPKNG_CN">회의발언내용</option>');
		}
	});
	
	//요청구분
	$(':radio[name="DISPLAY_TYPE"]').change(function(){

		if(this.value === 'list'){
			$('#startCountRow').show();
			$('#listCountRow').show();
			$('#searchTypeRow').show();
			$('#searchKeywordRow').show();
			$('#detailKeyRow').hide();
		}else if(this.value === 'detail'){
			$('#startCountRow').hide();
			$('#listCountRow').hide();
			$('#searchTypeRow').hide();
			$('#searchKeywordRow').hide();
			$('#detailKeyRow').show();
		}
	});
	
	// 숫자만 입력
	jQuery(document).on("keyup", "input:text[numberOnly]", function() {
		jQuery(this).val( jQuery(this).val().replace(/[^0-9]/gi,""));
	});	
});

/******************************************
 * 목록
 ******************************************/
	
function fnListMng() {

	var varForm = document.listForm;
	varForm.procMode.value = "UPDATE";
	varForm.action = "<c:url value='/rcm/AilList.do'/>";
	varForm.submit();
}

/******************************************
 * 
 ******************************************/
function fnOpenapiCallTest() {
	
	var apiType			= trim($(':radio[name="API_TYPE"]:checked').val());
	var key				= trim($("#KEY").val());
	var type			= trim($(':radio[name="TYPE"]:checked').val());
	var displayType		= trim($(':radio[name="DISPLAY_TYPE"]:checked').val());
	var startCount 		= trim($("#START_COUNT").val());
	var listCount		= trim($("#LIST_COUNT").val());
	var searchType 		= trim($("#SEARCH_TYPE").val());
	var searchKeyword 	= trim($("#SEARCH_KEYWORD").val());
	var docid 	= trim($("#DOCID").val());
	
	if(apiType === ''){
		alert('OPENAPI 구분을 선택해 주세요.');
		return false;
	}
	if(key === ''){
		alert('OPENAPI 인증키를 입력해주세요');
		return false;
	}
	if(type === ''){
		alert('응답결과 타입을 선택해 주세요.');
		return false;
	}
	if(displayType === ''){
		alert('요청구분을 선택해 주세요.');
		return false;
	}
	if(displayType === 'list')
	{
		if(startCount === ''){
			alert('요청시작 순번을 입력해주세요');
			return false;
		}
		if(listCount === ''){
			alert('요청 목록 수를 입력해주세요.');
			return false;
		}
		if(searchType === ''){
			alert('검색타입을 선택해 주세요.');
			return false;
		}
		if(searchKeyword === ''){
			alert('검색어를 입력해주세요.');
			return false;
		}
	}
	else if(displayType === 'detail')
	{
		if(docid === ''){
			alert('상세정보 KEY를 입력해주세요.');
			return false;
		}	
	}
	
	$.ajax({
		url : "/rcm/openapiCallAjaxRequest.do",
		type : "POST",
		data : {
			"apiType" : apiType
			,"key" : key
			,"type" : type
			,"displayType" : displayType
			,"startCount" : startCount
			,"listCount" : listCount
			,"searchType" : searchType
			,"searchKeyword" : encodeURI(searchKeyword)
			,"docid" : docid
		},
		dataType : "text",
        error:function(x,e){
            if(x.status==0){
            alert('You are offline!!n Please Check Your Network.');
            }else if(x.status==404){
            alert('Requested URL not found.');
            }else if(x.status==500){
            alert('Internel Server Error.');
            }else if(e=='parsererror'){
            alert('Error.nParsing JSON Request failed.');
            }else if(e=='timeout'){
            alert('Request Time out.');
            }else {
            alert('Unknow Error.n'+x.responseText);
            }
        },
		success : function(data) {
			$("#apiResult").show();
			if(type == "json") {
				$("#apiResult").text(data);
			} else {
				$("#apiResult").text(data);
			}
		}
	});
	return false;
}

/******************************************
 * openapi 구분에 따른 검색구분 변경
 ******************************************/
function fnSearchTypeChange(value) {
	
}

/******************************************
 * 특수문자 변환
 ******************************************/
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
-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
	<div id="page-wrapper">
		<div class="row">
		    <div class="col-lg-12">
		        <h1 class="page-header">OPENAPI 요청 테스트</h1>
		    </div>
		</div>
		
		<h2>OPENAPI 요청 테스트</h2>
		<p class="tr">
			<button type="button" class="btn btn-default" onclick="fnListMng(); return false;">목록</button>
		</p>
		<p class="tl">
		<font color="red">샘플 인증키(e1a7f967a146465aaf8721392e50e7a9) 사용시 [요청시작순번 : 0, 요청목록수 : 5] 고정으로 요청되어 집니다.</font>
		</p>
		<table class="table table-striped table-bordered table-hover" >
			<colgroup>
				<col width="20%" />
				<col width="" />
			</colgroup>
			<tbody>
				<tr>
					<th>* OPENAPI 구분</th>
					<td>
						<input type="radio" name="API_TYPE" value="minutes" checked="checked" />지방의회 회의록&nbsp;
						<input type="radio" name="API_TYPE" value="bill" />지방의회 의안&nbsp;
						<input type="radio" name="API_TYPE" value="assemblyinfo" />지방의회 의원
					</td>
				</tr>
				<tr>
					<th>* OPENAPI 인증키</th>
					<td><input id="KEY" type="text" class="ip" style="width:60%;" value="e1a7f967a146465aaf8721392e50e7a9"></td>
				</tr>
				<tr>
					<th>* 응답결과 타입</th>
					<td>
						<input type="radio" name="TYPE" value="json" checked="checked" />JSON&nbsp;
						<input type="radio" name="TYPE" value="xml" />XML
					</td>
				</tr>
				<tr>
					<th>* 요청구분</th>
					<td>
						<input type="radio" name="DISPLAY_TYPE" value="list" checked="checked" />목록&nbsp;
						<input type="radio" name="DISPLAY_TYPE" value="detail" />상세
					</td>
				</tr>
				<tr id="startCountRow">
					<th>* 요청시작순번</th>
					<td>
						<input id="START_COUNT" type="text" class="ip" style="width:60%;" value="0" numberOnly="true" >
					</td>
				</tr>
				<tr id="listCountRow">
					<th>* 요청 목록 수</th>
					<td>
						<input id="LIST_COUNT" type="text" class="ip" style="width:60%;" value="5" numberOnly="true" >
					</td>
				</tr>
				<tr id="searchTypeRow">
					<th>* 검색타입</th>
					<td>
						<select class="input-sm" style="min-width:150px;" id="SEARCH_TYPE">
							<option value="ALL">전체</option>
							<option value="MTR_SJ">안건</option>
							<option value="ASEMBY_NM">발언자</option>
							<option value="MINTS_HTML">내용</option>
							<option value="APNDX_FILE_NM">부록</option>
							<option value="RASMBLY_NM">지방의회명</option>
							<option value="PRMPST_CMIT_NM">위원회명</option>
						</select>
					</td>
				</tr>
				<tr id="searchKeywordRow">
					<th>* 검색어</th>
					<td>
						<input id="SEARCH_KEYWORD" type="text" class="ip" style="width:60%;" value="">
					</td>
				</tr>
				<tr id="detailKeyRow" style="display:none;">
					<th>* 상세정보 KEY</th>
					<td>
						<input id="DOCID" type="text" class="ip" style="width:60%;" value="">
					</td>
				</tr>
			</tbody>
		</table>

		<p class="tr">
			<button type="button" class="btn btn-default" onclick="fnOpenapiCallTest(); return false;">요청</button>
		</p>
		
		<h2>OPENAPI 요청 테스트 결과</h2>
		<div id="apiResult" style="border: 1px solid rgb(204, 204, 204); height: auto; word-break: break-all;"></div>
	</div><!--//page-wrapper-->
</body>
</html>

