package mcda;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.world.WorldEvent;

public class MCDAEventListener {
	private String lastServerName = "";

	@SubscribeEvent
	public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent e) {
		try {
			lastServerName = e.manager.getSocketAddress().toString();
			String[] parts = lastServerName.split("/");
			if (parts.length == 2) {
				lastServerName = parts[1];
			}
			String key = "server." + lastServerName;
			MCDAMod.logger.info(key);
			lastServerName = MCDASettings.properties.getProperty(key.replace(':', '_'), lastServerName);
			MCDARichPresence.INSTANCE.setState(MCDAClientState.ON_SERVER);
			MCDAClientWindowHelper.setWindowTitle(lastServerName);
		} catch (Exception ex) {
			MCDAMod.logger.catching(ex);
		}
	}

	@SubscribeEvent
	public void onDisconect(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
		Minecraft.getMinecraft().func_152344_a(() -> {
			try {
				MCDARichHelper.mainMenu(MCDARichPresence.INSTANCE);
				MCDAClientWindowHelper.setWindowTitle(MCDASettings.category);
			} catch (Exception ex) {
				MCDAMod.logger.catching(ex);
			}
		});
	}

	@SubscribeEvent
	public void onMenuOpen(GuiScreenEvent.InitGuiEvent e) {
		if (e.gui instanceof GuiMainMenu) {
			lastServerName = "";
			MCDARichPresence.INSTANCE.setState(MCDAClientState.MENU);
			MCDARichHelper.mainMenu(MCDARichPresence.INSTANCE);
		}
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load e) {
		Minecraft.getMinecraft().func_152344_a(() -> {
			try {
				MCDARichHelper.onWorldJoin(MCDARichPresence.INSTANCE, lastServerName, Minecraft.getMinecraft().getSession().getUsername(), e.world.provider.dimensionId);
			} catch (Exception ex) {
				MCDAMod.logger.catching(ex);
			}
		});
	}
}

