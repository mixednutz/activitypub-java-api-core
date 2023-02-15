package org.w3c.activitystreams.model.activity;

import org.w3c.activitystreams.model.ActivityImpl;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Delete")
public class Delete extends ActivityImpl  {

	public Delete() {
		super("Delete");
	}
	
}
