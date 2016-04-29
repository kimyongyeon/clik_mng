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
<title>공지사항 등록</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mngManage" staticJavascript="false" xhtml="true" cdata="false"/>
 
<script type="text/javaScript" language="javascript">
<!--

/******************************************
 * 목록
 ******************************************/

function fnListMng() {
		var f = document.getElementById("frm");
	    f.action = "<c:url value='/mob/NoticeList.do'/>";
	    f.submit();
}

-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form name="frm" id="frm" method="post" >
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">공지사항</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>공지사항 등록</h2>
            
			

				<table class="table table-striped table-bordered table-hover "  id="">
					
					<tbody>
						<tr>
							<th>제목</th>
							<td ><input type="text" class="ip input-sm" style="width:30%;" /></td>
						</tr>
						<tr>
							<th>작성일</th>
							<td>
								<input type="text" class="ip input-sm" style="width:30%;" />
								<button type="button" class="btn btn-default">달력</button>
							</td>
							
						</tr>
						<tr>
							
							<th>작성자</th>
							<td><input type="text" class="ip input-sm" style="width:30%;" /></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea cols="100" rows="10" style="width:100%;" class="ip"></textarea></td>
						</tr>
					
					</tbody>
				</table>

				<p class="tr">
					<button type="button" class="btn btn-default" onclick="fnListMng();">목록</button>
					<button type="button" class="btn btn-primary">수정</button>
					<button type="button" class="btn btn-danger">취소</button>
				</p>
	</div><!--//page-wrapper-->
</form>
</body>
</html>

