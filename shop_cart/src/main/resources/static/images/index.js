//幻灯片广告控制	
	$(function(){
		$("#KinSlideshow").KinSlideshow({
			moveStyle:"left",
			titleBar:{titleBar_height:30,titleBar_bgColor:"#FFF",titleBar_alpha:0.5},
			titleFont:{TitleFont_size:12,TitleFont_color:"#FFFFFF",TitleFont_weight:"normal"},
			btn:{btn_bgColor:"#FFFFFF",btn_bgHoverColor:"#1072aa",btn_fontColor:"#000000",btn_fontHoverColor:"#FFFFFF",btn_borderColor:"#cccccc",btn_borderHoverColor:"#1188c0",btn_borderWidth:1}
		});
	})

//精选推荐左右按钮控制显示
	$(function(){
         
	     var $obj = $('#slideshow ul');
		 var len  = $obj.length;
		 var i = 0;
		 $("#next").click(function(){
		      i++;
			  if(i==len){
			    i = 0;
			  }
			  $obj.stop(true,true).hide().eq(i).fadeIn(2000);
			  return false;
		 });	
		 $("#previous").click(function(){
		      i--;
			  if(i==-1){
			    i = len-1;
			  }
             $obj.stop(true,true).hide().eq(i).fadeIn(2000);
			  return false;
		 });
         
		 //滑入div 停止动画，滑出开始动画.
         $('#slideshow').hover(function(){
		      if(MyTime){
			     clearInterval(MyTime);
			  }
		 },function(){
              MyTime = setInterval(function(){
				 $("#next").trigger("click");
			  } , 5000);
		 })

		 //每2秒，自动切换。触发".next"的click事件.
		 var MyTime = setInterval(function(){
		    $("#next").trigger("click");
		 } , 5000);
	})





	// 页面返回顶部控制
		$(function() {
	    var $backToTopTxt = "返回顶部", $backToTopEle = $('<div class="backToTop"></div>').appendTo($("body"))
	        .text($backToTopTxt).attr("title", $backToTopTxt).click(function() {
	            $("html, body").animate({ scrollTop: 0 }, 120);
	    }), $backToTopFun = function() {
	        var st = $(document).scrollTop(), winh = $(window).height();
	        (st > 0)? $backToTopEle.show(): $backToTopEle.hide();
	        //IE6下的定位
	        if (!window.XMLHttpRequest) {
	            $backToTopEle.css("top", st + winh - 166);
	        }
	    };
	    $(window).bind("scroll", $backToTopFun);
	    $(function() { $backToTopFun(); });
		});




		$(function(){
		var i = 0;
		$('#navbar').children('ul').children('li').mouseover(function(){
			if($(this).children('a')[0].className == 'link'){
				i = 1;
			}
			$(this).children('a').removeClass();
			$(this).children('a').addClass('link');
		}).mouseout(function(){
			if(i != 1){
				$(this).children('a').removeClass();
				$(this).children('a').addClass('hover');
			}
			i = 0;
		});
		//search
		$("#details").children('ul').children('li').click(function(){
			$(this).parent().children('li').removeClass("current");
			$(this).addClass("current");
			$('#search_act').attr("value",$(this).attr("act"));
		});
		var search_act = $("#details").find("li[class='current']").attr("act");
		$('#search_act').attr("value",search_act);
		$("#keyword").blur();
	});

//首页Tab标签卡滑门切换
$(function() {
$(".tabs-nav > li > a").mouseover(function(e) {
	if (e.target == this) {
		var tabs = $(this).parent().parent().children("li");
		var panels = $(this).parent().parent().parent().children(".tabs-panel");
		var index = $.inArray(this, $(this).parent().parent().find("a"));
	if (panels.eq(index)[0]) {
		tabs.removeClass("tabs-selected")
			.eq(index).addClass("tabs-selected");
		panels.addClass("tabs-hide")
			.eq(index).removeClass("tabs-hide");
		}
	}
}); 
});


// 首页图片延时加载
	$(function() {
		$(".goodslist-content img").lazyload({
			placeholder : "./templates/default/images/loading.gif", 
			event : "sporty" 
		});
		$(".goodslist-content img").lazyload({
			placeholder : "./templates/default/images/loading.gif",
			event : "scroll"
		});
	});
	$(window).bind("load", function() { 
		var timeout = setTimeout(function() {$("img").trigger("sporty")}, 2000);
	});


