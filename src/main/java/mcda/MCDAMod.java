package mcda;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

@Mod(modid = MCDAMod.MODID, name = MCDAMod.MODNAME, version = MCDAMod.VERSION, acceptableRemoteVersions = "*")
public class MCDAMod {
	@Mod.Instance
	private static MCDAMod instance;
	private static Logger logger;

	protected static final String MODID = "mcda";
	protected static final String MODNAME = "MC Discord Activity";
	protected static final String VERSION = "23.09.02";

	public MCDAMod() {
		logger = FMLLog.getLogger();
		MCDASettings.load();
		MCDAClientWindowHelper.setWindowIcon();
		MCDAClientWindowHelper.setWindowTitle();
		MCDARichPresence.INSTANCE.init();
	}

	public static MCDAMod getInstance() {
		return instance;
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

