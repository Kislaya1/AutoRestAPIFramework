package com.rest.api.pro.utils;

import com.rest.api.pro.pojo.booking.Booking;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public interface DataMockUtils<T> {
    static DataMockUtils<Booking> fetchBookingRequestPojo() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        return () -> podamFactory.manufacturePojo(Booking.class);
    }

    T mockData();
}
