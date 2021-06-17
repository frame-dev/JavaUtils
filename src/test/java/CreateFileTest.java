import static org.junit.Assert.assertEquals;

import com.google.common.reflect.TypeToken;
import de.framedev.javautils.CustomList;
import de.framedev.javautils.Utils;
import org.junit.Test;

import de.framedev.javautils.APIs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This Plugin was Created by FrameDev
 * Package : PACKAGE_NAME
 * ClassName CreateFile
 * Date: 16.04.21
 * Project: JavaUtils
 * Copyrighted by FrameDev
 */

public class CreateFileTest implements APIs {

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
        CustomList<Object> list = new CustomList<>();
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
        } else if(os.contains("win")) {
            userDir = System.getProperty("%APPDATA%") + "/";
        } else {
            userDir = System.getProperty("user.home") + "/";
        }
        System.out.println(userDir);

        File file = new File(userDir + "JavaUtils","test.json");
        if(file.getParentFile() != null && !file.getParentFile().exists())
            file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            utils.saveJsonToFile(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
