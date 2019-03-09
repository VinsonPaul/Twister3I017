package services.friends;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.Friends_BD;
import bdd.User_BD;
import tools.ErrorClass;
import tools.ErrorCode;
import tools.toolFriends;
import tools.toolUser;

public class AddFriends {
	
	public static JSONObject addFriends(String username_from, String username_to, String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if (toolUser.checCo(username_from,key)) {
			if (!toolFriends.checkFriends(User_BD.getID(username_from),User_BD.getID(username_to))) {
					if(Friends_BD.addFriends(User_BD.getID(username_from), User_BD.getID(username_to))) {
						JSONObject o = new JSONObject();
						o.append("username_from", username_from);
						o.append("username_to", username_to);
						o.append("key", key);
						return ErrorClass.serviceAccepted(o);
					}	
					return ErrorCode.DATABASE_ADD_FRIENDS.toJSON();
			}
			return ErrorCode.FRIENDS_ALREADY.toJSON();
		}
		return ErrorCode.USER_NOT_LOGED.toJSON();
	}
}
