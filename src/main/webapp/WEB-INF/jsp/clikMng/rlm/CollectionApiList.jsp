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
<title>지방의회관리</title>

<script type="text/javaScript" language="javascript" defer="defer">

/* ********************************************************
* 등록
******************************************************** */
function fncAddCollectionInsert() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rlm/CollectionApiRegist.do' />";

	varForm.submit();
}

/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	
// 	if($("#searchKeyword").val() == "" || $("#searchKeyword").val() == null) {
// 		alert('검색어를 입력해 주세요.');
// 		return; 
// 	}
	
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rlm/CollectionApiList.do' />";
	varForm.pageIndex.value = '1';
	varForm.submit();
	
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************* */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/rlm/CollectionApiList.do'/>";
   	document.listForm.submit();
}


/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.currentPageNo.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/rlm/CollectionApiList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
	varForm.pageUnit.value = val;
    varForm.action = "<c:url value='/rlm/CollectionApiList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 정렬 변경(셀렉트박스)
******************************************************** */
function fnChgOrder(val) {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rlm/CollectionApiList.do'/>";
    varForm.submit();
}

/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnMngDetailView(rasmbly_id) {

	var varForm = document.listForm;
	varForm.rasmbly_id.value = rasmbly_id;
	varForm.action = "<c:url value='/rlm/CollectionApiDetail.do'/>";
	varForm.submit();
}

/* ********************************************************
*  전체선택 / 해제
******************************************************** */
function fn_all_chk(){
	if($('input[id="all_chk"]:checked').length == 1){
		$('table input[type="checkbox"]').each(function(index,value){
			$(value).attr('checked','checked');
		});
	}else{
		$('table input[type="checkbox"]').each(function(index,value){
			$(value).removeAttr('checked');
		});
	}
}

/* ********************************************************
*  삭제
******************************************************** */
function fn_delete(){
	
	var varForm = document.listForm;
	var keyList = "";
	
	$('input[type="checkbox"]:checked').each(function(index,value){
		if($(value).val().trim() != ""){
			keyList += $(value).val().trim() + ",";
		}
	});
	
	keyList = keyList.substring(0, keyList.length-1);
	
	if(!confirm("선택한 항목을 삭제 하시겠습니까?")){
		return false;
	}else if(keyList.length == 0){
		alert("선택한 항목이 존재하지 않습니다.");
		return false;
	}
	
	varForm.deleteKeyList.value = keyList;
	//alert(keyList);
	varForm.action = "<c:url value='/rlm/CollectionApiDelete.do'/>";
	varForm.submit();
}

