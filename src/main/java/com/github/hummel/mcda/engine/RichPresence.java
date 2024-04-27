package com.github.hummel.mcda.engine;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class RichPresence {
	public static final RichPresence INSTANCE = new RichPresence();
	public static final DiscordRPC RPC = DiscordRPC.INSTANCE;

	private final DiscordRichPresence presence = new DiscordRichPresence();
	private ClientState state = ClientState.DISABLED;

	private RichPresence() {
	}

	public ClientState getState() {
		return state;
	}

	public void setState(ClientState state) {
		if (state == ClientState.DISABLED) {
			return;
		}
		this.state = state;
	}

	public void preInit() {
		try {
			String applicationId = Settings.getDiscordAppId();
			DiscordEventHandlers handlers = new DiscordEventHandlers();
			handlers.ready = user -> System.out.println("Rich ready!");
			RPC.Discord_Initialize(applicationId, handlers, true, null);
			presence.startTimestamp = System.currentTimeMillis() / 1000L;
			presence.largeImageKey = Settings.getMainLogo();
			presence.smallImageKey = Settings.getMainLogoMin();
			presence.largeImageText = Settings.getMainLabel();
			presence.smallImageText = Settings.getCategory();
			RPC.Discord_UpdatePresence(presence);
			state = ClientState.LOADING;
			ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
			executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
			executor.scheduleAtFixedRate(() -> {
				try {
					RichPresenceHelper.onRichUpdate(this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (state != ClientState.DISABLED) {
					RPC.Discord_RunCallbacks();
				}
			}, 0L, 2L, TimeUnit.SECONDS);
			Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownRunnable(this, executor), "Discord Rich Presence Shutdown"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Consumer<DiscordRichPresence> consumer) {
		if (state == ClientState.DISABLED) {
			return;
		}
		try {
			consumer.accept(presence);
		} catch (Exception ignored) {
		}
		try {
			RPC.Discord_UpdatePresence(presence);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

