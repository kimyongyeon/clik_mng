<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : ElmGroupList.java
 * @Description : ElmGroupList jsp
 */

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>전자도서관 권한 관리 > 그룹별 권한 지정</title>


<script type="text/javaScript" language="javascript" defer="defer">
<!--

	function fnElmGupDetail(id)
	{
		var form = document.getElementById("listForm");
		form.action = "<c:url value='/elm/gup/ElmGupDetail.do'/>";
		form.userGroupId.value = id;
		form.submit();
	};

	function fnElmGupRegist() {
		var form = document.getElementById("listForm");
		form.action = "<c:url value='/elm/gup/ElmGupRegist.do'/>";
		form.submit();
	};


-->
</script>

<style>
th {text-align:center;}
td.td_no {text-align:center;}
td.td_group {text-align:center;}
td.td_use {}
td.td_open {text-align:center;}
</style>
</head>

<body>
<jsp:include page="/cmm/top/top.do" />



	<div id="page-wrapper">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">그룹별 권한 지정</h1>
			</div><!-- /.col-lg-12 -->
		</div><!-- /.row -->

		<h2>그룹별 권한 지정 목록</h2>

		<table class="table table-striped table-bordered table-hover "  id="">
			<colgroup>
				<col width="5%" />
				<col width="20%" />
				<col width="" />
				<col width="20%" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>그룹명</th>
					<th>자료이용권한</th>
					<th>열람신청권한</th>
				</tr>
			</thead>
			<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td class="lt_text3" colspan="4"><spring:message code="common.nodata.msg" /></td>
				</tr>
			</c:if>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
			<c:forEach var="x" items="${resultList}" varStatus="s">
				<tr>
					<td class="td_no">${(searchVO.pageIndex-1) * searchVO.pageSize + s.count}</td>
					<td class="td_group">
						<a href="#" onclick="fnElmGupDetail('${x.userGroupId}')">
							<c:out value="${x.userGroupNm}" />(<c:out value="${x.userGroupId}" />)
						</a>
					</td>
					<td class="td_use"><c:out value="${x.dtaSeCode}" /></td>
					<td class="td_open">
						<c:set var="open" value="${x.readngSeCode}" />
						<c:set var="perm" value="${fn:replace(open, 'L', '온라인 열람')}" />
						<c:set var="perm" value="${fn:replace(open, 'R', '야간이용')}" />
						<c:set var="perm" value="${fn:replace(open, 'O', '국회의원 대출')}" />
						<c:out value="${open}" />
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>


		<div class="tr mb20">
			<button type="button" class="btn btn-primary" onclick="fnElmGupRegist()">등록</button>
		</div><!--//tr-->

		<form id="listForm" name="listForm" method="post">
			<input type="hidden" id="userGroupId" name="userGroupId"  />
		</form>

	</div><!--//page-wrapper-->

</body>
</html>
