package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;


public class Friends_BD {

	
	public static boolean addFriends(int idFrom ,int idTo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean res;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		
		
		String q = "INSERT INTO Friends(idfrom, idto,date) "
				+ "values (" + idFrom + "," + idTo + ", CURRENT_DATE());";
		
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(q);
		if(rs == 1)
			res = true;
		else
			res = false;
		st.close();
		conn.close();
		
		return res ; 
	}
	
	
	public static JSONObject  searchUsers(String query) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		JSONObject res =new JSONObject() ;
		int i =0;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		String q = "SELECT * FROM Users WHERE login LIKE'%"+query+"%' OR mail LIKE '%"+query+"%';";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(q);
		while (rs.next())
		{
			res.put("Resultat "+i, rs.getString("login"));
			i++;
		}
		st.close();
		conn.close();
		return res;		
	}


	public static boolean checkFriends(int idFrom, int idTo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean res;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		String q = "SELECT * FROM Friends WHERE idfrom ="+idFrom+" AND idto= "+idTo+";";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(q);
		res = rs.next();
		st.close();
		conn.close();
		return res;		
	}


	public static boolean removeFriends(int i, int j) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		boolean res; 
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		String q = "DELETE FROM Friends WHERE idfrom = "+i+" AND idto ="+j+";" ;
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(q);
		if(rs == 1)
			res = true ;
		else res = false;
		st.close();
		conn.close();
		return res;	
	}


	public static JSONObject listFriends(String username) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		JSONObject res =new JSONObject() ;
		int i=0;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		String q = "SELECT * FROM Friends WHERE idfrom ="+User_BD.getID(username)+";";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(q);
		while (rs.next())
		{
			res.put(""+i, User_BD.getUser(rs.getInt("idto")));
			i++;
		}
		st.close();
		conn.close();
		return res;		
	}

}
