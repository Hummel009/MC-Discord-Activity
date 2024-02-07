package com.github.hummel.mcda;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.world.WorldEvent;

public class EventHandler {
	@SubscribeEvent
	public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent e) {
		try {
			RichPresence.INSTANCE.setState(ClientState.ON_SERVER);
			ClientWindowHelper.setWindowTitle();
		} catch (Exception ex) {
			Main.getLogger().catching(ex);
		}
	}

	@SubscribeEvent
	public void onDisconect(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
		Minecraft.getMinecraft().func_152344_a(() -> {
			try {
				RichPresenceHelper.mainMenu(RichPresence.INSTANCE);
				ClientWindowHelper.setWindowTitle();
			} catch (Exception ex) {
				Main.getLogger().catching(ex);
			}
		});
	}

	@SubscribeEvent
	public void onMenuOpen(GuiScreenEvent.InitGuiEvent e) {
		if (e.gui instanceof GuiMainMenu) {
			RichPresence.INSTANCE.setState(ClientState.MENU);
			RichPresenceHelper.mainMenu(RichPresence.INSTANCE);
		}
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load e) {
		Minecraft.getMinecraft().func_152344_a(() -> {
			try {
				RichPresenceHelper.onWorldJoin(RichPresence.INSTANCE, Minecraft.getMinecraft().getSession().getUsername(), e.world.provider.dimensionId);
			} catch (Exception ex) {
				Main.getLogger().catching(ex);
			}
		});
	}
}

