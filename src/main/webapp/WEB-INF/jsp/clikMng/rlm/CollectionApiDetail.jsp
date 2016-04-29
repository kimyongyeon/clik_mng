<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>지방의회 관리</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mngManage" staticJavascript="false" xhtml="true" cdata="false"/>
 
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
* 초기 데이터 입력
******************************************************** */
$( document ).ready(function() {
	
	// 기관유형 선택
	insttTyChange('<c:out value="${result.fInsttClCode}" />', 0);
	
	// 광역시군구 선택
	localChange('<c:out value="${result.insttClCode}" />', 0);
	
	// 소속 선택
	psitnChange('<c:out value="${result.brtcCode}" />', 0);
	
	<c:if test="${result.rasmbly_sttus_code == 'RAS002'}">
	// 기관유형 선택
	insttTyChangePrmt('<c:out value="${result.prmtFInsttClCode}" />', 0);
	
	// 광역시군구 선택
	localChangePrmt('<c:out value="${result.prmtInsttClCode}" />', 0);
	
	// 소속 선택
	psitnChangePrmt('<c:out value="${result.prmtBrtcCode}" />', 0);
	
	fn_chk_sttus_code('<c:out value="${result.rasmbly_sttus_code}" />');
	</c:if>
});



/******************************************
 * 목록
 ******************************************/

function fnListMng() {
	if(confirm("수정을 취소 하시겠습니까?")){
		var f = document.getElementById("detailForm");
	    f.action = "<c:url value='/rlm/CollectionApiList.do'/>";
	    f.submit();
	}
}

/******************************************
 * 수정
 ******************************************/

function fnEditMng() {
	if(confirm("수정 하시겠습니까?")){
	    var f = document.getElementById("detailForm");
	    if(f.rasmbly_id.value==""){
	    	alert("지방의회 ID는 필수 입력사항 입니다.");
	    	return false;
	    }
	    
	    if(f.password.value==""){
	    	alert("비밀번호는 필수 입력사항 입니다.");
	    	return false;
	    }
	    
	    if(f.rasmbly_site_url.value==""){
	    	alert("사이트 URL은 필수 입력사항 입니다.");
	    	return false;
	    }

	    if(f.rasmbly_site_mints_url.value==""){
	    	alert("사이트 내 회의록 URL은 필수 입력사항 입니다.");
	    	return false;
	    }

	    if(f.rasmbly_site_bill_url.value==""){
	    	alert("사이트 내 의안 URL은 필수 입력사항 입니다.");
	    	return false;
	    }

	    if(f.rasmbly_site_asemby_url.value==""){
	    	alert("사이트 내 의원 URL은 필수 입력사항 입니다.");
	    	return false;
	    }

	    if(f.server_ip.value==""){
	    	alert("SERVER IP는 필수 입력사항 입니다.");
	    	return false;
	    }
	    
	    if(f.rasmbly_sttus_code.value==""){
	    	alert("지방의회 상태는 필수 입력사항 입니다.");
	    	return false;
	    }
	    
	    if(f.isview.value==""){
	    	alert("게시여부는 필수 입력사항 입니다.");
	    	return false;
	    }
	    
	    f.action = "<c:url value='/rlm/CollectionApiRegistProc.do'/>";
	    f.submit();
	}
}

/******************************************
 * 삭제
 ******************************************/

function fnDelMng() {
	if(confirm("삭제 하시겠습니까?")){
		var f = document.getElementById("detailForm");
		f.action = "<c:url value='/rlm/CollectionApiDelete.do'/>";
		f.submit();
	}
}




