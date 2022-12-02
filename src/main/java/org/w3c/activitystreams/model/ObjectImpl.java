package org.w3c.activitystreams.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Object")
public class ObjectImpl extends BaseObjectOrLink {

	public ObjectImpl() {
		super("Object");
	}

}
