package org.w3c.activitystreams.model;

import java.net.URI;

import org.w3c.activitystreams.Link;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Link")
public class LinkImpl extends BaseObjectOrLink implements Link {

	public LinkImpl() {
		super("Link");
		this.setLink(true);
	}
	public LinkImpl(URI href) {
		this();
		this.href = href;	
	}
	public LinkImpl(String href) {
		this(URI.create(href));
	}
	
}
