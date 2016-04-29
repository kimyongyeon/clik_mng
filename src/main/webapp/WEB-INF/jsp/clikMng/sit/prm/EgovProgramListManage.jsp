<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovProgramListManage.jsp
  * @Description : 프로그램목록 조회 화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  *
  *  @author 
  *  @since 
  *  @version 
  *  @see
  *
  */
  /* Image Path 설정 */
  String imagePath_icon   = "/images/clikmng/sit/prm/icon/";
  String imagePath_button = "/images/clikmng/sit/prm/button/";
%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>프로그램목록리스트</title>
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 모두선택 처리 함수
 ******************************************************** */
function fCheckAll() {
    var checkField = document.progrmManageForm.checkField;
    if(document.progrmManageForm.checkAll.checked) {
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

/* ********************************************************
 * 멀티삭제 처리 함수
 ******************************************************** */
function fDeleteProgrmManageList() {
    var checkField = document.progrmManageForm.checkField;
    var ProgrmFileNm = document.progrmManageForm.checkProgrmFileNm;
    var checkProgrmFileNms = "";
    var checkedCount = 0;
    if(checkField) {
    	if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkProgrmFileNms += ((checkedCount==0? "" : ",") + ProgrmFileNm[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
            	checkProgrmFileNms = ProgrmFileNm.value;
            }
        }
    }

    document.progrmManageForm.checkedProgrmFileNmForDel.value=checkProgrmFileNms;
    document.progrmManageForm.action = "<c:url value='/sit/prm/EgovProgrmManageListDelete.do'/>";
    document.progrmManageForm.submit();
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
//	document.menuManageForm.searchKeyword.value =
	document.progrmManageForm.pageIndex.value = pageNo;
	document.progrmManageForm.action = "<c:url value='/sit/prm/EgovProgramListManageSelect.do'/>";
   	document.progrmManageForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectProgramListManage() {
	document.progrmManageForm.pageIndex.value = 1;
	document.progrmManageForm.action = "<c:url value='/sit/prm/EgovProgramListManageSelect.do'/>";
	document.progrmManageForm.submit();
}
/* ********************************************************
 * 입력 화면 호출 함수
 ******************************************************** */
function insertProgramListManage() {
   	document.progrmManageForm.action = "<c:url value='/sit/prm/EgovProgramListRegist.do'/>";
   	document.progrmManageForm.submit();
}
/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function selectUpdtProgramListDetail(progrmFileNm) {
	document.progrmManageForm.tmp_progrmNm.value = progrmFileNm;
   	document.progrmManageForm.action = "<c:url value='/sit/prm/EgovProgramListDetailSelect.do'/>";
   	document.progrmManageForm.submit();
}
/* ********************************************************
 * focus 시작점 지정함수
 ******************************************************** */
 function fn_FocusStart(){
		var objFocus = document.getElementById('F1');
		objFocus.focus();
	}
-->
</script>
</head>
<body>
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">프로그램관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
		<p class="tr">					
			<button type="button" class="btn btn-primary" onclick="insertProgramListManage(); return false;"><spring:message code="button.create" /></button>
			<button type="button" class="btn btn-danger" onclick="fDeleteProgrmManageList(); return false;"><spring:message code="button.delete" /></button>												
			<!--
			<a href="<c:url value='/uss/ion/pwm/listPopup.do' />"><button type="button" class="btn btn-default" ><spring:message code="button.list" /></button></a>
			<button type="button" class="btn btn-success"  onclick="fn_egov_modify_PopupManage(); return false;"><spring:message code="button.update" /></button>				
			<button type="button" class="btn btn-danger" onclick="fn_egov_delete_PopupManage(); return false;"><spring:message code="button.delete" /></button>						
			-->
		</p>  	
	<div id="border" style="display:" class="col-lg-12">
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				 프로그램목록관리
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="border" class="panel-body">
			<table border="0" width="100%">
			  <tr>
			    <td>
			<!-- ********** 여기서 부터 본문 내용 *************** -->
			
			<form name="progrmManageForm" action ="<c:url value='/sit/prm/EgovProgramListManageSelect.do' />" method="post">
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			<input name="checkedProgrmFileNmForDel" type="hidden" />
			<DIV id="main" style="display:">
			

			<table width="100%" border="0" cellpadding="0" cellspacing="1">
			 <tr>
			  	<td width="50%"><input name="searchKeyword" type="text" value="${searchVO.searchKeyword}" id="F1" title="검색조건" class="form-control input-sm input-search"></td>
				<td width="50%"><span class="button"><input type="submit" value="<spring:message code="button.search" />" onclick="selectProgramListManage(); return false;"></span></td>  
			 </tr>
			</table>
			<table width="717" cellpadding="8" class="table table-striped table-bordered" style="table-layout:fixed" summary="프로그램목록관리 목록으로 프로그램파일명, 프로그램명, url,프로그램설명 으로 구성">
			 <thead>
			  <tr>
			    <th class="title" width="20" scope="col">
			    <input type="checkbox" name="checkAll" class="check2" onclick="javascript:fCheckAll();" title="전체선택">
			    </th>
			    <th class="title" width="150" scope="col">프로그램파일명</th>
			    <th class="title" width="137" scope="col">프로그램명</th>
			    <th class="title" width="260" scope="col">URL</th>
			    <th class="title" width="150" scope="col">프로그램설명</th>
			  </tr>
			 </thead>
			 <tbody>
			 <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			 <c:if test="${fn:length(list_progrmmanage) == 0}">
			 <tr>
			 <td class="lt_text3" colspan="5">
				<spring:message code="common.nodata.msg" />
			 </td>
			 </tr>
			 </c:if>
			 <c:forEach var="result" items="${list_progrmmanage}" varStatus="status">
			  <tr>
			    <td class="lt_text3" nowrap>
			       <input type="checkbox" name="checkField" class="check2" title="선택">
			       <input name="checkProgrmFileNm" type="hidden" value="<c:out value='${result.progrmFileNm}'/>"/>
			    </td>
			    <td class="lt_text" style="cursor:hand;" nowrap>
			            <span class="link"><a href="<c:url value='/sit/prm/EgovProgramListDetailSelect.do'/>?tmp_progrmNm=<c:out value="${result.progrmFileNm}"/>"  onclick="selectUpdtProgramListDetail('<c:out value="${result.progrmFileNm}"/>'); return false;">
			
			            <c:if test="${fn:length(result.progrmFileNm)> 22}">
					    	<c:out value="${fn:substring(result.progrmFileNm,0, 22)}"/>...
					    </c:if>
					    <c:if test="${fn:length(result.progrmFileNm)<= 22}">
					    	<c:out value="${result.progrmFileNm}"/>
					    </c:if>
			
			            </a></span>
			    </td>
			    <td class="lt_text" nowrap>
			    <c:if test="${fn:length(result.progrmKoreanNm)> 12}">
			    	<c:out value="${fn:substring(result.progrmKoreanNm,0, 12)}"/>...
			    </c:if>
			    <c:if test="${fn:length(result.progrmKoreanNm)<= 12}">
			    	<c:out value="${result.progrmKoreanNm}"/>
			    </c:if>
			    </td>
			    <td class="lt_text" nowrap>
			    <c:if test="${fn:length(result.URL)> 35}">
			    	<c:out value="${fn:substring(result.URL,0, 35)}"/>...
			    </c:if>
			    <c:if test="${fn:length(result.URL)<= 35}">
			    	<c:out value="${result.URL}"/>
			    </c:if>
			
			    </td>
			    <td class="lt_text" nowrap><c:out value="${result.progrmDc}"/></td>
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
			  <tr>
			    <td height="10">
			<!-- 페이징 시작 -->
			<div align="center">
			  <div>
			  	<ul class="pagination">
					<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="linkPage"/>
				</ul>
			  </div>
			</div>
			<!-- 페이징 끝 -->
			    </td>
			  </tr>
			</table>
			
			
			</DIV>
			
			
			<input type="hidden" name="cmd">
			<input type="hidden" name="tmp_progrmNm">
			</form>
			<!-- ********** 여기까지 내용 *************** -->
			</td>
			</tr>
			</table>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->	 
		<p class="tr">					
			<button type="button" class="btn btn-primary" onclick="insertProgramListManage(); return false;"><spring:message code="button.create" /></button>
			<button type="button" class="btn btn-danger" onclick="fDeleteProgrmManageList(); return false;"><spring:message code="button.delete" /></button>												
			<!--
			<a href="<c:url value='/uss/ion/pwm/listPopup.do' />"><button type="button" class="btn btn-default" ><spring:message code="button.list" /></button></a>
			<button type="button" class="btn btn-success"  onclick="fn_egov_modify_PopupManage(); return false;"><spring:message code="button.update" /></button>				
			<button type="button" class="btn btn-danger" onclick="fn_egov_delete_PopupManage(); return false;"><spring:message code="button.delete" /></button>						
			-->
		</p>  			
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
<!-- /page-wrapper -->
</body>
</html>

