<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<title>전자도서관 권한 관리 > 그룹 등록</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--

//목록가기
function fnElmGrpList()	
{
	location.href ="/elm/grp/ElmGrpList.do";
}

// 삭제하기
function fnElmGrpDelete()	
{
	if(confirm("삭제하시겠습니까?") == true)
	{
	
		var form = document.getElementById("detailForm");
		form.cmd.value = "del";
		form.method = "POST";
		form.action = "/elm/grp/ElmGrpDetail.do";
		form.submit();
	}
}

	// 수정하기
function fnElmGrpUpdate()	
{
	var form = document.getElementById("detailForm");
	
	if(confirm("수정하시겠습니까?") == false)
	{
		return;
	}
	

	// 그룹 ID 체크
	if($.trim(form.userGroupId.value).length == 0)
	{
		alert("그룹ID를 입력하셔야 합니다.");
		form.userGroupId.focus();
		return;
	}

	// 그룹명 체크
	if($.trim(form.userGroupNm.value).length == 0)
	{
		alert("그룹명을 입력하셔야 합니다.");
		form.userGroupNm.focus();
		return;
	}

	// 클래스명 체크
	if($.trim(form.userClassNm.value).length == 0)
	{
		alert("클래스를 입력하셔야 합니다.");
		form.userClassNm.focus();
		return;
	}

	//그룹 ID 숫자만 입력 여부 체크
	if(isNaN(form.userGroupId.value) == true)
	{
		alert("숫자만 입력하세요.");
		form.userGroupId.value = "";
		form.userGroupId.focus();
		return;
	}

	form.cmd.value = "edit";
	form.method = "POST";
	form.action = "/elm/grp/ElmGrpDetail.do";
	form.submit();

}

//클래스 체크
function fnUserClassCheck()
{
	var form 				= 	$("#detailForm");
	var userClassNm 		= 	form.find("#userClassNm");
	var userSubClassNm 		= 	form.find("#userSubClassNm");

	if(userClassNm.val() != "국회직원")
	{
		$(".subclass_staff").hide();
		$(".subclass_no").show();
		userSubClassNm.val("");
	}
	else
	{
		$(".subclass_staff").show();
		$(".subclass_no").hide();
		
		if(userSubClassNm.val() == '')
		{
			userSubClassNm.val("국회직원");
		}
	}
}

$(document).ready(function() {
	fnUserClassCheck();
});
-->

-->
</script>

</head>

<body>
<jsp:include page="/cmm/top/top.do" />


		
		
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">그룹 등록</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>그룹 상세정보</h2>
            <form id="detailForm" name="detailForm">
            <input type="hidden" id="cmd" name="cmd" />
            <input type="hidden" id="userGroupId" name="userGroupId" value="<c:out value="${userClassVO.userGroupId}" />" />
            
			<table class="table table-striped table-bordered table-hover "  id="">
				<colgroup>
					<col width="30%" />
					<col width="" />
				</colgroup>
				<tbody>
					<tr>
						<th>그룹 ID</th>
						<td><c:out value="${userClassVO.userGroupId}" /></td>
					</tr>
					<tr>
						<th>그룹명</th>
						<td><input type="text" name="userGroupNm" class="ip input-sm" style="width:30%;" value="<c:out value="${userClassVO.userGroupNm}" />" /></td>
					</tr>
					<tr>
						<th>클래스</th>
						<td>
							<select id="userClassNm" name="userClassNm" class="input-sm" style="width:30%;" onchange="fnUserClassCheck()">
								<option class="normal" value="일반" <c:if test="${userClassVO.userClassNm == '일반'}">selected</c:if> >일반</option>
								<option class="user" value="협정기관 이용자" <c:if test="${userClassVO.userClassNm == '협정기관 이용자'}">selected</c:if> >협정기관 이용자</option>
								<option class="guest" value="국회도서관 방문자" <c:if test="${userClassVO.userClassNm == '국회도서관 방문자'}">selected</c:if> >국회도서관 방문자</option>
								<option class="staff" value="국회직원" <c:if test="${userClassVO.userClassNm == '국회직원'}">selected</c:if> >국회직원</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>서브클래스</th>
						<td>
							<select id="userSubClassNm" name="userSubClassNm" class="input-sm" style="width:30%;" onchange="fnUserClassCheck()">
								<option class="subclass_no" value="" <c:if test="${userClassVO.userSubClassNm == ''}">selected</c:if> >없음</option>
								<option class="subclass_staff" value="국회직원" <c:if test="${userClassVO.userSubClassNm == '국회직원'}">selected</c:if> >국회직원</option>
								<option class="subclass_staff" value="입법보좌직원" <c:if test="${userClassVO.userSubClassNm == '입법보좌직원'}">selected</c:if> >입법보좌직원</option>
								<option class="subclass_staff" value="국회의원" <c:if test="${userClassVO.userSubClassNm == '국회의원'}">selected</c:if> >국회의원</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
            </form>

			<p class="tr">
                <button id="goList" type="button" onclick="fnElmGrpList();" class="btn btn-default">목록</button>
                <button id="goUpdate" type="button" onclick="fnElmGrpUpdate();" class="btn btn-success">수정</button>
                <button id="goDelete" type="button" onclick="fnElmGrpDelete();" class="btn btn-danger">삭제</button>
			</p>

    	</div><!--//page-wrapper-->

</body>
</html>
