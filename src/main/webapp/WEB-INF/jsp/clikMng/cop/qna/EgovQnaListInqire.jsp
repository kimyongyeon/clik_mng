<%
 /**
  * @Class Name : EgovQnaListInqire.jsp
  * @Description : EgovQnaListInqire 화면
  * @Modification Information
  * @
  * @  수정일   	수정자		수정내용
  * @ ----------	------		----------------------------------------------
  *
  *  @author  
  *  @since 
  *  @version 
  *  @see
  *  
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javaScript" language="javascript">

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_qnalist(){

	// 첫 입력란에 포커스..
	document.QnaListForm.searchKeyword.focus();
	
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	
	document.QnaListForm.pageIndex.value = pageNo;
	document.QnaListForm.action = "<c:url value='/cop/qna/QnaListInqire.do'/>";
   	document.QnaListForm.submit();
   	
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_qnacn(){

	document.QnaListForm.pageIndex.value = 1;
	document.QnaListForm.submit();
	
}

/*********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_qnacn(){

	// 로그인을 할 것인자? 실명확인을 할것인지? 판단 화면
	var loginRealnm_url 	= "<c:url value='/cop/qna/LoginRealnmChoice.do'/>";	
	var	loginRealnm_status 	= "dialogWidth=350px;dialogHeight=150px;resizable=no;center=yes";

	// 로그인 화면
	var	login_url 		= "<c:url value='/uat/uia/egovLoginUsr.do'/>"; 		
	var login_status 	= "dialogWidth=700px;dialogHeight=420px;resizable=no;center=yes";
	

	// 실명확인 화면
	var	realnm_url 	= "<c:url value='/sec/rnc/EgovRlnmCnfirm.do?nextUrlName=button.qnaregist&nextUrl=C'/>"; 		
	var realnm_status 	= "dialogWidth=740px;dialogHeight=180px;resizable=no;center=yes";

	var	returnValue = false;
	
	var certificationAt = document.QnaListForm.certificationAt.value;
	
	// 인증여부 확인
	if (certificationAt == "N") {

		// 로그인? 실명확인 여부 화면 호출
		var returnValue = window.showModalDialog(loginRealnm_url, self, loginRealnm_status);
		
		// 결과값을 받아. 결과를 Submit한다.
	 	if	(returnValue)	{

	 		ls_loginRealnmAt = document.QnaListForm.loginRealnmAt.value;

	 		// 로그인처리
	 		if (ls_loginRealnmAt == "L")		{

				// 로그인 화면 호출
			    /* 추후 진행 예정..
	 			returnValue = window.showModalDialog(login_url, self, login_status);
	 			
	 			returnValue = true;
				*/

				// 팝업이 아닌 메인 화면으로 처리.
	 			document.QnaListForm.action = "<c:url value='/uat/uia/egovLoginUsr.do'/>";
	 			document.QnaListForm.submit();
	 			 			

	 			returnValue = false;
	 			
	 		// 실명확인처리
	 		} else if (ls_loginRealnmAt == "R")	{
			 		 			
				// 실명확인 화면 호출
	 			returnValue = window.showModalDialog(realnm_url, self, realnm_status);
				
	 			ls_wrterNm = document.QnaListForm.realname.value;
	 			
	 			document.QnaListForm.wrterNm.value = ls_wrterNm;	 			 				 			

	 		}  // 로그인처리 혹은 실명확인 경우 end...

	 		
 			if	(returnValue)	{

 				// QNA등록화면 호출..
 				fn_egov_regist_cnsltcn();
 		 		 				
 			}
	 		
	 			 			
	 	}	// 결과값을 받아. 결과를 Submit한다. end..
		
	} else	{

		// QNA등록화면 호출..
		fn_egov_regist_cnsltcn();
		
	}

	
				
}

/*********************************************************
 * Q&A 등록화면 호출
 ******************************************************** */
