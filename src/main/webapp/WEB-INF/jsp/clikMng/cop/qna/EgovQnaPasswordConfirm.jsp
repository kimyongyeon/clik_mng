<%
 /**
  * @Class Name : EgovQnaPasswordConfirm.jsp
  * @Description : EgovQnaPasswordConfirm 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<base target="_self" />
<title>작성비밀번호 확인</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<%-- <link type="text/css" rel="stylesheet" href="<c:url value='/css/clikmng/cmm/com.css' />" />
<link type="text/css" rel="stylesheet" href="<c:url value='/css/clikmng/cmm/button.css' />" /> --%>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_qnacn(){

	// 작성글 비밀번호 입력란에 포커스..
	document.QnaManageForm.writngPassword.focus();

}

/* ********************************************************
 * 확인처리
 ******************************************************** */
function fn_egov_confirm_qnapassword(){
	/* validation check */
	if (document.QnaManageForm.writngPassword.value == "") {
		alert("비밀번호를 입력해 주세요.!");
		document.QnaManageForm.writngPassword.focus();
		return;
	}
	
	/* var opener = window.dialogArguments; */
	var opener;
 
	if (window.dialogArguments) {
	    opener = window.dialogArguments; // Support IE
	} else {
	    opener = window.opener;    // Support Chrome, Firefox, Safari, Opera
	}

	//  현재화면의 작성 비밀번호
	var	ls_writngPassword = document.QnaManageForm.writngPassword.value;
	
	// 부모화면으로 작성비밀번호를 넘긴다.
	opener.document.QnaManageForm.writngPassword.value = ls_writngPassword;	
	
	// 부모화면으로 결과값을 True 넘긴다.
	window.returnValue = true;	
	window.close();	

		
}

/* ********************************************************
 * 취소처리
 ******************************************************** */
function fn_egov_cancel_qnapassword() {
	
	self.close();
			
}

</script>
</head>

<body style="padding-top:10px;" onLoad="fn_egov_initl_qnacn();">
<!-- 상단타이틀 -->
<form name="QnaManageForm" method="post" onsubmit="fn_egov_confirm_qnapassword();">  

<!-- 작성 비밀번호를 확인하기 위한. ---------------------------->
	  	  
	<!-- 상단 타이틀  영역 -->
	<table width="100%" cellpadding="0" border="0">
		<tr>
	  		<td width="100%">
	   			<h4><img src="<c:url value='/images/clikmng/cmm/icon/tit_icon.gif' />" width="16" height="16" alt="">&nbsp;작성 비밀번호 확인</h4>
	   		</td>
	 	</tr>
	</table>

	<!-- 줄간격조정  -->
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td height="15px"></td>
		</tr>
	</table>

	<!-- 등록  폼 영역  -->
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register">
	<caption style="visibility:hidden;overflow:hidden;position:absolute;top:0;left:0;width:0;height:0;font-size:0;line-height:0">
		작성 비밀번호 확인
	</caption>
		<tr>
			<th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="writngPassword">&nbsp;작성글 비밀번호</label>
		  		<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수항목">
		  	</th>
		  	<td width="80%" nowrap>
		  		<input name="writngPassword" id="writngPassword" type="password" size="20" value="" maxlength="20" title="작성글 비밀번호">  
		  	</td>
		</tr>
	</table>

	<!-- 줄간격조정  -->
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td height="20px"></td>
		</tr>
	</table>


	<!-- 확인/취소버튼  -->
	<table border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td> 
				<button type="button" class="btn btn-primary" onClick="javascript:fn_egov_confirm_qnapassword();">
					<spring:message code="button.confirm" />
				</button>
				<button type="button" class="btn btn-danger" onClick="JavaScript:fn_egov_cancel_qnapassword();">
					<spring:message code="button.reset" />
				</button>
			</td>
		</tr>
	</table>
</form>

</body>
</html>
