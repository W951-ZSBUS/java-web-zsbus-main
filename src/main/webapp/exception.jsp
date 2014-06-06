<%@ page import="org.apache.log4j.Logger"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	Logger logger = Logger.getLogger(this.getClass());
	//logger.error("-------------------- Action Layer throws Exception --------------------");
	//logger.error(request.getAttribute("exception.message"));
	logger.error(request.getAttribute("exceptionStack"));
%>
<script>alert('出错了');</script>