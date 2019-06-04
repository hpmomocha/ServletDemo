package demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// https://www.yiibai.com/servlet/requestdispatcher-in-servlet.html
public class HelloForwardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8190114728488389528L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("Hello Forward!");
	}

}
