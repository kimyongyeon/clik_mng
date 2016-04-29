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
<title>이용자 방문 통계 조회</title>
<script type="text/javaScript" language="javascript" defer="defer">
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
	
	setCal("schDt1","schDt2");
	$("#schDt1").datepicker("setDate", new Date());
	$("#schDt2").datepicker("setDate", new Date());
	
	if("${searchVO.schDt1}" != "" && "${searchVO.schDt2}" != ""){
		var schDt1 = "${searchVO.schDt1}";
		var schDt2 = "${searchVO.schDt2}";
		
		$("#schDt1").datepicker("setDate", schDt1);
		$("#schDt2").datepicker("setDate", schDt2);
	}
});

function lodding() {
	$(".spinner").show();
}

/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	lodding();
	var varForm = document.listForm;
	if($('input[name="searchCondition"]:checked').val() == 'grp'){
		varForm.action = "<c:url value='/sts/stm/UvsMonGrpList.do'/>";
	}else{
		varForm.action = "<c:url value='/sts/stm/GrpUvsList.do'/>";
	}
	varForm.submit();
}

/* ********************************************************
* 엑셀 다운로드
******************************************************** */
function fnExcel() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/sts/stm/selectGrpUvsExcel.do'/>";
	varForm.submit();
}

function changeSearchCondition(type){
	if(type == "term"){
		$('#searchDivMonth').css('display', 'none');
		$('#searchDivTerm').css('display', '');
	}else if(type == "month"){
		$('#searchDivTerm').css('display', 'none');
		$('#searchDivMonth').css('display', '');
	}else if(type == "grp"){
		$('#searchDivTerm').css('display', 'none');
		$('#searchDivMonth').css('display', '');
	}
}
</script>
<style type="text/css">
.tr{
	margin-bottom: 20px;
}
</style>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
	<div id="page-wrapper">
           <div class="row">
               <div class="col-lg-12">
                   <h1 class="page-header">그룹별 이용자 방문통계</h1>
               </div>
               <!-- /.col-lg-12 -->
           </div>

           <!-- /.row -->
		<h2>그룹별 이용자 방문통계</h2>
		<form id="listForm" name="listForm" method="post">
		<div class="tl mb20">
			<input type="radio" name="searchCondition" value="term" /> 기간지정
			<input type="radio" name="searchCondition" value="month" /> 그룹별 월별
			<input type="radio" name="searchCondition" value="grp" /> 월별
		</div>
<!-- 		<table class="table table-striped table-bordered table-hover" > -->
<%-- 			<colgroup> --%>
<%-- 				<col width="20%" /> --%>
<%-- 				<col width="" /> --%>
<%-- 			</colgroup> --%>
<!-- 			<tr> -->
<!-- 				<th>방문기간</th> -->
<!-- 				<td> -->
<!-- 					<input type="text" name="schDt1" id="schDt1" value="" class="input-sm ip" style="width:150px;" /> -->
<!-- 					&nbsp;~&nbsp; -->
<!-- 					<input type="text" name="schDt2" id="schDt2" value="" class="input-sm ip" style="width:150px;" /> -->
					
<!-- 					<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-success">오늘</button></a> -->
<!-- 					<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-danger">일주일</button></a> -->
<!-- 					<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-primary">한달</button></a> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
		
		<!-- 기간지정 -->
		<div class="cont mb20" id="searchDivTerm">
				<input type="text" name="schDt1" id="schDt1" value="" class="input-sm ip" style="width:150px;" />
				&nbsp;~&nbsp;
				<input type="text" name="schDt2" id="schDt2" value="" class="input-sm ip" style="width:150px;" />
				
				<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default1">오늘</button></a>
				<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default2">일주일</button></a>
				<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default3">한달</button></a>

				<a href="#none"><button type="button" class="btn btn-primary" onclick="fnSearch();">검색</button></a>
				<a href="#none"><button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button></a>
		</div>
		
		<!-- 월별 -->
		<div class="cont mb20" id="searchDivMonth" style="display:none;">
			<select id="bgYear" name="bgYear" style="min-width:100px;"name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm">
				<option value="2014">2014</option>
				<option value="2015">2015</option>
				<option value="2016">2016</option>
				<option value="2017">2017</option>
				<option value="2018">2018</option>
				<option value="2019">2019</option>
				<option value="2020">2020</option>
			</select> 년
			<select id="bgMonth" name="bgMonth" style="min-width:100px;"name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm">
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
			</select> 
			
			<div style="min-width:70px; text-align:left; display: inline-block;">월 부터</div> 
			
			<select id="edYear" name="edYear" style="min-width:100px;"name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm">
				<option value="2014">2014</option>
				<option value="2015">2015</option>
				<option value="2016">2016</option>
				<option value="2017">2017</option>
				<option value="2018">2018</option>
				<option value="2019">2019</option>
				<option value="2020">2020</option>
			</select> 년
			<select id="edMonth" name="edMonth" style="min-width:100px;"name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm">
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
			</select> 
			
			<div style="min-width:70px; text-align:left; display: inline-block;">월 까지</div>

			<a href="#none"><button type="button" class="btn btn-primary" onclick="fnSearch(); return false;">검색</button> </a>
			<a href="#none"><button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button></a>
		</div>
		
