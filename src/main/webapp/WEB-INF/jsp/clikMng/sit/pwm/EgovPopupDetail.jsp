<%--
  Class Name : EgovPopupDetail.jsp
  Description : 팝업창 상세보기
  Modification Information
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/clikmng/cmm/" />
<c:set var="CssUrl" value="${pageContext.request.contextPath}/css/clikmng/cmm/" />
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>팝업창 관리</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<validator:javascript formName="popupManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_PopupManage(){

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_PopupManage(){
	var varFrom = document.popupManageVO;

	if(confirm("<spring:message code="common.save.msg" />")){
		
		varFrom.action =  "<c:url value='/sit/pwm/updtPopup.do' />";
		
		if($("input[name='popupTitleNm']").val() =="") {
	    	alert("팝업창 명은 필수 항목입니다.");
	    	return;
	    }
		
		if($("input[name='fileUrl']").val() =="") {
	    	alert("링크 URL은 필수 항목입니다.");
	    	return;
	    }
		
		if('<c:out value="${fn:length(fileList)}" />' == 0 && $("#file_1").val()=="") {
	    	alert("팝업이미지는 필수 항목입니다.");
	    	return;
	    }
		
		if($("input[name='popupWlc']").val() =="" || $("input[name='popupHlc']").val() =="") {
	    	alert("팝업창 위치는 필수 항목입니다.");
	    	return;
	    }
		
		if($("input[name='popupWSize']").val() =="" || $("input[name='popupHSize']").val() =="") {
	    	alert("팝업창 사이즈는 필수 항목입니다.");
	    	return;
	    }
		
		if($("input[name='ntceBgndeYYYMMDD']").val() =="" || $("input[name='ntceEnddeYYYMMDD']").val() =="") {
	    	alert("팝업창 게시기간는 필수 항목입니다.");
	    	return;
	    }
		
		if($("#file_1").val() != undefined && $("#file_1").val() != "") {
			// 파일 확장자 체크
	        var confirmExt;
	        var thumbext = document.getElementById('file_1').value; 
	        
	        if(document.getElementById('file_1').value != "") {
	            confirmExt = fn_confirmExt(thumbext);
	            if(!confirmExt) {
	            	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
	            	return;
	            }
	        }			
		}
		
		var ntceBgndeYYYMMDD = document.getElementById('ntceBgndeYYYMMDD').value;
		var ntceEnddeYYYMMDD = document.getElementById('ntceEnddeYYYMMDD').value;

		var iChkBeginDe = Number( ntceBgndeYYYMMDD.replaceAll("-","") );
		var iChkEndDe = Number( ntceEnddeYYYMMDD.replaceAll("-","") );

		if(iChkBeginDe > iChkEndDe || iChkEndDe < iChkBeginDe ){
			alert("게시시작일자는 게시종료일자 보다 클수 없고,\n게시종료일자는 게시시작일자 보다 작을수 없습니다. ");
			return;
		}

		varFrom.ntceBgnde.value = ntceBgndeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('ntceBgndeHH') +  fn_egov_SelectBoxValue('ntceBgndeMM');
		varFrom.ntceEndde.value = ntceEnddeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('ntceEnddeHH') +  fn_egov_SelectBoxValue('ntceEnddeMM');

		varFrom.submit();
	}
		
	
	return;
		
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
* RADIO BOX VALUE FUNCTION
******************************************************** */
function fn_egov_RadioBoxValue(sbName)
{
	var FLength = document.getElementsByName(sbName).length;
	var FValue = "";
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			FValue = document.getElementsByName(sbName)[i].value;
		}
	}
	return FValue;
}
/* ********************************************************
* SELECT BOX VALUE FUNCTION
******************************************************** */
function fn_egov_SelectBoxValue(sbName)
{
	var FValue = "";
	for(var i=0; i < document.getElementById(sbName).length; i++)
	{
		if(document.getElementById(sbName).options[i].selected == true){

			FValue=document.getElementById(sbName).options[i].value;
		}
	}

	return  FValue;
}
/* ********************************************************
* PROTOTYPE JS FUNCTION
******************************************************** */
String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, "");
}

