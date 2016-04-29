<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovNoticeInqire.jsp
  * @Description : 게시물 조회 화면
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/clikmng/cmm/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='${brdMstrVO.tmplatCours}' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/clikmng/cop/bbs/EgovBBSMng.js' />"></script>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript">
	function onloading() {
		if ("<c:out value='${msg}'/>" != "") {
			alert("<c:out value='${msg}'/>");
		}
	}

	function fn_egov_select_noticeList(pageNo) {
		document.frm.nttSj.value.value = encodeURIComponent(document.frm.nttSj.value);
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/bbs${prefix}/selectBoardList.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_notice(nttId) {
		document.frm.nttId.value = nttId;
		document.frm.submit();
	}
	
	function fn_egov_delete_notice() {
		if ("<c:out value='${anonymous}'/>" == "true" && document.frm.password.value == '') {
			alert('등록시 사용한 패스워드를 입력해 주세요.');
			document.frm.password.focus();
			return;
		}

		if (confirm('<spring:message code="common.delete.msg" />')) {
			document.frm.action = "<c:url value='/cop/bbs${prefix}/deleteBoardArticle.do'/>";
			document.frm.nttSj.value.value = encodeURIComponent(document.frm.nttSj.value);
			document.frm.submit();
		}
	}

	function fn_egov_moveUpdt_notice() {
		if ("<c:out value='${anonymous}'/>" == "true" && document.frm.password.value == '') {
			alert('등록시 사용한 패스워드를 입력해 주세요.');
			document.frm.password.focus();
			return;
		}

		document.frm.action = "<c:url value='/cop/bbs${prefix}/forUpdateBoardArticle.do'/>";
		document.frm.submit();
	}

	function fn_egov_addReply() {
		document.frm.action = "<c:url value='/cop/bbs${prefix}/addReplyBoardArticle.do'/>";
		document.frm.submit();
	}
</script>
<!-- 2009.06.29 : 2단계 기능 추가  -->
<c:if test="${useComment == 'true'}">
<c:import url="/cop/cmt/selectCommentList.do" charEncoding="utf-8">
	<c:param name="type" value="head" />
</c:import>
</c:if>
<c:if test="${useSatisfaction == 'true'}">
<c:import url="/cop/stf/selectSatisfactionList.do" charEncoding="utf-8">
	<c:param name="type" value="head" />
</c:import>
</c:if>
<c:if test="${useScrap == 'true'}">
<script type="text/javascript">
	function fn_egov_addScrap() {
		document.frm.action = "<c:url value='/cop/scp/addScrap.do'/>";
		document.frm.submit();
	}
</script>
</c:if>
<!-- 2009.06.29 : 2단계 기능 추가  -->
<title><c:out value='${result.bbsNm}'/> - 글조회</title>
<!--<link href="<c:url value='/css/clikmng/bootstrap.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/jquery-ui.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/sb-admin.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/font-awesome.css' />" rel="stylesheet" type="text/css">-->

<style type="text/css">
	body.popup h1{ margin-left:10px; font-size:18px; font-weight:bold; margin:20px;}
</style>


</head>
<body class="popup" onload="onloading();">
<form name="frm" method="post" action="">
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" >
<input type="hidden" name="nttId" value="<c:out value='${result.nttId}'/>" >
<input type="hidden" name="parnts" value="<c:out value='${result.parnts}'/>" >
<input type="hidden" name="sortOrdr" value="<c:out value='${result.sortOrdr}'/>" >
<input type="hidden" name="replyLc" value="<c:out value='${result.replyLc}'/>" >
<input type="hidden" name="nttSj" value="<c:out value='${result.nttSj}'/>" >

<h1><c:out value='${result.bbsNm}'/> - 글조회</h1>

	
	<table class="boardview" summary="새소식의 게시글 목록으로 번호,제목,첨부,작성부서,등록일자,조회수로 구성되어 있습니다.">
		<colgroup>
			<col width="20%" />
			<col />
			
		</colgroup>
		<tr>
			<th scope="row" class="tit"  colspan="2"><c:out value="${result.nttSj}" /></th>	
		</tr>
		<tr>
			<th scope="row" class="name">작성자</th>
			<td>
			 <c:choose>
				<c:when test="${anonymous == 'true'}">
					******
				</c:when>
				<c:when test="${result.ntcrNm == null || result.ntcrNm == ''}">
					<c:out value="${result.frstRegisterNm}" />
				</c:when>
				<c:otherwise>
					<c:out value="${result.ntcrNm}" />
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<th scope="row" class="data">작성일</th>	
			<td><c:out value="${result.frstRegisterPnttm}" /></td>
		</tr>
		<tr>
			<th scope="row" class="data">조회수</th>	
			<td><c:out value="${result.inqireCo}" /></td>
		</tr>
		<tr>
			<th scope="row" class="file">첨부</th>	
			<td class="file">
				<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${result.atchFileId}" />
				</c:import>
			</td>
		</tr>
	  <c:if test="${anonymous == 'true'}">
	  <tr>
		<th scope="row"><spring:message code="cop.password" /></th>
		<td>
			<input name="password" type="password" size="20" value="" maxlength="20" title="비밀번호입력">
		</td>
	  </tr>
	  </c:if>

		<tr>
			<td colspan="2" class="text">
				<div id="bbs_cn" style="min-height:300px;">
					<c:out value="${result.nttCn}" escapeXml="false" />
				 </div>
			</td>
		</tr>
		<c:if test="${result.prevNttId != null && result.prevNttId != ''}">
		<tr>
			<th scope="row">이전글</th>
			<td><a href="#none" onclick="javascript:fn_egov_inqire_notice(${result.prevNttId})">${result.prevNttSj }</a></td>
		</tr>
		</c:if>
		<c:if test="${result.nextNttId != null && result.nextNttId != ''}">
		<tr>
			<th scope="row">다음글</th>
			<td><a href="#none" onclick="javascript:fn_egov_inqire_notice(${result.nextNttId})">${result.nextNttSj }</a></td>
		</tr>
		</c:if>

	</table>



	
	<div class="btn_list">
			 <!-- 2015.10.21 안선생님 요청으로 삭제버튼 open -->
			 <a href="#none" onclick="javascript:fn_egov_delete_notice()"><img src="/images/clikmng/board/btn_delete.gif" alt="삭제" /></a>
			 
		     <c:if test="${result.frstRegisterId == sessionUniqId}">
			 <a href="#none" onclick="javascript:fn_egov_moveUpdt_notice()"><img src="/images/clikmng/board/btn_modify.gif" alt="수정" /></a>
			     
		     </c:if>
		     <c:if test="${result.replyPosblAt == 'Y'}">
		     	  <a href="#none" onclick="javascript:fn_egov_addReply()"><img src="/images/clikmng/board/btn_answer1.gif" alt="답글작성" /></a>
	          </c:if>
	          <a href="#none" onclick="javascript:fn_egov_select_noticeList('1')"><img src="/images/clikmng/board/btn_list.gif" alt="목록" /></a>
		      <!-- 2009.06.29 : 2단계 기능 추가  -->
		      <c:if test="${useScrap == 'true'}">
			     <a href="#none" onclick="javascript:fn_egov_addScrap()"><button type="button" class="btn btn-danger">스크랩</button></a>
	          </c:if>
	          <!-- 2009.06.29 : 2단계 기능 추가  -->
			
	</div><!--//btn_list-->

	<!-- 2009.06.29 : 2단계 기능 추가  -->
	<c:if test="${useComment == 'true'}">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="10"></td>
	  </tr>
	</table>

	<c:import url="/cop/cmt${prefix}/selectCommentList.do" charEncoding="utf-8">
		<c:param name="type" value="body" />
	</c:import>
	</c:if>

	<c:if test="${useSatisfaction == 'true'}">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="10"></td>
	  </tr>
	</table>

	<c:import url="/cop/stf${prefix}/selectSatisfactionList.do" charEncoding="utf-8">
		<c:param name="type" value="body" />
	</c:import>
	</c:if>
	<!-- 2009.06.29 : 2단계 기능 추가  -->

	
	

</form>
</body>
</html>