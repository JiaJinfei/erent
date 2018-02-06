<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
if(${code eq "success"}){
	var execStr = "window.parent.${param.callBack}('${code}','${path}','${msg}','${fileName}')";
	eval(execStr);
}else if(${not empty msg}){
	alert("${msg}");
}

function sub(){
 document.forms[0].submit();
}
</script>
<form action="${pageContext.request.contextPath}/employee/UploadServlet" method="post" enctype="multipart/form-data" >
	<input type="file" name="upload" onchange="sub();" />
	<input type="hidden" name="callBack" value="${param.callBack}" />
	<input type="hidden" name="type" value="${(empty param.type and param.isAtta != 1)  ? 'jpg,jpeg,bmp,gif,png' : param.type}" />
	<input type="hidden" name="size" value="${param.size}" />
	<input type="hidden" name="isAtta" value="${param.isAtta}"/>
</form>