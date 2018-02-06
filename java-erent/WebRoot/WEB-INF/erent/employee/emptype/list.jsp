<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>机械分类</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/admin.css">
    <script src="${pageContext.request.contextPath}/employee/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/employee/js/pintuer.js"></script>  
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 机械分类管理</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
          <div class="padding border-bottom">
    		<button type="button" class="button border-yellow" id="add"><span class="icon-plus-square-o"></span> 添加分类</button>
  		</div>
        </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="120">序号</th>
        <th width="120">分类编号</th>
        <th>分类名</th>       
        <th>父分类</th>
        <th>排序</th>
        <th>状态</th>
        <th>操作</th>       
      </tr>   
         <c:forEach var="machineType" items="${machineTypes}" varStatus="vs">
	        <tr>
	          <td>${vs.index + 1}</td>
	          <td>${machineType.typeCode}</td>
	          <td>${machineType.typeName}</td>
	          <td>${machineType.parent.typeName}</td>
	          <td>${machineType.typeSort}</td>  
	          <td>${machineType.typeStatus == 1 ? "<font color='red'>禁用</font>":"启用"}</td>         
	          <td><div class="button-group"> <a class="button border-red" href="${pageContext.request.contextPath}/employee/TypeServlet?m=edit&typeId=${machineType.typeId}"><span class="icon-trash-o"></span> 修改</a> </div></td>
	        </tr>
        </c:forEach>
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