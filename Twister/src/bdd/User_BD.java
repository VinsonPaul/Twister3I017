package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.json.JSONException;


public class User_BD {
	
	public final static long EXPIRE_TIME = 18000000;
	
	public static boolean userExists(String login) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			boolean res;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
			String q = "SELECT * FROM Users WHERE login = \""+login+"\";";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(q);
			if(rs.next())
				res = true;
			else
				res = false;
			st.close();
			conn.close();
			return res;		
	}
	
	
	public static boolean createUser(String username, String password, String name, String firstName, String mail, String sexe) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean res;
		if (username == null)
			return false;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		
		
		String q = "INSERT INTO Users(login,password,nom,prenom,mail,sexe) "
				+ "values ('" + username +"','" + password  + "','" + name  + "','" + firstName  + "','"+mail + "','" + sexe +"');";
		
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(q);
		if(rs==1)
			res = true;
		else
			res = false;
		st.close();
		conn.close();
		if(res)
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
			
			q = "INSERT INTO Sessions(id) "
				+ "values  ("+getID(username)+");";
			st = conn.createStatement();
			rs = st.executeUpdate(q);
			if(rs==1)
				res = true;
			else
				res = false;
			st.close();
			conn.close();
		}
		return res;		
	}


	public static boolean mailExists(String mail) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean res;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		String q = "SELECT * FROM Users WHERE mail = \""+mail+"\";";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(q);
		if(rs.next())
			res = true;
		else
			res = false;
		st.close();
		conn.close();
		return res;		
	}

	public static boolean login (String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		boolean res; 
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		
		Date date =new Date();
		String var=""+date.getTime();  
		String q = "UPDATE Sessions SET clef = '"+tools.toolUser.createKey() + "', start = '" +  var
				+ "' WHERE id= "+getID(username)+";" ;
		
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(q);
		if(rs == 1)
			res = true;
		else
			res = false;		
		st.close();
		conn.close();
		setTimeout(getID(username));
		return res;		
	}
	
	
	public static void setTimeout(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		Date date =new Date();
		long update = date.getTime()+EXPIRE_TIME;
		String var=""+update;
		String q = "UPDATE Sessions SET finish = \"" + var + "\" WHERE id = " + id + ";";
		Statement st = conn.createStatement();
		st.executeUpdate(q);
		st.close();
		conn.close();
	}
	
	public static boolean checkTimeout(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		String q = "SELECT * FROM Sessions WHERE id = \""+id+"\";";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(q);
		rs.next();
		String finish = rs.getString("finish");
		long fin = Long.parseLong(finish);
		Date date = new Date();
		if ( fin - date.getTime() >=0 )
		{
			setTimeout(id);
			return true;
		}
		deleteKey(getKey(getUser(id)));
		logout(getUser(id), getKey(getUser(id)));
		st.close();
		conn.close();
		return false;
	}

	public static boolean logout (String username, String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		boolean res; 
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		
		String q = "UPDATE Sessions SET clef = null WHERE id= "+getID(username)+" AND clef ='"+key+"';" ;
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(q);
		if(rs==1)
			res = true;
		else
			res = false;
		st.close();
		conn.close();
		return res;		
	}


	public static boolean deleteUser(String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean res; 
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		String q = "DELETE FROM Users WHERE login = '"+username+"' AND password ='"+password+"';" ;
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(q);
		if(rs == 1)
			res = true ;
		else res = false;
		st.close();
		conn.close();
		return res;	
	}
	
	
	public static int getID (String username) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		Statement st = conn.createStatement();
		String q = "SELECT * FROM Users WHERE login = \""+username+"\";";
		ResultSet rs = st.executeQuery(q);
		rs.next();
		int res = rs.getInt("id");
				
		st.close();
		conn.close();		
		return res ;
		
	}
	
	public static String getKey(String username) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		
		String res="";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		Statement st = conn.createStatement();
		String q = "SELECT s.* FROM Users u, Sessions s WHERE u.login= \""+username+"\"AND s.id = u.id ;";
		ResultSet rs = st.executeQuery(q);
		if (rs.next())
			res= rs.getString("clef");
		else 
			res = null;
		st.close();
		conn.close();
		return res;
	}
	

	public static boolean checkConnexion (String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		Statement st = conn.createStatement();
		String q = "SELECT * FROM Users WHERE login = \""+username+"\"AND password ='"+password+"' ;";
		ResultSet rs = st.executeQuery(q);
		boolean res=rs.next();
		st.close();
		conn.close();
		return res;
		
	}


	public static boolean deleteKey(String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		boolean res;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		
		
		String q = "INSERT INTO DeadKeys(clef) "
				+ "values ('" + key+"');";
		
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(q);
		if(rs==1)
			res = true;
		else
			res = false;
		st.close();
		conn.close();
		return res;
	}


	public static boolean checkClefMorte(String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		Statement st = conn.createStatement();
		String q = "SELECT * FROM DeadKeys WHERE clef = '"+key+"' ;";
		ResultSet rs = st.executeQuery(q);
		boolean res=rs.next();
		st.close();
		conn.close();
		return res;
	}


	public static String getUser(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		String res="";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		Statement st = conn.createStatement();
		String q = "SELECT * FROM Users WHERE id= "+id+";" ;
		ResultSet rs = st.executeQuery(q);
		if (rs.next())
			res= rs.getString("login");
		else 
			res = null;
		st.close();
		conn.close();
		return res;
	}

	public static boolean setPassword(String username, String newPassword) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		Statement st = conn.createStatement();
		String q = "UPDATE Users SET password = '" + newPassword + "' WHERE id = " + getID(username) + ";";
		int rs = st.executeUpdate(q);
		st.close();
		conn.close();
		return rs != 0;
	}
	
	public static boolean setName(String username, String newName) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		Statement st = conn.createStatement();
		String q = "UPDATE Users SET nom = '" + newName + "' WHERE id = " + getID(username) +  ";";
		int rs = st.executeUpdate(q);
		st.close();
		conn.close();
		return rs != 0;
	}
	
	public static boolean setFirstName(String username, String newFirstName) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host+"/"+DBStatic.mysql_db,DBStatic.mysql_username,DBStatic.mysql_password);
		Statement st = conn.createStatement();
		String q = "UPDATE Users SET prenom = '" + newFirstName + "' WHERE id = " + getID(username) +  ";";
		int rs = st.executeUpdate(q);
		st.close();
		conn.close();
		return rs != 0;
	}
	
}
