package services.messages;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.Message_BD;
import bdd.User_BD;
import tools.ErrorClass;
import tools.ErrorCode;
import tools.toolMessage;
import tools.toolUser;

public class RemovePost {

	public static JSONObject removePost (String username, String content, String date, String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if (toolUser.checCo(username,key)) {
			if(toolMessage.messageExist(username,content,date)) {
				if (Message_BD.removeMessage(username, content, date, "Post")) {
					JSONObject o = new JSONObject();
					o.append("username", username);
					o.append("key", key);
					o.append("content", content);
					o.append("date", date);
					o.append("id", User_BD.getID(username));
					return ErrorClass.serviceAccepted(o);
				}
				return ErrorCode.DATABASE_REMOVE_MESSAGE.toJSON();
			}
			return ErrorCode.NO_SUCH_MESSAGE.toJSON();
		}
		return ErrorCode.USER_NOT_LOGED.toJSON();
	}
	
}
