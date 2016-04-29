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
<title>대수/기수 상세정보</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mngManage" staticJavascript="false" xhtml="true" cdata="false"/>
 
<script type="text/javaScript" language="javascript">

$(document).ready(function(){
	if("${errMsg}" != ""){
		alert("${errMsg}");
		location.href="<c:url value='/rlm/GenerationFlagList.do'/>";
	}
	
	$('#frhfyr_begin_de, #frhfyr_end_de, #shyy_begin_de, #shyy_end_de').datepicker({
    	dateFormat: 'yy-mm-dd',
    	changeYear: true,
    	changeMonth: true, 
    	showMonthAfterYear: true,
    	showButtonPanel:true,
    	showOn: 'both',
        buttonText: '<img src="<c:url value='/images/clikmng/cmm/icon/bu_icon_carlendar.gif' />" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
        dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
        dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'], 
        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']        	
    });
	
	if("${resultObj.frhfyr_begin_de }" != "null" && "${resultObj.frhfyr_begin_de }" != "")
	{
		var ymd = "${resultObj.frhfyr_begin_de }";
		$("#frhfyr_begin_de").datepicker("setDate",new Date(ymd.substr(0,4),ymd.substr(5,2)-1,ymd.substr(8,2)));
		ymd = "${resultObj.frhfyr_end_de }";
		$("#frhfyr_end_de").datepicker("setDate",new Date(ymd.substr(0,4),ymd.substr(5,2)-1,ymd.substr(8,2)));
		ymd = "${resultObj.shyy_begin_de }";
		$("#shyy_begin_de").datepicker("setDate",new Date(ymd.substr(0,4),ymd.substr(5,2)-1,ymd.substr(8,2)));
		ymd = "${resultObj.shyy_end_de }";
		$("#shyy_end_de").datepicker("setDate",new Date(ymd.substr(0,4),ymd.substr(5,2)-1,ymd.substr(8,2)));
	}
	
	$('#frhfyr_begin_de').on('change',function(){
		$('input[name="begin_de"]').val($(this).val());
	});
	$('#shyy_end_de').on('change',function(){
		$('input[name="end_de"]').val($(this).val());
	});
	
	$('#frhfyr_begin_de').change();
	$('#shyy_end_de').change();
});

<!--
/******************************************
 * 목록
 ******************************************/

function fnListMng() {
	var f = document.getElementById("detailForm");
    f.action = "<c:url value='/rlm/GenerationFlagList.do'/>";
    f.submit();
}

/******************************************
 * 수정
 ******************************************/

function fnEditMng() {
	if(confirm("수정 하시겠습니까?")){
	    var f = document.getElementById("detailForm");
	    
	  //필수값 체크
	    if(f.hrsmnpd_nm.value == ""){
	    	alert("기수명을 입력해 주세요.");
	    	$('#hrsmnpd_nm').focus();
	    	return false;
	    }
	    if(f.frhfyr_begin_de.value == ""){
	    	alert("전반기 시작 일자를 선택해 주세요.");
	    	$('#frhfyr_begin_de').focus();
	    	return false;
	    }
	    if(f.frhfyr_end_de.value == ""){
	    	alert("전반기 종료 일자를 선택해 주세요.");
	    	$('#frhfyr_end_de').focus();
	    	return false;
	    }
	    if(f.shyy_begin_de.value == ""){
	    	alert("후반기 시작 일자를 선택해 주세요.");
	    	$('#shyy_begin_de').focus();
	    	return false;
	    }
	    if(f.shyy_end_de.value == ""){
	    	alert("후반기 종료 일자를 선택해 주세요.");
	    	$('#shyy_end_de').focus();
	    	return false;
	    }
	    
	    f.mode.value = 'U';
	    f.action = "<c:url value='/rlm/GenerationFlagRegistProc.do'/>";
	    f.submit();
	}
}

/******************************************
 * 삭제
 ******************************************/

function fnDelMng() {
	if(confirm("삭제 하시겠습니까?")){
	    var f = document.getElementById("detailForm");
	    f.mode.value = 'D';
	    f.action = "<c:url value='/rlm/GenerationFlagRegistProc.do'/>";
	    f.submit();
	}
}

/******************************************
 * 매핑하기
 ******************************************/

