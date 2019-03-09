package services.user;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.User_BD;
import tools.ErrorClass;
import tools.ErrorCode;
import tools.toolUser;

public class Logout {
	
	public static JSONObject logout (String username, String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if (toolUser.checCo(username,key)) {
			if (User_BD.deleteKey(key)) {
				if (User_BD.logout(username, key)) {
					JSONObject o = new JSONObject();
					o.append("username", username);
					o.append("key", key);
					o.append("id", User_BD.getID(username));
					return ErrorClass.serviceAccepted(o);
				}
				return ErrorCode.DATABASE_LOGOUT.toJSON();
			}
			return ErrorCode.DATABASE_DELETE_KEY.toJSON();
		}
		return ErrorCode.USER_NOT_LOGED.toJSON() ;
	}
}
