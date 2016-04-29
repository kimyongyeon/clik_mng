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
  * @Class Name : EgovBoardMstrRegist.jsp
  * @Description : 게시판 생성 화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
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
<script type="text/javascript" src="<c:url value='/js/clikmng/cop/bbs/EgovBBSMng.js' />"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="boardMaster" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_regist_brdMstr(){
		if (!validateBoardMaster(document.boardMaster)){
			return;
		}

		//----------------------------
		// 2009.06.26 : 2단계 기능 추가
		//----------------------------
		var theForm = document.boardMaster;
		if (theForm.bbsTyCode.options[theForm.bbsTyCode.selectedIndex].value == 'BBST04' &&
				theForm.option.options[theForm.option.selectedIndex].value != '') {
			alert('방명록의 경우는 추가 선택사항을 지원하지 않습니다.');
			theForm.option.focus();
			return;
		}
		////--------------------------
		
		// 2011.11.11 : 첨부파일가능 선택 시 파일숫자를 선택하도록 함, 첨부파일가능 미선택시 파일 숫자를 없음으로 변경
		var list = document.getElementsByName('fileAtchPosblAt');
		var fileAtchPosblAt_value;
		for (var i=0; i < list.length; i++) {
			if(list[i].checked == true) {
				fileAtchPosblAt_value = list[i].value;
			}
		}
		
		if (fileAtchPosblAt_value == "Y" && document.boardMaster.posblAtchFileNumber.value == 0 ) {
			alert("첨부가능파일 숫자를 1개이상 선택하세요.");
			return;
		}
		
		if (fileAtchPosblAt_value == "N" && document.boardMaster.posblAtchFileNumber.value != 0 ) {
			document.boardMaster.posblAtchFileNumber.value = 0;
		}
		////--------------------------

		if (confirm('<spring:message code="common.regist.msg" />')) {
			form = document.boardMaster;
			form.action = "<c:url value='/cop/bbs/insertBBSMasterInf.do'/>";
			form.submit();
		}
	}

	function fn_egov_select_brdMstrList(){
		form = document.boardMaster;
		form.action = "<c:url value='/cop/bbs/SelectBBSMasterInfs.do'/>";
		form.submit();
	}

	function fn_egov_inqire_tmplatInqire(){
		form = document.boardMaster;
		var retVal;
		var url = "<c:out value='/cop/tpl/selectTemplateInfsPop.do?typeFlag=BBS' />";
		
		window.open(url, "p_tmplatInqire", "width=880px, height=380px;");

	}

</script>

<title>커뮤니티 게시판 등록</title>

</head>
<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<jsp:include page="/cmm/top/top.do" />
<form:form commandName="boardMaster" name="boardMaster" method="post" action="${pageContext.request.contextPath}/cop/bbs/SelectBBSMasterInfs.do">

