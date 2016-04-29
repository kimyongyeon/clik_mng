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
<title>메타데이터관리</title>
<script type="text/javascript" src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

$(document).ready(function(){
	
	//필드 정렬 
	$('#listTable th').click(function(){
		
		if($(this).attr('value') == undefined){
			return false;
		}
		
		if($(this).find("span i").length == 0){
			$('#sort').val($(this).attr('value') + " DESC");
		}else if($(this).find("i").attr('class') == "fa fa-caret-down"){
			$('#sort').val($(this).attr('value') + " ASC");
		}else{
			$('#sort').val("");
		}
		frmSubmit('th');
	});
	
});




function piView(cnId, extId ,pageNum) {
	$('#cnId').val(cnId);
	$('#extId').val(extId);
	$('#pageNum').val(pageNum);
	pgfrm.action = "/mdm/MdmPolicyInfoView.do";
	document.pgfrm.submit();
}
function lodding() {
	$(".spinner").show();
	//return true;
}

function setDownload(DOWNID) {
	document.location.href="/mdm/MdmPolicyInfoDownLoad.do?DOWNID="+DOWNID;
}

function getCheckAll() {
	var FLength = document.getElementsByName("isview").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if( FLength == 1 ) {
		document.getElementById("isview").checked = checkAllValue;
	}
	
	for(var i=0; i < FLength; i++) {
		document.getElementsByName("isview")[i].checked = checkAllValue;
	}
} 
function setCheck(val) {
	//var mydata = $("#checkform").serialize();
	var msg = "";
	var isview = "";
	var chkmsg = "";
	var dismsg = "";
	var flg = false;
	
	var idx = 0;
	var arr = new Array();

	if( val == "Y" ) {
		chkmsg = "게시";
		dismsg = "게시";
	}
	else {
		chkmsg = "미게시";
		dismsg = "<font color='red'>미게시</font>";
	}

	$("input:checkbox[name='isview']").each(function() {
 		if( $(this).is(":checked") == true ) {
			if( flg == false ) {
				flg = true;
				isview += $(this).val() + "=" + val;
			}
			else {
				isview += "," + $(this).val() + "=" + val;
			}
			arr[idx] = "viewmsg" + $(this).val();
			idx++;
		}
	});
	
	if( isview != "" ) {
		if( !confirm("체크한 게시물을 " + chkmsg + "하시겠습니까?") ) {
			return false;
		}
		
  		$.ajax({
			url:"/mdm/MdmPolicyInfoIsView.do",
			cache:false,
			type:"post",
			data:{"ISVIEW":isview},
			success: function(data, status) {
				msg = JSON.parse(data);
				if( msg.rst == "yes" ) {
					alert("게시를 수정하였습니다.");
// 					for(var i = 0; i < arr.length; i++) {
// 						$("#" + arr[i]).html(dismsg);
// 					}
				}
				else {
					alert("게시를 수정하는데 실패하였습니다.");
				}
				
				frmSubmit();
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
  	}
	else {
		alert(chkmsg + "할 항목을 체크하세요");
	}
	return false;
}

//게시여부 게신판 단위 일괄수정
function setCheckAll(val) {
	var schRegion2 = $('#schRegion2').val();
	var schSiteId = $('#schSiteId').val();
	var seedid = $("#schSeedId").val();
	
	if(schRegion2 == '' || schRegion2 == '0'){
		alert('지역을 선택해주세요.');
		$('#schRegion2').focus();
		return false;
	}
	
	if(schSiteId == '' || schSiteId == '0'){
		alert('사이트명을 선택해주세요.');
		$('#schSiteId').focus();
		return false;
	}
	
	if(seedid == '' || seedid == '0'){
		alert('게시판을 선택해주세요.');
		$('#schSeedId').focus();
		return false;
	}
	
	var sel = document.getElementById("schSeedId");
	var bbsText = sel.options[sel.selectedIndex].text;
	var msg = "";
	var chkmsg = "";
	
	if( val == "Y" ) {
		chkmsg = "일괄 게시";
	}
	else {
		chkmsg = "일괄 미게시";
	}
	
	if( !confirm("해당 게시판(" + bbsText + ")을 " + chkmsg + " 합니다.\n\n계속 하시겠습니까?") ) {
		return false;
	}
	
 	$.ajax({
		url:"/mdm/MdmPolicyInfoIsViewAll.do",
		cache:false,
		type:"post",
		data:{SEEDID:seedid,ISVIEW:val},
		success: function(data, status) {
			msg = JSON.parse(data);
			if( msg.rst == "yes" ) {
				alert("게시를 수정하였습니다.");
			}
			else {
				alert("게시를 수정하는데 실패하였습니다.");
			}
			
			frmSubmit();
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
 		
	return false;
}
function setDelete(val) {
	//var mydata = $("#checkform").serialize();
	var msg = "";
	var chkmsg = "";
	var dismsg = "";
	var isview = "";
	var flg = false;

	var idx = 0;
	var arr = new Array();

	if( val == "D" ) {
		chkmsg = "삭제";
		dismsg = "<font color='red'>삭제</font>";
	}
	else {
		chkmsg = "복구";
	}
	
	$("input:checkbox[name='isview']").each(function() {
 		if( $(this).is(":checked") == true ) {
			if( flg == false ) {
				flg = true;
				isview += $(this).val() + "=" + val;
			}
			else {
				isview += "&" + $(this).val() + "=" + val;
			}
			arr[idx] = "delmsg" + $(this).val();
			idx++;
		}
	});
	
	if( isview != "" ) {
		if( !confirm("체크한 게시물을 " + chkmsg + "하시겠습니까?") ) {
			return false;
		}

  		$.ajax({
			url:"/mdm/MdmPolicyInfoDeleteChk.do",
			cache:false,
			type:"post",
			data:{"ISVIEW":isview},
			data_type : "json",
			success: function(data, status) {
				msg = JSON.parse(data);
				//msg = $.parseJSON(data);
				if( msg.rst == "yes" ) {
					alert("게시물을 " + chkmsg + "하였습니다.");
					for(var i = 0; i < arr.length; i++) {
						$("#" + arr[i]).html(dismsg);
					}
					//체크박스 해제
					$("input:checkbox[name='isview']").each(function() {
						$(this).attr('checked',false)
					});
				}
				else {
					alert("게시물을 " + chkmsg + "하는데 실패하였습니다.");
				}
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
  	}
	else {
		alert(chkmsg + "할 항목을 체크하세요");
	}
	return false;
}
</script>

<script type="text/javaScript" language="javascript" defer="defer">
$(window).load(function(){
	$(".myButton").click(function () {
		
		//기존에 열려 있는 팝업이 있을 경우 닫기 위해서 toggle 호출
		if($('#myDiv').css('display') == "block")
			$('#myDiv').toggle(effect, options, duration);
		
		var top = getRealOffsetTop(this) - 7;
		var left = getRealOffsetLeft(this) + 45;
		
		$('#myDiv').css('top',top);
		$('#myDiv').css('left',left);
		
		// Set the effect type
		var effect = 'slide';
		// Set the options for the effect type chosen
		var options = { direction: 'right' }; //{ direction: $('.mySelect').val() };
		// Set the duration (default: 400 milliseconds)
		var duration = 500;
		
		$('#myDiv').toggle(effect, options, duration);
	});
	
	//추가 버튼 보여주기
	if($('input[type=radio][name=selCondition]:checked').val() == 'siteType'){
		$('#addBtn').css('display', '');
	}
});
function getSideMenu(MODE, OUTBBS_CN, DUPLICATION) {
	$("#slideId").html("");
	//if( $('#myDiv').is(':hidden') ) {
	//	getSlideShowHidden();
	//}
	$("#slideId").html("");
	var url = "";

	if( MODE == "FILE" ) {
		url = "/mdm/MdmPolicyInfoFileListCmmn.do";
	}
	else {
		url = "/mdm/MdmPolicyInfoListCmmn.do";
	}
	$.ajax({
		url:url,
		cache:false,
		type:"get",
		data:{"OUTBBS_CN":OUTBBS_CN, "DUPLICATION":DUPLICATION},
		success: function(data, status) {
			$("#slideId").html(data);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
}

function getRealOffsetTop(o) { return o ? o.offsetTop + getRealOffsetTop(o.offsetParent) : 0; } 

function getRealOffsetLeft(o) { return o ? o.offsetLeft + getRealOffsetLeft(o.offsetParent) : 0; } 

function getSlideShowHidden() {
	// Set the effect type
	var effect = 'slide';
	// Set the options for the effect type chosen
	var options = { direction: 'right' }; //{ direction: $('.mySelect').val() };
	// Set the duration (default: 400 milliseconds)
	var duration = 500;
	$('#myDiv').toggle(effect, options, duration);
}

function getSlideCheckAll() {
	var FLength = document.getElementsByName("chkid").length;
	var checkAllValue = document.getElementById('slideCheckAll').checked;

	//undefined
	if( FLength == 1 ) {
		document.getElementsByName("chkid")[0].checked = checkAllValue;
	}
	else {
		for(var i=0; i < FLength; i++) {
			document.getElementsByName("chkid")[i].checked = checkAllValue;
		}
	}
} 
function setSlideDelCheck(MODE) {
	//var mydata = $("#checkform").serialize();
	var msg = "";
	var isview = "";
	var flg = false;
	var url = "";
	if( MODE == "FILE" ) {
		url = "/mdm/MdmPolicyInfoFileListDelete.do";
	}
	else {
		url = "/mdm/MdmPolicyInfoDeleteChk.do";
	}

	$("input:checkbox[name='chkid']").each(function() {
 		if( $(this).is(":checked") == true ) {
			if( flg == false ) {
				flg = true;
				isview += $(this).val() + "=D";
			}
			else {
				isview += "&" + $(this).val() + "=D";
			}
		}
 	});
	if( isview != "" ) {
		if( !confirm("선택한 목록을 삭제하시겠습니까?") ) {
			return false;
		}
  		$.ajax({
			url:url,
			cache:false,
			type:"post",
			data:{"ISVIEW":isview},
			success: function(data, status) {
				msg = JSON.parse(data);
				if( msg.rst == "yes" ) {
					alert("선택한 목록을 삭제하였습니다.");
				}
				else {
					alert("선택한 목록을 삭제하는데 실패하였습니다.");
				}
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
  	}
	else {
		alert("삭제할 목록을 체크하세요");
	}
	return false;
}
function setSlideReCnvrsCheck() {
	//var mydata = $("#checkform").serialize();
	var msg = "";
	var isview = "";
	var flg = false;
	
	$("input:checkbox[name='chkid']").each(function() {
 		if( $(this).is(":checked") == true ) {
			if( flg == false ) {
				flg = true;
				isview += $(this).val() + "=0";
			}
			else {
				isview += "&" + $(this).val() + "=0";
			}
		}
 	});
	
	if( isview != "" ) {
		if( !confirm("선택한 파일을 재변환하시겠습니까?") ) {
			return false;
		}
  		$.ajax({
			url:"/mdm/MdmPolicyInfoFileListReCnvrs.do",
			cache:false,
			type:"post",
			data:{"ISVIEW":isview},
			success: function(data, status) {
				msg = JSON.parse(data);
				if( msg.rst == "yes" ) {
					alert("선택한 파일을 재변환하였습니다.");
				}
				else {
					alert("선택한 파일을 재변환하는데 실패하였습니다.");
				}
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
  	}
	else {
		alert("재변환할 파일을 체크하세요");
	}
	return false;
}

function regist() {
	sfrm.action = "/mdm/MdmPolicyInfoForm.do?mdmAdm=PolicyInfo";
	document.sfrm.submit();
}
</script>
<style type="text/css">
 .myButton {
    padding: .2em 1em;
    font-size: 1em;
}
#myDiv {
	position:absolute;/* right:340px; top:400px;*/
    background-color:#fff;
    border:3px solid #b9b9b9;
    display:block;
    text-align:justify;
}
#myDiv p {
    margin: 15px;
    font-size: 0.917em;
}
.slidesession { padding-left:6px; }
.slidesession table { width:475px; border:0; border-spacing:0; border-collapse:collapse; border-top:2px solid #a96824; }
.slidesession table thead th { height:25px; padding-top:6px; padding-bottom:6px; background:url(/images/clikmng/mdm/th_bg.gif) no-repeat right top; border-bottom:1px solid #aaa; color:#a96824; text-align:center; font-weight:bold; vertical-align:top; }
.slidesession table thead th.end { background:url(/images/clikmng/mdm/th_bg.gif) no-repeat left top; }
.slidesession table tbody th { padding:6px 0 9px 0; line-height:130%; border-bottom:1px solid #ccc; border-right:1px solid #fafafa; font-weight:normal; }
.slidesession table tbody td { padding:6px 0 9px 0; line-height:130%; border-bottom:1px solid #ccc; text-align:center; }
.slidesession table tbody td.left { text-align:left; padding-left:0px; }

</style>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">메타데이터 관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
			<h2>메타데이터 목록 (지방정책정보) </h2>
			<form name="sfrm" id="sfrm" method="post" action="/mdm/MdmPolicyInfoList.do" enctype="multipart/form-data" >
			<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
			<input type="hidden" name="schDflt" id="schDflt" value="N" >
			<input type="hidden" name="pageNum" id="sFrmPageNum" value="${mdmSearchVO.pageNum}" >
			
            <%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchDt.jsp" %>
 			
<!--  			<p class="tr"> -->
<!-- 				<a href="#" onclick="return regist();"><button type="button" class="btn btn-primary">등록</button></a> -->
<!-- 				<a href="#;" onclick="return setCheck('Y');"><button type="button" class="btn btn-success">게시</button></a> -->
<!-- 				<a href="#;" onclick="return setCheck('N');"><button type="button" class="btn btn-danger">미게시</button></a> -->
<!-- 				<a href="#;" onclick="return setDelete('D');"><button type="button" class="btn btn-danger">삭제</button></a> -->
<!-- 				<a href="#;" onclick="return setDelete('U');"><button type="button" class="btn btn-primary">복구</button></a> -->
<!-- 			</p> -->
			
			<div class="page">
				총 건수 : <fmt:formatNumber value="${policyInfoListTotCnt}" groupingUsed="true"/>건
				<span>
					<button type="button" id="btnTextDownload" style="display:none;" class="btn btn-default" onclick="fnText();">TEXT</button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" id="btnExcelDownload" style="display:none;" class="btn btn-default" onclick="fnExcel();">EXCEL</button>&nbsp;&nbsp;&nbsp;&nbsp;
					출력건수
					<select name="listCnt" id="listCnt" aria-controls="dataTables-example" class=" input-sm">
						<option value="10" <c:if test="${mdmSearchVO.listCnt == '10'}"> selected="selected"</c:if>>10</option>
						<option value="30" <c:if test="${mdmSearchVO.listCnt == '30'}"> selected="selected"</c:if>>30</option>
						<option value="50" <c:if test="${mdmSearchVO.listCnt == '50'}"> selected="selected"</c:if>>50</option>
						<option value="100" <c:if test="${mdmSearchVO.listCnt == '100'}"> selected="selected"</c:if>>100</option>
					</select>
				</span>
			</div>
			
			</form>	
			
			<table class="table table-striped table-bordered table-hover "  id="listTable">
				<thead>
					<tr>
						<th><input type="checkbox" name="checkAll" id="checkAll" onclick="getCheckAll();" /></th>
						<th style="width:5%">번호</th>
						<th value="SEEDNM" style="cursor: pointer;">
							기관명
							<span class="align">
								<c:if test="${mdmSearchVO.sort == 'SEEDNM ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
								<c:if test="${mdmSearchVO.sort == 'SEEDNM DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
							</span>
						</th>
						<th value="SEEDNAME" style="cursor: pointer;">
							게시판명
							<span class="align">
								<c:if test="${mdmSearchVO.sort == 'SEEDNAME ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
								<c:if test="${mdmSearchVO.sort == 'SEEDNAME DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
							</span>
						</th>
						<th value="SITENM" style="cursor: pointer;">
							자료유형
							<span class="align">
								<c:if test="${mdmSearchVO.sort == 'SITENM ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
								<c:if test="${mdmSearchVO.sort == 'SITENM DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
							</span>
						</th>
						<th value="TITLE" style="cursor: pointer;">
							제목
							<span class="align">
								<c:if test="${mdmSearchVO.sort == 'TITLE ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
								<c:if test="${mdmSearchVO.sort == 'TITLE DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
							</span>
						</th>
<!-- 						<th value="FILECNT" style="cursor: pointer;"> -->
<!-- 							첨부파일 -->
<!-- 							<span class="align"> -->
<%-- 								<c:if test="${mdmSearchVO.sort == 'FILECNT ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if> --%>
<%-- 								<c:if test="${mdmSearchVO.sort == 'FILECNT DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if> --%>
<!-- 							</span> -->
<!-- 						</th> -->
						<th value="CDATE" style="cursor: pointer;">
							등록일
							<span class="align">
								<c:if test="${mdmSearchVO.sort == 'CDATE ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
								<c:if test="${mdmSearchVO.sort == 'CDATE DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
							</span>
						</th>
						<th value="REGDATE" style="cursor: pointer;">
							수집일
							<span class="align">
								<c:if test="${mdmSearchVO.sort == 'REGDATE ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
								<c:if test="${mdmSearchVO.sort == 'REGDATE DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
							</span>
						</th>
						<th value="CUD_CODE" style="cursor: pointer;">
							삭제
							<span class="align">
								<c:if test="${mdmSearchVO.sort == 'CUD_CODE ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
								<c:if test="${mdmSearchVO.sort == 'CUD_CODE DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
							</span>
						</th>
						<th value="DUPCNT" style="cursor: pointer;">
							중복
							<span class="align">
								<c:if test="${mdmSearchVO.sort == 'DUPCNT ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
								<c:if test="${mdmSearchVO.sort == 'DUPCNT DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
							</span>
						</th>
						<th value="ISVIEW" style="cursor: pointer;">
							게시
							<span class="align">
								<c:if test="${mdmSearchVO.sort == 'ISVIEW ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
								<c:if test="${mdmSearchVO.sort == 'ISVIEW DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
							</span>
						</th>
						<th value="OUTBBS_CN" style="cursor: pointer;">
							문서번호
							<span class="align">
								<c:if test="${mdmSearchVO.sort == 'OUTBBS_CN ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
								<c:if test="${mdmSearchVO.sort == 'OUTBBS_CN DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
							</span>
						</th>
						<th value="URL" style="cursor: pointer;">
							url
							<span class="align">
								<c:if test="${mdmSearchVO.sort == 'URL ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
								<c:if test="${mdmSearchVO.sort == 'URL DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
							</span>
						</th>
						<th>서비스링크</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="mList" items="${policyInfoList}" varStatus="status">
					<tr>
						<td><input type="checkbox" name="isview" id="isview${status.count}" value="${mList.OUTBBS_CN}" /></td>
						<td style="text-align:center">${listStartNo + status.count}</td>
						<td>${mList.SEEDNM}</td>
						<td>${mList.SEEDNAME}</td>
						<td>${mList.SITENM}</td>
						<td>
							<%-- <a href="#;" onclick="piView('${mList.OUTBBS_CN}','${mList.EXTID}','${mdmSearchVO.pageNum}');">${mList.TITLE}</a> --%>
<%-- 							<a href="#;" onclick="window.open('/mdm/MdmPolicyInfoMetaDataView1.do?cnId=${mList.OUTBBS_CN}','메타데이터보기','width=1366,height=748,scrollbars=yes,resizable=yes');">${mList.TITLE}</a> --%>
							<a href="#;" onclick="fn_detailView('/mdm/MdmPolicyInfoMetaDataView1.do?cnId=${mList.OUTBBS_CN}','메타데이터보기','width=1366,height=748,scrollbars=yes,resizable=yes');">${mList.TITLE}</a>
						</td>
<!-- 						<td> -->
<%-- 							<c:if test="${mList.FILECNT > 0}"> --%>
<%-- 								<a href="#;" class="myButton" onclick="getSideMenu('FILE', '${mList.OUTBBS_CN}', '');"> --%>
<%-- 									<c:if test="${mList.FILESUCCCNT > 0}">변환성공(${mList.FILESUCCCNT})</c:if> --%>
<%-- 									<c:if test="${mList.FILEFLRCNT > 0}"><br />변환실패(<font color="red">${mList.FILEFLRCNT}</font>)</c:if> --%>
<%-- 									<c:if test="${mList.FILECNT - mList.FILESUCCCNT - mList.FILEFLRCNT > 0}"><br />변환대기(${mList.FILECNT - mList.FILESUCCCNT - mList.FILEFLRCNT})</c:if> --%>
<!-- 								</a> -->
<%-- 							</c:if> --%>
<!-- 						</td> -->
						<td>
							<c:choose>
								<c:when test="${fn:length(mList.CDATE) > 7}">
									${fn:substring(mList.CDATE, 0, 4)}-${fn:substring(mList.CDATE, 4, 6)}-${fn:substring(mList.CDATE, 6, 8)}
								</c:when>
								<c:otherwise>${mList.CDATE}</c:otherwise>
							</c:choose> 
						</td>
						<td>
							<c:choose>
								<c:when test="${fn:length(mList.REGDATE) > 8}">
									${fn:substring(mList.REGDATE, 0, 4)}-${fn:substring(mList.REGDATE, 4, 6)}-${fn:substring(mList.REGDATE, 6, 8)}
								</c:when>
								<c:otherwise>${mList.REGDATE}</c:otherwise>
							</c:choose> 
						</td>
						<td>
							<span id="delmsg${mList.OUTBBS_CN}">
								<c:if test="${mList.CUD_CODE == 'D'}"><font color='red'>삭제</font></c:if>
							</span>
						</td>
						<td>
							<c:choose>
								<c:when test="${mList.DUPCNT > 1}"><a href="#;" class="myButton" onclick="getSideMenu('DPLCT', '${mList.OUTBBS_CN}', '${mList.DUPLICATION}');">중복(${mList.DUPCNT})</a></c:when>
								<c:otherwise>N</c:otherwise>
							</c:choose>
						</td>
						<td>
							<span id="viewmsg${mList.OUTBBS_CN}">
								<c:choose>
									<c:when test="${mList.ISVIEW == 'N'}"><font color='red'>미게시</font></c:when>
									<c:otherwise>게시</c:otherwise>
								</c:choose>
							</span>
						</td>
						<td>
							${mList.OUTBBS_CN}
						</td>
						<td>
							<c:choose>
								<c:when test="${fn:length(mList.URL) > 25 }">
									<a href="${mList.URL}" title="${mList.URL}"> ${fn:substring(mList.URL,0,24)}... </a>
								</c:when>
								<c:otherwise>
									<a href="${mList.URL}" title="${mList.URL}">${mList.URL}</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="http://clik.nanet.go.kr/potal/search/searchView.do?collection=policyinfo&DOCID=${mList.OUTBBS_CN}" target="_blank">서비스링크</a>
						</td>
<%-- 							
						<td>
							<span id="viewmsg${mList.OUTBBS_CN}">
								<c:choose>
									<c:when test="${mList.ISVIEW == 'N'}"><font color='red'>미게시</font></c:when>
									<c:otherwise>게시</c:otherwise>
								</c:choose>
							</span>
						</td>
 --%>
					</tr>
				</c:forEach>

				</tbody>
			</table>

				<c:if test="${fn:length(policyInfoList) < 1}">
					<p style="text-align:center">검색된 결과가 없습니다.</p>
				</c:if>

<!-- 				<p class="tr"> -->
<!-- 					<a href="#" onclick="return regist();"><button type="button" class="btn btn-primary">등록</button></a> -->
<!-- 					<a href="#;" onclick="return setCheck('Y');"><button type="button" class="btn btn-success">게시</button></a> -->
<!-- 					<a href="#;" onclick="return setCheck('N');"><button type="button" class="btn btn-danger">미게시</button></a> -->
<!-- 					<a href="#;" onclick="return setDelete('D');"><button type="button" class="btn btn-danger">삭제</button></a> -->
<!-- 					<a href="#;" onclick="return setDelete('U');"><button type="button" class="btn btn-primary">복구</button></a> -->
<!-- 				</p> -->
				
				<p class="tr" id="addBtn" style="display:none;">
					<a href="#;" onclick="return setCheckAll('Y');"><button type="button" class="btn btn-success">일괄 게시</button></a>
					<a href="#;" onclick="return setCheckAll('N');"><button type="button" class="btn btn-success">일괄 미게시</button></a>
				</p>

				<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
					<ul class="pagination">
						${paginationInfo}
					</ul>
				</div> 
			
<div id="myDiv" style="display:none; height:400px;">
	<div style="padding:5px; float:auto; background:#efefef;  text-align:center; margin-bottom:5px;"><a href="#" onclick="getSlideShowHidden();"><img src="/images/clikmng/mdm/btn_close.gif"></a></div>
	<div id="slideId" style="clear:both; height:330px; width:490px; overflow:auto;"></div>
</div>			
<div class="spinner"></div>

</body>
</html>
