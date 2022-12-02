package org.w3c.activitystreams.model.actor;

import org.w3c.activitystreams.model.ActorImpl;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Person")
public class Person extends ActorImpl {

	public Person() {
		super("Person");
	}

}
