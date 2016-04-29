<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/clikMng/include/style.jsp" %>
<title>환경설정</title>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* ********************************************************
* 게실물 삭제
******************************************************** */
function fncDelOpinion() {
	//선택된 항목 체크
	var delSeq = '';
	var checkBox = $('#tableList').find(':input[name="chkbox"]');
	
	for(var i=0;i<checkBox.length; i++){
		var chkbox = checkBox[i];
		if($(chkbox).is(':checked')){
			
			if(delSeq.length > 0)
				delSeq += ',' + $(chkbox).parent().find(':input[name="seq"]').val();
			else
				delSeq += $(chkbox).parent().find(':input[name="seq"]').val();
		}
	}
	
	if(delSeq == '' || delSeq.length <= 0){
		alert('삭제할 항목을 선택해주세요.');
		return;
	}
	
	$('#delSeq').val(delSeq);
	var varForm = document.listForm;
	varForm.action = "<c:url value='/mob/OnsDel.do' />";

	varForm.submit();
}

/* ********************************************************
* 공지사항 등록
******************************************************** */
function fncAddNoticeInsert() {
	var varForm = document.listForm;
	varForm.action = "<c:url value='/mob/NoticeRegist.do' />";

	varForm.submit();
}

/* ********************************************************
* 공지사항 검색
******************************************************** */
function fnSearch() {
	
	if($("#searchKeyword").val() == "" || $("#searchKeyword").val() == null) {
		alert('검색어를 입력해 주세요.');
		return; 
	}
	
	var varForm = document.listForm;
	varForm.action = "<c:url value='/uss/mng/MngList.do' />";
	varForm.submit();
	
}


/* ********************************************************
* 페이징
******************************************************** */
function fn_paging(currentPageNo) {
    var varForm = document.listForm;
    varForm.currentPageNo.value = currentPageNo;
    varForm.target="_self";
    varForm.action = "<c:url value='/uss/mng/MngList.do'/>";
    varForm.submit();
}

/* ********************************************************
* 게시물 수 변경(셀렉트박스)
******************************************************** */
function fnChgListCnt(val) {
	var varForm = document.listForm;
    varForm.action = "<c:url value='/uss/mng/MngList.do'/>";
    varForm.submit();
}

/* ********************************************************
*  상세보기 및 수정 페이지로 이동
******************************************************** */
function fnMngDetailView(mngrId) {

	var varForm = document.listForm;
	varForm.mngrId.value = mngrId;
	varForm.action = "<c:url value='/uss/mng/MngDetail.do'/>";
	varForm.submit();
}
-->
</script>
</head>

