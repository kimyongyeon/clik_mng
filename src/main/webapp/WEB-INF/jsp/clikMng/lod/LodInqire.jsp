<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="java.io.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%

Date date = new Date();
SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
String Today = dt.format(date);
String ntceBgndeYYYMMDD = "" ;
String ntceEnddeYYYMMDD = "";
if (request.getParameter("ntceBgndeYYYMMDD") != null ) {
	ntceBgndeYYYMMDD =  request.getParameter("ntceBgndeYYYMMDD") ;
} else {
	ntceBgndeYYYMMDD = Today;
}
if (request.getParameter("ntceEnddeYYYMMDD") != null ) {
	ntceEnddeYYYMMDD =  request.getParameter("ntceEnddeYYYMMDD") ;
} else {
	ntceEnddeYYYMMDD = Today;
}


%>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>LOD 관리</title>
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--

/* ********************************************************
* SELECT BOX 검색
******************************************************** */
function fnSearch() {
	var varFrom = document.listForm;
	
	if($('#schDt1').val() == "" && $('#schDt2').val() == ""
			&& varFrom.searchCondition.value == "") {
		alert("검색기간을 선택하여 주세요.");
		return;
	}
	
	varFrom.action = '/lod/LodInqire.do';
	if($('#schDt1').val() != "" || $('#schDt2').val() != "") {
		var ntceBgndeYYYMMDD = $('#schDt1').val();
		var ntceEnddeYYYMMDD = $('#schDt2').val();

		var iChkBeginDe = ntceBgndeYYYMMDD.split("-").join("") * 1;
		var iChkEndDe = ntceEnddeYYYMMDD.split("-").join("") * 1;

		if(iChkBeginDe > iChkEndDe || iChkEndDe < iChkBeginDe ){
			alert("게시시작일자는 게시종료일자 보다 클수 없고,\n게시종료일자는 게시시작일자 보다 작을수 없습니다. ");
			return;
		}

		//varFrom.ntceBgnde.value = ntceBgndeYYYMMDD.split("-").join("");
		//varFrom.ntceEndde.value = ntceEnddeYYYMMDD.split("-").join("");
	}
	
	varFrom.submit();
}

-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

		
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">LOD 관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>변환 파일 내역</h2>
				<form name="listForm" method="get">
					<div class="search">
						<input type="text" name="schDt1" id="schDt1" value="<c:out value="${searchVO.schDt1}" />" class="input-sm ip" style="width:150px;" />
						&nbsp;~&nbsp;
						<input type="text" name="schDt2" id="schDt2" value="<c:out value="${searchVO.schDt2}" />" class="input-sm ip" style="width:150px;" />
		 				<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-default1">한달</button></a>
						<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-default2">일주일</button></a>
						<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-default3">오늘</button></a>
		 				<script>setCal("schDt1","schDt2");</script>
	
						<button type="button" class="btn btn-primary"  onclick="fnSearch(); return false;">검색</button>
					</div>
				</form>


				
				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="5%" />
						<col />
						<col />
						<col />
						<col />
						<col />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>처리일자</th>
							<th>DB인원수</th>
							<th>json파일생성수</th>
							<th>미처리수</th>
							<th>처리시간</th>
						</tr>
					</thead>
					<tbody>
					
					<%
					BufferedReader in = new BufferedReader(new FileReader("/clik-web/LodDaemon/LodLog.txt"));
					
	   			 	String s = "";
	   			 	int i = 1;
	   			 	while ((s = in.readLine()) != null) {
	   			 		
	   			 		String [] str = s.split(" ");
	   			 		Integer thisDt = Integer.parseInt(str[0].replace("-",""));	
	   			 		if (  thisDt >=  Integer.parseInt(ntceBgndeYYYMMDD.replace("-",""))  &&  thisDt <=  Integer.parseInt(ntceEnddeYYYMMDD.replace("-",""))  ) {
			   				%>
								<tr>
									<td><%=i %></td>
									<td><%=str[0] %></td>
									<td><%=str[1] %></td>
									<td><%=str[2] %></td>
									<td><%=str[3] %></td>
									<td><%=str[0] %> <%=str[4] %></td>
								</tr>		   				
			   				<%
			   				i++;
	   			 		}
		   				
		   			}
		   			in.close();
					%>
					

					</tbody>
				</table>

				<!--<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="10%" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th>파일등록</th>
							<td>
								<div class="left">
									<input class="input-sm ip " placeholder="" value="찾아보기"  type="file" style="float:left; margin-right:10px;">
		 
								<button type="button" class="btn btn-danger">삭제</button>
								</div>
								
							</td>
						</tr>
					</tbody>
				</table>

				<p class="tr">
					<button type="button" class="btn btn-primary">등록</button>
				</p>-->


	</div><!--//page-wrapper-->


</body>
</html>
