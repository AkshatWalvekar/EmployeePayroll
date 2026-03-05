package com.payroll.model;

import com.payroll.util.PasswordUtil;

public class UserAccount {

    private String username;
    private String passwordHash;

    public UserAccount(String username, String password) {

        this.username = username;
        this.passwordHash = PasswordUtil.hash(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}