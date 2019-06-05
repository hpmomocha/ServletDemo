package demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// https://www.yiibai.com/servlet/requestdispatcher-in-servlet.html
public class HelloForwardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8190114728488389528L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
	    PrintWriter out = resp.getWriter();
	    // 获取session
	    HttpSession session = req.getSession();
	    String name = (String) session.getAttribute("name");
	    if (name == null) {
	    	session.setAttribute("from", "helloForward");
	    	req.getRequestDispatcher("/hello").forward(req, resp);
	    } else {
		    String age = (String) session.getAttribute("age");
		    out.println("name:"+name+" age:"+age);
	    }
	    if ("true".equals(session.getAttribute("jump"))) {
	    	out.println("HelloForwardServlet->HelloServlet->HelloForwardServlet");
	    	session.removeAttribute("jump");
	    }
	}

}
