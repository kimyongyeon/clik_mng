function ajaxFileUpload() {
	//if( !/(\.gif|\.jpg|\.jpeg|\.png)$/i.test($("#fileToUpload").val()) ) { 
	//	alert("이미지 형식의 파일을 선택하십시오"); 
	//	return false; 
	//}
	/*
	$("#loading").ajaxStart(function() {
		$(this).show();
	}).ajaxComplete(function() {
		$(this).hide();
	});
	*/
	var mUid;
	var mDaesu;

	mUid = $("#mUid").val();
	mDaesu = $("#mDaesu").val();
	
	$.ajaxFileUpload({
		url:"./hwpRegProc.jsp",
		secureuri:false,
		cache:false,
		fileElementId:"fileToUpload",
		type:"post",
		dataType: "json",
		data:{"mUid":mUid,"mDaesu":mDaesu},
		success: function(data, status) {
			if( typeof(data.error) != "undefined" ) {
				if( data.error != "" ) {
					//alert(data.error);
				}
				else {
					//$("ul#fileList").append(data.msg);
					//getAddInit(data.msg);
				}
			}
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	
	
	return false;
}

$(document).ready(function(e) {
	getInit();
});
function getInit() {
	var mOrd;
	var cName;
	$("span[id^='msg']").each(function() {
		$(this).css({"cursor":"pointer"});
		$(this).click(function(e) {
			mOrd  = $("#mOrd").val();
			cName = $(this).attr("id");
			getSelect();
			if( mOrd == cName ) {
				$("#mOrd").val("");
			}
			else {
				$(this).css({"backgroundColor":"gray"});
				$("#mOrd").val(cName);
			}
		});
	});
}
function getAddInit(data) {
	var reg;
	var msg;
	var mOrd;

	reg = /msg[0-9]+/i;
	if( data.match(reg) ) {
		msg = data.match(reg);
		$("span#"+msg).css({"cursor":"pointer"});
		$("span#"+msg).click(function(e) {
			mOrd  = $("#mOrd").val();
			cName = $(this).attr("id");
			getSelect();
			if( mOrd == cName ) {
				$("#mOrd").val("");
			}
			else {
				$(this).css({"backgroundColor":"gray"});
				$("#mOrd").val(cName);
			}
		});
	}
}
function getSelect() {
	$("span[id^='msg']").each(function() {
		$(this).css({"backgroundColor":"#FFF"});
	});
}
function getAppendixDel() {
	var mDaesu;
	var mOrd;
	var mUid;

	mDaesu = $("#mDaesu").val();
	mOrd = $("#mOrd").val();
	if( mOrd == "" ) {
		alert("삭제할 부록을 선택하십시오.");
		return false;
	}
	msg = $("#"+mOrd).text();
	if( !confirm(msg + "\n삭제하시겠습니까?") ) { 
		return false; 
	}
	mUid = mOrd.replace("msg", "");
	$.ajax({
		url:"/minutes/xcom/appendixFileDelProc.php",
		cache:false,
		type:"post",
		data:{"mUid":mUid,"mDaesu":mDaesu},
		success: function(data, status) {
			$("li#fdel"+mUid).remove();
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}
function delTreeFile(mUid, mDaesu) {
	msg = $("#msg"+mUid).text();
	if( !confirm(msg + "\n삭제하시겠습니까?") ) { 
		return false; 
	}
	$.ajax({
		url:"/minutes/xcom/appendixFileDelProc.php",
		cache:false,
		type:"post",
		data:{"mUid":mUid,"mDaesu":mDaesu},
		success: function(data, status) {
			$("p#app"+mUid).remove();
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}
function getUpDown(mode) {
	var mFk;
	var mOrd;
	mFk  = $("#mFk").val();
	mOrd = $("#mOrd").val();
	if( mOrd == "" ) {
		alert("순서를 변경할 부록을 선택하십시오.");
		return false;
	}
	$.ajax({
		url:"/minutes/xcom/appendixOrdProc.php",
		cache:false,
		type:"post",
		data:{"mFk":mFk,"mOrd":mOrd,"mode":mode},
		success: function(data, status) {
			$("ul#fileList").html(data);
			getInit();
			$("span#"+mOrd).css({"backgroundColor":"gray"});
			$("#mOrd").val(mOrd);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}

function getDownLoad2() {
	var mUid;
	var mDaesu;
	mUid   = $("#mOrd").val().replace("msg", "");
	mDaesu = $("#mDaesu").val();
	if( mUid == "" ) {
		alert("다운로드할 부록을 선택하십시오.");
		return false;
	}
	document.location.href="/minutes/xcom/fileDownLoad.php?mUid="+mUid+"&mDaesu="+mDaesu;
	return false;
}

function getPv(pv) {
	var url = "/content/member/popprofile.html?daesu="+$("#mDaesu").val()+"&f_code="+pv.replace("pv", "");
	window.open(url,'pv','width=380,height=560,scrollbars=no');
}