/* ********************************************************
* multi selectbox 
******************************************************** */
//-------------------------------------------------------------------기관유형 선택
function insttTyChange(fInsttClCode, type) {
	var fInsttClCode = fInsttClCode;
	
	var insttClCode = '<c:out value="${result.insttClCode}" />';
	
	// 지역선택 셀렉트박스 초기화
	$("select[name='selInsttTy'] option").remove();
	$("#selInsttTy").append("<option>기관유형 선택</option>");	
	$("select[name='selRegion'] option").remove();
	$("#selRegion").append("<option>지역 선택</option>");
	$("select[name='selPsitn'] option").remove();
	$("#selPsitn").append("<option>소속 선택</option>");
	
 	$.ajax({
	   type: "POST",
	   url : "/uss/mng/selectAjaxInsttTy.do",
	   data : "fInsttClCode=" + fInsttClCode,
	   dataType:"json", 
	   async : false,
	   success: function(args) {
		   $.each(args, function() {
			   $("#selInsttTy").append("<option value='"+this.fInsttTyClCode+"'>"+this.fInsttTyClCodeNm+"</option>");
			   if(type == 0) {
				   $("#selInsttTy").val(insttClCode).attr("selected", "selected");
			   }
			});
	   }
 		,error:function(e) {
			alert(e.responseText);
	   }
	});
	
} 

//광역시도 선택
// type1 - 0:parameter, 1:본페이지 선택
function localChange(insttClCode, type1) {
	
	var insttClCode = insttClCode;
	var brtcCode = '<c:out value="${result.brtcCode}" />';
	
	// 셀렉트박스 초기화
	$("select[name='selRegion'] option").remove();
	$("#selRegion").append("<option>지역 선택</option>");
	$("select[name='selPsitn'] option").remove();
	$("#selPsitn").append("<option>소속 선택</option>");
   
   $.ajax({
	   type: "POST",
	   url : "/uss/mng/selectAjaxBrtc.do",
	   dataType:"json",
	   async : false,
	   success: function(args) {
		   $.each(args, function() {
			   $("#selRegion").append("<option value='"+this.codeId+"'>"+this.codeIdNm+"</option>");
			   if(type1 == 0) {
				   $("#selRegion").val(brtcCode).attr("selected", "selected");
			   }
			   
			});
	   }
   		,error:function(e) {
  			alert(e.responseText);
	   }
	});
	
} 

//소속선택
function psitnChange(brtcCode, type1) {
	
	var insttClCode;
	
	// 기관분류코드
	if(type1 == 0) {
		insttClCode = '<c:out value="${result.insttClCode}" />';	
	} else {
		insttClCode = $('[name="selInsttTy"]').val();
	}
	
	
   // 소속코드
	var psitnCode = '<c:out value="${result.loasmCode}" />';
	
	var formData = "brtcCode=" + brtcCode + "&insttClCode=" + insttClCode;
	
	$("#rasmbly_area_code").val(brtcCode);
	
	// 셀렉트박스 초기화
	$("select[name='selPsitn'] option").remove();
	$("#selPsitn").append("<option>소속 선택</option>");

	$.ajax({
	   type: "POST",
	   url : "/uss/mng/selectAjaxPsitn.do",
	   data : formData,
	   dataType:"json",
	   async : false,
	   success: function(args) {
		   $.each(args, function() {
			   $("#selPsitn").append("<option value='"+this.loasmCode+"'>"+this.loasmNm+"</option>");
			   if(type1 == 0) {
				   $("#selPsitn").val(psitnCode).attr("selected", "selected");
			   }
			});
	   }
		,error:function(e) {
			alert(e.responseText);
	   }
	});
	
} 


// value setting 
function setLocal(localId) {
	var localName =	$("#selPsitn option:selected").text();

	var formData = "rasmbly_id=" + localId;
	
	$.ajax({
		   type: "POST",
		   url : "/rlm/CollectionApiExist.do",
		   data : formData,
		   dataType:"json",
		   async : false,
		   success: function(result) {
				console.log('result', result);
				console.log((result == true));
				if(result)
				{
					$("#selPsitn :selected").attr('selected', false);
					$("#rasmbly_nm").val('');
					$("#rasmbly_id").val('');
					
					$("#txt_rasmbly_nm").html('');
					$("#txt_rasmbly_id").html('');
					
					alert('이미 등록되어진 지방의회입니다.');
				}
				else
				{
					$("#rasmbly_nm").val(localName);
					$("#rasmbly_id").val(localId);
					
					$("#txt_rasmbly_nm").html(localName);
					$("#txt_rasmbly_id").html(localId);
				}
				
		   }
			,error:function(e) {
				alert(e.responseText);
		   }
		});
}

