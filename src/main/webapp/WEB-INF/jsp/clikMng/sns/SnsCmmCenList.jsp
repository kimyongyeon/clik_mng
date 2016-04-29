<%--
  Class Name : SnsCmmList.jsp
  Description : SNS소통센터 목록 페이지
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/clikmng/cmm/" />
<c:set var="CssUrl" value="${pageContext.request.contextPath}/css/clikmng/cmm/" />
<c:set var="JsUrl"  value="${pageContext.request.contextPath}/js/clikmng/sit/pwm/"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>SNS 소통센터 관리 목록조회</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************* */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/sns/SnsCmmCenList.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_search_SnsManage(){
	var vFrom = document.listForm;
	vFrom.pageIndex.value = '1';
	vFrom.action = "<c:url value='/sns/SnsCmmCenList.do' />";
	vFrom.submit();
}

/* ********************************************************
 * 상세보기
 ******************************************************** */
function fnDetailView(snsSeCode, snsAcntId){
	var vFrom = document.listForm;
	
	vFrom.snsSeCode.value = snsSeCode;
	vFrom.snsAcntId.value = snsAcntId;
	vFrom.action = "<c:url value='/sns/detailSnsCmmCen.do' />";
	vFrom.submit();
}

/* ********************************************************
*  사용여부 수정처리
******************************************************** */
function fnUseAt(snsAcntId, value){
	var varForm = document.listForm;
	varForm.snsAcntId.value = snsAcntId;
	varForm.action = "<c:url value='/sns/updateSnsUseAt.do' />";
	
	if(value == 'Y') {
		if(confirm(snsAcntId+'를(을) 사용으로 상태 변경 하시겠습니까?')){
			varForm.useAt.value = value;
			varForm.submit();
		}
	} else {
		if(confirm(snsAcntId+'를(을) 사용안함으로 상태 변경 하시겠습니까?')){
			varForm.useAt.value = value;
			varForm.submit();
		}
	}
	
}


/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
	varForm.pageIndex.value = '1';
    varForm.action = "<c:url value='/sns/SnsCmmCenList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 등록화면
******************************************************** */
function fnRegist() {
    location.href = "<c:url value='/sns/registSnsCmmCen.do'/>";
}

