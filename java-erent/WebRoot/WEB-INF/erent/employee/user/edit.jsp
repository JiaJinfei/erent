<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>增加员工</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/admin.css">
<script src="${pageContext.request.contextPath}/employee/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/employee/js/pintuer.js"></script>
<script type="text/javascript">
	$(function(){
		$("input[name=\"userUsername\"]").change(function(){
			var username = $("input[name=\"userUsername\"]");
			$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/employee/UserServlet",
			   data: "m=validUserName&username=" + username.val(),
			   success: function(data){
					var json = eval("(" + data + ")");
					if(json.code == -1){
						alert("用户名已存在请重新输入");
						username.val("");
					}
			   }
			});
		
		
		});
	});
</script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span> 增加内容</strong></div>
  <div class="body-content">
    <form id="emplForm" method="post" class="form-x" action="${pageContext.request.contextPath}/employee/UserServlet?m=add">  
      
      <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>员工姓名：</label>
        </div>
        <div class="field">
          <input type="text" name="userName" value="${user.userName}"  class="input w50" data-validate="required:请输入员工姓名" maxlength="15"/>
          <input type="hidden" name="userId" value="${user.userId}">
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>员工编号：</label>
        </div>
        <div class="field">
          <input type="text" name="userCode" value="${user.userCode}" class="input w50" data-validate="required:请输入员工编号" maxlength="15"/>
          <div class="tips"></div>
        </div>
      </div> 
      
      <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>员工用户名：</label>
        </div>
        <div class="field">
          <input type="text" name="userUsername" value="${user.userUsername}"  class="input w50" data-validate="required:请输入员工用户名" maxlength="15"/>
          <div class="tips"></div>
        </div>
      </div>
       
      <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>员工手机号：</label>
        </div>
        <div class="field">
          <input type="text" name="userPhone" value="${user.userPhone}"  class="input w50" data-validate="required:请输入员工手机号,length#>=11||length#<=11:手机号为11位" maxlength="11"/>
          <div class="tips"></div>
        </div>
      </div>
       
      <div class="clear"></div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body></html>