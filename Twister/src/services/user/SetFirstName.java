package services.user;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.User_BD;
import tools.ErrorClass;
import tools.ErrorCode;
import tools.toolUser;

public class SetFirstName {
	
	public static JSONObject setFirstName(String username, String new_name, String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if (toolUser.checCo(username,key)) {
			if  (User_BD.setFirstName(username, new_name)) {
				JSONObject o = new JSONObject();
				o.append("username", username);
				o.append("key", key);
				o.append("id", User_BD.getID(username));
				return ErrorClass.serviceAccepted(o);
			}
			return ErrorCode.DATABASE.toJSON();
		}
		return ErrorCode.USER_NOT_LOGED.toJSON();
	}
}
