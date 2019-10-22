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
@JsonPropertyOrder({ "temp", "duration" })
public class MashTemp {

	@JsonProperty("temp")
	private Temp temp;
	@JsonProperty("duration")
	private Long duration;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("temp")
	public Temp getTemp() {
		return temp;
	}

	@JsonProperty("temp")
	public void setTemp(Temp temp) {
		this.temp = temp;
	}

	@JsonProperty("duration")
	public Long getDuration() {
		return duration;
	}

	@JsonProperty("duration")
	public void setDuration(Long duration) {
		this.duration = duration;
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