package de.framedev.javautils;

/**
 * This Plugin was Created by FrameDev
 * Package : de.framedev.javautils
 * ClassName Apis
 * Date: 16.04.21
 * Project: JavaUtils
 * Copyrighted by FrameDev
 */
public interface APIs {

    Utils utils = new Utils();
    Utils.PasswordGenerator passwordGenerator = new Utils.PasswordGenerator();
    Utils.CustomGenerators.IntRandomNumberGenerator intRandomGenerator = new Utils.CustomGenerators.IntRandomNumberGenerator();
    Utils.CustomGenerators.DoubleRandomNumberGenerator doubleRandomGenerator = new Utils.CustomGenerators.DoubleRandomNumberGenerator();
}
