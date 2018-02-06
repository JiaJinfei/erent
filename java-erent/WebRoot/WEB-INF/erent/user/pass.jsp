<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/user/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/user/css/admin.css">
<script src="${pageContext.request.contextPath}/user/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/user/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/user/js/pintuer.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 修改密码</strong></div>
  <div class="body-content">
    <form id="form" method="post" class="form-x" action="${pageContext.request.contextPath}/user/UserCenterServlet?m=repwd">
      <div class="form-group">
        <div class="label">
          <label for="sitename">当前用户帐号：</label>
        </div>
        <div class="field">
          <label style="line-height:33px;">
           ${user_session.userUsername}
          </label>
        </div>
      </div>      
      <div class="form-group">
        <div class="label">
          <label for="sitename">原始密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input w50" id="mpass" name="mpass" size="50" placeholder="请输入原始密码" data-validate="required:请输入原始密码" maxlength="15"/>       
        </div>
      </div>      
      <div class="form-group">
        <div class="label">
          <label for="sitename">新密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input w50" name="newpass" size="50" placeholder="请输入新密码" data-validate="required:请输入新密码,length#>=5:新密码不能小于5位" maxlength="15"/>         
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">确认新密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input w50" name="renewpass" size="50" placeholder="请再次输入新密码" data-validate="required:请再次输入新密码,repeat#newpass:两次输入的密码不一致" maxlength="15" />          
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
        	<p style="color:red; font-size:14px;">${not empty msg ? msg : ""}</p>
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>   
        </div>
      </div>      
    </form>
  </div>
</div>
</body></html>