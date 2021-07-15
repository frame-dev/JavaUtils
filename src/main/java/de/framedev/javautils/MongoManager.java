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

    private final String hostname;
    private final String username;
    private final String password;
    private final int port;
    private final String dataBaseString;
    private MongoClient client;
    private MongoDatabase database;

    public MongoManager(String hostname, String username, String password, int port, String dataBaseString, MongoClient client, MongoDatabase database) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.port = port;
        this.dataBaseString = dataBaseString;
        this.client = client;
        this.database = database;
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
                                builder.hosts(Arrays.asList(new ServerAddress(hostname, port))))
                        .build());
        this.database = this.client.getDatabase(dataBaseString);
    }

    public void connect() {
        MongoCredential credential = MongoCredential.createCredential(username, dataBaseString, password.toCharArray());
        this.client = MongoClients.create(
                MongoClientSettings.builder()
                        .credential(credential)
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress(hostname, port))))
                        .build());
        this.database = this.client.getDatabase(dataBaseString);
    }


    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}

