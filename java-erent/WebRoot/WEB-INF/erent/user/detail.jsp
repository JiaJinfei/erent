<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>订单</title>
<script type="application/x-javascript"> 
		addEventListener("load", function() {
			 setTimeout(hideURLbar, 0); 
			}, false);
		function hideURLbar(){
		 	window.scrollTo(0,1); 
		} 
</script>
<!-- //for-mobile-apps -->
<link href="${pageContext.request.contextPath}/user/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/user/css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script src="${pageContext.request.contextPath}/user/js/jquery-3.1.1.min.js"></script>
<!-- //js -->
<!-- animation-effect -->
<link href="${pageContext.request.contextPath}/user/css/animate.min.css" rel="stylesheet"> 
<script src="${pageContext.request.contextPath}/user/js/wow.min.js"></script>
<script>
 new WOW().init();
</script>
<!-- //animation-effect -->
<!-- <link href='http://fonts.useso.com/css?family=Raleway:400,100,100italic,200,200italic,300,300italic,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'> -->
<!-- start-smooth-scrolling -->
<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/move-top.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/easing.js"></script>
<script type="text/javascript">
	$(function(){
		$("#payForm").submit(function(){
			var orderAddress = $("input[name=\"orderAddr\"]");
			if(/^\s*$/.test(orderAddress.val())){
				alert("请填写工作地址！");
				return false;
			}
		});
		
		
	
	});
</script>
<!-- start-smooth-scrolling -->
<style type="text/css">

</style>
</head>
<body>
<div style="height:30px;margin: 10px 0 0 20px;">
		<a href="${pageContext.request.contextPath}/user/IndexServlet"><span style="color:#888;font-size: 15px;">返回首页</span></a>
		<span style="color:#888;font-size: 15px;"> | </span>
		<a href="${pageContext.request.contextPath}/user/MachineTypeServlet"><span style="color:#888;font-size: 15px;">返回类型展示</span></a>
	</div>

<!-- mail -->
	<div class="mail" id="mail">
		<div class="container">
			<h3 class="animated wow zoomIn" data-wow-duration="1000ms" data-wow-delay="500ms">机械预订</h3>
			<p class="dolor animated wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="800ms">Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.</p>
			<div class="mail-grids">
				<div class="col-md-4 mail-grid animated wow slideInLeft" data-wow-duration="1000ms" data-wow-delay="500ms">
					<div class="mail-grid1" style="width: 814px; margin:0 auto;position: relative; left:163px;">
						<!-- <i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i> -->
						
						<p style="width:30%;float:left">机械名称：${mdetail.machineName}</p>
						<p style="width:30%;float:right">用户姓名：${user_session.userName}</p>
						<p style="clear: both;"></p>
						<c:if test="${mdetail.machineNum > 0}">
							<p style="width:30%;float:left">剩余数量：${mdetail.machineNum}</p>
						</c:if>
						<c:if test="${mdetail.machineNum < 1}">
							<p style="width:30%;float:left">剩余数量：<font color="red">余量不足</font></p>
						</c:if>
						
						<p style="width:30%;float:right">用户手机号：${user_session.userPhone}</p>
						<p style="clear: both;"></p>
						<p style="width:30%;float:left">机械价格：${mdetail.machinePrice}</p>
						<p style="width:30%;float:right">用户账号：${user_session.userUsername}</p>
						<p style="clear: both;"></p>
						
						<form id="payForm" action="${pageContext.request.contextPath}/user/MachineDetailServlet?m=order&machineId=${mdetail.machineId}" method="post">
							<div style="color: black;" class="mail-grids1-sub1-grid">
							<input name="orderAddr" type="text" placeholder="请填写工作地址" style="width:400px;margin-top:10px;" maxlength="20">
							</div>
							
							<div style="position: relative; float:right; right:100px;" class="mail-grids1-sub1-grid">
							<span style="font-size:14px;color:#999999;">请选择预订数量：</span>
								
								<select style="width:100px;height:35px;" name="machineNum">
									<c:forEach step="1" begin="1" end="${mdetail.machineNum}" varStatus="vs">
									<option value="${vs.index}">${vs.index}</option>
									</c:forEach>
								</select>
							</div>
							<p style="clear: both;"></p>
							<div class="mail-grids1-sub1-grid mail-grids1-sub1-grid-textarea animated wow slideInUp" data-wow-duration="1000ms" data-wow-delay="500ms">
								<c:if test="${mdetail.machineNum < 1}">
									<input type="submit" value="立即预订" disabled="disabled">
									<br/>
									<a style="float:right;position:relative;right:130px;" href="${pageContext.request.contextPath}/user/MachineDetailServlet?m=back">返回首页</a>
								</c:if>
								
								<c:if test="${mdetail.machineNum > 0}">
									<input type="submit" value="立即预订" >
								</c:if>
							</div>
						</form>
					</div>
				</div>
				
				<div class="clearfix"> </div>
				
			</div>
			</div>
			
		</div>
	</div>
<!-- //mail -->

</body>
</html>
