<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="clikmng.nanet.go.kr.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="/images/clikmng/cop/bbs/"/>
<%
 /**
  * @Class Name : NoticeList.jsp
  * @Description : 게시물 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 
  * @ 
  *
  *
  */
%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/clikmng/cmm/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/cmm/button.css' />" rel="stylesheet" type="text/css">

<!-- <link href="<c:url value='/css/clikmng/bootstrap.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/jquery-ui.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/sb-admin.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/font-awesome.css' />" rel="stylesheet" type="text/css"> -->

<link href="<c:url value='${brdMstrVO.tmplatCours}' />" rel="stylesheet" type="text/css">
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript" src="<c:url value='/js/clikmng/cop/bbs/EgovBBSMng.js' />" ></script>
<c:choose>
<c:when test="${preview == 'true'}">
<script type="text/javascript">
<!--
	function press(event) {
	}

	function fn_egov_addNotice() {
	}

	function fn_egov_select_noticeList(pageNo) {
	}

	function fn_egov_inqire_notice(nttId, bbsId) {
	}
//-->
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
<!--
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_noticeList('1');
		}
	}

	function fn_egov_addNotice() {
		document.frm.action = "<c:url value='/cop/bbs${prefix}/addBoardArticle.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_noticeList(pageNo) {
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/bbs${prefix}/selectBoardList.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_notice(i, nttId, bbsId) {
		document.subForm.nttId.value = nttId;
		document.subForm.bbsId.value = bbsId;
		document.subForm.action = "<c:url value='/cop/bbs${prefix}/selectBoardArticle.do'/>";
		document.subForm.submit();
	}
	
	function fn_egov_downFile(atchFileId, fileSn){
		window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
	}
//-->
</script>
</c:otherwise>
</c:choose>
<title><c:out value="${brdMstrVO.bbsNm}"/></title>

<style type="text/css">
	body.popup h1{ margin-left:10px; font-size:18px; font-weight:bold; margin:20px;}

</style>

</head>
<body class="popup">



<form name="frm" action ="<c:url value='/cop/bbs${prefix}/selectBoardList.do'/>" method="post">
<input type="hidden" name="bbsId" value="<c:out value='${boardVO.bbsId}'/>" />
<input type="hidden" name="nttId"  value="0" />
<input type="hidden" name="bbsTyCode" value="<c:out value='${brdMstrVO.bbsTyCode}'/>" />
<input type="hidden" name="bbsAttrbCode" value="<c:out value='${brdMstrVO.bbsAttrbCode}'/>" />
<input type="hidden" name="authFlag" value="<c:out value='${brdMstrVO.authFlag}'/>" />
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<h1><c:out value="${brdMstrVO.bbsNm}"/></h1>
 
 <!-- <div class="search">
 	<select name="searchCnd" class="select" title="검색조건선택">
		   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >제목</option>
		   <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >내용</option>
		   <c:if test="${anonymous != 'true'}">
		   <option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >작성자</option>
		   </c:if>
	</select>
	<input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">
 	<input type="submit" value="<spring:message code="button.inquire" />" onclick="fn_egov_select_noticeList('1'); return false;"  class="btn btn-primary"  >
 	<c:if test="${brdMstrVO.authFlag == 'Y'}">
	      <a href="<c:url value='/cop/bbs${prefix}/addBoardArticle.do'/>" onClick="javascript:fn_egov_addNotice(); return false;">
	      <button type="button" class="btn btn-primary" ><spring:message code="button.create" /></button>
	      </a>
      </c:if>
 </div>  --> 
 
<div class="search">
	<label for="searchCnd" class="hide">검색항목선택</label>
	<select name="searchCnd" id="searchCnd" class="select" title="검색조건선택">
		   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >제목</option>
		   <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >내용</option>
		   <c:if test="${anonymous != 'true'}">
		   <option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >작성자</option>
		   </c:if>
	</select>
	<input name="searchWrd" type="text" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" class="ip" title="검색어 입력창" />
 	<input type="image" src="/images/clikmng/board/btn_search.gif" alt="<spring:message code="button.inquire" />" onclick="fn_egov_select_noticeList('1'); return false;"   />
	

</div><!--//search-->
 		
  	

 </form>

<table width="100%" cellpadding="8" class="boardlist" summary="번호, 제목, 게시시작일, 게시종료일, 작성자, 작성일, 조회수   입니다">
 <thead>
  <tr>
    <!-- th class="title" width="3%" nowrap><input type="checkbox" name="all_check" class="check2"></th-->
    <th scope="col" class="num m_hide" nowrap>번호</th>
    <th scope="col" nowrap>제목</th>
   	<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
	    <th scope="col" class="listTitle" nowrap>게시시작일</th>
	    <th scope="col" class="listTitle" nowrap>게시종료일</th>
   	</c:if>
   	<c:if test="${anonymous != 'true'}">
    	<th scope="col" class="name m_hide" nowrap>작성자</th>
    </c:if>
    <th scope="col" class="data m_hide" nowrap>작성일</th>
    <th scope="col" class="file">첨부</th>
    <th scope="col" class="hit m_hide" nowrap>조회수</th>
  </tr>
 </thead>

 <tbody>
	 <c:forEach var="result" items="${resultList}" varStatus="status">
	  <tr>
	    <!--td class="lt_text3" nowrap><input type="checkbox" name="check1" class="check2"></td-->
	    <td class="num m_hide" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
	    <td class="tit" nowrap>
	    	<c:choose>
	    		<c:when test="${result.isExpired=='Y' || result.useAt == 'N'}">
			    	<c:if test="${result.replyLc!=0}">
			    		<c:forEach begin="0" end="${result.replyLc}" step="1">
			    			&nbsp;
			    		</c:forEach>
			    		<img src="<c:url value='/images/clikmng/cmm/icon/reply_arrow.gif'/>" alt="reply arrow">
			    	</c:if>
	    			<c:out value="${result.nttSj}" />
	    		</c:when>
	    		<c:otherwise>
		    		<form name="subForm" method="post" action="<c:url value='/cop/bbs${prefix}/selectBoardArticle.do'/>">
						<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" />
						<input type="hidden" name="nttId"  value="<c:out value="${result.nttId}"/>" />
						<input type="hidden" name="bbsTyCode" value="<c:out value='${brdMstrVO.bbsTyCode}'/>" />
						<input type="hidden" name="bbsAttrbCode" value="<c:out value='${brdMstrVO.bbsAttrbCode}'/>" />
						<input type="hidden" name="authFlag" value="<c:out value='${brdMstrVO.authFlag}'/>" />
						<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
				    	<c:if test="${result.replyLc!=0}">
				    		<c:forEach begin="0" end="${result.replyLc}" step="1">
				    			&nbsp;
				    		</c:forEach>
				    		<img src="<c:url value='/images/clikmng/cmm/icon/reply_arrow.gif'/>" alt="reply arrow">
				    	</c:if>

			    		<c:choose>
				    		<c:when test="${preview == 'true'}">
				    			<c:out value="${result.nttSj}"/>
				    		</c:when>
				    		<c:otherwise>
					    		<span class="link">
				    				<input type="submit" style="width:320px;border:solid 0px black;text-align:left;" value="<c:out value="${result.nttSj}"/>" >
				    			</span>
				    		</c:otherwise>
				    	</c:choose>
			    			
			    	</form>
	    		</c:otherwise>
	    	</c:choose>
	    </td>
    	<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
		    <td class="listCenter" nowrap><c:out value="${result.ntceBgnde}"/></td>
		    <td class="listCenter" nowrap><c:out value="${result.ntceEndde}"/></td>
    	</c:if>
    	<c:if test="${anonymous != 'true'}">
	    	<td class="name" nowrap><c:out value="${result.frstRegisterNm}"/></td>
	    </c:if>
	    <td class="data m_hide"  nowrap><c:out value="${result.frstRegisterPnttm}"/></td>
	    <td class="file">
			<c:if test="${result.atchFileSn != ''}">
			<a href="javascript:fn_egov_downFile('<c:out value="${fn:trim(result.atchFileId)}"/>','<c:out value="${result.atchFileSn}"/>')">
				<img src="/images/clikmng/board/btn_file.gif" alt="파일" />
	    	</a>
	    	</c:if>
	    </td>
	    <td class="hit m_hide" nowrap><c:out value="${result.inqireCo}"/></td>
	  </tr>
	 </c:forEach>
	 <c:if test="${fn:length(resultList) == 0}">
	  <tr>
    	<c:choose>
    		<c:when test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
    			<td class="listCenter" colspan="8" ><spring:message code="common.nodata.msg" /></td>
    		</c:when>
    		<c:otherwise>
    			<c:choose>
    				<c:when test="${anonymous == 'true'}">
		    			<td class="listCenter" colspan="4" ><spring:message code="common.nodata.msg" /></td>
		    		</c:when>
		    		<c:otherwise>
		    			<td class="listCenter" colspan="6" ><spring:message code="common.nodata.msg" /></td>
		    		</c:otherwise>
		    	</c:choose>
    		</c:otherwise>
    	</c:choose>
 		  </tr>
	 </c:if>
 </tbody>
</table>

<div class="search">
<c:if test="${brdMstrVO.authFlag == 'Y'}">
    <input type="image" src="/images/clikmng/board/btn_write.gif" alt="<spring:message code="button.create" />" onclick="javascript:fn_egov_addNotice(); return false;"   />
</c:if>
</div>

<div align="center">
	<ul class="pagination">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_noticeList" />
	</ul>
</div>


</body>
</html>
