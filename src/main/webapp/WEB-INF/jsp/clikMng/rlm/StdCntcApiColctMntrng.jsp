<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"		uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>표준연계API 모니터링</title>

<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function() {
	
});

/* *****************************
* 공통 레이어 팟업 open
********************************* */
function fnOpenLayerPopup(RASMBLY_ID,CMMND_CODE){
	var bw = $('body').width();
    var sw = $('#scrollbar').width();
    var wh = $(window).height();
    var dh = $(document).height();
    $('body').css('overflow', 'hidden').width( bw-sw );
    
    var frm = document.getElementById("frm");
	frm.RASMBLY_ID.value = RASMBLY_ID;
	frm.CMMND_CODE.value = CMMND_CODE;
	
	//업데이트일 경우
	if(CMMND_CODE == "REQ003"){
		var frm = document.getElementById("frmUpdate");
		frm.RASMBLY_ID.value = RASMBLY_ID;
		frm.CMMND_CODE.value = CMMND_CODE;
	}
	
    $('div[id^="layer_pop_REQ"]').hide();
    $('#layer_pop_'+CMMND_CODE).show();
    $('#layerPop').show();
}

/* *****************************
* 공통 레이어 팟업 close
********************************* */
function fnCloseLayerPopup(){
	$('#layerPop').hide();
	$('#scrollbar').hide();
	$('body').css('overflow', '').css('width', '');
	$('#councilUpdateFile').val('');
}

/* *****************************
* 서버 start_up 요청
********************************* */
function fnServerStartup(){
	if(confirm("지방의회 수집API 구동 요청을 하시겠습니까?")){
		var frm = document.getElementById("frm");
		//frm.RASMBLY_ID.value = RASMBLY_ID;
		frm.CMMND_CODE.value = "REQ001";
		frm.EXE_CMMND.value = $('#startExeCmmnd').val();
		frm.submit();
	}
}

/* *****************************
* 서버 shutdown 요청
********************************* */
function fnServerShutdown(){
	if(confirm("지방의회 수집API 종료 요청을 하시겠습니까?")){
		var frm = document.getElementById("frm");
		//frm.RASMBLY_ID.value = RASMBLY_ID;
		frm.CMMND_CODE.value = "REQ002";
		frm.EXE_CMMND.value = $('#stopExeCmmnd').val();
		frm.submit();
	}
}

/* *****************************
* log list 요청
********************************* */
function fnAgentLogfileList(RASMBLY_ID){
	if(confirm("지방의회 agent 로그목록 요청을 하시겠습니까?")){
		var frm = document.getElementById("frm");
		frm.RASMBLY_ID.value = RASMBLY_ID;
		frm.CMMND_CODE.value = "REQ004";
		frm.submit();
	}
}

/* *****************************
* log view 요청
********************************* */
function fnAgentLogFileView(RASMBLY_ID){
	if(confirm("지방의회 agent 로그상세 요청을 하시겠습니까?")){
		var frm = document.getElementById("frm");
		frm.RASMBLY_ID.value = RASMBLY_ID;
		frm.CMMND_CODE.value = "REQ005";
		frm.LOG_FILE_NAME.value = $('#logFileName').val();
		frm.LOG_FILE_LINE_CO.value = $('#logFileLineCo').val();
		frm.submit();
	}
}

/* *****************************
* log 상세화면
********************************* */
function fnLogFileDetailView(RASMBLY_ID){
	window.open("/rlm/selectCouncilSystemControllList.do?RASMBLY_ID="+RASMBLY_ID,"_blank","width=1366,height=748,scrollbars=yes,resizable=yes");
}

/* *****************************
* 재수집 요청
********************************* */
function fnRecollTerm(RASMBLY_ID){
	if(confirm("지방의회 연계데이터 재수집 요청을 하시겠습니까?")){
		var frm = document.getElementById("frm");
		frm.RASMBLY_ID.value = RASMBLY_ID;
		frm.CMMND_CODE.value = "REQ007";
		frm.submit();
	}
}

/* *****************************
* request interval time 수정 요청
********************************* */
function fnAgentRequestInterval(){
	if(confirm("지방의회 요청시간간격 수정요청을 하시겠습니까?")){
		if($('#intervalTime').val() == ""){
			alert("시간 간격을 입력해주세요.");
			$('#intervalTime').focus();
			return false;
		}
		
		var frm = document.getElementById("frm");
		//frm.RASMBLY_ID.value = RASMBLY_ID;
		//frm.CMMND_CODE.value = "REQ006";
		frm.SET_INTERVAL.value = $('#intervalTime').val();
		frm.submit();
	}
}

