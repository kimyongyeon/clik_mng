<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovMenuCreatManage.jsp
  * @Description : 메뉴생성관리 조회 화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  *
  *  @author 
  *  @since 
  *  @version 
  *  @see
  *
  */

  /* Image Path 설정 */
  String imagePath_icon   = "/images/clikmng/mna/mcm/icon/";
  String imagePath_button = "/images/clikmng/mna/mcm/button/";
%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>메뉴생성관리</title>
<script  language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 최초조회 함수
 ******************************************************** */
function fMenuCreatManageSelect(){
    document.menuCreatManageForm.action = "<c:url value='/mna/mcm/EgovMenuCreatManageSelect.do'/>";
    document.menuCreatManageForm.submit();
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.menuCreatManageForm.pageIndex.value = pageNo;
	document.menuCreatManageForm.action = "<c:url value='/mna/mcm/EgovMenuCreatManageSelect.do'/>";
   	document.menuCreatManageForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectMenuCreatManageList() {
	document.menuCreatManageForm.pageIndex.value = 1;
    document.menuCreatManageForm.action = "<c:url value='/mna/mcm/EgovMenuCreatManageSelect.do'/>";
    document.menuCreatManageForm.submit();
}

/* ********************************************************
 * 메뉴생성 화면 호출
 ******************************************************** */
function selectMenuCreat(vAuthorCode) {
	document.menuCreatManageForm.authorCode.value = vAuthorCode;
   	document.menuCreatManageForm.action = "<c:url value='/mna/mcm/EgovMenuCreatSelect.do'/>";
   	document.menuCreatManageForm.submit();
}

-->
</script>
</head>
<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">메뉴관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
	<p class="tr">
		<!--<button type="button" class="btn btn-default" onclick="javascript:fncSelectAuthorList('1')">목록</button>-->	
	</p>    	
	<div id="border" style="display:" class="col-lg-12">
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				 메뉴생성관리
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="border" class="panel-body">
				<table border="0" width="100%">
				  <tr>
				    <td>
				<!-- ********** 여기서 부터 본문 내용 *************** -->
				
				<form name="menuCreatManageForm" action ="<c:url value='/mna/mcm/EgovMenuCreatManageSelect.do'/>" method="post">
				<input name="checkedMenuNoForDel" type="hidden" />
				<input name="authorCode"          type="hidden" />
				<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
				<div id="main" style="display:">
				

				<table width="100%" border="0" cellpadding="0" cellspacing="1">
				 <tr>
				  <td width="100%">
				    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register" summary="메뉴생성관리 검색조건" >
				      <tr>
				        <th width="20%" height="40" class="" scope="row"><label for="searchKeyword">권한코드&nbsp;</label></th>
				        <td width="80%">
				          <table border="0" cellspacing="0" cellpadding="0" align="left">
				            <tr>
				              <td >&nbsp;<input name="searchKeyword" type="text" size="80" value=""  maxlength="60" title="검색조건"/></td>
				              <td width="5%"></td>
							  <td><span class="button"><input type="submit" value="<spring:message code="button.inquire" />" onclick="selectMenuCreatManageList(); return false;"/></span></td>
				            </tr>
				          </table>
				        </td>
				      </tr>
				    </table>
				   </td>
				 </tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td height="10">&nbsp; </td>
				  </tr>
				</table>
				<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="메뉴생성관리  목록화면으로 권한코드, 권한명, 권한설명, 메뉴생성여부, 메뉴생성으로 구성됨" >
				 <thead>
				  <tr>
				    <th class="title" width="20%" scope="col">권한코드</th>
				    <th class="title" width="20%" scope="col">권한명</th>
				    <th class="title" width="20%" scope="col">권한 설명</th>
				    <th class="title" width="20%" scope="col">메뉴생성여부</th>
				    <th class="title" width="20%" scope="col">메뉴생성</th>
				</tr>
				 </thead>
				 <tbody>
				
				
				 <c:forEach var="result" items="${list_menumanage}" varStatus="status">
				  <tr>
				    <td class="lt_text3" style="cursor:hand;" ><c:out value="${result.authorCode}"/></td>
				    <td class="lt_text3" style="cursor:hand;" ><c:out value="${result.authorNm}"/></td>
				    <td class="lt_text3" style="cursor:hand;" ><c:out value="${result.authorDc}"/></td>
				    <td class="lt_text3" style="cursor:hand;" >
				          <c:if test="${result.chkYeoBu > 0}">Y</c:if>
				          <c:if test="${result.chkYeoBu == 0}">N</c:if>
				    </td>
				    <td class="lt_text3" >
				       <table border="0" cellspacing="0" cellpadding="0" align="center">
				         <tr>
				           <td width="10"></td>
				    	   <td><span class="button"><a href="<c:url value='/mna/mcm/EgovMenuCreatSelect.do'/>?authorCode='<c:out value="${result.authorCode}"/>'"  onclick="selectMenuCreat('<c:out value="${result.authorCode}"/>'); return false;">메뉴생성</a></span></td>
				           <td width="10"></td>
				         </tr>
				       </table>
				    </td>
				  </tr>
				 </c:forEach>
				 </tbody>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td height="10"></td>
				  </tr>
				  <tr>
				    <td height="10">
				<!-- 페이징 시작 -->
				<div align="center">
				  <div>
				  	<ul class="pagination">
						<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="linkPage"/>
					</ul>
				  </div>
				</div>
				<!-- 페이징 끝 -->
				    </td>
				  </tr>
				</table>
				
				</div>
				<input type="hidden" name="req_menuNo">
				</form>
				<!-- ********** 여기까지 내용 *************** -->
				</td>
				</tr>
				</table>
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

