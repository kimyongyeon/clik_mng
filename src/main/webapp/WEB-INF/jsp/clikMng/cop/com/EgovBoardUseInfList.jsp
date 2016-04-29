<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovBoardUseInfList.jsp
  * @Description : 게시판  사용정보  목록화면
  * @Modification Information
  * @
  * @  수정일     	     수정자            		수정내용
  * @ -------     --------    ---------------------------
  *  @author 
  *  @since 
  *  @version
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_bbsUseInfs('1');
		}
	}

	function fn_egov_select_bbsUseInfs(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/com/selectBBSUseInfs.do'/>";
		document.frm.submit();
	}
	function fn_egov_insert_addbbsUseInf(){
		document.frm.action = "<c:url value='/cop/com/addBBSUseInf.do'/>";
		document.frm.submit();
	}
	function fn_egov_select_bbsUseInf(bbsId, trgetId){
		document.frm.bbsId.value = bbsId;
		document.frm.trgetId.value = trgetId;
		document.frm.action = "<c:url value='/cop/com/selectBBSUseInf.do'/>";
		document.frm.submit();
	}

</script>
<title>게시판 사용정보 조회</title>
</head>
<body>
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">게시판 사용정보 조회</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->
	<p class="tr">
		<button type="button" class="btn btn-primary" onclick="fn_egov_insert_addbbsUseInf(); return false;">등록</button>	
	</p>
	<DIV id="main" style="display:" class="col-lg-12">
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				 게시판 사용정보 조회
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="border" class="panel-body">
			
			<form name="frm" method="post" action = "<c:url value='/cop/com/selectBBSUseInf.do'/>">
			<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
			<input type="hidden" name="bbsId" >
			<input type="hidden" name="trgetId" >
			
				<table width="100%" cellpadding="8" class="table-search" border="0">
				 <tr>

				  <td width="45%" >
					   <select name="searchCnd" class="form-control"  title="선택">
						   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >게시판명</option>
						   <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >사용 커뮤니티명</option>
						   <option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >사용 동호회명</option>
					   </select>				  
				  </td>
				  <td width="45%">
				    	<input name="searchWrd" type="text" size="35" title="검색단어입력" value='<c:out value="${searchVO.searchWrd}" />'  maxlength="35" onkeypress="press(event);" class="form-control span1" >
				  </td>
				  <th width="10%">
				   <table border="0" cellspacing="0" cellpadding="0">
				    <tr>
				      <td><span class="button">
				      <a href="<c:url value='/cop/com/selectBBSUseInfs.do'/>" onclick="fn_egov_select_bbsUseInfs('1'); return false;">조회</a>
				      </span></td>
				      <td>&nbsp;&nbsp;</td>
				    </tr>
				   </table>
				  </th>
				 </tr>
				</table>
			
				<table width="100%" cellpadding="8" class="table table-striped table-bordered table-hover dataTable no-footer"  summary="번호,게시판명,사용 커뮤니티 명,사용 동호회 명,등록일시,사용여부   목록입니다">
				 <thead>
				  <tr>
				    <!-- th class="title" width="3%" nowrap><input type="checkbox" name="all_check" class="check2"></th-->
				    <th scope="col" class="title" width="10%" nowrap>번호</th>
			
			
				    <c:choose>
				    	<c:when test="${useCommunity == 'true'}">
				    		<th scope="col" class="title" width="30%" nowrap>게시판명</th>
				    		<!--  
						    <th scope="col" class="title" width="20%" nowrap>사용 커뮤니티 명</th>
						    <th scope="col" class="title" width="15%" nowrap>사용 동호회 명</th>
						    -->
				    	</c:when>
				    	<c:otherwise>
				    		<th scope="col" class="title" width="65%" nowrap>게시판명</th>
				    	</c:otherwise>
				    </c:choose>
			
				    <th scope="col" class="title" width="15%" nowrap>등록일시</th>
				    <th scope="col" class="title" width="7%" nowrap>사용여부</th>
				  </tr>
				 </thead>
				 <tbody>
				 <c:forEach var="result" items="${resultList}" varStatus="status">
				 	<tr>
				    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
				    <!-- 2011.09.15 -->
				    <c:choose>
				    	<c:when test="${useCommunity == 'true'}">
				    		<td class="lt_text" nowrap>
			
						    	<!-- 2010.11.1
						    	<form name="item" method="post" action="<c:url value='/cop/com/selectBBSUseInf.do'/>"  target="_blank">
						        	<input type=hidden name="bbsId" value="<c:out value="${result.bbsId}"/>">
						        	<input type=hidden name="trgetId" value="<c:out value="${result.trgetId}"/>">
						            <span class="link"><input type="submit" value="<c:out value="${result.bbsNm}"/>" onclick="fn_egov_select_bbsUseInf('<c:out value="${result.bbsId}"/>','<c:out value="${result.trgetId}"/>'); return false;"></span>
								</form>
								-->
			
					        	<input type=hidden name="bbsId" value="<c:out value="${result.bbsId}"/>">
					        	<input type=hidden name="trgetId" value="<c:out value="${result.trgetId}"/>">
			
								<a href="<c:url value='/cop/com/selectBBSUseInf.do'/>?bbsId=<c:out value='${result.bbsId}'/>&amp;trgetId=<c:out value='${result.trgetId}'/>" onclick="">
									<c:out value="${result.bbsNm}"/>
								</a>
			
						    </td>
						    <!--  
						    <td class="lt_text3" nowrap><c:out value="${result.cmmntyNm}"/></td>
						    <td class="lt_text3" nowrap><c:out value="${result.clbNm}"/></td>
						    -->
				    	</c:when>
				    	<c:otherwise>
				    		<td class="lt_text" nowrap>
			
					        	<input type=hidden name="bbsId" value="<c:out value="${result.bbsId}"/>">
					        	<input type=hidden name="trgetId" value="<c:out value="${result.trgetId}"/>">
			
								<a href="<c:url value='/cop/com/selectBBSUseInf.do'/>?bbsId=<c:out value='${result.bbsId}'/>&amp;trgetId=<c:out value='${result.trgetId}'/>" onclick="">
									<c:out value="${result.bbsNm}"/>
								</a>
			
						    </td>
				    	</c:otherwise>
				    </c:choose>
			
			
			
				    <td class="lt_text3" nowrap><c:out value="${result.frstRegisterPnttm}"/></td>
				    <td class="lt_text3" nowrap>
				    	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
				    	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
				    </td>
				  </tr>
				 </c:forEach>
					 <c:if test="${fn:length(resultList) == 0}">
					  <tr>
					    <td class="lt_text3" nowrap colspan="6" ><spring:message code="common.nodata.msg" /></td>
					  </tr>
					 </c:if>
				 </tbody>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td height="10"></td>
				  </tr>
				</table>
				<div align="center">
					<ul class="pagination">
						<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_select_bbsUseInfs" />
					</ul>
				</div>
				<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
				</form>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->							
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
</body>
</html>
