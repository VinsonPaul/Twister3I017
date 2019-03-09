package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CreateUser extends HttpServlet{
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("text/json");
		PrintWriter out = null;
		try {
			out = response.getWriter ();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		String name = request.getParameter("name");
		String firstName = request.getParameter("firstName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String sexe = request.getParameter("sexe");
		
			try {
				out.println(services.user.CreateUser.createUser(username, password, name, firstName, mail, sexe));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				response.setContentType("text/plain");
				out.println("username : " + username);
				out.println("mail : " + mail);
				out.println(e.getMessage());
				out.println(e.getStackTrace() + "Instantiation");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				response.setContentType("text/plain");
				out.println("username : " + username);
				out.println("mail : " + mail);
				out.println(e.getMessage());
				out.println(e.getStackTrace()  + "Illegal");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				response.setContentType("text/plain");
				out.println("username : " + username);
				out.println("mail : " + mail);
				out.println(e.getMessage());
				out.println(e.getStackTrace() + "NotFound");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				response.setContentType("text/plain");
				out.println("username : " + username);
				out.println("sexe : " + sexe);
				out.println(e.getMessage());
				out.println(e.getStackTrace() + "SQL");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				response.setContentType("text/plain");
				out.println("username : " + username);
				out.println("mail : " + mail);
				out.println(e.getMessage());
				out.println(e.getStackTrace()  + "???");
			}
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	{
		doPost(request, response);
	}
}
