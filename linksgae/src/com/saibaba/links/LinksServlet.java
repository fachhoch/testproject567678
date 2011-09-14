package com.saibaba.links;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LinksServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		req.setAttribute("linksMap", MyLinks.getInstance().getLinks());
		try{
			 getServletConfig().getServletContext().getRequestDispatcher(
		        "/jsp/links.jsp").forward(req,resp);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
