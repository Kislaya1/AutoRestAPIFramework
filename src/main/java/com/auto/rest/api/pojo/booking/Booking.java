package com.auto.rest.api.pojo.booking;

import com.auto.rest.api.pojo.booking.entity.BookingDates;
import com.auto.rest.api.pojo.strategy.MetaDataStrategy;
import com.auto.rest.api.pojo.strategy.NameStrategy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uk.co.jemos.podam.common.PodamLongValue;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Booking {
  @PodamStrategyValue(value = NameStrategy.class)
  @JsonProperty("firstname")
  private String firstName;

  @PodamStrategyValue(value = NameStrategy.class)
  @JsonProperty("lastname")
  private String lastName;

  @JsonProperty("totalprice")
  @PodamLongValue(minValue = 1_000L, maxValue = 1_00_000L)
  private long totalPrice;

  @JsonProperty("depositpaid")
  private boolean depositPaid;

  @JsonProperty("bookingdates")
  private BookingDates bookingDates;

  @PodamStrategyValue(value = MetaDataStrategy.class)
  @JsonProperty("additionalneeds")
  private String additionalNeeds;
}
