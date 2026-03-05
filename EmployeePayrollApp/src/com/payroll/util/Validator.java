package com.payroll.util;

import java.util.regex.Pattern;

import com.payroll.exception.EmailValidationException;
import com.payroll.exception.PhoneValidationException;
import com.payroll.exception.PasswordValidationException;
import com.payroll.exception.EmployeeIdValidationException;

public class Validator {

    private static String sanitize(String input) {
        return input.trim();
    }

    public static void validateEmail(String email) throws EmailValidationException {

        email = sanitize(email);

        String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";

        if(!Pattern.matches(pattern, email)) {
            throw new EmailValidationException("Invalid Email Format");
        }
    }

    public static void validatePhone(String phone) throws PhoneValidationException {

        phone = sanitize(phone);

        if(!phone.matches("[0-9]{10}")) {
            throw new PhoneValidationException("Phone must be 10 digits");
        }
    }

    public static void validatePassword(String password) throws PasswordValidationException {

        password = sanitize(password);

        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";

        if(!Pattern.matches(pattern, password)) {
            throw new PasswordValidationException(
                    "Password must contain uppercase, lowercase, number and special character");
        }
    }

    public static void validateEmployeeId(String empId) throws EmployeeIdValidationException {

        empId = sanitize(empId);

        if(!empId.matches("EMP-[0-9]{4}")) {
            throw new EmployeeIdValidationException("Employee ID must be in format EMP-XXXX");
        }
    }
}