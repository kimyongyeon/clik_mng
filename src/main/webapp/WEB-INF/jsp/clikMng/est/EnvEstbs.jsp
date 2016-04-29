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
<title>자동설정관리</title>

<style type="text/css">
	div.fl{ width:45%; margin-right:20px; overflow:hidden;}
	input[type="checkbox"] {
		margin-right:10px;
	}
</style>
<script type="text/javascript" language="javascript">
<!--
/* ********************************************************
* 모두 자동 게시 승인 처리
******************************************************** */
function fnAutoAllYCheck(size) {
	$("input:radio.Y").prop("checked", "checked");

	/* 	
	$('input:radio[name*="checkAuto"]:input[value=Y]').each(function(index,value){
		$(this).prop("checked", true);
	});
 	*/	
}

/* ********************************************************
* 모두 수동 게시 승인 처리
******************************************************** */
function fnAutoAllNCheck(size) {
	$("input:radio.N").prop("checked", "checked");
/* 	
	$('input:radio[name*="checkAuto"]:input[value=N]').each(function(index,value){
		$(this).attr("checked", true);
	});
 */	
}

/* ********************************************************
* 모두 PDF변환 자동 승인 처리
******************************************************** */
function fnAutoPdfAllYCheck(size) {
	$("input:radio.O").prop("checked", "checked");
/* 	
	$('input:radio[name*="checkPdfAuto"]:input[value=Y]').each(function(index,value){
		$(this).prop("checked", true);
	});
 */	
}

/* ********************************************************
* 모두 PDF변환 수동 승인 처리
******************************************************** */
function fnAutoPdfAllNCheck(size) {
	$("input:radio.X").prop("checked", "checked");
	
/* 	
	$('input:radio[name*="checkPdfAuto"]:input[value=N]').each(function(index,value){
		$(this).attr("checked", true);
	});
 */	
}

/* ********************************************************
* radio button 적용
******************************************************** */
function fncApply() {

	if(confirm("적용 하시겠습니까?")){
		
	    document.listForm.action = "<c:url value='/est/ApplySetup.do'/>";
	    document.listForm.submit();
	}
	
	return;
}




-->

</script>



</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form name="listForm" id="listForm" method="post">
<input type="hidden" name="ntctAtListSize" id="ntctAtListSize" value="<c:out value="${ntctAtListSize}" />" />
<input type="hidden" name="cnvrAtListSize" id="cnvrAtListSize" value="<c:out value="${cnvrAtListSize}" />"  />


		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">자동설정관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
				<div class="fl">
					<h2>자동/수동 게시 승인 설정</h2>
					<table class="table table-striped table-bordered table-hover "  id="">
						<colgroup>
							<col width="30%" />
							
							<col />
						 </colgroup>
						 <thead>
							<tr>
								<th>전체</th>
								<th>
									<label for="check1">자동</label> <input type="radio" name="checkAuto" class="Y" value="Y" onclick="fnAutoAllYCheck(<c:out value="${ntctAtListSize}" />); " />
									<label for="check2">수동</label> <input type="radio" name="checkAuto" class="N" value="N" onclick="fnAutoAllNCheck(<c:out value="${ntctAtListSize}" />); " />
								</th>
							</tr>
						 </thead>
						<tbody>
						<c:forEach var="x" items="${ntctAtList}" varStatus="s">			
							<tr>
								<th><c:out value="${x.colctInfoTyCodeNm }" /></th>
								<td>
									 <label for="check">자동</label> <input type="radio"  name="checkAuto_${s.count}" class="Y" value="Y_${x.colctInfoTyCode}" <c:if test="${x.atmcNtceAt == 'Y'}">checked="checked"</c:if> />
									 <label for="check">수동</label> <input type="radio"  name="checkAuto_${s.count}" class="N" value="N_${x.colctInfoTyCode}" <c:if test="${x.atmcNtceAt == 'N'}">checked="checked"</c:if> />
								</td>
							</tr>						
						</c:forEach>
						</tbody>
					</table>

				</div><!--//fl-->

				<div class="fl">

					<h2>자동/수동 PDF변환 승인 설정</h2>
					<table class="table table-striped table-bordered table-hover "  id="">
						<colgroup>
							<col width="30%" />
							
							<col />
						 </colgroup>
						 <thead>
							<tr>
								<th>전체</th>
								<th>
									<label for="check">자동</label> <input type="radio" name="checkPdfAuto"  value="Y" onclick="fnAutoPdfAllYCheck(<c:out value="${cnvrAtListSize}" />);" />
									<label for="check">수동</label> <input type="radio" name="checkPdfAuto"  value="N" onclick="fnAutoPdfAllNCheck(<c:out value="${cnvrAtListSize}" />);" />
								</th>
							</tr>
						 </thead>
						<tbody>
						<c:forEach var="n" items="${cnvrAtList}" varStatus="s">			
							<tr>
								<th><c:out value="${n.colctInfoTyCodeNm }" /></th>
								<td>
									 <label for="check">자동</label> <input type="radio" name="checkPdfAuto_${s.count}" class="O" value="Y_${n.colctInfoTyCode}" <c:if test="${n.atmcCnvrAt == 'Y'}">checked="checked"</c:if> />
									 <label for="check">수동</label> <input type="radio" name="checkPdfAuto_${s.count}" class="X" value="N_${n.colctInfoTyCode}" <c:if test="${n.atmcCnvrAt == 'N'}">checked="checked"</c:if> />
								</td>
							</tr>						
						</c:forEach>
						</tbody>
					</table>
					
				<p class="tr">
					<button type="button" class="btn btn-primary" onclick="fncApply();">적용</button>
				</p>					

				</div><!--//fl-->
		
				 <!-- /.panel-body -->
                     
		</div>
        <!-- /#page-wrapper -->
</form>
</body>
</html>
