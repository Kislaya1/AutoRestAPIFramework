package com.auto.rest.api.pojo.booking.entity;

import com.auto.rest.api.pojo.strategy.DateStrategy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookingDates {
  @PodamStrategyValue(value = DateStrategy.class)
  @JsonProperty("checkin")
  private String checkIn;

  @PodamStrategyValue(value = DateStrategy.class)
  @JsonProperty("checkout")
  private String checkOut;
}
