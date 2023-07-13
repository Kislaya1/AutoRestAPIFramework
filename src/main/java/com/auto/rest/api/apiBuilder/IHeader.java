package com.auto.rest.api.apiBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.immutables.value.Value;

import java.util.Map;
import java.util.Optional;

@Value.Immutable
@Value.Style(stagedBuilder = true, build = "createHeader")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface IHeader {
  @JsonProperty("Content-Type")
  Optional<String> requestContentType();

  @JsonProperty("Accept")
  Optional<String> responseContentType();

  @JsonProperty("Cookie")
  Optional<String> cookie();

  @JsonProperty("Authorization")
  Optional<String> authorization();

  default Map<String, String> fetchHeader() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new Jdk8Module());
    return objectMapper.convertValue(this, Map.class);
  }
}
