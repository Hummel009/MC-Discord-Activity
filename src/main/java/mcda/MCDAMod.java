package mcda;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

@Mod(modid = MCDAMod.MODID, name = MCDAMod.MODNAME, version = MCDAMod.VERSION, acceptableRemoteVersions = "*")
public class MCDAMod {
	public static final String MODID = "mcda";
	public static final String MODNAME = "MC Discord Activity";
	public static final String VERSION = "23.09.02";
	@Mod.Instance
	public static MCDAMod instance;
	public static Logger logger;

	public MCDAMod() {
		logger = FMLLog.getLogger();
		MCDASettings.load();
		MCDAClientWindowHelper.setWindowIcon();
		MCDAClientWindowHelper.setWindowTitle(MCDASettings.category);
		MCDARichPresence.getInstance().init();
	}

	public static Logger getLogger() {
		return logger;
	}

	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent e) {
		logger = e.getModLog();
		MCDAEventListener eventListener = new MCDAEventListener();
		MinecraftForge.EVENT_BUS.register(eventListener);
		FMLCommonHandler.instance().bus().register(eventListener);
	}
}

