package services.messages;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.Message_BD;
import bdd.User_BD;
import tools.ErrorClass;
import tools.ErrorCode;
import tools.toolUser;

public class ListFriendsMessages {
	
	public static JSONObject listFriendsMessages(String username, String key) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if(toolUser.checCo(username, key)) {
			JSONObject o = new JSONObject();
			o.append("username", username);
			o.append("key", key);
			o.append("id", User_BD.getID(username));
			o.append("list", Message_BD.listFriendsMessages(username));
			return ErrorClass.serviceAccepted(o);
		}
		return ErrorCode.USER_NOT_LOGED.toJSON();
	}

}
