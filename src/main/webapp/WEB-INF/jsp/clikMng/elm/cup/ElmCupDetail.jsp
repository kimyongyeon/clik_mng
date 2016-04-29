<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form" %>
<%

/**
 * @Class Name : ElmGroupList.java
 * @Description : ElmGroupList jsp
 */

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>전자도서관 권한 관리 > 권한등록 > 저작권허락 권한 관리</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
	function fnElmCupList()
	{
		location.href ="<c:url value="/elm/cup/ElmCupList.do"/>";
	}

	function fnElmCupUpdate()
	{
		var obj;
		var form = document.copyrightPermVO;
		form.action =  "<c:url value="/elm/cup/ElmCupRegistProc.do"/>";

		if(confirm("<spring:message code="common.save.msg" />"))
		{

			obj = form.cpyrhtCode;
			if($.trim(obj.value).length == 0)
			{
				alert("Case ID를 입력하셔야 합니다.");
				obj.focus();
				return;
			}

			obj = form.chrgnAt;
			if($.trim(obj.value).length == 0)
			{
				alert("과금여부를 입력하셔야 합니다.");
				obj.focus();
				return;
			}

			obj = form.cpyrhtUsePermCode;
			if($.trim(obj.value).length == 0)
			{
				alert("저작권 이용허락을 입력하셔야 합니다.");
				obj.focus();
				return;
			}

			obj = form.cpyrhtSvcScopeCode;
			if($.trim(obj.value).length == 0)
			{
				alert("서비스 범위을 입력하셔야 합니다.");
				obj.focus();
				return;
			}

			obj = form.userGroupId;
			if($.trim(obj.value).length == 0)
			{
				alert("그룹을 입력하셔야 합니다.");
				obj.focus();
				return;
			}

			obj = form.userGroupId;
			if($.trim(obj.value).length == 0)
			{
				alert("그룹을 입력하셔야 합니다.");
				obj.focus();
				return;
			}
			
	        // 파일 확장자 체크
	        var confirmExt;
	        var thumbext = document.getElementById('fileUploader').value; 
	         
	        if(document.getElementById('fileUploader').value != "") {
	            confirmExt = fn_confirmExt(thumbext);
	            if(!confirmExt) {
	            	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
	            	return;
	            }
	        }

			form.submit();
		}
	}

	
	/**
	 * 파일 확장자 체크
	 * 해당 확장자가 아닌 다른 확장자로 업로드 실행 시 필터
	 */

	function fn_confirmExt(str) {
		str = str.slice(str.lastIndexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.
	
		var result = true;
	
		if(str != "jpg" && str != "png" &&  str != "gif" &&  str != "bmp"
			&& str != "alz" && str != "doc" && str != "docx" && str != "hwp" && str != "jpeg" 
			&& str != "pdf" && str != "ppt" && str != "pptx" && str != "pptx" && str != "txt"
			&& str != "xls" && str != "xlsx" && str != "zip" ){ //확장자를 확인합니다.
	
			//alert('썸네일은 이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
			result = false;
			return result;
	
		} else {
			return result;
		}
	}

	function fnElmCupDelete()
	{
		var form = document.copyrightPermVO;
		form.cmd.value = "del";
		form.method = "POST";
		form.action = "<c:url value="/elm/cup/ElmCupDetail.do"/>";
		form.submit();
	}
	
	function fnIconDelete()
	{
		$('#iconFileNm').val('');
		$('.td_icon .icon_regist').show();
		$('.td_icon .icon_view').hide();
	}

	function fnIconView()
	{
		$('.td_icon .icon_regist').hide();
		$('.td_icon .icon_view').show();
	}

	$(document).ready(function() {
		
		$("#iconDelete").click(function(e) {
			e.preventDefault();
			fnIconDelete();
		});
		
		<c:choose>
			<c:when test="${result.iconFileNm != null}">
				fnIconView();
			</c:when>
			<c:otherwise>
				fnIconDelete();
			</c:otherwise>
		</c:choose>
	});
-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />


	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">저작권허락 권한 관리</h1>
			</div><!-- /.col-lg-12 -->
		</div><!-- /.row -->

		<div class="page">
			<h2>저작권허락 권한 상세정보</h2>

			<form:form commandName="copyrightPermVO" name="copyrightPermVO" action="${pageContext.request.contextPath}/elm/cup/ElmCupRegist.do" method="post" enctype="multipart/form-data">

			<input type="hidden" id="cmd" name="cmd" value="edit" />
			<input type="hidden" id="oriCpyrhtCode" name="oriCpyrhtCode" value="${result.cpyrhtCode}" />

				<table class="table table-striped table-bordered table-hover " id="">
					<colgroup>
						<col width="20%" />
						<col width="" />
					</colgroup>
					<tbody>
						<tr>
							<th>저작권 처리코드</th>
							<td>
								<input type="text" name="cpyrhtCode" class="ip input-sm" style="width:30%;" value="${result.cpyrhtCode}" />
							</td>
						</tr>
						<tr>
							<th>과금여부</th>
							<td>
								<select name="chrgnAt" class=" input-sm"  style="width:30%;">
									<option value="">과금여부 선택</option>
									<option value="Y" <c:if test="${result.chrgnAt == 'Y'}"> selected</c:if>>과금</option>
									<option value="N" <c:if test="${result.chrgnAt != 'Y'}"> selected</c:if>>비과금</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>저작권 이용허락</th>
							<td>
								<select name="cpyrhtUsePermCode" class=" input-sm"  style="width:30%;">
									<option value="">저작권 이용허락 선택</option>
									<c:forEach var="open" items="${cpyUseList}">
										<option value="${open.code}" <c:if test="${result.cpyrhtUsePermCode == open.code}"> selected</c:if>>${open.codeNm} ( ${open.code} )</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>서비스 범위</th>
							<td>
								<select name="cpyrhtSvcScopeCode" class=" input-sm"  style="width:30%;">
									<option>서비스 범위 선택</option>
									<c:forEach var="scope" items="${svcScopeList}">
										<option value="${scope.code}" <c:if test="${result.cpyrhtSvcScopeCode == scope.code}"> selected</c:if>>${scope.codeNm} ( ${scope.code} )</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>그룹</th>
							<td>
								<select name="userGroupId" class=" input-sm"  style="width:30%;">
									<c:forEach var="cl" items="${userClassList}">
										<option value="${cl.userGroupId}" <c:if test="${result.userGroupId == cl.userGroupId}"> selected</c:if>>${cl.userGroupNm} ( ${cl.userGroupId} )</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>아이콘 등록 [${result.iconFileNm}]</th>
							<td class="td_icon">
								<input type="hidden" id="iconFileNm"  name="iconFileNm" value="${result.iconFileNm}" />
							
								<div class="icon_regist">
									<input type="file" id="fileUploader" name="file_1" title="아이콘" class="input-sm ip" style="display:inline;" />								
								</div>
								
								<div class="icon_view">
									<c:import url="/cmm/fms/selectImageFileInf.do" charEncoding="utf-8">
										<c:param name="atchFileId" value="${result.iconFileNm}" />
										<c:param name="fileSn" value="0" />
									</c:import>
									
									<button type="button" id="iconDelete" class="btn btn-danger">삭제</button>
								</div>

							</td>
						</tr>
						<tr>
							<th>아이콘 메시지</th>
							<td>
								<input type="text" name="iconMssage" class="input-sm ip" style="display:inline; width:100%;" value="${result.iconMssage}" />
							</td>
						</tr>
					</tbody>
				</table>
			</form:form>

			<p class="tr">
			<button id="goList" type="button" onclick="fnElmCupList();" class="btn btn-default">목록</button>
			<button id="goUpdate" type="button" onclick="fnElmCupUpdate();" class="btn btn-success">수정</button>
			<button id="goDelete" type="button" onclick="fnElmCupDelete();" class="btn btn-danger">삭제</button>
			</p>

		</div><!--//page-->
	</div><!--//page-wrapper-->


</body>
</html>
