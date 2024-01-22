package mcda;

import cpw.mods.fml.common.ProgressManager;
import net.minecraft.client.Minecraft;

import java.util.Iterator;

public class MCDARichHelper {
	private static float maxPercents;

	public static void mainMenu(MCDARichPresence presence) {
		mainMenu(presence, Minecraft.getMinecraft().getSession().getUsername());
	}

	private static void mainMenu(MCDARichPresence presence, String playerName) {
		presence.update(p -> p.setState(playerName + " -> " + "Главное меню"));
	}

	public static void onRichUpdate(MCDARichPresence presence) {
		if (presence.getState() == MCDAClientState.LOADING) {
			updateLoading(presence);
		}
	}

	public static void onWorldJoin(MCDARichPresence presence, String serverName, String playerName, int dimension) {
		String worldName = MCDASettings.properties.getProperty("dimension_name." + dimension, "Неизвестный мир");
		presence.update(p -> p.setState(playerName + " -> " + worldName));
	}

	@SuppressWarnings("deprecation")
	private static void updateLoading(MCDARichPresence presence) {
		try {
			ProgressManager.ProgressBar progressBar;
			Iterator<ProgressManager.ProgressBar> pit = ProgressManager.barIterator();
			ProgressManager.ProgressBar progressBar2 = progressBar = pit.hasNext() ? pit.next() : null;
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
					p.setState("Запуск игры " + String.format("%2.2f", maxPercents * 100.0f) + '%');
				});
			}
		} catch (Exception e) {
			MCDAMod.logger.catching(e);
		}
	}
}

