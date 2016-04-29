<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>지방의회/지자체 담당자 상세보기 및 수정</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="localMngManage" staticJavascript="false" xhtml="true" cdata="false"/>
 
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
* 초기 데이터 입력
******************************************************** */
$( document ).ready(function() {
	/******************************************
	 * 관심의회 추가/삭제
	 ******************************************/	
	$("#btnAdd").on("click",function() {
    	
    	if($('#infoTable tr').length > 2) {
    		alert('관심의회는 3개 이상 등록하실 수 없습니다.');
    		return;
    	}
    	
        // clone
        //$.trClone = $("#infoTable tr:last").clone().html();
		var newTr="";
        newTr += '<tr>';
        newTr += '<td>';
   		newTr += '<select name="selRasmbly" id="selRasmbly">';
   		newTr += '<option>의회선택</option>';
   		newTr += '<option value="INTSTTCL_000023">광역의회</option>';
   		newTr += '<option value="INTSTTCL_000024">기초의회</option>';
   		newTr += '</select>&nbsp;';			
   		newTr += '<select name="selLocal" id="selLocal">';
   		newTr += '<option disabled="disabled" >지역 선택</option>';
   		newTr += '</select>&nbsp;';
   		newTr += '<select name="selAssembly" id="selAssembly">';
   		newTr += '<option disabled="disabled" >지방의회 선택</option>';
   		newTr += '</select>';
   	    newTr += '</td>';
   	    newTr += '<td>';
   	    newTr += '<input type="button" name="btnAdd" id="btnAdd" value="추가" />';
   	    newTr += '</td>';
        newTr += '</tr>';

        //$.newTr = $("<tr>"+$.trClone+"</tr>");
        
        // append
        $("#infoTable").append(newTr);

        // delete Button 추가
        $.btnDel = $(document.createElement("input"));
        $.btnDel.attr({
            name : "btnRemove",
            type : "button" ,
            value : "삭제"
        });
        $("#infoTable tr:last td:last").html("");
        $("#infoTable tr:last td:last").append($.btnDel);

        // 버튼에 클릭 이벤트 추가
        $("#infoTable tr>td:last>input[type='button']").on('click', function(){
            $(this).parent().parent().remove();
        });
        
        var $select = $('[name="selRasmbly"]').last();
        $select.on('change', function () {
        	var i = $('[name="selRasmbly"]').index(this);
        	var value = $('[name="selRasmbly"]').eq(i).val();
        	regionChange(value, 1, i);
        });
        
        var $select = $('[name="selLocal"]').last();
        $select.on('change', function () {
        	var i = $('[name="selLocal"]').index(this);
        	var value = $('[name="selLocal"]').eq(i).val();
        	assemblyChange(value, 1, i);
        });        
        
    });	
	
	
	// 기관유형 선택
	insttTyChange('<c:out value="${mngVO.fInsttClCode}" />', 0);
	
	// 광역시군구 선택
	localChange('<c:out value="${mngVO.insttClCode}" />', 0);
	
	// 소속 선택
	psitnChange('<c:out value="${mngVO.brtcCode}" />', 0);

	// 관심의회 시군구 선택
	if('${mngVO.intrstRasmblyId1}' != ""){
		regionChange('<c:out value="${mngVO.intRasmblyInsttClCode1}" />', 0, 0);
		assemblyChange('<c:out value="${mngVO.intRasmblyBrtc1}" />', 0, 0);
	}
	if('${mngVO.intrstRasmblyId2}' != ""){
		$("#btnAdd").click();
		regionChange('<c:out value="${mngVO.intRasmblyInsttClCode2}" />', 0, 1);
		assemblyChange('<c:out value="${mngVO.intRasmblyBrtc2}" />', 0, 1);
	}
	if('${mngVO.intrstRasmblyId3}' != ""){
		$("#btnAdd").click();
		regionChange('<c:out value="${mngVO.intRasmblyInsttClCode3}" />', 0, 2);
		assemblyChange('<c:out value="${mngVO.intRasmblyBrtc3}" />', 0, 2);
	}		
	
    var $select = $('[name="selRasmbly"]');
    $select.on('change', function () {
    	var i = $('[name="selRasmbly"]').index(this);
    	var value = $('[name="selRasmbly"]').eq(i).val();
    	regionChange(value, 1, i);
    });
	
    var $select = $('[name="selLocal"]');
    $select.on('change', function () {
    	var i = $('[name="selLocal"]').index(this);
    	var value = $('[name="selLocal"]').eq(i).val();
    	assemblyChange(value, 1, i);
    });
	
	
});

