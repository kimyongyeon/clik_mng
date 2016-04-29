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
  * @Class Name : EgovTemplateRegist.jsp
  * @Description : 템플릿 속성 등록화면
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
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="templateInf" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_regist_tmplatInfo(){
		if (!validateTemplateInf(document.templateInf)){
			return;
		}

		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.templateInf.action = "<c:url value='/cop/tpl/insertTemplateInf.do'/>";
			document.templateInf.submit();
		}
	}

	function fn_egov_select_tmplatInfo(){
		document.templateInf.action = "<c:url value='/cop/tpl/selectTemplateInfs.do'/>";
		document.templateInf.submit();
	}

	function fn_egov_selectTmplatType(obj){
		if (obj.value == 'TMPT01') {
			document.getElementById('sometext').innerHTML = "게시판 템플릿은 CSS만 가능합니다.";
		} else if (obj.value == '') {
			document.getElementById('sometext').innerHTML = "";
		} else {
			document.getElementById('sometext').innerHTML = "템플릿은 JSP만 가능합니다.";
		}
	}

	function fn_egov_previewTmplat() {
		var frm = document.templateInf;

		var url = frm.tmplatCours.value;

		var target = "";

		if (frm.tmplatSeCode.value == 'TMPT01') {
			target = "<c:url value='/cop/bbs/previewBoardList.do' />";
			width = "750";
		} else if (frm.tmplatSeCode.value == 'TMPT02') {
			target = "<c:url value='/cop/cmy/previewCmmntyMainPage.do' />";
			width = "980";
		} else if (frm.tmplatSeCode.value == 'TMPT03') {
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
<title>템플릿 등록</title>
</head>
<body>
<jsp:include page="/cmm/top/top.do" />
<form:form commandName="templateInf" name="templateInf" method="post" >
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />


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
		<button type="button" class="btn btn-default" onclick="javascript:fn_egov_select_tmplatInfo();return false;">목록</button>
		<button type="button" class="btn btn-primary" onclick="fn_egov_regist_tmplatInfo(); return false;">등록</button>
		<button type="button" class="btn btn-success" onclick="fn_egov_previewTmplat();">미리보기</button>
		<!--<button type="button" class="btn btn-danger" onclick="javascript:fncAuthorDeleteList()">삭제</button>-->	
	</p>	
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				 템플릿 등록
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="border" class="panel-body">
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover dataTable no-footer">
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap >
				    	<label for="tmplatNm">
				    		<spring:message code="cop.tmplatNm" />
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="80%" nowrap>
				      <input name="tmplatNm" type="text" size="60" value="" maxlength="60" style="width:100%" id="tmplatNm"  title="템플릿명" class="form-control input-sm">
				      <br/><form:errors path="tmplatNm" />
				    </td>
				  </tr>
			
				  <tr>
				    <th height="23" class="required_text" >
				    	<label for="tmplatSeCode">
				    		<spring:message code="cop.tmplatSeCode" />
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td>
				   	<select name="tmplatSeCode" class="form-control" onchange="fn_egov_selectTmplatType(this)" id="tmplatSeCode" title="템플릿구분">
						   <option selected value=''>--선택하세요--</option>
						<c:forEach var="result" items="${resultList}" varStatus="status">
							<option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
						</c:forEach>
					   </select>&nbsp;&nbsp;&nbsp;<span id="sometext"></span>
					   <br/><form:errors path="tmplatSeCode" />
				    </td>
				  </tr>
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap >
				    	<label for="tmplatCours">
				    		<spring:message code="cop.tmplatCours" />
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="80%" nowrap>
				      <input name="tmplatCours" type="text" size="60" value="" maxlength="60" style="width:100%" id="tmplatCours"  title="템플릿경로" class="form-control input-sm">
				      <br/><form:errors path="tmplatCours" />
				    </td>
				  </tr>
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap >
				    	<label for="useAt">
				    		<spring:message code="cop.useAt" />
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="80%" nowrap>
				     	Y : <input type="radio" name="useAt" class="radio2" value="Y"  checked>&nbsp;
				     	N : <input type="radio" name="useAt" class="radio2" value="N">
				     	<br/><form:errors path="useAt" />
				    </td>
				  </tr>
			
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td height="10"></td>
				  </tr>
				</table>
			
				<!--
				<div align="center">
				  <span class="button"><input type="submit" value="조회" onclick="fn_egov_regist_tmplatInfo(); return false;"></span>
			      <span class="button"><input type="button" value="목록" onclick="fn_egov_select_tmplatInfo();" alt="목록"></span>
			      <span class="button"><input type="button" value="미리보기" onclick="fn_egov_previewTmplat();" alt="새창"></span>
				</div>
				-->
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
