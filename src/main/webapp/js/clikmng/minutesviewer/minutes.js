/**
 * 
 */

var defaultSize=16;
var TRange=null;
var backstr = null;
var old_rasmbly_id = 0;
var old_asemby_id = 0;

function menuToggle(cp){
	var backDivWidth=$("#middleDiv").width();
	var backDivHeight=$("#middleDiv").height();
	//var backDivTop=$("#middleDiv").offset().top;
	var backDivTop=$("#wideDiv").offset().top;

	//var backDivTop=0;
	//var backDivTop=-55;
	//var backDivTop=-97;
	///var backDivTop=-177;


		if($(".n13_menu").css("left")=="0px")
		{
			$("#wideDiv").animate({"left":"0px"});
			$(".n13_menu").animate({"left":"-201px"});
			
		}else{

			$(".n13_menu").animate({"left":"0px"});
			if(cp!="")toggleSubMenu(cp, "open");
		}
}

$(function() {
	$( "#menushow" )
	.accordion({
		header: "h3",
		heightStyle: "fill"
	});
	
	$( window ).load(function(){
		var title = "";
		
		if($(".minutes_title").text())
		{
			title += $(".minutes_title").text();
		}
		
		if($(".minutes_subject").text())
		{
			if(title != "")
			{
				title += " ";
			}
			title += $(".minutes_subject").text();
		}
		
		if(title == "")
		{
			title += " ";
		}
		
		$("#minutestitle_top").html(title);
		$("#minutestitle").html(title);
		self.moveTo(0,0);
		self.resizeTo(screen.availWidth,screen.availHeight - 28);
	});
	
	
	$("#btnSearch").click(function () {
		var count = 0;
		var str = document.getElementById("keyword").value;
		if(str != "")
		{	
			if(backstr != null){
				$('#minutes .highlight').html(backstr);//기존검색단어를 치환
				$('#minutes .highlight').toggleClass('highlight');//highlight 검색기능 토글링
			}
		    $('[id=minutes]:contains('+str+')').each(function(){
		       var regex = new RegExp(str, "g");
		       $(this).html( $(this).html().replace( regex ,"<span class='highlight'>"+str+"</span>"));
		       count =  $('.highlight').length;
		    });
		    if(count > 0)
		    {
			    alert('총 '+count+'개의 검색된 단어가 있습니다.');
			    //$(".highlight").focus();
			    var scroll_top = $(".highlight").offset().top - 100;
			    $(window).scrollTop(scroll_top);
		    }
		    else
		    {
			    alert('검색된 단어가 없습니다.');
		    }
		    backstr = str;
		}
		else
		{
			alert('검색어를 입력하여 주십시오');
		}
	});
	
	$(".matter_group_item").click(function(){
		var viewid = $(this).attr("mtr_id");
		var scroll_top = $("#" + viewid).offset().top - 100; 
		$(window).scrollTop(scroll_top);
	});
	
	if (!!$('#titlearea').offset()) // make sure ".sticky" element exists
	{
		var stickyTop = $('#titlearea').offset().top; // returns number 
		$(window).scroll(function(){ // scroll event
			var windowTop = $(window).scrollTop(); // returns number
			if (stickyTop < windowTop)
			{
				//$('.sticky').css({top : 0 });
				$("#titlearea").css({top : '0px'});
				$("#menuarea").css({top : '36px'});
				
			}
			else if((stickyTop - windowTop) > 0)
			{
				if(windowTop == 0)
				{
					$("#titlearea").css({top : '63px'});
					$("#menuarea").css({top : '100px'});
				}
				else
				{
					//$('.sticky').css({top : 0 });
					$("#titlearea").css({top : '0px'});
					$("#menuarea").css({top : '36px'});
				}
					
			}
			else 
			{
				//$('.sticky').css({top : '44px'});
				$("#titlearea").css({top : '63px'});
				$("#menuarea").css({top : '100px'});
				
			}
		});
	}
	
	$("#printBtn").click(function() {
		var print = $(this).attr("link");
		var win = window.open(print,"_blank",'width=800,height=900'); 
		win.print();
	});
	
	$("#orgindnBtn").click(function() {
		var orgin = $(this).attr("link");
		if(orgin != "")
		{
			window.open(orgin);
		}
		else
		{
			alert('회의록 원문 파일을 다운로드 할수 없습니다');
		}
	});
	
	//회의록 본문 발언정보
	$(".speaker_name").click(function () {
		var rasmbly_id = 0;
		var asemby_id = 0;
		rasmbly_id = $(this).attr("rasmbly_id");
		asemby_id = $(this).attr("asemby_id");
		if(rasmbly_id > 0 && asemby_id > 0)
		{
			var win = window.open("https://clik.nanet.go.kr/potal/search/searchView.do?rasmbly_id="+rasmbly_id+"&asemby_id="+asemby_id,"chairman_info","width=500,height:500,resizeable=no,scrollbars=no");
			win.focus();
		}
	});
	
	//발언자
	
	$(".leftAssem").click(function () {
		var rasmbly_id = 0;
		var asemby_id = 0;
		rasmbly_id = $(this).attr("rasmbly_id");
		asemby_id = $(this).attr("asemby_id");
		if(rasmbly_id > 0 && asemby_id > 0)
		{
			var minimum_scroll = 0;
			//걸려잇는 발언잦 이펙트를 모두 삭제
			$( ".speaking_content" ).each(function() {
				if(asemby_id != old_asemby_id)
				{
					$(this).removeClass("effectSpeaker");
				}
				
			});

			// 발언자 이펙트를 설정
			$(".speaker_name").each(function( ) {
				sub_rasmbly_id = $(this).attr("rasmbly_id");
				sub_asemby_id = $(this).attr("asemby_id");
				if(sub_asemby_id == asemby_id)
				{
					if(asemby_id != old_asemby_id)
					{
						$(this).parent(".speaking_content").addClass("effectSpeaker");
					}
					else
					{
						$(this).parent(".speaking_content").toggleClass("effectSpeaker");
					}
					scroll_top = $(this).offset().top;
					if(minimum_scroll == 0)
					{
						minimum_scroll = scroll_top;
					}
					else if(minimum_scroll > scroll_top)
					{
						minimum_scroll = scroll_top;
					}
				}
			});
			
			//최상단 발언자로 포커스 이동
			minimum_scroll = minimum_scroll - 35;
			$(window).scrollTop(minimum_scroll);
			
			if(asemby_id != old_asemby_id)
			{
				$(".leftAssem").each(function( ) {
					$(this).removeClass("effectSpeaker");
				});
				$(this).addClass("effectSpeaker");
				old_rasmbly_id = rasmbly_id;
				old_asemby_id = asemby_id;
			}
			else
			{				
				$(this).toggleClass("effectSpeaker");
			}
		}
	});
	
	$(".apdxdownlink").click(function () {
		var download = $(this).attr("href");
		if(download != "")
		{
			window.open(download);
		}
		else
		{
			alert('관련 부록을 다운로드 할수 없습니다.');
		}
	});
	
});

