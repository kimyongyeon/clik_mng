<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>지방의회 회의록</title>
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
		url:"/mdm/MdmMinutesMtgNmList.do",
		cache:false,
		type:"post",
		data:{"RASMBLY_ID":RASMBLY_ID,"RASMBLY_NUMPR":RASMBLY_NUMPR},
		success: function(data, status) {
			$("#MTGNM_ID").html(data);
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
	if( $("#SESN_SE_STDCD option:selected").val() == "" ) {
		alert("회기구분을 선택하십시오.");
		return false;
	}
	if( $("#MTGNM").val() == "" ) {
		alert("회의명을 입력하십시오.");
		return false;
	}
	if( $("#MINTS_ODR").val() == "" ) {
		alert("차수를 입력하십시오.");
		return false;
	}
	if( $("#MINTS_HTML").val() == "" ) {
		alert("회의록 본문을 입력하십시오.");
		return false;
	}
	
	// 파일 확장자 체크
    var confirmExt;
    var thumbext = document.getElementById('_FILE_NM').value; 
    if(document.getElementById('_FILE_NM').value != "") {
        confirmExt = fn_confirmExt(thumbext);
        if(!confirmExt) {
        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
        	return;
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
	pgfrm.action = "/mdm/MdmMinutesList.do";
	document.pgfrm.submit();
}
</script>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />

<div id="page-wrapper">
<form name="pgfrm" method="post" action="/mdm/MdmMinutesRegist.do" enctype="multipart/form-data">
<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
	<div class="row">
		<div class="col-lg-12">
        	<h1 class="page-header">메타데이터 관리</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>

	<h2>메타데이터 목록 (지방의회 회의록정보) </h2>
    <!-- /.row -->
    <div class="row">
    	<p class="tr">
    		<a href="#;" onclick="javascript:history.back();"><button type="button" class="btn btn-default">목록</button></a>
			<a href="#;" onclick="regist();"><button type="button" class="btn btn-success">등록</button></a>
		</p>
		
		 <!-- /.panel-heading -->
		 <div class="panel-body ">
		 	<div class="movebox">
		 		<div class="table-responsive" id="leftArea">
		 		<table class="table table-striped table-bordered ">
                <colgroup>
                <col width="10%" />
				<col width="20%" />
				<col width="10%" />
				<col width="20%" />
				<col width="10%" />
				<col width="30%" />
                </colgroup>
                <tbody>
                    <tr>
                        <th>의회</th>
                        <td>
 							<select name="RASMBLY_ID" id="RASMBLY_ID" aria-controls="dataTables-example" class=" input-sm" style="width:80%;">
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
                        </td>
						<th>대수</th>
                        <td>
                        	<select name="RASMBLY_NUMPR" id="RASMBLY_NUMPR" onchange="return getJrsdCmitId(this.value)" aria-controls="dataTables-example" class=" input-sm" style="width:80%;">
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
						<th>회의명</th>
                        <td>
	                        <input type="text" name="MTGNM" id="MTGNM"  size=50" />
                        </td>
                    </tr>
                    <tr>
                        <th>회수</th>
                        <td><input type="text" name="RASMBLY_SESN" id="RASMBLY_SESN" /></td>
						<th>차수</th>
                        <td><input type="text" name="MINTS_ODR" id="MINTS_ODR" size="5" /> 차</td>
						<th>차수명칭</th>
                        <td><input type="text" name="ODR_NM" id="ODR_NM" /></td>
                    </tr>
                     <tr>
                        <th>회의일자</th>
                        <td>
							<input name="MTG_DE" id="MTG_DE" class="input-sm ip" placeholder="" type="text" style="width:60%;" class="readOnlyClass" readonly title="제안일" />
							<a href="#" onClick="javascript:fn_egov_NormalCalendar(document.pgfrm, document.pgfrm.MTG_DE);"><button type="button" class="btn btn-default">달력</button></a>
                        </td>
						<th>회의시작시간</th>
                        <td><input type="text" name="BEGIN_TM" size="8" /></td>
						<th>회의종료시간</th>
                        <td><input type="text" name="END_TM" /></td>
                    </tr>
                    <tr>
                        <th>공개/비공개/임시</th>
                        <td>
                        	<select name="OTHBC_STDCD">
								<option value="OTH001">공개</option>
								<option value="OTH002">비공개</option>
								<option value="OTH003">임시</option>
                            </select>
                        </td>
						<th>회기구분</th>
                        <td>
                        	<select name="SESN_SE_STDCD">
								<option value="SES100">일반회기</option>
								<option value="SES200">사무감사/조사 회기</option>
                            </select>
                        </td>
						<th>폐회중여부</th>
                        <td><input type="checkbox" name="CLOSE_AT" value="Y" style="border:0" /> 폐회중</td>
                    </tr>
					<tr>
                        <th>원본파일</th>
                        <td colspan="5">
							<input name="_FILE_NM" id="_FILE_NM" class="input-sm ip " placeholder="" value="찾아보기"  type="file" style="width:40%; float:left; margin-right:10px;">
                        </td>
                    </tr>
					<tr>
                        <th>회의록본문</th>
                        <td colspan="5">
							<textarea name="MINTS_HTML" cols="100" rows="30" class="ip" style="width:90%; height:250px;"></textarea>
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
				<a href="#;" onclick="regist();"><button type="button" class="btn btn-success">등록</button></a>
				<!-- <button type="button" class="btn btn-danger">삭제</button> -->
			</p>

	</div>
	<!-- /.panel-body -->

	<%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchVar.jsp" %>

</form>
</div>
	
</body>
</html>
