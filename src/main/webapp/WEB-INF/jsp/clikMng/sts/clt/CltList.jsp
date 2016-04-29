<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>수집통계</title>
<script type="text/javaScript" language="javascript" defer="defer">

$(document).ready(function(){
	
	setCal("bgDt","edDt");
	
	if("${searchVO.bgDt}" == "")
	$("#bgDt").datepicker("setDate", new Date());
	if("${searchVO.edDt}" == "")
	$("#edDt").datepicker("setDate", new Date());
	
	var searchTerm = "${searchVO.searchTerm}";
	if(searchTerm == null || searchTerm == ''){
		searchTerm = 'year';
	}
	if(searchTerm == 'term_sum'){
		var bgDt = "${searchVO.schDt1}";
		var edDt = "${searchVO.schDt2}";
		bgDt = bgDt.substring(0, 4) + '-' + bgDt.substring(4, 6) + '-' + bgDt.substring(6, 8);
		edDt = edDt.substring(0, 4) + '-' + edDt.substring(4, 6) + '-' + edDt.substring(6, 8);
		
		$("#bgDt").datepicker("setDate", bgDt);
		$("#edDt").datepicker("setDate", edDt);
	}
	
	$("input[name='searchTerm'][value='"+searchTerm+"']").prop('checked', true);
	$("input[name='searchTerm']").on('change', function(e){
		changeSearchCondition(e.currentTarget.value);
	});
	
	changeSearchCondition(searchTerm);
	
	$('#bgYear').val("${searchVO.bgYear}");
	$('#edYear').val("${searchVO.edYear}");
	$('#bgMonth').val("${searchVO.bgMonth}");
	$('#edMonth').val("${searchVO.edMonth}");
});

function changeSearchCondition(type){
	if(type == "term_sum"){
		$('#searchYearDiv').css('display', 'none');
		$('#searchDayDiv').css('display', '');
		
		$('#termTable').css('display', 'none');
		$('#termSumTable').css('display', '');
	}else if(type == "day"){
		$('#searchYearDiv').css('display', 'none');
		$('#searchDayDiv').css('display', '');
		
		$('#termTable').css('display', '');
		$('#termSumTable').css('display', 'none');
	}else if(type == "month"){
		$('#searchYearDiv').css('display', '');
		$('#searchDayDiv').css('display', 'none');
		
		$('#bgMonth').css('display', '');
		$('#edMonth').css('display', '');
		$('.from').css('display', '');
		
		$('#termTable').css('display', '');
		$('#termSumTable').css('display', 'none');
	}else{
		$('#searchYearDiv').css('display', '');
		$('#searchDayDiv').css('display', 'none');
		
		$('#bgMonth').css('display', 'none');
		$('#edMonth').css('display', 'none');
		$('.from').css('display', 'none');
		
		$('#termTable').css('display', '');
		$('#termSumTable').css('display', 'none');
	}
}

function lodding() {
	$(".spinner").show();
}

/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	lodding();
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sts/clt/CltList.do'/>";
	varForm.submit();
}

