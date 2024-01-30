package de.framedev.javautils;

/**
 * This Plugin was Created by FrameDev
 * Package : de.framedev.javautils
 * ClassName TextUtils
 * Date: 03.05.21
 * Project: JavaUtils
 * Copyrighted by FrameDev
 */

public class TextUtils extends Converter {

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

    public static int doubleToInt(double d) {
        return (int) d;
    }

    public static int longToInt(long number) {
        return (int) number;
    }

    public static int floatToInt(float number) {
        return (int) number;
    }
}
