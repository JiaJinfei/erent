<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<title>农车快租</title>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="${pageContext.request.contextPath}/user/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/user/css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script src="${pageContext.request.contextPath}/user/js/jquery-1.11.1.min.js"></script>
<!-- //js -->
<!-- animation-effect -->
<link href="${pageContext.request.contextPath}/user/css/animate.min.css" rel="stylesheet"> 
<script src="${pageContext.request.contextPath}/user/js/wow.min.js"></script>
<script>
 new WOW().init();
</script>

<!-- start-smooth-scrolling -->
<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/move-top.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/user/js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
<!-- start-smooth-scrolling -->
</head>
	
<body>

<!-- gallery -->
	<div style="height:30px;margin: 10px 0 0 20px;">
		<a href="${pageContext.request.contextPath}/user/IndexServlet"><span style="color:#888;font-size: 15px;">返回首页</span></a>
		<span style="color:#888;font-size: 15px;"> | </span>
		<a href="${pageContext.request.contextPath}/user/MachineTypeServlet"><span style="color:#888;font-size: 15px;">返回类型展示</span></a>
	</div>
	<div class="gallery" id="gallery">
		<div class="container">
			<h3 class="animated wow zoomIn" data-wow-duration="1000ms" data-wow-delay="500ms">机型展示</h3>
			<p class="dolor animated wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="800ms">所选机型在下面进行展示，点击进行订购。
				<c:if test="${fn:length(machineList) > 0}">
					<c:forEach items="${machineList}" var="machine" >
						<div class="gallery-grid animated wow slideInLeft" data-wow-duration="1000ms" data-wow-delay="500ms" style="width:342px;height:342px;">
								<c:if test="${not empty machine.machinePic}">
									<img src="/upload/${machine.machinePic}" class="img-responsive" alt="" />
								</c:if>
								<c:if test="${empty machine.machinePic}">
									<img src="${pageContext.request.contextPath}/user/images/11.jpg" class="img-responsive" alt="" />
								</c:if>
								<div class="textbox">
									<h4>${machine.machineName}</h4>
									<a href="${pageContext.request.contextPath}/user/MachineDetailServlet?m=detail&machineId=${machine.machineId}">立即预订</a>
								</div>
							</a>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${fn:length(machineList) < 1}">
					<div style="height:200px;width:400px;margin: 100px auto;">
						<h3  class="animated wow zoomIn" data-wow-duration="1000ms" data-wow-delay="500ms">抱歉，暂无机械</h3>
					</div>
				</c:if>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
<!-- //gallery -->

</body>
</html>
