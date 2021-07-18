package de.framedev.javautils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName SystemUtils
 * / Date: 16.07.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

public class SystemUtils {

    public static enum DiskSizeType {
        MB(1000 * 1000),
        GB(1000 * 1000 * 1000),
        TB(1000L * 1000 * 1000 * 1000),
        PB(1000L * 1000 * 1000 * 1000 * 1000);

        private final long size;

        DiskSizeType(long size) {
            this.size = size;
        }

        public long getSize() {
            return size;
        }
    }

    public String getLocalAddress() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            //hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

    public String getLocalHostName() {
        String hostName = "";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }

    public double getTotalDiskSpace() {
        long gb = DiskSizeType.GB.getSize();
        long totalSpace = 0;
        NumberFormat nf = NumberFormat.getNumberInstance();
        for (Path root : FileSystems.getDefault().getRootDirectories()) {

            System.out.print(root + ": ");
            try {
                FileStore store = Files.getFileStore(root);
                totalSpace = store.getTotalSpace() / gb;
            } catch (IOException e) {
                System.out.println("error querying space: " + e.toString());
            }
        }
        return totalSpace;
    }
}
