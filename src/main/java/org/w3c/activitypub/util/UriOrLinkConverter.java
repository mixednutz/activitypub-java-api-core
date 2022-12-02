package org.w3c.activitypub.util;

import org.w3c.activitystreams.Link;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

public class UriOrLinkConverter implements Converter<String, Link> {

	@Override
	public Link convert(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

}
