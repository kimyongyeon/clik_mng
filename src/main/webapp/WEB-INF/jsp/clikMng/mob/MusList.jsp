<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>모바일 설치현황 조회</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--


function lodding() {
	$(".spinner").show();
}

/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	lodding();
	var varForm = document.listForm;
	varForm.action = "<c:url value='/mob/MusList.do' />";
	varForm.submit();
	
}

/* ********************************************************
* 엑셀 다운로드
******************************************************** */
function fnExcel() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/mob/MusExcel.do' />";
	varForm.submit();
}

/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.currentPageNo.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/mob/MusList.do' />";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/uss/mng/MngList.do'/>";
    varForm.submit();
}

/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnMngDetailView(mngrId) {

	var varForm = document.listForm;
	varForm.mngrId.value = mngrId;
	varForm.action = "<c:url value='/uss/mng/MngDetail.do'/>";
	varForm.submit();
}

function changeSearchCondition(type){
	if(type == "term"){
		$('#searchDivMonth').css('display', 'none');
		$('#searchDivTerm').css('display', '');
	}else if(type == "month"){
		$('#searchDivTerm').css('display', 'none');
		$('#searchDivMonth').css('display', '');
	}
}

$(document).ready(function(){
	var searchCondition = "${searchVO.searchCondition}";
	if(searchCondition == null || searchCondition == ''){
		searchCondition = 'term';
	}
	
	$("input[name='searchCondition'][value='"+searchCondition+"']").prop('checked', true);
	
	$("input[name='searchCondition']").on('change', function(e){
		changeSearchCondition(e.currentTarget.value);
	});
	
	changeSearchCondition(searchCondition);
	
	$('#bgYear').val("${searchVO.bgYear}");
	$('#edYear').val("${searchVO.edYear}");
	$('#bgMonth').val("${searchVO.bgMonth}");
	$('#edMonth').val("${searchVO.edMonth}");
});
-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">모바일 설치현황</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>모바일 설치현황 조회</h2>

			<form name="listForm" method="post" action="<c:url value='/mob/MusList.do'/>">
			<div class="tl mb20">
				<input type="radio" name="searchCondition" value="term" /> 기간지정
				<input type="radio" name="searchCondition" value="month" /> 월별
			</div>
			<div class="cont mb20" id="searchDivTerm">
				
				<input type="text" name="bgDt" id="bgDt" value="<c:out value="${searchVO.bgDt}" />" class="input-sm ip" style="width:150px;" />
				&nbsp;~&nbsp;
				<input type="text" name="edDt" id="edDt" value="<c:out value="${searchVO.edDt}" />" class="input-sm ip" style="width:150px;" />
				
 				<a href="#" onclick="getDate('M','bgDt','edDt')"><button type="button" class="btn btn-default btn-default1">한달</button></a>
				<a href="#" onclick="getDate('W','bgDt','edDt')"><button type="button" class="btn btn-default btn-default2">일주일</button></a>
				<a href="#" onclick="getDate('T','bgDt','edDt')"><button type="button" class="btn btn-default btn-default3">오늘</button></a>
 				<script>setCal("bgDt","edDt");</script>

				<a href="#none"><button type="button" class="btn btn-primary" onclick="fnSearch()">검색</button> </a>
				<a href="#none"><button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button></a>
			</div><!--//tc-->
			<div class="cont mb20" id="searchDivMonth" style="display:none;">
				
				<select id="bgYear" name="bgYear" style="min-width:150px;"name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm">
					<option value="2014">2014</option>
					<option value="2015">2015</option>
					<option value="2016">2016</option>
					<option value="2017">2017</option>
					<option value="2018">2018</option>
					<option value="2019">2019</option>
					<option value="2020">2020</option>
				</select> 년
				<select id="bgMonth" name="bgMonth" style="min-width:150px;"name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm">
					<option value="01">01</option>
					<option value="02">02</option>
					<option value="03">03</option>
					<option value="04">04</option>
					<option value="05">05</option>
					<option value="06">06</option>
					<option value="07">07</option>
					<option value="08">08</option>
					<option value="09">09</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select> 월 부터 
				
				<select id="edYear" name="edYear" style="min-width:150px;"name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm">
					<option value="2014">2014</option>
					<option value="2015">2015</option>
					<option value="2016">2016</option>
					<option value="2017">2017</option>
					<option value="2018">2018</option>
					<option value="2019">2019</option>
					<option value="2020">2020</option>
				</select> 년
				<select id="edMonth" name="edMonth" style="min-width:150px;"name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm">
					<option value="01">01</option>
					<option value="02">02</option>
					<option value="03">03</option>
					<option value="04">04</option>
					<option value="05">05</option>
					<option value="06">06</option>
					<option value="07">07</option>
					<option value="08">08</option>
					<option value="09">09</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select> 월 까지

				<a href="#none"><button type="button" class="btn btn-primary" onclick="fnSearch()">검색</button> </a>
				<a href="#none"><button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button></a>
			</div><!--//tc-->
       		</form>


				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="16%" />
						<col width="14%" />
						<col width="14%" />
						<col width="14%" />
<%-- 						<col width="14%" /> --%>
<%-- 						<col width="14%" /> --%>
<%-- 						<col width="14%" /> --%>
					</colgroup>
					<thead>
						<tr>
							<th rowspan="3">누적통계</th>
							<th colspan="3">누적 설치횟수</th>
