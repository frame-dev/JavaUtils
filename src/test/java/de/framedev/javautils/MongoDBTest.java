package de.framedev.javautils;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName MongoDBTest
 * / Date: 15.07.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

public class MongoDBTest {

    String ip;
    String hostName;

    @Test
    public void testMongoDB() {
        MongoDBManager mongoDBManager = new MongoDBManager("framedev.ch", "server", "ej'gYn&]]EuHw7J[", "server");
        mongoDBManager.connect();
        BackendMongoDBManager backendManager = new BackendMongoDBManager(mongoDBManager);
        HashMap<String, Object> data = new HashMap<>();
        SystemMemory systemMemory = new SystemMemory();
        int mb = 1024 * 1024;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        data.put("ram", "Free Memory : " + systemMemory.getAllocatedFree() / mb + " mb");
        data.put("ramAllocated", "Allocated Memory : " + systemMemory.getAllocatedTotal() / mb + " mb");
        data.put("ramTotal", "Total Memory : " + systemMemory.getTotal() / mb + " mb");
        data.put("hostname", hostName);
        data.put("ip", ip);
        data.put("totaldiskspace", new SystemUtils().getAllTotalDiskSpace());
        backendManager.createData("hostname", hostName, data, "JavaUtils");
        backendManager.updateAll("hostname", hostName, data, "JavaUtils");
    }
}
