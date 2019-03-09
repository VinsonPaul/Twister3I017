package bdd;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Message_BD {

	
	@SuppressWarnings("unchecked")
	public static Date addComment(String username, String content, String usernameTo, String contentTo, String dateTo) {
		
		MongoClient mongo=MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db =mongo.getDatabase("PADYDB");
		MongoCollection<Document> col=db.getCollection("Messages");
		Document q = new Document();
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date d = calendar.getTime();
		String s = d.toString();
		q.append("date",s);
		q.append("content",content);
		q.append("username",username);
		q.append("type", "Comment");
		q.append("likes", new ArrayList<Document>());
		//col.insertOne(q);
		
		Document p = new Document();
		p.append("date",dateTo);
		p.append("content", contentTo);
		p.append("username", usernameTo);
		MongoCursor <Document> cursor = col.find(p).iterator();
		if(cursor.hasNext())
		{
			Document o = cursor.next();
			ArrayList<Document> newcoms = (ArrayList<Document>)o.get("comments");
			newcoms.add(q);
			o.append("comments",newcoms);
			col.replaceOne(p, o);
			System.out.println(o);
			return d;
		}
		return null;
	}

	public static Date addPost(String content, String username) throws JSONException {
		
		MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db = mongo.getDatabase("PADYDB");
		MongoCollection<Document> col = db.getCollection("Messages");
		Document q = new Document();
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date d = calendar.getTime();
		String s = d.toString();
		q.append("date",s);
		q.append("content",content);
		q.append("username",username.toString());
		q.append("type", "Post");
		q.append("likes", new ArrayList<Document>());
		q.append("comments", new ArrayList<Document>());
		System.out.println(q);
		col.insertOne(q);
		return d;
	}
	
	public static JSONObject listMessages() throws JSONException {
		MongoClient mongo=MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db =mongo.getDatabase("PADYDB");
		MongoCollection<Document> col=db.getCollection("Messages");
		MongoCursor <Document> cursor=col.find().iterator();
		JSONObject res = new JSONObject();
		JSONObject msg = new JSONObject();
		Object user, text, date, likes;
		int i = 0;
		Document o=null;
		while(cursor.hasNext()) {
			o=cursor.next();
			user = o.get("username");
			text = o.get("content");
			date = o.get("date");
			likes = o.get("likes");
			msg.append("username", user);
			msg.append("content", text);
			msg.append("date", date);
			msg.append("likes", likes);
			res.append("" + i, msg);
			i++;
			msg = new JSONObject();
			System.out.println(o);
		
		}
		
		return res;
	}

	public static boolean messageExist(String username, String content, String date) {
		MongoClient mongo=MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db =mongo.getDatabase("PADYDB");
		MongoCollection<Document> col=db.getCollection("Messages");
		Document q=new Document();
		q.append("date",date);
		q.append("content", content);
		q.append("username",username);
		MongoCursor <Document> cursor=col.find(q).iterator();
		if(cursor.hasNext())
			return true ;
		System.out.println("false");
		return false;
	}
	
	public static boolean removeMessage(String username, String content, String date, String type) {
		MongoClient mongo=MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db =mongo.getDatabase("PADYDB");
		MongoCollection<Document> col=db.getCollection("Messages");
		Document q=new Document();
		q.append("date",date);
		q.append("content", content);
		q.append("username", username);
		q.append("type", type);
		MongoCursor <Document> cursor=col.find(q).iterator();
		if(cursor.hasNext())
		{
			col.deleteOne(q);
			return true ;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean likePost(String username, String usernameTo, String content, String date) {
		MongoClient mongo=MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db =mongo.getDatabase("PADYDB");
		MongoCollection<Document> col=db.getCollection("Messages");
		Document q = new Document();
		q.append("date",date);
		q.append("content", content);
		q.append("username",usernameTo);
		MongoCursor <Document> cursor=col.find(q).iterator();
		if(cursor.hasNext())
		{
			Document o=cursor.next();
			ArrayList<String> newlikes = (ArrayList<String>)o.get("likes");
			if(newlikes!=null)
			{
				if (!newlikes.isEmpty() && newlikes.contains(username))
					newlikes.remove(username);
				else
					newlikes.add(username);
			}
			o.remove("likes");
			o.append("likes",newlikes);
			col.replaceOne(q, o);
			
			//System.out.println(o);
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean likeComment(String username, String usernamecom, String textcom, String datecom, String usernamepost, String textpost, String datepost) {
		MongoClient mongo=MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db =mongo.getDatabase("PADYDB");
		MongoCollection<Document> col=db.getCollection("Messages");
		Document q = new Document();
		q.append("date",datepost);
		q.append("content", textpost);
		q.append("username", usernamepost);
		MongoCursor <Document> cursor=col.find(q).iterator();
		if(cursor.hasNext())
		{
			Document post = cursor.next();
			ArrayList<Document> newcomments = (ArrayList<Document>) post.get("comments");
			for (Document doc : newcomments)
				if (doc.get("username").equals(usernamecom) && doc.get("text").equals(textcom) && doc.get("date").equals(datecom))
				{
					ArrayList<String> newlikes = (ArrayList<String>)doc.get("likes");
					if(newlikes.contains(username))
						newlikes.remove(username);
					else
						newlikes.add(username);
				//o.remove("Like");
					doc.append("likes",newlikes);
					newcomments.remove(doc);
					newcomments.add(doc);
					break;
				}
			q.append("comments",newcomments);
			return true;
		}
		return false;
	}

	public static JSONObject searchMessage(String friend,String query) throws JSONException {
		MongoClient mongo=MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db =mongo.getDatabase("PADYDB");
		MongoCollection<Document> col=db.getCollection("Messages");
		Document q = new Document();
		Document reg = new Document();
		reg.append("$regex", ".*" + query + ".*");
		q.append("content",reg);
		MongoCursor <Document> cursor = col.find(q).iterator();
		JSONObject res = new JSONObject();
		JSONObject msg = new JSONObject();
		Object user, text, date, likes;
		int i = 1;
		Document o=null;
		while(cursor.hasNext()) {
			o=cursor.next();
			user = o.get("username");
			text = o.get("content");
			date = o.get("date");
			likes = o.get("likes");
			msg.append("username", user);
			msg.append("content", text);
			msg.append("date", date);
			msg.append("likes", likes);
			res.append("" + i, msg);
			i++;
			msg = new JSONObject();
			System.out.println(o);
		
		}
		
		return res;
	}

	public static JSONObject listFriendsMessages(String username) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		JSONObject msgs = new JSONObject();
		JSONObject friends = Friends_BD.listFriends(username);
		String friend;
		int i = 0;
		while (friends.has("" + i)) {
			friend = (String) friends.get("" + i);
			msgs.append(friend, listeMessageFriend(friend));
			i++;
		}
		return msgs;
	}
	
	public static JSONObject listeMessageFriend(String username) throws JSONException {
		MongoClient mongo=MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db =mongo.getDatabase("PADYDB");
		MongoCollection<Document> col=db.getCollection("Messages");
		Document q= new Document();
		q.append("username", username);
		MongoCursor <Document> cursor=col.find(q).iterator();
		JSONObject res = new JSONObject();
		JSONObject msg;
		Object user, text, date, likes;
		int i = 0;
		Document o=null;
		while(cursor.hasNext()) {
			msg = new JSONObject();
			o=cursor.next();
			user = o.get("username");
			text = o.get("content");
			date = o.get("date");
			likes = o.get("likes");
			msg.append("username", user);
			msg.append("content", text);
			msg.append("date", date);
			msg.append("likes", likes);
			res.append("" + i, msg);
			i++;
			System.out.println(o);
		}
		return res;
	}
}
	

