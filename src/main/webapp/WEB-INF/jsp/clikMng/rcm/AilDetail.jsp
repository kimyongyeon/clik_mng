<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>인증키 발급내역 상세정보</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mngManage" staticJavascript="false" xhtml="true" cdata="false"/>
 
<script type="text/javaScript" language="javascript">
<!--
/******************************************
 * 목록
 ******************************************/
	
function fnListMng() {

	var varForm = document.listForm;
	varForm.procMode.value = "UPDATE";
	varForm.action = "<c:url value='/rcm/AilList.do'/>";
	varForm.submit();
}

/******************************************
 * 수정
 ******************************************/
function fnEditMng() {
	if(confirm("수정 하시겠습니까?")){
		var varForm = document.listForm;
		varForm.procMode.value = "UPDATE";
		varForm.action = "<c:url value='/rcm/AilProc.do'/>";
		
		varForm.reqstInsttNm[1].value = encodeURIComponent(htmlEntityEnc($('#reqstInsttNm').val())).replace(/[!'()*]/g, escape);
		varForm.chargerNm[1].value = encodeURIComponent(htmlEntityEnc($('#chargerNm').val())).replace(/[!'()*]/g, escape);
		varForm.chargerEmail[1].value = encodeURIComponent(htmlEntityEnc($('#chargerEmail').val())).replace(/[!'()*]/g, escape);
		varForm.chargerTelno[1].value = encodeURIComponent(htmlEntityEnc($('#chargerTelno').val())).replace(/[!'()*]/g, escape);
		varForm.prcusePrpos[1].value = encodeURIComponent(htmlEntityEnc($('#prcusePrpos').val())).replace(/[!'()*]/g, escape);
		varForm.rm[1].value = encodeURIComponent(htmlEntityEnc($('#rm').val())).replace(/[!'()*]/g, escape);
		
		varForm.submit();
	}
}

/******************************************
 * 삭제
 ******************************************/
function fnDelMng() {
	if(confirm("삭제 하시겠습니까?")){
		var varForm = document.listForm;
		varForm.procMode.value = "DELETE";
		varForm.action = "<c:url value='/rcm/AilProc.do'/>";
		varForm.submit();
	}
}

/******************************************
 * 특수문자 변환
 ******************************************/
function htmlEntityEnc(str){
    if(str == "" || str == null){
        return str;
    }
    else{
    	str = str.split("&").join("&amp;");
    	str = str.split("#").join("&#35;");
    	str = str.split("<").join("&lt;");
    	str = str.split(">").join("&gt;");
    	str = str.split("\"").join("&quot;");
    	str = str.split("\\").join("&#39;");
    	str = str.split("%").join("&#37;");
    	str = str.split("(").join("&#40;");
    	str = str.split(")").join("&#41;");
    	str = str.split("+").join("&#43;");
    	str = str.split("/").join("&#47;");
    	str = str.split(".").join("&#46;");
    	
        return str;
        //return str.replace("&", "&amp;").replace("#", "&#35;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace('\\', "&#39;").replace('%', "&#37;").replace('(', "&#40;").replace(')', "&#41;").replace('+', "&#43;").replace('/', "&#47;").replace('.', "&#46;");
    }
}
-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
	<div id="page-wrapper">
		<div class="row">
		    <div class="col-lg-12">
		        <h1 class="page-header">OPENAPI 신청내역</h1>
		    </div>
		</div>

		<form name="listForm" action="<c:url value='/rcm/AilProc.do'/>" method="post">
			<input type="hidden" name="unityId" value="<c:out value='${result.unityId}'/>">
			<input type="hidden" name="openApiSeCode" value="<c:out value='${result.openApiSeCode}'/>">
			<input type="hidden" name="procMode">
			<h2>OPENAPI 신청내역 상세정보</h2>
			
			<table class="table table-striped table-bordered table-hover" >
				<colgroup>
					<col width="20%" />
					<col width="" />
				</colgroup>
				<tbody>
					<tr>
						<th>OPENAPI 인증키</th>
						<td>${result.crtfcKey}</td>
					</tr>
					<tr>
						<th>* 신청기관명</th>
						<td>
							<input id="reqstInsttNm" type="text" class="ip" style="width:60%;" value="<c:out value="${result.reqstInsttNm}"/>">
							<input name="reqstInsttNm" class="input-sm ip" type="text" style="display:none;" title="작성자" />
						</td>
					</tr>
					<tr>
						<th>신청일시</th>
						<td>${result.frstRegistPnttm}</td>
					</tr>
					<tr>
						<th>* 담당자명</th>
						<td>
							<input id="chargerNm" type="text" class="ip" style="width:60%;" value="<c:out value="${result.chargerNm}"/>">
							<input name="chargerNm" class="input-sm ip" type="text" style="display:none;" title="작성자" />
						</td>
					</tr>
					<tr>
						<th>* 아이디</th>
						<td>${result.unityId}</td>
					</tr>
					<tr>
						<th>* 담당자이메일</th>
						<td>
							<input id="chargerEmail" type="text" class="ip" style="width:60%;" value="<c:out value="${result.chargerEmail}"/>">
							<input name="chargerEmail" class="input-sm ip" type="text" style="display:none;" title="작성자" />
						</td>
					</tr>
					<tr>
						<th>* 담당자연락처</th>
						<td>
							<input id="chargerTelno" type="text" class="ip" style="width:60%;" value="<c:out value="${result.chargerTelno}"/>">
							<input name="chargerTelno" class="input-sm ip" type="text" style="display:none;" title="작성자" />
						</td>
					</tr>
					<tr>
						<th>* 활용용도</th>
						<td>
							<input id="prcusePrpos" type="text" class="ip" style="width:60%;" value="<c:out value="${result.prcusePrpos}"/>">
							<input name="prcusePrpos" class="input-sm ip" type="text" style="display:none;" title="작성자" />
						</td>
					</tr>
					<tr>
						<th>* 일일처리한도</th>
						<td>
							<select class="input-sm" style="min-width:150px;" id="dalyPermTrfic" name="dalyPermTrfic">
								<option <c:if test="${result.dalyPermTrfic == '1000'}">selected</c:if> value="1000">1,000 건</option>
								<option <c:if test="${result.dalyPermTrfic == '5000'}" >selected</c:if> value="5000">5,000 건</option>
								<option <c:if test="${result.dalyPermTrfic == '10000'}">selected</c:if> value="10000">10,000 건</option>
								<option <c:if test="${result.dalyPermTrfic == '20000'}" >selected</c:if> value="20000">20,000 건</option>
								<option <c:if test="${result.dalyPermTrfic == '50000'}" >selected</c:if> value="50000">50,000 건</option>
								<option <c:if test="${result.dalyPermTrfic == '70000'}">selected</c:if> value="70000">70,000 건</option>
								<option <c:if test="${result.dalyPermTrfic == '100000'}">selected</c:if> value="100000">100,000 건</option>
								<option <c:if test="${result.dalyPermTrfic == '0'}">selected</c:if> value="0">무제한</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>승인상태</th>
						<td>
							<select class="input-sm" style="min-width:150px;" id="sttusCode" name="sttusCode">
								<option <c:if test="${result.sttusCode == 'STC01'}">selected</c:if> value="STC01">대기</option>
								<option <c:if test="${result.sttusCode == 'STC02'}">selected</c:if> value="STC02">승인</option>
								<option <c:if test="${result.sttusCode == 'STC03'}">selected</c:if> value="STC03">차단</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>승인일시</th>
						<td>${result.confmPnttm}</td>
					</tr>
					<tr>
						<th>비고</th>
						<td>
							<textarea id="rm" rows="5" class="ip" style="width:98%;">${result.rm}</textarea>
							<textarea name="rm" rows="5" class="ip" style="display:none;"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</form>

		<p class="tr">
			<button type="button" class="btn btn-default" onclick="fnListMng(); return false;">목록</button>
			<button type="button" class="btn btn-success" onclick="fnEditMng(); return false;">수정</button>
			<button type="button" class="btn btn-danger" onclick="fnDelMng(); return false;">삭제</button>
		</p>
	</div><!--//page-wrapper-->
</body>
</html>

