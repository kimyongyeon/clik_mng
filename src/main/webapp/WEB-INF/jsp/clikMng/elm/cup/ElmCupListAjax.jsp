<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>

<%

/**
 * @Class Name : ElmGroupList.java
 * @Description : ElmGroupList jsp
 */

%>


		<table class="table table-striped table-bordered table-hover "  id="">
			<colgroup>
				<col width="" />
				<col width="" />
				<col width="" />
				<col width="" />
			</colgroup>
			<thead>
				<tr>
					<th>과금여부</th>
					<th>저작권 이용허락</th>
					<th>서비스 범위</th>
					<th>아이콘/출력메세지</th>
				</tr>
			</thead>
			<tbody>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 시작 --%>
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
				</tr>
			</c:if>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 끝 --%>
			<c:forEach var="x" items="${resultList}" varStatus="s">
				<tr>
					<td>
						<c:choose>
							<c:when test="${x.chrgnAt == 'Y'}">과금</c:when>
							<c:otherwise>비과금</c:otherwise>
						</c:choose>
						
					</td>
					<td>
						<c:forEach var="copy" items="${cpyUseList}">
							<c:if test="${x.cpyrhtUsePermCode == copy.code}">
								<c:out value="${copy.codeNm}" />
								(<c:out value="${copy.code}" />)
							</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach var="scope" items="${svcScopeList}">
							<c:if test="${x.cpyrhtSvcScopeCode == scope.code}">
								<c:out value="${scope.codeNm}" />
								(<c:out value="${scope.code}" />)
							</c:if>
						</c:forEach>
					</td>
					<td>
						<img src="http://dl.nanet.go.kr/images/cmn_img/icon_w_tiff_on.gif" alt="tiff 파일 아이콘" />
						<%-- <img src="<c:out value='${x.iconFileNm}' />" alt="<c:out value='${x.iconMssage}' />" /> --%>
						<br>
						<c:out value='${x.iconMssage}' />
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>