<body class="body">
<jsp:include page="/cmm/top/top.do" />
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">메타데이터 관리</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>

			<h2>메타데이터 목록 </h2>
			<div class="search">
				기간 : <input type="text" class="input-sm ip" style="width:150px;" /> ~ <input type="text" class=" input-sm ip" style="width:150px;" /> 
				<button type="button" class="btn btn-success">한달</button>
				<button type="button" class="btn btn-danger">일주일</button>
			</div><!--//search-->
            
			<div class="select_box">
				
				<select name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option>중앙정부</option>
					<option>지방정부</option>
					<option>지방의회</option>
					<option>국회</option>
					<option>국회도서관</option>
					<option>중앙정부산하 연구기관</option>
					<option>지방정부산하 연구기관</option>
					<option>연구기관(기타)</option>
					<option>기타</option>
				
				</select>
				<select name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option>중앙정부------------------------</option>
					<option>기획재정부</option>
					<option>미래창조과학부</option>
					<option>교육부</option>
					<option>외교부</option>
					<option>통일부</option>
					<option>법무부</option>
					<option>국방부</option>
					<option>안전행정부</option>
					<option>문화체육관광부</option>
					<option>농림축산식품부</option>
					<option>산업통상자원부</option>
					<option>보건복지부</option>
					<option>환경부</option>
					<option>고용노동부</option>
					<option>여성가족부</option>
					<option>국토교통부</option>
					<option>해양수산부</option>

					<option>지방정부--------------- </option>
					<option>서울특별시</option>
					<option>부산광역시</option>
					<option>대구광역시</option>
					<option>인천광역시</option>
					<option>광주광역시</option>
					<option>대전광역시</option>
					<option>울산광역시</option>
					<option>세종특별자치시</option>
					<option>경기도</option>
					<option>강원도</option>
					<option>충청북도</option>
					<option>충청남도</option>
					<option>전라북도</option>
					<option>전라남도</option>
					<option>경상북도</option>
					<option>경상남도</option>
					<option>제주특별자치도</option>

					<option>지방의회------------ </option>
					<option>서울특별시의회</option>
					<option>부산광역시의회</option>
					<option>대구광역시의회</option>
					<option>인천광역시의회</option>
					<option>광주광역시의회</option>
					<option>대전광역시의회</option>
					<option>울산광역시의회</option>
					<option>세종특별자치시의회</option>
					<option>경기도의회</option>
					<option>강원도의회</option>
					<option>충청북도의회</option>
					<option>충청남도의회</option>
					<option>전라북도의회</option>
					<option>전라남도의회</option>
					<option>경상북도의회</option>
					<option>경상남도의회</option>
					<option>제주특별자치도의회</option>

					<option>중앙정부산하 연구기관--------</option>
					<option>과학기술정책연구원 [STEPI]</option>
					<option>한국교통연구원 [KOTI]</option>
					<option>국토연구원 [KRIHS]</option>
					<option>대외경제정책연구원 [KIEP]</option>
					<option>산업연구원 [KIET]</option>
					<option>에너지경제연구원 [KEEI]</option>
					<option>정보통신정책연구원[KISDI]</option>
					<option>한국개발연구원 [KDI]</option>
					<option>한국노동연구원 [KLI]</option>
					<option>한국농촌경제연구원 [KREI]</option>
					<option>한국보건사회연구원 [KIHASA]</option>
					<option>한국조세연구원 [KIPF]</option>
					<option>한국해양수산개발원 [KMI]</option>
					<option>한국환경정책·평가연구원 [KEI]</option>
					<option>통일연구원 [KINU]</option>
					<option>한국교육개발원 [KEDI]</option>
					<option>한국교육과정평가원 [KICE]</option>
					<option>한국법제연구원 [KLRI]</option>
					<option>한국여성개발원 [KWDI]</option>
					<option>한국직업능력개발원 [KRIVET]</option>
					<option>한국청소년개발원[KIYD]</option>
					<option>한국행정연구원 [KIPA]</option>
					<option>한국형사정책연구원 [KIC]</option>

					<option>지방정부산하 연구기관--------</option>
					<option>미확정</option>
					<option>연구기관(기타--------) </option>
					<option>미확정</option>
					<option>기타-------- </option>
					<option>미확정</option>
					
				
				</select>
				<br/>
				<select name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option>자료유형선택</option>
					<option>전체</option>
					<option>회의록</option>
					<option>의안</option>
					<option>의원</option>
					<option>홍보/보도/소식</option>
					<option>정책자료/업무보고자료</option>
					<option>연구자료</option>
					<option>의정활동자료</option>
					<option>통계</option>
					<option>정책매뉴얼</option>
					<option>출장보고서</option>
					<option>세미나/공청회</option>
					<option>교육&매뉴얼</option>
					<option>지역현안자료</option>
				
				</select>
				<select name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option>수집유형선택</option>
					<option>전체</option>
					<option>웹크롤러</option>
					<option>연계API</option>
					<option>수동입력</option>
				
				</select>
				<select name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option>변환여부</option>
					<option>전체</option>
					<option>없음</option>
					<option>성공</option>
					<option>실패</option>
				
				</select>
				<select name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm" style="width:150px;">
					<option>중복여부</option>
					<option>전체</option>
					<option>Y</option>
					<option>N</option>
				
				</select>

				


				<span>
					<select name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm" style="width:80px;">
						<option>전체</option>
						<option>제목</option>
						<option>내용</option>
					
					</select>


					<input type="text" class="ip input-sm" style="width:200px;" />
					<button type="button" class="btn btn-primary" >검색</button>
					<input type="checkbox" id="de" class="" /> <label for="de">결과내검색</label>
				</span>
			
			</div><!--//tc-->
				


			<div class="page">
				총 건수 : ${minutesListTotCnt }건

				<span>
					출력건수
					<select name="dataTables-example_length" aria-controls="dataTables-example" class=" input-sm">
						<option>10</option>
						<option>20</option>
						<option>50</option>
						<option>100</option>
					</select>
				</span>
			
			</div>
				
				
				
			<table class="table table-striped table-bordered table-hover "  id="">
					<colgroup>
						<col width="5%" />
						<col />
						<col />
						<col />
						<col />
						<col />
						<col />
						<col />
						<col />
						<col />
						<col />
					
					</colgroup>

					<thead>
						<tr>
							<th>번호</th>
							<th>기관유형</th>
							<th>자료유형</th>
							<th>수집유형</th>
							<th>제목</th>
							<th>등록일자</th>
							<th>첨부</th>
							<th>변환</th>
							<th>처리</th>
							<th>중복</th>
							<th><input type="checkbox" /></th>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach var="mList" items="${minutesList}" varStatus="status">
					
						<tr>
							<td>1</td>
							<td>서울특별시</td>
							<td>홍보/보도/소식</td>
							<td>
								<c:choose>
									<c:when test="${fn:indexOf(mList.RASMBLY_MINTS_ID, 'CLIK') != -1}">API</c:when>
									<c:otherwise>웹크롤러</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${mList.RASMBLY_SESN < '1991'}"> 제${mList.RASMBLY_SESN }회 </c:when>
									<c:otherwise>${mList.RASMBLY_SESN }년도</c:otherwise>
								</c:choose> 
								<c:choose>
									<c:when test="${mList.MINTS_ODR > '0'}">제${mList.MINTS_ODR }차</c:when>
									<c:otherwise>${mList.ODR_NM }</c:otherwise>
								</c:choose> ${mList.MTGNM }
							</td>
							<td>${mList.MTG_DE }</td>
							<td>-</td>
							<td>없음</td>
							<td>없음</td>
							<c:choose>
								<c:when test="${mList.DUPLICATION > '1'}"> 
									<td class="relative"  onmouseover="MM_showHideLayers('pop_data${mList.MTGNM_ID}${mList.MTG_DE}','','show');" onmouseout="MM_showHideLayers('pop_data${mList.MTGNM_ID}${mList.MTG_DE}','','hide');">
										<a href="#none" class="r" >Y</a>
										<div id="pop_data${mList.MTGNM_ID}${mList.MTG_DE}" class="pop_data">
											<div>
												<table class="table table-striped table-bordered table-hover "  id="">
													<tbody>
														<tr>
															<td>11111111</td>
															<td>${mList.MTGNM }</td>
															<td class="r">삭제</td>
														
														</tr>
														<tr>
															<td>1197</td>
															<td>${mList.MTGNM }</td>
															<td class="r">삭제</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</td>
								</c:when>
								<c:otherwise><td>N</td></c:otherwise>
							</c:choose> 
							<td><input type="checkbox" /></td>
						</tr>
					
					</c:forEach>

						
