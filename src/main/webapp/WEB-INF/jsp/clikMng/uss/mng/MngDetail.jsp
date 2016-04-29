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
<title>관리자 상세보기 및 수정(clik 관리자)</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mngManage" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">
<!--
/******************************************
 * 목록
 ******************************************/

function fnListMng() {
	var f = document.getElementById("mngManage");
	f.action = "<c:url value='/uss/mng/MngList.do'/>";
	f.submit();
}

/******************************************
 * 수정
 ******************************************/

function fnEditMng() {

	 var f = document.getElementById("mngManage");


	if(confirm("수정 하시겠습니까?")){
		f.authorClCode.value = 'MNG';
		f.cmd.value = 'edit';
		f.action = "<c:url value='/uss/mng/MngDetail.do'/>";
		f.submit();
	}

}

/******************************************
 * 삭제
 ******************************************/

function fnDelMng() {
	if(confirm("삭제 하시겠습니까?")){
		var f = document.getElementById("mngManage");
		f.authorClCode.value = 'MNG';
		f.cmd.value = 'del';
		f.action = "<c:url value='/uss/mng/MngDetail.do'/>";
		f.submit();
	}
}

-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<div id="page-wrapper">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">관리자 관리</h1>
		</div><!-- /.col-lg-12 -->
	</div><!-- /row -->

	<div id="border" style="display:" class="">
	
		<div class="">

			<h2>
				 관리자 상세보기 및 수정(clik 관리자)
			</h2><!-- /.panel-heading -->
			
			<div id="main" class="">
				<table border="0" width="100%">
				<tr>
					<td>
						<form:form commandName="mngManage" method="post" >
<!-- 							<input type="hidden" name="mngrPw" /> -->
							<!-- 검색조건 유지 -->
							<input name="cmd" type="hidden" />
							<input name="authorClCode" type="hidden" />
							<input name="searchKeyword" type="hidden" value="<c:out value='${searchKeyword}' />" />
							<input name="searchCondition" type="hidden" value="<c:out value='${searchCondition}' />" />
							<input type="hidden" name="pageIndex" value="<c:out value='${mngVO.pageIndex}'/>"/>
							
							<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="아이디, 이름, 부서, 권한등을 입력 및 등록하는 테이블 입니다.">
							<tr>
								<th scope="row">
									<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">구분
								</th>
								<td>
									<input type="hidden" id="mngrSeCode" name="mngrSeCode" value="${mngVO.mngrSeCode}" />
									<input type="hidden" id="mngrId" name="mngrId" value="${mngVO.mngrId}" />
									<c:if test="${mngVO.mngrSeCode == '1'}">직원아이디</c:if>
									<c:if test="${mngVO.mngrSeCode == '2'}">일반아이디</c:if>
									
								<!-- 
									<input type="radio" name="mngrOpt" onclick="setMem('1');" checked="checked">직원아이디
									&nbsp;&nbsp;&nbsp;
									<input type="radio" name="mngrOpt" onclick="setMem('2');">일반아이디
								 -->
								</td>
							</tr>
							<c:if test="${mngVO.mngrSeCode == '2'}">
							<tr>
								<th class="required_text" width="20%" scope="row" nowrap="nowrap"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">아이디</th>
								<td class="lt_text" nowrap="nowrap">
									${mngVO.mngrId}
								</td>
							</tr>
							</c:if>
							<c:if test="${mngVO.mngrSeCode == '2'}">
							<tr>
								<th class="required_text" width="20%" scope="row" nowrap="nowrap">
									<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">패스워드
								</th>
								<td class="lt_text" nowrap="nowrap">
									<input name="mngrPw" id="mngrPw" type="password" placeholder="**********" size="30" title="패스워드" class="form-control input-sm" />
								</td>
							</tr>
							</c:if>
							<tr>
								<th class="required_text" width="20%" scope="row" nowrap="nowrap"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">이름</th>
								<td class="lt_text" nowrap="nowrap">
								<c:choose>
									<c:when test="${mngVO.mngrSeCode == '1'}">
										${mngVO.mngrNm}
									</c:when>
									<c:otherwise>
										<input name="mngrNm" id="mngrNm" type="text" value="<c:out value='${mngVO.mngrNm}' />" size="30" title="직원 이름" class="form-control input-sm" />
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
							<tr>
								<th class="required_text" width="20%" scope="row" nowrap="nowrap">
									<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">이메일
								</th>
								<td class="lt_text" nowrap="nowrap">
								<c:choose>
									<c:when test="${mngVO.mngrSeCode == '1'}">${mngVO.mngrEmail}</c:when>
									<c:otherwise><input name="mngrEmail" id="mngrEmail" type="text" value="<c:out value="${mngVO.mngrEmail}" />" size="100" title="이메일" class="form-control input-sm" /></c:otherwise>
								</c:choose>
								</td>
							</tr>
							<tr>
								<th class="required_text" width="20%" scope="row" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;부서</th>
								<td class="lt_text" nowrap="nowrap">
								<c:choose>
									<c:when test="${mngVO.mngrSeCode == '1'}">
										${mngVO.mngrDept}
									</c:when>
									<c:otherwise>
										<input name="mngrDept" id="mngrDept" value="<c:out value='${mngVO.mngrDept }' />" type="text" value="" maxLength="20" size="30" title="직원부서" class="form-control input-sm"/>
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
							<tr>
								<th class="required_text" width="20%" scope="row" nowrap="nowrap"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">권한</th>
								<td class="lt_text" nowrap="nowrap">
									<select id="authorCode" name="authorCode">
										<option value="0">선택하세요</option>
										<c:forEach items="${authorList}" var="x" varStatus="s">
											<option value="${x.authorCode}" <c:if test='${mngVO.authorCode == x.authorCode}'> selected </c:if>><c:out value="${x.authorNm}" /></option>
										</c:forEach>
									</select>
								</td>
							</tr>
							</table>

						</form:form>
					</td>
				</tr>
				</table>
			</div><!-- /panel body -->
			
		</div><!-- /.panel panel-default -->

		<p class="tr">
			<button type="button" class="btn btn-default" onclick="javascript:fnListMng(); return false;"><spring:message code="button.list" /></button>
			<button type="button" class="btn btn-success" onclick="javascript:fnEditMng(); return false;"><spring:message code="button.update" /></button>
			<button type="button" class="btn btn-danger" onclick="javascript:fnDelMng(); return false;"><spring:message code="button.delete" /></button>
		</p>
		
	</div><!-- /.border -->
	
</div><!-- /page-wrapper -->

</body>
</html>

