<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>前台首页</title>
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
<!-- <script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script> -->
<!-- start-smooth-scrolling -->
<style type="text/css">
	a {color:#fff;}
	a:hover{color:#F8BE1E;}
</style>
</head>
	
<body>
<!-- banner -->
	<div class="banner">
		<div class="container">
			<div class="logo-top">
				<div class="logo-top-left animated wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="900ms">
					<p>
					<span class="glyphicon glyphicon-enveok"></span>
					<c:if test="${not empty user_session}">
						<a href="${pageContext.request.contextPath}/user/UserCenterServlet?m=rename">${user_session.userName}</a>
					</c:if>
					我们欢迎您！</p>
				</div>
				<div class="logo-top-right animated wow fadeInRight" data-wow-duration="1000ms" data-wow-delay="900ms">
					<ul>
						<c:if test="${empty user_session}">
							<li><span class="glyphicon glyphicon-envelog" aria-hidden="true"></span> <a href="${pageContext.request.contextPath}/user/UserLoginServlet?m=login">请登录</a></li>
						</c:if>
						<c:if test="${not empty user_session}">
							<li><span class="glyphicon glyphicon-envelogok" aria-hidden="true"></span>已登录</li>
							<li><span class="glyphicon glyphicon-envelogout" aria-hidden="true"></span><a href="${pageContext.request.contextPath}/user/UserLoginServlet?m=out">注销</a></li>
						</c:if>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="logo">
				<h1 class="animated wow zoomIn" data-wow-duration="1000ms" data-wow-delay="500ms"><a href="${pageContext.request.contextPath}/user/IndexServlet">农<span>车</span>快租</a></h1>
			</div>
			<div class="navigation animated wow zoomIn" data-wow-duration="1000ms" data-wow-delay="500ms">
				<nav class="navbar navbar-default">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
					  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					  </button>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">
						<nav>
							<ul class="nav navbar-nav" style="width:60%;">
								<li class="active"><a href="${pageContext.request.contextPath}/user/IndexServlet">主页</a></li>
								<li><a href="${pageContext.request.contextPath}/user/UserCenterServlet?m=repwd" class="scroll">修改密码</a></li>
								<li><a href="#about" class="scroll">关于我们</a></li>
								<li><a href="${pageContext.request.contextPath}/user/MachineTypeServlet" class="scroll">租赁商品</a></li>
								<li><a href="${pageContext.request.contextPath}/user/UserOrderServlet?m=order" class="scroll">查看订单</a></li>
							</ul>
						</nav>
					</div>
					<!-- /.navbar-collapse -->
				</nav>
			</div>
		</div>
	</div>
<!-- //banner -->
<!-- banner-bottom -->
	<div class="banner-bottom">
		<div class="container">
			<div class="banner-bottom-top animated wow flash" data-wow-duration="1000ms" data-wow-delay="800ms">
				<span></span>
			</div>
			<h2 class="animated wow slideInUp" data-wow-duration="1000ms" data-wow-delay="800ms"><span>更</span>迅速  <span>更</span>方便 <span>更</span>新型</h2>
			<div class="banner-bottom-grids">
				<h3 class="animated wow slideInLeft" data-wow-duration="1000ms" data-wow-delay="800ms">
					&nbsp;农车快租，让你享受最优质的服务，最快捷的方式，更普通的价格，让你的作物得到适宜的收获，让你的土地得到最优质的保养。我们就在你身边！
				</h3>
				<div class="col-md-4 banner-bottom-grid animated wow fadeInLeftBig" data-wow-duration="1000ms" data-wow-delay="500ms">
					 <div id="box" class="burst-circle teal">
						<div class="caption"></div>
						<img src="${pageContext.request.contextPath}/user/images/12.jpg" class="img-responsive" />
						<h4>优质</h4>
					</div>
				</div>
				<div class="col-md-4 banner-bottom-grid animated wow fadeInUpBig" data-wow-duration="1000ms" data-wow-delay="500ms">
					 <div id="box" class="burst-circle teal">
						<div class="caption"></div>
						<img src="${pageContext.request.contextPath}/user/images/11.jpg" class="img-responsive" />
						<h4>快捷</h4>
					</div>
				</div>
				<div class="col-md-4 banner-bottom-grid animated wow fadeInRightBig" data-wow-duration="1000ms" data-wow-delay="500ms">
					 <div id="box" class="burst-circle teal">
						<div class="caption"></div>
						<img src="${pageContext.request.contextPath}/user/images/2.jpg" class="img-responsive" />
						<h4>量优</h4>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
<!-- //banner-bottom -->
<!-- about -->
	<div class="about" id="about">	
		<div class="container">
			<h3 class="animated wow zoomIn" data-wow-duration="1000ms" data-wow-delay="500ms">关于我们</h3>
			<p class="dolor dolor1 animated wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="800ms">我公司的发展是要从农业机械化方面促进我国农业技术和产量和不断提高</p>
			<div class="about-grids">
				<div class="col-md-6 about-grid animated wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="800ms">
					<figure class="block-1">
					  <div class="thumbs">
						<a href="index.html" class="btn">农业机械化</a>
						<img src="${pageContext.request.contextPath}/user/images/4.jpg" alt=" " class="img-responsive" />
					  </div>
						<figcaption>
						<span>农业机械化的发展是当前的主流发展方向</span>
						</figcaption>
					</figure>
				</div>
				<div class="col-md-6 about-grid animated wow fadeInRight" data-wow-duration="1000ms" data-wow-delay="800ms">
					<h4>我们使用最优质的机械和技术实现农业生产的机械化生产</h4>
					<p>当前农村农业的发展还没有从农耕的老式的农业生产中脱离出来，农业的发展迫切的需要机械的生产方式转型和农业生产
						人员的技术现代化转型，机械现代化的发展是一个转型农业生产的重要阶段。
					</p>
					<div class="bar-one bar-con">
							<div class="bar" data-percent="20"></div>
						</div>

					  <div class="bar-two bar-con">
						<div class="bar" data-percent="50"></div>
					  </div>

					  <div class="bar-three bar-con">
						<div class="bar" data-percent="70"></div>
					  </div>
					<script>
						(function ( $ ) {
							  $.fn.progress = function() {
								var percent = this.data("percent");
								this.css("width", percent+"%");
							  };
							}( jQuery ));

							$(document).ready(function(){
							  $(".bar-one .bar").progress();
							  $(".bar-two .bar").progress();
							  $(".bar-three .bar").progress();
							});
					</script>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
<!-- //about -->
<!-- about-bottom -->
	<div class="about-bottom">
		<div class="col-md-9 about-bottom-left animated wow fadeInLeft" data-wow-duration="1000ms" data-wow-delay="800ms">
			<h3>我们致力于更优质的服务，先进的农产品加工机械，更高的机械化农业理念，精准的全球化战略目标，实现农业现代化。</h3>
			<p>在新的时代，全球的人口剧增造成了发展农业的必要性，农业技术的高度发展是一个国家首当其冲的重要目标。农业生产的产量更高，
			农业技术的稳步上升，土地资源的合理利用，人员技术知识的高水平化是我国在新的时代有着重要的战略意义和社会意义。高技术农业的发展是
			一个国家在世界的潮流中稳步提高的重要指标，这不仅对中国，更加对全人类的生存发展有着显示的意义。
			</p>
		</div>
		<div class="col-md-3 about-bottom-right animated wow fadeInRight" data-wow-duration="1000ms" data-wow-delay="800ms">
			<div class="grid">
				<figure class="effect-julia">
					<img src="${pageContext.request.contextPath}/user/images/10.jpg" alt=" " class="img-responsive" />
					<figcaption>
						<h3>优质的<span>农产品</span></h3>
						<div>
							<p>优质的现代化机械</p>
							<p>先进的技术水平</p>
							<p>丰富的产品服务</p>
						</div>
					</figcaption>			
				</figure>
			</div>
		</div>
		<div class="clearfix"> </div>
	</div>
<!-- //about-bottom -->

<!-- footer -->
	<div class="footer animated wow lightSpeedIn" data-wow-duration="1000ms" data-wow-delay="500ms">
		<div class="container">
			<div class="footer-grid">
				<h3><span>这是个新的时代</span> 农业新时代</h3>
			</div>
			<div class="footer-grid-logo">
			</div>
		</div>
	</div>
	<div class="footer-copy animated wow slideInUp" data-wow-duration="1000ms" data-wow-delay="500ms">
		<div class="container">
			<p>农车快租 &copy; 山西农业大学软件学院.</p>
		</div>
	</div>
<!-- //footer -->
<!-- for bootstrap working -->
	<script src="${pageContext.request.contextPath}/user/js/bootstrap.js"></script>
</body>
</html>
