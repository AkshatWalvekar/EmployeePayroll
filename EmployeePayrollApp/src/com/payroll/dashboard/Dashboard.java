package com.payroll.dashboard;

import java.util.ArrayList;
import com.payroll.model.Employee;
import com.payroll.model.Payslip;

public interface Dashboard {

    void display(ArrayList<Payslip> payslips, Employee employee);

}