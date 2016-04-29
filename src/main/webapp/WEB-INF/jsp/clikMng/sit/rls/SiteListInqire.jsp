<%
 /**
  * @Class Name : EgovSiteListInqire.jsp
  * @Description : EgovSiteListInqire 화면
  * @Modification Information
  * @
  * @ 수정일    	수정자		수정내용
  * @ ----------    ------		---------------------------
  *
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
<title>관련사이트 목록조회</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javaScript" language="javascript">

/*********************************************************
 * 초기화
 ******************************************************** */
function fnIinitlSitelist(){

    // 첫 입력란에 포커스..
    document.listForm.searchKeyword.focus();
    
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
    
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sit/rls/SiteListInqire.do'/>";
    document.listForm.submit();
    
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fnSearchSiteinfo(){

	if($("select[name='searchCondition3']").val() != "0" && $("#searchKeyword").val() == "") {
		alert('검색어를 입력해 주세요.');
		return; 
	}

    document.listForm.pageIndex.value = 1;
    document.listForm.submit();
    
}

/*********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fnSiteinfoRegist(){

    document.listForm.action = "<c:url value='/sit/rls/SiteInfoRegistView.do'/>";
    document.listForm.submit(); 
    
}

/* ********************************************************
* 사이트 순서 변경(up) 
******************************************************** */
function fnEditUpOrdr(siteId)
{
	
    var i;
    var j;
    var strTempValue = '';

    var obj = document.getElementsByName("hiddenSiteId");

    document.listForm.siteOrdrChg.value = '';
    for(var s=0; s<obj.length; s++) {
    	if (siteId == obj[s].value) {
    		i = s;
    	} 
    }
    
    if (i > 0)
    {
        j = i - 1;
        strTempValue = obj[j].value;

        obj[j].value = obj[i].value;

        obj[i].value = strTempValue;

        document.listForm.siteOrdrChg.value = obj[i].value + "=" + (i);
        document.listForm.siteOrdrChg.value +=  "," + obj[j].value + "=" + (j);
    }
    
    if (document.listForm.siteOrdrChg.value != ''){
    	document.listForm.action = "<c:url value='/sit/rls/SiteOrdrUpdt.do'/>";     
		document.listForm.submit();	    
    }	
}

/* ********************************************************
* 사이트 순서 변경(down) 
******************************************************** */
function fnEditDownOrdr(siteId)
{
    var i;
    var j;
    var strTempValue = '';

    var obj = document.getElementsByName("hiddenSiteId");

    document.listForm.siteOrdrChg.value = '';
    for(var s=0; s<obj.length; s++) {
    	if (siteId == obj[s].value) {
    		i = s;
    	} 
    }
    
    if (i < obj.length - 1)
    {
        j = i + 1;
        strTempValue = obj[j].value;
        obj[j].value = obj[i].value;
        obj[i].value = strTempValue;
        
        document.listForm.siteOrdrChg.value = obj[i].value + "=" + (i);
        document.listForm.siteOrdrChg.value +=  "," + obj[j].value + "=" + (j);
    }
    
    if (document.listForm.siteOrdrChg.value != ''){
    	document.listForm.action = "<c:url value='/sit/rls/SiteOrdrUpdt.do'/>";     
		document.listForm.submit();	    
    }    
}

</script>

</head>
<body onLoad="fnIinitlSitelist();">

<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"> 관련사이트 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->

	<DIV id="main" style="display:" class="">
	<p class="tr">
		<button type="button" class="btn btn-primary" onclick="fnSiteinfoRegist(); return false;">등록</button>
	</p>	
		<DIV class="">
			<h2>
				 관련사이트 목록조회
			</h2>
			<!-- /.panel-heading -->	 		
			<DIV id="content" class="">
								
				<form name="listForm" action="<c:url value='/sit/rls/SiteListInqire.do'/>" method="post">
				<input type="hidden" name="siteOrdrChg" id="siteOrdrChg" />
				<div class="select_box">
					<span>
						<select name="searchCondition1" title="검색조건구분1" >
				    		<c:forEach items="${categoryList}" var="x" varStatus="status">
				    			<option value="${x.codeId}" <c:if test="${x.codeId == searchCondition1}">selected="selected"</c:if>><c:out value="${x.codeIdNm}" /></option>
				    		</c:forEach>				           
						</select>					
<!-- 						
						<select name="searchCondition2"  title="검색조건구분2">
				           <option disabled="disabled" value="0">지역 선택</option>
						</select>
 -->											
						<select name="searchCondition3" title="검색조건구분3">
				           <option value="0">전체</option>
				           <option value="siteNm"  <c:if test="${searchVO.searchCondition3 == 'siteNm'}">selected="selected"</c:if> >사이트명</option>
				           <option value="siteUrl"   <c:if test="${searchVO.searchCondition3 == 'siteUrl'}">selected="selected"</c:if> >사이트URL</option>                             
				       </select>
				       <input name="searchKeyword" id="searchKeyword" type="text" value='<c:out value="${searchVO.searchKeyword}"/>' title="검색키워드" class=" input-sm input-search" >
						<input type="submit"  class="btn btn-primary" value="<spring:message code="button.search" />" onclick="fnSearchSiteinfo(); return false;">
					</span>
				</div>
				
				
				<table width="100%" cellpadding="8" class="table table-striped table-bordered table-hover dataTable no-footer" border="0" summary="사이트정보목록 테이블">
				<thead>
				<tr>      
				    <th scope="col" class="title" width="5%" nowrap>번호</th>    
				    <th scope="col" class="title" width="10%" nowrap>카테고리</th>    
				    <th scope="col" class="title" width="15%" nowrap>지역명</th>        
				    <th scope="col" class="title" width="20%" nowrap>사이트명</th>
				    <th scope="col" class="title" width="20%" nowrap>URL</th>      
				    <th scope="col" class="title" width="15%" nowrap>순서변경</th>      
				    <th scope="col" class="title" width="15%" nowrap>등록일</th>               
				</tr>
				</thead>
				
				 <c:if test="${fn:length(resultList) == 0}">
				  <tr> 
				    <td colspan="7">
				        <spring:message code="common.nodata.msg" />
				    </td>
				  </tr>                                            
				 </c:if>
				    
				<tbody>
				<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
				  <tr>
				        <td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>         
				        <td><c:out value="${resultInfo.siteThemaClNm}"/></td>
						<td><c:out value="${resultInfo.siteThemaClDetailNm}"/></td>
				        <td><a href="<c:url value='/sit/rls/SiteInfoUpdtView.do' />?pageIndex=${searchVO.pageIndex}&amp;siteId=${resultInfo.siteId}&amp;siteThemaClCode=${resultInfo.siteThemaClCode}&amp;siteThemaClDetailCode=${resultInfo.siteThemaClDetailCode}"><c:out value="${resultInfo.siteNm}"/></td>
				        <td><c:out value="${resultInfo.siteUrl}"/></td>
				        <td>
				        	<input type="button" value="▲" onclick = "javascript:fnEditUpOrdr('${resultInfo.siteId}')" >&nbsp;
	                   		<input type="button" value="▼" onclick = "javascript:fnEditDownOrdr('${resultInfo.siteId}')" >&nbsp;<br />
	                   		<input type="hidden" name="hiddenSiteId" id="hiddenSiteId" value="${resultInfo.siteId}" />
				        </td>
				        <td><c:out value="${resultInfo.frstRegistPnttm}" /></td>     
				  </tr>   
				</c:forEach>
				</tbody>
				  
				</table>
				
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
				    <td height="3px"></td>
				</tr>
				</table>
<%-- 				
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
--%>
				<input name="siteId" type="hidden" value="">
				<input name="seOrdr" type="hidden" value="">
				<input name="type" type="hidden" value="">
				
				<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
				
				</form>
				
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->		
		<p class="tr">
		<button type="button" class="btn btn-primary" onclick="fnSiteinfoRegist(); return false;">등록</button>
		</p>						
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->
</body>
</html>

