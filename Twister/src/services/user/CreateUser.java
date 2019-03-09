package services.user;

import java.sql.SQLException;

import org.json.JSONObject;

import bdd.User_BD;
import tools.ErrorClass;
import tools.ErrorCode;
import tools.toolUser;

public class CreateUser {
	
	public static JSONObject createUser(String username, String password, String name, String first_name, String mail, String sex) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, Exception {	
		if(!toolUser.userExists(username)) {
			if (toolUser.isValid(password)) {
				if (!toolUser.mailExists(mail)) {
					if (User_BD.createUser(username, password, name, first_name, mail, sex)) {
						JSONObject o = new JSONObject();
						o.append("username", username);
						o.append("name", name);
						o.append("first_name", first_name);
						o.append("sex", sex);
						o.append("mail", mail);
						o.append("id", User_BD.getID(username));
						return ErrorClass.serviceAccepted(o);
					}
					return ErrorCode.DATABASE_CREATE_USER.toJSON();
				}
				return ErrorCode.MAIL_EXISTS.toJSON();
			}
			return ErrorCode.INVALID_PASSWORD.toJSON();
		}
		return ErrorCode.USER_EXISTS.toJSON();
	}
}
