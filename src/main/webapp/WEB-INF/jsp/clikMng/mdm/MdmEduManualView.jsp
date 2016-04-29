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
<title>매뉴얼&서식</title>
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
function fn_leftArea() {
	var fullWidth1 = 50;
	var fullWidth = 45;
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
function getOutSiteList(REGION) {
	if( REGION == null || REGION == "" ) {
		return false;
	}
	$.ajax({
		url:"/mdm/MdmEduManualOutSiteList.do",
		cache:false,
		type:"post",
		data:{"REGION":REGION},
		success: function(data, status) {
			$("#outSiteList").html(data);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}
function getOutSeedList(DOCTYPE) {
	if( DOCTYPE == null || DOCTYPE == "" ) {
		return false;
	}
	/*???*/
	DOCTYPE = $("#REGION option:selected").val();
	$.ajax({
		url:"/mdm/MdmEduManualOutSeedList.do",
		cache:false,
		type:"post",
		data:{"DOCTYPE":DOCTYPE},
		success: function(data, status) {
			$("#outSeedList").html(data);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}
function setUpdate() {
	if( $("#REGION").val() == "" ) {
		alert("지역을 선택하십시오.");
		return false;
	}
	if( $("#outSiteList").val() == "" ) {
		alert("사이트를 선택하십시오.");
		return false;
	}
	if( $("#outSeedList").val() == "" ) {
		alert("SEED를 선택하십시오.");
		return false;
	}
	if( $("#outDocTypeList").val() == "" ) {
		alert("자료유형을 선택하십시오.");
		return false;
	}
	if( $("#REGDATE").val() == "" ) {
		alert("등록일을 입력하십시오.");
		return false;
	}
	
	// 파일 확장자 체크
	if(document.getElementById('_FILE_NM') != null){
	    var confirmExt;
	    var thumbext = document.getElementById('_FILE_NM').value; 
	    if(document.getElementById('_FILE_NM').value != "") {
	        confirmExt = fn_confirmExt(thumbext);
	        if(!confirmExt) {
	        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
	        	return;
	        }
	    }
	}
	
	if( !confirm("수정하시겠습니까?") ) {
		return false;
	}
	pgfrm.action="/mdm/MdmEduManualUpdate.do";
	document.pgfrm.submit();
}

/**
 * 파일 확장자 체크
 * 해당 확장자가 아닌 다른 확장자로 업로드 실행 시 필터
 */

function fn_confirmExt(str) {
	str = str.slice(str.lastIndexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.

	var result = true;

	if(str != "jpg" && str != "png" &&  str != "gif" &&  str != "bmp"
		&& str != "alz" && str != "doc" && str != "docx" && str != "hwp" && str != "jpeg" 
		&& str != "pdf" && str != "ppt" && str != "pptx" && str != "pptx" && str != "txt"
		&& str != "xls" && str != "xlsx" && str != "zip" ){ //확장자를 확인합니다.

		//alert('썸네일은 이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
		result = false;
		return result;

	} else {
		return result;
	}
}

function list() {
	fvfrm.action = "/mdm/MdmEduManualList.do";
	document.fvfrm.submit();
}
function fileview(disfile) {
	$('#disfile').val(disfile);
	fvfrm.action = "/mdm/MdmEduManualMetaDataView.do";
	document.fvfrm.submit();
}
function setDownload(DOWNID) {
	document.location.href="/mdm/MdmEduManualDownLoad.do?DOWNID="+DOWNID;
}
function setDelete() {
	if( !confirm("게시물을 삭제하시겠습니까?") ) {
		return false;
	}
	pgfrm.action = "/mdm/MdmEduManualDelete.do";
	document.pgfrm.submit();
}
function setFileDelete(DOWNID) {
	if( !confirm($("#filemsg"+DOWNID).text() + "\n\n 삭제하시겠습니까?") ) {
		return false;
	}
	$.ajax({
		url:"/mdm/MdmEduManualFileDelete.do",
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
</script>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
<div id="page-wrapper">
<form name="pgfrm" method="post" enctype="multipart/form-data">
<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
<input type="hidden" name="EXTID" value="${eduManualVO.EXTID}" />
<input type="hidden" name="OUTBBS_CN" value="${eduManualVO.OUTBBS_CN}" />
	<div class="row">
    	<div class="col-lg-12">
        	<h1 class="page-header">메타데이터 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
	<h2>메타데이터 목록 (매뉴얼&서식) </h2>
	<div class="row">
		<p class="tr">
			<a href="#;" onclick="list();"><button type="button" class="btn btn-default">목록</button></a>
			<a href="#;" onclick="return setUpdate();"><button type="button" class="btn btn-success">수정</button></a>
			<a href="#;" onclick="return setDelete();"><button type="button" class="btn btn-danger">삭제</button></a>
		</p>

		<!-- /.panel-heading -->
		<div class="panel-body ">
			<div class="movebox">
				<div class="table-responsive" id="leftArea">
					<table class="table table-striped table-bordered " >
						<colgroup>
							<col width="10%" />
							<col width="40%"/>
							<col width="10%" />
							<col />
						 </colgroup>
							
						<tbody>
							<tr>	
								<th>기관유형</th>
								<td colspan="3">
								
									<select name="REGION" id="REGION" onchange="return getOutSiteList(this.value)" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
										<option value="">-- 지역 선택 --</option>
										<option value="002" <c:if test="${eduManualVO.REGION == '002'}"> selected="selected"</c:if>>서울특별시</option>
										<option value="031" <c:if test="${eduManualVO.REGION == '031'}"> selected="selected"</c:if>>경기도</option>
										<option value="032" <c:if test="${eduManualVO.REGION == '032'}"> selected="selected"</c:if>>인천광역시</option>
										<option value="033" <c:if test="${eduManualVO.REGION == '033'}"> selected="selected"</c:if>>강원도</option>
										<option value="041" <c:if test="${eduManualVO.REGION == '041'}"> selected="selected"</c:if>>충청남도</option>
										<option value="042" <c:if test="${eduManualVO.REGION == '042'}"> selected="selected"</c:if>>대전광역시</option>
										<option value="043" <c:if test="${eduManualVO.REGION == '043'}"> selected="selected"</c:if>>충청북도</option>
										<option value="044" <c:if test="${eduManualVO.REGION == '044'}"> selected="selected"</c:if>>세종특별자치시</option>
										<option value="051" <c:if test="${eduManualVO.REGION == '051'}"> selected="selected"</c:if>>부산광역시</option>
										<option value="052" <c:if test="${eduManualVO.REGION == '052'}"> selected="selected"</c:if>>울산광역시</option>
										<option value="053" <c:if test="${eduManualVO.REGION == '053'}"> selected="selected"</c:if>>대구광역시</option>
										<option value="054" <c:if test="${eduManualVO.REGION == '054'}"> selected="selected"</c:if>>경상북도</option>
										<option value="055" <c:if test="${eduManualVO.REGION == '055'}"> selected="selected"</c:if>>경상남도</option>
										<option value="061" <c:if test="${eduManualVO.REGION == '061'}"> selected="selected"</c:if>>전라남도</option>
										<option value="062" <c:if test="${eduManualVO.REGION == '062'}"> selected="selected"</c:if>>광주광역시</option>
										<option value="063" <c:if test="${eduManualVO.REGION == '063'}"> selected="selected"</c:if>>전라북도</option>
										<option value="064" <c:if test="${eduManualVO.REGION == '064'}"> selected="selected"</c:if>>제주특별자치도</option>
									</select>

									<select name="SITEID" id="outSiteList" onchange="return getOutSeedList(this.value)" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
										<option value="">-- 사이트 선택 --</option>
										<c:forEach var="cList" items="${codeOutSiteList}" varStatus="status">
											<option value="${cList.SITEID}" <c:if test="${eduManualVO.SITEID == cList.SITEID}"> selected="selected"</c:if>>${cList.SITENM}</option>
										</c:forEach>
									</select>

									<select name="SEEDID" id="outSeedList" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
										<option value="">--  게시판 선택 --</option>
										<c:forEach var="cList" items="${codeOutSeedList}" varStatus="status">
											<option value="${cList.SEEDID}" <c:if test="${eduManualVO.SEEDID == cList.SEEDID}"> selected="selected"</c:if>>${cList.SEEDNM}</option>
										</c:forEach>
									</select>

									<select name="DOCTYPE" id="outDocTypeList" aria-controls="dataTables-example" class=" input-sm" style="width:180px;">
										<option value="140">매뉴얼&서식</option>
									</select>
					
								</td>
							</tr>
							<tr>	
								<th>제  목</th>
								<td colspan="3"><input name="TITLE" class="form-control" placeholder=""  type="text" style="width:80%;" value="${eduManualVO.TITLE}" /></td>
							</tr>
							<tr>	
								<th>작성일</th>
								<td>
									<input name="CDATE" id="CDATE" class="input-sm ip" placeholder=""  type="text" style="width:30%;" value="${eduManualVO.CDATE}" class="readOnlyClass" readonly title="작성일" /> 
									<a href="#" onClick="javascript:fn_egov_NormalCalendar(document.pgfrm, document.pgfrm.CDATE);"><button type="button" class="btn btn-default">달력</button></a> <br/>
								</td>
								<th>작성자</th>
								<td><input name="WRITER" id="WRITER" class="input-sm ip" placeholder="" type="text" style="width:30%;" value="${eduManualVO.WRITER}" title="작성자" /></td>
							</tr>
								
							<tr>	
								<th>내  용</th>
								<td colspan="3">
									<textarea name="CONTENT" cols="100" rows="10" class="ip" style="width:100%; height:400px;">${eduManualVO.CONTENT}</textarea>
								</td>
							</tr>
							<tr>	
								<th>첨부파일</th>
								<td colspan="3">
									<input name="_FILE_NM" id="_FILE_NM" class="input-sm ip " placeholder="" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;">
 								</td>
							</tr>
							
							<tr>	
								<th></th>
								<td colspan="3">
									<c:if test="${fn:length(eduManualFileList) > 0}">
										<c:forEach var="cList" items="${eduManualFileList}" varStatus="status">
											<div id="filedel${cList.DOWNID}" style="padding-bottom:8px;">
												<button type="button" class="btn btn-danger" onclick="setFileDelete('${cList.DOWNID}');">삭제</button>
												<c:set var="afile" value="${fn:split(cList.DOWNPATH, '/')}" />
												<c:choose>
													<c:when test="${cList.DOC_CNVR_STTU_CODE == '1' || cList.DOC_CNVR_STTU_CODE == '3'}">
														[성공]
														<c:choose>
															<c:when test="${disfile == cList.OUTBBS_PDF_FILE_NM}">
																<strong><span id="filemsg${cList.DOWNID}">${fn:replace(afile[fn:length(afile)-1], cList.FILEEXT, 'pdf')}</span></strong>
															</c:when>
															<c:otherwise>
																<a href="#;" onclick="fileview('${cList.DOC_CNVR_PDF_PATH}');"><span id="filemsg${cList.DOWNID}">${fn:replace(afile[fn:length(afile)-1], cList.FILEEXT, 'pdf')}</span></a>
															</c:otherwise>
														</c:choose>
													</c:when>
													<c:when test="${cList.DOC_CNVR_STTU_CODE == '0'}">[대기] <span id="filemsg${cList.DOWNID}">${afile[fn:length(afile)-1]}</span></c:when>
													<c:otherwise>
														[<font color="red">실패</font>] <span id="filemsg${cList.DOWNID}">${afile[fn:length(afile)-1]}</span><br />${cList.DOC_CNVR_RESULT_MSSAGE}
													</c:otherwise>
												</c:choose>
											</div>
										</c:forEach>
									</c:if>
 								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
				
				<c:if test="${fn:length(eduManualFileList) >= 0 && disfile != null && disfile != ''}">
					<div id="cntArea" class="pdf">
						<div id="leftAreaBtn" valign="top" style="display:none">
							<object width="100%" height="600" type="application/pdf" data="/unidocs/fileTemp/${disfile}">
								<param name="src" value="/unidocs/fileTemp/${disfile}" />
							</object>
						</div>
					</div><!-- //cntArea -->
				</c:if>

			</div><!-- //movebox -->
				<p class="tr">
					<a href="#;" onclick="list();"><button type="button" class="btn btn-default">목록</button></a>
					<a href="#;" onclick="return setUpdate();"><button type="button" class="btn btn-success">수정</button></a>
					<a href="#;" onclick="return setDelete();"><button type="button" class="btn btn-danger">삭제</button></a>
				</p>
			</div>
			<!-- /.panel-body -->
		</div>
	</div>
	</form>

	<form name="fvfrm" method="post">
		<%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchVar.jsp" %>
	</form>
	
</div>

<c:if test="${fn:length(eduManualFileList) >= 0 && disfile != null && disfile != ''}">
	<script type="text/javascript">
		fn_leftArea();
	</script>
</c:if>

</body>
</html>
