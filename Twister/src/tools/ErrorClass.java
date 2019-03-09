package tools;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorClass {
	
	public ErrorClass() {};
	
	public static JSONObject refuse(ErrorCode e)
	{
		return e.toJSON();
	}
	public static JSONObject serviceAccepted(JSONObject o) throws JSONException
	{
		JSONObject res = new JSONObject();
		@SuppressWarnings("unchecked")
		Iterator<String> it = o.keys();
		String key;
		while (it.hasNext())
		{
			key = it.next();
			res.append(key, o.get(key));
			res.append("error_message", ErrorCode.OK.getMessage());
			res.append("error_code", ErrorCode.OK.getCode());
		}
		return res;
	}
}
