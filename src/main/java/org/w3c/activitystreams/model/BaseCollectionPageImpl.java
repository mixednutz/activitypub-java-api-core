package org.w3c.activitystreams.model;

import java.net.URI;

import org.w3c.activitypub.util.PropertySerializer;
import org.w3c.activitystreams.CollectionPage;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class BaseCollectionPageImpl<C extends java.util.Collection<BaseObjectOrLink>> 
	extends BaseCollectionImpl<C> implements CollectionPage<BaseObjectOrLink> {

	@JsonSerialize (using = PropertySerializer.class)
	BaseObjectOrLink next;
	@JsonSerialize (using = PropertySerializer.class)
	BaseObjectOrLink prev;
	URI partOf;
	
	public BaseCollectionPageImpl() {
		super("CollectionPage");
	}
	public BaseCollectionPageImpl(String type) {
		super(type);
	}
	public BaseObjectOrLink getNext() {
		return next;
	}
	public void setNext(BaseObjectOrLink next) {
		this.next = next;
	}
	public BaseObjectOrLink getPrev() {
		return prev;
	}
	public void setPrev(BaseObjectOrLink prev) {
		this.prev = prev;
	}
	public URI getPartOf() {
		return partOf;
	}
	public void setPartOf(URI partOf) {
		this.partOf = partOf;
	}
	
	
}
