<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 /**
  * @Class Name  : EgovRestdeList.jsp
  * @Description : EgovRestdeList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  *  @author 
  *  @since 
  *  @version 
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<html lang="ko">
<head>
<title>휴일 목록</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/clikmng/cmm/com.css' />">
<link href="<c:url value='/css/clikmng/cmm/button.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/list.css' />" rel="stylesheet" type="text/css">
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/sym/cal/EgovRestdeList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fn_egov_search_Restde(){
	document.listForm.pageIndex.value = 1;
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_Restde(){
	location.href = "<c:url value='/sym/cal/EgovRestdeRegist.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_Restde(restdeNo){
	var varForm				 = document.all["Form"];
	varForm.action           = "<c:url value='/sym/cal/EgovRestdeDetail.do'/>";
	varForm.restdeNo.value   = restdeNo;
	varForm.submit();
}
-->
</script>
</head>
<a name="noscript" id="noscript">
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
</a>
<DIV id="content" style="display">

<form name="listForm" action="<c:url value='/sym/cal/EgovRestdeList.do'/>" method="post">
<table width="700" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="40%"class="title_left"><h1 class="title_left">
   <img src="<c:url value='/images/clikMng/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle" alt="제목아이콘이미지">&nbsp;휴일 목록</h1></td>
  <th>
  </th>
  <td width="10%">
   	<select name="searchCondition" class="select" title="" id = "searchCondition" >
		   <option selected value=''>--선택하세요--</option>
		   <option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>>휴일일자</option>
		   <option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>>휴일명</option>
	   </select>
	</td>
  <td width="35%">
    <input name="searchKeyword" type="text" value="${searchVO.searchKeyword}" title="" id="searchKeyword" class="input-sm input-search">
  </td>
  <th width="10%">
   <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><input type="button" value="<spring:message code="button.search" />"  class="btn btn-primary" onclick="javascript:fn_egov_search_Restde(); return false;"></td>
      <td><span class="button"><input type="submit" value="등록" onclick="fn_egov_regist_Restde(); return false;"></span></td>
    </tr>
   </table>
  </th>
 </tr>
</table>

<table width="700" cellspacing="0" cellpadding="0" border="0">
<tr>
	<td height="3px"></td>
</tr>
</table>

<table width="700" cellpadding="0" class="table-line" border="0" summary="휴일일자, 휴일명, 휴일구분이 표시된 휴일 목록을 표시한다.">
<CAPTION style="display: none;">휴일 목록</CAPTION>
<thead>
<tr>
	<th scope="col" class="title" width="10%" nowrap>번호</th>
	<th scope="col" class="title" width="20%" nowrap>휴일일자</th>
	<th scope="col" class="title" width="30%" nowrap>휴일명</th>
	<th scope="col" class="title" width="25%" nowrap>휴일구분</th>
</tr>
</thead>
<tbody>
<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
<tr style="cursor:pointer;cursor:hand;" onclick="javascript:fn_egov_detail_Restde('${resultInfo.restdeNo}');">
	<td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
	<td class="lt_text3" nowrap><c:out value='${fn:substring(resultInfo.restdeDe, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.restdeDe,  4,6)}'/>-<c:out value='${fn:substring(resultInfo.restdeDe, 6, 8)}'/></td>
	<td class="lt_text" nowrap>${resultInfo.restdeNm}</td>
	<td class="lt_text3" nowrap>${resultInfo.restdeSe}</td>
</tr>
</c:forEach>

<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td class="lt_text3" colspan=4>
			<spring:message code="common.nodata.msg" />
		</td>
	</tr>
</c:if>


</tbody>
</table>

<table width="700" cellspacing="0" cellpadding="0" border="0">
<tr>
	<td height="3px">


	</td>
	<td>
	<div align="center">
		<div>
			<ui:pagination paginationInfo = "${paginationInfo}"
					type="image"
					jsFunction="fn_egov_pageview"
					/>
		</div>
	</div>
	</td>

</tr>
</table>



<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>

</form>
<form name="Form" method="post">
	<input type="hidden" name="restdeNo">
	<input type="submit" id="invisible" class="invisible"/>
</form>
</DIV>
</html>
