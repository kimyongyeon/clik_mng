<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>

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

	/* ********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	function linkPage(pageNo){
		var form = document.getElementById("listForm");
		form.pageIndex.value = pageNo;
		form.action = "<c:url value='/elm/crp/ElmCrpList.do'/>";
		form.submit();
	}
	
	/* ********************************************************
	 * 검색
	 ******************************************************** */
	function fnSearch() {
		var form = document.getElementById("listForm");
		//form.pageUnit.value = val;
		form.action = "<c:url value='/elm/crp/ElmCrpList.do'/>";
		form.submit();
	}

	/* ********************************************************
	* 게시물 수 변경(셀렉트박스)
	******************************************************** */
	function fnChgListCnt(val) {
		var form = document.getElementById("listForm");
		//form.pageUnit.value = val;
		form.action = "<c:url value='/elm/crp/ElmCrpList.do'/>";
		form.submit();
	}

	function fnElmCrpDetail(id)
	{
		var form = document.getElementById("listForm");
		form.action = "<c:url value='/elm/crp/ElmCrpDetail.do'/>";
		form.readngReqstSetupId.value = id;
		form.submit();
	};

	function fnElmCrpRegist() {
		var form = document.getElementById("listForm");
		form.action = "<c:url value='/elm/crp/ElmCrpRegist.do'/>";
		form.submit();
	};

	$(document).ready(function() {
		$('#searchDtaSeCode').change(function(e) {
			var form = $('#listForm');
			form.action = "<c:url value='/elm/crp/ElmCrpList.do'/>";
			form.submit();
		});
		
		$('#searchOpenOnline').change(function(e) {
			var form = $('#listForm');
			form.action = "<c:url value='/elm/crp/ElmCrpList.do'/>";
			form.submit();
		});			

		$('#searchOpenNight').change(function(e) {
			var form = $('#listForm');
			form.action = "<c:url value='/elm/crp/ElmCrpList.do'/>";
			form.submit();
		});			

		$('#searchOpenAssembly').change(function(e) {
			var form = $('#listForm');
			form.action = "<c:url value='/elm/crp/ElmCrpList.do'/>";
			form.submit();
		});			
	});
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form id="listForm" name="listForm" method="POST">

	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">권한등록</h1>
			</div><!-- /.col-lg-12 -->
		</div><!-- /.row -->



		<h2>열람신청 권한 목록</h2>

		<ul class="nav nav-tabs" id="menuTab">
			<li class="active"><a href="<c:url value="/elm/crp/ElmCrpList.do"/>">열람 신청 권한</a></li>
			<li><a href="<c:url value="/elm/cup/ElmCupList.do"/>">저작권 허락</a></li>
		</ul>
		
		<div class="select_box">
			<span>
				<select name="searchCondition" id="searchCondition" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option value="caseId" <c:if test="${searchVO.searchCondition == 'caseId' }">selected="selected" </c:if>>Case ID</option>
				</select>
				<input type="text" name="searchKeyword" id="searchKeyword" class="ip input-sm input-search" value="<c:out value="${searchKeyword}" />" />
				<button type="button" class="btn btn-primary" onclick="fnSearch(); return false;" ><spring:message code="button.search" /></button>
			</span>
		</div>

		<div class="select_box">
			<span>
				<select name="searchDtaSeCode" id="searchDtaSeCode" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option value="">구분</option>
					<c:forEach var="x" items="${codeList}" varStatus="s">
						<option value="<c:out value="${x.code}" />" <c:if test="${searchVO.searchDtaSeCode == x.code}">selected</c:if>>
							<c:out value="${x.codeNm}" />(<c:out value="${x.code}" />)
						</option>
					</c:forEach>
				</select>
			</span>
			<span style="padding:3px;"></span>
			<span>
				<select name="searchOpenOnline" id="searchOpenOnline" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option value="">온라인 열람 권한</option>
					<option value="Y" <c:if test="${searchVO.searchOpenOnline == 'Y'}">selected</c:if>>가능</option>
					<option value="N" <c:if test="${searchVO.searchOpenOnline == 'N'}">selected</c:if>>불가능</option>
				</select>
			</span>
			<span style="padding:3px;"></span>
			<span>
				<select name="searchOpenNight" id="searchOpenNight" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option value="">야간이용 권한</option>
					<option value="Y" <c:if test="${searchVO.searchOpenNight == 'Y'}">selected</c:if>>가능</option>
					<option value="N" <c:if test="${searchVO.searchOpenNight == 'N'}">selected</c:if>>불가능</option>
				</select>
			</span>
			<span style="padding:3px;"></span>
			<span>
				<select name="searchOpenAssembly" id="searchOpenAssembly" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option value="">국회의원 대출 권한</option>
					<option value="Y" <c:if test="${searchVO.searchOpenAssembly == 'Y'}">selected</c:if>>가능</option>
					<option value="N" <c:if test="${searchVO.searchOpenAssembly == 'N'}">selected</c:if>>불가능</option>
				</select>
			</span>
		</div>

		<div class="page">
		
			총 건수 :  ${totCnt}건

			<span>
				출력건수
				<select id="pageUnit" name="pageUnit"  aria-controls="dataTables-example" class=" input-sm" onchange="fnChgListCnt(this.value);">
					<option value="10" <c:if test="${searchVO.pageUnit == '10' }">selected</c:if>>10</option>
					<option value="30" <c:if test="${searchVO.pageUnit == '30' }">selected</c:if>>30</option>
					<option value="50" <c:if test="${searchVO.pageUnit == '50' }">selected</c:if>>50</option>
					<option value="100" <c:if test="${searchVO.pageUnit == '100' }">selected</c:if>>100</option>
				</select>
			</span>

		</div>

		<table class="table table-striped table-bordered table-hover " id="">
			<colgroup>
				<col width="5%" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>Case ID</th>
					<th>구분</th>
					<th>청구(DDC) 082.a</th>
					<th>별치(PL)</th>
					<th>배가(SL)</th>
					<th>Position</th>
					<th>CN_TYPE</th>
					<th>온라인열람</th>
					<th>야간이용</th>
					<th>국회의원</th>
				</tr>
			</thead>
			<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td class="lt_text3" colspan="13"><spring:message code="common.nodata.msg" /></td>
				</tr>
			</c:if>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
			<c:forEach var="x" items="${resultList}" varStatus="s">
				<tr>
					<td>${(searchVO.pageIndex-1) * searchVO.pageUnit + s.count}</td>
					<td>
						<a href="#" onclick="javascript:fnElmCrpDetail('${x.readngReqstSetupId}');">
							<c:out value="${x.readngReqstSetupId}" />
						</a>
					</td>
					<td><c:out value="${x.dtaSeCode}" /></td>
					<td><c:out value="${x.ddc}" /></td>
					<td><c:out value="${x.pl}" /></td>
					<td><c:out value="${x.sl}" /></td>
					<td><c:out value="${x.postn}" /></td>
					<td><c:out value="${x.cnType}" /></td>
					<td><c:if test="${fn:indexOf(x.readngSeCode, 'L') > -1}">O</c:if></td>
					<td><c:if test="${fn:indexOf(x.readngSeCode, 'R') > -1}">O</c:if></td>
					<td><c:if test="${fn:indexOf(x.readngSeCode, 'O') > -1}">O</c:if></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>

		<div class="tr mb20">
			<button type="button" class="btn btn-primary" onclick="javascript:fnElmCrpRegist();">등록</button>
		</div><!--//tr-->


		<div align="center">
			<div>
				<ul class="pagination">
					<ui:pagination paginationInfo="${paginationInfo}"
							type="image"
							jsFunction="linkPage"
							/>
				</ul>
			</div>
		</div>


			<input type="hidden" id="pageSize" name="pageSize" value="${searchVO.pageSize}"/>
			<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}"/>
			<input type="hidden" id="readngReqstSetupId" name="readngReqstSetupId" />

	</div><!--//page-wrapper-->

</form>

</body>
</html>
