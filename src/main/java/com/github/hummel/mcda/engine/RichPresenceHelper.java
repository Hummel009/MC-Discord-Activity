package com.github.hummel.mcda.engine;

import com.github.hummel.mcda.Main;
import cpw.mods.fml.common.ProgressManager;
import net.minecraft.client.Minecraft;

import java.util.Iterator;

public class RichPresenceHelper {
	private static float maxPercents;

	private RichPresenceHelper() {
	}

	public static void mainMenu(RichPresence presence) {
		mainMenu(presence, Minecraft.getMinecraft().getSession().getUsername());
	}

	private static void mainMenu(RichPresence presence, String playerName) {
		presence.update(p -> p.state = playerName + " -> " + "Главное меню");
	}

	public static void onRichUpdate(RichPresence presence) {
		if (presence.getState() == ClientState.LOADING) {
			updateLoading(presence);
		}
	}

	public static void onWorldJoin(RichPresence presence, String playerName, int dimension) {
		String worldName = Settings.getProperties().getProperty("dimension_name." + dimension, "Неизвестный мир");
		presence.update(p -> p.state = playerName + " -> " + worldName);
	}

	@SuppressWarnings("deprecation")
	private static void updateLoading(RichPresence presence) {
		try {
			Iterator<ProgressManager.ProgressBar> pit = ProgressManager.barIterator();
			ProgressManager.ProgressBar progressBar = pit.hasNext() ? pit.next() : null;
			if (progressBar != null) {
				presence.update(p -> {
					float percents0 = 1.0f / progressBar.getSteps();
					float percents = percents0 * (progressBar.getStep() - 1);
					while (pit.hasNext()) {
						ProgressManager.ProgressBar subbar = pit.next();
						percents += percents0 * (subbar.getStep() - 1) / subbar.getSteps();
						percents0 *= percents0 / subbar.getSteps();
					}
					if (percents > 1.0f) {
						percents = 1.0f;
					}
					if (percents > maxPercents) {
						maxPercents = percents;
					}
					p.state = "Запуск игры " + String.format("%2.2f", maxPercents * 100.0f) + '%';
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

