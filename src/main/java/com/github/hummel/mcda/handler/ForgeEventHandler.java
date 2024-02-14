package com.github.hummel.mcda.handler;

import com.github.hummel.mcda.engine.ClientState;
import com.github.hummel.mcda.engine.RichPresence;
import com.github.hummel.mcda.engine.RichPresenceHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.world.WorldEvent;

public class ForgeEventHandler {
	@SubscribeEvent
	public void onMenuOpen(GuiScreenEvent.InitGuiEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			RichPresence.INSTANCE.setState(ClientState.MENU);
			RichPresenceHelper.mainMenu(RichPresence.INSTANCE);
		}
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event) {
		Minecraft.getMinecraft().func_152344_a(() -> {
			try {
				RichPresenceHelper.onWorldJoin(RichPresence.INSTANCE, Minecraft.getMinecraft().getSession().getUsername(), event.world.provider.dimensionId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}

