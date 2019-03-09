package tools;

import org.json.JSONException;
import org.json.JSONObject;

public enum ErrorCode {
	
	  OK (0, "Success"), 
	  
	  NULL_PARAMETERS (1, "NULL Parameter(s)"),
	  INVALID_PASSWORD (2, "Invalid Password"),
	  
	  DATABASE (100, "Database Error: Operation aborted"),
	  DATABASE_DELETE_KEY (101, "Database Error: Key not deleted"),
	  DATABASE_LOGIN (111, "Database Error: Session not updated"),
	  DATABASE_LOGOUT (112, "Database Error: Session not updated"),
	  DATABASE_CREATE_USER (113, "Database Error: User not created"),
	  DATABASE_DELETE_USER (114, "Database Error: User not deleted"),
	  DATABASE_ADD_FRIENDS (121, "Database Error: Friend not Added"),
	  DATABASE_REMOVE_FRIENDS (122, "Database Error: Friend not Removed"),
	  DATABASE_ADD_COMMENT (132, "Database Error: Comment not added"),
	  DATABASE_ADD_POST (133, "Database Error: Post not added"),
	  DATABASE_REMOVE_MESSAGE (134, "Database Error: Post not removed"),
	  DATABASE_LIKE (135, "Database Error: Like not Applied"),
	  
	  USER_LOGED (201, "Login failed: Already logged in"),
	  USER_NOT_LOGED (202, "Logout failed: Already logged out"),
	  USER_AUTH_FAILED (203, "Login failed: Authentification failed"),
	  USER_EXISTS (204, "Username already exists"),
	  MAIL_EXISTS (205, "Mail already exists"),
	  FRIENDS_ALREADY (211, "Users already Friends"),
	  NOT_FRIENDS (212, "Users not Friends"),
	  NO_SUCH_USER (213, "No Such User"),
	  NO_SUCH_MESSAGE (221, "No Such Message"), ;
	   
	  private int code = -1;
	  private String message = "";
	  
	  ErrorCode(int code, String message){
		  this.code = code;
		  this.message = message;
	  }
	   
	  public String toString() {
		  JSONObject error = toJSON();
		  return error.toString();
	  }
	  
	  public int getCode() {
		  return code;
	  }
	  
	  public String getMessage() {
		  return message;
	  }
	  
	  public JSONObject toJSON() {
		  JSONObject error = new JSONObject();
		  try {
			  error.append("error_code", code);
			  error.append("error_message", message);
		  } catch (JSONException e) {
			  e.printStackTrace();
		  }
		  return error;
	  }
}
