var TRange     = null;
var dupeRange  = null;
var TestRange  = null;
var win        = null;
var startId    = null;

var nom        = navigator.appName.toLowerCase();
var agt        = navigator.userAgent.toLowerCase();
var is_major   = parseInt(navigator.appVersion);
var is_minor   = parseFloat(navigator.appVersion);
var is_ie      = (agt.indexOf("msie") != -1);
var is_ie4up   = (is_ie && (is_major >= 4));
var is_not_moz = (agt.indexOf('netscape')!=-1);
var is_nav     = (nom.indexOf('netscape')!=-1);
var is_nav4    = (is_nav && (is_major == 4));
var is_mac     = (agt.indexOf("mac")!=-1);
var is_gecko   = (agt.indexOf('gecko') != -1);
var is_opera   = (agt.indexOf("opera") != -1);

var is_rev = 0;
if (is_gecko) {
    temp   = agt.split("rv:");
    is_rev = parseFloat(temp[1]);
}

var frametosearch = self;

function search(whichform, whichframe) {
    if (is_ie4up && is_mac) {
		return;
	}
    
	if (is_gecko && (is_rev <1)) {
		return;
	}
    
	if (is_opera) {
		return;
	}

    if (whichform.findthis.value!=null && whichform.findthis.value!='') {
		str = whichform.findthis.value;
		win = whichframe;
		
		var frameval = false;
		
		if (win != self) {
			frameval = true;  // this will enable Nav7 to search child frame
			win = parent.frames[whichframe];
		}
	}
	else {
		return;
	}

	var strFound = 0;

	if (is_nav4 && (is_minor < 5)) {
		strFound = win.find(str);
	}
	
	//if (is_gecko && (is_rev >= 1)) { // FireFox일 경우
	if (is_gecko) { // FireFox or chrome일 경우
		//if (frameval != false) {
		//	win.focus(); // force search in specified child frame
		//}

   		if (startId == null) {
			document.getElementById("contents").focus();
			startId = true;
		}

		strFound = win.find(str, false, false, true, false, frameval, false);

		//if (is_not_moz) {
			//whichform.findthis.focus();
		//}
	}
	else if (is_ie4up) { // IE일 경우
		if (TRange != null) {
            TestRange = win.document.body.createTextRange();
			startId = document.getElementById("contents");
			TestRange.moveToElementText(startId);

			if (dupeRange.inRange(TestRange)) {
				TRange.collapse(false);
				strFound = TRange.findText(str);

				if (strFound) {
					//the following line added by Mike and Susan Keenan, 7 June 2003
					win.document.body.scrollTop = win.document.body.scrollTop + TRange.offsetTop;
					TRange.select();
				}
			}
			else {
				TRange = win.document.body.createTextRange();

				TRange.collapse(false);
				strFound = TRange.findText(str);
				
				if (strFound) {
					//the following line added by Mike and Susan Keenan, 7 June 2003
					win.document.body.scrollTop = TRange.offsetTop;
					TRange.select();
				}
			}
		}

		if (TRange==null || strFound == 0) {
            TRange = win.document.body.createTextRange();
		    //TRange = win.document.getElementById("contents").nodeName.createTextRange();

			startId = document.getElementById("contents");
			TRange.moveToElementText(startId);

			dupeRange = TRange.duplicate();
			strFound  = TRange.findText(str);

			if (strFound) {
				//the following line added by Mike and Susan Keenan, 7 June 2003
				win.document.body.scrollTop = TRange.offsetTop;
				TRange.select();
			}
		}
	}

	if (!strFound) {
		alert ("찾으시는 '"+str+"' 에 대한 검색결과가 없습니다!"); // string not found
		startId = null;

	}
}

if (is_gecko && (is_rev >= 1)) { // FireFox일 경우
    document.documentElement.onclick = getY;
}

function getY(e) {
	var posY;
	var posX;
	//var offsetX;
	//var offsetY;
	
	e = e || window.event;

	posX = e.pageX || e.clientX + document.documentElement.scrollTop || 0;
	posY = e.pageY || e.clientY + document.documentElement.scrollTop || 0;
	//offsetX = document.getElementById("contents").offsetLeft;
	//offsetY = document.getElementById("contents").offsetTop;

	if (!(posX >= 397 && posX < 436  && posY < 82)) {
		startId = null;
	}
}
