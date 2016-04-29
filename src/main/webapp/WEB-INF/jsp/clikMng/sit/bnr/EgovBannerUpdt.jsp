<%--
/**
 * @Class Name  : EgovBannerUpdt.jsp
 * @Description : EgovBannerUpdt.jsp
 * @Modification Information
 */
 --%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>홍보존 상세보기 및 수정</title>
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="banner" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectBannerList() {
    //var varFrom = document.getElementById("banner");
    //varFrom.action = "<c:url value='/sit/bnr/selectBannerList.do'/>";
    //varFrom.submit();       
    location.href = "<c:url value='/sit/bnr/selectBannerList.do'/>";
}

function fncBannerUpdate() {
    var varFrom = document.getElementById("banner");

	if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.action = "<c:url value='/sit/bnr/updtBanner.do'/>";

		if($('#bannerNm').val() == "") {
			alert('홍보존 명을 입력해 주세요.');
			return;
		}
		
		if($('#linkUrl').val() == "") {
			alert('링크 URL을 입력해 주세요.');
			return;
		}
		
		if(varFrom.bannerImage.value == '' && varFrom.file_1.value == '') {
			alert('홍보존 이미지를 입력해 주세요.');
			return;
		}
		
		if($('#bannerDc').val() == '') {
			alert('홍보존 설명을 입력해 주세요.');
			return;
		}
			
		var ntceBgndeYYYMMDD = document.getElementById('ntceBgndeYYYMMDD').value;
		var ntceEnddeYYYMMDD = document.getElementById('ntceEnddeYYYMMDD').value;

		var iChkBeginDe = ntceBgndeYYYMMDD.split("-").join("") * 1;
		var iChkEndDe = ntceEnddeYYYMMDD.split("-").join("") * 1;

		if(iChkBeginDe > iChkEndDe || iChkEndDe < iChkBeginDe ){
			alert("게시시작일자는 게시종료일자 보다 클수 없고,\n게시종료일자는 게시시작일자 보다 작을수 없습니다. ");
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
		
		varFrom.ntceBgnde.value = ntceBgndeYYYMMDD.split("-").join("") + fn_egov_SelectBoxValue('ntceBgndeHH') +  fn_egov_SelectBoxValue('ntceBgndeMM');
		varFrom.ntceEndde.value = ntceEnddeYYYMMDD.split("-").join("") + fn_egov_SelectBoxValue('ntceEnddeHH') +  fn_egov_SelectBoxValue('ntceEnddeMM');
		
		$('input[name="bannerNm"]').val(encodeURIComponent(htmlEntityEnc($('#bannerNm').val())));
		$('input[name="linkUrl"]').val(encodeURIComponent(htmlEntityEnc($('#linkUrl').val())));
		$('input[name="bannerDc"]').val(encodeURIComponent(htmlEntityEnc($('#bannerDc').val())));
		
		varFrom.submit();
	}	
}

/**
 * 파일 확장자 체크
 * 해당 확장자가 아닌 다른 확장자로 업로드 실행 시 필터
 */

