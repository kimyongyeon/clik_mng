<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>이용자 상세보기</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>" ></script>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_list() {
    var varForm = document.Form;
    varForm.action = "<c:url value='/uss/umt/UmtSearchList.do'/>";
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
            <h1 class="page-header">이용자 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
   	
	<div id="border" style="display:" class="">
 
		<DIV class=" ">
			<h2>
				 이용자 상세보기
			</h2>
			<!-- /.panel-heading -->			
			<DIV id="content" class="">
				
				<!-- 상단타이틀 -->
				<form name="Form" method="post" action="">
				<input type="hidden" name="menu_id" value="<c:out value='${ menu_id }'/>" >
			    <input type="hidden" name="select_db" value="<c:out value='${ select_db }'/>" >
			    <input type="hidden" name="userid" value="<c:out value='${ info.userid }'/>" >
			    <input type="hidden" name="class" value="<c:out value='${ info.class }'/>" >
			    <input type='hidden' name="level" value="<c:out value='${ info.level }'/>">
			    <input type='hidden' name="no_seq" value="<c:out value='${ info.no_seq }'/>">
			
			    <input type="hidden" name="select_classtype" value="<c:out value='${ select_classtype }'/>" >
			    <input type="hidden" name="select_userid" value="<c:out value='${ select_userid }'/>" >
			    <input type="hidden" name="select_username" value="<c:out value='${ select_username }'/>" >
			    <input type="hidden" name="select_mail" value="<c:out value='${ select_mail }'/>" >
			    <input type="hidden" name="select_birth" value="<c:out value='${ select_birth }'/>" >
			    <input type="hidden" name="select_regdate" value="<c:out value='${ select_regdate }'/>" >
			    <input type="hidden" name="select_orderfield" value="<c:out value='${ select_orderfield }'/>" >
			    <input type="hidden" name="select_ordersort" value="<c:out value='${ select_ordersort }'/>" >
				
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<!-- 등록  폼 영역  -->
				<table width="98%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered" summary="이용자 상세정보를  조회합니다.">
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >클래스&nbsp;&nbsp;</th>
		            <td width="80%" nowrap>
				          <c:if test="${info.class == '0'}">일반이용자</c:if>
		            </td>				    
				  </tr>
				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="userid">아이디</label>&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> ${ info.userid } </td>
				  </tr>
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="user_name">이름</label>&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> <c:out value="${ info.user_name }"/> </td>
				  </tr>
				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >등록날짜&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> <c:out value="${ info.reg_date }"/> </td>
				  </tr>
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >생년월일&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> <c:out value="${ info.birth }"/> </td>
				  </tr>
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >직업&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
                        <select name="job" title="직업">
                            <option value="" <c:if test="${info.job == ''}">selected</c:if>>--직업 선택--</option>
                            <option value="고등학생이하"   <c:if test="${info.job == '고등학생이하'}">selected</c:if>>고등학생이하</option>
                            <option value="대학생"         <c:if test="${info.job == '대학생'}">selected</c:if>>대학생</option>
                            <option value="대학원생"       <c:if test="${info.job == '대학원생'}">selected</c:if>>대학원생</option>
                            <option value="회사원"         <c:if test="${info.job == '회사원'}">selected</c:if>>회사원</option>
                            <option value="공무원"         <c:if test="${info.job == '공무원'}">selected</c:if>>공무원</option>
                            <option value="교직원"         <c:if test="${info.job == '교직원'}">selected</c:if>>교직원</option>
                            <option value="의료인"         <c:if test="${info.job == '의료인'}">selected</c:if>>의료인</option>
                            <option value="법조인"         <c:if test="${info.job == '법조인'}">selected</c:if>>법조인</option>
                            <option value="언론인"         <c:if test="${info.job == '언론인'}">selected</c:if>>언론인</option>
                            <option value="종교인"         <c:if test="${info.job == '종교인'}">selected</c:if>>종교인</option>
                            <option value="연구원"         <c:if test="${info.job == '연구원'}">selected</c:if>>연구원</option>
                            <option value="교수"           <c:if test="${info.job == '교수'}">selected</c:if>>교수</option>
                            <option value="자영업"         <c:if test="${info.job == '자영업'}">selected</c:if>>자영업</option>
                            <option value="농/수/축산업"   <c:if test="${info.job == '농/수/축산업'}">selected</c:if>>농/수/축산업</option>
                            <option value="주부"           <c:if test="${info.job == '주부'}">selected</c:if>>주부</option>
                            <option value="기타"           <c:if test="${info.job == '기타'}">selected</c:if>>기타</option>
                        </select>
                    </td>
				  </tr>
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >이메일&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> 
                        <input type="text" id="user_mail" name="user_mail" class="text widthC" value="<c:out value='${ info.user_mail }'/>" >
                    </td>
				  </tr>	
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >전화번호&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> 
                        <input type="text" id="user_mail" name="user_mail" class="text widthC" value="<c:out value='${ info.user_tel }'/>" >
                    </td>
				  </tr>		
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >우편번호&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> 
                        <input type="text" id="user_mail" name="user_mail" class="text widthC" value="<c:out value='${ info.zipcode }'/>" >
                    </td>
				  </tr>				  		

				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >주소&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> 
                        <input type="text" readonly = "readonly" id="address" name="address" class="text widthD" size="50" value="<c:out value='${ info.address }'/>" ><br>
                        <input type="text" readonly = "readonly" id="address_detail" name="address_detail" class="text widthD" size="50" value="<c:out value='${ info.address_detail }'/>" >
                    </td>
				  </tr>		
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >가입서비스&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> 
                        <select name='use_system' title="가입서비스">
                            <option value='1100000000' <c:if test="${info.use_system == '1100000000'}">selected</c:if>>통합</option>
                            <option value='1000000000' <c:if test="${info.use_system == '1000000000'}">selected</c:if>>전자도서관</option>
                            <option value='0100000000' <c:if test="${info.use_system == '0100000000'}">selected</c:if>>출입</option>
                        </select>  
                    </td>
				  </tr>					  			  
				  
	              <c:if test="${not empty info.certify_cd}">
	              <tr>
	              	<th scope="row" width="20%" height="23" class="required_text" nowrap >본인인증&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
					        <c:out value='${ info.certify_cd}'/>
					        <p>※ D:전자도서관(인증안됨), I:ipin, V:가상식별코드</p>
					</td>
				  </tr>
	              </c:if>   
				</table>
				
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="5px"></td>
				</tr>
				</table>
				
				</form>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->	 
		
		<p class="tr">
			<button type="button" class="btn btn-default" onclick="fn_list(); return false;"><spring:message code="button.list" /></button>	
		</p>
		
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->	

</body>
</html>
