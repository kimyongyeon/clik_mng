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
  * @Class Name : EgovBoardUseInfRegist.jsp
  * @Description : 게시판  사용정보  등록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  *
  *  @author 
  *  @since 
  *  @version 
  *
  */
%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="boardUseInf" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_select_bbsUseInfs(){
		document.boardUseInf.action = "<c:url value='/cop/com/selectBBSUseInfs.do'/>";
		document.boardUseInf.submit();
	}

	function fn_egov_regist_bbsUseInf(){
		if (!validateBoardUseInf(document.boardUseInf)){
			return;
		}

		if (confirm('<spring:message code="common.regist.msg" />')) {
			document.boardUseInf.param_trgetType.value = document.boardUseInf.trgetType.value;
			document.boardUseInf.action = "<c:url value='/cop/com/insertBBSUseInf.do'/>";
			document.boardUseInf.submit();
		}
	}

	function fn_egov_inqire_bbsInf(){
		var retVal;
		var url = "<c:url value='/cop/com/openPopup.do' />?requestUrl=/cop/bbs/SelectBBSMasterInfsPop.do&width=850&height=520";
		var openParam = "dialogWidth: 900px; dialogHeight: 520px; resizable: 0, scroll: 1, center: 1";

		retVal = window.showModalDialog(url,"p_cmmntyInqire", openParam);
		if(retVal != null){
			var tmp = retVal.split("|");
			document.boardUseInf.bbsId.value = tmp[0];
			document.boardUseInf.bbsNm.value = tmp[1];
		}
	}

	function fn_egov_selectTargetType(obj) {

		var retVal;
		var _strType = obj.value;

		if (_strType == 'CMMNTY') {
			retVal = fn_egov_inqire_cmmnty();
		} else if (_strType == 'CLUB') {
			retVal = fn_egov_inqire_club();
		} else if (_strType == '') {
			retVal = "|";
		} else {
			retVal = "SYSTEM_DEFAULT_BOARD"+"|"+"시스템 활용";
		}

		if (retVal != null) {
			var tmp = retVal.split("|");
			document.boardUseInf.trgetId.value = tmp[0];
			document.boardUseInf.trgetNm.value = tmp[1];
		}
	}

	function fn_egov_inqire_cmmnty(){
		var retVal;
		var url = "<c:url value='/cop/com/openPopup.do' />?requestUrl=/cop/cmy/selectCmmntyInfsByPop.do&width=850&height=360";
		var openParam = "dialogWidth: 850px; dialogHeight: 360px; resizable: 0, scroll: 1, center: 1";

		retVal = window.showModalDialog(url,"p_cmmntyInqire", openParam);
		return retVal;
	}

	function fn_egov_inqire_club(){
		var retVal;
		var url = "<c:url value='/cop/com/openPopup.do' />?requestUrl=/cop/clb/selectClubInfsByPop.do&width=850&height=360";
		var openParam = "dialogWidth: 850px; dialogHeight: 360px; resizable: 0, scroll: 1, center: 1";

		retVal = window.showModalDialog(url,"p_cmmntyInqire", openParam);
		return retVal;
	}
</script>
<title>게시판 사용등록</title>

</head>
<body>
<jsp:include page="/cmm/top/top.do" />
<form:form commandName="boardUseInf" name="boardUseInf" method="post">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
<input type="hidden" name="param_trgetType" value="" />

<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">게시판 사용등록</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->
	<p class="tr">
		<button type="button" class="btn btn-default" onclick="fn_egov_select_bbsUseInfs(); return false;">목록</button>
		<button type="button" class="btn btn-primary" onclick="fn_egov_regist_bbsUseInf(); return false;">등록</button>	
	</p>
	<DIV id="main" style="display:" class="col-lg-12">
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				 게시판 사용정보 등록
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="border" class="panel-body">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table table-striped table-bordered table-hover dataTable no-footer">
				  <tr>
				    <th width="30%" height="23" class="required_text" nowrap ><spring:message code="cop.bbsNm" />
				    <img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td width="70%" nowrap colspan="3">
				      <input name="bbsId" type="hidden" />
				      <input name="bbsNm" type="text" size="40" value=""  maxlength="40" title="게시판명" readonly class="form-control span1" style="width:200px;" />
				      &nbsp;<a href="#LINK" onclick="fn_egov_inqire_bbsInf();" style="selector-dummy: expression(this.hideFocus=false);"><img src="<c:url value='/images/clikmng/cmm/icon/search.gif' />"
				     			width="15" height="15" align="middle" alt="새창" /></a>
			 		<br/><form:errors path="bbsId" />
				    </td>
				  </tr>
				  <tr>
				    <th width="30%" height="23" class="required_text" nowrap >
				    	<label for="trgetType">
				    		<spring:message code="cop.trgetNm" />
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="70%" nowrap colspan="3">
					   <select name="trgetType" class="form-control" title="<spring:message code="cop.trgetNm" />" onChange="javascript:fn_egov_selectTargetType(this)" style="width:200px;"  >
						   <option selected value=''>--선택하세요--</option>
						   <c:if test="${useCommunity == 'true'}">
						   	<option value="CMMNTY" >커뮤니티</option>
						   </c:if>
						   <c:if test="${useClub == 'true'}">
						   	<option value="CLUB" >동호회</option>
						   </c:if>
						   <option value="SYSTEM" >시스템</option>
					   </select>
					   <input type="hidden" name="trgetId" value="" >
					   <input type="text" name="trgetNm" value=""  size="40" title="<spring:message code="cop.trgetNm" />" readOnly class="form-control span1"  style="width:200px;" >
					   <br/><form:errors path="trgetId" />
				    </td>
				  </tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td height="10"></td>
				  </tr>
				</table>
				
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->
		<p class="tr">
		<button type="button" class="btn btn-default" onclick="fn_egov_select_bbsUseInfs(); return false;">목록</button>
		<button type="button" class="btn btn-primary" onclick="fn_egov_regist_bbsUseInf(); return false;">등록</button>	
		</p>									
	</DIV>		
	<!-- /.MAIN -->		
</DIV>		
</form:form>
</body>
</html>
