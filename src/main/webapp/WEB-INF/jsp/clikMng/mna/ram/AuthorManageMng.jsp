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
  * @Class Name : EgovAuthorManage.java
  * @Description : EgovAuthorManage List 화면
  * @Modification Information
  * @
  * @  수정일                     수정자                    수정내용
  * @ -------       --------    ---------------------------
  * @ 2009.03.01    Lee.m.j       최초 생성
  *
  *  @author 실행환경 개발팀 홍길동
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *
  */
%>

<html lang="ko">
<head>

<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>

<title>메뉴 및 기능권한 설정 목록조회</title>

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
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                	    returnValue = returnValue + ";" + checkField[i].value;
                    checkCount++;
                }
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
                alert("선택된 권한이 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 권한이 없습니다.");
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

    document.listForm.authorCodes.value = returnValue;

    return returnBoolean;
}

function fncSelectAuthorList(pageNo){
	
	document.location.href= "/mna/ram/EgovAuthorList.do?pageIndex="+pageNo+"&searchCondition=1";
/* 	
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/mna/ram/EgovAuthorList.do'/>";
    document.listForm.submit();
 */    
}

function fncSelectAuthor(author) {
    document.listForm.authorCode.value = author;
    document.listForm.action = "<c:url value='/mna/ram/EgovAuthor.do'/>";
    document.listForm.submit();
}

function fncAddAuthorInsert() {
	location.href = "/mna/ram/EgovAuthorInsertView.do?authorClCode=MNG";
    //location.replace("<c:url value='/mna/ram/EgovAuthorInsertView.do'/>");
}

function fncAuthorDeleteList() {

    if(fncManageChecked()) {
        if(confirm("삭제하시겠습니까?")) {
            document.listForm.action = "<c:url value='/mna/ram/EgovAuthorListDelete.do'/>";
            document.listForm.submit();
        }
    }
}

function fncAddAuthorView() {
    document.listForm.action = "<c:url value='/mna/ram/EgovAuthorUpdate.do'/>";
    document.listForm.submit();
}

function fncSelectAuthorRole(author, authorClCode) {
	
	document.location.href= "/mna/ram/EgovAuthorRoleList.do?searchKeyword="+author+"&authorClCode="+authorClCode;
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/mna/ram/EgovAuthorList.do'/>";
    document.listForm.submit();
}


function press() {

    if (event.keyCode==13) {
    	fncSelectAuthorList('1');
    }
}

/* ********************************************************
* 탭클릭시 화면전환
******************************************************** */
function fn_tabClick(sel_name) {
	location.href="/mna/ram/EgovAuthorList.do?authorClCode="+sel_name;		
}



-->
</script>

</head>

<body>

<jsp:include page="/cmm/top/top.do" />

