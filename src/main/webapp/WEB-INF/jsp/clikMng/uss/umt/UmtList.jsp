<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>이용자 조회</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>

<script type="text/javaScript" language="javascript">
<!-- 달력 -->
$( document ).ready(function() {
    $(function() {
        $('#select_regdate').datepicker({
        	dateFormat: 'yy-mm-dd',
        	changeYear: true,
        	changeMonth: true, 
        	showMonthAfterYear: true,
        	yearRange: '-100:+0',
            dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
            dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
            monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']        	
        });
        $('#select_birth').datepicker({
        	dateFormat: 'yy-mm-dd',
        	changeYear: true,
        	changeMonth: true, 
        	showMonthAfterYear: true,
        	yearRange: '-100:+0',
            dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
            dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
            monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']  
        }); 
    });
    
	document.getElementById("select_regdate").onkeyup = function(e){
		//방향키와 백스페이스는 이벤트를 실행하지 않는다.
		if(event.keyCode != 37 && event.keyCode != 38 && event.keyCode != 39 && event.keyCode != 40 && event.keyCode != 8){
			dateFormatter(document.getElementById("select_regdate"));
		}
	}

	document.getElementById("select_birth").onkeyup = function(e){
		//방향키와 백스페이스는 이벤트를 실행하지 않는다.
		if(event.keyCode != 37 && event.keyCode != 38 && event.keyCode != 39 && event.keyCode != 40 && event.keyCode != 8){
			dateFormatter(document.getElementById("select_birth"));
		}
	}
	
});    
</script>
<!-- /달력 -->

<script type="text/javaScript" defer="defer">
/*
	날짜포맷 javascript
*/
function dateFormatter(obj){
	
	var v = obj.value;
	var dateMatching = /^(19|20)\d{2}-(0[1-9]|1[0-2])-([0-2]\d|3(0|1))$/	;
	//변환될 숫자만 모으기
	var v2 = v.replace(/-/g,"").replace(/[^0-9]/g,"");
	
	//입력값중 숫자만 남기고 문자 삭제
	v = v.replace(/[^0-9]/g,"");
	
	//-붙이고 yyyy-mm-dd 포맷보다 길 경우 잘라내기
	if(v2.length > 4)
		v = v2.substring(0,4) + "-" + v2.substring(4);

	if(v2.length > 6)
		v = v.substring(0,7) + "-" + v.substring(7);
	
	if(v2.length >= 8)
		v = v.substring(0,10);

	obj.value = v;
}

/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.SearchForm;
    varForm.currentPageNo.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/uss/umt/UmtSearchList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 조회
******************************************************** */
function fn_search(){
	if(fn_valid()){
	    var varForm = document.SearchForm;
	    varForm.currentPageNo.value = "1";
	    varForm.target="_self";
	    varForm.action = "<c:url value='/uss/umt/UmtSearchList.do' />";
	    varForm.submit();
	}else{
		alert('검색조건이 없습니다.');
		return false;
	}
}

function fn_valid(){
    var varForm = document.SearchForm;
    if(!checkDate(trim(varForm.select_birth.value))
            && !checkDate(trim(varForm.select_regdate.value))
    	    && !validateCheck(trim(varForm.select_userid.value),'required')
    	    && !validateCheck(trim(varForm.select_username.value),'required')
            && !validateCheck(trim(varForm.select_mail.value),'required')
            && !validateCheck(trim(varForm.select_birth.value),'required')
            && !validateCheck(trim(varForm.select_regdate.value),'required')
    ){
        return false;
    }
    return true;
}


function checkDate(date){
	var result=false;
	var reg = /^(19|20)\d{2}-(0[1-9]|1[0-2])-([0-2]\d|3(0|1))$/	;
	if(!!date){
		if(reg.test(date)){
			result=true;
		}else{
			alert('날자 형식은 yyyy-mm-dd(ex: 1944-03-01)과 같이 입력해주세요.');
			result=false;
		}
	}
	return result;
}

/* ********************************************************
* 수정페이지
******************************************************** */
function fn_edit(userid){
    var varForm = document.SearchForm;
    varForm.userid.value = userid;
    varForm.target = "_self";
    varForm.action = "<c:url value='/uss/umt/UmtDetailView.do' />";
    varForm.submit();
}

// -->
</script>

