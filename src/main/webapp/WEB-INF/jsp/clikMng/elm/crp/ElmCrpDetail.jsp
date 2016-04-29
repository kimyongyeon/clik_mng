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
<title>전자도서관 권한 관리 > 권한등록 > 열람신청 권한 관리</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
	function fnElmCrpList()
	{
		location.href ="<c:url value="/elm/crp/ElmCrpList.do"/>";
	}

	function fnElmCrpUpdate()
	{
		var form = document.getElementById("regForm");

		if(confirm("수정하시겠습니까?") == false)
		{
			return;
		}
		
		if($.trim(form.dtaSeCode.value).length == 0)
		{
			alert("구분을 입력하셔야 합니다.");
			form.dtaSeCode.focus();
			return;
		}
/*
		if($.trim(form.registNo.value).length == 0)
		{
			alert("등록번호 1번째 자리를 입력하셔야 합니다.");
			form.resistNo.focus();
			return;
		}

		if($.trim(form.ddc.value).length == 0)
		{
			alert("청구(DDC)를 입력하셔야 합니다.");
			form.ddc.focus();
			return;
		}

		if($.trim(form.pl.value).length == 0)
		{
			alert("별치(PL)를 입력하셔야 합니다.");
			form.pl.focus();
			return;
		}

		if($.trim(form.sl.value).length == 0)
		{
			alert("배가(SL)를 입력하셔야 합니다.");
			form.sl.focus();
			return;
		}

		if($.trim(form.dtaSe.value).length == 0)
		{
			alert("자료구분를 입력하셔야 합니다.");
			form.dtaSe.focus();
			return;
		}

		if($.trim(form.pblicteYear.value).length == 0)
		{
			alert("발행년도를 입력하셔야 합니다.");
			form.pblicteYear.focus();
			return;
		}

		if($.trim(form.postn.value).length == 0)
		{
			alert("Position를 입력하셔야 합니다.");
			form.postn.focus();
			return;
		}

		if($.trim(form.postnLc.value).length == 0)
		{
			alert("위치를 입력하셔야 합니다.");
			form.postnLc.focus();
			return;
		}

		if($.trim(form.printId.value).length == 0)
		{
			alert("프린트 아이디를 입력하셔야 합니다.");
			form.printId.focus();
			return;
		}

		if($.trim(form.printIdDc.value).length == 0)
		{
			alert("프린트 설명를 입력하셔야 합니다.");
			form.printIdDc.focus();
			return;
		}

		if($.trim(form.cnType.value).length == 0)
		{
			alert("cnType를 입력하셔야 합니다.");
			form.cnType.focus();
			return;
		}
*/

		form.cmd.value = "edit";
		form.method = "POST";
		form.action = "<c:url value="/elm/crp/ElmCrpDetail.do"/>";
		form.submit();
	}

	function fnElmCrpDelete()
	{
		var form = document.getElementById("regForm");
		
		if(confirm("삭제하시겠습니까?") == false)
		{
			return;
		}
		
		form.cmd.value = "del";
		form.method = "POST";
		form.action = "<c:url value="/elm/crp/ElmCrpDetail.do"/>";
		form.submit();
	}
