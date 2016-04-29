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
 
<title>직원검색 팝업</title>
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

<script type="text/javaScript" defer="defer">
<!--
/* ********************************************************
 * 검색하기
 ******************************************************** */
function Search(){
	var varForm = document.listForm;
	if (varForm.searchKeyword.value == null || varForm.searchKeyword.value == '') {
		alert('검색어를 입력해 주세요.');
		return;
	}
	varForm.action = "<c:url value='/docs/SearchEmp.do' />";
	varForm.submit();
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************* */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/docs/SearchEmp.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
 * 부모창에 아이디와 이름 집어넣기
 ******************************************************* */
function pasteMother(userId, name, email, deptNm){

	opener.document.getElementById('mngrId').value = userId;
	opener.document.getElementById('mngrNm').value = name;
	opener.document.getElementById('mngrEmail').value = email;
	opener.document.getElementById('mngrDept').value = deptNm;
/* 
	opener.document.getElementById('mngrIdTxt').value = userId;
	opener.document.getElementById('mngrNmTxt').value = name;
	opener.document.getElementById('mngrEmailTxt').value = email;
	opener.document.getElementById('mngrDeptTxt').value = deptNm;
 */
 
 	opener.parent.hideTxt();

	window.close();
}

-->
</script>

</head>
<body class="popup">
<h1 class="">직원검색</h1>
<!-- /.panel-heading -->
<div class="panel-body ">
	<form:form name="listForm" action="" method="post">
	<%-- <img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"> ${title_name} : ${ info_name } --%>

	<!-- ::::::: 직원검색 시작 ::::::: -->
	
	<div class="search">
		<select name="searchCondition" title="검색조건">      
				<%-- <option value='userid' <c:if test="${searchCondition =='userid'}">selected</c:if>>아이디</option> --%>
				<option value='name' <c:if test="${searchCondition =='name'}">selected</c:if>>이름</option>
				<option value='email' <c:if test="${searchCondition =='email'}">selected</c:if>>이메일</option>
     		</select>
  			<input type='text' name='searchKeyword' value="<c:out value='${searchKeyword}'/>" onKeyDown="javascript:if(event.keyCode==13){Search();}" class="ip input-sm input-search">
  			<a href="#LINK" onclick="javascript:Search(); return false;"><button type="button" class="btn btn-primary"><spring:message code="button.search" /></button></a>
	</div>
	<!-- ::::::: 직원검색 끝 ::::::: -->
	
	<div class="table-responsive" style="overflow:auto;">
			<table class="table table-striped table-bordered table-hover ">
				 <colgroup>
					<col width="10%" />
					<col width="33%" />
					<col />
				 </colgroup>
				
				<thead>
				<tr>	
	                <th>번호</th>
	                <th>부서</th>
	                <th>이름</th>
	                <th>이메일</th>
				</tr>
				
				<tbody>
				<tr>
				<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
				<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td class="lt_text3" colspan="4"><spring:message code="common.nodata.msg" /></td>
					</tr>
				</c:if>
				<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
				
                <c:forEach items="${ resultList }" var="x" varStatus="status">
                    <tr>
                       <td>${(docsVO.pageIndex-1) * docsVO.pageSize + status.count}</td>
                       <td>${x.deptNm}</td>
                       <td><a href="#LINK" onclick="pasteMother('${x.userId}', '${x.name}', '${x.email}', '${x.deptNm}');">${x.name}</a></td>
                       <td>${x.email}</td>
                    </tr>
                </c:forEach>
				</tr>				
				</tbody>
			</table>
			
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td height="3px"></td>
			</tr>
			</table>
			
			<c:if test="${not empty resultList}">
			<input name="pageIndex" type="hidden" value="<c:out value='${docsVO.pageIndex}'/>"/>
			<div align="center">
				<div>
					<ul class="pagination">
						<ui:pagination paginationInfo = "${paginationInfo}"
							type="image"
							jsFunction="linkPage"
							/>
					</ul>
				</div>
			</div>
			</c:if>
			
		</div>
	<!-- /.table-responsive -->
	</form:form>
</div>
<!-- /.panel-body -->

<p class="tc">
	<button type="button" class="btn btn-primary" onclick="javascript:window.close();">닫기</button>
</p>

</body>
</html>