String.prototype.replaceAll = function(src, repl){
	 var str = this;
	 if(src == repl){return str;}
	 while(str.indexOf(src) != -1) {
	 	str = str.replace(src, repl);
	 }
	 return str;
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_PopupManage(){
	var varFrom = document.popupManageVO;
	var isResult = false;
	if(confirm("삭제 하시겠습니까?")){
		var url = "<c:url value='/sit/pwm/ajaxPopupManageDelte.do' />";
		var param = {
				popupId: varFrom.popupId.value,
				cmd:"del"
		};
				
		$.ajax({      
				type:"POST",  
				url:url,      
				data:param,      
				success:function(returnValue){   
					alert("삭제 되었습니다.");
					fnGoList();
				},     
				error:function(e){  
					alert("삭제 과정 중 오류가 발생하였습니다. \n잠시 후 다시 시도해주세요.");
					isResult = false;
				}  
	    });
	}else{
		varFrom.cmd.value = '';
	}
	
	return isResult;
}

/* ********************************************************
* 이미지 변경
******************************************************** */
function fncOnChangeImage() {
	var varFrom = document.popupManageVO;
	//varFrom.popupImage.value = varFrom.file_1.value;
}

/* ********************************************************
* 파일 제거
******************************************************** */
function fnDeleteFile(atchFileId, fileSn) {
    if(confirm("첨부파일을 삭제 하시겠습니까?")){
    	var varFrom = document.popupManageVO;
		var popupId 	=  '<c:out value="${popupManageVO.popupId}" />';
		var returnUrl = "/sit/pwm/detailPopup.do?rpopupId="+popupId;	

		varFrom.atchFileId.value = atchFileId;
		varFrom.fileSn.value = fileSn;
		varFrom.returnUrl.value =returnUrl;
		varFrom.action = "<c:url value='/cmm/fms/deleteFileInfs.do'/>";
		varFrom.submit();
    }
}

/* ********************************************************
* 목록보기
******************************************************** */
function fnGoList() {
	location.href = "/sit/pwm/listPopup.do";
}
</script>
</head>
<body onLoad="fn_egov_init_PopupManage()">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">팝업창 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
	
	<div id="border" style="display:" class="">

		<DIV class="">
			<h2>
				 팝업창 상세정보 및 수정
			</h2>
			<!-- /.panel-heading -->			
			<DIV id="content" class="">
				<!--  상단타이틀 -->
				<form:form commandName="popupManageVO" name="popupManageVO" action="${pageContext.request.contextPath}/sit/pwm/updtPopup.do" method="post" enctype="multipart/form-data" >
				<!-- ----------------- 상단 타이틀  영역 -->
				<!--  등록  폼 영역  -->
				
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered" summary="팝업창관리  수정을 제공한다.">
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap ><label id="IdPopupTitleNm">팝업창 명</label><img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
				    <td width="80%">
				      <form:input path="popupTitleNm" size="73" cssClass="form-control input-sm" maxlength="255"/>
				      <form:errors path="popupTitleNm" cssClass="error"/>
				    </td>
				  </tr>
				
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap ><label id="IdFileUrl">링크 URL</label><img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
				    <td width="80%">
				      <form:input path="fileUrl" size="73" cssClass="form-control input-sm" maxlength="255"/>
				      <form:errors path="fileUrl" cssClass="error"/>
				    </td>
				  </tr>
				
				  <tr>
				    <th class="required_text" width="20%" scope="row"><label for="fileUploader">팝업 이미지 등록</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td width="80%">
				    <c:set var="imageBoolean_1" value= "0" />
					<c:forEach var="fileVO" items="${fileList}" varStatus="status">
						<c:if test="${fn:length(fileList) != 0}">
						<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
						<img src="<c:url value='/images/clikmng/cmm/fms/icon/bu5_close.gif' />" 
						width="19" height="18" onClick="fnDeleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" alt="파일삭제">
						
						<br><br>
						<img alt="이미지" width="230" height="150" src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${fileVO.atchFileId}"/>'>
						
						<c:set var="imageBoolean_1" value="1" />
						</c:if>
					</c:forEach>
					<c:if test="${imageBoolean_1 == '0' }">
<%-- 						<c:out value="${imageBoolean_1}" /> --%>
						<!-- <input type="file" name="file_1" id="file_1" title="팝업이미지" onchange="javascript:fncOnChangeImage();"> -->
						<input type="file" name="file_1" id="file_1" title="팝업이미지">
					</c:if>			    
				    </td>
				  </tr>						
				
				  <tr>
				   <th height="23" class="required_text" ><label id="IdPopupWlc">팝업창 위치</label><img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
				    <td>
						가로 <form:input path="popupWlc" size="5" maxlength="10" cssClass="input-sm" style="width:100px;"/> cm  
						<br>세로 <form:input path="popupHlc" size="5" maxlength="10" cssClass="input-sm" style="width:100px;"/> cm
						<form:errors path="popupWlc" cssClass="error"/>
						<form:errors path="popupHlc" cssClass="error"/>
				    </td>
				  </tr>
				
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap><label id="IdNtceBgndeYYYMMDD">팝업창 사이즈</label><img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"><br>(cm)</th>
				    <td>
						WIDTH <form:input path="popupWSize" size="5" maxlength="10" cssClass="input-sm" style="width:100px;"/> cm  
						<br>HEIGHT <form:input path="popupHSize" size="5" maxlength="10" cssClass="input-sm" style="width:100px;"/> cm
						<form:errors path="popupWSize" cssClass="error"/>
						<form:errors path="popupHSize" cssClass="error"/>
				    </td>
				  </tr>
				
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap><label id="IdNtceBgndeYYYMMDD1">게시 기간</label><img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
				    <td width="80%">
				
					<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
				    <input type="text" name="ntceBgndeYYYMMDD" id="ntceBgndeYYYMMDD" size="20" maxlength="10" class="readOnlyClass" value="<c:out value="${fn:substring(popupManageVO.ntceBgnde, 0, 4)}"/>-<c:out value="${fn:substring(popupManageVO.ntceBgnde, 4, 6)}"/>-<c:out value="${fn:substring(popupManageVO.ntceBgnde, 6, 8)}"/>" readonly>
				   
				    <form:select path="ntceBgndeHH">
				        <form:options items="${ntceBgndeHH}" itemValue="code" itemLabel="codeNm"/>
				    </form:select>시
				    <form:select path="ntceBgndeMM">
				        <form:options items="${ntceBgndeMM}" itemValue="code" itemLabel="codeNm"/>
				    </form:select>분
				    &nbsp&nbsp~&nbsp&nbsp
				    <input type="text" name="ntceEnddeYYYMMDD" id="ntceEnddeYYYMMDD" size="20" maxlength="10" class="readOnlyClass" value="<c:out value="${fn:substring(popupManageVO.ntceEndde, 0, 4)}"/>-<c:out value="${fn:substring(popupManageVO.ntceEndde, 4, 6)}"/>-<c:out value="${fn:substring(popupManageVO.ntceEndde, 6, 8)}"/>" readonly>
				    
				    <form:select path="ntceEnddeHH">
				        <form:options items="${ntceEnddeHH}" itemValue="code" itemLabel="codeNm"/>
				    </form:select>시
				    <form:select path="ntceEnddeMM">
				        <form:options items="${ntceEnddeMM}" itemValue="code" itemLabel="codeNm"/>
				    </form:select>분
				    </td>
				  </tr>
				  <tr>
				   <th width="20%" height="23" class="required_text" nowrap ><label id="IdStopVewAt">그만보기 설정 여부</label><img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
				    <td width="80%">
						<input type="radio" name="stopVewAt" value="Y" <c:if test="${popupManageVO.stopVewAt eq 'Y'}">checked</c:if>>Y
						<input type="radio" name="stopVewAt" value="N" <c:if test="${popupManageVO.stopVewAt eq 'N'}">checked</c:if>>N
				    </td>
				  </tr>
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap ><label id="IdStceAt">게시 상태</label><img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
				    <td width="80%">
						<input type="radio" name="ntceAt" value="Y" <c:if test="${popupManageVO.ntceAt eq 'Y'}">checked</c:if>>Y
						<input type="radio" name="ntceAt" value="N" <c:if test="${popupManageVO.ntceAt eq 'N'}">checked</c:if>>N
				    </td>
				  </tr>
				</table>
				

				<form:hidden path="ntceBgnde" />
				<form:hidden path="ntceEndde" />
				<input type="hidden" name="atchFileId" />
				<input type="hidden" name="fileSn" />
				<input type="hidden" name="returnUrl" />
				<input name="popupId" type="hidden" value="${popupManageVO.popupId}">
				<input name="cmd" type="hidden" value="<c:out value='save'/>"/>
				<input name="confirmFile" type="hidden" value="<c:out value="${imageBoolean_1}" />"/>
				</form:form>
			</DIV>
			<script>setCal("ntceBgndeYYYMMDD","ntceEnddeYYYMMDD");</script>
			
					<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->	 
		<p class="tr">		
			<button type="button" class="btn btn-default" onclick="javaScript:fnGoList();"><spring:message code="button.list" /></button>
			<button type="button" class="btn btn-primary" onclick="fn_egov_save_PopupManage(); return false;"><spring:message code="button.update" /></button>
			<button type="button" class="btn btn-danger" onclick="fn_egov_delete_PopupManage(); return false;"><spring:message code="button.delete" /></button>														
		</p>    			
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->						

</body>
</html>
