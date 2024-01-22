package club.minnced.discord.rpc;

import com.sun.jna.Structure;

import java.util.Objects;

@SuppressWarnings({"WeakerAccess", "PublicField"})
@Structure.FieldOrder({"userId", "username", "discriminator", "avatar"})
public class DiscordUser extends Structure {
	public String userId;
	public String username;
	public String discriminator;
	public String avatar;

	public DiscordUser() {
		this("UTF-8");
	}

	public DiscordUser(String encoding) {
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
	public int hashCode() {
		return Objects.hash(userId, username, discriminator, avatar);
	}
}

