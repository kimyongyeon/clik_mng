<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %> 
<link rel="StyleSheet" href="/js/clikmng/menu/dtree.css" type="text/css" />
<script type="text/javascript" src="/js/clikmng/menu/dtree.js"></script>

<!-- Core CSS - Include with every page -->
<link href="/css/clikmng/bootstrap.min.css" rel="stylesheet">
<link href="/css/clikmng/font-awesome.css" rel="stylesheet">

<!-- Page-Level Plugin CSS - Dashboard -->
<link href="/css/clikmng/morris-0.4.3.min.css" rel="stylesheet">
<link href="/css/clikmng/timeline.css" rel="stylesheet">

<!-- SB Admin CSS - Include with every page -->
<link href="/css/clikmng/sb-admin.css" rel="stylesheet">

<script type="text/javascript" src="/js/clikmng/respond_src.js"></script>
<script type="text/javascript" src="/js/clikmng/default.js"></script>


<nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
       <div class="navbar-header" style="	">
           <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
           </button>
           <a class="navbar-brand" href="/cmm/dashBoard/DashBoard.do"><b>지방의회 지식정보 공유시스템</b></a>
       </div>
       <!-- /.navbar-header -->

	  <!-- 우측상단 메뉴 주석처리 START  -->
       <ul class="nav navbar-top-links navbar-right">
           <!-- /.dropdown -->
           <li class="divider">
               <a href="/uat/uia/actionLogout.do"><i class="fa fa-sign-out fa-fw"></i></a>
               <!-- /.dropdown-user -->
           </li>
           <!-- /.dropdown -->
       </ul>
       <!-- /.navbar-top-links -->
 
		
	   </nav>
	   
	   <div class="navbar-default navbar-static-side" role="navigation" style=" position:absolute; left:0; top:0;">
	            <div class="sidebar-collapse">  
	                <ul class="nav" id="side-menu"> 

					<script type="text/javascript">
						<!--
						var currentUrl = '<%= request.getAttribute("javax.servlet.forward.request_uri") %>';
						
						d = new dTree('d');
						<c:forEach var="x" items="${menuList}" varStatus="status" >
							d.add(<c:out value='${x.menuNo}' />,<c:out value='${x.upperMenuNo}' />,'<c:out value='${x.menuNm}' />','<c:out value='${x.progrmFileNm}' />', currentUrl, '<c:out value='${x.menuLevel}' />');
		        		</c:forEach>						
						
						document.write(d);
				
						//-->
					</script>

	               </ul>
	               <!-- /#side-menu -->
	           </div>
	           <!-- /.sidebar-collapse -->
	       </div>
	       <!-- /.navbar-static-side -->