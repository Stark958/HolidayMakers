package com.holidaymakers.model;

public class BookingDetails {

    private Long id;

    private String name;

    private int age;

    private String gender;

    private Long id_proof_number;

    private Long booking_id;

    public BookingDetails() {
    }

    public BookingDetails(String name, int age, String gender, Long id_proof_number) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.id_proof_number = id_proof_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getId_proof_number() {
        return id_proof_number;
    }

    public void setId_proof_number(Long id_proof_number) {
        this.id_proof_number = id_proof_number;
    }

    public Long getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Long booking_id) {
        this.booking_id = booking_id;
    }

    
    
}
