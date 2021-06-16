import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.framedev.javautils.APIs;

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
        if(os.startsWith("mac"));

    }
}
