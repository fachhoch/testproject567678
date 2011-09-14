package com.saibaba.links;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListner implements ServletContextListener {
	
	Map<String, String>  urls=new HashMap<String, String>();
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		Mirchi9Reader  mirchi9Reader =null;
		try{
			mirchi9Reader= new Mirchi9Reader();
		}catch (Exception e) {
		}
		context.getServletContext().setAttribute("links", mirchi9Reader!=null ?mirchi9Reader.getLinks():new HashMap<String, String>());
	}
}
