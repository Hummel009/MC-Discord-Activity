package club.minnced.discord.rpc;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscordRichPresence extends Structure {
	private static final List<String> FIELD_ORDER = Collections.unmodifiableList(Arrays.asList("state", "details", "startTimestamp", "endTimestamp", "largeImageKey", "largeImageText", "smallImageKey", "smallImageText", "partyId", "partySize", "partyMax", "matchSecret", "joinSecret", "spectateSecret", "instance"));
	private String state;
	private String details;
	private long startTimestamp;
	private long endTimestamp;
	private String largeImageKey;
	private String largeImageText;
	private String smallImageKey;
	private String smallImageText;
	private String partyId;
	private int partySize;
	private int partyMax;
	private String matchSecret;
	private String joinSecret;
	private String spectateSecret;
	private byte instance;

	public DiscordRichPresence() {
		this("UTF-8");
	}

	private DiscordRichPresence(String encoding) {
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
	protected List<String> getFieldOrder() {
		return FIELD_ORDER;
	}

	public String getLargeImageKey() {
		return largeImageKey;
	}

	public void setLargeImageKey(String largeImageKey) {
		this.largeImageKey = largeImageKey;
	}

	public String getLargeImageText() {
		return largeImageText;
	}

	public void setLargeImageText(String largeImageText) {
		this.largeImageText = largeImageText;
	}

	public String getSmallImageKey() {
		return smallImageKey;
	}

	public void setSmallImageKey(String smallImageKey) {
		this.smallImageKey = smallImageKey;
	}

	public String getSmallImageText() {
		return smallImageText;
	}

	public void setSmallImageText(String smallImageText) {
		this.smallImageText = smallImageText;
	}

	public long getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(long startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		return Objects.hash(state, details, startTimestamp, endTimestamp, largeImageKey, largeImageText, smallImageKey, smallImageText, partyId, partySize, partyMax, matchSecret, joinSecret, spectateSecret, instance);
	}
}

