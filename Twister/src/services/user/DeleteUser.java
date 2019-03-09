package services.user;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.User_BD;
import tools.ErrorClass;
import tools.ErrorCode;
import tools.toolUser;

public class DeleteUser {

	public static JSONObject deleteUser (String username, String password, String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if (toolUser.checCo(username,key)) {
			if  (User_BD.deleteKey(key)) {
				if (User_BD.deleteUser(username,password)) {
					JSONObject o = new JSONObject();
					o.append("username", username);
					o.append("key", key);
					o.append("id", User_BD.getID(username));
					return ErrorClass.serviceAccepted(o);
				}
				return ErrorCode.DATABASE_DELETE_USER.toJSON();
			}
			return ErrorCode.DATABASE_DELETE_KEY.toJSON();
		}
		return ErrorCode.USER_NOT_LOGED.toJSON();
	}
}
