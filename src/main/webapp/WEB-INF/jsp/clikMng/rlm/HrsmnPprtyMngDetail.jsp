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
<title>정당관리 상세정보</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mngManage" staticJavascript="false" xhtml="true" cdata="false"/>
 
<script type="text/javaScript" language="javascript">
$(document).ready(function(){
	
});

<!--
/******************************************
 * 목록
 ******************************************/

function fnListMng() {
	//if(confirm("수정을 취소하시겠습니까?")){
		var f = document.getElementById("detailForm");
	    f.action = "<c:url value='/rlm/HrsmnPprtyMngList.do'/>";
	    f.submit();
	//}
}

/******************************************
 * 수정
 ******************************************/

function fnEditMng() {
	if(confirm("수정 하시겠습니까?")){
	    var f = document.getElementById("detailForm");
	    
	    //필수값 체크
	    if(f.hrsmnpd_sn.value == ""){
	    	alert("기수를 선택해 주세요.");
	    	$('#hrsmnpd_sn').focus();
	    	return false;
	    }
	    
	    $('#pprty_list').val('');
	    
	    $('input[name="pprty"]:checked').each(function(){
	    	$('#pprty_list').val($('#pprty_list').val() + "," + $(this).val());
	    });
	    
	    $('#pprty_list').val($('#pprty_list').val().substr(1));
	    
	    if(f.pprty_list.value == ""){
	    	alert("정당을 선택해 주세요.");
	    	$('input[name="pprty"]').focus();
	    	return false;
	    }
	    
	    f.action = "<c:url value='/rlm/HrsmnPprtyMngRegistProc.do'/>";
	    f.submit();
	}
}

/******************************************
 * 삭제
 ******************************************/

function fnDelMng() {
	if(confirm("삭제 하시겠습니까?")){
	    var f = document.getElementById("detailForm");
	    f.pprty_list.value = "";
	    f.action = "<c:url value='/rlm/HrsmnPprtyMngRegistProc.do'/>";
	    f.submit();
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
                    <h1 class="page-header">정당관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>정당관리 상세정보</h2>
				
				<input type="hidden" id="pprty_list" name="pprty_list" value="" />
				
				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="15%" />
						
						<col />
					 </colgroup>
					<tbody>
						<tr>
							<th>기수</th>
							<td>
								<c:set var="HrsmnpdPprtyVal"></c:set>
								<c:set var="HrsmnpdPprtyName"></c:set>
							
								<c:forEach items="${HrsmnList}" var="item">

									<c:if test="${item.hrsmnpd_sn == HrsmnpdPprtyVO.hrsmnpd_sn}">

										<c:choose>
											<c:when test="${HrsmnpdPprtyVO.ht_se_stdcd == 'HTS001'}">
												<c:set var="HrsmnpdPprtyVal">${item.hrsmnpd_sn}#HTS001</c:set>
												<c:set var="HrsmnpdPprtyName">${item.hrsmnpd_nm} 전반기</c:set>
											</c:when>
											<c:when test="${HrsmnpdPprtyVO.ht_se_stdcd == 'HTS002'}">
												<c:set var="HrsmnpdPprtyVal">${item.hrsmnpd_sn}#HTS002</c:set>
												<c:set var="HrsmnpdPprtyName">${item.hrsmnpd_nm} 후반기</c:set>
											</c:when>
										</c:choose>

									</c:if>

								</c:forEach>

								<input type="hidden" id="hrsmnpd_sn" name="hrsmnpd_sn" value="${HrsmnpdPprtyVal}" />
								<span>${HrsmnpdPprtyName}</span>
							</td>
						</tr>
						<tr>
							<th>정당</th>
							<td>
								<ul class="roll">
								<c:forEach items="${pprtyList}" var="item">
								<li>
									<c:set var="checked"></c:set>
									<c:if test="${resultMap[item.pprty_code] == 'true'}">
										<c:set var="checked">checked</c:set>
									</c:if>
									<input type="checkbox" name="pprty" id="${item.pprty_code}" value="${item.pprty_code}" ${checked} />
									<label for="${item.pprty_code}">${item.pprty_nm}</label>
									</li>
								</c:forEach>
								</ul>

							</td>
						</tr>
					</tbody>
				</table>

				<div class="right mb20">
					<button type="button" class="btn btn-default" onclick="fnListMng();">목록</button>
					<button type="button" class="btn btn-success" onclick="fnEditMng();">수정</button>
					<button type="button" class="btn btn-danger" onclick="fnDelMng();">삭제</button>
				</div>

		</div><!--//page-wrapper-->
</form>
</body>
</html>

