<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>의견보내기 목록</title>

<script type="text/javaScript" language="javascript" defer="defer">

/* ********************************************************
* 의견보내기 삭제
******************************************************** */
function fncDelOpinion() {
	
	if	(confirm("삭제하시겠습니까?"))	{
		//선택된 항목 체크
		var delSeq = '';
		var checkBox = $('#tableList').find(':input[name="chkbox"]');
		
		for(var i=0;i<checkBox.length; i++){
			var chkbox = checkBox[i];
			if($(chkbox).is(':checked')){
				
				if(delSeq.length > 0)
					delSeq += ',' + $(chkbox).parent().find(':input[name="seq"]').val();
				else
					delSeq += $(chkbox).parent().find(':input[name="seq"]').val();
			}
		}
		
		if(delSeq == '' || delSeq.length <= 0){
			alert('삭제할 항목을 선택해주세요.');
			return;
		}
		
		$('#delSeq').val(delSeq);
		var varForm = document.listForm;
		varForm.action = "<c:url value='/mob/OnsDel.do' />";

		varForm.submit();	
	}
	
}

/* ********************************************************
* 의션보내기 검색
******************************************************** */
function fnSearch() {
	
	if($("#searchKeyword").val() == "" && $("#schDt1").val() == "" && $("#schDt2").val() == ""){
		alert('검색어 또는 기간을 입력해 주세요.');
		return;
	} else if ($("#searchKeyword").val() == null && ($("#schDt1").val() != "" && $("#schDt2").val() == "") 
			|| ($("#schDt1").val() == "" && $("#schDt2").val() != "")){
			alert("기간을 정상적으로 입력해 주세요.");
			return;
	}
	
	// 달력 validate
	var ntceBgndeYYYMMDD = document.getElementById('schDt1').value;
	var ntceEnddeYYYMMDD = document.getElementById('schDt2').value;

	var iChkBeginDe = ntceBgndeYYYMMDD.split("-").join("") * 1;
	var iChkEndDe = ntceEnddeYYYMMDD.split("-").join("") * 1;

	if(iChkBeginDe > iChkEndDe || iChkEndDe < iChkBeginDe ){
		alert("게시시작일자는 게시종료일자 보다 클수 없고,\n게시종료일자는 게시시작일자 보다 작을수 없습니다. ");
		return;
	}

/* 	
	if($("#searchKeyword").val() == "" || $("#searchKeyword").val() == null) {
		
		 if ( $("#schDt1").val() != ""  &&  $("#schDt2").val() != ""  ) {
			 
		 } else {
			 alert('검색어를 입력해 주세요.');
			 return; 
		 }
		
	} 
	
	//var schDt1 = $("#schDt1").val().replace("-","").replace("-","");
	//var schDt2 = $("#schDt2").val().replace("-","").replace("-","");
	
	//var schDt1 = $("#schDt1").val();
	//var schDt2 = $("#schDt2").val();	
	
	var schDt1 = $("#schDt1").val().replace("-","/").replace("-","/");
	var schDt2 = $("#schDt2").val().replace("-","/").replace("-","/");	

	$("#schDt1").val(schDt1);
	$("#schDt2").val(schDt2);
 */	
	
	var varForm = document.listForm;
	varForm.action = "<c:url value='/mob/OnsList.do' />";
	varForm.submit();
	
}


/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.pageIndex.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/mob/OnsList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
    varForm.action = "<c:url value='/mob/OnsList.do'/>";
    varForm.submit();
}

/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnMngDetailView(mngrId) {

	var varForm = document.listForm;
	varForm.mngrId.value = mngrId;
	varForm.action = "<c:url value='/uss/mng/MngDetail.do'/>";
	varForm.submit();
}
 
/* ********************************************************
*  전체 선택 체크박스
******************************************************** */
function fnCheckAll(){
	var isChecked = $('#chkboxAll').is(':checked');
	
	$('input:checkbox[name="chkbox"]').each(function() {
		$(this).prop("checked", isChecked);
	});
}
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

