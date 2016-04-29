<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>


<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>지방의회/지자체 담당자 관리</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* ********************************************************
* 탭클릭시 화면전환
******************************************************** */
function fn_tabClick(sel_name) {
	if (sel_name == 'MNG') {
		document.listForm.authorClCode = sel_name;
		document.listForm.action = "/uss/mng/MngList.do";
	} else {
		document.listForm.authorClCode = sel_name;
		document.listForm.action = "/uss/mng/LocalMngList.do";
	}
	
	document.listForm.submit();		
}

/* ********************************************************
* 관리자 검색
******************************************************** */
function fnSearch() {
	/*
	if($("#searchKeyword").val() == "" || $("#searchKeyword").val() == null) {
		alert('검색어를 입력해 주세요.');
		return; 
	}
	*/
	
	var varForm = document.listForm;
	varForm.action = "<c:url value='/uss/mng/LocalMngList.do' />";
	varForm.submit();
	
}

/* ********************************************************
* 엑셀 다운로드
******************************************************** */
function fnExcel() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/uss/mng/LocalMngListExcelDownload.do' />";
	varForm.submit();
}

/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.pageIndex.value = currentPageNo;
    varForm.action = "<c:url value='/uss/mng/LocalMngList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
    varForm.action = "<c:url value='/uss/mng/LocalMngList.do'/>";
    varForm.submit();
}


/* ********************************************************
* 게시물 정렬 변경(셀렉트박스)
******************************************************** */
function fnChgOrder(val) {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/uss/mng/LocalMngList.do'/>";
    varForm.submit();
}

/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnMngDetailView(unityId) {
	var varForm = document.listForm;
	varForm.unityId.value = unityId;
	varForm.action = "<c:url value='/uss/mng/LocalMngDetail.do'/>";
	varForm.submit();
}

/* ********************************************************
*  승인여부 수정처리
******************************************************** */
function fnApproval(unityId, value){
	var varForm = document.listForm;
	varForm.unityId.value = unityId;
	varForm.action = "<c:url value='/uss/mng/ApproveLocalMng.do'/>";
	
	if(value == 'Y') {
		if(confirm(unityId+'를(을) 승인으로 상태 변경 하시겠습니까?')){
			varForm.confmSttusAt.value = value;
			varForm.submit();
		}
	} else {
		if(confirm(unityId+'를(을) 미승인으로 상태 변경 하시겠습니까?')){
			varForm.confmSttusAt.value = value;
			varForm.submit();
		}
	}
	
}

/* ********************************************************
* 기관유형 검색
******************************************************** */
function insttTyChange(fInsttClCode) {
	
	// 지역선택 셀렉트박스 초기화
	$("#INSTT_TY_CODE option").remove();
	$("#INSTT_TY_CODE").append("<option value=''>기관유형 선택</option>");	
	$("#BRTC_CODE option").remove();
	$("#BRTC_CODE").append("<option value=''>지역 선택</option>");
	$("#LOASM_CODE option").remove();
	$("#LOASM_CODE").append("<option value=''>선택</option>");
	
 	$.ajax({
	   type: "POST",
	   url : "/sts/stm/selectAjaxInsttTy.do",
	   data : "fInsttClCode=" + fInsttClCode,
	   dataType:"json", 
	   async : false,
	   success: function(args) {
		   $.each(args, function() {
			   $("#INSTT_TY_CODE").append("<option value='"+this.fInsttTyClCode+"'>"+this.fInsttTyClCodeNm+"</option>");
			});
	   }
 		,error:function(e) {
			alert(e.responseText);
	   }
	});
} 

/* ********************************************************
* 지역 조회
******************************************************** */
function localChange(insttClCode) {
	
	$("#BRTC_CODE option").remove();
	$("#BRTC_CODE").append("<option value=''>지역 선택</option>");
	$("#LOASM_CODE option").remove();
	$("#LOASM_CODE").append("<option value=''>선택</option>");
	
	// 셀렉트박스 초기화
	$("BRTC_CODE option").remove();
   
	$.ajax({
		type: "POST",
		url : "/sts/stm/selectAjaxBrtc.do",
		dataType:"json",
		async : false,
		success: function(args) {
			$.each(args, function() {
				$("#BRTC_CODE").append("<option value='"+this.codeId+"'>"+this.codeIdNm+"</option>");
			});
		}
		,error:function(e) {
			alert(e.responseText);
		}
	});
} 

