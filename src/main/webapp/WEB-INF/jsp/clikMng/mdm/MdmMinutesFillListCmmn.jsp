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
				<th class="end" style="text-align:left">[<a href="#;" onclick="setSlideReCnvrsCheck();">재변환</a>] &nbsp; &nbsp; [<a href="#;" onclick="setSlideDelCheck();">삭제</a>]</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="cList" items="${fileList}" varStatus="status">
			<c:if test="${cList.orignlFileNm != null}">
			<tr>
				<td><input type="checkbox" name="chkid" id="chkid${status.count}" value="${cList.atchFileId}" style="vertical-align:-3px;" /></td>
				<td class="left">
					<c:choose>
						<c:when test="${cList.fileSn == '1' || cList.fileSn == '3'}">[성공]</c:when>
						<c:when test="${cList.fileSn == '2'}">[<font color="red">실패</font>]</c:when>
						<c:otherwise>[대기]</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${cList.fileMg == 'APP'}">[부록]</c:when>
						<c:otherwise>[<span style="font-weight:bold">원문</span>]</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${cList.fileSn == '2'}">${cList.fileCn}<br />${cList.orignlFileNm}</c:when>
						<c:otherwise>
							<img src="/images/clikmng/mdm/icon_${cList.fileExtsn}.png" style="width:14px;vertical-align:-3px;" />
							${cList.orignlFileNm}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:if>
		</c:forEach>
		</tbody>
	</table>
</div>