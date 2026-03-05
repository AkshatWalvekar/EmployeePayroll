package com.payroll.util;

import java.util.regex.Pattern;
import com.payroll.exception.ValidationException;

public class Validator {

    // Validate Email using regex
    public static void validateEmail(String email) throws ValidationException {

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!Pattern.matches(emailRegex, email)) {
            throw new ValidationException("Invalid Email Format!");
        }
    }

    // Validate Phone (Indian)
    public static void validatePhone(String phone) throws ValidationException {

        String phoneRegex = "^[6-9][0-9]{9}$";

        if (!Pattern.matches(phoneRegex, phone)) {
            throw new ValidationException("Phone must be 10 digits starting with 6-9!");
        }
    }

    // Validate Employee ID
    public static void validateEmpId(String empId) throws ValidationException {

        String empRegex = "^EMP-[0-9]{4}$";

        if (!Pattern.matches(empRegex, empId)) {
            throw new ValidationException("Employee ID must be like EMP-0001");
        }
    }
}