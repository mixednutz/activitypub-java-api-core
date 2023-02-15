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
import org.w3c.activitystreams.model.activity.Follow;
import org.w3c.activitystreams.model.actor.Person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonTest {
	
	ObjectMapper mapper;
	
	@BeforeEach
	public void setup() {
		mapper = new ObjectMapper();
		mapper.addHandler(new ProblemHandler());
		JavaTimeModule javaTimeModule=new JavaTimeModule();
        mapper.registerModule(javaTimeModule);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
	
	public void testAcceptActivity() {
		
	}
	
	@Test
	public void testFollowActivity1() throws JsonProcessingException {
		String json ="{\"@context\":\"https://www.w3.org/ns/activitystreams\","
				+ "\"type\":\"Follow\","
				+ "\"actor\":{\"type\":\"Person\",\"id\":\"https://mixednutz.net/sally\"},"
				+ "\"object\":{\"type\":\"Person\",\"id\":\"https://mixednutz.net/john\"},"
				+ "\"summary\":\"Sally followed John\"}";
		
		// Deserialize
		BaseObjectOrLink object = mapper.readValue(json, BaseObjectOrLink.class);
				
		assertTrue(object instanceof Follow);
		Follow follow = (Follow) object;
		assertTrue(follow.getActor() instanceof Person);
		assertEquals("https://mixednutz.net/sally",((Person)follow.getActor()).getId().toString());
		assertTrue(follow.getObject() instanceof Person);
		assertEquals("https://mixednutz.net/john",((Person)follow.getObject()).getId().toString());
				
		//Serialize new object from scratch
		follow = new Follow();
		follow.setContext(List.of(BaseObjectOrLink.CONTEXT));
		follow.setSummary("Sally followed John");
		Person sally = new Person();
		sally.setId(URI.create("https://mixednutz.net/sally"));
		Person john = new Person();
		john.setId(URI.create("https://mixednutz.net/john"));
		follow.setActor(sally);
		follow.setObject(john);
		
		String actual = mapper.writeValueAsString(follow);
		assertEquals(json, actual);
	}
	
	@Test
	public void testFollowActivity2() throws JsonProcessingException {
		String json = "{\"@context\":\"https://www.w3.org/ns/activitystreams\","
				+ "\"type\":\"Follow\","
				+ "\"actor\":\"https://mixednutz.net/sally\","
				+ "\"object\":\"https://mixednutz.net/john\","
				+ "\"summary\":\"Sally followed John\"}";
		
		// Deserialize
		BaseObjectOrLink object = mapper.readValue(json, BaseObjectOrLink.class);
		
		assertTrue(object instanceof Follow);
		Follow follow = (Follow) object;
		assertTrue(follow.getActor() instanceof Link);
		assertEquals("https://mixednutz.net/sally",((Link)follow.getActor()).getHref().toString());
		assertTrue(follow.getObject() instanceof Link);
		assertEquals("https://mixednutz.net/john",((Link)follow.getObject()).getHref().toString());
		
		//Serialize new object from scratch
		follow = new Follow();
		follow.setContext(List.of(BaseObjectOrLink.CONTEXT));
		follow.setSummary("Sally followed John");
		follow.setActor(new LinkImpl(URI.create("https://mixednutz.net/sally")));
		follow.setObject(new LinkImpl(URI.create("https://mixednutz.net/john")));
		
		String actual = mapper.writeValueAsString(follow);
		assertEquals(json, actual);
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
		orderedcollection.setContext(List.of(BaseObjectOrLink.CONTEXT));
		orderedcollection.setItems(List.of(new LinkImpl("http://mixednutz.net"), new LinkImpl("http://andrewfesta.com"), new LinkImpl("https://www.google.com")));
		orderedcollection.setTotalItems(3L);
		orderedcollection.setId(URI.create("https://mixednutz.net/user/followers"));
		
	
		String actual = mapper.writeValueAsString(orderedcollection);
		assertEquals(json, actual);
	}
	
	@Test
	public void testOrderedCollectionEmpty() throws JsonProcessingException {
		String json = "{\"@context\":\"https://www.w3.org/ns/activitystreams\","
				+ "\"type\":\"OrderedCollection\","
				+ "\"id\":\"https://mixednutz.net/user/followers\","
				+ "\"orderedItems\":[],"
				+ "\"totalItems\":0}";
		
		// Deserialize
		BaseObjectOrLink object = mapper.readValue(json, BaseObjectOrLink.class);
		
		assertTrue(object instanceof OrderedCollectionImpl);
		OrderedCollectionImpl orderedcollection = (OrderedCollectionImpl) object;
		assertEquals("https://mixednutz.net/user/followers", orderedcollection.getId().toString());
		assertEquals(0L, orderedcollection.getTotalItems());
		assertTrue(orderedcollection.getItems().isEmpty());
		
		
		//Serialize new object from scratch
		orderedcollection = new OrderedCollectionImpl();
		orderedcollection.setContext(List.of(BaseObjectOrLink.CONTEXT));
		orderedcollection.setItems(List.of());
		orderedcollection.setTotalItems(0L);
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
		orderedcollection.setContext(List.of(BaseObjectOrLink.CONTEXT));
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
	public void testOrderedCollection2() throws JsonMappingException, JsonProcessingException  {
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
		String actual =null;
		try {
			actual = mapper.writeValueAsString(orderedcollection);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(json, actual);
		
		//Serialize new object from scratch
		orderedcollection = new OrderedCollectionImpl();
		orderedcollection.setContext(List.of(BaseObjectOrLink.CONTEXT));
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
				+ "\"inbox\":\"https://mixednutz.net/inbox\","
				+ "\"outbox\":\"https://mixednutz.net/outbox\","
				+ "\"preferredUsername\":\"Andy\"}";
		
		// Deserialize
		BaseObjectOrLink object = mapper.readValue(json, BaseObjectOrLink.class);
		
		assertTrue(object instanceof Person);
		Person person = (Person) object;
		assertEquals("https://mixednutz.net/user",person.getId().toString());
		assertEquals("https://mixednutz.net/inbox",person.getInbox().toString());
		assertEquals("https://mixednutz.net/outbox",person.getOutbox().toString());
		assertEquals("Andy",person.getPreferredUsername());
		
		//Re-serialize
		String actual = mapper.writeValueAsString(person);
		assertEquals(json, actual);
		
		//Serialize new object from scratch
		person = new Person();
		person.setContext(List.of(BaseObjectOrLink.CONTEXT));
		person.setId(URI.create("https://mixednutz.net/user"));
		person.setPreferredUsername("Andy");
		person.setInbox(URI.create("https://mixednutz.net/inbox"));
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
				+ "\"inbox\":\"https://mixednutz.net/inbox\","
				+ "\"outbox\":\"https://mixednutz.net/outbox\","
				+ "\"preferredUsername\":\"Andy\"}";
		
		// Deserialize
		BaseObjectOrLink object = mapper.readValue(json, BaseObjectOrLink.class);
		
		assertTrue(object instanceof Person);
		Person person = (Person) object;
		assertEquals("https://mixednutz.net/user",person.getId().toString());
		assertEquals("https://mixednutz.net/inbox",person.getInbox().toString());
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
		person.setContext(List.of(BaseObjectOrLink.CONTEXT));
		person.setId(URI.create("https://mixednutz.net/user"));
		person.setPreferredUsername("Andy");
		person.setInbox(URI.create("https://mixednutz.net/inbox"));
		person.setOutbox(URI.create("https://mixednutz.net/outbox"));
		icon = new ImageImpl();
		icon.setUrl("https://mixednutz.net/avatar.jpg");
		icon.setName("Andy's avatar");
		person.setIcon(List.of(icon));
		
		actual = mapper.writeValueAsString(person);
		assertEquals(json, actual);
	}
	
	@Test
	public void testPersonFromMastodon() throws JsonProcessingException {
		
		String json = "{\"@context\":["
					+ "\"https://www.w3.org/ns/activitystreams\","
					+ "\"https://w3id.org/security/v1\","
					+ "{\"manuallyApprovesFollowers\":\"as:manuallyApprovesFollowers\","
					+ "\"toot\":\"http://joinmastodon.org/ns#\","
					+ "\"featured\":{"
						+ "\"@id\":\"toot:featured\","
						+ "\"@type\":\"@id\"}"
					+ ",\"featuredTags\":{"
						+ "\"@id\":\"toot:featuredTags\","
						+ "\"@type\":\"@id\"},"
					+ "\"alsoKnownAs\":{"
						+ "\"@id\":\"as:alsoKnownAs\","
						+ "\"@type\":\"@id\"},"
					+ "\"movedTo\":{"
						+ "\"@id\":\"as:movedTo\","
						+ "\"@type\":\"@id\"},"
					+ "\"schema\":\"http://schema.org#\","
					+ "\"PropertyValue\":\"schema:PropertyValue\","
					+ "\"value\":\"schema:value\","
					+ "\"discoverable\":\"toot:discoverable\","
					+ "\"Device\":\"toot:Device\","
					+ "\"Ed25519Signature\":\"toot:Ed25519Signature\","
					+ "\"Ed25519Key\":\"toot:Ed25519Key\","
					+ "\"Curve25519Key\":\"toot:Curve25519Key\","
					+ "\"EncryptedMessage\":\"toot:EncryptedMessage\","
					+ "\"publicKeyBase64\":\"toot:publicKeyBase64\","
					+ "\"deviceId\":\"toot:deviceId\","
					+ "\"claim\":{\"@type\":\"@id\",\"@id\":\"toot:claim\"},"
					+ "\"fingerprintKey\":{"
						+ "\"@type\":\"@id\","
						+ "\"@id\":\"toot:fingerprintKey\"},"
					+ "\"identityKey\":{"
						+ "\"@type\":\"@id\","
						+ "\"@id\":\"toot:identityKey\"},"
					+ "\"devices\":{"
						+ "\"@type\":\"@id\","
						+ "\"@id\":\"toot:devices\"},"
					+ "\"messageFranking\":\"toot:messageFranking\","
					+ "\"messageType\":\"toot:messageType\","
					+ "\"cipherText\":\"toot:cipherText\","
					+ "\"suspended\":\"toot:suspended\"}],"
				+ "\"id\":\"https://universeodon.com/users/festaindctest\","
				+ "\"type\":\"Person\","
				+ "\"following\":\"https://universeodon.com/users/festaindctest/following\","
				+ "\"followers\":\"https://universeodon.com/users/festaindctest/followers\","
				+ "\"inbox\":\"https://universeodon.com/users/festaindctest/inbox\","
				+ "\"outbox\":\"https://universeodon.com/users/festaindctest/outbox\","
				+ "\"featured\":\"https://universeodon.com/users/festaindctest/collections/featured\","
				+ "\"featuredTags\":\"https://universeodon.com/users/festaindctest/collections/tags\","
				+ "\"preferredUsername\":\"festaindctest\","
				+ "\"name\":\"Andy\","
				+ "\"summary\":\"\","
				+ "\"url\":\"https://universeodon.com/@festaindctest\","
				+ "\"manuallyApprovesFollowers\":false,"
				+ "\"discoverable\":false,"
				+ "\"published\":\"2023-02-11T00:00:00Z\","
				+ "\"devices\":\"https://universeodon.com/users/festaindctest/collections/devices\","
				+ "\"publicKey\":{"
					+ "\"id\":\"https://universeodon.com/users/festaindctest#main-key\","
					+ "\"owner\":\"https://universeodon.com/users/festaindctest\","
					+ "\"publicKeyPem\":\"-----BEGIN PUBLIC KEY-----\\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyly+DZZ/ZenfA9iqSCv3\\nbovf25Mk23xi2ugZqsB7bV/TP5YjwmGlin/ZZIm+i0sIJpelknZdQyYzVLXwvUTk\\nKkqhReYXJF3y4Co3JIWJ47k3gjzD+4U5B4fKhch/BlnkQbpfA2vjAtw6Ql9IcjNc\\nctNJ2pr6w+W7RFmLkLj/I0cgzzo9Pc72LTL3s61ON8/wXMm+j1FytSsIkyXlMQbm\\nVJHMyNnNdDyCvn1suWGKOsvCagiwgs5aeOohQBMw02rFS7I3zJEpiy8n7IA51eP9\\n6aZ5NgdwQ+0w2xTERJ6OICPwGljFE/pe088mSHnUiwGKf8RL2dbHAuR9l6Ke+ann\\nDQIDAQAB\\n-----END PUBLIC KEY-----\\n\"},"
				+ "\"tag\":[],"
				+ "\"attachment\":[],"
				+ "\"endpoints\":{"
					+ "\"sharedInbox\":\"https://universeodon.com/inbox\"}}";
		
		// Deserialize
		BaseObjectOrLink object = mapper.readValue(json, BaseObjectOrLink.class);
				
		assertTrue(object instanceof Person);
		Person person = (Person) object;
		assertEquals("https://universeodon.com/users/festaindctest",person.getId().toString());
		assertEquals("https://universeodon.com/users/festaindctest/inbox",person.getInbox().toString());
		assertEquals("https://universeodon.com/users/festaindctest/outbox",person.getOutbox().toString());
		assertEquals("festaindctest",person.getPreferredUsername());
		
		
	}
}
