package com.payroll.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.payroll.model.*;
import com.payroll.session.Session;
import com.payroll.util.PasswordUtil;

public class AuthenticationService {

    private Map<String,User> users=new HashMap<>();
    
    public void loadUsersFromFile() {
    	try {
    		BufferedReader br = new BufferedReader(new FileReader("employee_data.txt"));
    		String line;
    		
    		while((line=br.readLine())!=null) {
    			
    			if(line.trim().isEmpty()) {
    				continue;
    			}
    			
    			String[] data = line.split(",");
    			if(data.length<6) {
    				System.out.println("Skipping invalid line: "+line);
    				continue;
    			}
    			String username = data[4];
    			String password = data[5];
    			
    			RegularEmployee emp = new RegularEmployee(username,"temp");
    			emp.setPasswordHash(password);
    			users.put(username, emp);
    		}
    		br.close();
    	}
    	catch(Exception e) {
    		System.out.println("Error loading Users");
    		e.printStackTrace();
    	}
    }

    public AuthenticationService(){

    	loadUsersFromFile();
    }

    public Session login(){

        Scanner sc=new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username=sc.nextLine();

        System.out.print("Enter Password: ");
        String password=sc.nextLine();

        User user=users.get(username);

        if(user!=null && user.authenticate(username,PasswordUtil.hash(password))){

            System.out.println("\nLogin Successful!");
            System.out.println("Role: "+user.getRole());

            showDashboard(user.getRole());

            return new Session(username);
        }

        System.out.println("Invalid Credentials");
        return null;
    }

    private void showDashboard(String role){

        System.out.println("\n===== DASHBOARD =====");

        if(role.equals("EMPLOYEE")){

            System.out.println("Employee Dashboard");
            System.out.println("View Payslip | Update Profile");
        }

        else{

            System.out.println("Manager Dashboard");
            System.out.println("Approve Leave | View Reports");
        }
    }
}