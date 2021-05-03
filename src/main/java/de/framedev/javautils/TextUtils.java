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
        if(text.contains(format)) {
            text = text.replace(format, String.valueOf(data));
        }
        return text;
    }
}
