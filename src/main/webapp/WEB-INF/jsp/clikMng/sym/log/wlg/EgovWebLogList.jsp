<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovWebLogList.jsp
  * @Description : 웹 로그 정보목록 화면
  * @Modification Information
  * @
  * @  수정일         수정자          수정내용
  * @ -------        --------    ---------------------------
  
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
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js'/>" ></script>
<script type="text/javascript">

	function fn_egov_select_webLog(pageNo){
		var fromDate = document.frm.searchBgnDe.value;
		var toDate = document.frm.searchEndDe.value;

		if(fromDate > toDate) {
	        alert("종료일자는 시작일자보다  이후날짜로 선택하세요.");
	        //return false;
	    } else {
			document.frm.pageIndex.value = pageNo;
			document.frm.action = "<c:url value='/sym/log/wlg/SelectWebLogList.do'/>";
			document.frm.submit();
	    }
	}

	function fn_egov_inqire_webLog(requstId){
		var url = "<c:url value='/sym/log/wlg/InqireWebLog.do' />?requstId="+requstId;

		var openParam = "scrollbars=yes,toolbar=0,location=no,resizable=0,status=0,menubar=0,width=750,height=240,left=0,top=0";
		window.open(url,"p_webLogInqire", openParam);
	}
</script>
<title>웹 로그 목록</title>
</head>
<body>
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">로그관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
	
	<div id="border" style="display:" class="col-lg-12">
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				 웹 로그조회
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="border" class="panel-body">
				<form name="frm" action ="<c:url value='/sym/log/wlg/SelectWebLogList.do'/>" method="post">
				<input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
				
				 <table width="100%" cellpadding="8" class="table-search" border="0">
					 <tr>
					  <th>
					   발생일자
					  </th>
					  <td>
					      <input name="searchBgnDe" type="hidden"  value="<c:out value='${searchVO.searchBgnDe}'/>">
					      <input name="searchBgnDeView" type="text" size="10" value="${searchVO.searchBgnDeView}"  readOnly
					      	onClick="javascript:fn_egov_NormalCalendar(document.frm, document.frm.searchBgnDe, document.frm.searchBgnDeView);" title="시작일자입력">
					      <a href="javascript:fn_egov_NormalCalendar(document.frm, document.frm.searchBgnDe, document.frm.searchBgnDeView);"
					    	style="selector-dummy:expression(this.hideFocus=false);"><img src="<c:url value='/images/clikmng/cmm/icon/bu_icon_carlendar.gif' />" alt="달력창팝업버튼이미지"
						    width="15" height="15"></a>
					      ~
					      <input name="searchEndDe" type="hidden"  value="<c:out value='${searchVO.searchEndDe}'/>">
					      <input name="searchEndDeView" type="text" size="10" value="${searchVO.searchEndDeView}"  readOnly
					      	onClick="javascript:fn_egov_NormalCalendar(document.frm, document.frm.searchEndDe, document.frm.searchEndDeView);" title="종료일자입력">
					      <a href="javascript:fn_egov_NormalCalendar(document.frm, document.frm.searchEndDe, document.frm.searchEndDeView);"
					    	style="selector-dummy:expression(this.hideFocus=false);"><img src="<c:url value='/images/clikmng/cmm/icon/bu_icon_carlendar.gif' />" alt="달력창팝업버튼이미지"
						    width="15" height="15"></a>
						</td>
						<td>URL</td>
					  <td>
					    <input name="searchWrd" type="text" size="15" value="<c:out value='${searchVO.searchWrd}'/>"  maxlength="15" title="검색단어입력">
					  </td>
					  <th>
					   <table border="0" cellspacing="0" cellpadding="0">
					    <tr>
					    <!-- 
					      <td><img src="<c:url value='/images/clikmng/cmm/btn/bu2_left.gif' />" width="8" height="20" alt="버튼이미지"></td>
					      <td background="<c:url value='/images/clikmng/cmm/btn/bu2_bg.gif'/>" class="text_left" nowrap>
					      <a href="javascript:fn_egov_select_webLog('1')">조회</a>
					      </td>
					      <td><img src="<c:url value='/images/clikmng/cmm/btn/bu2_right.gif'/>" width="8" height="20" alt="버튼이미지"></td>
					    -->
					      <td><span class="button"><input type="submit" value="조회" onclick="javascript:fn_egov_select_webLog('1');return false;"></span></td>
					    </tr>
					   </table>
					  </th>
					 </tr>
					</table>
					<table width="100%" cellpadding="8" class="table table-striped table-bordered">
					 <thead>
					  <tr>
					    <!-- th class="title" width="3%" nowrap><input type="checkbox" name="all_check" class="check2"></th-->
					    <th class="title" width="5%" nowrap>번호</th>
					    <th class="title" width="15%" nowrap>요청ID</th>
					    <th class="title" width="10%" nowrap>발생일자</th>
					    <th class="title" width="40%" nowrap>URL</th>
					    <th class="title" width="10%" nowrap>요청자</th>
					    <th class="title" width="15%" nowrap>요청자IP</th>
					    <th class="title" width="5%" nowrap>상세보기</th>
					  </tr>
					 </thead>
					 <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
					<td class="lt_text3" colspan="9">
						<spring:message code="common.nodata.msg" />
					</td>
					</tr>
					</c:if>
					 <tbody>
					 <c:forEach var="result" items="${resultList}" varStatus="status">
					  <tr>
					    <!--td class="lt_text3" nowrap><input type="checkbox" name="check1" class="check2"></td-->
					    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.requstId}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.occrrncDe}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.url}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.rqsterNm}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.rqesterIp}"/></td>
					    <td class="lt_text3" nowrap>
					    <a href="javascript:fn_egov_inqire_webLog('<c:out value="${result.requstId}"/>');"
					    	style="selector-dummy:expression(this.hideFocus=false);">
					    	<img src="<c:url value='/images/clikmng/cmm/icon/search.gif'/>"  alt="상세보기"
					    	width="15" height="15" align="middle"></a>
					    </td>
					  </tr>
					 </c:forEach>
				
				
					 </tbody>
					 <!--tfoot>
					  <tr class="">
					   <td colspan=6 align="center"></td>
					  </tr>
					 </tfoot -->
					</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td height="10"></td>
				  </tr>
				  <tr><td>
					<div align="center">
						<ul class="pagination">
							<ui:pagination paginationInfo = "${paginationInfo}"
									   type="image" jsFunction="fn_egov_select_webLog"	/>
						</ul>
					</div>
				  </td></tr>
				</table>
					<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
				</form>
			</DIV>
			<!-- /panel body -->
		</DIV>

	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->				
</body>
</html>
