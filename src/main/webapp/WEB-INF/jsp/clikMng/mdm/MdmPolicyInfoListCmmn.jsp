<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="slidesession">
	<table summary="파일 목록입니다" style="width:100%;">
		<caption></caption>
		<colgroup>
		<col width="10%" />
		<col width="90%" />
		</colgroup>
		<thead>
			<tr>
				<th style="text-align: center;"><input type="checkbox" name="slideCheckAll" id="slideCheckAll" onclick="getSlideCheckAll();" /></th>
				<th class="end" style="text-align:left"> &nbsp; &nbsp; [<a href="#;" onclick="setSlideDelCheck();">삭제</a>]</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="cList" items="${list}" varStatus="status">
		<c:if test="${cList.OUTBBS_CN == outbbs_cn}">
			<tr>
				<td style="text-align: center;"><input type="checkbox" name="chkid" id="chkid${status.count}" value="${cList.OUTBBS_CN}" style="vertical-align:-3px;" /></td>
				<td class="left"><font color="blue">
		</c:if>
		<c:if test="${cList.OUTBBS_CN != outbbs_cn}">
			<tr>
				<td style="text-align: center;"><input type="checkbox" name="chkid" id="chkid${status.count}" value="${cList.OUTBBS_CN}" style="vertical-align:-3px;" /></td>
				<td class="left">
		</c:if>		
					제목&nbsp;:&nbsp; <a href="#;" onclick="window.open('/mdm/MdmPolicyInfoMetaDataView1.do?cnId=${cList.OUTBBS_CN}','메타데이터보기','width=1366,height=748,scrollbars=yes,resizable=yes');">[${cList.WRITER}]&nbsp;${cList.TITLE}</a><br />
					기관(사이트)명&nbsp;:&nbsp; ${cList.SITENM}<br />
					게시판명&nbsp;&nbsp;:&nbsp; ${cList.SEEDNM}<br />
					제어번호&nbsp;&nbsp;:&nbsp; [${cList.OUTBBS_CN}]<br />
					수집일&nbsp;&nbsp;:&nbsp; 
					<c:choose>
						<c:when test="${fn:length(cList.REGDATE) > 8}">
							[${fn:substring(cList.REGDATE, 0, 4)}-${fn:substring(cList.REGDATE, 4, 6)}-${fn:substring(cList.REGDATE, 6, 8)}]&nbsp;
						</c:when>
						<c:otherwise>[${cList.REGDATE}]&nbsp;</c:otherwise>
					</c:choose>
		<c:if test="${cList.OUTBBS_CN == outbbs_cn}">
				</font>
		</c:if>					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>