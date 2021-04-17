import de.framedev.javautils.APIs;
import de.framedev.javautils.Utils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

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
        try {
            if(!new Utils().saveJsonToFile(new File("data/test.json"),"Fuck"))
                System.err.println("why");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object o = new Utils().getClassFromJsonFile(new File("data/test.json"));
        System.out.println(o);
        assertEquals("Data", "Data");

    }
}