function fnMapping() {
	if(confirm("매핑 하시겠습니까?")){
		
		if( $("#frhfyr_begin_de").val() == "" ||
			$("#frhfyr_end_de").val() == "" ||
			$("#shyy_begin_de").val() == "" ||
			$("#shyy_end_de").val() == ""){
			alert("전반기, 후반기 기간을 모두 입력해 주세요.");
			return false;
		}
		
		$.ajax({
			type : "POST"
			,url : "/rlm/ajaxSearchNumprMapping.do"
			,data : {begin_de:$('input[name="begin_de"]').val(), end_de:$('input[name="end_de"]').val()}
			,data_type : "json"
			,success : function(result){
				var json = $.parseJSON(result);
				
				if(json.length == 0){
					alert("조회 기간에 해당하는 의회 정보가 존재하지 않습니다.");
					return false;
				}
				
				var str="";
				$('#mapping > tbody > tr').remove();
				$('#mapping_rasmbly').val('');
				$(json).each(function(index,value){
					$('#mapping_rasmbly').val($('#mapping_rasmbly').val() + "," + $(value[0].split("#"))[0] +"#"+$(value[0].split("#"))[2]);
					str += '<tr>';
					str += '	<th>' + (index+1) + '</th>';
					str += '	<td>' + $(value[0].split("#"))[1] + '</td>';
					str += '	<td>제' + $(value[0].split("#"))[2] + '대</td>';
					str += '</tr>';
				});
				$('#mapping_rasmbly').val($('#mapping_rasmbly').val().substr(1));
				$('#mapping > tbody').append(str);
			}
			,error : function(e){
				console.log(e);
				return "";
			}
		});
	}
}
-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form id="detailForm" name="detailForm" method="post">
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">대수/기수 관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>대수/기수 상세정보</h2>
				
				<input type="hidden" id="mapping_rasmbly" name="mapping_rasmbly" value="" />
				<input type="hidden" id="mode" name="mode" value="U" />
				<input type="hidden" id="hrsmnpd_sn" name="hrsmnpd_sn" value="${resultObj.hrsmnpd_sn }" />
				
				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="15%" />
						
						<col />
					 </colgroup>
					<tbody>
						<tr>
							<th>기수</th>
							<td><input type="text" class="ip input-sm" name="hrsmnpd_nm" value="${resultObj.hrsmnpd_nm }"/></td>
						</tr>
						<tr>
							<th>기간</th>
							<td>
								<div class="select_box">
									<input type="text" style="width:120px; border:solid 0px;" readonly name="begin_de" value="${resultObj.begin_de }" readonly /> ~ 
									<input type="text" style="width:120px; border:solid 0px;" readonly name="end_de" value="${resultObj.end_de }" readonly /> 
								</div><!--//tc-->
							</td>
						</tr>
						<tr>
							<th>전반기</th>
							<td>
								<div class="select_box">
									<input type="text" class="input-sm ip" style="width:150px;" id="frhfyr_begin_de" name="frhfyr_begin_de" value="${resultObj.frhfyr_begin_de }" readonly />
									~ <input type="text" class=" input-sm ip" style="width:150px;" id="frhfyr_end_de" name="frhfyr_end_de" value="${resultObj.frhfyr_end_de }" readonly /> 
								</div><!--//tc-->
							</td>
						</tr>
						<tr>
							<th>후반기</th>
							<td>
								<div class="select_box">
									<input type="text" class="input-sm ip" style="width:150px;" id="shyy_begin_de" name="shyy_begin_de" value="${resultObj.shyy_begin_de }" readonly />
									~ <input type="text" class=" input-sm ip" style="width:150px;" id="shyy_end_de" name="shyy_end_de" value="${resultObj.shyy_end_de }" readonly /> 
								</div><!--//tc-->
							</td>
						</tr>
					</tbody>
				</table>

				<div class="left mb20">
					<button type="button" class="btn btn-info" onclick="fnMapping();">매핑하기</button>
				</div>
				<div class="right mb20">
					<button type="button" class="btn btn-default" onclick="fnListMng();">목록</button>
					<button type="button" class="btn btn-success" onclick="fnEditMng();">수정</button>
					<button type="button" class="btn btn-danger" onclick="fnDelMng();">삭제</button>
				</div>

				<table class="table table-striped table-bordered table-hover "  id="mapping">
					<colgroup>
						<col width="5%" />
						<col />
						<col />
					 </colgroup>
					 <thead>
						<tr>
							<th>번호</th>
							<th>의회명</th>
							<th>대수</th>
						</tr>
					 </thead>
					<tbody>
						<c:forEach items="${resultList}" var="item"  varStatus="i">
							<tr>
								<th>${i.index + 1 }</th>
								<td>${item.rasmbly_nm}</td>
								<td>제${item.rasmbly_numpr }대</td>
							</tr>
							</c:forEach>
					</tbody>
				</table>
		</div><!--//page-wrapper-->
</form>
</body>
</html>

