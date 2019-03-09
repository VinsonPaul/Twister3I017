package tests;

import java.sql.SQLException;

import org.json.JSONException;

import bdd.User_BD;
import services.friends.*;
import services.user.Login;

public class TestFriends {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String userFrom = "Eve"; String passFrom = "jardindeden";
		String userTo = "Satan"; String passTo = "deus1234";
		Boolean add = true; //true to add Friends, false to remove
		Boolean reci = false; //true = dans les deux sens
		
		try {
			System.out.println(Login.login(userFrom, passFrom));
			System.out.println(Login.login(userTo, passTo));
			
			if(add)
			{
				System.out.println(AddFriends.addFriends(userFrom,userTo, User_BD.getKey(userFrom)));
				if (reci)
					System.out.println(AddFriends.addFriends(userTo,userFrom, User_BD.getKey(userTo)));
			}
			else
			{
				System.out.println(RemoveFriends.removeFriends(userFrom, userTo, User_BD.getKey(userFrom)));
				if (reci)
					System.out.println(RemoveFriends.removeFriends(userTo, userFrom, User_BD.getKey(userTo)));
			}
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
