package mcda;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.world.WorldEvent;

public class MCDAEventListener {
	public String lastServerName = "";

	@SubscribeEvent
	public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent e) {
		try {
			lastServerName = e.manager.getSocketAddress().toString();
			String[] parts = lastServerName.split("/");
			if (parts.length == 2) {
				lastServerName = parts[1];
			}
			String key = "server." + lastServerName;
			MCDAMod.getLogger().info(key);
			lastServerName = MCDASettings.getProperties().getProperty(key.replace(':', '_'), lastServerName);
			MCDARichPresence.getInstance().setState(MCDAClientState.ON_SERVER);
			MCDAClientWindowHelper.setWindowTitle(lastServerName);
		} catch (Exception ex) {
			MCDAMod.getLogger().catching(ex);
		}
	}

	@SubscribeEvent
	public void onMenuOpen(GuiScreenEvent.InitGuiEvent e) {
		if (e.gui instanceof GuiMainMenu) {
			lastServerName = "";
			MCDARichPresence.getInstance().setState(MCDAClientState.MENU);
			MCDARichHelper.mainMenu(MCDARichPresence.getInstance());
		}
	}

	@SubscribeEvent
	public void onDisconect(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
		Minecraft.getMinecraft().func_152344_a(() -> {
			try {
				MCDARichHelper.mainMenu(MCDARichPresence.getInstance());
				MCDAClientWindowHelper.setWindowTitle(MCDASettings.category);
			} catch (Exception ex) {
				MCDAMod.getLogger().catching(ex);
			}
		});
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load e) {
		Minecraft.getMinecraft().func_152344_a(() -> {
			try {
				MCDARichHelper.onWorldJoin(MCDARichPresence.getInstance(), lastServerName, Minecraft.getMinecraft().getSession().getUsername(), e.world.provider.dimensionId);
			} catch (Exception ex) {
				MCDAMod.getLogger().catching(ex);
			}
		});
	}
}

