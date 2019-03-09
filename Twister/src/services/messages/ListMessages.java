package services.messages;

import org.json.JSONException;
import org.json.JSONObject;

import bdd.Message_BD;
import tools.ErrorClass;

public class ListMessages {
	
	public static JSONObject listMessages() throws JSONException
	{
		JSONObject res = Message_BD.listMessages();
		return ErrorClass.serviceAccepted(res);
	}
	

}
