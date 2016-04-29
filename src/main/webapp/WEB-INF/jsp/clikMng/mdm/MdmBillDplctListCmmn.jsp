<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="slidesession">
	<table summary="파일 목록입니다">
		<caption></caption>
		<colgroup>
		<col width="10%" />
		<col width="90%" />
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkbox" name="slideCheckAll" id="slideCheckAll" onclick="getSlideCheckAll();" /></th>
				<th class="end" style="text-align:left"> &nbsp; &nbsp; [<a href="#;" onclick="setSlideDelCheck('DPLCT');">삭제</a>]</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="cList" items="${list}" varStatus="status">
			<tr>
				<td><input type="checkbox" name="chkid" id="chkid${status.count}" value="${cList.BI_CN}" style="vertical-align:-3px;" /></td>
				<td class="left">[${cList.BI_CN}] [${cList.RASMBLY_NM}] [${cList.RASMBLY_NUMPR}대] [의안번호-${cList.BI_NO}] [${cList.MTGNM}] ${cList.BI_SJ}</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>