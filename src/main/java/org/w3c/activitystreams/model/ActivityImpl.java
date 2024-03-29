package org.w3c.activitystreams.model;

import org.springframework.http.MediaType;
import org.w3c.activitypub.util.PropertySerializer;
import org.w3c.activitystreams.Activity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonTypeName ("Activity")
public class ActivityImpl extends BaseObjectOrLink implements Activity {
	
	
	public static final MediaType APPLICATION_ACTIVITY;
	public static final String APPLICATION_ACTIVITY_VALUE = "application/activity+json";
	
	static {
		APPLICATION_ACTIVITY = new MediaType("application", "activity+json");
	}

	public ActivityImpl(String type) {
		super(type);
	}
	public ActivityImpl() {
		super("Activity");
	}
	
	@JsonSerialize (using = PropertySerializer.class)
	BaseObjectOrLink actor;
	@JsonSerialize (using = PropertySerializer.class)
	BaseObjectOrLink object;

	public BaseObjectOrLink getActor() {
		return actor;
	}
	public void setActor(BaseObjectOrLink actor) {
		this.actor = actor;
	}
	public BaseObjectOrLink getObject() {
		return object;
	}
	public void setObject(BaseObjectOrLink object) {
		this.object = object;
	}

}
