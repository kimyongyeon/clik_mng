/****************************************************************
 *
 * 파일명 : main.js
 * 설  명 : 메뉴 JavaScript
 *
 *   수정일         수정자           Function 명
 * ------------    ---------    ----------------------------
 * 
 *
 */

var selTAB			= "TAB_01"; // 선택된 탭
/*********************************************************************
 *	탭 클릭 시
 *********************************************************************/
function fn_changeTAB(obj){
	selTAB = obj.id;
	fn_setImgOn(obj.id);

}

/*********************************************************************
 *	MOUSE OVER 시
 *********************************************************************/
function fn_swapImage(obj){
//	eval("obj.src='"+getContextPath+"/images/egovframework/com/"+obj.id+"_o.gif'");
	obj.src="'"+getContextPath+"/images/egovframework/com/"+obj.id+"_o.gif'";
}

/*********************************************************************
 *	MOUSE OUT 시
 *********************************************************************/
function fn_swapImgRestore(obj){
	if(selTAB != obj.id){
//		eval("obj.src='"+getContextPath+"/images/egovframework/com/"+obj.id+".gif'");
		obj.src="'"+getContextPath+"/images/egovframework/com/"+obj.id+".gif'";
	}
}

/*********************************************************************
 *	클릭된 이미지만 ON 나머지 OFF
 *********************************************************************/
function fn_setImgOn(id){
	if(id == "TAB_01"){
		document.all.TAB_01.src = getContextPath+"/images/egovframework/com/TAB_01_o.gif";
		document.all.TAB_02.src = getContextPath+"/images/egovframework/com/TAB_02.gif";
		document.all.TAB_03.src = getContextPath+"/images/egovframework/com/TAB_03.gif";
	}else if(id == "TAB_02"){
		document.all.TAB_01.src = getContextPath+"/images/egovframework/com/TAB_01.gif";
		document.all.TAB_02.src = getContextPath+"/images/egovframework/com/TAB_02_o.gif";
		document.all.TAB_03.src = getContextPath+"/images/egovframework/com/TAB_03.gif";
	}else if(id == "TAB_03"){
		document.all.TAB_01.src = getContextPath+"/images/egovframework/com/TAB_01.gif";
		document.all.TAB_02.src = getContextPath+"/images/egovframework/com/TAB_02.gif";
		document.all.TAB_03.src = getContextPath+"/images/egovframework/com/TAB_03_o.gif";
	}
}

function goPageLNK(form){
	//var status ="toolbar=no, location=no, directories=no, status=yes, menubar=no, resizable=yes, scrollbars=yes, width=800, height=600, left=20, top=20";
	window.open(form.select01.value,'요소기술','toolbar=no, location=no, directories=no, status=yes, menubar=no, resizable=yes, scrollbars=yes, width=800, height=600, left=20, top=20');
}