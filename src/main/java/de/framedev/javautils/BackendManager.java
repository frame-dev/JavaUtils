package de.framedev.javautils;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName BackendManager
 * / Date: 06.06.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.InsertOneOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class BackendManager {

    private final MongoManager mongoManager;

    public BackendManager(MongoManager mongoManager) {
        this.mongoManager = mongoManager;
    }

    public MongoManager getMongoManager() {
        return mongoManager;
    }

    /**
     * Creating the Document of the User
     * @param where the Where
     * @param dataWhere the Data Where
     * @param collection Collection in the Database
     */
    public void createData(String where, Object dataWhere, HashMap<String, Object> data, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = this.mongoManager.getDatabase().getCollection(collection);
            Document result = collections.find(new Document(where, dataWhere)).first();
            if (result == null) {
                Document dc = (new Document(where, dataWhere));
                dc.putAll(data);
                collections.insertOne(dc, (new InsertOneOptions()).bypassDocumentValidation(false));
            }
        } else {
            mongoManager.getDatabase().createCollection(collection);
            MongoCollection<Document> collections = mongoManager.getDatabase().getCollection(collection);
            Document result = collections.find(new Document(where, dataWhere)).first();
            if (result == null) {
                Document dc = (new Document(where, dataWhere));
                dc.putAll(data);
                collections.insertOne(dc, (new InsertOneOptions()).bypassDocumentValidation(false));
            }
        }
    }

    /**
     *
     * @param where from the Database Document
     * @param data Data in where
     * @param selected the Selected key in your Database
     * @param collection the Collection in your Database
     * @return data from Database
     */
    public Object getObject(String where, Object data, String selected, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where,data)).first();
            if(document != null) {
                return document.get(selected);
            }
        }
        return null;
    }

    public void updateData(String where, Object data, String selected, Object dataSelected, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, data)).first();
            if (document != null) {
                Document document1 = new Document(selected, dataSelected);
                Document document2 = new Document("$set", document1);
                if(document.get(where) != null) {
                    collections.updateOne(document, document2);
                } else {
                    document.put(selected,dataSelected);
                    collections.updateOne(collections.find(new Document(where, data)).first(), document);
                }
            }
        } else {
            mongoManager.getDatabase().createCollection(collection);
            MongoCollection<Document> collections = mongoManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, data)).first();
            if (document != null) {
                Document document1 = new Document(selected, dataSelected);
                Document document2 = new Document("$set", document1);
                collections.updateOne(document, document2);
            }
        }
    }

    public void updateAll(String where, Object data, HashMap<String, Object> newData, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, data)).first();
            if (document != null) {
                if(document.get(where) != null) {
                    Document doc = Document.parse(new Gson().toJson(newData));
                    collections.replaceOne(document, doc);
                }
            }
        }
    }

    public boolean exists(String where, Object data, String whereSelected, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, data)).first();
            if (document != null) {
                return document.get(whereSelected) != null;
            }
        }
        return false;
    }

    public void insertData(String where, Object data, String newKey, Object newValue, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, data)).first();
            if (document != null) {
                collections.updateOne(new Document(where, data),
                        new Document("$set", new Document(newKey, newValue)));
            }
        }
    }

    public boolean existsCollection(String collection) {
        MongoCollection<Document> collections = mongoManager.getDatabase().getCollection(collection);
        return collections != null;
    }

    public List<Object> getList(String where, Object data, String selected, String collection) {
        ArrayList<Object> players = new ArrayList<>();
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoManager.getDatabase().getCollection(collection);
            collections.find(new Document(where,data)).forEach((Block<? super Document>) document -> {
                if (document != null) {
                    players.add(document.get(selected));
                }
            });
            return players;
        }
        return null;
    }

    public List<Document> getAllDocuments(String collection) {
        List<Document> list = new ArrayList<>();
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = this.mongoManager.getDatabase().getCollection(collection);
            FindIterable<Document> find = collections.find();
            Iterator it = find.iterator();
            while (it.hasNext()) {
                list.add((Document) it.next());
            }
        }
        return list;
    }
}
