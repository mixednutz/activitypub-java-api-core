package org.w3c.activitystreams.model;

import java.net.URI;
import java.util.List;

import org.w3c.activitypub.Actor;
import org.w3c.activitypub.util.PropertyListSerializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonTypeName ("Actor")
public class ActorImpl extends org.w3c.activitystreams.model.BaseObjectOrLink implements Actor {
	
	
	private String inbox;
	private URI outbox;
	
	private String preferredUsername;
	
	@JsonFormat (with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@JsonSerialize (using = PropertyListSerializer.class)
	private List<BaseObjectOrLink> icon;
	
	public ActorImpl(String type) {
		super(type);
	}
	public ActorImpl() {
		super("Actor");
	}
	
	public String getInbox() {
		return inbox;
	}
	public void setInbox(String inbox) {
		this.inbox = inbox;
	}
	public URI getOutbox() {
		return outbox;
	}
	public void setOutbox(URI outbox) {
		this.outbox = outbox;
	}
	public String getPreferredUsername() {
		return preferredUsername;
	}
	public void setPreferredUsername(String preferredUsername) {
		this.preferredUsername = preferredUsername;
	}
	public List<BaseObjectOrLink> getIcon() {
		return icon;
	}
	public void setIcon(List<BaseObjectOrLink> icon) {
		this.icon = icon;
	}

}
