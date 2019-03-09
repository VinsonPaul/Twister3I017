package services.user;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.User_BD;
import tools.ErrorCode;
import tools.ErrorClass;
import tools.toolUser;

public class Login {

	public static JSONObject login (String username,String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException, SQLException, Exception  {
		if (username != null && password != null) {
			if (toolUser.connexion(username,password)) {
				if (!toolUser.checCo(username,null)) {
					if  (User_BD.login(username, password)) {
						JSONObject o = new JSONObject();
						o.append("username", username);
						o.append("key", User_BD.getKey(username));
						o.append("id", User_BD.getID(username));
						return ErrorClass.serviceAccepted(o);
					}
					return ErrorCode.DATABASE_LOGIN.toJSON();
				}
				return ErrorCode.USER_LOGED.toJSON();
			}
			return ErrorCode.USER_AUTH_FAILED.toJSON();
		}
		return ErrorCode.NULL_PARAMETERS.toJSON();
	}
	
}
