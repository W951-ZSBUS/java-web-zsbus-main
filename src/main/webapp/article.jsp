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
    	<div class="newstitle">
    		<h3><s:property value="#request.article.articleTitle" /></h3>
    		<h4><s:property value="#request.article.articleSubtitle" /></h4>
    		<h5>作者：<s:property value="#request.article.articleAuthor" />&nbsp;添加时间:<s:property value="#request.article.articleCreatedate.substring(0, 10)" /></h5>
    	</div>
    	<div class="newsdetails">
    		<div class="newstext">
    			<s:property value="#request.article.articleContent" escapeHtml="false" />
    		</div>
    	</div>
    </div>
    
    <jsp:include page="foot.jsp" />
  </body>
</html>
