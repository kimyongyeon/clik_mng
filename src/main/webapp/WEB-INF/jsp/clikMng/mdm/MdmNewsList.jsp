<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% pageContext.setAttribute("LF", "\n"); %>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>

<title>환경설정</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--

-->
</script>
</head>

<body>

	<div class="container">

		<div class="row">
			ftp 파일 갯수 : ${fn:length(fileList) }
		</div>
		<div class="row">
			리모트 xml 파일 갯수 : ${fn:length(remoteFileList) }
		</div>
		<div class="row">
			로컬 파일 갯수 : ${fn:length(localFileList) }
		</div>
		<div class="row">
			뉴시스 찾음 갯수 : ${fn:length(newsysList) }
		</div>
		<div class="row">
			연합뉴스 찾음 갯수 : ${fn:length(yonhapList) }
		</div>
		
		<c:forEach var="x" items="${searchList}" varStatus="s">
			<div class="row">
				<div class="col-sm-2">${s.index}</div>
				<div class="col-sm-2">${x.region}</div>
				<div class="col-sm-2">${x.regionNm}</div>
				<div class="col-sm-3">${x.title}</div>
				<div class="col-sm-5"><${fn:replace(x.content, LF, '<br>')}</div>
			</div>
		</c:forEach>
		
<% /*		
		<c:forEach var="x" items="${categoryList1}" varStatus="s">
			<div class="row">
				<div class="col-sm-2">${s.index}</div>
				<div class="col-sm-8">${x}</div>
			</div>
		</c:forEach>
		
		<c:forEach var="x" items="${localFileList}" varStatus="s">
			<div class="row">
				<div class="col-sm-2">${s.index}</div>
				<div class="col-sm-8">${x}</div>
			</div>
		</c:forEach>
		*/ %>
df


	</div>
	
</body>
</html>
