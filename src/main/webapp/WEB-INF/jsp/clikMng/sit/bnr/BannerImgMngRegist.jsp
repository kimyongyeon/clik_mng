<%--
/**
 * @Class Name  : EgovBannerRegist.jsp
 * @Description : EgovBannerRegist.jsp
 */
 --%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>홍보존소식 이미지 관리 등록</title>
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="banner" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--

/* ********************************************************
* 목록으로 이동
******************************************************** */
function fnBannerImgMngList() {
    var varFrom = document.getElementById("writeForm");
    varFrom.action = "<c:url value='/sit/bnr/selectBannerImgMngView.do'/>";
    varFrom.submit();       
}

/* ********************************************************
* 홍보존소식 이미지 관리 -- 등록 
******************************************************** */

function fnBannerImgMngRegist() {
	var varFrom = document.getElementById("writeForm");

	if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.action =  "<c:url value='/sit/bnr/RegistBannerImgMngProc.do'/>";
		
		// 유효성체크 
		if(varFrom.imgFile.value != '') {
			varFrom.areaCode.value = varFrom.selRegion.value;

	        // 파일 확장자 체크
	        var confirmExt;
	        var thumbext = document.getElementById('imgFile').value; 
	         
	        if(document.getElementById('imgFile').value != "") {
	            confirmExt = fn_confirmExt(thumbext);
	            if(!confirmExt) {
	            	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
	            	return;
	            }
	        }	        
			
			varFrom.submit();
		} else {
			alert("이미지는 필수 입력값입니다.");
	           return;
	    }				
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
* 홍보존소식 이미지 관리 -- 이미지 등록 
******************************************************** */
function fnOnChangeImage() {
	var varFrom = document.getElementById("writeForm");
	varFrom.bannerImage.value = varFrom.imgFile.value;
}

-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">홍보존소식 이미지 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->

	<div id="border" style="display:" class="col-lg-12">
		<DIV class="panel panel-default">
			<DIV class="panel-heading">홍보존소식 이미지 등록</DIV>
			<!-- /.panel-heading -->			
			<DIV id="border" class="panel-body">		

			
			<form name="writeForm" id="writeForm" method="post" enctype="multipart/form-data">

			<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="홍보존소식 이미지 관리 정보를 입력하는 항목">
				<tr>
					<th width="20%" scope="row"><label for="regionId">지역</label></th>
			    	<td>
				    	<select id="selRegion" name="selRegion">
				    		<option value="0">지역 선택</option>
				    		<c:forEach var="x" items="${areaList}" varStatus="s">
				    			<option value="<c:out value="${x.codeId}" />"><c:out value="${x.codeIdNm }" /></option>
				    		</c:forEach>
				    	</select>
			    	</td>
			  	</tr>
			  	<tr>
			  		<th width="20%" scope="row"><label for="fileUploader">이미지첨부</label></th>
				    <td>
				        <input type="file" name="imgFile" id="imgFile" title="이미지첨부" onchange="javascript:fnOnChangeImage();">
				    </td>
				</tr>
				<tr>
					<th width="20%" scope="row"><label for="fixAt">고정여부</label></th>
			    	<td>
			    		<input type="radio" name="fixingAt" id="fixingAt" value="Y" />고정			    		
			    		<input type="radio" name="fixingAt" id="fixingAt" value="N" checked />고정안함
			    	</td>
			  	</tr>				
			</table>
			<!-- 검색조건 유지 -->
			<input type="hidden" name="pageIndex" value="<c:out value='${bannerVO.pageIndex}'/>" >
			<input type="hidden" name="areaCode" />
			<input type="hidden" name="bannerImage" >
			
			<!-- 검색조건 유지 -->
			</form> 
				         
			</DIV>
			<!-- /panel body -->
		</DIV> 
		<!-- /.panel panel-default -->	 
		<p class="tr">
			<button type="button" class="btn btn-default" onclick="fnBannerImgMngList(); return false;" ><spring:message code="button.list" /></button>		
			<button type="button" class="btn btn-primary" onclick="fnBannerImgMngRegist(); return false;"><spring:message code="button.create" /></button>												
		</p>    			
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->
</body>
</html>

