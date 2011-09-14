package com.saibaba.links;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GAEJSignupSubscriberServlet extends HttpServlet {
	private static final Logger _logger = Logger
			.getLogger(GAEJSignupSubscriberServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String strCallResult = "";
		resp.setContentType("text/plain");
		try {
			_logger.log(Level.INFO, "staerting task");
			Mirchi9Reader  mirchi9Reader= new Mirchi9Reader();
			_logger.log(Level.INFO, "task finished");
			resp.getWriter().println(strCallResult);
		} catch (Exception ex) {
			_logger.log(Level.SEVERE, "Uncaught exception", ex);
			//strCallResult = "FAIL: Subscriber Signup : " + ex.getMessage();
			//_logger.info(strCallResult);
			resp.getWriter().println("failed");
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}