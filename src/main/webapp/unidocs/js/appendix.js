var frmid;
var frmuid;
var frmdaesu;

function FileuploadCallback(data,state){
	var msg = JSON.parse(data);

	if( msg.error == "Y" ) {
		alert("파일을 전송하는 중에 오류가 발생하였습니다.");
		return false;
	}

	var mEtc1 = $("#afrm"+frmid+">input[name='mEtc1']").val();
	alert("부록이 수정되었습니다.");
	$("#msg"+frmuid).text(mEtc1);

	if( msg.flg != "Y" ) {
		var img = $("#img"+frmuid).attr("src");
		$("#img"+frmuid).attr("src", "/minutes/img/ext/"+msg.flg+".gif");
	}

	getAppFrmClose(frmid);
	
	frmid = "";
	frmuid = "";
	frmdaesu = "";
}
function _FileUpload(id) {
	var frm=$("#afrm"+id);
	frm.ajaxForm(FileuploadCallback);
	frm.submit(function(){return false; });
}
function getFileUpload(id, mDaesu, mUid) {
	frmid = id;
	frmuid = mUid;
	frmdaesu = mDaesu;

	var mEtc1 = $("#afrm"+id+">input[name='mEtc1']").val();
	if( mEtc1.replace(/ /g, "") == "") {
		alert("제목을 입력하십시오.");
		return false;
	}
	
	_FileUpload(id);

	//파일전송
	var frm;
	frm = $("#afrm"+id);
	frm.attr("action","./appendixModifyProc.jsp");
	frm.submit();
}
function getAppendixModifyForm(id, mDaesu, mUid) {
	if( $("#afrm"+id).length > 0 ) {
		return false;
	}
	var mEtc1;
	var str;

	mEtc1 = $("#msg"+mUid).text().replace(/(^\s*)|(\s*$)/, "");
	str ="<div class='menufrdv'><ul class='catul'><li>";
	str+="<form name='afrm"+id+"' id='afrm"+id+"' method='post' enctype='multipart/form-data'>";
	str+="<input type='hidden' name='mDaesu' id='mDaesu' value='"+mDaesu+"' /> ";
	str+="<input type='hidden' name='mUid' id='mUid' value='"+mUid+"' /> ";
	str+="제목 : <input type='text' name='mEtc1' id='mEtc1' size='75' value='"+mEtc1+"' /><br /><br />";
	str+="부록 : <input type='file' name='mFile' id='mFile' size='47' /> ";
	str+="<input type='button' value='등록' onclick=\"getFileUpload('"+id+"','"+mDaesu+"','"+mUid+"');\" /> ";
	str+="<input type='button' value='취소' onclick=\"getRemoveClass('"+id+"','"+mUid+"');\" />";
	str+="</form>";
	str+="</li></ul></div>";
	
	$("#appList"+id).append($("<span />").attr("id", "appfrm"+id).html(str));
	$("#appchk"+mUid).css({"text-decoration":"line-through"});
	return false;
}
function getAppFrmClose(id) {
	$("span#appfrm"+id).remove();
}
function getRemoveClass(id, mUid) {
	$("#appchk"+mUid).css({"text-decoration":"none"});
	getAppFrmClose(id);
	return false;
}
function check1(form) {
	if(form.mFile.value.replace(/ /g, "") == "") {
		alert("부록제목을 입력하십시오.");
		return false;
	}
}
function check2(form) {
	if(form.mFile.value.replace(/ /g, "") == "") {
		alert("부록파일을 선택하십시오.");
		return false;
	}
}
function getInit() {
	var mUid;
	var cName;
	$("span[id^='msg']").each(function() {
		$(this).css({"cursor":"pointer"});
		$(this).click(function(e) {
			mUid  = $("#mUid").val();
			cName = $(this).attr("id").replace("msg","");
			getSelect();
			if( mUid == cName ) {
				$("#mUid").val("");
			}
			else {
				$(this).css({"backgroundColor":"yellow"});
				$(this).css({"color":"black"});
				$("#mUid").val(cName.replace("msg",""));
			}
		});
	});
}
function getAddInit(data) {
	var reg;
	var msg;
	var mUid;

	reg = /msg[0-9]+/i;
	if( data.match(reg) ) {
		msg = data.match(reg);
		$("span#"+msg).css({"cursor":"pointer"});
		$("span#"+msg).click(function(e) {
			mUid  = $("#mUid").val();
			cName = $(this).attr("id");
			getSelect();
			if( mUid == cName ) {
				$("#mUid").val("");
			}
			else {
				$(this).css({"backgroundColor":"gray"});
				$("#mUid").val(cName.replace("msg",""));
			}
		});
	}
}
function getSelect() {
	$("span[id^='msg']").each(function() {
		$(this).css({"backgroundColor":"#FFF"});
	});
}
function getUpDown(mode) {
	var mUid;
	var mFk;
	mUid = $("#mUid").val();
	mFk  = $("#mFk").val();
	if( mUid == "" ) {
		alert("순서를 변경할 부록을 선택하십시오.");
		return false;
	}
	document.location.href="appendixOrdProc.jsp?mUid="+mUid+"&mFk="+mFk+"&mode="+mode;
	return false;
}
function getDelete1(mUid, mDaesu) {
	var msg;
	msg = $("#msg"+mUid).text().replace(/(^\s*)|(\s*$)/, "");
	if( !confirm(msg + "\n\n삭제하시겠습니까?") ) { 
		return false; 
	}
	$.ajax({
		url:"appendixDeleteProc1.jsp",
		cache:false,
		type:"post",
		data:{"mUid":mUid,"mDaesu":mDaesu},
		success: function(data, status) {
			if( data != "-1" ) {
				$("#appListchasu"+mUid).remove();
			}
			else {
				alert("부록을 삭제하는데 실패했습니다.");
			}
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}
function getDelete2() {
	var mDaesu;
	var mUid;
	var mFk;
	var msg;

	mUid = $("#mUid").val();
	mFk  = $("#mFk").val();
	mDaesu = $("#mDaesu").val();
	
	if( mUid == "" ) {
		alert("삭제할 부록을 선택하십시오.");
		return false;
	}
	msg = $("#msg"+mUid).text().replace(/(^\s*)|(\s*$)/, "");
	if( !confirm(msg + "\n\n삭제하시겠습니까?") ) { 
		return false; 
	}
	document.location.href="appendixDeleteProc2.jsp?mUid="+mUid+"&mFk="+mFk+"&mDaesu="+mDaesu;
	return false;
}
function getFolderOpenClose(mUid) {
	if( $(".appList"+mUid).css("display") == "none" ) {
		$(".appList"+mUid).show();
	} 
	else {
		$(".appList"+mUid).hide();
	}
	return false;
}
