<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/user/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/user/css/admin.css">
<script src="${pageContext.request.contextPath}/user/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/user/js/pintuer.js"></script>
<style type="text/css">
.form-group input{width:400px;}
.form-group{margin: 50px 0;}
div.label{width:100px;}
</style>
</head>
<body>
<div class="panel admin-panel margin-top">
  <div class="panel-head" id="add">
	  <strong>
	  <center><span class="icon-pencil-square-o"></span>注册页面</center>
	  </strong>
  </div>
  <div class="body-content" style="width:600px; margin:0 auto;">
  	
    <form method="post" class="form-x" action="${pageContext.request.contextPath}/user/IndexServlet?m=reg">        
      
      <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>账号：</label>
        </div>
        <div class="field">
        	
          <input type="text" class="input w50" name="userUsername" value="" data-validate="required:请填写账号" maxlength="16"/>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input w50" name="userPwd" value="" data-validate="required:请填写密码,length#>=8:新密码不能小于8位" maxlength="16"/>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>再次输入密码：</label>
        </div>
        <div class="field">
          <input type="password" class="input w50" name="repwd" value="" data-validate="required:请再次输入新密码,repeat#userPwd:两次输入的密码不一致" maxlength="16"/>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>手机号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="userPhone" value="" data-validate="required:请输入手机号,length#>=11||length#<=11:手机号为11位" maxlength="11"/>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group" style="width:200px; margin:0 auto;">
        <div class="label">
          <label></label>
        </div>
        <div class="field" style="width:150px; margin:0 auto;">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body></html>