<input type="hidden" name="pageIndex"  value="<c:out value='${searchVO.pageIndex}'/>"/>
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
				 커뮤니티 게시판 등록
			</h2>
			<!-- /.panel-heading -->			
			<DIV id="border" class=""> 

				<table width="100%" cellpadding="8" class="table-search" border="0">
				 <tr>
				  <td width="100%"class="title_left">
				  </td>
				 </tr>
				</table>
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover dataTable no-footer" summary="게시판명,게시판소개,게시판 유형,게시판 속성,답장가능여부,파일첨부가능여부, ..  입니다">
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap >
				    	<label for="bbsNm">
				    		<spring:message code="cop.bbsNm" />
				    	</label>
				    <img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td width="80%" nowrap colspan="3">
				    	<form:input title="게시판명입력" path="bbsNm" size="60" cssStyle="width:100%" class="form-control span1" />
				    	<br/><form:errors path="bbsNm" />
				    </td>
				  </tr>
				  <tr>
				    <th height="23" class="required_text" >
				    	<label for="bbsIntrcn">
				    		<spring:message code="cop.bbsIntrcn" />
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td colspan="3">
				       <form:textarea title="게시판소개입력" path="bbsIntrcn" cols="75" rows="4" cssStyle="width:100%" class="form-control span1" />
				       <br/><form:errors path="bbsIntrcn" />
				    </td>
				  </tr>
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap >
				    	<label for="bbsTyCode">
				    		<spring:message code="cop.bbsTyCode" />
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="30%" nowrap>
				        <form:select path="bbsTyCode" title="게시판유형선택" class="form-control">
			    	  		<form:option value='' label="--선택하세요--" />
				      		<form:options items="${typeList}" itemValue="code" itemLabel="codeNm"/>
			      		</form:select>
				  	   <br/><form:errors path="bbsTyCode" />
				    </td>
			
				    <th width="20%" height="23" class="required_text" nowrap >
				    	<label for="bbsAttrbCode">
				    		<spring:message code="cop.bbsAttrbCode" />
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="30%" nowrap>
				        <form:select path="bbsAttrbCode" title="게시판속성선택" class="form-control">
			    	  		<form:option value='' label="--선택하세요--" />
				      		<form:options items="${attrbList}" itemValue="code" itemLabel="codeNm"/>
			      		</form:select>
				  	    <br/><form:errors path="bbsAttrbCode" />
				    </td>
				  </tr>
			
				  <tr>
				    <th width="20%" height="23" class="required_text" >
				    	<label for="replyPosblAt">
				    		<spring:message code="cop.replyPosblAt" />
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="30%" nowrap>
				     	<spring:message code="button.possible" /> : <form:radiobutton path="replyPosblAt"  value="Y" />&nbsp;
				     	<spring:message code="button.impossible" /> : <form:radiobutton path="replyPosblAt"  value="N"  />
				     	 <br/><form:errors path="replyPosblAt" />
				    </td>
			
				    <th width="20%" height="23" class="required_text" >
				    	<label for="fileAtchPosblAt">
				    		<spring:message code="cop.fileAtchPosblAt" />
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="30%" nowrap>
				     	<spring:message code="button.possible" /> : <form:radiobutton path="fileAtchPosblAt"  value="Y" />&nbsp;
				     	<spring:message code="button.impossible" /> : <form:radiobutton path="fileAtchPosblAt"  value="N"  />
				     	 <br/><form:errors path="fileAtchPosblAt" />
				    </td>
				  </tr>
			
				  <tr>
				    <th width="20%" height="23" class="required_text"  >
				    	<label for="posblAtchFileNumber">
				    		<spring:message code="cop.posblAtchFileNumber" />
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="30%" nowrap colspan="3" >
				     	<form:select path="posblAtchFileNumber" title="첨부가능파일 숫자선택" style="width:30%;"	class="input-sm">
				  		   <form:option value="0"  label="없음" />
				  		   <form:option value='1'>1개</form:option>
				  		   <form:option value='2'>2개</form:option>
				  		   <form:option value='3'>3개</form:option>
				  	   </form:select>
				  	   <br/><form:errors path="posblAtchFileNumber" />
				    </td>
				  </tr>
				  <tr>
				    <th width="20%" height="23" class="required_text" >
				    	<label for="tmplatNm">
				    		<spring:message code="cop.tmplatId" />
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="30%" nowrap colspan="3">
				     <form:input path="tmplatNm" size="20" readonly="true" title="템플릿정보입력" class=" span1" style="width:200px;"/>
				     <form:hidden path="tmplatId"  />
				     &nbsp;<a href="#LINK" onclick="fn_egov_inqire_tmplatInqire(); return false;" style="selector-dummy: expression(this.hideFocus=false);"><img src="<c:url value='/images/clikmng/cmm/icon/search.gif' />"
				     			width="15" height="15" align="middle" alt="새창" /></a>
					 <br/><form:errors path="tmplatId" />
				    </td>
				  </tr>
			
			
					<!-- 2011.09.15 : 2단계 기능 추가 방법 변경  -->
					<c:if test="${useComment == 'true' || useSatisfaction == 'true'}">
					  <tr style="display:none;">
					    <th width="20%" height="23" class="required_text"><label for="option">추가 선택사항&nbsp;&nbsp;&nbsp;&nbsp;</label></th>
					    <td width="30%" nowrap colspan="3" >
					     	<form:select path="option" title="추가선택사항선택"  class="form-control">
					  		   <form:option value=""  label="사용안함" />
					  		   <c:if test="${useComment == 'true'}">
					  		   	 <form:option value='comment'>댓글</form:option>
					  		   </c:if>
					  		   <c:if test="${useSatisfaction == 'true'}">
					  		   	<form:option value='stsfdg'>만족도조사</form:option>
					  		   </c:if>
					  	   </form:select>
					    </td>
					  </tr>
					</c:if>
					<!-- 2009.06.26 : 2단계 기능 추가 방법 변경 -->
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td height="10"></td>
				  </tr>
				</table>
				
				<p class="tr">
					<button type="button" class="btn btn-default" onclick="fn_egov_select_brdMstrList(); return false;">목록</button>
					<button type="button" class="btn btn-primary" onclick="javascript:fn_egov_regist_brdMstr(); return false;">등록</button>
				</p>
							
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->							
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->
</form:form>
</body>
</html>
