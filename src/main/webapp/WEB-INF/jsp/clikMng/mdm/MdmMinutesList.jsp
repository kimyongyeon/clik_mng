<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp"%>
<title>지방의회 회의록</title>
<script type="text/javascript"
	src="<c:url value='/js/clikmng/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/js/clikmng/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function() {

	//필드 정렬
	$('#listTable th').click(function() {
		if ($(this).attr('value') == undefined) {
			return false;
		}

		if ($(this).find("span i").length == 0) {
			$('#sort').val($(this).attr('value')+ " DESC");
		} else if ($(this).find("i").attr('class') == "fa fa-caret-down") {
			$('#sort').val($(this).attr('value')+ " ASC");
		} else {
			$('#sort').val("");
		}
		frmSubmit('th');
	});

});

function mView(cnId, pageNum) {
	$('#cnId').val(cnId);
	$('#pageNum').val(pageNum);
	pgfrm.action = "/mdm/MdmMinutesMetaDataView1.do";
	document.pgfrm.submit();
}
function lodding() {
	$(".spinner").show();
	//return true;
}

function getDownload(DOWNID) {
	if (DOWNID == null || DOWNID == "") {
		alert("다운로드할 파일ID가 없습니다.");
		return false;
	}
	document.location.href = "/mdm/MdmMinutesDownLoad.do?DOWNID=" + DOWNID;
}

