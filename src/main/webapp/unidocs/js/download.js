function getAppendixDownLoad1(mId, mDaesu, mUid) {
	$("#"+mId).css({"text-decoration":"line-through"});
	document.location.href="/minutes/xcom/appendixDownLoad.jsp?mUid="+mUid+"&mDaesu="+mDaesu;
	return false;
}
function getAppendixDownLoad2() {
	var mUid;
	var mDaesu;
	mUid   = $("#mUid").val();
	mDaesu = $("#mDaesu").val();
	if( mUid == "" ) {
		alert("다운로드할 부록을 선택하십시오.");
		return false;
	}
	document.location.href="/minutes/xcom/appendixDownLoad.jsp?mUid="+mUid+"&mDaesu="+mDaesu;
	return false;
}
function getAppendixDownLoad3(mUid, mDaesu) {
	if( mUid == "" ) {
		alert("다운로드할 부록을 선택하십시오.");
		return false;
	}
	$("#app"+mUid).css({"text-decoration":"line-through"});
	document.location.href="/minutes/xcom/appendixDownLoad.jsp?mUid="+mUid+"&mDaesu="+mDaesu;
	return false;
}
function getHwpDownLoad1(mDaesu, mUid) {
	$("#chasu"+mUid).css({"text-decoration":"line-through"});
	document.location.href="/minutes/xcom/hwpDownLoad.jsp?mUid="+mUid+"&mDaesu="+mDaesu;
	return false;
}
function getHwpDownLoad2(mDaesu, mUid) {
	document.location.href="/minutes/xcom/hwpDownLoad.jsp?mUid="+mUid+"&mDaesu="+mDaesu;
	return false;
}