/******************************************
 * 목록
 ******************************************/

function fnListLocalMng() {
	var f = document.getElementById("localMngManage");
    f.action = "<c:url value='/uss/mng/LocalMngList.do'/>";
    f.submit();
}


/* ********************************************************
* multi selectbox 
******************************************************** */
//-------------------------------------------------------------------기관유형 선택
function insttTyChange(fInsttClCode, type) {
	var fInsttClCode = fInsttClCode;
	
	var insttClCode = '<c:out value="${mngVO.insttClCode}" />';
	
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
	var brtcCode = '<c:out value="${mngVO.brtcCode}" />';
	
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
		insttClCode = '<c:out value="${mngVO.insttClCode}" />';	
	} else {
		insttClCode = $('[name="selInsttTy"]').val();
	}
	
	
   // 소속코드
	var psitnCode = '<c:out value="${mngVO.loasmCode}" />';
	
	var formData = "brtcCode=" + brtcCode + "&insttClCode=" + insttClCode;
	
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


//-------------------------------------------------------------------관심의회 
//광역시도 선택
//type1 - 0:parameter, 1:본페이지 선택, 2 순서
function regionChange(insttClCode, type1, type2) {
	if(type1 == 0) {
		$('select[name="selRasmbly"]').eq(type2).val(insttClCode).attr("selected", "selected");	
	}
	
	var insttClCode = insttClCode;
	var intRasmblyBrtc1 = '<c:out value="${mngVO.intRasmblyBrtc1}" />';
	var intRasmblyBrtc2 = '<c:out value="${mngVO.intRasmblyBrtc2}" />';
	var intRasmblyBrtc3 = '<c:out value="${mngVO.intRasmblyBrtc3}" />';
	
	// 관심의회 쪽 지역선택 시
	$("select[name='selLocal']").eq(type2).find('option').remove();
	$("select[name='selLocal']").eq(type2).append("<option>지역 선택</option>");
	$("select[name='selAssembly']").eq(type2).find('option').remove();
	$("select[name='selAssembly']").eq(type2).append("<option>지방의회 선택</option>");	

	$.ajax({
	   type: "POST",
	   url : "/uss/mng/selectAjaxBrtc.do",
	   dataType:"json", 
	   async : false,
	   success: function(args) {
		   $.each(args, function() {
			   $('select[name="selLocal"]').eq(type2).append("<option value='"+this.codeId+"'>"+this.codeIdNm+"</option>");
			   if(type1 == 0 && type2 == 0) {
				   $('select[name="selLocal"]').eq(type2).val(intRasmblyBrtc1).attr("selected", "selected");
			   }
			   if(type1 == 0 && type2 == 1) {
				   $('select[name="selLocal"]').eq(type2).val(intRasmblyBrtc2).attr("selected", "selected");
			   }
			   if(type1 == 0 && type2 == 2) {
				   $('select[name="selLocal"]').eq(type2).val(intRasmblyBrtc3).attr("selected", "selected");
			   }			   			   
			   
			});
	   }
		,error:function(e) {
			alert(e.responseText);
	   }
	});
	
} 


