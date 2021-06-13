package com.holidaymakers.model;

public class IssueResponse {

    private Long id;
    
    private String subject;

    private String user_name;

    private String reply;

    public IssueResponse() {
    }

    public IssueResponse(Long id, String subject, String user_name, String reply) {
        this.id = id;
        this.subject = subject;
        this.user_name = user_name;
        this.reply = reply;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

}