function ie_top()
{
	$(window).scrollTop(0);
	
	$("#menuarea").css({top : '100px'});
	$("#controlbox").css({top : '49px'});
}

function FontChange()
{
	var ff = document.getElementById("fontFamily").value;
	$("#viewer").css('font-family',ff);
}

function FontSize(val)
{
	 if (val == "+")
	 {
		 if(defaultSize==24)
		 {
			 alert('더이상 크게 할수없습니다.');
		 }
		 else
		 {
			 defaultSize=defaultSize+2;
		 }
	 }
	 else if(val == "-")
	 {
		 if(defaultSize==12)
		 {
			 alert('더이상 작게 할수없습니다.');
		 }
		 else
		 {
			 defaultSize=defaultSize-2;
		 }
	 }
	 $("#viewer").css('font-size',defaultSize + 'px');
}

function billFocus(i)
{
	var viewid = eval(i) + 1;
	var scroll_top = $("#matter_" + viewid).offset().top - 35; 
	$(window).scrollTop(scroll_top);
}



function downloadFile(val)
{
	var languageCheck = /http[:][/][/]/;
	if(!languageCheck.test(val))
	{
		document.getElementById("custom_viewpage").src = val;
	}
	else
	{
		alert("정상적인 다운로드가 아닙니다");
	}
}