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
<title>지역현안소식</title>
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
function setUpdate() {
	if( $("#SEED_ID").val() == "" ) {
		alert("수집기관을 선택하십시오.");
		return false;
	}
	if( $("#REGION").val() == "" ) {
		alert("지역을 선택하십시오.");
		return false;
	}
	
	// 파일 확장자 체크
    var confirmExt;
    var thumbext = document.getElementById('_FILE_NM').value; 
    if(document.getElementById('_FILE_NM').value != "") {
        confirmExt = fn_confirmExt(thumbext);
        if(!confirmExt) {
        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
        	return;
        }
    }    
    
	if( !confirm("수정하시겠습니까?") ) {
		return false;
	}
	pgfrm.action="/mdm/MdmRegionNewsUpdate.do";
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
	pgfrm.action = "/mdm/MdmRegionNewsList.do";
	document.pgfrm.submit();
}
function setDownload(ATCH_FILE_ID) {
	document.location.href="/mdm/MdmRegionNewsDownLoad.do?ATCH_FILE_ID="+ATCH_FILE_ID;
}
function setDelete() {
	if( !confirm("게시물을 삭제하시겠습니까?") ) {
		return false;
	}
	pgfrm.action = "/mdm/MdmRegionNewsDelete.do";
	document.pgfrm.submit();
}
function setFileDelete(ATCH_FILE_ID, FILE_STRE_COURS) {
	if( !confirm($("#filemsg"+FILE_STRE_COURS).text() + "\n 삭제하시겠습니까?") ) {
		return false;
	}
	$.ajax({
		url:"/mdm/MdmRegionNewsFileDelete.do",
		cache:false,
		type:"post",
		data:{"ATCH_FILE_ID":ATCH_FILE_ID},
		success: function(data, status) {
			$("#filedel"+FILE_STRE_COURS).remove();
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
<input type="hidden" name="NEWS_ID" value="${regionNewsVO.NEWS_ID}" />
<input type="hidden" name="ATCH_FILE_ID" value="${regionNewsVO.ATCH_FILE_ID}" />

	<div class="row">
    	<div class="col-lg-12">
        	<h1 class="page-header">메타데이터 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
	<h2>메타데이터 목록 (지역현안소식) </h2>
	<!-- /.row -->
	<div class="row">
		<p class="tr">
			<a href="#;" onclick="javascript:history.back();"><button type="button" class="btn btn-default">목록</button></a>
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
								<th>수집기관/지역</th>
								<td colspan="3">
									<select name="SEED_ID" id="SEED_ID" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
										<option value="">-- 수집기관 선택 --</option>
										<c:forEach var="cList" items="${codeRKS026List}" varStatus="status">
											<option value="${cList.CODE}" <c:if test="${cList.CODE == regionNewsVO.SEED_ID}"> selected="selected"</c:if>>${cList.CODE_NM}</option>
										</c:forEach>
									</select>
									<select name="REGION" id="REGION" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
										<option value="">-- 지역 선택 --</option>
										<c:forEach var="cList" items="${codeRKS025List}" varStatus="status">
											<option value="${cList.CODE}" <c:if test="${cList.CODE == regionNewsVO.REGION}"> selected="selected"</c:if>>${cList.CODE_NM}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>	
								<th>제  목</th>
								<td colspan="3"><input name="TITLE" class="form-control" placeholder=""  type="text" style="width:80%;" value="${regionNewsVO.TITLE}" /></td>
							</tr>
							<tr>	
								<th>작성일</th>
								<td>
									<input name="INDT" id="INDT" class="input-sm ip" placeholder=""  type="text" style="width:30%;" value="${regionNewsVO.INDT}" class="readOnlyClass" readonly title="작성일" /> 
									<a href="#" onClick="javascript:fn_egov_NormalCalendar(document.pgfrm, document.pgfrm.INDT);"><button type="button" class="btn btn-default">달력</button></a> <br/>
								</td>
								<th>작성자</th>
								<td><input name="WRITER" id="WRITER" class="input-sm ip" placeholder=""  type="text" style="width:30%;" value="${regionNewsVO.WRITER}" title="작성자" /></td>
							</tr>
							<tr>	
								<th>내  용</th>
								<td colspan="3">
									<textarea name="CONTENT" cols="100" rows="10" class="ip" style="width:100%; height:400px;">${regionNewsVO.CONTENT}</textarea>
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
 									<c:if test="${fn:length(regionNewsFileList) > 0}">
										<c:forEach var="cList" items="${regionNewsFileList}" varStatus="status">
											<div id="filedel${cList.STRE_FILE_NM}" style="padding-bottom:8px;">
												<img src="${cList.FILE_STRE_COURS}${cList.STRE_FILE_NM}.${cList.FILE_EXTSN}" width="80" height="80" />
												<span id="filemsg${cList.STRE_FILE_NM}">${cList.ORIGNL_FILE_NM}</span> &nbsp;&nbsp; 
												<button type="button" class="btn btn-danger" onclick="setFileDelete('${regionNewsVO.ATCH_FILE_ID}','${cList.STRE_FILE_NM}');">삭제</button>
												<br />
											</div>
										</c:forEach>
									</c:if>
 								</td>
							</tr>

						</tbody>
					
					</table>
				</div>
				<!-- /.table-responsive -->

			</div><!--//movebox-->

				<p class="tr">
					<a href="#;" onclick="javascript:history.back();"><button type="button" class="btn btn-default">목록</button></a>
					<a href="#;" onclick="return setUpdate();"><button type="button" class="btn btn-success">수정</button></a>
					<a href="#;" onclick="return setDelete();"><button type="button" class="btn btn-danger">삭제</button></a>
				</p>

			</div>
			<!-- /.panel-body -->
		</div>
	</div>

	<%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchVar.jsp" %>
	
</form>
</div>
</body>
</html>
