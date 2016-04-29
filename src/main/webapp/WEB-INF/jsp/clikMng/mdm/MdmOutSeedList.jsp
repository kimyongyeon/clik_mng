<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<option value="">-- 게시판 선택 --</option>
<c:forEach var="cList" items="${codeOutSeedList}" varStatus="status">
	<option value="${cList.SEEDID}" <c:if test="${cList.SEEDID == seedId}"> selected="selected"</c:if>> ${cList.SEEDNM} </option>
</c:forEach>