//选择器
function $a(id,tag){var re=(id&&typeof id!="string")?id:document.getElementById(id);if(!tag){return re;}else{return re.getElementsByTagName(tag);}}

//焦点滚动图 点击移动
function movec()
{
	var o=$a("xifan_bd1lfimg","");
	var oli=$a("xifan_bd1lfimg","dl");
    var oliw=oli[0].offsetWidth; //每次移动的宽度	 
	var ow=o.offsetWidth-2;
	var dnow=0; //当前位置	
	var olf=oliw-(ow-oliw+10)/2;
		o["scrollLeft"]=olf+(dnow*oliw);
	var rqbd=$a("xifan_bd1lfsj","ul")[0];
	var extime;
	for(var i=1;i<oli.length;i++){rqbd.innerHTML+="<li>"+i+"</li>";}
	var rq=$a("xifan_bd1lfsj","li");
	for(var i=0;i<rq.length;i++){reg(i);};
	oli[dnow].className=rq[dnow].className="show";
	var wwww=setInterval(uu,2000);

	function reg(i){rq[i].onclick=function(){oli[dnow].className=rq[dnow].className="";dnow=i;oli[dnow].className=rq[dnow].className="show";mv();}}
	function mv(){clearInterval(extime);clearInterval(wwww);extime=setInterval(bc,15);wwww=setInterval(uu,4000);}
	function bc()
	{
		var ns=((dnow*oliw+olf)-o["scrollLeft"]);
		var v=ns>0?Math.ceil(ns/10):Math.floor(ns/10);
		o["scrollLeft"]+=v;if(v==0){clearInterval(extime);oli[dnow].className=rq[dnow].className="show";v=null;}
	}
	function uu()
	{
		if(dnow<oli.length-2)
		{
			oli[dnow].className=rq[dnow].className="";
			dnow++;
			oli[dnow].className=rq[dnow].className="show";
		}
		else{oli[dnow].className=rq[dnow].className="";dnow=0;oli[dnow].className=rq[dnow].className="show";}
		mv();
	}
	o.onmouseover=function(){clearInterval(extime);clearInterval(wwww);}
	o.onmouseout=function(){extime=setInterval(bc,15);wwww=setInterval(uu,4000);}
}