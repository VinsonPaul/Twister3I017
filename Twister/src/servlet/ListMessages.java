package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

@SuppressWarnings("serial")
public class ListMessages extends HttpServlet{
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("text/json");
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}

		try {
			out.println(services.messages.ListMessages.listMessages());
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
