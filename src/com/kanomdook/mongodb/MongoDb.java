package com.kanomdook.mongodb;

//import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
//import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDb {

	public static void main(String[] args) {
		connection();
	}

	public static void connection() {
		try {
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoDatabase database = mongoClient.getDatabase("testdb");
			MongoCollection<Document> collection = database.getCollection("user");
//			Document doc = new Document("name", "MongoDB").append("type", "database").append("count", 1)
//					.append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
//					.append("info", new Document("x", 203).append("y", 102));
//			collection.insertOne(doc);
//			Document myDoc = collection.find().first();
//			System.out.println(myDoc.toJson());
//			System.out.println(collection.countDocuments());
			MongoCursor<Document> cursor = collection.find().iterator();
			while (cursor.hasNext()) {
				System.out.println(cursor.next().toJson());
			}
			cursor.close();
			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