<!-- 						<tr>
							<td>1</td>
							<td>서울특별시</td>
							<td>홍보/보도/소식</td>
							<td>웹크롤러</td>
							<td>2014 서울김장축제 참여안내</td>
							<td>2014-07-18</td>
							<td>HWP</td>
							<td>성공</td>
							<td>삭제</td>
							<td>N</td>
							<td><input type="checkbox" /></td>
						</tr>
						<tr>
							<td>1</td>
							<td>서울특별시</td>
							<td>홍보/보도/소식</td>
							<td>웹크롤러</td>
							<td>2014 서울김장축제 참여안내</td>
							<td>2014-07-18</td>
							<td>PPT</td>
							<td class="r">실패</td>
							<td>재변환/삭제</td>
							<td class="relative"  onmouseover="MM_showHideLayers('pop_data','','show');" onmouseout="MM_showHideLayers('pop_data','','hide');">
								<a href="#none" class="r" >Y</a>

								<div id="pop_data"class="pop_data">
									<div>
										<table class="table table-striped table-bordered table-hover "  id="">
											<tbody>
												<tr>
													<td>11111111</td>
													<td>지방교육재정교부금 확대 촉구 공동성명서 발표</td>
													<td class="r">삭제</td>
												
												</tr>
												<tr>
													<td>1197</td>
													<td>지방교육재정교부금 확대 촉구 공동성명서 발표</td>
													<td class="r">삭제</td>
												</tr>
											</tbody>
										</table>
									</div>//pop_data
									
								</div>//pop_data
							
							</td>
							<td><input type="checkbox" /></td>
						</tr>
						<tr>
							<td>1</td>
							<td>서울특별시</td>
							<td>홍보/보도/소식</td>
							<td>웹크롤러</td>
							<td>2014 서울김장축제 참여안내</td>
							<td>2014-07-18</td>
							<td>HWP</td>
							<td>성공</td>
							<td>삭제</td>
							<td>N</td>
							<td><input type="checkbox" /></td>
						</tr>
						
						<tr>
							<td>1</td>
							<td>서울특별시</td>
							<td>홍보/보도/소식</td>
							<td>웹크롤러</td>
							<td>2014 서울김장축제 참여안내</td>
							<td>2014-07-18</td>
							<td>PPT</td>
							<td class="r">실패</td>
							<td>재변환/삭제</td>
							<td class="relative"  onmouseover="MM_showHideLayers('pop_data1','','show');" onmouseout="MM_showHideLayers('pop_data1','','hide');">
								<a href="#none" class="r" >Y</a>

								<div id="pop_data1" class="pop_data">
									<div >
										<table class="table table-striped table-bordered table-hover "  id="">
											<tbody>
												<tr>
													<td>22222</td>
													<td>지방교육재정교부금 확대 촉구 공동성명서 발표</td>
													<td class="r">삭제</td>
												
												</tr>
												<tr>
													<td>1197</td>
													<td>지방교육재정교부금 확대 촉구 공동성명서 발표</td>
													<td class="r">삭제</td>
												</tr>
											</tbody>
										</table>
									</div>//pop_data
									
								</div>//pop_data
							
							</td>
							<td><input type="checkbox" /></td>
						</tr>
 -->
						
						
						
					</tbody>
				</table>

				

				<p class="tr">
					<button type="button" class="btn btn-primary">등록</button>
					<button type="button" class="btn btn-success">게시</button>
					<button type="button" class="btn btn-danger">삭제</button>
				</p>


					

			<div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
				<ul class="pagination">
					<li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous">
					<a href="#">Previous</a></li>
					<li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
					<a href="#">1</a></li>
					<li class="paginate_button active" aria-controls="dataTables-example" tabindex="0">
					<a href="#">2</a></li>
					<li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
					<a href="#">3</a></li>
					<li class="paginate_button " aria-controls="dataTables-example" tabindex="0">
					<a href="#">4</a></li>
					<li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next">
					<a href="#">Next</a>
					</li>
				</ul>
			</div><!--//dataTables_paginate-->
</body>
</html>
