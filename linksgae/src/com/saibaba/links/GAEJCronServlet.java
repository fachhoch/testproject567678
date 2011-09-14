package com.saibaba.links;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GAEJCronServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(GAEJCronServlet.class
			.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			log.info("Cron Job has been executed");
			Mirchi9Reader  mirchi9Reader =null;
				log.info("start reader");
				mirchi9Reader= new Mirchi9Reader();
				log.info("finish reaing url");
				log.log(Level.INFO, String.valueOf(mirchi9Reader.getLinks().size()));
				MyLinks.getInstance().addFromMirchi9(mirchi9Reader);
		} catch (Exception ex) {
			log.log(Level.SEVERE, "Uncaught exception", ex);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
