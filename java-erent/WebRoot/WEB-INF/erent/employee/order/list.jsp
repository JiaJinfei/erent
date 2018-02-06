<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>订单</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/admin.css">
    <script src="${pageContext.request.contextPath}/employee/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/employee/js/pintuer.js"></script>  
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 订单</strong></div>
    <table class="table table-hover text-center">
      <tr>
        <th width="120">序号</th>
        <th width="120">订单编号</th>
        <th>订单日期</th>       
        <th>下单用户</th>
        <th>订单状态</th>
        <th>订单总价</th>
        <th>用户手机号</th>
        <th>工作地址</th>
      </tr>   
         <c:forEach var="order" items="${orderList}" varStatus="vs">
	        <tr>
	          <td>${vs.index + 1}</td>
	          <td>${order.orderCode}</td>
	          <td>${order.orderDate}</td>
	          <td>${order.user.userName}</td>
	          <td>${order.orderStatus == 1 ? "<font style='color:red;'>".concat(order.orderStatusText).concat("</font>"):order.orderStatusText}</td>
	          <td>${order.orderPrice}</td>
	          <td>${order.user.userPhone}</td>  
	          <td>${order.orderAddr}</td>         
	        </tr>
        </c:forEach>
        <tr>
      <%@include file="/WEB-INF/erent/inc/page.jsp" %>
    </table>
  </div>
</form>
<script type="text/javascript">

$("#checkall").click(function(){ 
  $("input[name='id[]']").each(function(){
	  if (this.checked) {
		  this.checked = false;
	  }
	  else {
		  this.checked = true;
	  }
  });
})

$("#add").click(function(){
	window.location.href="${pageContext.request.contextPath}/employee/TypeServlet?m=edit";
});

function DelSelect(){
	var Checkbox=false;
	 $("input[name='id[]']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if (t==false) return false; 		
	}
	else{
		alert("请选择您要删除的内容!");
		return false;
	}
}

</script>
</body></html>