<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<jsp:useBean id="now" class="java.util.Date" />
<%
 /**
  * @Class Name : OrgList.jsp
  * @Description : 협정기관 목록 조회 화면
  * @Modification Information
  *  
  * Copyright (C) All right reserved.
  */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>협정기관 조회</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javaScript" defer="defer">

<!--
/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.Form;
    varForm.currentPageNo.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/uss/org/OrgSearchList.do'/>";
    varForm.submit();
}
/* ********************************************************
 * 검색하기
 ******************************************************** */
function Search(){
	var varForm = document.Form;
    varForm.currentPageNo.value = "1";
	varForm.action = "<c:url value='/uss/org/OrgSearchList.do' />";
    varForm.target="_self";
	varForm.submit();
}

/* ********************************************************
* 수정화면
******************************************************** */
function fn_update(name){
	var varForm = document.Form;
	varForm.update_name.value = name;
	varForm.target="_self";
	varForm.action = "<c:url value='/uss/org/OrgDetailView.do' />";
	varForm.submit();
}

/* ********************************************************
* MacAddress관리
******************************************************** */
function fn_mac_mng(names, class1, class2){
    var winName = "MacAddressMng";
    var w = 1020;
    var h = 700;
    var x = 5;
    var y = 5;
    var menubar = "no" ; 
    var toolbar = "no" ;
    var locat = "no" ; 
    var scrollbars = "yes" ; 
    var status = "no" ; 
    winOpens("about:blank",winName,w,h,x,y,menubar,toolbar,locat,scrollbars,status) ; 
	
    var varForm = document.Form;
    varForm.names.value = names;
    varForm.class1.value = class1;
    varForm.class2.value = class2;
    varForm.action = "<c:url value='/oppop/MacAddressMng.do' />";
    varForm.target = winName;
    varForm.submit();       
}

/* ********************************************************
 * 관리자화면
 ******************************************************** */
function fn_manager(names){
    var winName = "CoopMng";
    var w = 1000;
    var h = 350;
    var x = 5;
    var y = 5;
    var menubar = "no" ; 
    var toolbar = "no" ;
    var locat = "no" ; 
    var scrollbars = "yes" ; 
    var status = "no" ; 
    winOpens("about:blank",winName,w,h,x,y,menubar,toolbar,locat,scrollbars,status) ; 
    
    var varForm = document.Form;
    varForm.names.value = names;
    varForm.action = "<c:url value='/oppop/CoopMng.do' />";
    varForm.target = winName;
    varForm.submit();       
}
/* ********************************************************
* Validation Check
******************************************************** */
function fn_valid(){
    var varForm = document.Form;
    varForm.search.setAttribute('check','true');
    varForm.search.setAttribute('dpname','검색어');

    return checkForm(varForm);
}

// -->
</script>

</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
            <h1 class="page-header">협정기관 조회</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
   	
	<div id="border" style="display:" class="">

		<DIV class=" ">
			<h2>
				 협정기관 목록 
			</h2>
			<!-- /.panel-heading -->			
			<DIV id="content" class="">	
			<form method="post" name ="Form" action="">
				 <input type='hidden' name="names">
			    <input type='hidden' name="class2">
			    <input type='hidden' name="id">
			    <input type='hidden' name="class1">
			    <input type="hidden" name="update_name" >
			     <!-- 페이징 번호 -->
		        <input type="hidden" name="currentPageNo" value="<c:out value='${ paginationInfo.currentPageNo }'/>" >	
				<div class="select_box">
					<span>
						 <select name="criteria" class="widthD" title="검색조건">      
				               <option value='name' <c:if test="${criteria=='name'}">selected</c:if>>약정체결기관명</option>
				               <option value='agree_name' <c:if test="${criteria=='agree_name'}">selected</c:if>>협정체결기관명</option>
				               <option value='reg_date' <c:if test="${criteria=='reg_date'}">selected</c:if>>등록일(형식:YYYY-MM-DD)</option>
				               <option value='charge' <c:if test="${criteria=='charge'}">selected</c:if>>협정담당자명</option>
				               <option value='contract_no' <c:if test="${criteria=='contract_no'}">selected</c:if>>약정일련번호</option>
				               <option value='user_name' <c:if test="${criteria=='user_name'}">selected</c:if>>이용자ID</option>
				               <option value='mgr_name' <c:if test="${criteria=='mgr_name'}">selected</c:if>>국회관리자명</option>
				               <option value='old_name' <c:if test="${criteria=='old_name'}">selected</c:if>>과거기관명</option>
				           </select>
	           			   <input type='text' name='search' class="text" value="<c:out value='${ search }'/>" onKeyDown="javascript:if(event.keyCode==13){Search();}">
	           			   <a href="#LINK" onclick="javascript:Search(); return false;"><button type="button" class="btn btn-primary"><spring:message code="button.inquire" /></button></a>
					</span>
				</div>		
				
			   
		        
		       
		        
				
				
				
				
	            <table width="100%" cellpadding="8" class="table table-striped table-bordered" border="0" summary="협정기관 목록입니다.">
	           	<thead>
				<tr>      
				    <th scope="col" class="title" width="7%" nowrap>본관</th>        
				    <th scope="col" class="title" width="38%" nowrap>기관명</th>                   
				    <th scope="col" class="title" width="15%" nowrap>구분</th>
				    <th scope="col" class="title" width="15%" nowrap>기관담당자</th>        
				    <th scope="col" class="title" width="15%" nowrap>등록일</th>
				</tr>
				</thead>
				
				<c:if test="${empty list}">
				<tr> 
					<td class="lt_text3" colspan="5">
				  		<spring:message code="common.nodata.msg" />
				  	</td>
				</tr>   	          				 			   
				</c:if>
				 
				<tbody>      
				<c:forEach items="${list}" var="x" varStatus="s">
					<tr>
						<td class="lt_text3">${ x.main_yn }</td>	        
						<c:choose>
	                    	<c:when test="${criteria=='agree_name'}">
	                    		<td class="lt_text3"><a href="JavaScript: fn_update('${x.name}');">${ x.agree_name }</a></td>
	                    	</c:when>
	                   		<c:otherwise>
	                   			<td class="lt_text3"><a href="JavaScript: fn_update('${x.name}');">${ x.name }</a></td>
	                   		</c:otherwise>
                   		</c:choose>
                   		<td class="lt_text3">
				        <c:choose>
				            <c:when test="${ x.class eq '1' }"><c:out value="협정기관" /></c:when>
				            <c:when test="${ x.class eq '2' }"><c:out value="국회기관" /></c:when>           
				            <c:otherwise><c:out value="지정안됨" /></c:otherwise>
				        </c:choose>          
				        </td>         		
						<td class="lt_text3">${ x.charge }</td>
						<td class="lt_text3">${ x.reg_date }</td>		
					</tr>   
				</c:forEach>
				</tbody> 				 		
	            </table>				
				
						
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
		        <div align ="center">
		            <c:if test="${not empty list}">
		            <ul class="pagination">
		                <ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_paging" />
		            </ul>
		            </c:if>
		        </div>			
				
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
