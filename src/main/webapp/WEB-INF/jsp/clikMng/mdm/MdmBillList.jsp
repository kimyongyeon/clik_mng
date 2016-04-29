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
<title>지방의회 의안정보</title>
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

function biView(cnId, pageNum) {
	$('#cnId').val(cnId);
	$('#pageNum').val(pageNum);
	pgfrm.action = "/mdm/MdmBillView.do";
	document.pgfrm.submit();
}
function getDownloadByBiFileId(DOWNID) {
	if( DOWNID == null || DOWNID == "" ) {
		alert("다운로드 아이디가 없습니다.");
		return false;
	}
	document.location.href="/mdm/MdmBillFileDownLoadByBiFileId.do?DOWNID="+DOWNID;
}
function lodding() {
	$(".spinner").show();
	return true;
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
				isview += "&" + $(this).val() + "=" + val;
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
			url:"/mdm/MdmBillIsView.do",
			cache:false,
			type:"post",
			data:{"ISVIEW":isview},
			success: function(data, status) {
				msg = JSON.parse(data);
				if( msg.rst == "yes" ) {
					alert("게시를 수정하였습니다.");
					for(var i = 0; i < arr.length; i++) {
						$("#" + arr[i]).html(dismsg);
					}
					
					//체크박스 해제
					$("input:checkbox[name='isview']").each(function() {
						$(this).attr('checked',false)
					});
				}
				else {
					alert("게시를 수정하는데 실패하였습니다.");
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
function setDelete(val) {
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
		if( !confirm("체크한 의안정보를 " + chkmsg + "하시겠습니까?") ) {
			return false;
		}

  		$.ajax({
			url:"/mdm/MdmBillDeleteChk.do",
			cache:false,
			type:"post",
			data:{"ISVIEW":isview},
			success: function(data, status) {
				msg = JSON.parse(data);
				if( msg.rst == "yes" ) {
					alert("의안정보를 " + chkmsg + "하였습니다.");
					for(var i = 0; i < arr.length; i++) {
						$("#" + arr[i]).html(dismsg);
					}
					
					//체크박스 해제
					$("input:checkbox[name='isview']").each(function() {
						$(this).attr('checked',false)
					});
				}
				else {
					alert("의안정보를 " + chkmsg + "하는데 실패하였습니다.");
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
		// Set the effect type
		var effect = 'slide';
		// Set the options for the effect type chosen
		var options = { direction: 'right' }; //{ direction: $('.mySelect').val() };
		// Set the duration (default: 400 milliseconds)
		var duration = 500;
		$('#myDiv').toggle(effect, options, duration);
	});
});
function getSideMenu(MODE, BI_CN, RASMBLY_ID, RASMBLY_NUMPR, BI_NO, JRSD_CMIT_ID) {
	//if( $('#myDiv').is(':hidden') ) {
	//	getSlideShowHidden();
	//}
	$("#slideId").html("");
	var url = "";

	if( MODE == "DPLCT" ) {
		url = "/mdm/MdmBillDplctListCmmn.do";
	}
	else {
		url = "/mdm/MdmBillFileListCmmn.do";
	}
	$.ajax({
		url:url,
		cache:false,
		type:"get",
		data:{"BI_CN":BI_CN, "RASMBLY_ID":RASMBLY_ID, "RASMBLY_NUMPR":RASMBLY_NUMPR, "BI_NO":BI_NO, "JRSD_CMIT_ID":JRSD_CMIT_ID},
		success: function(data, status) {
			$("#slideId").html(data);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
}
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
	var work = "";
	var isview = "";
	var flg = false;
	
	var url = "";
	if( MODE == "DPLCT" ) {
		work = "의안";
		url = "/mdm/MdmBillDplctListDelete.do";
	}
	else {
		work = "파일";
		url = "/mdm/MdmBillFileListDelete.do";
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
		if( !confirm("선택한 " + work + "을 삭제하시겠습니까?") ) {
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
					alert("선택한 " + work + "을 삭제하였습니다.");
				}
				else {
					alert("선택한 " + work + "삭제하는데 실패하였습니다.");
				}
			},
			error: function (data, status, e) {
				alert(e);
			}
		});
  	}
	else {
		alert("삭제할 " + work + "을 체크하세요");
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
			url:"/mdm/MdmBillFileListReCnvrs.do",
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
</script>
<style type="text/css">
 .myButton {
    padding: .2em 1em;
    font-size: 1em;
}
#myDiv {
	position:absolute; right:340px; top:400px;
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

			<h2>메타데이터 목록 (지방의회 의안정보) </h2>
			<form name="sfrm" method="post" action="/mdm/MdmBillList.do" enctype="multipart/form-data">
			<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
			<input type="hidden" name="schDflt" id="schDflt" value="N" >
			<input type="hidden" name="pageNum" id="sFrmPageNum" value="${mdmSearchVO.pageNum}" >
			
            <%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchDt.jsp" %>
			
<!-- 			<p class="tr"> -->
<%-- 				<c:if test="${billList != null && fn:length(billList) > 0}"> --%>
<!-- 					<span class="fl"> <a href="#;" -->
<!-- 						onclick="return insertStdCntcApiColct();"><button type="button" class="btn btn-success">재수집</button></a> -->
<!-- 					</span> -->
<%-- 				</c:if> --%>
<!-- 				<a href="/mdm/MdmBillForm.do?mdmAdm=Bill"><button type="button" class="btn btn-primary">등록</button></a> -->
<!-- 				<a href="#;" onclick="return setCheck('Y');"><button type="button" class="btn btn-success">게시</button></a> -->
<!-- 				<a href="#;" onclick="return setCheck('N');"><button type="button" class="btn btn-danger">미게시</button></a> -->
<!-- 				<a href="#;" onclick="return setDelete('D');"><button type="button" class="btn btn-danger">삭제</button></a> -->
<!-- 				<a href="#;" onclick="return setDelete('U');"><button type="button" class="btn btn-primary">복구</button></a> -->
<!-- 			</p> -->
				
			<div class="page">
				총 건수 : <fmt:formatNumber value="${billListTotCnt}" groupingUsed="true"/>건
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
			
			<table class="table table-striped table-bordered table-hover"  id="listTable">
					<colgroup>
						<col width="5%" />
						<col />
						<col />
						<col />
						<col />
						<col />
						<col />
						<col />
						<col />
						<col />
						<col />
						<col />
						<col /> 
						<col /> 
						<col /> 
					</colgroup>
					<thead>
						<tr>
							<th><input type="checkbox" name="checkAll" id="checkAll" onclick="getCheckAll();" /></th>
							<th>번호</th>
							<th value="RASMBLY_NM" style="cursor: pointer;">
								의회명
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'RASMBLY_NM ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'RASMBLY_NM DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="RASMBLY_NUMPR" style="cursor: pointer;">
								대수
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'RASMBLY_NUMPR ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'RASMBLY_NUMPR DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="BI_NO" style="cursor: pointer;">
								의안번호
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'BI_NO ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'BI_NO DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="BI_SJ" style="cursor: pointer;">
								의안명
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'BI_SJ ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'BI_SJ DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="MTGNM" style="cursor: pointer;">
								소관위원회
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'MTGNM ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'MTGNM DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="PROPSR" style="cursor: pointer;">
								제안자
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'PROPSR ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'PROPSR DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
<!-- 							<th value="FILECNT" style="cursor: pointer;"> -->
<!-- 								첨부파일 -->
<!-- 								<span class="align"> -->
<%-- 									<c:if test="${mdmSearchVO.sort == 'FILECNT ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if> --%>
<%-- 									<c:if test="${mdmSearchVO.sort == 'FILECNT DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if> --%>
<!-- 								</span> -->
<!-- 							</th> -->
							<th value="ITNC_DE" style="cursor: pointer;">
								제안일
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'ITNC_DE ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'ITNC_DE DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="FRST_REGIST_DT" style="cursor: pointer;">
								수집일
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'FRST_REGIST_DT ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'FRST_REGIST_DT DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="CUD_CODE" style="cursor: pointer;">
								삭제
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'CUD_CODE ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'CUD_CODE DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="BI_CN" style="cursor: pointer;">
								문서번호
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'BI_CN ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'BI_CN DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th>서비스링크</th>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach var="mList" items="${billList}" varStatus="status">

						<tr>
							<td>
								<input type="checkbox" name="isview" id="isview${status.count}" value="${mList.BI_CN}" />
								<input type="hidden" value="${mList.BI_ID}" >
								<input type="hidden" name="RASMBLY_ID" value="${mList.RASMBLY_ID}" >
							</td>
							<td style="text-align:center">${listStartNo + status.count}</td>
							<td>${mList.RASMBLY_NM}</td><!-- 의회명 -->
							<td><c:if test="${mList.RASMBLY_NUMPR > 0}">${mList.RASMBLY_NUMPR}대</c:if></td>
							<td>${mList.BI_NO}</td>
							<td>
								<%-- <a href="#;" onclick="biView('${mList.BI_CN}','${mdmSearchVO.pageNum}');">${mList.BI_SJ }</a> --%>
<%-- 								<a href="#;" onclick="window.open('/mdm/MdmBillView1.do?cnId=${mList.BI_CN}','메타데이터보기','width=1366,height=748,scrollbars=yes,resizable=yes');">${mList.BI_SJ}</a> --%>
								<a href="#;" onclick="fn_detailView('/mdm/MdmBillView1.do?cnId=${mList.BI_CN}','메타데이터보기','width=1366,height=748,scrollbars=yes,resizable=yes');">${mList.BI_SJ}</a>
							</td>
							<td>${mList.MTGNM}</td>
							<td>${mList.PROPSR}</td>
<!-- 							<td> -->
<%-- 								<c:if test="${mList.FILECNT > 0}"> --%>
<%-- 									<a href="#;" class="myButton" onclick="getSideMenu('FILE','${mList.BI_CN}','','','','');"> --%>
<%-- 										<c:if test="${mList.FILESUCCCNT > 0}">성공(${mList.FILESUCCCNT})</c:if> --%>
<%-- 									 	<c:if test="${mList.FILEFLRCNT > 0}">실패(<font color="red">${mList.FILEFLRCNT}</font>)</c:if> --%>
<%-- 									 	<c:if test="${mList.FILECNT - mList.FILESUCCCNT - mList.FILEFLRCNT > 0}">대기(${mList.FILECNT - mList.FILESUCCCNT - mList.FILEFLRCNT})</c:if> --%>
<!-- 									</a> -->
<%-- 								</c:if> --%>
<!-- 							</td> -->
							<td>
								<c:choose>
									<c:when test="${fn:length(mList.ITNC_DE) == 8}">
										${fn:substring(mList.ITNC_DE, 0, 4)}-${fn:substring(mList.ITNC_DE, 4, 6)}-${fn:substring(mList.ITNC_DE, 6, 8)}
									</c:when>
									<c:otherwise>${mList.ITNC_DE}</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<c:choose>
									<c:when test="${fn:length(mList.FRST_REGIST_DT) > 8}">
										${fn:substring(mList.FRST_REGIST_DT, 0, 4)}-${fn:substring(mList.FRST_REGIST_DT, 4, 6)}-${fn:substring(mList.FRST_REGIST_DT, 6, 8)}
									</c:when>
									<c:otherwise>${mList.REGDATE}</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<span id="delmsg${mList.BI_CN}">
									<c:if test="${mList.CUD_CODE == 'D'}"><font color='red'>삭제</font></c:if>
								</span>
							</td>
							<td>${mList.BI_CN}</td><!-- 문서번호  -->
							<td><a href="http://clik.nanet.go.kr/potal/search/searchView.do?collection=bill&DOCID=${mList.BI_CN}" target="_blank">서비스링크</a></td>
						</tr>
						
					</c:forEach>
					
					</tbody>
				</table>

				<c:if test="${fn:length(billList) < 1}">
					<p style="text-align:center">검색된 결과가 없습니다.</p>
				</c:if>

<!-- 				<p class="tr"> -->
<%-- 					<c:if test="${billList != null && fn:length(billList) > 0}"> --%>
<!-- 						<span class="fl"> <a href="#;" -->
<!-- 							onclick="return insertStdCntcApiColct();"><button type="button" class="btn btn-success">재수집</button></a> -->
<!-- 						</span> -->
<%-- 					</c:if> --%>
<!-- 					<a href="/mdm/MdmBillForm.do?mdmAdm=Bill"><button type="button" class="btn btn-primary">등록</button></a> -->
<!-- 					<a href="#;" onclick="return setCheck('Y');"><button type="button" class="btn btn-success">게시</button></a> -->
<!-- 					<a href="#;" onclick="return setCheck('N');"><button type="button" class="btn btn-danger">미게시</button></a> -->
<!-- 					<a href="#;" onclick="return setDelete('D');"><button type="button" class="btn btn-danger">삭제</button></a> -->
<!-- 					<a href="#;" onclick="return setDelete('U');"><button type="button" class="btn btn-primary">복구</button></a> -->
<!-- 				</p> -->

			<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
				<ul class="pagination">
					${paginationInfo}
				</ul> 
			</div><!--//dataTables_paginate-->

<div id="myDiv" style="display:none; height:400px;">
	<div style="padding:5px; float:auto; background:#efefef;  text-align:center; margin-bottom:5px;"><a href="#" onclick="getSlideShowHidden();"><img src="/images/clikmng/mdm/btn_close.gif"></a></div>
	<div id="slideId" style="clear:both; height:330px; width:490px; overflow:auto;"></div>
</div>			
<div class="spinner"></div>

</body>
</html>
