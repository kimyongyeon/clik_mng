<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
 /**
  * @Class Name : EgovFaqCnUpdt.jsp
  * @Description : EgovFaqCnUpdt 화면
  * @Modification Information
  * @
  * @ 수정일   		수정자		수정내용
  * @ ----------	------		---------------------------
  *
  *  @author 
  *  @since 
  *  @version
  *  @see
  *
  **/
%>

<%@ page contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html lang="ko">
<head>
<title>FAQ내용수정</title>
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
function fn_egov_updt_faqcn(form, faqId){

	// 서버사이드 테스트용
	/*
		form.action = "<c:url value='/cop/faq/FaqCnUpdt.do'/>";
		form.submit();
		return;
	*/
	
	if (!validateFaqManageVO(form)) {

		return;

	} else {

        // 파일 확장자 체크
        var confirmExt;
        var extYn = true;
         
        if($('#egovComFileList > div').length > 0) {
        	$('#egovComFileList > div').each(function(index,div){
	            if(extYn){
	        		confirmExt = fn_confirmExt(div.textContent);
		            if(!confirmExt) {
		            	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
		            	extYn = false;
		            	return false;
		            }
	            }
        	});
        }
		
		if	(extYn && confirm("수정하시겠습니까?"))	{

			// form.faqId.value = faqId; 주석처리
			form.action = "<c:url value='/cop/faq/FaqCnUpdt.do'/>";
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

function fn_egov_check_file(flag) {
	if(flag=="Y") {
		document.getElementById('file_upload_posbl').style.display = "block";
		document.getElementById('file_upload_imposbl').style.display = "none";
	} else {
		document.getElementById('file_upload_posbl').style.display = "none";
		document.getElementById('file_upload_imposbl').style.display = "block";
	}
}

/* ********************************************************
 * 삭제하기
 ******************************************************** */
function fn_egov_delete_faq(faqId){

	if	(confirm("<spring:message code="common.delete.msg" />"))	{	

		// Delete하기 위한 키값(faqId)을 셋팅
		document.faqManageVO.faqId.value = faqId;	
		document.faqManageVO.action = "<c:url value='/cop/faq/FaqCnDelete.do'/>";
		document.faqManageVO.submit();
			
	}
	
	
}
</script>
</head>
<body onLoad="fn_egov_initl_faqcn();">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">FAQ관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
  	
			<DIV class="panel-heading">
				 FAQ내용수정
			</DIV>


				<!-- 상단타이틀 파일첨부를 위한 폼명 및 Enctype 설정-->
				<form:form commandName="faqManageVO" name="faqManageVO" action="${pageContext.request.contextPath}/cop/faq/FaqCnUpdt.do" method="post"  enctype="multipart/form-data">
				
				<!-- FaqCnUpdtView.do Call을 위한 처리-->
				<input name="faqId" type="hidden" value="<c:out value='${result.faqId}'/>">
				
				<!-- 첨부파일을 위한 Hidden -->
				<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3">
				
				<!-- 첨부파일 삭제 후 리턴 URL -->
				<input type="hidden" name="returnUrl" id="returnUrl" value="<c:url value='/cop/faq/FaqCnUpdtView.do'/>"/>
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<!-- 등록  폼 영역  -->
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered" summary="FAQ에 대한 정보를 수정합니다.">
				  <tr>
				    <th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="qestnSj">질문제목</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td width="80%" nowrap>
				        <form:input path="qestnSj" size="70" maxlength="70" title="질문제목"/>
				    	<div><form:errors path="qestnSj"/></div>
				    </td>
				  </tr>
				
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="qestnCn">질문내용</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td>
				      <form:textarea path="qestnCn" cols="150" rows="15" cssClass="txaClass"  style="width:100%;" title="질문내용"/>
				      <div><form:errors path="qestnCn"/></div>
				    </td>
				  </tr>
				
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="answerCn">답변내용</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td>
				      <form:textarea path="answerCn" cols="150" rows="15" cssClass="txaClass" style="width:100%;" title="답변내용"/>
				      <div><form:errors path="answerCn"/></div>
				    </td>
				  </tr>
				
				 <!-- 첨부목록을 보여주기 위한 -->
				  <c:if test="${result.atchFileId != ''}">
					<tr>
						<th scope="row" height="23" class="required_text">첨부파일 목록&nbsp;&nbsp;</th>
						    <td >
								<c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfsForUpdate.do" >
									<c:param name="param_atchFileId" value="${result.atchFileId}" />
								</c:import>
						    </td>
					</tr>
				  </c:if>
				
					<c:if test="${result.atchFileId == null}">
						<input type="hidden" name="fileListCnt" id="fileListCnt" value="0" >
						<input name="atchFileAt" id="atchFileAt" type="hidden" value="N">
					</c:if>
				
					<c:if test="${result.atchFileId != null}">
						<input name="atchFileAt" id="atchFileAt" type="hidden" value="Y">
					</c:if>
				
				
				
				 <!--첨부화일 업로드를 위한 Start -->
				  <tr>
					<th height="23" class="required_text"><label for="file_1">파일첨부&nbsp;&nbsp;</label></th>
						<td colspan="3">
						    <div id="file_upload_posbl" >
					            <table width="580px" cellspacing="0" cellpadding="0" border="0" align="center" class="UseTable">
								    <tr>
								        <td><input name="file_1" id="egovComFileUploader" type="file" title="파일첨부" /></td>
								    </tr>
								    <tr>
								        <td>
								        	<div id="egovComFileList"></div>
								        </td>
								    </tr>
					   	        </table>
							</div>
							<div id="file_upload_imposbl"  style="display:none;" >
				                <table width="580px" cellspacing="0" cellpadding="0" border="0" align="center" class="UseTable">
				                    <tr>
				                        <td><spring:message code="common.imposbl.fileupload" /></td>
				                    </tr>
				                </table>
				            </div>
						</td>
				  </tr>
				 <!-- 첨부화일 업로드를 위한 end..-->
				
				</table>
				
				<!-- 첨부파일 업로드 가능화일 설정(Update) Start..-->
				<script type="text/javascript">
				
				var existFileNum = null;
					var maxFileNum = null;
				
					if (document.getElementById('fileListCnt') != null) {
					    existFileNum = document.getElementById('fileListCnt').value;
					}
				
					if (document.getElementById('posblAtchFileNumber') != null) {
					    maxFileNum = document.getElementById('posblAtchFileNumber').value;
					}
				
				    if(existFileNum=="undefined" || existFileNum ==null){
				        existFileNum = 0;
				    }
				
				    if(maxFileNum=="undefined" || maxFileNum ==null){
				        maxFileNum = 0;
				    }
				
					var uploadableFileNum = maxFileNum - existFileNum;
				
					if(uploadableFileNum<0) {
						uploadableFileNum = 0;
					}
				
					if(uploadableFileNum != 0){
				
						fn_egov_check_file('Y');
				
						var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), uploadableFileNum );
						multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
				
					}else{
						fn_egov_check_file('N');
					}
				
				</script>
				<!-- 첨부파일 업로드 가능화일 설정(Update) End.-->
				
				
				<p class="tr">
					<button type="button" class="btn btn-default" onclick="fn_egov_inqire_faqcnlist(); return false;"><spring:message code="button.list" /></button>
					<button type="button" class="btn btn-success" onclick="fn_egov_updt_faqcn(document.forms[0],'<c:out value="${result.faqId}"/>'); return false;" /><spring:message code="button.update" /></button>
					<button type="button" class="btn btn-danger" onclick="fn_egov_delete_faq('<c:out value="${result.faqId}"/>'); return false;"><spring:message code="button.delete" /></button>
				
				</p>  	
				
				</form:form>
	
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>
