<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="ui" 		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovBannerList.java
 * @Description : EgovBannerList jsp
 */

%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>홍보존 목록</title>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* ********************************************************
* 탭클릭시 화면전환
******************************************************** */
function fn_tabClick(sel_name) {
	if (sel_name == 'MNG') {
		document.listForm.action = "/sit/bnr/selectBannerList.do";
	} else {
		document.listForm.action = "/sit/bnr/selectBannerImgMngView.do";
	}
	
	document.listForm.submit();	
}

function fncCheckAll() {
    var checkField = document.listForm.delYn;
    if(document.listForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

function fncManageChecked() {

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkCount++;
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                        returnValue = returnValue + ";" + checkField[i].value;
                }
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
                alert("선택된  홍보존이 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 홍보존이 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
    	alert("조회된 결과가 없습니다.");
    }

    document.listForm.bannerIds.value = returnValue;
    return returnBoolean;
}

function fncSelectBannerList(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sit/bnr/selectBannerList.do'/>";
    document.listForm.submit();
}

function fncSelectBanner(bannerId, pageIndex, searchCondition, searchKeyword) {
    
	document.listForm.bannerId.value = bannerId;
    document.listForm.pageIndex.value = pageIndex;
    document.listForm.searchCondition.value = searchCondition;
    document.listForm.searchKeyword.value = searchKeyword;
    
    document.listForm.action = "<c:url value='/sit/bnr/getBanner.do'/>";
    document.listForm.submit();
}

function fncAddBannerInsert() {
	if(document.listForm.pageIndex.value == "") {
		document.listForm.pageIndex.value = 1;
	}
    document.listForm.action = "<c:url value='/sit/bnr/addViewBanner.do'/>";
    document.listForm.submit();
}

function fncBannerListDelete() {
	if(fncManageChecked()) {
        if(confirm("삭제하시겠습니까?")) {
            document.listForm.action = "<c:url value='/sit/bnr/removeBannerList.do'/>";
            document.listForm.submit();
        }
    }
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sit/bnr/selectBannerList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectBannerList('1');
    }
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
	varForm.pageUnit.value = val;
    varForm.action = "<c:url value='/sit/bnr/selectBannerList.do'/>";
    varForm.submit();
}

-->
</script>
</head>
<body class="body">
<jsp:include page="/cmm/top/top.do" />

