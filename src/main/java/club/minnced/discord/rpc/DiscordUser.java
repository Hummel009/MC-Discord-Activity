package club.minnced.discord.rpc;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscordUser extends Structure {
	private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList("userId", "username", "discriminator", "avatar"));
	private String userId;
	private String username;
	private String discriminator;
	private String avatar;

	public DiscordUser() {
		this("UTF-8");
	}

	private DiscordUser(String encoding) {
		setStringEncoding(encoding);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof DiscordUser)) {
			return false;
		}
		DiscordUser that = (DiscordUser) o;
		return Objects.equals(userId, that.userId) && Objects.equals(username, that.username) && Objects.equals(discriminator, that.discriminator) && Objects.equals(avatar, that.avatar);
	}

	@Override
	protected List<String> getFieldOrder() {
		return FIELD_ORDER;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, username, discriminator, avatar);
	}
}