/* ********************************************************
* 승격 지방의회 조회
******************************************************** */
//-------------------------------------------------------------------기관유형 선택
function insttTyChangePrmt(fInsttClCode, type) {
	
	var insttClCode = '<c:out value="${result.prmtInsttClCode}" />';
	
	// 지역선택 셀렉트박스 초기화
	$("select[name='selInsttTyPrmt'] option").remove();
	$("#selInsttTyPrmt").append("<option>기관유형 선택</option>");	
	$("select[name='selRegionPrmt'] option").remove();
	$("#selRegionPrmt").append("<option>지역 선택</option>");
	$("select[name='prmt_rasmbly_id'] option").remove();
	$("#prmt_rasmbly_id").append("<option>소속 선택</option>");
	
 	$.ajax({
	   type: "POST",
	   url : "/uss/mng/selectAjaxInsttTy.do",
	   data : "fInsttClCode=" + fInsttClCode,
	   dataType:"json", 
	   async : false,
	   success: function(args) {
		   $.each(args, function() {
			   $("#selInsttTyPrmt").append("<option value='"+this.fInsttTyClCode+"'>"+this.fInsttTyClCodeNm+"</option>");
			   if(type == 0) {
				   $("#selInsttTyPrmt").val(insttClCode).attr("selected", "selected");
			   }
			});
	   }
 		,error:function(e) {
			alert(e.responseText);
	   }
	});
	
} 

//광역시도 선택
// type1 - 0:parameter, 1:본페이지 선택
function localChangePrmt(insttClCode, type1) {
	
	var brtcCode = '<c:out value="${result.prmtBrtcCode}" />';
	
	// 셀렉트박스 초기화
	$("select[name='selRegionPrmt'] option").remove();
	$("#selRegionPrmt").append("<option>지역 선택</option>");
	$("select[name='selPsitnPrmt'] option").remove();
	$("#selPsitnPrmt").append("<option>소속 선택</option>");
   
   $.ajax({
	   type: "POST",
	   url : "/uss/mng/selectAjaxBrtc.do",
	   dataType:"json",
	   async : false,
	   success: function(args) {
		   $.each(args, function() {
			   $("#selRegionPrmt").append("<option value='"+this.codeId+"'>"+this.codeIdNm+"</option>");
			   if(type1 == 0) {
				   $("#selRegionPrmt").val(brtcCode).attr("selected", "selected");
			   }
			   
			});
	   }
   		,error:function(e) {
  			alert(e.responseText);
	   }
	});
	
} 

//소속선택
function psitnChangePrmt(brtcCode, type1) {
	
	var insttClCode;
	
	// 기관분류코드
	if(type1 == 0) {
		insttClCode = '<c:out value="${result.prmtInsttClCode}" />';	
	} else {
		insttClCode = $('[name="selInsttTyPrmt"]').val();
	}
	
	
   // 소속코드
	var psitnCode = '<c:out value="${result.prmtLoasmCode}" />';
	
	var formData = "brtcCode=" + brtcCode + "&insttClCode=" + insttClCode;
	
	// 셀렉트박스 초기화
	$("select[name='prmt_rasmbly_id'] option").remove();
	$("#prmt_rasmbly_id").append("<option>소속 선택</option>");

	$.ajax({
	   type: "POST",
	   url : "/uss/mng/selectAjaxPsitn.do",
	   data : formData,
	   dataType:"json",
	   async : false,
	   success: function(args) {
		   $.each(args, function() {
			   $("#prmt_rasmbly_id").append("<option value='"+this.loasmCode+"'>"+this.loasmNm+"</option>");
			   if(type1 == 0) {
				   $("#prmt_rasmbly_id").val(psitnCode).attr("selected", "selected");
			   }
			});
	   }
		,error:function(e) {
			alert(e.responseText);
	   }
	});
	
}

