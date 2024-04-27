package com.github.hummel.mcda.engine;

import java.util.concurrent.ScheduledThreadPoolExecutor;


class ShutdownRunnable implements Runnable {
	private final RichPresence richPresence;
	private final ScheduledThreadPoolExecutor executor;

	ShutdownRunnable(RichPresence richPresence, ScheduledThreadPoolExecutor executor) {
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