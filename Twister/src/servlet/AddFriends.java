package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

@SuppressWarnings("serial")
public class AddFriends  extends HttpServlet {
	
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
		String key = request.getParameter("key");
		String usernameTo = request.getParameter("usernameTo");
		

		try {
			out.println(services.friends.AddFriends.addFriends(username, usernameTo, key));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	{
		doPost(request, response);
	}
}
