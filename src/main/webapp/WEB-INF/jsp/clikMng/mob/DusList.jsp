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
<title>모바일자료 이용통계</title>
<script type="text/javascript">
function lodding() {
	$(".spinner").show();
}

function fn_search(){
	lodding();
	document.sfrm.action = "<c:url value='/mob/DusList.do'/>";
   	document.sfrm.submit();
}

/* excel download function */
function fn_egov_excel(){
	document.sfrm.action = "<c:url value='/mob/selectMobDusExcel.do'/>";
   	document.sfrm.submit();
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
</script>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">모바일자료 이용통계</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
			<h2>모바일자료 이용통계 조회</h2>
			<form name="sfrm" method="post">
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

					<a href="#none"><button type="button" class="btn btn-primary" onclick="fn_search(); return false;">검색</button></a> 
					<a href="#none"><button type="button" class="btn btn-success" onClick="fn_egov_excel(); return false;">excel</button></a>
			</div>
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

				<a href="#none"><button type="button" class="btn btn-primary" onclick="fn_search(); return false;">검색</button> </a>
				<a href="#none"><button type="button" class="btn btn-success" onClick="fn_egov_excel(); return false;">excel</button></a>
			</div><!--//tc-->
			</form>
			<c:choose>
			<c:when test="${searchVO.searchCondition eq 'term'}">
				<table class="table table-striped table-bordered table-hover " id="dataTable" >
				<thead>
					<tr>
						<th colspan="2"  align="center">구분</th><th colspan="5"  align="center">이용 현황(건수)</th>
					</tr>
					<tr>
						<th align="center">카테고리</th>
						<th align="center">자료유형</th>
						<th align="center">접속</th>
						<th align="center">검색</th>
						<th align="center">상세보기</th>
						<th align="center">원문보기</th>
						<th align="center">다운로드</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="menu" value="1" />
				<c:set var="visitTotal" value="1" />
							
				<c:forEach var="x" items="${categoryList }" varStatus="s">
					<c:forEach var="i" items="${itemMap[x.codeId]}" varStatus="s1">
						<tr>
							<td>
								<c:if test="${x.codeIdNm != menu}" >
									 ${x.codeIdNm }
									 <c:set var="menu"  value="${x.codeIdNm }" />
								</c:if>
							</td>
							<td>${i.codeIdNm} </td>
							<td align="right"><c:if test="${s1.index == 0}"><c:set var="visitTotal" value="${visitTotalCnt }" /></c:if></td>
							<td id="SC_${i.codeId}" align="right">0</td>
							<td id="DV_${i.codeId}" align="right">0</td>
							<td id="OV_${i.codeId}" align="right">0</td>
							<td id="DL_${i.codeId}" align="right">0</td>
						</tr>
					</c:forEach>
				</c:forEach>
					<tr>
						<td colspan="3" align="center">합계</td>
						<td id="SC_SUM" align="right">0</td>
						<td id="DV_SUM" align="right">0</td>
						<td id="OV_SUM" align="right">0</td>
						<td id="DL_SUM" align="right">0</td>
					</tr>	
				</tbody>				
				</table>
				<script type="text/javascript">
				$('#SC_SC_ALL').prev().html(numberWithCommas(<c:out value="${visitTotalCnt }" />));
				<c:set var="sc_sum" value="0"/>
				<c:set var="dv_sum" value="0"/>
				<c:set var="ov_sum" value="0"/>
				<c:set var="dl_sum" value="0"/>
				<c:forEach var="i" items="${dusList }" varStatus="dus">
					<c:if test="${i.logSeCode ne null && i.rasmblyDtaSeCode ne null}">
						$("#${i.logSeCode}_${i.rasmblyDtaSeCode}").html(numberWithCommas(<c:out value="${i.useCo}" />));
						<c:if test="${i.logSeCode eq 'SC'}"><c:set var="sc_sum" value="${sc_sum + i.useCo}"/></c:if>
						<c:if test="${i.logSeCode eq 'DV'}"><c:set var="dv_sum" value="${dv_sum + i.useCo}"/></c:if>
						<c:if test="${i.logSeCode eq 'OV'}"><c:set var="ov_sum" value="${ov_sum + i.useCo}"/></c:if>
						<c:if test="${i.logSeCode eq 'DL'}"><c:set var="dl_sum" value="${dl_sum + i.useCo}"/></c:if>
					</c:if>
				</c:forEach>
				$("#SC_SUM").html(numberWithCommas(<c:out value="${sc_sum}" />));
				$("#DV_SUM").html(numberWithCommas(<c:out value="${dv_sum}" />));
				$("#OV_SUM").html(numberWithCommas(<c:out value="${ov_sum}" />));
				$("#DL_SUM").html(numberWithCommas(<c:out value="${dl_sum}" />));
				</script>
			</c:when>
			<c:when test="${searchVO.searchCondition eq 'month'}">
				<table class="table table-striped table-bordered table-hover " id="dataTable" >
				<thead>
					<tr>
						<th colspan="2"  align="center">구분</th><th colspan="5"  align="center">이용 현황(건수)</th>
					</tr>
					<tr>
						<th align="center">카테고리</th>
						<th align="center">일자</th>
						<th align="center">접속</th>
						<th align="center">검색</th>
						<th align="center">상세보기</th>
						<th align="center">원문보기</th>
						<th align="center">다운로드</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="ss_sum" value="0" />
					<c:set var="sc_sum" value="0" />
					<c:set var="dv_sum" value="0" />
					<c:set var="ov_sum" value="0" />
					<c:set var="dl_sum" value="0" />
					
					<c:forEach var="x" items="${dusList }" varStatus="s">
					<tr>
						<td><c:if test="${s.index == 0}">전자도서관</c:if></td>
						<td>${fn:replace(x.USE_DATE, '-', '.')}</td>
						<td align="right"><fmt:formatNumber value="${x.SS}" groupingUsed="true"/></td>
						<td align="right"><fmt:formatNumber value="${x.SC}" groupingUsed="true"/></td>
						<td align="right"><fmt:formatNumber value="${x.DV}" groupingUsed="true"/></td>
						<td align="right"><fmt:formatNumber value="${x.OV}" groupingUsed="true"/></td>
						<td align="right"><fmt:formatNumber value="${x.DL}" groupingUsed="true"/></td>
					</tr>
					<c:set var="ss_sum" value="${ss_sum + x.SS}" />
					<c:set var="sc_sum" value="${sc_sum + x.SC}" />
					<c:set var="dv_sum" value="${dv_sum + x.DV}" />
					<c:set var="ov_sum" value="${ov_sum + x.OV}" />
					<c:set var="dl_sum" value="${dl_sum + x.DL}" />
					</c:forEach>
					<tr>
						<td colspan="2" align="center">합계</td>
						<td id="SS_SUM" align="right"><fmt:formatNumber value="${ss_sum}" groupingUsed="true"/></td>
						<td id="SC_SUM" align="right"><fmt:formatNumber value="${sc_sum}" groupingUsed="true"/></td>
						<td id="DV_SUM" align="right"><fmt:formatNumber value="${dv_sum}" groupingUsed="true"/></td>
						<td id="OV_SUM" align="right"><fmt:formatNumber value="${ov_sum}" groupingUsed="true"/></td>
						<td id="DL_SUM" align="right"><fmt:formatNumber value="${dl_sum}" groupingUsed="true"/></td>
					</tr>	
				</tbody>				
				</table>
			</c:when>
			</c:choose>
	</div><!--//page-wrapper-->
	
	<div class="spinner"></div>
</body>
</html>
