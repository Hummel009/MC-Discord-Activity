package mcda;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MCDASettings {
	public static Properties properties;

	public static String discordAppId = "1147449520565801001";
	public static String mainLabel = "UN-ION";
	public static String mainLogo = "icon_arabic";
	public static String mainLogoMin = "icon";
	public static String category = "Minecraft";

	private static String window = "Hummel009's Minecraft 1.7.10";

	public static void load() {
		properties = read();
		if (properties != null) {
			discordAppId = properties.getProperty("app_id", discordAppId);
			mainLogo = properties.getProperty("main_logo", mainLogo);
			mainLogoMin = properties.getProperty("main_logo_min", mainLogoMin);
			category = properties.getProperty("category", category);
			window = properties.getProperty("window", window);
			mainLabel = properties.getProperty("mainLabel", mainLabel);
		}
	}

	private static Properties read() {
		try (InputStream is = MCDAMod.class.getResourceAsStream("/config.properties")) {
			Properties prop = new Properties();
			prop.load(is);
			return prop;
		} catch (IOException ex) {
			MCDAMod.logger.catching(ex);
			return null;
		}
	}
}

