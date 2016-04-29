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
<title>표준연계API 파일동기화</title>

<style>
.content_url {
overflow: hidden; 
white-space: nowrap; 
text-overflow: ellipsis; 
padding-right: 10px; 
display: block; 
word-wrap: normal;
}
</style>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
   
//로딩 이미지 ajax 이벤트
$(document).on({
	ajaxStart: function() { $(".spinner").show(); },
    ajaxStop: function() { $(".spinner").hide(); }    
});


/* ********************************************************
* 초기 데이터 입력
******************************************************** */
// 광역시군구 셀렉트 박스 선택
$( document ).ready(function() {
	
	<c:if test="${cfmCompareResultVO.insttClCode ne null}">
	
		localChange('<c:out value="${cfmCompareResultVO.insttClCode}" />', 0);
		rasmblyChange('<c:out value="${cfmCompareResultVO.brtcCode}" />', 0);	
		
	</c:if>

	
	$("input:radio[name=api_code]").click(function(){
		$("input:radio[name=api_code]:checked").each(function() {
			var text = $(this).attr("text");
			$("#api_nm").val(text);
		});
	});
	
});


/* ********************************************************
* multi selectbox 
******************************************************** */
//광역시도 선택
function localChange(insttClCode, type) {

	//var insttClCode = $("select[name='selRasmbly'] option:selected").val();	
	var insttClCode = insttClCode;
	var brtcCode = '<c:out value="${cfmCompareResultVO.brtcCode}" />';
	
	// 지역선택 셀렉트박스 초기화
	$("select[name='selRegion'] option").remove();
	$("#selRegion").append("<option>지역 선택</option>");
	$("select[name='rasmbly_id'] option").remove();
	$("#rasmbly_id").append("<option>지방의회 선택</option>");
   
   $.ajax({
	   type: "POST",
	   url : "/sit/spc/selectAjaxBrtc.do",
	   data : "insttClCode=" + insttClCode,
	   dataType:"json", 
	   success: function(args) {
		   $.each(args, function() {
			   $("#selRegion").append("<option value='"+this.codeId+"'>"+this.codeIdNm+"</option>");
			   if(type == 0) {
				   $("#selRegion").val(brtcCode).attr("selected", "selected");
			   }
			   
			});
	   }
   		,error:function(e) {
  			alert(e.responseText);
	   }
	});
	
} 


//지방의회 셀렉트 박스
function rasmblyChange(brtcCode, type) {
	
	// 기관분류코드
	var insttClCode = $("select[name='selRasmbly'] option:selected").val();
	
	// 광역시도코드
    var brtcCode = brtcCode;
	
	// 각 의회 코드
    var loasmCode = '<c:out value="${cfmCompareResultVO.loasmCode}" />';
	
	// 지역선택 셀렉트박스 초기화
	$("select[name='rasmbly_id'] option").remove();
	$("#rasmbly_id").append("<option value=0>지방의회 선택</option>");
   
	var formData = "brtcCode=" + brtcCode + "&insttClCode=" + insttClCode;  
	
   $.ajax({
	   type: "POST",
	   url : "/sit/spc/AjaxAsmbly.do",
	   data : formData,
	   dataType:"json", 
	   success: function(args) {
		   $.each(args, function() {
			   $("#rasmbly_id").append("<option value='"+this.loasmCode+"'>"+this.loasmNm+"</option>");
			   if(type == 0) {
				   $("#rasmbly_id").val(loasmCode).attr("selected", "selected");
			   }			   
			});
	   }
   		,error:function(e) {
  			alert(e.responseText);
	   }
	});
}

//지방의회 셀렉트 박스
function rasmblySelect(rasmbly) {
	$("#rasmbly_nm").val($("#rasmbly_id option:selected").text());
	
}


