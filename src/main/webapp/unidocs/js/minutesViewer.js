self.moveTo(0,0);
self.resizeTo(screen.availWidth,screen.availHeight);

var currheight;
var currwidth;
//var el=document.getElementById;

function reHeight(){
	//el=document.getElementById;
	if(currheight != document.documentElement.clientHeight || currwidth != document.documentElement.clientWidth){
		h=document.documentElement.clientHeight;
		w=document.documentElement.clientWidth;
		LH=h-90;
		LH2=h-115;
		RH=h-125;
		RW=w-260;

		document.getElementById("contents").style.height=RH+"px";
		document.getElementById("contents").style.width=RW+"px";
		//document.getElementById("snb").style.height=LH+"px";
		//document.getElementById("snb-content").style.height=LH2+"px";

		document.getElementById("supplement").style.top=(RH/2+86)+"px";
		document.getElementById("bill").style.top=(RH/2+34)+"px";
		document.getElementById("speaker_list").style.height=(RH/2-50)+"px";
		document.getElementById("agenda_list").style.height=(RH/2-50)+"px";
		document.getElementById("supplement_list").style.height=(RH/2-13)+"px";
		document.getElementById("bill_list").style.height=(RH/2-13)+"px";
	}
	currheight = document.documentElement.clientHeight;
	currhwidth = document.documentElement.clientWidth;
}
window.onload=reHeight;
window.onresize = reHeight;

function withPlus() {
	//snbW=document.getElementById('snb').style.width;
	cntW=document.getElementById("contents").style.marginLeft;
	iw=parseInt(snbW);
	icw=parseInt(cntW);
	if(!iw) {
		iw=200;
	}
	if(!icw) {
		icw=200;
	}
	

	reWsize=iw+10;
	cntWsize=icw+15;
	cntWsize2=icw-15;
	document.getElementById("contents").style.marginLeft=cntWsize+"px";
	document.getElementById("contents").style.width=cntWsize+"px";
	//document.getElementById("snb").style.width=reWsize+"px";
	//document.getElementById("snb-content").style.width=reWsize+"px";
}
function gock() {
	document.getElementById("ws").onmouseDown=withPlus;
}
//window.onload = function() {
	//document.getElementById('ws').onmousedown = function() { withPlus(); };
//}
