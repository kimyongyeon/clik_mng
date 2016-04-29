<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>로그인</title>

<script type="text/javaScript" language="javascript">

function press(event) {
	if (event.keyCode==13) {
		actionLogin();
	}
}

function actionLogin() {

    if (document.loginForm.id.value =="") {
        alert("아이디를 입력하세요");
    } else if (document.loginForm.password.value =="") {
        alert("비밀번호를 입력하세요");
    } else {
        document.loginForm.action="<c:url value='/uat/uia/actionLogin.do'/>";
        document.loginForm.submit();
    }
}




</script>
<style type="text/css">
h1{color:#fff; margin-top:30px; text-align:center; font-size:24px;} 
.login-panel{ margin-top: 20px;}
</style>

</head>
 
<body style="background:#666;">
<form name="loginForm" id="loginForm" method="post">

    <div class="container">
        <div class="row">
        	<h1>[국회 · 지방의회 의정자료 공유 통합시스템]</h1>
            <div class="col-md-4 col-md-offset-4">
            	
                <div class="login-panel panel panel-default">
                	
                    <div class="panel-heading"> 
                        <h3 class="panel-title">로그인을 해주세요</h3>
                    </div> 
                    <div class="panel-body">
<!--                         <form role="form"> -->
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="id" name="userId" id="userId" type="id" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="password" name="password" id="password" type="password" value="" onkeypress="press(event);"  />
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <c:if test="${message ne null }">
                                <div id="checkLogin" > 
                                		<font color="red"><c:out value="${message }" /></font>
                                </div>
                                </c:if>
                                <a href="#LINK" onclick="actionLogin(); return false;" class="btn btn-lg btn-success btn-block">Login</a>
                            </fieldset>
<!--                         </form> -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</form>
</body>
</html>


