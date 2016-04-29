<%
 /**
  * @Class Name : EgovErrAnswerDetailInqure.jsp
  * @Description : EgovErrAnswerDetailInqure 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  *  @author  
  *  @since 
  *  @version 
  *  @see
  *  
  */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>오류신고관리</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_erranswerlist() {

// 	document.ErrManageForm.action = "<c:url value='/cop/err/ErrAnswerListInqire.do'/>";
// 	document.ErrManageForm.submit();
	location.href = "<c:url value='/cop/err/ErrAnswerListInqire.do'/>";	
}


/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_egov_updt_errcn(errorReportId){

	// Update하기 위한 키값을 셋팅
	document.ErrManageForm.errorReportId.value = errorReportId;	

	var url 	= "<c:url value='/cop/err/ErrPasswordConfirmView.do'/>";
	var	status 	= "dialogWidth=300px;dialogHeight=160px;resizable=yes;center=yes;";

	
	// 작성비밀번호 확인 화면을 호출한다.
	var returnValue = window.showModalDialog(url, self, status);

	// 결과값을 받아. 결과를 Submit한다.
 	if	(returnValue)	{

 		document.ErrManageForm.action = "<c:url value='/cop/err/ErrPasswordConfirm.do'/>"; 	 	
 		document.ErrManageForm.submit();
 		
 	}
	
}


/* ********************************************************
 * 답변처리화면
 ******************************************************** */
function fn_egov_updt_errcnanswer(errorReportId){

	// Update하기 위한 키값을 셋팅
	document.ErrManageForm.errorReportId.value = errorReportId;	


		document.ErrManageForm.action = "<c:url value='/cop/err/ErrCnAnswerUpdtView.do'/>";
		document.ErrManageForm.submit();

		
}


function fn_egov_delete_qa(errorReportId){

	if	(confirm("<spring:message code="common.delete.msg" />"))	{	

		// Delete하기 위한 키값(faqId)을 셋팅
		document.ErrManageForm.errorReportId.value = errorReportId;	
		document.ErrManageForm.action = "<c:url value='/cop/err/ErrCnDelete.do'/>";
		document.ErrManageForm.submit();
			
	}
	
	
}



</script>
</head>
 
<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">오류신고관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->

			<DIV class="panel-heading">
				 오류신고 상세보기
			</DIV>

				<!-- 상단타이틀 -->
				<form name="ErrManageForm" action="<c:url value='/cop/err/ErrCnAnswerUpdtView.do'/>" method="post">
	
				
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<!-- 등록  폼 영역  -->
				<table width="98%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover dataTable no-footer" summary="오류신고 상세정보">
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >작성자명&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<c:out value="${result.wrterNm}"/>
				    	<c:out value="${result.passwordConfirmAt}"/>  
				    </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" height="23" class="required_text" >연락처&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>    
				      <c:out value="${result.telno}"/>                      
				    </td>
				  </tr> 
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >이메일&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<c:out value="${result.emailAdres}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%-- 						<input name="emailAnswerAt" type="checkbox" disabled value="Y" <c:if test="${result.emailAnswerAt == 'Y'}">checked</c:if> title="이메일답변여부"> 이메일답변여부   --%>
				    </td>
				  </tr>
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >신고일자&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				  		<c:if test="${result.writngDe != null}">
				    		<c:out value='${fn:substring(result.writngDe, 0,4)}'/>-<c:out value='${fn:substring(result.writngDe, 4,6)}'/>-<c:out value='${fn:substring(result.writngDe, 6,8)}'/>       			   	          				 			   
				 		</c:if>
				    
				  
				    </td>
				  </tr>
				
<!-- 				  <tr>  -->
<!-- 				    <th scope="row" width="20%" height="23" class="required_text" nowrap >조회횟수&nbsp;&nbsp;</th> -->
<!-- 				    <td width="80%" nowrap> -->
<%-- 				    	<c:out value="${result.inqireCo}"/>   --%>
<!-- 				    </td> -->
<!-- 				  </tr> -->
				
<!-- 				  <tr>  -->
<!-- 				    <th scope="row" width="20%" height="23" class="required_text" nowrap >진행처리상태&nbsp;&nbsp;</th> -->
<!-- 				    <td width="80%" nowrap> -->
<%-- 				    	<c:out value="${result.reportProcessSttusNm}"/>   --%>
<!-- 				    </td> -->
<!-- 				  </tr> -->
				
				  <tr> 
				    <th scope="row" height="23" class="required_text" >신고제목&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>    
				      <c:out value="${result.reportSj}"/>                 
				    </td>
				  </tr> 
				
				  <tr> 
				    <th scope="row" height="23" class="required_text" ><label for="qestnCn">신고내용</label>&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>    
				      <textarea class="textarea"  cols="300" rows="15"  style="width:100%;" readonly title="신고내용"><c:out value="${result.reportCn}" escapeXml="false"/></textarea>                       
				    </td>
				  </tr> 
				
				<!--답변내용이 있을경우 Display...-->
				<c:if test="${result.reportProcessSttusCode == '3' || result.answerCn != null}">
				  <tr> 
				    <th scope="row" height="23" class="required_text" ><label for="answerCn">답변내용</label>&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>    
				      <textarea class="textarea"  cols="300" rows="15"  style="width:100%;" readonly title="답변내용"><c:out value="${result.answerCn}" escapeXml="false"/></textarea>                                        
				    </td>
				  </tr> 
				

				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >답변일자&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				  		<c:if test="${result.answerDe != null}">
				    		<c:out value='${fn:substring(result.answerDe, 0,4)}'/>-<c:out value='${fn:substring(result.answerDe, 4,6)}'/>-<c:out value='${fn:substring(result.answerDe, 6,8)}'/>       			   	          				 			   
				 		</c:if>
				    </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >답변자&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<c:out value="${result.lastUpdusrId}"/>
				    </td>
				  </tr>
				
				  <!--  tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >전화번호&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<c:out value="${result.offmTelno}"/>
				    </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >이메일&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<c:out value="${result.aemailAdres}"/>
				    </td>
				  </tr> -->
				
				  
				</c:if>
				   
				   
				</table>
				
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="10px"></td>
				</tr>
				</table>
				
				
				<input name="errorReportId" type="hidden" value="">
				<input name="writngPassword" 	type="hidden" value="">
				<input name="passwordConfirmAt" type="hidden" value="">
				
				</form>

		<p class="tr">
			<button type="button" class="btn btn-default" onclick="fn_egov_inqire_erranswerlist(); return false;"><spring:message code="button.list" /></button>
			<!--   <button type="button" class="btn btn-success" onclick="fn_egov_updt_errcn('<c:out value="${result.errorReportId}"/>'); return false;"><spring:message code="button.update" /></button>  -->
			<%-- 답변한 정보가 없을 경우에만 display --%>
			<c:if test="${result.reportProcessSttusCode != '3' || result.emailAnswerAt != 'Y'}">
			<button type="button" class="btn btn-primary" onclick="fn_egov_updt_errcnanswer('<c:out value="${result.errorReportId}"/>'); return false;">답변</button>
			</c:if>
			<button type="button" class="btn btn-danger" onclick="fn_egov_delete_qa('<c:out value="${result.errorReportId}"/>'); return false;">삭제</button>			
		</p>
			<!-- /panel body -->
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>
