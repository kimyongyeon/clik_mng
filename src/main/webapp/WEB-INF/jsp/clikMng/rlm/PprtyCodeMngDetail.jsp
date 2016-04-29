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
<title>정당코드 상세</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mngManage" staticJavascript="false" xhtml="true" cdata="false"/>
 
<script type="text/javaScript" language="javascript">
<!--
$(document).ready(function(){

});

/******************************************
 * 취소
 ******************************************/
function fnListMng() {
	if(confirm("작성을 취소하고 목록화면으로 이동 하시겠습니까?")){
		var f = document.getElementById("detailForm");
	    f.action = "<c:url value='/rlm/selectPprtyCodeList.do'/>";
	    f.submit();
	}
}

/******************************************
 * 등록
 ******************************************/
function fnEditMng(CUD_CODE) {
	//필수값 체크
	if($('#PPRTY_CODE').val() == ''){
		alert('정당코드는 필수입력 값 입니다.');
		$('#PPRTY_CODE').focus();
		return false;
	}
	if($('#PPRTY_NM').val() == ''){
		alert('정당명은 필수입력 값 입니다.');
		$('#PPRTY_NM').focus();
		return false;
	}
	if($('#BEGIN_DE').val() == ''){
		alert('시작일자는 필수입력 값 입니다.');
		$('#BEGIN_DE').focus();
		return false;
	}
	if($('#END_DE').val() == ''){
		alert('종료일자는 필수입력 값 입니다.');
		$('#END_DE').focus();
		return false;
	}
	
	$.ajax({
	   type: "POST",
	   url : "/rlm/pprtyCodeProc.do",
	   data : {
		   CUD_CODE : CUD_CODE
		   ,PPRTY_SN : $('#PPRTY_SN').val()
		   ,PPRTY_CODE : encodeURIComponent(htmlEntityEnc($('#PPRTY_CODE').val()))
		   ,PPRTY_ABRV : encodeURIComponent(htmlEntityEnc($('#PPRTY_ABRV').val()))
		   ,PPRTY_NM : encodeURIComponent(htmlEntityEnc($('#PPRTY_NM').val()))
		   ,BEGIN_DE : $('#BEGIN_DE').val()
		   ,END_DE : $('#END_DE').val()
		   ,RM : encodeURIComponent(htmlEntityEnc($('#RM').val()))
		   ,DELETE_AT : $('input[name="DELETE_AT"]:checked').val()
	   },
	   dataType:"json",
	   async : false,
	   success: function(result) {
		   alert(result[0].resultMsg);
		   if(result[0].resultCode == "success"){
			   var f = document.getElementById("detailForm");
			   f.action = "<c:url value='/rlm/selectPprtyCodeList.do'/>";
			   f.submit();
		   }else{
			   $('#PPRTY_CODE').focus();
		   }
	   }
		,error:function(e) {
			alert(e.responseText);
	   }
	});	
}

/* ********************************************************
*  삭제
******************************************************** */
function fnDelete(){
	
	if(!confirm("삭제 하시겠습니까?")){
		return false;
	}
	
	$.ajax({
	   type: "POST",
	   url : "/rlm/deletePprtyCode.do",
	   data : {"deleteKeyList" : '${obj.PPRTY_CODE}'},
	   dataType:"json",
	   async : false,
	   success: function(args) {
		alert(args[0].resultMsg);
		
		var f = document.getElementById("detailForm");
	    f.action = "<c:url value='/rlm/selectPprtyCodeList.do'/>";
	    f.submit();
	   }
		,error:function(e) {
			alert(e.responseText);
	   }
	});
}

/* ********************************************************
*  특수문자 변환
******************************************************** */
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
<form id="detailForm" name="detailForm" method="post">
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">정당코드 관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>정당코드 상세</h2>
				
				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="15%" />
						
						<col />
					 </colgroup>
					<tbody>
						<tr style="display:none;">
							<th>정당순번</th>
							<td><input type="text" id="PPRTY_SN" name="PPRTY_SN" value="${obj.PPRTY_SN }" /></td>
						</tr>
						<tr>
							<th>* 정당코드</th>
							<td>
								<c:choose>
									<c:when test="${obj == null || obj.PPRTY_CODE == null }">
									<input type="text" id="PPRTY_CODE" name="PPRTY_CODE" value="" />
									</c:when>
									<c:otherwise>
									<input type="hidden" id="PPRTY_CODE" name="PPRTY_CODE" value="${obj.PPRTY_CODE }" />
									${obj.PPRTY_CODE }
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th>* 정당명</th>
							<td><input type="text" id="PPRTY_NM" name="PPRTY_NM" value="${obj.PPRTY_NM }" /></td>
						</tr>
						<tr>
							<th>정당약칭</th>
							<td><input type="text" id="PPRTY_ABRV" name="PPRTY_ABRV" value="${obj.PPRTY_ABRV }" /></td>
						</tr>
						<tr>
							<th>* 시작일자</th>
							<td><input type="text" id="BEGIN_DE" name="BEGIN_DE" value="${obj.BEGIN_DE }" /></td>
						</tr>
						<tr>
							<th>* 종료일자</th>
							<td><input type="text" id="END_DE" name="END_DE" value="${obj.END_DE }" /></td>
						</tr>
						<script>
						$('#BEGIN_DE').datepicker({
							dateFormat: 'yy-mm-dd',
							changeYear: true,
							changeMonth: true,
							showMonthAfterYear: true,
							showButtonPanel: true,
							showOn: 'button',
							buttonText: '<img src="/images/clikmng/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
							yearRange: '-100:+0',
							dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
							dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
							monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
							monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
						});
						$('#END_DE').datepicker({
							dateFormat: 'yy-mm-dd',
							changeYear: true,
							changeMonth: true,
							showMonthAfterYear: true,
							showButtonPanel: true,
							showOn: 'button',
							buttonText: '<img src="/images/clikmng/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
							yearRange: '-100:+0',
							dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
							dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
							monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
							monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
						});
						</script>
						<tr>
							<th>비고</th>
							<td><textarea id="RM" name="RM" style="width:100%;" cols="" rows="">${obj.RM }</textarea></td>
						</tr>
						<c:if test="${obj != null && obj.PPRTY_CODE != null }">
						<tr>
							<th>* 삭제여부</th>
							<td>
								<input type="radio" id="DELETE_AT_Y" name="DELETE_AT" value="Y" <c:if test="${obj.DELETE_AT == 'Y'}">checked="checked"</c:if> /> 삭제
								<input type="radio" id="DELETE_AT_N" name="DELETE_AT" value="N" <c:if test="${obj.DELETE_AT == 'N' || obj.DELETE_AT == null}">checked="checked="</c:if> /> 미삭제
							</td>
						</tr>
						</c:if>
					</tbody>
				</table>

				<div class="right">
					<button type="button" class="btn btn-default" onclick="fnListMng();">목록</button>
					<c:choose>
						<c:when test="${obj == null || obj.PPRTY_CODE == null }">
						<button type="button" class="btn btn-primary" onclick="fnEditMng('C');">등록</button>
						</c:when>
						<c:otherwise>
<!-- 						<button type="button" class="btn btn-danger" onclick="fnDelete();">삭제</button> -->
						<button type="button" class="btn btn-primary" onclick="fnEditMng('U');">수정</button>
						</c:otherwise>
					</c:choose>

				</div>

		</div><!--//page-wrapper-->
</form>
</body>
</html>

