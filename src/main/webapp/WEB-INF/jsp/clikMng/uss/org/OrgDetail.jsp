<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="clikmng.nanet.go.kr.cmm.LibraryInfoXmlUtil"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>협정기관 상세보기</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>" ></script>
<!-- 달력 -->
<!-- <script type="text/javascript">
window.onload = function() {
    $(function() {
        $('#reg_date').datepick();
    });
};
</script> -->
<!-- /달력 -->

<script type="text/javaScript" defer="defer">
<!--
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_list() {
    var varForm = document.InputForm;
    varForm.target = "_self";
    varForm.action = "<c:url value='/uss/org/OrgSearchList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 수정처리
******************************************************** */
function fn_update(){
	if(fn_valid()){

		var format = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
		if( !format.test(document.getElementById("reg_date").value) ){
			alert("협정체결 일자는 2009-01-01 형식으로 입력해야 합니다.");
			document.getElementById("reg_date").focus();
			return;
		}
		
	    if ( confirm('<spring:message code="common.update.msg" />') ) {
	        var varForm = document.InputForm;
	        varForm.target = "_self";
	        varForm.action = "<c:url value='/op/LibraryInfoUserUpdate.do'/>";
	        varForm.submit();
	    }           
	}
}

/* ********************************************************
* 삭제처리
******************************************************** */
function fn_delete() {
    if ( confirm('<spring:message code="common.delete.msg" />') ) {
        var varForm = document.InputForm;
        varForm.target = "_self";
        varForm.action = "<c:url value='/op/LibraryInfoUserDelete.do'/>";
        varForm.submit();
    }
}

/* ********************************************************
* 협정기관구분 노출여부
******************************************************** */
function fn_onchangeClass()
{
    var f = document.InputForm;
    var objStyle = document.getElementById("trClass2");

    if (f.select_class.value == "1"){
        objStyle.style.display = "block";
    }else{
        objStyle.style.display = "none";
    }
}       
/* ********************************************************
* 협정/약정이력보기
******************************************************** */
function fn_showHistory(category)
{
    var f = document.InputForm;
    var winName = "LibInfoShowHistoryView";
    var w = 740;
    var h = 500;
    var x = (screen.width / 2) - (w / 2);
    var y = (screen.height / 2) - (h / 2);
    var menubar = "no" ; 
    var toolbar = "no" ;
    var locat = "no" ; 
    var scrollbars = "no" ; 
    var status = "no" ; 
    winOpens("about:blank",winName,w,h,x,y,menubar,toolbar,locat,scrollbars,status) ; 
    f.target = winName;
    f.flag.value = category;
    f.action = "<c:url value='/uss/org/OrgUserInfoHistory.do'/>";
    f.submit();
    
}

/* ********************************************************
* Validation Check
******************************************************** */
function fn_valid(){
    var varForm = document.InputForm;

    //2012-08-06 권인애 : 협정기관관리 기관종류 구분 변경에 따른 추가 및 수정
    var org_div2=varForm.org_div2.value;
    if(varForm.org_div2.value=="0"){
		alert("기관종류를 선택해주세요");
		varForm.org_div2.focus;
		return false;
    }
    else{
    	if(org_div2 == "1") varForm.org_div.value=varForm.org_sub1.value;
    	else if(org_div2=="2") varForm.org_div.value=varForm.org_sub2.value;
    	else if(org_div2=="3") varForm.org_div.value=varForm.org_sub3.value;
    	else if(org_div2=="4") varForm.org_div.value=varForm.org_sub4.value;
	    varForm.agree_name.setAttribute('check','true');
	    varForm.agree_name.setAttribute('dpname','협정체결기관명');
	    varForm.reg_date.setAttribute('check','true');
	    varForm.reg_date.setAttribute('dpname','협정체결일자');
	    varForm.name.setAttribute('check','true');
	    varForm.name.setAttribute('dpname','약정체결기관명');
	    return checkForm(varForm);
    }
}

/* ********************************************************
* 검색PC목록보기 : 2011-10-25 황재철
******************************************************** */
function fn_showPCList()
{
    var f = document.InputForm;
    var winName = "PCListView";
    var w = 1000;
    var h = 600;
    var x = (screen.width / 2) - (w / 2);
    var y = (screen.height / 2) - (h / 2);
    var menubar = "no" ; 
    var toolbar = "no" ;
    var locat = "no" ; 
    var scrollbars = "yes" ; 
    var status = "no" ; 
    winOpens("about:blank",winName,w,h,x,y,menubar,toolbar,locat,scrollbars,status) ; 
    f.target = winName;
    //f.flag.value = category;
    f.action = "<c:url value='/uss/org/UserPCIDDupCheck.do'/>";
    f.submit();
    
}

/* ********************************************************
* PC아이디중복체크 (2012.01.20 AbsKim)
******************************************************** */
function fn_dupIDCheck(form){
	var userid   = trim(form.pcid_prefix.value);
	var username = trim(form.name.value);
	var pccount  = trim(form.pc_id_count.value);
	
	if(username != ""){
		if(userid != ""){
			if(pccount != "") {
			    var winName = "idDupCheckView";
			    var w = 200;
			    var h = 140;
			    var x = (screen.width / 2) - (w / 2);
			    var y = (screen.height / 2) - (h / 2);
			    var menubar = "no" ; 
			    var toolbar = "no" ;
			    var locat = "no" ; 
			    var scrollbars = "no" ; 
			    var status = "no" ; 
			    winOpens("about:blank",winName,w,h,x,y,menubar,toolbar,locat,scrollbars,status) ; 
			    var f = document.InputForm;
		        f.checkid.value   = userid;
		        f.checkname.value = username;	        
			    f.target = winName;
			    f.action = "<c:url value='/oppop/UserPCIDDupCheck.do'/>";
			    f.submit();
			} else {
				alert("먼저 검색PC대수를 입력하세요.");
				form.pc_id_count.focus();
			}
		}else{
			alert("먼저 검색PC아이디를 입력하세요.");
			form.pcid_prefix.focus();
		}
	}else{
		alert("먼저 약정체결기관명(센터)을 입력하세요.");
		form.name.focus();
	}		
}		

function fn_allchk() {
	var varForm = document.LibForm;
	var num=varForm.elements.length;
	var chk=false;
	if(varForm.all.checked ==true) chk=true;
	for(var i=0;i<num;i++){
		if(varForm.elements[i].name=="library_name"){
			varForm.elements[i].checked=chk;
		}
	}
}

/* ********************************************************
*2012-08-06 권인애 : 기관종류 카테고리구분 변경에 따른 추가
******************************************************** */
function setDiv2(){
	var org_div2=document.InputForm.org_div2.value;
	//document.getElementById("org_div").options[0].selected="selected";
	if(org_div2 == "1"){
		document.getElementById("sub1").style.display="";
		document.getElementById("sub2").style.display="none";
		document.getElementById("sub3").style.display="none";
		document.getElementById("sub4").style.display="none";
	}
	else if(org_div2=="2"){
		document.getElementById("sub1").style.display="none";
		document.getElementById("sub2").style.display="";
		document.getElementById("sub3").style.display="none";
		document.getElementById("sub4").style.display="none";
	}
	else if(org_div2=="3"){
		document.getElementById("sub1").style.display="none";
		document.getElementById("sub2").style.display="none";
		document.getElementById("sub3").style.display="";
		document.getElementById("sub4").style.display="none";
	}
	else if(org_div2=="4"){
		document.getElementById("sub1").style.display="none";
		document.getElementById("sub2").style.display="none";
		document.getElementById("sub3").style.display="none";
		document.getElementById("sub4").style.display="";
	}
	else{
		document.getElementById("sub1").style.display="none";
		document.getElementById("sub2").style.display="none";
		document.getElementById("sub3").style.display="none";
		document.getElementById("sub4").style.display="none";
	}	
}
/* ********************************************************
*2012-08-06 권인애 : 본관/분관에 따른 기관조회 유무
******************************************************** */
function chkMainYn(){
	var org_div2=document.InputForm.main_yn.value;
	if(org_div2=="N"){
		document.getElementById("sub").style.display="";
		document.getElementById("dissub").style.display="none";
	}
	else{
		document.getElementById("sub").style.display="none";
		document.getElementById("dissub").style.display="";
	}
}

/* ********************************************************
* 2012-08-06 권인애 : 통합기관조회 팝업
******************************************************** */
function fn_addUni()
{
	 var context = "<%= request.getContextPath() %>";
	 var url= context+"/op/UniSearchForm.do";
	 window.open(url, "UniSearchpopup","width=400, height=600, scrollbars=yes, status=no");
}

//숫자만
function onlyNumber(e){
	if( !window.event && !e ) return;
	var keyCode = window.event?window.event.keyCode:e.which;
	if( (48<=keyCode && keyCode<=57) || keyCode==0 || keyCode==45 || keyCode==8 ){
		return;
	}else{
		if(window.event) window.event.returnValue = false;
		else e.preventDefault();
	}
}

// 특수문자 제외
function checkExt(e){

	if( !window.event && !e ) return;
	var keyCode = window.event?window.event.keyCode:e.which;
	if( (keyCode>32 && keyCode<48) || (keyCode>57 && keyCode<65) || (keyCode>90 && keyCode<97) ){
		if(window.event) window.event.returnValue = false;
		else e.preventDefault();
	}else{
		return;
	}
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
 
		<DIV class="">
			<h2>
				 협정기관 상세보기
			</h2>
			<!-- /.panel-heading -->			
			<DIV id="content" class="">
				
				<!-- 상단타이틀 -->
				<form method="post" name="InputForm" onsubmit="javascript:Update()" action="">
				<input type="hidden" id="checkid" name="checkid">
				<input type="hidden" id="checkname" name="checkname">
				<input type="hidden" name="criteria" value="<c:out value='${ criteria }'/>">
				<input type="hidden" name="search" value="<c:out value='${ search }'/>">
				<input type="hidden" id="flag" name="flag" >   
				<input type="hidden" id="names" name="names" value="<c:out value='${ info.name }'/>" > 
				<input type="hidden" id="old_name" name="old_name" value="<c:out value='${ info.name }'/>" >
				<input type="hidden" id="old_agreename" name="old_agreename" value="<c:out value='${ info.agree_name }'/>" >
				<input type='hidden' id="old_class2" name='old_class2' value="<c:out value='${ info.class2 }'/>">
				<!--2012-08-06 권인애 : 기관종류 카테고리분류 변경에 따른 추가내용-->
				<input type="hidden"  name="org_div" value="<c:out value='${ info.org_div }'/>">
				<!--2012-08-06 권인애 : 본관/분관 수정에 따른 추가-->
				<input type="hidden" name="old_main_yn" value="<c:out value='${ info.main_yn }'/>">
				
				<!-- 줄간격조정  -->
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<!-- 등록  폼 영역  -->
				<table width="98%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered" summary="이용자 상세정보를  조회합니다.">
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >기관구분&nbsp;&nbsp;</th>
		            <td width="80%" nowrap>
					<c:choose>
					    <c:when test="${ not empty info.class }">
					        <c:if test="${info.class == '1'}">협정기관</c:if>
					        <c:if test="${info.class == '2'}">국회기관</c:if>
					        <input type="hidden" id="select_class" name="select_class" value="<c:out value='${ info.class }'/>" style="display: ">
					    </c:when>
					    <c:otherwise>
					      <select title="기관 구분" id="select_class" name="select_class" onChange="javascript:fn_onchangeClass()">
					          <option value="1" <c:if test="${info.class=='1'}">selected</c:if>>협정기관</option>
					          <option value="2" <c:if test="${info.class=='2'}">selected</c:if>>국회기관</option>             
					      </select>
					    </c:otherwise>
					</c:choose>
		            </td>				    
				  </tr>
				
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="userid">본관/분관여부</label>&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<select title="약정체결여부" name="main_yn" onchange="javascript:chkMainYn()">
				    		<option value="Y" <c:if test="${info.main_yn=='Y'}" >selected</c:if>>본관</option>
                			<option value="N" <c:if test="${info.main_yn=='N'}">selected</c:if>>분관</option>  
                			<option value="" <c:if test="${info.main_yn==''}">selected</c:if>>미지정</option>          
            			</select>
            		</td>
				  </tr>
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap ><label for="user_name">기관종류</label>&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
					<c:choose>
					 <c:when test="${ not empty info }">
					  	<%  HashMap mapInfo = (HashMap)request.getAttribute("info"); %>
					 	<%= LibraryInfoXmlUtil.getSelect("org_div2", "widthB", mapInfo) %>
					 </c:when> 	
					<c:otherwise>
						<%= LibraryInfoXmlUtil.getSelect("org_div2", "widthB", request) %>
					</c:otherwise>
					</c:choose>
					<c:choose>
					 <c:when test="${ not empty info }">
					  	<span id="sub1" <c:if test="${info.org_div2!='1'}"> style="display: none" </c:if>>
					  		<select title="하위기관종류" name="org_sub1" >
					        <option value="1" <c:if test="${info.org_div=='1'}">selected</c:if>>4년제대학</option>
					        <option value="3" <c:if test="${info.org_div=='3'}">selected</c:if>>대학원대학교</option>    
					        <option value="5" <c:if test="${info.org_div=='5'}">selected</c:if>>전문대학</option>         
					        <option value="10" <c:if test="${info.org_div=='10'}">selected</c:if>>기타대학</option>         
					 			</select>
					  	</span>
					  	<span id="sub2" <c:if test="${info.org_div2!='2'}"> style="display: none" </c:if>>
					  		<select title="하위기관종류" name="org_sub2" >
					        <option value="8" <c:if test="${info.org_div=='8'}">selected</c:if>>외국전문</option>
					        <option value="4" <c:if test="${info.org_div=='4'}">selected</c:if>>외국대학</option>    
					 			</select>
					  	</span>
					  	<span id="sub3" <c:if test="${info.org_div2!=''}"> style="display: none" </c:if>>
					  		<select title="하위기관종류" name="org_sub3" >
					        <option value="6" <c:if test="${info.org_div=='6'}">selected</c:if>>전문도서관</option>
					        <option value="14" <c:if test="${info.org_div=='14'}">selected</c:if>>의회</option>    
					        <option value="15" <c:if test="${info.org_div=='15'}">selected</c:if>>자치단체</option>         
					        <option value="16" <c:if test="${info.org_div=='16'}">selected</c:if>>방송사</option>
					        <option value="17" <c:if test="${info.org_div=='17'}">selected</c:if>>신문사</option>         
					 			</select>
					  	</span>
					  	<span id="sub4" <c:if test="${info.org_div2!='4'}"> style="display: none" </c:if>>
					  		<select title="하위기관종류" name="org_sub4" >
					        <option value="2" <c:if test="${info.org_div=='2'}">selected</c:if>>공공도서관</option>
					 			</select>
					  	</span>
					</c:when>
					<c:otherwise>
					  	<span id="sub1" style="display: none">
					  		<select title="하위기관종류" name="org_sub1" >
					        <option value="1" <c:if test="${info.org_div=='1'}">selected</c:if>>4년제대학</option>
					        <option value="3" <c:if test="${info.org_div=='3'}">selected</c:if>>대학원대학교</option>    
					        <option value="5" <c:if test="${info.org_div=='5'}">selected</c:if>>전문대학</option>         
					        <option value="10" <c:if test="${info.org_div=='10'}">selected</c:if>>기타대학</option>         
					 			</select>
					  	</span>
					  	<span id="sub2" style="display: none">
					  		<select title="하위기관종류" name="org_sub2" >
					        <option value="8" <c:if test="${info.org_div=='8'}">selected</c:if>>외국전문</option>
					        <option value="4" <c:if test="${info.org_div=='4'}">selected</c:if>>외국대학</option>    
					 			</select>
					  	</span>
					  	<span id="sub3" style="display: none" >
					  		<select title="하위기관종류" name="org_sub3" >
					        <option value="6" <c:if test="${info.org_div=='6'}">selected</c:if>>전문도서관</option>
					        <option value="14" <c:if test="${info.org_div=='14'}">selected</c:if>>의회</option>    
					        <option value="15" <c:if test="${info.org_div=='15'}">selected</c:if>>자치단체</option>         
					        <option value="16" <c:if test="${info.org_div=='16'}">selected</c:if>>방송사</option>
					        <option value="17" <c:if test="${info.org_div=='17'}">selected</c:if>>신문사</option>         
					 			</select>
					  	</span>
					  	<span id="sub4" style="display: none">
					  		<select title="하위기관종류" name="org_sub4" >
					        <option value="2" <c:if test="${info.org_div=='2'}">selected</c:if>>공공도서관</option>
					 			</select>
					  	</span>
					</c:otherwise>
					</c:choose>				    
					</td>
				  </tr>
				
				  <tr><th scope="row" colspan="2" height="23" class="required_text" nowrap >협정체결정보&nbsp;&nbsp;</th></tr>
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >협정체결기관명(국회)&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<label for="agree_name" class="hidden">협정체결기관명(국회)입력</label>
						<input type="text" id="agree_name" name="agree_name" value= "<c:out value='${ info.agree_name }'/>"  >
						<!--2012-08-06 권인애 : 기관의 상위기관 연번 저장 ( 본관 : 자신의 일련번호 , 분관 : 조회하여 선택한 기관의 일련번호 ) -->
						<input type="hidden" id="up_agree_no" name="up_agree_no" value="<c:out value='${ info.up_agree_no}'/>"  >
						<input type="hidden" id="up_agree_name" name="up_agree_name">
						<c:if test="${ not empty info.class }">
							<input type="button" value="변경이력보기" onclick = "javascript:fn_showHistory('A')" >
						</c:if>
						<!--  2012-08-06 권인애 : 본관/분관여부 변경에 따른 조회화면 추가-->
						<!--  2012-09-05 권인애 : 본관도 이름이 보이게 수정-->
						&nbsp;&nbsp;
						<c:if test="${not empty info}">
							<span id="dissub"  <c:if test="${info.main_yn=='N'}">style="display:none;"</c:if>>
							<input type="text" name="Text" style="width:120px;" maxlength="15" readonly="readonly" disabled="disabled" value="<c:out value='${ info.up_agree_no }'/>:<c:out value='${ umap.agree_name }'/>"> <span class="button small">
							<!-- <input type="button" value="조회" onclick="javascript:fn_addUni()" disabled="disabled"></span> -->
						</span>
						<span id="sub" <c:if test="${info.main_yn!='N'}">style="display:none;"</c:if>>
							<!-- 2012-08-31 권인애 :  본관 이름 가져오기 -->
							<input type="text" name="parText" style="width:120px;" size="50" maxlength="15" readonly="readonly"  value="<c:out value='${ info.up_agree_no }'/>:<c:out value='${ umap.agree_name }'/>"> <span class="button small">
							<!-- <input type="button" value="조회" onclick="javascript:fn_addUni()"></span> -->
						</span>
						</c:if>
						<c:if test="${empty info}">
							<span id="dissub" >
							<input type="text" name="Text" style="width:150px;" maxlength="15" readonly="readonly" disabled="disabled" > <span class="button small">
							<!-- <input type="button" value="조회" onclick="javascript:fn_addUni()" disabled="disabled"></span> -->
						</span>
						<span id="sub" style="display: none;">
							<input type="text" name="parText" style="width:150px;" size="50" maxlength="15" readonly="readonly"  value="<c:out value='${ info.up_agree_no }:${ info.up_agree_name }'/>"> <span class="button small">
							<!-- <input type="button" value="조회" onclick="javascript:fn_addUni()"></span> -->
						</span>
						</c:if>				    
				    </td>
				  </tr>
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >협정체결여부&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
			            <select title="협정체결여부" name="agree_yn" >
			                <option value="Y" <c:if test="${info.agree_yn=='Y'}">selected</c:if>>체결</option>
			                <option value="T" <c:if test="${info.agree_yn=='T'}">selected</c:if>>테스트용</option>    
			                <option value="D" <c:if test="${info.agree_yn=='D'}">selected</c:if>>보류</option>         
			                <option value="S" <c:if test="${info.agree_yn=='S'}">selected</c:if>>탈퇴</option>         
			            </select>
                    </td>
				  </tr>
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >협정체결 일련번호&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> 
						<c:choose>
			            	<c:when test="${ not empty info }">
			          			<input type="hidden" class="text widthA" id="agree_no" name="agree_no" value="<c:out value='${info.agree_no }'/>" >${info.agree_no}
			          		</c:when> 	
			       			<c:otherwise>
			       				<input type="hidden" class="text widthA" id="agree_no" name="agree_no" value="<c:out value='${agree_no }'/>" >${agree_no}
			       			</c:otherwise>
			       		</c:choose>
                    </td>
				  </tr>	
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >협정체결 일자&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> 
				    	<label for="reg_date" class="hidden">협정체결 일자입력</label>
                        <input type="text" id="reg_date" name="reg_date" value="<c:out value='${ info.reg_date }'/>" maxlength="9">&nbsp;<span>(ex)2000-09-01</span>
                    </td>
				  </tr>		
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >협정방법&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> 
			        	<c:choose>
			            	<c:when test="${ not empty info }">
				             	<%  HashMap mapInfo = (HashMap)request.getAttribute("info"); %>
				            	<%= LibraryInfoXmlUtil.getSelect("contract_method", "widthB", mapInfo) %>
			            	</c:when> 	
			       			<c:otherwise>
			         			<%= LibraryInfoXmlUtil.getSelect("contract_method", "widthB", request) %>
			       			</c:otherwise>
			       		</c:choose>
                    </td>
				  </tr>				  		
				  
				  <tr><th scope="row" colspan="2" height="23" class="required_text" nowrap style="text-align:center;">약정체결정보&nbsp;&nbsp;</th></tr>
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >약정체결기관명(센터)&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<label for="name" class="hidden">약정체결기관명(센터) </span></label>
				    	<input type="text" id="name" name="name" size="18" maxlength="50" value="<c:out value='${ info.name }'/>" >
			          	<c:if test="${ not empty class }">
			          		<input type="button" value="변경이력보기" onclick = "javascript:fn_showHistory('C')" >
			          	</c:if>
                    </td>
				  </tr>		
				  
				  <tr> 
				    <th scope="row" width="20%" height="23" class="required_text" nowrap >약정체결여부&nbsp;&nbsp;</th>
				    <td width="80%" nowrap> 
			            <select title="약정체결여부" name="contract_yn">
			                <option value="N" <c:if test="${info.contract_yn=='N'}">selected</c:if>>미체결</option>
			                <option value="Y" <c:if test="${info.contract_yn=='Y'}">selected</c:if>>체결</option>          
			                <option value="S" <c:if test="${info.contract_yn=='S'}">selected</c:if>>탈퇴</option>          
			            </select> 
                    </td>
				  </tr>					  			  
				  
	              <tr>
	              	<th scope="row" width="20%" height="23" class="required_text" nowrap >약정 일련번호&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="contract_no" class="hidden">약정 일련번호입력</label>
						<input type="text" id="contract_no" name="contract_no" value="<c:out value='${ info.contract_no }'/>" >
					</td>
				  </tr>
				  
	              <tr>
	              	<th scope="row" width="20%" height="23" class="required_text" nowrap >납입시기&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
			            <select title="납입시기" name="account_period">
			                <option value="월납" <c:if test="${info.account_period=='월납'}">selected</c:if>>월납</option>
			                <option value="분기납" <c:if test="${info.account_period=='분기납'}">selected</c:if>>분기납</option>
			                <option value="반기납" <c:if test="${info.account_period=='반기납'}">selected</c:if>>반기납</option>
			                <option value="연납" <c:if test="${info.account_period=='연납'}">selected</c:if>>연납</option>
			                <option value="" <c:if test="${info.account_period==''}">selected</c:if>>미지정</option>
			            </select>
					</td>
				  </tr>		
				  
				<tr><th scope="row" colspan="2" height="23" class="required_text" nowrap >담당자 정보&nbsp;&nbsp;</th></tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >기관 담당자&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="charge" class="hidden">기관 담당자입력</label>
						<input type="text" id="charge" name="charge" value="<c:out value='${ info.charge }'/>" >
					</td>
				</tr>					  

				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >기관부서명&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="dept_name" class="hidden">기관부서명입력</label>
						<input type="text" id="dept_name" name="dept_name" value="<c:out value='${ info.dept_name }'/>" >
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >기관장&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="org_chief" class="hidden">기관장입력</label>
						<input type="text" id="org_chief" name="org_chief" value="<c:out value='${ info.org_chief }'/>" >
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >전화 번호&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="tel_no" class="hidden">전화 번호입력</label>
						<input type="text" id="tel_no" name="tel_no" value="<c:out value='${ info.tel_no }'/>" >
					</td>
				</tr>	
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >핸드폰&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="mobile_no" class="hidden">핸드폰입력</label>
						<input type="text" id="mobile_no" name="mobile_no" value="<c:out value='${ info.mobile_no }'/>" >
					</td>
				</tr>

				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >E-MAIL&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="tel_no" class="hidden">E-MAIL입력</label>
						<input type="text" id="user_mail" name="user_mail" value="<c:out value='${ info.user_mail }'/>" >
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >우편번호&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="post_code" class="hidden">우편번호입력</label>
						<input type="text" id="post_code" name="post_code" value="<c:out value='${ info.post_code }'/>" >
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >주소&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="address" class="hidden">주소입력</label>
						<input type="text" id="address" name="address" value="<c:out value='${ info.address }'/>" >
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >국회 담당자&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="na_charge" class="hidden">국회 담당자입력</label>
						<input type="text" class="text widthName" id="na_charge" name="na_charge" value="<c:out value='${ info.na_charge }'/>" >
					</td>
				</tr>																							
								
				<tr><th scope="row" colspan="2" height="23" class="required_text" nowrap style="text-align:center;">기타 정보&nbsp;&nbsp;</th></tr>

				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >홈페이지 안내 여부&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<select title="홈페이지 안내 여부" name="display_yn">
						    <option value="Y" <c:if test="${info.display_yn=='Y'}">selected</c:if>>Y</option>    
						    <option value="N" <c:if test="${info.display_yn=='N'}">selected</c:if>>N</option>                
						</select>
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >지역&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
			            <select title="지역" name="local">
			                <option value="서울" <c:if test="${info.local=='서울'}">selected</c:if>>서울</option>
			                <option value="부산" <c:if test="${info.local=='부산'}">selected</c:if>>부산</option>
			                <option value="대구" <c:if test="${info.local=='대구'}">selected</c:if>>대구</option>
			                <option value="인천" <c:if test="${info.local=='인천'}">selected</c:if>>인천</option>
			                <option value="대전" <c:if test="${info.local=='대전'}">selected</c:if>>대전</option>
			                <option value="울산" <c:if test="${info.local=='울산'}">selected</c:if>>울산</option>
			                <option value="광주" <c:if test="${info.local=='광주'}">selected</c:if>>광주</option>
			                <option value="경기" <c:if test="${info.local=='경기'}">selected</c:if>>경기</option>              
			                <option value="강원" <c:if test="${info.local=='강원'}">selected</c:if>>강원</option>              
			                <option value="충북" <c:if test="${info.local=='충북'}">selected</c:if>>충북</option>              
			                <option value="충남" <c:if test="${info.local=='충남'}">selected</c:if>>충남</option>              
			                <option value="경북" <c:if test="${info.local=='경북'}">selected</c:if>>경북</option>              
			                <option value="경남" <c:if test="${info.local=='경남'}">selected</c:if>>경남</option>              
			                <option value="전북" <c:if test="${info.local=='전북'}">selected</c:if>>전북</option>              
			                <option value="전남" <c:if test="${info.local=='전남'}">selected</c:if>>전남</option>              
			                <option value="제주" <c:if test="${info.local=='제주'}">selected</c:if>>제주</option>              
			                <option value="해외" <c:if test="${info.local=='해외'}">selected</c:if>>해외</option>                      
			            </select>
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >지역2&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="local2" class="hidden">지역2입력</label>
						<input type="text" id="local2" name="local2" value="<c:out value='${ info.local2 }'/>" >
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >홈페이지 출력 기관명&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="display_name" class="hidden">홈페이지 출력 기관명</label>
						<input type="text" id="display_name" name="display_name" value="<c:out value='${ info.display_name }'/>" >
					</td>
				</tr>
				
				<c:if test="${info.class == '2'}">
				<!-- <tr id="trClass2" style="display:none"> -->
				<tr style="display:none">
					<th scope="row" width="20%" height="23" class="required_text" nowrap >협정기관 구분&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<select title="협정기관 구분" name="class2">
				        	<option value="1" <c:if test="${info.class2=='1'}">selected</c:if>>국내협정기관</option>
				            <option value="2" <c:if test="${info.class2=='2'}">selected</c:if>>국외협정기관</option>
				        </select>
			     	</td>
				</tr>
				</c:if>
				<c:if test="${info.class != '2'}">
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >협정기관 구분&nbsp;&nbsp;</th>
				    <td width="80%" nowrap>
				    	<select title="협정기관 구분" name="class2">
				        	<option value="1" <c:if test="${info.class2=='1'}">selected</c:if>>국내협정기관</option>
				            <option value="2" <c:if test="${info.class2=='2'}">selected</c:if>>국외협정기관</option>
			         	</select>
		     		</td>
				</tr>
				</c:if>				
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >분관ID정보&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="branch_id" class="hidden">분관ID정보</label>
						<input type="text" id="branch_id" name="branch_id" value="<c:out value='${ info.branch_id }'/>" >
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >비고&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="remark" class="hidden">비고</label>
						<textarea id="remark" name="remark" rows="3" style="overflow:auto; width:100%;"><c:out value='${info.remark}'/></textarea>
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >위도&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="remark" class="hidden">위도</label>
						<input type="text" id="gps_latitude" name="gps_latitude" value="<c:out value='${ info.gps_latitude }'/>" >
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >경도&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="remark" class="hidden">경도</label>
			          	<input type="text" id="gps_longitude" name="gps_longitude" value="<c:out value='${ info.gps_longitude }'/>" >
					</td>
				</tr>				

				<tr><th scope="row" colspan="2" height="23" class="required_text" nowrap style="text-align:center;">기관별 검색PC정보&nbsp;&nbsp;</th></tr>

				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >검색PC인증방식&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<input type="checkbox" id="pc_login_type0" name="pc_login_type0" value="1" <c:if test="${info.pc_login_type0=='1'}">checked</c:if> ><label for="pc_login_type0">MAC</label>
						&nbsp;
						<input type="checkbox" id="pc_login_type1" name="pc_login_type1" value="1" <c:if test="${info.pc_login_type1=='1'}">checked</c:if> ><label for="pc_login_type1">CPU ID</label>
						&nbsp;
						<input type="checkbox" id="pc_login_type2" name="pc_login_type2" value="1" <c:if test="${info.pc_login_type2=='1'}">checked</c:if> ><label for="pc_login_type2">IP</label>
						&nbsp;
						<input type="checkbox" id="pc_login_type3" name="pc_login_type3" value="1" <c:if test="${info.pc_login_type3=='1'}">checked</c:if> ><label for="pc_login_type3">아이디</label>
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >과금대상여부<br>/복사카드사용유무<br>기본설정</th>
					<td width="80%" nowrap>
						<label for="fee_use">과금대상여부</label>
						<select title="과금대상" id="fee_use" name="fee_use">
						    <option value="Y" <c:if test="${info.fee_use=='Y'}">selected</c:if>>예</option>
						    <option value="N" <c:if test="${info.fee_use=='N'}">selected</c:if>>아니오</option>
						</select>
						<br>
						<label for="card_use">복사카드사용유무</label>
						<select title="복사카드" id="card_use" name="card_use">
						    <option value="Y" <c:if test="${info.card_use=='Y'}">selected</c:if>>예</option>
						    <option value="N" <c:if test="${info.card_use=='N'}">selected</c:if>>아니오</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<th scope="row" width="20%" height="23" class="required_text" nowrap >검색PC아이디 Prefix / PC대수&nbsp;&nbsp;</th>
					<td width="80%" nowrap>
						<label for="pcid_prefix" class="hidden">검색PC아이디 Prefix</label>
						<input type="text" id="pcid_prefix" name="pcid_prefix" value="<c:out value='${ info.pcid_prefix }'/>" >
						<input type="text" id="pc_id_count" name="pc_id_count" value="<c:out value='${ info.pc_id_count }'/>" ><label for="pc_id_count" >대</label>
						<input type='hidden' id="old_pc_id_count" name='old_pc_id_count' value="<c:out value='${ info.pc_id_count }'/>">
						<c:if test="${ not empty info }">
							<input type="button" value="검색PC목록보기" onclick = "javascript:fn_showPCList()" >
						</c:if>
						<!-- <span class="button small"><input type="button" value="중복확인" onclick="javascript:fn_dupIDCheck(this.form)" ></span> -->
					</td>
				</tr>
				
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
