<%
 /**
  * @Class Name : EgovFaqListInqire.jsp
  * @Description : EgovFaqListInqire 화면
  * @Modification Information
  * @
  * @  수정일   	수정자		수정내용
  * @ ----------	------		--------------------------------------------------------------
  *  @author  
  *  @since 
  *  @version 
  *  @see
  *  
  **/
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
<title>FAQ 목록조회</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javaScript" language="javascript">

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_faqlist(){

	// 첫 입력란에 포커스..
	document.FaqListForm.searchKeyword.focus();
	
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	
	document.FaqListForm.pageIndex.value = pageNo;
	document.FaqListForm.action = "<c:url value='/cop/faq/FaqListInqire.do'/>";
   	document.FaqListForm.submit();
   	
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_faq(){

	document.FaqListForm.pageIndex.value = 1;
	document.FaqListForm.submit();
	
}

/*********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_faq(){

	document.FaqListForm.action = "<c:url value='/cop/faq/FaqCnRegistView.do'/>";
	document.FaqListForm.submit();	
	
}

/*********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_updt_faqlist(){

	document.FaqListForm.action = "<c:url value='/cop/faq/FaqCnUpdtView.do'/>";
	document.FaqListForm.submit();	

}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_faqlistdetail(faqId) {		

	// Faqid
	document.FaqListForm.faqId.value = faqId;	
//  document.FaqListForm.action = "<c:url value='/cop/faq/FaqListDetailInqire.do'/>";
  	document.FaqListForm.action = "<c:url value='/cop/faq/FaqInqireCoUpdt.do'/>";  	
  	document.FaqListForm.submit();	
	   	   		
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.FaqListForm;
	varForm.pageIndex.value = '1';
	varForm.pageUnit.value = val;
    varForm.action = "<c:url value='/cop/faq/FaqListInqire.do'/>";
    varForm.submit();
}


</script>
</head>
<body>
<jsp:include page="/cmm/top/top.do" />
<form name="FaqListForm" action="<c:url value='/cop/faq/FaqListInqire.do'/>" method="POST">
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">FAQ 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
   	

			<DIV class="panel-heading">
				 FAQ 목록조회
			</DIV>
			<!-- /.panel-heading -->			
	

				<div class="select_box">
					<span>
						<select name="searchCondition" class="" title="조회조건 선택">
						   <option value='0' >전체</option>
						   <option value="qestnSj"  <c:if test="${searchVO.searchCondition == 'qestnSj'}">selected="selected"</c:if> >질문제목</option>			   
					      <option value="qestnCn"  <c:if test="${searchVO.searchCondition == 'qestnCn'}">selected="selected"</c:if> >질문내용</option>			   
					      <option value="answerCn"  <c:if test="${searchVO.searchCondition == 'answerCn'}">selected="selected"</c:if> >답변내용</option>			   
					   
					   </select>
					   <input name="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>' title="검색어 입력" class=" input-sm input-search">
					   <input type="button" value="<spring:message code="button.search" />"  class="btn btn-primary" onclick="javascript:fn_egov_search_faq(); return false;">
					</span>
				</div>
				

				
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
				<input name="faqId" type="hidden" value="">
				<input type="hidden" name="pageIndex" value="<c:if test="${empty searchVO.pageIndex }">1</c:if><c:if test="${!empty searchVO.pageIndex }"><c:out value='${searchVO.pageIndex}'/></c:if>">
		
				
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<table width="100%" cellpadding="8" class="table table-striped table-bordered" border="0" summary="FAQ에 대한 목록을 제공합니다.">
				<thead>
				<tr>      
				    <th scope="col" class="title" width="15%" nowrap>번호</th>        
				    <th scope="col" class="title" width="55" nowrap>질문제목</th>                   
				    <th scope="col" class="title" width="15%" nowrap>조회수</th>        
				    <th scope="col" class="title" width="15%" nowrap>등록일자</th>               
				</tr>
				</thead>
				
				 <c:if test="${fn:length(resultList) == 0}">
				  <tr> 
				  	<td class="lt_text3" colspan="4">
				  		<spring:message code="common.nodata.msg" />
				  	</td>
				  </tr>   	          				 			   
				 </c:if>
				    
				<tbody>      
				<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
				  <tr>
						<td class="lt_text3"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>	        
						<td class="listLeft">
					    	<span class="link">
					    		<a href="<c:url value='/cop/faq/FaqCnUpdtView.do'/>?faqId=${resultInfo.faqId}&pageIndex=${searchVO.pageIndex}"><c:out value="${resultInfo.qestnSj}"/></a>
					    	</span>
						</td>		
						<td class="lt_text3"><c:out value="${resultInfo.inqireCo}"/></td>		
						<td class="lt_text3"><c:out value="${resultInfo.lastUpdusrPnttm}"/></td>			
				  </tr>   
				</c:forEach>
				</tbody>  
				</table>
				
								
				<p class="tr">
					<button type="button" class="btn btn-primary" onclick="fn_egov_regist_faq(); return false;"><spring:message code="button.create" /></button>
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
</form>		
<!-- /page-wrapper -->	
</body>
</html>
