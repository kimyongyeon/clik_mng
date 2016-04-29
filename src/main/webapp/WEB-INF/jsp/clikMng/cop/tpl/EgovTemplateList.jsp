<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovTemplateList.jsp
  * @Description : 템플릿 목록화면
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
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_tmplatInfo('1');
		}
	}

	function fn_egov_insert_addTmplatInfo(){
		document.frm.action = "<c:url value='/cop/tpl/addTemplateInf.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_tmplatInfo(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/cop/tpl/selectTemplateInfs.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_tmplatInfor(tmplatId){
		document.frm.tmplatId.value = tmplatId;
		document.frm.action = "<c:url value='/cop/tpl/selectTemplateInf.do'/>";
		document.frm.submit();
	}
</script>
<title>템플릿 목록조회</title>
</head>
<body>

<jsp:include page="/cmm/top/top.do" />

	<form name="frm" action ="" method="post">
	<input type="hidden" name="tmplatId" value="" />
	
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">템플릿 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->
	<DIV id="main" style="display:" class="col-lg-12">
	<p class="tr">
		<button type="button" class="btn btn-primary" onclick="javascript:fn_egov_insert_addTmplatInfo();return false;">등록</button>
	</p>
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				 템플릿 목록조회
			</DIV>
			<!-- /.panel-heading -->			
			<DIV class="panel-body">	
				
				<div class="select_box">
					<span>
						<select name="searchCnd" class="" title="검색조건선택">
						   <!-- option selected value=''--><!--선택하세요--><!--/option-->
						   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >템플릿명</option>
						   <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >템플릿구분</option>
					   </select>
					   <input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="35" onkeypress="press(event);" title="검색단어입력" class=" input-sm">
						<input type="button" value="조회" class="btn btn-primary" onclick="javascript:fn_egov_select_tmplatInfo('1');return false;">
					</span> 
				</div>
	
			
			<table width="100%" cellpadding="8" class="table table-striped table-bordered table-hover dataTable no-footer">
			 <thead>
			  <tr>
			    <!-- th class="title" width="3%" nowrap><input type="checkbox" name="all_check" class="check2"></th-->
			    <th class="title" width="10%" nowrap>번호</th>
			    <th class="title" width="15%" nowrap>템플릿명</th>
			    <th class="title" width="10%" nowrap>템플릿구분</th>
			    <th class="title" width="32%" nowrap>템플릿경로</th>
			    <th class="title" width="10%" nowrap>사용여부</th>
			    <th class="title" width="10%" nowrap>등록일자</th>
			  </tr>
			 </thead>
			 <tbody>
				 <c:forEach var="result" items="${resultList}" varStatus="status">
				  <tr>
				    <!--td class="lt_text3" nowrap><input type="checkbox" name="check1" class="check2"></td-->
				    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		
				    <!-- 2010.10.15
				    <td class="lt_text3" nowrap>
				    	<a href="javascript:fn_egov_inqire_tmplatInfor('<c:out value="${result.tmplatId}"/>')">
				    	<c:out value="${result.tmplatNm}"/></a>
				    </td>
					-->
		
				    <td class="lt_text3" nowrap>
						<a href="<c:url value='/cop/tpl/selectTemplateInf.do'/>?tmplatId=<c:out value='${result.tmplatId}'/>" onclick="">
							<c:out value="${result.tmplatNm}"/>
						</a>
				    </td>
		
				    <td class="lt_text3" nowrap><c:out value="${result.tmplatSeCodeNm}"/></td>
				    <td class="lt_text3" nowrap><c:out value="${result.tmplatCours}"/></td>
				    <td class="lt_text3" nowrap>
				    	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
				    	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
				    </td>
					<td class="lt_text3" nowrap><c:out value="${result.frstRegisterPnttm}"/></td	>
				  </tr>
				 </c:forEach>
				 <c:if test="${fn:length(resultList) == 0}">
				  <tr>
				    <td class="lt_text3" nowrap colspan="6" ><spring:message code="common.nodata.msg" /></td>
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
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_tmplatInfo" />
				</ul>
			</div>
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->							
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->
</form>
</body>
</html>
