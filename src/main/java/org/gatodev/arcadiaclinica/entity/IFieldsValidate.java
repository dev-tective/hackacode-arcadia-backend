package org.gatodev.arcadiaclinica.entity;

import java.util.regex.Pattern;

public interface IFieldsValidate {
    default boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return !pattern.matcher(email).matches();
    }

    default boolean isValidDni(String dni) {
        if (dni.length() != 8) return false;

        try {
            Long.parseLong(dni);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    default boolean isValidNumberPhone(String numberPhone) {
        if (numberPhone.length() != 9) return false;
        if (numberPhone.charAt(0) != '9') return false;

        try {
            Long.parseLong(numberPhone);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
