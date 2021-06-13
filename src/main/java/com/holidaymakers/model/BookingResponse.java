package com.holidaymakers.model;

import java.util.Date;

public class BookingResponse {

    Long booking_id;

    Date start_date;
    
    String name;

    String tour_name;

    String status;

    

    public BookingResponse() {
    }



    public BookingResponse(Long booking_id, Date start_date, String name, String tour_name, String status) {
        this.booking_id = booking_id;
        this.start_date = start_date;
        this.name = name;
        this.tour_name = tour_name;
        this.status = status;
    }



    public Long getBooking_id() {
        return booking_id;
    }



    public void setBooking_id(Long booking_id) {
        this.booking_id = booking_id;
    }



    public Date getStart_date() {
        return start_date;
    }



    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getTour_name() {
        return tour_name;
    }



    public void setTour_name(String tour_name) {
        this.tour_name = tour_name;
    }



    public String getStatus() {
        return status;
    }



    public void setStatus(String status) {
        this.status = status;
    }

    
    

    
}
