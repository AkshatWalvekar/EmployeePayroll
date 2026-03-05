package com.payroll.model;

import com.payroll.util.PasswordUtil;

public abstract class User {

    protected String username;
    protected String passwordHash;
    protected String role;

    public User(String username,String password,String role){

        this.username=username;
        this.passwordHash=PasswordUtil.hash(password);
        this.role=role;
    }
    
    public void setPasswordHash(String passwordHash) {
    	this.passwordHash = passwordHash;
    }

    public abstract boolean authenticate(String username,String password);

    public String getRole(){
        return role;
    }
}