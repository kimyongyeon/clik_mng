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
			<c:if test="${cList.fileSn != '5'}">
			<tr>
				<td><input type="checkbox" name="chkid" id="chkid${status.count}" value="${cList.atchFileId}" style="vertical-align:-3px;" /></td>
				<td class="left">
					<c:choose>
						<c:when test="${cList.fileSn == '1' || cList.fileSn == '3'}">
							[<span style="color:blue">성공</span>]
							<img src="/images/clikmng/mdm/icon_${cList.fileExtsn}.png" style="width:14px;vertical-align:-3px;" />
							<a href="/mdm/MdmPolicyInfoDownLoad.do?DOWNID=${cList.atchFileId}" title="/mdm/MdmPolicyInfoDownLoad.do?DOWNID=${cList.atchFileId}">${fn:replace(cList.orignlFileNm, cList.fileExtsn, 'pdf')}</a>
						</c:when>
						<c:when test="${cList.fileSn == '4'}">
							[<span>변환중</span>]
							<img src="/images/clikmng/mdm/icon_${cList.fileExtsn}.png" style="width:14px;vertical-align:-3px;" />
							<a href="/mdm/MdmPolicyInfoDownLoad.do?DOWNID=${cList.atchFileId}" title="/mdm/MdmPolicyInfoDownLoad.do?DOWNID=${cList.atchFileId}">${fn:replace(cList.orignlFileNm, cList.fileExtsn, 'pdf')}</a>
						</c:when>
						<c:when test="${cList.fileSn == '2'}">[<span style="color:red">실패</span>] ${cList.fileCn}<br />${cList.orignlFileNm}</c:when>
						<c:when test="${cList.fileStreCours == null}"> [<span style="color:red">수집된 파일이 없음</span>]${cList.orignlFileNm}</c:when>
						<c:otherwise>
							[대기]
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