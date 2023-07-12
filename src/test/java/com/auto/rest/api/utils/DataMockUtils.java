package com.auto.rest.api.utils;

import com.auto.rest.api.pojo.booking.Booking;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public interface DataMockUtils<T> {
  static DataMockUtils<Booking> fetchBookingRequestPojo() {
    PodamFactory podamFactory = new PodamFactoryImpl();
    return () -> podamFactory.manufacturePojo(Booking.class);
  }

  T mockData();
}
