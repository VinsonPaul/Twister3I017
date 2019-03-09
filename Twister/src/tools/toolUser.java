package tools;

import java.sql.SQLException;
import java.util.Random;

import org.json.JSONException;

import bdd.User_BD;

public class toolUser {
	
	public static boolean isValid(String password) {
		return (password.length()>7);
	}
	
	public static boolean checkPassword (String username,String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException//throws SQLQueryException
	{
		//check sql 
		return User_BD.checkConnexion(username, password) ;
	}
	
	public static boolean userExists(String username) throws Exception {
		if (username == null) return false;
		return User_BD.userExists(username);
	}
	
	public static boolean mailExists(String mail) throws Exception {
		if (mail == null ) return true ;
		return User_BD.mailExists(mail);
	}
	
	public static boolean checkNameFirstName (String name,String firstname)
	{
		if(name == null || firstname == null  )
			return false ;
		return true ;
	}
	
	public static String createKey () throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		String res = "";
		int rdm = 0;
        Random randomizer=new Random(); 
		do {
			res += ((rdm = randomizer.nextInt()) < 0 ? -rdm : rdm);
		} while (User_BD.checkClefMorte(res));
		return res;
	}

	public static boolean connexion(String username, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return User_BD.checkConnexion(username, password) ;
	}

	public static boolean checCo(String username, String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if (key == null)
			return User_BD.getKey(username) != null;
		if (!User_BD.checkClefMorte(key)  )
			if(checkTimeout(User_BD.getID(username)))
				return key.equals(User_BD.getKey(username)) ;
		return false;
	}
	
	private static boolean checkTimeout(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException
	{
		return User_BD.checkTimeout(id);
	}


}
