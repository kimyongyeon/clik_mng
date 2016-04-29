/*
	add window.onload : addLoadEvent( func );
*/
function addLoadEvent(func) {
	var oldOnload = window.onload;

	if (typeof window.onload != 'function') {
		window.onload = func;
	} else {
		window.onload = function () {
			oldOnload();
			func();
		}
	}
}

// imgover
	function imgOver() {
	  var aImages = document.getElementsByTagName('img');
	  for(var i=0; i<aImages.length; i++) {
		if (aImages[i].className == 'imgover') {
		  aImages[i].onmouseover = function() {
			var img_src =  this.getAttribute('src');
			img_src = img_src.replace("_off", "_on");
			this.setAttribute('src', img_src);
			}
		   aImages[i].onmouseout = function() {
			  var img_src =  this.getAttribute('src');
			  img_src = img_src.replace("_on", "_off");
			  this.setAttribute('src', img_src);
		   }
		}
	}
	return false;
	}
addLoadEvent(imgOver);
{
}


/*
	ie6 background image cache : bgImgCache();
*/
function bgImgCache() {
	try {
		document.execCommand("BackgroundImageCache", false, true);
	} catch( e ) {}
}

/*
	insertAfter : insertAfter( newElement, targetElement );
*/
function insertAfter(newElement, targetElement) {
	var parent = targetElement.parentNode;

	if (parent.lastChild == targetElement) {
		parent.appendChild(newElement);
	} else {
		parent.insertBefore(newElement, targetElement.nextSibling);
	}
}

/*
	open popup : openPopup('url', { width:400, height:500, scroll:0, top:100, left:100, name:'name_popup' });
*/
function openPopup(url, option) {
	var obj_option = option == null ? {} : option;

	if (url               == null) url               = "";
	if (obj_option.width  == null) obj_option.width  = window.screen.availWidth;
	if (obj_option.height == null) obj_option.height = window.screen.availHeight;
	if (obj_option.scroll == null) obj_option.scroll = 0;
	if (obj_option.top    == null) obj_option.top    = (window.screen.availHeight - obj_option.height) / 4;
	if (obj_option.left   == null) obj_option.left   = (window.screen.availWidth  - obj_option.width) / 4;
	if (obj_option.name   == null) obj_option.name   = "";
	if (obj_option.errMsg == null) obj_option.errMsg = "Please disable popup blocking!";

	var newWindow = window.open(url, obj_option.name, "width=" + obj_option.width + ",height=" + obj_option.height + ",scrollbars=" + obj_option.scroll + ",toolbar=0,menubars=0,locationbar=0,historybar=0,statusbar=0,resizable=0,left=" + obj_option.left + ",top=" + obj_option.top + ",channelmode=no,titlebar=no", false);

	if (!newWindow) {
		alert(obj_option.errMsg);
		return false;
	}
	newWindow.focus();

	return newWindow;
}



//�� �ݱ�

function opendata(n){
	if(!n){
		return;
	}else{
		var i=1;
		for(i=1;i<=n;i++){
			var frm = document.getElementById("g"+i);
			if(i == n){
				if(frm.style.display == "none"){
					frm.style.display = "";
				}else{
					frm.style.display = "none";
				}
			}
		}
	}
}

function SwitchMenu(obj) {
	if(document.getElementById){
		var el = document.getElementById(obj);
		var er = el.parentNode.getElementsByTagName("dd");
		if(el.style.display != "block") {
			for (var i = 0; i < er.length ; i++) {
				er[i].style.display = "none";
			}
			el.style.display = "block";
		} else {
			el.style.display = "none";
		}

	}
}

function fnBottomAction(){
	if(document.bSelectForm.selectAction.value==""){
		return false;
	}
}

//�������ϱ�
var initBody;
var boxes;
var box;
function beforePrint() {
	boxes = document.body.innerHTML;
	document.body.innerHTML = box.innerHTML;
}
function afterPrint() { 
 document.body.innerHTML = boxes;
}
function printArea() {
 window.print();
}

//window.onbeforeprint = beforePrint;
//window.onafterprint = afterPrint;

// ���̹��˾�
function MM_showHideLayers() { //v9.0
	var i,p,v,obj, obj_style, args=MM_showHideLayers.arguments;
	for (i=0; i<(args.length-2); i+=3) 

	with (document){
		if (getElementById && ((obj=getElementById(args[i]))!=null)) { 
			v = args[i+2];
			if (obj.style) { 
				obj_style = obj.style; 
				v = (v=='show')?'visible':(v=='hide')?'hidden':v; 
			}
			obj_style.visibility=v; 
			obj.style.display = "";
		}
	}
}

