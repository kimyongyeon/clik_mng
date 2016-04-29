<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>지방의회 회의명정보</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<style>
.t td {

}
</style>

<script type="text/javaScript" language="javascript" defer="defer">
function paging(pageNum) {
	document.frm.pageNum.value = pageNum;
	frm.action = "/mdm/MdmTnsrMinutes.do";
	document.frm.submit();
}
function getMinutesViewer(mints_cn){
	
}
</script>
</head>
<body>
	
	<form name="frm" method="post" action="/mdm/MdmTnsrMinutes.do">
	<input type="hidden" name="pageNum" id="pageNum" value="${mdmSearchVO.pageNum }">
	<input type="hidden" name="asembyId" id="asembyId" value="${asembyId}">
	<input type="hidden" name="rasmblyId" id="rasmblyId" value="${rasmblyId}">
	<input type="hidden" name="listCnt" id="listCnt" value="1">
	</form>
	
	<div class="row">
		<div class="col-lg-12">
        	<h1 class="page-header">의원 관련 회의록 / 발언</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
	
	<c:if test="${fn:length(minutesList) == 0 }">
		<div>관련 회의록 / 발언 정보가 존재하지 않습니다.</div>
	</c:if>
	
	<c:if test="${fn:length(minutesList) > 0 }">
	<table class="table table-striped table-bordered ">
		<colgroup>
			<col width="7%" />
			<col width="25%" />
			<col width="7%" />
			<col width="5%" />
			<col width="7%" />
			<col width="5%" />
			<col width="7%" />
			<col width="10%" />
			<col width="10%" />
			<col width="17%" />
		</colgroup>
		<tbody>
			<tr>
				<th>회의명</th>
				<td>${minutesList[0].MTGNM }</td>
				<th>회수</th>
				<td>${minutesList[0].RASMBLY_SESN }</td>
				<th>차수</th>
				<td>${minutesList[0].MINTS_ODR }</td>
				<th>회의일</th>
				<td>${minutesList[0].MTG_DE }</td>
				<th>회의록뷰어</th>
				<td><button onclick="window.open('/minutesviewer/MinutesHtmlView.do?MINTS_CN=${minutesList[0].MINTS_CN}','회의록보기','width=1366,height=768,scrollbars=yes,resizable=yes');">회의록 원문 보기</button></td>
			</tr>
			<tr>
				<td colspan="10">
				<c:forEach var="item" items="${minutesList}">
				${item.SPKNG_CN }<br>
				</c:forEach>
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
		<div align="center">
		<ul class="pagination">
			${paginationInfo}
		</ul>
		</div>
	</div><!--//dataTables_paginate-->

	<%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchVar.jsp" %>
	
	</c:if>
	
</body>
</html>