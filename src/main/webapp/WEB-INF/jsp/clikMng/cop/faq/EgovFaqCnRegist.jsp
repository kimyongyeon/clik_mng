<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : EgovFaqCnRegist.jsp
  * @Description : EgovFaqCnRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  *
  *  @author 
  *  @since 
  *  @version 
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>FAQ 등록</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="faqManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_faqcn(){

	// 첫 입력란에 포커스..
	faqManageVO.qestnSj.focus();

}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_faqcn(form){

	// 서버사이드 테스트용
	/*
		form.action = "<c:url value='/cop/faq/FaqCnRegist.do'/>";
		form.submit();
		return;
	*/

	if (!validateFaqManageVO(form)) {

		return;

	} else {

        // 파일 확장자 체크
        var confirmExt;
        var thumbext = document.getElementById('egovComFileUploader').value; 
         
        if(document.getElementById('egovComFileUploader').value != "") {
            confirmExt = fn_confirmExt(thumbext);
            if(!confirmExt) {
            	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
            	return;
            }
        }
		
		
		if	(confirm("등록하시겠습니까?"))	{

			form.action = "<c:url value='/cop/faq/FaqCnRegist.do'/>";
			form.submit();

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

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_faqcnlist() {

	faqManageVO.action = "<c:url value='/cop/faq/FaqListInqire.do'/>";
	faqManageVO.submit();

}

</script>

</head>
<body onLoad="fn_egov_initl_faqcn();">
<jsp:include page="/cmm/top/top.do" />
<div id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">FAQ 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
   	
	<div id="border" style="display:" class="">
	 	
		<div class="">
			<div class="panel-heading">
				 FAQ 등록
			</div>
			<!-- /.panel-heading -->			
			<div id="content" class="">	

				<!-- 상단타이틀(파일첨부를 위한 폼명 및 Enctype 설정 -->
				<form:form commandName="faqManageVO" action="${pageContext.request.contextPath}/cop/faq/FaqCnRegist.do" method="post" enctype="multipart/form-data">
				
				<!-- 첨부파일을 위한 Hidden -->
				<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3">
								
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<!-- 등록  폼 영역  -->
				<table width="90%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered" summary="FAQ에 대한 정보를 등록합니다.">
				  <tr>
				    <th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="qestnSj">질문제목</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td >
				        <form:input path="qestnSj" size="70" maxlength="70" title="질문제목"/>
				    	<div><form:errors path="qestnSj"/></div>
				    </td>
				  </tr>
				  
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="qestnCn">질문내용</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td>
				      <form:textarea path="qestnCn" cols="150" rows="15" cssClass="txaClass"  title="질문내용"/>
				      <div><form:errors path="qestnCn"/></div>
				    </td>
				  </tr>
				
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="answerCn">답변내용</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td>
				      <form:textarea path="answerCn" cols="150" rows="15" cssClass="txaClass" title="답변내용"/>
				      <div><form:errors path="answerCn"/></div>
				    </td>
				  </tr>
				
				
				<!--첨부파일 테이블 레이아웃 설정 Start..-->
				  <tr>
					<th scope="row" height="23" class="required_text"><label for="file_1">파일첨부</label><img src="<c:url value='/images/clikmng/cmm/icon/no_required.gif' />" width="15" height="15" alt="부가항목"></th>
					<td>
				    	<table width="580px" cellspacing="0" cellpadding="0" border="0" align="center" class="UseTable">
							<tr>
								<td><input name="file_1" id="egovComFileUploader" type="file" title="파일첨부"/></td>
							</tr>
							<tr>
								<td>
							    	<div id="egovComFileList"></div>
							    </td>
							</tr>
				   	    </table>
					 </td>
				  </tr>
				<!--첨부파일 테이블 레이아웃 End.-->
				
				
				
				</table>
				
				<!--첨부파일 업로드 가능화일 설정 Start..-->
				<script type="text/javascript">
				var maxFileNum = document.getElementById('posblAtchFileNumber').value;
				
				   if(maxFileNum==null || maxFileNum==""){
				
				     maxFileNum = 3;
				
				    }
				
				   var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
				
				   multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
				
				</script>
				<!-- 첨부파일 업로드 가능화일 설정 End.-->
				
				<p class="tr">
					<button type="button" class="btn btn-default" onclick="fn_egov_inqire_faqcnlist(); return false;"><spring:message code="button.list" /></button>
					<button type="button" class="btn btn-primary" onclick="fn_egov_regist_faqcn(document.forms[0]); return false;"><spring:message code="button.create" /></button>
				</p>
	
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="5px"></td>
				</tr>
				</table>				
				
				</form:form>
			</div>
			<!-- /panel body -->
		</div>
		<!-- /.panel panel-default -->	 
	</div>		
	<!-- /.MAIN -->		
</div>	
<!-- /page-wrapper -->	
</body>
</html>
