package org.w3c.activitystreams.model.activity;

import org.w3c.activitystreams.model.ActivityImpl;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Create")
public class Create extends ActivityImpl {

	public Create() {
		super("Create");
	}

}
