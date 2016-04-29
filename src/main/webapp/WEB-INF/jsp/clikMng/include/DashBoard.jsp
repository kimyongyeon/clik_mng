<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui"		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring"	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"		uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>대쉬보드</title>
	<script src="/js/clikmng/highcharts/highcharts.js"></script>
	<script src="/js/clikmng/highchartTable/jquery.highchartTable-min.js"></script>

	<c:forEach var="x" items="hourList" varStatus="s">
		<c:set var="xAxis">
			${xAxis}, x.hour
		</c:set>
		<c:set var="yAxis">
			${yAxis}, x.cnt
		</c:set>
	</c:forEach>

	<script type="text/javaScript" language="javascript" defer="defer">
	<!--
	$(function () {
		$(document).ready(function() {
			
			//사이즈 조정
			var pHeight_0 = document.getElementById('panel_0').offsetHeight;
			var pHeight_1 = document.getElementById('panel_1').offsetHeight;
			
			var minHeight = pHeight_0 > pHeight_1 ? pHeight_0 : pHeight_1;
			
			$('#panel_0').css('min-height', minHeight);
			$('#panel_1').css('min-height', minHeight);
			

			// 금일 OS 별 이용자 통계 차트
			$('#summary_pie').highcharts({
				chart: {
					plotBackgroundColor: null,
					plotBorderWidth: 1,
					plotShadow: false
				},
				title:false,
				tooltip: {
					pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
				},
				plotOptions: {
					pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						dataLabels: {
							enabled: true,
							format: '<b>{point.name}</b>: {point.percentage:.1f} %'
						},
						showInLegend: true
					}
				},
				series: [{
					type: 'pie',
					name: '이용통계',
					data: [
						   <c:forEach var="x" items="${platformList}" varStatus="s">
						   ['<c:out value="${x.userOs}"/>', <c:out value="${x.conectCo}"/>],
						   </c:forEach>
						]

				}]
			});

			// 금일 시간별 이용자 통계 차트
			$('#summary_bar').highcharts({
				chart: {
					type: 'column'
				},
				title:false,
				xAxis: {
					categories: [
						<c:forEach var="x" begin="0" end="23" step="1" varStatus="status">
							<fmt:formatNumber var="h" value="${x}" pattern="00"/>
							<c:out value="'${h}'," escapeXml="false" />
						</c:forEach>
					]
				},
				yAxis: {
					min: 0,
					title: false
				},
				tooltip: {
					headerFormat: '<span style="font-size:10px">{point.key}시</span><table>',
					pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
						'<td style="padding:0"><b>{point.y:.1f} 명</b></td></tr>',
					footerFormat: '</table>',
					shared: true,
					useHTML: true
				},
				plotOptions: {
					column: {
						pointPadding: 0.1,
						borderWidth: 0
					}
				},
				series: [{
					name: '방문자수',
					data: [
						<c:forEach var="x" begin="0" end="23" step="1" varStatus="status">
							<fmt:formatNumber var="h" value="${x}" pattern="00"/>
							<c:set var="_conectCo" value="0"/>
	
							<c:forEach var="xx" items="${hourList}" varStatus="s">
								<c:if test="${xx.hour == h}">
									<c:set var="_conectCo" value="${xx.conectCo}"/>
								</c:if>
							</c:forEach>
							
							<c:out value="${_conectCo}," escapeXml="false" />
						</c:forEach>
					]
				}]
			});

		});
	});
	-->
	</script>

	<style type="text/css">
		#page-wrapper{ overflow:hidden; padding-top:70px;}
		.fl{ width:48%; float:left; margin-right:15px;}
		.fr{ width:48%; float:left; margin-right:15px;}
		.panel-heading{ padding:10px 10px 10px 10px;} 
	</style> 

</head>

