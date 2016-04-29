function getPos1(e) {
	var x;
	var y;
	
	e = e || window.event;

	x = e.pageX || e.clientX;
	y = e.pageY || e.clientY;

    window.status = "(x,y) = " + x + ", " + y;
}
function getPos2(e) {
    window.status = "";
}

function setSpeakerView() {
	var sClass;
	var arr = new Array();

	$("input:checkbox[name='speaker']").each(function() {
		if( $(this).is(":checked") == true ) {
			sClass = $(this).val();
			arr.push(sClass);
		}
	});

	if( arr.length > 0 ) {
		var csName;
		var id;
		var flg;

		$("div#anH").hide();
		$("div#anE").hide();

		$("div[class^='sMan']").each(function(e) {
			csName = this.className;
			if( $.inArray(csName, arr) == -1 ) {
				$(this).hide();
			}
			else {
				flg = true;
			}
		});
	}
}

function setSpeakerReset() {
	$("input:checkbox[name='speaker']").each(function() {
		if( $(this).is(":checked") == true ) {
			$(this).attr('checked', false);
		}
	});
	setBodyReset();
	return false;
}

function setBodyReset() {
	$("div[id^='an']").each(function(e) {
		if( $(this).css("display") == "none" ) {
			$(this).show();
		}
	});
	$("div[class^='sMan']").each(function(e) {
		if( $(this).css("display") == "none" ) {
			$(this).show();
		}
	});
	$("div#anB").show();
	$("div#anE").show();
	return false;
}

function getText(e) {
	var t = "";
	var regEx = "¡Û";

	e = e.childNodes || e;
	for(var j = 0; j < e.length; j++) {
		if (t.indexOf(regEx) != -1) {
			return t;
		}
		t += e[j].nodeType != 1 ? e[j].nodeValue : getText(e[j].childNodes);
	}
	return t;
}