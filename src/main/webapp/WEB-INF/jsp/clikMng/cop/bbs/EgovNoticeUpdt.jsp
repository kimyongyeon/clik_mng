<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovNoticeUpdt.jsp
  * @Description : 게시물 수정 화면
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/clikmng/cmm/com.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/cmm/button.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='${brdMstrVO.tmplatCours}' />" rel="stylesheet" type="text/css">
<!-- 
<script type="text/javascript">
_editor_area = "nttCn";
_editor_url = "<c:url value='/html/egovframework/com/cmm/utl/htmlarea3.0/'/>";
</script>
<script type="text/javascript" src="<c:url value='/html/egovframework/com/cmm/utl/htmlarea3.0/htmlarea.js'/>"></script>
 -->
<!-- script type="text/javascript" src="<c:url value='/html/egovframework/com/cmm/utl/htmlarea/EgovWebEditor.js'/>" ></script-->
<!-- Naver.com. SmartEditor 적용  -->
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>


<script type="text/javascript" src="<c:url value='/js/clikmng/cop/bbs/EgovBBSMng.js' />"></script>

<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/showModalDialog.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="board" staticJavascript="false" xhtml="true" cdata="false"/>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript">
	function fn_egov_validateForm(obj){
		return true;
	}

	function fn_egov_regist_notice(){
		//document.board.onsubmit();

		var ntceBgnde = eval(document.getElementById("ntceBgnde").value);
		var ntceEndde = eval(document.getElementById("ntceEndde").value);


		if(ntceBgnde > ntceEndde){
			alert("게시기간 종료일이 시작일보다 빠릅니다.");
			return;
		}

		//smartEditor textarea 적용
		oEditors.getById["nttCn"].exec("UPDATE_CONTENTS_FIELD", []);		
		
		if (!validateBoard(document.board)){
			return;
		}
		
        // 파일 확장자 체크
        var confirmExt;
        var thumbext = document.getElementById('egovComFileUploader').value; 
         
        if(document.getElementById('egovComFileUploader').value != "") {
            confirmExt = fn_confirmExt(thumbext);
            if(!confirmExt) {
            	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
            	return;
            }
        }

		if (confirm('<spring:message code="common.update.msg" />')) {
			var nttCn = document.getElementById("nttCn").value;
			document.board.nttCn.value = encodeURIComponent(nttCn);
			var nttSj = document.getElementById("nttSj").value;
			document.board.nttSj.value = encodeURIComponent(nttSj);
			document.board.action = "<c:url value='/cop/bbs${prefix}/updateBoardArticle.do'/>";
			document.board.submit();
		}
	}

	/**
	 * 파일 확장자 체크
	 * 해당 확장자가 아닌 다른 확장자로 업로드 실행 시 필터
	 */

	function fn_confirmExt(str) {
		str = str.slice(str.lastIndexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.
	
		var result = true;
	
		if(str != "jpg" && str != "png" &&  str != "gif" &&  str != "bmp"
			&& str != "alz" && str != "doc" && str != "docx" && str != "hwp" && str != "jpeg" 
			&& str != "pdf" && str != "ppt" && str != "pptx" && str != "pptx" && str != "txt"
			&& str != "xls" && str != "xlsx" && str != "zip" ){ //확장자를 확인합니다.
	
			//alert('썸네일은 이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
			result = false;
			return result;
	
		} else {
			return result;
		}
	}
	
	function fn_egov_select_noticeList() {
		document.board.action = "<c:url value='/cop/bbs${prefix}/selectBoardList.do'/>";
		document.board.submit();
	}

	function fn_egov_check_file(flag) {
		if (flag=="Y") {
			document.getElementById('file_upload_posbl').style.display = "block";
			document.getElementById('file_upload_imposbl').style.display = "none";
		} else {
			document.getElementById('file_upload_posbl').style.display = "none";
			document.getElementById('file_upload_imposbl').style.display = "block";
		}
	}
	function makeFileAttachment(){
	<c:if test="${bdMstr.fileAtchPosblAt == 'Y'}">
	
		var existFileNum = document.board.fileListCnt.value;
		var maxFileNum = document.board.posblAtchFileNumber.value;
	
		if (existFileNum=="undefined" || existFileNum ==null) {
			existFileNum = 0;
		}
		if (maxFileNum=="undefined" || maxFileNum ==null) {
			maxFileNum = 0;
		}
		var uploadableFileNum = maxFileNum - existFileNum;
		if (uploadableFileNum<0) {
			uploadableFileNum = 0;
		}
		if (uploadableFileNum != 0) {
			fn_egov_check_file('Y');
			var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), uploadableFileNum );
			multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
		} else {
			fn_egov_check_file('N');
		}
	</c:if>	
	}
