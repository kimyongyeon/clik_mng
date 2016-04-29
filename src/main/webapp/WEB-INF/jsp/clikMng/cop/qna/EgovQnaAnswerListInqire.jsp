<%
 /**
  * @Class Name : EgovQnaAnswerListInqire.jsp
  * @Description : EgovQnaAnswerListInqire 화면
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
<title>Q&amp;A 목록조회</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javaScript" language="javascript">

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_qnaanswerlist(){

	// 첫 입력란에 포커스..
	document.QnaAnswerListForm.searchKeyword.focus();
	
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	
	document.QnaAnswerListForm.pageIndex.value = pageNo;
	document.QnaAnswerListForm.action = "<c:url value='/cop/qnm/QnaAnswerListInqire.do'/>";
   	document.QnaAnswerListForm.submit();
   	
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_qnacnanswer(){

	document.QnaAnswerListForm.pageIndex.value = 1;
	document.QnaAnswerListForm.submit();
	
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
	
	var certificationAt = document.QnaAnswerListForm.certificationAt.value;
	
	// 인증여부 확인
	if (certificationAt == "N") {

		// 로그인? 실명확인 여부 화면 호출
		var returnValue = window.showModalDialog(loginRealnm_url, self, loginRealnm_status);
		
		// 결과값을 받아. 결과를 Submit한다.
	 	if	(returnValue)	{

	 		ls_loginRealnmAt = document.QnaAnswerListForm.loginRealnmAt.value;

	 		// 로그인처리
	 		if (ls_loginRealnmAt == "L")		{

				// 로그인 화면 호출
			    /* 추후 진행 예정..
	 			returnValue = window.showModalDialog(login_url, self, login_status);
	 			
	 			returnValue = true;
				*/

				// 팝업이 아닌 메인 화면으로 처리.
	 			document.QnaAnswerListForm.action = "<c:url value='/uat/uia/egovLoginUsr.do'/>";
	 			document.QnaAnswerListForm.submit();
	 			 			

	 			returnValue = false;
	 			
	 		// 실명확인처리
	 		} else if (ls_loginRealnmAt == "R")	{
			 		 			
				// 실명확인 화면 호출
	 			returnValue = window.showModalDialog(realnm_url, self, realnm_status);
				
	 			ls_wrterNm = document.QnaAnswerListForm.realname.value;
	 			
	 			document.QnaAnswerListForm.wrterNm.value = ls_wrterNm;	 			 				 			

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

	document.QnaAnswerListForm.action = "<c:url value='/cop/qna/QnaCnRegistView.do'/>";
	document.QnaAnswerListForm.submit();
		
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_qnaanswerdetail(qaId, pageIndex) {		

	// Q&A ID 셋팅.
	document.QnaAnswerListForm.qaId.value = qaId;	
	document.QnaAnswerListForm.pageIndex.value = pageIndex;
    document.QnaAnswerListForm.action = "<c:url value='/cop/qna/QnaInqireCoUpdt.do'/>"; 
  	document.QnaAnswerListForm.submit();	
	   	   		
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.QnaAnswerListForm;
	varForm.pageIndex.value = '1';
	varForm.pageUnit.value = val;
    varForm.action = "<c:url value='/cop/qnm/QnaAnswerListInqire.do'/>";
    varForm.submit();
}

</script>
</head>
<body onLoad="fn_egov_initl_qnaanswerlist();">
<jsp:include page="/cmm/top/top.do" />
<form name="QnaAnswerListForm" action="<c:url value='/cop/qnm/QnaAnswerListInqire.do'/>" method="post">
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Q&amp;A 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->

	 

			<DIV class="panel-heading">
				 &nbsp;Q&amp;A 목록조회
			</DIV>

				

				<input type="hidden" name="certificationAt" value="${certificationAt}">
				<input type="hidden" name="loginRealnmAt" value="">
				<div class="select_box">
					<span>
					   <select name="searchCondition" class=" input-sm" title="조회조건 선택">
						   <option selected value='0'>전체</option>
					       <option value="qestnSj"  <c:if test="${searchVO.searchCondition == 'qestnSj'}">selected="selected"</c:if> >질문제목</option>			   
					       <option value="qestnCn"  <c:if test="${searchVO.searchCondition == 'qestnCn'}">selected="selected"</c:if> >질문내용</option>							   
						   <option value="wrterNm"  <c:if test="${searchVO.searchCondition == 'wrterNm'}">selected="selected"</c:if> >작성자명</option>
				   
					   
					   </select>
					   <input name="searchKeyword" type="text" class=" input-sm input-search" value='<c:out value="${searchVO.searchKeyword}"/>' title="검색어 입력"> 
						<input type="submit" class="btn btn-primary" value="<spring:message code="button.search" />" onclick="fn_egov_search_qnacnanswer(); return false;">
					</span>
				</div>
				
				
				<div class="page">
					총 건수 :  <c:out value="${paginationInfo.totalRecordCount}" />건
	
					<span>
						출력건수
						<select id="pageUnit" name="pageUnit" class="input-sm" onchange="fnChgListCnt(this.value);">
							<option value="10" <c:if test="${searchVO.pageUnit == '10' }">selected</c:if>>10</option>
							<option value="30" <c:if test="${searchVO.pageUnit == '30' }">selected</c:if>>30</option>
							<option value="50" <c:if test="${searchVO.pageUnit == '50' }">selected</c:if>>50</option>
							<option value="100" <c:if test="${searchVO.pageUnit == '100' }">selected</c:if>>100</option>
						</select>
					</span>
				
				</div>					
				

				<input name="qaId" type="hidden" value="">
				<input name="passwordConfirmAt" type="hidden" value="">
				<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>

				
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<table width="98%" cellpadding="8" class="table table-striped table-bordered table-hover dataTable no-footer" border="0" summary="Q&amp;A에 대한 답변목록을 제공합니다.">
				<thead>
				<tr>      
				    <th scope="col" class="title" width="10%" nowrap>번호</th>        
				    <th scope="col" class="title" width="40%" nowrap>질문제목</th>    
				    <th scope="col" class="title" width="15%" nowrap>작성자</th>        
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
							<a href="#LINK" onclick="fn_egov_inquire_qnaanswerdetail('${resultInfo.qaId}', '${searchVO.pageIndex}'); return false;"><c:out value="${resultInfo.qestnSj}"/></a>
						</td>
						<td class="lt_text3"><c:out value="${resultInfo.wrterNm}"/></td>
						<!--  <td class="lt_text3"><c:out value="${resultInfo.qnaProcessSttusCodeNm}"/></td>  -->
						<td class="lt_text3"><c:out value="${resultInfo.inqireCo}"/></td>				
						<td class="lt_text3"><c:out value='${fn:substring(resultInfo.writngDe, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.writngDe, 4,6)}'/>-<c:out value='${fn:substring(resultInfo.writngDe, 6,8)}'/>		
				  </tr>   
				</c:forEach>
				</tbody>  
				</table>
				
				
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td height="3px"></td>
				</tr>
				</table>
				
				<p class="tr">
					<button type="button" class="btn btn-primary" onclick="fn_egov_regist_qnacn(); return false;"><spring:message code="button.create" /></button>						
				</p>
					
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

</form>
<!-- /page-wrapper -->					
</body>
</html>
