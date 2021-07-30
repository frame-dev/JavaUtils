package de.framedev.javautils;

import junit.framework.TestCase;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName TimeTest
 * / Date: 26.07.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */
public class TimeTest extends TestCase {

    public void testName() {
        double min = (long) Time.secToTime(320,Time.MINUTES);
        System.out.println(min);
    }
}