</script>
<style type="text/css">
.noStyle {background:ButtonFace; BORDER-TOP:0px; BORDER-bottom:0px; BORDER-left:0px; BORDER-right:0px;}
  .noStyle th{background:ButtonFace; padding-left:0px;padding-right:0px}
  .noStyle td{background:ButtonFace; padding-left:0px;padding-right:0px}
</style>
<title><c:out value='${bdMstr.bbsNm}'/> - 게시글 수정</title>
<link href="<c:url value='/css/clikmng/bootstrap.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/jquery-ui.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/sb-admin.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/clikmng/font-awesome.css' />" rel="stylesheet" type="text/css">


<style type="text/css">
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
</style>

</head>
<!-- body onload="javascript:editor_generate('nttCn');"-->
<!-- <body class="popup" onLoad="HTMLArea.init(); HTMLArea.onload = initEditor; document.board.nttSj.focus(); makeFileAttachment();"> -->
<body> 

<form:form commandName="board" name="board" method="post" enctype="multipart/form-data" >
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="returnUrl" value="<c:url value='/cop/bbs/forUpdateBoardArticle.do'/>"/>

<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" />
<input type="hidden" name="nttId" value="<c:out value='${result.nttId}'/>" />

<input type="hidden" name="bbsAttrbCode" value="<c:out value='${bdMstr.bbsAttrbCode}'/>" />
<input type="hidden" name="bbsTyCode" value="<c:out value='${bdMstr.bbsTyCode}'/>" />
<input type="hidden" name="replyPosblAt" value="<c:out value='${bdMstr.replyPosblAt}'/>" />
<input type="hidden" name="fileAtchPosblAt" value="<c:out value='${bdMstr.fileAtchPosblAt}'/>" />
<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${bdMstr.posblAtchFileNumber}'/>" />
<input type="hidden" name="posblAtchFileSize" value="<c:out value='${bdMstr.posblAtchFileSize}'/>" />
<input type="hidden" name="tmplatId" value="<c:out value='${bdMstr.tmplatId}'/>" />

<input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />

<c:if test="${anonymous != 'true'}">
<input type="hidden" name="ntcrNm" value="dummy">	<!-- validator 처리를 위해 지정 -->
<input type="hidden" name="password" value="dummy">	<!-- validator 처리를 위해 지정 -->
</c:if>

<c:if test="${bdMstr.bbsAttrbCode != 'BBSA01'}">
   <input id="ntceBgnde" name="ntceBgnde" type="hidden" value="10000101">
   <input id="ntceEndde" name="ntceEndde" type="hidden" value="99991231">
</c:if>


