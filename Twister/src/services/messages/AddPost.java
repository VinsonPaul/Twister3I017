package services.messages;

import java.util.Date;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.Message_BD;
import bdd.User_BD;
import tools.ErrorClass;
import tools.ErrorCode;
import tools.toolUser;

public class AddPost {
	
	public static JSONObject addPost (String username, String content, String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if (toolUser.checCo(username, key)) {
			Date d = Message_BD.addPost(content,username);
			if(d != null) {
				JSONObject o = new JSONObject();
				o.append("username", username);
				o.append("key", key);
				o.append("content", content);
				o.append("date", d);
				o.append("id", User_BD.getID(username));
				return ErrorClass.serviceAccepted(o);
			}
			return ErrorCode.DATABASE_ADD_POST.toJSON();
		}
		return ErrorCode.USER_NOT_LOGED.toJSON();
	}

}