</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
            <h1 class="page-header">이용자 조회</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
   	
	<div id="border" style="display:" class="">

		<DIV class="">
			<h2> 
				 이용자 목록
			</h2>
			<!-- /.panel-heading -->			
			<DIV id="content" class="">			
				<form name="SearchForm" method="post">
		        <input type="hidden" name="userid" />
		        <input type="hidden" name="select_db" value="2" /> 			<!-- // DB : 통합아이디DB  -->
		        <input type="hidden" name="select_classtype" value="0" />	<!-- //그룹 : 일반사용자  -->
		        
		        <!-- 페이징 번호 -->
		        <input type="hidden" name="currentPageNo" value="<c:out value='${ paginationInfo.currentPageNo }'/>" >	
		        
				<table width="100%" cellpadding="8" class="table  table-bordered table-search" border="0"> 
				<colgroup>
            	<col width="15%" >
            	<col width="35%" >
            	<col width="15%" >
            	<col width="35%" >
            	</colgroup>
		            <tr>
		                <td ><span class="strong">아이디</span></td>
		                <td>
		                    <label for="select_userid" class="hidden">아이디</label>
		                    <input type="text" id="select_userid" name="select_userid" class="input-sm ip "  style="width:50%;" value="<c:out value='${ select_userid }'/>" >
		                </td>
		                <td ><span class="strong">이름</span></td>
		                <td>
		                    <label for="select_username" class="hidden">이름</label>
		                    <input type="text" id="select_username" name="select_username" class="input-sm ip "  style="width:50%;" value="<c:out value='${ select_username }'/>" >
		                </td>
		            </tr>
		            <tr>
		                <td ><span class="strong">이메일</span></td>
		                <td>
		                    <label for="select_mail" class="hidden">이메일 </label>
		                    <input type="text" id="select_mail" name="select_mail" class="input-sm ip "  style="width:50%;" value="<c:out value='${ select_mail }'/>" >
		                </td>
		                <td ><span class="strong">생년월일</span></td>
		                <td>
		                    <label for="select_birth" class="hidden">생년월일</label>
		                    <input type="text" id="select_birth" name="select_birth" class="input-sm ip "  style="width:50%;" value="<c:out value='${ select_birth }'/>">
		                </td>
		            </tr>
		            <tr>
		                <td ><span class="strong">처리일</span></td>
		                <td>
		                    <label for="select_regdate" class="hidden">등록일 </label>
		                    <input type="text" id="select_regdate" name="select_regdate" class="input-sm ip "  style="width:50%;"  value="<c:out value='${ select_regdate }'/>">
		                </td>
		                <td ><span class="strong">쪽당출력건수</span></td>
		                 <td>  
		                    <select title="쪽당출력건수" id="select_countperpg" name="select_countperpg"    class="input-sm">
		                        <option value='10' <c:if test="${empty select_countperpg || select_countperpg == '' || select_countperpg == '10'}">selected</c:if>>10</option>
		                        <option value='30' <c:if test="${select_countperpg == '30'}">selected</c:if>>30</option>
		                        <option value='50' <c:if test="${select_countperpg == '50'}">selected</c:if>>50</option>
		                        <option value='100' <c:if test="${select_countperpg == '100'}">selected</c:if>>100</option>
		                    </select> 
		                </td>
		            </tr>
		            <tr>
		                <td ><span class="strong">정렬</span></td>
		                <td colspan="3">
	                       <select title="정렬" id="select_orderfield" name="select_orderfield"  class="input-sm">
	                           <option value='user_id' <c:if test="${empty select_orderfield || select_orderfield == '' || select_orderfield == 'user_id'}">selected</c:if>>아이디</option>
	                           <option value='reg_date' <c:if test="${select_orderfield == 'reg_date'}">selected</c:if>>처리일</option>
	                       </select>
		                    <select title="정렬2" id="select_ordersort" name="select_ordersort"  class="input-sm">
		                        <option value='ASC' <c:if test="${empty select_ordersort || select_ordersort == '' || select_ordersort == 'ASC'}">selected</c:if>>오름차순</option>
		                        <option value='DESC' <c:if test="${select_ordersort == 'DESC'}">selected</c:if>>내림차순</option>
		                    </select>
		                </td>
		            </tr>
				</table>
				
				<p class="tr">						
		            <a href="#LINK" onclick="javascript:fn_search(); return false;"><button type="button" class="btn btn-primary"><spring:message code="button.inquire" /></button></a>
				</p>
				
				
	           <table width="100%" cellpadding="8" class="table table-striped table-bordered" border="0" summary="이용자 목록입니다.">
	           	<thead>
				<tr>      
				    <th scope="col" class="title" width="14%" nowrap>번호</th>         
				    <th scope="col" class="title" width="20" nowrap>아이디</th>                   
				    <th scope="col" class="title" width="15%" nowrap>이름</th>
				    <th scope="col" class="title" width="18%" nowrap>생년월일</th>        
				    <th scope="col" class="title" width="20%" nowrap>이메일</th>
				    <th scope="col" class="title" width="18%" nowrap>처리일</th>               
				</tr>
				</thead>
				
				 <c:if test="${fn:length(list) == 0}">
				  <tr> 
				  	<td class="lt_text3" colspan="6">
				  		<spring:message code="common.nodata.msg" />
				  	</td>
				  </tr>   	          				 			   
				 </c:if>
				 
				<tbody>      
				<c:forEach items="${list}" var="x" varStatus="s">
					<tr>
						<td class="lt_text3">${firstRecordIndex + s.count }</td>	        
						<td class="lt_text3"><a href="JavaScript: fn_edit('${x.userid}');">${ x.userid }</a></td>
						<td class="lt_text3">${x.user_name }</td>
						<td class="lt_text3">${x.birth }</td>		
						<td class="lt_text3">${x.user_mail }</td>
						<td class="lt_text3">${x.reg_date }</td>
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
		            <div class="page_number">
		            	<ul class="pagination">
		                	<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_paging" />
		                </ul>
		            </div>
		            </c:if>
		        </div>				
				
			</DIV>
			</form>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->	 
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>
