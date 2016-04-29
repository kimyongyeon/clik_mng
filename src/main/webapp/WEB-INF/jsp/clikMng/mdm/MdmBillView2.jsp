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
<title>지방의회 의안정보</title>

<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">


var currCnId = '${billVO.BI_CN}';
var cnList;
$(document).ready(function(){
	
	var tempCn = '${cnList}';

	cnList = tempCn.split(",");
	
});

//이전버튼
function fn_prev(){
	$(".spinner").show();
	var frm = document.fvfrm;
	var url = "";
	var i = 0;
	for(; i < cnList.length; i++){
		if(currCnId == cnList[i]){
// 			url = "/mdm/MdmBillView1.do?cnId="+cnList[i-1];
			frm.action = "/mdm/MdmBillView1.do";
			frm.cnId.value = cnList[i-1]; 
			$('#isPrevNextPaging').val('N');
			break;
		}
	}
	
	if(${mdmSearchVO.pageNum} > 1 && i == 0)
	{
		frm.pageNum.value = ${mdmSearchVO.pageNum} - 1;
		frm.action = "/mdm/MdmBillView1.do";
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
	
// 	frm.title.value = '메타데이터보기';
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
// 			url = "/mdm/MdmBillView1.do?cnId="+cnList[i+1];
			frm.action = "/mdm/MdmBillView1.do";
			frm.cnId.value = cnList[i+1];
			$('#isPrevNextPaging').val('N');
			break;
		}
	}
	
	if(cnList.length == (i+1) && ${EndPage} > (${mdmSearchVO.pageNum} + 1))
	{
		frm.pageNum.value = ${mdmSearchVO.pageNum} + 1;
		frm.action = "/mdm/MdmBillView1.do";
		frm.cnId.value = "";
		$('#prevNextGubun').val('next');
		$('#isPrevNextPaging').val('Y');
	}
	else if(cnList.length == (i+1) && ${EndPage} == ${mdmSearchVO.pageNum})
	{
		$(".spinner").hide();
		alert("마지막 입니다.");
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
	
// 	frm.title.value = '메타데이터보기';
// 	frm.target='VIEWER_POP';
	frm.submit();
// 	frm.target = "_self";
}


