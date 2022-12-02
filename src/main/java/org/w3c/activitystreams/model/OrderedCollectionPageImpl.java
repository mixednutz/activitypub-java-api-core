package org.w3c.activitystreams.model;

import java.net.URI;
import java.util.List;

import org.w3c.activitystreams.ObjectOrLink;
import org.w3c.activitystreams.OrderedCollectionPage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("OrderedCollectionPage")
public class OrderedCollectionPageImpl extends BaseCollectionPageImpl<List<BaseObjectOrLink>>
		implements OrderedCollectionPage<BaseObjectOrLink> {

	private URI partOf;

	public OrderedCollectionPageImpl() {
		super("OrderedCollectionPage");
	}

	public URI getPartOf() {
		return partOf;
	}

	public void setPartOf(URI partOf) {
		this.partOf = partOf;
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

}
