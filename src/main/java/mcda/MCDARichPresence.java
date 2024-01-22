package mcda;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class MCDARichPresence {
	public static final MCDARichPresence INSTANCE = new MCDARichPresence();

	private final DiscordRPC rpc = DiscordRPC.INSTANCE;
	private final DiscordRichPresence presence = new DiscordRichPresence();
	private MCDAClientState state = MCDAClientState.DISABLED;

	public DiscordRPC getRpc() {
		return rpc;
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

	public void init() {
		try {
			MCDAMod.getLogger().info("Creating Discord Rich Presence");
			String applicationId = MCDASettings.getDiscordAppId();
			DiscordEventHandlers handlers = new DiscordEventHandlers();
			handlers.ready = user -> MCDAMod.getLogger().info("Rich ready!");
			rpc.Discord_Initialize(applicationId, handlers, true, null);
			presence.startTimestamp = System.currentTimeMillis() / 1000L;
			presence.largeImageKey = MCDASettings.getMainLogo();
			presence.smallImageKey = MCDASettings.getMainLogoMin();
			presence.largeImageText = MCDASettings.getMainLabel();
			presence.smallImageText = MCDASettings.getCategory();
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
			Runtime.getRuntime().addShutdownHook(new MCDAThread(this, executor));
		} catch (Exception e) {
			MCDAMod.getLogger().catching(e);
		}
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

}

