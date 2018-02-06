<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>增加机械</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/employee/css/admin.css">
<script src="${pageContext.request.contextPath}/employee/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/employee/js/resize.js"></script>
<script src="${pageContext.request.contextPath}/employee/js/pintuer.js"></script>
<script type="text/javascript">
	function callBack(code,path,msg){
         $("input[name=machinePic]").val(path);
         $("#machinePic").attr("src","/upload/" + path);
         $("#machinePic").show();
         
         $("#machinePic").on("load",function(){
            resizeImg(500,400,"machinePic");
         });
     }
     
     function callBack2(code,path,msg){
         $("input[name=machinePic2]").val(path);
         $("#machinePic2").attr("src","/upload/" + path);
         $("#machinePic2").show();
         
         $("#machinePic2").on("load",function(){
            resizeImg(500,400,"machinePic2");
         });
     }
     
    $(function(){
	    if(${not empty machine.machinePic}){
	           resizeImg(500,400,"machinePic");
	    }
	    if(${not empty machine.machinePic2}){
	           resizeImg(500,400,"machinePic2");
	    }
	    $("#typeForm").submit(function(){
	    	var machineStatus = $("input[name=\"machineStatus\"]");
	    	if(machineStatus.filter(":checked").length < 1){
	    		alert("请选择类型状态");
	    		return false;
	    	}
	    	
	    	var type = $("select[name=\"typeId\"]").val();
	    	if(type == ""){
	    		alert("请选择所属类型");
	    		return false;
	    	}
	    });
    });
     
     
</script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span> 增加内容</strong></div>
  <div class="body-content">
    
    <form id="typeForm" method="post" class="form-x" action="${pageContext.request.contextPath}/employee/MachineServlet?m=add">  
      
      <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>机械名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${machine.machineName}" name="machineName" data-validate="required:请输入机械名称" maxlength="15"/>
          <input type="hidden" name="machineId" value="${machine.machineId}">
          <div class="tips"></div>
        </div>
      </div> 
      
      
      <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>数量：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${machine.machineNum}" name="machineNum" data-validate="required:请输入机械数量" maxlength="9"/>
          <div class="tips"></div>
        </div>
      </div>  
      
      
      <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${machine.machineCode}" name="machineCode" data-validate="required:请输入机械编号" maxlength="15"/>
          <div class="tips"></div>
        </div>
      </div>
      
         
        <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>状态：</label>
        </div>
        <div class="field">
          <div class="button-group radio">
              <input name="machineStatus" value="0" type="radio" ${not empty machine.machineStatus || machine.machineStatus == 0 ? "checked='checked'":""}/>空闲         
              <input name="machineStatus" value="1" type="radio" ${not empty machine.machineStatus && machine.machineStatus == 1 ? "checked='checked'":""}/>工作中
           </div>       
        </div>
     </div>
     
     <div class="clear"></div>

     
      <div class="form-group">
        <div class="label">
          <label><span style="color:red;">*</span>价格：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="machinePrice" value="${machine.machinePrice}"  data-validate="required:请输入机械价格" maxlength="9"/>
          <div class="tips"></div>
        </div>
      </div> 
      
      <if condition="$iscid eq 1">
        <div class="form-group">
          <div class="label">
            <label><span style="color:red;">*</span>所属类型：</label>
          </div>
          <div class="field">
            <select name="typeId" class="input w50">
              <option value="">请选择分类</option>
              <c:forEach var="parent" items="${typeList}">
              <option value="${parent.typeId}" ${parent.typeId == machine.typeId ? "selected='selected'":""}>${parent.typeName}</option>
              </c:forEach>
            </select>
            <div class="tips"></div>
          </div>
        </div>
      </if>
     
     
      <div class="form-group">
        <div class="label">
          <label>图片：</label>
        </div>
        <div class="field">
          <iframe src="${pageContext.request.contextPath}/page/inc/upload.jsp?callBack=callBack" style="border: 0px;height: 30px;margin: 0px;" scrolling="no"></iframe>
   	      <input name="machinePic"  type="hidden" value="${machine.machinePic}"/>
          <div class="tips"></div>
        </div>
      </div>
      
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <img id="machinePic" alt="logo" src="/upload/${machine.machinePic}"  ${empty machine.machinePic ? "style='display:none;'":""}/>
          <div class="tips"></div>
        </div>
      </div>  
      
      
      <div class="form-group">
        <div class="label">
          <label>图片2：</label>
        </div>
        <div class="field">
          <iframe src="${pageContext.request.contextPath}/page/inc/upload.jsp?callBack=callBack2" style="border: 0px;height: 30px;margin: 0px;" scrolling="no"></iframe>
   	      <input name="machinePic2"  type="hidden" value="${machine.machinePic2}"/>
          <div class="tips"></div>
        </div>
      </div>
      
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <img id="machinePic2" alt="logo" src="/upload/${machine.machinePic2}"  ${empty machine.machinePic2 ? "style='display:none;'":""}/>
          <div class="tips"></div>
        </div>
      </div>
      
      
      <div class="form-group">
        <div class="label">
          <label>备注：</label>
        </div>
        <div class="field">
          <textarea rows="10" cols="50" style="width:500px" class="input w50" name="machineRemark" maxlength="50">${machine.machineRemark}</textarea>
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