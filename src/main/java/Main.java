import de.framedev.javautils.Utils;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * / This Plugin was Created by FrameDev
 * / Package : PACKAGE_NAME
 * / ClassName Main
 * / Date: 18.06.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

public class Main {

    public static boolean run;

    private static Logger logger = new Utils().createEmptyLogger("MainTest", true);

    public static void main(String[] args) {
        run = true;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Run (true/false)");
        run = scanner.nextBoolean();
        if (run)
            System.out.println("hello");
        logger.log(Level.INFO, "Loaded");
    }
}
