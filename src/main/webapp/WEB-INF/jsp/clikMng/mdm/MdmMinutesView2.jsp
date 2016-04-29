<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>지방의회 회의록</title>

<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

var currCnId = '${minutesVO.MINTS_CN}';
var cnList;
$(document).ready(function(){
	
	var mintsCn = '${cnList}';
	cnList = mintsCn.split(",");
	
});

//이전버튼
function fn_prev(){
	$(".spinner").show();
	var frm = document.fvfrm;
	var url = "";
	var i = 0;
	for(; i < cnList.length; i++){
		if(currCnId == cnList[i]){
			if(cnList[i-1] != undefined){
// 				url = "/mdm/MdmMinutesMetaDataView1.do?cnId="+cnList[i-1];
				frm.action = "/mdm/MdmMinutesMetaDataView1.do";
				frm.cnId.value = cnList[i-1]; 
			}
			$('#isPrevNextPaging').val('N');
			break;
		}
	}
	
	if(${mdmSearchVO.pageNum} > 1 && i == 0)
	{
		frm.pageNum.value = ${mdmSearchVO.pageNum} - 1;
		frm.action = "/mdm/MdmMinutesMetaDataView1.do";
		frm.cnId.value = "";
		$('#prevNextGubun').val('prev');
		$('#isPrevNextPaging').val('Y');
	}
	else if(${mdmSearchVO.pageNum} == 1 && i == 0)
	{
		$(".spinner").hide();
		alert("처음 입니다.");
		return false;
	}
	
// 	var frm = document.sfrm;
// 	frm.action = url;
// 	var ret_open = window.open('about:blank','VIEWER_POP','width=1366,height=768,scrollbars=yes,resizable=yes');
	
// 	if(ret_open == null) {
// 		$(".spinner").hide();
// 		alert("팝업차단을 해제하세요!");
// 		return false;
// 	}
	
// 	frm.title.value = '회의록보기';
// 	frm.target='VIEWER_POP';
	frm.submit();
// 	frm.target = "_self";
}

//다음버튼
function fn_next(){
	$(".spinner").show();
	var frm = document.fvfrm;
	var url = "";
	var i = 0;
	for(; i < cnList.length; i++){
		if(currCnId == cnList[i]){
// 			url = "/mdm/MdmMinutesMetaDataView1.do?cnId="+cnList[i+1];
			frm.action = "/mdm/MdmMinutesMetaDataView1.do";
			frm.cnId.value = cnList[i+1]; 
			$('#isPrevNextPaging').val('N');
			break;
		}
	}
	
	if(cnList.length == (i+1) && ${EndPage} > (${mdmSearchVO.pageNum} + 1))
	{
		frm.pageNum.value = ${mdmSearchVO.pageNum} + 1;
		frm.action = "/mdm/MdmMinutesMetaDataView1.do";
		frm.cnId.value = "";
		$('#prevNextGubun').val('next');
		$('#isPrevNextPaging').val('Y');
	}
	else if(cnList.length-1 == (i+1) && ${EndPage} == ${mdmSearchVO.pageNum})
	{
		$(".spinner").hide();
		alert("마지막 입니다.");
		return false;
	}
	
// 	var ret_open = window.open('about:blank','VIEWER_POP','width=1366,height=768,scrollbars=yes,resizable=yes');
	
// 	if(ret_open == null) {
// 		$(".spinner").hide();
// 		alert("팝업차단을 해제하세요!");
// 		return false;
// 	}
	
// 	frm.title.value = '회의록보기';
// 	frm.target='VIEWER_POP';
	frm.submit();
// 	frm.target = "_self";
}

function fn_leftArea() {
	var fullWidth1 = 100;
	var fullWidth = 50;
	var basicWidth1 = 100;
	var basicWidth = 0;
	if($("#leftAreaBtn").css("display") == "none"){
		$("#leftArea").show("swing");
		$("#leftArea").animate({"width": fullWidth1+"%"}, 600 );
		$("#cntArea").animate({"width": fullWidth+"%"}, 600 );
		$("#leftAreaBtn").show();
	}
	else{
		$("#leftAreaBtn").hide();
		$("#leftArea").animate({"width": basicWidth1+"%"}, 400 );
		$("#cntArea").animate({"width": basicWidth+"%"}, 400 );
		$("#leftArea").show("swing");
	}
}

function fileview(disfile) {
	$('#disfile').val(disfile);
	fvfrm.action = "/mdm/MdmMinutesMetaDataView2.do";
	document.fvfrm.submit();
}

