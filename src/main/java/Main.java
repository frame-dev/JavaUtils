import java.util.Scanner;

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

    public static void main(String[] args) {
        run = true;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Run (true/false)");
        run = scanner.nextBoolean();
        if (run)
            System.out.println("hello");
    }
}
