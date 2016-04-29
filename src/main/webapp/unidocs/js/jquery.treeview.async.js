/*
 * Async Treeview 0.1 - Lazy-loading extension for Treeview
 * 
 * http://bassistance.de/jquery-plugins/jquery-plugin-treeview/
 *
 * Copyright (c) 2007 Jorn Zaefferer
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 * Revision: $Id$
 *
 */
/* 제이쿼리트리 */
;(function($) {
	function load(settings, root, child, container) {
		function createNode(parent) {
			var current = "";
			var str = "";
			var appid = "";
			if (this.var08 != undefined) {
				_var08 = this.var08;
				_var09 = this.var09;
			}
			if (this.var10 != undefined) {
				_var10 = this.var10;
			}
			if (this.children || this.hasChildren) {
				if(this.var11 == "appList" ) {
					appid = " id='app"+this.id+"'";
				}
				if(this.var09 == "T") {
					this.text = "[임시회의록]"+this.text;
				}
				current = $("<li/>").attr("id", this.id || "").html("<span" + appid + " class='folder' onclick=\"setvar('"+this.var01+"','"+this.var02+"','"+this.var03+"','"+this.var04+"','"+this.var05+"','"+this.var06+"','"+this.var07+"','"+this.var08+"','"+this.var09+"','"+this.var10+"','"+this.var11+"','"+this.var12+"')\">" + this.text +"</span>").appendTo(parent);
			}
			else{
				if (this.id == "resultEmpty") {
					current=$("<li/>").attr("id", this.id || "").html("<span class='file'>" + this.text + "</span>").appendTo(parent);
				} 
				else if (window.HBGateway) {
					//str="<span class='file'><a class='mlnk' href='/minutes/"+this.var11+".php?viewmode=y&uid="+this.var12+"&mmcode="+this.var10+"&keyword="+this.var07+"' target='_blank'>"+this.text+"</a></span>";
					str+="<span class='file'><a href='/minutes/xcom/minutesViewer.jsp?id="+this.var12+"#an"+this.var11+"' title='회의록뷰어 새창으로 이동' target='_blank'>";
					str+=this.text+"</a></span>";
					current=$("<li/>").attr("id", this.id || "").html(str).appendTo(parent);
				} 
				else{
					if (this.var09 == "sch") {
						str="<span class='file'><a href='#' onclick=\"getDocOpen('"+this.var12+"','"+this.var07+"');\">"+this.text+"</a></span>";
						current=$("<li/>").attr("id", this.id || "").html(str).appendTo(parent);
					}
					else {
						var str = "";
						if( this.var09 == "Adm" ) {
							str="<span class='file'>";
							str+="["+this.var11+"] ";
							if( this.var10 == "Y" ) {
								str+="[<a href='#' onclick=\"getOpenProc('"+this.var12+"','"+this.id+"');return false;\"><span id='open"+this.id+"'><font color='blue'>공개</font></span></a>] ";
							}
							else {
								str+="[<a href='#' onclick=\"getOpenProc('"+this.var12+"','"+this.id+"');return false;\"><span id='open"+this.id+"'><font color='red'>임시</font></span></a>] ";
							}
							str+="[<a href='#' onclick=\"getModify('"+this.id+"','"+this.var01+"','"+this.var12+"');return false;\"><font color='blue'>수정</font></a>] ";
							str+="[<a href='#' onclick=\"getDelete('"+this.var01+"','"+this.var02+"','"+this.var03+"','"+this.var08+"','"+this.var12+"','"+this.text+"');return false;\"><font color='red'>삭제</font></a>] ";
							str+="<a href='#' onclick=\"getDocOpen('"+this.var12+"','"+this.var07+"');return false;\">";
							str+="<span id='doc"+this.id+"'>"+this.text+"</span></a></span>";
						}
						else if( this.var09 == "AdmApp" ) {
							if( this.var11 == "" ) {
								str="<span class='file' id='"+this.id+"'>";
							}
							else {
								str="<span>";
							}
							str+="<a href='#' onclick=\"getAppendixDownLoad1('"+this.id+"','"+this.var01+"','"+this.var11+"');\">"+this.text+"</a></span>";
						}
						else if( this.var09 == "AdmHwp" ) {
							str="<span class='file' id='"+this.id+"'>["+this.var11+"] <span id='hwpreg"+this.var12+"'>";
							if( this.var10 == "" ) {
								str+="[<a href='#' onclick=\"getHwpRegForm('"+this.id+"','"+this.var01+"','"+this.var12+"');return false;\"><font color='blue'>원문등록</font></a>] ";
							}
							else {
								str+="[<a href='#' onclick=\"getHwpDownLoad1('"+this.var01+"','"+this.var12+"');return false;\"><font color='blue'>원문보기</font></a>] ";
								str+="[<a href='#' onclick=\"getHwpDelete('"+this.id+"','"+this.var01+"','"+this.var12+"');return false;\"><font color='red'>원문삭제</font></a>] ";
							}
							str+="</span>";
							str+="<a href='#' onclick=\"getDocOpen('"+this.var12+"','"+this.var07+"');\">";
							str+="<span id='hwpmsg"+this.var12+"'>"+this.text+"</span></a></span>";
						}
						else {
							str+="<span class='file'><a href='/minutes/xcom/minutesViewer.jsp?id="+this.var12+"#an"+this.var11+"' ";
							str+="onclick=\"window.open(this.href, 'minutesviewer', 'scrollbars=no,toolbar=no,resizable=yes,status=yes,menubar=no,width=1024,height=768'); ";
							str+="return false;\" title='회의록뷰어 새창으로 이동' target='_blank'>";
							str+=this.text+"</a></span>";
						}
						current = $("<li/>").attr("id", this.id || "").html(str).appendTo(parent);
					}
				}
			}
			if (this.classes) {
				current.children("span").addClass(this.classes);
			}
			if (this.expanded) {
				current.addClass("open");
			}
			if (this.hasChildren || this.children && this.children.length) {
				var branch = $("<ul/>").appendTo(current);
				if (this.hasChildren) {
					current.addClass("hasChildren");
					createNode.call({
						classes: "placeholder",
						text: "&nbsp;",
						children:[]
					}, branch);
				}
				if (this.children && this.children.length) {
					$.each(this.children, createNode, [branch]);
				}
			}
		}
		/*
		ajax: {
			data: {
				"additional": function() {
					return "yeah: " + new Date;
				},
				"pr2":"qwerasfjla"
			},
			//data: "daesu="+daesu+"&conf="+conf+"&depth="+depth,
			type: "post"
		}
		*/

		$.ajax($.extend(true, {
			url: settings.url,
			dataType: "json",
			data: {
				"var01":_var01,
				"var02":_var02,
				"var03":_var03,
				"var04":_var04,
				"var05":_var05,
				"var06":_var06,
				"var07":_var07,
				"var08":_var08,
				"var09":_var09,
				"var10":_var10,
				"var11":_var11,
				"var12":_var12
			},
			type: "get",
			success: function(response) {
				child.empty();
				$.each(response, createNode, [child]);
				$(container).treeview({add: child});
			}
		}, settings.ajax));
		/*
		$.getJSON(settings.url, {root: root}, function(response) {
			function createNode(parent) {
				var current = $("<li/>").attr("id", this.id || "").html("<span>" + this.text + "</span>").appendTo(parent);
				if (this.classes) {
					current.children("span").addClass(this.classes);
				}
				if (this.expanded) {
					current.addClass("open");
				}
				if (this.hasChildren || this.children && this.children.length) {
					var branch = $("<ul/>").appendTo(current);
					if (this.hasChildren) {
						current.addClass("hasChildren");
						createNode.call({
							classes: "placeholder",
							text: "&nbsp;",
							children:[]
						}, branch);
					}
					if (this.children && this.children.length) {
						$.each(this.children, createNode, [branch])
					}
				}
			}
			child.empty();
			$.each(response, createNode, [child]);
			$(container).treeview({add: child});
		});
		*/
	}

	var proxied = $.fn.treeview;
	$.fn.treeview = function(settings) {
		if (!settings.url) {
			return proxied.apply(this, arguments);
		}
		var container = this;
		if (!container.children().size()) {
			load(settings, "source", this, container);
		}
		var userToggle = settings.toggle;
		return proxied.call(this, $.extend({}, settings, {
			collapsed: true,
			toggle: function() {
				var $this = $(this);
				if ($this.hasClass("hasChildren")) {
					var childList = $this.removeClass("hasChildren").find("ul");
					load(settings, this.id, childList, container);
				}
				if (userToggle) {
					userToggle.apply(this, arguments);
				}
			}
		}));
	};
})(jQuery);