//faq
function openfaq(n){
	if(!n){
		return;
	}else{
		var i=1;
		for(i=1;i<=i;i++){
			var frm = document.getElementById("faq"+i);
			if(i == n){
				if(frm.style.display == "none"){
					frm.style.display = "";
				}else{
					frm.style.display = "none";
				}
			}else{
				frm.style.display = "none";
			}
		}
	}
}

function txtclear(){
	document.getElementById('search').value = "";
}


// imgOverquick/ //quick
function imgOverquick(num) {
	for (var i = 1; i <= 6; i++ ) {
		var content_tab = document.getElementById("quick"+i+"_tab");
		var img_src =  content_tab.getAttribute('src');
		img_src = img_src.replace("_on", "_off");
		content_tab.setAttribute('src', img_src);
	}

	var content_tab = document.getElementById("quick"+num+"_tab");
	var img_src =  content_tab.getAttribute('src');
	img_src = img_src.replace("_off", "_on");
	content_tab.setAttribute('src', img_src);
}



function getDate(schDt,sch1,sch2) {
	if( schDt == null || schDt == "" ) {
		return false;
	}
	$.ajax({
		url:"/mdm/MdmGetDate.do",
		cache:false,
		type:"post",
		data:{"schDt":schDt},
		success: function(data, status) {
			msg = JSON.parse(data);
			$("#"+sch1).val(msg.schDt1);
			$("#"+sch2).val(msg.schDt2);
		},
		error: function (data, status, e) {
			alert(e);
		}
	});
	return false;
}


function setCal(sD,eD){
	
    $('#'+sD).datepicker({
    	dateFormat: 'yy-mm-dd',
    	changeYear: true,
    	changeMonth: true, 
    	showMonthAfterYear: true,
    	showButtonPanel:true,
    	showOn: 'both',
        buttonText: '<img src="/images/clikmng/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',
    	yearRange: '-100:+1',
        dayNames: ['일','월','화','수','목','금','토'],
        dayNamesMin: ['일','월','화','수','목','금','토'], 
        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']        	
    });
    $('#'+eD).datepicker({
    	dateFormat: 'yy-mm-dd',
    	changeYear: true,
    	changeMonth: true, 
    	showMonthAfterYear: true,
    	showButtonPanel:true,
    	showOn: 'both',
    	buttonText: '<img src="/images/clikmng/cmm/icon/bu_icon_carlendar.gif" align="middle" style="border:0px" alt="달력창팝업버튼이미지">',        	
    	yearRange: '-100:+1',
        dayNames: ['일','월','화','수','목','금','토'],
        dayNamesMin: ['일','월','화','수','목','금','토'], 
        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']  
    }); 
    
}


/*
날짜포맷 javascript
*/
function dateFormatter(obj){
var v = obj.value;
var dateMatching = /^(19|20)\d{2}-(0[1-9]|1[0-2])-([0-2]\d|3(0|1))$/	;
//변환될 숫자만 모으기
var v2 = v.replace(/-/g,"").replace(/[^0-9]/g,"");

//입력값중 숫자만 남기고 문자 삭제
v = v.replace(/[^0-9]/g,"");

//-붙이고 yyyy-mm-dd 포맷보다 길 경우 잘라내기
if(v2.length > 4)
	v = v2.substring(0,4) + "-" + v2.substring(4);

if(v2.length > 6)
	v = v.substring(0,7) + "-" + v.substring(7);

if(v2.length >= 8)
	v = v.substring(0,10);

obj.value = v;
}


function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//문자입력 방지
//for firefox, IE, chreome
function checkNumber(e) {
	var eventCode =(window.netscape)? e.which : e.keyCode;

	if(eventCode==16) { alert('Shift는 사용 할 수 없습니다.'); return false; }

	if ( ( (96<=eventCode) && (eventCode<=105) ) 
			|| ( (48<=eventCode) && (eventCode<=57) ) 
			|| (eventCode==8) || (eventCode==37) || (eventCode==39) 
			|| (eventCode==9)|| (eventCode==46) || (eventCode==189) 
			|| (eventCode==190) || (eventCode==109) || (eventCode==110) )
	{
		e.returnValue=true;
	}
	else
	{
		//e.preventDefault();
		//e.returnValue=false;

		//ie6,7,8
		(e.preventDefault) ? e.preventDefault() : e.returnValue = false;
	}
}