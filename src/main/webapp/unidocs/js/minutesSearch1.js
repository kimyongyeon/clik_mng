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

	setAngunReset();

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

		$("div[id^='an']").each(function(e) {
			flg = false;
			id = $(this).attr("id");
			$("#"+id+" div[class^='sMan']").each(function(e) {
				csName = this.className;
				if( $.inArray(csName, arr) == -1 ) {
					$(this).hide();
				}
				else {
					flg = true;
				}
			});
			if( flg == false ) {
				$("div#"+id).hide();
			}
		});
		$("div#anE").hide();

	}
}

function setAngunView() {
	var sClass;
	var arr = new Array();

	setSpeakerReset();

	$("input:checkbox[name='angun']").each(function() {
		if( $(this).is(":checked") == true ) {
			sClass = $(this).val();
			arr.push(sClass);
		}
	});

	$("div[id^='an']").each(function(e) {
		if( $(this).css("display") == "none" ) {
			$(this).show();
		}
	});

	if( arr.length > 0 ) {
		var id;
		$("div[id^='an']").each(function(e) {
			id = $(this).attr("id");
			if( $.inArray(id, arr) == -1 ) {
				$("div#"+id).hide();
			}
		});
		$("div#anB").hide();
		$("div#anE").hide();
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

function setAngunReset() {
	$("input:checkbox[name='angun']").each(function() {
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