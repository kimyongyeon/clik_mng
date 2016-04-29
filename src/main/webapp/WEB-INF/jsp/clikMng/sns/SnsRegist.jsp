<%--
  Class Name : SnsRegist.jsp
  Description : sns 소통센터관리 등록 페이지
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>SNS 소통센터관리 등록</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<validator:javascript formName="snsManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_save_SnsManage(){
	if($('#snsAcntId').val() == ''){
		alert('SNS 계정 ID를 입력해주세요.');
		$('#snsAcntId').focus();
		return;
	}
	if($('#asembyNm').val() == ''){
		alert('성명을 입력해주세요.');
		$('#asembyNm').focus();
		return;
	}
	
	var varForm = document.snsManageVO;

	if(confirm("<spring:message code="common.save.msg" />")){
// 		varForm.action =  "<c:url value='/sns/registSnsCmmCen.do' />";
// 		varForm.submit();
		
		$.ajax({
		   type: "POST",
		   url : "/sns/registSnsCmmCen.do",
		   dataType:"json", 
		   async : false,
		   data : {
			   "cmd":"save"
			   ,"snsSeCode":$('#snsSeCode').val()
			   ,"snsAcntId":$('#snsAcntId').val()
			   ,"asembyNm":$('#asembyNm').val()
			   ,"useAt":$('input[type="radio"]:checked').val()
			   ,},
		   success: function(result) {
			   if(result[0].code == "success")
			   {
				   alert(result[0].message);
				   location.href = "/sns/SnsCmmCenList.do";
			   }
			   else
		   	   {
				   alert(result[0].message);
		   	   }
		   }
		});
	}
}

/* ********************************************************
* 목록화면
******************************************************** */
function fnGoList() {
    location.href = "<c:url value='/sns/SnsCmmCenList.do'/>";
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
</script>
</head>
<body>
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">SNS 소통센터 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
   	
	<div id="border" style="display:" class="">
	<p class="tr">		
		<a href="<c:url value='/sns/SnsCmmCenList.do' />"><button type="button" class="btn btn-default"><spring:message code="button.list" /></button></a>		
		<button type="button" class="btn btn-primary" onclick="JavaScript:fn_save_SnsManage();"><spring:message code="button.create" /></button>		
	</p> 	
		<DIV class="">
			<h2>
				 SNS 소통센터관리 등록
			</h2>
			<!-- /.panel-heading -->			
			<DIV id="content" class="">	
				<!--  상단타이틀 -->
				<form:form commandName="snsManageVO" name="snsManageVO" action="${pageContext.request.contextPath}/sns/registSnsCmmCen.do" method="post" >
				<!--  줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0" summary="화면 줄간격을 조정한다.">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				<!--  등록  폼 영역  -->
				
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered" summary="SNS 소통센터관리  입력을 제공한다.">
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap ><label id="IdPopupTitleNm">SNS 구분</label><img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
				    <td width="80%">
				    	<select id="snsSeCode" name="snsSeCode">
<!-- 						    <option value="FB">FACEBOOK</option> -->
						    <option value="TW">TWITTER</option>
						</select>
				    </td>
				  </tr>
				
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap ><label id="IdFileUrl">SNS 계정 ID</label><img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
				    <td width="80%">
				      <form:input path="snsAcntId" size="73" cssClass="form-control input-sm" maxlength="255"/>
				      <form:errors path="snsAcntId" cssClass="error"/>
				    </td>
				  </tr>
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap ><label id="IdName">성명</label><img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
				    <td width="80%">
				      <form:input path="asembyNm" size="73" cssClass="form-control input-sm" maxlength="255"/>
				      <form:errors path="asembyNm" cssClass="error"/>
				    </td>
				  </tr>
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap ><label id="IdStopVewAt">사용 설정 여부</label><img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
				    <td width="80%">
						<input type="radio" name="useAt" value="Y" checked>사용
						<input type="radio" name="useAt" value="N">미사용
				    </td>
				  </tr>
				</table>
				
				<!--  줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0" summary="화면 줄간격을 조정한다.">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>

				<input name="cmd" type="hidden" value="<c:out value='save'/>"/>
				</form:form>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->	 
		<p class="tr">		
			<button type="button" class="btn btn-default"  onclick="javascript:fnGoList();"><spring:message code="button.list" /></button>		
			<button type="button" class="btn btn-primary" onclick="JavaScript:fn_save_SnsManage();"><spring:message code="button.create" /></button>		
		</p>    			
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>
