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

/**
 * This Plugin was Created by FrameDev
 * Package : de.framedev.javautils
 * Date: 04.02.21
 * Project: EssentialsMini
 * Copyrighted by FrameDev
 */

public class YamlConfigurator {

    private HashMap<String, Object> data;

    File file;

    public YamlConfigurator(File file) {
        this.file = file;
    }

    protected HashMap<String, Object> getData() {
        return data;
    }

    private HashMap<String, Object> getConfig(File file) {
        ObjectMapper mapper = new YAMLMapper();
        HashMap<String, Object> hash = new HashMap<>();
        try {
            MapType type = TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, Object.class);
            hash = mapper.readValue(new FileReader(file), type);
        } catch (Exception ignored) {
        }
        return hash;
    }

    public void setDefaults(HashMap<String, Object> defaults) {
        data = getConfig(file);
        data.putAll(defaults);
    }

    public void addDefault(String path, Object value) {
        data = getConfig(file);
        data.put(path, value);
    }

    public void set(String path, Object value) {
        data = getConfig(file);
        data.put(path, value);
    }

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

    public List<String> getStringList(String path) {
        data = getConfig(file);
        if(data.containsKey(path))
            return (List<String>) data.get(path);
        return null;
    }

    public List<Object> getList(String path) {
        data = getConfig(file);
        if(data.containsKey(path))
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

    public void saveDefaultConfig(String resource) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(resource + ".yml");
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
        if (!data.isEmpty())
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