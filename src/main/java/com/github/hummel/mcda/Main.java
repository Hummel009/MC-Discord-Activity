package com.github.hummel.mcda;

import com.github.hummel.mcda.engine.ClientWindowHelper;
import com.github.hummel.mcda.engine.RichPresence;
import com.github.hummel.mcda.engine.Settings;
import com.github.hummel.mcda.handler.FmlEventHandler;
import com.github.hummel.mcda.handler.ForgeEventHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "mcda", acceptableRemoteVersions = "*", useMetadata = true)
public class Main {
	public Main() {
		EventBus fmlEventBus = FMLCommonHandler.instance().bus();
		FmlEventHandler fmlEventHandler = new FmlEventHandler();
		fmlEventBus.register(fmlEventHandler);

		EventBus forgeEventBus = MinecraftForge.EVENT_BUS;
		ForgeEventHandler forgeEventHandler = new ForgeEventHandler();
		forgeEventBus.register(forgeEventHandler);
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Settings.preInit();

		ClientWindowHelper.setWindowIcon();
		ClientWindowHelper.setWindowTitle();

		RichPresence.INSTANCE.preInit();
	}
}