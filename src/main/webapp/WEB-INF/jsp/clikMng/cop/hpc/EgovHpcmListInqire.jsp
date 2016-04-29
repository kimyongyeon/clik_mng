<%
 /**
  * @Class Name : EgovHpcmListInqire.jsp
  * @Description : EgovHpcmListInqire 화면
  * @Modification Information
  * @
  * @  수정일   	수정자		수정내용
  * @ ----------	------		----------------------------------------------
  *  @author  
  *  @since 
  *  @version 
  *  @see
  *  
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>도움말 목록조회</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javaScript" language="javascript">

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_hpcmlist(){

	// 첫 입력란에 포커스..
	document.HpcmListForm.searchKeyword.focus();
	
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	
	document.HpcmListForm.pageIndex.value = pageNo;
	document.HpcmListForm.action = "<c:url value='/cop/hpc/HpcmListInqire.do'/>";
   	document.HpcmListForm.submit();
   	
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_hpcmcn(){

	document.HpcmListForm.pageIndex.value = 1;
	document.HpcmListForm.submit();
	
}

/*********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_hpcmcn(){

	document.HpcmListForm.action = "<c:url value='/cop/hpc/HpcmCnRegistView.do'/>";
	document.HpcmListForm.submit();	
	
}

/*********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_updt_hpcmlist(){

	document.HpcmListForm.action = "<c:url value='/uss/ion/rec/HpcmCnUpdtView.do'/>";
	document.HpcmListForm.submit();	

}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_hpcmdetail(hpcmId, pageIndex) {		

	// 도움말 키값(hpcmId) 셋팅.
	document.HpcmListForm.hpcmId.value = hpcmId;
	document.HpcmListForm.pageIndex.value = pageIndex;
  	document.HpcmListForm.action = "<c:url value='/cop/hpc/HpcmDetailInqire.do'/>";
  	document.HpcmListForm.submit();	
	   	   		
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.HpcmListForm;
	varForm.pageIndex.value = '1';
	varForm.pageUnit.value = val;
    varForm.action = "<c:url value='/cop/hpc/HpcmListInqire.do'/>";
    varForm.submit();
}



</script>
</head>
<body onLoad="fn_egov_initl_hpcmlist();">

<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">도움말 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->

			<DIV class="panel-heading">
				 도움말 목록조회
			</DIV>

				<form name="HpcmListForm" action="<c:url value='/cop/hpc/HpcmListInqire.do'/>" method="post">
				<div class="select_box">
					<span>
						<select name="searchCondition" class="" title="조회조건 선택">
						   <option selected value='0'>전체</option>
						   <option value="hpcmSeCodeNm"  <c:if test="${searchVO.searchCondition == 'hpcmSeCodeNm'}">selected="selected"</c:if> >도움말 구분</option>
						   <option value="hpcmDf"  <c:if test="${searchVO.searchCondition == 'hpcmDf'}">selected="selected"</c:if> >도움말 정의</option>			   		   
					   </select>
					   <input name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>' title="검색어 입력" class=" input-sm input-search" > 
					   <input type="button" value="<spring:message code="button.search" />"  class="btn btn-primary" onclick="fn_egov_search_hpcmcn(); return false;">
					</span>
				</div><!-- select_box -->

				
				<div class="page">
					총 건수 :  <c:out value="${paginationInfo.totalRecordCount}" />건
	
					<span>
						출력건수
						<select id="pageUnit" name="pageUnit" class="input-sm" onchange="fnChgListCnt(this.value);">
							<option value="10" <c:if test="${searchVO.pageUnit == '10' }">selected</c:if>>10</option>
							<option value="30" <c:if test="${searchVO.pageUnit == '30' }">selected</c:if>>30</option>
							<option value="50" <c:if test="${searchVO.pageUnit == '50' }">selected</c:if>>50</option>
							<option value="100" <c:if test="${searchVO.pageUnit == '100' }">selected</c:if>>100</option>
						</select>
					</span>
				
				</div>	
				
				<input name="hpcmId" type="hidden" value="">
				<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
				
				
				
				
				
				
				</form>
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<table width="98%" cellpadding="8" class="table table-striped table-bordered" border="0" summary="도움말에 대한 목록을 제공합니다.">
				<thead>
				<tr>      
				    <th scope="col" class="title" width="10%" nowrap>번호</th>        
				    <th scope="col" class="title" width="25%" nowrap>도움말 구분</th>        
				    <th scope="col" class="title" width="50%" nowrap>도움말 정의</th>                   
				    <th scope="col" class="title" width="15%" nowrap>등록일자</th>               
				</tr>
				</thead>
				
				 <c:if test="${fn:length(resultList) == 0}">
				  <tr> 
				  	<td class="lt_text3" colspan=10>
				  		<spring:message code="common.nodata.msg" />
				  	</td>
				  </tr>   	          				 			   
				 </c:if>
				    
				<tbody>      
				<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
				  <tr>
						<td class="lt_text3"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>	        
						<td class="lt_text3"><c:out value="${resultInfo.hpcmSeCodeNm}"/></td>		
						<td class="lt_text3">
					    	<a href="#LINK" onClick="fn_egov_inquire_hpcmdetail('<c:out value="${resultInfo.hpcmId}"/>', '<c:out value='${searchVO.pageIndex}'/>'); return false;"><c:out value="${resultInfo.hpcmDf}"/></a>
						</td>
						<td class="lt_text3"><c:out value="${resultInfo.lastUpdusrPnttm}"/></td>			
				  </tr>   
				</c:forEach>
				</tbody>  
				</table>
				<p class="tr">
					<button type="button" class="btn btn-primary" onclick="fn_egov_regist_hpcmcn(); return false;"><spring:message code="button.create" /></button>
					<!--  
					<button type="button" class="btn btn-danger"  onclick="javascript:fncRoleDelete()">삭제</button>
					-->
				</p> 					
				
				
				<div align="center">
					<div>
						<ul class="pagination">
							<ui:pagination paginationInfo = "${paginationInfo}"
								type="image"
								jsFunction="fn_egov_select_linkPage"
								/>
						</ul>
					</div>
				</div>
				
	
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>
