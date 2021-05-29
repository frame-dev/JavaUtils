package de.framedev.javautils;

/**
 * This Plugin was Created by FrameDev
 * Package : de.framedev.javautils
 * ClassName TextUtils
 * Date: 03.05.21
 * Project: JavaUtils
 * Copyrighted by FrameDev
 */

public class TextUtils {

    public static String replaceAndWithParagraph(String text) {
        if (text.contains("&"))
            text = text.replace('&', '§');
        return text;
    }

    public static String replaceObject(String text, String format, Object data) {
        if (text.contains(format)) {
            text = text.replace(format, String.valueOf(data));
        }
        return text;
    }

    public static boolean stringToBoolean(String text) {
        try {
            return Boolean.parseBoolean(text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static int doubleToInt(double d) {
        return (int) d;
    }

    public static double intToDouble(int d) {
        return d;
    }

    public static float intToFloat(int d) {
        return (float) d;
    }

    public static long intToLong(int d) {
        return d;
    }

    public static float doubleToFloat(double d) {
        return (float) d;
    }

    public static long doubleToLong(double d) {
        return (long) d;
    }

    public static double longToDouble(long l) {
        return (double) l;
    }


    public static int longToInt(long l) {
        return (int) l;
    }

    public static float longToFloat(long l) {
        return l;
    }

    public static double floatToDouble(float f) {
        return f;
    }

    public static int floatToInt(float f) {
        return (int) f;
    }
}
