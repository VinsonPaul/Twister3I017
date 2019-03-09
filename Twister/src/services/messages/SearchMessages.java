package services.messages;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.Message_BD;
import bdd.User_BD;
import tools.ErrorClass;
import tools.ErrorCode;
import tools.toolFriends;
import tools.toolUser;

public class SearchMessages {
	
	public static JSONObject searchMessages(String username, String key, String query, String friends) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if(toolUser.checCo(username, key)) {
			if(toolFriends.checkFriends(User_BD.getID(username), User_BD.getID(friends))) {
				return ErrorClass.serviceAccepted(Message_BD.searchMessage(friends,query));
			}
			return ErrorCode.NOT_FRIENDS.toJSON();
		}
		return ErrorCode.USER_NOT_LOGED.toJSON();
	}

}
