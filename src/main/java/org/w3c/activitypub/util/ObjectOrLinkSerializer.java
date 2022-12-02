package org.w3c.activitypub.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.activitystreams.model.BaseObjectOrLink;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ObjectOrLinkSerializer extends StdSerializer<BaseObjectOrLink> {
	
	private static Logger log = LoggerFactory.getLogger(ObjectOrLinkSerializer.class);

	protected ObjectOrLinkSerializer() {
		super(BaseObjectOrLink.class);
	    JsonSerializer js;
	}

	 @Override
	   public void serialize(BaseObjectOrLink value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
	      log.debug("serialize: value: {}", value);
	      if (value.getHref() != null)
	         gen.writeString(value.getHref().toString());
	      else
	         gen.writeObject(value);
	   }

}
