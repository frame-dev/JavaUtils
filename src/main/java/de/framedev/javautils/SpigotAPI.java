package de.framedev.javautils;

import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.*;
import java.util.*;

/**
 * This Plugin was Created by FrameDev
 * Package : de.framedev.javautils
 * ClassName SpigotAPI
 * Date: 17.04.21
 * Project: JavaUtils
 * Copyrighted by FrameDev
 */

public class SpigotAPI implements APIs {

    /**
     * Serialize a Bukkit Object to Base64
     *
     * @param object the Object for Serialization
     * @return return the Serialized Object
     */
    public <T extends Serializable> String objectToBase64(T object) {
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

    /**
     * Decode a Bukkit Object from Base64 String
     *
     * @param base the encoded Base64 String
     * @return return the Object of Base64
     */
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

    public Object getObjectFromBase64File(File file) {
        Object object = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            object = objectFromBase64(reader.readLine());
        } catch (Exception ignored) {

        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return object;
    }

    /**
     * Convert an Material to an ItemStack
     *
     * @param material the Material for converting to an ItemStack
     * @return returns the Converted ItemStack
     */
    public ItemStack materialToItemStack(Material material) {
        return new ItemStack(material);
    }

    /**
     * Get an ItemStack from a String
     *
     * @param item the String Item
     * @return return the ItemStack if not Null
     */
    public ItemStack stringToItemStack(@NotNull String item) {
        if (Material.getMaterial(item.toUpperCase()) == null)
            return null;
        return new ItemStack(Objects.requireNonNull(Material.getMaterial(item.toUpperCase())));
    }

    /**
     * Remove form an ItemStack the Recipe's
     *
     * @param itemStack the ItemStack to remove there Recipe
     */
    public void removeRecipe(ItemStack itemStack) {
        Iterator<Recipe> iterator = Bukkit.recipeIterator();
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe.getResult() == itemStack) {
                iterator.remove();
            }
        }
    }

    public ShapedRecipe createShapedRecipe(JavaPlugin plugin, String nameSpace, ItemStack result) {
        return new ShapedRecipe(new NamespacedKey(plugin, nameSpace + "_" + result.getType().name().toLowerCase()), result);
    }

    public ShapelessRecipe createShapelessRecipe(JavaPlugin plugin,String nameSpace, ItemStack result) {
        return new ShapelessRecipe(new NamespacedKey(plugin, nameSpace + "_" + result.getType().name().toLowerCase()), result);
    }

    /**
     * Create your own BossBar
     */
    public static class BossBarManager {

        private String title;
        private BarColor barColor;
        private BarStyle barStyle;
        private BarFlag[] barFlags;
        private static final List<BossBar> bossBars = new ArrayList<>();
        private BossBar bossBar;

        public BossBarManager(String title, BarColor barColor, BarStyle barStyle, BarFlag... barFlags) {
            this.title = title;
            this.barColor = barColor;
            this.barStyle = barStyle;
            this.barFlags = barFlags;
        }

        public BossBarManager(String title, BarColor barColor, BarStyle barStyle) {
            this.title = title;
            this.barColor = barColor;
            this.barStyle = barStyle;
        }

        public BossBarManager(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public BarColor getBarColor() {
            return barColor;
        }

        public void setBarColor(BarColor barColor) {
            this.barColor = barColor;
        }

        public BarStyle getBarStyle() {
            return barStyle;
        }

        public void setBarStyle(BarStyle barStyle) {
            this.barStyle = barStyle;
        }

        public BarFlag[] getBarFlags() {
            return barFlags;
        }

        public void setBarFlags(BarFlag[] barFlags) {
            this.barFlags = barFlags;
        }

        /**
         * Need the Title the Color and the BarStyle (Optional BarFlags)
         *
         * @return return the Created BossBar
         */
        public BossBar createBossBar() {
            BossBar bossBar = null;
            if (barFlags == null)
                bossBar = Bukkit.createBossBar(title, barColor, barStyle);
            bossBar = Bukkit.createBossBar(title, barColor, barStyle, barFlags);
            bossBars.add(bossBar);
            this.bossBar = bossBar;
            return bossBar;
        }

        /**
         * Add a Player to the Created BossBar / Selected BossBar
         *
         * @param player the selected Player
         * @return return this BossBarManager
         */
        public BossBarManager addPlayer(Player player) {
            if (bossBar != null)
                bossBar.addPlayer(player);
            return this;
        }

        /**
         * Remove a Player from the BossBar
         *
         * @param player the selected Player
         * @return return this BossBarManager
         */
        public BossBarManager removePlayer(Player player) {
            if (bossBar != null)
                bossBar.removePlayer(player);
            return this;
        }

        /**
         * Remove all BossBars
         */
        public static void removeBossBars() {
            bossBars.forEach(bossBar -> {
                bossBar.setVisible(false);
            });
            bossBars.clear();
        }

        public void setBossBar(BossBar bossBar) {
            this.bossBar = bossBar;
        }

        public BossBar getBossBar() {
            return bossBar;
        }

        /**
         * Return all BossBars in a List
         *
         * @return return all BossBars
         */
        public static List<BossBar> getBossBars() {
            return bossBars;
        }
    }

    public static class ChunkLoader {

        private final Chunk chunk;

        private final File file;
        private final FileConfiguration cfg;

        public ChunkLoader(File file, Chunk chunk) {
            this.chunk = chunk;
            this.file = file;
            this.cfg = YamlConfiguration.loadConfiguration(file);
        }

        public void saveChunk(String name) {
            cfg.set("chunk." + name + ".world", chunk.getWorld().getName());
            cfg.set("chunk." + name + ".x", chunk.getX());
            cfg.set("chunk." + name + ".z", chunk.getZ());
            cfg.set("chunk." + name + ".loaded", true);
            chunk.setForceLoaded(true);
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void setLoaded(File file, String name, boolean loaded) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            if (cfg.contains("chunk." + name))
                cfg.set("chunk." + name + ".loaded", loaded);
            if (loaded) {
                Objects.requireNonNull(getChunk(file, name)).setForceLoaded(true);
            }
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static boolean isChunkEnabled(File file, String name) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            if (cfg.contains("chunk." + name + ".loaded"))
                return cfg.getBoolean("chunk." + name + ".loaded");
            return false;
        }

        public static Chunk getChunk(File file, String name) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            if (!cfg.contains("chunk." + name)) return null;
            World world = Bukkit.getWorld(Objects.requireNonNull(cfg.getString("chunk." + name + ".world")));
            int x = cfg.getInt("chunk." + name + ".x");
            int z = cfg.getInt("chunk." + name + ".z");
            return world != null ? world.getChunkAt(x, z) : null;
        }

        /**
         * Force Loading Chunks from files
         *
         * @param file the Chunk File where all Chunks are saved
         */
        public static void forceLoad(File file) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            if (cfg.getConfigurationSection("chunk") == null) return;
            for (String s : cfg.getConfigurationSection("chunk").getKeys(false)) {
                if (getChunk(file, s) == null) continue;
                Chunk chunk = getChunk(file, s);
                if (chunk == null) continue;
                if (isChunkEnabled(file, s))
                    chunk.setForceLoaded(true);
            }
        }
    }

    public static class ItemBuilder {

        private final ItemStack itemStack;
        private final ItemMeta itemMeta;

        public ItemBuilder(ItemStack itemStack) {
            this.itemStack = itemStack;
            this.itemMeta = itemStack.getItemMeta();
        }

        public ItemBuilder(Material material) {
            this.itemStack = new ItemStack(material);
            this.itemMeta = itemStack.getItemMeta();
        }

        public ItemBuilder hideEnchantments() {
            this.itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            return this;
        }

        public ItemBuilder addItemFlags(ItemFlag... flags) {
            for (ItemFlag flag : flags) {
                this.itemMeta.addItemFlags(flag);
            }
            return this;
        }

        public ItemStack getItemStack() {
            return itemStack;
        }

        public ItemMeta getItemMeta() {
            return itemMeta;
        }

        public ItemBuilder setDisplayName(String displayName) {
            if (itemMeta == null) {
                return null;
            }
            this.itemMeta.setDisplayName(displayName);
            return this;
        }

        public String getDisplayName() {
            if (itemMeta == null) {
                return null;
            }
            return this.itemMeta.getDisplayName();
        }

        public ItemBuilder setLore(String... lore) {
            if (itemMeta == null) return null;
            this.itemMeta.setLore(Arrays.asList(lore));
            return this;
        }

        public List<String> getLore() {
            if (itemMeta == null) return null;
            return this.itemMeta.getLore();
        }

        public ItemBuilder setAmount(int amount) {
            this.itemStack.setAmount(amount);
            return this;
        }

        public int getAmount() {
            if (itemStack == null) return 0;
            return itemStack.getAmount();
        }

        public ItemBuilder setUnbreakable(boolean unbreakable) {
            this.itemMeta.setUnbreakable(unbreakable);
            return this;
        }

        public ItemBuilder addLore(String str) {
            if (this.itemMeta == null) return null;
            if (!this.itemMeta.hasLore()) {
                List<String> lore = new ArrayList<>();
                lore.add(str);
                this.itemMeta.setLore(lore);
            } else {
                List<String> lore = this.itemMeta.getLore();
                if (this.itemMeta.getLore() != null && lore != null) {
                    lore.add(str);
                    this.itemMeta.setLore(lore);
                } else {
                    lore = new ArrayList<>();
                    lore.add(str);
                    this.itemMeta.setLore(lore);
                }
            }
            return this;
        }

        public Material getMaterial() {
            if (this.itemStack == null) return null;
            return this.itemStack.getType();
        }

        public String getLore(int index) {
            if (this.itemMeta == null) return null;
            if (this.itemMeta.hasLore() && this.itemMeta.getLore() != null) {
                return this.itemMeta.getLore().get(index);
            }
            return null;
        }

        public ItemBuilder addUnsafeEnchantment(Enchantment enchantment, int level) {
            this.itemStack.addUnsafeEnchantment(enchantment, level);
            return this;
        }

        public ItemBuilder addEnchantment(Enchantment enchantment, int level, boolean ignore) {
            if (this.itemMeta == null) return null;
            itemMeta.addEnchant(enchantment, level, ignore);
            return this;
        }

        public ItemStack build() {
            if (this.itemMeta == null) {
                return itemStack;
            }
            this.itemStack.setItemMeta(itemMeta);
            return itemStack;
        }

        public static class TestEnchantment extends Enchantment implements Listener {

            @Deprecated
            public TestEnchantment(String namespace) {
                super(new NamespacedKey(namespace, namespace));
            }

            @Override
            public @NotNull String getName() {
                return "Data";
            }

            @Override
            public int getMaxLevel() {
                return 3;
            }

            @Override
            public int getStartLevel() {
                return 1;
            }

            @Override
            public @NotNull EnchantmentTarget getItemTarget() {
                return EnchantmentTarget.TOOL;
            }

            @Override
            public boolean isTreasure() {
                return false;
            }

            @Override
            public boolean isCursed() {
                return false;
            }

            @Override
            public boolean conflictsWith(Enchantment other) {
                return false;
            }

            @Override
            public boolean canEnchantItem(ItemStack item) {
                return true;
            }
        }
    }

    public static class InventoryManager {

        private String title;
        private int size;
        private Inventory inventory;

        public InventoryManager(Inventory inventory) {
            this.inventory = inventory;
            this.size = inventory.getSize();
        }

        public InventoryManager(String title, int size) {
            this.title = title;
            this.size = size;
        }

        public InventoryManager(String title) {
            this.title = title;
            this.size = 9;
        }

        /**
         * Return the Created Inventory
         * It can return null if the Inventory wasn't Created
         *
         * @return return the Created Inventory
         */
        public Inventory getInventory() {
            return inventory;
        }

        public InventoryManager setInventory(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        /**
         * Set the Title of the Inventory
         *
         * @param title the Title for the Inventory
         * @return return this Class
         */
        public InventoryManager setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Return the Title from the Inventory
         *
         * @return return the Title from the Inventory
         */
        public String getTitle() {
            return title;
        }

        /**
         * Set the Inventory size. Like (inventoryManager.setSize(1);) This means that the Inventory will have 9 Slots.
         *
         * @param size the Size for the Inventory
         * @return return this Class back
         */
        public InventoryManager setSize(int size) {
            this.size = size * 9;
            return this;
        }

        /**
         * Return the Size of the Inventory
         *
         * @return return the Size of the Inventory
         */
        public int getSize() {
            return size;
        }

        public InventoryManager setItem(int index, ItemStack itemStack) {
            if (inventory == null) throw new NullPointerException("The Inventory is Null, please create it first!");
            inventory.setItem(index, itemStack);
            return this;
        }

        public InventoryManager setItem(int index, Material material) {
            if (inventory == null) throw new NullPointerException("The Inventory is Null, please create it first!");
            inventory.setItem(index, new ItemStack(material));
            return this;
        }

        public InventoryManager addItem(ItemStack itemStack) {
            inventoryNull();
            inventory.addItem(itemStack);
            return this;
        }

        public InventoryManager addItem(ItemStack... itemStack) {
            inventoryNull();
            inventory.addItem(itemStack);
            return this;
        }

        public ItemStack getItem(int index) {
            inventoryNull();
            return inventory.getItem(index);
        }

        private void inventoryNull() {
            if (inventory == null) throw new NullPointerException("The Inventory is Null. Please create it first!");
        }

        public InventoryManager create() {
            if (title == null) throw new NullPointerException("Inventory Title is null");
            if (size == 0) size = 9;
            inventory = Bukkit.createInventory(null, size, title);
            return this;
        }

        /**
         * Fill all empty Spaces with Black Stained Glass Pane
         *
         * @return return this Class back
         */
        public InventoryManager fillNulls() {
            inventoryNull();
            int size = getSize();
            for (int i = 0; i < size; i++) {
                if (getItem(i) == null)
                    inventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName(" ").build());
            }
            return this;
        }

        public ItemStack[] getContent() {
            inventoryNull();
            return inventory.getContents();
        }

        public InventoryManager setContent(ItemStack[] content) {
            inventoryNull();
            inventory.setContents(content);
            return this;
        }

        /**
         * Show inventory at Player
         *
         * @param player Player
         */
        public void show(Player player) {
            inventoryNull();
            player.openInventory(inventory);
        }
    }

}
