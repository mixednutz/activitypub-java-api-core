package org.w3c.activitystreams.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Note")
public class Note extends BaseObjectOrLink {

	public Note() {
		super("Note");
	}

}
