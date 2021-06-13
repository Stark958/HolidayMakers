package com.holidaymakers.model;

import java.util.Date;

public class Bookings {

    private Long id;
    private Date start_date;
    private int no_of_persons;
    private String food_type;
    private String accomodation;
    private String payment_mode;
    private String payment_info;
    private Double price;
    private Date booked_date;
    private String cancel_msg;
    private String status;
    private Long tour_id;
    private Long user_id;


    public Bookings() {
    }


    public Bookings(Long id, Date start_date, int no_of_persons, String food_type, String accomodation,
            String payment_mode, String payment_info, Double price, Date booked_date, String cancel_msg, String status,
            Long tour_id, Long user_id) {
        this.id = id;
        this.start_date = start_date;
        this.no_of_persons = no_of_persons;
        this.food_type = food_type;
        this.accomodation = accomodation;
        this.payment_mode = payment_mode;
        this.payment_info = payment_info;
        this.price = price;
        this.booked_date = booked_date;
        this.cancel_msg = cancel_msg;
        this.status = status;
        this.tour_id = tour_id;
        this.user_id = user_id;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Date getStart_date() {
        return start_date;
    }


    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }


    public int getNo_of_persons() {
        return no_of_persons;
    }


    public void setNo_of_persons(int no_of_persons) {
        this.no_of_persons = no_of_persons;
    }


    public String getFood_type() {
        return food_type;
    }


    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }


    public String getAccomodation() {
        return accomodation;
    }


    public void setAccomodation(String accomodation) {
        this.accomodation = accomodation;
    }


    public String getPayment_mode() {
        return payment_mode;
    }


    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }


    public String getPayment_info() {
        return payment_info;
    }


    public void setPayment_info(String payment_info) {
        this.payment_info = payment_info;
    }


    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price = price;
    }


    public Date getBooked_date() {
        return booked_date;
    }


    public void setBooked_date(Date booked_date) {
        this.booked_date = booked_date;
    }


    public String getCancel_msg() {
        return cancel_msg;
    }


    public void setCancel_msg(String cancel_msg) {
        this.cancel_msg = cancel_msg;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public Long getTour_id() {
        return tour_id;
    }


    public void setTour_id(Long tour_id) {
        this.tour_id = tour_id;
    }


    public Long getUser_id() {
        return user_id;
    }


    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }


    

    
    
    
}
