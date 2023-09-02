package mcda;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class MCDARichPresence {
	public static MCDARichPresence instance = new MCDARichPresence();
	public DiscordRPC rpc = DiscordRPC.INSTANCE;
	public MCDAClientState state = MCDAClientState.DISABLED;
	public DiscordRichPresence presence = new DiscordRichPresence();

	public static MCDARichPresence getInstance() {
		return instance;
	}

	public void init() {
		try {
			MCDAMod.getLogger().info("Creating Discord Rich Presence");
			String applicationId = MCDASettings.discordAppId;
			DiscordEventHandlers handlers = new DiscordEventHandlers();
			handlers.ready = user -> MCDAMod.getLogger().info("Rich ready!");
			rpc.Discord_Initialize(applicationId, handlers, true, null);
			presence.startTimestamp = System.currentTimeMillis() / 1000L;
			presence.largeImageKey = MCDASettings.mainLogo;
			presence.smallImageKey = MCDASettings.mainLogoMin;
			presence.largeImageText = "UN-ION";
			presence.smallImageText = MCDASettings.category;
			rpc.Discord_UpdatePresence(presence);
			state = MCDAClientState.LOADING;
			MCDAMod.getLogger().info("Discord Rich Presence thread started");
			ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
			executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
			executor.scheduleAtFixedRate(() -> {
				try {
					MCDARichHelper.onRichUpdate(this);
				} catch (Exception e) {
					MCDAMod.getLogger().catching(e);
				}
				if (state != MCDAClientState.DISABLED) {
					rpc.Discord_RunCallbacks();
				}
			}, 0L, 2L, TimeUnit.SECONDS);
			Runtime.getRuntime().addShutdownHook(new Thread("Discord Rich Presence Shutdown") {

				@Override
				public void run() {
					executor.shutdown();
					disable();
				}
			});
		} catch (Exception e) {
			MCDAMod.getLogger().catching(e);
		}
	}

	public MCDAClientState getState() {
		return state;
	}

	public void setState(MCDAClientState state) {
		if (state == MCDAClientState.DISABLED) {
			return;
		}
		this.state = state;
	}

	public void update(Consumer<DiscordRichPresence> consumer) {
		if (state == MCDAClientState.DISABLED) {
			return;
		}
		try {
			consumer.accept(presence);
		} catch (Exception ignored) {
		}
		try {
			rpc.Discord_UpdatePresence(presence);
		} catch (Exception ex) {
			MCDAMod.getLogger().catching(ex);
		}
	}

	public void disable() {
		if (state == MCDAClientState.DISABLED) {
			return;
		}
		rpc.Discord_ClearPresence();
		rpc.Discord_Shutdown();
		state = MCDAClientState.DISABLED;
	}

}

