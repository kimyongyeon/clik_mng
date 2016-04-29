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
<title>스페셜검색 등록</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<!-- Naver.com. SmartEditor 적용  시작-->
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- Naver.com. SmartEditor 적용  끝 -->
<validator:javascript formName="spcManage" staticJavascript="false" xhtml="true" cdata="false"/>
 
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
* 스페셜 검색 등록
******************************************************** */
function fncAddRegist() {
    var varFrom = document.getElementById("spcManage");
    
    if($("select[name='selRasmbly']").val() == "0" || $("select[name='selRegion']").val() == "0" || $("select[name='selAssembly']").val() == "0") {
    	alert("지방의회선택은 필수 입니다.");
    	return;
    }
    
    if($("#file_0").val()=="") {
    	alert("대표이미지 항목을 확인해 주세요.");
    	return;
    }
    
    if($("#mainUrl_temp").val()=="") {
    	alert("대표URL 항목을 확인해 주세요.");
    	return;
    }
    
    if($("#mainSj_temp").val()=="") {
    	alert("제목 항목을 확인해 주세요.");
    	return;
    }
    
  	//smartEditor textarea 적용
	oEditors.getById["mainCtt"].exec("UPDATE_CONTENTS_FIELD", []);
	
    if($("#mainCtt").val()=="" || $("#mainCtt").val()== null || $("#mainCtt").val()== "&nbsp;" || $("#mainCtt").val()== "<p>&nbsp;</p>") {
    	alert("내용 항목을 확인해 주세요.");
    	return;
    }
    
    if($("#kwrd_temp").val()=="") {
    	alert("키워드 항목을 확인해 주세요.");
    	return;
    }
    
    // 파일 확장자 체크
    if(document.getElementById('file_0').value != null && document.getElementById('file_0').value != ''){
        var confirmExt;
        var thumbext = document.getElementById('file_0').value; 
        confirmExt = fn_confirmExt(thumbext);
        if(!confirmExt) {
        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
        	return;
        }
    } else if(document.getElementById('file_1').value != null && document.getElementById('file_1').value != '') {
        var confirmExt;
        var thumbext = document.getElementById('file_1').value; 
        confirmExt = fn_confirmExt(thumbext);
        if(!confirmExt) {
        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
        	return;
        }        
    } else if(document.getElementById('file_2').value != null && document.getElementById('file_2').value != '') {
        var confirmExt;
        var thumbext = document.getElementById('file_2').value; 
        confirmExt = fn_confirmExt(thumbext);
        if(!confirmExt) {
        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
        	return;
        }
    } else if(document.getElementById('file_3').value != null && document.getElementById('file_3').value != '') {
        var confirmExt;
        var thumbext = document.getElementById('file_3').value; 
        confirmExt = fn_confirmExt(thumbext);
        if(!confirmExt) {
        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
        	return;
        }
    } else if(document.getElementById('file_4').value != null && document.getElementById('file_4').value != '') {
        var confirmExt;
        var thumbext = document.getElementById('file_4').value; 
        confirmExt = fn_confirmExt(thumbext);
        if(!confirmExt) {
        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
        	return;
        }
    } else if(document.getElementById('file_5').value != null && document.getElementById('file_5').value != '') {
        var confirmExt;
        var thumbext = document.getElementById('file_5').value; 
        confirmExt = fn_confirmExt(thumbext);
        if(!confirmExt) {
        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
        	return;
        }
    } else if(document.getElementById('file_6').value != null && document.getElementById('file_6').value != '') {
        var confirmExt;
        var thumbext = document.getElementById('file_6').value; 
        confirmExt = fn_confirmExt(thumbext);
        if(!confirmExt) {
        	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
        	return;
        }
    }
    
    //인코딩 처리
    document.getElementsByName("mainUrl")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("mainUrl_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("mainSj")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("mainSj_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("mainCtt")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("mainCtt").value));
    document.getElementsByName("kwrd")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("kwrd_temp").value)).replace(/[!'()*]/g, escape);
    
    document.getElementsByName("subText1")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText1_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subText2")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText2_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subText3")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText3_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subText4")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText4_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subText5")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText5_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subText6")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subText6_temp").value)).replace(/[!'()*]/g, escape);
    
    document.getElementsByName("subImage1Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage1Url_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subImage2Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage2Url_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subImage3Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage3Url_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subImage4Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage4Url_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subImage5Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage5Url_temp").value)).replace(/[!'()*]/g, escape);
    document.getElementsByName("subImage6Url")[0].value = encodeURIComponent(htmlEntityEnc(document.getElementById("subImage6Url_temp").value)).replace(/[!'()*]/g, escape);
    
    
    varFrom.action = "<c:url value='/sit/spc/SpcRegistProc.do'/>";
    varFrom.submit();
}

function htmlEntityEnc(str){
    if(str == "" || str == null){
        return str;
    }
    else{
    	str = str.split("&").join("&amp;");
    	str = str.split("#").join("&#35;");
    	str = str.split("<").join("&lt;");
    	str = str.split(">").join("&gt;");
    	str = str.split("\"").join("&quot;");
    	str = str.split("\\").join("&#39;");
    	str = str.split("%").join("&#37;");
    	str = str.split("(").join("&#40;");
    	str = str.split(")").join("&#41;");
    	str = str.split("+").join("&#43;");
    	str = str.split("/").join("&#47;");
    	str = str.split(".").join("&#46;");
        return str;
    	//return str.replace("&", "&amp;").replace("#", "&#35;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace('\\', "&#39;").replace('%', "&#37;").replace('(', "&#40;").replace(')', "&#41;").replace('+', "&#43;").replace('/', "&#47;").replace('.', "&#46;");
    }
}

/**
 * 파일 확장자 체크
 * 해당 확장자가 아닌 다른 확장자로 업로드 실행 시 필터
 */

function fn_confirmExt(str) {
	str = str.slice(str.lastIndexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.

	var result = true;

	if(str != "jpg" && str != "png" &&  str != "gif" &&  str != "bmp" && str != "jpeg"){ //확장자를 확인합니다.

		//alert('썸네일은 이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
		result = false;
		return result;

	} else {
		return result;
	}
}



function fncSpcList() {
    var varFrom = document.getElementById("spcManage");
    varFrom.action = "<c:url value='/sit/spc/SpcList.do'/>";
    varFrom.submit();
}

/* ********************************************************
* multi selectbox 
******************************************************** */
//광역시도 선택
function localChange() {
	var insttClCode = $("select[name='selRasmbly'] option:selected").val();
	
	// 지역선택 셀렉트박스 초기화
	$("select[name='selRegion'] option").remove();
	$("#selRegion").append("<option value='0'>지역 선택</option>");
	$("select[name='selAssembly'] option").remove();
	$("#selAssembly").append("<option value='0'>지방의회 선택</option>");
   
   $.ajax({
	   type: "POST",
	   url : "/sit/spc/selectAjaxBrtc.do",
	   data : "insttClCode=" + insttClCode,
	   dataType:"json", 
	   success: function(args) {
		   $.each(args, function() {
			   $("#selRegion").append("<option value='"+this.codeId+"'>"+this.codeIdNm+"</option>");
			});
	   }
   		,error:function(e) {
  			alert(e.responseText);
	   }
	});
	
} 


// 지방의회 셀렉트 박스
function rasmblyChange() {
	
	// 기관분류코드
	var insttClCode = $("select[name='selRasmbly'] option:selected").val();
	
	// 광역시도코드
   var brtcCode = $("select[name='selRegion'] option:selected").val();
   
	// 지역선택 셀렉트박스 초기화
	$("select[name='selAssembly'] option").remove();
	
	if (brtcCode == 0) {
		// 지역선택 셀렉트박스 초기화
		$("#selAssembly").append("<option value='0'>지방의회 선택</option>");
	}
	
   
	var formData = "brtcCode=" + brtcCode + "&insttClCode=" + insttClCode;  
	
   $.ajax({
	   type: "POST",
	   url : "/sit/spc/AjaxAsmbly.do",
	   data : formData,
	   dataType:"json", 
	   success: function(args) {
		   $.each(args, function() {
			   $("#selAssembly").append("<option value='"+this.loasmCode+"'>"+this.loasmNm+"</option>");
			});
	   }
   		,error:function(e) {
  			alert(e.responseText);
	   }
	});
}




/* ********************************************************
* 파일 input Change
******************************************************** */
function fncOnChangeImage() {
	var varFrom = document.getElementById("spcManage");
	varFrom.spcImage.value = varFrom.file_1.value;
}

/* ********************************************************
* 파일 input reset
******************************************************** */

function fnClearFile(fileId) {
	$(fileId).replaceWith($(fileId).clone());
}


-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form:form commandName="spcManage" method="post" enctype="multipart/form-data" >
<input type="hidden" name="posblAtchFileNumber" value="1" >
<input type="hidden" name="spcImage" >
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">스페셜검색 관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>스페셜검색 등록</h2>

				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="15%" />
						
						<col />
					 </colgroup>
					<tbody>
						<tr>
							<th>지방의회선택<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
							<td>
						    	<select aria-controls="dataTables-example" class=" input-sm" name="selRasmbly" id="selRasmbly" onchange="localChange();">
						    		<option value="0">의회선택</option>
						    		<option value="INTSTTCL_000023">광역의회</option>
						    		<option value="INTSTTCL_000024">기초의회</option>
						    	</select>			
						    									
						    	<select aria-controls="dataTables-example" class=" input-sm" name="selRegion" id="selRegion" onchange="rasmblyChange();">
									<option disabled="disabled" value="0">지역 선택</option>
						    	</select>								
							
						    	<select name="selAssembly" id="selAssembly" aria-controls="dataTables-example" class=" input-sm">
						    		<option disabled="disabled" value="0">지방의회 선택</option>
						    	</select>									
							
							</td>
						</tr>
						<tr>
							<th>대표이미지<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
							<td>
								<input class="input-sm ip " placeholder="" value="찾아보기"  name="file_0" id="file_0" type="file" onchange="javascript:fncOnChangeImage();" style="width:30%; float:left; margin-right:10px;">
							    <button type="button" class="btn btn-danger" onclick="fnClearFile('#file_0');">삭제</button>
	
								<span class="r">* 사이즈 : 가로 214px, 세로 110px</span>
	
							</td>
						</tr>
						<tr>
							<th>대표 URL<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
							<td>
								<input id="mainUrl_temp" placeholder="http:// 를  같이 입력해주세요." type="text" class="ip input-sm" style="width:100%;" />
								<input name="mainUrl" type="hidden" style="display:none;" />
							</td>
						</tr>
	
						<tr>
							<th>제목<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
							<td>
								<input id="mainSj_temp" type="text" class="ip input-sm" style="width:100%;" />
								<input name="mainSj" type="hidden" style="display:none;" />
							</td>
						</tr>
						<tr>
							<th>내용<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력" ></th>
							<td><textarea name="mainCtt" id="mainCtt" cols="100" rows="10" style="width:100%;" class="ip"></textarea></td>
						</tr>
						<tr>
							<th>키워드<img src="<c:url value='/images/clikmng/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력"></th>
							<td>
							<input type="text" id="kwrd_temp" class="ip input-sm" style="width:100%;" />
							<input name="kwrd" type="hidden" style="display:none;" />
							<span class="r">* 키워드는 컴머(,)로 구분</span>
							</td>
						</tr>							
						<tr>
							<th>링크정보</th>
							<td><span class="r">*  사이즈 : 가로 97px, 세로 73px (링크정보는 최대 6개 까지 등록 가능)</span></td>
						</tr>
	
						<tr>
							<th>링크정보 #1</th>
							<td>
							<input type="text" id="subText1_temp"  class="ip input-sm" style="width:30%; float:left; margin-right:10px; margin-bottom:5px;" />
							<input name="subText1" type="hidden" style="display:none;" />
							<input class="input-sm ip " placeholder="" name="file_1" id="file_1" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;margin-bottom:5px;">
							
							<button type="button" class="btn btn-danger"  onclick="fnClearFile('#file_1');">삭제</button> <br/>
							<input type="text" id="subImage1Url_temp" placeholder="http:// 를  같이 입력해주세요." class="ip input-sm" style="width:100%;" />
							<input name="subImage1Url" type="hidden" style="display:none;" />
							</td>
						</tr>
						<tr>
							<th>링크정보 #2</th>
							<td>
							<input type="text" id="subText2_temp" class="ip input-sm" style="width:30%; float:left; margin-right:10px; margin-bottom:5px;" />
							<input name="subText2" type="hidden" style="display:none;" />
							<input class="input-sm ip " placeholder="" name="file_2" id="file_2" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;margin-bottom:5px;">
	 
							<button type="button" class="btn btn-danger" onclick="fnClearFile('#file_2');">삭제</button> <br/>
							<input type="text" id="subImage2Url_temp"  placeholder="http:// 를  같이 입력해주세요." class="ip input-sm" style="width:100%;" />
							<input name="subImage2Url" type="hidden" style="display:none;" />
							</td>
						</tr>
						<tr>
							<th>링크정보 #3</th>
							<td>
							<input type="text" id="subText3_temp" class="ip input-sm" style="width:30%; float:left; margin-right:10px; margin-bottom:5px;" />
							<input name="subText3" type="hidden" style="display:none;" />
							<input class="input-sm ip " placeholder="" name="file_3" id="file_3" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;margin-bottom:5px;">
	 
							<button type="button" class="btn btn-danger" onclick="fnClearFile('#file_3');">삭제</button> <br/>
							<input type="text" id="subImage3Url_temp" placeholder="http:// 를  같이 입력해주세요." class="ip input-sm" style="width:100%;" />
							<input name="subImage3Url" type="hidden" style="display:none;" />
							</td>
						</tr>
						<tr>
							<th>링크정보 #4</th>
							<td>
							<input type="text" id="subText4_temp" class="ip input-sm" style="width:30%; float:left; margin-right:10px; margin-bottom:5px;" />
							<input name="subText4" type="hidden" style="display:none;" />
							<input class="input-sm ip " placeholder="" name="file_4" id="file_4" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;margin-bottom:5px;">
	 
							<button type="button" class="btn btn-danger" onclick="fnClearFile('#file_4');">삭제</button> <br/>
							<input type="text" id="subImage4Url_temp" placeholder="http:// 를  같이 입력해주세요." class="ip input-sm" style="width:100%;" />
							<input name="subImage4Url" type="hidden" style="display:none;" />
							</td>
						</tr>
						<tr>
							<th>링크정보 #5</th>
							<td>
							<input type="text" id="subText5_temp" class="ip input-sm" style="width:30%; float:left; margin-right:10px; margin-bottom:5px;" />
							<input name="subText5" type="hidden" style="display:none;" />
							<input class="input-sm ip " placeholder="" name="file_5" id="file_5" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;margin-bottom:5px;">
	 
							<button type="button" class="btn btn-danger" onclick="fnClearFile('#file_5');">삭제</button> <br/>
							<input type="text" id="subImage5Url_temp" placeholder="http:// 를  같이 입력해주세요." class="ip input-sm" style="width:100%;" />
							<input name="subImage5Url" type="hidden" style="display:none;" />
							</td>
						</tr>
						<tr>
							<th>링크정보 #6</th>
							<td>
							<input type="text" id="subText6_temp" class="ip input-sm" style="width:30%; float:left; margin-right:10px; margin-bottom:5px;" />
							<input name="subText6" type="hidden" style="display:none;" />
							<input class="input-sm ip " placeholder="" name="file_6" id="file_6" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;margin-bottom:5px;">
	 
							<button type="button" class="btn btn-danger" onclick="fnClearFile('#file_6');">삭제</button> <br/>
							<input type="text" id="subImage6Url_temp" placeholder="http:// 를  같이 입력해주세요." class="ip input-sm" style="width:100%;" />
							<input name="subImage6Url" type="hidden" style="display:none;" />
							</td>
						</tr>
					</tbody>
				</table>
			<p class="tr">
				<button type="button" class="btn btn-default" onclick="javascript:fncSpcList()">목록</button>
				<button type="button" class="btn btn btn-primary" onclick="javascript:fncAddRegist()">등록</button>
			</p>
	
			<!-- 검색조건 유지 -->
			<input type="hidden" name="pageIndex" value="<c:out value='${spcVO.pageIndex}'/>"/>


	</div><!--//page-wrapper-->
</form:form>


<!-- Naver.com SmartEditor -->
<script type="text/javascript">
var oEditors = [];

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "mainCtt",
	sSkinURI: "/smarteditor/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["mainCtt"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});

</script>

</body>
</html>

