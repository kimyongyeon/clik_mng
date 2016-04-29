<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>그룹 상세정보</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mngManage" staticJavascript="false" xhtml="true" cdata="false"/>
 
<script type="text/javaScript" language="javascript">
<!--
/******************************************
 * 구성원 선택
 ******************************************/
function fnPopSelectGroup() {

	var url = "<c:url value='/mim/PopSearchMember.do' />";
	var name = "popSearchMember";
	var openWindows = window.open(url, name, "height=950, width=750, top=50, left=20, scrollbars=no, resizable=no");
	

	if (window.focus) {openWindows.focus()}
}



/******************************************
 * 목록
 ******************************************/
function fnGroupList() {
	var f = document.getElementById("updateForm");
    f.action = "<c:url value='/mim/GroupList.do'/>";
    f.submit();
}

/******************************************
 * 수정
 ******************************************/

function fnGroupRegist(emailGroupId) {
	if(confirm("수정 하시겠습니까?")){
	    var f = document.getElementById("updateForm");
	    f.cmd.value = 'update';
	    f.emailGroupId.value = emailGroupId;
	    f.action = "<c:url value='/mim/GroupUpdate.do'/>";
	    f.submit();
	}
}


/******************************************
 * 삭제
 ******************************************/

function fnGroupDelete(emailGroupId) {
	if(confirm("삭제 하시겠습니까?")){
	    var f = document.getElementById("updateForm");
	    f.cmd.value = 'delete';
	    f.emailGroupId.value = emailGroupId;
	    f.action = "<c:url value='/mim/GroupUpdate.do'/>";
	    f.submit();
	}
}

-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form id="updateForm" name="updateForm" method="post" >
<input type="hidden" name="emailGroupId" id="emailGroupId" />
<input type="hidden" name="cmd" id="cmd" />

		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">그룹 설정</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>그룹 상세정보</h2>
            
			

				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="15%" />
						
						<col />
					 </colgroup>
					<tbody>
						<tr>
							<th>그룹명</th>
							<td><input type="text" name="groupNm"  id="groupNm" class="ip input-sm" style="width:30%;" value="<c:out value="${resultGroupInfo.groupNm }" />" /></td>
						</tr>
						<tr>
							<th>설명</th>
							<td><input type="text" name="dc" id="dc" class="ip input-sm" style="width:100%;" value="<c:out value="${resultGroupInfo.dc }" />" /></td>
						</tr>
						<tr>
							<th>구성원 <br/><br/><button type="button" class="btn btn-success" onclick="fnPopSelectGroup(); return false;">선택</button></th>
							<td><textarea id="groupArea" name="groupArea" cols="100" rows="10" style="width:100%;" class="ip"></textarea></td>
						</tr>
						<tr>
							<th>수신거부목록</th>
							<td>
								<table class="table table-striped table-bordered table-hover "  id="">
									<thead>
										<tr>
											<th>번호</th>
											<th>직업</th>
											<th>이름</th>
											<th>이메일</th>
											<th>수신거부일시</th>
										</tr>
									</thead>
									<tbody>
									<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
									<c:if test="${fn:length(resultRejectList) == 0}">
										<tr>
											<td colspan="5"><spring:message code="common.nodata.msg" /></td>
										</tr>
									</c:if>
									<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>				
									<c:forEach var="x" items="${resultRejectList}" varStatus="s">								
										<tr>
											<td>${(searchVO.pageIndex-1) * searchVO.pageSize + s.count}</td>
											<td><c:out value="${x.rejectRcverJob}" /></td>
											<td><c:out value="${x.rejectRcverNm}" /></td>
											<td><c:out value="${x.rejectRcverEmail}" /></td>
											<td><c:out value="${x.rejectRecptnPnttm}" /></td>
										</tr>
									</c:forEach>	
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>

				<p class="tr">
					<button type="button" class="btn btn-default" onclick="fnGroupList();">목록</button>
					<button type="button" class="btn btn-success" onclick="fnGroupRegist('<c:out value="${resultGroupInfo.emailGroupId}" />');">수정</button>
					<button type="button" class="btn btn-danger" onclick="fnGroupDelete('<c:out value="${resultGroupInfo.emailGroupId}" />');">삭제</button>
				</p>
        </div>
        <!-- /#page-wrapper -->
        
        
<script type="text/javaScript" language="javascript">
/******************************************
 * 구성원 textarea에 입력
 ******************************************/
$(document).ready(function () {
	
	document.updateForm.groupArea.value = '${resultGroupDtlsInfo}';
	
    $("#groupArea").select2({tags: []
    										, dropdownCss: {display: 'none'}
											, tokenSeparators: [",", ";"] });
	
	
	
});

function addTextarea(obj) {
		
	var list = new Array();
 	$.each(obj, function( index, value ) {
		//alert("name: "+ value.name +" email: "+ value.email);
		list[index] = "\""+value.name+"(" + value.email +")\"";
	});
 	
 	if (document.updateForm.groupArea.value == "") {
 		$('textarea[id=groupArea]').val(list);	
 	} else {
 		$('#groupArea').val($('#groupArea').val()+","+list); 
 	}
 	
 	$("#groupArea").select2({tags: list
     									, dropdownCss: {display: 'none'}
     									, tokenSeparators: [",", ";"] });
	
}

function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 
</script>         
        
</body>
</html>

