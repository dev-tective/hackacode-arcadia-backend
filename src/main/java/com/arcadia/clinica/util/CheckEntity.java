package com.arcadia.clinica.util;

public interface CheckEntity {
    default boolean verificateStringField(String field) {
        return field == null || field.isEmpty();
    }
}
