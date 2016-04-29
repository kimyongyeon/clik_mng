<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
 
<title>PC 목록</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="apple-mobile-web-app-capable" content="yes" />

<script language="javascript" src="<c:url value='/js/clikmng/jquery-1.10.2.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/bootstrap.min.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/jquery.metisMenu.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/sb-admin.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/jquery-ui.min.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/cmm/common.js' />"></script>
<script language="javascript" src="<c:url value='/js/clikmng/respond_src.js' />"></script>

<!-- Core CSS - Include with every page -->
<link href="<c:url value='/css/clikmng/bootstrap.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/jquery-ui.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/sb-admin.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/font-awesome.css' />" rel="stylesheet" type="text/css">

<style type="text/css">
/**/
h1{margin:0;padding:0;}
body.popup{ position:relative; overflow:hidden;padding:15px;}
div.today{  position:absolute;left:0; bottom:0; background:#f1f1f1; padding:10px; text-align:right; width:100%; border-top:1px solid #ccc;}
div.today label{color:#333;}
div.today a{color:#333;}

div.table-responsive{ }
h1{ margin-left:10px; font-size:18px; font-weight:bold;}
</style>

</head>
<body class="popup">
<h1 class="">대학 및 기관 검색PC관리</h1>
<!-- /.panel-heading -->
<form name="MacAddressUpdateForm" method="post" action="">
<input type="hidden" id="names" name="names" value="<c:out value='${names }'/>" >
<input type="hidden" id="select_no" name="select_no" >


<div class="panel-body ">
	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">기관 : ${ names }
	<div class="table-responsive" style="overflow:auto;">
		<table class="table table-striped table-bordered table-hover ">
		<colgroup>
        	<col width="10%" /> <!-- PC ID -->
            <col width="10%" /> <!-- MAC -->
            <col width="10%" /> <!-- CPU -->
            <col width="10%" /> <!-- 사설IP -->
            <col width="10%" /> <!-- 공인IP -->
            <col width="8%" /> <!-- FEE_USE -->
            <col width="8%" /> <!-- CARD_USE -->
            <col width="10%" /> <!-- 승인상태 -->
            <col /> <!-- 바고 -->
		</colgroup>
			<thead>
			<tr>
                <th scope="row" >PC ID</th>
                <th scope="row" >MAC</th>
                <th scope="row" >CPU</th>
                <th scope="row" >사설IP</th>
                <th scope="row" >공인IP</th>
                <th scope="row" >과금대상</th>
                <th scope="row" >복사카드</th>
                <th scope="row" >승인상태</th>
                <th scope="row" rowspan="2">기관코멘트<br>/비고</th>
			</tr>
			</thead>
			
			<tbody>
            <c:forEach items="${ list }" var="x" varStatus="s">
                <tr>
                   <td style="text-align:center;">
                        <p>${x.userid }</p>
                        <input type="hidden" id="old_userid${s.count}" name="old_userid" value="<c:out value='${x.userid }'/>">
                   </td>
                   <td style="text-align:center;">
                        <c:if test="${not empty x.app_status}">
                        </c:if>
                        <p><input type="text" class="text" id="mac${s.count}" name="mac" value="<c:out value='${x.mac }'/>"></p>
                   </td>
                   <td style="text-align:center;">
                        <c:if test="${not empty x.app_status}">
                        </c:if>
                        <p><input type="text" class="text" id="cpuid${s.count}" name="cpuid" value="<c:out value='${x.cpuid }'/>"></p>
                   </td>
                   <td style="text-align:center;">
                        <c:if test="${not empty x.app_status}">
                        </c:if>
                        <input type="text" class="text" id="ip${s.count}" name="ip" value="<c:out value='${x.ip }'/>">
                        <input type="text" class="text" id="ip2${s.count}" name="ip2" value="<c:out value='${x.ip2 }'/>">
                   </td>
                   <td style="text-align:center;">
                        <c:if test="${not empty x.app_status}">
                        </c:if>
                        <p><input type="text" class="text" id="public_ip${s.count}" name="public_ip" value="<c:out value='${x.public_ip }'/>"></p>
                        <p><input type="text" class="text" id="public_ip2${s.count}" name="public_ip2" value="<c:out value='${x.public_ip2 }'/>"></p>
                   </td>
                   <td style="text-align:center;">
                        <c:if test="${not empty x.app_status}">
                        </c:if>
                        <select title="fee use" style="width:60px" id="fee_use${s.count}" name="fee_use">
                            <option value="Y" ${x.fee_use=='Y'?'selected':'' }>예</option>
                            <option value="N" ${x.fee_use=='N'?'selected':'' }>아니오</option>
                        </select>
                   </td>
                   <td style="text-align:center;">
                        <c:if test="${not empty x.app_status}">
                        </c:if>
                        <select title="card use" style="width:60px" id="card_use${s.count}" name="card_use">
                            <option value="Y" ${x.card_use=='Y'?'selected':'' }>예</option>
                            <option value="N" ${x.card_use=='N'?'selected':'' }>아니오</option>
                        </select>
                   </td>
                   <td style="text-align:center;">
                        <c:if test="${not empty x.app_status}">
                        </c:if>
                        <select title="승인상태" style="width:80px" id="app_status${s.count}" name="app_status">
                            <option value="0" ${x.app_status=='0'?'selected':'' }>승인요청</option>
                            <option value="1" ${x.app_status=='1'?'selected':'' }>승인완료</option>
                            <option value="2" ${x.app_status=='2'?'selected':'' }>승인보류</option>
                        </select>
                        <input type="hidden" id="old_app_status${s.count}" name="old_app_status" value="<c:out value='${x.app_status }'/>">
                   </td>
                   <td style="text-align:center;">
                        <c:if test="${not empty x.app_status}">
                        </c:if>
                        <input type="text" class="text" id="comment${s.count}" name="comment" value="<c:out value='${x.comment }'/>" >
                        <input type="text" class="text" id="app_reason${s.count}" name="app_reason" value="<c:out value='${x.app_reason }'/>">
                   </td>
                   <td class="last" style="text-align:center;">
                        <c:if test="${sess_menu_auth_Mg == 2}">
                         <c:if test="${not empty x.app_status}">
                         <span class="button small"><input type="button" value="수정" onclick="javaScript:fn_macupdate('${s.count}');" ></span>
                         <span class="button small"><input type="button" value="승인" onclick="javaScript:fn_macapproval('${s.count}');" ></span>
                         </c:if>
                         <c:if test="${empty x.app_status}">
                            <span class="button small"><input type="button" value="등록" onclick="javaScript:fn_macinsert('${s.count}');" ></span>
                         <!-- 2013-10-29 배지훈 : 삭제 버튼 추가 -->
                         <span class="button small"><input type="button" value="삭제" onclick="javaScript:fn_macdelete('${s.count}');" ></span>
                         </c:if>
                        </c:if>
                   </td>
                </tr>
            </c:forEach>			
			</tbody>
		</table>
		</div>
	<!-- /.table-responsive -->
</div>
<!-- /.panel-body -->

<p class="tc">
	<button type="button" class="btn btn-primary" onclick="javascript:window.close();">닫기</button>
</p>

</body>
</html>