<!-- 		<div class="tr"> -->
<!-- 			<a href="#none"><button type="button" class="btn btn-primary" onclick="fnSearch();">검색</button></a> -->
<!-- 			<a href="#none"><button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button></a>  -->
<!-- 		</div> -->
		
		<div class="tl mb20" style="border:1px solid #ccc; background:#f9f9f9; padding:10px 15px; line-height:180%; color:#666;">
			 [국회.지방의회 의정자료 공유통합시스템]을 이용하는 로그인 기준 이용자(지방의회담당자, 협정기관, 국회직원, 일반사용자)별 방문 및 검색통계입니다. 
		</div>
		</form>
		
		<!-- 기간지정 -->
		<c:if test="${searchVO.searchCondition == 'term' }">
		<table class="table table-striped table-bordered table-hover tc" >
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
					<th colspan="2">PC</th>
					<th colspan="2">모바일</th>
					<th colspan="2">합계</th>
				</tr>
				<tr>
					<th>접속</th>
					<th>검색</th>
					<th>접속</th>
					<th>검색</th>
					<th>접속</th>
					<th>검색</th>
				</tr>
			</thead>
			
			<tbody>
				<c:set var="pc_access_sum" value="0"/>
				<c:set var="pc_query_sum" value="0"/>
				<c:set var="mobile_access_sum" value="0"/>
				<c:set var="mobile_query_sum" value="0"/>
			
				<c:forEach var="x" items="${logList}" varStatus="s">
					<tr>
						<th colspan="2">${x.rasmbly_nm}</th>
						<td><fmt:formatNumber value="${x.pc_access_cnt}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.pc_query_cnt}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.mobile_access_cnt}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.mobile_query_cnt}" groupingUsed="true"/></td>
						<td class="b"><fmt:formatNumber value="${x.pc_access_cnt + x.mobile_access_cnt}" groupingUsed="true"/></td>
						<td class="b"><fmt:formatNumber value="${x.pc_query_cnt + x.mobile_query_cnt}" groupingUsed="true"/></td>
					</tr>
					
					<c:set var="pc_access_sum" value="${pc_access_sum + x.pc_access_cnt}"/>
					<c:set var="pc_query_sum" value="${pc_query_sum + x.pc_query_cnt}"/>
					<c:set var="mobile_access_sum" value="${mobile_access_sum + x.mobile_access_cnt}"/>
					<c:set var="mobile_query_sum" value="${mobile_query_sum + x.mobile_query_cnt}"/>
				</c:forEach>
				
				<tr>
					<th colspan="2">합계</th>
					<td><fmt:formatNumber value="${pc_access_sum}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${pc_query_sum}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${mobile_access_sum}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${mobile_query_sum}" groupingUsed="true"/></td>
					<td class="r"><b><fmt:formatNumber value="${pc_access_sum + mobile_access_sum}" groupingUsed="true"/></b></td>
					<td class="r"><b><fmt:formatNumber value="${pc_query_sum + mobile_query_sum}" groupingUsed="true"/></b></td>
				</tr>
			</tbody>
		</table>
		</c:if>
		
		<!-- 그룹 월별 -->
		<c:if test="${searchVO.searchCondition == 'month' }">
		<table class="table table-striped table-bordered table-hover tc" >
			<colgroup>
				<col width="20%" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="2">구분</th>
					<th colspan="2">PC</th>
					<th colspan="2">모바일</th>
					<th colspan="2">합계</th>
				</tr>
				<tr>
					<th>그룹명</th>
					<th>일자</th>
					<th>접속</th>
					<th>검색</th>
					<th>접속</th>
					<th>검색</th>
					<th>접속</th>
					<th>검색</th>
				</tr>
			</thead>
			
			<tbody>
				<c:set var="pc_access_sum" value="0"/>
				<c:set var="pc_query_sum" value="0"/>
				<c:set var="mobile_access_sum" value="0"/>
				<c:set var="mobile_query_sum" value="0"/>
			
				<c:forEach var="x" items="${logList}" varStatus="s">
					<tr>
						<th>${x.rasmbly_nm}</th>
						<th>${x.target_date}</th>
						<td><fmt:formatNumber value="${x.pc_access_cnt}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.pc_query_cnt}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.mobile_access_cnt}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.mobile_query_cnt}" groupingUsed="true"/></td>
						<td class="b"><fmt:formatNumber value="${x.pc_access_cnt + x.mobile_access_cnt}" groupingUsed="true"/></td>
						<td class="b"><fmt:formatNumber value="${x.pc_query_cnt + x.mobile_query_cnt}" groupingUsed="true"/></td>
					</tr>
					
					<c:set var="pc_access_sum" value="${pc_access_sum + x.pc_access_cnt}"/>
					<c:set var="pc_query_sum" value="${pc_query_sum + x.pc_query_cnt}"/>
					<c:set var="mobile_access_sum" value="${mobile_access_sum + x.mobile_access_cnt}"/>
					<c:set var="mobile_query_sum" value="${mobile_query_sum + x.mobile_query_cnt}"/>
				</c:forEach>
				
				<tr>
					<th colspan="2">합계</th>
					<td><fmt:formatNumber value="${pc_access_sum}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${pc_query_sum}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${mobile_access_sum}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${mobile_query_sum}" groupingUsed="true"/></td>
					<td class="r"><b><fmt:formatNumber value="${pc_access_sum + mobile_access_sum}" groupingUsed="true"/></b></td>
					<td class="r"><b><fmt:formatNumber value="${pc_query_sum + mobile_query_sum}" groupingUsed="true"/></b></td>
				</tr>
			</tbody>
		</table>
		</c:if>
		
		<!-- 월별 -->
		<c:if test="${searchVO.searchCondition == 'grp' }">
		<table class="table table-striped table-bordered table-hover tc" >
			<colgroup>
				<col width="20%" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="2">구분</th>
					<th colspan="2">PC</th>
					<th colspan="2">모바일</th>
					<th colspan="2">합계</th>
				</tr>
				<tr>
					<th colspan="2">일자</th>
					<th>접속</th>
					<th>검색</th>
					<th>접속</th>
					<th>검색</th>
					<th>접속</th>
					<th>검색</th>
				</tr>
			</thead>
			
			<tbody>
				<c:set var="pc_access_sum" value="0"/>
				<c:set var="pc_query_sum" value="0"/>
				<c:set var="mobile_access_sum" value="0"/>
				<c:set var="mobile_query_sum" value="0"/>
			
				<c:forEach var="x" items="${logList}" varStatus="s">
					<tr>
						<th colspan="2">${x.target_date}</th>
						<td><fmt:formatNumber value="${x.pc_access_cnt}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.pc_query_cnt}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.mobile_access_cnt}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${x.mobile_query_cnt}" groupingUsed="true"/></td>
						<td class="b"><fmt:formatNumber value="${x.pc_access_cnt + x.mobile_access_cnt}" groupingUsed="true"/></td>
						<td class="b"><fmt:formatNumber value="${x.pc_query_cnt + x.mobile_query_cnt}" groupingUsed="true"/></td>
					</tr>
					
					<c:set var="pc_access_sum" value="${pc_access_sum + x.pc_access_cnt}"/>
					<c:set var="pc_query_sum" value="${pc_query_sum + x.pc_query_cnt}"/>
					<c:set var="mobile_access_sum" value="${mobile_access_sum + x.mobile_access_cnt}"/>
					<c:set var="mobile_query_sum" value="${mobile_query_sum + x.mobile_query_cnt}"/>
				</c:forEach>
				
				<tr>
					<th colspan="2">합계</th>
					<td><fmt:formatNumber value="${pc_access_sum}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${pc_query_sum}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${mobile_access_sum}" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${mobile_query_sum}" groupingUsed="true"/></td>
					<td class="r"><b><fmt:formatNumber value="${pc_access_sum + mobile_access_sum}" groupingUsed="true"/></b></td>
					<td class="r"><b><fmt:formatNumber value="${pc_query_sum + mobile_query_sum}" groupingUsed="true"/></b></td>
				</tr>
			</tbody>
		</table>
		</c:if>
		
	</div><!--//page-wrapper-->
		
	<div class="spinner"></div>
</body>
</html>