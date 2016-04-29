<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 /**
  * @Class Name  : CcmCmmClCodeList.jsp
  * @Description : CcmCmmClCodeList 화면
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
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>코드관리 목록</title>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/ccm/ccc/EgovCcmCmmnClCodeList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fnSearch(){
	document.listForm.pageIndex.value = 1;
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fnRegist(){
	//location.href = "<c:url value='/ccm/ccd/CmmClCodeRegist.do' />";

	if(!confirm("코드를 등록 하시겠습니까?")){
		return false;
	}
	
	var depth = $("#codeDepth").val();
    if(depth == '1'){    		
		document.listForm.action = "<c:url value='/ccm/ccd/CmmClCodeRegist.do'/>";
    }
    else if(depth == '2'){
    	document.listForm.action = "<c:url value='/ccm/ccd/CcmCmmCodeRegist.do'/>";
    }
    else if(depth == '3'){
    	$("#codeOrdr").val(eval($("#codeList_depth3 option").size()) +1);
    	document.listForm.action = "<c:url value='/ccm/ccd/CcmCmmDetailCodeRegist.do'/>";
    }    
	document.listForm.submit();	
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fnModify(){
	
	if(!confirm("코드를 수정 하시겠습니까?")){
		return false;
	}	
	
	var depth = $("#codeDepth").val();
    if(depth == '1'){    		
		document.listForm.action = "<c:url value='/ccm/ccd/CmmClCodeModify.do'/>";
    }
    else if(depth == '2'){
    	document.listForm.action = "<c:url value='/ccm/ccd/CmmCodeModify.do'/>";
    }
    else if(depth == '3'){
    	document.listForm.action = "<c:url value='/ccm/ccd/CmmDetailCodeModify.do'/>";
    }    
	document.listForm.submit();	
}

/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fnDelete(){

	if(!confirm("코드를 삭제 하시겠습니까?")){
		return false;
	}	
	
	var depth = $("#codeDepth").val();
    if(depth == '1'){    		
		document.listForm.action = "<c:url value='/ccm/ccd/CmmClCodeDelete.do'/>";
    }
    else if(depth == '2'){
    	document.listForm.action = "<c:url value='/ccm/ccd/CmmCodeDelete.do'/>";
    }
    else if(depth == '3'){
    	document.listForm.action = "<c:url value='/ccm/ccd/CmmCodeDetailDelete.do'/>";    	                                                   
    }    
	document.listForm.submit();	
}

/* ********************************************************
 * 분류선택
 ******************************************************** */
function fnOnSeleteMenu(depth, obj) {
    var varForm = document.listForm;
    var selMenuId = obj.options[obj.selectedIndex].value;
    varForm.codeDepth.value = depth;
    
    if(depth == '1'){
    	
    	varForm.codeClCd.value = selMenuId;
    	varForm.codeId.value ='';
    	varForm.parentCd.value ='';
    	//varForm.parentCdNm.value ='';
        if(varForm.codeList_depth2 != null){        	
	        var depth2 = varForm.codeList_depth2;
	        for(var i=depth2.options.length;i>=0;i--){
	            depth2.options[i] = null;
	        }
	        if(varForm.codeList_depth3 != null){
		        var depth3 = varForm.codeList_depth3;
		        for(var i=depth3.options.length;i>=0;i--){
		            depth3.options[i] = null;
		        }
	        }
        }
    }else if(depth == '2'){
    	
    	$("#parentCd").val($("#codeList_depth1").val());
    	$("#parentCdNm").val($("#codeList_depth1 option:selected").text());	
    	varForm.codeId.value = obj.options[obj.selectedIndex].value;
    	varForm.code.value  ='';
    	if(varForm.codeList_depth3 != null){
	        var depth3 = varForm.codeList_depth3;
	        for(var i=depth3.options.length;i>=0;i--){
	            depth3.options[i] = null;
	        }
    	}    
	}else if(depth == '3'){
    	$("#parentCd").val($("#codeList_depth2").val());
    	$("#parentCdNm").val($("#codeList_depth2 option:selected").text());		
		varForm.code.value = obj.options[obj.selectedIndex].value;
	}
    //varForm.action = "<c:url value='/op/OmsMngMenuMngSearch.do'/>";
    varForm.submit();
}

