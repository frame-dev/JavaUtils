package de.framedev.javautils;

/**
 * This Plugin was Created by FrameDev
 * Package : de.framedev.javautils
 * ClassName YamlConfigurator
 * Date: 17.04.21
 * Project: JavaUtils
 * Copyrighted by FrameDev
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class YamlConfigurator {

    private HashMap<String, Object> data;

    private File file;

    public YamlConfigurator(File file) {
        this.file = file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    private HashMap<String, Object> getConfig(File file) {
        ObjectMapper mapper = new YAMLMapper();
        HashMap<String, Object> hash = new HashMap<>();
        try {
            MapType type = TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, Object.class);
            hash = mapper.readValue(new FileReader(file), type);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hash;
    }

    /**
     * Create all Defaults for the Config
     *
     * @param defaults the Defaults to set
     */
    public void setDefaults(HashMap<String, Object> defaults) {
        data = getConfig(file);
        data.putAll(defaults);
    }

    /**
     * @param path  the Path for defaults
     * @param value the Value for defaults
     */
    public void addDefault(String path, Object value) {
        data = getConfig(file);
        if (!isSet(path))
            data.put(path, value);
    }

    /**
     * @param path  the Path to set Value
     * @param value the Value to set
     */
    public void set(String path, Object value) {
        data = getConfig(file);
        if (value == null) {
            data.remove(path);
        } else {
            data.put(path, value);
        }
    }

    /**
     * @param path the Path where the Int is located
     * @return returns the Int
     */
    public int getInt(String path) {
        data = getConfig(file);
        if (data.containsKey(path))
            return Integer.parseInt(String.valueOf(data.get(path)));
        return 0;
    }

    public double getDouble(String path) {
        data = getConfig(file);
        if (data.containsKey(path))
            return Double.parseDouble(String.valueOf(data.get(path)));
        return 0d;
    }

    public Object get(String path) {
        data = getConfig(file);
        if (data.containsKey(path))
            return data.get(path);
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<String> getStringList(String path) {
        data = getConfig(file);
        if (data.containsKey(path))
            //noinspection unchecked
            return (List<String>) data.get(path);
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Object> getList(String path) {
        data = getConfig(file);
        if (data.containsKey(path))
            //noinspection unchecked
            return (List<Object>) data.get(path);
        return null;
    }

    public String getString(String path) {
        data = getConfig(file);
        if (data.containsKey(path))
            return (String) data.get(path);
        return null;
    }

    public boolean getBoolean(String path) {
        data = getConfig(file);
        if (data.containsKey(path))
            return Boolean.parseBoolean(String.valueOf(data.get(path)));
        return false;
    }

    public boolean isInteger(String path) {
        data = getConfig(file);
        if (data.containsKey(path)) {
            try {
                Integer.parseInt(String.valueOf(data.get(path)));
                return true;
            } catch (Exception ignored) {

            }
        }
        return false;
    }

    public boolean isDouble(String path) {
        data = getConfig(file);
        if (data.containsKey(path)) {
            try {
                Double.parseDouble(String.valueOf(data.get(path)));
                return true;
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public boolean contains(String path) {
        data = getConfig(file);
        return data.containsKey(path);
    }

    public boolean isSet(String path) {
        data = getConfig(file);
        if (data.get(path) != null)
            return data.containsKey(path);
        return false;
    }

    public void saveDefaultConfig(Class<?> clazz, String resource) {
        InputStream is = clazz.getResourceAsStream(resource + ".yml");
        ObjectMapper mapper = new YAMLMapper();
        HashMap<String, Object> hash = new HashMap<>();
        try {
            MapType type = TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, Object.class);
            hash = mapper.readValue(is, type);
        } catch (Exception ignored) {
        }
        if (is != null) {
            try {
                String yaml = mapper.writeValueAsString(hash);
                FileWriter writer = new FileWriter(file);
                writer.write(yaml);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (data != null && !data.isEmpty())
            hash.putAll(data);
        data = hash;
    }

    public void saveConfig() {
        ObjectMapper mapper = new YAMLMapper();
        try {
            mapper.writeValue(new FileWriter(file), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = getConfig(file);
    }

    @Override
    public String toString() {
        return "YAMLConfigurator{" +
                "data=" + data +
                ", file='" + file + '\'' +
                '}';
    }
}