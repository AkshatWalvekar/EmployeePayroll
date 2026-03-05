package com.payroll.app;

import java.util.Scanner;
import java.io.IOException;

import com.payroll.model.Employee;
import com.payroll.model.UserAccount;
import com.payroll.util.Validator;
import com.payroll.exception.ValidationException;

public class EmployeeApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        //use case 1 
        System.out.println("=== USE CASE 1: EMPLOYEE REGISTRATION ===");

        try {

            System.out.print("Enter Employee ID (EMP-XXXX): ");
            String empId = sc.nextLine();
            Validator.validateEmpId(empId);
            
            //take name as input
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            
            //take email as input
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            Validator.validateEmail(email);

            //take phone no. as input
            System.out.print("Enter Phone (10 digits starting 6-9): ");
            String phone = sc.nextLine();
            Validator.validatePhone(phone);

            System.out.print("Create Username: ");
            String username = sc.nextLine();

            System.out.print("Create Password: ");
            String password = sc.nextLine();

            // Create objects
            UserAccount account = new UserAccount(username, password);

            Employee emp = new Employee(empId, name, email, phone, account);

            // Save data
            emp.persist();

            // Display confirmation message
            System.out.println(emp);

            System.out.println("\nData persisted in file: employee_data.txt");

        }
        catch (ValidationException e) {
            System.out.println("\nValidation Failed: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("\nError saving employee data!");
        }

        sc.close();
    }
}