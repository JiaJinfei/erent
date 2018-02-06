<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>登录</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/admin.css">
    <script src="${pageContext.request.contextPath}/employee/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/employee/js/pintuer.js"></script> 
    <script src="${pageContext.request.contextPath}/employee/js/jquery-3.1.1.min.js"></script> 
    <script type="text/javascript">
    	$(function(){
    		$("#reg").click(function(){
    			window.location.href="${pageContext.request.contextPath}/user/UserLoginServlet?m=regpage";
    		});
    	});
    </script> 
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form action="${pageContext.request.contextPath}/user/IndexServlet?m=log" method="post">
            
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>农车快租欢迎你</h1></div>
                
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input name="userUsername" type="text" class="input input-big" placeholder="登录账号" data-validate="required:请填写账号" maxlength="15"/>
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    
                    
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input name="userPwd" type="password" class="input input-big" placeholder="登录密码" data-validate="required:请填写密码" maxlength="15"/>
                            <span class="icon icon-key margin-small"></span>
                            
                        </div>
                    </div>
                </div>
                <div style="padding:30px;"><input id="submit" type="submit" class="button button-block bg-main text-big input-big" value="登录"></div>
                 <div style="padding:30px;"><input id="reg" type="button" class="button button-block bg-main text-big input-big" value="注册"></div>
                <p style="font-size: 12px; color:red; width:168px; margin:0 auto;">${msg}</p>
            </div>
            </form>          
        </div>
    </div>
</div>

</body>
</html>