/* ********************************************************
* 소속 조회
******************************************************** */
function psitnChange(brtcCode) {

	var formData = "brtcCode=" + brtcCode + "&insttClCode=" + $('#INSTT_TY_CODE').val();
	
	// 셀렉트박스 초기화
	$("#LOASM_CODE option").remove();
	$("#LOASM_CODE").append("<option value=''>선택</option>");

	$.ajax({
	   type: "POST",
	   url : "/sts/stm/selectAjaxPsitn.do",
	   data : formData,
	   dataType:"json",
	   async : false,
	   success: function(args) {
		   $.each(args, function() {
			   $("#LOASM_CODE").append("<option value='"+this.loasmCode+"'>"+this.loasmNm+"</option>");
			});
	   }
		,error:function(e) {
			alert(e.responseText);
	   }
	});
	
} 
-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

<DIV id="page-wrapper">
	<div class="row">
    	<div class="col-lg-12">
            <h1 class="page-header">지방의회/지자체 담당자 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    
    <!-- /row -->
	<div id="border" style="display:" class="">

		<DIV class="">
			<h2>지방의회/지자체 담당자 목록</h2>
			<!--  
			<ul class="nav nav-tabs">
				<li class="">
					<a href="#LINK" onClick="javascript:fn_tabClick('MNG')" data-toggle="tab">국회직원 관리자</a>
                </li>
                <li class="active">
                	<a href="#LINK" onClick="javascript:fn_tabClick('CLIK')"  data-toggle="tab">지방의회/지자체 관리자</a>
                </li>
            </ul>	-->
			
			<!-- /.panel-heading -->			
			<DIV id="main" class="">
				<form name="listForm" action="" method="post">

				<div class="select_box">
					<span style=" display:block; width:100%; text-align:right;">
						<select name="selChargerSeCode" id="selChargerSeCode" title="관리자 여부">
							<option value=''>관리자 여부</option>
							<c:forEach var="x" items="${chargerSeCodeList}">
							<option value='${x.code}' <c:if test="${mngVO.selChargerSeCode == x.code}">selected</c:if>>${x.codeNm}</option>
							</c:forEach>
						</select>

						<select name="selConfmSttusAt" id="selConfmSttusAt" title="승인여부">
						<option value=''>승인 여부</option>
						<option value='Y' <c:if test="${mngVO.selConfmSttusAt == 'Y'}">selected</c:if>>승인</option>
						<option value='N' <c:if test="${mngVO.selConfmSttusAt == 'N'}">selected</c:if>>미승인</option>
						</select>

						<select name="searchCondition" id="searchCondition" title="검색조건">      
							<option value='selUnityId' <c:if test="${searchCondition =='selUnityId'}">selected</c:if>>아이디</option>
							<option value='selChargerNm' <c:if test="${searchCondition =='selChargerNm'}">selected</c:if>>이름</option>
							<option value='selPstnCode' <c:if test="${searchCondition =='selPstnCode'}">selected</c:if>>소속</option>
							<option value='selEmail' <c:if test="${searchCondition =='selEmail'}">selected</c:if>>이메일</option>
						</select>
						<input type='text' id='searchKeyword' name='searchKeyword' value="<c:out value='${searchKeyword}'/>" onKeyDown="javascript:if(event.keyCode==13){fnSearch();}" class="ip input-sm input-search" style="width:150px;">
						
					</span> 
					<span style=" display:block;">
						<select id="INSTT_CL_CODE" name="fInsttClCode" onchange="insttTyChange(this.value);" style="min-width:150px;">
				    		<option value="">기관선택</option>
						    <option value="INTSTTCL_000006" <c:if test="${mngVO.fInsttClCode == 'INTSTTCL_000006'}">selected="selected"</c:if>>지방정부</option>
						    <option value="INTSTTCL_000012" <c:if test="${mngVO.fInsttClCode == 'INTSTTCL_000012'}">selected="selected"</c:if>>지방의회</option>
				    	</select>
						<select id="INSTT_TY_CODE" name="insttClCode" onchange="localChange(this.value);" style="min-width:150px;">
				    		<option <c:if test="${mngVO.fInsttClCode == ''}">disabled="disabled"</c:if> value="">기관유형 선택</option>
				    		<c:forEach var="item" items="${instt_ty_code_code_list }">
				    		<option value="${item.fInsttClCode }" <c:if test="${mngVO.insttClCode == item.fInsttClCode}">selected="selected"</c:if>>${item.fInsttClCodeNm }</option>
				    		</c:forEach>
				    	</select>
				    	<select id="BRTC_CODE" name="brtcCode" onchange="psitnChange(this.value);" style="min-width:150px;">
				    		<option <c:if test="${mngVO.insttClCode == ''}">disabled="disabled"</c:if> value="">지역 선택</option>
				    		<c:forEach var="item" items="${brtc_code_list }">
				    		<option value="${item.codeId }" <c:if test="${mngVO.brtcCode == item.codeId}">selected="selected"</c:if>>${item.codeIdNm }</option>
				    		</c:forEach>
				    	</select>
				    	<select id="LOASM_CODE" name="loasmCode" style="min-width:150px;">
				    		<option <c:if test="${mngVO.brtcCode == ''}">disabled="disabled"</c:if> value="">선택</option>
				    		<c:forEach var="item" items="${loasm_code_list }">
				    		<option value="${item.loasmCode }" <c:if test="${mngVO.loasmCode == item.loasmCode}">selected="selected"</c:if>>${item.loasmNm }</option>
				    		</c:forEach>
				    	</select>
				    	
		
						<a href="#none"><button type="button" class="btn btn-primary" onclick="fnSearch();">검색</button></a> 
						<a href="#none"><button type="button" class="btn btn-success" onClick="fnExcel(); return false;">excel</button></a>
						</span>
						
				</div>
				<div class="page">
						총 관리자 수 : <c:out value="${paginationInfo.totalRecordCount}" />&nbsp;명
						<span>
						정렬 : 
						<select id="sortOrder" name="sortOrder" onchange="fnChgOrder(this.value)">
							<option value="">정렬기준</option>
							<option value="unityId_DESC" <c:if test="${mngVO.sortOrder == 'unityId_DESC'}">selected</c:if>>아이디 내림차순</option>
							<option value="unityId_ASC" <c:if test="${mngVO.sortOrder == 'unityId_ASC'}">selected</c:if>>아이디 오름차순</option>
							<option value="chargerNm_DESC" <c:if test="${mngVO.sortOrder == 'chargerNm_DESC'}">selected</c:if>>이름 내림차순</option>
							<option value="chargerNm_ASC" <c:if test="${mngVO.sortOrder == 'chargerNm_ASC'}">selected</c:if>>이름 오름차순</option>
							<option value="loasmNm_DESC" <c:if test="${mngVO.sortOrder == 'loasmNm_DESC'}">selected</c:if>>소속 내림차순</option>
							<option value="loasmNm_ASC" <c:if test="${mngVO.sortOrder == 'loasmNm_ASC'}">selected</c:if>>소속 오름차순</option>
							<option value="chargerEmail_DESC" <c:if test="${mngVO.sortOrder == 'chargerEmail_DESC'}">selected</c:if>>이메일 내림차순</option>
							<option value="chargerEmail_ASC" <c:if test="${mngVO.sortOrder == 'chargerEmail_ASC'}">selected</c:if>>이메일 오름차순</option>
							<option value="confmDate_DESC" <c:if test="${mngVO.sortOrder == 'confmDate_DESC'}">selected</c:if>>승인일 내림차순</option>
							<option value="confmDate_ASC" <c:if test="${mngVO.sortOrder == 'confmDate_ASC'}">selected</c:if>>승인일 오름차순</option>
						</select>
	
						&nbsp;&nbsp;&nbsp;
												
						출력건수 :
						<select title="쪽당출력건수" id="pageUnit" name="pageUnit" onchange="fnChgListCnt(this.value)">
							<option value='10' <c:if test="${mngVO.pageUnit == '10'}">selected</c:if>>10</option>
	                        <option value='30' <c:if test="${mngVO.pageUnit == '30'}">selected</c:if>>30</option>
	                        <option value='50' <c:if test="${mngVO.pageUnit == '50'}">selected</c:if>>50</option>
	                        <option value='100' <c:if test="${mngVO.pageUnit == '100'}">selected</c:if>>100</option>
	                    </select>	
						</span>
					</div>
			
			
					
					<table width="100%" cellpadding="9" class="table table-striped table-bordered" summary="기능 관리 테이블입니다.기능  ID,기능 명,기능 타입,기능 Sort,기능 설명,등록일자의 정보를 담고 있습니다.">
						<thead>
						<tr>
							<th class="title" width="3%" scope="col" nowrap="nowrap">번호</th>
							<th class="title" width="10%" scope="col" nowrap="nowrap">관리자</th>
							<th class="title" width="10%" scope="col" nowrap="nowrap">아이디</th>
							<th class="title" width="20%" scope="col" nowrap="nowrap">이름</th>
							<th class="title" width="10%" scope="col" nowrap="nowrap">소속</th>
							<th class="title" width="10%" scope="col" nowrap="nowrap">대표관심의회</th>
							<th class="title" width="12%" scope="col" nowrap="nowrap">전화번호</th>
							<th class="title" width="12%" scope="col" nowrap="nowrap">이메일</th>
							<th class="title" width="12%" scope="col" nowrap="nowrap">등록일</th>
							<th class="title" width="12%" scope="col" nowrap="nowrap">승인여부</th>
						</tr>
					 	</thead>

					 	<tbody>
						<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
						<c:if test="${fn:length(resultList) == 0}">
							<tr>
								<td class="lt_text3" colspan="10"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
					 
					 	<c:forEach var="x" items="${resultList}" varStatus="s">
					 	<tr>
						    <td class="lt_text3" nowrap="nowrap">${(mngVO.pageIndex-1) * mngVO.pageUnit + s.count}</td>
						    <td class="lt_text3" nowrap="nowrap">
						    	<c:forEach var="c" items="${chargerSeCodeList}">
								<c:if test="${x.chargerSeCode == c.code}">${c.codeNm}</c:if>
								</c:forEach>

						    </td>
						    <td class="lt_text" nowrap="nowrap">
						    	<a href="#" onclick="fnMngDetailView('<c:out value="${x.unityId}" />');"><c:out value="${x.unityId}" /></a>
						    </td>
						    <td class="lt_text" nowrap="nowrap"><c:out value="${x.chargerNm}" /></td>
						    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.loasmNm}" /></td>
						    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.intRasmblyInsttClCodeNm1}" /></td>
						    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.chargerTelno}" /></td>
						    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.chargerEmail}" /></td>
						    <td class="lt_text3" nowrap="nowrap"><c:out value="${x.frstRegisterPnttm}" /></td>
						    <td class="lt_text3" nowrap="nowrap">
						    	<input type="radio" name="confmSttusAt_${s.count}" onclick="fnApproval('<c:out value="${x.unityId}" />', 'Y')" value="Y" <c:if test="${x.confmSttusAt == 'Y'}">checked="checked"</c:if> />승인
						    	<input type="radio" name="confmSttusAt_${s.count}" onclick="fnApproval('<c:out value="${x.unityId}" />', 'N')" value="N" <c:if test="${x.confmSttusAt == 'N'}">checked="checked"</c:if> />&nbsp;미승인
						    </td>
					 	</tr>
					 	</c:forEach>
					 	</tbody>
					</table>
					
					
					<c:if test="${not empty resultList}">
					<div align="center">
					    <div>
					    	<ul class="pagination">
						        <ui:pagination paginationInfo = "${paginationInfo}"
						            type="image"
						            jsFunction="fn_paging"
						            />
						     </ul>
					    </div>
					</div>
					
					</c:if>
					
					<input type="hidden" name="pageIndex" value="1"/>
			        <input type="hidden" name="confmSttusAt" />
					<input type="hidden" name="unityId" />
					</form>
				
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->					
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>
