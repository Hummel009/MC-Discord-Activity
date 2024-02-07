package com.github.hummel.mcda;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

@Mod(modid = "com/github/hummel/mcda", acceptableRemoteVersions = "*", useMetadata = true)
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
		EventHandler eventHandler = new EventHandler();
		MinecraftForge.EVENT_BUS.register(eventHandler);
		FMLCommonHandler.instance().bus().register(eventHandler);
	}
}

