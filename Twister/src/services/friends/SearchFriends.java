package services.friends;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.Friends_BD;
import tools.ErrorClass;
import tools.ErrorCode;
import tools.toolUser;

public class SearchFriends {
	
	public static JSONObject searchFriends(String username, String query, String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if (toolUser.checCo(username,key)) 
			return ErrorClass.serviceAccepted(Friends_BD.searchUsers(query));
		return ErrorCode.USER_NOT_LOGED.toJSON();
	}
}
