<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovBoardMstrList.jsp
  * @Description : 게시판 속성 목록화면
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

<script type="text/javascript" src="<c:url value='/js/clikmng/cop/bbs/EgovBBSMng.js' />"></script>
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_brdMstr('1');
		}
	}

	function fn_egov_insert_addBrdMstr(){
		document.frm.action = "<c:url value='/cop/bbs/addBBSMaster.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_brdMstr(pageNo){
		document.frm.pageIndex.value = pageNo;
		
		if(document.frm.searchCnd.value != '' && document.frm.searchWrd.value == '') {
			alert('검색어를 입력해 주세요.');
			return;
		}		
		
		document.frm.action = "<c:url value='/cop/bbs/SelectBBSMasterInfs.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_brdMstr(bbsId){
		document.frm.bbsId.value = bbsId;
		document.frm.action = "<c:url value='/cop/bbs/SelectBBSMasterInf.do'/>";
		document.frm.submit();
	}
	
	/* ********************************************************
	* 게시물 수 변경(셀렉트박스)
	******************************************************** */
	function fnChgListCnt(val) {
		var varForm = document.frm;
		varForm.pageUnit.value = val;
		varForm.action = "<c:url value='/cop/bbs/SelectBBSMasterInfs.do'/>";
	    varForm.submit();
	}	
</script>
<title>커뮤니티 게시판 목록조회</title>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">커뮤니티 게시판 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->

	<DIV id="main" style="display:" class="">
		<DIV class="">
			<h2>
				 커뮤니티 게시판 목록조회
			</h2>
			<!-- /.panel-heading -->			 
			<DIV id="border" class="">
			<form name="frm" action="<c:url value='/cop/bbs/SelectBBSMasterInfs.do'/>" method="post">
			<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
			<input type="hidden" name="pageUnit"  value="<c:out value='${searchVO.pageUnit}'/>"/>
			<input type="hidden" name="bbsId">
			<input type="hidden" name="trgetId">
			
				<div class="select_box">
					<span>
							<select name="searchCnd" class="" title="검색유형선력">
								<option value="" >전체</option>
							   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >게시판명</option>
							   <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >게시판유형</option>
						   </select>
						   <input  name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}" />' maxlength="35" onkeypress="press(event);" title="검색단어입력" class=" input-sm">
							
							<!-- <input type="submit" value="<spring:message code="button.inquire" />" onclick="fn_egov_select_brdMstr('1'); return false;"> -->
							
							<input type="button" value="<spring:message code="button.inquire" />" class="btn btn-primary" onclick="fn_egov_select_brdMstr('1'); return false;">
							
					</span>
				</div>
			
				<div class="page">
					총 건수 : <c:out value="${paginationInfo.totalRecordCount}" /> 건
	
					<span>
						출력선수
							<select title="쪽당출력건수" id="pageSize" name="pageSize" onchange="fnChgListCnt(this.value)">
								<option value="10" <c:if test="${searchVO.pageUnit == '10' }">selected</c:if>>10</option>
								<option value="30" <c:if test="${searchVO.pageUnit == '30' }">selected</c:if>>30</option>
								<option value="50" <c:if test="${searchVO.pageUnit == '50' }">selected</c:if>>50</option>
								<option value="100" <c:if test="${searchVO.pageUnit == '100' }">selected</c:if>>100</option>
		                    </select>	
					</span>
				</div>						
			
				<table width="100%" cellpadding="8" class="table table-striped table-bordered table-hover dataTable no-footer" summary="번호,게시판명,게시판유형,게시판속성,생성일,사용여부  목록입니다" >
				 <thead>
				  <tr>
				    <th scope="col" class="title" width="10%" nowrap>번호</th>
				    <th scope="col" class="title" width="" nowrap>게시판명</th>
				    <th scope="col" class="title" width="10%" nowrap>게시판유형</th>
				    <th scope="col" class="title" width="10%" nowrap>게시판속성</th>
				    <th scope="col" class="title" width="15%" nowrap>생성일</th>
				    <th scope="col" class="title" width="8%" nowrap>사용여부</th>
				    <th scope="col" class="title" width="8%" nowrap>미리보기</th>
				  </tr>
				 </thead>
				 <tbody>
					 <c:forEach var="result" items="${resultList}" varStatus="status">
					  <tr>
					    <!--td class="lt_text3" nowrap><input type="checkbox" name="check1" class="check2"></td-->
					    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
					    <td class="lt_text3" nowrap>
			
						    <!-- 2010.11.1
						    <form name="item" method="post" action="<c:url value='/cop/bbs/SelectBBSMasterInf.do'/>">
				        		<input type=hidden name="bbsId" value="<c:out value="${result.bbsId      }"/>">
				            	<span class="link"><input type="submit" value="<c:out value="${result.bbsNm}"/>" onclick="fn_egov_inqire_brdMstr('<c:out value="${result.bbsId}"/>'); return false;"></span>
				        	</form>
				            -->
			
							<a href="<c:url value='/cop/bbs/SelectBBSMasterInf.do'/>?bbsId=<c:out value='${result.bbsId}'/>">
								<c:out value="${result.bbsNm}"/>
							</a>
			
			
					    </td>
					    <td class="lt_text3" nowrap><c:out value="${result.bbsTyCodeNm}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.bbsAttrbCodeNm}"/></td>
					    <td class="lt_text3" nowrap><c:out value="${result.frstRegisterPnttm}"/></td>
					    <td class="lt_text3" nowrap>
					    	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
					    	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
					    </td>
					    <td class="lt_text3" nowrap>
					    	<a href="<%=request.getContextPath()%>/cop/bbs/selectBoardList.do?bbsId=<c:out value='${result.bbsId}'/>" target="_new">
				    	   		/cop/bbs/selectBoardList.do?bbsId=<c:out value='${result.bbsId}'/>
							</a>
					    </td>
					  </tr>
					 </c:forEach>
					 <c:if test="${fn:length(resultList) == 0}">
					  <tr>
					    <td class="lt_text3" nowrap colspan="7"><spring:message code="common.nodata.msg" /></td>
					  </tr>
					 </c:if>
				 </tbody>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td height="10"></td>
				  </tr>
				</table>
				
				<p class="tr">
					<button type="button" class="btn btn-primary" onclick="fn_egov_insert_addBrdMstr(); return false;">등록</button>
				</p>					
				
				
				<div align="center">
					<ul class="pagination">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_brdMstr" />
					</ul>
				</div>
			</form>
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
