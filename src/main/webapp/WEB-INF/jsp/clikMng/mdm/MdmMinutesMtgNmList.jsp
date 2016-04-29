<%@ page   contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<option value="">회의명선택</option>
<c:forEach var="cList" items="${mtgNmList}" varStatus="status">
	<option value="${cList.MTGNM_ID}"> ${cList.MTGNM} </option>
</c:forEach>