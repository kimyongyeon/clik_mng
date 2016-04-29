<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
 
<title>그룹 검색 팝업</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-mobile-web-app-capable" content="yes" />

<script language="javascript" src="<c:url value='/js/clikmng/jquery-1.10.2.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/bootstrap.min.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/jquery.metisMenu.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/sb-admin.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/jquery-ui.min.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/cmm/common.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/respond_src.js' />"></script>

<!-- Core CSS - Include with every page -->
<link href="<c:url value='/css/clikmng/bootstrap.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/jquery-ui.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/sb-admin.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/font-awesome.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/list.css' />" rel="stylesheet" type="text/css">

<style type="text/css">
/**/

body.popup{ position:relative; overflow:hidden; padding:0; background:#fff;}
div.today{  position:absolute;left:0; bottom:0; background:#f1f1f1; padding:10px; text-align:right; width:100%; border-top:1px solid #ccc;}
div.today label{color:#333;}
div.today a{color:#333;}

div.table-responsive{ }
h1{ margin-left:10px; font-size:18px; font-weight:bold;}
</style>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
* 체크박스
******************************************************** */
$(function(){
        //전체선택 체크박스 클릭
        $("#checkAll").click(function(){
            //만약 전체 선택 체크박스가 체크된상태일경우
            if($("#checkAll").prop("checked")) {
                //해당화면에 전체 checkbox들을 체크해준다
                $("input[type=checkbox]").prop("checked",true);
            // 전체선택 체크박스가 해제된 경우
            } else {
                //해당화면에 모든 checkbox들의 체크를해제시킨다.
                $("input[type=checkbox]").prop("checked",false);
            }
        });
});


/* ********************************************************
* 검색
******************************************************** */
function fnSearch() {
	var varForm = document.listForm;	
	

	varForm.action = "<c:url value='/mim/PopSelectGroup.do' />";
	varForm.submit();
	
}

/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.pageIndex.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/mim/PopSelectGroup.do'/>";
    varForm.submit();
    
}

/* ********************************************************
* 선택한 그룹을 부모창으로 
******************************************************** */
function fnSelectGroup() {
    var varForm = document.listForm;

    //체크박스에 체크 여부 확인
  	if($("input[name=checks]").is(':checked') == false){
            alert("그룹을 체크하세요.");
            return;
    }else{
    	var valStr = "";
    	var jSonStr = new Array();
    	var name ="";
    	var email = "";
    	
        $("input:checkbox:checked").each(function (index) {
        	//alert($(this).val());
        	
        	if($(this).val() != "") {
            	// 그룹 구성원 조회.
            	var url="/mim/ajaxSearchGroup.do";  
    		    var params="emailGroupId="+ $(this).val();  
    		  
    		    $.ajax({      
    		    	type: "POST",
    		    	dataType:"json",
    		        url:url,      
    		        data:params,     
    		        async : false, 
    		        success:function(args){
    		        	opener.addTextarea(args);
    		        },   
    		        error:function(e){  
    		            alert(e.responseText);  
    		        }  
    		    });  
        	}
        });  

    }
    window.close();
    
}

-->
</script>



</head>
<body class="popup">
 <h1 class="">그룹검색</h1>
<!-- /.panel-heading -->
<form name="listForm" id="listForm" method="post">
<div class="panel-body ">
	<div class="search">
		
		<input type="text" name="searchKeyword" id="searchKeyword" value="<c:if test="${empty searchVO.searchKeyword }"></c:if><c:if test="${!empty searchVO.searchKeyword }"><c:out value='${searchVO.searchKeyword}'/></c:if>"  class="ip input-sm input-search" />
		<button type="button" class="btn btn-primary" onclick="fnSearch(); return false;"><spring:message code="button.search" /></button>
		
	</div><!--//tc-->

	<div class="table-responsive" style="">
		<table class="table table-striped table-bordered table-hover ">
			

			 <thead>
				<tr>
					<th>번호</th>
					<th>그룹명</th>
					<th><input type="checkbox" id="checkAll" name="checkAll" class="ip" value="" /></th>
				</tr>
			 </thead>
			
			<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td colspan="3"><spring:message code="common.nodata.msg" /></td>
				</tr>
			</c:if>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
		 
			<c:forEach var="x" items="${resultList}" varStatus="s">
	 		<tr>
			    <td>${(searchVO.pageIndex-1) * searchVO.pageSize + s.count}</td>
				<td><c:out value="${x.groupNm}" /></td>
				<td><input type="checkbox" name="checks" id="checks" class="ip" value="<c:out value="${x.emailGroupId}" />" /></td>
			</tr>
	 		</c:forEach>
			</tbody>

			
		</table>
	</div>
	<!-- /.table-responsive -->
</div>
<!-- /.panel-body -->

<p class="tr">
	<button type="button" class="btn btn-primary" onclick="fnSelectGroup(); return false;">선택</button>
	<button type="button" class="btn btn-default" onclick="window.self.close();">닫기</button>
</p>

<c:if test="${not empty resultList}">
<div align="center">
    <div>
    	<ul class="pagination">
	        <ui:pagination paginationInfo = "${paginationInfo}"
	            type="image"
	            jsFunction="fn_paging"
	            />
	     </ul>
    </div>
</div>
</c:if>

<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="currentPageNo" value="<c:out value='${ paginationInfo.currentPageNo }'/>" >
</form>
</body>
</html>
