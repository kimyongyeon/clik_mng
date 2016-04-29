    // ajax 실행
    var request = null;
  
    try {
        request = new XMLHttpRequest();
    } catch (trymicrosoft) {
        try {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (othermicrosoft) {
            try {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (failed) {
                request = null;
            }
        }
    }

    if (request == null)
        alert("Error creating request object!");

    // 현재 웹 브라우저 체크 
    function chkBrowser() {
        var a, ua = navigator.userAgent;
        this.bw = { 
            safari    : ((a=ua.split('AppleWebKit/')[1])?a.split('(')[0]:0)>=124 ,
            konqueror : ((a=ua.split('Konqueror/')[1])?a.split(';')[0]:0)>=3.3 ,
            mozes     : ((a=ua.split('Gecko/')[1])?a.split(" ")[0]:0) >= 20011128 ,
            opera     : (!!window.opera) && ((typeof XMLHttpRequest)=='function') ,
            msie      : (!!window.ActiveXObject)?(!!request):false 
        }
        return (this.bw.safari || this.bw.konqueror || this.bw.mozes || this.bw.opera || this.bw.msie);
    }


/**
 * 등록/수정/삭제의 성공/실패 메세지.
 * msg = 메세지
 */
function fn_msg(msg) {
    // 메시지가 있는지 확인.
    if ( msg != "" ) {
        alert(msg);
        if (parent.action_openNclose && typeof parent.action_openNclose == "function") {
        	parent.action_openNclose("hidden");
    	}
    }
}
 
/**
 * 팝업출력
 */
function winOpens(src,name,width,height,left,top,menubar,toolbar,locat,scrollbars,status)
{
  var option = "left="+left+",top="+top+",width="+width+",height="+height+",menubar="+menubar+
  	",toolbar="+toolbar+",location="+locat+",scrollbars="+scrollbars+",status="+status ; 
  window.open(src,name,option) ; 
}

 
/**
 * 입력시 숫자만 입력 되게.(onkeydown)
 * @param evt
 * @return
 */
function isNumberKey(evt) {
     evt = (evt) ? evt : window.event;
     var charCode = (evt.which) ? evt.which : evt.keyCode;
     if (charCode > 31 && (charCode < 48 || charCode > 57) && !(charCode >= 35 && charCode <= 40) && charCode != 46) {
		 if(charCode > 95 && charCode < 106) return true;
         else return false;
     }  
     return true;
}
 
/**
 * 필수 입력, 데이터 형 체크
 * @param str 체크할 문자
 * @param type 체크할 유형
 * @return 확인 결과를 boolean type으로 반환
 */
function validateCheck(str, type) {
    var result = true;
    var reg = "";
    
    if(type == "required") {
        result = (str.replace(/\s*/, "").length > 0);
    } else if (type == "number") {
        reg = /[0-9]+/;
        result = reg.test(str);
    } else if ( type == "email" ) {
        reg = /^[_0-9a-zA-Z-]+@[0-9a-zA-Z-]+(.[_0-9a-zA-Z-]+)*$/;
        result = reg.test(str);
    } else if ( type == "date" ) {
        reg = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
        result = reg.test(str);
    } else if ( type == "year" ) {
        reg = /^(19|20)\d{2}$/;
        result = reg.test(str);
    } else if ( type == "month" ) {
        reg = /^(0[1-9]|1[012])$/;
        result = reg.test(str);
    } else if ( type == "day" ) {
        reg = /(0[1-9]|[12][0-9]|3[0-1])/;
        result = reg.test(str);
    }
      
    // 결과가 false 라면 리턴
    if ( !result ) {
        return false;
    }
    
    return true;
}

 
 /**
  * 공백제거
  * @param s
  * @return
  */
 function trim(s){
 	return s.replace(/^\s*|\s*$/g, '');
 }
 
 /**
  * 필수입력 체크
  * 
  * @param form
  * @return boolean
  */
 function checkForm(form){
 	var msg = "값은 필수 입력항목입니다.";
 	var k=0;
 	var flag=false;
 	var arr=new Array();
 	var ele;
 	for (var i=0; i<form.elements.length; i++) {
 		ele = form.elements[i];
 		tagName = ele.tagName;
 		
 		if( (tagName == "SELECT" || tagName == "INPUT")){
 			
 			if( ele.getAttribute("check")== "true"){
 				if(tagName == "SELECT" ){
 					if(ele[ele.selectedIndex].value ==''){
 						alert(ele.getAttribute("dpname")+msg);
 						return false;
 					}
 				}else{
 				
 					if ((ele.getAttribute("type")=="text" || ele.getAttribute("type")=="password")) {
 						
 						if (trim(ele.value)=="" ) {
 							alert(ele.getAttribute("dpname")+msg);
 							ele.select();
 							return false;
 						}
 					} else if (ele.type=="checkbox") {
 						if (!ele.checked) {
 							alert(ele.getAttribute("dpname")+msg);
 							ele.focus();
 							return false;
 						}
 					}else if(ele.type=="hidden"){
 						
 					}else if (ele.type=="radio") {
 						for (var j1=0; j1<arr.length; j1++) {
 							if (arr[j1]==ele.name) {
 								continue;
 							}
 						}
 						if (eval("form."+ele.getAttribute("name")).length=="undefined") {
 							if (!ele.checked) {
 								alert(ele.getAttribute("dpname")+msg);
 								ele.focus();
 								return false;
 							}
 						} else {
 							flag=false;
 							for (var j2=0; j2<eval("form."+ele.getAttribute("name")).length; j2++) {
 								if (eval("form."+ele.getAttribute("name"))[j2].checked) {
 									flag=true;
 									break;
 								}
 							}
 							if (!flag) {
 								alert(ele.getAttribute("dpname")+msg);
 							    ele.focus();
 							    return false;
 							}
 				 		}
 				  		arr[k++]=ele.getAttribute("name");
 					}
 				}
 			}
 		}
 	}

 	return true;
 }
 
  /**
   * 필수입력 체크 후 submit
   * 
   * @param form
   * @return 
   */
function fn_checkSubmit(form) {
	if(checkForm(form))
		form.submit();
}
   
   
/**
 * 설정한 날짜만큼 쿠키가 유지되게. expiredays가 1 이면 하루동안 유지
 * name = 쿠키명
 * value = 설정값 (true)
 * expiredays = 쿠기 유지 시간(일단위)
 */
function fn_setCookie(name, value, expiredays) {
	var expire_date = new Date();
	expire_date.setDate(expire_date.getDate() + expiredays );
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + expire_date.toGMTString() + ";"
}

/**
 * 쿠키 소멸 함수
 * name = 쿠키명
 */
function fn_clearCookie(name) {
	var expire_date = new Date();
	//어제 날짜를 쿠키 소멸 날짜로 설정한다.
	expire_date.setDate(expire_date.getDate() - 1);
	document.cookie = name + "= " + "; expires=" + expire_date.toGMTString() + ";";
}

/**
 * 체크 상태에 따라 쿠키 생성과 소멸을 제어하는 함수
 * element = 'webdb_info' checkBox
 */ 
function fn_controlCookie(element) {
	if ( element.checked ) {
		//체크 박스를 선택했을 경우 쿠키 생성 함수 호출
		fn_setCookie("webdb_info","true", 1);
		window.close();
	} else {
		//체크 박스를 해제했을 경우 쿠키 소멸 함수 호출
		fn_clearCookie("webdb_info");
		window.close();
	}
}

//쿠키 가져오기
function getCookies() {
	return document.cookie.split(";");
}

//문자열 내에 특수문자를 변환처리
function htmlEntityEnc(str){
  if(str == "" || str == null)
  {
      return str;
  }
  else
  {
  	str = str.split("&").join("&amp;");
  	str = str.split("<").join("&lt;");
  	str = str.split(">").join("&gt;");
  	str = str.split("\"").join("&quot;");
  	str = str.split("#").join("&#35;");
  	str = str.split("%").join("&#37;");
  	str = str.split("'").join("&#39;");
  	str = str.split("(").join("&#40;");
  	str = str.split(")").join("&#41;");
  	str = str.split("+").join("&#43;");
  	str = str.split(".").join("&#46;");
  	str = str.split("/").join("&#47;");
  	str = str.split("?").join("&#63;");
  	str = str.split("\\").join("&#92;");
  	str = str.split("|").join("&#124;");
  	str = str.split("·").join("&middot;");
  	
  	return str;
  }
}