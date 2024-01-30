package de.framedev.javautils;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName TimeTest
 * / Date: 26.07.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */
public class TimeTest extends TestCase {

    @Test
    public void testName() {
        File tempFile = new File(new Utils().getTempDir() + "test.json");
        tempFile.deleteOnExit();
        JsonConfigurator jsonConfigurator = new JsonConfigurator(tempFile);
        jsonConfigurator.set("Fucker", "Hello World");

        jsonConfigurator.set("Hello World", new Utils().objectToBase64(new Cooldown(1, 12)));
        jsonConfigurator.saveConfig();

        Cooldown cooldown = new Utils().objectFromBase64(jsonConfigurator.getString("Hello World"));


        /**
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(execute(cooldown)) {
                break;
            }
        }

        new Utils().getLogger().info("Bye!");**/

        FrameTreeMap<String, Object> map = new FrameTreeMap<>();
        map.put("Fuck",21);
        System.out.println(map.getKey(21));
        System.out.println("Bye!");
    }

    public static boolean success = false;
    public static int run = 0;

    public boolean execute(Cooldown cooldown) {
        if (cooldown.isExpired()) {
            success = true;
        }
        return success;
    }

    protected static void getBundlesInfo() {
        System.out.println("ay");
    }
}