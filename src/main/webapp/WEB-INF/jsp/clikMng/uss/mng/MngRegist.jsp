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
<title>관리자 등록</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mngManage" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">
<!--

/******************************************
 * 직원찾기
 ******************************************/

function fnSearchEmp() {
	var f = document.getElementById("mngManage");
	var winName = "EMPListView";
	var w = 1000;
	var h = 600;
	var x = (screen.width / 2) - (w / 2);
	var y = (screen.height / 2) - (h / 2);
	var menubar = "no" ;
	var toolbar = "no" ;
	var locat = "no" ;
	var scrollbars = "yes" ;
	var status = "no" ;
	winOpens("about:blank",winName,w,h,x,y,menubar,toolbar,locat,scrollbars,status);
	f.target = winName;
	f.action = "<c:url value='/uss/mng/PopSearchEmp.do'/>";
	f.submit();
}

function fncRoleInsert() {

	var varFrom = document.getElementById("mngManage");
	varFrom.target = "_self";
	varFrom.action = "<c:url value='/uss/mng/MngRegist.do'/>";

	// if( $("#mngrOptVal").val() == "1" ) $("#mngrPw").val( $("#mngrId").val() ) ;

	/*
	* 2015.04.13
	* 직원 ID를 암호화 된 값으로 등록
	*/
	
	if($("#mngrId").val() == null){
		alert('아이디 항목을 확인해 주세요.');
		return;
	}else if($("#mngrNm").val() == null){
		alert('이름 항목을 확인해 주세요.');
		return;
	}else if($("#authorCode").val() == 0){
		alert('권한을 선택해 주세요.');
		return;
	}
	
	if(confirm("저장 하시겠습니까?")){
		varFrom.submit();
	}
}

function fncSelectMngList() {
	location.href="/uss/mng/MngList.do";
}

// 비직원 등록 기능 추가
function setMem(m){

	if (m == '1')
	{ // member
		
		$("#mngrOptVal").val("1");
	
		$(".txtGrp").show();
		
		// 직원찾기 활성화
		$("#searchEmp").attr("disabled",false);
		$("#searchEmp").css("display","inline-block");

		// 아이디 비활성화
		//$("#mngrId").attr("type","hidden");
		//$("#mngrId").css("display","none");
		//$("#mngrId").val("");
		$("input[name=mngrId]").attr("readonly",true);
		
		
		// 패스워드 비활성
		$("#mngrPwBox").css("display","none");
		$("#mngrPw").val("");

		// 이름 비활성
		//$("#mngrNm").attr("type","hidden");
		//$("#mngrNm").css("display","none");
		//$("#mngrNm").val("");
		$("input[name=mngrNm]").attr("readonly",true);
		

		// 이메일 비활성
		//$("#mngrEmail").attr("type","hidden");
		//$("#mngrEmail").css("display","none");
		//$("#mngrEmail").val("");
		$("input[name=mngrEmail]").attr("readonly",true);
		

		// 부서 비활성
		//$("#mngrDept").attr("type","hidden");
		//$("#mngrDept").css("display","none");
		//$("#mngrDept").val("");
		$("input[name=mngrDept]").attr("readonly",true);


	} else { // no member

		$("#mngrOptVal").val("2");
	
		$("input[name=mngrId]").attr("readonly",false);
		$("input[name=mngrNm]").attr("readonly",false);
		$("input[name=mngrEmail]").attr("readonly",false);
		$("input[name=mngrDept]").attr("readonly",false);
	
	
		$(".txtGrp").hide();
		

		// 직원찾기 비활성
		$("#searchEmp").attr("disabled",true);
		$("#searchEmp").css("display","none");
		
		// 아이디 활성화
		//$("#mngrId").attr("type",'text');
		$("#mngrId").css("display","inline-block");
		$("#mngrId").val("");

		
		// 패스워드 활성	
		$("#mngrPwBox").css("display","");
		$("#mngrPw").val("");

		// 이름 활성
		//$("#mngrNm").attr("type",'text');
		$("#mngrNm").css("display","inline-block");
		$("#mngrNm").val("");

		// 이메일 활성
		//$("#mngrEmail").attr("type",'text');
		$("#mngrEmail").css("display","inline-block");
		$("#mngrEmail").val("");

		// 부서 비활성
		//$("#mngrDept").attr("type","text");
		$("#mngrDept").css("display","inline-block");
		$("#mngrDept").val("");


	}

}

