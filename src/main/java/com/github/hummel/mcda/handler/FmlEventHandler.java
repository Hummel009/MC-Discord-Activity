package com.github.hummel.mcda.handler;

import com.github.hummel.mcda.engine.ClientState;
import com.github.hummel.mcda.engine.ClientWindowHelper;
import com.github.hummel.mcda.engine.RichPresence;
import com.github.hummel.mcda.engine.RichPresenceHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.client.Minecraft;

public class FmlEventHandler {
	@SubscribeEvent
	public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
		try {
			RichPresence.INSTANCE.setState(ClientState.ON_SERVER);
			ClientWindowHelper.setWindowTitle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public void onDisconect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
		Minecraft.getMinecraft().func_152344_a(() -> {
			try {
				RichPresenceHelper.mainMenu(RichPresence.INSTANCE);
				ClientWindowHelper.setWindowTitle();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}

