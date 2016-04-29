<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<style>
#btn_council {background:#AAA; padding:5px 0; margin-left:10px; width:110px; color:#ffffff; border:1px solid #AAA;}
/*layerpopup--------------------------------*/
.layerpopup{background:url(/images/clikmng/bg_layer.png) left top repeat; width:100%; height:100%; min-height:904px;position:fixed;top:0;left:0;z-index:999999999999999999999;}
.layerpopup .layerWrap{ width:1000px; }
.layerpopup .layerWrap .close{position:absolute;top:0;right:0;}

/*layer_council*/
#layer_council{display:none;}
#layer_council .layerWrap{ background:#ffffff;position:relative;margin:80px auto;padding:60px; text-align:center;}

.table-council table {border:1px solid #cccccc; border-radius:5px; font-size:0.9em;}
.table-council th, td {border:1px solid #cccccc;}
.table-council thead th {background:#efefef;}
.table-council tbody th {background:#efefef; font-size:0.9em;}
.table-council tbody span {font-size:0.8em;}
.table-council tbody .last td {color:red;}
}
</style>

<script type="text/javascript">
 $(document).ready(function() {
	 
	$("#btn_council").click(function(){
		$("#layer_council").show();		
	});
	$(".layerpopup .close").click(function(){
		$("#layer_council").hide();
	});	
	
});
</script>

	<div class="layerpopup mainfamilysite" id="layer_council">
		<div class="layerWrap ">
			
			<div class="table-council">
				<table width="100%" class="table table-bordered table-hover" summary="의회별 기수 정보">
					<caption>의회별 기수 정보</caption>
					<thead>
						<tr>
							<th width="72">구분</th>
							<th width="72">서울</th>
							<th width="72">부산</th>
							<th width="72">대구</th>
							<th width="72">인천</th>
							<th width="72">광주</th>
							<th width="72">대전</th>
							<th width="72">울산</th>
							<th width="72">세종</th>
							<th width="72">경기</th>
							<th width="72">강원</th>
							<th width="72">충북</th>
							<th width="72">충남</th>
							<th width="72">전북</th>
							<th width="72">전남</th>
							<th width="72">경북</th>
							<th width="72">경남</th>
							<th width="72">제주</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th>1952.05.20~<br>1956.06.30</th>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>1대</td>
							<td>1대</td>
							<td>1대</td>
							<td>1대</td>
							<td>1대</td>
							<td>1대</td>
							<td>1대</td>
						</tr>
						<tr>
							<th>1956.09.05~<br>1960.08.12</th>
							<td>1대</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>1대</td>
							<td>1대<br><span>(1956.09.05~<br>1960.12.20)</span></td>
							<td>2대</td>
							<td>2대</td>
							<td>2대</td>
							<td>2대</td>
							<td>2대</td>
							<td>2대</td>
							<td>2대</td>
						</tr>


						<tr>
							<th>1960.12.22~<br>1961.05.16</th>
							<td>2대</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>2대</td>
							<td>2대<br><span>(1960.12.21~<br>1961.05.16)</span></td>
							<td>3대</td>
							<td>3대</td>
							<td>3대</td>
							<td>3대</td>
							<td>3대</td>
							<td>3대</td>
							<td>3대</td>
						</tr>
						<tr>
							<th>1991.07.01~<br>1995.06.30</th>
							<td>3대</td>
							<td>1대</td>
							<td>1대</td>
							<td>1대</td>
							<td>1대</td>
							<td>1대</td>
							<td></td>
							<td></td>
							<td>3대</td>
							<td>3대</td>
							<td>4대</td>
							<td>4대</td>
							<td>4대</td>
							<td>4대</td>
							<td>4대</td>
							<td>4대</td>
							<td>4대</td>
						</tr>
						<tr>
							<th>1995.07.01~<br>1998.06.30</th>
							<td>4대</td>
							<td>2대</td>
							<td>2대</td>
							<td>2대</td>
							<td>2대</td>
							<td>2대</td>
							<td>1대</td>
							<td></td>
							<td>4대</td>
							<td>4대</td>
							<td>5대</td>
							<td>5대</td>
							<td>5대</td>
							<td>5대</td>
							<td>5대</td>
							<td>5대</td>
							<td>5대</td>
						</tr>
						<tr>
							<th>1998.07.01~<br>2002.06.30</th>
							<td>5대</td>
							<td>3대</td>
							<td>3대</td>
							<td>3대</td>
							<td>3대</td>
							<td>3대</td>
							<td>2대</td>
							<td></td>
							<td>5대</td>
							<td>5대</td>
							<td>6대</td>
							<td>6대</td>
							<td>6대</td>
							<td>6대</td>
							<td>6대</td>
							<td>6대</td>
							<td>6대</td>
						</tr>
						<tr>
							<th>2002.07.01~<br>2006.06.30</th>
							<td>6대</td>
							<td>4대</td>
							<td>4대</td>
							<td>4대</td>
							<td>4대</td>
							<td>4대</td>
							<td>3대</td>
							<td></td>
							<td>6대</td>
							<td>6대</td>
							<td>7대</td>
							<td>7대</td>
							<td>7대</td>
							<td>7대</td>
							<td>7대</td>
							<td>7대</td>
							<td>7대</td>
						</tr>
						<tr>
							<th>2006.07.01~<br>2010.06.30</th>
							<td>7대</td>
							<td>5대</td>
							<td>5대</td>
							<td>5대</td>
							<td>5대</td>
							<td>5대</td>
							<td>4대</td>
							<td></td>
							<td>7대</td>
							<td>7대</td>
							<td>8대</td>
							<td>8대</td>
							<td>8대</td>
							<td>8대</td>
							<td>8대</td>
							<td>8대</td>
							<td>8대</td>
						</tr>
						<tr>
							<th>2010.07.01~<br>2014.06.30</th>
							<td>8대</td>
							<td>6대</td>
							<td>6대</td>
							<td>6대</td>
							<td>6대</td>
							<td>6대</td>
							<td>5대</td>
							<td>1대<br><span>(2012.07.01~<br>2014.06.30)</span></td>
							<td>8대</td>
							<td>8대</td>
							<td>9대</td>
							<td>9대</td>
							<td>9대</td>
							<td>9대</td>
							<td>9대</td>
							<td>9대</td>
							<td>9대</td>
						</tr>
						<tr class="last">
							<th>2014.07.01~<br>2018.06.30</th>
							<td>9대</td>
							<td>7대</td>
							<td>7대</td>
							<td>7대</td>
							<td>7대</td>
							<td>7대</td>
							<td>6대</td>
							<td>2대</td>
							<td>9대</td>
							<td>9대</td>
							<td>10대</td>
							<td>10대</td>
							<td>10대</td>
							<td>10대</td>
							<td>10대</td>
							<td>10대</td>
							<td>10대</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="close"><a href="#none" onclick="return false;"><img src="/images/clikmng/layerClosebtn1.gif" alt="닫기" /></a></div>
		</div>
	</div>
	