<DIV id="page-wrapper">
	<div class="row">
    	<div class="col-lg-12">
            <h1 class="page-header">의견보내기</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
			
				<h2>의견보내기 목록</h2>
				
				<form:form name="listForm" action="" method="post">
				
					<div class="search">
						<input type="text" name="schDt1" id="schDt1" value="<c:out value="${mobManageVO.schDt1}" />" class="input-sm ip" style="width:150px;" />
						&nbsp;~&nbsp;
						<input type="text" name="schDt2" id="schDt2" value="<c:out value="${mobManageVO.schDt2}" />" class="input-sm ip" style="width:150px;" />
						
		 				<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default1">한달</button></a>
						<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default2">일주일</button></a>
						<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default3">오늘</button></a>
		 				<script>setCal("schDt1","schDt2");</script>
					</div><!--//tc-->

					<div class="select_box">
						<span>
							<select name="searchCondition" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
							<option value="0">전체</option>
							<option value="selCn" <c:if test="${mobManageVO.searchCondition == 'selCn'}">selected</c:if>>의견</option>
							<option value="selUser" <c:if test="${mobManageVO.searchCondition == 'selUser'}">selected</c:if>>작성자</option>
						</select>
						<input type='text' class="ip input-sm input-search" id='searchKeyword' name='searchKeyword' value="<c:out value='${mobManageVO.searchKeyword}'/>" onKeyDown="javascript:if(event.keyCode==13){fnSearch();}">
						<a href="#LINK" onclick="javascript:fnSearch(); return false;"><button type="button" class="btn btn-primary"><spring:message code="button.search" /></button></a>
						</span>
					</div><!--//select_box-->

		
					<div class="page">
						총 건수 : <c:out value="${paginationInfo.totalRecordCount eq null ? 0 : paginationInfo.totalRecordCount}" />건 
						<span>
							출력건수
							<select class=" input-sm" title="쪽당출력건수" id="pageUnit" name="pageUnit" onchange="fnChgListCnt(this.value)" >
		                        <option value='10' <c:if test="${empty mobManageVO.pageUnit ||  mobManageVO.pageUnit == '' ||  mobManageVO.pageUnit == '10'}">selected</c:if>>10</option>
		                        <option value='30' <c:if test="${mobManageVO.pageUnit == '30'}">selected</c:if>>30</option>
		                        <option value='50' <c:if test="${mobManageVO.pageUnit == '50'}">selected</c:if>>50</option>
		                        <option value='100' <c:if test="${mobManageVO.pageUnit == '100'}">selected</c:if>>100</option>
		                    </select>	
						</span>
					
					</div>
					
					<table class="table table-striped table-bordered table-hover "  id="tableList">
						<colgroup>
							<col width="5%" />
							<col width="" />
							<col width="15%" />
							<col width="10%" />
							<col width="8%" />
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>의견</th>
								<th>작성자</th>
								<th>등록일</th>
								<th><input type="checkbox" id="chkboxAll" onclick="fnCheckAll();"></th>
							</tr>
						</thead>
						<tbody>
							<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
							<c:if test="${fn:length(resultList) == 0}">
								<tr>
									<td class="lt_text3" colspan="5"><spring:message code="common.nodata.msg" /></td>
								</tr>
							</c:if>
							<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
							<c:forEach var="x" items="${resultList}" varStatus="status">
								<tr>
									<td>${(mobManageVO.pageIndex-1) * mobManageVO.pageUnit + status.count}</td>
									<td>${x.opinionCn }</td>
									<td>${x.userId }</td>
									<td>${x.regDate }</td>
									<td>
										<input type="checkbox" name="chkbox">
										<input type="hidden" name="seq" value="${x.seq }">
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<p class="tr">
						<a href="#LINK" onclick="javascript:fncDelOpinion(); return false;"><button type="button" class="btn btn-danger"><spring:message code="button.delete" /></button></a>
					</p>
					
					<c:if test="${not empty resultList}">
				    <div align="center">
				    	<ul class="pagination">
					        <ui:pagination paginationInfo = "${paginationInfo}"
					            type="image"
					            jsFunction="fn_paging"
					            />
					     </ul>
				    </div>
					</c:if>

					<input type="hidden" name="pageIndex" value="<c:out value='${mobManageVO.pageIndex}'/>"/>
			        <input type="hidden" name="currentPageNo" value="<c:out value='${ paginationInfo.currentPageNo }'/>" >
					<input type="hidden" name="authorClCode" value="MNG"/>
					
					<input type="hidden" id="delSeq" name="delSeq">
	
				</form:form>


	
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>
