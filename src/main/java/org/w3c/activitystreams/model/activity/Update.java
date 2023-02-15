package org.w3c.activitystreams.model.activity;

import org.w3c.activitystreams.model.ActivityImpl;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Update")
public class Update extends ActivityImpl  {
	
	public Update() {
		super("Update");
	}
	
}
