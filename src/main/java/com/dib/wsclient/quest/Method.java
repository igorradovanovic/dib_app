package com.dib.wsclient.quest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "mash_temp", "fermentation", "twist" })
public class Method {

	@JsonProperty("mash_temp")
	private List<MashTemp> mashTemp = null;
	@JsonProperty("fermentation")
	private Fermentation fermentation;
	@JsonProperty("twist")
	private Object twist;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("mash_temp")
	public List<MashTemp> getMashTemp() {
		return mashTemp;
	}

	@JsonProperty("mash_temp")
	public void setMashTemp(List<MashTemp> mashTemp) {
		this.mashTemp = mashTemp;
	}

	@JsonProperty("fermentation")
	public Fermentation getFermentation() {
		return fermentation;
	}

	@JsonProperty("fermentation")
	public void setFermentation(Fermentation fermentation) {
		this.fermentation = fermentation;
	}

	@JsonProperty("twist")
	public Object getTwist() {
		return twist;
	}

	@JsonProperty("twist")
	public void setTwist(Object twist) {
		this.twist = twist;
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
