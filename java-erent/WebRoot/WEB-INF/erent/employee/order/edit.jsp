<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>增加机械类型</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/admin.css">
<script src="${pageContext.request.contextPath}/employee/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/employee/js/pintuer.js"></script>
<script type="text/javascript">
	$(function(){
		var typeStatus = $("#typeStatus").filter("checked");
				
		
	});
</script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span> 增加内容</strong></div>
  <div class="body-content">
    <form id="order" method="post" class="form-x" action="${pageContext.request.contextPath}/employee/TypeServlet?m=add">  
      <div class="form-group">
        <div class="label">
          <label>类型名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${machineType.typeName}" name="typeName" data-validate="required:请输入类型名称" maxlength="15"/>
          <input type="hidden" name="typeId" value="${machineType.typeId}">
          <div class="tips"></div>
        </div>
      </div> 
      <div class="form-group">
        <div class="label">
          <label>编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${machineType.typeCode}" name="typeCode" data-validate="required:请输入编号" maxlength="15"/>
          <div class="tips"></div>
        </div>
      </div>     
        <div class="form-group">
        <div class="label">
          <label>状态：</label>
        </div>
        <div class="field">
          <div class="button-group radio">
              <input name="typeStatus" value="0" type="radio" ${not empty machineType.typeStatus || machineType.typeStatus == 0 ? "checked='checked'":""}/>启用           
              <input name="typeStatus" value="1" type="radio" ${not empty machineType.typeStatus && machineType.typeStatus == 1 ? "checked='checked'":""}/>禁用
           </div>       
        </div>
     </div>
      <if condition="$iscid eq 1">
        <div class="form-group">
          <div class="label">
            <label>父分类：</label>
          </div>
          <div class="field">
          	
            <select name="parentId" class="input w50">
              <option value="">请选择分类</option>
              <c:forEach var="parent" items="${parentList}">
              <option value="${parent.typeId}" ${parent.typeId == machineType.parentId ? "selected='selected'":""}>${parent.typeName}</option>
              </c:forEach>
            </select>
            <div class="tips"></div>
          </div>
        </div>
      </if>
     
      <div class="clear"></div>

      <div class="form-group">
        <div class="label">
          <label>排序：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="typeSort" value="${machineType.typeSort}"  data-validate="number:排序必须为数字" maxlength="5"/>
          <div class="tips"></div>
        </div>
      </div>
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