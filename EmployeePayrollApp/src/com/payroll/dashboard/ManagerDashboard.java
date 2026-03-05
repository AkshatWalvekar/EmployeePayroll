package com.payroll.dashboard;

import java.util.ArrayList;

import com.payroll.model.Employee;
import com.payroll.model.Payslip;

public class ManagerDashboard implements Dashboard {

    @Override
    public void display(ArrayList<Payslip> payslips, Employee employee) {

        System.out.println("\n=== MANAGER DASHBOARD ===");

        System.out.println("Manager : " + employee.getName());

        System.out.println("Dashboard Type : " + this.getClass().getName());

        double total = 0;

        for(Payslip p : payslips) {

            total += p.getComponents().netPay;

        }

        System.out.println("\nTeam Total YTD Earnings : " + total);

    }

}