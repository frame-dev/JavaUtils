package de.framedev.javautils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Plugin was Created by FrameDev
 * Package : de.framedev.javautils
 * ClassName Utils
 * Date: 14.04.21
 * Project: JavaUtils
 * Copyrighted by FrameDev
 */

public class Utils {

    private static final Logger logger = Logger.getLogger(Utils.class.getName());

    /**
     * Init of this Class run this as First
     */
    public Utils() {
        getLogger().log(Level.INFO, getClass().getSimpleName() + " Loaded");
    }

    /**
     *
     * @return the Logger for this Class (Project)
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * Generator Class for a Custom Key
     */
    public static class PasswordGenerator {

        private final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        private final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        private final String NUMBER = "0123456789";
        private final String OTHER_CHAR = "!@#$%&*()_+-=[]?";

        private final String STRING_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;

        // optional, make it more random
        private final String STRING_ALLOW_BASE_SHUFFLE = shuffleString(STRING_ALLOW_BASE);
        private final String STRING_ALLOW = STRING_ALLOW_BASE_SHUFFLE;

        private static final SecureRandom random = new SecureRandom();

        /**
         * Generate a Custom key with all letters!
         *
         * @param length Key Length
         * @return returns the Key
         */
        public String generatorKey(int length) {
            if (length < 1)
                throw new IllegalArgumentException();

            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {

                int rndCharAt = random.nextInt(STRING_ALLOW.length());
                char rndChar = STRING_ALLOW.charAt(rndCharAt);
                sb.append(rndChar);
            }
            return shuffleString(sb.toString());
        }

        // shuffle
        private String shuffleString(String string) {
            List<String> letters = Arrays.asList(string.split(""));
            Collections.shuffle(letters);
            return String.join("", letters);
        }
    }

    /**
     * @param file the Location File where it need to be saved
     * @param o    the Object do you want to save
     * @return if it was success or not
     * @throws IOException error when File cannot be created
     */
    public boolean saveJsonToFile(File file, Object o) throws IOException {
        if (!file.exists()) {
            if (file.getParentFile() == null || !file.getParentFile().mkdir()) {
                return false;
            }
            try {
                if (!file.createNewFile())
                    return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(new GsonBuilder().setPrettyPrinting().serializeNulls().create().toJson(o));
            writer.flush();
            writer.close();
            return true;
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }

    /**
     * @param file      the File where al is Located
     * @param class_ the Class form the Class Objectg
     * @param <T>       the Class
     * @return the Class Object from File
     */
    public <T> T getClassFromJsonFile(File file, Class<T> class_) {
        try {
            FileReader reader = new FileReader(file);
            return new Gson().fromJson(reader, class_);
        } catch (Exception ignored) {

        }
        return null;
    }

    /**
     *
     * @param file the Located File where al is saved
     * @return returns the Object from the Json File
     */
    public Object getClassFromJsonFile(File file) {
        try {
            FileReader reader = new FileReader(file);
            return new Gson().fromJson(reader, Object.class);
        } catch (Exception ignored) {

        }
        return null;
    }

    /**
     * Class for Custom Generators with other Classes
     */
    public static class CustomGenerators {

        /**
         *
         * @param min the Min Value inclusive
         * @param max the Max Value inclusive
         * @return a Random Integer
         */
        public int randomInt(int min, int max) {
            return new IntRandomNumberGenerator(min, max).nextInt();
        }

        /**
         *
         * @param min the Min Value inclusive
         * @param max the Max Value inclusive
         * @return a Random Double
         */
        public double randomDouble(double min, double max) {
            return new DoubleRandomNumberGenerator(min, max).nextDouble();
        }

        public static final class IntRandomNumberGenerator {

            private final PrimitiveIterator.OfInt randomIterator;

            /**
             * Initialize a new random number generator that generates as Integer
             * random numbers in the range [min, max]
             *
             * @param min - the min value (inclusive)
             * @param max - the max value (inclusive)
             */
            public IntRandomNumberGenerator(int min, int max) {
                randomIterator = new Random().ints(min, max + 1).iterator();
            }

            /**
             * Returns a random number in the range (min, max)
             *
             * @return a random number in the range (min, max)
             */
            public int nextInt() {
                return randomIterator.nextInt();
            }
        }

        public static final class DoubleRandomNumberGenerator {

            private final PrimitiveIterator.OfDouble randomIterator;

            /**
             * Initialize a new random number generator that generates as Double
             * random numbers in the range [min, max]
             *
             * @param min - the min value (inclusive)
             * @param max - the max value (inclusive)
             */
            public DoubleRandomNumberGenerator(double min, double max) {
                randomIterator = new Random().doubles(min, max + 1).iterator();
            }

            /**
             * Returns a random number in the range (min, max)
             *
             * @return a random number in the range (min, max)
             */
            public double nextDouble() {
                return randomIterator.nextDouble();
            }
        }
    }
}