function setUpdate() {
	
	if( !confirm("수정하시겠습니까?") ) {
		return false;
	}
	
	$(".spinner").show();
	
	$("#MTGNM").val($("#MTGNM_ID option:selected").text());
	
	pgfrm.action="/mdm/MdmMinutesUpdate.do";
	document.pgfrm.submit();
}

function getDownload(DOWNID) {
	document.location.href="/mdm/MdmMinutesDownLoad.do?DOWNID="+DOWNID;
}

function setDelete() {
	if( !confirm("게시물을 삭제하시겠습니까?") ) {
		return false;
	}
	
	$.ajax({
		url:"/mdm/MdmMinutesDelete.do",
		cache:false,
		type:"post",
		data:{"MINTS_CN":'${minutesVO.MINTS_CN}'},
		success: function(data, status) {
			alert("정상처리되었습니다.");
			location.reload();
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	
}

function setFileDelete(MINTS_CN) {
	if( !confirm($("#filemsg"+MINTS_CN).text() + "\n\n 삭제하시겠습니까?") ) {
		return false;
	}
	$.ajax({
		url:"/mdm/MdmMinutesFileDelete.do",
		cache:false,
		type:"post",
		data:{"MINTS_CN":MINTS_CN},
		success: function(data, status) {
			$("#filemsg"+MINTS_CN).parent().remove();
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}

//대수정보 변경
function changeRasmblyNumpr(numpr){
	//회수정보
	$.ajax({
		url:"/mdm/MdmMinutesRasmblySesnView.do",
		cache:false,
		type:"post",
		data:{
			"rasmbly_id":'${minutesVO.RASMBLY_ID}'
			,rasmbly_numpr:numpr},
		success: function(data, status) {
			var json = $.parseJSON(data);
			$('#RASMBLY_SESN > option').remove();
			var option = "";
			$(json).each(function(index,value){
				option += "<option value='"+value.RASMBLY_SESN+"'>"+value.RASMBLY_SESN+"</option>";
			});
			$('#RASMBLY_SESN').append(option);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	
	//회의명
	$.ajax({
		url:"/mdm/MdmMinutesMtgnmView.do",
		cache:false,
		type:"post",
		data:{
			"rasmbly_id":'${minutesVO.RASMBLY_ID}'
			,rasmbly_numpr:numpr},
		success: function(data, status) {
			var json = $.parseJSON(data);
			$('#MTGNM > option').remove();
			var option = "";
			$(json).each(function(index,value){
				option += "<option value='"+value.RASMBLY_SESN+"'>"+value.RASMBLY_SESN+"</option>";
			});
			$('#MTGNM').append(option);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	
	return false;
}

//안건보기 버튼
function selectItem(){
	var mints_cn = '${minutesVO.MINTS_CN}';
	var option="width=950px,height=400px";
	window.open("/mdm/MdmMinutesItemView.do?mints_cn="+mints_cn,'지방의회 안건정보',option);
	return false;
}

//관련의원보기 버튼
function selectAsembyInfo(){
	var mints_cn = '${minutesVO.MINTS_CN}';
	var option="width=950px,height=400px";
	window.open("/mdm/MdmMinutesAsembyInfoView.do?mints_cn="+mints_cn,'지방의회 관련의원정보',option);
	return false;
}

//서비스시스템 상새화면을 호출한다
function openServicePopup(){
	window.open('http://clik.nanet.go.kr/potal/search/searchView.do?collection=minutes&DOCID=${minutesVO.MINTS_CN}','_blank');
}
</script>
</head>
<body class="body" style="background:none;">
<c:choose>
<c:when test="${fn:length(minutesFileList) >= 0 && disfile != null && disfile != ''}"> 
<div id="page-wrapper" style="margin-left:0; width:50%; float:left;">
</c:when>
<c:otherwise>
<div id="page-wrapper" style="margin-left:0; width:100%; float:left;">
</c:otherwise>
</c:choose>
<form name="pgfrm" method="post" enctype="multipart/form-data">
<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
<input type="hidden" name="MINTS_CN" value="${minutesVO.MINTS_CN}" />
<input type="hidden" name="RASMBLY_ID" value="${minutesVO.RASMBLY_ID}" />
<input type="hidden" name="EndPage" id="EndPage" value="${EndPage}" >
<input type="hidden" name="cnList" id="cnList" value="${cnList}" >
<input type="hidden" name="sort" value="${mdmSearchVO.sort }" >

	<div class="row">
		<div class="col-lg-12">
        	<h1 class="page-header">메타데이터 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>

	<h2>메타데이터 목록 (지방의회 회의록정보) </h2>
    <!-- /.row -->
    <div class="row">
    	<p class="tr">
    		<a href="#none" class="btn btn btn-default" onclick="fn_prev();" style="color:#d84e2c">이전</a>
			<a href="#none" class="btn btn btn-default" onclick="fn_next();" style="color:#d84e2c">다음</a>
    	</p>
    	<p class="tr">
    		<a href="#;" onclick="return openServicePopup();"><button type="button" class="btn btn-default">서비스 화면보기</button></a>
    		<a href="#;" onclick="self.close();"><button type="button" class="btn btn-default">닫기</button></a>
			<a href="#;" onclick="return setUpdate();"><button type="button" class="btn btn-success">수정</button></a>
			<a href="#;" onclick="return setDelete();"><button type="button" class="btn btn-danger">삭제</button></a>
		</p>
		
		 <!-- /.panel-heading -->
		 <div class="panel-body ">
		 	<div class="movebox">
		 		<div class="table-responsive" id="leftArea">
			 		<table class="table table-striped table-bordered ">
	                <colgroup>
	                <col width="15%" />
					<col width="20%" />
					<col width="15%" />
					<col width="20%" />
					<col width="15%" />
					<col width="15%" />
	                </colgroup>
		                <tbody>
		                	<tr>
		                		<td colspan="6"><font color="red">주의!!<br>지방의회 API로 수집된 데이터입니다.<br>관리시스템에서 수정한 후에 재수집되는 경우 다시 업데이트가 될 수 있습니다.</font></td>
		                	</tr>
		                	<tr>
		                		<th>제어번호</th>
		                		<td colspan="2">${minutesVO.MINTS_CN}</td>
		                		<th>수집여부</th>
								<td colspan="2">
									<input type="radio" id="COLCT_AT_Y" name="COLCT_AT" value="Y" <c:if test="${billVO.COLCT_AT == 'Y'}"> checked="checked"</c:if>/> 허용
									<input type="radio" id="COLCT_AT_N" name="COLCT_AT" value="N" <c:if test="${billVO.COLCT_AT == 'N' || billVO.COLCT_AT == null}"> checked="checked"</c:if>/> 미허용
								</td>
		                	</tr>
		                	<tr>
		                		<th>의회명</th>
		                		<td colspan="5">${minutesVO.RASMBLY_NM}</td>
		                	</tr>
		                	<tr>
		                		<th>대수</th>
		                		<td>
		                		<input type="hidden" name="RASMBLY_NUMPR" id="RASMBLY_NUMPR" value="${minutesVO.RASMBLY_NUMPR}"/>
		                		${minutesVO.RASMBLY_NUMPR}대
		                		</td>
		                		<th>회수</th>
		                		<td>
		                		<input type="hidden" name="RASMBLY_SESN" id="RASMBLY_SESN" value="${minutesVO.RASMBLY_SESN}"/>
		                		${minutesVO.RASMBLY_SESN}
		                		</td>
		                		<th>차수</th>
		                		<td>
		                		<input type="hidden" name="MINTS_ODR" id="MINTS_ODR" value="${minutesVO.MINTS_ODR}"/>
		                		${minutesVO.MINTS_ODR}
		                		</td>
		                	</tr>
		                	<tr>
		                		<th>회의명</th>
		                		<td>
		                		<input type="hidden" name="MTGNM_ID" id="MTGNM_ID" value="${minutesVO.MTGNM_ID}"/>
		                		<c:forEach var="item" items="${mtgnmList}">
		                		<c:if test="${minutesVO.MTGNM_ID == item.MTGNM_ID}">
		                		<input type="hidden" name="MTGNM" id="MTGNM" value="${item.MTGNM}"/>
		                		${item.MTGNM}
		                		</c:if>
		                		</c:forEach>
		                		</td>
		                		<th>회의록순번</th>
		                		<td>
		                		<input type="hidden" name="MINTS_SN" id="MINTS_SN" value="${minutesVO.MINTS_SN}"/>
		                		${minutesVO.MINTS_SN}
		                		</td>
		                		<th>차수명칭</th>
		                		<td>
		                			<input type="text" name="ODR_NM" id="ODR_NM" value="${minutesVO.ODR_NM}" />
		                		</td>
		                	</tr>
		                	<tr>
		                		<th>공개</th>
		                		<td>
		                			<select id="OTHBC_STDCD" name="OTHBC_STDCD" class=" input-sm" style="">
		                				<c:forEach var="item" items="${othbcStdcdList}" varStatus="status">
		                				<option value="${item.code}"<c:if test="${minutesVO.OTHBC_STDCD == item.code}"> selected="selected"</c:if>>${item.codeNm }</option>
		                				</c:forEach>
		                			</select>
		                		</td>
		                		<th>회기구분</th>
		                		<td>
		                		<input type="hidden" name="SESN_SE_STDCD" id="SESN_SE_STDCD" value="${minutesVO.SESN_SE_STDCD}"/>
                				<c:forEach var="item" items="${rasmblySeanList}" varStatus="status">
                				<c:if test="${minutesVO.SESN_SE_STDCD == item.code}">${item.codeNm }</c:if>
                				</c:forEach>
		                		</td>
		                		<th>폐회중여부</th>
		                		<td>
		                			<select id="CLOSE_AT" name="CLOSE_AT" class=" input-sm" style="">
		                				<option value="Y" <c:if test="${minutesVO.CLOSE_AT == 'Y' }">selected="selected"</c:if>>Y</option>
		                				<option value="N" <c:if test="${minutesVO.CLOSE_AT == 'N' }">selected="selected"</c:if>>N</option>
		                			</select>
		                		</td>
		                	</tr>
		                	<tr>
		                		<th>회의일자</th>
		                		<td>${minutesVO.MTG_DE}</td>
		                		<th>시작시간</th>
		                		<td>${minutesVO.BEGIN_TM}</td>
		                		<th>종료시간</th>
		                		<td>${minutesVO.END_TM}</td>
		                	</tr>
		                	<tr>
		                		<th>안건</th>
		                		<td colspan="5">
		                			<button onclick="javascript:selectItem(); return false;">안건보기</button>
		                			<button onclick="javascript:selectAsembyInfo(); return false;">관련의원보기</button>
		                		</td>
		                	</tr>
		                	<tr>
		                		<th>회의록파일</th>
		                        <td colspan="5">
		                        	<c:if test="${minutesFileList.size() == 0}">
									</c:if>
									<c:if test="${fn:length(minutesFileList) > 0}">
										<c:forEach var="cList" items="${minutesFileList}" varStatus="status">
											<c:if test="${cList.orignlFileNm != null && cList.fileMg == 'MINTS'}">
												<div id="filedel${cList.atchFileId}" style="padding-bottom:8px;">
<%-- 													<c:choose> --%>
<%-- 														<c:when test="${cList.fileSn == '1' || cList.fileSn == '3'}">[성공]</c:when> --%>
<%-- 														<c:when test="${cList.fileSn == '2'}">[<font color="red">실패</font>]</c:when> --%>
<%-- 														<c:otherwise>[대기]</c:otherwise> --%>
<%-- 													</c:choose> --%>
													<c:choose>
														<c:when test="${cList.fileMg == 'APP'}">[부록]</c:when>
														<c:otherwise>[<span style="font-weight:bold">원문</span>]</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${cList.fileSn == '2'}"><span id="filemsg${cList.atchFileId}">${cList.orignlFileNm}</span><br />${cList.fileCn}</c:when>
														<c:when test="${cList.fileSn == '1' || cList.fileSn == '3'}">
															<c:choose>
																<c:when test="${disfile == cList.orignlFileNm}">
																	<img src="/images/clikmng/mdm/icon_${cList.fileExtsn}.png" style="width:14px;vertical-align:-3px;" />
																	<strong><span id="filemsg${cList.atchFileId}">${cList.orignlFileNm}</span></strong>
																</c:when>
																<c:otherwise>
																	<img src="/images/clikmng/mdm/icon_${cList.fileExtsn}.png" style="width:14px;vertical-align:-3px;" />
																	<a href="#;" onclick="fileview('${cList.streFileNm}');"><span id="filemsg${cList.atchFileId}">${cList.orignlFileNm}</span></a>
																</c:otherwise>
															</c:choose>
														</c:when>
														<c:otherwise>${cList.orignlFileNm}</c:otherwise>
													</c:choose>
												</div>
											</c:if>
										</c:forEach>
									</c:if>
		                        </td>
		                	</tr>
		                	<tr>
								<th>회의록 부록파일</th>
								<td colspan="5">
									<c:if test="${fn:length(minutesFileList) > 0}">
										<c:forEach var="cList" items="${minutesFileList}" varStatus="status">
											<c:if test="${cList.orignlFileNm != null && cList.fileMg == 'APP'}">
												<div id="filedel${cList.atchFileId}" style="padding-bottom:8px;">
													<!-- 
													<button type="button" class="btn btn-danger" onclick="setFileDelete('${cList.atchFileId}');">삭제</button>
													 -->
<%-- 													<c:choose> --%>
<%-- 														<c:when test="${cList.fileSn == '1' || cList.fileSn == '3'}">[성공]</c:when> --%>
<%-- 														<c:when test="${cList.fileSn == '4'}">[변환중]</c:when> --%>
<%-- 														<c:when test="${cList.fileSn == '2'}">[<font color="red">실패</font>]</c:when> --%>
<%-- 														<c:otherwise>[대기]</c:otherwise> --%>
<%-- 													</c:choose> --%>
													<c:choose>
														<c:when test="${cList.fileMg == 'APP'}">[부록]</c:when>
														<c:otherwise>[<span style="font-weight:bold">원문</span>]</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${cList.fileSn == '2'}"><span id="filemsg${cList.atchFileId}">${cList.orignlFileNm}</span><br />${cList.fileCn}</c:when>
														<c:when test="${cList.fileSn == '1' || cList.fileSn == '3'}">
															<c:choose>
																<c:when test="${disfile == cList.orignlFileNm}">
																	<img src="/images/clikmng/mdm/icon_${cList.fileExtsn}.png" style="width:14px;vertical-align:-3px;" />
																	<strong><span id="filemsg${cList.atchFileId}">${cList.orignlFileNm}</span></strong>
																</c:when>
																<c:otherwise>
																	<img src="/images/clikmng/mdm/icon_${cList.fileExtsn}.png" style="width:14px;vertical-align:-3px;" />
																	<a href="#;" onclick="fileview('${cList.streFileNm}');"><span id="filemsg${cList.atchFileId}">${cList.orignlFileNm}</span></a>
																</c:otherwise>
															</c:choose>
														</c:when>
														<c:otherwise>${cList.orignlFileNm}</c:otherwise>
													</c:choose>
												</div>
											</c:if>
										</c:forEach>
									</c:if>
								</td>
							</tr>
		                	<tr>	
								<th>수집일자</th>
								<td colspan="2">${minutesVO.FRST_REGIST_DT }</td>
								<th>수정/삭제 ID</th>
								<td colspan="2">${minutesVO.LAST_UPDUSR_ID }</td>
							</tr>
							<tr>	
								<th>수정일자</th>
								<td colspan="2">${minutesVO.LAST_UPDT_DT }</td>
								<th>삭제일자</th>
								<td colspan="2">${minutesVO.DELETE_DT }</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div><!--//movebox-->
			
			<p class="tr">
	    		<a href="#none" class="btn btn btn-default" onclick="fn_prev();" style="color:#d84e2c">이전</a>
				<a href="#none" class="btn btn btn-default" onclick="fn_next();" style="color:#d84e2c">다음</a>
	    	</p>
	    	<p class="tr">
	    		<a href="#;" onclick="return openServicePopup();"><button type="button" class="btn btn-default">서비스 화면보기</button></a>
	    		<a href="#;" onclick="self.close();"><button type="button" class="btn btn-default">닫기</button></a>
				<a href="#;" onclick="return setUpdate();"><button type="button" class="btn btn-success">수정</button></a>
				<a href="#;" onclick="return setDelete();"><button type="button" class="btn btn-danger">삭제</button></a>
			</p>
		</div><!-- /.panel-body -->
	</div><!--//cntArea-->
	</form>
	<form name="fvfrm" method="post">
		<%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchVar.jsp" %>
		<input type="hidden" name="isPrevNextPaging" id="isPrevNextPaging" value="N" ><!-- 페이징할 경우사용 -->
		<input type="hidden" name="prevNextGubun" id="prevNextGubun" value="N" >
		<input type="hidden" name="EndPage" id="EndPage" value="${EndPage}" >
		<input type="hidden" name="cnList" id="cnList" value="${cnList}" >
		<input type="hidden" name="sort" value="${mdmSearchVO.sort }" >
	</form>
</div>

<c:if test="${fn:length(minutesFileList) >= 0 && disfile != null && disfile != ''}">
	<div id="cntArea" class="pdf" style="float:left;width:40%">
		<div id="leftAreaBtn" style="display:none; valign:top;">
			<object width="100%" height="1000px" type="application/pdf" data="/unidocs/fileTemp/${disfile}">
				<param name="src" value="/unidocs/fileTemp/${disfile}" />
			</object>
		</div>
	</div><!-- //cntArea -->
</c:if>

<c:if test="${fn:length(minutesFileList) >= 0 && disfile != null && disfile != ''}">
	<script type="text/javascript">
		fn_leftArea();
	</script>
</c:if>

<div class="spinner"></div>

</html>