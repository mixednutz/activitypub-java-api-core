package org.w3c.activitystreams;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.activitypub.util.ProblemHandler;
import org.w3c.activitystreams.model.BaseObjectOrLink;
import org.w3c.activitystreams.model.ImageImpl;
import org.w3c.activitystreams.model.LinkImpl;
import org.w3c.activitystreams.model.OrderedCollectionImpl;
import org.w3c.activitystreams.model.OrderedCollectionPageImpl;
import org.w3c.activitystreams.model.activity.Create;
import org.w3c.activitystreams.model.actor.Person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
	
	ObjectMapper mapper;
	
	@BeforeEach
	public void setup() {
		mapper = new ObjectMapper();
		mapper.addHandler(new ProblemHandler());
	}
	
	public void testAcceptActivity() {
		
	}
	
	@Test
	public void testFollowActivity() {
		
	}
	
	@Test
	public void testOrderedCollectionLinks() throws JsonProcessingException {
		String json = "{\"@context\":\"https://www.w3.org/ns/activitystreams\","
				+ "\"type\":\"OrderedCollection\","
				+ "\"id\":\"https://mixednutz.net/user/followers\","
				+ "\"orderedItems\":["
					+ "\"http://mixednutz.net\","
					+ "\"http://andrewfesta.com\","
					+ "\"https://www.google.com\"],"
				+ "\"totalItems\":3}";
		
		// Deserialize
		BaseObjectOrLink object = mapper.readValue(json, BaseObjectOrLink.class);
		
		assertTrue(object instanceof OrderedCollectionImpl);
		OrderedCollectionImpl orderedcollection = (OrderedCollectionImpl) object;
		assertEquals("https://mixednutz.net/user/followers", orderedcollection.getId().toString());
		assertEquals(3L, orderedcollection.getTotalItems());
		assertEquals(3, orderedcollection.getItems().size());
		assertTrue(orderedcollection.getItems().get(0) instanceof Link);
		Link link = (Link)orderedcollection.getItems().get(0);
		assertEquals("http://mixednutz.net", link.getHref().toString());
		
		//Serialize new object from scratch
		orderedcollection = new OrderedCollectionImpl();
		orderedcollection.set_Context(BaseObjectOrLink.CONTEXT);
		orderedcollection.setItems(List.of(new LinkImpl("http://mixednutz.net"), new LinkImpl("http://andrewfesta.com"), new LinkImpl("https://www.google.com")));
		orderedcollection.setTotalItems(3L);
		orderedcollection.setId(URI.create("https://mixednutz.net/user/followers"));
		
	
		String actual = mapper.writeValueAsString(orderedcollection);
		assertEquals(json, actual);
	}
	
	@Test
	public void testOrderedCollectionPage() throws JsonProcessingException {
		String json = "{\"@context\":\"https://www.w3.org/ns/activitystreams\","
				+ "\"type\":\"OrderedCollectionPage\","
				+ "\"id\":\"https://mixednutz.net/user/outbox/next\","
				+ "\"next\":\"https://mixednutz.net/user/outbox/next?start=4&pageSize=3\","
				+ "\"orderedItems\":["
					+ "{\"type\":\"Create\"},"
					+ "{\"type\":\"Create\"},"
					+ "{\"type\":\"Create\"}],"
				+ "\"partOf\":\"https://mixednutz.net/user/outbox\","
				+ "\"totalItems\":3}";
		
		// Deserialize
		BaseObjectOrLink object = mapper.readValue(json, BaseObjectOrLink.class);
		
		assertTrue(object instanceof OrderedCollectionPageImpl);
		OrderedCollectionPageImpl orderedcollection = (OrderedCollectionPageImpl) object;
		assertEquals("https://mixednutz.net/user/outbox/next", orderedcollection.getId().toString());
		assertEquals("https://mixednutz.net/user/outbox", orderedcollection.getPartOf().toString());
		assertTrue(orderedcollection.getNext() instanceof LinkImpl);
		Link next = (Link)orderedcollection.getNext();
		assertEquals("https://mixednutz.net/user/outbox/next?start=4&pageSize=3", next.getHref().toString());
		assertEquals(3L, orderedcollection.getTotalItems());
		assertEquals(3, orderedcollection.getItems().size());
		
		//Serialize new object from scratch
		orderedcollection = new OrderedCollectionPageImpl();
		orderedcollection.set_Context(BaseObjectOrLink.CONTEXT);
		orderedcollection.setItems(List.of(new Create(), new Create(), new Create()));
		orderedcollection.setTotalItems(3L);
		orderedcollection.setPartOf(URI.create("https://mixednutz.net/user/outbox"));
		orderedcollection.setId(URI.create("https://mixednutz.net/user/outbox/next"));
		orderedcollection.setNext(new LinkImpl(URI.create("https://mixednutz.net/user/outbox/next?start=4&pageSize=3")));
	
		String actual = mapper.writeValueAsString(orderedcollection);
		assertEquals(json, actual);
	}
	
	@Test
	public void testOrderedCollection1() throws JsonProcessingException {
		String json = "{\"@context\":\"https://www.w3.org/ns/activitystreams\","
				+ "\"type\":\"OrderedCollection\","
				+ "\"id\":\"https://mixednutz.net/user/outbox\","
				+ "\"first\":{"
					+ "\"type\":\"Link\","
					+ "\"href\":\"https://mixednutz.net/user/outbox/next\"}"
				+ "}";
		
		// Deserialize
		BaseObjectOrLink object = mapper.readValue(json, BaseObjectOrLink.class);
		
		assertTrue(object instanceof OrderedCollectionImpl);
		OrderedCollectionImpl orderedcollection = (OrderedCollectionImpl) object;
		assertEquals("https://mixednutz.net/user/outbox",orderedcollection.getId().toString());
		assertTrue(orderedcollection.getFirst() instanceof LinkImpl);
		Link first = (Link)orderedcollection.getFirst();
		assertEquals("https://mixednutz.net/user/outbox/next", first.getHref().toString());
	}
	
	@Test
	public void testOrderedCollection2() throws JsonProcessingException {
		String json = "{\"@context\":\"https://www.w3.org/ns/activitystreams\","
				+ "\"type\":\"OrderedCollection\","
				+ "\"id\":\"https://mixednutz.net/user/outbox\","
				+ "\"first\":\"https://mixednutz.net/user/outbox/next\"}";
		
		// Deserialize
		BaseObjectOrLink object = mapper.readValue(json, BaseObjectOrLink.class);
		
		assertTrue(object instanceof OrderedCollectionImpl);
		OrderedCollectionImpl orderedcollection = (OrderedCollectionImpl) object;
		assertEquals("https://mixednutz.net/user/outbox",orderedcollection.getId().toString());
		assertTrue(orderedcollection.getFirst() instanceof LinkImpl);
		Link first = (Link)orderedcollection.getFirst();
		assertEquals("https://mixednutz.net/user/outbox/next", first.getHref().toString());
		
		//Re-serialize
		String actual = mapper.writeValueAsString(orderedcollection);
		assertEquals(json, actual);
		
		//Serialize new object from scratch
		orderedcollection = new OrderedCollectionImpl();
		orderedcollection.set_Context(BaseObjectOrLink.CONTEXT);
		orderedcollection.setId(URI.create("https://mixednutz.net/user/outbox"));
		orderedcollection.setFirst(new LinkImpl(URI.create("https://mixednutz.net/user/outbox/next")));
		
		actual = mapper.writeValueAsString(orderedcollection);
		assertEquals(json, actual);
	}

	@Test
	public void testPerson() throws JsonProcessingException {
		
		String json = "{\"@context\":\"https://www.w3.org/ns/activitystreams\","
				+ "\"type\":\"Person\","
				+ "\"id\":\"https://mixednutz.net/user\","
				+ "\"inbox\":\"Not Implemented\","
				+ "\"outbox\":\"https://mixednutz.net/outbox\","
				+ "\"preferredUsername\":\"Andy\"}";
		
		// Deserialize
		BaseObjectOrLink object = mapper.readValue(json, BaseObjectOrLink.class);
		
		assertTrue(object instanceof Person);
		Person person = (Person) object;
		assertEquals("https://mixednutz.net/user",person.getId().toString());
		assertEquals("Not Implemented",person.getInbox().toString());
		assertEquals("https://mixednutz.net/outbox",person.getOutbox().toString());
		assertEquals("Andy",person.getPreferredUsername());
		
		//Re-serialize
		String actual = mapper.writeValueAsString(person);
		assertEquals(json, actual);
		
		//Serialize new object from scratch
		person = new Person();
		person.set_Context(BaseObjectOrLink.CONTEXT);
		person.setId(URI.create("https://mixednutz.net/user"));
		person.setPreferredUsername("Andy");
		person.setInbox("Not Implemented");
		person.setOutbox(URI.create("https://mixednutz.net/outbox"));
		
		actual = mapper.writeValueAsString(person);
		assertEquals(json, actual);
	}
	
	@Test
	public void testPersonWithIcon() throws JsonProcessingException {
		
		String json = "{\"@context\":\"https://www.w3.org/ns/activitystreams\","
				+ "\"type\":\"Person\","
				+ "\"id\":\"https://mixednutz.net/user\","
				+ "\"icon\":{"
					+ "\"type\":\"Image\","
					+ "\"name\":\"Andy's avatar\","
					+ "\"url\":\"https://mixednutz.net/avatar.jpg\"},"
				+ "\"inbox\":\"Not Implemented\","
				+ "\"outbox\":\"https://mixednutz.net/outbox\","
				+ "\"preferredUsername\":\"Andy\"}";
		
		// Deserialize
		BaseObjectOrLink object = mapper.readValue(json, BaseObjectOrLink.class);
		
		assertTrue(object instanceof Person);
		Person person = (Person) object;
		assertEquals("https://mixednutz.net/user",person.getId().toString());
		assertEquals("Not Implemented",person.getInbox().toString());
		assertEquals("https://mixednutz.net/outbox",person.getOutbox().toString());
		assertEquals("Andy",person.getPreferredUsername());
		assertNotNull(person.getIcon());
		assertNotNull(person.getIcon());
		assertEquals(1, person.getIcon().size());
		assertTrue(person.getIcon().get(0) instanceof ImageImpl);
		ImageImpl icon = (ImageImpl)person.getIcon().get(0);
		assertEquals("Andy's avatar", icon.getName());
		assertEquals("https://mixednutz.net/avatar.jpg", icon.getUrl());
		
		//Re-serialize
		String actual = mapper.writeValueAsString(person);
		assertEquals(json, actual);
		
		//Serialize new object from scratch
		person = new Person();
		person.set_Context(BaseObjectOrLink.CONTEXT);
		person.setId(URI.create("https://mixednutz.net/user"));
		person.setPreferredUsername("Andy");
		person.setInbox("Not Implemented");
		person.setOutbox(URI.create("https://mixednutz.net/outbox"));
		icon = new ImageImpl();
		icon.setUrl("https://mixednutz.net/avatar.jpg");
		icon.setName("Andy's avatar");
		person.setIcon(List.of(icon));
		
		actual = mapper.writeValueAsString(person);
		assertEquals(json, actual);
	}
	
}
