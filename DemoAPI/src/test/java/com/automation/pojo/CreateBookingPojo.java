package com.automation.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingPojo {

    String firstname;
    String lastname;
    String totalprice;
    String depositpaid;
    BookingDates bookingdates;
    String additionalneeds;
}
