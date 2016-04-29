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
<title>지역현안소식</title>
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

function piView(extId,pageNum) {
	$('#extId').val(extId);
	$('#pageNum').val(pageNum);
	pgfrm.action = "/mdm/MdmRegionNewsView.do";
	document.pgfrm.submit();
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
			url:"/mdm/MdmRegionNewsIsView.do",
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
			url:"/mdm/MdmRegionNewsDeleteChk.do",
			cache:false,
			type:"post",
			data:{"ISVIEW":isview},
			success: function(data, status) {
				msg = JSON.parse(data);
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
		// Set the effect type
		var effect = 'slide';
		// Set the options for the effect type chosen
		var options = { direction: 'right' }; //{ direction: $('.mySelect').val() };
		// Set the duration (default: 400 milliseconds)
		var duration = 500;
		$('#myDiv').toggle(effect, options, duration);
	});
});
function getSideMenu(EXTID) {
	$("#slideId").html("");
	//if( $('#myDiv').is(':hidden') ) {
	//	getSlideShowHidden();
	//}
	$("#slideId").html("");
	$.ajax({
		url:"/mdm/MdmRegionNewsListCmmn.do",
		cache:false,
		type:"get",
		data:{"EXTID":EXTID},
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
function setSlideDelCheck() {
	//var mydata = $("#checkform").serialize();
	var msg = "";
	var isview = "";
	var flg = false;

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
			url:"/mdm/MdmRegionNewsDeleteChk.do",
			cache:false,
			type:"post",
			data:{"ISVIEW":isview},
			success: function(data, status) {
				msg = JSON.parse(data);
				if( msg.rst == "yes" ) {
					alert("선택한 목록을 삭제하였습니다.");
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

			<h2>메타데이터 목록 (지역현안소식) </h2>
			<form name="sfrm" method="post" action="/mdm/MdmRegionNewsList.do" enctype="multipart/form-data">
			<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
			<input type="hidden" name="schDflt" id="schDflt" value="N" >
			<input type="hidden" name="pageNum" id="sFrmPageNum" value="${mdmSearchVO.pageNum}" >

            <%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchDt.jsp" %>

<!-- 			<p class="tr"> -->
<!-- 				<a href="/mdm/MdmRegionNewsForm.do?mdmAdm=RegionNews"><button type="button" class="btn btn-primary">등록</button></a> -->
<!-- 				<a href="#;" onclick="return setCheck('Y');"><button type="button" class="btn btn-success">게시</button></a> -->
<!-- 				<a href="#;" onclick="return setCheck('N');"><button type="button" class="btn btn-danger">미게시</button></a> -->
<!-- 				<a href="#;" onclick="return setDelete('D');"><button type="button" class="btn btn-danger">삭제</button></a> -->
<!-- 				<a href="#;" onclick="return setDelete('U');"><button type="button" class="btn btn-primary">복구</button></a> -->
<!-- 			</p> -->
				
			<div class="page">
				총 건수 : <fmt:formatNumber value="${regionNewsListTotCnt}" groupingUsed="true"/>건 
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
							<th value="NEWS_ID" style="cursor: pointer;">
								문서번호
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'NEWS_ID ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'NEWS_ID DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="SEED_NM" style="cursor: pointer;">
								수집기관
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'SEED_NM ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'SEED_NM DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="REGION_NM" style="cursor: pointer;">
								지역
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'REGION_NM ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'REGION_NM DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="TITLE" style="cursor: pointer;">
								제목
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'TITLE ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'TITLE DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="WRITER" style="cursor: pointer;">
								작성자
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'WRITER ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'WRITER DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="INDT" style="cursor: pointer;">
								등록일자
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'INDT ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'INDT DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="REGDATE" style="cursor: pointer;">
								수집일자
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'REGDATE ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'REGDATE DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="ISVIEW" style="cursor: pointer;">
								게시
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'ISVIEW ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'ISVIEW DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="CUD" style="cursor: pointer;">
								삭제
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'CUD ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'CUD DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="DUPCNT" style="cursor: pointer;">
								중복
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'DUPCNT ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'DUPCNT DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th>서비스링크</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="mList" items="${regionNewsList}" varStatus="status">
						<tr>
							<td><input type="checkbox" name="isview" id="isview${status.count}" value="${mList.NEWS_ID}" /></td>
							<td style="text-align:center">${listStartNo + status.count}</td>
							<td>${mList.NEWS_ID}</td>
							<td>${mList.SEED_NM}</td>
							<td>${mList.REGION_NM}</td>
							<td>
								<a href="#;" onclick="fn_detailView('/mdm/MdmRegionNewsView1.do?extId=${mList.NEWS_ID}','메타데이터보기','width=1366,height=748,scrollbars=yes,resizable=yes');">${mList.TITLE}</a>
							</td>
							<td>${mList.WRITER}</td>
							<td>
								<c:choose>
									<c:when test="${fn:length(mList.INDT) > 8}">
										${fn:substring(mList.INDT, 0, 4)}-${fn:substring(mList.INDT, 5, 7)}-${fn:substring(mList.INDT, 8, 10)}
									</c:when>
									<c:otherwise>${mList.INDT}</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<c:choose>
									<c:when test="${fn:length(mList.REGDATE) > 8}">
										${fn:substring(mList.REGDATE, 0, 4)}-${fn:substring(mList.REGDATE, 5, 7)}-${fn:substring(mList.REGDATE, 8, 10)}
									</c:when>
									<c:otherwise>${mList.REGDATE}</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<span id="viewmsg${mList.NEWS_ID}">
									<c:choose>
										<c:when test="${mList.ISVIEW == 'N'}"><font color='red'>미게시</font></c:when>
										<c:otherwise>게시</c:otherwise>
									</c:choose>
								</span>
							</td>
							<td>
								<span id="delmsg${mList.NEWS_ID}">
									<c:if test="${mList.CUD == 'D'}"><font color='red'>삭제</font></c:if>
								</span>
							</td>
							<td>
								<c:choose>
									<c:when test="${mList.DUPCNT > 1}"><a href="#;" class="myButton" onclick="getSideMenu('DPLCT', '${mList.DUPLICATION}');">중복(${mList.DUPCNT})</a></c:when>
									<c:otherwise>N</c:otherwise>
								</c:choose>
							</td>
							<td>
								<a href="http://clik.nanet.go.kr/potal/search/searchList.do?collection=news&DOCID=${mList.NEWS_ID}" target="_blank">서비스링크</a>
							</td>
						</tr>
					</c:forEach>

					</tbody>
				</table>

<!-- 				<p class="tr"> -->
<!-- 					<a href="/mdm/MdmRegionNewsForm.do?mdmAdm=RegionNews"><button type="button" class="btn btn-primary">등록</button></a> -->
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
