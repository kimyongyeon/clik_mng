<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovBoardUseInfInqire.jsp
  * @Description : 게시판  사용정보  조회화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  *
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
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="boardUseInf" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_updt_bbsUseInf(){
		if (!validateBoardUseInf(document.boardUseInf)){
			return;
		}

		document.boardUseInf.action = "<c:url value='/cop/com/updateBBSUseInf.do'/>";
		document.boardUseInf.submit();
	}
	function fn_egov_select_bbsUseInfs(){
		document.boardUseInf.action = "<c:url value='/cop/com/selectBBSUseInfs.do'/>";
		document.boardUseInf.submit();
	}

</script>
<title>게시판 사용정보 수정</title>

</head>
<body>
<jsp:include page="/cmm/top/top.do" />
<form name="boardUseInf" method="post" action="<c:url value='/cop/com/updateBBSUseInf.do'/>">
<div style="visibility:hidden;display:none;">
<input name="iptSubmit" type="submit" value="전송" title="전송"></div>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
<input type="hidden" name="bbsId" value="<c:out value='${bdUseVO.bbsId}'/>" />
<input type="hidden" name="trgetId" value="<c:out value='${bdUseVO.trgetId}'/>" />

<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">게시판 사용정보 조회</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->
	<p class="tr">
		<button type="button" class="btn btn-default" onclick="fn_egov_select_bbsUseInfs(); return false;">목록</button>
		<button type="button" class="btn btn-success" onclick="fn_egov_updt_bbsUseInf(); return false;">수정</button>	
	</p>
	<DIV id="main" style="display:" class="col-lg-12">
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				 게시판 사용정보 수정
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="border" class="panel-body">
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover dataTable no-footer"  summary="게시판명, 커뮤니티/ 동호회명, 사용여부  입니다" >
				  <tr>
				    <th scope="col" width="20%" height="23" class="" nowrap >게시판명</th>
				    <td width="80%" nowrap colspan="3">
				    <c:out value="${bdUseVO.bbsNm}" />
				    </td>
				  </tr>
				  <!--  
				  <tr>
				    <th scope="col" width="20%" height="23" class="" nowrap >커뮤니티/ 동호회명</th>
				    <td width="80%" nowrap colspan="3">
				    <c:choose>
				    	<c:when test="${not empty bdUseVO.cmmntyNm}">
				    		<c:out value="${bdUseVO.cmmntyNm}" />
				    	</c:when>
				    	<c:when test="${not empty bdUseVO.clbNm}">
			   				<c:out value="${bdUseVO.clbNm}" />
			   			</c:when>
			   			<c:otherwise>(시스템  활용)</c:otherwise>
					</c:choose>
				    </td>
				  </tr>
				  -->
				  <tr>
				    <th scope="col" width="20%" height="23" class="required_text" nowrap >
				    	<label for="useAt">
				    		사용여부
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="80%" nowrap colspan="3">
				     	<spring:message code="button.use" /> : <input type="radio" name="useAt" class="radio2" value="Y" <c:if test="${bdUseVO.useAt == 'Y'}"> checked="checked"</c:if>>&nbsp;
				     	<spring:message code="button.notUsed" /> : <input type="radio" name="useAt" class="radio2" value="N" <c:if test="${bdUseVO.useAt == 'N'}"> checked="checked"</c:if>>
				     	<br/><form:errors path="useAt" />
				    </td>
			
				  </tr>
				  <c:choose>
				  <c:when test="${not empty bdUseVO.provdUrl}">
				  <tr>
				    <th width="20%" height="23" class="" nowrap >제공 URL</th>
				    <td width="80%" nowrap colspan="3">
				    	<a href="<c:out value="${bdUseVO.provdUrl}" />" target="_new">
				    	   	<c:out value="${bdUseVO.provdUrl}" />
						</a>
				    </td>
				  </tr>
				  </c:when>
				  </c:choose>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td height="10"></td>
				  </tr>
				</table>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->
		<p class="tr">
			<button type="button" class="btn btn-default" onclick="fn_egov_select_bbsUseInfs(); return false;">목록</button>
			<button type="button" class="btn btn-success" onclick="fn_egov_updt_bbsUseInf(); return false;">수정</button>	
		</p>									
	</DIV>		
	<!-- /.MAIN -->		
</DIV>					
</form>
</body>
</html>
