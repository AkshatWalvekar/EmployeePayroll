package com.payroll.app;

import java.io.IOException;
import java.util.Scanner;

import com.payroll.exception.ValidationException;
import com.payroll.model.Employee;
import com.payroll.model.UserAccount;
import com.payroll.service.AuthenticationService;
import com.payroll.session.Session;
import com.payroll.util.Validator;
import com.payroll.service.PayrollService;
import com.payroll.model.Payslip;
import com.payroll.util.DownloadToken;
import com.payroll.service.FileService;
import com.payroll.model.SalaryComponents;

public class EmployeeApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;
        
        while(true) {

        System.out.println("===== EMPLOYEE PAYROLL SYSTEM =====");
        System.out.println("1. Employee Registration");
        System.out.println("2. Employee Login");
        System.out.println("3. Generate Payslip");
        System.out.println("4. Download Payslip");
        System.out.println("5. Exit");

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

        	System.out.println("\n=== USE CASE 3 : PAYSLIP GENERATION ===");

        	System.out.print("Enter Employee ID: ");
        	String id = sc.nextLine();

        	System.out.print("Enter Employee Name: ");
        	String name = sc.nextLine();

        	System.out.print("Enter Month: ");
        	String month = sc.nextLine();

        	System.out.print("Enter Basic Salary: ");
        	double basic = sc.nextDouble();

        	System.out.print("Enter HRA: ");
        	double hra = sc.nextDouble();

        	System.out.print("Enter DA: ");
        	double da = sc.nextDouble();

        	System.out.print("Enter Allowances: ");
        	double allowances = sc.nextDouble();
        	sc.nextLine();

        	Employee emp = new Employee(id, name, "", "", null);

        	PayrollService service = new PayrollService();

        	Payslip payslip = service.generatePayslip(emp, month, basic, hra, da, allowances);

        	System.out.println(payslip);

        	break;
        	
        case 4:

            System.out.println("\n=== USE CASE 4 : PAYSLIP PRINT / DOWNLOAD ===");
            
            Employee emp1 = new Employee("EMP-1010","John David","","",null);
            
            SalaryComponents comp = new SalaryComponents(40000,5000,3000,500);
            Payslip original =
                    new Payslip(emp1,comp,"January 2026");

            System.out.println("\nOriginal Payslip:");
            System.out.println(original);

            try {

                Payslip copy = (Payslip) original.clone();

                System.out.println("\nVerified: Download copy is equal to original.");

                DownloadToken token = new DownloadToken();

                if(token.isExpired()) {
                    System.out.println("Download expired.");
                    break;
                }

                FileService fs = new FileService();

                String txt = fs.savePayslipAsText(copy);
                String pdf = fs.savePayslipAsPdf(copy);

                System.out.println("\nPayslip Download Successful.");
                System.out.println("Saved as text file: " + txt);
                System.out.println("Saved as PDF file: " + pdf);

                System.out.println("\n--- Printed Payslip ---");
                System.out.println(copy);

            }
            catch(Exception e) {
                System.out.println("Error during payslip download.");
            }

        break;
            
        case 5:
        	System.out.println("Exiting....");
        	sc.close();
        	System.exit(0);

        default:
            System.out.println("Invalid choice.");
        }
        
        }
        
    }
}