package com.github.hummel.mcda;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

@Mod(modid = "mcda", acceptableRemoteVersions = "*", useMetadata = true)
public class Main {
	@Mod.Instance
	private static Main instance;
	private static Logger logger;

	public Main() {
		logger = FMLLog.getLogger();
		Settings.load();
		ClientWindowHelper.setWindowIcon();
		ClientWindowHelper.setWindowTitle();
		RichPresence.INSTANCE.init();
	}

	public static Main getInstance() {
		return instance;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent e) {
		logger = e.getModLog();
		EventBus fmlEventBus = FMLCommonHandler.instance().bus();
		EventBus forgeEventBus = MinecraftForge.EVENT_BUS;
		EventHandler eventHandler = new EventHandler();
		forgeEventBus.register(eventHandler);
		fmlEventBus.register(eventHandler);
	}
}