<!-- 							<th colspan="3">누적 실행횟수</th> -->
						</tr>
						<tr>
							<th>iOS</th>
							<th>Android</th>
							<th>Total</th>
<!-- 							<th>iOS</th> -->
<!-- 							<th>Android</th> -->
<!-- 							<th>Total</th> -->
						</tr>
						<tr>
							<th id="total_FR_ios">0</th>
							<th id="total_FR_android">0</th>
							<th id="total_FR">0</th>
<!-- 							<th id="total_NR_ios">0</th> -->
<!-- 							<th id="total_NR_android">0</th> -->
<!-- 							<th id="total_NR">0</th> -->
						</tr>
					</thead>
				</table>

				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="" />
						<col width="" />
						<col width="" />
						<col width="" />
<%-- 						<col width="" /> --%>
<%-- 						<col width="" /> --%>
<%-- 						<col width="" /> --%>
					</colgroup>
					<thead>
						<tr>
							<th rowspan="2">일자</th>
							<th colspan="3">설치횟수</th>
<!-- 							<th colspan="3">실행횟수</th> -->
						</tr>
						<tr>
							<th>iOS</th>
							<th>Android</th>
							<th>Total</th>
<!-- 							<th>iOS</th> -->
<!-- 							<th>Android</th> -->
<!-- 							<th>Total</th> -->
						</tr>
					</thead>
					<tbody>
						<c:set var="sum_FR_ios" value="0" />
						<c:set var="sum_FR_android" value="0" />
						<c:set var="sum_FR" value="0" />
						<c:set var="sum_NR_ios" value="0" />
						<c:set var="sum_NR_android" value="0" />
						<c:set var="sum_NR" value="0" />
						<c:forEach var="x" items="${resultList}" varStatus="s">
							<tr>
								<td>${fn:substring(x.creatDt, 0, 4)}.${fn:substring(x.creatDt, 4, 6)}<c:if test="${fn:length(x.creatDt) > 6}">.${fn:substring(x.creatDt, 6, 8)}</c:if></td>
								<td><fmt:formatNumber value="${x.FR_ios}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.FR_android}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.FR_ios + x.FR_android}" groupingUsed="true"/></td>
<%-- 								<td><fmt:formatNumber value="${x.NR_ios}" groupingUsed="true"/></td> --%>
<%-- 								<td><fmt:formatNumber value="${x.NR_android}" groupingUsed="true"/></td> --%>
<%-- 								<td><fmt:formatNumber value="${x.NR_ios + x.NR_android}" groupingUsed="true"/></td> --%>
							</tr>
							
							<c:set var="sum_FR_ios" value="${sum_FR_ios + x.FR_ios}" />
							<c:set var="sum_FR_android" value="${sum_FR_android + x.FR_android}" />
							<c:set var="sum_FR" value="${sum_FR + x.FR_ios + x.FR_android}" />
							<c:set var="sum_NR_ios" value="${sum_NR_ios + x.NR_ios}" />
							<c:set var="sum_NR_android" value="${sum_NR_android + x.NR_android}" />
							<c:set var="sum_NR" value="${sum_NR + x.NR_ios + x.NR_android}" />
 						</c:forEach>
						<tr>
							<th>합계</th>
							<th id="sum_FR_ios"><fmt:formatNumber value="${sum_FR_ios}" groupingUsed="true"/></th>
							<th id="sum_FR_android"><fmt:formatNumber value="${sum_FR_android}" groupingUsed="true"/></th>
							<th id="sum_FR"><fmt:formatNumber value="${sum_FR}" groupingUsed="true"/></th>
<%-- 							<th id="sum_NR_ios"><fmt:formatNumber value="${sum_NR_ios}" groupingUsed="true"/></th> --%>
<%-- 							<th id="sum_NR_android"><fmt:formatNumber value="${sum_NR_android}" groupingUsed="true"/></th> --%>
<%-- 							<th id="sum_NR"><fmt:formatNumber value="${sum_NR}" groupingUsed="true"/></th> --%>
						</tr>
					</tbody>
				</table>

	</div><!--//age-wrapper-->
	
<script type="text/javascript">
	//누적통계
	<c:set var="total_FR" value="0" />
	<c:set var="total_NR" value="0" />
	<c:forEach var="x" items="${resultSumList}">
		<c:if test="${x.appOs eq 'ios' && x.logSeCode eq 'FR'}">
			$('#total_FR_ios').html(numberWithCommas('${x.cnt}'));
			<c:set var="total_FR" value="${total_FR + x.cnt}" />
		</c:if>
		<c:if test="${x.appOs eq 'android' && x.logSeCode eq 'FR'}">
			$('#total_FR_android').html(numberWithCommas('${x.cnt}'));
			<c:set var="total_FR" value="${total_FR + x.cnt}" />
		</c:if>
		<c:if test="${x.appOs eq 'ios' && x.logSeCode eq 'NR'}">
			$('#total_NR_ios').html(numberWithCommas('${x.cnt}'));
			<c:set var="total_NR" value="${total_NR + x.cnt}" />
		</c:if>
		<c:if test="${x.appOs eq 'android' && x.logSeCode eq 'NR'}">
			$('#total_NR_android').html(numberWithCommas('${x.cnt}'));
			<c:set var="total_NR" value="${total_NR + x.cnt}" />
		</c:if>
	</c:forEach>
	$('#total_FR').html(numberWithCommas("${total_FR}"));
// 	$('#total_NR').html(numberWithCommas("${total_NR}"));
</script>

	<div class="spinner"></div>
	
</body>
</html>
