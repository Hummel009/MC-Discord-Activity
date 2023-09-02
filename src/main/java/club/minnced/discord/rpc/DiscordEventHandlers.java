package club.minnced.discord.rpc;

import com.sun.jna.Callback;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscordEventHandlers extends Structure {
	private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest"));
	public OnReady ready;
	public OnStatus disconnected;
	public OnStatus errored;
	public OnGameUpdate joinGame;
	public OnGameUpdate spectateGame;
	public OnJoinRequest joinRequest;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof DiscordEventHandlers)) {
			return false;
		}
		DiscordEventHandlers that = (DiscordEventHandlers) o;
		return Objects.equals(ready, that.ready) && Objects.equals(disconnected, that.disconnected) && Objects.equals(errored, that.errored) && Objects.equals(joinGame, that.joinGame) && Objects.equals(spectateGame, that.spectateGame) && Objects.equals(joinRequest, that.joinRequest);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ready, disconnected, errored, joinGame, spectateGame, joinRequest);
	}

	@Override
	protected List<String> getFieldOrder() {
		return FIELD_ORDER;
	}

	public interface OnJoinRequest
			extends Callback {
		void accept(DiscordUser var1);
	}

	public interface OnGameUpdate
			extends Callback {
		void accept(String var1);
	}

	public interface OnStatus
			extends Callback {
		void accept(int var1, String var2);
	}

	public interface OnReady
			extends Callback {
		void accept(DiscordUser var1);
	}

}

