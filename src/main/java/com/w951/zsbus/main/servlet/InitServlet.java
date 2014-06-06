package com.w951.zsbus.main.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = -1L;

	public void init() throws ServletException {
		try {
			Properties pro = PropertiesLoaderUtils.loadAllProperties("system.properties");
			
			String systemName = pro.getProperty("system.name");
			String systemPath = pro.getProperty("system.path");
			String systemProj = pro.getProperty("system.proj");
			String newsUrl = pro.getProperty("system.newsUrl");
			
			System.setProperty("systemName", systemName);
			System.setProperty("systemPath", systemPath);
			System.setProperty("systemProj", systemProj);
			System.setProperty("newsUrl", newsUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
