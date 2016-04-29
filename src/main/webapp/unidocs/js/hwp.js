var frmid;
var frmuid;
var frmdaesu;

function FileuploadCallback(data,state){
	//var msg = JSON.parse(data);
	var msg = data.replace(/(^\s*)|(\s*$)/, "");

	if( msg == "failure" ) {
		alert("파일을 전송하는 중에 오류가 발생하였습니다.");
		return false;
	}

	alert("원문이 등록되었습니다.");

	getHwpFrmClose(frmid);
	str = "[<a href='#' onclick=\"getHwpDownLoad('"+frmdaesu+"','"+frmuid+"');\"><font color='blue'>원문보기</font></a>] ";
	str+= "[<a href='#' onclick=\"getHwpDelete('"+frmid+"','"+frmdaesu+"','"+frmuid+"');\"><font color='red'>원문삭제</font></a>] ";
	$("span#hwpreg"+frmuid).html(str);
	frmid = "";
	frmuid = "";
	frmdaesu = "";
}
function _FileUpload(id) {
	var frm=$("#hfrm"+id);
	frm.ajaxForm(FileuploadCallback);
	frm.submit(function(){return false; });
}
function getFileUpload(id, mDaesu, mUid) {
	frmid = id;
	frmuid = mUid;
	frmdaesu = mDaesu;

	var mFile = $("#hfrm"+id+">input[name='mFile']").val();
	if( mFile.replace(/ /g, "") == "") {
		alert("원문파일을 입력하십시오.");
		return false;
	}
	
	_FileUpload(id);
	//파일전송
	var frm;
	frm = $("#hfrm"+id);
	frm.attr("action","./hwpRegProc.jsp");
	frm.submit();
}
function getHwpRegForm(id, mDaesu, mUid) {
	if( $("span#hwpfrm"+id).length > 0 ) {
		return false;
	}
	var str;
	str ="<div class='menufrdv'>";
	str+= "<form name='hfrm"+id+"' id='hfrm"+id+"' method='post' enctype='multipart/form-data'>";
	str+= "<input type='hidden' name='mDaesu' id='mDaesu' value='"+mDaesu+"' /> ";
	str+= "<input type='hidden' name='mUid' id='mUid' value='"+mUid+"' /> ";
	str+= "<input type='file' name='mFile' id='mFile' size='55' /> ";
	str+= "<input type='button' value='등록' onclick=\"getFileUpload('"+id+"','"+mDaesu+"','"+mUid+"');\" /> ";
	str+= "<input type='button' value='취소' onclick=\"getHwpFrmClose('"+id+"','"+mDaesu+"','"+mUid+"');\" /> ";
	str+= "</form>";
	str+="</div>";

	$("#"+id).append($("<span />").attr("id", "hwpfrm"+id).html(str));
}
function getHwpFrmClose(id) {
	$("span#hwpfrm"+id).remove();
}
/*
function getHwpChkFrm(id) {
	var mFile = $("#hfrm"+id+">input[name='mFile']").val();
	if( mFile.replace(/ /g, "") == "") {
		alert("원문파일을 입력하십시오.");
		return false;
	}
	ajaxFileUpload();
	return false;
}
*/
function getHwpDelete(id, mDaesu, mUid) {
	var str;
	var msg;
	msg = $("#hwpmsg"+mUid).text();
	str = "[<a href='#' onclick=\"getHwpRegForm('"+id+"','"+mDaesu+"','"+mUid+"');\"><font color='blue'>원문등록</font></a>] ";

	if( !confirm(msg + "\n\n원문을 삭제하시겠습니까?") ) { 
		return false; 
	}
	$.ajax({
		url:"hwpDeleteProc.jsp",
		cache:false,
		type:"post",
		data:{"mUid":mUid,"mDaesu":mDaesu},
		success: function(data, status) {
			if( data != "-1" ) {
				$("span#hwpreg"+mUid).html(str);
			}
			else {
				alert("원문을 삭제하는데 실패했습니다.");
			}
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}
