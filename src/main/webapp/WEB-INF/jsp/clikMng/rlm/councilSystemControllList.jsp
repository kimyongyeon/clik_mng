<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>지방의회 시스템제어 log 상세</title>

<script type="text/javaScript" language="javascript" defer="defer">

/* *****************************
* 공통 레이어 팟업 open
********************************* */
function fnOpenLayerPopup(REQST_NO){
	
	$(".spinner").show();
	$.ajax({
		type: "POST",
		url : "/rlm/selectCouncilSystemLogDetailView.do",
		data : {"reustNo" : REQST_NO},
		dataType:"json",
		async : false,
		success: function(args) {
			$(".spinner").hide();
			$('#log_text').text(args[0].logText);
			$('#layerPop').show();
		}
		,error:function(e) {
			$(".spinner").hide();
			alert(e.responseText);
		}
	});
	
}

/* *****************************
* 공통 레이어 팟업 close
********************************* */
function fnCloseLayerPopup(){
	$('#layerPop').hide();
	$('#scrollbar').hide();
	$('body').css('overflow', '').css('width', '');
}

$(document).ready(function() {
	
});
</script>

</head>
<body>

<div>
	<!-- log list -->
	<div>
		<table class="table table-striped table-bordered table-hover" style="cursor: pointer;">
			<colgroup>
				<col width="10%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="20%"/>
				<col width="20%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="10%"/>
			</colgroup>
			<tbody>
				<tr>
					<th>순번</th>
					<th>REUST_NO</th>
					<th>지방의회명</th>
					<th>명령종류</th>
					<th>요청일시</th>
					<th>명령전달여부</th>
					<th>명령수행여부</th>
<!-- 					<th>로그확인</th> -->
				</tr>
				
				<c:choose>
					<c:when test="${fn:length(systemControllList) == 0}">
						<tr><td colspan="8">정보가 존재하지 않습니다.</td></tr>
					</c:when>
					<c:otherwise>
						<c:set var="rowNum" value="0" />
						<c:forEach items="${systemControllList}" var="item" varStatus="s">
						<c:if test="${item.CMMND_NM != 'agent_logfile_view' && item.CMMND_NM != 'agent_logfile_list'}">
						<tr>
							<c:set var="rowNum" value="${rowNum + 1}" />
							<td style="display:none;"><input type="hidden" name="reustNo" value="${item.REQST_NO} " /></td>
							<td>${rowNum}</td>
							<td>${item.REQST_NO }</td>
							<td>${item.RASMBLY_NM }</td>
							<td>${item.CMMND_NM }</td>
							<td>${item.FRST_REGIST_DT }</td>
							<td>${item.CMMND_TRNSMIS_AT }</td>
							<td>${item.CMMND_EXC_AT }</td>
<!-- 							<td> -->
<%-- 								<c:if test="${item.CMMND_CODE == 'REQ005' && item.CMMND_EXC_AT == 'Y'}"><button onclick="fnOpenLayerPopup('${item.REQST_NO}');">로그 상세 보기</button></c:if> --%>
<%-- 								<c:if test="${item.CMMND_CODE == 'REQ005' && item.CMMND_EXC_AT == 'N'}"></c:if> --%>
<!-- 							</td> -->
						</tr>
						</c:if>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<!-- log view -->
	<div id="layerPop" style="display:none; background:url(/images/clikmng/bg_layer.png) left top repeat; width:100%; height:100%; min-height:904px; position:fixed; top:0; left:0; z-index:999999999;">
		<div class="layerWrap " style="position:relative; width:80%; top:5%; margin:auto;">
			<div style="background: #ffffff; margin:20px auto; padding:50px;">
			
				<textarea id="log_text" style="height: 500px; overflow-y: scroll; overflow-x: hidden; width: 100%; border: 0px; margin: 0px;" readonly></textarea>
				
			</div>
			<a href="#none" onclick="return fnCloseLayerPopup();" style="position:absolute; right:0; top:0;"><img src="/images/clikmng/layerClosebtn1.gif" alt="닫기"></a>
		</div>
	</div>
	
	<div class="spinner"></div>
	
</div>

</body>
</html>