<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/clikmng/cmm/"/>
<%
 /**
  * @Class Name : EgovTemplateInqirePop.jsp
  * @Description : 템플릿 목록 조회 팝업화면
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
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_tmplatInfo('1');
		}
	}
	function fn_egov_select_tmplatInfo(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/tpl/selectTemplateInfsPop.do'/>";
		document.frm.submit();
	}

	function fn_egov_returnTmplatInfo(tmplatId, tmplatNm){
		var retVal = tmplatId +"|"+tmplatNm;
		
		opener.parent.boardMaster.tmplatId.value = tmplatId; 
		opener.parent.boardMaster.tmplatNm.value = tmplatNm;
		
		window.close();
	}

	// 템플릿 미리보기
	function fn_egov_previewTmplat(tmplatSeCode, tmplatCours) {
		var frm = document.frm;

		var url = tmplatCours;

		var target = "";

		if (tmplatSeCode == 'TMPT01') {
			target = "<c:url value='/cop/bbs/previewBoardList.do' />";
			width = "750";
		} else if (tmplatSeCode == 'TMPT02') {
			target = "<c:url value='/cop/cmy/previewCmmntyMainPage.do' />";
			width = "980";
		} else if (tmplatSeCode == 'TMPT03') {
			target = "<c:url value='/cop/cus/previewClubMainPage.do' />";
			width = "980";
		} else {
			alert('<spring:message code="cop.tmplatCours" /> 지정 후 선택해 주세요.');
		}

		if (target != "") {
			window.open(target + "?searchWrd="+url, "preview", "width=" + width + "px, height=500px;");
		}
	}
	
</script>
<title>템플릿 목록</title>
<link href="<c:url value='/css/clikmng/bootstrap.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/jquery-ui.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/sb-admin.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/font-awesome.css' />" rel="stylesheet" type="text/css">
<style type="text/css">
	
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>


</head>
<body class="popup">
<h1>템플릿 목록</h1>
	
	  	
<form name="frm" action ="<c:url value='/cop/tpl/selectTemplateInfsPop.do'/>" method="post">
<input type="hidden" name="tmplatId" value="" />
	<div class="select_box">
		<span>
		<select name="searchCnd" class="select" title="검색조건선택">
			   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >템플릿명</option>
			   <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >템플릿구분</option>
		   </select>
		   <input name="searchWrd" type="text" class="ip input-sm" style="width:350px;" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">
		    <input name="typeFlag" type="hidden" value="<c:out value='${typeFlag}'/>"/>
		  <input type="submit" value="<spring:message code="button.inquire" />" onclick="fn_egov_select_tmplatInfo('1'); return false;" class="btn btn-primary" >
			
		</span>
	
	</div><!--//select_box-->
				

	
	<table width="100%" cellpadding="8" class="table table-striped table-bordered table-hover" border="0"  summary="번호, 템플릿명, 템플릿구분, 템플릿경로, 사용여부, 등록일자, 선택   목록입니다"  >
	 <thead>
	  <tr>
	    <!-- th class="title" width="3%" nowrap><input type="checkbox" name="all_check" class="check2"></th-->
	    <th scope="col" class="title" width="5%" nowrap>번호</th>
	    <th scope="col" class="title" width="15%" nowrap>템플릿명</th>
	    <th scope="col" class="title" width="10%" nowrap>템플릿구분</th>
	    <th scope="col" class="title" width="37%" nowrap>템플릿경로</th>
	    <th scope="col" class="title" width="5%" nowrap>사용여부</th>
	    <th scope="col" class="title" width="10%" nowrap>등록일자</th>
	    <th scope="col" class="title" width="10%" nowrap>미리보기</th>
	    <th scope="col" class="title" width="8%" nowrap>선택</th>
	  </tr>
	 </thead>
	 <tbody>
		 <c:forEach var="result" items="${resultList}" varStatus="status">
		  <tr>
		    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.tmplatNm}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.tmplatSeCodeNm}"/></td>
		    <td class="lt_text3" nowrap><c:out value="${result.tmplatCours}"/></td>
		    <td class="lt_text3" nowrap>
		    	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
		    	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
		    </td>
			<td class="lt_text3" nowrap><c:out value="${result.frstRegisterPnttm}"/></td>
			<td class="lt_text3" nowrap>
				<input type="button" name="previewTmplat" id="previewTmplat" 
					onClick="javascript:fn_egov_previewTmplat('<c:out value="${result.tmplatSeCode}"/>','${result.tmplatCours}')" 
					value="미리보기" />
			</td>
			<td class="lt_text3" nowrap>
		    	<c:if test="${result.useAt == 'Y'}">
		    		<input type="button" name="selectTmplat" value="선택"
		    			onClick="javascript:fn_egov_returnTmplatInfo('<c:out value="${result.tmplatId}"/>','<c:out value="${result.tmplatNm}"/>')" />
		    	</c:if>
			</td>
		  </tr>
		 </c:forEach>
		 <c:if test="${fn:length(resultList) == 0}">
		  <tr>
		    <td class="lt_text3" nowrap colspan="8" ><spring:message code="common.nodata.msg" /></td>
		  </tr>
		 </c:if>

	 </tbody>
	 <!--tfoot>
	  <tr class="">
	   <td colspan=6 align="center"></td>
	  </tr>
	 </tfoot -->
	</table>
	
	<div align="center">
		<ul class="pagination">
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_select_tmplatInfo" />
		</ul>
	</div>
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
	
	<div align="center">
	<button type="button" class="btn btn-primary" onclick="javascript:parent.close(); return false;">닫기</button>
	
	</div>
</form>
</body>
</html>
