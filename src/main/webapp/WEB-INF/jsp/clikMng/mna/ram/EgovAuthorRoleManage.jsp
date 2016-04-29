<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>기능권한 및 기능 관리</title>

<script type="text/javaScript" language="javascript" defer="defer">
<!--

function fncCheckAll() {
    var checkField = document.fnForm.delYn;
    if(document.fnForm.checkAll.checked) {
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

    var checkField = document.fnForm.delYn;
    var checkId = document.fnForm.checkId;
    var checkRegYn = document.fnForm.regYn;
    var returnValue = "";
    var returnRegYns = "";
    var checkedCount = 0;
    var returnBoolean = false;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkedCount++;
                    checkField[i].value = checkId[i].value;

	                if(returnValue == "") {
	                    returnValue = checkField[i].value;
	                    returnRegYns = checkRegYn[i].value;
	                }
	                else {
	                    returnValue = returnValue + ";" + checkField[i].value;
	                    returnRegYns = returnRegYns + ";" + checkRegYn[i].value;
	                }
                }
            }

            if(checkedCount > 0)
            	returnBoolean = true;
            else {
                alert("선택된  기능이 없습니다.");
                returnBoolean = false;
            }
        } else {
        	 if(document.fnForm.delYn.checked == false) {
                alert("선택된 기능이 없습니다.");
            	returnBoolean = false;
            }
            else {
            	returnValue = checkId.value;
                returnRegYns = checkRegYn.value;

                returnBoolean = true;
            }
        }
    } else {
        alert("조회된 결과가 없습니다.");
    }

    document.fnForm.roleCodes.value = returnValue;
    document.fnForm.regYns.value = returnRegYns;

    return returnBoolean;

}

function fncSelectAuthorRoleList() {
    document.fnForm.searchCondition.value = "1";
    document.fnForm.pageIndex.value = "1";
    document.fnForm.action = "<c:url value='/mna/ram/EgovAuthorRoleList.do'/>";
    document.fnForm.submit();
}

function fncSelectAuthorList(){
	
	location.href="/mna/ram/EgovAuthorList.do?authorClCode=CLIK&pageIndex=1";
	
   // document.fnForm.searchCondition.value = "1";
   // document.fnForm.pageIndex.value = "1";
/*    
    document.fnForm.searchKeyword.value = "";
    document.fnForm.action = "<c:url value='/mna/ram/EgovAuthorList.do'/>";
    document.fnForm.submit();
 */    
}

function fncSelectAuthorRole(roleCode) {
    document.fnForm.roleCode.value = roleCode;
    document.fnForm.action = "<c:url value='/mna/ram/EgovRole.do'/>";
    document.fnForm.submit();
}

function fncAddAuthorRoleInsert() {
	if(fncManageChecked()) {
	    if(confirm("등록하시겠습니까?")) {
            document.fnForm.action = "<c:url value='/mna/ram/EgovAuthorRoleInsert.do'/>";
            document.fnForm.submit();
	    }
	} else return;
}

function linkPage(pageNo){
    document.fnForm.searchCondition.value = "1";
    document.fnForm.pageIndex.value = pageNo;
    document.fnForm.action = "<c:url value='/mna/ram/EgovAuthorRoleList.do'/>";
    document.fnForm.submit();
}


function press() {

    if (event.keyCode==13) {
    	fncSelectAuthorRoleList();
    }
}
-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">메뉴 및 기능 설정</h1>
            <p class="tr">
				<button type="button" class="btn btn-default" onclick="javascript:fncSelectAuthorList()">목록</button>
			</p>  		
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
  	
	<div id="border" class="" style="overflow:hidden;">

		<!-- ::::: 메뉴 설정 시작 ::::: -->
		<DIV class="panel panel-default" style="  height:1800px; width:510px; float:left; position:relative; ">
			<DIV class="panel-heading">
				 권한별 메뉴 설정
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="main" class="panel-body" style="height:100%; width:100%;">
			    <object data="/mna/mcm/EgovMenuCreatSelect.do?authorCode=<c:out value='${authorRoleManageVO.searchKeyword}' />&authorClCode=<c:out value='${authorClCode}' />"  type="text/html" title="메뉴설정" width="100%" height="100%">  
			      이 폼은 메뉴 설정 하는 폼입니다. <br /> 
			    <a href="/mna/mcm/EgovMenuCreatSelect.do?authorCode=<c:out value='${authorRoleManageVO.searchKeyword}' />">인라인프레임이 표시되지 않는 분은 여기를 클릭하세요.</a> 
			    </object> 
			</DIV>
			<!-- /panel body --> 
		</DIV>
		<!-- ::::: 메뉴 설정 끝 ::::: --> 




		<!-- ::::: 기능 설정 시작 ::::: -->
		<DIV class="panel panel-default" style="  position:absolute ;  min-width:35%; margin-left:500px; margin-right:20px; ">
			<DIV class="panel-heading">
				 권한별 기능제한 설정
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="main" class="panel-body">
			<p class="tr">
				<button type="button" class="btn btn-primary" onclick="javascript:fncAddAuthorRoleInsert()">기능제한저장</button>
			</p>  				
				<table border="0" width="100%">
				  <tr>
				    <td>
				<form:form name="fnForm" action="${pageContext.request.contextPath}/mna/ram/EgovAuthorRoleList.do" method="post">
				