// text 감추기
function hideTxt(){
	$(".txtGrp").hide();
}


-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">관리자 관리</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /row -->
	<div id="border" style="display:" class="col-lg-12">
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				 관리자 등록(clik 관리자)
			</DIV>
			<!-- /.panel-heading -->
			<DIV id="main" class="panel-body">

				<form:form commandName="mngManage" method="post">
				<input type="hidden" name="mngrOptVal" id="mngrOptVal" />
				<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="아이디, 이름, 부서, 권한등을 입력 및 등록하는 테이블 입니다.">
				<colgroup>
					<col width="20%" />
					<col width="80%" />
				</colgroup>
				<tr>
					<th scope="row">
						<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">구분
					</th>
					<td>
						<input type="radio" name="selMngrOpt" value="1" onclick="setMem('1');" checked="checked">직원아이디
						&nbsp;&nbsp;&nbsp;
						<input type="radio" name="selMngrOpt" value="2" onclick="setMem('2');">일반아이디
					</td>
				</tr>
				<tr>
					<th scope="row"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">아이디</th>
					<td>
						<button type="button" class="btn btn-default" id="searchEmp" name="searchEmp" onclick="fnSearchEmp();">직원찾기</button>
						<!--<input type="button" id="searchEmp" name="searchEmp" value="직원찾기" onclick="fnSearchEmp();" /> -->
						<input name="mngrId" id="mngrId" type="text" value="" size="50" title="아이디" class="ip input-sm mngrIdGrp" /> <br/>
						<span id="mngrIdTxt" class="txtGrp mngrIdGrp">직원찾기 후 입력됩니다.</span>
					</td>

				</tr>

				<tr id="mngrPwBox">
					<th scope="row"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />패스워드</th>
					<td>
						<input name="mngrPw" id="mngrPw" type="password" value="" size="30" title="패스워드" class="ip input-sm" />
					</td>
				</tr>

				<!--<tr id="mngrPwBox">
					<th scope="row"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시" />패스워드</th>
					<td><input name="mngrPw" id="mngrPw" type="password" value="" size="30" title="패스워드" class="ip input-sm" /></td>
				</tr>	-->

				<tr>
					<th scope="row"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">이름</th>
					<td>
						<input name="mngrNm" id="mngrNm" type="text" value="" size="30" title="직원 이름" class="form-control input-sm mngrNmGrp" />
						<span id="mngrNmTxt" class="txtGrp mngrNmGrp">직원찾기 후 입력됩니다.</span>
					</td>
				</tr>
					<tr>
					<th scope="row"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">이메일</th>
					<td>
						<input name="mngrEmail" id="mngrEmail" type="text" value="" size="30" title="직원 이메일" class="form-control input-sm mngrEmailGrp" />
						<span id="mngrEmailTxt" class="txtGrp mngrEmailGrp">직원찾기 후 입력됩니다.</span>
					</td>
				</tr>
				<tr>
					<th scope="row">&nbsp;&nbsp;&nbsp;&nbsp;부서</th>
					<td>
						<input name="mngrDept" id="mngrDept" type="text" value="" maxLength="20" size="30" title="직원부서" class="form-control input-sm mngrDeptGrp"/>
						<span id="mngrDeptTxt" class="txtGrp mngrDeptGrp">직원찾기 후 입력됩니다.</span>
					</td>
				</tr>
				<tr>
					<th scope="row"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">권한</th>
					<td>
						<select id="authorCode" name="authorCode">
							<option value="0">선택하세요</option>
							<c:forEach items="${authorList}" var="x" varStatus="s">
								<option value="${x.authorCode}"><c:out value="${x.authorNm}" /></option>
							</c:forEach>
						</select>
					</td>
				</tr>


				</table>







				<!-- 검색조건 유지 -->
				<input name="cmd" type="hidden" value="<c:out value='save'/>"/>
				<input type="hidden" name="pageIndex" value="<c:out value='${mngVO.pageIndex}'/>"/>
				</form:form>


			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->
		<p class="tr">

		<button type="button" class="btn btn-default" onclick="javascript:fncSelectMngList()">목록</button>
		<button type="button" class="btn btn-primary" onclick="javascript:fncRoleInsert()">등록</button>
		</p>
	</DIV>
	<!-- /.MAIN -->
</DIV>
<!-- /page-wrapper -->

<script>
setMem("1");
</script>

</body>
</html>

