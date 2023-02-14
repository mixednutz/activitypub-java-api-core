package org.w3c.activitystreams.model.activity;

import org.w3c.activitystreams.model.ActivityImpl;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Accept")
public class Accept extends ActivityImpl  {
	
	public Accept() {
		super("Accept");
	}

}
