<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>부록등록</title>
<style type="text/css">
#container { float:left; position:relative; margin-top:20px; padding:0 15px;}

#table_qam { width:650px; border-top:2px solid #2173ce; border-bottom:1px solid #2173ce;}
#table_qam tbody th { font:bold 12px "굴림", gulim; color:#2173ce; padding:5px 0 5px 5px; background:#d8e3f1; border-right:1px solid #ccc; border-bottom:1px solid #ccc; width:110px; line-height:1.7em; }
#table_qam tbody td { font:12px "굴림", gulim; color:#666; padding:10px 0 5px 10px; border-bottom:1px solid #ccc; width:540px;}
#table_qam input, #table_qam textarea { font:12px "굴림", gulim; color:#444; line-height:1.7em;}
#table_qam input { height:18px; border:1px solid #ccc;}

#fileList { width:540px; margin:0 0 0 0; padding:0; }
#fileList li { width:540px; margin:5px 0px 0px 0px; display:inline-block; }
#fileList li img { width:15px; height:15px; vertical-align:-3px; }
#fileList li .btn { width:125px; text-align:center; margin-top:7px; display:inline-block; }

.tableBTN { width:500px; margin-top:20px; margin-left:100px;}

a:link    {color:black; text-decoration:none}
a:active  {color:black; text-decoration:none}
a:visited {color:black; text-decoration:none}
a:hover   {color:blue;  text-decoration:underline}
</style>
</head>
<body>
<div id="container">
	<form name="afrm" method="post" enctype="multipart/form-data" action="/mdm/MdmMinutesAppForm.do" onsubmit="return check(this);">
	<table id="table_qam" border="0" cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<th>회 의 명</th>
				<td><b></b></td>
			</tr>
			<tr>
				<th>부록파일명</th>
				<td>
					<input type="file" name="mFile" id="mFile" style="width:450px; margin-bottom:7px;" />
					<input type="submit" value="등 록" style="vertical-align:top" />
				</td>
			</tr>
			<tr>
				<th>등록된 부록<br /></th>
				<td>
					<ul id="fileList">
	
						<li>
	
							<%-- <img src="/minutes/img/ext/<%=sch.getExt(vo.getMext())%>.gif" /> --%>
							<span id="msg">부록 제목</span>
							
						</li>
	
					</ul>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</div>
</body>
</html>