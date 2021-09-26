package de.framedev.javautils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName SystemUtils
 * / Date: 16.07.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

public class SystemUtils {

    public enum OSType {
        WINDOWS, MACOS, LINUX, OTHER;
    }

    public OSType getOSType() {
        String OS = System.getProperty("os.name", "generic").toLowerCase();
        if (OS.contains("win")) {
            return OSType.WINDOWS;
        } else if ((OS.contains("mac")) || (OS.contains("darwin"))) {
            return OSType.MACOS;
        } else if (OS.contains("nux")) {
            return OSType.LINUX;
        } else {
            return OSType.OTHER;
        }
    }

    public Thread getActiveThread() {
        return Thread.currentThread();
    }

    public int getActiveThreadCount() {
        return Thread.activeCount();
    }

    public String getArchitecture() {
        return ManagementFactory.getOperatingSystemMXBean().getArch();
    }

    public int getCores() {
        return ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
    }

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

    public String getDriveDescription(File file) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        return fsv.getSystemTypeDescription(file);
    }

    public boolean isDrive(File file) {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        return fsv.isDrive(file);
    }

    public double getAllTotalDiskSpace() {
        long gb = DiskSizeType.GB.getSize();
        long totalSpace = 0;
        for (Path root : FileSystems.getDefault().getRootDirectories()) {

            try {
                if (isDrive(root.toFile())) {
                    FileStore store = Files.getFileStore(root);
                    totalSpace += store.getTotalSpace() / gb;
                } else if (getOSType() == OSType.MACOS || getOSType() == OSType.LINUX) {
                    FileStore store = Files.getFileStore(root);
                    totalSpace += store.getTotalSpace() / gb;
                }
            } catch (IOException e) {
                System.out.println("error querying space: " + e.toString());
            }
        }
        return totalSpace;
    }

    public double getTotalDiskSpace(File file) {
        long gb = DiskSizeType.GB.getSize();
        long totalSpace = 0;
        try {
            if (isDrive(file)) {
                FileStore store = Files.getFileStore(file.toPath());
                totalSpace = store.getTotalSpace() / gb;
            } else if (getOSType() == OSType.MACOS || getOSType() == OSType.LINUX) {
                FileStore store = Files.getFileStore(file.toPath());
                totalSpace = store.getTotalSpace() / gb;
            }
        } catch (IOException e) {
            System.out.println("error querying space: " + e.toString());
        }
        return totalSpace;
    }

    public double getUsedDiskSpace(File file) {
        long gb = DiskSizeType.GB.getSize();
        long usedSpace = 0;
        long totalSpace = 0;
        try {
            if (isDrive(file)) {
                FileStore store = Files.getFileStore(file.toPath());
                totalSpace = store.getTotalSpace() / gb;
                usedSpace = totalSpace - (store.getUsableSpace() / gb);
            } else if (getOSType() == OSType.MACOS || getOSType() == OSType.LINUX) {
                FileStore store = Files.getFileStore(file.toPath());
                totalSpace = store.getTotalSpace() / gb;
                usedSpace = totalSpace - (store.getUsableSpace() / gb);
            }
        } catch (IOException e) {
            System.out.println("error querying space: " + e.toString());
        }
        return usedSpace;
    }

    public double getAllUsedDiskSpace() {
        long gb = DiskSizeType.GB.getSize();
        long usedSpace = 0;
        long totalSpace = 0;
        for (Path root : FileSystems.getDefault().getRootDirectories()) {
            try {
                if (isDrive(root.toFile())) {
                    FileStore store = Files.getFileStore(root);
                    totalSpace += store.getTotalSpace() / gb;
                    usedSpace += totalSpace - (store.getUsableSpace() / gb);
                } else if (getOSType() == OSType.MACOS || getOSType() == OSType.LINUX) {
                    FileStore store = Files.getFileStore(root);
                    totalSpace += store.getTotalSpace() / gb;
                    usedSpace += totalSpace - (store.getUsableSpace() / gb);
                }
            } catch (IOException e) {
                System.out.println("error querying space: " + e.toString());
            }
        }
        return usedSpace;
    }

    public double getAllFreeDiskSpace() {
        double used = getAllUsedDiskSpace();
        double total = getAllTotalDiskSpace();
        return total - used;
    }

    public double getFreeDiskSpace(File file) {
        double used = getUsedDiskSpace(file);
        double total = getTotalDiskSpace(file);
        return total - used;
    }
}