function fn_confirmExt(str) {
	str = str.slice(str.lastIndexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.

	var result = true;

	if(str != "jpg" && str != "png" &&  str != "gif" &&  str != "bmp" && str != "jpeg"){ //확장자를 확인합니다.

		//alert('썸네일은 이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
		result = false;
		return result;

	} else {
		return result;
	}
}


function fncBannerDelete() {
    var varFrom = document.getElementById("banner");
    varFrom.action = "<c:url value='/sit/bnr/removeBanner.do'/>";
    if(confirm("삭제 하시겠습니까?")){
    	$('input[name="bannerNm"]').val(encodeURIComponent(htmlEntityEnc($('#bannerNm').val())));
		$('input[name="linkUrl"]').val(encodeURIComponent(htmlEntityEnc($('#linkUrl').val())));
		$('input[name="bannerDc"]').val(encodeURIComponent(htmlEntityEnc($('#bannerDc').val())));
    	varFrom.submit();
    }
}

/* ********************************************************
* SELECT BOX VALUE FUNCTION
******************************************************** */
function fn_egov_SelectBoxValue(sbName)
{
	var FValue = "";
	for(var i=0; i < document.getElementById(sbName).length; i++)
	{
		if(document.getElementById(sbName).options[i].selected == true){

			FValue=document.getElementById(sbName).options[i].value;
		}
	}

	return  FValue;
}


</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">홍보존 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->


			<DIV class="panel-heading">
				 홍보존 상세보기 및 수정
			</DIV>
			<!-- /.panel-heading -->			

				<table border="0" width="100%">
				  <tr>
				    <td>
				<form:form commandName="banner" method="post" action="${pageContext.request.contextPath}/sit/bnr/updtBanner.do" enctype="multipart/form-data">
				<form:hidden path="ntceBgnde" />
				<form:hidden path="ntceEndde" /> 				 
				<input type="hidden" name="posblAtchFileNumber" value="1"  >
				
				<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="홍보존정보를 수정하는 항목">
				  <tr>
				    <th class="required_text" width="20%" scope="row"><label for="bannerId">홍보존 ID</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap><input name="bannerId" id="bannerId" title="홍보존ID" type="text" value="<c:out value='${banner.bannerId}'/>" size="30" class="form-control input-sm" readonly></td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"><label for="bannerNm">홍보존 명</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap>
				    <input type="hidden" name="bannerNm" value=""/>
				    <input id="bannerNm" title="홍보존명" type="text" value="<c:out value='${banner.bannerNm}'/>" maxLength="55"  size="30" class="form-control input-sm">&nbsp;<form:errors path="bannerNm" /></td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"><label for="linkUrl">링크 URL</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap>
				    <input type="hidden" name="linkUrl" value=""/>
				    <input id="linkUrl" title="링크URL" type="text" value="<c:out value='${banner.linkUrl}'/>" maxLength="255" size="50" class="form-control input-sm">&nbsp;<form:errors path="linkUrl" /></td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"><label for="egovComFileUploader">홍보존 이미지</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap>
				        <input type="file" name="file_1" id="egovComFileUploader" title="홍보존이미지파일">&nbsp; <br/>
				        <input name="bannerImage" id="bannerImage" type="text" title="홍보존이미지" value="<c:out value="${banner.bannerImage}"/>" maxLength="30" size="30" class="readOnlyClass" readonly >
				    </td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"><label for="bannerDc">홍보존 설명</label><img src="<c:url value='/images/clikmng/cmm/icon/no_required.gif' />" width="15" height="15" alt="선택입력표시"></th>
				    <td class="lt_text" nowrap>
				    <input type="hidden" name="bannerDc" value=""/>
				    <input id="bannerDc" title="홍보존설명" type="text" value="<c:out value='${banner.bannerDc}'/>" maxLength="100" size="80" class="form-control input-sm" ></td>
				  </tr>
<%-- 				   
				  <tr>
				    <th class="required_text" width="20%" scope="row"><label for="bassImageUseAt">정렬순서</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap>
				      <select name="bassImageUseAt" id="bassImageUseAt" title="기본이미지 사용여부" class="form-control">
				          <option value="Y" <c:if test="${banner.bassImageUseAt == 'Y'}">selected</c:if> >사용</option>
				          <option value="N" <c:if test="${banner.bassImageUseAt == 'N'}">selected</c:if> >사용안함</option>
				      </select>				    	
				    </td>
				  </tr>  
 --%>
				  <tr>
				    <th width="20%" height="23" class="required_text" nowrap><label id="IdNtceEnddeHH">게시 기간</label><img src="/images/clikmng/cmm/icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>
				    <td width="80%">
				
					<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
				    <input type="text" name="ntceBgndeYYYMMDD" id="ntceBgndeYYYMMDD" size="10" maxlength="10" class="readOnlyClass" value="<c:out value="${fn:substring(banner.ntceBgnde, 0, 4)}"/>-<c:out value="${fn:substring(banner.ntceBgnde, 4, 6)}"/>-<c:out value="${fn:substring(banner.ntceBgnde, 6, 8)}"/>" readonly title="게시기간">
				    
				    <form:select path="ntceBgndeHH">
				        <form:options items="${ntceBgndeHH}" itemValue="code" itemLabel="codeNm"/>
				    </form:select>시
				    <form:select path="ntceBgndeMM">
				        <form:options items="${ntceBgndeMM}" itemValue="code" itemLabel="codeNm"/>
				    </form:select>분
				    &nbsp;&nbsp;~&nbsp;&nbsp;
				    <input type="text" name="ntceEnddeYYYMMDD" id="ntceEnddeYYYMMDD" size="10" maxlength="10" class="readOnlyClass" value="<c:out value="${fn:substring(banner.ntceEndde, 0, 4)}"/>-<c:out value="${fn:substring(banner.ntceEndde, 4, 6)}"/>-<c:out value="${fn:substring(banner.ntceEndde, 6, 8)}"/>" readonly title="게시기간">
				    
				    <form:select path="ntceEnddeHH">
				        <form:options items="${ntceEnddeHH}" itemValue="code" itemLabel="codeNm"/>
				    </form:select>시
				    <form:select path="ntceEnddeMM">
				        <form:options items="${ntceEnddeMM}" itemValue="code" itemLabel="codeNm"/>
				    </form:select>분
				    </td>
				  </tr>  
				  <tr>
				    <th class="required_text" width="20%" scope="row"><label for="reflctAt">반영여부</label><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
				    <td class="lt_text" nowrap>
				      <select name="reflctAt" id="reflctAt" title="반영여부" class="form-control">
				          <option value="Y" <c:if test="${banner.reflctAt == 'Y'}">selected</c:if> >사용</option>
				          <option value="N" <c:if test="${banner.reflctAt == 'N'}">selected</c:if> >사용안함</option>
				      </select>
				   </td> 
				  </tr>  
				  <tr>
				    <th class="required_text" width="20%" scope="row"><label for="regDate">등록일시</label><img src="<c:url value='/images/clikmng/cmm/icon/no_required.gif' />" width="15" height="15" alt="선택입력표시"></th>
				    <td class="lt_text" nowrap><input name="regDate" id="regDate" title="등록일시" type="text" value="<c:out value="${banner.regDate}"/>" maxLength="50" size="20" class="form-control input-sm" readonly></td>
				  </tr>
				</table>
				<script>setCal("ntceBgndeYYYMMDD","ntceEnddeYYYMMDD");</script>

				<!-- 검색조건 유지 -->
				<input type="hidden" name="searchCondition" value="<c:out value='${bannerVO.searchCondition}'/>" >
				<input type="hidden" name="searchKeyword" value="<c:out value='${bannerVO.searchKeyword}'/>" >
				<input type="hidden" name="pageIndex" value="<c:out value='${bannerVO.pageIndex}'/>" >
				</form:form>
				</td>
				</tr>
				</table>

		<p class="tr">
			<button type="button" class="btn btn-default" onclick="fncSelectBannerList(); return false;" ><spring:message code="button.list" /></button>		
			<button type="button" class="btn btn-success"  onclick="fncBannerUpdate(); return false;"><spring:message code="button.update" /></button>				
			<button type="button" class="btn btn-danger" onclick="fncBannerDelete(); return false;"><spring:message code="button.delete" /></button>															
			<!--
			<button type="button" class="btn btn-primary" onclick="fncBannerInsert(); return false;"><spring:message code="button.create" /></button>						
			-->
		</p>    			
</DIV>
<!-- /page-wrapper -->
</body>
</html>

