package de.framedev.javautils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import junit.framework.TestCase;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import java.util.List;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName TimeTest
 * / Date: 26.07.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */
public class TimeTest extends TestCase {

    public void testName() {
        MongoDBManager mongoDBManager = new MongoDBManager("192.168.8.20", "server", "Inside71", "server");
        mongoDBManager.connect();
        BackendMongoDBManager backendManager = new BackendMongoDBManager(mongoDBManager);
        JsonWriterSettings settings = JsonWriterSettings.builder().indent(true).build();
        List<Document> documents = backendManager.getAllDocuments("Test");
        for (Document document : documents) {
            if (document.get("money") != null && document.get("money").equals(2000)) {
                MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection("Test");
                collections.updateOne(Filters.eq("uuid", document.get("uuid")), new Document("$set", new Document("money", 12.0)));
            }
            System.out.println(document.toJson(settings));
            if (document.get("osType") != null && document.get("osType").equals("MACOS")) {
                SystemUtils.OSType osType = SystemUtils.OSType.valueOf(document.getString("osType"));
                System.out.println(osType);
            }
        }
    }
}