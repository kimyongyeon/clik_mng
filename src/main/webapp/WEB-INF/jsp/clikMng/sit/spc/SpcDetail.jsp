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
<title>스페셜검색 상세정보</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<!-- Naver.com. SmartEditor 적용  시작-->
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- Naver.com. SmartEditor 적용  끝 -->
<validator:javascript formName="spcManage" staticJavascript="false" xhtml="true" cdata="false"/>
 
<script type="text/javaScript" language="javascript">
<!--

/* ********************************************************
* 초기 데이터 입력
******************************************************** */
// 광역시군구 셀렉트 박스 선택
$( document ).ready(function() {
	localChange('<c:out value="${insttClCode}" />', 0);
	
	rasmblyChange('<c:out value="${brtcCode}" />', 0);
});

/* ********************************************************
* 목록이동
******************************************************** */
function fnSpcList() {
	location.href='/sit/spc/SpcList.do';
}


/* ********************************************************
* 스페셜 검색 수정
******************************************************** */
function fnSpcUpdate() {
	var varFrom = document.getElementById("spcManage");
	
    if($("select[name='selRasmbly']").val() == "0" || $("select[name='selRegion']").val() == "0" || $("select[name='selAssembly']").val() == "0") {
    	alert("지방의회선택은 필수 입니다.");
    	return;
    }
    
    if($("#file_0").val()=="") {
    	alert("대표이미지 항목을 확인해 주세요.");
    	return;
    }
    
    if($("#mainUrl").val()=="") {
    	alert("대표URL 항목을 확인해 주세요.");
    	return;
    }
    
    if($("#mainSj").val()=="") {
    	alert("제목 항목을 확인해 주세요.");
    	return;
    }		
	
	//smartEditor textarea 적용
	oEditors.getById["mainCtt"].exec("UPDATE_CONTENTS_FIELD", []);
	
    if($("#mainCtt").val()=="" || $("#mainCtt").val()== null || $("#mainCtt").val()== "&nbsp;" || $("#mainCtt").val()== "<p>&nbsp;</p>") {
    	alert("내용 항목을 확인해 주세요.");
    	return;
    }
	
    if($("#kwrd").val()=="") {
    	alert("키워드 항목을 확인해 주세요.");
    	return;
    }
    
    // 확장자 유효성 체크
    $('table input[type="file"]').each(function(index,item){

    	if(item.value != '') {
	        var confirmExt;
	        var thumbext = item.value; 
	        confirmExt = fn_confirmExt(thumbext);
	        if(!confirmExt) {
	        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
	        	return;
	        }	    		
    	}
    });
	
  //인코딩 처리
    document.getElementsByName("mainUrl")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("mainUrl_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("mainSj")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("mainSj_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("mainCtt")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("mainCtt").value));
    document.getElementsByName("kwrd")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("kwrd_temp").value)).replace(/[!'()*]/g, escape);
    
    document.getElementsByName("subText1")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText1_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subText2")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText2_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subText3")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText3_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subText4")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText4_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subText5")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText5_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subText6")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText6_temp").value)).replace(/[!'()*]/g, escape);
    
    document.getElementsByName("subImage1Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage1Url_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subImage2Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage2Url_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subImage3Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage3Url_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subImage4Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage4Url_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subImage5Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage5Url_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subImage6Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage6Url_temp").value)).replace(/[!'()*]/g, escape)
	    
	if(confirm("수정 하시겠습니까?")){	    
		varFrom.cmd.value = 'update';
		varFrom.speclSearchId.value = '<c:out value="${spcDetailInfo.speclSearchId}" />';
		varFrom.mainImageFileNm.value = '<c:out value="${spcDetailInfo.mainImageFileNm}" />';
		varFrom.fileListCnt.value = '<c:out value="${fileListCnt}" />';
		
		varFrom.action = "<c:url value='/sit/spc/SpcUpdateProc.do'/>";
	    varFrom.submit();		
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
    }
}


/**
 * 파일 확장자 체크
 * 해당 확장자가 아닌 다른 확장자로 업로드 실행 시 필터
 */

function fn_confirmExt(str) {
	str = str.slice(str.lastIndexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.

	var result = true;

	if(str != "jpg" && str != "png" &&  str != "gif" &&  str != "bmp" && str != "jpeg"){ //확장자를 확인합니다.

		//alert('썸네일은 이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
		result = false;
		return result;

	} else {
		return result;
	}
}

/* ********************************************************
* multi selectbox 
******************************************************** */
//광역시도 선택
function localChange(insttClCode, type) {

	//var insttClCode = $("select[name='selRasmbly'] option:selected").val();	
	var insttClCode = insttClCode;
	var brtcCode = '<c:out value="${brtcCode}" />';
	
	// 지역선택 셀렉트박스 초기화
	$("select[name='selRegion'] option").remove();
	$("#selRegion").append("<option>지역 선택</option>");
	$("select[name='selAssembly'] option").remove();
	$("#selAssembly").append("<option>지방의회 선택</option>");
   
   $.ajax({
	   type: "POST",
	   url : "/sit/spc/selectAjaxBrtc.do",
	   data : "insttClCode=" + insttClCode,
	   dataType:"json", 
	   success: function(args) {
		   $.each(args, function() {
			   $("#selRegion").append("<option value='"+this.codeId+"'>"+this.codeIdNm+"</option>");
			   if(type == 0) {
				   $("#selRegion").val(brtcCode).attr("selected", "selected");
			   }
			   
			});
	   }
   		,error:function(e) {
  			alert(e.responseText);
	   }
	});
	
} 


// 지방의회 셀렉트 박스
function rasmblyChange(brtcCode, type) {
	
	// 기관분류코드
	var insttClCode = $("select[name='selRasmbly'] option:selected").val();
	
	// 광역시도코드
   var brtcCode = brtcCode;
	
	// 각 의회 코드
   var loasmCode = '<c:out value="${loasmCode}" />';
	
	// 지역선택 셀렉트박스 초기화
	$("select[name='selAssembly'] option").remove();
	$("#selAssembly").append("<option value=0>지방의회 선택</option>");
   
	var formData = "brtcCode=" + brtcCode + "&insttClCode=" + insttClCode;  
	
   $.ajax({
	   type: "POST",
	   url : "/sit/spc/AjaxAsmbly.do",
	   data : formData,
	   dataType:"json", 
	   success: function(args) {
		   $.each(args, function() {
			   $("#selAssembly").append("<option value='"+this.loasmCode+"'>"+this.loasmNm+"</option>");
			   if(type == 0) {
				   $("#selAssembly").val(loasmCode).attr("selected", "selected");
			   }			   
			});
	   }
   		,error:function(e) {
  			alert(e.responseText);
	   }
	});
}

/* ********************************************************
* 파일 input Change
******************************************************** */
function fncOnChangeImage() {
	var varFrom = document.getElementById("spcManage");
	varFrom.spcImage.value = varFrom.file_1.value;
}

/* ********************************************************
* 파일 input reset
******************************************************** */

function fnClearFile(fileId) {
	$(fileId).replaceWith($(fileId).clone());
}

/* ********************************************************
* 파일 제거
******************************************************** */

function fnDeleteFile(atchFileId, fileSn) {
    if(confirm("첨부파일을 삭제 하시겠습니까?")){
		var varFrom = document.getElementById("spcManage");
		
		var speclSearchId 	=  '<c:out value="${spcDetailInfo.speclSearchId}" />';
		var insttClCode 	=  '<c:out value="${insttClCode}" />';
		var brtcCode 		=  '<c:out value="${brtcCode}" />';
		var loasmCode 	=  '<c:out value="${loasmCode}" />';
		
		var returnUrl = "/sit/spc/SpcDetail.do?rspeclSearchId="+speclSearchId+"&rinsttClCode="+insttClCode+"&rbrtcCode="+brtcCode+"&rloasmCode="+loasmCode;	
		
		varFrom.atchFileId.value = atchFileId;
		varFrom.fileSn.value = fileSn;
		varFrom.returnUrl.value =returnUrl; 
		varFrom.action = "<c:url value='/cmm/fms/deleteFileInfs.do'/>";
		varFrom.submit();
    }
}

/* ********************************************************
* 스페셜검색 정보 제거
******************************************************** */
function fnSpcDelete() {
    if(confirm("삭제 하시겠습니까?")){
		var varFrom = document.getElementById("spcManage");
		
		//인코딩 처리
	    document.getElementsByName("mainUrl")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("mainUrl_temp").value)).replace(/[!'()*]/g, escape);
	    document.getElementsByName("mainSj")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("mainSj_temp").value)).replace(/[!'()*]/g, escape);
	    document.getElementsByName("mainCtt")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("mainCtt").value));
	    document.getElementsByName("kwrd")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("kwrd_temp").value)).replace(/[!'()*]/g, escape);
	    
	    document.getElementsByName("subText1")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText1_temp").value)).replace(/[!'()*]/g, escape);
	    document.getElementsByName("subText2")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText2_temp").value)).replace(/[!'()*]/g, escape);
	    document.getElementsByName("subText3")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText3_temp").value)).replace(/[!'()*]/g, escape);
	    document.getElementsByName("subText4")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText4_temp").value)).replace(/[!'()*]/g, escape);
	    document.getElementsByName("subText5")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText5_temp").value)).replace(/[!'()*]/g, escape);
	    document.getElementsByName("subText6")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText6_temp").value)).replace(/[!'()*]/g, escape);
	    
	    document.getElementsByName("subImage1Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage1Url_temp").value)).replace(/[!'()*]/g, escape);
	    document.getElementsByName("subImage2Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage2Url_temp").value)).replace(/[!'()*]/g, escape);
	    document.getElementsByName("subImage3Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage3Url_temp").value)).replace(/[!'()*]/g, escape);
	    document.getElementsByName("subImage4Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage4Url_temp").value)).replace(/[!'()*]/g, escape);
	    document.getElementsByName("subImage5Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage5Url_temp").value)).replace(/[!'()*]/g, escape);
	    document.getElementsByName("subImage6Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage6Url_temp").value)).replace(/[!'()*]/g, escape)
		
		varFrom.cmd.value = 'del';
		varFrom.speclSearchId.value = '<c:out value="${spcDetailInfo.speclSearchId}" />';
		varFrom.action = "<c:url value='/sit/spc/SpcDetail.do'/>";
		varFrom.submit();
    }
}
-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form:form commandName="spcManage" method="post" enctype="multipart/form-data" >
<input type="hidden" name="atchFileId" />
<input type="hidden" name="mainImageFileNm" />
<input type="hidden" name="fileSn" />
<input type="hidden" name="fileListCnt" />
<input type="hidden" name="returnUrl" />
<input type="hidden" name="speclSearchId" />
<input type="hidden" name="posblAtchFileNumber" value="1" >
<input type="hidden" name="spcImage" >
<input name="cmd" type="hidden" />
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">스페셜검색 관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>스페셜검색 상세정보</h2>

			<table class="table table-striped table-bordered table-hover "  id="">
				<colgroup>
					<col width="15%" />
					
					<col />
				 </colgroup>
				<tbody>
					<tr>
						<th>지방의회선택<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
						<td>
					    	<select aria-controls="dataTables-example" class=" input-sm" name="selRasmbly" id="selRasmbly" onchange="localChange(this.value, 1);">
					    		<option value="0">의회선택</option>
					    		<option value="INTSTTCL_000023" <c:if test="${insttClCode == 'INTSTTCL_000023'}">selected="selected"</c:if>>광역의회</option>
					    		<option value="INTSTTCL_000024" <c:if test="${insttClCode == 'INTSTTCL_000024'}">selected="selected"</c:if>>기초의회</option>
					    	</select>			
					    									
					    	<select aria-controls="dataTables-example" class=" input-sm" name="selRegion" id="selRegion" onchange="rasmblyChange(this.value, 1);">
								<option disabled="disabled" value="0">지역 선택</option>
					    	</select>							
						
					    	<select name="selAssembly" id="selAssembly" aria-controls="dataTables-example" class=" input-sm">
					    		<option disabled="disabled" value="0">지방의회 선택</option>
					    	</select>									
						
						</td>
					</tr>
					<c:set var="imageBoolean_0" value= "0" />
					<c:set var="imageBoolean_1" value="0" />
					<c:set var="imageBoolean_2" value="0" />
					<c:set var="imageBoolean_3" value="0" />
					<c:set var="imageBoolean_4" value="0" />
					<c:set var="imageBoolean_5" value="0" />
					<c:set var="imageBoolean_6" value="0" />
					
					<tr>
						<th>대표이미지<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
						<td>
						<c:forEach var="fileVO" items="${fileList}" varStatus="status">
							<c:if test="${fileVO.fileSn==0}">
							<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
							<img src="<c:url value='/images/clikmng/cmm/fms/icon/bu5_close.gif' />" 
							width="19" height="18" onClick="fnDeleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" alt="파일삭제">
							<c:set var="imageBoolean_0" value="1" />
							</c:if>
						</c:forEach>
						<c:if test="${imageBoolean_0 == '0' }">
							<input class="input-sm ip " value="<c:out value="${spcDetailInfo.mainImagePath}" />" placeholder="" name="file_0" id="file_0" type="file" onchange="javascript:fncOnChangeImage();" style="width:30%; float:left; margin-right:10px;">
							<button type="button" class="btn btn-danger" onclick="fnClearFile('#file_0');">삭제</button>
							<span class="r">* 사이즈 : 가로 214px, 세로 110px</span>
						</c:if>
						</td>
					</tr>
					<tr>
						<th>대표 URL<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
						<td>
							<input id="mainUrl_temp" type="text" class="ip input-sm" style="width:100%;" value="<c:out value="${spcDetailInfo.mainUrl}" escapeXml="false"/>" />
							<input name="mainUrl" type="hidden" style="display:none;" />
						</td>
					</tr>
	
					<tr>
						<th>제목<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
						<td>
							<input id="mainSj_temp" value="<c:out value="${spcDetailInfo.mainSj}" escapeXml="false"/>" type="text" class="ip input-sm" style="width:100%;" />
							<input name="mainSj" type="hidden" style="display:none;" />
						</td>
					</tr>
					<tr>
						<th>내용<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
						<td><textarea name="mainCtt" id="mainCtt" cols="100" rows="10" style="width:100%;" class="ip"><c:out value="${spcDetailInfo.mainCtt}"  escapeXml="false"/></textarea></td>
					</tr>
					<tr>
						<th>키워드<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
						<td>
						<input type="text" id="kwrd_temp" class="ip input-sm" style="width:100%;" value="<c:out value="${kwrdJoin }" escapeXml="false"/>" />
						<input name="kwrd" type="hidden" style="display:none;" />
						<span class="r">* 키워드는 컴머(,)로 구분</span>
						</td>
					</tr>							
					<tr>
						<th>링크정보</th>
						<td><span class="r">*  사이즈 : 가로 97px, 세로 73px (링크정보는 최대 6개 까지 등록 가능)</span></td>
					</tr>
	
					<tr>
						<th>링크정보 #1</th>
						<td>
						<input type="text" id="subText1_temp"  value="<c:out value="${spcDetailInfo.subText1}" escapeXml="false"/>"  class="ip input-sm" style="width:30%; float:left; margin-right:10px; margin-bottom:5px;" />
						<input name="subText1" type="hidden" style="display:none;" />
						<c:forEach var="fileVO" items="${fileList}" varStatus="status">
							<c:if test="${fileVO.fileSn==1}">
							<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
							<img src="<c:url value='/images/clikmng/cmm/fms/icon/bu5_close.gif' />" 
							width="19" height="18" onClick="fnDeleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" alt="파일삭제">
							<c:set var="imageBoolean_1" value="1" />
							</c:if>
						</c:forEach>
						<c:if test="${imageBoolean_1 == '0' }">
							<input class="input-sm ip " placeholder="" name="file_1" id="file_1" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;margin-bottom:5px;">
								<button type="button" class="btn btn-danger"  onclick="fnClearFile('#file_1');">삭제</button> <br/>
						</c:if>								
	
						<input type="text" id="subImage1Url_temp"  placeholder="http:// 를  같이 입력해주세요." value="<c:out value="${spcDetailInfo.subImage1Url}" escapeXml="false"/>" class="ip input-sm" style="width:100%;" />
						<input name="subImage1Url" type="hidden" style="display:none;" />
						</td>
					</tr>
					<tr>
						<th>링크정보 #2</th>
						<td>
						<input type="text" id="subText2_temp"  value="<c:out value="${spcDetailInfo.subText2}" escapeXml="false"/>" class="ip input-sm" style="width:30%; float:left; margin-right:10px; margin-bottom:5px;" />
						<input name="subText2" type="hidden" style="display:none;" />
						<c:forEach var="fileVO" items="${fileList}" varStatus="status">
							<c:if test="${fileVO.fileSn==2}">
							<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
							<img src="<c:url value='/images/clikmng/cmm/fms/icon/bu5_close.gif' />" 
							width="19" height="18" onClick="fnDeleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" alt="파일삭제">
							<c:set var="imageBoolean_2" value="1" />
							</c:if>
						</c:forEach>
						<c:if test="${imageBoolean_2 == '0' }">
							<input class="input-sm ip " placeholder="" name="file_2" id="file_2" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;margin-bottom:5px;">
							<button type="button" class="btn btn-danger" onclick="fnClearFile('#file_2');">삭제</button> <br/>
						</c:if>									
	
						<input type="text" id="subImage2Url_temp"  placeholder="http:// 를  같이 입력해주세요." value="<c:out value="${spcDetailInfo.subImage2Url}" escapeXml="false"/>" class="ip input-sm" style="width:100%;" />
						<input name="subImage2Url" type="hidden" style="display:none;" />
						</td>
					</tr>
					<tr>
						<th>링크정보 #3</th>
						<td>
						<input type="text" id="subText3_temp"  value="<c:out value="${spcDetailInfo.subText3}" escapeXml="false"/>" class="ip input-sm" style="width:30%; float:left; margin-right:10px; margin-bottom:5px;" />
						<input name="subText3" type="hidden" style="display:none;" />
						<c:forEach var="fileVO" items="${fileList}" varStatus="status">
							<c:if test="${fileVO.fileSn==3}">
							<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
							<img src="<c:url value='/images/clikmng/cmm/fms/icon/bu5_close.gif' />" 
							width="19" height="18" onClick="fnDeleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" alt="파일삭제">
							<c:set var="imageBoolean_3" value="1" />
							</c:if>
						</c:forEach>
						<c:if test="${imageBoolean_3 == '0' }">
							<input class="input-sm ip " placeholder="" name="file_3" id="file_3" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;margin-bottom:5px;">
							<button type="button" class="btn btn-danger" onclick="fnClearFile('#file_3');">삭제</button> <br/>
						</c:if>								
	
						<input type="text" id="subImage3Url_temp"  placeholder="http:// 를  같이 입력해주세요." value="<c:out value="${spcDetailInfo.subImage3Url}" escapeXml="false"/>" class="ip input-sm" style="width:100%;" />
						<input name="subImage3Url" type="hidden" style="display:none;" />
						</td>
					</tr>
					<tr>
						<th>링크정보 #4</th>
						<td>
						<input type="text" id="subText4_temp" value="<c:out value="${spcDetailInfo.subText4}" escapeXml="false"/>"  class="ip input-sm" style="width:30%; float:left; margin-right:10px; margin-bottom:5px;" />
						<input name="subText4" type="hidden" style="display:none;" />
						<c:forEach var="fileVO" items="${fileList}" varStatus="status">
							<c:if test="${fileVO.fileSn==4}">
							<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
							<img src="<c:url value='/images/clikmng/cmm/fms/icon/bu5_close.gif' />" 
							width="19" height="18" onClick="fnDeleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" alt="파일삭제">
							<c:set var="imageBoolean_4" value="1" />
							</c:if>
						</c:forEach>
						<c:if test="${imageBoolean_4 == '0' }">
							<input class="input-sm ip " placeholder="" name="file_4" id="file_4" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;margin-bottom:5px;">
							<button type="button" class="btn btn-danger" onclick="fnClearFile('#file_4');">삭제</button> <br/>
						</c:if>								
	
						<input type="text" id="subImage4Url_temp"  placeholder="http:// 를  같이 입력해주세요." value="<c:out value="${spcDetailInfo.subImage4Url}" escapeXml="false"/>" class="ip input-sm" style="width:100%;" />
						<input name="subImage4Url" type="hidden" style="display:none;" />
						</td>
					</tr>
					<tr>
						<th>링크정보 #5</th>
						<td>
						<input type="text" id="subText5_temp"  value="<c:out value="${spcDetailInfo.subText5}" escapeXml="false"/>" class="ip input-sm" style="width:30%; float:left; margin-right:10px; margin-bottom:5px;" />
						<input name="subText5" type="hidden" style="display:none;" />
						<c:forEach var="fileVO" items="${fileList}" varStatus="status">
							<c:if test="${fileVO.fileSn==5}">
							<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
							<img src="<c:url value='/images/clikmng/cmm/fms/icon/bu5_close.gif' />" 
							width="19" height="18" onClick="fnDeleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" alt="파일삭제">
							<c:set var="imageBoolean_5" value="1" />
							</c:if>
						</c:forEach>
						<c:if test="${imageBoolean_5 == '0' }">
							<input class="input-sm ip " placeholder="" name="file_5" id="file_5" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;margin-bottom:5px;">
							<button type="button" class="btn btn-danger" onclick="fnClearFile('#file_5');">삭제</button> <br/>
						</c:if>										
	
						<input type="text" id="subImage5Url_temp" placeholder="http:// 를  같이 입력해주세요."  value="<c:out value="${spcDetailInfo.subImage5Url}" escapeXml="false"/>" class="ip input-sm" style="width:100%;" />
						<input name="subImage5Url" type="hidden" style="display:none;" />
						</td>
					</tr>
					<tr>
						<th>링크정보 #6</th>
						<td>
						<input type="text" id="subText6_temp" value="<c:out value="${spcDetailInfo.subText6}" escapeXml="false"/>" class="ip input-sm" style="width:30%; float:left; margin-right:10px; margin-bottom:5px;" />
						<input name="subText6" type="hidden" style="display:none;" />
						<c:forEach var="fileVO" items="${fileList}" varStatus="status">
							<c:if test="${fileVO.fileSn==6}">
							<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
							<img src="<c:url value='/images/clikmng/cmm/fms/icon/bu5_close.gif' />" 
							width="19" height="18" onClick="fnDeleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" alt="파일삭제">
							<c:set var="imageBoolean_6" value="1" />
							</c:if>
						</c:forEach>
						<c:if test="${imageBoolean_6 == '0' }">
							<input class="input-sm ip " placeholder="" name="file_6" id="file_6" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;margin-bottom:5px;">
							<button type="button" class="btn btn-danger" onclick="fnClearFile('#file_6');">삭제</button> <br/>
						</c:if>									
						
						<input type="text" id="subImage6Url_temp" placeholder="http:// 를  같이 입력해주세요." value="<c:out value="${spcDetailInfo.subImage6Url}" escapeXml="false"/>"  class="ip input-sm" style="width:100%;" />
						<input name="subImage6Url" type="hidden" style="display:none;" />
						</td>
					</tr>
					
				</tbody>
			</table>
		<p class="tr">
			<button type="button" class="btn btn-default" onclick="javascript:fnSpcList(); return false;"><spring:message code="button.list" /></a></button> 
			<button type="button" class="btn btn-success" onclick="javascript:fnSpcUpdate(); return false;"><spring:message code="button.update" /></button>
			<button type="button" class="btn btn-danger" onclick="javascript:fnSpcDelete(); return false;"><spring:message code="button.delete" /></button>			
		</p>

	</div><!--//page-wrapper-->
	
</form:form>

<!-- Naver.com SmartEditor -->
<script type="text/javascript">
var oEditors = [];

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "mainCtt",
	sSkinURI: "/smarteditor/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["mainCtt"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});

</script>

</body>
</html>