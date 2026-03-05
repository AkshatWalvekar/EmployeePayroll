package com.payroll.service;

import java.io.FileWriter;
import java.io.IOException;
import com.payroll.model.Payslip;

public class FileService {

    public String savePayslipAsText(Payslip payslip) throws IOException {

        String fileName =
                "Payslip_" + payslip.getEmployee().getEmpId() + "_"
                        + System.currentTimeMillis() + ".txt";

        FileWriter fw = new FileWriter(fileName);
        fw.write(payslip.toString());
        fw.close();

        return fileName;
    }

    public String savePayslipAsPdf(Payslip payslip) throws IOException {

        String fileName =
                "Payslip_" + payslip.getEmployee().getEmpId() + "_"
                        + System.currentTimeMillis() + ".pdf";

        FileWriter fw = new FileWriter(fileName);
        fw.write(payslip.toString());
        fw.close();

        return fileName;
    }
}
