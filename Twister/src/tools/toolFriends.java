package tools;

import java.sql.SQLException;

import bdd.Friends_BD;

public class toolFriends {

	public static boolean checkFriends(int i, int j) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return Friends_BD.checkFriends( i,  j);
	}
	
	

}