/* ********************************************************
 * 상세내용 처리 함수
 ******************************************************** */
function fnGetDetail(){
			
	var depth ='';	
	var enterDepth ='';
	depth = $("#codeDepth").val();
	enterDepth = $("#enterDepth").val();
	
	if (enterDepth != '')
	    return;					
	
    if(depth == '1'){
    	$("#commCd").val($("#codeList_depth1").val());    	
    }else if(depth == '2'){
    	$("#commCd").val($("#codeList_depth2").val());    	    	
    	$("#parentCdDc").val($("#codeList_depth1 option:selected").text());
    	
    }else if(depth == '3'){
    	$("#commCd").val($("#codeList_depth3").val());
    	$("#parentCdDc").val($("#codeList_depth1 option:selected").text() + '>' + $("#codeList_depth2 option:selected").text());
    }
    
    if ($("#commCd").val() =='')
    	$("#commCd").attr("readonly",false);    	

}

/* ********************************************************
* 등록화면
******************************************************** */
function fn_enter(sel_name,depth) {
	
	

	$("#codeDepth").val(depth);
    $("#enterDepth").val(sel_name);
    
    if(depth == '1'){    	
    	$("#codeDepth").val(depth);
    }
    else if(depth == '2'){
    	if ($("#codeList_depth1").val() == '' || $("#codeList_depth1").val() == null){
    		alert('공통분류코드를 선택하세요');
    		return;
    	}    	
    	$("#codeDepth").val(depth);
    	$("#parentCd").val($("#codeList_depth1").val());
    	$("#parentCdNm").val($("#codeList_depth1 option:selected").text());	
	}else if(depth == '3'){
		if ($("#codeList_depth2").val() == '' || $("#codeList_depth2").val() == null){
    		alert('공통코드를 선택하세요');
    		return;
    	}		
		$("#codeDepth").val(depth);
    	$("#parentCd").val($("#codeList_depth2").val());
    	$("#parentCdNm").val($("#codeList_depth2 option:selected").text());		
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

    document.listForm.codeOrdrChg.value = '';
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
        
        document.listForm.codeOrdrChg.value = obj.options[i].value + "=" + (i);
        document.listForm.codeOrdrChg.value +=  "," + obj.options[j].value + "=" + (j);
        
        if (sel_name == 'codeList_depth3')
    		document.listForm.action = "<c:url value='/ccm/ccd/CmmDetailCodeOrdrModify.do'/>";  
    	else
    		document.listForm.action = "<c:url value='/ccm/ccd/CmmCodeOrdrModify.do'/>"; 
    	
		document.listForm.submit();	            
    }
    
    //CmmDetailCodeOrdrModify
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

    document.listForm.codeOrdrChg.value = '';
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
        
        document.listForm.codeOrdrChg.value = obj.options[i].value + "=" + (i);
        document.listForm.codeOrdrChg.value +=  "," + obj.options[j].value + "=" + (j);
                
    
        if (sel_name == 'codeList_depth3')
    		document.listForm.action = "<c:url value='/ccm/ccd/CmmDetailCodeOrdrModify.do'/>";  
    	else
    		document.listForm.action = "<c:url value='/ccm/ccd/CmmCodeOrdrModify.do'/>"; 

		document.listForm.submit();        
    }
}