// 지방의회 셀렉트 박스
function assemblyChange(brtcCode, type1, type2) {
	var f = document.getElementById("localMngManage");
	
	// 기관분류코드
	var intRasmblyInsttClCode1 = '<c:out value="${mngVO.intRasmblyInsttClCode1}" />';
	var intRasmblyInsttClCode2 = '<c:out value="${mngVO.intRasmblyInsttClCode2}" />';
	var intRasmblyInsttClCode3 = '<c:out value="${mngVO.intRasmblyInsttClCode3}" />';
	
    // 소속코드
	var psitnCode1 = '<c:out value="${mngVO.intrstRasmblyId1}" />';
	var psitnCode2 = '<c:out value="${mngVO.intrstRasmblyId2}" />';
	var psitnCode3 = '<c:out value="${mngVO.intrstRasmblyId3}" />';
	
	if(type2 == 0) {
		var formData = "brtcCode=" + brtcCode + "&insttClCode=" + intRasmblyInsttClCode1;	
	}
	if(type2 == 1) {
		var formData = "brtcCode=" + brtcCode + "&insttClCode=" + intRasmblyInsttClCode2;	
	}
	if(type2 == 2) {
		var formData = "brtcCode=" + brtcCode + "&insttClCode=" + intRasmblyInsttClCode3;	
	}
	if(type1 == 1) {
		var _insttClCode = $('select[name="selRasmbly"]').eq(type2).val();
		var formData = "brtcCode=" + brtcCode + "&insttClCode=" + _insttClCode;
	}
	
	// 관심의회 쪽 지역선택 시
	$("select[name='selAssembly']").eq(type2).find('option').remove();
	$("select[name='selAssembly']").eq(type2).append("<option>지방의회 선택</option>");	

	$.ajax({
	   type: "POST",
	   url : "/uss/mng/selectAjaxPsitn.do",
	   data : formData,
	   dataType:"json", 
	   async : false,
	   success: function(args) {
		   $.each(args, function() {
			   $('select[name="selAssembly"]').eq(type2).append("<option value='"+this.loasmCode+"'>"+this.loasmNm+"</option>");
			   if(type1 == 0 && type2 == 0) {
				   $('select[name="selAssembly"]').eq(type2).val(psitnCode1).attr("selected", "selected");
			   }
			   if(type1 == 0 && type2 == 1) {
				   $('select[name="selAssembly"]').eq(type2).val(psitnCode2).attr("selected", "selected");
			   }
			   if(type1 == 0 && type2 == 2) {
				   $('select[name="selAssembly"]').eq(type2).val(psitnCode3).attr("selected", "selected");
			   }			   
			});
	   }
		,error:function(e) {
			alert(e.responseText);
	   }
	});
}


/******************************************
 * 승인
 ******************************************/

function fnApproveLocalMng() {
	if(confirm("승인 하시겠습니까?")){
	    var f = document.getElementById("localMngManage");
	    f.confmSttusAt.value = "Y";
	    f.action = "<c:url value='/uss/mng/ApproveLocalMng.do'/>";
	    f.submit();
	}
}


/******************************************
 * 수정
 ******************************************/

function fnEditLocalMng() {
	if(confirm("수정 하시겠습니까?")){
	    var f = document.getElementById("localMngManage");
	    f.psitnCode.value = $('select[name="selPsitn"]').val();
	    f.chrg.value = $('select[name="selChrgList"]').val();
	    f.cmd.value = 'edit';
	    f.action = "<c:url value='/uss/mng/LocalMngDetail.do'/>";
	    f.submit();
	}
}

/******************************************
 * 삭제
 ******************************************/

