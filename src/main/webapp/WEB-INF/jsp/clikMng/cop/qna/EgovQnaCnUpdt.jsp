<%
 /**
  * @Class Name : EgovQnaCnUpdt.jsp
  * @Description : EgovQnaCnUpdt 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @
  *
  *  @author 
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
<title>Q&amp;A 상세내용 수정</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="qnaManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_qnacn(form, qaId){

	if (!validateQnaManageVO(form)) {

		return;

	} else {

		form.qaId.value = qaId;

		if	(confirm("수정하시겠습니까?"))	{

			form.action = "<c:url value='/cop/qna/QnaCnUpdt.do'/>";
			form.submit();

		}

	}

}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_qnalist() {

// 	qnaManageVO.action = "<c:url value='/cop/qnm/QnaAnswerListInqire.do'/>";
// 	qnaManageVO.submit();
	location.href = "<c:url value='/cop/qnm/QnaAnswerListInqire.do'/>";
}

/**********************************************************
 * 삭제처리화면
 ******************************************************** */
function fn_egov_delete_qnacn(qaId){

	// Delete하기 위한 키값을 셋팅
	qnaManageVO.qaId.value = qaId;	
	
	if	(confirm("삭제하시겠습니까?"))	{
		
		qnaManageVO.action = "<c:url value='/cop/qna/QnaPasswordConfirmDel.do'/>";
 		qnaManageVO.submit();
 		
	}
	
}


</script>
</head>

<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">
	<div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Q&amp;A 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->

	<div id="border" style="display:" class="col-lg-12">
    
    <p class="tr">
		<button type="button" class="btn btn-default" onclick="fn_egov_inqire_qnalist(); return false;"><spring:message code="button.list" /></button>
		<button type="button" class="btn btn-success" onclick="fn_egov_updt_qnacn(document.forms[0],'<c:out value="${result.qaId}"/>'); return false;"><spring:message code="button.update" /></button>
		<button type="button" class="btn btn-danger"  onclick="fn_egov_delete_qnacn('<c:out value="${result.qaId}"/>'); return false;"><spring:message code="button.delete" /></button>
	</p> 
		
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				Q&amp;A 상세내용 수정
			</DIV>
			<!-- /.panel-heading -->
				<DIV id="content">
				<!-- 상단타이틀 -->
				<form:form commandName="qnaManageVO" action="${pageContext.request.contextPath}/cop/qna/QnaCnUpdt.do" method="post">
				
				<input name="qaId" type="hidden" value="<c:out value='${result.qaId}'/>">
				<input name="answerCn" type="hidden" value="Testing...">
				
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<!-- 등록  폼 영역  -->
				<table width="98%" border="1" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover dataTable no-footer" summary="QNA의 내용을 수정합니다.">  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >작성자명&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<form:input path="wrterNm" maxlength="20" title="작성자명"/>
    					<div><form:errors path="wrterNm"/></div>
				    </td>
				  </tr>
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >
				    	<label for="writngPassword">작성 비밀번호</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목">
				    </th>
				    <td width="80%" nowrap>
				    	<input name="writngPassword" type="password" size="20" value="<c:out value='${result.writngPassword}'/>"  maxlength="20" title="작성 비밀번호">
				    </td>
				  </tr>				  
				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" >
				    	<label for="areaNo">전화번호</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목">
				    </th>
				    <td width="80%" nowrap>    
				        <form:input path="areaNo" size="4" maxlength="4" title="전화번호(지역)"/>-<form:input path="middleTelno" size="4" maxlength="4" title="전화번호(국번)"/>-<form:input path="endTelno" size="4" maxlength="4" title="전화번호(지번)"/>
				    	<div><form:errors path="areaNo"/></div>
				    	<div><form:errors path="middleTelno"/></div>
				    	<div><form:errors path="endTelno"/></div>                     
				    </td>
				  </tr> 
<%-- 				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >이메일</th>
				    <td width="80%" nowrap>
				    	<input name="emailAdres" type="text" size="30" value="<c:out value='${result.emailAdres}'/>" maxlength="30" title="이메일">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="emailAnswerAt" type="checkbox" value="Y" <c:if test="${result.emailAnswerAt == 'Y'}">checked</c:if>  title="이메일답변여부"> 이메일답변여부  
				    </td>
				  </tr>
 --%>				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >조회횟수&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<c:out value="${result.inqireCo}"/>  
				    </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >
				    	<label for="qestnSj">질문제목</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목">
					</th>
				    <td width="80%" nowrap>
				    	<form:input path="qestnSj" size="69" maxlength="70"  title="질문제목"/>
    					<div><form:errors path="qestnSj"/></div>  
				    </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" ><label for="qestnCn">질문내용</label>&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>    
				      <form:textarea path="qestnCn" cols="300" rows="20" cssClass="txaClass" style="width:450px;" title="질문내용"/>
				      <div><form:errors path="qestnCn"/></div>                       
				    </td>
				  </tr> 
				</table>
				
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<td height="3px"></td>
					</tr>
				</table>
				

				</form:form>
				</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->
		<p class="tr">	
			<button type="button" class="btn btn-default" onclick="fn_egov_inqire_qnalist(); return false;"><spring:message code="button.list" /></button>
			<button type="button" class="btn btn-success" onclick="fn_egov_updt_qnacn(document.forms[0],'<c:out value="${result.qaId}"/>'); return false;"><spring:message code="button.update" /></button>
			<button type="button" class="btn btn-danger"  onclick="fn_egov_delete_qnacn('<c:out value="${result.qaId}"/>'); return false;"><spring:message code="button.delete" /></button>
		</p> 
	</DIV>	    
    
</DIV>
</html>
