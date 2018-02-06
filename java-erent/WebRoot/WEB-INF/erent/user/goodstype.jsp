<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>机械类型</title>

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
<script src="${pageContext.request.contextPath}/user/js/jquery-1.11.1.min.js"></script>
<!-- //js -->
<!-- animation-effect -->
<link href="${pageContext.request.contextPath}/user/css/animate.min.css" rel="stylesheet"> 
<script src="${pageContext.request.contextPath}/user/js/wow.min.js"></script>
<script>
 new WOW().init();
</script>
<!-- //animation-effect -->

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
<!-- services -->
	<div style="height:30px;margin: 10px 0 0 20px;">
		<a href="${pageContext.request.contextPath}/user/IndexServlet"><span style="color:#888;font-size: 15px;">返回首页</span></a>
	</div>
	<div class="services" id="services">
			
		<div class="container">
			<h3 class="animated wow zoomIn" data-wow-duration="1000ms" data-wow-delay="500ms">机械类型</h3>
			<p class="dolor animated wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="800ms">请在下面选择想要租赁的机械类型</p>
			<div class="services-grids">
					
					<c:forEach var="type" items="${typeList}" varStatus="vs" begin="0" step="1">
					<div class="col-md-4 services-grid animated wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="800ms">
						<div class="services-grid1" style="height: 340px;">
							<div class="services-grid1-sub">
								<a href="${pageContext.request.contextPath}/user/UserMachineServlet?typeId=${type.typeId}"><span></span></a>
							</div>
							<h4>${type.typeName}</h4>
							<p>${type.typeRemark}</p>
						</div>
					</div>
					</c:forEach>
					
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
<!-- //services -->
</body>
</html>
