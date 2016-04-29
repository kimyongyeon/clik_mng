<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>메일수신 거부 대상자 목록</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--

-->
</script>
</head>
<body>

 <c:choose>
 	<c:when test="${resultCode eq 0}">
    	<c:out value="${resultMessage }" />
    </c:when>
    <c:otherwise>
      <c:out value="${resultMessage }" />
    </c:otherwise>
</c:choose>


</body>
</html>
