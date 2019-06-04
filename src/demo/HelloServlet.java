package demo;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// https://www.oschina.net/question/12_52027
public class HelloServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8344471980771383365L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.getWriter().println("Hello World!");
		
		Random r = new Random(Calendar.getInstance().getTimeInMillis());
		int randomInt = r.nextInt(100);
		System.out.println("" + randomInt);
		if (randomInt > 50) {
			req.getRequestDispatcher("/helloForward").forward(req, res);
		} else {
			req.getRequestDispatcher("/helloInclude").forward(req, res);
		}
	}

}