/* *****************************
* update file upload 요청
********************************* */
function fnAgentSystemUpdate(){
	if(confirm("지방의회 업데이트 요청을 하시겠습니까?")){
		var frm = $('#frmUpdate');
		if($(frm).find('input[name="councilUpdateFile"]').val() == ""){
			alert("파일을 입력해주세요.");
			$(frm).find('input[name="councilUpdateFile"]').focus();
			return false;
		}
		frm.submit();
	}
}

/* *****************************
* 기타 명령 요청
********************************* */
function fnEtcUrlCall(){
	if(confirm("지방의회 요청시간간격 수정요청을 하시겠습니까?")){
		if($('#callUrl').val() == ""){
			alert("호출할 URL을 입력해주세요.");
			$('#callUrl').focus();
			return false;
		}
		var frm = document.getElementById("frm");
		//frm.RASMBLY_ID.value = RASMBLY_ID;
		//frm.CMMND_CODE.value = "REQ901";
		frm.CALL_URL.value = $('#callUrl').val();
		frm.submit();
	}
}

/* *****************************
* 시스템제어 항목 표시
********************************* */
function fnOpenCallInfo(el){
	$(el).parent().parent().next().toggle();
	if( $(el).find('i').hasClass('fa-minus-square') ){
		$(el).find('i').removeClass('fa-minus-square');
		$(el).find('i').addClass('fa-plus-square');
	}else if( $(el).find('i').hasClass('fa-plus-square') ){
		$(el).find('i').removeClass('fa-plus-square');
		$(el).find('i').addClass('fa-minus-square');
	}
}
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
	<div id="page-wrapper">
        <div class="row">
	        <div class="col-lg-12">
	        	<h1 class="page-header">표준연계API 모니터링</h1>
	        </div>
       	</div>
		<!-- /.row -->

		<h2>표준연계API 모니터링</h2>
		
		<form id="frm" name="frm" method="post" action="/rlm/insertCouncilSystemControl.do" enctype="multipart/form-data">
			<input type="hidden" name="RASMBLY_ID" value=""/>
			<input type="hidden" name="CMMND_CODE" value=""/>
			<input type="hidden" name="EXE_CMMND" value=""/>
			<input type="hidden" name="LOG_FILE_NAME" value=""/>
			<input type="hidden" name="LOG_FILE_LINE_CO" value=""/>
			<input type="hidden" name="CALL_URL" value=""/>
			<input type="hidden" name="SET_INTERVAL" value=""/>
		</form>
		
		<div class="api-box" style="overflow:hidden;">
		<c:forEach items="${resultList}" var="item" varStatus="s">
			<c:if test="${item.SYSTEM_STTUS_CODE == '000' }"><c:set var="status" value="활성화"/></c:if>
			<c:if test="${item.SYSTEM_STTUS_CODE == '001'}"><c:set var="status" value="미 테스트"/></c:if>
			<c:if test="${item.SYSTEM_STTUS_CODE == '002'}"><c:set var="status" value="비활성화"/></c:if>
			
			<c:if test="${item.SYSTEM_STTUS_CODE == '000' && item.DELAY_SE_CODE == 'NORMAL' }"> <c:set var="style" value="color:#383838; font-size: 14px;"/> </c:if>
			<c:if test="${item.SYSTEM_STTUS_CODE != '000' || item.DELAY_SE_CODE == 'ERROR'}"> <c:set var="style" value="color:#D9534F; font-size: 14px;"/> </c:if>
					
			<div class="panel panel-default" style="/*width:31%;*/min-width:510px; float:left; margin-right:10px;">
				<div class="panel-heading" style="background-color:#f5f5f5; color:#383838; border-bottom:1px solid #ddd; padding-right:5px; overflow:hidden;">
					<i class="fa fa-bar-chart-o fa-fw"></i> ${item.RASMBLY_NM } 
					<span style="${style}">[${status} / ${item.LAST_CNTC_DT}]</span> 