<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">메뉴 및 기능권한 설정</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->


			<DIV class="panel-heading">
				 메뉴 및 기능제한 권한 설정 목록조회
			</DIV>
			<!-- /.panel-heading -->	
			
			<!-- User / Manager 구분 -->
			<ul class="nav nav-tabs">
                   <li ${(authorManageVO.authorClCode == '' || authorManageVO.authorClCode == 'CLIK') ? 'class="active"' : '' }><a href="#LINK" onClick="javascript:fn_tabClick('CLIK')" data-toggle="tab">사용자</a>
                   </li>
                   <li ${authorManageVO.authorClCode == 'MNG' ? 'class="active"' : '' }><a href="#LINK" onClick="javascript:fn_tabClick('MNG')"  data-toggle="tab">관리자</a>
                   </li>
            </ul>
					

				<form:form name="listForm" action="${pageContext.request.contextPath}/mna/ram/EgovAuthorList.do" method="post">
				<input type="hidden" id="authorClCode" name="authorClCode" value="<c:out value='${authorManageVO.authorClCode}'/>">
				<div class="select_box">
					<span>
						권한명 <input name="searchKeyword" type="text" value="<c:out value='${authorManageVO.searchKeyword}'/>" title="검색" onkeypress="press();" class=" input-sm input-search" aria-controls="dataTables-example" />
						<a href="#LINK" onclick="javascript:fncSelectAuthorList('1')" style="selector-dummy:expression(this.hideFocus=false);">
						<button type="button" class="btn btn-primary" ><spring:message code="button.search" /></button>
						</a>
					</span>
				</div>
				 
				<!--  <table width="100%" cellpadding="8" class="table-line" summary="권한관리에  관한 테이블입니다.권한ID,권한 명,설명, 등록일자, 롤 정보의 내용을 담고 있습니다.">-->
				<table width="100%" cellpadding="8" class="table table-striped table-bordered table-hover dataTable no-footer" summary="기능권한 관리에  관한 테이블입니다.기능권한 ID,기능권한 명,기능권한 설명, 등록일자, 롤 정보의 내용을 담고 있습니다.">	
				
				 <thead>
				  <tr>
				    <th class="title" width="3%" nowrap="nowrap"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택체크박스"></th>
				    <th class="title" width="15%" scope="col" nowrap="nowrap">권한 ID</th>
				    <th class="title" width="25%" scope="col" nowrap="nowrap">권한명</th>
				    <th class="title" width="30%" scope="col" nowrap="nowrap">권한설명</th>
				    <th class="title" width="15%" scope="col" nowrap="nowrap">메뉴 및 기능제한설정</th>
				    <th class="title" width="15%" scope="col" nowrap="nowrap">등록일자</th>
				    
				  </tr>
				 </thead>
				 <tbody>
				 <c:forEach var="author" items="${authorList}" varStatus="status">
				  <tr>
				    <td class="lt_text3" nowrap="nowrap"><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${author.authorCode}"/>" /></td>
				    <td class="lt_text" nowrap="nowrap"><a href="/mna/ram/EgovAuthor.do?authorCode=<c:out value="${author.authorCode}"/>&authorClCode=<c:out value="${author.authorClCode}" />"><c:out value="${author.authorCode}"/></a></td>
				    <td class="lt_text" nowrap="nowrap"><c:out value="${author.authorNm}"/></td>
				    <td class="lt_text3" nowrap="nowrap"><c:out value="${author.authorDc}"/></td>
				    <td class="lt_text3" nowrap="nowrap"><a href="/mna/ram/EgovAuthorRoleList.do?searchKeyword=<c:out value="${author.authorCode}"/>&authorClCode=<c:out value="${author.authorClCode}"/>"><img src="<c:url value='/images/clikmng/cmm/icon/search.gif'/>" width="15" height="15" align="middle" alt="롤 정보"></a></td>
					<td class="lt_text3" nowrap="nowrap"><c:out value="${author.authorCreatDe}"/></td>
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
					<!-- <button type="button" class="btn btn-default" onclick="javascript:fncSelectAuthorList('1')">목록</button> -->
					<button type="button" class="btn btn-primary" onclick="javascript:fncAddAuthorInsert()">등록</button>
					<!--  <button type="button" class="btn btn-success">수정</button>-->
					<button type="button" class="btn btn-danger" onclick="javascript:fncAuthorDeleteList()">삭제</button>	
				</p>					
							
				
				<c:if test="${!empty authorManageVO.pageIndex }">
				<div align="center" >
				    
				    	<ul class="pagination">
				        	<ui:pagination paginationInfo = "${paginationInfo}"
				            	type="image"
				            	jsFunction="linkPage"
				            	/>
				        </ul>

				</div>
				</c:if>
				<input type="hidden" name="authorCode"/>
				<input type="hidden" name="authorCodes"/>
				<input type="hidden" name="pageIndex" value="<c:out value='${authorManageVO.pageIndex}'/>"/>
				<input type="hidden" name="searchCondition"/>
				</form:form>
				
	
</DIV>	
<!-- /page-wrapper -->
</body>
</html>
