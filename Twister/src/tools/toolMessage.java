package tools;

import bdd.Message_BD;

public class toolMessage {

	public static boolean messageExist(String username, String text, String date) {
		if(username!=null && text !=null && date != null )
			return Message_BD.messageExist(username,text,date);
		return false;
	}

}
