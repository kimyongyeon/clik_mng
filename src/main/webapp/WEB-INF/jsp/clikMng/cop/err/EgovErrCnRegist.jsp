<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 /**
  * @Class Name : EgovQnaCnRegist.jsp
  * @Description : EgovQnaCnRegist 화면
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

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>오류신고관리</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<validator:javascript formName="qnaManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_qnacn(){

	// 첫 입력란에 포커스..
	qnaManageVO.writngPassword.focus();

}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_qnacn(form){

	// 서버사이드 테스트용

	if (!validateQnaManageVO(form)) {

			return;

	} else {
			if	(confirm("등록하시겠습니까?"))	{

				form.action = "<c:url value='/cop/qna/QnaCnRegist.do'/>";
				form.submit();

			}

	}

}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_qnalist() {

	qnaManageVO.action = "<c:url value='/cop/qnm/QnaAnswerListInqire.do'/>";
	qnaManageVO.submit();


}

</script>

</head>
<body onLoad="fn_egov_initl_qnacn();">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">오류신고관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
   	
	<div id="border" style="display:" class="col-lg-12">
	<p class="tr">
		<button type="button" class="btn btn-default" onclick="fn_egov_inqire_qnalist(); return false;"><spring:message code="button.list" /></button>
		<button type="button" class="btn btn-primary" onclick="fn_egov_regist_qnacn(document.forms[0]); return false;"><spring:message code="button.create" /></button>
	</p> 	
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				 &nbsp;Q&amp;A 등록
			</DIV>
			<!-- /.panel-heading -->	
				<DIV id="content">
				<!-- 상단타이틀 -->
				<form:form commandName="qnaManageVO" action="${pageContext.request.contextPath}/cop/qna/QnaCnRegist.do" method="post" >
				
				<input name="answerCn" type="hidden" value="Testing...">

				
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<!--  등록  폼 영역  -->
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover dataTable no-footer" summary="Q&amp;A에 대한 정보를 등록합니다.">
				  <tr>
				    <th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="wrterNm">작성자명</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td width="80%" nowrap>
				        <form:input path="wrterNm" size="20" maxlength="20" title="작성자명"/>
				    	<div><form:errors path="wrterNm"/></div>
				    </td>
				  </tr>
				
				  <tr>
				    <th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="writngPassword">작성 비밀번호</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td width="80%" nowrap>
				        <form:password path="writngPassword" size="20" maxlength="20"  title="작성 비밀번호"/>
				    	<div><form:errors path="writngPassword"/></div>
				    </td>
				  </tr>
				
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="telno">전화번호</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td>
				        <form:input path="telno" size="4" maxlength="30" title="전화번호"/>
				    	<div><form:errors path="telno"/></div>
				    </td>
				
				  </tr>
<%-- 				
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="emailAdres">이메일</label><img src="<c:url value='/images/clikmng/cmm/icon/no_required.gif' />" width="15" height="15" alt="선택항목"></th>
				    <td>
						<input name="emailAdres" 	type="text" size="30" value="<c:out value='${result.emailAdres}'/>" maxlength="30" title="이메일">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="emailAnswerAt" type="checkbox" value="Y" title="이메일답변여부"> 이메일답변여부
				    </td>
				  </tr>
 --%>				
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="qestnSj">질문제목</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td width="80%" nowrap>
				        <form:input path="qestnSj" size="70" maxlength="70"  title="질문제목"/>
				    	<div><form:errors path="qestnSj"/></div>
				    </td>
				  </tr>
				
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="qestnCn">질문내용</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td width="80%" nowrap>
				      <form:textarea path="qestnCn" cols="150" rows="20" cssClass="txaClass"  title="질문내용"/>
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
			</DIV>
			<!-- /panel body -->
			<p class="tr">
				<button type="button" class="btn btn-default" onclick="fn_egov_inqire_qnalist(); return false;"><spring:message code="button.list" /></button>
				<button type="button" class="btn btn-primary" onclick="fn_egov_regist_qnacn(document.forms[0]); return false;"><spring:message code="button.create" /></button>
			</p>			
		</DIV>
		<!-- /.panel panel-default -->	
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>
