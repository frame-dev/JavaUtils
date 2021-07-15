
package de.framedev.javautils;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName MongoManager
 * / Date: 06.06.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */
public class MongoManager {

    private final String hostname;
    private final String username;
    private final String password;
    private final int port;
    private final String dataBaseString;
    private MongoClient client;
    private MongoDatabase database;

    public MongoManager(String hostname, String username, String password, int port, String dataBaseString) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.port = port;
        this.dataBaseString = dataBaseString;
    }

    public MongoManager(String hostname, String username, String password, String dataBaseString) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.port = 27017;
        this.dataBaseString = dataBaseString;
    }

    public MongoManager(String hostname, String dataBaseString) {
        this.hostname = hostname;
        this.port = 27017;
        this.dataBaseString = dataBaseString;
        this.username = "";
        this.password = "";
    }

    public MongoManager(String hostname, int port, String dataBaseString) {
        this.hostname = hostname;
        this.port = port;
        this.username = "";
        this.password = "";
        this.dataBaseString = dataBaseString;
    }

    public String getHostname() {
        return hostname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getDataBaseString() {
        return dataBaseString;
    }

    public void connectLocalHost() {
        this.client = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Collections.singletonList(new ServerAddress(hostname, port))))
                        .build());
        this.database = this.client.getDatabase(dataBaseString);
        APIs.utils.getLogger().log(Level.INFO,"Connected via LocalHost");
    }

    public void connect() {
        MongoCredential credential = MongoCredential.createCredential(username, dataBaseString, password.toCharArray());
        this.client = MongoClients.create(
                MongoClientSettings.builder()
                        .credential(credential)
                        .applyToClusterSettings(builder ->
                                builder.hosts(Collections.singletonList(new ServerAddress(hostname, port))))
                        .build());
        this.database = this.client.getDatabase(dataBaseString);
        APIs.utils.getLogger().log(Level.INFO,"Connected");
    }


    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}

