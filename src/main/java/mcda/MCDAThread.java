package mcda;

import java.util.concurrent.ScheduledThreadPoolExecutor;

class MCDAThread extends Thread {
	private final MCDARichPresence mcdaRichPresence;
	private final ScheduledThreadPoolExecutor executor;

	MCDAThread(MCDARichPresence mcdaRichPresence, ScheduledThreadPoolExecutor executor) {
		super("Discord Rich Presence Shutdown");
		this.mcdaRichPresence = mcdaRichPresence;
		this.executor = executor;
	}

	private void disable() {
		if (mcdaRichPresence.getState() == MCDAClientState.DISABLED) {
			return;
		}
		mcdaRichPresence.getRpc().Discord_ClearPresence();
		mcdaRichPresence.getRpc().Discord_Shutdown();
		mcdaRichPresence.setState(MCDAClientState.DISABLED);
	}

	@Override
	public void run() {
		executor.shutdown();
		disable();
	}
}
