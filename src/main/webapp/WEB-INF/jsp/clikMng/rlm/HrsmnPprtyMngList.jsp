<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form" %>


<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>정당관리 목록</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--


/* ********************************************************
* 등록
******************************************************** */
function fnInsert() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rlm/HrsmnPprtyMngRegist.do' />";

	varForm.submit();
}


/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnDetailView(hrsmnpd_sn,ht_se_stdcd) {

	var varForm = document.listForm;
	varForm.hrsmnpd_sn.value = hrsmnpd_sn;
	varForm.ht_se_stdcd.value = ht_se_stdcd;
	varForm.action = "<c:url value='/rlm/HrsmnPprtyMngDetail.do'/>";
	varForm.submit();
}
-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form name="listForm" id="listForm" method="post">

		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">정당 관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div><!--//row-->

            <!-- /.row -->
			<h2>정당관리 목록</h2>
				
				<input type="hidden" name="hrsmnpd_sn" value=""/>
				<input type="hidden" name="ht_se_stdcd" value=""/>
				
				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="5%" />
						<col width="" />
						<col width="" />
						<col width="" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>기수</th>
							<th>기간</th>
							<th>정당</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:forEach items="${resultList}" var="item" varStatus="i">
							<tr>
								<td>${i.index + 1}</td>
								<td><a href=#none onclick="fnDetailView('${item.hrsmnpd_sn }','${item.ht_se_stdcd }');">${item.hrsmnpd_nm }</a></td>
								<td>${item.begin_de}</td>
								<td>${fn:replace(item.pprty_nm, "," , ", ")}</td>
							</tr>
							</c:forEach>
						</tr>
					</tbody>
				</table>
				<p class="tr">
					<button type="button" class="btn btn-primary" onclick="fnInsert();">등록</button>
				</p>
			</table>
		</div><!--//page-wrapper-->
		

</form>
</body>
</html>
