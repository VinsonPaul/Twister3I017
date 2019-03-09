package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DeleteUser extends HttpServlet{
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("text/json");
		PrintWriter out=null;
		try {
			out = response.getWriter ();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String key = request.getParameter("key");
		
	    try {
			out.println(services.user.DeleteUser.deleteUser(username,password,key));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	{
		doPost(request, response);
	}
}

