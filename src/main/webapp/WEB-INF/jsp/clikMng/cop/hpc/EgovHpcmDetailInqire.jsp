<%
 /**
  * @Class Name : EgovHpcmDetailInqure.jsp
  * @Description : EgovHpcmDetailInqure 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  *
  *  @author  
  *  @since 
  *  @version 
  *  @see
  *  
  */
%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>도움말 상세보기 및 수정</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_hpcmcn(){



}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_inqire_hpcmlist() {

	document.HpcmManageForm.action = "<c:url value='/cop/hpc/HpcmListInqire.do'/>";
	document.HpcmManageForm.submit();
		
}

/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_egov_updt_hpcmcn(hpcmId){

	// Update하기 위한 키값(recomendSiteId)을 셋팅
	document.HpcmManageForm.hpcmId.value = hpcmId;	
	document.HpcmManageForm.action = "<c:url value='/cop/hpc/HpcmCnUpdtView.do'/>";
	document.HpcmManageForm.submit();	
	
}


function fn_egov_delete_hpcmcn(hpcmId){

	if	(confirm("<spring:message code="common.delete.msg" />"))	{	

		// Delete하기 위한 키값(hpcmId)을 셋팅
		document.HpcmManageForm.hpcmId.value = hpcmId;	
		document.HpcmManageForm.action = "<c:url value='/cop/hpc/HpcmCnDelete.do'/>";
		document.HpcmManageForm.submit();
			
	}
	
	
}

</script>
</head>

<body onLoad="fn_egov_initl_hpcmcn();">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">도움말 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
  	
	<div id="border" style="display:" class="">
	  	
		<DIV class="">
			<DIV class="panel-heading">
				 도움말 상세보기 및 수정
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="content" class="">
				<!-- 상단타이틀 -->
				<form name="HpcmManageForm" action="<c:url value='/cop/hpc/HpcmCnUpdtView.do'/>" method="post">
								
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<!-- 등록  폼 영역  -->
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered" summary="도움말에 대한 정보를 조회합니다.">				
				  <tr> 
				    <th scope="row" height="23" class="required_text" nowrap >도움말 구분&nbsp;&nbsp;</th>
				    <td>
				    	<c:out value="${result.hpcmSeCodeNm}"/>  
				    </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" height="23" class="required_text" nowrap ><label for="hpcmDf">도움말 정의</label>&nbsp;&nbsp;</th>
				    <td>
				      <textarea name="hpcmDf" class="textarea"  cols="300" rows="5"  style="width:450px;"  readonly title="도움말 정의"><c:out value="${result.hpcmDf}"/>
				      </textarea>                   
				    </td>
				  </tr>
				  
				  <tr> 
				    <th scope="row" height="23" class="required_text" ><label for="hpcmDc">도움말 설명</label>&nbsp;&nbsp;</th>
				    <td>    
				      <textarea name="hpcmDc" class="textarea"  cols="300" rows="30"  style="width:450px;"  readonly title="도움말 설명"><c:out value="${result.hpcmDc}"/>
				      </textarea>                                    
				    </td>
				  </tr> 
				
				  <tr> 
				    <th scope="row" height="23" class="required_text" >등록일자&nbsp;&nbsp;</th>
				    <td>
				    	<c:out value="${result.lastUpdusrPnttm}"/>
				    </td>
				  </tr> 
				   
				</table>
				
				<p class="tr">
					<button type="button" class="btn btn-default" onclick="fn_egov_inqire_hpcmlist(); return false;"><spring:message code="button.list" /></button>
					<button type="button" class="btn btn-success" onclick="fn_egov_updt_hpcmcn('<c:out value="${result.hpcmId}"/>'); return false;"><spring:message code="button.update" /></button>
					<button type="button" class="btn btn-danger" onclick="fn_egov_delete_hpcmcn('<c:out value="${result.hpcmId}"/>'); return false;"><spring:message code="button.delete" /></button>
				</p>
	
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="10px"></td>
				</tr>
				</table>
				
				
				<input name="hpcmId" type="hidden" value="">
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
