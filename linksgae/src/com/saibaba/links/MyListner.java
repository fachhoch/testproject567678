package com.saibaba.links;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListner implements ServletContextListener {
	
	private static final Logger log = Logger.getLogger(MyListner.class.getName()); 

	Map<String, String>  urls=new HashMap<String, String>();
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		log.info("My liastner initialized  *****************************************");
		Mirchi9Reader  mirchi9Reader =null;
		try{
			log.info("start reader");

			mirchi9Reader= new Mirchi9Reader();
			log.info("finish reader");
			log.log(Level.INFO, String.valueOf(mirchi9Reader.getLinks().size()));
		}catch (Exception e) {
			log.log(Level.SEVERE, "Uncaught exception", e);
		}
		context.getServletContext().setAttribute("links", mirchi9Reader!=null ?mirchi9Reader.getLinks():new HashMap<String, String>());
	}
}