//지방의회 승격 출력
function fn_chk_sttus_code(value){
	if(value == "RAS002"){
		$('#rasmbly_prmt').show();
	}else{
		$('#rasmbly_prmt').hide();
	}
}
-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form id="detailForm" name="detailForm" method="post">
<input type="hidden" id="rasmbly_area_code" name="rasmbly_area_code" value="${result.rasmbly_area_code }" />

		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">지방의회 관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>지방의회 상세정보</h2>
				<!-- input type="hidden" name="rasmbly_id" value="${result.rasmbly_id }"/-->
				<input type="hidden" name="api_crtfc_key" value="${result.api_crtfc_key }"/>
				<input name="deleteKeyList" type="hidden" value="${result.rasmbly_id }"/>
				
				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="20%" />
						<col width="" />
					</colgroup>
					<tbody>
						<tr>
							<th>기관유형</th>
							<td>
								
								<select id="selInstt" name="selInstt" onChange="insttTyChange(this.value, 1);">
						    		<option >기관선택</option>
								    <option value="INTSTTCL_000006" <c:if test="${result.fInsttClCode == 'INTSTTCL_000006'}">selected="selected"</c:if>>지방정부</option>
								    <option value="INTSTTCL_000012" <c:if test="${result.fInsttClCode == 'INTSTTCL_000012'}">selected="selected"</c:if>>지방의회</option>
						    	</select>
						    	<select id="selInsttTy" name="selInsttTy" onChange="localChange(this.value, 1);">
						    		<option disabled="disabled" >기관유형 선택</option>
						    	</select>
						    	<select id="selRegion" name="selRegion" onchange="psitnChange(this.value, 1);">
						    		<option disabled="disabled" value="0">지역 선택</option>
						    	</select>
						    	<select id="selPsitn" name="selPsitn" onchange="setLocal(this.value);">
						    		<option disabled="disabled" value="0">소속 선택</option>
						    	</select>									
								
								
							</td>
						</tr>
						<tr>
							<th>지방의회 ID</th>
							<td>
								<span id="txt_rasmbly_id">${result.rasmbly_id }</span>
								<input type="hidden" class="ip input-sm" id="rasmbly_id" name="rasmbly_id" value="${result.rasmbly_id }"/>
							</td>
						</tr>
						<tr>
							<th>지방의회 명</th>
							<td>
								<span id="txt_rasmbly_nm">${result.rasmbly_nm }</span>
								<input type="hidden" class="ip input-sm" id="rasmbly_nm" name="rasmbly_nm" value="${result.rasmbly_nm }"/>
							</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td>
								<input type="text" class="ip input-sm" id="password" name="password" value="${result.password }"/>
							</td>
						</tr>
						<tr>
							<th>사이트 URL</th>
							<td>
								<input type="text" class="ip input-sm form-control" id="rasmbly_site_url" name="rasmbly_site_url" value="${result.rasmbly_site_url }"/>
							</td>
						</tr>
						<tr>
							<th>사이트 내 회의록 URL</th>
							<td>
								<input type="text" class="ip input-sm form-control" id="rasmbly_site_mints_url" name="rasmbly_site_mints_url" value="${result.rasmbly_site_mints_url }"/>
							</td>
						</tr>
						<tr>
							<th>사이트 내 의안 URL</th>
							<td>
								<input type="text" class="ip input-sm form-control" id="rasmbly_site_bill_url" name="rasmbly_site_bill_url" value="${result.rasmbly_site_bill_url }"/>
							</td>
						</tr>
						<tr>
							<th>사이트 내 의원 URL</th>
							<td>
								<input type="text" class="ip input-sm form-control" id="rasmbly_site_asemby_url" name="rasmbly_site_asemby_url" value="${result.rasmbly_site_asemby_url }"/>
							</td>
						</tr>
						<tr>
							<th>OpenApi 정보 제공 여부</th>
							<td>
								<input type="checkbox" id="mints_othbc_at" name="mints_othbc_at" value="Y" <c:if test="${result.mints_othbc_at == 'Y'}">checked="checked"</c:if> /> 회의록
								<br><input type="checkbox" id="bi_othbc_at" name="bi_othbc_at" value="Y" <c:if test="${result.bi_othbc_at == 'Y'}">checked="checked"</c:if> /> 의안
								<br><input type="checkbox" id="asemby_othbc_at" name="asemby_othbc_at" value="Y"  <c:if test="${result.asemby_othbc_at == 'Y'}">checked="checked"</c:if>/> 의원
							</td>
						</tr>
						<tr>
							<th>최초 등록일자</th>
							<td>
								${fn:substring(result.frst_regist_dt,0,4)}.${fn:substring(result.frst_regist_dt,4,6)}.${fn:substring(result.frst_regist_dt,6,8)}
							</td>
						</tr>
						<tr>
							<th>최종 수정일자</th>
							<td>
								${fn:substring(result.last_updt_dt,0,4)}.${fn:substring(result.last_updt_dt,4,6)}.${fn:substring(result.last_updt_dt,6,8)}
							</td>
						</tr>						
						<tr>
							<th>Server IP</th>
							<td>
								<input type="text" class="ip input-sm" id="server_ip" name="server_ip" value="${result.server_ip }"/>
							</td>
						</tr>
						<tr>
							<th>게시여부</th>
							<td>
								<select id="isview" name="isview">
									<option value="">-- 선택 --</option>
									<option value="Y" <c:if test="${result.isview == 'Y'}">selected</c:if> >게시</option>
									<option value="N" <c:if test="${result.isview == 'N'}">selected</c:if> >미게시</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>지방의회 상태</th>
							<td>
								<select id="rasmbly_sttus_code" name="rasmbly_sttus_code" onchange="fn_chk_sttus_code(this.value);">
									<option value="">-- 선택 --</option>
									<c:forEach items="${codeList}" var="item">
									<option value="${item.code}" <c:if test="${result.rasmbly_sttus_code == item.code}">selected</c:if> >${item.code_nm}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr id="rasmbly_prmt" style="display:none;">
							<th>승격 지방의회</th>
							<td>
								<select id="selInsttPrmt" name="selInsttPrmt" onChange="insttTyChangePrmt(this.value, 1);">
						    		<option >기관선택</option>
								    <option value="INTSTTCL_000006" <c:if test="${result.prmtFInsttClCode == 'INTSTTCL_000006'}">selected="selected"</c:if>>지방정부</option>
								    <option value="INTSTTCL_000012" <c:if test="${result.prmtFInsttClCode == 'INTSTTCL_000012'}">selected="selected"</c:if>>지방의회</option>
						    	</select>
						    	<select id="selInsttTyPrmt" name="selInsttTyPrmt" onChange="localChangePrmt(this.value, 1);">
						    		<option disabled="disabled" >기관유형 선택</option>
						    	</select>
						    	<select id="selRegionPrmt" name="selRegionPrmt" onchange="psitnChangePrmt(this.value, 1);">
						    		<option disabled="disabled" value="0">지역 선택</option>
						    	</select>
						    	<select id="prmt_rasmbly_id" name="prmt_rasmbly_id">
						    		<option disabled="disabled" value="0">소속 선택</option>
						    	</select>
							</td>
						</tr>
						<tr>
							<th>API Key</th>
							<td>
								${result.api_crtfc_key }
							</td>
						</tr>
					</tbody>
				</table>

				<p class="tr">
					<button type="button" class="btn btn-default" onclick="fnListMng();">목록</button>				
					<button type="button" class="btn btn-primary" onclick="fnEditMng();">수정</button>
					<button type="button" class="btn btn-danger" onclick="fnDelMng();">삭제</button>

				</p>
</form>
	</div><!--//page-wrapper-->
</body>
</html>