<body>
<jsp:include page="/cmm/top/top.do" />
 

		<div id="page-wrapper" style="margin-top:62px;">

			<form id="frm" name="frm" method="post" >

				<div class="panel panel-default fl" id="panel_0">
					<div class="panel-heading">
						<i class="fa fa-tasks fa-fw"></i> [지방의회/지자체 담당자 승인요청 목록]
						<div class="pull-right">
							<span class="btn btn-default btn-xs dropdown-toggle"><a href="<c:url value='/uss/mng/LocalMngList.do' />">+ 더보기</a></span>
						</div>
					</div><!-- /.panel-heading -->

					<div class="panel-body">
						<table class="table table-striped table-bordered table-hover "  id="">
							<colgroup>
								<col width="" />
								<col width="" />
								<col width="" />
								<col width="" />
								<col width="" />

							</colgroup>
							<thead>
								<tr>
									<td>번호</td>
									<td>아이디</td>
									<td>이름</td>
									<td>소속</td>
									<td>전화번호</td>
								</tr>
							</thead>
							<tbody>
							<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
							<c:if test="${fn:length(localMngList) == 0}">
								<tr>
									<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
								</tr>
							</c:if>
							<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
							<c:forEach var="x" items="${localMngList}" varStatus="s">
							<tr>
								<td>${s.index + 1}</td>
								<td><a href="<c:url value='/uss/mng/LocalMngDetail.do?authorClCode=CLIK&unityId=${x.unityId}' />">${x.unityId}</a></td>
								<td>${x.chargerNm}</td>
								<td>${x.loasmNm}</td>
								<td>${x.chargerTelno}</td>
							</tr>
							</c:forEach>
							</tbody>
						</table>
					</div><!-- /.panel-body -->
				</div><!-- /.panel -->

<!-- 				<div class="panel panel-default fl"> -->
<!-- 					<div class="panel-heading"> -->
<!-- 						<i class="fa fa-tasks fa-fw"></i> [승인 대기 게시물 목록] -->
<!-- 						<div class="pull-right"> -->
<%-- 							<span class="btn btn-default btn-xs dropdown-toggle"><a href="<c:url value='/mdm/MdmPolicyInfoList.do' />" >+ 더보기</a></span> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					/.panel-heading -->
<!-- 					<div class="panel-body"> -->
<!-- 						<table class="table table-striped table-bordered table-hover "  id=""> -->
<%-- 							<colgroup> --%>
<%-- 								<col width="" /> --%>
<%-- 								<col width="" /> --%>
<%-- 								<col width="" /> --%>
<%-- 								<col width="" /> --%>
<%-- 							</colgroup> --%>
<!-- 							<thead> -->
<!-- 								<tr> -->
<!-- 									<td>번호</td> -->
<!-- 									<td>자료유형</td> -->
<!-- 									<td>수집유형</td> -->
<!-- 									<td>제목</td> -->
<!-- 								</tr> -->
<!-- 							</thead> -->
<!-- 							<tbody> -->
<%-- 							데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
<%-- 							<c:if test="${fn:length(policyInfoList) == 0}"> --%>
<!-- 								<tr> -->
<%-- 									<td class="lt_text3" colspan="4"><spring:message code="common.nodata.msg" /></td> --%>
<!-- 								</tr> -->
<%-- 							</c:if> --%>
<%-- 							데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
<%-- 							<c:forEach var="x" items="${policyInfoList}" varStatus="s"> --%>
<!-- 							<tr> -->
<%-- 								<td>${s.index + 1}</td> --%>
<!-- 								<td>지방정책정보</td> -->
<!-- 								<td>웹크롤러</td> -->
<%-- 								<td><a href="/mdm/MdmPolicyInfoView.do?extId=${fn:trim(x.EXTID)}"><span>${x.TITLE }</span></a></td> --%>
<!-- 							</tr> -->
<%-- 							</c:forEach> --%>
<!-- 							</tbody> -->
<!-- 						</table> -->
<!-- 					</div>//.panel-body -->
<!-- 				</div>/.panel -->

				<div class="panel panel-default fl" id="panel_1">
					<div class="panel-heading">
						<i class="fa fa-tasks fa-fw"></i> [Open API 발급 목록]
						<div class="pull-right">
							<span class="btn btn-default btn-xs dropdown-toggle"><a href="<c:url value='/rcm/AilList.do' />" >+ 더보기</a></span>
						</div>
					</div><!-- /.panel-heading -->
					
					<div class="panel-body">
						<table class="table table-striped table-bordered table-hover "  id="">
							<colgroup>
								<col width="" />
								<col width="" />
								<col width="" />
								<col width="" />
							</colgroup>
							<thead>
								<tr>
									<td>번호</td>
									<td>신청기관명</td>
									<td>승인여부</td>
									<td>승인일자</td>
								</tr>
							</thead>
							<tbody>
								<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
								<c:if test="${fn:length(rcmList) == 0}">
									<tr>
										<td class="lt_text3" colspan="4"><spring:message code="common.nodata.msg" /></td>
									</tr>
								</c:if>
								<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
								<c:forEach var="x" items="${rcmList}" varStatus="s">
									<tr>
										<td>${s.index + 1}</td>
										<td>
											<a href="<c:url value='/rcm/AilDetail.do?authorClCode=CLIK&unityId=${x.unityId}' />">
												${x.reqstInsttNm }
											</a>
										</td>
										<td>${x.sttusCode}</td>
										<td>
											<fmt:parseDate value="${x.frstRegistPnttm}" var="dateFmt" pattern="yyyyMMdd"/>
											<fmt:formatDate value="${dateFmt}"  pattern="yyyy.MM.dd" />
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div><!-- /.panel-body -->
				</div><!-- /.panel -->
