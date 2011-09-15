package com.saibaba.links;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PingCronServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(PingCronServlet.class
			.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		log.log(Level.INFO, "started ping ");
		log.log(Level.INFO, "end ping ");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
