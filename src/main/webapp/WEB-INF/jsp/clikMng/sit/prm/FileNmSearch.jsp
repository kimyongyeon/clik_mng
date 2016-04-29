<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : FileNmSearch.jsp
  * @Description : 프로그램파일명 검색 화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  *  @author 
  *  @since 
  *  @version 1.0
  *  @see
  *
  */
  /* Image Path 설정 */
  String imagePath_icon   = "/images/clikmng/sit/prm/icon/";
  String imagePath_button = "/images/clikmng/sit/prm/button/";
%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>프로그램파일명 검색</title>
<script language="javascript1.2"  type="text/javaScript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.progrmManageForm.pageIndex.value = pageNo;
	document.progrmManageForm.action = "<c:url value='/sit/prm/ProgramListSearch.do'/>";
   	document.progrmManageForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectProgramListSearch() {
	document.progrmManageForm.pageIndex.value = 1;
	document.progrmManageForm.action = "<c:url value='/sit/prm/ProgramListSearch.do'/>";
	document.progrmManageForm.submit();
}

/* ********************************************************
 * 프로그램목록 선택 처리 함수
 ******************************************************** */
function choisProgramListSearch(vFileNm) {
	//eval("opener.document.all."+opener.document.all.tmp_SearchElementName.value).value = vFileNm;
	//opener.document.menuManageVO.progrmFileNm.value = vFileNm;
	var parentFrom = opener.document.getElementsByTagName('form');
	parentFrom[0].progrmFileNm.value = vFileNm;
    window.close();
}
-->
</script>
</head>
<body>
<form name="progrmManageForm" action ="<c:url value='/sit/prm/ProgramListSearch.do'/>" method="post">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<DIV id="main" style="display:">


<table width="450" border="0" cellpadding="0" cellspacing="1" align="center">
 <tr>
  <td width="100%">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register">
      <tr>
        <th width="30%" height="40" class=""  scope="row"><label for="searchKeyword">프로그램파일명</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" ></th>
        <td width="70%">
          <table border="0" cellspacing="0" cellpadding="0" align="left">
            <tr>
              <td >&nbsp;<input name="searchKeyword" type="text" value="" title="검색조건" class="input-sm input-search"></td>
              <td width="5%"></td>
              <td><span class="button"><input type="submit" value="<spring:message code="button.search" />" onclick="selectProgramListSearch(); return false;"></span></td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
   </td>
 </tr>
</table>
<table width="450" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="10">&nbsp; </td>
  </tr>
</table>
<table width="450" cellpadding="8" class="table table-striped table-bordered table-hover dataTable no-footer" align="center" summary="프로그램파일명 검색목록으로 프로그램파일명 프로그램명으로 구성됨" >
 <thead>
  <tr>
    <th class="title" width="50%" scope="col">프로그램파일명</th>
    <th class="title" width="50%" scope="col">프로그램명</th>
  </tr>
 </thead>
 <tbody>
 <c:forEach var="result" items="${list_progrmmanage}" varStatus="status">
  <tr>
    <td class="lt_text3">
      <span class="link"><a href="#LINK" onclick="choisProgramListSearch('<c:out value="${result.progrmFileNm}"/>'); return false;">
      <c:out value="${result.progrmFileNm}"/></a></span></td>
    <td class="lt_text3"><c:out value="${result.progrmKoreanNm}"/></td>
  </tr>
 </c:forEach>
 </tbody>
 <!--tfoot>
  <tr class="">
   <td colspan=6 align="center"></td>
  </tr>
 </tfoot -->
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
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

</DIV>
</form>
</body>
</html>

