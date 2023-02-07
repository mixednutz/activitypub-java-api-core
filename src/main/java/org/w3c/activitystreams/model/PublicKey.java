package org.w3c.activitystreams.model;

import java.net.URI;

public class PublicKey {

	URI id;
	String keyName;
	URI owner;
	String publicKeyPem;
	
	
	public PublicKey(String name, URI owner, String publicKeyPem) {
		super();
		this.id = URI.create(owner.toString()+"#"+name);
		this.owner = owner;
		this.publicKeyPem = publicKeyPem;
	}
	public PublicKey() {
		super();
	}
	public URI getId() {
		return id;
	}
	public void setId(URI id) {
		this.id = id;
	}
	public URI getOwner() {
		return owner;
	}
	public void setOwner(URI owner) {
		this.owner = owner;
	}
	public String getPublicKeyPem() {
		return publicKeyPem;
	}
	public void setPublicKeyPem(String publicKeyPem) {
		this.publicKeyPem = publicKeyPem;
	}
	
	
	
}
