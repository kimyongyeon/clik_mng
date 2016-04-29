<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/**
 * @Class Name  : EgovRoleUpdate.java
 * @Description : EgovRoleUpdate jsp
 * @Modification Information
 */
 --%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="registerFlag" value="${empty roleManageVO.roleCode ? 'INSERT' : 'UPDATE'}"/>
<c:set var="registerFlagName" value="${empty roleManageVO.roleCode ? '기능 등록' : '기능 수정'}"/>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>기능 상세보기 및 수정</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="roleManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectRoleList(roleClCode) {
    var varFrom = document.getElementById("roleManage");
    varFrom.action = "<c:url value='/mna/rmt/EgovRoleList.do'/>";
    varFrom.submit();
}

function fncRoleInsert() {

    var varFrom = document.getElementById("roleManage");
    varFrom.action = "<c:url value='/mna/rmt/EgovRoleInsert.do'/>";

    if(confirm("저장 하시겠습니까?")){
        if(!validateRoleManage(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncRoleUpdate() {
    var varFrom = document.getElementById("roleManage");
    varFrom.action = "<c:url value='/mna/rmt/EgovRoleUpdate.do'/>";

    if(confirm("저장 하시겠습니까?")){
        if(!validateRoleManage(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncRoleDelete() {
    var varFrom = document.getElementById("roleManage");
    varFrom.action = "<c:url value='/mna/rmt/EgovRoleDelete.do'/>";
    if(confirm("삭제 하시겠습니까?")){
        varFrom.submit();
    }
}

</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">기능 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
	<div id="border" style="display:" class="">
	<p class="tr">
	
	<button type="button" class="btn btn-default" onclick="javascript:fncSelectRoleList('${roleManage.roleClCode}')">목록</button>		
	<c:if test="${registerFlag == 'INSERT'}">
		<button type="button" class="btn btn-primary" onclick="javascript:fncRoleInsert()">등록</button>
	</c:if>
	<c:if test="${registerFlag == 'UPDATE'}">
		<button type="button" class="btn btn-success" onclick="javascript:fncRoleUpdate()">수정</button>
	</c:if>
	<button type="button" class="btn btn-danger"  onclick="javascript:fncRoleDelete()">삭제</button>
		
	</p> 
		<DIV class="">
			<DIV class="panel-heading">
				 기능 상세보기 및 수정
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="main" class="">
				<table border="0" width="100%">
				  <tr>
				    <td>
				<form:form commandName="roleManage" method="post" >
				<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="기능을 수정하는 테이블입니다.기능 코드,기능 명,기능 패턴,설명,기능 타입,기능 Sort,등록일자 정보를 담고 있습니다.">
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap">사용 구분<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap="nowrap">
				    	<input type="radio" id="roleClCode" name="roleClCode" value="CLIK" <c:if test="${roleManage.roleClCode == 'CLIK'}">checked</c:if> /> 사용자 기능 &nbsp;
				    	<input type="radio" id="roleClCode" name="roleClCode" value="MNG" <c:if test="${roleManage.roleClCode == 'MNG'}">checked</c:if> /> 관리자 기능 
				    </td>
				  </tr>					  
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap">기능  코드<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap="nowrap"><input name="roleCode" id="roleCode" type="text" value="<c:out value='${roleManage.roleCode}'/>" size="30" readonly="readonly" title="기능 코드" class="form-control input-sm" /></td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap">기능 명<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap="nowrap"><input name="roleNm" id="roleNm" type="text" value="<c:out value='${roleManage.roleNm}'/>" maxLength="50" size="30" title="기능 명" class="form-control input-sm" />&nbsp;<form:errors path="roleNm" /></td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap">기능 패턴<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap="nowrap"><input name="rolePtn" id="rolePtn" type="text" value="<c:out value='${roleManage.rolePtn}'/>" maxLength="200" size="50" title="기능 패턴" class="form-control input-sm" />&nbsp;<form:errors path="rolePtn" /></td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap">설명<img src="<c:url value='/images/clikmng/cmm/icon/no_required.gif' />" width="15" height="15" alt="부가항목"></th>
				    <td class="lt_text" nowrap="nowrap"><input name="roleDc" id="roleDc" type="text" value="<c:out value='${roleManage.roleDc}'/>" maxLength="50" size="50" title="설명" class="form-control input-sm" /></td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap">기능 타입<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap="nowrap">
				      <select name="roleTyp" title="기능 타입" class="form-control">
				        <c:forEach var="cmmCodeDetail" items="${cmmCodeDetailList}" varStatus="status">
				          <option value="<c:out value="${cmmCodeDetail.code}"/>" <c:if test="${cmmCodeDetail.code == roleManage.roleTyp}">selected</c:if> ><c:out value="${cmmCodeDetail.codeNm}"/></option>
				        </c:forEach>
				      </select>
				   </td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap">기능 Sort<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap="nowrap"><input name="roleSort" id="roleSort" type="text" value="<c:out value='${roleManage.roleSort}'/>" maxLength="10" size="10" title="기능 sort" class="form-control input-sm" /></td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap">등록일자<img src="<c:url value='/images/clikmng/cmm/icon/no_required.gif' />" width="15" height="15" alt="부가항목"></th>
				    <td class="lt_text" nowrap="nowrap"><input name="roleCreatDe" id="roleCreatDe" type="text" value="<c:out value='${roleManage.roleCreatDe}'/>" maxLength="50" size="20" title="등록일자" class="form-control input-sm" readonly="readonly"/></td>
				  </tr>
				</table>
				
				<!-- 검색조건 유지 -->
				<c:if test="${registerFlag == 'UPDATE'}">
				<input type="hidden" name="searchCondition" value="<c:out value='${roleManageVO.searchCondition}'/>"/>
				<input type="hidden" name="searchKeyword" value="<c:out value='${roleManageVO.searchKeyword}'/>"/>
				<input type="hidden" name="pageIndex" value="<c:out value='${roleManageVO.pageIndex}'/>"/>
				</c:if>
				</form:form>
				</td>
				</tr>
				</table>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->	
		<p class="tr">
		
		<button type="button" class="btn btn-default" onclick="javascript:fncSelectRoleList('${roleManage.roleClCode}')">목록</button>		
		<c:if test="${registerFlag == 'INSERT'}">
			<button type="button" class="btn btn-primary" onclick="javascript:fncRoleInsert()">등록</button>
		</c:if>
		<c:if test="${registerFlag == 'UPDATE'}">
			<button type="button" class="btn btn-success" onclick="javascript:fncRoleUpdate()">수정</button>
		</c:if>
		<button type="button" class="btn btn-danger"  onclick="javascript:fncRoleDelete()">삭제</button>		
		</p>    
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>

