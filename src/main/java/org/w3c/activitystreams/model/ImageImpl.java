package org.w3c.activitystreams.model;

import org.w3c.activitystreams.Image;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Image")
public class ImageImpl extends BaseDocument implements Image {

	public ImageImpl() {
		super("Image");
	}

}
