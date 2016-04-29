<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>

<!-- 협정기관과 약정기관 같이 사용하는 팝업 :: -->
<c:choose>
	<c:when test="${flag == 'A'}">
		<c:set var="title_name" value="협정기관" />
	</c:when>
	<c:otherwise>
		<c:set var="title_name" value="약정기관" />
	</c:otherwise>
</c:choose>
<!-- 협정기관과 약정기관 같이 사용하는 팝업 :: -->
 
<title>${title_name} 변경이력</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-mobile-web-app-capable" content="yes" />

<script language="javascript" src="<c:url value='/js/clikmng/jquery-1.10.2.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/bootstrap.min.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/jquery.metisMenu.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/sb-admin.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/jquery-ui.min.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/cmm/common.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/respond_src.js' />"></script>

<!-- Core CSS - Include with every page -->
<link href="<c:url value='/css/clikmng/bootstrap.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/jquery-ui.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/sb-admin.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/font-awesome.css' />" rel="stylesheet" type="text/css">

<style type="text/css">
/**/
h1{margin:0;padding:0;}
body.popup{ position:relative; overflow:hidden;padding:15px;}
div.today{  position:absolute;left:0; bottom:0; background:#f1f1f1; padding:10px; text-align:right; width:100%; border-top:1px solid #ccc;}
div.today label{color:#333;}
div.today a{color:#333;}

div.table-responsive{ }
h1{ margin-left:10px; font-size:18px; font-weight:bold;}
</style>

</head>
<body class="popup">
<h1 class="">${title_name } 변경이력</h1>
<!-- /.panel-heading -->
<div class="panel-body ">
	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"> ${title_name} : ${ info_name }
	<div class="table-responsive" style="overflow:auto;">
			<table class="table table-striped table-bordered table-hover ">
				 <colgroup>
					<col width="10%" />
					<col width="33%" />
					<col width="33%" />
					<col />
				 </colgroup>
				
				<thead>
				<tr>	
	                <th>변경일</th>
	                <th>변경전 기관명</th>
	                <th>변경후 기관명</th>
	                <th>변경자</th>						
				</tr>
				
				<tbody>
				<tr>	
                <c:forEach items="${ list }" var="x">
                    <tr>
                       <td>${x.change_date }</td>
                       <td>${x.name_before }</td>
                       <td>${x.name_after }</td>
                       <td>${x.update_by }</td>
                    </tr>
                </c:forEach>
				</tr>				
				</tbody>
			</table>
		</div>
	<!-- /.table-responsive -->
</div>
<!-- /.panel-body -->

<p class="tc">
	<button type="button" class="btn btn-primary" onclick="javascript:window.close();">닫기</button>
</p>

</body>
</html>
