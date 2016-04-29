<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name = "viewport" content = "width = device-width" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="imagetoolbar" content="no" />
<meta name="robots" content="index,follow" />
<meta name="classification" content="internet" />
<meta name="language" content="ko" />
<title>국회도서관 회의록 뷰어</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/clikmng/mdm/minutes/jquery_ui.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/clikmng/mdm/minutes/base.css'/>"/>
<link href="<c:url value='/css/clikmng/mdm/minutes/mvMobile.css' />" rel="stylesheet" type="text/css" media="screen and (min-width:0px) and (max-width:720px)"/> 
<link href="<c:url value='/css/clikmng/mdm/minutes/mvPc.css' />" rel="stylesheet" type="text/css" media="screen and (min-width:721px)"/>
<!--[if lt IE 9]><link href="<c:url value='/css/clikmng/mdm/minutes/mvPc.css' />" rel="stylesheet" type="text/css" /><![endif]-->
<script src="<c:url value='/js/clikmng/mdm/minutes/jquery-1.11.1.min.js' />"></script>
<script src="<c:url value='/js/clikmng/mdm/minutes/ui/jquery-ui.js' />"></script>
<script src="<c:url value='/js/clikmng/mdm/minutes/minutes.js' />"></script>
</head>
<body>
	<div id="contentarea">
		<div id="header">
			<h1 id="minutestitle_top">&nbsp;</h1>
			<div class="menu_view">
				<a href="#" onclick="menuToggle();return false;"><img src="<c:url value='/images/clikmng/mdm/minutes/btn_menu.png' />" alt="전체메뉴" /></a>
			</div>
		</div>	
		<div id="wideDiv" style="position:relative"></div>
		<div id="middleDiv" style="float:left;">
			<div class="rightDiv">
				<div id="titlearea" class="sticky">
					<ul>
						<li class="first">${ViewConcilName}</li>
						<li>${ViewCmtName}</li>
						<li id="minutestitle">${ViewTitle}</li>
					</ul>
					<div id="controlbox">
						<div class="controlbox_right">
							<form name="control" action="?" onsubmit="return false;">
							<fieldset>
								<legend>컨트롤박스</legend>
								<input type="hidden" name="CCode" value="" title="의회 코드 (숨겨진요소)" />
								<input type="hidden" name="daesu" value="10" title="대수 (숨겨진요소)" />
								<input type="hidden" name="th" value="274" title="회기 (숨겨진요소)" />
								<input type="hidden" name="cha" value="2" title="회차 (숨겨진요소)" />
								<input type="image" onclick="FontSize(this.value)" value="+" src="<c:url value='/images/clikmng/mdm/minutes/btn_big.gif' />" title="글자확대" />
								<input type="image" onclick="FontSize(this.value)" value="-" src="<c:url value='/images/clikmng/mdm/minutes/btn_small.gif' />" title="글자축소" />
								<select id="fontFamily" name="fontFamily" title="글꼴선택" onchange="FontChange()">
									<option value="x" title="글꼴">글꼴</option>
									<option value="굴림" title="굴림">굴림</option>
									<option value="돋움" title="돋움">돋움</option>
									<option value="바탕" title="바탕">바탕</option>
									<option value="궁서" title="궁서">궁서</option>
									<option value="NanumGothic" title="나눔">나눔</option>
								</select>
								<c:if test="${fn:length(ViewText) > 0}">
								<input type="image" id="printBtn" title="회의록 출력" src="<c:url value='/images/clikmng/mdm/minutes/btn_print.gif' />" link="<c:url value='/mdm/MdmMinutesPrintView.do?MINTS_CN=${ViewDocID}' />" />
								</c:if>
								<c:if test="${fn:length(ViewText) == 0}">
								</c:if>
								<c:if test="${fn:length(ViewOrginFileLoc) > 0}">
								<input type="image" id="orgindnBtn"  title="회의록  원문 다운" src="<c:url value='/images/clikmng/mdm/minutes/middle_originals.png' />" link="${ViewOrginFileLoc}"  />
								</c:if>
								<c:if test="${fn:length(ViewOrginFileLoc) > 0}">
								</c:if>								
								
							</fieldset>
							</form>	
						</div>
					</div>
				</div>
				<div id="content">
					<div id="menuarea" class="sticky">
						<div id="schshow">
							<h3>본문검색</h3>
							<form name="sch" method="get" action="?" onsubmit="return false;">
							<fieldset>
								<legend>본문검색</legend>
								<label for="keyword">검색</label>
								<input type="text" name="keyword_small" id="keyword" size="20" title="검색어를 입력하세요." />
								<input type="image" src="<c:url value='/images/clikmng/mdm/minutes/btn_search.gif' />" alt="검색" id="btnSearch" class="btnsch" />
							</fieldset>
							</form>
						</div>
						
						<div id="menushow" class="n13_menu">				
							<h3>안건별보기</h3>
							<div>
								<ul>
								<c:choose>
								<c:when test="${fn:length(Bill) > 0}">
									<c:forEach var="rsltbill" items="${Bill}" varStatus="status">
									<li class="agenda"><a href="#" onclick="billFocus('${status.index}');return false;" title="<c:out value="${rsltbill.MTR_SJ}"/> 이동">${rsltbill.MTR_SJ}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<li class="agenda none">안건이 없습니다.</li>
								</c:otherwise>
								</c:choose>
								</ul>
							</div>
							<h3>별도자료</h3>
							<div>
								<ul>
								<c:choose>
								<c:when test="${fn:length(Appendix) > 0}">
									<c:forEach var="rsltAppendix" items="${Appendix}" varStatus="status">
									<li class="appendix"><a href="<c:url value="${rsltAppendix.APNDX_FILE_URL}"/>" class="apdxdownlink" onclick="return false;" title="${ rsltAppendix.APNDX_FILE_NM } 다운">${ rsltAppendix.APNDX_FILE_NM }</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<li class="appendix none">관련 자료가 없습니다.</li>
								</c:otherwise>
								</c:choose>
								</ul>
							</div>
							<h3>참석의원발언</h3>
							<div>
								<ul>
								<c:choose>
								<c:when test="${fn:length(Speaker) > 0}">
									<c:forEach var="rsltSpeaker" items="${Speaker}" varStatus="status">
									<li class="leftAssem" rasmbly_id="<c:out value="${rsltSpeaker.RASMBLY_ID}"/>" asemby_id="<c:out value="${rsltSpeaker.ASEMBY_ID}"/>"><c:out value="${rsltSpeaker.ASEMBY_NM}"/></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<li class="agenda none">참석 의원의 발언이 없습니다.</li>
								</c:otherwise>
								</c:choose>
								</ul>
							</div>
						</div>
					</div>
					<div id="viewarea">
						<div id="viewer">
						<c:if test="${fn:length(ViewText) > 0}">
						${ViewText}
						</c:if>
						<c:if test="${fn:length(ViewText) == 0}">
						<div id="none_conference_text">
							회의록 수집중입니다.
						</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<div class="shareBackDiv2"></div>
		<script type="text/javascript">
		$(window).load(function(){
			var leftDivHeight=$(".leftDiv").height();
		});
		$(window).on("resize load", function(event) {
		   pageResize();
	
		});
		function pageResize(){
		   $(".n13_menu").css({"height":$("#middleDiv").height()+"px"});
		   /*
		   if($("body").width()>719){
			  if($(".leftDiv").height()<$(".rightDiv").height()){
				  $(".leftDiv").height($(".rightDiv").height()+"px");
			  }else{
				  $(".leftDiv").css({"height":$(".leftDiv").height()+"px"});
			  }
		   }else{
			   $(".leftDiv").css({"height":""});
		   }
		   */
		   if($("body").width()>719){
			  if($(".leftDiv").height()<$(".rightDiv").height()){
				  $(".leftDiv").css({"border":"0px"});
				  $(".rightDiv").css({"border-left":"1px solid #D7D7D7"});
			  }else{
				 $(".leftDiv").css({"border-right":"1px solid #D7D7D7"});
				  $(".rightDiv").css({"border":"0px"});
			  }
		   }else{
			  // $(".leftDiv").css({"height":""});
		   }
		}
		</script>
		<div id="footer">
			<div id="foot">
				<p class="logo"><img src="<c:url value='/images/clikmng/mdm/minutes/logo_foot.gif' />" alt="국회도서관" /></p>
				<p class="copyright">Copyright(c) 대한민국 국회도서관. All rights reserved.</p>
			</div>
		</div>
	</div>
</div>
</body>
</html>