$(document).ready(function() {
	
	/* ********************************************************
	*  검색창에서 엔터 시, 검색 진행
	******************************************************** */
	$('#searchKeyword').keydown(function(e) {
		if(e.keyCode == 13)
		{
			if($.trim($('#searchKeyword').val()).length > 0)
			{
				fnSearch();
			}
		}
	})
});
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form id="listForm" name="listForm" method="post">

			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
            <input name="deleteKeyList" type="hidden" value=""/>
            <input name="rasmbly_id" type="hidden" value=""/>


		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">지방의회 관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->

			<!--
			<div class="search">
					<input type="text" name="schDt1" id="schDt1" value="<c:out value="${searchVO.schDt1}" />" class="input-sm ip" style="width:150px;" />
					&nbsp;~&nbsp;
					<input type="text" name="schDt2" id="schDt2" value="<c:out value="${searchVO.schDt2}" />" class="input-sm ip" style="width:150px;" />
					
	 				<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default1">한달</button></a>
					<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default2">일주일</button></a>
					<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default3">오늘</button></a>
	 				<script>setCal("schDt1","schDt2");</script>
			</div>
			 -->

			<div class="select_box">
				<span>

					<select id="selectStatus" name="selectStatus" aria-controls="dataTables-example" class="ip input-sm" style="width:120px;">
						<option value="">지방의회 상태</option>
						<c:forEach var="v" items="${statusCodeList}" varStatus="s">
							<option value="${v.code}" <c:if test="${searchVO.selectStatus == v.code}">selected</c:if>>${v.codeNm}</option>
						</c:forEach>
					</select>
				
					<select id="searchCondition" name="searchCondition" aria-controls="dataTables-example" class="ip input-sm" style="width:120px;">
						<option value="">전체</option>
						<option value="id" <c:if test="${searchVO.searchCondition == 'id' }">selected</c:if>>지방의회ID</option>
						<option value="name" <c:if test="${searchVO.searchCondition == 'name' }">selected</c:if>>지방의회명</option>
						<option value="url" <c:if test="${searchVO.searchCondition == 'url' }">selected</c:if>>URL</option>
						<option value="key" <c:if test="${searchVO.searchCondition == 'key' }">selected</c:if>>API Key No</option>
					</select>

					<input type="text" class="ip input-sm input-search" id="searchKeyword" name="searchKeyword" value="<c:if test="${searchVO.searchKeyword != null}">${searchVO.searchKeyword}</c:if>"  />
					<button type="button" class="btn btn-primary" onclick="javascript:fnSearch();"><spring:message code="button.search" /></button>

				</span>
			</div><!--//select_box-->

			<div class="page">
				총 건수 : <fmt:formatNumber value="${paginationInfo.totalRecordCount}" type="number" />건

				<span>
					정렬 : 
					<select id="sortOrder" name="sortOrder" onchange="fnChgOrder(this.value)">
						<option value="">정렬기준</option>
						<option value="rasmblyId_DESC" <c:if test="${searchVO.sortOrder == 'rasmblyId_DESC'}">selected</c:if>>지방의회 ID 내림차순</option>
						<option value="rasmblyId_ASC" <c:if test="${searchVO.sortOrder == 'rasmblyId_ASC'}">selected</c:if>>지방의회 ID 오름차순</option>
						<option value="rasmblyNm_DESC" <c:if test="${searchVO.sortOrder == 'rasmblyNm_DESC'}">selected</c:if>>지방의회 명 내림차순</option>
						<option value="rasmblyNm_ASC" <c:if test="${searchVO.sortOrder == 'rasmblyNm_ASC'}">selected</c:if>>지방의회 명 오름차순</option>
						<option value="frstRegistDt_DESC" <c:if test="${searchVO.sortOrder == 'frstRegistDt_DESC'}">selected</c:if>>등록일자 내림차순</option>
						<option value="frstRegistDt_ASC" <c:if test="${searchVO.sortOrder == 'frstRegistDt_ASC'}">selected</c:if>>등록일자 오름차순</option>
					</select>

					&nbsp;&nbsp;&nbsp;

					출력건수
					<select name="pageUnit" id="listCnt" aria-controls="dataTables-example" class=" input-sm" onchange="fnChgListCnt(this.value)">
						<option value="10" <c:if test="${searchVO.pageUnit == '10'}"> selected="selected"</c:if>>10</option>
						<option value="30" <c:if test="${searchVO.pageUnit == '30'}"> selected="selected"</c:if>>30</option>
						<option value="50" <c:if test="${searchVO.pageUnit == '50'}"> selected="selected"</c:if>>50</option>
						<option value="100" <c:if test="${searchVO.pageUnit == '100'}"> selected="selected"</c:if>>100</option>
					</select>
				</span>

			</div>				
				

				<table class="table table-striped table-bordered table-hover "  id="dataTable" >
					<colgroup>
						<col width="" />
						<col width="5%" />
						<col width="" />
						<col width="" />
						<col width="" />
						<col width="" />
						<col width="" />
						<col width="" />
						<col width="" />
					</colgroup>
					<thead>
						<tr>
							<th rowspan="2"><input type="checkbox" id="all_chk" onclick="javascript:fn_all_chk();"></th>
							<th>번호</th>
							<th>지방의회 ID</th>
							<th>지방의회 명</th>
							<th>URL</th>
							<th>지방의회 상태</th>
							<th>등록일자</th>
							<th>Server IP</th>
						</tr>
						<tr>
							<th colspan="7">API Key No</th>
						</tr>
					</thead>
					<tbody>
							<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
							<c:if test="${fn:length(resultList) == 0}">
								<tr>
									<td class="lt_text3" colspan="8"><spring:message code="common.nodata.msg" /></td>
								</tr>
							</c:if>
							<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
					
							<c:forEach items="${resultList}" var="item" varStatus="s">
							<tr>
								<td rowspan="2"><input type="checkbox" value="${item.rasmbly_id}"></td>
								<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageUnit + s.count}"/></td>
								<td>${item.rasmbly_id }</td>
								<td><a href=#none onclick="fnMngDetailView('${item.rasmbly_id }');">${item.rasmbly_nm }</a></td>
								<td>${item.rasmbly_site_url }</td>
								<td>${item.rasmbly_sttus_nm }</td>
								<td>${item.frst_regist_dt}</td>
								<td>${item.server_ip }</td>
							</tr>
							<tr>
								<td colspan="7">${item.api_crtfc_key }</td>
							</tr>
							</c:forEach>
					</tbody>
				</table>
				
				<c:if test="${!empty searchVO.pageIndex }">
				<div align="center">
				    <div>
				    	<ul class="pagination">
				        	<ui:pagination paginationInfo = "${paginationInfo}"
				            type="image"
				            jsFunction="linkPage"
				            />
				         </ul>
				    </div>
				
				</div>
				</c:if>				
				
				<p class="tr">
					<button type="button" class="btn btn-primary" onclick="fncAddCollectionInsert();">등록</button>
					<button type="button" class="btn btn-danger" onclick="fn_delete();">삭제</button>
				</p>

				
				
        <script>
/* 	        $('#dataTable').dataTable( {
				"pageLength":100
			});        
 */	        
	        function fn_enter_chk(event){

		    	if(event.keyCode == 13){
		    		if($('#searchKeyword').val() != "검색어를 입력하세요" && $('#searchKeyword').val() != "")
		    		{
		    			//setCookieSearchHistory($('#searchKeyword').val());
		    			document.listForm.submit();
		    		}
		    		else if($('#searchKeyword').val() == "검색어를 입력하세요" || $('#searchKeyword').val() == "")
		    		{
		    			alert("검색어를 입력해 주세요.");
		    			return false;
		    		}
		    		else
		    		{
		    			document.listForm.submit();
		    		}
		    	}
	        }
	        
	        
	        
        </script>        
			

	</div><!--//page-wrapper-->
</form>	
</body>
</html>