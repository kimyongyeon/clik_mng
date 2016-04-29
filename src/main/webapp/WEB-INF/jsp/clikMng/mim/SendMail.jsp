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
<title>일반 / 지역현안자료 발송</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<!-- Naver.com. SmartEditor 적용  -->
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<validator:javascript formName="contentsForm" staticJavascript="false" xhtml="true" cdata="false"/>
 
<script type="text/javaScript" language="javascript">
<!--
/******************************************
 * 발송
 ******************************************/
function fnSendMail() {
	if(confirm("발송 하시겠습니까?")){
	    var f = document.getElementById("contentsForm");
	    f.cmd = "sendMail";
	    
		//smartEditor textarea 적용
		oEditors.getById["emailCn"].exec("UPDATE_CONTENTS_FIELD", []);
		
		f.emailCn.value = encodeURIComponent(f.emailCn.value).replace(/[!'()*]/g, escape);
		
        // 파일 확장자 체크
        var confirmExt;
        var thumbext = document.getElementById('atchFile').value; 
         
        if(document.getElementById('atchFile').value != "") {
            confirmExt = fn_confirmExt(thumbext);
            if(!confirmExt) {
            	alert('유효하지 않은 확장자 입니다. 다시 확인해 주세요.');
            	return;
            }
        }        
		
	    f.action = "<c:url value='/mim/ProcSendMail.do'/>";
	    f.submit();
	}
}

/**
 * 파일 확장자 체크
 * 해당 확장자가 아닌 다른 확장자로 업로드 실행 시 필터
 */

function fn_confirmExt(str) {
	str = str.slice(str.lastIndexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.

	var result = true;

	if(str != "jpg" && str != "png" &&  str != "gif" &&  str != "bmp"
		&& str != "alz" && str != "doc" && str != "docx" && str != "hwp" && str != "jpeg" 
		&& str != "pdf" && str != "ppt" && str != "pptx" && str != "pptx" && str != "txt"
		&& str != "xls" && str != "xlsx" && str != "zip" ){ //확장자를 확인합니다.

		//alert('썸네일은 이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
		result = false;
		return result;

	} else {
		return result;
	}
}

// filter
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

/******************************************
 * 팝업 - 그룹선택
 ******************************************/
function fnPopSelectGroup() {
	var url = "<c:url value='/mim/PopSelectGroup.do' />";
	var name = "popSelectGroup";
	var openWindows = window.open(url, name, "height=950, width=750, top=50, left=20, scrollbars=no, resizable=no");
}
 
/******************************************
 * 팝업 - 구성원선택
 ******************************************/
function fnPopSelectMember() {
	var url = "<c:url value='/mim/PopSearchMember.do' />";
	var name = "popSearchMember";
	var openWindows = window.open(url, name, "height=950, width=750, top=50, left=20, scrollbars=no, resizable=no");
	
}

/******************************************
 * 첨부파일 삭제
 ******************************************/
function fnDeleteFile() {
	if(confirm("파일을 삭제하시겠습니까?")){
		$(atchFile).replaceWith($(atchFile).clone());
	}
}
 
-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form name="contentsForm" id="contentsForm" method="post" enctype="multipart/form-data" >
<input type="hidden" name="cmd" id="cmde" />
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">메일발송</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->
			<h2>일반 메일 발송</h2>
            
			<ul class="nav nav-tabs">
				<li class="active" ><a href="/mim/SendMail.do" >일반</a></li>
				<li><a href="/mim/AreaPndprbDta.do">지역현안자료</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content" style="margin-top:20px;">
				<div class="tab-pane fade in active" id="home">
					
					<table class="table table-striped table-bordered table-hover "  id="">
						<colgroup>
							<col width="15%" />
							
							<col />
						 </colgroup>
						<tbody>
							<tr>
								<th>받는사람</th>
								<td><textarea cols="100" rows="4"  id="mailRcver" name="mailRcver" style="width:100%;" class="ip"></textarea>
								<p class="tr" style="margin-top:10px;">
									<button type="button" class="btn btn-success" onclick="fnPopSelectGroup(); return false;" >그룹선택</button>
									<button type="button" class="btn btn-primary" onclick="fnPopSelectMember(); return false;">구성원검색</button>
								</p>
								</td>
							</tr>
							<tr>
								<th>보내는 사람</th>
								<td><input type="text" id="sendNm" name="sendNm" value="<c:out value="${mailSndr }" />" class="ip input-sm" style="width:30%;" /></td>
							</tr>

							<tr>
								<th>제목</th>
								<td><input type="text" id="sj" name="sj" class="ip input-sm" style="width:100%;" /></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><textarea cols="100" id="emailCn" name="emailCn" rows="10" style="width:100%;" class="ip"></textarea></td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td>
								<input class="input-sm ip"  name="atchFile" id="atchFile" placeholder="" value="찾아보기"  type="file" style="width:30%; float:left; margin-right:10px;">
								<button type="button" class="btn btn-danger" onclick="fnDeleteFile(); return false;">삭제</button>
								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>

				<p class="tr">
					<button type="button" class="btn btn-success" onclick="fnSendMail(); return false;">메일발송</button>
				</p>

        </div>
        <!-- /#page-wrapper -->
</form>        
        
<script type="text/javaScript" language="javascript">
/******************************************
 * 구성원 textarea에 입력
 ******************************************/
$(document).ready(function () {
	
    $("#mailRcver").select2({tags: []
			, dropdownCss: {display: 'none'}
			, tokenSeparators: [",", ";"] });

});

function addTextarea(obj, popSe) {
		
	var list = new Array();
 	$.each(obj, function( index, value ) {
 		
 		list[index] = value.name+"(" + value.email +")";
 		
	});
 	
 	if (document.contentsForm.mailRcver.value == "") {
 		$('textarea[id=mailRcver]').val(list);	
 	} else {
 		$('#mailRcver').val($('#mailRcver').val()+","+list); 
 	}
 	

     $("#mailRcver").select2({tags: []
     									, dropdownCss: {display: 'none'}
     									, tokenSeparators: [",", ";"] });
	
}

function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
} 

var oEditors = [];

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "emailCn",
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
		//oEditors.getById["nttCn"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});


</script>        

</body>
</html>

