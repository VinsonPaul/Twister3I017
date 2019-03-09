package tests;

import java.sql.SQLException;

import bdd.User_BD;
import services.user.Login;

public class TestMessages {
	
	public static void main(String[] args) {
		
		Boolean dispOnly = true;
		String user1 = "Dieu"; String pass1 = "deus1234";
		String user2 = "Satan"; String pass2 = "deus1234";
		String content = "Yo les pds";
		String datePost = "Thu Mar 07 16:57:42 CET 2019";
		String comment = "";
		Boolean post = false;
		Boolean like = false;
		Boolean remove = false;
		
		try {
			System.out.println(Login.login(user1, pass1));
			System.out.println(Login.login(user2, pass2));
			if (!dispOnly)
			{
				if (post && !(user1.equals("") || content.equals("")))
					services.messages.AddPost.addPost(user1, content, User_BD.getKey(user1));
				if (!(user1.equals("") || user2.equals("") ||content.equals("") || comment.equals("") || datePost.equals("")))
					services.messages.AddComment.addComment(user2, comment, user1, content, datePost ,User_BD.getKey(user2));
				if (like && !(user1.equals("") || user2.equals("") || content.equals("") || datePost.equals("")))
					services.messages.Like.like(user2, user1, content, datePost, "Post", User_BD.getKey(user2));
				if (remove && !(user1.equals("") || user2.equals("") || content.equals("") || datePost.equals("")))
					services.messages.RemovePost.removePost(user1, content, datePost, User_BD.getKey(user1));
			}
			//System.out.println(Message.searchMessage("Eve",User_BD.getKey("Eve"),"pomme","Adam"));
			/*System.out.println("Fil d'actualité de " + user2 + ":");
			Message.listeFriendsMessage(user2, User_BD.getKey(user2));
			
			System.out.println("Tous les messages:");*/
			services.messages.ListMessages.listMessages();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
