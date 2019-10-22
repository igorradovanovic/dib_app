package com.dib.wsclient.quest;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "amount", "add", "attribute" })
public class Hop {

	@JsonProperty("name")
	private String name;
	@JsonProperty("amount")
	private Amount_ amount;
	@JsonProperty("add")
	private String add;
	@JsonProperty("attribute")
	private String attribute;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("amount")
	public Amount_ getAmount() {
		return amount;
	}

	@JsonProperty("amount")
	public void setAmount(Amount_ amount) {
		this.amount = amount;
	}

	@JsonProperty("add")
	public String getAdd() {
		return add;
	}

	@JsonProperty("add")
	public void setAdd(String add) {
		this.add = add;
	}

	@JsonProperty("attribute")
	public String getAttribute() {
		return attribute;
	}

	@JsonProperty("attribute")
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}