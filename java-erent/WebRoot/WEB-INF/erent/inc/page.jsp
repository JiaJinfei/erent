<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<tr>
  <td colspan="8">
     <div class="pagelist">
     	<c:if test="${pager.hasPrevious}">
	        <a href="${pageContext.request.contextPath}/${pager.urlLink}&cno=1">首页</a>
	    </c:if>
	    <c:if test="${!pager.hasPrevious}">
	        <a href="">首页</a>
	    </c:if>
	    
     	<c:if test="${pager.hasPrevious}">
     		<a href="${pageContext.request.contextPath}/${pager.urlLink}&cno=${pager.currentPageNo - 1}">上一页</a> 
     	</c:if>
     	<c:if test="${!pager.hasPrevious}">
     		<a href="">上一页</a>
     	</c:if>
     	
     	<!-- <span class="current">1</span> -->
     	
     	<c:if test="${not empty pager.currentPageNo}">
		当前第${pager.currentPageNo}页
		</c:if>
		<c:if test="${!not empty pager.currentPageNo}">当前第1页</c:if>                   
	    <c:if test="${pager.hasNext}">
     		<a href="${pageContext.request.contextPath}/${pager.urlLink}&cno=${pager.currentPageNo + 1}">下一页</a>
     	</c:if>
     	<c:if test="${!pager.hasNext}">
     	  	<a href="">下一页</a>
	    </c:if>
	    
     	<c:if test="${pager.hasNext}">
     		<a href="${pageContext.request.contextPath}/${pager.urlLink}&cno=${pager.totalPageCount}">尾页</a> 
     	</c:if>
     	<c:if test="${!pager.hasNext}">
     		<a href="">尾页</a> 
     	</c:if>
     	
     </div>
       
  </td>
</tr>