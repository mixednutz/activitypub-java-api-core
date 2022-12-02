package org.w3c.activitystreams.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("CollectionPage")
public class CollectionPageImpl<C extends java.util.Collection<BaseObjectOrLink>> extends BaseCollectionPageImpl<C> {

}