<h1><c:out value='${bdMstr.bbsNm}'/> - 게시글 수정</h1>

	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="table table-striped table-bordered table-hover">
	  <tr>
	    <th width="10%" height="23"  nowrap ><spring:message code="cop.nttSj" />
	    <img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
	    <td width="90%" nowrap colspan="3">
	      <input name="nttSj" id="nttSj" type="text" size="60" value='<c:out value="${result.nttSj}" />'  maxlength="60" title="제목수정">
	       <br/><form:errors path="nttSj" />
	    </td>
	  </tr>
	  <tr>
	    <th height="23"  ><spring:message code="cop.nttCn" />
	    <img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
	    <td colspan="3">
	     <table width="100%" border="0" cellpadding="0" cellspacing="0" class="noStyle">
	     <tr><td>
	      <%-- <textarea id="nttCn" name="nttCn" class="textarea" cols="75" rows="28"  style="width:550px;"><c:out value="${result.nttCn}" escapeXml="false" /></textarea> --%>
	      <textarea name="nttCn" id="nttCn" rows="28" cols="75" style="width:640px; height:412px; " title="내용입력" ><c:out value="${result.nttCn}" escapeXml="false" /></textarea>
      
	      <form:errors path="nttCn" />
	      </td></tr>
	     </table>
	    </td>
	  </tr>
	  <c:if test="${bdMstr.bbsAttrbCode == 'BBSA01'}">
		  <tr>
		    <th height="23" ><spring:message code="cop.noticeTerm" />
		    <img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
		    <td colspan="3">

		      <input id="ntceBgnde" name="ntceBgnde" type="hidden" value='<c:out value="${result.ntceBgnde}" />'>
		      <input name="ntceBgndeView" type="text" size="15"
		      	value="${fn:substring(result.ntceBgnde, 0, 4)}-${fn:substring(result.ntceBgnde, 4, 6)}-${fn:substring(result.ntceBgnde, 6, 8)}"  readOnly title="게시시작일자수정"
		      	onClick="javascript:fn_egov_NormalCalendar(document.board, document.board.ntceBgnde, document.board.ntceBgndeView);" >
		      <img src="<c:url value='/images/clikmng/cmm/icon/bu_icon_carlendar.gif' />"
		      	onClick="javascript:fn_egov_NormalCalendar(document.board, document.board.ntceBgnde, document.board.ntceBgndeView);"
			    width="15" height="15" alt="달력창팝업버튼이미지">
		      ~
		      <input id="ntceEndde" name="ntceEndde" type="hidden"  value='<c:out value="${result.ntceEndde}" />'>
		      <input name="ntceEnddeView" type="text" size="15"
		      	value="${fn:substring(result.ntceEndde, 0, 4)}-${fn:substring(result.ntceEndde, 4, 6)}-${fn:substring(result.ntceEndde, 6, 8)}"  readOnly title="게시종료일자수정"
		      	onClick="javascript:fn_egov_NormalCalendar(document.board, document.board.ntceEndde, document.board.ntceEnddeView);"  >
		      <img src="<c:url value='/images/clikmng/cmm/icon/bu_icon_carlendar.gif' />"
		      	onClick="javascript:fn_egov_NormalCalendar(document.board, document.board.ntceEndde, document.board.ntceEnddeView);"
			    width="15" height="15" alt="달력창팝업버튼이미지">
			     <br/><form:errors path="ntceBgndeView" />
				 <br/><form:errors path="ntceEnddeView" />
		    </td>
		  </tr>
	  </c:if>
	  <c:if test="${anonymous == 'true'}">
		  <tr>
		    <th height="23" ><spring:message code="cop.ntcrNm" />
		    <img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
		    <td colspan="3">
		      <input name="ntcrNm" type="text" size="20" value='<c:out value="${result.ntcrNm}" />'  maxlength="10" title="작성자이름">
		    </td>

		  </tr>
		  <tr>
		    <th height="23" ><spring:message code="cop.password" />
		    <img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
		    <td colspan="3">
		      <input name="password" type="password" size="20" value="" maxlength="20" title="비밀번호입력">
		    </td>
		  </tr>
	  </c:if>
	  <c:if test="${not empty result.atchFileId}">
		  <tr>
		    <th height="23"><spring:message code="cop.atchFileList" /></th>
		    <td colspan="6">
				<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${result.atchFileId}" />
				</c:import>
		    </td>
		  </tr>
	  </c:if>
	  <c:if test="${bdMstr.fileAtchPosblAt == 'Y'}">
	  	<c:if test="${result.atchFileId == ''}">
	  		<input type="hidden" name="fileListCnt" value="0" />
	  	</c:if>
		  <tr>
		    <th height="23"><spring:message code="cop.atchFile" /></th>
		    <td colspan="3">
		    <div id="file_upload_posbl">
	            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
				    <tr>
				        <td><input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명 입력"/></td>
				    </tr>
				    <tr>
				        <td>
				        	<div id="egovComFileList"></div>
				        </td>
				    </tr>
	   	        </table>
			</div>
			<div id="file_upload_imposbl"  style="display:none;" >
	            <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
				    <tr>
				        <td><spring:message code="common.imposbl.fileupload" /></td>
				    </tr>
	   	        </table>
			</div>
		  </tr>


	  </c:if>
	</table>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="10"></td>
	  </tr>
	</table>
	<div align="center">
	
	 <c:if test="${bdMstr.authFlag == 'Y'}">
	     <c:if test="${result.frstRegisterId == searchVO.frstRegisterId}">
			   <a href="#none" onclick="javascript:fn_egov_regist_notice();return false;"><img src="/images/clikmng/board/btn_modify.gif" alt="<spring:message code="button.update" />" /></a>
	      </c:if>
      </c:if>
      <!-- 
      <td><img src="<c:url value='/images/clikmng/cmm/btn/bu2_left.gif'/>" width="8" height="20" alt="버튼이미지"></td>
      <td background="<c:url value='/images/clikmng/cmm/btn/bu2_bg.gif'/>" class="text_left" nowrap>
      <a href="javascript:fn_egov_select_noticeList();"><spring:message code="button.list" /></a>
      </td>
      <td><img src="<c:url value='/images/clikmng/cmm/btn/bu2_right.gif'/>" width="8" height="20" alt="버튼이미지"></td>
      -->
		<a href="#none" onclick="javascript:fn_egov_select_noticeList();return false"><img src="/images/clikmng/board/btn_list.gif" alt="<spring:message code="button.list" />" /></a>
	  	 
	
	</div>
</div>
</form:form>

<!-- Naver.com SmartEditor -->
<script type="text/javascript">
var oEditors = [];

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "nttCn",
	sSkinURI: "/smarteditor/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["nttCn"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});
</script>
</body>
</html>
