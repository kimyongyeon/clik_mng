<%
 /**
  * @Class Name : EgovSiteInfoUpdt.jsp
  * @Description : EgovSiteInfoUpdt 화면
  *  
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>관련사이트 상세정보</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="siteManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_siteinfo(){

    // 첫 입력란에 포커스..
    siteManageVO.siteNm.focus();
    
    detailCodeChange(0);
    
    
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fnUpdtSiteinfo(form, siteId){

     if (!validateSiteManageVO(form)) {
         return;
         
     } else {
    	 if(confirm("수정 하시겠습니까?")){
             $('input[name=siteId]').val(siteId);
             
             form.action = "<c:url value='/sit/rls/SiteInfoUpdt.do'/>";
             form.submit();
    	 } else {
    		 return;
    	 }
     } 
        
}

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fnInqireSitelist() {

    siteManageVO.action = "<c:url value='/sit/rls/SiteListInqire.do'/>";
    siteManageVO.submit();
        
}

/* ********************************************************
 * 삭제처리화면
 ******************************************************** */
function fnDeleteSiteinfo(form, siteId){   

    if  (confirm("<spring:message code="common.delete.msg" />"))    { 
            
        // Delete하기 위한 키값(wordId)을 셋팅
        form.siteId.value = siteId;  
        form.action = "<c:url value='/sit/rls/SiteInfoDelete.do'/>";
        form.submit();   
    }       
    
}

/* ********************************************************
* multi selectbox 
******************************************************** */
function detailCodeChange(type) {
	var codeId = $("select[name='siteThemaClCode'] option:selected").val();
   
    $("select[name='siteThemaClDetailCode'] option").remove();
   
	$.ajax({
		type: "POST",
	    url : "/sit/rls/AjaxSiteDetailCode.do",
	    data : "codeId="+codeId,
	    dataType:"json", 
	    success: function(args) {
		    $('#siteThemaClDetailCode').attr('disabled', false);
		    $("#siteThemaClDetailCode").prepend("<option value='0'>분류선택</option>");
		    $.each(args, function() {
		    	$("#siteThemaClDetailCode").append("<option value='"+this.code+"'>"+this.codeNm+"</option>");
				if(type==0){
					$("#siteThemaClDetailCode").val("${result.siteThemaClDetailCode}").attr("selected", "selected");					
				}		    	
		    });
	   }
	   		,error:function(e) {
   			alert(e.responseText);
	   }
	});

}


</script>
</head>
<body onLoad="fn_egov_initl_siteinfo();">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"> 관련사이트 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->

	<DIV id="main" style="display:" class="">
		<DIV class="">
			<h2>
				 관련사이트 상세정보
			</h2>
			<!-- /.panel-heading -->			
			<DIV id="content" class="">
				<!-- 상단타이틀 -->
				<form:form commandName="siteManageVO" action="" method="post"> 
				      				
				<!--  줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
				    <td height="3px"></td>
				</tr>
				</table>
				
				<!-- 등록  폼 영역  -->
				<table width="98%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover dataTable no-footer" summary="사이트 정보테이블">

				  <tr> 
				    <th height="23" class="required_text" ><label id="idSiteThemaClCode" for="siteThemaClCode">카테고리</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
					    <td>
					    	<select name="siteThemaClCode" id="siteThemaClCode" class="form-control" onchange="detailCodeChange(1);">
					    		<option value="0">카테고리</option>
					    		<c:forEach items="${codeList}" var="codeList" varStatus="status">
					    			<option value="${codeList.codeId}" <c:if test="${codeList.codeId == result.siteThemaClCode}">selected</c:if>><c:out value="${codeList.codeIdNm}" /></option>
					    		</c:forEach>
					    	</select>
					    </td>
				  </tr> 
				  
				  <tr> 
				    <th height="23" class="required_text" ><label id="idSiteThemaClCode" for="siteThemaClCode">분류선택</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
					    <td>
					    	<select name="siteThemaClDetailCode" id="siteThemaClDetailCode" class="form-control">
					    		<option disabled="disabled" value="0">분류선택</option>
					    	</select>			    	
					    </td>
				  </tr> 				  
				
				  <tr> 
				    <th width="20%" height="23" class="required_text" nowrap ><label id="idSiteNm" for="siteNm">사이트 명</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
				    <td width="80%" nowrap  colspan="2">  
				        <form:input path="siteNm" size="70" maxlength="70" class="form-control input-sm" value="${result.siteNm}" />
				        <div><form:errors path="siteNm"/></div>         
				    </td>
				  </tr>
				  
				  <tr> 
				    <th height="23" class="required_text" ><label id="idSiteUrl" for="siteUrl">URL</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
				    <td colspan="2">
				        <form:input path="siteUrl" size="70"  class="form-control input-sm" value="${result.siteUrl}" /> 
				        <div><form:errors path="siteUrl"/></div>               
				    </td>
				  </tr> 
				
				  <tr> 
				    <th height="23" class="required_text" ><label id="idActvtyAt" for="actvtyAt">게시여부</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
				    <td colspan="2">
				        게시:   <input type="radio" name="actvtyAt" class="radio2" value="Y" <c:if test="${result.actvtyAt eq 'Y'}"> checked</c:if>>&nbsp;
				        미게시: <input type="radio" name="actvtyAt" class="radio2" value="N" <c:if test="${result.actvtyAt eq 'N'}"> checked</c:if>>     
				    </td>
				  </tr> 
				  
				  <tr> 
				    <th height="23" class="required_text" >등록일&nbsp;&nbsp;</th>
				    <td width="80%" colspan="2" nowrap>
				        <c:out value="${result.lastUpdusrPnttm}"/>  
				    </td>
				  </tr> 
				  				  
				</table>
			
				
				<input name="siteId" id="siteId" type="hidden" value="">
				
				</form:form>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->		
		<p class="tr">
			<!--
			<button type="button" class="btn btn-primary" onclick="fn_egov_regist_siteinfo(); return false;">등록</button>
			-->	
			<button type="button" class="btn btn-default" onclick="fnInqireSitelist(); return false;">목록</button>		
			<button type="button" class="btn btn-success" onclick="fnUpdtSiteinfo(document.forms[0], '<c:out value="${result.siteId}" />'); return false;">수정</button>
			<button type="button" class="btn btn-danger" onclick="fnDeleteSiteinfo(document.forms[0], '<c:out value="${result.siteId}"/>'); return false;">삭제</button>		
		
			
		</p>							
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->
</body>
</html>