<%-- 					<button type="button" class="btn btn-default" style="margin-left:5px;padding: 6px 2px;font-size:13px; float: right;" onclick="fnLogFileDetailView('${item.RASMBLY_ID}')">시스템제어이력</button> --%>
					<div style="display: inline-block; float: right; font-size: 1.2em; margin-right: 3px;">
	  					<a href="#none" style="margin-right: 3px;" onclick="fnLogFileDetailView('${item.RASMBLY_ID}')" title="시스템제어이력"><i class="fa fa-search" style="line-height: 20px;"></i></a>
	  					<a href="#none" onclick="fnOpenCallInfo(this);" title="제어요청상세"><i class="fa fa-plus-square" style="line-height: 20px;"></i></a>
					</div>
				</div>
				
				<div class="pl10 pt10 pr10 pb10" style="display:none;">
					<!-- 연계API 구동 -->
					<div>
					<c:choose>
						<c:when test="${item.CMMND_CODE_001 != null && item.CMMND_EXC_AT_001 == 'N' }">
						<button type="button" class="btn btn-warning" style="width:160px;">수집API 구동 요청중</button>
						<div style="display: inline-block; min-width:200px; height:40px;">
						최근요청일시 : ${item.FRST_REGIST_DT_001} 
						</div>
						</c:when>
						<c:otherwise>
						<button type="button" class="btn btn-success" style="width:160px;" onclick="fnOpenLayerPopup('${item.RASMBLY_ID}','REQ001');">수집API 구동</button>
						<div style="display: inline-block; min-width:200px; height:40px;">
						최근수행일시 : ${item.CMMND_EXC_DT_001}
						</div>						
						</c:otherwise>
					</c:choose>
					</div>
					
					<!-- 연계API 종료 -->
					<div>
					<c:choose>
						<c:when test="${item.CMMND_CODE_002 != null && item.CMMND_EXC_AT_002 == 'N' }">
						<button type="button" class="btn btn-warning" style="width:160px;">수집API 종료 요청중</button>
						<div style="display: inline-block; min-width:200px; height:40px;">
						최근요청일시 : ${item.FRST_REGIST_DT_002} 
						</div>
						</c:when>
						<c:otherwise>
						<button type="button" class="btn btn-success" style="width:160px;" onclick="fnOpenLayerPopup('${item.RASMBLY_ID}','REQ002');">수집API 종료</button>
						<div style="display: inline-block; min-width:200px; height:40px;">
						최근수행일시 : ${item.CMMND_EXC_DT_002}
						</div>						
						</c:otherwise>
					</c:choose>
					</div>
					
					<!-- 업데이트 -->
					<div>
					<c:choose>
						<c:when test="${item.CMMND_CODE_003 != null && item.CMMND_EXC_AT_003 == 'N' }">
						<button type="button" class="btn btn-warning" style="width:160px;">업데이트 요청중</button>
						<div style="display: inline-block; min-width:200px; height:40px;">
						최근요청일시 : ${item.FRST_REGIST_DT_003} 
						</div>
						</c:when>
						<c:otherwise>
						<button type="button" class="btn btn-success" style="width:160px;" onclick="fnOpenLayerPopup('${item.RASMBLY_ID}','REQ003')">업데이트 요청</button>
						<div style="display: inline-block; min-width:200px; height:40px;">
						최근수행일시 : ${item.CMMND_EXC_DT_003}
						</div>						
						</c:otherwise>
					</c:choose>
					</div>
					
					<!-- 요청간격변경 -->
					<div>
					<c:choose>
						<c:when test="${item.CMMND_CODE_006 != null && item.CMMND_EXC_AT_006 == 'N' }">
							<button type="button" class="btn btn-warning" style="width:160px;">요청간격변경 요청중</button>
							<div style="display: inline-block; min-width:200px; height:40px;">
							최근요청일시 : ${item.FRST_REGIST_DT_006}
							</div>
							<br>[요청간격 : ${item.SET_INTERVAL }초]
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-success" style="width:160px;" onclick="fnOpenLayerPopup('${item.RASMBLY_ID}','REQ006')">요청간격변경 요청</button>
							<div style="display: inline-block; min-width:200px; height:40px;">
							최근수행일시 : ${item.CMMND_EXC_DT_006}
							</div>
							<br>[설정간격 :<c:choose>
									<c:when test="${item.SET_INTERVAL != null}">${item.SET_INTERVAL }초</c:when>
									<c:otherwise>설정정보없음</c:otherwise>
									</c:choose>]
						</c:otherwise>
					</c:choose>
					</div>
					
				</div>
				
			</div><!--//panel panel-default-->
			
			</c:forEach>
			
		</div><!--//api-box-->
		
		
		<br>
		<h2>연계파일 저장용량 모니터링</h2>
		
		<!-- 
		progressbar %에 따른 색상 / class명
		 0 ~ 60 : 초록 / progress-bar-success
		61 ~ 80 : 노랑 / progress-bar-warning
		81 ~    : 빨강  / progress-bar-danger
		  -->
		<script>
		$(document).ready(function() {
			//clikapi-file/clik001
			if(${clikapiFileDiskUsableRate } <= 60){
				$('#prog_api').addClass("progress-bar-success");
				
			}else if(${clikapiFileDiskUsableRate } <= 80){
				$('#prog_api').addClass("progress-bar-warning");
			}else{
				$('#prog_api').addClass("progress-bar-danger");
			}
			
			//clik-data
			if(${clikDataDiskUsableRate } <= 60){
				$('#prog_data').addClass("progress-bar-success");
				
			}else if(${clikDataDiskUsableRate } <= 80){
				$('#prog_data').addClass("progress-bar-warning");
			}else{
				$('#prog_data').addClass("progress-bar-danger");
			}
			
			//clik-cols
			if(${clikColsDiskUsableRate } <= 60){
				$('#prog_cols').addClass("progress-bar-success");
				
			}else if(${clikColsDiskUsableRate } <= 80){
				$('#prog_cols').addClass("progress-bar-warning");
			}else{
				$('#prog_cols').addClass("progress-bar-danger");
			}
		});
		</script>
				  
		<div class="panel panel-default" style="  width:32%; float:left; margin-right:10px;">
			<div class="panel-heading">
				<i class="fa fa-bar-chart-o fa-fw"></i> /clikapi-file/clik001
			</div>
			<div class="pl10 pt10 pr10 pb10">
					<p>
						<strong>${clikapiFileDiskUsableRate }%</strong>
						<span class="pull-right text-muted"></span>
					</p>
					<div class="progress progress-striped active">
						<div id="prog_api" class="progress-bar" role="progressbar" aria-valuenow="${clikapiFileDiskUsableRate }" aria-valuemin="0" aria-valuemax="100" style="width: ${clikapiFileDiskUsableRate }%">
							<span class="sr-only">${clikapiFileDiskUsableRate }% Complete</span>
						</div>
					</div>
	
					T : ${clikapiFileDiskSizeGB } GB<br/>
					U : ${clikDataDiskUsableSizeGB } GB<br/>
					A : ${clikapiFileDiskFreeSizeGB } GB
	
				</div>
		</div>
	
		<div class="panel panel-default" style="  width:32%; float:left; margin-right:10px;">
			<div class="panel-heading">
				<i class="fa fa-bar-chart-o fa-fw"></i> /clik-data
			</div>
			<div class="pl10 pt10 pr10 pb10">
					<p>
						<strong>${clikDataDiskUsableRate }%</strong>
						<span class="pull-right text-muted"></span>
					</p>
					<div class="progress progress-striped active">
						<div id="prog_data" class="progress-bar" role="progressbar" aria-valuenow="${clikDataDiskUsableRate }" aria-valuemin="0" aria-valuemax="100" style="width: ${clikDataDiskUsableRate }%">
                            <span class="sr-only">${clikDataDiskUsableRate }% Complete</span>
                        </div>
					</div>
	
					T : ${clikDataDiskSizeGB } GB<br/>
					U : ${clikDataDiskUsableSizeGB } GB<br/>
					A : ${clikDataDiskFreeSizeGB } GB
	
				</div>
		</div>
	
		<div class="panel panel-default" style="  width:32%; float:left; margin-right:10px;">
			<div class="panel-heading">
				<i class="fa fa-bar-chart-o fa-fw"></i> /clik-cols
			</div>
			<div class="pl10 pt10 pr10 pb10">
					<p>
						<strong>${clikColsDiskUsableRate }%</strong>
						<span class="pull-right text-muted"></span>
					</p>
					<div class="progress progress-striped active">
						<div id="prog_cols" class="progress-bar" role="progressbar" aria-valuenow="${clikColsDiskUsableRate }" aria-valuemin="0" aria-valuemax="100" style="width: ${clikColsDiskUsableRate }%">
                            <span class="sr-only">${clikColsDiskUsableRate }% Complete (warning)</span>
                        </div>
					</div>
	
					T : ${clikColsDiskSizeGB } GB<br/>
					U : ${clikColsDiskUsableSizeGB } GB<br/>
					A : ${clikColsDiskFreeSizeGB } GB
					
				</div>
		</div> 
				
	</div><!--//page-wrapper-->
	
	<div id="scrollbar" style="position:fixed; top:0; right:0; height:100%; overflow-y:scroll; display:none; opacity:0.5;"></div>
	
	<div id="layerPop" style="display:none; background:url(/images/clikmng/bg_layer.png) left top repeat; width:100%; height:100%; min-height:904px;
	 position:fixed; top:0; left:0; z-index:999999999999999999999999999999;">
		<div class="layerWrap " style="position:relative; width:50%; left:25%; top:25%;">
			<div style="background: #ffffff; margin:20px auto; padding:60px;">
			
				<!-- 서버시작 -->
				<div id="layer_pop_REQ001">
					<h3>수집API 구동 요청</h3>
					실행 명령어 : <input type="text" id="startExeCmmnd" name="startExeCmmnd" style="width:200px;"/>
					<button onclick="return fnServerStartup()">요청</button>
					<button onclick="return fnCloseLayerPopup();">취소</button>
				</div>
				
				<!-- 서버종료 -->
				<div id="layer_pop_REQ002">
					<h3>수집API 종료 요청</h3>
					실행 명령어 : <input type="text" id="stopExeCmmnd" name="stopExeCmmnd" style="width:200px;"/>
					<button onclick="return fnServerShutdown()">요청</button>
					<button onclick="return fnCloseLayerPopup();">취소</button>
				</div>
			
				<!-- 업데이트 -->
				<div id="layer_pop_REQ003">
					<h3>연계API서버 업데이트</h3>
					<form style="display:inline-block; width:100%;" id="frmUpdate" name="frmUpdate" method="post" action="/rlm/insertCouncilSystemControl.do" enctype="multipart/form-data">
						<input type="hidden" name="RASMBLY_ID" value=""/>
						<input type="hidden" name="CMMND_CODE" value=""/>
					
					<p style="width: 150px; display: inherit;">배포파일 : </p> <input type="file" style="width:70%; display:inline;" id="councilUpdateFile" name="councilUpdateFile" style="display:inline-block;" /><br>
					<p style="width: 150px; display: inherit;">배포경로 : </p> <input type="text" style="width:70%;" id="TRGET_FILE_PATH" name="TRGET_FILE_PATH" /><br>
					<p style="width: 150px; display: inherit;">파일크기 : </p> <input type="text" id="TRGET_FILE_SIZE" name="TRGET_FILE_SIZE" /><br>
					</form>
					<button onclick="return fnAgentSystemUpdate()">요청</button>
					<button onclick="return fnCloseLayerPopup();">취소</button>
				</div>
				
				<!-- 서버로그목록 -->
