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
<title>지방의회 의원정보</title>
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

function asembyView(rasmblyId,rasmblyNumpr,asembyId,pageNum) {
	$('#rasmblyId').val(rasmblyId);
	$('#rasmblyNumpr').val(rasmblyNumpr);
	$('#asembyId').val(asembyId);
	$('#pageNum').val(pageNum);
	pgfrm.action = "/mdm/MdmAsmblyAsembyView.do";
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
		if( !confirm("의원정보를 " + chkmsg + "하시겠습니까?") ) {
			return false;
		}

  		$.ajax({
			url:"/mdm/MdmTnsrAsmblyAsembyIsView.do",
			cache:false,
			type:"post",
			data:{"ISVIEW":isview},
			success: function(data, status) {
				msg = JSON.parse(data);
				if( msg.rst == "yes" ) {
					alert("의원정보를 수정하였습니다.");
					for(var i = 0; i < arr.length; i++) {
						$("#" + arr[i]).html(dismsg);
					}
					
					//체크박스 해제
					$("input:checkbox[name='isview']").each(function() {
						$(this).attr('checked',false)
					});
				}
				else {
					alert("의원정보를 수정하는데 실패하였습니다.");
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
		if( !confirm("체크한 의원정보를 " + chkmsg + "하시겠습니까?") ) {
			return false;
		}

  		$.ajax({
			url:"/mdm/MdmTnsrAsmblyAsembyDeleteChk.do",
			cache:false,
			type:"post",
			data:{"ISVIEW":isview},
			success: function(data, status) {
				msg = JSON.parse(data);
				if( msg.rst == "yes" ) {
					alert("의원정보를 " + chkmsg + "하였습니다.");
					for(var i = 0; i < arr.length; i++) {
						$("#" + arr[i]).html(dismsg);
					}
					
					//체크박스 해제
					$("input:checkbox[name='isview']").each(function() {
						$(this).attr('checked',false)
					});
				}
				else {
					alert("의원정보를 " + chkmsg + "하는데 실패하였습니다.");
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

//등록화면
function regist() {
	sfrm.action = "/mdm/MdmAsmblyAsembyForm.do?mdmAdm=AsmblyAsemby";
	document.sfrm.submit();
}

</script>
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

			<h2>메타데이터 목록 (지방의회 의원정보) </h2>
			<form name="sfrm" method="post" action="/mdm/MdmAsmblyAsembyList.do" enctype="multipart/form-data">
			<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
			<input type="hidden" name="schDflt" id="schDflt" value="N" >
			<input type="hidden" name="pageNum" id="sFrmPageNum" value="${mdmSearchVO.pageNum}" >

            <%@ include file="/WEB-INF/jsp/clikMng/mdm/MdmSchDt.jsp" %>
            
<!--             <p class="tr"> -->
<%-- 				<c:if test="${asmblyAsembyActList != null && fn:length(asmblyAsembyActList) > 0}"> --%>
<!-- 					<span class="fl"> <a href="#;" -->
<!-- 						onclick="return insertStdCntcApiColct();"><button type="button" class="btn btn-success">재수집</button></a> -->
<!-- 					</span> -->
<%-- 				</c:if> --%>
<!-- 				<a href="#" onclick="return regist();"><button type="button" class="btn btn-primary">등록</button></a> -->
<!-- 				<a href="#;" onclick="return setCheck('Y');"><button type="button" class="btn btn-success">게시</button></a> -->
<!-- 				<a href="#;" onclick="return setCheck('N');"><button type="button" class="btn btn-danger">미게시</button></a> -->
<!-- 				<a href="#;" onclick="return setDelete('D');"><button type="button" class="btn btn-danger">삭제</button></a> -->
<!-- 				<a href="#;" onclick="return setDelete('U');"><button type="button" class="btn btn-primary">복구</button></a> -->
<!-- 			</p> -->
				
			<div class="page">
				총 건수 : <fmt:formatNumber value="${asmblyAsembyActListTotCnt}" groupingUsed="true"/>건
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
					<colgroup>
						<col width="1%" />
						<col />
						<col />
						<col width="15%" />
						<col />
						<col />
						<col width="15%" />
						<col width="15%" />
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
							<th value="ASEMBY_NM" style="cursor: pointer;">
								의원명
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'ASEMBY_NM ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'ASEMBY_NM DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="BRTHDY" style="cursor: pointer;">
								생년<br>월일
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'BRTHDY ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'BRTHDY DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="RASMBLY_NUMPR" style="cursor: pointer;">
								대수
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'RASMBLY_NUMPR ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'RASMBLY_NUMPR DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="EST_NM" style="cursor: pointer;">
								선거구
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'EST_NM ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'EST_NM DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="PPRTY_NM" style="cursor: pointer;">
								정당명
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'PPRTY_NM ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'PPRTY_NM DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="FRST_REGIST_DT" style="cursor: pointer;">
								수집일
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'FRST_REGIST_DT ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'FRST_REGIST_DT DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="ISVIEW" style="cursor: pointer;">
								게시여부
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'ISVIEW ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'ISVIEW DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="CUD_CODE" style="cursor: pointer;">
								삭제
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'CUD_CODE ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'CUD_CODE DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th value="ASEMBY_CN" style="cursor: pointer;">
								문서번호
								<span class="align">
									<c:if test="${mdmSearchVO.sort == 'ASEMBY_CN ASC'}"> <a href="#none"><i class="fa fa-caret-up"></i></a></c:if>
									<c:if test="${mdmSearchVO.sort == 'ASEMBY_CN DESC'}"> <a href="#none"><i class="fa fa-caret-down"></i></a></c:if>
								</span>
							</th>
							<th>
								서비스링크
							</th>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach var="mList" items="${asmblyAsembyActList}" varStatus="status">

						<tr>
							<td>
								<input type="checkbox" name="isview" id="isview${status.count}" value="${mList.RASMBLY_ID}-${mList.ASEMBY_ID}" />
								<input type="hidden" value="${mList.ASEMBY_ID}" >
								<input type="hidden" value="${mList.RASMBLY_ID}" >
								<input type="hidden" value="${mList.ASEMBY_CN}" >
							</td>
							<td style="text-align:center">${listStartNo + status.count}</td>
							<td>${mList.RASMBLY_NM}</td>
							<td style="text-align:left">
								<c:choose>
									<c:when test="${fn:trim(mList.PHOTO_FILE_PATH) == ''}">
										<img src="http://clik.nanet.go.kr/images/sub/no_image.gif" width="64" height="85" />
									</c:when>
									<c:otherwise><img src="http://clik.nanet.go.kr/clik-data/image${fn:substringAfter(mList.PHOTO_FILE_PATH, 'image')}" width="64" height="85" /></c:otherwise>
								</c:choose> &nbsp;&nbsp;
<%-- 								<a href="#;" onclick="window.open('/mdm/MdmAsmblyAsembyView.do?rasmblyId=${mList.RASMBLY_ID}&asembyId=${mList.ASEMBY_ID}','메타데이터보기','width=1366,height=748,scrollbars=yes,resizable=yes');">${mList.ASEMBY_NM}</a> --%>
<%-- 								<a href="#;" onclick="fn_detailView('/mdm/MdmAsmblyAsembyView.do?rasmblyId=${mList.RASMBLY_ID}&asembyId=${mList.ASEMBY_ID}','메타데이터보기','width=1366,height=748,scrollbars=yes,resizable=yes');">${mList.ASEMBY_NM}</a> --%>
									<a href="#;" onclick="fn_detailView('/mdm/MdmAsmblyAsembyView.do?cnId=${mList.ASEMBY_CN}','메타데이터보기','width=1366,height=748,scrollbars=yes,resizable=yes');">${mList.ASEMBY_NM}</a>
							</td>
							<td>
								<c:choose>
									<c:when test="${fn:length(mList.BRTHDY) > 8}">
										${fn:substring(mList.BRTHDY, 0, 4)}-${fn:substring(mList.BRTHDY, 4, 6)}-${fn:substring(mList.BRTHDY, 6, 8)}
									</c:when>
									<c:otherwise>${mList.BRTHDY}</c:otherwise>
								</c:choose> 
							</td>
							<td>${mList.RASMBLY_NUMPR}</td>
							<td>${mList.EST_NM}</td>						
							<td>${mList.PPRTY_NM}</td>
							<td>
								<c:choose>
									<c:when test="${fn:length(mList.FRST_REGIST_DT) > 8}">
										${fn:substring(mList.FRST_REGIST_DT, 0, 4)}-${fn:substring(mList.FRST_REGIST_DT, 4, 6)}-${fn:substring(mList.FRST_REGIST_DT, 6, 8)}
									</c:when>
									<c:otherwise>${mList.REGDATE}</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<span>
									<c:if test="${mList.ISVIEW == 'N'}"><font color='red'>미게시</font></c:if>
								</span>
							</td>
							<td>
								<span>
									<c:if test="${mList.CUD_CODE == 'D'}"><font color='red'>삭제</font></c:if>
								</span>
							</td>
							<td>${mList.ASEMBY_CN}</td>
							<td><a href="http://clik.nanet.go.kr/potal/search/searchView.do?collection=assemblyinfo&DOCID=${mList.ASEMBY_CN}" target="_blank">서비스링크</a></td>
						</tr>
						
					</c:forEach>

					</tbody>
				</table>

<!-- 				<p class="tr"> -->
<%-- 					<c:if test="${asmblyAsembyActList != null && fn:length(asmblyAsembyActList) > 0}"> --%>
<!-- 						<span class="fl"> <a href="#;" -->
<!-- 							onclick="return insertStdCntcApiColct();"><button type="button" class="btn btn-success">재수집</button></a> -->
<!-- 						</span> -->
<%-- 					</c:if> --%>
<!-- 					<a href="#" onclick="return regist();"><button type="button" class="btn btn-primary">등록</button></a> -->
<!-- 					<a href="#;" onclick="return setCheck('Y');"><button type="button" class="btn btn-success">게시</button></a> -->
<!-- 					<a href="#;" onclick="return setCheck('N');"><button type="button" class="btn btn-danger">미게시</button></a> -->
<!-- 					<a href="#;" onclick="return setDelete('D');"><button type="button" class="btn btn-danger">삭제</button></a> -->
<!-- 					<a href="#;" onclick="return setDelete('U');"><button type="button" class="btn btn-primary">복구</button></a> -->
<!-- 				</p> -->

			<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
				<div align="center">
				<ul class="pagination">
					${paginationInfo}
				</ul>
				</div>
			</div><!--//dataTables_paginate-->
 	
<div id="myDiv" style="display:none; height:400px;">
	<div style="padding:5px; float:auto; background:#efefef;  text-align:center; margin-bottom:5px;"><a href="#" onclick="getSlideShowHidden();"><img src="/images/clikmng/mdm/btn_close.gif"></a></div>
	<div id="slideId" style="clear:both; height:330px; width:490px; overflow:auto;"></div>
</div>			
<div class="spinner"></div>

</body>
</html>
