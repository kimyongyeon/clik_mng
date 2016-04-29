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
<title>지방의원정보</title>
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
function list() {
	pgfrm.action = "/mdm/MdmAsmblyAsembyList.do";
	document.pgfrm.submit();
}
function setUpdate() {

	// 파일 확장자 체크
    var confirmExt;
    var thumbext = document.getElementById('_PHOTO_FILE_NM').value; 
    if(document.getElementById('_PHOTO_FILE_NM').value != "") {
        confirmExt = fn_confirmExt(thumbext);
        if(!confirmExt) {
        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
        	return;
        }
    }
    
	if( !confirm("의원정보를 수정하시겠습니까?") ) {
		return false;
	}
	document.pgfrm.action="/mdm/MdmAsmblyAsembyUpdate.do";
	document.pgfrm.submit();
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

function setDelete() {
	if( !confirm("의원정보를 삭제하시겠습니까?") ) {
		return false;
	}
	document.pgfrm.action="/mdm/MdmAsmblyAsembyDelete.do";
	document.pgfrm.submit();
}
function setEmail(val) {
	if( val == "" ) {
		return false;
	}
	if( val == "dw" ) {
		$("#email2").val("");
	}
	else {
		$("#email2").val(val);
	}
}
</script>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
<div id="page-wrapper">
<form name="pgfrm" method="post" enctype="multipart/form-data">
<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
	<div class="row">
		<div class="col-lg-12">
        	<h1 class="page-header">메타데이터 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
	<h2>메타데이터 목록 (지방의회 의원정보) </h2>
    <!-- /.row -->
    <div class="row">
    	<p class="tr">
    		<a href="#;" onclick="javascript:history.back();"><button type="button" class="btn btn-default">목록</button></a>
			<a href="#;" onclick="return setUpdate();"><button type="button" class="btn btn-success">수정</button></a>
			<a href="#;" onclick="return setDelete();"><button type="button" class="btn btn-danger">삭제</button></a>
		</p>
		 <!-- /.panel-heading -->
		 <div class="panel-body ">
		 	<div class="movebox">
		 		<div class="table-responsive" id="leftArea">
		 			<table class="table table-striped table-bordered ">
		 				<colgroup>
		 					<col width="10%" />
							<col width="40%"/>
							<col width="10%" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th>의원명/대수</th>
								<td>
									<input name="ASEMBY_ID" type="hidden" value="${asmblyAsembyView.ASEMBY_ID}" /> 
									<input name="RASMBLY_NUMPR" type="hidden" value="${asmblyAsembyView.RASMBLY_NUMPR}" /> 
									<input name="RASMBLY_ID" type="hidden" value="${asmblyAsembyView.RASMBLY_ID}" /> 
									<input name="RASMBLY_ASEMBY_ID" type="hidden" value="${asmblyAsembyView.RASMBLY_ASEMBY_ID}" /> 
									${asmblyAsembyView.ASEMBY_NM} / ${asmblyAsembyView.RASMBLY_NUMPR}대
								</td>
								<th>출생년도</th>
								<td>
									<input name="BRTHDY" id="BRTHDY" class="input-sm ip" placeholder="" type="text" style="width:40%;" value="${asmblyAsembyView.BRTHDY}" class="readOnlyClass" readonly title="출생년도" /> 
								</td>
							</tr>
							<tr>
								<th>의원사진</th>
								<td colspan="3">
									<input name="_PHOTO_FILE_NM" id="_PHOTO_FILE_NM" class="input-sm ip " placeholder="" value="찾아보기"  type="file" style="width:40%; float:left; margin-right:10px;">
	 								<%-- <c:if test="${asmblyAsembyView.PHOTO_FILE_PATH != ''}">
	 								<img src="${asmblyAsembyView.PHOTO_FILE_PATH}.jpg" with="80" height="80"></c:if> --%>
	 								<!-- <button type="button" class="btn btn-danger">삭제</button> -->
								</td>
							</tr>
							<tr>	
								<th>소속/정당</th>
								<td colspan="3">${asmblyAsembyView.RASMBLY_NM} / 
									<select name="PPRTY_CODE" aria-controls="dataTables-example" class=" input-sm" style="width:20%;">
										<option value="">정당선택</option>
										<c:forEach var="cList" items="${codePprtyList}" varStatus="status">
											<option value="${cList.PPRTY_CODE}" <c:if test="${cList.PPRTY_CODE == asmblyAsembyView.PPRTY_CODE}"> selected="selected"</c:if>>${cList.PPRTY_NM}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>	
								<th>지역구</th>
								<td colspan="3">
									<%-- <input name="EST_ID" value="${asmblyAsembyView.EST_NM}" class="form-control" placeholder="" type="text" style="width:80%;"> --%>
									<select name="EST_ID" id="EST_ID" aria-controls="dataTables-example" class=" input-sm" style="width:20%;">
										<option value="">지역구선택</option>
										<c:forEach var="cList" items="${codeEstbsList}" varStatus="status">
											<option value="${cList.EST_ID}" <c:if test="${cList.EST_ID == asmblyAsembyView.EST_ID}"> selected="selected"</c:if>>${cList.EST_NM}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>	
								<th>현 소속(직책)</th>
								<td colspan="3">
									<%-- <input name="AsembyOfCps" value="${asembyOfCps}" class="form-control" placeholder="" type="text" style="width:80%;"> --%>
									<select name="HT_SE_STDCD01" aria-controls="dataTables-example" class=" input-sm" style="width:16%;">
										<option value="">전반기선택</option>
										<option value="HTS001" <c:if test="${codeOfCps01.HT_SE_STDCD == 'HTS001'}"> selected="selected"</c:if>>전반기</option>
									</select>
									<select name="MTGNM_ID01" id="MTGNM_ID01" aria-controls="dataTables-example" class=" input-sm" style="width:30%;">
										<option value="">전반기 위원회선택</option>
										<c:forEach var="cList" items="${codeJrsdCmitIdList}" varStatus="status">
											<option value="${cList.MTGNM_ID}" <c:if test="${cList.MTGNM_ID == codeOfCps01.MTGNM_ID}"> selected="selected"</c:if>>${cList.MTGNM}</option>
										</c:forEach>
									</select>
									<select name="ASEMBY_OFCPS_STDCD01" aria-controls="dataTables-example" class=" input-sm" style="width:15%;">
										<option value="">전반기 직위선택</option>
										<option value="OPS001" <c:if test="${codeOfCps01.ASEMBY_OFCPS_STDCD == 'OPS001'}"> selected="selected"</c:if>>의장</option>
										<option value="OPS002" <c:if test="${codeOfCps01.ASEMBY_OFCPS_STDCD == 'OPS002'}"> selected="selected"</c:if>>부의장</option>
										<option value="OPS003" <c:if test="${codeOfCps01.ASEMBY_OFCPS_STDCD == 'OPS003'}"> selected="selected"</c:if>>위원장</option>
										<option value="OPS004" <c:if test="${codeOfCps01.ASEMBY_OFCPS_STDCD == 'OPS004'}"> selected="selected"</c:if>>부위원장</option>
										<option value="OPS005" <c:if test="${codeOfCps01.ASEMBY_OFCPS_STDCD == 'OPS005'}"> selected="selected"</c:if>>위원</option>
									</select>
									<select name="ACT_AT" aria-controls="dataTables-example" class=" input-sm" style="width:15%;">
										<option value="N">현/역대의원선택</option>
										<option value="Y" <c:if test="${asmblyAsembyView.ACT_AT == 'Y'}"> selected="selected"</c:if>>현의원</option>
										<option value="N" <c:if test="${asmblyAsembyView.ACT_AT == 'N'}"> selected="selected"</c:if>>역대의원</option>
									</select>
									<br />
									<select name="HT_SE_STDCD02" aria-controls="dataTables-example" class=" input-sm" style="width:16%;">
										<option value="">후반기선택</option>
										<option value="HTS002" <c:if test="${codeOfCps02.HT_SE_STDCD == 'HTS002'}"> selected="selected"</c:if>>후반기</option>
									</select>
									<select name="MTGNM_ID02" id="MTGNM_ID02" aria-controls="dataTables-example" class=" input-sm" style="width:30%;">
										<option value="">후반기 위원회선택</option>
										<c:forEach var="cList" items="${codeJrsdCmitIdList}" varStatus="status">
											<option value="${cList.MTGNM_ID}" <c:if test="${cList.MTGNM_ID == codeOfCps02.MTGNM_ID}"> selected="selected"</c:if>>${cList.MTGNM}</option>
										</c:forEach>
									</select>
									<select name="ASEMBY_OFCPS_STDCD02" aria-controls="dataTables-example" class=" input-sm" style="width:15%;">
										<option value="">후반기 직위선택</option>
										<option value="OPS001" <c:if test="${codeOfCps02.ASEMBY_OFCPS_STDCD == 'OPS001'}"> selected="selected"</c:if>>의장</option>
										<option value="OPS002" <c:if test="${codeOfCps02.ASEMBY_OFCPS_STDCD == 'OPS002'}"> selected="selected"</c:if>>부의장</option>
										<option value="OPS003" <c:if test="${codeOfCps02.ASEMBY_OFCPS_STDCD == 'OPS003'}"> selected="selected"</c:if>>위원장</option>
										<option value="OPS004" <c:if test="${codeOfCps02.ASEMBY_OFCPS_STDCD == 'OPS004'}"> selected="selected"</c:if>>부위원장</option>
										<option value="OPS005" <c:if test="${codeOfCps02.ASEMBY_OFCPS_STDCD == 'OPS005'}"> selected="selected"</c:if>>위원</option>
									</select>
								</td>
							</tr>
							<tr>	
								<th>주소</th>
								<td colspan="3">
									도로명주소 <input name="RDNMADR" class="input-sm ip" placeholder=""  type="text" style="width:40%;" value="${asmblyAsembyView.RDNMADR}"><!--   <button type="button" class="btn btn-default">도로명주소 찾기</button> --> <br/>
									상세주소 &nbsp;&nbsp;&nbsp;&nbsp;<input name="ADRES" class="input-sm ip" placeholder=""  type="text" style="width:80%;" value="${asmblyAsembyView.ADRES}">
									<!-- 우편번호 &nbsp;&nbsp;&nbsp;&nbsp;<input name="ADRESDETAIL" class="input-sm ip" placeholder=""  type="text" style="width:40%;"> -->
								</td>
							</tr>
							<tr>	
								<th>사무실전화</th>
								<td><input name="OFFM_TLPHON" class="input-sm ip" placeholder=""  type="text" style="width:40%;" value="${asmblyAsembyView.OFFM_TLPHON}" /> </td>
								<th>사무실팩스</th>
								<td>
									<input name="FAX" class="input-sm ip" placeholder=""  type="text" style="width:40%;" value="${asmblyAsembyView.FAX}" /> 
								</td>
							</tr>
							<tr>	
								<th>휴대전화</th>
								<td>
									<c:set var="mbtlnum" value="${fn:split(asmblyAsembyView.MBTLNUM,'-')}" />
									<select name="phone1" aria-controls="dataTables-example" class=" input-sm" style="width:18%;" >
										<option value="010" <c:if test="${mbtlnum[0] == '010'}"> selected="selected"</c:if>>010</option>
										<option value="011" <c:if test="${mbtlnum[0] == '011'}"> selected="selected"</c:if>>011</option>
										<option value="016" <c:if test="${mbtlnum[0] == '016'}"> selected="selected"</c:if>>016</option>
										<option value="017" <c:if test="${mbtlnum[0] == '017'}"> selected="selected"</c:if>>017</option>
										<option value="018" <c:if test="${mbtlnum[0] == '018'}"> selected="selected"</c:if>>018</option>
										<option value="019" <c:if test="${mbtlnum[0] == '019'}"> selected="selected"</c:if>>019</option>
									</select>
									<input type="text" class="input-sm ip" name="phone2" id="phone2" value="${mbtlnum[1]}" onkeyup="goHPCheck(this)" title="휴대폰 중간자리 입력창" maxlength="4" style="width:15%;" /> - 
									<input type="text" class="input-sm ip" name="phone3" id="phone3" value="${mbtlnum[2]}" onkeyup="goHPCheck(this)" title="휴대폰 끝자리 입력창" maxlength="4" style="width:15%;" />
								</td>
								<th>이메일</th>
								<td>
									<c:set var="email" value="${fn:split(asmblyAsembyView.EMAIL,'@')}" />
									<input name="email1" id="email1" value="${email[0]}" class="input-sm ip" placeholder=""  type="text" style="width:20%;"> @ <input name="email2" id="email2" value="${email[1]}" class="input-sm ip" placeholder="" type="text" style="width:20%;">
									<select name="email3" id="email3" onchange="return setEmail(this.value);" aria-controls="dataTables-example" class=" input-sm" style="width:40%;">
										<option value="">이메일주소 선택</option>
										<option value="dw">직접입력</option>
										<option value="naver.com">naver.com</option>
										<option value="hanmail.net">hanmail.net</option>
										<option value="nate.com">nate.com</option>
										<option value="hotmail.com">hotmail.com</option>
										<option value="msn.com">msn.com</option>
										<option value="live.co.kr">live.co.kr</option>
										<option value="gmail.com">gmail.com</option>
										<option value="yahoo.co.kr">yahoo.co.kr</option>
										<option value="paran.com">paran.com</option>
										<option value="freechal.com">freechal.com</option>
										<option value="lycos.co.kr">lycos.co.kr</option>
										<option value="korea.com">korea.com</option>
									</select>
								</td>
							</tr>
							<tr>	
								<th>트위터</th>
								<td><input name="TWITTER" class="input-sm ip" placeholder=""  type="text" style="width:80%;" value="${asmblyAsembyView.TWITTER}" /> </td>
								<th>페이스북</th>
								<td>
									<input name="FACEBOOK" class="input-sm ip" placeholder=""  type="text" style="width:86%;" value="${asmblyAsembyView.FACEBOOK}" /> 
								</td>
							</tr>
							<tr>	
								<th>홈페이지</th>
								<td colspan="3"><input name="HMPG" class="form-control" placeholder=""  type="text" style="width:80%;" value="${asmblyAsembyView.HMPG}" /></td>
							</tr>
							<tr>	
								<th>학력</th>
								<td colspan="3">
									<textarea name="ACDMCR_MATTER" cols="100" rows="10" class="ip" style="width:100%; height:100px;">${asmblyAsembyView.ACDMCR_MATTER}</textarea>
								</td>
							</tr>
							<tr>	
								<th>경력</th>
								<td colspan="3">
									<textarea name="CAREER_MATTER" cols="100" rows="10" class="ip" style="width:100%; height:100px;">${asmblyAsembyView.CAREER_MATTER}</textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div><!--//cntArea-->
		</div><!--//movebox-->
			<p class="tr">
				<a href="#;" onclick="javascript:history.back();"><button type="button" class="btn btn-default">목록</button></a>
				<a href="#;" onclick="return setUpdate();"><button type="button" class="btn btn-success">수정</button></a>
				<a href="#;" onclick="return setDelete();"><button type="button" class="btn btn-danger">삭제</button></a>
			</p>
	</div>
	<!-- /.panel-body -->

	<%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchVar.jsp" %>

</form>
</div>
</body>
</html>
