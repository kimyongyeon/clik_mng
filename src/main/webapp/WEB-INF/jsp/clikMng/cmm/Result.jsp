<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<link href="<c:url value='/css/clikmng/com.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$( document ).ready(function() {
    
    var message = '${message}';
    var return_url = '${return_url}';
    
    if(message != '')
    	alert(message);
	
    try{
	    if(return_url == '')
	    	goBack();
	    else
	    	location.replace(return_url);
    }catch(e){
    	location.replace("/cmm/dashBoard/DashBoard.do");
    }
    
});

function goBack() {
    window.history.back();
}
</script>
</head>
<body>
</body>
</html>
