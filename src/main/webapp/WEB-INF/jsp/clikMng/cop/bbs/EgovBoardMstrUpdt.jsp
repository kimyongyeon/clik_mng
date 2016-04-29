<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovBoardMstrUpdt.jsp
  * @Description : 게시판 속성정보 변경화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  *  @author 
  *  @since 
  *  @version 
  *  @see
  *
  */
%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<script type="text/javascript" src="<c:url value="/js/clikmng/cop/bbs/EgovBBSMng.js" />" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="boardMaster" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_validateForm(obj){
		return true;
	}

	function fn_egov_update_brdMstr(){
		if (!validateBoardMaster(document.boardMaster)){
			return;
		}

		//----------------------------
		// 2009.06.26 : 2단계 기능 추가
		//----------------------------
		var theForm = document.boardMaster;
		if ('<c:out value="${result.bbsTyCode}"/>' == 'BBST04' &&
				(theForm.option.options[theForm.option.selectedIndex].value == 'comment' ||
				 theForm.option.options[theForm.option.selectedIndex].value == 'stsfdg')) {
			alert('방명록의 경우는 추가 선택사항을 지원하지 않습니다.');
			theForm.option.focus();
			return;
		}
		////--------------------------
		
		// 2011.11.11 : 첨부파일가능 선택 시 파일숫자를 선택하도록 함, 첨부파일가능 미선택시 파일 숫자를 없음으로 변경
		var list = document.getElementsByName('fileAtchPosblAt');
		var fileAtchPosblAt_value;
		for (var i=0; i < list.length; i++) {
			if(list[i].checked == true) {
				fileAtchPosblAt_value = list[i].value;
			}
		}
		
		if (fileAtchPosblAt_value == "Y" && document.boardMaster.posblAtchFileNumber.value == 0 ) {
			alert("첨부가능파일 숫자를 1개이상 선택하세요.");
			return;
		}
		
		if (fileAtchPosblAt_value == "N" && document.boardMaster.posblAtchFileNumber.value != 0 ) {
			document.boardMaster.posblAtchFileNumber.value = 0;
		}
		////--------------------------


		if(confirm('<spring:message code="common.update.msg" />')){
			document.boardMaster.action = "<c:url value='/cop/bbs/UpdateBBSMasterInf.do'/>";
			document.boardMaster.submit();
		}
	}

	function fn_egov_select_brdMstrList(){
		document.boardMaster.action = "<c:url value='/cop/bbs/SelectBBSMasterInfs.do'/>";
		document.boardMaster.submit();
	}

	function fn_egov_delete_brdMstr(){
		if(confirm('<spring:message code="common.delete.msg" />')){
			document.boardMaster.action = "<c:url value='/cop/bbs/DeleteBBSMasterInf.do'/>";
			document.boardMaster.submit();
		}
	}

	function fn_egov_inqire_tmplatInqire(){

		var url = "<c:out value='/cop/tpl/selectTemplateInfsPop.do?typeFlag=BBS' />";
		
		window.open(url, "p_tmplatInqire", "width=880px, height=380px;");
	}
</script>
<title>커뮤니티 게시판 상세보기 및 수정</title>
</head>
<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<jsp:include page="/cmm/top/top.do" />
<form:form commandName="boardMaster" name="boardMaster" action="${pageContext.request.contextPath}/cop/bbs/SelectBBSMasterInfs.do" method="post" >
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="bbsId" type="hidden" value="<c:out value='${result.bbsId}'/>" />
<input name="bbsTyCode" type="hidden" value="<c:out value='${result.bbsTyCode}'/>" />
<input name="bbsAttrbCode" type="hidden" value="<c:out value='${result.bbsAttrbCode}'/>" />
<input name="replyPosblAt" type="hidden" value="<c:out value='${result.replyPosblAt}'/>" />

