<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>지방의회 회의명정보</title>
<style>
.t td {

}
</style>
</head>
<body>

	<table class="t">
		<colgroup>
			<col width="10%" />
			<col width="90%" />
		</colgroup>
		<tbody>
			<tr>
				<th>안건순번</th>
				<th>안건명</th>
			</tr>
			<c:forEach var="item" items="${itemList}">
			<tr style="text-align:center;">
			<td>${item.MTR_SN }</td>
			<td>${item.MTR_SJ }</td>
			</tr>
			</c:forEach>
			
			<c:if test="${itemList == null || itemList.size() == 0}">
			<tr style="text-align:center;">
			<td colspan="2">안건 정보가 존재하지 않습니다.</td>
			</tr>
			</c:if>
		</tbody>
	</table>

</body>
</html>