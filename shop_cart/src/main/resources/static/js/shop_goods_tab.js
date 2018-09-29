/*
EASY TABS 1.2 Produced and Copyright by Koller Juergen
www.kollermedia.at | www.austria-media.at
Need Help? http:/www.kollermedia.at/archive/2007/07/10/easy-tabs-12-now-with-autochange
You can use this Script for private and commercial Projects, but just leave the two credit lines, thank you.
*/

// Tabs 如（tabid1,tabid2...）格式。填写tabid
var tablink_idname = new Array("xiangqing_tab_")
// TabContents 如（tabconid1,tabconid2...）格式。填写tabconid
var tabcontent_idname = new Array("xiangqing_content_") 
// 一共要切换几个
var tabcount = new Array("3")
// 第几个默认显示出来
var loadtabs = new Array("1")  
// 0关闭自动切换，1开始自动切换
var autochangemenu = 0;
// 自动切换时间
var IntervalTime = 1000;
//the speed in seconds when the tabs should change
var changespeed = 2;
//should the autochange stop if the user hover over a tab from the autochangemenu? 0=no 1=yes
var stoponhover = 0;
//END MENU SETTINGS

//This Hover Add Class Name
var hoverClassName = 'hover';

/*Swich EasyTabs Functions - no need to edit something here*/
function shop_goods_easytabs(menunr, active) {if (menunr == autochangemenu){currenttab=active;}if ((menunr == autochangemenu)&&(stoponhover==1)) {stop_autochange()} else if ((menunr == autochangemenu)&&(stoponhover==0))  {counter=0;}menunr = menunr-1;for (i=1; i <= tabcount[menunr]; i++){document.getElementById(tablink_idname[menunr]+i).className='tab'+i;document.getElementById(tabcontent_idname[menunr]+i).style.display = 'none';}document.getElementById(tablink_idname[menunr]+active).className='tab'+active+' '+hoverClassName;document.getElementById(tabcontent_idname[menunr]+active).style.display = 'block';}var timer; counter=0; var totaltabs=tabcount[autochangemenu-1];var currenttab=loadtabs[autochangemenu-1];function start_autochange(){counter=counter+1;timer=setTimeout("start_autochange()",IntervalTime);if (counter == changespeed+1) {currenttab++;if (currenttab>totaltabs) {currenttab=1}shop_goods_easytabs(autochangemenu,currenttab);restart_autochange();}}function restart_autochange(){clearTimeout(timer);counter=0;start_autochange();}function stop_autochange(){clearTimeout(timer);counter=0;}

window.onload=function(){
var menucount=loadtabs.length; var a = 0; var b = 1; do {shop_goods_easytabs(b, loadtabs[a]);  a++; b++;}while (b<=menucount);
if (autochangemenu!=0){start_autochange();}
}