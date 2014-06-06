<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div id="header">
	<div id="bgbox">
		<div id="logo" onclick="window.location.href='index'" style="cursor:pointer;">
            <div id="logopc">
                <img src="images/home_03.png" />
            </div>
            <div id="logotx">
                <div id="logotxcn">
                    <img src="images/home_05.png" />
                </div>
                <div id="logotxen">
                    <img src="images/home_08.png" />
                </div>
            </div>
        </div>
        
        <div class="nav">
			<ul>
				<s:iterator value="#request.menus" var="m" status="s1">
					<li>
						<a href="main/category?categoryId=<s:property value='#m.categoryId' />&categoryName=<s:property value='#m.categoryNameUTF8' />" style="color:#555;"><s:property value='#m.categoryName' /></a>
						<div class="nav_list">
							<s:iterator value="#m.children" var="m1" status="s2">
								<div class="nav_list_li"><a href="main/category?categoryId=<s:property value='#m1.categoryId' />&categoryName=<s:property value='#m1.categoryNameUTF8' />"><s:property value='#m1.categoryName' /></a></div>
							</s:iterator>
						</div>
					</li>
				</s:iterator>
	       </ul>
		</div>
	</div>
</div>

<div id="banner">
	<img src="images/home_13.png" />
	<div id="bannershadow"></div>
</div>