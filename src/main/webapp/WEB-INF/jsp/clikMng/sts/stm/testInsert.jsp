<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


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
    $(document).ready(function() {

		//$('table.highchart').highchartTable();

		
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
					[${x.userOs}, ${x.conectCo}],
					</c:forEach>
    			]
    		}]
    	});
    	</c:if>

		//브라우져별 방문자 통계
		<c:if test="${cmd == 'wbsr'}">
		$('#highchart_brws').highcharts({
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
					['Android Browser', 603],
					['Blackberry', 407],
					['Chrome', 920],
					['Firefox', 19],
					['IE with Chrome Frame', 14],
					['Internet Explorer', 3787],
					['Maxthon', 1],
					['Safari', 70],
					['Safari(in-app)', 79]
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
				categories:	[<c:forEach var="x" begin="0" end="23">'${x}시',</c:forEach>]
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
				pointFormat: '{series.name}: <b>{point.percentage:.1f}명</b>',
			},
			series:	[{
				name: '방문자수',
				data: [
					<c:forEach var="x" begin="0" end="23">
						<c:set	var="rand">
							<%= java.lang.Math.round(java.lang.Math.random() * 10) %>
						</c:set>
						<c:out value="${rand}" />,
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
				categories:	[<c:forEach var="x" begin="1" end="30">'2014-10-${x}',</c:forEach>]
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
				pointFormat: '{series.name}: <b>{point.percentage:.1f}명</b>',
			},
			series:	[{
				name: '방문자수',
				data: [
					<c:forEach var="x" begin="1" end="30">
						<c:set var="rand">
							<%= java.lang.Math.round(java.lang.Math.random() * 100) %>
						</c:set>
						<c:out value="${rand}" />,
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
				categories:	[<c:forEach var="x" begin="1" end="12">'${x}윌',</c:forEach>]
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
				pointFormat: '{series.name}: <b>{point.percentage:.1f}명</b>',
			},
			series:	[{
				name: '방문자수',
				data: [
					<c:forEach var="x" begin="1" end="12">
						<c:set var="rand">
							<%= java.lang.Math.round(java.lang.Math.random() * 1000) %>
						</c:set>
						<c:out value="${rand}" />,
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
				categories:	[<c:forEach var="x" begin="1" end="4">'201${x}년',</c:forEach>]
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
				pointFormat: '{series.name}: <b>{point.percentage:.1f}명</b>',
			},
			series:	[{
				name: '방문자수',
				data: [
					<c:forEach var="x" begin="1" end="4">
						<c:set var="rand">
							<%= java.lang.Math.round(java.lang.Math.random() * 100000) %>
						</c:set>
						<c:out value="${rand}" />,
					</c:forEach>
				]
			}]
		});
		</c:if>
		
		//시작 날짜 세팅
        $('#selectBgdate').datepicker({
        	dateFormat: 'yy-mm-dd',
        	changeYear: true,
        	changeMonth: true, 
        	showMonthAfterYear: true,
        	showButtonPanel:true,
        	showOn: 'both',
            buttonText: '<img src="<c:url value='/images/clikmng/cmm/icon/bu_icon_carlendar.gif' />" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
        	yearRange: '-100:+0',
            dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
            dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
            monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']        	
        }); 

		// 종료날짜 세팅
        $('#selectEndate').datepicker({
        	dateFormat: 'yy-mm-dd',
        	changeYear: true,
        	changeMonth: true, 
        	showMonthAfterYear: true,
        	showButtonPanel:true,
        	showOn: 'both',
            buttonText: '<img src="<c:url value='/images/clikmng/cmm/icon/bu_icon_carlendar.gif' />" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
        	yearRange: '-100:+0',
            dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
            dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
            monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
        }); 

    });

	-->
	</script>
    
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

		<div id="page-wrapper">

				<div class="row">
					<div class="col-md-3">year</div>
					<div class="col-md-3">mt</div>
					<div class="col-md-3">de</div>
					<div class="col-md-3">cnt</div>
				</div>

			<c:forEach var="x" items="${logList}" varStatus="s">
				<div class="row">
					<div class="col-md-3">${x.year}</div>
					<div class="col-md-3">${x.mt}</div>
					<div class="col-md-3">${x.de}</div>
					<div class="col-md-3">${x.conectCo}</div>
				</div>
			</c:forEach>
		</div><!-- /page-wrapper -->

</body>
</html>