<DIV id="page-wrapper">        
    
    <form name="listForm" action="<c:url value='/sit/bnr/selectBannerList.do'/>" method="post">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">홍보존 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
	

			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#LINK" onClick="javascript:fn_tabClick('MNG')" data-toggle="tab">홍보존 관리</a>
                </li>
                <li class="">
                	<a href="#LINK" onClick="javascript:fn_tabClick('ImgMng')"  data-toggle="tab">홍보존소식 이미지 관리</a>
                </li>
            </ul>
            
            <!-- <h2 >홍보존 목록</h2>   -->
 			

			<div class="select_box">
				<span>
					<select name="searchCondition" id="searchCondition" class="input-sm"  style="width:150px;" title="검색조건">
						<option value='' >전체검색</option>
						<option value='selBannerNm' <c:if test="${bannerVO.searchCondition =='selBannerNm'}">selected</c:if>>홍보존 명</option>
						<option value='selLinkUrl' <c:if test="${bannerVO.searchCondition =='selLinkUrl'}">selected</c:if>>링크 URL</option>
					</select>
					<input name="searchKeyword" type="text" value="<c:out value="${bannerVO.searchKeyword}"/>" onkeypress="press();" id="searchKeyword" title="검색단어입력" class=" input-sm input-search">
					<input type="submit" class="btn btn-primary" value="<spring:message code="button.search" />" onclick="fncSelectBannerList('1'); return false;">
				</span>
			</div>

			<div class="page">
				총 건수 :  <c:out value="${paginationInfo.totalRecordCount}" />건

				<span>
					출력건수
					<select id="pageUnit" name="pageUnit" class="input-sm" onchange="fnChgListCnt(this.value);">
						<option value="10" <c:if test="${bannerVO.pageUnit == '10' }">selected</c:if>>10</option>
						<option value="30" <c:if test="${bannerVO.pageUnit == '30' }">selected</c:if>>30</option>
						<option value="50" <c:if test="${bannerVO.pageUnit == '50' }">selected</c:if>>50</option>
						<option value="100" <c:if test="${bannerVO.pageUnit == '100' }">selected</c:if>>100</option>
					</select>
				</span>
			
			</div>		
			
				<input type="hidden" id="pageSize" name="pageSize" value="${bannerVO.pageSize}"/>
		        <input type="hidden" name="currentPageNo" value="<c:out value='${ paginationInfo.currentPageNo }'/>" >
				<input type="hidden" name="bannerId">
				<input type="hidden" name="pageIndex" value="<c:if test="${empty bannerVO.pageIndex }">1</c:if><c:if test="${!empty bannerVO.pageIndex }"><c:out value='${bannerVO.pageIndex}'/></c:if>">
				</form>				
				
				<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="메인화면에서 배너에 대한 목록으로 배너명, 링크url,배너설명,반영여부를 제공한다.">
				 <thead>
				  <tr>
				    <th class="title" width="30%" scope="col">홍보존 명</th>
				    <th class="title" width="10%" scope="col">홍보존 이미지</th>
				    <th class="title" width="25%" scope="col">링크 URL</th>
				    <!--  <th class="title" width="30%" scope="col">홍보존 설명</th> -->
				    <th class="title" width="25%" scope="col">게시기간</th>
				    <th class="title" width="10%" scope="col">반영여부</th>
				  </tr>
				 </thead>
				 <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
				 <c:if test="${fn:length(bannerList) == 0}">
				 <tr>
				 <td class="lt_text3" colspan="5">
				 	<spring:message code="common.nodata.msg" />
				 </td>
				 </tr>
				 </c:if>
				 <tbody>
				 <c:forEach var="banner" items="${bannerList}" varStatus="status">
				  <tr>
				    <td class="lt_text3" >
				        <a href="#LINK" alt="<c:out value="${banner.bannerDc}" />" onclick="fncSelectBanner('<c:out value="${banner.bannerId}"/>','<c:out value="${bannerVO.pageIndex}"/>','<c:out value="${bannerVO.searchCondition}"/>','<c:out value="${bannerVO.searchKeyword}"/>'); return false;">
				        <c:out value="${banner.bannerNm}"/>
				        </a>
				    </td>
				    <td class="lt_text3" >
				    	<img src="<c:if test="${banner.bannerImageFile != null && banner.bannerImageFile != ''}">http://clik.nanet.go.kr</c:if><c:out value="${banner.bannerImageFile}" />" alt="<c:out value="${banner.bannerNm}"/>" width="80" height="80" /></td>
				    <td class="lt_text3" ><c:out value="${fn:substring(banner.linkUrl,0,50)}" /></td>
				    <!--  <td class="lt_text3" ><c:out value="${fn:substring(banner.bannerDc,0,70)}" />...</td>  -->
				    <td class="lt_text3" >
						<c:out value="${fn:substring(banner.ntceBgnde, 0, 4)}"/>-<c:out value="${fn:substring(banner.ntceBgnde, 4, 6)}"/>-<c:out value="${fn:substring(banner.ntceBgnde, 6, 8)}"/> <c:out value="${fn:substring(banner.ntceBgnde, 8, 10)}"/>시 <c:out value="${fn:substring(banner.ntceBgnde, 10, 12)}"/>분
				 		~
				 		<c:out value="${fn:substring(banner.ntceEndde, 0, 4)}"/>-<c:out value="${fn:substring(banner.ntceEndde, 4, 6)}"/>-<c:out value="${fn:substring(banner.ntceEndde, 6, 8)}"/> <c:out value="${fn:substring(banner.ntceEndde, 8, 10)}"/>시 <c:out value="${fn:substring(banner.ntceEndde, 10, 12)}"/>분				    
				    </td>
				    <td class="lt_text3" ><c:out value="${banner.reflctAt}"/></td>
				  </tr>
				 </c:forEach>
				 </tbody>
				</table>
				
				<p class="tr">		
					<button type="button" class="btn btn-primary" onclick="fncAddBannerInsert(); return false;"><spring:message code="button.create" /></button>												
					<!--
					<a href="<c:url value='/sit/pwm/listPopup.do' />"><button type="button" class="btn btn-default" ><spring:message code="button.list" /></button></a>
					<button type="button" class="btn btn-success"  onclick="fn_egov_modify_PopupManage(); return false;"><spring:message code="button.update" /></button>				
					<button type="button" class="btn btn-danger" onclick="fn_egov_delete_PopupManage(); return false;"><spring:message code="button.delete" /></button>						
					-->
				</p>    			
							
				<c:if test="${!empty bannerVO.pageIndex }">
				<div align="center">
				    <div>
				    	<ul class="pagination">
				        	<ui:pagination paginationInfo = "${paginationInfo}"
				            type="image"
				            jsFunction="linkPage"
				            />
				         </ul>
				    </div>
				</div>
				</c:if>
				

</DIV>	
<!-- /page-wrapper -->
</body>
</html>
