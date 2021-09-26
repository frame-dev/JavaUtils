package de.framedev.javautils;

import java.util.logging.Level;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName Main
 * / Date: 07.09.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

public class Main {

    public static void main(String[] args) {
        new Utils().createEmptyLogger("JavaUtils", true).log(Level.INFO, "JavaUtils Successfully Loaded!");
    }
}
