package org.w3c.activitystreams.model.activity;

import org.w3c.activitystreams.model.ActivityImpl;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Follow")
public class Follow extends ActivityImpl  {
	
	public Follow() {
		super("Follow");
	}

}