<%-- 				
				<div class="select_box">
				<span>권한코드 :  <input name="searchKeyword" type="text" size="30" style="width:150px;" value="<c:out value='${authorRoleManageVO.searchKeyword}'/>" title="검색"  onkeypress="press();" readonly="readonly" class="ip input-sm" />
				<a href="#LINK" onclick="javascript:fncSelectAuthorRoleList()" style="selector-dummy:expression(this.hideFocus=false);" class="btn btn-primary">조회</a>
				</span>
				</div>
 --%>				
				
				<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="권한 기능을 관리하는 테이블입니다.기능 ID,기능 명,기능 타입,기능 Sort,기능 설명,등록일자,등록여부의 내용을 담고 있습니다.">
				 <thead>
				  <tr>
				    <th class="title" width="3%" scope="col" nowrap="nowrap"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
				    <th class="title" width="10%" scope="col" nowrap="nowrap">기능제한ID</th>
				    <th class="title" width="15%" scope="col" nowrap="nowrap">기능제한명</th>
				    <!-- <th class="title" width="10%" scope="col" nowrap="nowrap">기능 타입</th> -->
				    <!-- <th class="title" width="10%" scope="col" nowrap="nowrap">기능 Sort</th> -->
				    <th class="title" width="20%" scope="col" nowrap="nowrap">기능 설명</th>
				     <!--<th class="title" width="12%" scope="col" nowrap="nowrap">등록일자</th>-->
				    <th class="title" width="15%" scope="col" nowrap="nowrap">등록여부</th>
				  </tr>
				 </thead>
				 <tbody>
				  <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
				<c:if test="${fn:length(authorRoleList) == 0}">
				<tr>
				<td class="lt_text3" colspan="7">
					<spring:message code="common.nodata.msg" />
				</td>
				</tr>
				</c:if>
				 <c:forEach var="authorRole" items="${authorRoleList}" varStatus="status">
				  <tr>
				    <td class="lt_text3" nowrap="nowrap"><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${authorRole.roleCode}"/>" /></td>
				    <td class="lt_text" nowrap="nowrap"><c:out value="${authorRole.roleCode}"/></td>
				    <td class="lt_text" nowrap="nowrap"><c:out value="${authorRole.roleNm}"/></td>
				    <!-- <td class="lt_text3" nowrap="nowrap"><c:out value="${authorRole.roleTyp}"/></td> -->
				    <%-- <td class="lt_text3" nowrap="nowrap"><c:out value="${authorRole.roleSort}"/></td> --%>
				    <td class="lt_text3" nowrap="nowrap"><c:out value="${authorRole.roleDc}"/></td>
				    <!--<td class="lt_text3" nowrap="nowrap"><c:out value="${authorRole.creatDt}"/></td>-->
				    <td class="lt_text3" nowrap="nowrap">
				        <select name="regYn" title="등록여부" class="form-control">
				            <option value="Y" <c:if test="${authorRole.regYn == 'Y'}">selected</c:if> >등록</option>
				            <option value="N" <c:if test="${authorRole.regYn == 'N'}">selected</c:if> >미등록</option>
				        </select>
				    </td>
				  </tr>
				 </c:forEach>
				 </tbody>
				
				 <!--tfoot>
				  <tr class="">
				   <td colspan=6 align="center"></td>
				  </tr>
				 </tfoot -->
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td height="10"></td>
				  </tr>
				</table>
				
				<input type="hidden" name="roleCodes"/>
				<input type="hidden" name="regYns"/>
				<input type="hidden" name="pageIndex" value="<c:out value='${authorRoleManageVO.pageIndex}'/>"/>
				<input type="hidden" name="authorCode" value="<c:out value="${authorRoleManageVO.searchKeyword}"/>"/>
				<input type="hidden" name="searchCondition"/>
				</form:form>
				</td>
				</tr>
				</table>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- ::::: 기능 설정 끝 ::::: -->
		<!-- /.panel panel-default -->					
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->	
</body>
</html>