/* ********************************************************
* 수집파일비교실행
******************************************************** */
function fnCompareCollectionFile() {
	
	//alert($("#rasmbly_id option:selected").val());
	if($("#rasmbly_id option:selected").val() == "0") {
		alert('지방의회를 선택해 주세요.');
		return; 
	}
	
	var api_code = $("input:radio[name=api_code]:checked");
	if(api_code.length == 0){
		alert('자료유형을 선택해 주세요.');
		return;
	}


	var varForm = document.listForm;
	$("#compareYn").val("true");
	
	//로딩이미지
	$(".spinner").show();
	
	varForm.submit();
	return true;
}
/*
$('#form').submit(function() {
    $('#wait').show();
    $.post('/whatever.php', function() {
        $('#wait').hide();
    });
    return false;
});
*/
/* ********************************************************
* 레리어 팝업 열기
******************************************************** */
function openLayerPopup(procNo){
	
	$(".popup-wrap").css("display", "");
	
	$("#tbCompareList > tbody > tr").remove();
	
	$('#procNo').val(procNo);

	if(procNo == 1) {
		
		$("#popupTitle").text("자료는 있으나 파일이 없는 경우");
		$('#top_btn_1').show();
		$('#top_btn_2').hide();
		$('#bottom_btn_1').show();
		$('#bottom_btn_2').hide();
		
	} else if(procNo == 2) {
		
		$("#popupTitle").text("자료는 없는데 파일이 있는 경우");
		$('#top_btn_1').hide();
		$('#top_btn_2').show();
		$('#bottom_btn_1').hide();
		$('#bottom_btn_2').show();
		
	}
	
   	$.ajax({
	   	type: "POST",
	   	url : "/col/cfm/CfmPopupCompareList.do",
	   	data : "procNo=" + procNo,
	   	dataType:"json", 
	   	success: function(args) {
	   		var no = 0;
	   		var row = "";
	   		var cn;
		   	$.each(args, function() {
		   		cn = this.api_key != null ? this.api_key.split("^^") : ""
			   	row = "";
		   		row += "<tr>";
			   		row += "<td>";
			   		row += ++no;
			   		row += "</td>";
			   		
			   		row += "<td>";
			   		row += "<input type='checkbox' name='checkedRows' value='" + this.file_path + "' />";
			   		row += "</td>";
			   		
			   		row += "<td style='display:none;'>";
			   		row += this.api_key != null ? this.api_key : "";
			   		row += "</td>";
			   		
			   		row += "<td>";
			   		row += cn != "" ? cn[cn.length-1] : "";
			   		row += "</td>";
			   		
			   		row += "<td>";
			   		row += this.file_url != null ? "<div class='content_url' style='width:200px;' title='" + this.file_url + "'>" + this.file_url + "</div>" : "";
			   		row += "</td>";
			   		
			   		row += "<td>";
			   		row += "<div class='content_url' style='width: 410px;' title='" + this.file_path + "'>";
			   		row += this.file_path != null ? this.file_path : "";
			   		row += "</div>";
			   		row += "</td>";
		   		row += "</tr>";
		   		
		   		$("#tbCompareList > tbody:last").append(row);

			});
	   	}
   		,error:function(e) {
  			alert(e.responseText);
	   	}
	});	
}


/* ********************************************************
* 레이어 팝업 닫기
******************************************************** */
function closeLayerPopup(){
	$(".popup-wrap").css("display", "none");
}


