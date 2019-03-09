package services.friends;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.Friends_BD;
import tools.ErrorClass;
import tools.ErrorCode;
import tools.toolUser;

public class ListFriends {

	public static JSONObject  listFriends(String username) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException, Exception {
		if (toolUser.userExists(username))
			return ErrorClass.serviceAccepted(Friends_BD.listFriends(username));
		return ErrorCode.NO_SUCH_USER.toJSON();
	}
}