<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">커뮤니티 게시판 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /row -->

	<DIV id="main" style="display:" class="">

		<DIV class="">
			<h2>
				커뮤니티 게시판 상세보기 및 수정
			</h2>
			<!-- /.panel-heading -->			
			<DIV id="border" class="">  

				<table width="100%" cellpadding="8" class="table-search" border="0">
				 <tr>
				  <td width="100%"class="title_left">
				  </td>
				 </tr>
				</table>
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover dataTable no-footer" summary="게시판명,게시판 소개,게시판 유형,게시판 속성,답장가능여부, ..   입니다">
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap >
				    	<label for="bbsNm">
				    		게시판명
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="80%" nowrap colspan="3">
				      <input title="게시판명입력" name="bbsNm" type="text" size="60" value='<c:out value="${result.bbsNm}"/>' maxlength="60" style="width:100%" class="form-control span1">
				      <br/><form:errors path="bbsNm" />
				    </td>
				  </tr>
			
				  <tr>
				    <th height="23" class="required_text" >
				    	<label for="bbsIntrcn">
				    		게시판 소개
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td colspan="3">
				      <textarea title="게시판소개입력" name="bbsIntrcn" class="textarea"  cols="75" rows="4"  style="width:100%"><c:out value="${result.bbsIntrcn}" escapeXml="true" /></textarea>
				      <form:errors path="bbsIntrcn" />
				    </td>
				  </tr>
			
				  <tr>
				    <th width="20%" height="23" class="" nowrap >게시판 유형</th>
				    <td width="30%" nowrap><c:out value="${result.bbsTyCodeNm}"/>
				    </td>
				    <th width="20%" height="23" class="" nowrap >게시판 속성</th>
				    <td width="30%" nowrap><c:out value="${result.bbsAttrbCodeNm}"/>
				    </td>
				  </tr>
				  <tr>
				    <th width="20%" height="23" class="" nowrap >답장가능여부</th>
				    <td>
				    	<c:choose>
				    		<c:when test="${result.replyPosblAt == 'Y'}">
				    			<spring:message code="button.possible" />
				    		</c:when>
				    		<c:otherwise>
				    			<spring:message code="button.impossible" />
				    		</c:otherwise>
				    	</c:choose>
				    </td>
				    <th width="20%" height="23" class="required_text" nowrap >
				    	<label for="fileAtchPosblAt">
				    		파일첨부가능여부
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="30%" nowrap>
				     	<spring:message code="button.possible" /> : <input type="radio" name="fileAtchPosblAt" class="radio2" value="Y" <c:if test="${result.fileAtchPosblAt == 'Y'}"> checked="checked"</c:if>>&nbsp;
				     	<spring:message code="button.impossible" /> : <input type="radio" name="fileAtchPosblAt" class="radio2" value="N" <c:if test="${result.fileAtchPosblAt == 'N'}"> checked="checked"</c:if>>
				     	<br/><form:errors path="fileAtchPosblAt" />
				    </td>
				  </tr>
			
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap >
				    	<label for="posblAtchFileNumber">
				    		첨부가능파일 숫자
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="새창">
				    </th>
				    <td width="30%" nowrap colspan="3">
				     	<select title="첨부가능파일 숫자선택" name="posblAtchFileNumber" class="">
				  		   <option selected value="0">없음</option>
				  		   <option value='1' <c:if test="${result.posblAtchFileNumber == '1'}">selected="selected"</c:if>>1개</option>
				  		   <option value='2' <c:if test="${result.posblAtchFileNumber == '2'}">selected="selected"</c:if>>2개</option>
				  		   <option value='3' <c:if test="${result.posblAtchFileNumber == '3'}">selected="selected"</c:if>>3개</option>
				  	   </select>
				  	   <br/><form:errors path="posblAtchFileNumber" />
				    </td>
				  </tr>
			
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap >
				    	<label for="tmplatNm">
				    		템플릿 정보
				    	</label>
				    	<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">
				    </th>
				    <td width="30%" nowrap colspan="3">
				     <input title="템플릿정보입력" name="tmplatNm" type="text" size="20" value="<c:out value="${result.tmplatNm}"/>"  maxlength="20" readonly class=" span1">
				     <input name="tmplatId" type="hidden" size="20" value='<c:out value="${result.tmplatId}"/>' >
				     &nbsp;<a href="#LINK" onclick="fn_egov_inqire_tmplatInqire(); return false;"><img src="<c:url value='/images/clikmng/cmm/icon/search.gif' />"
				     			width="15" height="15" align="middle" alt="새창"></a>
			         <br/><form:errors path="tmplatId" />
				    </td>
				  </tr>
					<!-- 2011.09.15 : 2단계 기능 추가 방법 변경  -->
					<c:if test="${useComment == 'true' || useSatisfaction == 'true'}">
					  <tr style="display:none;">
					    <th width="20%" height="23" class=""><label for="option">추가 선택사항</label></th>
					    <td width="30%" nowrap colspan="3" >
					     	<select name="option" class="form-control" <c:if test="${result.option != 'na'}">disabled="disabled"</c:if> title="추가선택사항">
									<option value='na' <c:if test="${result.option == 'na'}">selected="selected"</c:if>>---선택하세요--</option>
									<option value='' <c:if test="${result.option == ''}">selected="selected"</c:if>>사용안함</option>
								<c:if test="${useComment == 'true' }">
									<option value='comment' <c:if test="${result.option == 'comment'}">selected="selected"</c:if>>댓글</option>
								</c:if>
								<c:if test="${useSatisfaction == 'true' }">
									<option value='stsfdg' <c:if test="${result.option == 'stsfdg'}">selected="selected"</c:if>>만족도조사</option>
								</c:if>
					  	   </select>
					  	   ※ 추가 선택사항은 수정 불가 (미설정된 기존 게시판의 경우 처음 설정은 가능함)
					    </td>
					  </tr>
					</c:if>
					<!-- 2009.06.26 : 2단계 기능 추가 방법 변경 -->
					
					<!-- 게시판 정보 수정 추가 : 사용여부, 제공 URL START -->
					<tr>
					    <th width="20%" height="23" class=""><label for="option">사용여부</label></th>
					    <td width="30%" nowrap colspan="3" >
				     		<spring:message code="button.use" /> : <input type="radio" name="useAt" class="radio2" value="Y" <c:if test="${result.useAt == 'Y'}"> checked="checked"</c:if>>&nbsp;
				     		<spring:message code="button.notUsed" /> : <input type="radio" name="useAt" class="radio2" value="N" <c:if test="${result.useAt == 'N'}"> checked="checked"</c:if>>
				     		<br/><form:errors path="useAt" />
					    </td>
					</tr>
					
					<c:choose>
				  	<c:when test="${not empty result.provdUrl}">
					<tr>
					    <th width="20%" height="23" class=""><label for="option">제공 URL</label></th>
					    <td width="30%" nowrap colspan="3" >
							<a href="<c:out value="${result.provdUrl}" />" target="_new">
				    	   		<c:out value="${result.provdUrl}" />
							</a>
					    </td>
					</tr>
					</c:when>
				  	</c:choose>
					<!-- 게시판 정보 수정 추가 : 사용여부, 제공 URL END -->						
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td height="10"></td>
				  </tr>
				</table>

				<p class="tr">
					<button type="button" class="btn btn-default" onclick="fn_egov_select_brdMstrList(); return false;">목록</button>
					<button type="button" class="btn btn-success" onclick="javascript:fn_egov_update_brdMstr(); return false;">수정</button>
					<!-- <button type="button" class="btn btn-danger" onclick="javascript:fn_egov_delete_brdMstr(); return false;">삭제</button> -->
				</p>					

			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->							
	</DIV>		
	<!-- /.MAIN -->		
</DIV>	
</form:form>
</body>
</html>
