//<![CDATA[

function printIt(printThis)  {
    var win=null;
    win = window.open('','','scrollbars=yes,resizable=yes,width=950,height=700');
    self.focus();
    win.document.open();
    win.document.write("<html><head>");
	win.document.write("<link href='/minutes/css/cssXcom.css' rel='stylesheet' type='text/css'>");

	//win.document.write("<style type=\"text/css\">");
    //win.document.write("body, div { font-family: 돋움; font-size: 13px;}");
	//win.document.write("p { margin:0px; line-height:160%; clear:both;}");
   // win.document.write("</style>");
    win.document.write("<body>");
    win.document.write(printThis);
    win.document.write("</body></html>");
    win.document.close();
    win.print();
    win.focus();
}

function printContent(cssUrl)  {
    var win=null;
    var printThis = "";

	printThis = document.getElementById('content').innerHTML;
    win = window.open('','','scrollbars=yes,resizable=yes,width=900,height=600');
    self.focus();
    win.document.open();
    win.document.write("<html><head>");
    win.document.write("<link href='/minutes/css/"+cssUrl+"' rel='stylesheet' type='text/css' />");
	win.document.write("<style type='text/css'>");
    win.document.write("body,table,th,tr,td,div{font-family: 돋움; font-size: 12px;}");
    win.document.write("</style>");
    win.document.write("<body>");
    win.document.write(printThis);
    win.document.write("</body></html>");
    win.document.close();
    win.print();
    win.focus();
}

var font_size = 12;
function fontToggle(n) {
	var obj;
	font_size += n;
	if (font_size > 24 || font_size < 8) {
		alert("글자크기를 더 이상 변경할 수 없습니다.   ");
		n = n == 2 ? -2 : 2;
		font_size += n;
		return false;
	}
	obj = document.getElementById("contents");
	obj.style.fontSize = font_size + "pt";
}

function fontFamilyToggle(n) {
	var obj;
	obj = document.getElementById("contents");
	if (n == "") {
		obj.style.fontFamily = "굴림";
		return false;
	}
	obj.style.fontFamily = n;
}

function sDefault() {
	var obj;
	obj = document.getElementById("contents");
	obj.style.fontSize = "12pt";
	obj.style.fontFamily = "굴림";
	
	font_size = 12;
	document.getElementById("fontName")[0].selected = true;
}

function getPageDocuHtml() {
	return $("content").innerHTML;
}
//]]>
