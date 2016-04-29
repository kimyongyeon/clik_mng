<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>이용자 방문 통계 조회</title>

	<style>
		.highcharts-container {width:100%;}
	</style>
    <script src="/js/clikmng/highcharts/highcharts.js"></script>
    <script src="/js/clikmng/highchartTable/jquery.highchartTable.js"></script>
    
	<script type="text/javaScript" language="javascript" defer="defer">

	/* ********************************************************
	* 검색
	******************************************************** */
	function fnSearch() {
		var varForm = document.listForm;
		varForm.action = "<c:url value='/sts/stm/UvsList.do'/>";
		varForm.submit();
	}

	/* ********************************************************
	* 엑셀 다운로드
	******************************************************** */
	function fnExcel() {
		var varForm = document.listForm;
		varForm.action = "<c:url value='/sts/stm/selectUvsExcel.do'/>";
		varForm.submit();
	}
	
	/* ********************************************************
	* 탭 이동
	******************************************************** */
	function fnTab(tabId) {
		var varForm = document.listForm;
		varForm.cmd.value = tabId;
		varForm.action = "<c:url value='/sts/stm/UvsList.do'/>";
		varForm.submit();
	}
	
	$(document).ready(function() {

		
		// OS 별 방문자 통계
		<c:if test="${cmd == 'os'}">
    	$('#highchart_os').highcharts({
    		chart: {
    			plotBackgroundColor: null,
    			plotBorderWidth: null,
    			plotShadow:	false
    		},
    		title:false,
    		legend:	{
    			align: 'right',
    			verticalAlign: 'top',
    			layout: 'vertical',
    		},
    		tooltip: {
    			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    		},
    		plotOptions: {
    			pie: {
	    			allowPointSelect: true,
    				cursor: 'pointer',
    				dataLabels: {
	    				enabled: true,
    					format: '<b>{point.name}</b>: {point.percentage:.1f} %',
    					style: {
    						color: (Highcharts.theme && Highcharts.theme.contrastTextColor)	|| 'black'
    					}
    				}
    			}
    		},
    		series:	[{
    			type: 'pie',
    			name: 'OS별	방문자',
    			data: [
					<c:forEach var="x" items="${logList}" varStatus="s">
						['<c:out value="${x.userOs}" />', <c:out value="${x.conectCo}" />],
					</c:forEach>
    			]
    		}]
    	});
    	</c:if>

		//브라우져별 방문자 통계
		<c:if test="${cmd == 'wbsr'}">
		$('#highchart_wbsr').highcharts({
			chart: {
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow:	false
			},
			title:false,
			legend:	{
				align: 'right',
				verticalAlign: 'top',
				layout: 'vertical',
			},
			tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor:	'pointer',
					dataLabels: {
						enabled: true,
						format: '<b>{point.name}</b>: {point.percentage:.1f} %',
						style: {
							color: (Highcharts.theme && Highcharts.theme.contrastTextColor)	|| 'black'
						}
					}
				}
			},
			series:	[{
				type: 'pie',
				name: '브라우저별 방문자',
				data: [
					<c:forEach var="x" items="${logList}" varStatus="s">
						[
						 	'<c:out value="${x.userWbsr}"/>"', 
						 	<c:out value="${x.conectCo}"/>
						],
					</c:forEach>
				]
			}]
		});
		</c:if>
		
		// 시간별 방문자 통계
		<c:if test="${cmd == 'hour'}">
		$('#highchart_hour').highcharts({
			title:false,
			chart: {
				type: 'line'
			},
			xAxis: {
				categories:	[
					<c:forEach var="x" items="${logList}" varStatus="s">
					<c:out value="\"${x.hour} 시\"," escapeXml="false" />
					</c:forEach>
				]
			},
			yAxis: {
				title: {
					text: '방문자수'
				},
				plotLines: [{
					value: 0,
					width: 1,
					color: '#808080'
				}]
			},
			legend:	{
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'middle',
				borderWidth: 0
			},
			tooltip: {
				crosshairs: true,
				valueSuffix: '명'
			},
			series:	[{
				name: '방문자수',
				data: [
					<c:forEach var="x" items="${logList}" varStatus="s">
						<c:out value="${x.conectCo}" />,
					</c:forEach>
				]
			}]
		});
		</c:if>

		// 일별 방문자 통계
		<c:if test="${cmd == 'day'}">
		$('#highchart_day').highcharts({
			title:false,
			chart: {
				type: 'line'
			},
			xAxis: {
				categories:	[
					<c:forEach var="x" items="${logList}" varStatus="s">
						<c:out value="'${x.de} 일'," escapeXml="false" />
					</c:forEach>
				]
			},
			yAxis: {
				title: {
					text: '방문자수'
				},
				plotLines: [{
					value: 0,
					width: 1,
					color: '#808080'
				}]
			},
			legend:	{
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'middle',
				borderWidth: 0
			},
			tooltip: {
				crosshairs: true,
				valueSuffix: '명'
			},
			series:	[{
				name: '방문자수',
				data: [
					<c:forEach var="x" items="${logList}" varStatus="s">
						<c:out value="${x.conectCo}" />,
					</c:forEach>
				]
			}]
		});
		</c:if>


		// 월별 방문자 통계
		<c:if test="${cmd == 'month'}">
		$('#highchart_month').highcharts({
			title:false,
			chart: {
				type: 'line'
			},
			xAxis: {
				categories:	[
					<c:forEach var="x" items="${logList}" varStatus="s">
						<c:out value="'${x.mt} 월'," escapeXml="false" />
					</c:forEach>
				]
			},
			yAxis: {
				title: {
					text: '방문자수'
				},
				plotLines: [{
					value: 0,
					width: 1,
					color: '#808080'
				}]
			},
			legend:	{
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'middle',
				borderWidth: 0
			},
			tooltip: {
				crosshairs: true,
				valueSuffix: '명'
			},
			series:	[{
				name: '방문자수',
				data: [
					<c:forEach var="x" items="${logList}" varStatus="s">
						<c:out value="${x.conectCo}" />,
					</c:forEach>
				]
			}]
		});
		</c:if>

		// 년별 방문자 통계
		<c:if test="${cmd == 'year'}">
		$('#highchart_year').highcharts({
			title:false,
			chart: {
				type: 'line'
			},
			xAxis: {
				categories:	[
					<c:forEach var="x" items="${logList}" varStatus="s">
						<c:out value="'${x.year} 년'," escapeXml="false" />
					</c:forEach>
				]
			},
			yAxis: {
				title: {
					text: '방문자수'
				},
				plotLines: [{
					value: 0,
					width: 1,
					color: '#808080'
				}]
			},
			legend:	{
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'middle',
				borderWidth: 0
			},
			tooltip: {
				crosshairs: true,
				valueSuffix: '명'
			},
			series:	[{
				name: '방문자수',
				data: [
					<c:forEach var="x" items="${logList}" varStatus="s">
						<c:out value="${x.conectCo}" />,
					</c:forEach>
				]
			}]
		});
		</c:if>
		

    });

	-->
	</script>
    
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">이용자 방문 통계</h1>
				</div><!-- /.col-lg-12 -->
			</div><!-- /.row -->

			
			<h2>이용자 방문 통계 조회</h2>

			<form id="listForm" name="listForm" method="post">
			<input type="hidden" id="cmd" name="cmd" value="<c:out value="${cmd}" />" />
			<div class="search">
					<input type="text" name="schDt1" id="schDt1" value="<c:out value="${searchVO.schDt1}" />" class="input-sm ip" style="width:150px;" />
					&nbsp;~&nbsp;
					<input type="text" name="schDt2" id="schDt2" value="<c:out value="${searchVO.schDt2}" />" class="input-sm ip" style="width:150px;" />
					
	 				<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default1">한달</button></a>
					<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default2">일주일</button></a>
					<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default3">오늘</button></a>
	 				<script>setCal("schDt1","schDt2");</script>

					<a href="#none"><button type="button" class="btn btn-primary" onclick="fnSearch();">검색</button></a>
					<a href="#none"><button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button></a>
						 				
			</div><!--//tc-->			
			

			<ul class="nav nav-tabs" id="statTab">
				<li <c:if test="${cmd == 'os'}">class="active"</c:if>><a href="#" onclick="fnTab('os')">OS별 방문자</a></li>
				<li <c:if test="${cmd == 'wbsr'}">class="active"</c:if>><a href="#" onclick="fnTab('wbsr')">브라우저별 방문자</a></li>
				<li <c:if test="${cmd == 'hour'}">class="active"</c:if>><a href="#" onclick="fnTab('hour')">시간별 방문자</a></li>
				<li <c:if test="${cmd == 'day'}">class="active"</c:if>><a href="#" onclick="fnTab('day')">일별 방문자</a></li>
				<li <c:if test="${cmd == 'month'}">class="active"</c:if>><a href="#" onclick="fnTab('month')">월별 방문자</a></li>
				<li <c:if test="${cmd == 'year'}">class="active"</c:if>><a href="#" onclick="fnTab('year')">년별 방문자</a></li>
			</ul><!-- //statTab -->

			<div class="tab-content">
			
			<c:if test="${cmd == 'os' }">
				<div id="os">
					<h2>OS별 방문자</h2>
					<div class="img_box mb20" style="border:1px solid #cccccc">
						<div class="row">
							<div class="col-md-8">
								<div id="highchart_os" style="min-width: 310px; height: 290px; max-width: 900px; margin: 0 auto"></div>
							</div><!--  //col-md-8 -->
							<div class="col-md-4" style="overflow-y:auto; height:300px;">
								<table class="table table-striped table-bordered">
									<thead>
										<tr>
											<th width="">OS</th>
											<th class="col-md-6">방문자수</th>
										</tr>
									</thead>
									<tbody>
				                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
				                    <c:if test="${fn:length(logList) == 0}">
				                        <tr>
				                            <td class="lt_text3" colspan="2"><spring:message code="common.nodata.msg" /></td>
				                        </tr>
				                    </c:if>
				                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
									
									<c:forEach var="x" items="${logList}" varStatus="s">
										<tr>
											<td>${x.userOs}</td>
											<td><fmt:formatNumber type="number" value="${x.conectCo}"  /></td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div><!--  col-md-4 -->
						</div><!--  row -->
					</div><!--  img_box mb20 -->
					
					<table class="table table-striped table-bordered table-hover "  id="">
						<colgroup>
							<col width="" />
							<col width="" />
						</colgroup>
						<thead>
							<tr>
								<th>OS</th>
								<th>방문자수</th>
							</tr>
						</thead>
						<tbody>
	                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
	                    <c:if test="${fn:length(logList) == 0}">
	                        <tr>
	                            <td class="lt_text3" colspan="2"><spring:message code="common.nodata.msg" /></td>
	                        </tr>
	                    </c:if>
	                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
						
						<c:forEach var="x" items="${logList}" varStatus="s">
							<tr>
								<td>${x.userOs}</td>
								<td><fmt:formatNumber type="number" value="${x.conectCo}"  /></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>


			<c:if test="${cmd == 'wbsr'}">
				<div id="wbsrwbsr">
					<h2>브라우저별 방문자</h2>
						<div class="img_box mb20" style="border:1px solid #cccccc">
							<div class="row">
								<div class="col-md-8">
									<div id="highchart_wbsr" style="min-width: 310px; height: 290px; max-width: 900px; margin: 0 auto"></div>

								</div>
								<div class="col-md-4" style="overflow-y:auto; height:300px;">
								
										<table class="table table-striped table-bordered">
										    <thead>
										        <tr>                                  
										            <th>브라우저</th>
										            <th>방문자수</th>
										        </tr>
										     </thead>
										     <tbody>
							                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
							                    <c:if test="${fn:length(logList) == 0}">
							                        <tr>
							                            <td class="lt_text3" colspan="2"><spring:message code="common.nodata.msg" /></td>
							                        </tr>
							                    </c:if>
							                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
												
												<c:forEach var="x" items="${logList}" varStatus="s">
													<tr>
														<td>${x.userWbsr}</td>
														<td><fmt:formatNumber type="number" value="${x.conectCo}"  /></td>
													</tr>
												</c:forEach>
										    </tbody>
										</table>

								</div>
							</div>	
							
							
						</div>
						<table class="table table-striped table-bordered table-hover "  id="">
							<colgroup>
								<col width="" />
								<col width="" />
							</colgroup>
							<thead>
								<tr>
									<th>브라우저</th>
									<th>방문자수</th>
								</tr>
							</thead>
							<tbody>
			                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
			                    <c:if test="${fn:length(logList) == 0}">
			                        <tr>
			                            <td class="lt_text3" colspan="2"><spring:message code="common.nodata.msg" /></td>
			                        </tr>
			                    </c:if>
			                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
								
								<c:forEach var="x" items="${logList}" varStatus="s">
									<tr>
										<td>${x.userWbsr}</td>
										<td><fmt:formatNumber type="number" value="${x.conectCo}"  /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				</div>
			</c:if>
				
			<c:if test="${cmd == 'hour'}">
				<div id="hour">
					<h2>시간별 방문자</h2>
						<div class="img_box mb20" style="border:1px solid #cccccc">

									<div id="highchart_hour" style="min-width: 910px; height: 290px; max-width: 1200px; margin: 0 auto"></div>

						</div>
						<table class="table table-striped table-bordered table-hover "  id="">
							<colgroup>
								<col width="" />
								<col width="" />
							</colgroup>
							<thead>
								<tr>
									<th>시간</th>
									<th>방문자수</th>
								</tr>
							</thead>
							<tbody>
			                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
			                    <c:if test="${fn:length(logList) == 0}">
			                        <tr>
			                            <td class="lt_text3" colspan="2"><spring:message code="common.nodata.msg" /></td>
			                        </tr>
			                    </c:if>
			                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
								
								<c:forEach var="x" items="${logList}" varStatus="s">
									<tr>
										<td>${x.hour}</td>
										<td><fmt:formatNumber type="number" value="${x.conectCo}"  /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

				</div>
			</c:if>
				
			<c:if test="${cmd == 'day'}">
				<div id="day">
					<h2>일별 방문자</h2>
					<div class="img_box mb20" style="border:1px solid #cccccc">
						<div id="highchart_day" style="min-width: 910px; height: 290px; max-width: 1200px; margin: 0 auto"></div>
					</div>

					<table class="table table-striped table-bordered table-hover "  id="">
						<colgroup>
							<col width="" />
							<col width="" />
						</colgroup>
						<thead>
							<tr>
								<th>일</th>
								<th>방문자수</th>
							</tr>
						</thead>
						<tbody>
		                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
		                    <c:if test="${fn:length(logList) == 0}">
		                        <tr>
		                            <td class="lt_text3" colspan="2"><spring:message code="common.nodata.msg" /></td>
		                        </tr>
		                    </c:if>
		                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
							
							<c:forEach var="x" items="${logList}" varStatus="s">
								<tr>
									<td>${x.de}</td>
									<td><fmt:formatNumber type="number" value="${x.conectCo}"  /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
				</div>
			</c:if>
				
			<c:if test="${cmd == 'month'}">
				<div id="month">
					<h2>월별 방문자</h2>
					<div class="img_box mb20" style="border:1px solid #cccccc">
						<div id="highchart_month" style="min-width: 910px; height: 290px; max-width: 1200px; margin: 0 auto"></div>
					</div>

					<table class="table table-striped table-bordered table-hover "  id="">
						<colgroup>
							<col width="" />
							<col width="" />
						</colgroup>
						<thead>
							<tr>
								<th>월</th>
								<th>방문자수</th>
							</tr>
						</thead>
						<tbody>
		                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
		                    <c:if test="${fn:length(logList) == 0}">
		                        <tr>
		                            <td class="lt_text3" colspan="2"><spring:message code="common.nodata.msg" /></td>
		                        </tr>
		                    </c:if>
		                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
							
							<c:forEach var="x" items="${logList}" varStatus="s">
								<tr>
									<td>${x.mt}</td>
									<td><fmt:formatNumber type="number" value="${x.conectCo}"  /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</c:if>


			<c:if test="${cmd == 'year'}">
				<div id="year">
					<h2>년별 방문자</h2>
					<div class="img_box mb20" style="border:1px solid #cccccc">
						<div id="highchart_year" style="min-width: 910px; height: 290px; max-width: 1200px; margin: 0 auto"></div>
					</div>
					
					<table class="table table-striped table-bordered table-hover "  id="">
						<colgroup>
							<col width="" />
							<col width="" />
						</colgroup>
						<thead>
							<tr>
								<th>년</th>
								<th>방문자수</th>
							</tr>
						</thead>
						<tbody>
		                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
		                    <c:if test="${fn:length(logList) == 0}">
		                        <tr>
		                            <td class="lt_text3" colspan="2"><spring:message code="common.nodata.msg" /></td>
		                        </tr>
		                    </c:if>
		                    <%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
							
							<c:forEach var="x" items="${logList}" varStatus="s">
								<tr>
									<td>${x.year}</td>
									<td><fmt:formatNumber type="number" value="${x.conectCo}"  /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
				</div>
			</c:if>

			</div>
	
		</div><!-- /page-wrapper -->

</body>
</html>
