package org.w3c.activitypub.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ContextListSerializer extends StdSerializer<List<Object>> {

	private static Logger log = LoggerFactory.getLogger(ContextListSerializer.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	private static JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class,
			HashMap.class);

	public ContextListSerializer() {
		super(javaType);
	}

	protected ContextListSerializer(JavaType type) {
		super(type);
		log.debug("Constructor: javaType: {}", type);
	}

	// Weirdly serializing objects whose 'type' is not one of our types "just
	// works".
	// Apparently Jackson doesn't care what the type var says on serialization
	@Override
	public void serialize(List<Object> list, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		log.debug("serialize: list: {}", list);
		if (list.size() > 1) {
			gen.writeStartArray(list.size());
			gen.writeEndArray();
		} else if (list.size() == 1) {
			serializeWithType_(list.get(0), gen, serializers, null);
		}
	}

	private void serializeWithType_(Object value, JsonGenerator gen, SerializerProvider serializers,
			TypeSerializer typeSer) throws IOException {
		log.debug("serializeWithType: value.getClass: {}", value.getClass());
		log.debug("serializeWithType: value: {}", value);
		gen.writeObject(value);
	}

	@Override
	public void serializeWithType(List<Object> list, JsonGenerator gen, SerializerProvider serializers,
			TypeSerializer typeSer) throws IOException {
		log.debug("serializeWithType: list.getClass: {}", list.getClass());
		log.debug("serializeWithType: list: {}", list);

		if (list.size() > 1) {
			gen.writeStartArray(list.size());
			gen.writeEndArray();
		} else if (list.size() == 1) {
			serializeWithType_(list.get(0), gen, serializers, typeSer);
		}
	}

}
