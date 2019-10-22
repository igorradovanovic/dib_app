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
@JsonPropertyOrder({
"malt",
"hops",
"yeast"
})
public class Ingredients {

@JsonProperty("malt")
private List<Malt> malt = null;
@JsonProperty("hops")
private List<Hop> hops = null;
@JsonProperty("yeast")
private String yeast;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("malt")
public List<Malt> getMalt() {
return malt;
}

@JsonProperty("malt")
public void setMalt(List<Malt> malt) {
this.malt = malt;
}

@JsonProperty("hops")
public List<Hop> getHops() {
return hops;
}

@JsonProperty("hops")
public void setHops(List<Hop> hops) {
this.hops = hops;
}

@JsonProperty("yeast")
public String getYeast() {
return yeast;
}

@JsonProperty("yeast")
public void setYeast(String yeast) {
this.yeast = yeast;
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