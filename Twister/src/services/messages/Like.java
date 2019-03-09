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

public class Like {
	
	public static JSONObject like(String username, String username_to, String content_to, String date_to, String type, String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		if (toolUser.checCo(username,key)) {		
			if(toolMessage.messageExist(username_to, content_to, date_to)) {
				if(Message_BD.likePost(username,username_to,content_to,date_to)) {
					JSONObject o = new JSONObject();
					o.append("username", username);
					o.append("key", key);
					o.append("username_to", username_to);
					o.append("content_to", content_to);
					o.append("date_to", date_to);
					o.append("id", User_BD.getID(username));
					o.append("id_to", User_BD.getID(username_to));
					return ErrorClass.serviceAccepted(o);
				}
				return ErrorCode.DATABASE_LIKE.toJSON();
			}
			return ErrorCode.NO_SUCH_MESSAGE.toJSON();	
		}
		return ErrorCode.USER_NOT_LOGED.toJSON();
	}

}
