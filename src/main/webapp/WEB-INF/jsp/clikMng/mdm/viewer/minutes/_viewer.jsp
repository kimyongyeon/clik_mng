<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="imagetoolbar" content="no" />
	<meta name="robots" content="index,follow" />
	<meta name="classification" content="internet" />
	<meta name="language" content="ko" />
	<title>${ViewTitle}</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/clikmng/minutes/minutes.css'/>"/>
	<script src="<c:url value='/js/clikmng/jquery-1.10.2.js' />"></script>
	<%-- <script src="<c:url value='/js/clikmng/ui/jquery-ui.js' />"></script> --%>
	<script type="text/javascript">
	//<![CDATA[
		var old_num = 1;
		$(function() {
			$( "#menu" )
			.accordion({
				header: "> div > h3",
				heightStyle: "content"
			})

			if (!!$('.sticky').offset()) // make sure ".sticky" element exists
			{
				var stickyTop = $('.sticky').offset().top; // returns number 
				$(window).scroll(function(){ // scroll event
					var windowTop = $(window).scrollTop(); // returns number 
					if (stickyTop < windowTop)
					{
						$('.sticky').css({ position: 'fixed', top: 0 , width:'240px'});
					}
					else 
					{
						$('.sticky').css('position','static');
					}
				});
			}
		});
		
		function billFocus(i)
		{
			var viewid = eval(i) + 1;
			$(window).scrollTop($("#matter_" + viewid).offset().top);
			
			//alert($("#matter_" + viewid).offset().top);
		}
		//]]>
	</script>
	</head>
	<body>
		<div id="basis">
			<div id="top">
				<h1>${ViewTitle}</h1>
				<div id="sch"></div>
			</div>
			<div id="left">
				<div id="menu" class="sticky">
					<div class="group">
						<h3>안건별보기</h3>
						<div>
							<ul>
							<c:forEach var="rsltbill" items="${Bill}" varStatus="status">
								<li>
									<span class="agenda"><a href="#" onclick="billFocus('${status.index}')"><c:out value="${rsltbill.MTR_SJ}"/></a></span>
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>
					<div class="group">
						<h3>별도자료</h3>
						<div>
							<c:forEach var="rsltAppendix" items="${Appendix}" varStatus="status">
								<li>
									<span class="appendix"><a href="#"><c:out value="${rsltAppendix.APNDX_FILE_NM}"/></a></span>
								</li>
							</c:forEach>
						</div>
					</div>
					<div class="group">
						<h3>발언자</h3>
						<div>
							<c:forEach var="rsltSpeaker" items="${Speaker}" varStatus="status">
								<span class="name">${rsltSpeaker.SPKR_PSITN_NM}</span>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>
			<div id="right">
				${ViewText}
			</div>
		</div>
	</body>
</html>
