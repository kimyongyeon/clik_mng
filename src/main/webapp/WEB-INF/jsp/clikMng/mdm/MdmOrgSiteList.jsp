<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<option value="">-- 기관명 --</option>
<c:forEach var="cList" items="${codeSiteList}" varStatus="status">
	<option value="${cList.SITEID}" <c:if test="${cList.SITEID == mdmSearchVO.schOrgCodeStep4}"> selected="selected"</c:if>> ${cList.SITENM} </option>
</c:forEach>