<%--
  Class Name : EgovPopupList.jsp
  Description : 팝업창관리 목록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------

    author   : 
    since    : 

    Copyright (C) 2009 by MOPAS  All right reserved.
--%>
<%@ page contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>

<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/clikmng/cmm/" />
<c:set var="CssUrl" value="${pageContext.request.contextPath}/css/clikmng/cmm/" />
<c:set var="JsUrl"  value="${pageContext.request.contextPath}/js/clikmng/sit/pwm/"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>팝업창 관리</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************* */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/sit/pwm/listPopup.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
	varForm.pageUnit.value = val;
	varForm.action = "<c:url value='/sit/pwm/listPopup.do'/>";
    varForm.submit();
}

/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_PopupManage(){
	location.href = "<c:url value='/sit/pwm/registPopup.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_PopupManage(popupId){
	var vFrom = document.listForm;
	vFrom.popupId.value = popupId;
	vFrom.action = "<c:url value='/sit/pwm/detailPopup.do' />";
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_PopupManage(){
	var vFrom = document.listForm;

	if(vFrom.searchCondition.value != '' && vFrom.searchKeyword.value == '') {
		alert('검색어를 입력해 주세요.');
		return;
	}
	
	
	vFrom.action = "<c:url value='/sit/pwm/listPopup.do' />";
	vFrom.submit();

}
/* ********************************************************
* 체크 박스 선택 함수
******************************************************** */
function fn_egov_checkAll_PopupManage(){

	var FLength = document.getElementsByName("checkList").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if( FLength == 1){
		document.getElementById("checkList").checked = checkAllValue;
	}{
			for(var i=0; i < FLength; i++)
			{
				document.getElementsByName("checkList")[i].checked = checkAllValue;
			}
		}

}
/* ********************************************************
* 팝업창 미리보기
******************************************************** */
function fn_egov_view_PopupManage(){


	var FLength = document.getElementsByName("checkList").length;

	
	if( $(".check2").is(':checked') == false)
	{
		alert("선택한 항목이 존재하지 않습니다.");
		return false;
	}
	else if( FLength == 1){
		if(document.getElementById("checkList").checked == true){
			fn_egov_ajaxPopupInfo_PopupManage( document.getElementById("checkList").value );
		}
	}
	else
	{
		for(var i=0; i < FLength; i++)
		{
			if(document.getElementsByName("checkList")[i].checked == true){
				fn_egov_ajaxPopupInfo_PopupManage( document.getElementsByName("checkList")[i].value );
			}
		}
	}
	return;

}
/* ********************************************************
* 팝업창 정보 Ajax통신으로 정보 획득
******************************************************** */
function fn_egov_ajaxPopupInfo_PopupManage(popupIds){
	var url = "<c:url value='/sit/pwm/ajaxPopupManageInfo.do' />";

	var param = {
			popupId: popupIds
	};
		
   $.ajax({      
        type:"POST",  
        url:url,      
        data:param,      
        success:function(returnValue){   
        	var returnValueArr = returnValue.split("||");
       	   	fn_egov_popupOpen_PopupManage(popupIds,
           	    	returnValueArr[0],
           	    	returnValueArr[1],
           	    	returnValueArr[2],
           	    	returnValueArr[3],
           	    	returnValueArr[4],
           	    	returnValueArr[5],
           	    	returnValueArr[6]);
        },     
        error:function(e){  
            alert(e.responseText);  
        }  
    }); 	
	

}

/* ********************************************************
* 팝업창  오픈
******************************************************** */
function fn_egov_popupOpen_PopupManage(popupId,fileUrl,width,height,top,left,stopVewAt,imageFileNm){

	var url = "<c:url value='/sit/pwm/openPopupManage.do' />?";
	url = url + "fileUrl=" + fileUrl;
	url = url + "&stopVewAt=" + stopVewAt;
	url = url + "&popupId=" + popupId;
	url = url + "&imageFileNm=" + imageFileNm;
	
	var name = popupId;
	var openWindows = window.open(url,name,"width="+width+",height="+height+",top="+top+",left="+left+",toolbar=no,status=no,location=no,scrollbars=no,menubar=no,resizable=no");

	if (window.focus) {openWindows.focus()}
}

/* ********************************************************
* 팝업창  오픈 쿠키 정보 OPEN
******************************************************** */
function fnGetCookie(name) {
	  var prefix = name + "=";

	  var cookieStartIndex = document.cookie.indexOf(prefix);
	  if (cookieStartIndex == -1) return null;
	  var cookieEndIndex = document.cookie.indexOf(";", cookieStartIndex + prefix.length);
	  if (cookieEndIndex == -1) cookieEndIndex = document.cookie.length;


	  return unescape(document.cookie.substring(cookieStartIndex + prefix.length, cookieEndIndex));
}

/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_delete(){
	var isResult = true;
	var FLength = document.getElementsByName("checkList").length;

	if( $(".check2").is(':checked') == false)
	{
		alert("선택한 항목이 존재하지 않습니다.");
		return false;
	}

	if(confirm("삭제하시겠습니까?") == true)
	{
		if( FLength == 1)
		{
			if(document.getElementById("checkList").checked == true)
			{
				isResult = fn_egov_delete_PopupManage( document.getElementById("checkList").value );
			}
		}
		else
		{
			for(var i=0; i < FLength; i++)
			{
				if(document.getElementsByName("checkList")[i].checked == true)
				{
					isResult = fn_egov_delete_PopupManage( document.getElementsByName("checkList")[i].value );
				}
				
				if(isResult == false)
				{
					return false;
				}
			}
		}
		
		document.location = "/sit/pwm/listPopup.do";
	}
	else
	{
		return;
	}
	
		
	
	
} 
 
 
function fn_egov_delete_PopupManage(popupIds){

	var isResult = true;
	var varFrom = document.popupManageVO;
	var url = "<c:url value='/sit/pwm/ajaxPopupManageDelte.do' />";
	var param = {
			popupId: popupIds,
			cmd:"del"
	};
			
	$.ajax({      
			type:"POST",  
			url:url,      
			data:param,      
			success:function(returnValue){   
				$("#tr_"+popupIds).hide();
				isResult = true;
			},     
			error:function(e){  
				alert("삭제 과정 중 오류가 발생하였습니다. \n잠시 후 다시 시도해주세요.");
				isResult = false;;
			}  
    }); 	

	return isResult;
}

/* ********************************************************
* 등록화면 보기
******************************************************** */
function fnGoRegist() {
	location.href = "/sit/pwm/registPopup.do";
}
</script>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">팝업창 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
  	
	<div id="border" style="display:" class="">
		<DIV class="">
			<h2>
				 팝업창 목록
			</h2>
			<!-- /.panel-heading -->			
			<DIV id="content" class="">	
				<form name="listForm" action="<c:url value='/sit/pwm/listPopup.do'/>" method="post">
				<div class="select_box">
					<span>
						<select name="searchCondition" class=""  title="검색조건선택">
						   <option value=''>전체</option>
						   <option value='POPUP_SJ_NM' <c:if test="${searchCondition == 'POPUP_SJ_NM'}">selected</c:if>>팝업창명</option>
						   <option value='FILE_URL' <c:if test="${searchCondition == 'FILE_URL'}">selected</c:if>>팝업창URL</option>
					   </select>
					   <input name="searchKeyword" class=" input-sm input-search" type="text" value="<c:out value="${searchKeyword}" />" title="검색단어입력">
						<input type="submit" class="btn btn-primary" value="<spring:message code="button.search" />" onclick="fn_egov_search_PopupManage(); return false;">
					</span>
				</div>
				
				<div class="page">
					총 건수 : <c:out value="${paginationInfo.totalRecordCount}" /> 건
	
					<span>
						출력건수
							<select title="쪽당출력건수" id="pageSize" name="pageSize" onchange="fnChgListCnt(this.value)">
								<option value="10" <c:if test="${popupManageVO.pageUnit == '10' }">selected</c:if>>10</option>
								<option value="30" <c:if test="${popupManageVO.pageUnit == '30' }">selected</c:if>>30</option>
								<option value="50" <c:if test="${popupManageVO.pageUnit == '50' }">selected</c:if>>50</option>
								<option value="100" <c:if test="${popupManageVO.pageUnit == '100' }">selected</c:if>>100</option>
		                    </select>	
					</span>
				</div>				
				
				<input name="popupId" type="hidden" value="">
				<input type="hidden" name="pageIndex" value="<c:out value='${popupManageVO.pageIndex}'/>"/>
				<input type="hidden" name="pageUnit"  value="<c:out value='${popupManageVO.pageUnit}'/>"/>
				</form>
				
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				<table width="100%" cellpadding="0" class="table table-striped table-bordered" border="0">
				<thead>
				  <tr>
				    <th class="title" width="5%" nowrap>번호</th>
				    <th class="title" width="30%" nowrap>제목</th>
				    <th class="title" width="35%" nowrap>게시 기간</th>
				    <th class="title" width="20%" nowrap>링크URL</th>
				    <th class="title" width="*" nowrap>게시상태</th>
					<th class="title" width="5%" nowrap><input type="checkbox" name="checkAll" id="checkAll" class="check2" value="1" onClick="fn_egov_checkAll_PopupManage();"></th>				    
				  </tr>
				</thead>
				<tbody>
				<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
				
				<c:if test="${fn:length(resultList) == 0}">
				<tr>
				<td class="lt_text3" colspan="7">
					<spring:message code="common.nodata.msg" />
				</td>
				</tr>
				</c:if>
				 <%-- 데이터를 화면에 출력해준다 --%>
				<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
				<tr id="tr_${resultInfo.popupId}" >
				    <td class="lt_text3" nowrap><c:out value="${(popupManageVO.pageIndex-1) * popupManageVO.pageSize + status.count}"/></td>
				     <td class="lt_text3L">
				    	<input name="popupId" type="hidden" value="${resultInfo.popupId}">
						<input name="pageIndex" type="hidden" value="<c:out value='${popupManageVO.pageIndex}'/>"/>
						<a href="<c:url value='/sit/pwm/detailPopup.do'/>?popupId=${resultInfo.popupId}&pageIndex=<c:out value='${popupManageVO.pageIndex}'/>">
							<c:out value="${resultInfo.popupTitleNm}"/>
						</a>
				     </td>
				    <td class="lt_text3">
					 	<c:out value="${fn:substring(resultInfo.ntceBgnde, 0, 4)}"/>-<c:out value="${fn:substring(resultInfo.ntceBgnde, 4, 6)}"/>-<c:out value="${fn:substring(resultInfo.ntceBgnde, 6, 8)}"/> <c:out value="${fn:substring(resultInfo.ntceBgnde, 8, 10)}"/>시 <c:out value="${fn:substring(resultInfo.ntceBgnde, 10, 12)}"/>분
					 	~
					 	<c:out value="${fn:substring(resultInfo.ntceEndde, 0, 4)}"/>-<c:out value="${fn:substring(resultInfo.ntceEndde, 4, 6)}"/>-<c:out value="${fn:substring(resultInfo.ntceEndde, 6, 8)}"/> <c:out value="${fn:substring(resultInfo.ntceEndde, 8, 10)}"/>시 <c:out value="${fn:substring(resultInfo.ntceEndde, 10, 12)}"/>분
				    <td class="lt_text3L" nowrap>
				    	<c:out value="${resultInfo.fileUrl}"/>
				    </td>
				    <td class="lt_text3">
						<c:out value="${resultInfo.ntceAt}"/>
				    </td>
				    <td class="lt_text3">
						<input type="checkbox" name="checkList" id="checkList" class="check2" value="${resultInfo.popupId}">
				    </td>
				</tr>
				</c:forEach>
				</tbody>
				</table>
				
				<p class="tr">
				<button type="button" class="btn btn-primary" onclick="javascript:fnGoRegist();"><spring:message code="button.create" /></button>
				<button type="button" class="btn btn-success" onclick="JavaScript:fn_egov_view_PopupManage()">미리보기</button>
				<button type="button" class="btn btn-danger" onclick="JavaScript:fn_delete()">삭제</button>
				</p>  	
				
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<div align="center">
					<div>
						<ul class="pagination">
							<ui:pagination paginationInfo = "${paginationInfo}"
								type="image"
								jsFunction="linkPage"
								/>
						</ul>
					</div>
				</div>
			</div>
			<!-- /panel body -->
		</div>
		<!-- /.panel panel-default -->	 
	</div>		
	<!-- /.MAIN -->		
</div>	
<!-- /page-wrapper -->	


<form commandName="popupManageVO" name="popupManageVO" action="/sit/pwm/updtPopup.do" method="post">
				<input name="popupId" type="hidden" value="">
				<input name="cmd" type="hidden" value="del"/>
</form>


</body>
</html>