function getJrsdCmitId(RASMBLY_NUMPR) {
 	if( RASMBLY_NUMPR == "" ) {
		return false;
	}
	
	var RASMBLY_ID = $("#RASMBLY_ID option:selected").val();
	if( RASMBLY_ID == "" ) {
		alert("의회를 선택하십시오.");
		return false;
	}
	$.ajax({
		url:"/mdm/MdmJrsdCmitIdList.do",
		cache:false,
		type:"post",
		data:{"RASMBLY_ID":RASMBLY_ID,"RASMBLY_NUMPR":RASMBLY_NUMPR},
		success: function(data, status) {
			$("#JRSD_CMIT_ID").html(data);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}
function setUpdate() {
// 	if( $("#RASMBLY_ID option:selected").val() == "" ) {
// 		alert("의회를 선택하십시오.");
// 		return false;
// 	}
// 	if( $("#RASMBLY_NUMPR option:selected").val() == "" ) {
// 		alert("대수를 선택하십시오.");
// 		return false;
// 	}
// 	if( $("#BI_SJ").val() == "" ) {
// 		alert("의안명을 입력하십시오.");
// 		return false;
// 	}
// 	if( $("#BI_NO").val() == "" ) {
// 		alert("의안번호를 입력하십시오.");
// 		return false;
// 	}
// 	if( $("#BI_KND_STDCD").val() == "" ) {
// 		alert("의안종류를 선택하십시오.");
// 		return false;
// 	}
	if( $("#RASMBLY_SESN").val() == "" ) {
		alert("회기를 선택해 주세요.");
		return false;
	}
	if( !confirm("수정하시겠습니까?") ) {
		return false;
	}
	
	$(".spinner").show();
	
	$("#CMIT_RESULT").val($("#CMIT_RESULT_STDCD option:selected").text());
	$("#PLNMT_RESULT").val($("#PLNMT_RESULT_STDCD option:selected").text());
	
	pgfrm.action="/mdm/MdmBillUpdate.do";
	document.pgfrm.submit();
}
function getDownload(DOWNID) {
	document.location.href="/mdm/MdmBillDownLoad.do?DOWNID="+DOWNID;
}
function setDelete() {
	if( !confirm("게시물을 삭제하시겠습니까?") ) {
		return false;
	}
	pgfrm.action = "/mdm/MdmBillDelete.do";
	document.pgfrm.submit();
}
function setFileDelete(DOWNID) {
	if( !confirm($("#filemsg"+DOWNID).text() + "\n\n 삭제하시겠습니까?") ) {
		return false;
	}
	$.ajax({
		url:"/mdm/MdmBillFileDelete.do",
		cache:false,
		type:"post",
		data:{"DOWNID":DOWNID},
		success: function(data, status) {
			$("#filedel"+DOWNID).remove();
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}
//대수정보 변경
function changeRasmblyNumpr(){
	//회수정보
	$.ajax({
		url:"/mdm/MdmMinutesRasmblySesnView.do",
		cache:false,
		type:"post",
		data:{
			"rasmbly_id":'${billVO.RASMBLY_ID}'
			,rasmbly_numpr : $('#RASMBLY_NUMPR').val()
			,sesn_se_stdcd : $('#SESN_SE_STDCD').val()},
		success: function(data, status) {
			var json = $.parseJSON(data);
			$('#RASMBLY_SESN > option').remove();
			var option = "";
			$('#RASMBLY_SESN').append('<option value="">== 선택 ==</option>');
			$(json).each(function(index,value){
				option += "<option value='"+value.RASMBLY_SESN+"'>"+value.RASMBLY_SESN+"</option>";
			});
			$('#RASMBLY_SESN').append(option);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	
	//소관위원회
	$.ajax({
		url:"/mdm/MdmJrsdCmitIdList.do",
		cache:false,
		type:"post",
		data:{
			"rasmbly_id":'${billVO.RASMBLY_ID}'
			,rasmbly_numpr:$('#RASMBLY_NUMPR').val()},
		success: function(data, status) {
			var json = $.parseJSON(data);
			$('#JRSD_CMIT_ID > option').remove();
			var option = "";
			$('#JRSD_CMIT_ID').append('<option value="">== 선택 ==</option>');
			$(json).each(function(index,value){
				option += "<option value='"+value.MTGNM_ID+"'>"+value.MTGNM+"</option>";
			});
			$('#JRSD_CMIT_ID').append(option);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	
	return false;
}

function fileview(disfile) {
	$('#disfile').val(disfile);
	fvfrm.action = "/mdm/MdmBillView2.do";
	document.fvfrm.submit();
}

$(document).ready(function(){
	//해당 데이터에 제안회기 정보 추가
	var rasmbly_sesn = '${billVO.RASMBLY_SESN}';
	var yn = false;
	$('#RASMBLY_SESN').each(function(index,item){
		if(item.value == rasmbly_sesn){
			yn = true;
		}
	});
	
	if(!yn){
		$('#RASMBLY_SESN').append("<option value='${billVO.RASMBLY_SESN}' selected='selected'>${billVO.RASMBLY_SESN}</option>");
	}
});

//서비스시스템 상새화면을 호출한다
function openServicePopup(){
	window.open('http://clik.nanet.go.kr/potal/search/searchView.do?collection=bill&DOCID=${billVO.BI_CN}','_blank');
}
</script>
</head>
<body class="body" style="background:none;">
<c:choose>
<c:when test="${fn:length(billFileList) >= 0 && disfile != null && disfile != ''}"> 
<div id="page-wrapper" style="margin-left:0; width:60%; float:left;">
</c:when>
<c:otherwise>
<div id="page-wrapper" style="margin-left:0; width:100%; float:left;">
</c:otherwise>
</c:choose>
<form name="pgfrm" method="post" enctype="multipart/form-data">
<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
<input type="hidden" name="BI_ID" value="${billVO.BI_ID}" />
<input type="hidden" name="BI_CN" value="${billVO.BI_CN}" />
<input type="hidden" name="RASMBLY_ID" value="${billVO.RASMBLY_ID}" />
<input type="hidden" name="CUD_CODE" value="${billVO.CUD_CODE}" />
<input type="hidden" name="EndPage" id="EndPage" value="${EndPage}" >
<input type="hidden" name="cnList" id="cnList" value="${cnList}" >
<input type="hidden" name="sort" value="${mdmSearchVO.sort }" >
		
	<div class="row">
    	<div class="col-lg-12">
        	<h1 class="page-header">메타데이터 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
	<h2>메타데이터 목록 (지방의회 의안정보) </h2>	<!-- /.row -->
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
			                		<td colspan="2">${billVO.BI_CN}</td>
			                		<th>수집여부</th>
									<td colspan="2">
										<input type="radio" id="COLCT_AT_Y" name="COLCT_AT" value="Y" <c:if test="${billVO.COLCT_AT == 'Y'}"> checked="checked"</c:if>/> 허용
										<input type="radio" id="COLCT_AT_N" name="COLCT_AT" value="N" <c:if test="${billVO.COLCT_AT == 'N' || billVO.COLCT_AT == null}"> checked="checked"</c:if>/> 미허용
									</td>
			                	</tr>
			                	<tr>
			                		<th>의회명</th>
			                		<td colspan="2">${billVO.RASMBLY_NM}</td>
			                		<th>의안종류</th>
			                		<td colspan="2">
			                		<select id="BI_KND_STDCD" name="BI_KND_STDCD" class=" input-sm" >
		                				<option value="">== 선택 ==</option>
		                				<c:forEach var="item" items="${mdmBiKndStdCd}" varStatus="status">
		                				<option value="${item.CODE}"<c:if test="${billVO.BI_KND_STDCD == item.CODE}"> selected="selected"</c:if>>${item.CODE_NM}</option>
		                				</c:forEach>
		                			</select>
			                		</td>
			                	</tr>
								<tr>
			                		<th>대수</th>
			                		<td colspan="2">
			                			<select id="RASMBLY_NUMPR" name="RASMBLY_NUMPR" class=" input-sm" onchange="javascript:changeRasmblyNumpr();">
			                				<option value="">== 선택 ==</option>
			                				<c:forEach var="item" items="${rasmblyNumperList}" varStatus="status">
			                				<option value="${item.RASMBLY_NUMPR}"<c:if test="${billVO.RASMBLY_NUMPR == item.RASMBLY_NUMPR}"> selected="selected"</c:if>>${item.RASMBLY_NUMPR }대</option>
			                				</c:forEach>
			                			</select>
			                		</td>
			                		<th>제안자</th>
			                		<td colspan="2">
			                			<input type="text" name="PROPSR" id="PROPSR" value="${billVO.PROPSR}" />
			                		</td>
			                	</tr>
			                	<tr>
			                		<th>회기구분명</th>
			                		<td colspan="2">
			                			<select id="SESN_SE_STDCD" name="SESN_SE_STDCD" class=" input-sm" onchange="javascript:changeRasmblyNumpr();">
			                				<option value="">== 선택 ==</option>
			                				<c:forEach var="item" items="${sesnSeStdcdList}" varStatus="status">
			                				<option value="${item.CODE}"<c:if test="${billVO.SESN_SE_STDCD == item.CODE}"> selected="selected"</c:if>>${item.CODE_NM}</option>
			                				</c:forEach>
			                			</select>
			                		</td>
			                		<th>제안회기</th>
			                		<td colspan="2">
			                			<select id="RASMBLY_SESN" name="RASMBLY_SESN" class=" input-sm">
			                				<option value="">== 선택 ==</option>
			                				<c:forEach var="item" items="${sesnList}" varStatus="status">
			                				<option value="${item.RASMBLY_SESN}"<c:if test="${billVO.RASMBLY_SESN == item.RASMBLY_SESN}"> selected="selected"</c:if>>${item.RASMBLY_SESN}</option>
			                				</c:forEach>
			                			</select>
			                		</td>
			                	</tr>
			                	<tr>
			                		<th>발의의원</th>
			                		<td colspan="2">
			                			${billVO.PROPSR_CM}
			                		</td>
			                		<th>제안일</th>
			                		<td colspan="2">
			                			<input type="text" name="ITNC_DE" id="ITNC_DE" value="${billVO.ITNC_DE}" />
			                		</td>
			                	</tr>
			                	<tr>
			                		<th>의안요지</th>
			                		<td colspan="5">
			                			${billVO.BI_OUTLINE}
			                		</td>
			                	</tr>
								<tr>
			                		<th>의안번호</th>
			                		<td colspan="2">
			                			<input type="text" name="BI_NO" id="BI_NO" value="${billVO.BI_NO}" />
			                		</td>
			                		<th>소관위원회</th>
			                		<td colspan="2">
			                			<select id="JRSD_CMIT_ID" name="JRSD_CMIT_ID" class=" input-sm" style="max-width:200px;">
			                				<option value="">== 선택 ==</option>
			                				<c:forEach var="item" items="${mdmJrsdCmitId}" varStatus="status">
			                				<option value="${item.MTGNM_ID}"<c:if test="${billVO.JRSD_CMIT_ID == item.MTGNM_ID}"> selected="selected"</c:if>>${item.MTGNM}</option>
			                				</c:forEach>
			                			</select>
			                		</td>
			                	</tr>
			                	<tr>
			                		<th>위원회처리</th>
			                		<td colspan="2">
			                			회부일&nbsp;<input type="text" name="CMIT_REPORT_DE" id="CMIT_REPORT_DE" value="${billVO.CMIT_REPORT_DE}" /><br/>
			                			상정일&nbsp;<input type="text" name="CMIT_SBMISN_DE" id="CMIT_SBMISN_DE" value="${billVO.CMIT_SBMISN_DE}" /><br/>
			                			의결일&nbsp;<input type="text" name="CMIT_PROCESS_DE" id="CMIT_PROCESS_DE" value="${billVO.CMIT_PROCESS_DE}" /><br/>
			                			처리결과&nbsp;
			                			<input type="hidden" name="CMIT_RESULT" id="CMIT_RESULT" value="${billVO.CMIT_RESULT}" />
			                			<select id="CMIT_RESULT_STDCD" name="CMIT_RESULT_STDCD" class=" input-sm">
			                				<option value="">-처리결과-</option>
			                				<c:forEach var="item" items="${mdmCmitResult}" varStatus="status">
			                				<option value="${item.CODE}"<c:if test="${billVO.CMIT_RESULT_STDCD == item.CODE}"> selected="selected"</c:if>>${item.CODE_NM}</option>
			                				</c:forEach>
			                			</select>
			                		</td>
			                		<th>본회의처리</th>
			                		<td colspan="2">
			                			회부일&nbsp;<input type="text" name="PLNMT_REPORT_DE" id="PLNMT_REPORT_DE" value="${billVO.PLNMT_REPORT_DE}" /><br/>
			                			상정일&nbsp;<input type="text" name="PLNMT_SBMISN_DE" id="PLNMT_SBMISN_DE" value="${billVO.PLNMT_SBMISN_DE}" /><br/>
			                			의결일&nbsp;<input type="text" name="PLNMT_PROCESS_DE" id="PLNMT_PROCESS_DE" value="${billVO.PLNMT_PROCESS_DE}" /><br/>
			                			처리결과&nbsp;
			                			<input type="hidden" name="PLNMT_RESULT" id="PLNMT_RESULT" value="${billVO.PLNMT_RESULT}" />
			                			<select id="PLNMT_RESULT_STDCD" name="PLNMT_RESULT_STDCD" class=" input-sm">
			                				<option value="">-처리결과-</option>
			                				<c:forEach var="item" items="${mdmCmitResult}" varStatus="status">
			                				<option value="${item.CODE}"<c:if test="${billVO.PLNMT_RESULT_STDCD == item.CODE}"> selected="selected"</c:if>>${item.CODE_NM}</option>
			                				</c:forEach>
			                			</select>
			                		</td>
			                	</tr>
			                	<tr>
			                		<th>집행기관 이송일</th>
			                		<td>
			                			${billVO.TRNSF_DE}
			                		</td>
			                		<th>공포일</th>
			                		<td>
			                			${billVO.PRMLGT_DE}
			                		</td>
			                		<th>공포번호</th>
			                		<td>
			                			<input type="text" name="PRMLGT_NO" id="PRMLGT_NO" value="${billVO.PRMLGT_NO}" />
			                		</td>
			                	</tr>
			                	<tr>	
									<th>의안파일</th>
									<td colspan="5">
										<c:if test="${fn:length(billFileList) > 0}">
											<c:forEach var="cList" items="${billFileList}" varStatus="status">
												<div id="filedel${cList.BI_FILE_ID}" style="padding-bottom:8px;">
													<button type="button" class="btn btn-danger" onclick="setFileDelete('${cList.BI_FILE_ID}');">삭제</button>
													<c:set var="afile" value="${fn:split(cList.BI_FILE_PATH, '/')}" />
													<c:choose>
														<c:when test="${cList.DOC_CNVR_STTU_CODE == '1' || cList.DOC_CNVR_STTU_CODE == '3'}">
															<!-- [성공] -->
															<c:choose>
																<c:when test="${disfile == cList.BI_PDF_FILE_NM}">
																	<strong><span id="filemsg${cList.BI_FILE_ID}">${fn:replace(afile[fn:length(afile)-1], cList.FILEEXT, 'pdf')}</span></strong>
																</c:when>
																<c:otherwise>
																	<a href="#;" onclick="fileview('${cList.BI_PDF_FILE_PATH}');"><span id="filemsg${cList.BI_FILE_ID}">${fn:replace(afile[fn:length(afile)-1], cList.FILEEXT, 'pdf')}</span></a>
																</c:otherwise>
															</c:choose>
														</c:when>
														<c:when test="${cList.DOC_CNVR_STTU_CODE == '0'}">[대기]<span id="filemsg${cList.BI_FILE_ID}">${afile[fn:length(afile)-1]}</span></c:when>
														<c:otherwise>
															<!-- [<font color="red">실패</font>] --> <span id="filemsg${cList.BI_FILE_ID}">${afile[fn:length(afile)-1]}</span><br /><!-- ${cList.DOC_CNVR_RESULT_MSSAGE}  -->
														</c:otherwise>
													</c:choose>
												</div>
											</c:forEach>
										</c:if>
	 								</td>
								</tr>
								<tr>	
									<th>수집일자</th>
									<td colspan="2">${billVO.FRST_REGIST_DT}</td>
									<th>수정/삭제 ID</th>
									<td colspan="2">${billVO.LAST_UPDUSR_ID}</td>
								</tr>
								<tr>	
									<th>수정일자</th>
									<td colspan="2">${billVO.LAST_UPDT_DT}</td>
									<th>삭제일자</th>
									<td colspan="2">${billVO.DELETE_DT}</td>
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
		</div>
		<!-- /.panel-body -->
	</div>
	
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

<c:if test="${fn:length(billFileList) >= 0 && disfile != null && disfile != ''}">
	<div id="cntArea" class="pdf" style="float:left; width:39%; margin-top:116px; min-width:490px;">
		<div id="leftAreaBtn" style="display:none">
			<object width="100%" height="1000px" type="application/pdf" data="/unidocs/fileTemp/${disfile}">
				<param name="src" value="/unidocs/fileTemp/${disfile}" />
			</object>
		</div>
	</div><!-- //cntArea -->
</c:if>

<c:if test="${fn:length(billFileList) >= 0 && disfile != null && disfile != ''}">
	<script type="text/javascript">
		//fn_leftArea();
		var fullWidth1 = 100;
		var fullWidth = 39;
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
	</script>
</c:if>

<div class="spinner"></div>

</body>
</html>
