<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%

/**
 * @Class Name : EgovRoleManage.java
 * @Description : EgovRoleManage jsp
 * @Modification Information
 * @
 */

%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>기능 목록조회</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--

function fncCheckAll() {
    var checkField = document.listForm.delYn;
    if(document.listForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

function fncManageChecked() {

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkCount++;
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                        returnValue = returnValue + ";" + checkField[i].value;
                }
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
                alert("선택된  기능이 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 기능이 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
    	alert("조회된 결과가 없습니다.");
    }

    document.listForm.roleCodes.value = returnValue;
    return returnBoolean;
}

function fncSelectRoleList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/mna/rmt/EgovRoleList.do'/>";
    document.listForm.submit();
}
/* 
function fncSelectRole(roleCode) {
    document.listForm.roleCode.value = roleCode;
    document.listForm.action = "<c:url value='/mna/rmt/EgovRole.do'/>";
    document.listForm.submit();
}

 */
function fncAddRoleInsert() {
    location.replace("<c:url value='/mna/rmt/EgovRoleInsertView.do?roleClCode=${roleManageVO.roleClCode}' />");
}

function fncRoleListDelete() {
	if(fncManageChecked()) {
        if(confirm("삭제하시겠습니까?")) {
            document.listForm.action = "<c:url value='/mna/rmt/EgovRoleListDelete.do'/>";
            document.listForm.submit();
        }
    }
}

function fncAddRoleView() {
    document.listForm.action = "<c:url value='/mna/rmt/EgovRoleUpdate.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/mna/rmt/EgovRoleList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectRoleList('1');
    }
}


/* ********************************************************
* 탭클릭시 화면전환
******************************************************** */
function fn_tabClick(sel_name) {
	$("#roleClCode").val(sel_name);
	document.listForm.submit();		
}


-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

<c:if test="${roleManageVO.roleClCode == ''}">
	<c:set var="roleClCode" value="CLIK"/>
</c:if>


<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">기능 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
	<div id="border" style="display:" class="">
	    	
		<DIV class="">
			<DIV class="panel-heading">
				 기능 관리 목록조회
			</DIV>
			
			<!-- User / Manager 구분 -->
			<ul class="nav nav-tabs">
                   <li ${(roleManageVO.roleClCode == '' || roleManageVO.roleClCode == 'CLIK') ? 'class="active"' : '' }><a href="/mna/rmt/EgovRoleList.do?roleClCode=CLIK">사용자</a>
                   </li>
                   <li ${roleManageVO.roleClCode == 'MNG' ? 'class="active"' : '' }><a href="/mna/rmt/EgovRoleList.do?roleClCode=MNG">관리자</a>
                   </li>
            </ul>	
			
			<!-- /.panel-heading -->			
			<DIV id="main" class="">
			<form:form name="listForm">
			<input type="hidden" id="roleClCode" name="roleClCode" value="<c:out value='${roleManageVO.roleClCode}'/>">
				<div class="select_box">
					<span>
						기능제한명 <input name="searchKeyword" type="text" value="<c:out value='${roleManageVO.searchKeyword}'/>" title="검색" onkeypress="press();" class=" input-sm input-search" />
						<a href="#LINK" onclick="javascript:fncSelectRoleList('1')" style="selector-dummy:expression(this.hideFocus=false);">
						<button type="button" class="btn btn-primary" ><spring:message code="button.search" /></button>
						</a>
					</span>
				</div>
			
			
			<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="기능 관리 테이블입니다.기능  ID,기능 명,기능 타입,기능 Sort,기능 설명,등록일자의 정보를 담고 있습니다.">
			 <thead>
			  <tr>
			    <th class="title" width="3%" scope="col" nowrap="nowrap"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
			    <th class="title" width="10%" scope="col" nowrap="nowrap">기능제한ID</th>
			    <th class="title" width="20%" scope="col" nowrap="nowrap">기능제한명</th>
			    <!-- <th class="title" width="10%" scope="col" nowrap="nowrap">제한기능타입</th> -->
			    <!-- <th class="title" width="10%" scope="col" nowrap="nowrap">기능 Sort</th> -->
			    <th class="title" width="30%" scope="col" nowrap="nowrap">기능제한설명</th>
			    <th class="title" width="12%" scope="col" nowrap="nowrap">등록일자</th>
			    <th class="title" width="5%" scope="col" nowrap="nowrap"></th>
			  </tr>
			 </thead>
			 <tbody>
			 <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(roleList) == 0}">
			<tr>
			<td class="lt_text3" colspan="6">
				<spring:message code="common.nodata.msg" />
			</td>
			</tr>
			</c:if>
			 <c:forEach var="role" items="${roleList}" varStatus="status">
			  <tr>
			    <td class="lt_text3" nowrap="nowrap"><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${role.roleCode}"/>" /></td>
			    <td class="lt_text" nowrap="nowrap"><a href="/mna/rmt/EgovRole.do?roleCode=${role.roleCode}"><c:out value="${role.roleCode}"/></a></td>
			    <td class="lt_text" nowrap="nowrap"><c:out value="${role.roleNm}"/></td>
			    <%-- <td class="lt_text3" nowrap="nowrap"><c:out value="${role.roleTyp}"/></td> --%>
			    <%-- <td class="lt_text3" nowrap="nowrap"><c:out value="${role.roleSort}"/></td> --%>
			    <td class="lt_text3" nowrap="nowrap"><c:out value="${role.roleDc}"/></td>
			    <td class="lt_text3" nowrap="nowrap"><c:out value="${role.roleCreatDe}"/></td>
			    <td class="lt_text3" nowrap="nowrap"><a href="#LINK" onclick="javascript:fncSelectRole('<c:out value="${role.roleCode}"/>')"><img src="<c:url value='/images/clikmng/cmm/icon/search.gif'/>" width="15" height="15" align="middle"  alt="상세조회"></a></td>
			  </tr>
			 </c:forEach>
			 </tbody>
			
			 <!--tfoot>
			  <tr class="">
			   <td colspan=6 align="center"></td>
			  </tr>
			 </tfoot -->
			</table>
			
			<p class="tr">
				<button type="button" class="btn btn-primary" onclick="javascript:fncAddRoleInsert()">등록</button>
				<button type="button" class="btn btn-danger"  onclick="javascript:fncRoleListDelete()">삭제</button>
			</p>
			
			<c:if test="${!empty roleManageVO.pageIndex }">
			<div align="center">
			    <div>
			    	<ul class="pagination">
				        <ui:pagination paginationInfo = "${paginationInfo}"
				            type="image"
				            jsFunction="linkPage"
				            />
				     </ul>
			    </div>
			</div>
			</c:if>
			<input type="hidden" name="roleCode"/>
			<input type="hidden" name="roleCodes"/>
			<input type="hidden" name="pageIndex" value="<c:out value='${roleManageVO.pageIndex}'/>"/>
			<input type="hidden" name="searchCondition"/>
			</form:form>
			
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->					
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>
