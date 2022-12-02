package org.w3c.activitystreams.model;

import org.w3c.activitypub.util.PropertySerializer;
import org.w3c.activitypub.util.PropertyListSerializer;
import org.w3c.activitystreams.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class BaseCollectionImpl<C extends java.util.Collection<BaseObjectOrLink>> extends org.w3c.activitystreams.model.BaseObjectOrLink 
	implements Collection<BaseObjectOrLink> {

	
	Long totalItems;
	@JsonSerialize (using = PropertySerializer.class)
	BaseObjectOrLink current;
	@JsonSerialize (using = PropertySerializer.class)
	BaseObjectOrLink first;
	@JsonSerialize (using = PropertySerializer.class)
	BaseObjectOrLink last;
	@JsonSerialize (using = PropertyListSerializer.class)
	@JsonFormat (with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	C items;
	
	public BaseCollectionImpl(String type) {
		super(type);
	}
	
	public Long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}
	
	public BaseObjectOrLink getCurrent() {
		return current;
	}

	public void setCurrent(BaseObjectOrLink current) {
		this.current = current;
	}

	public BaseObjectOrLink getFirst() {
		return first;
	}

	public void setFirst(BaseObjectOrLink first) {
		this.first = first;
	}

	public BaseObjectOrLink getLast() {
		return last;
	}

	public void setLast(BaseObjectOrLink last) {
		this.last = last;
	}

	public C getItems() {
		return items;
	}
	public void setItems(C items) {
		this.items = items;
	}
	
}
