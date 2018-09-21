<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    Hello,${key}!!!!
    <hr/>
    对象：
    <#--${goods.id}-->
    <#--${goods.title}-->
    <#--${goods.gimage}-->
    <hr/>
    if的用法：
    <#if age <= 18>
        未成年
        <#elseif (age > 18 && age <=40) >
        成年
        <#elseif (age > 40 && age <= 60) >
        中年
        <#else>
        老年
    </#if>

    <hr>
    循环的用法：<br/>
    <#list goods as good>
        ${good.id} - ${good.title} - ${good.ginfo}<br/>
    </#list>

    <hr/>
    时间：
    ${now?date}
    ${now?string("yyyy")}

    <hr/>
    数值类型
    ${money?string("$#,###.##")}
    <hr/>

    控制字符 - 为空返回false， 不为空返回true
    <#if obj??>
        对象不为空
        <#else>
        对象为空
    </#if>
    <hr/>
    ${obj!'默认值'}

</body>
</html>