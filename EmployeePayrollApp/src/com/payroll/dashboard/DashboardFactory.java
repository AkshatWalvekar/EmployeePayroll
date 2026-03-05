package com.payroll.dashboard;

public class DashboardFactory {

    public static Dashboard getDashboard(String role) {

        if(role.equalsIgnoreCase("EMPLOYEE")) {

            return new EmployeeDashboard();

        }
        else if(role.equalsIgnoreCase("MANAGER")) {

            return new ManagerDashboard();

        }

        return null;
    }

}
