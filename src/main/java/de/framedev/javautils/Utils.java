package de.framedev.javautils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utils Class for the JavaUtils Jar
 * This Class contains much Methods
 * This Plugin was Created by FrameDev
 * Package : de.framedev.javautils
 * ClassName Utils
 * Date: 14.04.21
 * Project: JavaUtils
 * Copyrighted by FrameDev
 */

public class Utils {

    /**
     * The Logger for this Class
     */
    private final Logger logger = createEmptyLogger("Utils", true);

    /**
     * Init of this Class run this as First
     */
    public Utils() {
        getLogger().log(Level.INFO, getClass().getSimpleName() + " Loaded");
    }

    /**
     * @return the Logger for this Class (Project)
     */
    public Logger getLogger() {
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

        // shuffle the String
        private String shuffleString(String string) {
            List<String> letters = Arrays.asList(string.split(""));
            Collections.shuffle(letters);
            return String.join("", letters);
        }
    }

    /**
     * Please add to the File the extension .yml
     *
     * @param file the Located File
     * @param o    The Object to save in the File
     * @return a boolean if it was Success or not
     * @throws IOException error when no permissions or other error
     */
    public boolean saveYamlToFile(File file, Object o) throws IOException {
        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            try {
                if (!file.createNewFile())
                    return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        ObjectMapper mapper = new YAMLMapper();
        try {
            mapper.writeValue(new FileWriter(file), o);
            return true;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    /**
     * Please add to the File the extension .yml
     *
     * @param file the Located File
     * @return the Object from File
     */
    public Object getObjectFromYamlFile(File file) {
        if (file.exists()) {
            YAMLMapper mapper = new YAMLMapper();
            try {
                return mapper.readValue(file, Object.class);
            } catch (IOException ignored) {
            }
        }
        return null;
    }

    /**
     * Please add to the File the extension .yml
     *
     * @param file   the Located File
     * @param class_ the Object Class
     * @param <T>    The Object class
     * @return the Object Class from File
     */
    public <T> T getClassFromYamlFile(File file, Class<T> class_) {
        if (file.exists()) {
            YAMLMapper mapper = new YAMLMapper();
            try {
                return mapper.readValue(file, class_);
            } catch (IOException ignored) {

            }
        }
        return null;
    }

    public boolean saveXmlToFile(File file, Object object) {
        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
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
            Serializer mapper = new Persister();
            mapper.write(object, file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Object getObjectFromXml(File file) {
        if (file.exists()) {
            Serializer mapper = new Persister();
            try {
                return mapper.read(Object.class, file);
            } catch (Exception ignored) {

            }
        }
        return null;
    }

    public <T> T getObjectFromClassXml(File file, Class<T> class_) {
        if (file.exists()) {
            Serializer mapper = new Persister();
            try {
                return mapper.read(class_, file);
            } catch (Exception ignored) {

            }
        }
        return null;
    }

    /**
     * Please add to the File the extension .json
     *
     * @param file the Location File where it need to be saved
     * @param o    the Object do you want to save
     * @return if it was success or not
     * @throws IOException throws an error(IOException) when File cannot be created
     */
    public boolean saveJsonToFile(File file, Object o) throws IOException {
        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
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
     * Please add to the File the extension .json
     *
     * @param file   the File where al is Located
     * @param class_ the Class form the Class Objectg
     * @param <T>    the Class
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
     * Please add to the File the extension .json
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
         * @param min the Min Value inclusive
         * @param max the Max Value inclusive
         * @return a Random Integer
         */
        public int randomInt(int min, int max) {
            return new IntRandomNumberGenerator(min, max).nextInt();
        }

        /**
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

    /**
     * @param object the Object to ecncode to Base64
     * @return returns the encoded Base64 Byte Array
     */
    public String objectToBase64(Object object) {
        try {
            ByteArrayOutputStream is = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(is);
            os.writeObject(object);
            os.flush();
            os.close();
            return Base64.getEncoder().encodeToString(is.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param base the encoded Base64
     * @return returns the decoded Object
     */
    public Object objectFromBase64(String base) {
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(Base64.getDecoder().decode(base));
            ObjectInputStream os = new ObjectInputStream(is);
            return os.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Debug an Object
     *
     * @param object the Object for debugging
     */
    public void debug(Object object) {
        System.out.println(object);
    }

    /**
     * Print an Error with the Object
     *
     * @param object the selected Object for printing an error
     */
    public void error(Object object) {
        System.err.println(object);
    }

    /**
     * @param name the Name of the new Logger
     * @return returns the new Created Logger
     */
    public Logger createLogger(String name) {
        return Logger.getLogger(name);
    }

    /**
     * @param text  the Text for Splitting
     * @param regex the char for splitting
     * @return return an Array of splitted Strings
     */
    public String[] stringSplitter(String text, @NotNull String regex) {
        if (text == null) return null;
        if (!text.contains(regex)) return null;
        return text.split(regex);
    }

    /**
     * This Method Creates an Logger from scratch
     *
     * @param name       the Name of the new Logger
     * @param timeFormat if you would like an TimeStamp in logger
     * @return return the new Logger
     */
    public Logger createEmptyLogger(String name, boolean timeFormat) {
        return new MyFormatter(timeFormat).createEmptyLogger(name);
    }

    /**
     * return if the first object is the same as the second object
     *
     * @param obj_1 the First Object
     * @param obj_2 the Second Object
     * @return return if the first object is the same as the second object
     */
    public boolean equals(Object obj_1, Object obj_2) {
        if (obj_1 == null) return false;
        if (obj_2 == null) return false;
        return obj_1.equals(obj_2);
    }
}