<!-- 2015.06.09 서부장님 요청으로 주석 처리 -->
<!-- 				<div class="panel panel-default fl"> -->
<!-- 					<div class="panel-heading"> -->
<!-- 						<i class="fa fa-tasks fa-fw"></i> [모바일 의견 목록] -->
<!-- 						<div class="pull-right"> -->
<%-- 							<span class="btn btn-default btn-xs dropdown-toggle"><a href="<c:url value='/mob/OnsList.do' />">+ 더보기</a></span> --%>
<!-- 						</div> -->
<!-- 					</div>/.panel-heading -->
					
<!-- 					<div class="panel-body"> -->
<!-- 						<table class="table table-striped table-bordered table-hover "  id=""> -->
<%-- 							<colgroup> --%>
<%-- 								<col width="" /> --%>
<%-- 								<col width="" /> --%>
<%-- 								<col width="" /> --%>
<%-- 								<col width="" /> --%>
<%-- 							</colgroup> --%>
<!-- 							<thead> -->
<!-- 								<tr> -->
<!-- 									<td>번호</td> -->
<!-- 									<td>의견</td> -->
<!-- 									<td>작성자</td> -->
<!-- 									<td>등록일</td> -->
<!-- 								</tr> -->
<!-- 							</thead> -->
<!-- 							<tbody> -->
<%-- 								데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
<%-- 								<c:if test="${fn:length(mobList) == 0}"> --%>
<!-- 									<tr> -->
<%-- 										<td class="lt_text3" colspan="4"><spring:message code="common.nodata.msg" /></td> --%>
<!-- 									</tr> -->
<%-- 								</c:if> --%>
<%-- 								데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
<%-- 								<c:forEach var="x" items="${mobList}" varStatus="s"> --%>
<!-- 									<tr> -->
<%-- 										<td>${s.index + 1}</td> --%>
<%-- 										<td>${x.opinionCn }</td> --%>
<%-- 										<td>${x.userId }</td> --%>
<%-- 										<td>${x.regDate}</td> --%>
<!-- 									</tr> -->
<%-- 								</c:forEach> --%>
<!-- 							</tbody> -->
<!-- 						</table> -->
<!-- 					</div>//.panel-body -->
<!-- 				</div>/.panel -->

				<div class="panel panel-default fl" style="border:1px solid #cccccc; clear:both;">
					<div class="panel-heading">
						<i class="fa fa-tasks fa-fw"></i> [OS별 이용자 통계]
					</div><!-- /.panel-heading -->
					<div class="panel-body">
						<!-- 금일 OS 별 이용자 통계 차트 -->
						<div class="panel panel-default" style="border:1px solid #cccccc;">
							<div class="panel-body">
								<div id="summary_pie" style="min-width: 310px; height: 300px; max-width: 600px; margin: 0 auto"></div>
							</div><!-- /.panel-body -->
						</div><!-- /.panel -->
	
						<div class="panel panel-default">
							<div class="list-group">
								<a href="#" class="list-group-item">
									<i class="fa fa-comment fa-fw"></i>&nbsp;전일 방문자 수&nbsp;
									[<fmt:formatNumber value="${cntBefore}" type="number" />]
								</a>
								<a href="#" class="list-group-item">
									<i class="fa fa-comment fa-fw"></i>&nbsp;금일 방문자 수&nbsp;
									[<fmt:formatNumber value="${cntToday}" type="number" />]
								</a>
							</div><!-- /.list-group -->
						</div><!-- /.panel -->
					</div>
				</div>



				<div class="panel panel-default fl" style="border:1px solid #cccccc;">
					<div class="panel-heading">
						<i class="fa fa-tasks fa-fw"></i> [방문자 통계]
					</div><!-- /.panel-heading -->
					<div class="panel-body">
						<div id="summary_bar" style="min-width: 310px; height: 450px; max-width: 600px; margin: 0 auto"></div>
					</div><!-- /.panel-body --> 
				</div><!-- /.panel -->

			</form>

		</div><!--//page-wrapper-->


</body>
</html>
