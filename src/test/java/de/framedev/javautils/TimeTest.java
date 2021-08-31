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
        SystemUtils.OSType osType = new SystemUtils().getOSType();
        System.out.println(osType);
    }
}