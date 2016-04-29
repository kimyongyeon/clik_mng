<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="imagetoolbar" content="no" />
<meta name="robots" content="index,follow" />
<meta name="classification" content="internet" />
<meta name="language" content="ko" />
<title>${ViewTitle}</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/clikmng/minutesviewer/jquery_ui.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/clikmng/minutesviewer/base.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/clikmng/minutesviewer/print.css'/>"/>
<script src="<c:url value='/js/clikmng/minutesviewer/jquery-1.11.1.min.js' />"></script>
<script src="<c:url value='/js/clikmng/minutesviewer/ui/jquery-ui.js' />"></script>
<script src="<c:url value='/js/clikmng/minutesviewer/minutes.js' />"></script>
</head>
<body>
	<div id="printarea">
		<div id="viewer">
			${ViewText}
		</div>
	</div>
</body>
</html>