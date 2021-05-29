package de.framedev.javautils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Plugin was Created by FrameDev
 * Package : de.framedev.javautils
 * ClassName SpigotAPI
 * Date: 17.04.21
 * Project: JavaUtils
 * Copyrighted by FrameDev
 */

public class SpigotAPI {

    public SpigotAPI() {
        Logger.getLogger(SpigotAPI.class.getName()).log(Level.INFO, "Loaded");
    }

    public String objectToBase64(Object object) throws Exception {
        for (Class<?> anInterface : object.getClass().getInterfaces()) {
            if(!anInterface.getName().equalsIgnoreCase("Serializable")) {
                throw new Exception("Need to Serializable");
            }
        }

        try {
            ByteArrayOutputStream is = new ByteArrayOutputStream();
            BukkitObjectOutputStream os = new BukkitObjectOutputStream(is);
            os.writeObject(object);
            os.flush();
            os.close();
            return Base64Coder.encodeLines(is.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object objectFromBase64(String base) {
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(Base64Coder.decodeLines(base));
            BukkitObjectInputStream os = new BukkitObjectInputStream(is);
            return os.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ItemStack materialToItemStack(Material material) {
        return new ItemStack(material);
    }

    public ItemStack stringToItemStack(@NotNull String item) {
        if(Material.getMaterial(item.toUpperCase()) == null) return null;
        return new ItemStack(Material.getMaterial(item.toUpperCase()));
    }
}
