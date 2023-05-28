package de.framedev.javautils;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.InsertOneOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
@SuppressWarnings("unused")
public class BackendMongoDBManager {

    private final MongoDBManager mongoDBManager;

    public BackendMongoDBManager(MongoDBManager mongoDBManager) {
        this.mongoDBManager = mongoDBManager;
    }

    public MongoDBManager getMongoManager() {
        return mongoDBManager;
    }

    /**
     * Creating the Document of the User
     *
     * @param where      the Where
     * @param dataWhere  the Data Where
     * @param collection Collection in the Database
     */
    public void createData(String where, Object dataWhere, HashMap<String, Object> data, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = this.mongoDBManager.getDatabase().getCollection(collection);
            Document result = collections.find(new Document(where, dataWhere)).first();
            if (result == null) {
                Document dc = (new Document(where, dataWhere));
                dc.putAll(data);
                collections.insertOne(dc, (new InsertOneOptions()).bypassDocumentValidation(false));
            }
        } else {
            mongoDBManager.getDatabase().createCollection(collection);
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            Document result = collections.find(new Document(where, dataWhere)).first();
            if (result == null) {
                Document dc = (new Document(where, dataWhere));
                dc.putAll(data);
                collections.insertOne(dc, (new InsertOneOptions()).bypassDocumentValidation(false));
            }
        }
    }

    public void createData(String where, Object dataWhere, Object object, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = this.mongoDBManager.getDatabase().getCollection(collection);
            Document result = collections.find(new Document(where, dataWhere)).first();
            if (result == null) {
                Document document = Document.parse(new Utils().objectToJsonString(object));
                collections.insertOne(document, (new InsertOneOptions()).bypassDocumentValidation(false));
            }
        } else {
            mongoDBManager.getDatabase().createCollection(collection);
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            Document result = collections.find(new Document(where, dataWhere)).first();
            if (result == null) {
                Document document = Document.parse(new Utils().objectToJsonString(object));
                collections.insertOne(document, (new InsertOneOptions()).bypassDocumentValidation(false));
            }
        }
    }

    /**
     * @param where      from the Database Document
     * @param data       Data in where
     * @param selected   the Selected key in your Database
     * @param collection the Collection in your Database
     * @return data from Database
     */
    public Object getObject(String where, Object data, String selected, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, data)).first();
            if (document != null) {
                return document.get(selected);
            }
        }
        return null;
    }

    public Document getDocument(String where, Object data, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            return collections.find(new Document(where, data)).first();
        }
        return null;
    }

    public void updateData(String where, Object data, String selected, Object dataSelected, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, data)).first();
            if (document != null) {
                Document document1 = new Document(selected, dataSelected);
                Document document2 = new Document("$set", document1);
                if (document.get(where) != null) {
                    collections.updateOne(document, document2);
                } else {
                    document.put(selected, dataSelected);
                    collections.updateOne(Objects.requireNonNull(collections.find(new Document(where, data)).first()), document);
                }
            }
        } else {
            mongoDBManager.getDatabase().createCollection(collection);
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, data)).first();
            if (document != null) {
                Document document1 = new Document(selected, dataSelected);
                Document document2 = new Document("$set", document1);
                collections.updateOne(document, document2);
            }
        }
    }

    public void updateAll(String where, Object whereData, HashMap<String, Object> newData, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, whereData)).first();
            if (document != null) {
                if (document.get(where) != null) {
                    Document doc = Document.parse(new Gson().toJson(newData));
                    collections.replaceOne(document, doc);
                }
            }
        }
    }

    public void updateAll(String where, Object whereData, Object object, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, whereData)).first();
            if (document != null) {
                if (document.get(where) != null) {
                    Document doc = Document.parse(new Gson().toJson(object));
                    collections.replaceOne(document, doc);
                }
            }
        }
    }

    public boolean exists(String where, Object data, String whereSelected, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, data)).first();
            if (document != null) {
                return document.get(whereSelected) != null;
            }
        }
        return false;
    }

    public boolean exists(String where, Object data, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, data)).first();
            return document != null;
        }
        return false;
    }

    public boolean exists(String where, Object data, String whereSelected, Object whereData, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, data)).first();
            if (document != null) {
                return document.get(whereSelected) == whereData;
            }
        }
        return false;
    }

    public void insertData(String where, Object data, String newKey, Object newValue, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, data)).first();
            if (document != null) {
                collections.updateOne(new Document(where, data),
                        new Document("$set", new Document(newKey, newValue)));
            }
        }
    }

    @SuppressWarnings("ConstantValue")
    public boolean existsCollection(String collection) {
        MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
        return collections != null;
    }

    public List<Object> getList(String where, Object data, String selected, String collection) {
        ArrayList<Object> players = new ArrayList<>();
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = mongoDBManager.getDatabase().getCollection(collection);
            collections.find(new Document(where, data)).forEach((Consumer<? super Document>) document -> {
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
            MongoCollection<Document> collections = this.mongoDBManager.getDatabase().getCollection(collection);
            FindIterable<Document> find = collections.find();
            for (Document document : find) {
                list.add(document);
            }
        }
        return list;
    }

    public <T> T getObjectFromJson(String where, String whereData, String collection, Class<T> class__) {
        T t = null;
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = this.mongoDBManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, whereData)).first();
            if (document != null) {
                String json = document.toJson();
                t = new Utils().classFromJsonString(json, class__);
            }
        }
        return t;
    }

    public void removeDocument(String where, String whereData, String collection) {
        if (existsCollection(collection)) {
            MongoCollection<Document> collections = this.mongoDBManager.getDatabase().getCollection(collection);
            Document document = collections.find(new Document(where, whereData)).first();
            if (document != null)
                collections.deleteMany(document);
        }
    }
}
