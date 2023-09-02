package mcda;

import cpw.mods.fml.common.ProgressManager;
import net.minecraft.client.Minecraft;

import java.util.Iterator;

public class MCDARichHelper {
	public static float maxPercents;

	public static void onRichUpdate(MCDARichPresence presence) {
		if (presence.getState() == MCDAClientState.LOADING) {
			updateLoading(presence);
		}
	}

	public static void updateLoading(MCDARichPresence presence) {
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
					p.state = "\u0417\u0430\u043f\u0443\u0441\u043a \u0438\u0433\u0440\u044b " + String.format("%2.2f", maxPercents * 100.0f) + "%";
				});
			}
		} catch (Exception e) {
			MCDAMod.getLogger().catching(e);
		}
	}

	public static void mainMenu(MCDARichPresence presence) {
		mainMenu(presence, Minecraft.getMinecraft().getSession().getUsername());
	}

	public static void mainMenu(MCDARichPresence presence, String playerName) {
		presence.update(p -> {
			p.state = playerName + " -> " + "\u0413\u043b\u0430\u0432\u043d\u043e\u0435 \u043c\u0435\u043d\u044e";
		});
	}

	public static void onWorldJoin(MCDARichPresence presence, String serverName, String playerName, int dimension) {
		String worldName = MCDASettings.getProperties().getProperty("dimension_name." + dimension, "\u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u044b\u0439 \u043c\u0438\u0440");
		presence.update(p -> {
			p.state = playerName + " -> " + worldName;
		});
	}
}

