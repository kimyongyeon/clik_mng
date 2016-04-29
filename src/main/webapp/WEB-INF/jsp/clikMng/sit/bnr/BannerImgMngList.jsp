<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
 * @Class Name : EgovBannerList.java
 * @Description : EgovBannerList jsp
 */

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

/* ********************************************************
* 등록 페이지 이동
******************************************************** */
function fnBannerImgMngRegist() {
	if(document.listForm.pageIndex.value == "") {
		document.listForm.pageIndex.value = 1;
	}
    document.listForm.action = "<c:url value='/sit/bnr/RegistBannerImgMng.do'/>";
    document.listForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
	varForm.pageUnit.value = val;
	varForm.action = "<c:url value='/sit/bnr/selectBannerImgMngView.do'/>";
    varForm.submit();
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************* */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/sit/bnr/selectBannerImgMngView.do'/>";
   	document.listForm.submit();
}
-->
</script>

</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<DIV id="page-wrapper">        
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">홍보존 관리</h1>
        </div>
        <!-- /.col-lg-12 --> 
    </div>
    <!-- /row -->
	
	
			<ul class="nav nav-tabs">
				<li class="">
					<a href="#LINK" onClick="javascript:fn_tabClick('MNG')" data-toggle="tab">홍보존 관리</a>
                </li>
                <li class="active">
                	<a href="#LINK" onClick="javascript:fn_tabClick('CLIK')"  data-toggle="tab">홍보존소식 이미지 관리</a>
                </li>
            </ul>				

				
			<form name="listForm" action="<c:url value='/sit/bnr/selectBannerImgMngView'/>" method="post">
				<div class="page">
					총 건수 : <c:out value="${paginationInfo.totalRecordCount}" /> 건
	
					<span>
						출력건수
							<select title="쪽당출력건수" id="pageSize" name="pageSize" onchange="fnChgListCnt(this.value)">
								<option value="10" <c:if test="${bannerVO.pageUnit == '10' }">selected</c:if>>10</option>
								<option value="30" <c:if test="${bannerVO.pageUnit == '30' }">selected</c:if>>30</option>
								<option value="50" <c:if test="${bannerVO.pageUnit == '50' }">selected</c:if>>50</option>
								<option value="100" <c:if test="${bannerVO.pageUnit == '100' }">selected</c:if>>100</option>
		                    </select>	
					</span>
				</div>					
				
				<table width="100%" cellpadding="8" class="table table-striped table-bordered" summary="메인화면에서 배너에 대한 목록으로 배너명, 링크url,배너설명,반영여부를 제공한다.">
				 <thead>
				  <tr>
				    <th class="title" width="5%" scope="col">번호</th>
				    <th class="title" width="25%" scope="col">지역</th>
				    <th class="title" scope="col">이미지</th>
				    <th class="title" width="20%" scope="col">고정여부</th>
				  </tr>
				 </thead>
				 <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
				 <c:if test="${fn:length(resultList) == 0}">
				 <tr>
				 	<td colspan="4"><spring:message code="common.nodata.msg" /></td>
				 </tr>
				 </c:if>
				 <tbody>
				 <c:forEach var="banner" items="${resultList}" varStatus="status">
				 	<tr>
				    <td>${(bannerVO.pageIndex-1) * bannerVO.pageSize + status.count}</td>
				    <td>
				    	<a href="/sit/bnr/DetailBannerImgMng.do?bannerImgMngId=<c:out value='${banner.bannerImgMngId}'/>&pageIndex=<c:out value='${bannerVO.pageIndex}'/>">
				    		<c:out value="${banner.areaNm}"/>
				    	</a>
				    </td>
				    <td>
				    	<%-- <img src='<c:url value='/cmm/fms/getImage2.do'/>?fileExtsn=<c:out value="${banner.fileExtsn}"/>&streFileNm=<c:out value="${banner.streFileNm}"/>&fileStreCours=<c:out value="${banner.streCours}"/>'  alt="해당파일이미지"/> --%>
				    	<c:import url="/cmm/fms/selectImageFileInfs.do" charEncoding="utf-8">
							<c:param name="atchFileId" value="${banner.imageCnvrFileNm}" />
							<c:param name="fileSn" value="0" />
							<%-- <c:param name="streCours" value="${banner.streCours}" />
							<c:param name="streFileNm" value="${banner.streFileNm}" />
							<c:param name="fileExtsn" value="${banner.fileExtsn}" /> --%>							
						</c:import>
							
				    </td>
				    <td><c:out value="${banner.fixingAt}"/></td>
				  </tr> 
				 </c:forEach>
				 </tbody>
				</table>
				
				<p class="tr">
				<button type="button" class="btn btn-primary" onclick="fnBannerImgMngRegist(); return false;"><spring:message code="button.create" /></button>												
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

				<input type="hidden" name="bannerId">
				<input type="hidden" name="pageIndex" value="<c:out value='${bannerVO.pageIndex}'/>"/>
				<input name="pageUnit" type="hidden" value="<c:out value='${bannerVO.pageUnit}'/>"/>
				<input type="hidden" name="searchCondition" value="1">
				</form>
	
</DIV>	
<!-- /page-wrapper -->
</body>
</html>
