package club.minnced.discord.rpc;

import com.sun.jna.Structure;

import java.util.Objects;

@SuppressWarnings({"WeakerAccess", "PublicField"})
@Structure.FieldOrder({"state", "details", "startTimestamp", "endTimestamp", "largeImageKey", "largeImageText", "smallImageKey", "smallImageText", "partyId", "partySize", "partyMax", "matchSecret", "joinSecret", "spectateSecret", "instance"})
public class DiscordRichPresence extends Structure {
	public String state;
	public String details;
	public long startTimestamp;
	public long endTimestamp;
	public String largeImageKey;
	public String largeImageText;
	public String smallImageKey;
	public String smallImageText;
	public String partyId;
	public int partySize;
	public int partyMax;
	public String matchSecret;
	public String joinSecret;
	public String spectateSecret;
	public byte instance;

	public DiscordRichPresence() {
		this("UTF-8");
	}

	public DiscordRichPresence(String encoding) {
		setStringEncoding(encoding);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof DiscordRichPresence)) {
			return false;
		}
		DiscordRichPresence presence = (DiscordRichPresence) o;
		return startTimestamp == presence.startTimestamp && endTimestamp == presence.endTimestamp && partySize == presence.partySize && partyMax == presence.partyMax && instance == presence.instance && Objects.equals(state, presence.state) && Objects.equals(details, presence.details) && Objects.equals(largeImageKey, presence.largeImageKey) && Objects.equals(largeImageText, presence.largeImageText) && Objects.equals(smallImageKey, presence.smallImageKey) && Objects.equals(smallImageText, presence.smallImageText) && Objects.equals(partyId, presence.partyId) && Objects.equals(matchSecret, presence.matchSecret) && Objects.equals(joinSecret, presence.joinSecret) && Objects.equals(spectateSecret, presence.spectateSecret);
	}

	@Override
	public int hashCode() {
		return Objects.hash(state, details, startTimestamp, endTimestamp, largeImageKey, largeImageText, smallImageKey, smallImageText, partyId, partySize, partyMax, matchSecret, joinSecret, spectateSecret, instance);
	}
}

