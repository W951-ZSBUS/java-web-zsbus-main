<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>" />
    <jsp:include page="/resources.jsp" />
    <title><%=System.getProperty("systemName") %></title>
  </head>
  
  <body>
    <jsp:include page="head.jsp" />
    
    <div class="content">
    	<div class="title"><s:property value="#request.categoryName" /></div>
    	<div class="buslist">
    		<ul>
    			<s:iterator value="#request.articles" var="v" status="s">
    				<li><a href="main/article?articleId=<s:property value='#v.articleId' />"><s:property value="#v.articleTitle" /><span><s:property value="#v.articleCreatedate.substring(0, 10)" /></span></a></li>
    			</s:iterator>
    		</ul>
    	</div>
    	<div class="buslist" style="padding-top:20px;">
    		<s:if test="#request.page > 1">
    			<div class="pagebreak" onclick="window.location.href='main/category?categoryId=<s:property value='#request.categoryId' />&categoryName=<s:property value='#request.categoryName' />&page=<s:property value='#request.page - 1' />'">上一页</div>
    		</s:if>
    		<s:if test="#request.page < #request.pageCount">
    			<div class="pagebreak" onclick="window.location.href='main/category?categoryId=<s:property value='#request.categoryId' />&categoryName=<s:property value='#request.categoryName' />&page=<s:property value='#request.page + 1' />'">下一页</div>
    		</s:if>
	    </div>
    </div>
    
    <jsp:include page="foot.jsp" />
  </body>
</html>
