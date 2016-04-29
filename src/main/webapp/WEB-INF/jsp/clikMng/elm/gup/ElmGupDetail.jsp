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
<title>전자도서관 권한 관리 &gt; 그룹별 권한 지정</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
	function fnElmGupList()
	{
		location.href ="<c:url value="/elm/gup/ElmGupList.do"/>";
	}

	function fnElmGupUpdate()
	{
		var form = document.getElementById("regForm");
		
		var obj;
		
		obj = form.userGroupId;
		if($.trim(obj.value).length == 0)
		{
			alert("구분을 입력하셔야 합니다.");
			obj.focus();
			return;
		}


		form.cmd.value = "edit";
		form.method = "POST";
		form.action = "<c:url value="/elm/gup/ElmGupDetail.do"/>";
		form.submit();
	}

	function fnElmGupDelete()
	{
		var form = document.getElementById("regForm");
		form.cmd.value = "del";
		form.method = "POST";
		form.action = "<c:url value="/elm/gup/ElmGupDetail.do"/>";
		form.submit();
	}

	function fnElmCupList(userGroupId)
	{
		$.post('/elm/cup/ElmCupListAjax.do', 
				{userGroupId: userGroupId}, 
				function(data, textStatus, jqXHR ) {
					console.log(data);
					console.log(textStatus);
					console.log(jqXHR);
					if(textStatus == 'success')
					{
						$('#area_ElmCupList').html(data);
					}
				},
				'html'
		);	
	}
	
	
	$(document).ready(function() {
		fnElmCupList("<c:out value="${userGroupId }" />");
		
		$('#userGroupId').change(function(e) {
			fnElmCupList($(this).val());
		});
	});
-->
</script>

</head>

<body>
<jsp:include page="/cmm/top/top.do" />


	<div id="page-wrapper">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">그룹별 권한 지정</h1>
			</div><!-- /.col-lg-12 -->
		</div><!-- /.row -->

		<h2>그룹별 권한 지정 상세정보</h2>

		<form id="regForm" name="regForm">
		<input type="hidden" name="cmd" />

			<table class="table table-striped table-bordered table-hover "  id="">
				<colgroup>
					<col width="20%" />
					<col width="" />
				</colgroup>
				<tbody>
					<tr>
						<th>그룹선택</th>
						<td>
								<c:forEach var="x" items="${userClassList}" varStatus="s">
									<c:if test="${userGroupId == x.userGroupId}">
										${x.userGroupNm} (${x.userGroupId})
									</c:if>
								</c:forEach>
						</td>
					</tr>
					<tr>
						<th>자료 이용 권한</th>
						<td>
							<c:forEach var="x" items="${codeList}" varStatus="s">
								<c:set var="code" value="${x.code}" />
								<c:if test="${s.index % 4 == 0}"><div class="row"></c:if>
								<div class="col-md-3">
										<input type="checkbox" id="grantUseCode_${x.code}" 
											name="grantUseCode[${x.code}]" value="100" 
											<c:if test="${grantUseCode[code] == '100'}">checked</c:if> /> 
										<label for="grantUseCode_${x.code}" title=" ${x.codeNm}"> ${x.code}</label>
								</div>
								<c:if test="${s.index % 4 == 3}"></div></c:if>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>서비스 제한</th>
						<td>
							<c:forEach var="x" items="${codeList}" varStatus="s">
								<c:set var="code" value="${x.code}" />
								<div class="form-group">
									<div class="col-sm-3">
										<input type="checkbox" id="grantBenCode_${x.code}" 
											name="grantBenCode[${x.code}]" value="200" 
											<c:if test="${grantBenCode[code] == '200'}">checked</c:if> /> 
										<label for="grantBenCode_${x.code}" title=" ${x.codeNm}"> ${x.code}</label>
									</div>
									<div class="col-sm-9">
										<input type="text" name="grantBenMsg[${x.code}]" class="form-control"  value="${grantBenMsg[code]}" />
									</div>
								</div>
								<br>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>열람신청 권한</th>
						<td>
							<c:forEach var="x" items="${openList}" varStatus="s">
								<c:set var="code" value="${x.code}" />
								<input type="checkbox" id="openCode_${x.code}" name="openCode[${x.code}]" value="Y"
									<c:if test="${openCode[code] == 'Y'}">checked</c:if> /> 
								<label for="openCode_${x.code}"> ${x.codeNm} </label>
								&nbsp;&nbsp;&nbsp;
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th>저작권 이용허락</th>
						<td>
							<div id="area_ElmCupList"></div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>


		<p class="tr">
			<button id="goList" type="button" onclick="fnElmGupList();" class="btn btn-default">목록</button>
			<button id="goUpdate" type="button" onclick="fnElmGupUpdate();" class="btn btn-success">수정</button>
			<button id="goDelete" type="button" onclick="fnElmGupDelete();" class="btn btn-danger">삭제</button>
		</p>

	</div><!--//page-wrapper-->

</body>
</html>
