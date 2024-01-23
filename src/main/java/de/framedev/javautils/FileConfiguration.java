package de.framedev.javautils;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileConfiguration {

    private final Map<String, Object> data = new LinkedHashMap<>();
    private final File file;

    private Representer representer;
    private DumperOptions dumperOptions;

    public FileConfiguration(File file) {
        this.file = file;
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        representer = new Representer(dumperOptions);
    }

    public FileConfiguration() {
        this.file = null;
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        representer = new Representer(dumperOptions);
    }

    // Set a value at a specified path
    public void set(String path, Object value) {
        String[] keys = path.split("\\.");
        Map<String, Object> current = data;

        for (int i = 0; i < keys.length - 1; i++) {
            current = (Map<String, Object>) current.computeIfAbsent(keys[i], k -> new LinkedHashMap<>());
        }

        if (current != null) {
            current.put(keys[keys.length - 1], value);
        }
    }

    // Get a value at a specified path
    public Object get(String path) {
        String[] keys = path.split("\\.");
        Map<String, Object> current = data;

        for (String key : keys) {
            Object value = current.get(key);

            if (value == null) {
                return null; // Key not found
            }

            if (value instanceof Map) {
                current = (Map<String, Object>) value;
            } else {
                // Reached a leaf node, return the value
                return value;
            }
        }

        return null; // Shouldn't reach here
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public boolean containsValue(Object value) {
        return data.containsValue(value);
    }

    public FileConfiguration getConfigurationsSection(String path) {
        Object section = get(path);

        if (section instanceof Map) {
            FileConfiguration sectionConfig = new FileConfiguration();
            sectionConfig.data.putAll((Map<? extends String, ?>) section);
            return sectionConfig;
        }

        System.out.println("DEBUG: Section not found for path: " + path);
        System.out.println("DEBUG: Actual data: " + data);
        return null;
    }

    public List<String> getKeys(String path) {
        Object section = getMap(path);

        if (section != null) {
            Map<String, Object> sectionMap = (Map<String, Object>) section;
            return new ArrayList<>(sectionMap.keySet());
        }

        System.out.println("DEBUG: Section not found for path: " + path);
        System.out.println("DEBUG: Actual data: " + data);
        return null;
    }

    // Load configuration from a file
    public void load(File file) {
        try (InputStream input = new FileInputStream(file)) {
            Yaml yaml = new Yaml(representer, dumperOptions);
            Object loadedData = yaml.load(input);

            if (loadedData instanceof Map) {
                data.clear();
                data.putAll((Map<? extends String, ?>) loadedData);
            }
        } catch (FileNotFoundException e) {
            // Handle file not found, or initialize defaults if needed
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        if (file != null) {
            load(file);
        } else {
            System.err.println("File is not specified for loading.");
        }
    }

    // Save configuration to a file
    public void save(File file) {
        try (Writer writer = new FileWriter(file)) {
            Yaml yaml = new Yaml(representer, dumperOptions);
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        if(file != null) {
            save(file);
        }
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void set(String path, Map<String, Object> value) {
        // Set the nested map at the specified path
        String[] keys = path.split("\\.");
        Map<String, Object> current = data;

        for (int i = 0; i < keys.length - 1; i++) {
            current = (Map<String, Object>) current.computeIfAbsent(keys[i], k -> new LinkedHashMap<>());
        }

        if (current != null) {
            current.put(keys[keys.length - 1], value);
        }
    }

    public Map<String, Object> getMap(String path) {
        String[] keys = path.split("\\.");
        Map<String, Object> current = data;

        for (String key : keys) {
            Object value = current.get(key);

            if (value == null || !(value instanceof Map)) {
                return null; // Key not found or not a map
            }

            current = (Map<String, Object>) value;
        }

        return current;
    }

    public void addClassPath(Class<?> class_, Tag tag) {
        representer.addClassTag(class_, tag);
    }
}
