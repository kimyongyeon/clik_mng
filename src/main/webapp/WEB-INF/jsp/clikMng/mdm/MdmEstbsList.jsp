<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<option value="">전체</option>
	<c:forEach var="cList" items="${codeEstbsList}" varStatus="status">
		<option value="${cList.LOASM_CODE}" <c:if test="${cList.LOASM_CODE == mdmSearchVO.schLoAsmCode}"> selected="selected"</c:if>> ${cList.LOASM_NM} </option>
	</c:forEach>
