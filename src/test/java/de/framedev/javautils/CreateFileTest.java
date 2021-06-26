package de.framedev.javautils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * This Plugin was Created by FrameDev
 * Package : PACKAGE_NAME
 * ClassName CreateFile
 * Date: 16.04.21
 * Project: JavaUtils
 * Copyrighted by FrameDev
 */

public class CreateFileTest implements APIs {

    public void setUp() {
        start();
    }
    @Test
    public void start() {
        assertEquals(10, 10);
        assertEquals("Data", "Data");
        String os = System.getProperty("os.name").toLowerCase();
        String version = System.getProperty("os.version");
        String javaVersion = System.getProperty("java.version");
        System.out.println(javaVersion);
        System.out.println(os + " : " + version);
    }

    @Test
    public void listTest() {
        ReplaceList<Object> list = new ReplaceList<>();
        list.add("data");
        list.add("§atest");
        String passwd = new Utils.PasswordGenerator().generatorKey(200);
        list.add(passwd);
        System.out.println(list);
        list.replace("data","new");
        System.out.println(list);
        String os = System.getProperty("os.name").toLowerCase();
        String userDir = "";
        if(os.contains("mac")) {
            userDir = System.getProperty("user.home") + "/Library/Application Support/";
        } else if(os.contains("windows")) {
            userDir = System.getenv("%APPDATA%") + "/";
        } else {
            userDir = System.getProperty("user.home") + "/";
        }

        File file = new File(userDir + "JavaUtils","test.yaml");
        if(file.getParentFile() != null && !file.getParentFile().exists())
            file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            utils.saveYamlToFile(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
