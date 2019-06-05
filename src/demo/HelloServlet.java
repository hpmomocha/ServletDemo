package demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
		Cookie cookie = new Cookie("name", "Kevin");
		cookie.setPath("/hello");
		res.addCookie(cookie);
		
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				res.getWriter().println("Cookie:" + c.getName() + "=" + c.getValue());
			}
		} else {
			res.getWriter().println("No Cookies!");
		}
		res.getWriter().println("Hello World!");
		
//		Random r = new Random(Calendar.getInstance().getTimeInMillis());
//		int randomInt = r.nextInt(100);
//		System.out.println("" + randomInt);
//		if (randomInt > 50) {
//			req.getRequestDispatcher("/helloForward").forward(req, res);
//		} else {
//			req.getRequestDispatcher("/helloInclude").forward(req, res);
//		}
	}

}
