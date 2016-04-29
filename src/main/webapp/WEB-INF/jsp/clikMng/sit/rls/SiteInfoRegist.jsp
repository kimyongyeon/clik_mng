<%
 /**
  * @Class Name : EgovSiteInfoRegist.jsp
  * @Description : EgovSiteInfoRegist 화면
  * @Modification Information
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
<title>관련사이트 등록</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="siteManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fnInitlSiteinfo(){

    // 첫 입력란에 포커스..
    siteManageVO.siteNm.focus(); 
    
    document.getElementsByName('actvtyAt')[0].checked = true;
    //document.getElementsByName('useAt')[0].checked = true;

}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fnRegistSiteinfo(form){
	
	if($("select[name='siteThemaClCode']").val() == "0"){
		alert("카테고리를 선택해 주세요.");
		return;
	}
	if($("select[name='siteThemaClDetailCode']").val() == "0"){
		alert("분류를 선택해 주세요.");
		return;
	}
	if($("input[name='siteNm']").val() == null || $("input[name='siteNm']").val() == ""){
		alert("사이트 명을 입력해 주세요.");
		return;
	}
	
	if(confirm("등록 하시겠습니까?")){
		form.siteThemaClCode.value = fnSelectBoxValue("siteThemaClCode");
		form.action = "<c:url value='/sit/rls/SiteInfoRegist.do'/>";
		form.submit();
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
* SELECT BOX VALUE FUNCTION
******************************************************** */
function fnSelectBoxValue(sbName)
{
    var FValue = "";
    for(var i=0; i < document.getElementById(sbName).length; i++)
    {
        if(document.getElementById(sbName).options[i].selected == true){
            
            FValue=document.getElementById(sbName).options[i].value;
        }
    }
    
    return  FValue;
}



/* ********************************************************
* multi selectbox 
******************************************************** */
function detailCodeChange() {
	var codeId = $("select[name='siteThemaClCode'] option:selected").val();
	
	// 지역선택 셀렉트박스 초기화
	$("select[name='siteThemaClDetailCode'] option").remove();
	
	$.ajax({
		type: "POST",
	    url : "/sit/rls/AjaxSiteDetailCode.do",
	    data : "codeId="+codeId,
	    dataType:"json", 
	    async : false,
	    success: function(args) {
		    $('#siteThemaClDetailCode').attr('disabled', false);
		    $("#siteThemaClDetailCode").prepend("<option value='0'>분류선택</option>");
		    $.each(args, function() {
		    	$("#siteThemaClDetailCode").append("<option value='"+this.code+"'>"+this.codeNm+"</option>");
		    });
	   }
	   		,error:function(e) {
   			alert(e.responseText);
	   }
	});
}


</script>
</head>
<body onLoad="fnInitlSiteinfo();">
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
				 관련사이트 등록
			</h2>
			<!-- /.panel-heading -->			
			<DIV id="content" class="">
			<!--  상단타이틀 -->
			<form:form commandName="siteManageVO" action="" method="post"> 
			      			
			<!-- 줄간격조정  -->
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
			    <td height="3px"></td>
			</tr>
			</table>
			
			<!-- 등록  폼 영역  -->
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover dataTable no-footer" summary="사이트정보등록테이블">
			  <tr> 
			    <th height="23" class="required_text" ><label id="idSiteThemaClCode" for="siteThemaClCode">카테고리</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
			    <td>
			    	<select name="siteThemaClCode" id="siteThemaClCode" class="form-control" onchange="detailCodeChange();">
			    		<option value="0">카테고리 선택</option>
			    		<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			    			<option value="${resultInfo.codeId}"><c:out value="${resultInfo.codeIdNm}" /></option>
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
			        <form:input path="siteNm" size="70" maxlength="70" class="form-control input-sm" />
			        <div><form:errors path="siteNm"/></div>         
			    </td>
			  </tr>
			  
			  <tr> 
			    <th height="23" class="required_text" ><label id="idSiteUrl" for="siteUrl">URL</label></th>
			    <td colspan="2">
			        <form:input path="siteUrl" size="70" maxlength="70" class="form-control input-sm" value="http://"/>
			        <div><form:errors path="siteUrl"/></div>               
			    </td>
			  </tr> 
			
			  <tr> 
			    <th height="23" class="required_text" ><label id="idActvtyAt" for="actvtyAt">게시여부</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
			    <td colspan="2">
			    	게시 : <input type="radio" name="actvtyAt" class="radio2" value="Y" checked>&nbsp;
			    	미게시 : <input type="radio" name="actvtyAt" class="radio2" value="N">     
			    </td>
			  </tr> 
			   
			   
			</table>
			
			</form:form>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->		
		<p class="tr">
			<button type="button" class="btn btn-default" onclick="javascript:fnInqireSitelist(); return false;">목록</button>
			<button type="button" class="btn btn-primary" onclick="fnRegistSiteinfo(document.forms[0]); return false;">등록</button>		
		</p>							
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->

</body>
</html>