/* ********************************************************
* 체크박스 전체선택
******************************************************** */
function fnCheckAll() {
    var checkField = document.popupForm.checkedRows;
    if(document.popupForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}



/* ********************************************************
* 파일삭제
******************************************************** */
function deleteCompareListFile(isAll){
	
	var msg = "";
	
	if(isAll){
		msg = "전체 항목을 삭제 하시겠습니까?";
	}else{
		msg = "선택한 항목을 삭제 하시겠습니까?";
	}
	
	if(confirm(msg)){
		
		//var checkedRows = document.popupForm.checkedRows;
		
		var serializeData = "";
		//$("#results").text(serializeData);
		
		if($('input[name="checkedRows"]:checked').length == 0 && !isAll)
		{
			alert("삭제할 할목이 존재하지 않습니다.");
			return false;
		}
		else
		{
			if(isAll){
				$('input[name="checkedRows"]').each(function(index,target){
					serializeData += "&checkedRows=" + target.value;
				});
			}else{
				serializeData = $("#popupForm").serialize();
			}
			
			$.ajax({
			   	type : "POST",
			   	url : "/col/cfm/CfmDeleteCompareListFile.do",
			   	data : serializeData,
			   	dataType : "json", 
			   	success : function(args) {
			   		
			   		//alert(args.deletedFileCount);
			   		if(confirm(args[0].deletedFileCount + "개의 파일을 삭제하였습니다. 수집파일비교를 다시 하시겠습니까?")){
			   			
			   			closeLayerPopup();
			   			
			   			//수집파일비교실행
			   			fnCompareCollectionFile();
			   		}
			   	}
		   		,error:function(e) {
		  			alert(e.responseText);
			   	}
			});
		}
	} 
}//파일삭제 E	

/* ********************************************************
* 엑셀 다운로드
******************************************************** */
function fnExcel() {
	var varForm = document.popupForm;
	varForm.action = "<c:url value='/col/cfm/CfmListExcelDownload.do' />";
	varForm.submit();
}

/* ********************************************************
* 표운연계API 재수집
******************************************************** */
function fnStdCntcApiReclect(isAll){
	var msg = "";
	
	if(isAll){
		msg = "전체 항목을 재수집 요청 하시겠습니까?";
	}else{
		msg = "선택한 항목을 재수집 요청 하시겠습니까?";
	}
	
	if(confirm(msg)){

		var serializeData = "";
		
		if($('input[name="checkedRows"]:checked').length == 0 && !isAll)
		{
			alert("재수집 요청 할목이 존재하지 않습니다. 선택 후 다시 요청해 주세요.");
			return false;
		}
		else
		{
			var dta_se_code = "";
			<c:if test="${cfmCompareResultVO.api_code eq 'RES301'}">
			dta_se_code = "TAR101";
			</c:if>
			<c:if test="${cfmCompareResultVO.api_code eq 'RES305'}">
			dta_se_code = "TAR101";
			</c:if>
			<c:if test="${cfmCompareResultVO.api_code eq 'RES403'}">
			dta_se_code = "TAR102";
			</c:if>
			<c:if test="${cfmCompareResultVO.api_code eq 'RES203'}">
			dta_se_code = "TAR103";
			</c:if>
			
			var setObj = new Set();
			var tempCn = "";
			var cn = "";
			if(!isAll){
				$('input[name="checkedRows"]:checked').each(function(index,target){
					tempCn = "";
					tempCn = $(target).parent().next().text();
					
					if(dta_se_code != "TAR101"){
						cn = tempCn;
					}else{
						cn = tempCn.split("^^")[0];
						cn += "#" + tempCn.split("^^")[1];
						cn += "#" + tempCn.split("^^")[2];
						cn += "#" + tempCn.split("^^")[3];
						cn += "#" + tempCn.split("^^")[4];
						cn += "#" + tempCn.split("^^")[5];
						cn += "#" + tempCn.split("^^")[6];
						cn += "#" + tempCn.split("^^")[7];
					}
					
					setObj.add(cn + "@${cfmCompareResultVO.rasmbly_id}");
				});
			}else{
				$('input[name="checkedRows"]').each(function(index,target){
					tempCn = "";
					tempCn = $(target).parent().next().text();
					
					if(dta_se_code != "TAR101"){
						cn = tempCn;
					}else{
						cn = tempCn.split("^^")[0];
						cn += "#" + tempCn.split("^^")[1];
						cn += "#" + tempCn.split("^^")[2];
						cn += "#" + tempCn.split("^^")[3];
						cn += "#" + tempCn.split("^^")[4];
						cn += "#" + tempCn.split("^^")[5];
						cn += "#" + tempCn.split("^^")[6];
						cn += "#" + tempCn.split("^^")[7];
					}
					
					setObj.add(cn + "@${cfmCompareResultVO.rasmbly_id}");
				});
			}
			
			setObj.forEach(function(item,sameItem){
				serializeData += "," + item;
			});
			
			serializeData = serializeData.substr(1);
			
			$(".spinner").show();
			
			var form = document.ReclectForm;
			form.PK.value = serializeData;
			form.DTA_SE_CODE.value = dta_se_code;
			form.submit();
		}
	} 
}
-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />

		<div id="page-wrapper">
		
		<p><tt id="results"></tt></p>
		
		<form:form name="listForm" action="/col/cfm/CfmList.do" method="post">
				
			<div class="row">
          		<div class="col-lg-12">
                    <h1 class="page-header">표준연계API 파일동기화</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>표준연계API 파일동기화</h2>
			
			<table class="table table-striped table-bordered table-hover" >
				<colgroup>
					<col width="20%" />
					<col width="" />
				</colgroup>
			
				<tr>
					<th>수집년도</th>
					<td>
						<select style="min-width:150px;" class=" input-sm" name="colct_year" id="colct_year">
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
						</select> 년
					</td>
				</tr>
				<tr>
					<th>지방의회</th>
					<td>
				    	<select aria-controls="dataTables-example" class=" input-sm" name="selRasmbly" id="selRasmbly" onchange="localChange(this.value, 1);">
				    		<option value="0">의회선택</option>
				    		<option value="INTSTTCL_000023" <c:if test="${cfmCompareResultVO.insttClCode == 'INTSTTCL_000023'}">selected="selected"</c:if>>광역의회</option>
				    		<option value="INTSTTCL_000024" <c:if test="${cfmCompareResultVO.insttClCode == 'INTSTTCL_000024'}">selected="selected"</c:if>>기초의회</option>
				    	</select>			
				    									
				    	<select aria-controls="dataTables-example" class=" input-sm" name="selRegion" id="selRegion" onchange="rasmblyChange(this.value, 1);">
							<option disabled="disabled" value="0">지역 선택</option>
				    	</select>							
					
				    	<select name="rasmbly_id" id="rasmbly_id" aria-controls="dataTables-example" class=" input-sm" onchange="rasmblySelect(this);">
				    		<option disabled="disabled" value="0">지방의회 선택</option>
				    	</select>									
					</td>
				</tr>
				<tr>
					<th>자료유형</th>
					<td>
						<input type="radio" name="api_code" id="api_code1" value="RES301" text="회의록" <c:if test="${cfmCompareResultVO.api_code eq 'RES301'}">checked="checked"</c:if> /> 회의록
						<input type="radio" name="api_code" id="api_code2" value="RES305" text="부록" <c:if test="${cfmCompareResultVO.api_code eq 'RES305'}">checked="checked"</c:if> /> 부록
						<input type="radio" name="api_code" id="api_code3" value="RES403" text="의안" <c:if test="${cfmCompareResultVO.api_code eq 'RES403'}">checked="checked"</c:if> /> 의안
						<input type="radio" name="api_code" id="api_code4" value="RES203" text="의원" <c:if test="${cfmCompareResultVO.api_code eq 'RES203'}">checked="checked"</c:if> /> 의원
					</td>
				</tr>
			</table>


			<p class="tr">
				<a href="#LINK" onclick="javascript:fnCompareCollectionFile(); return false;">
				<button type="button" class="btn btn-success">수집파일비교실행</button>
				</a>
			</p>

			<input type="hidden" name="compareYn" id="compareYn" />
			<input type="hidden" name="rasmbly_nm" id="rasmbly_nm" value="${cfmCompareResultVO.rasmbly_nm}" />
			<input type="hidden" name="api_nm" id="api_nm" value="${cfmCompareResultVO.api_nm}" />
			

			<table class="table table-striped table-bordered table-hover tc" style="width:600px;">
				<colgroup>
					<col width="400px" />
					<col width="200px" />
				</colgroup>
				<tr>
					<th class="tc">수집년도</th><td>${cfmCompareResultVO.colct_year}</td>
				</tr>
				<tr>
					<th class="tc">지방의회</th><td>${cfmCompareResultVO.rasmbly_nm}</td>
				</tr>
				<tr>
					<th class="tc">자료유형</th><td>${cfmCompareResultVO.api_nm}</td>
				</tr>
			</table>
			
			<table class="table table-striped table-bordered table-hover tc" style="width:600px;" >
				<colgroup>
					<col width="400px" />
					<col width="200px" />
				</colgroup>
				<tr>
					<th class="tc">총 파일 개수</th><td>${cfmCompareResultVO.totCnt}</td>
				</tr>
				<tr>
					<th class="tc">[DB : O / FILE : O]</th><td>${cfmCompareResultVO.nrmltCnt}</td>
				</tr>
				<tr>
					<th class="tc">자료는 있으나 파일이 없는 경우 [DB : O / FILE : X]</th>
					<c:if test="${cfmCompareResultVO.retryColCnt > 0}">
					<td class="b"><a href="javascript:openLayerPopup('1');">${cfmCompareResultVO.retryColCnt}</a></td>
					</c:if>
					<c:if test="${cfmCompareResultVO.retryColCnt == null || cfmCompareResultVO.retryColCnt <= 0}">
					<td>${cfmCompareResultVO.retryColCnt}</td>
					</c:if>
				</tr>
				<tr>
					<th class="tc">자료는 없는데 파일만 있는 경우 [DB : X / FILE : O]</th>
					<c:if test="${cfmCompareResultVO.delCnt > 0}">
					<td class="b"><a href="javascript:openLayerPopup('2');">${cfmCompareResultVO.delCnt}</a></td>
					</c:if>
					<c:if test="${cfmCompareResultVO.delCnt == null || cfmCompareResultVO.delCnt <= 0}">
					<td>${cfmCompareResultVO.delCnt}</td>
					</c:if>
				</tr>				
			</table>
			</form:form>
		</div><!--//page-wrapper-->
		
			<div class="popup-wrap" style="display:none; width:100%; heigh:auto; position:fixed; top:0; margin:0; z-index:9999999999999999999999999999; background:url(/images/clikmng/bg_layer.png) left top repeat;">
				<div class="pop-cont" style="border:1px solid #999; background:#fff;  padding:10px; margin:120px auto; width:60%; height:800px; ">
					<h1 class="" style="font-size:16px;"><span id="popupTitle">자료는 있으나 파일이 없는 경우</span></h1>
					<!-- /.panel-heading -->
					<p class="tc">
						<button type="button" class="btn btn-default" onclick="closeLayerPopup();">닫기</button>
					</p>					
					<div class="panel-body ">	
						<div class="tl mb20">
							<a href="javascript:fnExcel();" class="btn btn-success">excel</a>
							<span class="fr" id="top_btn_1">
								<a href="javascript:fnStdCntcApiReclect('ALL');" class="btn btn-success"><span>전체재수집</span></a>
								<a href="javascript:fnStdCntcApiReclect();" class="btn btn-danger"><span>선택재수집</span></a>
							</span>
							<span class="fr" id="top_btn_2" style="display:none;">
								<a href="javascript:deleteCompareListFile('ALL');" class="btn btn-success"><span>전체파일삭제</span></a>
								<a href="javascript:deleteCompareListFile();" class="btn btn-danger"><span>선택파일삭제</span></a>
							</span>
						</div>
						
						<form:form name="popupForm" id="popupForm" method="post" style="height:520px;">
						
						<input type="hidden" id="procNo" name="procNo" value=""/>
						
						<div class="table-responsive" style="height:500px; overflow-x:hidden;">
							<table id="tbCompareList" name="tbCompareList" class="table table-striped table-bordered table-hover" style="overflow: hidden;" >
								<colgroup>
									<col width="5%" />
									<col width="5%" />
									<col width="200px" />
									<col width="200px" />
									<col width="410px" />
								</colgroup>
								<thead>
									<tr>
										<th>번호</th>
										<th>선택 <input type="checkbox" name="checkAll" onclick="javascript:fnCheckAll()" /></th>
										<th>KEY정보</th>
										<th>파일 URL</th>
										<th>파일저장경로</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						</form:form>
						<!-- /.table-responsive -->
						<div class="tl mb20">
							<a href="javascript:fnExcel();" class="btn btn-success">excel</a>
							<span class="fr" id="bottom_btn_1">
								<a href="javascript:fnStdCntcApiReclect('ALL');" class="btn btn-success"><span>전체재수집</span></a>
								<a href="javascript:fnStdCntcApiReclect();" class="btn btn-danger"><span>선택재수집</span></a>
							</span>
							<span class="fr" id="bottom_btn_2" style="display:none;">
								<a href="javascript:deleteCompareListFile('ALL');" class="btn btn-success"><span>전체파일삭제</span></a>
								<a href="javascript:deleteCompareListFile();" class="btn btn-danger"><span>선택파일삭제</span></a>
							</span>
						</div>

					</div>
					<!-- /.panel-body -->
					<p class="tc">
						<button type="button" class="btn btn-default" onclick="closeLayerPopup();">닫기</button>
					</p>
				</div><!--//pop-cont-->
			</div><!--//popup-wrap-->

	

<!-- <div class="modal">Place at bottom of page</div> -->
<div class="spinner"></div>

<!-- 파일 재수집용 form -->
<form:form id="ReclectForm" name="ReclectForm" action="/rlm/StdCntcApiColctProc.do" method="post">
<input type="hidden" id="LOASM_CODE" name="LOASM_CODE" value="${cfmCompareResultVO.rasmbly_id}"/>
<input type="hidden" id="DTA_SE_CODE" name="DTA_SE_CODE" value=""/>
<input type="hidden" id="COLCT_SE_CODE" name="COLCT_SE_CODE" value="CLT103"/>
<input type="hidden" id="PROCESS_GUBUN" name="PROCESS_GUBUN" value="PROMPT"/>
<input type="hidden" id="PK" name="PK" value=""/>
<input type="hidden" id="return_url" name="return_url" value="/col/cfm/CfmList.do"/>
</form:form>
</body>
</html>