function fnDelLocalMng() {
	if(confirm("삭제 하시겠습니까?")){
	    var f = document.getElementById("localMngManage");
	    f.cmd.value = 'del';
	    f.action = "<c:url value='/uss/mng/LocalMngDetail.do'/>";
	    f.submit();
	}
}
-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<div id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">지방의회/지자체 담당자 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
	<div id="border" style="display:" class="">
		<div class="">
			<h2>지방의회/지자체 담당자 상세보기 및 수정</h2>
			<!-- /.panel-heading -->			
			<div id="main" class="">
				<table border="0" width="100%">
				  <tr>
				    <td>
				<form:form commandName="localMngManage" method="post" >
				<input type="hidden" id="confmSttusAt" name="confmSttusAt" value="${mngVO.confmSttusAt}" />
				<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="아이디, 이름, 소속, 관심의회, 이메일, 전화번호등을 수정하는 테이블 입니다.">
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">아이디</th>
				    <td class="lt_text" nowrap="nowrap"><c:out value='${mngVO.unityId}' /></td>
				  </tr>				
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">이름</th>
				    <td class="lt_text" nowrap="nowrap">
				    	<input name="chargerNm" id="chargerNm" type="text" value="<c:out value='${mngVO.chargerNm}' />" size="30" title="직원 이름" readonly="readonly" class="form-control input-sm" />
				    </td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;소속</th>
				    <td class="lt_text" nowrap="nowrap">
				    	<select id="selInstt" name="selInstt" onChange="insttTyChange(this.value, 1);">
				    		<option >기관선택</option>
						    <option value="INTSTTCL_000006" <c:if test="${mngVO.fInsttClCode == 'INTSTTCL_000006'}">selected="selected"</c:if>>지방정부</option>
						    <option value="INTSTTCL_000012" <c:if test="${mngVO.fInsttClCode == 'INTSTTCL_000012'}">selected="selected"</c:if>>지방의회</option>
				    	</select>
				    	<select id="selInsttTy" name="selInsttTy" onChange="localChange(this.value, 1);">
				    		<option disabled="disabled" >기관유형 선택</option>
				    	</select>
				    	<select id="selRegion" name="selRegion" onchange="psitnChange(this.value, 1);">
				    		<option disabled="disabled" value="0">지역 선택</option>
				    	</select>
				    	<select id="selPsitn" name="selPsitn">
				    		<option disabled="disabled" value="0">소속 선택</option>
				    	</select>		
				    	
				    </td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">관심의회</th>
				    <td class="lt_text" nowrap="nowrap">
				    	<table id="infoTable">
				    	<tr>
				    		<td>
								<select name="selRasmbly" id="selRasmbly">
								<option >의회선택</option>
									<option value="INTSTTCL_000023">광역의회</option>
									<option value="INTSTTCL_000024">기초의회</option>
								</select>			
															
								<select name="selLocal" id="selLocal">
								<option disabled="disabled" >지역 선택</option>
								</select>								
								
								<select name="selAssembly" id="selAssembly">
								<option disabled="disabled" >지방의회 선택</option>
								</select>									    	
						    </td>
						    <td>
						    	<input type="button" name="btnAdd" id="btnAdd" value="추가" />
						    </td>
						</tr>
				    	</table>
				    </td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">담당</th>
				    <td class="lt_text" nowrap="nowrap">
						<select name="chargerSeCode" id="chargerSeCode" >
						<option >담당선택</option>
						<c:forEach var="x" varStatus="s" items="${chargerSeCodeList}">
							<option value="<c:out value='${x.code}' />"  <c:if test="${x.code == mngVO.chargerSeCode}">selected="selected"</c:if>><c:out value="${x.codeNm}" /></option>
						</c:forEach>
						</select>			
				    </td>
				  </tr>				  
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">업무</th>
				    <td class="lt_text" nowrap="nowrap">
						<select name="selChrgList" id="selChrgList" >
						<option >업무선택</option>
						<c:forEach var="x" varStatus="s" items="${chrgList}">
							<option value="<c:out value='${x.code}' />"  <c:if test="${x.code == mngVO.chrg}">selected="selected"</c:if>><c:out value="${x.codeNm}" /></option>
						</c:forEach>
						</select>			
				    </td>
				  </tr>				  
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">이메일</th>
				    <td class="lt_text" nowrap="nowrap"><c:out value='${mngVO.chargerEmail}' /></td>
				  </tr>	
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">전화번호</th>
				    <td class="lt_text" nowrap="nowrap"><c:out value='${mngVO.chargerTelno}' /></td>
				  </tr>
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">등록일</th>
				    <td class="lt_text" nowrap="nowrap"><c:out value='${mngVO.frstRegisterPnttm}' /></td>
				  </tr>
				  <c:if test="${mngVO.confmSttusAt == 'Y'}">
				  <tr>
				    <th class="required_text" width="20%" scope="row"  nowrap="nowrap"><img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">승인일</th>
				    <td class="lt_text" nowrap="nowrap"><c:out value='${mngVO.confmPnttm}' /></td>
				  </tr>
				  </c:if>	  
				  				  				  				  			  
				</table>
				
				<!-- 검색조건 유지 -->
				<input name="cmd" type="hidden" />
				<input name="psitnCode" type="hidden" />
				<input name="chrg" type="hidden" />
				<input type="hidden" name="unityId"  value="<c:out value='${mngVO.unityId}' />" />
				<input type="hidden" name="pageIndex" value="<c:out value='${mngVO.pageIndex}'/>"/>
				</form:form>
				
				</td>
				</tr>
				</table>
			</div>
			<!-- /panel body -->
		</div>
		<!-- /.panel panel-default -->	
	
		<p class="tr">
			<button type="button" class="btn btn-default" onclick="javascript:fnListLocalMng(); return false;"><spring:message code="button.list" /></button>
			<c:if test="${mngVO.confmSttusAt == 'N'}">
				<button type="button" class="btn btn-primary" onclick="javascript:fnApproveLocalMng(); return false;">승인</button>
			</c:if>
			<button type="button" class="btn btn-success" onclick="javascript:fnEditLocalMng(); return false;"><spring:message code="button.update" /></button>
			<button type="button" class="btn btn-danger" onclick="javascript:fnDelLocalMng(); return false;"><spring:message code="button.delete" /></button>
		</p>
	</div>		
	<!-- /.MAIN -->		
</div>	
<!-- /page-wrapper -->	
</body>
</html>