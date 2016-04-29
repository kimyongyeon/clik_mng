<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 /**
  * @Class Name  : MenuList.jsp
  * @Description : MenuList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 
  *
  *  @author 
  *  @since 
  *  @version 1.0
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>메뉴 목록</title>
<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<validator:javascript formName="menuManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--

/* ********************************************************
 * 파일검색 화면 호출 함수
 ******************************************************** */
function searchFileNm() {
	window.open("<c:url value='/sit/prm/ProgramListSearch.do' />",'','width=700,height=700');
}


/*********************************************************
 * 조회 처리
 ******************************************************** */
function fnSearch(){
   	document.listForm.submit();
}
/* ********************************************************
 * 적용 처리 함수
 ******************************************************** */
function fnApply(form){

	if(!confirm("현재 등록 된 메뉴를 사용할 수 있게끔 적용하시겠습니까?")){
		return false;
	}

	location.href = "<c:url value='/mna/mnu/ApplyMenuList.do' />";
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fnRegist(form){
	//location.href = "<c:url value='/ccm/ccd/CmmClCodeRegist.do' />";
	
/* 	alert($("#menuNo").val());
	return; */

	if(!confirm("메뉴를 등록 하시겠습니까?")){
		return false;
	}
		
	if(!validateMenuManageVO(form))
		return;	
		
	var depth = $("#menuLevel").val();
    if(depth == '1'){    		
    	$("#menuOrdr").val(eval($("#menuListdepth1 option").size()) +1);
    }
    else if(depth == '2'){
    	$("#menuOrdr").val(eval($("#menuListdepth2 option").size()) +1);
    }
    else if(depth == '3'){
    	$("#menuOrdr").val(eval($("#menuListdepth3 option").size()) +1);
    }  
	

	document.listForm.action = "<c:url value='/mna/mnu/MenuListInsert.do'/>";
	document.listForm.submit();	
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fnModify(form){

	if(!confirm("메뉴를 수정 하시겠습니까?")){
		return false;
	}	
	
	if(!validateMenuManageVO(form))
		return;		
	
	
	document.listForm.upperMenuIdDc.value = '';
    document.listForm.action = "<c:url value='/mna/mnu/MenuListUpdt.do'/>";     
	document.listForm.submit();	
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fnDelete(){

	if(!confirm("메뉴를 삭제 하시겠습니까?")){
		return false;
	}	
	
    document.listForm.action = "<c:url value='/mna/mnu/MenuListDelete.do'/>";     
	document.listForm.submit();	
}

/* ********************************************************
 * 분류선택
 ******************************************************** */
function fnOnSeleteMenu(depth, obj) {

	var varForm = document.listForm;
    var selMenuId = obj.options[obj.selectedIndex].value;
    varForm.menuLevel.value = depth;
    if(depth == '1'){
    	
    	varForm.upperMenuId.value = selMenuId;
    	$("#menuNo").val(selMenuId);
        if(varForm.menuListdepth2 != null){        	
	        var depth2 = varForm.menuListdepth2;
	        for(var i=depth2.options.length;i>=0;i--){
	            depth2.options[i] = null;
	        }
	        if(varForm.menuListdepth3 != null){
		        var depth3 = varForm.menuListdepth3;
		        for(var i=depth3.options.length;i>=0;i--){
		            depth3.options[i] = null;
		        }
	        }
        }    	
	}else if(depth == '2'){
		
		$("#upperMenuId").val($("#menuListdepth1").val());
		$("#upperMenuIdNm").val($("#menuListdepth1 option:selected").text());
		$("#menuNo").val(selMenuId);
		//varForm.code.value  ='';
		if(varForm.menuListdepth3 != null){
	        var depth3 = varForm.menuListdepth3;
	        for(var i=depth3.options.length;i>=0;i--){
	            depth3.options[i] = null;
	        }
		}    
	}else if(depth == '3'){
		$("#upperMenuId").val($("#menuListdepth2").val());
		$("#upperMenuIdNm").val($("#menuListdepth2 option:selected").text());
		$("#menuNo").val(selMenuId);
		//varForm.code.value = obj.options[obj.selectedIndex].value;
	}    
    varForm.upperMenuIdDc.value = '';
	document.listForm.submit();    
}

/* ********************************************************
 * 상세내용 처리 함수
 ******************************************************** */
function fnGetDetail(){
			
	var depth ='';	
	var enterLevel ='';
	depth = $("#menuLevel").val();
	enterLevel = $("#enterLevel").val();
	
	if (enterLevel != '')
	    return;					
	
    if(depth == '1'){
    	$("#upperMenuIdDc").val('');    	
    }else if(depth == '2'){    	    	
    	$("#upperMenuIdDc").val($("#menuListdepth1 option:selected").text());
    	
    }else if(depth == '3'){
    	$("#upperMenuIdDc").val($("#menuListdepth1 option:selected").text() + '>' + $("#menuListdepth2 option:selected").text());
    }
    
    if ($("#menuNo").val() =='')
    	$("#menuNo").attr("readonly",false);    	

}

/* ********************************************************
* 등록화면
******************************************************** */
function fn_enter(sel_name,depth) {
	
	

	$("#menuLevel").val(depth);
    $("#enterLevel").val(sel_name);
    $("#menuNo").val('0');
    
    if(depth == '1'){
    	$("#menuLevel").val(depth);
    	$("#upperMenuId").val('0');
    	
    }
    else if(depth == '2'){
    	if ($("#menuListdepth1").val() == '' || $("#menuListdepth1").val() == null){
    		alert('대메뉴를 선택하세요');
    		return;
    	}       	
    	$("#upperMenuId").val($("#menuListdepth1").val());
    	$("#upperMenuIdNm").val($("#menuListdepth1 option:selected").text());
    	
	}else if(depth == '3'){
		if ($("#menuListdepth2").val() == '' || $("#menuListdepth2").val() == null){
    		alert('중메뉴를 선택하세요');
    		return;
    	}		
    	$("#upperMenuId").val($("#menuListdepth2").val());
    	$("#upperMenuIdNm").val($("#menuListdepth2 option:selected").text());
	}    
    
    document.listForm.submit();
}

/* ********************************************************
* 메뉴순서 변경 - up
******************************************************** */
function fn_moveUp(sel_name)
{
    var i;
    var j;
    var strTempValue = '';
    var strTempText = '';

    var obj = eval("document.listForm."+sel_name);

    i = obj.options.selectedIndex;

    document.listForm.menuOrdChg.value = '';
    if (i > 0)
    {
        j = i - 1;
        strTempValue = obj.options[j].value;
        strTempText = obj.options[j].text;

        obj.options[j].value = obj.options[i].value;
        obj.options[j].text = obj.options[i].text;

        obj.options[i].value = strTempValue;
        obj.options[i].text = strTempText;

        obj.options[j].selected = true;
        
        document.listForm.menuOrdChg.value = obj.options[i].value + "=" + (i);
        document.listForm.menuOrdChg.value +=  "," + obj.options[j].value + "=" + (j);
    }
        
    if (document.listForm.menuOrdChg.value != ''){
    	document.listForm.action = "<c:url value='/mna/mnu/MenuOrdUpdt.do'/>";     
		document.listForm.submit();	    
    }
}

/* ********************************************************
* 메뉴순서 변경 - down
******************************************************** */
function fn_moveDown(sel_name)
{
    var i;
    var j;
    var strTempValue = '';
    var strTempText = '';
    
    var obj = eval("document.listForm."+sel_name);

    i = obj.options.selectedIndex;

   	document.listForm.menuOrdChg.value = '';
    
    if (i < obj.options.length - 1)
    {
        j = i + 1;
        strTempValue = obj.options[j].value;
        strTempText = obj.options[j].text;

        obj.options[j].value = obj.options[i].value;
        obj.options[j].text = obj.options[i].text;

        obj.options[i].value = strTempValue;
        obj.options[i].text = strTempText;

        obj.options[j].selected = true;
        
        document.listForm.menuOrdChg.value = obj.options[i].value + "=" + (i);
        document.listForm.menuOrdChg.value +=  "," + obj.options[j].value + "=" + (j);
    }
    
    if (document.listForm.menuOrdChg.value != ''){
    	document.listForm.action = "<c:url value='/mna/mnu/MenuOrdUpdt.do'/>";     
		document.listForm.submit();	    
    }    
}

/* ********************************************************
* 탭클릭시 화면전환
******************************************************** */
function fn_tabClick(sel_name)
{
	$("#menuLevel").val('0');
	$("#enterLevel").val('1');
	$("#upperMenuId").val('0');
	$("#menuNo").val('0');
	$("#menuClCode").val(sel_name);
	document.listForm.submit();		
}

//-->
<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
</script>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />

<c:if test="${searchVO.menuClCode == ''}">
	<c:set var="menuClCode" value="CLIK"/>
</c:if>
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">메뉴관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->

				<DIV class="panel-heading" >
					 메뉴 목록
					<span  class="fr">
						<button type="button" class="btn btn-primary" onClick="fnApply(document.forms[0]); return false;">적용</button>
						
						<c:choose>
							<c:when test="${searchVO.enterLevel != '' || empty searchVO.menuLevel !=''}">
								<button type="button" class="btn btn-success" onclick="fnRegist(document.forms[0]); return false;">임시등록</button>
							</c:when>
							<c:otherwise>
								<button type="button" class="btn btn-success" onclick="fnModify(document.forms[0]); return false;">수정</button>
								<button type="button" class="btn btn-danger" onclick="fnDelete(); return false;">삭제</button>
							</c:otherwise>		
						</c:choose>			
					
					</span>	
				</DIV>
				
				<ul class="nav nav-tabs">
	                   <li ${(searchVO.menuClCode == '' || searchVO.menuClCode == 'CLIK') ? 'class="active"' : '' }><a href="#LINK" onClick="javascript:fn_tabClick('CLIK')" data-toggle="tab">사용자 메뉴</a>
	                   </li>
	                   <li ${searchVO.menuClCode == 'MNG' ? 'class="active"' : '' }><a href="#LINK" onClick="javascript:fn_tabClick('MNG')"  data-toggle="tab">관리자 메뉴</a>
	                   </li>
	            </ul>			


				<form commandName="menuManageVO" name="listForm" action="<c:url value='/mna/mnu/MenuList.do'/>" method="post">
				<table width = "100% " cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<table width = "100% " cellpadding="0" class="table table-striped table-bordered table-hover dataTable no-footer" border="0" summary="메뉴코드, 메뉴코드명, 사용여부를 조회하는 메뉴 목록 테이블이다.">
				<thead>
				<tr>
					<th class="title" width="34%" scope="col" nowrap>대메뉴</th>
					<th class="title" width="4%" scope="col" nowrap></th>
					<th class="title" width="29%" scope="col" nowrap>중메뉴</th>
					<th class="title" width="4%" scope="col" nowrap></th>
					<th class="title" width="29%" scope="col" nowrap>소메뉴</th>
					<th class="title" width="4%" scope="col" nowrap></th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td>
						<select name="menuListdepth1" id="menuListdepth1" class="form-control" title="" size="10" onClick="javascript:fnOnSeleteMenu('1',this);">
							<c:forEach var="menuList1" items="${menuList1}" varStatus="status">						
								<option value="${menuList1.menuNo}" ${menuList1.menuNo == searchVO.menuListdepth1 ? 'selected="selected"' : '' }>[${menuList1.menuNo}]${menuList1.menuNm}</option>																															
							</c:forEach>
						</select>		
									    
					</td>
					<td>
					<c:if test="${searchVO.enterLevel == '' && empty searchVO.menuLevel ==''}">
	                   <input type="button" value="▲" onclick = "javascript:fn_moveUp('menuListdepth1')" >&nbsp;
	                   <input type="button" value="▼" onclick = "javascript:fn_moveDown('menuListdepth1')" >&nbsp;
	                </c:if>
					</td>
					<td>
						<select name="menuListdepth2" id="menuListdepth2" class="form-control" title="" size="10" onClick="javascript:fnOnSeleteMenu('2',this);">
							<c:forEach var="menuList2" items="${menuList2}" varStatus="status">						
								<option value="${menuList2.menuNo}" ${menuList2.menuNo == searchVO.menuListdepth2 ? 'selected="selected"' : '' }>[${menuList2.menuNo}]${menuList2.menuNm}</option>						
							</c:forEach>
						</select>					    
					</td>
					<td>
						<c:if test="${searchVO.enterLevel == '' && empty searchVO.menuLevel ==''}">
	                   		<input type="button" value="▲" onclick = "javascript:fn_moveUp('menuListdepth2')" >&nbsp;
	                   		<input type="button" value="▼" onclick = "javascript:fn_moveDown('menuListdepth2')" >&nbsp;
	                   	</c:if>
					</td>					
					<td>					
						<select name="menuListdepth3" id="menuListdepth3" class="form-control" title="" size="10" onClick="javascript:fnOnSeleteMenu('3',this);">
							<c:forEach var="menuList3" items="${menuList3}" varStatus="status">						
								<option value="${menuList3.menuNo}" ${menuList3.menuNo == searchVO.menuListdepth3 ? 'selected="selected"' : '' }>[${menuList3.menuNo}]${menuList3.menuNm}</option>				
							</c:forEach>
						</select>					    
					</td>
					<td>
						<c:if test="${searchVO.enterLevel == '' && empty searchVO.menuLevel ==''}">
	                   		<input type="button" value="▲" onclick = "javascript:fn_moveUp('menuListdepth3')" >&nbsp;
	                   		<input type="button" value="▼" onclick = "javascript:fn_moveDown('menuListdepth3')" >&nbsp;	                   		
	                   	</c:if>
					</td>
				</tr>	
				<tr>
					<td colspan="2"><button type="button" class="btn btn-primary" onclick="fn_enter('depth1','1'); return false;">추가</button></td>					
					<td colspan="2"><button type="button" class="btn btn-primary" onclick="fn_enter('depth2','2'); return false;">추가</button></td>
					<td colspan="2"><button type="button" class="btn btn-primary" onclick="fn_enter('depth3','3'); return false;">추가</button></td>
				</tr>				
				
				</tbody>
				</table>
				
				<table width = "100% " cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>	
						
				<c:choose>
					<c:when test="${searchVO.menuLevel == '1'}">						 
						 <c:set var="cdClNm" value="대메뉴"/>
					</c:when>				
					<c:when test="${searchVO.menuLevel == '2'}">
						 <c:set var="cdClNm" value="중메뉴"/>
					</c:when>
					<c:when test="${searchVO.menuLevel == '3'}">
						 <c:set var="cdClNm" value="소메뉴"/>
					</c:when>
				</c:choose>						
				
				<table width = "100% " cellpadding="0" class="table table-striped table-bordered table-hover dataTable no-footer" border="0" summary="분류코드, 분류코드명, 사용여부를 조회하는 메뉴목록 테이블이다.">
				<tbody>
				<tr>
					<th class="title" scope="col" nowrap>구분</th>
					<th class="title" scope="col" nowrap>메뉴 상세</th>
				</tr>
				</thead>				
				<tr>
					<td>메뉴 분류</td>
					<td>
						<input type="text" id="cdClNm" name="cdClNm" value="<c:out value='${cdClNm}'/>" readonly class="form-control">
					</td>
				</tr>
				<tr>
					<td>상위 메뉴</td>
					<td>
						<input type="text" id="upperMenuIdDc" name="upperMenuIdDc" value="<c:out value='${menuManageVO.upperMenuId}'/>" readonly class="form-control">
					</td>
				</tr>

				<tr>
					<td>메뉴 ID</td>
					<td>
						<input type="text" id="menuNo" name="menuNo" value="<c:out value='${menuManageVO.menuNo}'/>" readonly class="form-control">
						<form:input path="menuNo" size="10" maxlength="10" title="메뉴No"/>
					</td>
				</tr>
				<tr>
					<td>메뉴 명</td>
					<td>					
						<input type="text" id="menuNm" name="menuNm" value="<c:out value='${menuManageVO.menuNm}'/>" class="form-control">
					</td>
				</tr>
				<tr>
					<td>메뉴 URL</td>
					<td>
						<input type="text" id="progrmFileNm" name="progrmFileNm" maxlength="60" class="form-control" value="<c:out value='${menuManageVO.progrmFileNm}'/>">
						* 상위 메뉴 또는 중위 메뉴에 URL이 없는 경우 DIR로 표기해 주세요.
					</td>
				</tr>		
				<tr>
					<td>번호</td>
					<td>
					
					   <c:choose>
					       <c:when test="${menuManageVO.menuOrdr ne null || empty searchVO.menuOrdr !=''}">
					       	<input type="text" id="menuOrdr" name="menuOrdr" class="form-control" readonly value="<c:out value='${menuManageVO.menuOrdr}'/>">
				       	   </c:when>
				       	   <c:otherwise>
				       	   	<input type="text" id="menuOrdr" name="menuOrdr" class="form-control" readonly value="0">
				       	   </c:otherwise>
				       </c:choose>					       					
												
					</td>
				</tr>
				<!-- 								
				  <tr>
				    <td>사용여부</td>
				    <td>
						<select name="useAt" id="useAt" class="form-control">
							<option value="Y" <c:if test="${menuManageVO.useAt == 'Y'}">selected="selected"</c:if> >사용함</option>
							<option value="N" <c:if test="${menuManageVO.useAt == 'N'}">selected="selected"</c:if> >사용안함</option>
						</select>
				    </td>
				  </tr>
				  -->									
				</tbody>
				</table>
				<input type="hidden" id="menuLevel" name="menuLevel" value="<c:out value='${searchVO.menuLevel}'/>">
				<input type="hidden" id="enterLevel" name="enterLevel">
				<input type="hidden" id="upperMenuId" name="upperMenuId" value="<c:out value='${searchVO.upperMenuId}'/>">				
				<input type="hidden" id="menuOrdChg" name="menuOrdChg">
				<input type="hidden" id="menuClCode" name="menuClCode" value="<c:out value='${searchVO.menuClCode}'/>">
				<input type="hidden" id="useAt" name="useAt" value="<c:out value='${menuManageVO.useAt}'/>">
				</form>
				
				<DIV class="panel-heading">
					<span class="fr">
						<button type="button" class="btn btn-primary" onClick="fnApply(document.forms[0]); return false;">적용</button>
											
					<c:choose>
						<c:when test="${searchVO.enterLevel != '' || empty searchVO.menuLevel !=''}">
							<button type="button" class="btn btn-success" onclick="fnRegist(document.forms[0]); return false;">임시등록</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-success" onclick="fnModify(document.forms[0]); return false;">수정</button>
							<button type="button" class="btn btn-danger" onclick="fnDelete(); return false;">삭제</button>
						</c:otherwise>
				</c:choose>
				</span>				 
				</DIV>
			
</DIV>	
<!-- /page-wrapper -->
</body>

<script language="javascript">	
<!-- 코드상세정보조회--> 
	fnGetDetail();
</script>
	
	
</html>
