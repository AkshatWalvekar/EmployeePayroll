package com.payroll.app;

import java.io.IOException;
import java.util.Scanner;

import com.payroll.exception.ValidationException;
import com.payroll.model.Employee;
import com.payroll.model.UserAccount;
import com.payroll.service.AuthenticationService;
import com.payroll.session.Session;
import com.payroll.util.Validator;

public class EmployeeApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;
        
        while(true) {

        System.out.println("===== EMPLOYEE PAYROLL SYSTEM =====");
        System.out.println("1. Employee Registration");
        System.out.println("2. Employee Login");
        System.out.println("3. Exit");

        System.out.print("Enter your choice: ");
        choice = sc.nextInt();
        sc.nextLine();

        switch(choice) {

        // ===== UC1 : Employee Registration =====
        case 1:

            try {

                System.out.println("\n=== USE CASE 1: EMPLOYEE REGISTRATION ===");

                System.out.print("Enter Employee ID (EMP-XXXX): ");
                String empId = sc.nextLine();
                Validator.validateEmpId(empId);

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Email: ");
                String email = sc.nextLine();
                Validator.validateEmail(email);

                System.out.print("Enter Phone (10 digits starting 6-9): ");
                String phone = sc.nextLine();
                Validator.validatePhone(phone);

                System.out.print("Create Username: ");
                String username = sc.nextLine();

                System.out.print("Create Password: ");
                String password = sc.nextLine();

                UserAccount account = new UserAccount(username, password);

                Employee emp = new Employee(empId, name, email, phone, account);

                emp.persist();

                System.out.println("\nEmployee Registered Successfully!");
                System.out.println(emp);

                System.out.println("\nData persisted in file: employee_data.txt");

            }
            catch (ValidationException e) {
                System.out.println("\nValidation Failed: " + e.getMessage());
            }
            catch (IOException e) {
                System.out.println("\nError saving employee data!");
            }

            break;

        // ===== UC2 : Authentication & Login =====
        case 2:

            System.out.println("\n=== USE CASE 2: EMPLOYEE AUTHENTICATION & LOGIN ===");

            AuthenticationService auth = new AuthenticationService();

            Session session = auth.login();

            if(session != null) {

                System.out.println("\n" + session);

                if(!session.isExpired()) {
                    System.out.println("Session active and valid.");
                }
                else {
                    System.out.println("Session expired.");
                }
            }

            break;
            
        case 3:
        	System.out.println("Exiting....");
        	sc.close();
        	System.exit(0);

        default:
            System.out.println("Invalid choice.");
        }
        
        }
        
    }
}