<!-- 				<div id="layer_pop_REQ004">서버로그목록</div> -->
				
				<!-- 서버로그상세 -->
				<div id="layer_pop_REQ005">
					로그파일명(옵션) : <input type="text" id="logFileName" name="logFileName" />
					로그파일출력라인수(옵션) : <input type="text" id="logFileLineCo" name="logFileLineCo" />
					<button onclick="return fnEtcUrlCall()">요청</button>
					<button onclick="return fnCloseLayerPopup();">취소</button>
				</div>
				
				<!-- 서버요청간격 -->
				<div id="layer_pop_REQ006">
					<h3>서버요청 간격 변경</h3>
					요청간격시간(초단위) : <input type="text" id="intervalTime" name="intervalTime" />
					<button onclick="return fnAgentRequestInterval()">요청</button>
					<button onclick="return fnCloseLayerPopup();">취소</button>
				</div>
				
				<!-- 재수집 -->
<!-- 				<div id="layer_pop_REQ007">재수집</div> -->
				
				<!-- 기타명령 -->
				<div id="layer_pop_REQ901">
					요청 URL : <input type="text" id="callUrl" name="callUrl" />
					<button onclick="return fnEtcUrlCall()">요청</button>
					<button onclick="return fnCloseLayerPopup();">취소</button>
				</div>
				
			</div>
			<a href="#none" onclick="return fnCloseLayerPopup();" style="position:absolute; right:0; top:0;"><img src="/images/clikmng/layerClosebtn1.gif" alt="닫기"></a>
		</div>
	</div>

</body>
</html>