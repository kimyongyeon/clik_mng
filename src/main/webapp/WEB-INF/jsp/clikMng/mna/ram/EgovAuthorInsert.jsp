<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
/**
 * @Class Name  : EgovAuthorInsert.java
 * @Description : EgovAuthorInsert jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 *
 *  @author lee.m.j
 *  @since 2009.03.11
 *  @version 1.0
 *  @see
 *
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

<c:set var="registerFlag" value="${empty authorManageVO.authorCode ? 'INSERT' : 'UPDATE'}"/>
<c:set var="registerFlagName" value="${empty authorManageVO.authorCode ? '권한 등록' : '권한 수정'}"/>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>기능권한 등록</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="authorManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
<!--
function fncSelectAuthorList() {
	location.href="/mna/ram/EgovAuthorList.do?authorClCode=<c:out value="${authorClCode}" />";
/* 	
	var varFrom = document.getElementById("authorManage");
	varFrom.action = "<c:url value='/mna/ram/EgovAuthorList.do'/>";
	varFrom.submit();
 */
}

function fncAuthorInsert() {

    var varFrom = document.getElementById("authorManage");
    varFrom.action = "<c:url value='/mna/ram/EgovAuthorInsert.do'/>";

    if(confirm("저장 하시겠습니까?")){
        if(!validateAuthorManage(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncAuthorUpdate() {
	var varFrom = document.getElementById("authorManage");
	varFrom.action = "<c:url value='/mna/ram/EgovAuthorUpdate.do'/>";

    if(confirm("저장 하시겠습니까?")){
        if(!validateAuthorManage(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncAuthorDelete() {
	var varFrom = document.getElementById("authorManage");
	varFrom.action = "<c:url value='/mna/ram/EgovAuthorDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
	    varFrom.submit();
	}
}
//-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">메뉴 및 기능권한 설정</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->
    <form:form commandName="authorManage" method="post" >

			<DIV class="panel-heading">
				 기능권한 등록
			</DIV>
			<!-- /.panel-heading -->		
		
				<table border="0" width="100%">
				  <tr>
				    <td>			
				
				<table width="100%" cellpadding="8" class="table table-striped table-bordered " summary="권한을 등록하는 테이블입니다.권한 코드,권한 명,설명,등록일자의 정보를 담고 있습니다.">
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap">사용 구분<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap="nowrap">
				    	<input type="radio" id="authorClCode" name="authorClCode" value="CLIK" checked /> 사용자 기능 &nbsp;
				    	<input type="radio" id="authorClCode" name="authorClCode" value="MNG" /> 관리자 기능 
				    </td>
				  </tr>	
				  <tr>
				    <th class="required_text" width="20%" scope="row" nowrap="nowrap">기능권한 코드<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td nowrap="nowrap"><input name="authorCode" id="authorCode" type="text" value="<c:out value='${authorManage.authorCode}'/>" maxLength="30" size="40" title="권한코드" class="form-control span1" style="width:200px;" />&nbsp;<form:errors path="authorCode" /></td>
				
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row" nowrap="nowrap">기능권한 명<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td nowrap="nowrap"><input name="authorNm" id="authorNm" type="text" value="<c:out value='${authorManage.authorNm}'/>" maxLength="60" size="40" title="권한명" class="form-control span1" style="width:200px;" />&nbsp;<form:errors path="authorNm" /></td>
				
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row" nowrap="nowrap">기능권한 설명</th>
				    <td nowrap="nowrap"><input name="authorDc" id="authorDc" type="text" value="<c:out value='${authorManage.authorDc}'/>" maxLength="200" size="50" title="설명" class="form-control span1" style="width:200px;" /></td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row" nowrap="nowrap">등록일자</th>
				    <td nowrap="nowrap"><input name="authorCreatDe" id="authorCreatDe" type="text" value="<c:out value='${authorManage.authorCreatDe}'/>" maxLength="50" size="20" readonly="readonly" title="등록일자" class="form-control span1" style="width:200px;"/></td>
				  </tr>
				</table>
				
				<p class="tr">
					<button type="button" class="btn btn-default" onclick="javascript:fncSelectAuthorList('1')">목록</button>
					<c:if test="${registerFlag == 'INSERT'}">
						<button type="button" class="btn btn-primary" onclick="javascript:fncAuthorInsert()">등록</button>
					</c:if>
					<c:if test="${registerFlag == 'UPDATE'}">
						<button type="button" class="btn btn-success" onclick="javascript:fncAuthorUpdate()">수정</button>			
					</c:if>
				</p> 					
				
				<!--
				    <button type="button" onclick="javascript:fncSelectAuthorList();">List</button>
				    <input type="submit" value="<c:out value='${registerFlag}'/>"/>
				 -->
				<!-- 검색조건 유지 -->
				<c:if test="${registerFlag == 'UPDATE'}">
				<input type="hidden" name="searchCondition" value="<c:out value='${authorManageVO.searchCondition}'/>"/>
				<input type="hidden" name="searchKeyword" value="<c:out value='${authorManageVO.searchKeyword}'/>"/>
				<input type="hidden" name="pageIndex" value="<c:out value='${authorManageVO.pageIndex}'/>"/>
				</c:if>
				</form:form>
				
				</td>
				</tr>
				</table>

</DIV>	
<!-- /page-wrapper -->		
</body>
</html>

