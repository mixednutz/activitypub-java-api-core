package org.w3c.activitypub.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.activitystreams.model.BaseObjectOrLink;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ObjectOrLinkDeserializer extends StdDeserializer<BaseObjectOrLink>{
	
	private static Logger log = LoggerFactory.getLogger(ObjectOrLinkDeserializer.class);

	ObjectMapper objectMapper = new ObjectMapper();

   protected ObjectOrLinkDeserializer(Class<?> vc) {
      super(vc);
      log.debug("deserialize: protected constructor: vc: {}", vc);
   }

   public ObjectOrLinkDeserializer() {
      super(BaseObjectOrLink.class);
      log.debug("deserialize: no-arg public constructor");
   }

   

   @Override
   public BaseObjectOrLink deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
      log.debug("deserialize");
      BaseObjectOrLink item = (BaseObjectOrLink) new Object();
//      try {
//         item = (BaseObjectOrLink) objectMapper.readValue(p, Actor.class);
//      } catch (JsonProcessingException e) {
//         log.debug("deserialize: currentName: {} currentToken: {}", p.currentName(), p.currentToken());
//         item.setType("Actor");
//         item.isLink(p.getText());
//      }
      log.debug("deserialize: item: {}", item);
      return item;
   }
	
}
