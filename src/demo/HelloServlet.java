package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// https://www.oschina.net/question/12_52027
public class HelloServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8344471980771383365L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
//		Cookie[] cookies = req.getCookies();
//		if (cookies != null) {
//			for (Cookie c : cookies) {
//				c.setMaxAge(10);
//				res.addCookie(c);
//				res.getWriter().println("Cookie:" + c.getName() + "=" + c.getValue());
//			}
//		} else {
//			res.getWriter().println("No Cookies!");
//			Cookie cookie = new Cookie("name", "Kevin");
//			cookie.setMaxAge(10);
//			res.addCookie(cookie);
//		}
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		
		HttpSession session = req.getSession();
//		session.setMaxInactiveInterval(10);
		session.setAttribute("name", "Betty");
		session.setAttribute("age", "38");
		
		out.println("创建了session，并放入了两个属性，name和age");
		out.println("Hello World!");
		out.println("<font color=white>验证码：<input type='text' name='checkcode'/><img src='" + req.getContextPath() + "/createCode' />");
		
//		Random r = new Random(Calendar.getInstance().getTimeInMillis());
//		int randomInt = r.nextInt(100);
//		System.out.println("" + randomInt);
//		if (randomInt > 50) {
//			req.getRequestDispatcher("/helloForward").forward(req, res);
//		} else {
//			req.getRequestDispatcher("/helloInclude").include(req, res);
//		}
		if (session.getAttribute("from") != null && "helloForward".equals(session.getAttribute("from"))) {
			session.removeAttribute("from");
			session.setAttribute("jump", "true");
			req.getRequestDispatcher("/helloForward").forward(req, res);
		}
	}

}
