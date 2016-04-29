/* 회의록 트리 */
var _var01 = "";
var _var02 = "";
var _var03 = "";
var _var04 = "";
var _var05 = "";
var _var06 = "";
var _var07 = "";
var _var08 = "";
var _var09 = "";
var _var10 = "";
var _var11 = "";
var _var12 = "";
function setvar(var01,var02,var03,var04,var05,var06,var07,var08,var09,var10,var11,var12) {
	_var01 = var01;
	_var02 = var02;
	_var03 = var03;
	_var04 = var04;
	_var05 = var05;
	_var06 = var06;
	_var07 = var07;
	_var08 = var08;
	_var09 = var09;
	_var10 = var10;
	_var11 = var11;
	_var12 = var12;
}
function initTrees(v) {
	$("#mixed").treeview({
		url: "tree/AdmTree1.jsp",
		ajax: {
			data:{"var09":v},
			cache:false,
			type:"post",
			error : function(xhr, status) {
			},
			complete : function(xhr, status) {
			}
		}
	});
}
function getRemoveClass(id) {
	$("#"+id).css({"text-decoration":"none"});
	return false;
}
function getModify(id, var01,var12) {
	$("#"+id).css({"text-decoration":"line-through"});
	window.open("./minutesModifyForm.jsp?id="+id+"&var01="+var01+"&var12="+var12,'doc','width=980, height=768, resizable=yes, scrollbars=yes');
	return false;
}
function getDocOpen(var12, var07) {
	$("#chasu"+var12).css({"text-decoration":"line-through"});
	window.open("/minutes/xcom/minutesViewer.jsp?id="+var12,'','');
	return false;
}
function getOpenProc(mUid, id) {
	var msg1;
	var msg2;
	var mChk;
	
	msg1 = $("span#doc"+id).text();
	msg2 = $("span#open"+id).text();
	if( msg2 == "공개" ) {
		msg2 = "임시";
		mChk = "N";
	}
	else {
		msg2 = "공개";
		mChk = "Y";
	}
	if( !confirm(msg1+"\n\n\""+msg2+"\"회의록으로 하시겠습니까?") ) { 
		return false; 
	}
	$.ajax({
		url: "/minutes/xcom/minutesOpenProc.jsp",
		cache: false,
		type: "post",
		data: {"muid":mUid, "mchk":mChk},
		success: function(data, status) {
			if( data == "Y" ) {
				if( mChk == "N" ) {
					$("span#open"+id).html("<font color='red'>임시</font>");
				}
				else {
					$("span#open"+id).html("<font color='blue'>공개</font>");
				}
			}
			else {
				if( mChk == "N" ) {
					alert("회의록을 공개로 하는데 실패하였습니다.");
				}
				else {
					alert("회의록을 임시로 하는데 실패하였습니다.");
				}
			}
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}
function getDelete(mDaesu, mCode, mSs, mChk, mUid, msg) {
	if( mChk > 0 ) {
		if( !confirm(msg + " 회의록을 삭제하면\n등록된 부록("+mChk+"건)도 삭제됩니다.\n\n삭제하시겠습니까?") ) { 
			return false; 
		}
	}
	else if( !confirm(msg + "\n\n삭제하시겠습니까?") ) { 
		return false; 
	}
	$.ajax({
		url:"/minutes/xcom/minutesDeleteProc.jsp",
		cache:false,
		type:"post",
		data:{"muid":mUid,"mdaesu":mDaesu,"mcode":mCode,"mss":mSs},
		success: function(data, status) {
			if( data == "N" ) {
				alert("회의록을 삭제하는데 실패했습니다.");
			}
			else {
				$("li#chasu"+mUid).remove();
				if( data == "YC" ) {
					$("li#doc"+mSs+mCode).remove();
				}
				else if( data == "YS" ) {
					$("li#ss"+mSs).remove();
				}
				else if( data == "YCS" ) {
					$("li#doc"+mSs+mCode).remove();
					$("li#ss"+mSs).remove();
				}
			}
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}