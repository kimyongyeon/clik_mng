<%
 /**
  * @Class Name : EgovErrCnAnswerUpdt.jsp
  * @Description : EgovErrCnAnswerUpdt 화면
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>오류신고관리</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="errManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_errcnanswer(){

	// 첫 입력란에 포커스..
	errManageVO.answerCn.focus();

}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_errcnanswer(form, errorReportId){

	// 서버사이드 테스트용
	/*
		form.action = "<c:url value='/cop/err/ErrCnAnswerUpdt.do'/>";
		form.submit();
		return;
	*/

// 	if (!validateErrManageVO(form)) {

// 		return;

// 	} else {

		form.errorReportId.value = errorReportId;

		if($('#answerCn_temp').val() == ""){
			alert("답변내용은(는) 필수 입력값입니다.");
			return false;
		}
		
		if	(confirm("답변 작성(저장) 시 오류신고자 이메일 주소로 답변 내용이 전송됩니다. 저장하시겠습니까?"))	{
			
			form.action = "<c:url value='/cop/err/ErrCnAnswerUpdt.do'/>";
			
			form.reportCn.value = encodeURIComponent(htmlEntityEnc($('#reportCn_temp').val())).replace(/[!'()*]/g, escape);
			form.answerCn.value = encodeURIComponent(htmlEntityEnc($('#answerCn_temp').val())).replace(/[!'()*]/g, escape);
			
			form.submit();

		}

// 	}

}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_erranswerlist() {

// 	errManageVO.action = "<c:url value='/cop/err/ErrAnswerListInqire.do'/>";
// 	errManageVO.submit();
	location.href = "<c:url value='/cop/err/ErrAnswerListInqire.do'/>";
}

/* ********************************************************
 * 메일발송을 위한 화면을 호출
 ******************************************************** */
function fn_egov_pop_mailform() {
/*
	var url 	= "<c:url value='/ems/insertSndngMailView.do'/>";
	var	status 	= "width=640,height=480,top=200,left=200";


	window.open(url,'popup', status);
*/
}

/* ********************************************************
 * 특수문자 변환
 ******************************************************** */
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

</script>
</head>
<body onLoad="fn_egov_initl_errcnanswer();">
<jsp:include page="/cmm/top/top.do" />
<div id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">오류신고관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->

		<div class="">
			<div class="panel-heading">
				 오류신고 답변
			</div>
			<div>
			<font style="color:red;">답변 작성(저장) 시 오류신고자 이메일 주소로 답변 내용이 전송됩니다.</font>
			</div>
				<!-- 상단타이틀 -->
				<form:form commandName="errManageVO" action="${pageContext.request.contextPath}/cop/err/ErrCnAnswerUpdt.do" method="post">
				
				<!-- hidden 화일정의-->
				<input name="errorReportId" type="hidden" value="<c:out value='${result.errorReportId}'/>">
				<input name="writngPassword" type="hidden" value="<c:out value='${result.writngPassword}'/>">
								

				
				<!-- 등록  폼 영역  -->
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover dataTable no-footer" summary="">
				  <tr>
				    <th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="wrterNm">작성자명</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td width="80%" nowrap>
<%-- 				    	<input name="wrterNm" type="text" size="22" readonly value="<c:out value='${result.wrterNm}'/>" maxlength="20" title="작성자명"> --%>
				    	<c:out value='${result.wrterNm}'/>
				    </td>
				  </tr>
				
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="telno">전화번호</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td>
<%-- 						<input name="areaNo" type="text" size="5" 		readonly value="<c:out value='${result.areaNo}'/>" maxlength="5" title="전화번호(지역)">- --%>
<%-- 						<input name="middleTelno" type="text" size="5" 	readonly value="<c:out value='${result.middleTelno}'/>" maxlength="5" title="전화번호(국번)">- --%>
<%-- 						<input name="endTelno" type="text" size="5" 	readonly value="<c:out value='${result.endTelno}'/>" maxlength="5" title="전화번호(지번)"> --%>
						<c:out value="${result.telno}"/>   
				    </td>
				  </tr>
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="emailAdres">이메일</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td>
						<input name="emailAdres" type="text" size="30" readonly value="<c:out value='${result.emailAdres}'/>" maxlength="30" title="이메일">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%-- 						<input name="emailAnswerAt" type="checkbox" disabled value="Y" <c:if test="${result.emailAnswerAt == 'Y'}">checked</c:if> title="이메일답변여부"> 이메일답변여부 --%>
				    </td>
				  </tr>
				  <%--
 				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >진행처리상태&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<select id="reportProcessSttusCode" name="reportProcessSttusCode">
				    	<c:forEach var="item" items="${resultList }">
				    		<option value="${item.code }" <c:if test="${item.code == result.reportProcessSttusCode }">selected="selected"</c:if>>${item.codeNm }</option>
				    	</c:forEach>
				    	</select>
				    </td>
				  </tr>
				   --%>	
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="qestnSj">신고제목</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td width="80%" nowrap>
<%-- 				    	<input name="qestnSj" type="text" size="70" readonly value="<c:out value="${result.qestnSj}"/>" maxlength="70" title="질문제목"> --%>
						<c:out value="${result.reportSj}"/>
				    </td>
				  </tr>
				
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="reportCn">신고내용</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td width="80%" nowrap>
				      <textarea id="reportCn_temp" class="textarea"  cols="150" rows="20"  readonly style="width:100%;" title="신고내용"><c:out value="${result.reportCn}" escapeXml="false"/></textarea>
				      <textarea id="reportCn" name="reportCn" class="textarea"  cols="150" rows="20"  readonly style="display:none;" title="신고내용"><c:out value="${result.reportCn}" escapeXml="false"/></textarea>
				    </td>
				  </tr>
				 <!--  
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="reportProcessSttusCode">진행처리상태</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td>
				     	<select name="reportProcessSttusCode" class="select" title="진행처리상태">
				  		   <option selected value="<c:out value='${result.reportProcessSttusCode}'/>"><c:out value='${result.reportProcessSttusNm}'/>&nbsp;&nbsp;</option>
							<c:forEach var="result" items="${resultList}" varStatus="status">
								<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
							</c:forEach>
				  		</select>
				
				    </td>
				  </tr>
				 -->
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="cnsltCn">답변내용<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></label></th>
				    <td width="80%" nowrap>
				     <textarea id="answerCn_temp" class="textarea"  cols="150" rows="20"  style="width:100%;" title="답변내용"><c:out value="${result.answerCn}" escapeXml="false" /></textarea>
				      <form:textarea path="answerCn" cols="150" rows="20" style="width:100%; display:none;" cssClass="txaClass" title="답변내용"/>
				      <div><form:errors path="answerCn"/></div>
				    </td>
				  </tr>
				  
				  <!--  
 				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >답변자&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<input name="emplyrNm" type="text" size="70" readonly value="<c:out value="${loginVO.mngrId}"/>" />
				    </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >전화번호&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<input name="offmTelno" type="text" size="70"  value="<c:out value="${loginVO.mngrDept}"/>" />
				    </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >이메일&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<input name="aemailAdres" type="text" size="70"  value="<c:out value="${loginVO.mngrEmail}"/>" />
				    </td>
				  </tr>	
				  
				   -->			  
				
				</table>
				

								
				</form:form>

			<p class="tr">
				<button type="button" class="btn btn-default" onclick="fn_egov_inqire_erranswerlist(); return false;"><spring:message code="button.list" /></button>
				<button type="button" class="btn btn-primary" onclick="fn_egov_updt_errcnanswer(document.forms[0],'<c:out value="${result.errorReportId}"/>'); return false;">저장</button>						
			</p>			
			<!-- /panel body -->
	</div>
</div>	
<!-- /page-wrapper -->	
</body>
</html>
