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
	
	//등록하기
	function fnElmGrpRegist() 
	{
		var form = document.getElementById("regForm");

		if(confirm("등록하시겠습니까?") == false)
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
		
		form.cmd.value = "save";
		form.method = "POST";
		//form.action = "/elm/grp/ElmGrpRegist.do";
		form.submit();
	}

	//클래스 체크
	function fnUserClassCheck()
	{
		var form 				= 	$("#regForm");
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
	<c:if test="${!empty msg}">alert("${msg}");</c:if>
});
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
			<h2>그룹 등록</h2>
			
			<form id="regForm" name="regForm" method="post">
				<input type="hidden" id="cmd" name="cmd" />

				<table class="table table-striped table-bordered table-hover">
					<colgroup>
						<col width="30%" />
						<col width="" />
					</colgroup>
					<tbody>
						<tr>
							<th>그룹 ID</th>
							<td><input type="text" name="userGroupId" class="ip input-sm" style="width:30%;" /></td>
						</tr>
						<tr>
							<th>그룹명</th>
							<td><input type="text" name="userGroupNm" class="ip input-sm" style="width:30%;" /></td>
						</tr>
						<tr>
							<th>클래스</th>
							<td>
								<select id="userClassNm" name="userClassNm" class="input-sm" style="width:30%;" onchange="fnUserClassCheck()">
									<option class="normal" value="일반">일반</option>
									<option class="user" value="협정기관 이용자">협정기관 이용자</option>
									<option class="guest" value="국회도서관 방문자">국회도서관 방문자</option>
									<option class="staff" value="국회직원">국회직원</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>서브클래스</th>
							<td>
								<select id="userSubClassNm" name="userSubClassNm" class="input-sm" style="width:30%;" onchange="fnUserClassCheck()">
									<option class="subclass_no" value="">없음</option>
									<option class="subclass_staff" value="국회직원">국회직원</option>
									<option class="subclass_staff" value="입법보좌직원">입법보좌직원</option>
									<option class="subclass_staff" value="국회의원">국회의원</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</form>

			<p class="tr">
				<button id="goList" type="button"  onclick="fnElmGrpList();"class="btn btn-default">목록</button>
				<button id="goRegist" type="button" onclick="fnElmGrpRegist();" class="btn btn-success">등록</button>
			</p>

		</div><!--//page-wrapper-->
		<!-- /.panel-body -->

</body>
</html>
