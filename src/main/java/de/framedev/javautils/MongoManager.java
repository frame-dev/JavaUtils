/*
 * #Copyright (c) by FrameDev#
 * #Dies ist ein Project von FrameDev Bitte verändere nichts!#
 *
 */
package de.framedev.javautils;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

public class MongoManager {

    private String hostname;
    private String username;
    private String password;
    private int port;
    private String databasestring;
    private MongoClient client;
    private MongoDatabase database;

    public MongoManager(String hostname, String username, String password, int port, String databasestring, MongoClient client, MongoDatabase database) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.port = port;
        this.databasestring = databasestring;
        this.client = client;
        this.database = database;
    }

    public void connectLocalHost() {
        this.client = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress(hostname, port))))
                        .build());
        this.database = this.client.getDatabase(databasestring);
    }

    public void connect() {
        MongoCredential credential = MongoCredential.createCredential(username, databasestring, password.toCharArray());
        this.client = MongoClients.create(
                MongoClientSettings.builder()
                        .credential(credential)
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress(hostname, port))))
                        .build());
        this.database = this.client.getDatabase(databasestring);
    }


    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}