function fn_egov_regist_cnsltcn(){

	document.QnaListForm.action = "<c:url value='/cop/qna/QnaCnRegistView.do'/>";
	document.QnaListForm.submit();
		
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_qnadetail(qaId, pageIndex) {		

	// 사이트 키값(siteId) 셋팅.
	document.QnaListForm.qaId.value = qaId;	
	document.QnaListForm.pageIndex.value = pageIndex;
//  document.QnaListForm.action = "<c:url value='/cop/qna/QnaDetailInqire.do'/>";
  	document.QnaListForm.action = "<c:url value='/cop/qna/QnaInqireCoUpdt.do'/>"; 
  	document.QnaListForm.submit();	
	   	   		
}

</script>
</head>
<body onLoad="fn_egov_initl_qnalist();">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">커뮤니티관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
	<p class="tr">
		<!--<button type="button" class="btn btn-default" onclick="javascript:fncSelectAuthorList('1')">목록</button>-->
		<button type="button" class="btn btn-primary" onclick="fn_egov_regist_qnacn(); return false;"><spring:message code="button.create" /></button>						
	</p>    	
	<div id="border" style="display:" class="col-lg-12">
		<DIV class="panel panel-default">
			<DIV class="panel-heading">
				 Q&A목록조회
			</DIV>
			<!-- /.panel-heading -->			
			<DIV id="content">

				<form name="QnaListForm" action="<c:url value='/cop/qna/QnaListInqire.do'/>" method="post">
				
				<!--실명확인을 위한  설정   Start...-->
				<input type="hidden" name="ihidnum" value="">
				<input type="hidden" name="realname" value="">
				
				<input type="hidden" name ="nextUrlName" value="QA등록">
				<input type="hidden" name ="nextUrl" value="<c:url value='/cop/qna/QnaCnRegistView.do' />">
				
				<input type="hidden" name="certificationAt" value="${certificationAt}">
				<input type="hidden" name="loginRealnmAt" value="">
				
				<input type="hidden" name="wrterNm" value="">
				<!--실명확인을 위한  설정 End......-->
				
				<table width="100%" cellpadding="8" class="table-search" border="0">
				 <tr>
				  <td width="50%">
				   	<select name="searchCondition" class="form-control input-sm" title="조회조건 선택">
						   <option selected value=''>--선택하세요--</option>
						   <option value="wrterNm"  <c:if test="${searchVO.searchCondition == 'wrterNm'}">selected="selected"</c:if> >작성자명</option>
						   <option value="qestnSj"  <c:if test="${searchVO.searchCondition == 'qestnSj'}">selected="selected"</c:if> >질문제목</option>			   
					   </select>
					</td>
				  
				  <td width="35%">
				    <input name="searchKeyword" type="text" class="form-control input-sm input-search"  value='<c:out value="${searchVO.searchKeyword}"/>' title="검색어 입력" > 
				  </td>
				  
				  <th width="10%">
				   <table border="0" cellspacing="0" cellpadding="0">
				    <tr> 
				      <td><span class="button"><input type="submit" value="<spring:message code="button.search" />" onclick="fn_egov_search_qnacn(); return false;"></span></td>
					  <td>&nbsp;&nbsp;</td> 
				    </tr>
				   </table>
				  </th>  
				 </tr>
				</table>
				<input name="qaId" type="hidden" value="">
				<input name="passwordConfirmAt" type="hidden" value="">
				<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
				</form>
				
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<table width="98%" cellpadding="8" class="table table-striped table-bordered table-hover dataTable no-footer" border="0" summary="Q&amp;A에 대한 목록을 제공합니다.">
				<thead>
				<tr>      
				    <th scope="col" class="title" width="10%" nowrap>번호</th>        
				    <th scope="col" class="title" width="40%" nowrap>질문제목</th>    
				    <th scope="col" class="title" width="15%" nowrap>작성자</th>        
				    <th scope="col" class="title" width="10%" nowrap>진행상태</th>               
				    <th scope="col" class="title" width="10%" nowrap>조회수</th>        
				    <th scope="col" class="title" width="15%" nowrap>작성일자</th>                   
				</tr>
				</thead>
				
				 <c:if test="${fn:length(resultList) == 0}">
				  <tr> 
				  	<td class="lt_text3" colspan=10>
				  		<spring:message code="common.nodata.msg" />  		
				  	</td>
				  </tr>   	          				 			   
				 </c:if>
				  
				<tbody>      
				<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
				  <tr>
						<td class="lt_text3"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
						<td class="listLeft">
					    	<span class="link">
					    		<a href="#LINK" onclick="fn_egov_inquire_qnadetail('<c:out value="${resultInfo.qaId}"/>', '<c:out value='${searchVO.pageIndex}'/>'); return false;" >
					    		<c:out value="${resultInfo.qestnSj}"/></a>
					    	</span>
						</td>
						<td class="lt_text3"><c:out value="${resultInfo.wrterNm}"/></td>
						<td class="lt_text3"><c:out value="${resultInfo.qnaProcessSttusCodeNm}"/></td>
						<td class="lt_text3"><c:out value="${resultInfo.inqireCo}"/></td>				
						<td class="lt_text3"><c:out value='${fn:substring(resultInfo.writngDe, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.writngDe, 4,6)}'/>-<c:out value='${fn:substring(resultInfo.writngDe, 6,8)}'/></td>		
				  </tr>   
				</c:forEach>
				</tbody>  
				</table>
				
				
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<div align="center">
					<div>
						<ul class="pagination">
							<ui:pagination paginationInfo = "${paginationInfo}"
								type="image"
								jsFunction="fn_egov_select_linkPage"
								/>
						</ul>
					</div>
				</div>
				
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
