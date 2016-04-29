/********************************************************
   파일명 : EgovUtilMeetingManage.js
   설  명 : 공통 JavaScript
   수정일       수정자        Version        Function 명
  -------    --------    ----------   --------------
*********************************************************/

/************************************************************************
   함수명 : fn_egov_RadioBoxChecked                                   
   설   명 : 라디오박스 입력된 값 체크설정                
   인   자 : ssbName 객체이름    setValue 값        
   사용법 :   fn_egov_RadioBoxChecked(sbName,setValue) 
   작성일 :    
   작성자 :        
  수정일       수정자             수정내용
 ------      ------     -------------------
************************************************************************/
	function fn_egov_RadioBoxChecked(sbName,setValue)
	{
		var FLength = document.getElementsByName(sbName).length;
		var FValue = "";
		for(var i=0; i < FLength; i++)
		{
			if(document.getElementsByName(sbName)[i].value == setValue){
				document.getElementsByName(sbName)[i].checked = true;
				return;
			}
		}
	}

	/************************************************************************
	   함수명 : fn_egov_RadioBoxValue                                   
	   설   명 : 라디오박스 값가져오기                
	   인   자 : ssbName 객체이름        
	   사용법 :   fn_egov_RadioBoxValue(sbName) 
	   작성일 :    
	   작성자 :        
	  수정일       수정자             수정내용
	 ------      ------     -------------------
	************************************************************************/
	function fn_egov_RadioBoxValue(sbName)
	{
		var FLength = document.getElementsByName(sbName).length;
		var FValue = "";
		for(var i=0; i < FLength; i++)
		{
			if(document.getElementsByName(sbName)[i].checked == true){
				FValue = document.getElementsByName(sbName)[i].value;
			}
		}
		
		return FValue;
	}
	
	/************************************************************************
	   함수명 : fn_egov_RadioBoxDisabled                                   
	   설   명 : 라디오박스 값가져오기                
	   인   자 : ssbName 객체이름, sbStatus 상태값 true,false      
	   사용법 :   fn_egov_RadioBoxDisabled(sbName,sbStatus) 
	   작성일 :    
	   작성자 :        
	  수정일       수정자             수정내용
	 ------      ------     -------------------
	************************************************************************/
	function fn_egov_RadioBoxDisabled(sbName,sbStatus)
	{
		var FLength = document.getElementsByName(sbName).length;
		var FValue = "";
		for(var i=0; i < FLength; i++)
		{
				FValue = document.getElementsByName(sbName)[i].disabled=sbStatus;
		}
		
	}
	/************************************************************************
	   함수명 : fn_egov_SelectBoxText                                   
	   설   명 : 셀렉트박스 텍스트 가져오기                
	   인   자 : ssbName 객체이름, sbStatus       
	   사용법 :   fn_egov_SelectBoxText(sbName) 
	   작성일 :    
	   작성자 :        
	  수정일       수정자             수정내용
	 ------      ------     -------------------
	************************************************************************/
	function fn_egov_SelectBoxText(sbName)
	{
		var FValue = "";
		for(var i=0; i < document.getElementById(sbName).length; i++)
		{
			if(document.getElementById(sbName).options[i].selected == true){
				
				FValue=document.getElementById(sbName).options[i].text;
			}
		}
		
		return  FValue;
	}
	
	/************************************************************************
	   함수명 : fn_egov_SelectBoxText                                   
	   설   명 : 셀렉트박스 값 가져오기                
	   인   자 : ssbName 객체이름, sbStatus       
	   사용법 :   fn_egov_SelectBoxText(sbName) 
	   작성일 :    
	   작성자 :        
	  수정일       수정자             수정내용
	 ------      ------     -------------------
	************************************************************************/
	function fn_egov_SelectBoxValue(sbName)
	{
		var FValue = "";
		for(var i=0; i < document.getElementById(sbName).length; i++)
		{
			if(document.getElementById(sbName).options[i].selected == true){
				
				FValue=document.getElementById(sbName).options[i].value;
			}
		}
		
		return  FValue;
	}
	
	/************************************************************************
	   함수명 : fn_egov_SelectBox                                   
	   설   명 : 셀렉트박스 index 설정               
	   인   자 : ssbName 객체이름, sbValue value값       
	   사용법 : fn_egov_SelectBox(sbName, sbValue) 
	   작성일 :    
	   작성자 :        
	  수정일       수정자             수정내용
	 ------      ------     -------------------
	************************************************************************/
	function fn_egov_SelectBox(sbName, sbValue)
	{
		for(var i=0; i < document.getElementById(sbName).length; i++)
		{
		
			if(document.getElementById(sbName).options[i].value == sbValue){
				
				document.getElementById(sbName).options[i].selected = true;
			}
		}
	}
	 
	 
	/************************************************************************
	   함수명 : fn_egov_RadioBox                                   
	   설   명 : 라디오박스 index 설정               
	   인   자 : ssbName 객체이름, sbValue value값       
	   사용법 : fn_egov_RadioBox(sbName, sbValue) 
	   작성일 :    
	   작성자 :        
	  수정일       수정자             수정내용
	 ------      ------     -------------------
	************************************************************************/
	function fn_egov_RadioBox(sbName,sbValue)
	{
		var FLength= document.getElementsByName(sbName).length;
		
		for(var i=0; i < FLength; i++)
		{
			if(document.getElementsByName(sbName)[i].value == sbValue){
				
				document.getElementsByName(sbName)[i].checked=true;
			}
		}
	}

  
	/************************************************************************
	   함수명 : fn_egov_RadioBox                                   
	   설   명 : null 이거나 공백 String 여부 체크               
	   인   자 : ssbName 객체이름, sbValue value값       
	   사용법 : fn_egov_RadioBox(sbName, sbValue) 
	   작성일 :    
	   작성자 :        
	  수정일       수정자             수정내용
	 ------      ------     -------------------
	************************************************************************/
	function fn_egov_isNull(sbName,sbValue)
	{
		var FLength= document.getElementsByName(sbName).length;
		
		for(var i=0; i < FLength; i++)
		{
			if(document.getElementsByName(sbName)[i].value == sbValue){
				
				document.getElementsByName(sbName)[i].checked=true;
			}
		}
	}
    