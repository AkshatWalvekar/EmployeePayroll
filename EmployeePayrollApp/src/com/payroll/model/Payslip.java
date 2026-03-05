package com.payroll.model;

public final class Payslip implements Cloneable{

    private Employee employee;              // Aggregation
    private SalaryComponents components;    // Composition
    private String month;

    public Payslip(Employee employee, SalaryComponents components, String month) {
        this.employee = employee;
        this.components = components;
        this.month = month;
    }
    
    public Employee getEmployee() {
    	return employee;
    }
    
    public SalaryComponents getComponents() {
    	return components;
    }
    
    public String getMonth() {
    	return month;
    }

    @Override
    public String toString() {

        return "\n========== PAYSLIP ==========\n"
                + "Month        : " + month + "\n"
                + "Employee ID  : " + employee.getEmpId() + "\n"
                + "Employee Name: " + employee.getName() + "\n\n"

                + "---- Earnings ----\n"
                + "Basic Salary : " + components.basicSalary + "\n"
                + "HRA          : " + components.hra + "\n"
                + "DA           : " + components.da + "\n"
                + "Allowances   : " + components.allowances + "\n\n"

                + "---- Deductions ----\n"
                + "PF           : " + components.pf + "\n"
                + "Tax          : " + components.tax + "\n\n"

                + "Net Pay      : " + components.netPay + "\n"
                + "==============================\n";
    }
    
    @Override
    public Object clone() {
    	
    	SalaryComponents compCopy = new SalaryComponents(components.basicSalary,components.hra,components.da,components.allowances);
    	
    	return new Payslip(employee,compCopy,month);
    }
}







