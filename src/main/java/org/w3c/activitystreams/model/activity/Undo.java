package org.w3c.activitystreams.model.activity;

import org.w3c.activitystreams.model.ActivityImpl;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Undo")
public class Undo extends ActivityImpl  {
	
	public Undo() {
		super("Undo");
	}
	
}
