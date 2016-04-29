<%
 /**
  * @Class Name : EgovErrAnswerListInqire.jsp
  * @Description : EgovErrAnswerListInqire 화면
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
<title>오류신고관리</title>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javaScript" language="javascript">

/* *****************************
* 공통 레이어 팟업 open
********************************* */
function fnOpenLayerPopup(){
	
	$.ajax({
		url:"/cop/err/selectMngList.do",
		cache:false,
		type:"post",
		success: function(data, status) {
			//관리자 정보 출력
			var td = "";
			
			$(eval(data)).each(function(index,item){
				
				if(index != 0 && index % 2 == 0){
					$('#errMailRcvrChargerTable tbody').append("<tr>" + td + "</tr>");
					td = "";
				}
				
				td += "<td>";
				
				if(item.ER_EMAIL_RECPTN_AT == 'Y')
				{	
					td += "<input type='checkbox' name='chk' value='"+item.MNGR_ID+"' checked='checked'/> ";
				}
				else
				{
					td += "<input type='checkbox' name='chk' value='"+item.MNGR_ID+"'/> ";
				}
				
				td += "<input type='hidden' name='emailAddress' value='"+item.MNGR_EMAIL+"'/> ";
				td += item.MNGR_NM+"("+item.MNGR_EMAIL+")";
				td += "</td>";
			});
			
			$('#errMailRcvrChargerTable tbody').append("<tr>" + td + "</tr>");
			
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	
	var bw = $('body').width();
    var sw = $('#scrollbar').width();
    var wh = $(window).height();
    var dh = $(document).height();
    $('body').css('overflow', 'hidden').width( bw-sw );
    $('#layerPop').show();
}

/* *****************************
* 공통 레이어 팟업 close
********************************* */
function fnCloseLayerPopup(){
	$('#layerPop').hide();
	$('#scrollbar').hide();
	$('body').css('overflow', '').css('width', '');
	$('#councilUpdateFile').val('');
}

/* *****************************
* 오류신고 이메일 수신 담당자 등록
********************************* */
function fnEmailAdd(){
	
	var pattern = /^([a-zA-Z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	var mngrId = "";
	
	if(confirm("저장 하시겠습니까?")){
		var emailValid = true;
		$('#errMailRcvrChargerTable tbody tr input[name="chk"]:checked').each(function(index, item){
			mngrId += "," + $(item).val();
			if(!pattern.test($(item).next().val())){
				alert('이메일 형식에 맞지 않은 정보가 존재합니다. - ' + $(item).next().val());
				emailValid = false;
				return false;
			}
		});
		
		if(emailValid){
			mngrId = mngrId.substring(1);
			$.ajax({
				url:"/cop/err/updateErrMailRcvrCharger.do",
				cache:false,
				type:"post",
				data:{"mngrId" : mngrId},
				success: function(data, status) {
					alert(eval(data)[0].result_msg);
					
					if(eval(data)[0].result_code == "success"){
						fnCloseLayerPopup();
						window.location.reload();
					}else{
						
					}
				},
				error: function (data, status, e) {
					alert(eval(data)[0].result_msg);
				}
			});
		}
	}
		
}

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_initl_qnaanswerlist(){

	// 첫 입력란에 포커스..
	document.ErrAnswerListForm.searchKeyword.focus();
	
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	
	document.ErrAnswerListForm.pageIndex.value = pageNo;
	document.ErrAnswerListForm.action = "<c:url value='/cop/err/ErrAnswerListInqire.do'/>";
   	document.ErrAnswerListForm.submit();
   	
}

/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_qnacnanswer(){

	document.ErrAnswerListForm.pageIndex.value = 1;
	document.ErrAnswerListForm.submit();
	
}

/*********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_qnacn(){

	// 로그인을 할 것인자? 실명확인을 할것인지? 판단 화면
	var loginRealnm_url 	= "<c:url value='/cop/err/LoginRealnmChoice.do'/>";	
	var	loginRealnm_status 	= "dialogWidth=350px;dialogHeight=150px;resizable=no;center=yes";

	// 로그인 화면
	var	login_url 		= "<c:url value='/uat/uia/egovLoginUsr.do'/>"; 		
	var login_status 	= "dialogWidth=700px;dialogHeight=420px;resizable=no;center=yes";
	
	// 실명확인 화면
	var	realnm_url 	= "<c:url value='/sec/rnc/EgovRlnmCnfirm.do?nextUrlName=button.qnaregist&nextUrl=C'/>"; 		
	var realnm_status 	= "dialogWidth=740px;dialogHeight=180px;resizable=no;center=yes";

	var	returnValue = false;
	
	var certificationAt = document.ErrAnswerListForm.certificationAt.value;
	
	// 인증여부 확인
	if (certificationAt == "N") {

		// 로그인? 실명확인 여부 화면 호출
		var returnValue = window.showModalDialog(loginRealnm_url, self, loginRealnm_status);
		
		// 결과값을 받아. 결과를 Submit한다.
	 	if	(returnValue)	{

	 		ls_loginRealnmAt = document.ErrAnswerListForm.loginRealnmAt.value;

	 		// 로그인처리
	 		if (ls_loginRealnmAt == "L")		{

				// 로그인 화면 호출
			    /* 추후 진행 예정..
	 			returnValue = window.showModalDialog(login_url, self, login_status);
	 			
	 			returnValue = true;
				*/

				// 팝업이 아닌 메인 화면으로 처리.
	 			document.ErrAnswerListForm.action = "<c:url value='/uat/uia/egovLoginUsr.do'/>";
	 			document.ErrAnswerListForm.submit();
	 			 			

	 			returnValue = false;
	 			
	 		// 실명확인처리
	 		} else if (ls_loginRealnmAt == "R")	{
			 		 			
				// 실명확인 화면 호출
	 			returnValue = window.showModalDialog(realnm_url, self, realnm_status);
				
	 			ls_wrterNm = document.ErrAnswerListForm.realname.value;
	 			
	 			document.ErrAnswerListForm.wrterNm.value = ls_wrterNm;	 			 				 			

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

	document.ErrAnswerListForm.action = "<c:url value='/cop/err/ErrCnRegistView.do'/>";
	document.ErrAnswerListForm.submit();
		
}

/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_qnaanswerdetail(errorReportId, pageIndex) {		

	// Q&A ID 셋팅.
	document.ErrAnswerListForm.errorReportId.value = errorReportId;	
	document.ErrAnswerListForm.pageIndex.value = pageIndex;
    document.ErrAnswerListForm.action = "<c:url value='/cop/err/ErrInqireCoUpdt.do'/>"; 
  	document.ErrAnswerListForm.submit();	
	   	   		
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.ErrAnswerListForm;
	varForm.pageIndex.value = '1';
	varForm.pageUnit.value = val;
    varForm.action = "<c:url value='/cop/err/ErrAnswerListInqire.do'/>";
    varForm.submit();
}

</script>
</head>
<body onLoad="fn_egov_initl_qnaanswerlist();">
<jsp:include page="/cmm/top/top.do" />

<div id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">오류신고관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
	<div class="panel-heading">
		 오류신고 담당자 목록 <button onclick="fnOpenLayerPopup();" style="font-size: 13px;">설정</button>
	</div>
	<div>
		<table width="98%" cellpadding="8" class="table table-striped table-bordered table-hover dataTable no-footer" border="0" summary="">
			<c:forEach var="item" items="${errChargerList}" varStatus="s">
				<c:set var="row" value="${row } <td>${item.EMAIL }</td>" />
				<c:if test="${s.count % 5 == 0 && !s.first}">
				<tr>
				${row }
				</tr>
				<c:set var="row" value="" />
				</c:if>
			</c:forEach>
			<tr>
			${row }
			<c:if test="${fn:length(errChargerList) == 0}">
			<td>정보가 존재하지 않습니다.<td>
			</c:if>
			</tr>
		</table>
	</div>
	
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td height="10px"></td>
	</tr>
	</table>
<form name="ErrAnswerListForm" action="<c:url value='/cop/err/ErrAnswerListInqire.do'/>" method="post">	
	<div class="panel-heading">
		 오류신고 목록조회
	</div>
	<input type="hidden" name="certificationAt" value="${certificationAt}">
	<input type="hidden" name="loginRealnmAt" value="">
	<div class="select_box">
		<span>
		   <select name="searchCondition" class=" input-sm" title="조회조건 선택">
			   <option selected value='0'>전체</option>
		       <option value="reportSj"  <c:if test="${searchVO.searchCondition == 'reportSj'}">selected="selected"</c:if> >신고제목</option>			   
<%-- 		       <option value="reportCn"  <c:if test="${searchVO.searchCondition == 'reportCn'}">selected="selected"</c:if> >신고내용</option>							    --%>
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
	

	<input name="errorReportId" type="hidden" value="">
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
	    <th scope="col" class="title" width="40%" nowrap>신고제목</th>    
	    <th scope="col" class="title" width="15%" nowrap>작성자</th>        
<!-- 				    <th scope="col" class="title" width="10%" nowrap>조회수</th>         -->
	    <th scope="col" class="title" width="15%" nowrap>신고일자</th>
	    <th scope="col" class="title" width="15%" nowrap>답변일자</th>                   
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
				<a href="#LINK" onclick="fn_egov_inquire_qnaanswerdetail('${resultInfo.errorReportId}', '${searchVO.pageIndex}'); return false;"><c:out value="${resultInfo.reportSj}"/></a>
			</td>
			<td class="lt_text3"><c:out value="${resultInfo.wrterNm}"/></td>
			<!--  <td class="lt_text3"><c:out value="${resultInfo.reportProcessSttusNm}"/></td>  -->
<%-- 						<td class="lt_text3"><c:out value="${resultInfo.inqireCo}"/></td>				 --%>
			<td class="lt_text3"><c:out value='${fn:substring(resultInfo.writngDe, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.writngDe, 4,6)}'/>-<c:out value='${fn:substring(resultInfo.writngDe, 6,8)}'/></td>
			<td class="lt_text3">
			<c:if test="${resultInfo.answerDe != null }">
				<c:out value='${fn:substring(resultInfo.answerDe, 0,4)}'/>-<c:out value='${fn:substring(resultInfo.answerDe, 4,6)}'/>-<c:out value='${fn:substring(resultInfo.answerDe, 6,8)}'/>
			</c:if>		
			</td>		
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
<%-- 					<button type="button" class="btn btn-primary" onclick="fn_egov_regist_qnacn(); return false;"><spring:message code="button.create" /></button>						 --%>
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
</form>

</div>	

<!-- /page-wrapper -->

<div id="layerPop" style="display:none; background:url(/images/clikmng/bg_layer.png) left top repeat; width:100%; height:100%; min-height:904px;
	 position:fixed; top:0; left:0; z-index:999999999999999999999999999999;">
		<div class="layerWrap " style="position:fixed; width:50%; left:25%; top:25%;">
			<div style="background: #ffffff; margin:20px auto; padding:60px;">
				<div style="height: 500px; overflow-y: scroll; overflow-x: hidden;">
					
					<table id="errMailRcvrChargerTable" width="98%" cellpadding="8" class="table table-striped table-bordered table-hover dataTable no-footer" border="0" >
						<thead>
						<tr>      
						    <th scope="col" class="title" width="40%" nowrap>이름(이메일주소)</th>    
						    <th scope="col" class="title" width="40%" nowrap>이름(이메일주소)</th>                   
						</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					
				</div>
				
				<div style="text-align:right;">
					<button onclick="return fnEmailAdd()">저장</button>
					<button onclick="return fnCloseLayerPopup();">취소</button>
				</div>
			</div>
			<a href="#none" onclick="return fnCloseLayerPopup();" style="position:absolute; right:0; top:0;"><img src="/images/clikmng/layerClosebtn1.gif" alt="닫기"></a>
		</div>
	</div>	
</body>					
</body>
</html>
