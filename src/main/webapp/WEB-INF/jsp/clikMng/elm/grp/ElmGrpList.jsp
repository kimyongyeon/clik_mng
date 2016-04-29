<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<title>전자도서관 권한 관리 > 그룹 등록</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
	//상세보기
	function fnElmGrpDetail(id) 
	{
		var form = document.getElementById("listForm");
		form.action = "<c:url value='/elm/grp/ElmGrpDetail.do'/>";
		form.userGroupId.value = id;
		form.submit();
	};

	//신규 등록
	function fnElmGrpRegist() {
		var form = document.getElementById("listForm");
		form.action = "<c:url value='/elm/grp/ElmGrpRegist.do'/>";
		form.submit();
	};

-->
</script>

</head>

<body>
<jsp:include page="/cmm/top/top.do" />



		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">그룹 등록</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<!-- /.row -->
			<h2>그룹 목록</h2>
			<table class="table table-striped table-bordered table-hover" id="">
				<colgroup>
					<col width="5%" />
					<col width="" />
					<col width="" />
					<col width="" />
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>그룹명</th>
						<th>클래스명</th>
						<th>서브클래스명</th>
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
                        <td class="lt_text3" nowrap="nowrap">${(searchVO.pageIndex-1) * searchVO.pageSize + s.count}</td>
                        <td class="lt_text" nowrap="nowrap">
                            <a href="#" onclick="fnElmGrpDetail('${x.userGroupId}');">${x.userGroupId} ( ${x.userGroupNm} )</a>
                        </td>
                        <td class="lt_text" nowrap="nowrap"><c:out value="${x.userClassNm}" /></td>
                        <td class="lt_text3" nowrap="nowrap"><c:out value="${x.userSubClassNm}" /></td>
                    </tr>
                    </c:forEach>
				</tbody>
			</table>

			<p class="tr">
				<button id="regist" type="button" onclick="javascript:fnElmGrpRegist()" class="btn btn-primary">등록</button>
			</p>
			<form id="listForm" name="listForm" method="post">
				<input type="hidden" id="userGroupId" name="userGroupId"  />
			</form>
		</div><!--//page-wrapper-->
				


</body>
</html>