/* ********************************************************
* 엑셀 다운로드
******************************************************** */
function fnExcel() {
	$('#reqType').val('excel');
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sts/clt/CltList.do'/>";
	varForm.submit();
	$('#reqType').val('');
}
</script>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />

	<div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">수집통계</h1>
            </div><!-- /.col-lg-12 -->
        </div><!-- /.row -->
        
		<h2>수집통계</h2>
		<form id="listForm" name="listForm" method="post">
		<input type="hidden" id="reqType" name="reqType" />
		<div class="tl mb20">
			<input type="radio" name="searchTerm" value="year" /> 연도별
			<input type="radio" name="searchTerm" value="month" /> 월별
			<input type="radio" name="searchTerm" value="day" /> 일별
			<input type="radio" name="searchTerm" value="term_sum" /> 기관별
		</div><!--//search-->

		<div class="cont mb20" id="searchYearDiv">
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
			</select><span class="from"> 월</span> 부터 
			
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
			</select><span class="from"> 월</span> 까지 
			
			<span class="fr">
				<button type="button" class="btn btn-primary" onclick="fnSearch();">검색</button>
				<button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button>
			</span>
		</div><!--//search-->

		<div class="cont mb20" id="searchDayDiv" style="display:none;">
			<input type="text" name="bgDt" id="bgDt" value="<c:out value="${searchVO.bgDt}" />" class="input-sm ip" style="width:150px;" />
			&nbsp;~&nbsp;
			<input type="text" name="edDt" id="edDt" value="<c:out value="${searchVO.edDt}" />" class="input-sm ip" style="width:150px;" />

			<a href="#" onclick="getDate('T','bgDt','edDt')"><button type="button" class="btn btn-success">오늘</button></a>
			<a href="#" onclick="getDate('W','bgDt','edDt')"><button type="button" class="btn btn-danger">일주일</button></a>
			<a href="#" onclick="getDate('M','bgDt','edDt')"><button type="button" class="btn btn-primary">한달</button></a>
			
			<span class="fr">
				<button type="button" class="btn btn-primary" onclick="fnSearch();">검색</button>
				<button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button>
			</span>
		</div><!--//search-->
		</form>
		
		<!-- 기간별 수집통계 목록 -->
		<table id="termTable" class="table table-striped table-bordered table-hover tc">
			<colgroup>
				<col width="10%" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
			</colgroup>
			<thead>
				<tr>
					<th rowspan="2">연도</th>
					<th rowspan="2">의안</th>
					<th rowspan="2">회의록</th>
					<th colspan="11" class="tc">지방정책정보</th>
					<th rowspan="2">의원현황</th>
					<th rowspan="2">지역현안소식</th>
					<th rowspan="2">교육&매뉴얼</th>
					<th rowspan="2">합계</th>
				</tr>
				<tr>
					<th>홍보보도소식</th>
					<th>정책/업무자료</th>
					<th>연구자료</th>
					<th>의정활동자료</th>
					<th>통계</th>
					<th>정책매뉴얼</th>
					<th>출장보고서</th>
					<th>세미나/공청회</th>
					<th>감사자료</th>
					<th>기타</th>
					<th>소계</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${searchVO.searchTerm ne 'term_sum'}">
					<c:set var="sum_100" value="0" />
					<c:set var="sum_minutes" value="0" />
					<c:set var="sum_bill" value="0" />
					<c:set var="sum_assemblyinfo" value="0" />
					<c:set var="sum_140" value="0" />
					<c:set var="sum_news" value="0" />
					<c:set var="sum_160" value="0" />
					<c:set var="sum_200" value="0" />
					<c:set var="sum_300" value="0" />
					<c:set var="sum_400" value="0" />
					<c:set var="sum_500" value="0" />
					<c:set var="sum_600" value="0" />
					<c:set var="sum_700" value="0" />
					<c:set var="sum_800" value="0" />
					<c:set var="sum_900" value="0" />
					<c:set var="sum_999" value="0" />
					<c:set var="sum_tot_sub" value="0" />
					<c:set var="sum_tot_cnt" value="0" />
					<c:forEach var="x" items="${cltList}" varStatus="s">
					<tr>
						<th>${x.term}</th>
						<td><fmt:formatNumber value="${x.bill}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.minutes}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x._100}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x._200}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x._300}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x._400}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x._500}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x._600}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x._700}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x._800}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x._900}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x._999}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.tot_sub}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.assemblyinfo}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.news}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x._140}" groupingUsed="true"/></td>
						<td class="b"><fmt:formatNumber value="${x.tot_cnt}" groupingUsed="true"/></td>
						
						<c:set var="sum_100" value="${sum_100 + x._100}" />
						<c:set var="sum_minutes" value="${sum_minutes + x.minutes}" />
						<c:set var="sum_bill" value="${sum_bill + x.bill}" />
						<c:set var="sum_assemblyinfo" value="${sum_assemblyinfo + x.assemblyinfo}" />
						<c:set var="sum_140" value="${sum_140 + x._140}" />
						<c:set var="sum_news" value="${sum_news + x.news}" />
						<c:set var="sum_160" value="${sum_160 + x._160}" />
						<c:set var="sum_200" value="${sum_200 + x._200}" />
						<c:set var="sum_300" value="${sum_300 + x._300}" />
						<c:set var="sum_400" value="${sum_400 + x._400}" />
						<c:set var="sum_500" value="${sum_500 + x._500}" />
						<c:set var="sum_600" value="${sum_600 + x._600}" />
						<c:set var="sum_700" value="${sum_700 + x._700}" />
						<c:set var="sum_800" value="${sum_800 + x._800}" />
						<c:set var="sum_900" value="${sum_900 + x._900}" />
						<c:set var="sum_999" value="${sum_999 + x._999}" />
						<c:set var="sum_tot_sub" value="${sum_tot_sub + x.tot_sub}" />
						<c:set var="sum_tot_cnt" value="${sum_tot_cnt + x.tot_cnt}" />
					</tr>
				</c:forEach>
				<tr>
					<th>합계</th>
					<td><fmt:formatNumber value="${sum_bill}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_minutes}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_100}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_200}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_300}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_400}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_500}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_600}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_700}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_800}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_900}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_999}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_tot_sub}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_assemblyinfo}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_news}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sum_140}" groupingUsed="true"/></td>
					<td class="r"><b><fmt:formatNumber value="${sum_tot_cnt}" groupingUsed="true"/></b></td>
				</tr>
				</c:if>
				<c:if test="${searchVO.searchTerm eq 'term_sum'}">
					<tr>
						<th>합계</th>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td class="r"><b>0</b></td>
					</tr>
				</c:if>
			</tbody>
		</table>

		<!-- 기간합산 수집통계 목록 -->
		<table id="termSumTable" class="table table-striped table-bordered table-hover tc" style="display:none;">
			<colgroup>
				<col width="10%" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
			</colgroup>
			<thead>
				<tr>
					<th rowspan="2" colspan="2">구분</th>
					<th rowspan="2">의안</th>
					<th rowspan="2">회의록</th>
					<th colspan="11" class="tc">지방정책정보</th>
					<th rowspan="2">의원현황</th>
					<th rowspan="2">지역현안소식</th>
					<th rowspan="2">교육&매뉴얼</th>
					<th rowspan="2">합계</th>
				</tr>
				<tr>
					<th>홍보보도소식</th>
					<th>정책/업무자료</th>
					<th>연구자료</th>
					<th>의정활동자료</th>
					<th>통계</th>
					<th>정책매뉴얼</th>
					<th>출장보고서</th>
					<th>세미나/공청회</th>
					<th>감사자료</th>
					<th>기타</th>
					<th>소계</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${searchVO.searchTerm eq 'term_sum'}">
				
					<c:set var="rowspan1" value="0" />
					<c:set var="rowspan2" value="0" />
					<c:forEach var="w" items="${cltList}" varStatus="s">
						<c:if test="${w.rasmbly_ty_code eq 'WAC'}">
							<c:set var="rowspan1" value="${rowspan1 + 1}" />
						</c:if>
						<c:if test="${w.rasmbly_ty_code eq 'BAC'}">
							<c:set var="rowspan2" value="${rowspan2 + 1}" />
						</c:if>
					</c:forEach>
					
					<!-- 광역의회 -->
					<c:set var="sum_100" value="0" />
					<c:set var="sum_minutes" value="0" />
					<c:set var="sum_bill" value="0" />
					<c:set var="sum_assemblyinfo" value="0" />
					<c:set var="sum_140" value="0" />
					<c:set var="sum_news" value="0" />
					<c:set var="sum_160" value="0" />
					<c:set var="sum_200" value="0" />
					<c:set var="sum_300" value="0" />
					<c:set var="sum_400" value="0" />
					<c:set var="sum_500" value="0" />
					<c:set var="sum_600" value="0" />
					<c:set var="sum_700" value="0" />
					<c:set var="sum_800" value="0" />
					<c:set var="sum_900" value="0" />
					<c:set var="sum_999" value="0" />
					<c:set var="sum_tot_sub" value="0" />
					<c:set var="sum_tot_cnt" value="0" />
					
					<c:set var="flag1" value="N" />
					<c:forEach var="x" items="${cltList}" varStatus="s">
						
						<c:if test="${x.rasmbly_ty_code eq 'WAC'}">
							<tr>
								<c:if test="${flag1 eq 'N'}"><th rowspan="${rowspan1 + 1}">광역의회</th><!--//rowspan="늘어남"--><c:set var="flag1" value="Y" /></c:if>
								<th>${x.rasmbly_nm}</th>
								<td><fmt:formatNumber value="${x.bill}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.minutes}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._100}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._200}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._300}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._400}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._500}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._600}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._700}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._800}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._900}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._999}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.tot_sub}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.assemblyinfo}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.news}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._140}" groupingUsed="true"/></td>
								<td class="b"><fmt:formatNumber value="${x.tot_cnt}" groupingUsed="true"/></td>
								
								<c:set var="sum_100" value="${sum_100 + x._100}" />
								<c:set var="sum_minutes" value="${sum_minutes + x.minutes}" />
								<c:set var="sum_bill" value="${sum_bill + x.bill}" />
								<c:set var="sum_assemblyinfo" value="${sum_assemblyinfo + x.assemblyinfo}" />
								<c:set var="sum_140" value="${sum_140 + x._140}" />
								<c:set var="sum_news" value="${sum_news + x.news}" />
								<c:set var="sum_160" value="${sum_160 + x._160}" />
								<c:set var="sum_200" value="${sum_200 + x._200}" />
								<c:set var="sum_300" value="${sum_300 + x._300}" />
								<c:set var="sum_400" value="${sum_400 + x._400}" />
								<c:set var="sum_500" value="${sum_500 + x._500}" />
								<c:set var="sum_600" value="${sum_600 + x._600}" />
								<c:set var="sum_700" value="${sum_700 + x._700}" />
								<c:set var="sum_800" value="${sum_800 + x._800}" />
								<c:set var="sum_900" value="${sum_900 + x._900}" />
								<c:set var="sum_999" value="${sum_999 + x._999}" />
								<c:set var="sum_tot_sub" value="${sum_tot_sub + x.tot_sub}" />
								<c:set var="sum_tot_cnt" value="${sum_tot_cnt + x.tot_cnt}" />
							</tr>
						</c:if>
					
					</c:forEach>
					<c:if test="${rowspan1 > 0}">
						<tr>
							<c:if test="${flag1 eq 'N'}"><th rowspan="${rowspan1 + 1}">광역의회</th><!--//rowspan="늘어남"--><c:set var="flag1" value="Y" /></c:if>
							<th>소계</th>
							<td><fmt:formatNumber value="${sum_bill}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_minutes}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_100}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_200}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_300}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_400}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_500}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_600}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_700}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_800}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_900}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_999}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_tot_sub}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_assemblyinfo}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_news}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_140}" groupingUsed="true"/></td>
							<td class="r"><b><fmt:formatNumber value="${sum_tot_cnt}" groupingUsed="true"/></b></td>
						</tr>
					</c:if>
					
					<!-- 기초의회 -->
					<c:set var="sum_100_2" value="0" />
					<c:set var="sum_minutes_2" value="0" />
					<c:set var="sum_bill_2" value="0" />
					<c:set var="sum_assemblyinfo_2" value="0" />
					<c:set var="sum_140_2" value="0" />
					<c:set var="sum_news_2" value="0" />
					<c:set var="sum_160_2" value="0" />
					<c:set var="sum_200_2" value="0" />
					<c:set var="sum_300_2" value="0" />
					<c:set var="sum_400_2" value="0" />
					<c:set var="sum_500_2" value="0" />
					<c:set var="sum_600_2" value="0" />
					<c:set var="sum_700_2" value="0" />
					<c:set var="sum_800_2" value="0" />
					<c:set var="sum_900_2" value="0" />
					<c:set var="sum_999_2" value="0" />
					<c:set var="sum_tot_sub_2" value="0" />
					<c:set var="sum_tot_cnt_2" value="0" />
					
					<c:set var="flag2" value="N" />
					<c:forEach var="x" items="${cltList}" varStatus="s">
					
						<c:if test="${x.rasmbly_ty_code eq 'BAC'}">
							<tr>
								<c:if test="${flag2 eq 'N'}"><th rowspan="${rowspan2 + 1}">기초의회</th><!--//rowspan="늘어남"--><c:set var="flag2" value="Y" /></c:if>
								<th>${x.rasmbly_nm}</th>
								<td><fmt:formatNumber value="${x.bill}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.minutes}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._100}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._200}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._300}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._400}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._500}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._600}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._700}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._800}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._900}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._999}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.tot_sub}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.assemblyinfo}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.news}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._140}" groupingUsed="true"/></td>
								<td class="b"><fmt:formatNumber value="${x.tot_cnt}" groupingUsed="true"/></td>
								
								<c:set var="sum_100_2" value="${sum_100_2 + x._100}" />
								<c:set var="sum_minutes_2" value="${sum_minutes_2 + x.minutes}" />
								<c:set var="sum_bill_2" value="${sum_bill_2 + x.bill}" />
								<c:set var="sum_assemblyinfo_2" value="${sum_assemblyinfo_2 + x.assemblyinfo}" />
								<c:set var="sum_140_2" value="${sum_140_2 + x._140}" />
								<c:set var="sum_news_2" value="${sum_news_2 + x.news}" />
								<c:set var="sum_160_2" value="${sum_160_2 + x._160}" />
								<c:set var="sum_200_2" value="${sum_200_2 + x._200}" />
								<c:set var="sum_300_2" value="${sum_300_2 + x._300}" />
								<c:set var="sum_400_2" value="${sum_400_2 + x._400}" />
								<c:set var="sum_500_2" value="${sum_500_2 + x._500}" />
								<c:set var="sum_600_2" value="${sum_600_2 + x._600}" />
								<c:set var="sum_700_2" value="${sum_700_2 + x._700}" />
								<c:set var="sum_800_2" value="${sum_800_2 + x._800}" />
								<c:set var="sum_900_2" value="${sum_900_2 + x._900}" />
								<c:set var="sum_999_2" value="${sum_999_2 + x._999}" />
								<c:set var="sum_tot_sub_2" value="${sum_tot_sub_2 + x.tot_sub}" />
								<c:set var="sum_tot_cnt_2" value="${sum_tot_cnt_2 + x.tot_cnt}" />
							</tr>					
						</c:if>
					
					</c:forEach>
					
					<c:if test="${rowspan2 > 0}">
						<tr>
							<c:if test="${flag2 eq 'N'}"><th rowspan="${rowspan2 + 1}">기초의회</th><!--//rowspan="늘어남"--><c:set var="flag2" value="Y" /></c:if>
							<th>소계</th>
							<td><fmt:formatNumber value="${sum_bill_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_minutes_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_100_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_200_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_300_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_400_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_500_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_600_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_700_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_800_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_900_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_999_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_tot_sub_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_assemblyinfo_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_news_2}" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${sum_140_2}" groupingUsed="true"/></td>
							<td class="r"><b><fmt:formatNumber value="${sum_tot_cnt_2}" groupingUsed="true"/></b></td>
						</tr>
					</c:if>
					
					<!-- 정부기관, 연구기관, 기타 -->
					<c:set var="sum_100_3" value="0" />
					<c:set var="sum_minutes_3" value="0" />
					<c:set var="sum_bill_3" value="0" />
					<c:set var="sum_assemblyinfo_3" value="0" />
					<c:set var="sum_140_3" value="0" />
					<c:set var="sum_news_3" value="0" />
					<c:set var="sum_160_3" value="0" />
					<c:set var="sum_200_3" value="0" />
					<c:set var="sum_300_3" value="0" />
					<c:set var="sum_400_3" value="0" />
					<c:set var="sum_500_3" value="0" />
					<c:set var="sum_600_3" value="0" />
					<c:set var="sum_700_3" value="0" />
					<c:set var="sum_800_3" value="0" />
					<c:set var="sum_900_3" value="0" />
					<c:set var="sum_999_3" value="0" />
					<c:set var="sum_tot_sub_3" value="0" />
					<c:set var="sum_tot_cnt_3" value="0" />
					
					<c:forEach var="x" items="${cltList}" varStatus="s">
					
						<c:if test="${x.rasmbly_ty_code eq 'ETC'}">
							<tr>							
								<th colspan="2">${x.rasmbly_nm}</th>
								<td><fmt:formatNumber value="${x.bill}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.minutes}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._100}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._200}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._300}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._400}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._500}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._600}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._700}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._800}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._900}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._999}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.tot_sub}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.assemblyinfo}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x.news}" groupingUsed="true"/></td>
								<td><fmt:formatNumber value="${x._140}" groupingUsed="true"/></td>
								<td class="b"><fmt:formatNumber value="${x.tot_cnt}" groupingUsed="true"/></td>
								
								<c:set var="sum_100_3" value="${sum_100_3 + x._100}" />
								<c:set var="sum_minutes_3" value="${sum_minutes_3 + x.minutes}" />
								<c:set var="sum_bill_3" value="${sum_bill_3 + x.bill}" />
								<c:set var="sum_assemblyinfo_3" value="${sum_assemblyinfo_3 + x.assemblyinfo}" />
								<c:set var="sum_140_3" value="${sum_140_3 + x._140}" />
								<c:set var="sum_news_3" value="${sum_news_3 + x.news}" />
								<c:set var="sum_160_3" value="${sum_160_3 + x._160}" />
								<c:set var="sum_200_3" value="${sum_200_3 + x._200}" />
								<c:set var="sum_300_3" value="${sum_300_3 + x._300}" />
								<c:set var="sum_400_3" value="${sum_400_3 + x._400}" />
								<c:set var="sum_500_3" value="${sum_500_3 + x._500}" />
								<c:set var="sum_600_3" value="${sum_600_3 + x._600}" />
								<c:set var="sum_700_3" value="${sum_700_3 + x._700}" />
								<c:set var="sum_800_3" value="${sum_800_3 + x._800}" />
								<c:set var="sum_900_3" value="${sum_900_3 + x._900}" />
								<c:set var="sum_999_3" value="${sum_999_3 + x._999}" />
								<c:set var="sum_tot_sub_3" value="${sum_tot_sub_3 + x.tot_sub}" />
								<c:set var="sum_tot_cnt_3" value="${sum_tot_cnt_3 + x.tot_cnt}" />
							</tr>
						</c:if>
					</c:forEach>
					
					<!-- 합계 -->
					<tr>
						<th colspan="2">합계</th>
						<td><fmt:formatNumber value="${sum_bill + sum_bill_2 + sum_bill_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_minutes + sum_minutes_2 + sum_minutes_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_100 + sum_100_2 + sum_100_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_200 + sum_200_2 + sum_200_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_300 + sum_300_2 + sum_300_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_400 + sum_400_2 + sum_400_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_500 + sum_500_2 + sum_500_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_600 + sum_600_2 + sum_600_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_700 + sum_700_2 + sum_700_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_800 + sum_800_2 + sum_800_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_900 + sum_900_2 + sum_900_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_999 + sum_999_2 + sum_999_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_tot_sub + sum_tot_sub_2 + sum_tot_sub_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_assemblyinfo + sum_assemblyinfo_2 + sum_assemblyinfo_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_news + sum_news_2 + sum_news_3}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${sum_140 + sum_140_2 + sum_140_3}" groupingUsed="true"/></td>
						<td class="r"><b><fmt:formatNumber value="${sum_tot_cnt + sum_tot_cnt_2 + sum_tot_cnt_3}" groupingUsed="true"/></b></td>
					</tr>
				
				</c:if>
				
				
				<c:if test="${searchVO.searchTerm ne 'term_sum'}">
					<tr>
						<th colspan="2">합계</th>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td class="r"><b>0</b></td>
					</tr>
				</c:if>
			</tbody>
			
		</table>
		
	</div><!--//page-wrapper-->
	
	<div class="spinner"></div>
</body>
</html>