$(document).ready(function(){
	if("${errMsg}" != null && "${errMsg}" != ''){
		alert("${errMsg}");
	}
});
</script>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">SNS 소통센터 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
	    	
	<div id="border" style="display:" class="">

		<DIV class="">
			<h2>
				 SNS 소통관리 목록조회
			</h2>
			<!-- /.panel-heading -->			
			<DIV id="content" class="">	
				<form name="listForm" action="<c:url value='/sns/SnsCmmCenList.do'/>" method="post">
				<input name="pageIndex" type="hidden" value="<c:out value='${snsManageVO.pageIndex}'/>"/>
				<input name="snsAcntId" type="hidden" />
				<div class="select_box">
					<span>
					<select name="snsSeCode" class=""  title="검색조건선택">
						<option value=''>--선택하세요--</option>
						<option value='FB' <c:if test="${snsSeCode == 'FB'}">selected</c:if> style="display:none;">FACEBOOK</option>
						<option value='TW' <c:if test="${snsSeCode == 'TW'}">selected</c:if>>TWITTER</option>
					</select>
					<select name="useAt" class=""  title="사용여부">
						<option value=''>--사용여부--</option>
						<option value='Y' <c:if test="${useAt == 'Y'}">selected</c:if>>사용</option>
						<option value='N' <c:if test="${useAt == 'N'}">selected</c:if>>미사용</option>
					</select>
					<select name="searchCondition" class=""  title="검색조건선택">
						<option value=''>--전체--</option>
						<option value='SNS_ID' <c:if test="${searchCondition == 'SNS_ID'}">selected</c:if>>SNS계정ID</option>
						<option value='ASEMBY_NM' <c:if test="${searchCondition == 'ASEMBY_NM'}">selected</c:if>>의원명</option>
					</select>
					<input name="searchKeyword" <c:if test="${searchKeyword != null}">value='${searchKeyword }'</c:if> class=" input-sm input-search" type="text" title="검색단어입력" value="">
					<input type="submit"  class="btn btn-primary" value="<spring:message code="button.search" />" onclick="fn_search_SnsManage(); return false;">
					</span>
				</div>
				<div class="page">
					총 건수 :  <c:out value="${paginationInfo.totalRecordCount}" />건
					<span>
						출력건수
						<select name="selectCountperpg" aria-controls="dataTables-example" class=" input-sm" id="selectCountperpg"  onchange="fnChgListCnt(this.value)">
		                    <option value='10' <c:if test="${empty selectCountperpg || selectCountperpg == '' || selectCountperpg == '10'}">selected</c:if>>10</option>
		                    <option value='20' <c:if test="${selectCountperpg == '20'}">selected</c:if>>20</option>
		                    <option value='50' <c:if test="${selectCountperpg == '50'}">selected</c:if>>50</option>
		                    <option value='100' <c:if test="${selectCountperpg == '100'}">selected</c:if>>100</option>
						</select>
						&nbsp;&nbsp;정렬
						<select name="searchSort" aria-controls="dataTables-example" class=" input-sm" id="searchSort" >
		                    <option value='SNS_SE_CODE ASC' <c:if test="${searchSort == 'SNS_SE_CODE ASC'}">selected</c:if>>SNS구분코드(오름차순)</option>
		                    <option value='SNS_SE_CODE DESC' <c:if test="${searchSort == 'SNS_SE_CODE ASC'}">selected</c:if>>SNS구분코드(내림차순)</option>
		                    <option value='ASEMBY_NM ASC' <c:if test="${searchSort == 'ASEMBY_NM ASC'}">selected</c:if>>의원명(오름차순)</option>
		                    <option value='ASEMBY_NM DESC' <c:if test="${searchSort == 'ASEMBY_NM DESC'}">selected</c:if>>의원명(내림차순)</option>
		                    <option value='SNS_ACNT_ID ASC' <c:if test="${searchSort == 'SNS_ACNT_ID ASC'}">selected</c:if>>SNS계정ID(오름차순)</option>
		                    <option value='SNS_ACNT_ID DESC' <c:if test="${searchSort == 'SNS_ACNT_ID DESC'}">selected</c:if>>SNS계정ID(내림차순)</option>
						</select>
					</span>
				</div>
				</form>
				
				

				
				<table width="100%" cellpadding="0" class="table table-striped table-bordered" border="0">
				<thead>
				  <tr>
				  	<th class="title" width="10%" nowrap>번호</th>
				    <th class="title" width="20%" nowrap>SNS구분</th>
				    <th class="title" width="20%" nowrap>의원명</th>
				    <th class="title" width="20%" nowrap>SNS계정ID</th>
				    <th class="title" width="30%" nowrap>사용여부</th>
				  </tr>
				</thead>
				<tbody>
				<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
				<c:if test="${fn:length(resultList) == 0}">
				<tr>
				<td class="lt_text3" colspan="5">
					<spring:message code="common.nodata.msg" />
				</td>
				</tr>
				</c:if>			
					
				<%-- 데이터를 화면에 출력해준다 --%>
				<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
				<tr>
					<td class="lt_text3" nowrap><c:out value="${resultInfo.rnum }"></c:out></td>
				    <td class="lt_text3" nowrap><c:out value="${resultInfo.snsSeCodeNm }"></c:out></td>
				    <td class="lt_text3" nowrap><c:out value="${resultInfo.asembyNm }"></c:out></td>
				    <td class="lt_text3"><a href="LINK#" onclick="fnDetailView('<c:out value="${resultInfo.snsSeCode }" />', '<c:out value="${resultInfo.snsAcntId}" />'); return false;" ><c:out value="${resultInfo.snsAcntId}"/></a>
				    <td class="lt_text3">
				    	<input type="radio" name="useAt_${status.count}" onclick="fnUseAt('<c:out value="${resultInfo.snsAcntId}"/>', 'Y')" value="Y" <c:if test="${resultInfo.useAt == 'Y'}">checked="checked"</c:if> />사용
				    	<input type="radio" name="useAt_${status.count}" onclick="fnUseAt('<c:out value="${resultInfo.snsAcntId}"/>', 'N')" value="N" <c:if test="${resultInfo.useAt == 'N'}">checked="checked"</c:if> />&nbsp;사용안함
				    </td>
				</tr>
				</c:forEach>
				</tbody>
				</table>
				
				
				<p class="tr">
					<button type="button" class="btn btn-primary" onclick="javascript:fnRegist();"><spring:message code="button.create" /></button>
				</p>	
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
