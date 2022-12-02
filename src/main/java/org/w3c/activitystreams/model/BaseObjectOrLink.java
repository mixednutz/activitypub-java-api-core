package org.w3c.activitystreams.model;

import java.net.URI;
import java.time.ZonedDateTime;

import org.w3c.activitypub.util.PropertySerializer;
import org.w3c.activitystreams.ObjectOrLink;
import org.w3c.activitystreams.model.activity.Create;
import org.w3c.activitystreams.model.actor.Person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonTypeInfo (use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.EXISTING_PROPERTY,
	property = "type",
	visible = true)
@JsonSubTypes ({ 
	@Type (value = ObjectImpl.class),
	@Type (value = ActivityImpl.class),
	@Type (value = ActorImpl.class),
    @Type (value = CollectionImpl.class),
    @Type (value = Create.class),
    @Type (value = ImageImpl.class),
    @Type (value = LinkImpl.class),
    @Type (value = Note.class),
    @Type (value = OrderedCollectionImpl.class),
    @Type (value = OrderedCollectionPageImpl.class),
    @Type (value = Person.class)})
@JsonIgnoreProperties (ignoreUnknown = true)
@JsonPropertyOrder(value={"@context", "type", "id", "name"}, alphabetic=true)
public class BaseObjectOrLink implements org.w3c.activitystreams.Object, ObjectOrLink {
	
	public static final String CONTEXT = "https://www.w3.org/ns/activitystreams";
	public static final String PUBLIC = CONTEXT+"#Public";
	
	String _context;
	String type;
	URI id;
	String name;
	String url;
	String mediaType;
	String content;
	String summary;
	@JsonSerialize (using = PropertySerializer.class)
	BaseObjectOrLink attributedTo;
	@JsonSerialize (using = PropertySerializer.class)
	BaseObjectOrLink to;
	@JsonSerialize (using = PropertySerializer.class)
	BaseObjectOrLink cc;
	
	ZonedDateTime published;
	
	URI href;
	@JsonIgnore
	private Boolean isLink = false;
	
	public BaseObjectOrLink(String type) {
		super();
		this.type = type;
	}
	
	@JsonProperty("@context")	
	public String get_Context() {
		return _context;
	}
	public void set_Context(String context) {
		this._context = context;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public URI getId() {
		return id;
	}
	public void setId(URI id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public ZonedDateTime getPublished() {
		return published;
	}
	public void setPublished(ZonedDateTime published) {
		this.published = published;
	}
	
	public BaseObjectOrLink getAttributedTo() {
		return attributedTo;
	}

	public void setAttributedTo(BaseObjectOrLink attributedTo) {
		this.attributedTo = attributedTo;
	}

	public BaseObjectOrLink getTo() {
		return to;
	}

	public void setTo(BaseObjectOrLink to) {
		this.to = to;
	}

	public BaseObjectOrLink getCc() {
		return cc;
	}

	public void setCc(BaseObjectOrLink cc) {
		this.cc = cc;
	}

	@JsonIgnore
	public Boolean isLink() {
		return this.isLink;
	}

	@JsonIgnore
	protected void setLink(boolean isLink) {
		this.isLink = isLink;
	}

	public URI getHref() {
		return href;
	}

	public void setHref(URI href) {
		this.href = href;
	}
		
}
