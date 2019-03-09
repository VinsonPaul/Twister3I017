package servlet;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Login extends HttpServlet{
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
	{	
		PrintWriter out = null;
		response.setContentType("text/plain");
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		try {
			JSONObject res = services.user.Login.login(username, password);
			if((int)res.getInt("code") == 0) {
				RequestDispatcher view = request.getRequestDispatcher("/WebContent/resources/html/homepage.html");
		        view.forward(request, response);  
			}
			else {
				out = response.getWriter();
				out.println(res);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	{
		doPost(request, response);
	}
}

