package com.payroll.model;

import com.payroll.util.PasswordUtil;

public class RegularEmployee extends User{

    public RegularEmployee(String username,String password){
        super(username,password,"EMPLOYEE");
    }

    public boolean authenticate(String username,String passwordHash){

        return this.username.equals(username) &&
                this.passwordHash.equals(passwordHash);
    }
}