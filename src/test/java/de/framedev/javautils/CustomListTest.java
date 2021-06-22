package de.framedev.javautils;

import junit.framework.TestCase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName CustomListTest
 * / Date: 18.06.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */
public class CustomListTest extends TestCase {

    public static CustomList<Object> list = new CustomList<>();
    public void setUp() {
        System.out.println("register");
    }

    public void testList() {
        int mb = 1024 * 1024;
        // get Runtime instance
        Runtime instance = Runtime.getRuntime();

        System.out.println("***** Heap utilization statistics [MB] *****\n");

        // available memory
        System.out.println("Total Memory: " + instance.totalMemory() / mb);

        // free memory
        System.out.println("Free Memory: " + instance.freeMemory() / mb);

        // used memory
        System.out.println("Used Memory: "
                + (instance.totalMemory() - instance.freeMemory()) / mb);

        // Maximum available memory
        System.out.println("Max Memory: " + instance.maxMemory() / mb);

        System.out.println("***** Heap utilization Ended *****");

        long used = (instance.totalMemory() - instance.freeMemory()) / mb;
        double load = ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
        System.out.println(load);
        System.out.println("CPU: " + (float) (load * 100) + "%");
        list.add(System.getProperty("os.name") + " : " + System.getProperty("os.version"));
        list.add("Used-Memory: " + used + "Mb");
        list.add("Available Processors : " + Runtime.getRuntime().availableProcessors());
        list.add(load);

        System.out.println(list);

        String os = System.getProperty("os.name").toLowerCase();
        String userDir = "";
        if(os.contains("mac")) {
            userDir = System.getProperty("user.dir") + "/Library/Application Support/";
        } else if(os.contains("windows")) {
            userDir = System.getProperty("java.io.tmpdir") + "/";
        } else {
            userDir = System.getProperty("user.dir") + "/";
        }
        File file = new File(userDir + "JavaUtils/" + "log-" + new SimpleDateFormat("HH_mm_ss-dd.MM.yyyy").format(new Date()) + ".txt");
        if(file.getParentFile() != null && !file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Object o : list) {
                writer.write(o.toString() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getPath());
    }
}