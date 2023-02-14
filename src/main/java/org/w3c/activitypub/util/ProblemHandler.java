package org.w3c.activitypub.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.activitystreams.model.BaseObjectOrLink;
import org.w3c.activitystreams.model.LinkImpl;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class ProblemHandler extends DeserializationProblemHandler {

	private static final Logger log = LoggerFactory.getLogger(ProblemHandler.class);

	@Override
	public JavaType handleMissingTypeId(DeserializationContext ctxt, JavaType baseType, TypeIdResolver idResolver,
			String failureMsg) throws IOException {
		log.debug("handleMissingTypeId: rawClass: {}", baseType.getRawClass());
		if (baseType.getRawClass() == BaseObjectOrLink.class) {
			return TypeFactory.defaultInstance().constructType(LinkImpl.class);
		} else {
			return TypeFactory.defaultInstance().constructType(baseType.getRawClass());
		}

	}


}
