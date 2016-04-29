<%
 /**
  * @Class Name : EgovHpcmCnUpdt.jsp
  * @Description : EgovHpcmCnUpdt 화면
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
<title>도움말수정</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javascript" src="<c:url value='/js/clikmng/cop/hpc/EgovHpcm.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="hpcmManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_hpcmcn(){

	// 첫 입력란에 포커스..
	hpcmManageVO.hpcmDf.focus();

}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_updt_hpcmcn(form, hpcmId){

	// 서버사이드 테스트용
	/*
		form.action = "<c:url value='/cop/hpc/HpcmCnUpdt.do'/>";
		form.submit();
		return;
	*/

	if (!validateHpcmManageVO(form)) {

		return;

	} else {

		form.hpcmId.value = hpcmId;
		form.action = "<c:url value='/cop/hpc/HpcmCnUpdt.do'/>";
		form.submit();

	}

}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_hpcmlist() {

	hpcmManageVO.action = "<c:url value='/cop/hpc/HpcmListInqire.do'/>";
	hpcmManageVO.submit();

}


</script>
</head>
<body onLoad="fn_egov_initl_hpcmcn();">
<jsp:include page="/cmm/top/top.do" />

<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">권한관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->

	<DIV id="main" style="display:" class="">
	<p class="tr">
		<button type="button" class="btn btn-default" onclick="fn_egov_inqire_hpcmlist(); return false;"><spring:message code="button.list" /></button>
		<button type="button" class="btn btn-success" onclick="fn_egov_updt_hpcmcn(document.forms[0],'<c:out value="${result.hpcmId}"/>'); return false;"><spring:message code="button.update" /></button>		
	</p>	
		<DIV class="">
			<DIV class="panel-heading">
				 도움말수정
			</DIV>
			<!-- /.panel-heading -->			
			<DIV class="panel-body"> 
				<!-- 상단타이틀 -->
				<form:form commandName="hpcmManageVO" action="${pageContext.request.contextPath}/cop/hpc/HpcmCnUpdt.do" method="post">
								
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<!-- 등록  폼 영역  -->
				<table width="90%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover dataTable no-footer" summary="도움말에 대한 정보를 수정합니다.">
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="hpcmSeCode">도움말구분</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td>
						<form:select path="hpcmSeCode" title="도움말구분" class="form-control">
							<form:options items="${resultList}" itemValue="code" itemLabel="codeNm"/>
				 		</form:select>
				 		<div><form:errors path="hpcmSeCode"/></div>
				    </td>
				  </tr>
				
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="hpcmDf">도움말정의</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td>
				      <form:textarea path="hpcmDf" cols="70" rows="5" cssClass="txaClass" title="도움말정의"/>
				      <div><form:errors path="hpcmDf"/></div>
				    </td>
				  </tr>
				
				  <tr>
				    <th scope="row" height="23" class="required_text" ><label for="hpcmDc">도움말설명</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목"></th>
				    <td>
				      <form:textarea path="hpcmDc" cols="70" rows="30" cssClass="txaClass" title="도움말설명"/>
				      <div><form:errors path="hpcmDc"/></div>
				    </td>
				  </tr>
				
				</table>
				
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<input name="hpcmId" type="hidden" value="">
				
				</form:form>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->
		<p class="tr">
			
			<!--  <button type="button" class="btn btn-primary" onclick="javascript:fncAddAuthorInsert()">등록</button>
			      <button type="button" class="btn btn-danger" onclick="fn_egov_delete_hpcmcn('<c:out value="${result.hpcmId}"/>'); return false;"><spring:message code="button.delete" /></button>
			-->
			<button type="button" class="btn btn-success" onclick="fn_egov_updt_hpcmcn(document.forms[0],'<c:out value="${result.hpcmId}"/>'); return false;"><spring:message code="button.update" /></button>		
			<button type="button" class="btn btn-default" onclick="fn_egov_inqire_hpcmlist(); return false;"><spring:message code="button.list" /></button>	
		</p>									
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->

</body>
</html>
