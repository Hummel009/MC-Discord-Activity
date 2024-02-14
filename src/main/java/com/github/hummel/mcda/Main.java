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
	@Mod.Instance
	private static Main instance;

	public Main() {
		Settings.load();
		ClientWindowHelper.setWindowIcon();
		ClientWindowHelper.setWindowTitle();
		RichPresence.INSTANCE.init();
	}

	public static Main getInstance() {
		return instance;
	}

	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		EventBus fmlEventBus = FMLCommonHandler.instance().bus();
		FmlEventHandler fmlEventHandler = new FmlEventHandler();
		fmlEventBus.register(fmlEventHandler);

		EventBus forgeEventBus = MinecraftForge.EVENT_BUS;
		ForgeEventHandler forgeEventHandler = new ForgeEventHandler();
		forgeEventBus.register(forgeEventHandler);
	}
}

