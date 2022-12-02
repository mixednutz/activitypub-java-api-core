package org.w3c.activitystreams.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("OrderedCollection")
public class OrderedCollectionImpl extends BaseCollectionImpl<List<BaseObjectOrLink>> {

	private Integer startIndex;

	public OrderedCollectionImpl() {
		super("OrderedCollection");
	}

	
	@JsonProperty("orderedItems")
	@Override
	public List<BaseObjectOrLink> getItems() {
		return super.getItems();
	}

	@JsonProperty("orderedItems")
	@Override
	public void setItems(List<BaseObjectOrLink> items) {
		super.setItems(items);
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	
}
