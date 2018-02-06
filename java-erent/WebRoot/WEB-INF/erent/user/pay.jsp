<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>支付</title>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/user/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/user/css/admin.css">
<script src="${pageContext.request.contextPath}/user/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/move-top.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/easing.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/pintuer.js"></script>
<script type="text/javascript">

	$(function(){
		$("input[name=payerNo]").val("");
		$("input[name=payerPwd]").val("");
	
		$("#payForm").submit(function(){
			var payerNo = $("input[name=payerNo]");
			if(/^\s*$/.test(payerNo.val())){
				$("#payerNoValid").html("支付账号不能为空");
				payerNo.focus();
				return false;
			}
			
			$("#payerNoValid").html("");
			var payerPwd = $("input[name=payerPwd]");
			if(/^\s*$/.test(payerPwd.val())){
				$("#payerPwdValid").html("支付密码不能为空");
				payerPwd.focus();
				return false;		
			}
			$("#payerPwdValid").html("");
		});
		
	});
	
</script>
<!-- start-smooth-scrolling -->

</head>
<body>


<!-- mail -->
	<div class="mail" id="mail">
		<div class="container">
			<h3 class="animated wow zoomIn" data-wow-duration="1000ms" data-wow-delay="500ms">支付宝</h3>
			<p class="dolor animated wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="800ms"></p>
			<div class="mail-grids">
				<div class="col-md-4 mail-grid animated wow slideInLeft" data-wow-duration="1000ms" data-wow-delay="500ms">
					<div class="mail-grid1" style="width: 814px; margin:0 auto;position: relative; left:163px;">
						<!-- <i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i> -->
						
						<form id="payForm" action="${pageContext.request.contextPath}/user/UserOrderServlet?m=paySuccess" method="post">
							<div style="color: black;" class="mail-grids1-sub1-grid">
							<input name="payerNo" type="text" data-validate="required:请输入支付宝账号" maxlength="16" placeholder="请输入支付宝账号" style="width:480px;margin-top:10px;">
							<ul></ul><li id="payerNoValid" style="color: red;"></li></ul>
							<input name="payerPwd" type="password" data-validate="required:请输入支付宝密码" maxlength="16" placeholder="请输入支付宝密码" style="width:480px;margin-top:10px;"/>
							<ul></ul><li id="payerPwdValid" style="color: red;"></li></ul>
							</div>
							<div style="color: black;" class="mail-grids1-sub1-grid">
							<span style="font-size:20px; float:right; position: relative; right:160px;">总金额：${totalPrcie}元</span>
							<input type="hidden" name="totalPrice" value="${totalPrcie}">
							<c:forEach items="${orderIds}" var="orderId">
							<input type="hidden" name="orderId" value="${orderId}">
							</c:forEach>
							</div>
							<p style="clear: both;"></p>
							<div class="mail-grids1-sub1-grid mail-grids1-sub1-grid-textarea animated wow slideInUp" data-wow-duration="1000ms" data-wow-delay="500ms">
								<input type="submit" value="确认付款" >
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
