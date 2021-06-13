package com.holidaymakers.model;

public class DashboardResponse {

    Long no_of_ucbookings;
    Long no_of_urIssues;
    Integer no_of_users;
    public DashboardResponse() {
    }
    public DashboardResponse(Long no_of_ucbookings, Long no_of_urIssues, Integer no_of_users) {
        this.no_of_ucbookings = no_of_ucbookings;
        this.no_of_urIssues = no_of_urIssues;
        this.no_of_users = no_of_users;
    }
    public Long getNo_of_ucbookings() {
        return no_of_ucbookings;
    }
    public void setNo_of_ucbookings(Long no_of_ucbookings) {
        this.no_of_ucbookings = no_of_ucbookings;
    }
    public Long getNo_of_urIssues() {
        return no_of_urIssues;
    }
    public void setNo_of_urIssues(Long no_of_urIssues) {
        this.no_of_urIssues = no_of_urIssues;
    }
    public Integer getNo_of_users() {
        return no_of_users;
    }
    public void setNo_of_users(Integer no_of_users) {
        this.no_of_users = no_of_users;
    }

    
    
}
