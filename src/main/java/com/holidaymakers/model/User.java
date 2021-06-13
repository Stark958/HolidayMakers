package com.holidaymakers.model;

import java.util.Date;

public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Long phone;
    private Date registered_date;
    private String role;
    public User() {
    }
    public User(Long id, String name, String email, String password, Long phone, Date registered_date, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.registered_date = registered_date;
        this.role = role;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) {
        this.phone = phone;
    }
    public Date getRegistered_date() {
        return registered_date;
    }
    public void setRegistered_date(Date registered_date) {
        this.registered_date = registered_date;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    
    
}
