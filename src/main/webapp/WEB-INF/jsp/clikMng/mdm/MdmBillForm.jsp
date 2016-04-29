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
<title>지방의회 의안정보</title>
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
function getJrsdCmitId(RASMBLY_NUMPR) {
 	if( RASMBLY_NUMPR == "" ) {
		return false;
	}
	
	var RASMBLY_ID = $("#RASMBLY_ID option:selected").val();
	if( RASMBLY_ID == "" ) {
		alert("의회를 선택하십시오.");
		return false;
	}
	$.ajax({
		url:"/mdm/MdmJrsdCmitIdList.do",
		cache:false,
		type:"post",
		data:{"RASMBLY_ID":RASMBLY_ID,"RASMBLY_NUMPR":RASMBLY_NUMPR},
		success: function(data, status) {
			$("#JRSD_CMIT_ID").html(data);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}

function regist() {
	if( $("#RASMBLY_ID option:selected").val() == "" ) {
		alert("의회를 선택하십시오.");
		return false;
	}
	if( $("#RASMBLY_NUMPR option:selected").val() == "" ) {
		alert("대수를 선택하십시오.");
		return false;
	}
	if( $("#BI_SJ").val() == "" ) {
		alert("의안명을 입력하십시오.");
		return false;
	}
	if( $("#BI_NO").val() == "" ) {
		alert("의안번호를 입력하십시오.");
		return false;
	}
	if( $("#BI_KND_STDCD").val() == "" ) {
		alert("의안종류를 선택하십시오.");
		return false;
	}
	if( $("#RASMBLY_SESN").val() == "" ) {
		alert("회기를 입력하십시오.");
		return false;
	}
	
	// 파일 확장자 체크
	if(document.getElementById('_FILE_NM') != null){
	    var confirmExt;
	    var thumbext = document.getElementById('_FILE_NM').value; 
	    if(document.getElementById('_FILE_NM').value != "") {
	        confirmExt = fn_confirmExt(thumbext);
	        if(!confirmExt) {
	        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
	        	return;
	        }
	    }
	}
	
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

function list() {
	pgfrm.action = "/mdm/MdmBillList.do";
	document.pgfrm.submit();
}
</script>
</head>

<body>
<jsp:include page="/cmm/top/top.do" />
<div id="page-wrapper">
<form name="pgfrm" method="post" action="/mdm/MdmBillRegist.do" enctype="multipart/form-data">
<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
	<div class="row">
    	<div class="col-lg-12">
        	<h1 class="page-header">메타데이터 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
	<h2>메타데이터 등록 (지방의회 의안정보) </h2>	<!-- /.row -->
	<div class="row">
		<p class="tr">
			<a href="#;" onclick="javascript:history.back();"><button type="button" class="btn btn-default">목록</button></a>
			<a href="#;" onclick="return regist();"><button type="button" class="btn btn-success">등록</button></a>
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
								<th>의회/대수</th>
								<td colspan="3">
									<select name="RASMBLY_ID" id="RASMBLY_ID" aria-controls="dataTables-example" class=" input-sm" style="width:20%;">
										<option value="">의회선택</option>
										<option value="002001">서울특별시</option>
										<option value="031001">경기도</option>
										<option value="032001">인천광역시</option>
										<option value="033001">강원도</option>
										<option value="041001">충청남도</option>
										<option value="042001">대전광역시</option>
										<option value="043001">충청북도</option>
										<option value="044001">세종특별자치시</option>
										<option value="051001">부산광역시</option>
										<option value="052001">울산광역시</option>
										<option value="053001">대구광역시</option>
										<option value="054001">경상북도</option>
										<option value="055001">경상남도</option>
										<option value="061001">전라남도</option>
										<option value="062001">광주광역시</option>
										<option value="063001">전라북도</option>
										<option value="064001">제주특별자치도</option>
									</select>
									<select name="RASMBLY_NUMPR" id="RASMBLY_NUMPR" onchange="return getJrsdCmitId(this.value)" aria-controls="dataTables-example" class=" input-sm" style="width:15%;">
										<option value="">대수선택</option>
										<option value="10">10대</option>
										<option value="9">9대</option>
										<option value="8">8대</option>
										<option value="7">7대</option>
										<option value="6">6대</option>
										<option value="5">5대</option>
										<option value="4">4대</option>
										<option value="3">3대</option>
										<option value="2">2대</option>
										<option value="1">1대</option>
									</select>
								</td>
							</tr>
							<tr>	
								<th>의안명</th>
								<td colspan="3"><input name="BI_SJ" id="BI_SJ" class="form-control" placeholder=""  type="text" style="width:80%;" /></td>
							</tr>
							<tr>	
								<th>의안번호</th>
								<td>
									<input name="BI_NO" id="BI_NO" class="input-sm ip" placeholder=""  type="text" style="width:40%;" /> 
								</td>
								<th>의안종류</th>
								<td>
									<select name="BI_KND_STDCD" id="BI_KND_STDCD" aria-controls="dataTables-example" class=" input-sm" style="width:40%;">
										<option value="">의안종류 선택</option>
									    <c:forEach var="mCode" items="${mdmBiKndStdCd}" varStatus="status">
									    	<option value="${mCode.CODE}"> ${mCode.CODE_NM} </option>
									    </c:forEach>
									</select>
								</td>
							</tr>
							<tr>	
								<th>소관위원회</th>
								<td>
									<select name="JRSD_CMIT_ID" id="JRSD_CMIT_ID" aria-controls="dataTables-example" class=" input-sm" style="width:40%;">
										<option value="">위원회선택</option>
									</select>
								</td>
								<th>제안일</th>
								<td>
									<input name="ITNC_DE" id="ITNC_DE" class="input-sm ip" placeholder="" type="text" style="width:40%;" class="readOnlyClass" readonly title="제안일" />
									<a href="#" onclick="javascript:fn_egov_NormalCalendar(document.pgfrm, document.pgfrm.ITNC_DE);"><button type="button" class="btn btn-default">달력</button></a>
								</td>
							</tr>
							<tr>	
								<th>제안자</th>
								<td>
									<input name="PROPSR" id="PROPSR" class="input-sm ip" placeholder=""  type="text" style="width:40%;" />
								</td>
								<th>제안회기</th>
								<td>
									<input name="RASMBLY_SESN" id="RASMBLY_SESN" class="input-sm ip" placeholder=""  type="text" style="width:40%;" />
								</td>
							</tr>

							<tr>	
								<th>위원회<br />처리사항</th>
								<td>
									회부일 &nbsp;&nbsp;&nbsp;&nbsp;<input name="CMIT_REPORT_DE" id="CMIT_REPORT_DE" class="input-sm ip" placeholder="" type="text" style="width:40%;" class="readOnlyClass" readonly title="회부일" />
									<a href="#" onClick="javascript:fn_egov_NormalCalendar(document.pgfrm, document.pgfrm.CMIT_REPORT_DE);"><button type="button" class="btn btn-default">달력</button></a> <br/>
									상정일 &nbsp;&nbsp;&nbsp;&nbsp;<input name="CMIT_SBMISN_DE" id="CMIT_SBMISN_DE" class="input-sm ip" placeholder="" type="text" style="width:40%;" class="readOnlyClass" readonly title="상정일" /> 
									<a href="#" onClick="javascript:fn_egov_NormalCalendar(document.pgfrm, document.pgfrm.CMIT_SBMISN_DE);"><button type="button" class="btn btn-default">달력</button></a> <br/>
									의결일 &nbsp;&nbsp;&nbsp;&nbsp;<input name="CMIT_PROCESS_DE" id="CMIT_PROCESS_DE" class="input-sm ip" placeholder="" type="text" style="width:40%;" class="readOnlyClass" readonly title="의결일" /> 
									<a href="#" onClick="javascript:fn_egov_NormalCalendar(document.pgfrm, document.pgfrm.CMIT_PROCESS_DE);"><button type="button" class="btn btn-default">달력</button></a> <br/>
									처리결과&nbsp;
									<select name="CMIT_RESULT" id="CMIT_RESULT" aria-controls="dataTables-example" class=" input-sm" style="width:40%;">
										<option value="">처리결과 선택</option>
									    <c:forEach var="cmCode" items="${mdmCmitResult}" varStatus="status">
									    	<option value="${cmCode.CODE}"> ${cmCode.CODE_NM} </option>
									    </c:forEach>
									</select>
								</td>
								<th>본회의<br />처리사항</th>
								<td>
									보고일 &nbsp;&nbsp;&nbsp;&nbsp;<input name="PLNMT_REPORT_DE" id="PLNMT_REPORT_DE" class="input-sm ip" placeholder=""  type="text" style="width:40%;" class="readOnlyClass" readonly title="보고일" /> 
									<a href="#" onClick="javascript:fn_egov_NormalCalendar(document.pgfrm, document.pgfrm.PLNMT_REPORT_DE);"><button type="button" class="btn btn-default">달력</button></a> <br/>
									상정일 &nbsp;&nbsp;&nbsp;&nbsp;<input name="PLNMT_SBMISN_DE" id="PLNMT_SBMISN_DE" class="input-sm ip" placeholder=""  type="text" style="width:40%;" class="readOnlyClass" readonly title="상정일" /> 
									<a href="#" onClick="javascript:fn_egov_NormalCalendar(document.pgfrm, document.pgfrm.PLNMT_SBMISN_DE);"><button type="button" class="btn btn-default">달력</button></a> <br/>
									의결일 &nbsp;&nbsp;&nbsp;&nbsp;<input name="PLNMT_PROCESS_DE" id="PLNMT_PROCESS_DE" class="input-sm ip" placeholder=""  type="text" style="width:40%;" class="readOnlyClass" readonly title="의결일" /> 
									<a href="#" onClick="javascript:fn_egov_NormalCalendar(document.pgfrm, document.pgfrm.PLNMT_PROCESS_DE);"><button type="button" class="btn btn-default">달력</button></a>  <br/>
									처리결과&nbsp;
									<select name="PLNMT_RESULT" id="PLNMT_RESULT" aria-controls="dataTables-example" class=" input-sm" style="width:40%;">
										<option value="">처리결과 선택</option>
									    <c:forEach var="pmCode" items="${mdmPlnmtResult}" varStatus="status">
									    	<option value="${pmCode.CODE}"> ${pmCode.CODE_NM} </option>
									    </c:forEach>
									</select>
								</td>
							</tr>
								
							<tr>	
								<th>의안요지</th>
								<td colspan="3">
									<textarea name="BI_OUTLINE" cols="100" rows="10" class="ip" style="width:100%; height:100px;"></textarea>
								</td>
							</tr>
							<tr>	
								<th>원안파일</th>
								<td colspan="3">
									<input name="_FILE_NM" id="_FILE_NM" class="input-sm ip " placeholder="" value="찾아보기"  type="file" style="width:40%; float:left; margin-right:10px;">
								</td>
							</tr>

							<tr>	
								<th>발의의원(콤마로 구분)</th>
								<td colspan="3">
									<textarea name="PROPSR_CM" cols="100" rows="5" class="ip" style="width:100%; height:60px;"></textarea>
								</td>
							</tr>
							<tr>	
								<th>집행기관<br />이송일</th>
								<td>
									<input name="TRNSF_DE" id="TRNSF_DE" class="input-sm ip" placeholder=""  type="text" style="width:40%;" /> 
									<a href="#" onClick="javascript:fn_egov_NormalCalendar(document.pgfrm, document.pgfrm.TRNSF_DE);"><button type="button" class="btn btn-default">달력</button></a>
								</td>
								<th>공포번호</th>
								<td><input name="PRMLGT_NO" class="input-sm ip" placeholder=""  type="text" style="width:40%;" /> </td>
							</tr>
							<tr>	
								<th>공포일</th>
								<td>
									<input name="PRMLGT_DE" id="PRMLGT_DE" class="input-sm ip" placeholder=""  type="text" style="width:40%;" class="readOnlyClass" readonly title="공포일" /> 
									<a href="#" onclick="javascript:fn_egov_NormalCalendar(document.pgfrm, document.pgfrm.PRMLGT_DE);"><button type="button" class="btn btn-default">달력</button></a>
								</td>
								<th><!-- 철회일자 --></th>
								<td>
									<!-- <input class="input-sm ip" placeholder=""  type="text" style="width:40%;" /> 
									<button type="button" class="btn btn-default">달력</button> -->
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
					
			</div><!--//movebox-->
				<p class="tr">
					<a href="#;" onclick="javascript:history.back();"><button type="button" class="btn btn-default">목록</button></a>
					<a href="#;" onclick="return regist();"><button type="button" class="btn btn-success">등록</button></a>
				</p>
		</div>
		<!-- /.panel-body -->
	</div>
	
	<%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchVar.jsp" %>

</form>
</div>
</body>
</html>
