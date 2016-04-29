<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovMenuCreat.jsp
  * @Description : 메뉴생성 화면
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
//  String imagePath_icon   = "/images/clikmng/mna/mcm/icon/";
//  String imagePath_button = "/images/clikmng/mna/mcm/button/";
%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>메뉴생성</title>
<script type="text/javascript">
var imgpath = "<c:url value='/images/clikmng/cmm/utl/'/>";
</script>
<script language="javascript1.2" type="text/javaScript" src="<c:url value='/js/clikmng/mna/mcm/EgovMenuCreat.js' />"></script>
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 조회 함수
 ******************************************************** */
function selectMenuCreatTmp() {
    document.menuCreatManageForm.action = "<c:url value='/mna/mcm/EgovMenuCreatSelect.do'/>";
    document.menuCreatManageForm.submit();
}

/* ********************************************************
 * 멀티입력 처리 함수
 ******************************************************** */
function fInsertMenuCreat() {
    var checkField = document.menuCreatManageForm.checkField;
    var checkMenuNos = "";
    var checkedCount = 0;
    if(checkField) {
    	if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkMenuNos += ((checkedCount==0? "" : ",") + checkField[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkMenuNos = checkField.value;
            }
        }
    }
    if(checkedCount == 0){
        alert("선택된 메뉴가 없습니다.");
        return false;
    }
    document.menuCreatManageForm.checkedMenuNoForInsert.value=checkMenuNos;
    document.menuCreatManageForm.checkedAuthorForInsert.value=document.menuCreatManageForm.authorCode.value;
    document.menuCreatManageForm.authorClCode.value='<c:out value="${resultVO.authorClCode}" />';
    document.menuCreatManageForm.action = "<c:url value='/mna/mcm/EgovMenuCreatInsert.do'/>";
    document.menuCreatManageForm.submit();
}
/* ********************************************************
 * 메뉴사이트맵 생성 화면 호출
 ******************************************************** */
function fMenuCreatSiteMap() {
	id = document.menuCreatManageForm.authorCode.value;
	window.open("<c:url value='/mna/mcm/EgovMenuCreatSiteMapSelect.do'/>?authorCode="+id,'Pop_SiteMap','scrollbars=yes, width=550, height=700');
}
-->
</script>

</head>
<body style="background-color:white;margin:0 0 0 0;padding:0 0 0 0;">
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
   	
	<div id="border" style="width:470px;" >
	<p class="tr">
		<button type="button" class="btn btn-primary" onclick="fInsertMenuCreat(); return false;">메뉴설정</button>
		<!-- <button type="button" class="btn btn-primary" onclick="fMenuCreatSiteMap(); return false;">사이트맵생성</button> -->	
	</p> 
		<DIV class="panel panel-default" style="border:1px solid #ccc; border-radius:5px; padding:10px;">
			<!-- /.panel-heading -->			
			<DIV id="border" class="panel-body" >
				<table border="0" width="100%">
				  <tr>
				    <td>
				<!-- ********** 여기서 부터 본문 내용 *************** -->
				
				<form name="menuCreatManageForm" action ="<c:url value='/mna/mcm/EgovMenuCreatSiteMapSelect.do' />" method="post">
				<input name="checkedMenuNoForInsert" type="hidden" >
				<input name="checkedAuthorForInsert"  type="hidden" >
				<input name="authorClCode"  type="hidden" >				
				
				<div id="main" style="display:width:450px;">
				
				<table cellpadding="8" class="table-search" border="0">
				 <tr>
				  <td width="40%" class="title_left"></td>
				  <td width="10%"></td>
				  <td width="25%"></td>
				  <th width="25%"></th>
				 </tr>
				</table>
				
				<table width="450px" border="0" cellpadding="0" cellspacing="1">
				 <tr>
				  <td width="100%">
				    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="table-register" summary="메뉴생성 검색조건" >
				      <tr>
				        <th width="15%" height="40" class=""  scope="row"><label for="authorCode">권한코드&nbsp;</label></th>
				        <td width="85%"><input name="authorCode" type="text" size="30"  maxlength="30" title="권한코드" value="${resultVO.authorCode}" readonly class="form-control span1" style="width:200px;"></td>
				      </tr>
				    </table>
				   </td>
				 </tr>
				</table>
				
				<table border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td height="10">
						<c:forEach var="result1" items="${list_menulist}" varStatus="status" >
						<input type="hidden" name="tmp_menuNmVal" value="${result1.menuNo}|${result1.upperMenuId}|${result1.menuNm}|${result1.progrmFileNm}|${result1.chkYeoBu}|">
						</c:forEach>
				    </td>
				  </tr>
				</table>
				<table  cellpadding="8" class="table-line" summary="메뉴일괄등록" >
				  <tr>
				    <td width='20'>&nbsp;</td>
				    <td>
				    <!-- div class="tree" style="position:absolute; left:24px; top:70px; width:179px; height:25px; z-index:10;" -->
				    <div class="tree" style="width:450px;">
						<script language="javascript" type="text/javaScript">
						    var chk_Object = true;
						    var chk_browse = "";
							if (eval(document.menuCreatManageForm.authorCode)=="[object]") chk_browse = "IE";
							if (eval(document.menuCreatManageForm.authorCode)=="[object NodeList]") chk_browse = "Fox";
							if (eval(document.menuCreatManageForm.authorCode)=="[object Collection]") chk_browse = "safai";
				
							var Tree = new Array;
							if(chk_browse=="IE"&&eval(document.menuCreatManageForm.tmp_menuNmVal)!="[object]"){
							   alert("메뉴 목록 데이타가 존재하지 않습니다.");
							   chk_Object = false;
							}
							if(chk_browse=="Fox"&&eval(document.menuCreatManageForm.tmp_menuNmVal)!="[object NodeList]"){
							   alert("메뉴 목록 데이타가 존재하지 않습니다.");
							   chk_Object = false;
							}
							if(chk_browse=="safai"&&eval(document.menuCreatManageForm.tmp_menuNmVal)!="[object Collection]"){
								   alert("메뉴 목록 데이타가 존재하지 않습니다.");
								   chk_Object = false;
							}
							if( chk_Object ){
								for (var j = 0; j < document.menuCreatManageForm.tmp_menuNmVal.length; j++) {
									Tree[j] = document.menuCreatManageForm.tmp_menuNmVal[j].value;
							    }
							    createTree(Tree);
				            }else{
				                alert("메뉴가 존재하지 않습니다. 메뉴 등록 후 사용하세요.");
				                window.close();
				            }
						</script>
					</div>
				    </td>
				    <td>
				
				    </td>
				  </tr>
				</table>
				
				</div>
				<input type="hidden" name="req_menuNo">
				</form>
				<!-- ********** 여기까지 내용 *************** -->
				</td>
				</tr>
				</table>
			</DIV>
			<!-- /panel body -->
		</DIV>
		<!-- /.panel panel-default -->					
	</DIV>		
	<!-- /.MAIN -->		
<!-- /page-wrapper -->	
</body>
</html>

