package com.holidaymakers.model;

public class Issue {
    private Long id;
    
    private String subject;

    private String message;

    private String reply;

    private Long user_id;

    public Issue() {
    }

    public Issue(Long id, String subject, String message, String reply, Long user_id) {
        this.id = id;
        this.subject = subject;
        this.message = message;
        this.reply = reply;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    
}
