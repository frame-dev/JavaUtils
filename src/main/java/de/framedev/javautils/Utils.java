package de.framedev.javautils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvException;
import org.jetbrains.annotations.NotNull;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.security.SecureRandom;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utils Class for the JavaUtils Jar
 * This Class contains many Methods
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
     * @return the Logger for this Class (Project)
     */
    public Logger getLogger() {
        return logger;
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

            private PrimitiveIterator.OfInt randomIterator;

            private int min;
            private int max;

            public IntRandomNumberGenerator() {
            }

            public IntRandomNumberGenerator setMin(int min) {
                this.min = min;
                return this;
            }

            public IntRandomNumberGenerator setMax(int max) {
                this.max = max;
                return this;
            }

            public int getMin() {
                return min;
            }

            public int getMax() {
                return max;
            }

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
                if (randomIterator == null)
                    randomIterator = new Random().ints(min, max + 1).iterator();
                return randomIterator.nextInt();
            }
        }

        public static final class DoubleRandomNumberGenerator {

            private PrimitiveIterator.OfDouble randomIterator;

            private double min;
            private double max;

            public DoubleRandomNumberGenerator() {
            }

            public DoubleRandomNumberGenerator setMin(double min) {
                this.min = min;
                return this;
            }

            public DoubleRandomNumberGenerator setMax(double max) {
                this.max = max;
                return this;
            }

            public double getMax() {
                return max;
            }

            public double getMin() {
                return min;
            }

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
                if (randomIterator == null)
                    randomIterator = new Random().doubles(min, max + 1).iterator();
                return randomIterator.nextDouble();
            }
        }
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

    public String objectToJsonString(Object object) {
        return new Gson().toJson(object);
    }

    public Object objectFromJsonString(String json) {
        return new Gson().fromJson(json, Object.class);
    }

    public <T> T classFromJsonString(String json, Class<T> class_) {
        return new Gson().fromJson(json, class_);
    }

    public String objectToYamlString(Object object) {
        try {
            return new YAMLMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object objectFromYamlString(String yaml) {
        try {
            return new YAMLMapper().readValue(yaml, Object.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T classFromYamlString(String yaml, Class<T> class_) {
        try {
            return new YAMLMapper().readValue(yaml, class_);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String objectToXmlString(Object object) {
        Serializer serializer = new Persister();
        StringWriter writer = new StringWriter();
        try {
            serializer.write(object, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    public Object objectFromXmlString(String xml) {
        Serializer serializer = new Persister();
        try {
            return serializer.read(Object.class, xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T classFromXmlString(String xml, Class<T> class_) {
        Serializer serializer = new Persister();
        try {
            return serializer.read(class_, xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
     * Return an Object from a File
     *
     * @param file the Selected File with the Extension .json for get the Data
     * @return return the Object from the File
     */
    public Object getObjectFromJsonFile(File file) {
        if (file.exists()) {
            try {
                return new Gson().fromJson(new FileReader(file), Object.class);
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
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            System.err.println("File doesn't exists!");
        }
        return null;
    }

    public <T> T getClassTypeFromYamlFile(File file, Type type) {
        if (file.exists()) {
            YAMLMapper mapper = new YAMLMapper();
            JavaType javaType = mapper.constructType(type);
            try {
                return mapper.readValue(file, javaType);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            System.err.println("File doesn't exists!");
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

    public <T> T getTypeFromJsonFile(File file, Type class_) {
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
     * Create a String of a Base64 (encode)
     *
     * @param object the Object to ecncode to Base64
     * @return returns the encoded Base64 Byte Array
     */
    public <T extends Serializable> String objectToBase64(T object) {
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
     * Decode a Base64 String
     *
     * @param base the encoded Base64
     * @return returns the decoded Object
     */
    public <T> T objectFromBase64(String base) {
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(Base64.getDecoder().decode(base));
            ObjectInputStream os = new ObjectInputStream(is);
            return (T) os.readObject();
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
     * @return return an Array of split Strings
     */
    public String[] stringSplitter(String text, @NotNull String regex) {
        if (text == null) return null;
        if (!text.contains(regex)) return null;
        return text.split(regex);
    }

    /**
     * This Method Creates a Logger from scratch
     *
     * @param name       the Name of the new Logger
     * @param timeFormat if you would like an TimeStamp in logger
     * @return return the new Logger
     */
    public Logger createEmptyLogger(String name, boolean timeFormat) {
        return new MyFormatter(timeFormat).createEmptyLogger(name);
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void download(String fileUrl, String location, String fileNameWithExtensions, String newLocation) {
        File file = null;
        if (location != null) {
            file = new File(location, fileNameWithExtensions);
            if (file.getParentFile() != null && !file.getParentFile().exists())
                file.getParentFile().mkdirs();
        } else {
            file = new File(fileNameWithExtensions);
        }
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            URL url = new URL(fileUrl);
            in = new BufferedInputStream(url.openStream());
            fout = new FileOutputStream(new File(location, fileNameWithExtensions));
            final byte[] data = new byte[4096];
            int count;
            while ((count = in.read(data, 0, 4096)) != -1) {
                fout.write(data, 0, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        if (new File(newLocation, fileNameWithExtensions).getParentFile() != null &&
                !new File(newLocation, fileNameWithExtensions).getParentFile().exists())
            new File(newLocation, fileNameWithExtensions).getParentFile().mkdirs();
        if (!file.renameTo(new File(newLocation, fileNameWithExtensions))) {
            getLogger().log(Level.SEVERE, "File cannot be Renamed/Moved");
        }
        getLogger().info("File Downloaded and Moved!");
    }

    public void download(String fileUrl, String location, String fileNameWithExtensions) {
        File file = null;
        if (location != null) {
            file = new File(location, fileNameWithExtensions);
            if (file.getParentFile() != null && !file.getParentFile().exists())
                file.getParentFile().mkdirs();
        } else {
            file = new File(fileNameWithExtensions);
        }
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            URL url = new URL(fileUrl);
            in = new BufferedInputStream(url.openStream());
            fout = new FileOutputStream(file);
            final byte[] data = new byte[4096];
            int count;
            while ((count = in.read(data, 0, 4096)) != -1) {
                fout.write(data, 0, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        getLogger().info("File Downloaded!");
    }

    /**
     * Get The Temp Directory
     *
     * @return return the Temp Directory
     */
    public String getTempDir() {
        String os = System.getProperty("os.name").toLowerCase();
        String tempDir = "";
        if (os.contains("mac")) {
            tempDir = System.getProperty("java.io.tmpdir") + "/";
        } else if (os.contains("windows")) {
            tempDir = System.getProperty("java.io.tmpdir") + "/";
        } else {
            tempDir = System.getProperty("java.io.tmpdir") + "/";
        }
        return tempDir;
    }

    /**
     * Get The User Home Directory
     *
     * @return return the User Home Directory
     */
    public String getUserHome() {
        String os = System.getProperty("os.name").toLowerCase();
        String userDir = "";
        if (os.contains("mac")) {
            userDir = System.getProperty("user.home");
        } else if (os.contains("windows")) {
            userDir = System.getProperty("user.home") + "/";
        } else {
            userDir = System.getProperty("user.home") + "/";
        }
        return userDir;
    }

    /**
     * Get the User's Directory
     *
     * @return return the User Directory
     */
    public String getUserDir() {
        String os = System.getProperty("os.name").toLowerCase();
        String userDir = "";
        if (os.contains("mac")) {
            userDir = System.getProperty("user.dir") + "/";
        } else if (os.contains("windows")) {
            userDir = System.getProperty("user.dir") + "/";
        } else {
            userDir = System.getProperty("user.dir") + "/";
        }
        return userDir;
    }

    /**
     * Get the user Applications Store Folder
     *
     * @return return the Applications Store Folder
     */
    public String getUserAppData() {
        String os = getOs();
        String userData = "";
        if (os.contains("mac")) {
            userData = System.getProperty("user.home") + "/Library/Application Support/";
        } else if (os.contains("windows")) {
            userData = System.getenv("APPDATA") + "/";
        } else {
            userData = System.getProperty("user.home") + "/";
        }
        return userData;
    }

    /**
     * Return the Operating System Name
     *
     * @return return the Os Name
     **/
    public String getOs() {
        return System.getProperty("os.name").toLowerCase();
    }

    /**
     * Return Os's Version
     *
     * @return return the Os Version
     */
    public String getOsVersion() {
        return System.getProperty("os.version").toLowerCase();
    }

    protected File streamToFile(InputStream in) {
        if (in == null) {
            return null;
        }

        try {
            File f = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
            f.deleteOnExit();

            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];

            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            return f;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public File getFromResourceFile(String file) {
        InputStream resource = this.getClass().getClassLoader().getResourceAsStream(file);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            return streamToFile(resource);
        }
    }

    public File getFromResourceFile(String file, Class<?> class_) {
        InputStream resource = class_.getClassLoader().getResourceAsStream(file);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            return streamToFile(resource);
        }
    }


    /**
     * Check if a File is existing
     *
     * @param fileName the FileName to check if it is exists or not
     * @return return a boolean if exists or not
     */
    public boolean existFile(String fileName) {
        return new File(fileName).exists();
    }

    public boolean existFile(File file) {
        if (file == null) return false;
        return file.exists();
    }

    /**
     * Get the Default Values of a Yaml File
     *
     * @param fileName the FileName
     * @param class_   Main Class
     * @return returns a HashMap
     */
    public HashMap<String, Object> getDefaults(String fileName, Class<?> class_) {
        File file = getFromResourceFile(fileName, class_);
        HashMap<String, Object> data = getClassTypeFromYamlFile(file, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        try {
            saveYamlToFile(new File(fileName), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeCsvFile(File file, List<String[]> rows, List<String[]> objects) throws IOException {
        ICSVWriter csvWriter = new CSVWriterBuilder(new FileWriter(file)).withSeparator(',').build();
        csvWriter.writeAll(rows);
        csvWriter.writeAll(objects);
        csvWriter.flush();
        csvWriter.close();
    }

    public List<String[]> getDataFromCSVFile(File file, List<String[]> rows) throws IOException, CsvException {
        CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).build();
        List<String[]> data = csvReader.readAll();
        csvReader.close();
        List<String[]> updated = new ArrayList<>(data);
        for (String[] d : data) {
            for (int i = 0; i < rows.size(); i++) {
                if (d[i].equalsIgnoreCase(rows.get(0)[i]))
                    updated.remove(d);
            }
        }
        return updated;
    }

    public <T extends Serializable> void saveObjectToBase64File(File file, T object) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(objectToBase64(object));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T getObjectFromBase64File(File file) {
        T object = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            object = objectFromBase64(reader.readLine());
        } catch (Exception ignored) {

        }
        return object;
    }

    public void saveHashMapToJson(File file, HashMap<String, Object> objects) throws IOException {
        saveJsonToFile(file, objects);
    }

    public HashMap<String, Object> getHashMapFromJsonFile(File file) {
        // Type of HashMap
        Type type = new TypeToken<HashMap<String, Object>>() {
        }.getType();

        // Return the HashMap from the File (can return null)
        return getClassTypeFromYamlFile(file, type);
    }
}