//-->
<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
</script>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">코드관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->

	<DIV id="main" style="display:" class="">
	<p class="tr">
		<c:choose>
			<c:when test="${CmmCodeVO.enterDepth != '' || empty CmmCodeVO.codeDepth !=''}">
				<button type="button" class="btn btn-primary" onclick="fnRegist(); return false;">등록</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-success" onclick="fnModify(); return false;">수정</button>
				<button type="button" class="btn btn-danger" onclick="fnDelete(); return false;">삭제</button>
			</c:otherwise>
		</c:choose>	
	</p>	
		<DIV class="">
			<DIV class="panel-heading">
				 공통코드 목록
			</DIV> 
			<!-- /.panel-heading -->			
			<DIV id="content" class="">
				<form name="listForm" action="<c:url value='/ccm/ccd/CcmCmmCodeList.do'/>" method="post">
			
				
				<table width = "100% " cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<table width = "100% " cellpadding="0" class="table table-striped table-bordered table-hover dataTable no-footer" border="0" summary="분류코드, 분류코드명, 사용여부를 조회하는 공통분류코드 목록 테이블이다.">
				<thead>
				<tr>
					<th class="title" width="36%"  scope="col" nowrap>공통분류코드</th>
					<th class="title" width="32%" scope="col" nowrap>공통코드</th>
					<th class="title" width="4%" scope="col" nowrap></th>
					<th class="title" width="28%" scope="col" nowrap>공통상세코드</th>
					<th class="title" width="4%" scope="col" nowrap></th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td>
						<select name="codeList_depth1" id="codeList_depth1" class="form-control" title="" size="10" onClick="javascript:fnOnSeleteMenu('1',this);">
							<c:forEach var="resultInfo" items="${resultList}" varStatus="status">						
								<!--  <option value="${resultInfo.clCode}" ${resultInfo.clCode == provider ? 'selected="selected"' : '' }>${resultInfo.clCodeNm}</option>-->
								<option value="${resultInfo.clCode}" ${resultInfo.clCode == CmmCodeVO.codeClCd ? 'selected="selected"' : '' }>[${resultInfo.clCode}]${resultInfo.clCodeNm}</option>
								<c:if test="${resultInfo.clCode == CmmCodeVO.codeClCd}">
									<c:set var="clCdNm" value="${resultInfo.clCodeNm}"/>
								</c:if>																										
							</c:forEach>
						</select>		
									    
					</td>
					<td>
						<select name="codeList_depth2" id="codeList_depth2" class="form-control" title="" size="10" onClick="javascript:fnOnSeleteMenu('2',this);">
							<c:forEach var="resultList2" items="${resultListDept2}" varStatus="status">						
								<option value="${resultList2.codeId}" ${resultList2.codeId == CmmCodeVO.codeId ? 'selected="selected"' : '' }>[${resultList2.codeId}]${resultList2.codeIdNm}</option>
								<c:if test="${resultList2.codeId == CmmCodeVO.codeId}">
									<c:set var="cdIdNm" value="${resultList2.codeIdNm}"/>
								</c:if>														
							</c:forEach>
						</select>					    
					</td>
					<td>					
	                   		<input type="button" value="▲" onclick = "javascript:fn_moveUp('codeList_depth2')" >
	                   		<input type="button" value="▼" onclick = "javascript:fn_moveDown('codeList_depth2')">
					</td>
					<td>
						<select name="codeList_depth3" id="codeList_depth3" class="form-control" title="" size="10" onClick="javascript:fnOnSeleteMenu('3',this);">
							<c:forEach var="resultList3" items="${resultListDept3}" varStatus="status">						
								<option value="${resultList3.code}" ${resultList3.code == CmmDetailCodeVO.code ? 'selected="selected"' : '' }>[${resultList3.code}]${resultList3.codeNm}</option>
								<c:if test="${resultList3.code == CmmDetailCodeVO.code}">
									<c:set var="cdNm" value="${resultList3.codeNm}"/>
								</c:if>						
							</c:forEach>
						</select>					    
					</td>
					<td>					
	                   		<input type="button" value="▲" onclick = "javascript:fn_moveUp('codeList_depth3')" >
	                   		<input type="button" value="▼" onclick = "javascript:fn_moveDown('codeList_depth3')">
					</td>
				</tr>	
				<tr>
					<td><button type="button" class="btn btn-primary" onclick="fn_enter('depth1','1'); return false;">추가</button></td>					
					<td><button type="button" class="btn btn-primary" onclick="fn_enter('depth2','2'); return false;">추가</button></td>
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
					<c:when test="${CmmCodeVO.codeDepth == '1'}">						 
						 <c:set var="cdClNm" value="대분류"/>
					</c:when>				
					<c:when test="${CmmCodeVO.codeDepth == '2'}">
						 <c:set var="cdClNm" value="중분류"/>
					</c:when>
					<c:when test="${CmmCodeVO.codeDepth == '3'}">
						 <c:set var="cdClNm" value="소분류"/>
					</c:when>
				</c:choose>						
				
				<table width = "100% " cellpadding="0" class="table table-striped table-bordered table-hover dataTable no-footer" border="0" summary="분류코드, 분류코드명, 사용여부를 조회하는 공통분류코드 목록 테이블이다.">
				<tbody>
				<tr>
					<th class="title" scope="col" nowrap>구분</th>
					<th class="title" scope="col" nowrap>코드상세</th>
				</tr>
				</thead>				
				<tr>
					<td>코드분류</td>
					<td>
						<input type="text" id="cdClNm" name="cdClNm" value="<c:out value='${cdClNm}'/>" readonly class="form-control">
					</td>
				</tr>
				<tr>
					<td>상위코드</td>
					<td>
						<input type="text" id="parentCdDc" name="parentCdDc" value="<c:out value='${CmmCodeVO.parentCd}'/>" readonly class="form-control">
					</td>
				</tr>

				<tr>
					<td>코드</td>
					<td>
						<input type="text" id="commCd" name="commCd" readonly class="form-control">
					</td>
				</tr>
				<tr>
					<td>코드명</td>
					<td>
						<input type="text" id="commCdNm" name="commCdNm" value="<c:out value='${result.commCdNm}'/>" class="form-control">
					</td>
				</tr>	
				  <tr>
				    <td>사용여부</td>
				    <td>
						<select name="useAt" id="useAt" class="form-control">
							<option value="Y" <c:if test="${result.useAt == 'Y'}">selected="selected"</c:if> >Yes</option>
							<option value="N" <c:if test="${result.useAt == 'N'}">selected="selected"</c:if> >No</option>
						</select>
				    </td>
				  </tr>									
				</tbody>
				</table>
				<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
				<input type="hidden" id="codeClCd" name="codeClCd" value="<c:out value='${CmmCodeVO.codeClCd}'/>">				 
				<input type="hidden" id="codeId" name="codeId" value="<c:out value='${CmmCodeVO.codeId}'/>">
				<input type="hidden" id="code" name="code" value="<c:out value='${CmmDetailCodeVO.code}'/>">
				<input type="hidden" id="codeDepth" name="codeDepth" value="<c:out value='${CmmCodeVO.codeDepth}'/>">
				<input type="hidden" id="enterDepth" name="enterDepth">
				<input type="hidden" id="parentCd" name="parentCd" value="<c:out value='${CmmCodeVO.parentCd}'/>">
				<input type="hidden" id="codeOrdrChg" name="codeOrdrChg">								
				   <c:choose>
				       <c:when test="${CmmCodeVO.codeOrdr ne null || empty CmmCodeVO.codeOrdr !=''}">
				       	<input type="hidden" id="codeOrdr" name="codeOrdr" value="<c:out value='${CmmCodeVO.codeOrdr}'/>">
			       	   </c:when>
			       	   <c:otherwise>
			       	   	<input type="hidden" id="codeOrdr" name="codeOrdr" value="0">
			       	   </c:otherwise>
			       </c:choose>					
				
				</form>
				
				<form name="Form" id="Form" method="post" action="">
					<input type=hidden name="clCode">
					<input type="submit" id="invisible" class="invisible">
				</form>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->
		<p class="tr">
			<c:choose>
				<c:when test="${CmmCodeVO.enterDepth != '' || empty CmmCodeVO.codeDepth !=''}">
					<button type="button" class="btn btn-primary" onclick="fnRegist(); return false;">등록</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-success" onclick="fnModify(); return false;">수정</button>
					<button type="button" class="btn btn-danger" onclick="fnDelete(); return false;">삭제</button>
				</c:otherwise>
			</c:choose>	
		</p>									
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->
</body>
<script language="javascript">	
	<!-- 코드상세정보조회 -->
	fnGetDetail();
</script>
</html>
