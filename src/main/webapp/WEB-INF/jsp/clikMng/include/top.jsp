<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %> 
<link rel="StyleSheet" href="/js/clikmng/menu/dtree.css" type="text/css" />
<script type="text/javascript" src="/js/clikmng/menu/dtree.js"></script>
<script type="text/javascript" src="/js/clikmng/cmm/common.js"></script>
<!-- Core CSS - Include with every page 김우빈 주석
<link href="/css/clikmng/bootstrap.min.css" rel="stylesheet">
<link href="/css/clikmng/font-awesome.css" rel="stylesheet">-->
<!-- Page-Level Plugin CSS - Dashboard 
<link href="/css/clikmng/morris-0.4.3.min.css" rel="stylesheet">
<link href="/css/clikmng/timeline.css" rel="stylesheet">-->
<!-- SB Admin CSS - Include with every page 
<link href="/css/clikmng/sb-admin.css" rel="stylesheet">
<script type="text/javascript" src="/js/clikmng/respond_src.js"></script>
<script type="text/javascript" src="/js/clikmng/default.js"></script>-->
	
	   <div class="header">
		   <h1><a class="navbar-brand" href="/cmm/dashBoard/DashBoard.do"><img src="/images/clikmng/logo_admin.png" alt="지방의회 지식정보 공유시스템" /></a></h1>
		   <ul class="lnb">
			   <li>
				   <a href="/uat/uia/actionLogout.do">로그아웃</a>
			   </li>
		   </ul><!--//lnb-->
	   </div> <!-- //header -->		
	   
	   <div class="snb" role="navigation">
	            <div class="menu">   
	                <ul class="nav" id="side-menu"> 
						<script type="text/javascript">
						<!--
						var currentUrl = '<%= request.getAttribute("javax.servlet.forward.request_uri") %>';
						
						var d = new dTree('d');
						<c:forEach var="x" items="${menuList}" varStatus="status" >
						if(currentUrl === '${x.progrmFileNm}'){
							fn_setCookie('currentMenuUrl','${x.progrmFileNm}');
						}
						</c:forEach>
						
						if(currentUrl !== '/cmm/dashBoard/DashBoard.do' && currentUrl !== '/uat/uia/actionMain.do'){
							var cookies = getCookies();
							$(cookies).each(function(index, value){
								if(value.indexOf("currentMenuUrl") > -1)
								{
									currentUrl = value.split("=")[1];
								}
							});
						}
						
						<c:forEach var="x" items="${menuList}" varStatus="status" >
							d.add(<c:out value='${x.menuNo}' />,<c:out value='${x.upperMenuNo}' />,'<c:out value='${x.menuNm}' />','<c:out value='${x.progrmFileNm}' />', currentUrl, '<c:out value='${x.menuLevel}' />');
						</c:forEach>						
						
						document.write(d);
						
						
						var onId = $('#side-menu').find('.on').attr('id');
						
						if(onId != undefined){
						
							if ( $('#'+onId).parent().parent().attr("class").substr(0,9) == 'nav nav-s' )  {
								$('#'+onId).parent().parent().parent().attr("class","active") ;
							}
							else if ( $('#'+onId).parent().parent().attr("class").substr(0,9) == 'nav nav-t' )   {
								$('#'+onId).parent().parent().parent().attr("class","active") ;
								$('#'+onId).parent().parent().parent().parent().parent().attr("class","active")  ;
							}
							else if ( $('#'+onId).parent().parent().attr("class") == 'nav' )  {
								$('#'+onId).parent().attr("class","active")  ;
							}	
						}
						
						//20160303 IE9 이하 브라우저에서 메뉴를 2번 클릭해야 열리는 현상 수정
						$('#side-menu').find('li').children('ul').collapse('toggle');
						
						$('#side-menu').find('li').find('.active').children('ul.in').collapse('hide');
						
						//-->
					</script>
					
	               </ul>
	               <!-- //snb side-menu -->
	           </div> <!-- /.sidebar-collapse -->
	          
	    </div><!-- //snb -->
	    

	       