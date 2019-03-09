package tests;

import java.sql.SQLException;

import org.json.JSONException;

import bdd.User_BD;

public class TestUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username = "Dieu";
		String password = "deus1234";
		String newPassword = "deus1234";
		String newName = "Serpent";
		String newFirstName = "Dieu";
		String mail = "aztec@paradis.eden";
		String sexe = "U";
		
		try {
			System.out.println(services.user.Login.login(username, password));
			
			System.out.println(services.user.CreateUser.createUser(username, newPassword, newName, newFirstName, mail, sexe));
			//String key = User_BD.getKey(username);
			
			//System.out.println(User.setPassword(username, password, newPassword, key));
			//System.out.println(User.setName(username, newName, key));
			//System.out.println(User.setFirstName(username, newFirstName, key));
			
			System.out.println(services.user.Logout.logout(username,User_BD.getKey(username)));
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
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
