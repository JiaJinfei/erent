<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/user/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/user/css/admin.css">
<script src="${pageContext.request.contextPath}/user/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/user/js/pintuer.js"></script>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/UserOrderServlet?m=pay" id="listform" onsubmit="return sub();">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 订单列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    
    <table class="table table-hover text-center">
      <tr>
        <th width="100" style="text-align:left; padding-left:20px;">序号</th>
        <th width="10%">名称</th>
        <th>图片</th>
        <th>数量</th>
        <th>预订时间</th>
        <th>工作地点</th>
        <th width="10%">价格</th>
        <th width="310">操作</th>
      </tr>
      <volist name="list" id="vo">
      	<c:forEach var="order" items="${orderList}" varStatus="vs">
        <tr>
          <td style="text-align:left; padding-left:20px;"><input type="checkbox" name="deleteId" value="${order.orderId}" onclick="totalPrice();" />
           ${vs.index + 1}</td>
          <td>${order.machine.machineName}</td>
          <td width="10%"><img src="/upload/${order.machine.machinePic}" alt="" width="70" height="50" /></td>
          <td><a href="${pageContext.request.contextPath}/user/UserOrderServlet?m=reducenum&orderId=${order.orderId}" style="border:1px solid #ddd; padding: 0px 10px;margin-right:5px; font-size:20px;border-radius: 3px;">-</a>
          <span style="font-size:15px;">${order.machineNum}</span>
          <a href="${pageContext.request.contextPath}/user/UserOrderServlet?m=addnum&orderId=${order.orderId}" style="border:1px solid #ddd; padding: 0px 10px;margin-left:5px; font-size:20px;border-radius: 3px;">+</a></td>
          <td>${order.orderDate}</td>
          <td>${order.orderAddr}</td>
          <td><fmt:formatNumber value="${order.orderPrice}" pattern="#.00"/></td>
          <td><div class="button-group"><a class="button border-red" href="javascript:void(0)" onclick="return del(${order.orderId})"><span class="icon-trash-o"></span> 删除</a> </div></td>
        </tr>
   		 </c:forEach>
      
      
      <tr>
        <td style="text-align:left; padding:19px 0;padding-left:20px;"><input type="checkbox" id="checkall" />
         	 全选 </td>
        
        <td colspan="6" style="text-align:left;padding-left:20px;">
        	<a href="javascript:void(0)" class="button border-red icon-trash-o" style="padding:5px 15px;" onclick="DelSelect()"> 删除</a>
        	<input  type="submit" value="确认付款" style="padding:5px 15px; margin:0 10px;" class="button border-blue icon-edit" >
        </td>
        
        <td><span style="font-size:16px;">总价：</span><span id="total" style="font-size:16px;">0.0</span><span style="font-size:16px;">元</span>
        	<input id="totalhidden" type="hidden" name="totalPrcie" value="">
        </td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

//全选
$("#checkall").click(function(){ 
  $("input[name='deleteId']").each(function(){
	$(this).prop("checked",$("#checkall").prop("checked"));
		var total = 0.0;
		$("input[name=deleteId]").filter(":checked").each(function(){
		var price = $(this).parent().parent().find("td:eq(6)").html();
		total += parseFloat(price);
		});	
		countPrice(total);
  });
})

//单个删除
function del(id){
	if(confirm("您确定要删除吗?")){
		window.location.href="${pageContext.request.contextPath}/user/UserOrderServlet?m=del&orderId=" + id;
	}
}

//批量删除
function DelSelect(){
	var Checkbox=false;
	 $("input[name='deleteId']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
		var t=confirm("您确认要删除选中的内容吗？");
		if (t==false) return false;
		$("#listform").attr("action","${pageContext.request.contextPath}/user/UserOrderServlet?m=delAll");
		$("#listform").submit();		
	}
	else{
		alert("请选择您要删除的选项");
		return false;
	}
}

function totalPrice(){
	var total = 0.0;
	$("input[name=deleteId]").filter(":checked").each(function(){
			var price = $(this).parent().parent().find("td:eq(6)").html();
			total += parseFloat(price);
	});	
	countPrice(total);
}

function countPrice(price){
	$("#total").html(price);
	$("#totalhidden").attr("value",price);
}

function sub(){
	var Checkbox=false;
	 $("input[name='deleteId']").each(function(){
	  if (this.checked==true) {		
		Checkbox=true;	
	  }
	});
	if (Checkbox){
	
	}
	else{
		alert("请至少选择一项您要付款的机械");
		return false;
	}
}
</script>
</body>
</html>