-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />


	<div id="page-wrapper">


		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">권한등록</h1>
			</div><!-- /.col-lg-12 -->
		</div><!-- /.row -->


		<h2>열람신청 권한 상세정보</h2>

		<form id="regForm" name="regForm">
			<input type="hidden" id="cmd" name="cmd" />
			<input type="hidden" id="readngReqstSetupId" name="readngReqstSetupId" value="${vo.readngReqstSetupId}"/>

			<table class="table table-striped table-bordered table-hover " id="">
				<colgroup>
					<col width="20%">
					<col width="">
				</colgroup>
				<tbody>
					<tr>
						<th>Case ID</th>
						<td>${vo.readngReqstSetupId}</td>
					</tr>
					<tr>
						<th>구분[${fn:length(codeList)}]</th>
						<td>
							<select name="dtaSeCode" class=" input-sm" style="width:30%;">
								<c:forEach var="x" items="${codeList}" varStatus="s">
									<c:if test="${vo.dtaSeCode == x.code}"><c:set var="selected">selected</c:set></c:if>
									<option value="${x.code}"${selected}>${x.code} (${x.codeNm})</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>등록번호 1번째 자리</th>
						<td><input type="text" name="registNo" class="ip input-sm" style="width:30%;" value="${vo.registNo}"></td>
					</tr>
					<tr>
						<th>청구(DDC) 082.a</th>
						<td><input type="text" name="ddc" class="ip input-sm" style="width:30%;" value="${vo.ddc}"></td>
					</tr>
					<tr>
						<th>별치(PL)</th>
						<td><input type="text" name="pl" class="ip input-sm" style="width:30%;" value="${vo.pl}"></td>
					</tr>
					<tr>
						<th>배가(SL)</th>
						<td><input type="text" name="sl" class="ip input-sm" style="width:30%;" value="${vo.sl}"></td>
					</tr>
					<tr>
						<th>자료구분</th>
						<td><input type="text" name="dtaSe" class="ip input-sm" style="width:30%;" value="${vo.dtaSe}"></td>
					</tr>
					<tr>
						<th>발행년도</th>
						<td><input type="text" name="pblicteYear" class="ip input-sm" style="width:30%;" value="${vo.pblicteYear}"></td>
					</tr>
					<tr>
						<th>Position</th>
						<td><input type="text" name="postn" class="ip input-sm" style="width:30%;" value="${vo.postn}"></td>
					</tr>
					<tr>
						<th>위치</th>
						<td><input type="text" name="postnLc" class="ip input-sm" style="width:30%;" value="${vo.postnLc}"></td>
					</tr>
					<tr>
						<th>프린트 아이디/설명</th>
						<td>
							<input type="text" name="printId" class="ip input-sm" style="width:20%;" value="${vo.printId}">
							<input type="text" name="printIdDc" class="ip input-sm" style="width:60%;" value="${vo.printIdDc}">
						</td>
					</tr>
					<tr>
						<th>CN_TYPE</th>
						<td>
							<input type="text" name="cnType" class="ip input-sm" style="width:30%;" value="${vo.cnType}">
						</td>
					</tr>
					<tr>
						<th>온라인 열람 권한</th>
						<td>
							<input type="radio" name="openCode[L]" value="Y" <c:if test="${openCode.L == 'Y'}">checked</c:if>> O
							&nbsp;&nbsp;&nbsp;
							<input type="radio"name="openCode[L]" value="N" <c:if test="${openCode.L != 'Y'}">checked</c:if>> X
						</td>
					</tr>
					<tr>
						<th>야간이용 권한</th>
						<td>
							<input type="radio" name="openCode[R]" value="Y" <c:if test="${openCode.R == 'Y'}">checked</c:if>> O
							&nbsp;&nbsp;&nbsp;
							<input type="radio" name="openCode[R]" value="N" <c:if test="${openCode.R != 'Y'}">checked</c:if>> X
						</td>
					</tr>
					<tr>
						<th>국회의원 대출 권한</th>
						<td>
							<input type="radio" name="openCode[O]" value="Y" <c:if test="${openCode.O == 'Y'}">checked</c:if>> O
							&nbsp;&nbsp;&nbsp;
							<input type="radio" name="openCode[O]" value="N" <c:if test="${openCode.O != 'Y'}">checked</c:if>> X
						</td>
					</tr>
				</tbody>
			</table>
		</form>

		<p class="tr">
			<button id="goList" type="button" onclick="fnElmCrpList();" class="btn btn-default">목록</button>
			<button id="goUpdate" type="button" onclick="fnElmCrpUpdate();" class="btn btn-success">수정</button>
			<button id="goDelete" type="button" onclick="fnElmCrpDelete();" class="btn btn-danger">삭제</button>
		</p>

	</div><!--//page-wrapper-->
</body>
</html>
