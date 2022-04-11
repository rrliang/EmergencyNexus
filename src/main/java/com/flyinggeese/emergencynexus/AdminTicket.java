package com.flyinggeese.emergencynexus;

public class AdminTicket {

    private String email, username, issue, status, admin;
    private int ticketId;

    AdminTicket(int ticketId, String email, String username, String issue, String status, String admin) {
        this.ticketId = ticketId;
        this.email = email;
        this.username = username;
        this.issue = issue;
        this.status = status;
        this.admin = admin;
    }

    public int getId() {
        return ticketId;
    }

    public void setId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getIssue() {
        return issue;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
