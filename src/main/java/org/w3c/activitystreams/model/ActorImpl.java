package org.w3c.activitystreams.model;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.w3c.activitypub.Actor;
import org.w3c.activitypub.util.PropertyListSerializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonTypeName ("Actor")
public class ActorImpl extends org.w3c.activitystreams.model.BaseObjectOrLink implements Actor {
	
	
	private URI inbox;
	private URI outbox;
	private URI followers;
	private URI following;
	private Map<String, URI> endpoints;
	
	private String preferredUsername;
	
	@JsonFormat (with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@JsonSerialize (using = PropertyListSerializer.class)
	private List<BaseObjectOrLink> icon;
	
	private PublicKey publicKey;
	
	public ActorImpl(String type) {
		super(type);
	}
	public ActorImpl() {
		super("Actor");
	}
	
	public URI getInbox() {
		return inbox;
	}
	public void setInbox(URI inbox) {
		this.inbox = inbox;
	}
	public URI getOutbox() {
		return outbox;
	}
	public void setOutbox(URI outbox) {
		this.outbox = outbox;
	}
	public URI getFollowers() {
		return followers;
	}
	public void setFollowers(URI followers) {
		this.followers = followers;
	}
	public URI getFollowing() {
		return following;
	}
	public void setFollowing(URI following) {
		this.following = following;
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
	public PublicKey getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	public Map<String, URI> getEndpoints() {
		return endpoints;
	}
	public void setEndpoints(Map<String, URI> endpoints) {
		this.endpoints = endpoints;
	}

}
