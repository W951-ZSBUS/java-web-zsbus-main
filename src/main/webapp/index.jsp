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
		<div class="title">乘车指南</div>
		<div class="handbookpic">
			<div class="article" onclick="window.location.href='http://localhost:8080/w951-wap-zsbus-bus/#home'">
	            <div class="blackbg"></div>
	            <div class="handbooktext">线路查询</div>
	            <img class="zoom" src="images/zoom.png" />
	            <img class="buspic" src="images/home_14.jpg" />
        	</div>
        	<div class="article" onclick="window.location.href='http://localhost:8080/w951-wap-zsbus-bus/#transition'">
	            <div class="blackbg"></div>
	            <div class="handbooktext">换乘查询</div>
	            <img class="zoom" src="images/zoom.png" />
	            <img class="buspic" src="images/home_16.jpg" />
	        </div>
	        <div class="article" onclick="window.location.href='http://localhost:8080/w951-wap-zsbus-bus/#home'">
	            <div class="blackbg"></div>
	            <div class="handbooktext">实时公交</div>
	            <img class="zoom" src="images/zoom.png" />
	            <img class="buspic" src="images/home_18.jpg" />
	        </div>
		</div>
	</div>
	
	<div class="content">
		<div class="title">公告新闻</div>
		<div class="handbookpic">
			<s:iterator value="#request.categories" var="c" status="s1">
				<div class="notice">
					<div class="noticelist">
						<img class="noticeimg" src="images/noticeimg_40.png" />
	                	<span class="noticetitle"><s:property value='#c.categoryName' /></span>
	                	<span style="float:right"><a href="main/category?categoryId=<s:property value='#c.categoryId' />&categoryName=<s:property value='#c.categoryName' />"><img class="noticemore" src="images/noticeimg_43.png" /></a></span>
					</div>
					<div class="noticeul">
		                <ul>
		                	<s:iterator value="#c.articles" var="a" status="s2">
		                		<li>
		                			<a href="main/article?articleId=<s:property value='#a.articleId' />">
		                				<s:if test="#a.articleTitle.length() > 20">
		                					<s:property value='#a.articleTitle.substring(0, 20)' />...
		                				</s:if>
		                				<s:else>
		                					<s:property value='#a.articleTitle' />
		                				</s:else>
		                				<span><s:property value='#a.articleCreatedate.substring(0, 10)' /></span>
		                			</a>
		                		</li>
		                	</s:iterator>
		                </ul>
		            </div>
				</div>
			</s:iterator>
		</div>
	</div>
	
	<jsp:include page="foot.jsp" />
  </body>
</html>
