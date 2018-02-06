<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>员工管理</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/admin.css">
    <script src="${pageContext.request.contextPath}/employee/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/employee/js/pintuer.js"></script>
    <style type="text/css">
    	td{
    		height:59px;
    	}
    </style>  
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 员工管理</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
          <c:if test="${employee_user.userUsername == 'admin'}">
	          <button type="button" class="button border-yellow" id="add"><span class="icon-plus-square-o"></span> 添加员工</button>
          </c:if>
        </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="120">序号</th>
        <th width="120">员工编号</th>
        <th>姓名</th>       
        <th>用户名</th>
        <th>电话</th>
        <th>状态</th>
        <th>操作</th>       
      </tr>   
         <c:forEach items="${users}" var="user" varStatus="vs">
        <tr>
          <td>${vs.index + 1}</td>
          <td>${user.userCode}</td>
          <td>${user.userName}</td>
          <td>${user.userUsername}</td>
          <td>${user.userPhone}</td>  
          <td>${user.userStatus == 0 ? "<font style='color:green'>在线</font>":"<font style='color:red'>下线</font>"}</td>         
          <td>
          	<c:if test="${employee_user.userUsername == user.userUsername || employee_user.userUsername == 'admin'}">
          		<div class="button-group" > <a class="button border-red" href="${pageContext.request.contextPath}/employee/UserServlet?m=edit&userId=${user.userId}" ><span class="icon-trash-o"></span> 修改</a> </div>
          	</c:if>
          </td>
        </tr>
        </c:forEach>
      <%@include file="/WEB-INF/erent/inc/page.jsp" %>
    </table>
  </div>
</form>
<script type="text/javascript">


$("#add").click(function(){
	window.location.href="${pageContext.request.contextPath}/employee/UserServlet?m=edit";
});


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