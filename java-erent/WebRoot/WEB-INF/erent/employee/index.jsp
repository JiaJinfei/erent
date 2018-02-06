<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/admin.css">
    <script src="${pageContext.request.contextPath}/employee/js/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
  	<h1 style="float:left;">农车快租后台管理  &nbsp;&nbsp;&nbsp;&nbsp;</h1>
    <h1 style="float:left;"><img src="${pageContext.request.contextPath}/employee/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />当前用户：${employee_user.userName}</h1>
  </div>
  <div class="head-l">
  	<a class="button button-little bg-red" href="${pageContext.request.contextPath}/employee/LoginServlet?m=out"><span class="icon-power-off"></span> 退出登录</a>
  </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  
  
  <h2><span class="icon-employee"></span>员工管理</h2>
  <ul style="display:block">
    <li><a href="${pageContext.request.contextPath}/employee/UserServlet?m=list" target="right"><span class="icon-caret-right"></span>员工列表</a></li>     
  </ul> 
  
  <h2><span class="icon-machine"></span>商品管理</h2>
  <ul style="display:block">
    <li><a href="${pageContext.request.contextPath}/employee/TypeServlet?m=list" target="right"><span class="icon-caret-right"></span>机械类型</a></li>     
    <li><a href="${pageContext.request.contextPath}/employee/MachineServlet?m=list" target="right"><span class="icon-caret-right"></span>机械管理</a></li>     
    <li><a href="${pageContext.request.contextPath}/employee/OrderServlet?m=list" target="right"><span class="icon-caret-right"></span>订单管理</a></li>     
  </ul>
  
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  
  //sdfsdfsdfsf
  /* sdfsdfsdf*/
  <%-- sdfsdfsdfsdf--%>
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<div class="admin" style="overflow:hidden;">
  <iframe scrolling="auto" rameborder="0" src="${pageContext.request.contextPath}/employee/images/1.jpg" name="right" width="100%" height="100%"></iframe>
</div>
</body>
</html>