package org.w3c.activitypub.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.activitystreams.Link;
import org.w3c.activitystreams.model.BaseObjectOrLink;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class PropertySerializer extends StdSerializer<BaseObjectOrLink> {

	   private static Logger log = LoggerFactory.getLogger(PropertySerializer.class);

	   public PropertySerializer() {
	      super(BaseObjectOrLink.class);
	   }

	   @Override
	   public void serialize(BaseObjectOrLink value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
	      log.debug("serialize: value: {}", value);
	      if (value instanceof Link && ((Link) value).getHref() != null) {
	    	  gen.writeString(((Link) value).getHref().toString()); 
	      }
	      else {
	    	  gen.writeObject(value);
	      }
	   }

	   @Override
	   public void serializeWithType(BaseObjectOrLink value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
	      log.debug("serializeWithType: value.getClass: {}", value.getClass());
	      log.debug("serializeWithType: value: {}", value);
	      if (value.isLink()) {
	         gen.writeString(((Link) value).getHref().toString());
	      } else {
	         gen.writeObject(value);
	      }
	   }
	
}
