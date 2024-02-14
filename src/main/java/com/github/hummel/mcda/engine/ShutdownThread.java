package com.github.hummel.mcda.engine;

import java.util.concurrent.ScheduledThreadPoolExecutor;

class ShutdownThread extends Thread {
	private final RichPresence richPresence;
	private final ScheduledThreadPoolExecutor executor;

	ShutdownThread(RichPresence richPresence, ScheduledThreadPoolExecutor executor) {
		super("Discord Rich Presence Shutdown");
		this.richPresence = richPresence;
		this.executor = executor;
	}

	private void disable() {
		if (richPresence.getState() == ClientState.DISABLED) {
			return;
		}
		RichPresence.RPC.Discord_ClearPresence();
		RichPresence.RPC.Discord_Shutdown();
		richPresence.setState(ClientState.DISABLED);
	}

	@Override
	public void run() {
		executor.shutdown();
		disable();
	}
}
