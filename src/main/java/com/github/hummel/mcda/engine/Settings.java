package com.github.hummel.mcda.engine;

import com.github.hummel.mcda.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
	private static Properties properties;

	private static String discordAppId = "1147449520565801001";
	private static String mainLabel = "UN-ION";
	private static String mainLogo = "icon_arabic";
	private static String mainLogoMin = "icon";
	private static String category = "Minecraft";
	private static String window = "Hummel009's Minecraft 1.7.10";

	private Settings() {
	}

	public static String getCategory() {
		return category;
	}

	public static String getDiscordAppId() {
		return discordAppId;
	}

	public static String getMainLabel() {
		return mainLabel;
	}

	public static String getMainLogo() {
		return mainLogo;
	}

	public static String getMainLogoMin() {
		return mainLogoMin;
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void preInit() {
		properties = read();
		if (!properties.isEmpty()) {
			discordAppId = properties.getProperty("app_id", discordAppId);
			mainLogo = properties.getProperty("main_logo", mainLogo);
			mainLogoMin = properties.getProperty("main_logo_min", mainLogoMin);
			category = properties.getProperty("category", category);
			window = properties.getProperty("window", window);
			mainLabel = properties.getProperty("mainLabel", mainLabel);
		}
	}

	private static Properties read() {
		try (InputStream is = Main.class.getResourceAsStream("/config.properties")) {
			Properties prop = new Properties();
			prop.load(is);
			return prop;
		} catch (IOException e) {
			e.printStackTrace();
			return new Properties();
		}
	}
}