function getCheckAll() {
	var FLength = document.getElementsByName("isview").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if (FLength == 1) {
		document.getElementById("isview").checked = checkAllValue;
	}

	for (var i = 0; i < FLength; i++) {
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

	if (val == "Y") {
		chkmsg = "게시";
		dismsg = "게시";
	} else {
		chkmsg = "미게시";
		dismsg = "<font color='red'>미게시</font>";
	}

	$("input:checkbox[name='isview']").each(function() {
		if ($(this).is(":checked") == true) {
			if (flg == false) {
				flg = true;
				isview += $(this).val() + "=" + val;
			} else {
				isview += "&" + $(this).val() + "=" + val;
			}
			arr[idx] = "viewmsg" + $(this).val();
			idx++;
		}
	});

	if (isview != "") {
		if (!confirm("체크한 게시물을 " + chkmsg + "하시겠습니까?")) {
			return false;
		}

		$.ajax({
			url : "/mdm/MdmMinutesIsView.do",
			cache : false,
			type : "post",
			data : {
				"ISVIEW" : isview
			},
			success : function(data, status) {
				msg = JSON.parse(data);
				if (msg.rst == "yes") {
					alert("게시를 수정하였습니다.");
					for (var i = 0; i < arr.length; i++) {
						$("#" + arr[i]).html(dismsg);
					}

					//체크박스 해제
					$("input:checkbox[name='isview']").each(function() {
						$(this).attr('checked', false)
					});
				} else {
					alert("게시를 수정하는데 실패하였습니다.");
				}
			},
			error : function(data, status, e) {
				alert(e);
			}
		});
	} else {
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

	if (val == "D") {
		chkmsg = "삭제";
		dismsg = "<font color='red'>삭제</font>";
	} else {
		chkmsg = "복구";
	}

	$("input:checkbox[name='isview']").each(function() {
		if ($(this).is(":checked") == true) {
			if (flg == false) {
				flg = true;
				isview += $(this).val() + "=" + val;
			} else {
				isview += "&" + $(this).val() + "=" + val;
			}
			arr[idx] = "delmsg" + $(this).val();
			idx++;
		}
	});

	if (isview != "") {
		if (!confirm("체크한 게시물을 " + chkmsg + "하시겠습니까?")) {
			return false;
		}

		$.ajax({
			url : "/mdm/MdmMinutesDeleteChk.do",
			cache : false,
			type : "post",
			data : {
				"ISVIEW" : isview
			},
			success : function(data, status) {
				msg = JSON.parse(data);
				if (msg.rst == "yes") {
					alert("게시물을 " + chkmsg + "하였습니다.");
					for (var i = 0; i < arr.length; i++) {
						$("#" + arr[i]).html(dismsg);
					}

					//체크박스 해제
					$("input:checkbox[name='isview']").each(function() {
						$(this).attr('checked', false)
					});
				} else {
					alert("게시물을 " + chkmsg + "하는데 실패하였습니다.");
				}
			},
			error : function(data, status, e) {
				alert(e);
			}
		});
	} else {
		alert(chkmsg + "할 항목을 체크하세요");
	}
	return false;
}
</script>

<script type="text/javaScript" language="javascript" defer="defer">
$(window).load(function() {
	$(".myButton").click(function() {
		// Set the effect type
		var effect = 'slide';
		// Set the options for the effect type chosen
		var options = {
			direction : 'right'
		}; //{ direction: $('.mySelect').val() };
		// Set the duration (default: 400 milliseconds)
		var duration = 500;
		$('#myDiv').toggle(effect, options, duration);
	});
});
function getSideMenu(MINTS_CN) {
	//if( $('#myDiv').is(':hidden') ) {
	//	getSlideShowHidden();
	//}
	$("#slideId").html("");
	$.ajax({
		url : "/mdm/MdmMinutesFileListCmmn.do",
		cache : false,
		type : "get",
		data : {
			"MINTS_CN" : MINTS_CN
		},
		success : function(data, status) {
			$("#slideId").html(data);

		},
		error : function(data, status, e) {
			alert(e);
		}
	});
}
function getSlideShowHidden() {
	// Set the effect type
	var effect = 'slide';
	// Set the options for the effect type chosen
	var options = {
		direction : 'right'
	}; //{ direction: $('.mySelect').val() };
	// Set the duration (default: 400 milliseconds)
	var duration = 500;
	$('#myDiv').toggle(effect, options, duration);
}

function getSlideCheckAll() {
	var FLength = document.getElementsByName("chkid").length;
	var checkAllValue = document.getElementById('slideCheckAll').checked;

	//undefined
	if (FLength == 1) {
		document.getElementsByName("chkid")[0].checked = checkAllValue;
	} else {
		for (var i = 0; i < FLength; i++) {
			document.getElementsByName("chkid")[i].checked = checkAllValue;
		}
	}
}
function setSlideDelCheck() {
	//var mydata = $("#checkform").serialize();
	var msg = "";
	var isview = "";
	var flg = false;

	$("input:checkbox[name='chkid']").each(function() {
		if ($(this).is(":checked") == true) {
			if (flg == false) {
				flg = true;
				isview += $(this).val() + "=D";
			} else {
				isview += "&" + $(this).val() + "=D";
			}
		}
	});

	if (isview != "") {
		if (!confirm("선택한 파일을 삭제하시겠습니까?")) {
			return false;
		}
		$.ajax({
			url : "/mdm/MdmMinutesFileListDelete.do",
			cache : false,
			type : "post",
			data : {
				"ISVIEW" : isview
			},
			success : function(data, status) {
				msg = JSON.parse(data);
				if (msg.rst == "yes") {
					alert("선택한 파일을 삭제하였습니다.");
				} else {
					alert("선택한 파일을 삭제하는데 실패하였습니다.");
				}
			},
			error : function(data, status, e) {
				alert(e);
			}
		});
	} else {
		alert("삭제할 파일을 체크하세요");
	}
	return false;
}
function setSlideReCnvrsCheck() {
	//var mydata = $("#checkform").serialize();
	var msg = "";
	var isview = "";
	var flg = false;

	$("input:checkbox[name='chkid']").each(function() {
		if ($(this).is(":checked") == true) {
			if (flg == false) {
				flg = true;
				isview += $(this).val() + "=0";
			} else {
				isview += "&" + $(this).val() + "=0";
			}
		}
	});

	if (isview != "") {
		if (!confirm("선택한 파일을 재변환하시겠습니까?")) {
			return false;
		}
		$.ajax({
			url : "/mdm/MdmMinutesFileListReCnvrs.do",
			cache : false,
			type : "post",
			data : {
				"ISVIEW" : isview
			},
			success : function(data, status) {
				msg = JSON.parse(data);
				if (msg.rst == "yes") {
					alert("선택한 파일을 재변환하였습니다.");
				} else {
					alert("선택한 파일을 재변환하는데 실패하였습니다.");
				}
			},
			error : function(data, status, e) {
				alert(e);
			}
		});
	} else {
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
	position: absolute;
	right: 340px;
	top: 400px;
	background-color: #fff;
	border: 3px solid #b9b9b9;
	display: block;
	text-align: justify;
}

#myDiv p {
	margin: 15px;
	font-size: 0.917em;
}

.slidesession {
	padding-left: 6px;
}

.slidesession table {
	width: 475px;
	border: 0;
	border-spacing: 0;
	border-collapse: collapse;
	border-top: 2px solid #a96824;
}

.slidesession table thead th {
	height: 25px;
	padding-top: 6px;
	padding-bottom: 6px;
	background: url(/images/clikmng/mdm/th_bg.gif) no-repeat right top;
	border-bottom: 1px solid #aaa;
	color: #a96824;
	text-align: center;
	font-weight: bold;
	vertical-align: top;
}

.slidesession table thead th.end {
	background: url(/images/clikmng/mdm/th_bg.gif) no-repeat left top;
}

.slidesession table tbody th {
	padding: 6px 0 9px 0;
	line-height: 130%;
	border-bottom: 1px solid #ccc;
	border-right: 1px solid #fafafa;
	font-weight: normal;
}

.slidesession table tbody td {
	padding: 6px 0 9px 0;
	line-height: 130%;
	border-bottom: 1px solid #ccc;
	text-align: center;
}

.slidesession table tbody td.left {
	text-align: left;
	padding-left: 0px;
}

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

		<h2>메타데이터 목록 (지방의회 회의록)</h2>
		<form name="sfrm" method="post" action="/mdm/MdmMinutesList.do"
			enctype="multipart/form-data">
			<input type="hidden" name="cal_url" id="cal_url"
				value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>"> <input
				type="hidden" name="schDflt" id="schDflt" value="N"> <input
				type="hidden" name="pageNum" id="sFrmPageNum"
				value="${mdmSearchVO.pageNum}">

			<%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchDt.jsp"%>
			
<!-- 			<p class="tr"> -->
<%-- 				<c:if test="${minutesList != null && fn:length(minutesList) > 0}"> --%>
<!-- 					<span class="fl"> <a href="#;" -->
<!-- 						onclick="return insertStdCntcApiColct();"><button type="button" class="btn btn-success">재수집</button></a> -->
<!-- 					</span> -->
<%-- 				</c:if> --%>
<!-- 				<a href="/mdm/MdmMinutesForm.do?mdmAdm=Minutes"><button type="button" class="btn btn-primary">등록</button></a> -->
<!-- 				<a href="#;" onclick="return setCheck('Y');"><button type="button" class="btn btn-success">게시</button></a>  -->
<!-- 				<a href="#;" onclick="return setCheck('N');"><button type="button" class="btn btn-danger">미게시</button></a>  -->
<!-- 				<a href="#;" onclick="return setDelete('D');"><button type="button" class="btn btn-danger">삭제</button></a>  -->
<!-- 				<a href="#;" onclick="return setDelete('U');"><button type="button" class="btn btn-primary">복구</button></a> -->
<!-- 			</p> -->
		
			<div class="page">
				총 건수 :
				<fmt:formatNumber value="${minutesListTotCnt}" groupingUsed="true" />
				건 <span>
					<button type="button" id="btnTextDownload" style="display: none;"
						class="btn btn-default" onclick="fnText();">TEXT</button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" id="btnExcelDownload" style="display: none;"
						class="btn btn-default" onclick="fnExcel();">EXCEL</button>&nbsp;&nbsp;&nbsp;&nbsp;
					출력건수 <select name="listCnt" id="listCnt"
					aria-controls="dataTables-example" class=" input-sm">
						<option value="10"
							<c:if test="${mdmSearchVO.listCnt == '10'}"> selected="selected"</c:if>>10</option>
						<option value="30"
							<c:if test="${mdmSearchVO.listCnt == '30'}"> selected="selected"</c:if>>30</option>
						<option value="50"
							<c:if test="${mdmSearchVO.listCnt == '50'}"> selected="selected"</c:if>>50</option>
						<option value="100"
							<c:if test="${mdmSearchVO.listCnt == '100'}"> selected="selected"</c:if>>100</option>
				</select>
				</span>
			</div>

		</form>

		<table class="table table-striped table-bordered table-hover "
			id="listTable">
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
					<th><input type="checkbox" name="checkAll" id="checkAll"
						onclick="getCheckAll();" /></th>
					<th style="width: 5%;">번호</th>
					<th value="RASMBLY_NM" style="cursor: pointer;">의회명 <span class="align"> <c:if
								test="${mdmSearchVO.sort == 'RASMBLY_NM ASC'}">
								<a href="#none"><i class="fa fa-caret-up"></i></a>
							</c:if> <c:if test="${mdmSearchVO.sort == 'RASMBLY_NM DESC'}">
								<a href="#none"><i class="fa fa-caret-down"></i></a>
							</c:if>
					</span>
					</th>
					<th value="RASMBLY_NUMPR" style="cursor: pointer;">대수 <span class="align"> <c:if
								test="${mdmSearchVO.sort == 'RASMBLY_NUMPR ASC'}">
								<a href="#none"><i class="fa fa-caret-up"></i></a>
							</c:if> <c:if test="${mdmSearchVO.sort == 'RASMBLY_NUMPR DESC'}">
								<a href="#none"><i class="fa fa-caret-down"></i></a>
							</c:if>
					</span>
					</th>
					<th value="RASMBLY_SESN" style="cursor: pointer;">회기 <span class="align"> <c:if
								test="${mdmSearchVO.sort == 'RASMBLY_SESN ASC'}">
								<a href="#none"><i class="fa fa-caret-up"></i></a>
							</c:if> <c:if test="${mdmSearchVO.sort == 'RASMBLY_SESN DESC'}">
								<a href="#none"><i class="fa fa-caret-down"></i></a>
							</c:if>
					</span>
					</th>
					<th value="MINTS_ODR" style="cursor: pointer;">차수 <span class="align"> <c:if
								test="${mdmSearchVO.sort == 'MINTS_ODR ASC'}">
								<a href="#none"><i class="fa fa-caret-up"></i></a>
							</c:if> <c:if test="${mdmSearchVO.sort == 'MINTS_ODR DESC'}">
								<a href="#none"><i class="fa fa-caret-down"></i></a>
							</c:if>
					</span>
					</th>
					<th  style="cursor: pointer;" 
						<c:choose>
								<c:when test="${flg == 'APP'}"></c:when>
								<c:otherwise>
								 value="MTGNM"
								</c:otherwise>
							</c:choose>>
						<c:choose>
							<c:when test="${flg == 'APP'}">별첨제목</c:when>
							<c:otherwise>
										회의명
										<span class="align"> <c:if
										test="${mdmSearchVO.sort == 'MTGNM ASC'}">
										<a href="#none"><i class="fa fa-caret-up"></i></a>
									</c:if> <c:if test="${mdmSearchVO.sort == 'MTGNM DESC'}">
										<a href="#none"><i class="fa fa-caret-down"></i></a>
									</c:if>
								</span>
							</c:otherwise>
						</c:choose>
					</th>
<%-- 					<th value="MINTS_FILE_PATH" style="cursor: pointer;">첨부파일 <span class="align"> <c:if --%>
<%-- 								test="${mdmSearchVO.sort == 'MINTS_FILE_PATH ASC'}"> --%>
<!-- 								<a href="#none"><i class="fa fa-caret-up"></i></a> -->
<%-- 							</c:if> <c:if test="${mdmSearchVO.sort == 'MINTS_FILE_PATH DESC'}"> --%>
<!-- 								<a href="#none"><i class="fa fa-caret-down"></i></a> -->
<%-- 							</c:if> --%>
<!-- 					</span> -->
<!-- 					</th> -->
					<th value="MTG_DE" style="cursor: pointer;">등록일 <span class="align"> <c:if
								test="${mdmSearchVO.sort == 'MTG_DE ASC'}">
								<a href="#none"><i class="fa fa-caret-up"></i></a>
							</c:if> <c:if test="${mdmSearchVO.sort == 'MTG_DE DESC'}">
								<a href="#none"><i class="fa fa-caret-down"></i></a>
							</c:if>
					</span>
					</th>
					<th value="FRST_REGIST_DT" style="cursor: pointer;">수집일 <span class="align"> <c:if
								test="${mdmSearchVO.sort == 'FRST_REGIST_DT ASC'}">
								<a href="#none"><i class="fa fa-caret-up"></i></a>
							</c:if> <c:if test="${mdmSearchVO.sort == 'FRST_REGIST_DT DESC'}">
								<a href="#none"><i class="fa fa-caret-down"></i></a>
							</c:if>
					</span>
					</th>
					<th value="CUD_CODE" style="cursor: pointer;">삭제 <span class="align"> <c:if
								test="${mdmSearchVO.sort == 'CUD_CODE ASC'}">
								<a href="#none"><i class="fa fa-caret-up"></i></a>
							</c:if> <c:if test="${mdmSearchVO.sort == 'CUD_CODE DESC'}">
								<a href="#none"><i class="fa fa-caret-down"></i></a>
							</c:if>
					</span>
					</th>
					<th value="MINTS_CN" style="cursor: pointer;">문서번호 <span class="align"> <c:if
								test="${mdmSearchVO.sort == 'MINTS_CN ASC'}">
								<a href="#none"><i class="fa fa-caret-up"></i></a>
							</c:if> <c:if test="${mdmSearchVO.sort == 'MINTS_CN DESC'}">
								<a href="#none"><i class="fa fa-caret-down"></i></a>
							</c:if>
					</span>
					</th>
					<th>서비스링크</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="mList" items="${minutesList}" varStatus="status">
					<tr>
						<td>
							<input type="checkbox" name="isview" id="isview${status.count}" value="${mList.MINTS_CN}" />
							<input type="hidden" value="${mList.RASMBLY_ID}#${mList.RASMBLY_NUMPR}#${mList.SESN_SE_STDCD}#${mList.RASMBLY_SESN}#${mList.MTGNM_ID}#${mList.MINTS_ODR}#${mList.MINTS_SN}" >
							<input type="hidden" name="RASMBLY_ID" value="${mList.RASMBLY_ID}" >
						</td>
						<td style="text-align: center">${listStartNo + status.count}</td>
						<td>${mList.RASMBLY_NM}</td>
						<td>${mList.RASMBLY_NUMPR}대</td>
						<td><c:choose>
								<c:when test="${mList.RASMBLY_SESN < '1991'}"> 제${mList.RASMBLY_SESN }회 </c:when>
								<c:otherwise>${mList.RASMBLY_SESN }년도</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${mList.MINTS_ODR > '0'}">제${mList.MINTS_ODR }차</c:when>
								<c:otherwise>${mList.ODR_NM }</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${flg == 'APP'}">${mList.APNDX_FILE_NM}</c:when>
								<c:otherwise>
									<a href="#;"
										onclick="fn_detailView('/mdm/MdmMinutesMetaDataView1.do?cnId=${mList.MINTS_CN}','메타데이터보기','width=1366,height=748,scrollbars=yes,resizable=yes');">${mList.MTGNM}</a>
								</c:otherwise>
							</c:choose></td>
<%-- 						<td><c:if --%>
<%-- 								test="${mList.MINTS_FILE_PATH != null || mList.FILECNT > 0}"> --%>
<!-- 								<a href="#;" class="myButton" -->
<%-- 									onclick="getSideMenu('${mList.MINTS_CN}');"> <c:if --%>
<%-- 										test="${mList.MINTS_FILE_PATH != null}"> --%>
<!-- 										[원문 : -->
<%-- 											<c:choose> --%>
<%-- 											<c:when --%>
<%-- 												test="${mList.DOC_CNVR_STTU_CODE == '1' || mList.DOC_CNVR_STTU_CODE == '3'}">성공</c:when> --%>
<%-- 											<c:when test="${mList.DOC_CNVR_STTU_CODE == '2'}"> --%>
<!-- 												<font color="red">실패</font> -->
<%-- 											</c:when> --%>
<%-- 											<c:otherwise>대기</c:otherwise> --%>
<%-- 										</c:choose> --%>
<!-- 										]<br /> -->
<%-- 									</c:if> <c:if test="${mList.FILECNT > 0}"> --%>
<!-- 										[부록 : -->
<%-- 											성공(${mList.FILESUCCCNT})  --%>
<%-- 											실패(<font color="red">${mList.FILEFLRCNT}</font>)  --%>
<%-- 											대기(${mList.FILECNT - mList.FILESUCCCNT - mList.FILEFLRCNT}) --%>
<!-- 										] -->
<%-- 									</c:if> --%>
<!-- 								</a> -->
<%-- 							</c:if></td> --%>
						<td><c:choose>
								<c:when test="${fn:length(mList.MTG_DE) == 8}">
										${fn:substring(mList.MTG_DE, 0, 4)}-${fn:substring(mList.MTG_DE, 4, 6)}-${fn:substring(mList.MTG_DE, 6, 8)}
									</c:when>
								<c:otherwise>${mList.MTG_DE}</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${fn:length(mList.FRST_REGIST_DT) > 8}">
										${fn:substring(mList.FRST_REGIST_DT, 0, 4)}-${fn:substring(mList.FRST_REGIST_DT, 4, 6)}-${fn:substring(mList.FRST_REGIST_DT, 6, 8)}
									</c:when>
								<c:otherwise>${mList.REGDATE}</c:otherwise>
							</c:choose></td>
						<td><span id="delmsg${mList.MINTS_CN}"> <c:if
									test="${mList.CUD_CODE == 'D'}">
									<font color='red'>삭제</font>
								</c:if>
						</span></td>
						<td><a href="#;"
							onclick="window.open('/minutesviewer/MinutesHtmlView.do?MINTS_CN=${mList.MINTS_CN}','회의록보기','width=1366,height=768,scrollbars=yes,resizable=yes');">
								${mList.MINTS_CN} </a></td>
						<td>
							<a href="http://clik.nanet.go.kr/potal/search/searchView.do?collection=minutes&DOCID=${mList.MINTS_CN}" target="_blank">서비스링크</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<c:if test="${fn:length(minutesList) < 1}">
			<p style="text-align: center">검색된 결과가 없습니다.</p>
		</c:if>

<!-- 		<p class="tr"> -->
<%-- 			<c:if test="${minutesList != null && fn:length(minutesList) > 0}"> --%>
<!-- 				<span class="fl">  -->
<!-- 				<a href="#;"onclick="return insertStdCntcApiColct();"><button type="button" class="btn btn-success">재수집</button></a> -->
<!-- 				</span> -->
<%-- 			</c:if> --%>
<!-- 			<!-- <a href="/mdm/MdmMinutesForm.do?mdmAdm=Minutes"><button type="button" class="btn btn-primary">등록</button></a> -->
<!-- 			<a href="#;" onclick="return setCheck('Y');"><button type="button" class="btn btn-success">게시</button></a>  -->
<!-- 			<a href="#;" onclick="return setCheck('N');"><button type="button" class="btn btn-danger">미게시</button></a>  -->
<!-- 			<a href="#;" onclick="return setDelete('D');"><button type="button" class="btn btn-danger">삭제</button></a>  -->
<!-- 			<a href="#;" onclick="return setDelete('U');"><button type="button" class="btn btn-primary">복구</button></a> -->
<!-- 		</p> -->

		<div class="dataTables_paginate paging_simple_numbers"
			id="dataTables-example_paginate">
			<ul class="pagination">${paginationInfo}
			</ul>
		</div>

	</div>
	<!--//dataTables_paginate-->

	<div id="myDiv" style="display: none; height: 400px;">
		<div
			style="padding: 5px; float: auto; background: #efefef; text-align: center; margin-bottom: 5px;">
			<a href="#" onclick="getSlideShowHidden();"><img
				src="/images/clikmng/mdm/btn_close.gif"></a>
		</div>
		<div id="slideId"
			style="clear: both; height: 330px; width: 490px; overflow: auto;"></div>
	</div>
	<div class="spinner"></div>

</body>
</html>
