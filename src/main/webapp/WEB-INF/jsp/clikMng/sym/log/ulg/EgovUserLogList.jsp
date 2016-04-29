<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovUserLogList.jsp
  * @Description : 사용자 로그 정보목록 화면
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

	function fn_egov_select_userLog(pageNo){
		var fromDate = document.frm.searchBgnDe.value;
		var toDate = document.frm.searchEndDe.value;

		if(fromDate > toDate) {
	        alert("종료일자는 시작일자보다  이후날짜로 선택하세요.");
	        //return false;
	    } else {
			document.frm.pageIndex.value = pageNo;
			document.frm.action = "<c:url value='/sym/log/ulg/SelectUserLogList.do'/>";
			document.frm.submit();
	    }
	}

	function fn_egov_inqire_userLog(occrrncDe, rqesterId, srvcNm, methodNm){
		var url = "<c:url value='/sym/log/ulg/InqireUserLog.do' />?occrrncDe="+occrrncDe+"&rqesterId="+rqesterId+"&srvcNm="+srvcNm+"&methodNm="+methodNm;

		var openParam = "scrollbars=yes,toolbar=0,location=no,resizable=0,status=0,menubar=0,width=750,height=300,left=0,top=0";
		window.open(url,"p_userLogInqire", openParam);
	}
</script>
<title>사용자 로그 목록</title>
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
				 사용자 로그조회
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="border" class="panel-body">
				<form name="frm" action ="<c:url value='/sym/log/ulg/SelectUserLogList.do'/>" method="post">
				<input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
				
				 <table width="100%" cellpadding="8" class="table-search" border="0">
					 <tr>
					  <th>발생일자</th>
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
						<td>사용자</td>
					  <td>
					    <input name="searchWrd" type="text" size="15" value="<c:out value='${searchVO.searchWrd}'/>"  maxlength="15" title="검색단어입력">
					  </td>
					  <th>
					   <table border="0" cellspacing="0" cellpadding="0">
					    <tr>
					      <td>
				          	<span class="button"><input type="button" onclick="fn_egov_select_userLog('1');" value="조회" /></span>
				          </td>
					     
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
					    <th class="title" width="15%" nowrap>발생일자</th>
					    <th class="title" width="15%" nowrap>사용자</th>
					    <th class="title" width="35%" nowrap>메소드명</th>
					    <th class="title" width="5%" nowrap>생성</th>
					    <th class="title" width="5%" nowrap>수정</th>
					    <th class="title" width="5%" nowrap>조회</th>
					    <th class="title" width="5%" nowrap>삭제</th>
					    <th class="title" width="10%" nowrap>상세보기</th>
					  </tr>
					 </thead>
					 <tbody>
					 <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
					 <c:if test="${fn:length(resultList) == 0}">
					 <tr>
					 <td class="lt_text3" colspan="9">
				 		<spring:message code="common.nodata.msg" />
					 </td>
					 </tr>
					 </c:if>
					 <c:forEach var="result" items="${resultList}" varStatus="status">
					  <tr>
					    <!--td class="lt_text3" nowrap><input type="checkbox" name="check1" class="check2"></td-->
					    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.occrrncDe}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.rqsterNm}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.methodNm}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.creatCo}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.updtCo}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.rdCnt}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.deleteCo}"/></td>
					    <td class="lt_text3" nowrap>
					    <a href="javascript:fn_egov_inqire_userLog('<c:out value="${result.occrrncDe}"/>', '<c:out value="${result.rqesterId}"/>', '<c:out value="${result.srvcNm}"/>', '<c:out value="${result.methodNm}"/>');"
					    	style="selector-dummy:expression(this.hideFocus=false);"><img src="<c:url value='/images/clikmng/cmm/icon/search.gif'/>"  alt="상세보기"
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
								       type="image" jsFunction="fn_egov_select_userLog" />
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
