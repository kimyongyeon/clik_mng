<%
 /**
  * @Class Name : EgovQnaAnswerDetailInqure.jsp
  * @Description : EgovQnaAnswerDetailInqure 화면
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
<title>Q&amp;A 상세보기</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_qnaanswerlist() {

// 	document.QnaManageForm.action = "<c:url value='/cop/qnm/QnaAnswerListInqire.do'/>";
// 	document.QnaManageForm.submit();
	location.href = "<c:url value='/cop/qnm/QnaAnswerListInqire.do'/>";	
}


/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_egov_updt_qnacn(qaId){

	// Update하기 위한 키값을 셋팅
	document.QnaManageForm.qaId.value = qaId;	

	var url 	= "<c:url value='/cop/qna/QnaPasswordConfirmView.do'/>";
	var	status 	= "dialogWidth=300px;dialogHeight=160px;resizable=yes;center=yes;";

	
	// 작성비밀번호 확인 화면을 호출한다.
	var returnValue = window.showModalDialog(url, self, status);

	// 결과값을 받아. 결과를 Submit한다.
 	if	(returnValue)	{

 		document.QnaManageForm.action = "<c:url value='/cop/qna/QnaPasswordConfirm.do'/>"; 	 	
 		document.QnaManageForm.submit();
 		
 	}
	
}


/* ********************************************************
 * 답변처리화면
 ******************************************************** */
function fn_egov_updt_qnacnanswer(qaId){

	// Update하기 위한 키값을 셋팅
	document.QnaManageForm.qaId.value = qaId;	


		document.QnaManageForm.action = "<c:url value='/cop/qnm/QnaCnAnswerUpdtView.do'/>";
		document.QnaManageForm.submit();

		
}


function fn_egov_delete_qa(qaId){

	if	(confirm("<spring:message code="common.delete.msg" />"))	{	

		// Delete하기 위한 키값(faqId)을 셋팅
		document.QnaManageForm.qaId.value = qaId;	
		document.QnaManageForm.action = "<c:url value='/cop/qna/QnaCnDelete.do'/>";
		document.QnaManageForm.submit();
			
	}
	
	
}



</script>
</head>
 
<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Q&amp;A 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->

			<DIV class="panel-heading">
				 &nbsp;Q&amp;A 상세보기
			</DIV>

				<!-- 상단타이틀 -->
				<form name="QnaManageForm" action="<c:url value='/cop/qnm/QnaCnAnswerUpdtView.do'/>" method="post">
	
				
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<!-- 등록  폼 영역  -->
				<table width="98%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover dataTable no-footer" summary="Q&amp;A에 대한 답변을 조회합니다.">
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >작성자명&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<c:out value="${result.wrterNm}"/>
				    	<c:out value="${result.passwordConfirmAt}"/>  
				    </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" height="23" class="required_text" >전화&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>    
				      <c:out value="${result.areaNo}"/>-<c:out value="${result.middleTelno}"/>-<c:out value="${result.endTelno}"/>                      
				    </td>
				  </tr> 
<%-- 				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >이메일&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<c:out value="${result.emailAdres}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<input name="emailAnswerAt" type="checkbox" <c:if test="${result.emailAnswerAt == 'Y'}">checked</c:if> title="이메일답변여부"> 이메일답변여부  
				    </td>
				  </tr>
 --%>				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >작성일자&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				  		<c:if test="${result.writngDe != null}">
				    		<c:out value='${fn:substring(result.writngDe, 0,4)}'/>-<c:out value='${fn:substring(result.writngDe, 4,6)}'/>-<c:out value='${fn:substring(result.writngDe, 6,8)}'/>       			   	          				 			   
				 		</c:if>
				    
				  
				    </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >조회횟수&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<c:out value="${result.inqireCo}"/>  
				    </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >진행처리상태&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<c:out value="${result.qnaProcessSttusCodeNm}"/>  
				    </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" height="23" class="required_text" >질문제목&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>    
				      <c:out value="${result.qestnSj}"/>                 
				    </td>
				  </tr> 
				
				  <tr> 
				    <th scope="row" height="23" class="required_text" ><label for="qestnCn">질문내용</label>&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>    
				      <textarea class="textarea"  cols="300" rows="15"  style="width:100%;" readonly title="질문내용"><c:out value="${result.qestnCn}" escapeXml="false"/></textarea>                       
				    </td>
				  </tr> 
				
				<!--답변내용이 있을경우 Display...-->
				<c:if test="${result.qnaProcessSttusCode == '3' || result.answerCn != null}">
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
				
				
				<input name="qaId" type="hidden" value="">
				<input name="writngPassword" 	type="hidden" value="">
				<input name="passwordConfirmAt" type="hidden" value="">
				
				</form>

		<p class="tr">
			<button type="button" class="btn btn-default" onclick="fn_egov_inqire_qnaanswerlist(); return false;"><spring:message code="button.list" /></button>
			<!--   <button type="button" class="btn btn-success" onclick="fn_egov_updt_qnacn('<c:out value="${result.qaId}"/>'); return false;"><spring:message code="button.update" /></button>  -->
			<button type="button" class="btn btn-primary" onclick="fn_egov_updt_qnacnanswer('<c:out value="${result.qaId}"/>'); return false;">답변</button>
			<button type="button" class="btn btn-danger" onclick="fn_egov_delete_qa('<c:out value="${result.qaId}"/>'); return false;">삭제</button>			
		</p>
			<!-- /panel body -->
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>
