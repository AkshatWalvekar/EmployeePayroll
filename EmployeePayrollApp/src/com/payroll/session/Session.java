package com.payroll.session;

public class Session {

    private String username;
    private long loginTime;
    private long timeoutMillis=300000;

    public Session(String username){
        this.username=username;
        loginTime=System.currentTimeMillis();
    }

    public boolean isExpired(){

        long now=System.currentTimeMillis();
        return (now-loginTime)>timeoutMillis;
    }

    public String toString(){
        return "Session active for user: "+username;
    }
}