package com.payroll.model;

public class UserAccount {

    private String username;
    private String password;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    //getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}