package org.w3c.activitystreams.model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Collection")
public class CollectionImpl extends BaseCollectionImpl<Collection<BaseObjectOrLink>> {

	public CollectionImpl() {
		super("Collection");
	}


}
