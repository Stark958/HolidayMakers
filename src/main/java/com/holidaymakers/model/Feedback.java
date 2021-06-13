package com.holidaymakers.model;

public class Feedback {

    private Long id;

    private String message;

    private Long user_id;

    public Feedback() {
    }

    public Feedback(String message, Long user_id) {
        this.message = message;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    
    
}
