package org.w3c.activitystreams.model.actor;

import org.w3c.activitystreams.model.ActorImpl;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Application")
public class Application extends ActorImpl {
	
	public Application() {
		super("Application");
	}

}
