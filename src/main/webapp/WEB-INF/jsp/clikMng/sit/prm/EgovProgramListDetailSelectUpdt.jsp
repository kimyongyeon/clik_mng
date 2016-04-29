<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 /**
  * @Class Name : EgovProgramListDetailSelectUpdt.jsp
  * @Description : 프로그램목록 상세조회및 수정 화면
  * @Modification Ination
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  *
  *  @author 
  *  @since 
  *  @version 
  *  @see
  *
  */
  /* Image Path 설정 */
  //String imagePath_icon   = "/images/clikmng/sit/prm/icon/";
  //String imagePath_button = "/images/clikmng/sit/prm/button/";
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="ImgUrl" value="/images/clikmng/sit/prm/"/>
<c:set var="CssUrl" value="/css/clikmng/sit/prm/"/>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>프로그램목록리스트</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="progrmManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 수정처리 함수
 ******************************************************** */
function updateProgramListManage(form) {
	if(confirm("<spring:message code="common.save.msg" />")){
		if(!validateProgrmManageVO(form)){
			return;
		}else{
            form.action="<c:url value='/sit/prm/EgovProgramListDetailSelectUpdt.do' />";
			form.submit();
		}
	}
}

/* ********************************************************
 * 삭제처리함수
 ******************************************************** */
function deleteProgramListManage(form) {
	if(confirm("<spring:message code="common.delete.msg" />")){
        form.action="<c:url value='/sit/prm/EgovProgramListManageDelete.do' />";
		form.submit();
	}
}

/* ********************************************************
 * 목록조회 함수
 ******************************************************** */
function selectList(){
	location.href = "<c:url value='/sit/prm/EgovProgramListManageSelect.do' />";
}
-->
</script>
</head>
<body>
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">프로그램관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
		<p class="tr">					
			<button type="button" class="btn btn-default" onclick="selectList(); return false;" ><spring:message code="button.list" /></button>
			<button type="button" class="btn btn-success"  onclick="updateProgramListManage(document.forms[0]); return false;"><spring:message code="button.update" /></button>						
			<button type="button" class="btn btn-danger" onclick="deleteProgramListManage(document.forms[0]); return false;"><spring:message code="button.delete" /></button>												
			<!--
			<button type="button" class="btn btn-primary" onclick="insertProgramListManage(); return false;"><spring:message code="button.create" /></button>															
			-->
		</p>  	
	<div id="border" style="display:" class="col-lg-12">
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				 프로그램목록 상세조회 /수정
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="border" class="panel-body">
			<table border="0" width="100%">
			  <tr>
			    <td >
			<!-- ********** 여기서 부터 본문 내용 *************** -->
			
			<form:form commandName="progrmManageVO" method="post">
			<DIV id= "main">

			<table border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered" summary="프로그램목록 상세조회 /수정">			   	
			  <tr>
			    <th width="20%" height="23" class="required_text" scope="row"><label for="progrmFileNm">프로그램파일명</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
			    <td width="80%" nowrap>
			      <form:input  path="progrmFileNm" size="50"  maxlength="50" title="프로그램파일명" class="form-control input-sm"/>
			      <form:errors path="progrmFileNm"/>
			    </td>
			  </tr>
			  <tr>
			    <th width="20%" height="23" class="required_text" scope="row"><label for="progrmStrePath">저장경로</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
			    <td width="80%" nowrap>
			      <form:input  path="progrmStrePath" size="50"  maxlength="50" title="저장경로" class="form-control input-sm"/>
			      <form:errors path="progrmStrePath"/>
			    </td>
			  </tr>
			  <tr>
			    <th width="20%" height="23" class="required_text" scope="row"><label for="progrmKoreanNm">한글명</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
			    <td width="80%" nowrap>
			      <form:input path="progrmKoreanNm" size="60"  maxlength="60"  title="한글명" class="form-control input-sm"/>
			      <form:errors path="progrmKoreanNm" />
			    </td>
			  </tr>
			  <tr>
			    <th width="20%" height="23" class="required_text" scope="row"><label for="URL">URL</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
			    <td width="80%" nowrap>
			      <form:input path="URL" size="60"  maxlength="60" title="URL" class="form-control input-sm" />
			      <form:errors path="URL" />
			    </td>
			  </tr>
			  <tr>
			    <th height="23" class="required_text" scope="row"><label for="progrmDc">프로그램설명</label></th>
			    <td>
			      <form:textarea path="progrmDc" rows="14" cols="75" title="프로그램설명"/>
			      <form:errors path="progrmDc"/>
			    </td>
			  </tr>
			</table>

			
			</DIV>
			<input name="cmd" type="hidden" value="<c:out value='update'/>"/>
			</form:form>
			<!-- ********** 여기까지 내용 *************** -->
			</td>
			</tr>
			</table>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->	 
		<p class="tr">					
			<button type="button" class="btn btn-default" onclick="selectList(); return false;" ><spring:message code="button.list" /></button>
			<button type="button" class="btn btn-success"  onclick="updateProgramListManage(document.forms[0]); return false;"><spring:message code="button.update" /></button>						
			<button type="button" class="btn btn-danger" onclick="deleteProgramListManage(document.forms[0]); return false;"><spring:message code="button.delete" /></button>												
			<!--
			<button type="button" class="btn btn-primary" onclick="insertProgramListManage(); return false;"><spring:message code="button.create" /></button>															
			-->
		</p> 		
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->
</body>
</html>

