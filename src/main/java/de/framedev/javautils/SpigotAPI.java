package de.framedev.javautils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

/**
 * This Plugin was Created by FrameDev Package : de.framedev.javautils ClassName
 * SpigotAPI Date: 17.04.21 Project: JavaUtils Copyrighted by FrameDev
 */

public class SpigotAPI {

	public SpigotAPI() {
		Logger.getLogger(SpigotAPI.class.getName()).log(Level.INFO, "Loaded");
	}

	public String objectToBase64(Object object) throws Exception {
		for (Class<?> anInterface : object.getClass().getInterfaces()) {
			if (!anInterface.getName().equalsIgnoreCase("Serializable")) {
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
		if (Material.getMaterial(item.toUpperCase()) == null)
			return null;
		return new ItemStack(Material.getMaterial(item.toUpperCase()));
	}

	public static class BossBarManager {

		private String title;
		private BarColor barColor;
		private BarStyle barStyle;
		private BarFlag[] barFlags;
		private static List<BossBar> bossBars = new ArrayList<>();
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

		public BossBar createBossBar() {
			BossBar bossBar = null;
			if (barFlags == null)
				bossBar = Bukkit.createBossBar(title, barColor, barStyle);
			bossBar = Bukkit.createBossBar(title, barColor, barStyle, barFlags);
			bossBars.add(bossBar);
			this.bossBar = bossBar;
			return bossBar;
		}

		public BossBarManager addPlayer(Player player) {
			if (bossBar != null)
				bossBar.addPlayer(player);
			return this;
		}

		public BossBarManager removePlayer(Player player) {
			if (bossBar != null)
				bossBar.removePlayer(player);
			return this;
		}

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

		public static List<BossBar> getBossBars() {
			return bossBars;
		}
	}
}
