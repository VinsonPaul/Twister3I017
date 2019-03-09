package services.user;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.User_BD;
import tools.ErrorClass;
import tools.ErrorCode;
import tools.toolUser;

public class SetPassword {
	
	public static JSONObject setPassword(String username, String password, String new_password, String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if (toolUser.checCo(username,key)) {
			if (toolUser.checkPassword(username, password)) {
				if  (User_BD.setPassword(username, new_password)) {
					JSONObject o = new JSONObject();
					o.append("username", username);
					o.append("key", key);
					o.append("id", User_BD.getID(username));
					return ErrorClass.serviceAccepted(o);
				}
				return ErrorCode.DATABASE.toJSON();
			}
			return ErrorCode.INVALID_PASSWORD.toJSON();
		}
		return ErrorCode.USER_NOT_LOGED.toJSON();
	}
}
