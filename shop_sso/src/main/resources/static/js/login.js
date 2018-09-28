//ajax进行登录认证 -
$(function(){
    $.ajax({
        url:"http://localhost:8084/sso/islogin",
        dataType:"jsonp"
    });
})

function callback(data){
    if(data != null){
        $("#pid").html(data.name + "您好，欢迎来到<b>ShopCZ商城</b>&nbsp;&nbsp; <a href=\"http://localhost:8084/sso/logout\">注销</a>");
    } else {
        $("#pid").html("[<a href=\"javascript:login();\">登录</a>][<a href=\"\">注册</a>]");
    }
}

function login(){
    var returnUrl = location.href;
    returnUrl = encodeURI(returnUrl);
    returnUrl = returnUrl.replace("&", "*");

    //跳转到登录页面
    location.href="http://localhost:8084/sso/tologin?returnUrl=" + returnUrl;
}