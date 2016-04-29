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
<title>전자도서관 권한 관리 > 권한등록 > 저작권허락 권한 관리</title>
<style tyle="text/css">
	.td_icon img {max-width:150px;}
</style>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
	/* ********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	function linkPage(pageNo){
		var form = document.getElementById("listForm");
		form.pageIndex.value = pageNo;
		form.action = "<c:url value='/elm/cup/ElmCupList.do'/>";
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
		form.pageUnit.value = val;
		form.action = "<c:url value='/elm/cup/ElmCupList.do'/>";
		form.submit();
	}

	function fnElmCupDetail(id, permCode, userGroupId)
	{
		var form = document.getElementById("listForm");
		form.action = "<c:url value='/elm/cup/ElmCupDetail.do'/>";
		form.cpyrhtCode.value = id;
		form.cpyrhtUsePermCode.value = permCode;
		form.userGroupId.value = userGroupId;
		form.submit();
	};

	function fnElmCupRegist() {
		var form = document.getElementById("listForm");
		form.action = "<c:url value='/elm/cup/ElmCupRegist.do'/>";
		form.submit();
	};

	$(document).ready(function() {
		$('#searchChrgnAt').change(function(e) {
			var form = $('#listForm');
			form.action = "<c:url value='/elm/cup/ElmCupList.do'/>";
			form.submit();
		});
		
		$('#searchCpyrhtUsePermCode').change(function(e) {
			var form = $('#listForm');
			form.action = "<c:url value='/elm/cup/ElmCupList.do'/>";
			form.submit();
		});			

		$('#searchCpyrhtSvcScopeCode').change(function(e) {
			var form = $('#listForm');
			form.action = "<c:url value='/elm/cup/ElmCupList.do'/>";
			form.submit();
		});			

		$('#searchUserGroupId').change(function(e) {
			var form = $('#listForm');
			form.action = "<c:url value='/elm/cup/ElmCupList.do'/>";
			form.submit();
		});			
	});
		
-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form id="listForm" name="listForm" method="POST">

	<div id="page-wrapper">

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">저작권허락 권한 관리</h1>
			</div><!-- /.col-lg-12 -->
		</div><!-- /.row -->

		
		<h2>저작권허락 목록</h2>

		<ul class="nav nav-tabs" id="menuTab">
			<li><a href="<c:url value="/elm/crp/ElmCrpList.do"/>">열람 신청 권한</a></li>
			<li class="active"><a href="<c:url value="/elm/cup/ElmCupList.do"/>">저작권 허락</a></li>
		</ul>
		<div class="select_box">
			<span>
				<select name="searchCondition" id="searchCondition" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option value="cpyrhtCode" <c:if test="${searchVO.searchCondition == 'cpyrhtCode' }">selected="selected" </c:if>>저작권 처리코드</option>
				</select>
				<input type="text" name="searchKeyword" id="searchKeyword" class="ip input-sm input-search" value="<c:out value="${searchKeyword}" />" />
				<button type="button" class="btn btn-primary" onclick="fnSearch(); return false;" ><spring:message code="button.search" /></button>
				</span>
		</div>
		<div class="select_box">
			<span>
				<select name="searchChrgnAt" id="searchChrgnAt" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option value="">과금여부</option>
					<option value="Y" <c:if test="${searchVO.searchChrgnAt == 'Y'}">selected</c:if>>과금</option>
					<option value="N" <c:if test="${searchVO.searchChrgnAt == 'N'}">selected</c:if>>비과금</option>
				</select>
			</span>
			<span style="padding:3px;"></span>
			<span>
				<select name="searchCpyrhtUsePermCode" id="searchCpyrhtUsePermCode" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option value="">져작권 이용허락</option>
					<c:forEach var="copy" items="${cpyUseList}">
						<option value="<c:out value="${copy.code}" />" <c:if test="${searchVO.searchCpyrhtUsePermCode == copy.code}">selected</c:if>>
							<c:out value="${copy.codeNm}" />
							(<c:out value="${copy.code}" />)
						</option>							
					</c:forEach>					
				</select>
			</span>
			<span style="padding:3px;"></span>
			<span>
				<select name="searchCpyrhtSvcScopeCode" id="searchCpyrhtSvcScopeCode" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option value="">서비스 범위</option>
					<c:forEach var="scope" items="${svcScopeList}">
						<option value="<c:out value="${scope.code}" />" <c:if test="${searchVO.searchCpyrhtSvcScopeCode == scope.code}">selected</c:if>>
							<c:out value="${scope.codeNm}" />
							(<c:out value="${scope.code}" />)
						</option>								
					</c:forEach>
				</select>
			</span>
			<span style="padding:3px;"></span>
			<span>
				<select name="searchUserGroupId" id="searchUserGroupId" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option value="">사용자 클래스</option>
					<c:forEach var="userClass" items="${userClassList}">
						<option value="${userClass.userGroupId}" <c:if test="${searchVO.searchUserGroupId == userClass.userGroupId}">selected</c:if>>
							<c:out value="${userClass.userGroupNm}" />(<c:out value="${userClass.userGroupId}" />)
						</option>
					</c:forEach>
				</select>
			</span>
		</div>
		<div class="page">
			총 건수 :  ${totCnt}건
			<span>
				출력건수
				<select id="pageUnit" name="pageUnit" aria-controls="dataTables-example" class=" input-sm" onchange="fnChgListCnt(this.value);">
					<option value="10" <c:if test="${searchVO.pageUnit == '10' }">selected</c:if>>10</option>
					<option value="30" <c:if test="${searchVO.pageUnit == '30' }">selected</c:if>>30</option>
					<option value="50" <c:if test="${searchVO.pageUnit == '50' }">selected</c:if>>50</option>
					<option value="100" <c:if test="${searchVO.pageUnit == '100' }">selected</c:if>>100</option>
				</select>
			</span>

		</div>

		<table class="table table-striped table-bordered table-hover "  id="">
			<colgroup>
				<col width="5%" />
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
					<th>저작권 처리코드</th>
					<th>과금여부</th>
					<th>저작권 이용허락</th>
					<th>서비스 범위</th>
					<th>Class</th>
					<th>아이콘/출력메세지</th>
				</tr>
			</thead>
			<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
				</tr>
			</c:if>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
			<c:forEach var="x" items="${resultList}" varStatus="s">
				<tr>
					<td>${(searchVO.pageIndex-1) * searchVO.pageSize + s.count}</td>
					<td>
						<a href="#" onclick="javascript:fnElmCupDetail('${x.cpyrhtCode}', '${x.cpyrhtUsePermCode}', '${x.userGroupId}');">
							<c:out value="${x.cpyrhtCode}" />
						</a>
					</td>
					<td>
						<c:choose>
							<c:when test="${x.chrgnAt == 'Y'}">과금</c:when>
							<c:otherwise>비과금</c:otherwise>
						</c:choose>
						
					</td>
					<td>
						<c:forEach var="copy" items="${cpyUseList}">
							<c:if test="${x.cpyrhtUsePermCode == copy.code}">
								<c:out value="${copy.codeNm}" />
								(<c:out value="${copy.code}" />)
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach var="scope" items="${svcScopeList}">
							<c:if test="${x.cpyrhtSvcScopeCode == scope.code}">
								<c:out value="${scope.codeNm}" />
								(<c:out value="${scope.code}" />)
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach var="cl" items="${userClassList}">
							<c:if test="${x.userGroupId == cl.userGroupId }">
								<c:out value="${cl.userGroupNm}" />
								(<c:out value="${cl.userGroupId}" />)
							</c:if>
						</c:forEach>
					</td>
					<td class="td_icon">
						<%-- <img src="<c:out value='${x.iconFileNm}' />" alt="<c:out value='${x.iconMssage}' />" /> --%>
						<c:import url="/cmm/fms/selectImageFileInf.do" charEncoding="utf-8">
							<c:param name="atchFileId" value="${x.iconFileNm}" />
							<c:param name="fileSn" value="0" />
						</c:import>			    	
						
						<br>
						<c:out value='${x.iconMssage}' />
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
			<div style="display:none;">
				<img src="http://dl.nanet.go.kr/images/cmn_img/icon_w_tiff_5_off.gif" alt="협정기관용 tiff 파일 아이콘" />
				<img src="http://dl.nanet.go.kr/images/cmn_img/icon_w_pdf_5_off.gif" alt="협정기관용 pdf 파일 아이콘"  />
				<img src="http://dl.nanet.go.kr/images/cmn_img/icon_w_tiff_6_off.gif" alt="국회도서관 방문이용자용 tiff 파일 아이콘" />
				<img src="http://dl.nanet.go.kr/images/cmn_img/icon_w_pdf_6_off.gif" alt="국회도서관 방문이용자용 pdf 파일 아이콘"  />
				<img src="http://dl.nanet.go.kr/images/cmn_img/icon_w_tiff_7_off.gif" alt="국회직원용 tiff 파일 아이콘" />
				<img src="http://dl.nanet.go.kr/images/cmn_img/icon_w_pdf_7_off.gif" alt="국회직원용 pdf 파일 아이콘"  />
				<img src="http://dl.nanet.go.kr/images/cmn_img/icon_w.gif" alt="\">
			</div>



		<div class="tr mb20">
			<button type="button" class="btn btn-primary" onclick="fnElmCupRegist()">등록</button>
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
			<input type="hidden" id="cpyrhtCode" name="cpyrhtCode" />
			<input type="hidden" id="cpyrhtUsePermCode" name="cpyrhtUsePermCode" />
			<input type="hidden" id="userGroupId" name="userGroupId" />
			
		</form>

	</div><!--//page-wrapper-->

</body>
</html>
