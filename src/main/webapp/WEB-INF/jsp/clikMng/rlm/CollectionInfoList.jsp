<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui"		uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring"	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"		uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>연계 API 수집 내역</title>

<script type="text/javaScript" language="javascript" defer="defer">



/* ********************************************************
 * 페이징 처리 함수
 ******************************************************* */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/rlm/CollectionInfoList.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
    varForm.action = "<c:url value='/rlm/CollectionInfoList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 정렬 변경(셀렉트박스)
******************************************************** */
function fnChgOrder(val) {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/rlm/CollectionInfoList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.currentPageNo.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/rlm/CollectionInfoList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 페이징
******************************************************** */
function fn_search() {
    var varForm = document.listForm;
    varForm.action = "<c:url value='/rlm/CollectionInfoList.do'/>";
    varForm.submit();
}


</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
<form id="listForm" name="listForm" method="post">

<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${searchVO.pageSize}"/>" />

		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">연계 API 수집 내역</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

            <!-- /.row -->

			<div class="search">
					<input type="text" name="schDt1" id="schDt1" value="<c:out value="${searchVO.schDt1}" />" class="input-sm ip" style="width:150px;" />
					&nbsp;~&nbsp;
					<input type="text" name="schDt2" id="schDt2" value="<c:out value="${searchVO.schDt2}" />" class="input-sm ip" style="width:150px;" />
					
	 				<a href="#" onclick="getDate('M','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default1">한달</button></a>
					<a href="#" onclick="getDate('W','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default2">일주일</button></a>
					<a href="#" onclick="getDate('T','schDt1','schDt2')"><button type="button" class="btn btn-default btn-default3">오늘</button></a>
	 				<script>setCal("schDt1","schDt2");</script>
			</div><!--//tc-->

			<div class="select_box">
				<span>
					<select name="selectRasmbly" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
						<option value="">연계 의회 선택 </option>
						<c:forEach items="${rasmblyList}" var="x" varStatus="s">
							<option value="${x.rasmblyId}" <c:if test="${selectRasmbly == x.rasmblyId }">selected</c:if>>${x.rasmblyNm }</option>
						</c:forEach>
					</select>
					<select name="selectApi" aria-controls="dataTables-example" class=" input-sm" style="width:200px;">
						<option value="">연계 API 구분 선택    </option>
						<c:forEach items="${apiList}" var="x" varStatus="s">
							<option value="${x.codeId}" <c:if test="${selectApi == x.codeId }">selected</c:if>>${x.codeNm}</option>
						</c:forEach>						
					</select>
					
					<button type="button" class="btn btn-primary"  onclick="fn_search();">검색</button>
				</span>
			</div><!--//select_box-->
				
			<div class="page">
				총 건수 : <fmt:formatNumber value="${paginationInfo.totalRecordCount}" type="number" />건

				<span>
					정렬 : 
					<select id="sortOrder" name="sortOrder" onchange="fnChgOrder(this.value)">
						<option value="">정렬기준</option>
						<option value="rasmblyNm_DESC" <c:if test="${searchVO.sortOrder == 'rasmblyNm_DESC'}">selected</c:if>>연계 의회 내림차순</option>
						<option value="rasmblyNm_ASC" <c:if test="${searchVO.sortOrder == 'rasmblyNm_ASC'}">selected</c:if>>연계 의회 오름차순</option>
						<option value="codeNm_DESC" <c:if test="${searchVO.sortOrder == 'codeNm_DESC'}">selected</c:if>>연계 API 구분 내림차순</option>
						<option value="codeNm_ASC" <c:if test="${searchVO.sortOrder == 'codeNm_ASC'}">selected</c:if>>연계 API 구분 오름차순</option>
						<option value="recptnTotcnt_DESC" <c:if test="${searchVO.sortOrder == 'recptnTotcnt_DESC'}">selected</c:if>>수신건수 내림차순</option>
						<option value="recptnTotcnt_ASC" <c:if test="${searchVO.sortOrder == 'recptnTotcnt_ASC'}">selected</c:if>>수신건수 오름차순</option>
						<option value="occrrncDe_DESC" <c:if test="${searchVO.sortOrder == 'occrrncDe_DESC'}">selected</c:if>>수신일자 내림차순</option>
						<option value="occrrncDe_ASC" <c:if test="${searchVO.sortOrder == 'occrrncDe_ASC'}">selected</c:if>>수신일자 오름차순</option>		
					</select>

					&nbsp;&nbsp;&nbsp;
				
					출력건수
					<select name="pageUnit" id="listCnt" aria-controls="dataTables-example" class=" input-sm" onchange="fnChgListCnt(this.value)">
						<option value="10" <c:if test="${searchVO.pageUnit == '10'}"> selected="selected"</c:if>>10</option>
						<option value="30" <c:if test="${searchVO.pageUnit == '30'}"> selected="selected"</c:if>>30</option>
						<option value="50" <c:if test="${searchVO.pageUnit == '50'}"> selected="selected"</c:if>>50</option>
						<option value="100" <c:if test="${searchVO.pageUnit == '100'}"> selected="selected"</c:if>>100</option>
					</select>
				</span>

			</div>
				

				<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="5%" />					
						<col width="30%" />
						<col width="" />
						<col width="" />
						<col width="" />
						<col width="" />
						<col width="" />
					
					
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>연계 의회</th>
							<th>연계 API 구분</th>
							<th>수신건수</th>
							<th>수신일자</th>
							<th>에러메시지</th>
						
						
						</tr>
					</thead>
					<tbody>
					<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
					<td class="lt_text3" colspan="6">
						<spring:message code="common.nodata.msg" />
					</td>
					</tr>
					</c:if>		
					
					<%-- 데이터를 화면에 출력해준다 --%>
					<c:forEach items="${resultList}" var="x" varStatus="s">
					<tr>
						<td>${(searchVO.pageIndex-1) * searchVO.pageUnit + s.count}</td>
						<td>${x.rasmblyNm}</td>
					    <td>${x.codeNm}</td>
					    <td>${x.recptnTotcnt}</td>
					    <td>${x.occrrncDe}</td>
					    <td>${x.resultMssage}</td>
					</tr>
					</c:forEach>										
					</tbody>
				</table>
				
				<c:if test="${!empty searchVO.pageIndex }">
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

				 <!-- /.panel-body -->
                     
                    </div>
                    <!-- /.panel .chat-panel -->
                </div>
                <!-- /.col-lg-4 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
</form>	
</body>
</html>
